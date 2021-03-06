package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IAreaTypeDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothLocalBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryGroupDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.ICasteInsertTypeDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICustomVoterDAO;
import com.itgrids.partyanalyst.dao.ICustomVoterGroupDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHHLeaderBooksDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothPublicationDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IHouseHoldVoterDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.ILanguageDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.ILocalityDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.IPartialBoothPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserRelationDAO;
import com.itgrids.partyanalyst.dao.IUserStateAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserVoterCategoryDAO;
import com.itgrids.partyanalyst.dao.IUserVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterDataAvailableConstituenciesDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyRelationDAO;
import com.itgrids.partyanalyst.dao.IVoterFlagDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationTempDAO;
import com.itgrids.partyanalyst.dao.IVoterNamesDAO;
import com.itgrids.partyanalyst.dao.IVoterNamesTempDAO;
import com.itgrids.partyanalyst.dao.IVoterPartyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dao.IVoterStatusDAO;
import com.itgrids.partyanalyst.dao.IVoterTempDAO;
import com.itgrids.partyanalyst.dao.IWardDAO;
import com.itgrids.partyanalyst.dao.hibernate.HHBoothLeaderDAO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CastLocationVO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.CrossVotedMandalVO;
import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.DataVerificationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.HHSurveyVO;
import com.itgrids.partyanalyst.dto.ImportantFamiliesInfoVo;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.BoothVoterVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Caste;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.CustomVoter;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.HHBoothLeader;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.HamletBooth;
import com.itgrids.partyanalyst.model.HamletBoothElection;
import com.itgrids.partyanalyst.model.HamletBoothPublication;
import com.itgrids.partyanalyst.model.HouseHoldVoter;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Locality;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.PartialBoothPanchayat;
import com.itgrids.partyanalyst.model.PublicationDate;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.UserVoterCategory;
import com.itgrids.partyanalyst.model.UserVoterCategoryValue;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterAgeInfo;
import com.itgrids.partyanalyst.model.VoterAgeRange;
import com.itgrids.partyanalyst.model.VoterCastBasicInfo;
import com.itgrids.partyanalyst.model.VoterCastInfo;
import com.itgrids.partyanalyst.model.VoterCategoryValue;
import com.itgrids.partyanalyst.model.VoterFamilyInfo;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.model.VoterModification;
import com.itgrids.partyanalyst.model.VoterNames;
import com.itgrids.partyanalyst.model.VoterNamesTemp;
import com.itgrids.partyanalyst.model.VoterStatus;
import com.itgrids.partyanalyst.model.VoterTemp;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IPdfReportsService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.social.service.ISocialService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class VotersAnalysisService implements IVotersAnalysisService{
	private static final Logger log = Logger.getLogger(VotersAnalysisService.class);

	private IVoterCategoryValueDAO voterCategoryValueDAO;
	private IBoothDAO boothDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IRegionServiceData regionServiceDataImp;
	private IPanchayatDAO panchayatDAO;
	private IHamletBoothPublicationDAO hamletBoothPublicationDAO;
	private IConstituencyDAO constituencyDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private ITehsilDAO tehsilDAO;
	private IVoterDAO voterDAO;
	private IStaticDataService staticDataService;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IPartyDAO partyDAO;
	private IUserDAO userDAO;
	private IVoterTempDAO voterTempDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private ISocialService socialService;
	private IUserVoterCategoryDAO userVoterCategoryDAO;
	private IUserVoterCategoryValueDAO userVoterCategoryValueDAO;
	private TransactionTemplate transactionTemplate = null;
	private ICasteStateDAO casteStateDAO;
	//private IVoterCategoryValues voterCategoryValues;
	private IPublicationDateDAO publicationDateDAO;
	private IUserStateAccessInfoDAO userStateAccessInfoDAO;
	private ICasteCategoryGroupDAO casteCategoryGroupDAO;
	private ICasteDAO casteDAO;
	private IStateDAO stateDAO;
	private IElectionDAO electionDAO;
    private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IVoterReportLevelDAO voterReportLevelDAO;
	private IConstituencyPageService constituencyPageService;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IVoterAgeInfoDAO voterAgeInfoDAO;
    private IVoterAgeRangeDAO voterAgeRangeDAO;
    private IVoterFamilyInfoDAO voterFamilyInfoDAO;
    private IVoterFamilyRangeDAO voterFamilyRangeDAO;
    private IVillageBoothElectionDAO villageBoothElectionDAO;
    private IPublicationDateDAO publicationDAO;
    private ICandidateBoothResultDAO candidateBoothResultDAO;
    private IUserRelationDAO userRelationDAO;
    private ILocalityDAO localityDAO;
    private IHamletDAO hamletDAO;
    private ILocalElectionBodyDAO localElectionBodyDAO;
    private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
    private IBoothLocalBodyWardDAO boothLocalBodyWardDAO;
    private ILocalElectionBodyWardDAO localElectionBodyWardDAO;
    private IVoterModificationTempDAO voterModificationTempDAO;
    private IVoterModificationDAO voterModificationDAO;
    private IInfluencingPeopleDAO influencingPeopleDAO;
    private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
    private INominationDAO nominationDAO;
    private ICadreDAO cadreDAO;
    private ICandidateDAO candidateDAO;
    private IPanchayatHamletDAO panchayatHamletDAO ;
    private IWardDAO wardDAO;
    private IAssemblyLocalElectionBodyDAO assemblylocalElectionBodyDAO;
    private IVoterCastInfoDAO voterCastInfoDAO;
    private IVoterPartyInfoDAO voterPartyInfoDAO;
    private IVoterCastBasicInfoDAO voterCastBasicInfoDAO;
    private IVoterReportService voterReportService;
    private IVoterStatusDAO voterStatusDAO;
    private IDistrictDAO districtDAO;
    private ICustomVoterDAO customVoterDAO;
    private IAreaTypeDAO areaTypeDAO;
    private ICustomVoterGroupDAO customVoterGroupDAO;
    private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
    private IDelimitationConstituencyDAO delimitationConstituencyDAO;
    private IUserAddressDAO userAddressDAO;
    private IPartialBoothPanchayatDAO partialBoothPanchayatDAO;
    private IVoterDataAvailableConstituenciesDAO voterDataAvailableConstituenciesDAO;
    private IHamletBoothDAO hamletBoothDAO;
    private IVoterFlagDAO voterFlagDAO;
    private ICasteInsertTypeDAO casteInsertTypeDAO;
    private IVoterFamilyRelationDAO voterFamilyRelationDAO;
    private IHouseHoldVoterDAO houseHoldVoterDAO;
    private HHSurveyVO houseHoldVoterChildVO;
    private HHBoothLeaderDAO hhBoothLeaderDAO;
    private IVoterNamesTempDAO voterNamesTempDAO;
    private ILanguageDAO languageDAO;
    private IVoterNamesDAO voterNamesDAO;
    private IPdfReportsService pdfReportService;
    
    @Autowired private IHHLeaderBooksDAO hhLeaderBooksDAO;
    @Autowired private ITdpCadreDAO tdpCadreDAO;
    

	public void setPdfReportService(IPdfReportsService pdfReportService) {
		this.pdfReportService = pdfReportService;
	}

	public IVoterNamesDAO getVoterNamesDAO() {
		return voterNamesDAO;
	}

	public void setVoterNamesDAO(IVoterNamesDAO voterNamesDAO) {
		this.voterNamesDAO = voterNamesDAO;
	}

	public ILanguageDAO getLanguageDAO() {
		return languageDAO;
	}

	public void setLanguageDAO(ILanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
	}

	public IVoterNamesTempDAO getVoterNamesTempDAO() {
		return voterNamesTempDAO;
	}

	public void setVoterNamesTempDAO(IVoterNamesTempDAO voterNamesTempDAO) {
		this.voterNamesTempDAO = voterNamesTempDAO;
	}

	public HHBoothLeaderDAO getHhBoothLeaderDAO() {
		return hhBoothLeaderDAO;
	}

	public void setHhBoothLeaderDAO(HHBoothLeaderDAO hhBoothLeaderDAO) {
		this.hhBoothLeaderDAO = hhBoothLeaderDAO;
	}

	public HHSurveyVO getHouseHoldVoterChildVO() {
		return houseHoldVoterChildVO;
	}

	public void setHouseHoldVoterChildVO(HHSurveyVO houseHoldVoterChildVO) {
		this.houseHoldVoterChildVO = houseHoldVoterChildVO;
	}

	public IHouseHoldVoterDAO getHouseHoldVoterDAO() {
		return houseHoldVoterDAO;
	}

	public void setHouseHoldVoterDAO(IHouseHoldVoterDAO houseHoldVoterDAO) {
		this.houseHoldVoterDAO = houseHoldVoterDAO;
	}

	public IVoterFamilyRelationDAO getVoterFamilyRelationDAO() {
		return voterFamilyRelationDAO;
	}

	public void setVoterFamilyRelationDAO(
			IVoterFamilyRelationDAO voterFamilyRelationDAO) {
		this.voterFamilyRelationDAO = voterFamilyRelationDAO;
	}

	public ICasteInsertTypeDAO getCasteInsertTypeDAO() {
		return casteInsertTypeDAO;
	}

	public void setCasteInsertTypeDAO(ICasteInsertTypeDAO casteInsertTypeDAO) {
		this.casteInsertTypeDAO = casteInsertTypeDAO;
	}

	public IVoterFlagDAO getVoterFlagDAO() {
		return voterFlagDAO;
	}

	public void setVoterFlagDAO(IVoterFlagDAO voterFlagDAO) {
		this.voterFlagDAO = voterFlagDAO;
	}

	public IVoterDataAvailableConstituenciesDAO getVoterDataAvailableConstituenciesDAO() {
	return voterDataAvailableConstituenciesDAO;
}

public void setVoterDataAvailableConstituenciesDAO(
		IVoterDataAvailableConstituenciesDAO voterDataAvailableConstituenciesDAO) {
	this.voterDataAvailableConstituenciesDAO = voterDataAvailableConstituenciesDAO;
}

	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
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

	public ICustomVoterGroupDAO getCustomVoterGroupDAO() {
		return customVoterGroupDAO;
	}

	public void setCustomVoterGroupDAO(ICustomVoterGroupDAO customVoterGroupDAO) {
		this.customVoterGroupDAO = customVoterGroupDAO;
	}

	public IAreaTypeDAO getAreaTypeDAO() {
		return areaTypeDAO;
	}

	public void setAreaTypeDAO(IAreaTypeDAO areaTypeDAO) {
		this.areaTypeDAO = areaTypeDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IVoterStatusDAO getVoterStatusDAO() {
		return voterStatusDAO;
	}

	public void setVoterStatusDAO(IVoterStatusDAO voterStatusDAO) {
		this.voterStatusDAO = voterStatusDAO;
	}

	public IVoterCastBasicInfoDAO getVoterCastBasicInfoDAO() {
		return voterCastBasicInfoDAO;
	}

	public void setVoterCastBasicInfoDAO(
			IVoterCastBasicInfoDAO voterCastBasicInfoDAO) {
		this.voterCastBasicInfoDAO = voterCastBasicInfoDAO;
	}

	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
	}

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}

	public IVoterPartyInfoDAO getVoterPartyInfoDAO() {
		return voterPartyInfoDAO;
	}

	public void setVoterPartyInfoDAO(IVoterPartyInfoDAO voterPartyInfoDAO) {
		this.voterPartyInfoDAO = voterPartyInfoDAO;
	}

		public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
		return assemblyLocalElectionBodyWardDAO;
	}

	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}

   	public IInfluencingPeopleDAO getInfluencingPeopleDAO() {
		return influencingPeopleDAO;
	}

	public void setInfluencingPeopleDAO(IInfluencingPeopleDAO influencingPeopleDAO) {
		this.influencingPeopleDAO = influencingPeopleDAO;
	}

	public IVoterModificationDAO getVoterModificationDAO() {
		return voterModificationDAO;
	}

	public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
		this.voterModificationDAO = voterModificationDAO;
	}

	public IVoterModificationTempDAO getVoterModificationTempDAO() {
		return voterModificationTempDAO;
	}

	public void setVoterModificationTempDAO(
			IVoterModificationTempDAO voterModificationTempDAO) {
		this.voterModificationTempDAO = voterModificationTempDAO;
	}
    
   	public IAssemblyLocalElectionBodyDAO getAssemblylocalElectionBodyDAO() {
		return assemblylocalElectionBodyDAO;
	}

	public void setAssemblylocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblylocalElectionBodyDAO) {
		this.assemblylocalElectionBodyDAO = assemblylocalElectionBodyDAO;
	}

	public IWardDAO getWardDAO() {
		return wardDAO;
	}

	public void setWardDAO(IWardDAO wardDAO) {
		this.wardDAO = wardDAO;
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

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public ILocalityDAO getLocalityDAO() {
		return localityDAO;
	}

	public void setLocalityDAO(ILocalityDAO localityDAO) {
		this.localityDAO = localityDAO;
	}

	public IPublicationDateDAO getPublicationDAO() {
   		return publicationDAO;
   	}

   	public void setPublicationDAO(IPublicationDateDAO publicationDAO) {
   		this.publicationDAO = publicationDAO;
   	}
    
	public IVoterCategoryValueDAO getVoterCategoryValueDAO() {
		return voterCategoryValueDAO;
	}

	public void setVoterCategoryValueDAO(
			IVoterCategoryValueDAO voterCategoryValueDAO) {
		this.voterCategoryValueDAO = voterCategoryValueDAO;
	}

	public IUserVoterCategoryDAO getUserVoterCategoryDAO() {
		return userVoterCategoryDAO;
	}

	public void setUserVoterCategoryDAO(IUserVoterCategoryDAO userVoterCategoryDAO) {
		this.userVoterCategoryDAO = userVoterCategoryDAO;
	}

	public IUserVoterCategoryValueDAO getUserVoterCategoryValueDAO() {
		return userVoterCategoryValueDAO;
	}

	public void setUserVoterCategoryValueDAO(
			IUserVoterCategoryValueDAO userVoterCategoryValueDAO) {
		this.userVoterCategoryValueDAO = userVoterCategoryValueDAO;
	}

	public ISocialService getSocialService() {
		return socialService;
	}

	public void setSocialService(ISocialService socialService) {
		this.socialService = socialService;
	}

	public IVoterTempDAO getVoterTempDAO() {
		return voterTempDAO;
	}

	public void setVoterTempDAO(IVoterTempDAO voterTempDAO) {
		this.voterTempDAO = voterTempDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public ICasteStateDAO getCasteStateDAO() {
		return casteStateDAO;
	}

	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}
	
	public IUserStateAccessInfoDAO getUserStateAccessInfoDAO() {
		return userStateAccessInfoDAO;
	}
	public void setUserStateAccessInfoDAO(
			IUserStateAccessInfoDAO userStateAccessInfoDAO) {
		this.userStateAccessInfoDAO = userStateAccessInfoDAO;
	}
	public ICasteCategoryGroupDAO getCasteCategoryGroupDAO() {
		return casteCategoryGroupDAO;
	}
	public void setCasteCategoryGroupDAO(
			ICasteCategoryGroupDAO casteCategoryGroupDAO) {
		this.casteCategoryGroupDAO = casteCategoryGroupDAO;
	}
	
	public ICasteDAO getCasteDAO() {
		return casteDAO;
	}

	public void setCasteDAO(ICasteDAO casteDAO) {
		this.casteDAO = casteDAO;
	}
	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}

	
	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public IVoterReportLevelDAO getVoterReportLevelDAO() {
		return voterReportLevelDAO;
	}

	public void setVoterReportLevelDAO(IVoterReportLevelDAO voterReportLevelDAO) {
		this.voterReportLevelDAO = voterReportLevelDAO;
	}

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public IVoterAgeInfoDAO getVoterAgeInfoDAO() {
		return voterAgeInfoDAO;
	}

	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}

	public IVoterAgeRangeDAO getVoterAgeRangeDAO() {
		return voterAgeRangeDAO;
	}

	public void setVoterAgeRangeDAO(IVoterAgeRangeDAO voterAgeRangeDAO) {
		this.voterAgeRangeDAO = voterAgeRangeDAO;
	}

	public IVoterFamilyInfoDAO getVoterFamilyInfoDAO() {
		return voterFamilyInfoDAO;
	}

	public void setVoterFamilyInfoDAO(IVoterFamilyInfoDAO voterFamilyInfoDAO) {
		this.voterFamilyInfoDAO = voterFamilyInfoDAO;
	}

	public IVoterFamilyRangeDAO getVoterFamilyRangeDAO() {
		return voterFamilyRangeDAO;
	}

	public void setVoterFamilyRangeDAO(IVoterFamilyRangeDAO voterFamilyRangeDAO) {
		this.voterFamilyRangeDAO = voterFamilyRangeDAO;
	}

	public IVillageBoothElectionDAO getVillageBoothElectionDAO() {
		return villageBoothElectionDAO;
	}

	public void setVillageBoothElectionDAO(
			IVillageBoothElectionDAO villageBoothElectionDAO) {
		this.villageBoothElectionDAO = villageBoothElectionDAO;
	}

	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	
	public IBoothLocalBodyWardDAO getBoothLocalBodyWardDAO() {
		return boothLocalBodyWardDAO;
	}

	public void setBoothLocalBodyWardDAO(
			IBoothLocalBodyWardDAO boothLocalBodyWardDAO) {
		this.boothLocalBodyWardDAO = boothLocalBodyWardDAO;
	}

	
	public IUserRelationDAO getUserRelationDAO() {
		return userRelationDAO;
	}

	public void setUserRelationDAO(IUserRelationDAO userRelationDAO) {
		this.userRelationDAO = userRelationDAO;
	}
	
   public IVoterReportService getVoterReportService() {
		return voterReportService;
	}

	public void setVoterReportService(IVoterReportService voterReportService) {
		this.voterReportService = voterReportService;
	}

    public IPartialBoothPanchayatDAO getPartialBoothPanchayatDAO() {
		return partialBoothPanchayatDAO;
	}

	public void setPartialBoothPanchayatDAO(
			IPartialBoothPanchayatDAO partialBoothPanchayatDAO) {
		this.partialBoothPanchayatDAO = partialBoothPanchayatDAO;
	}
	
	public IHamletBoothDAO getHamletBoothDAO() {
		return hamletBoothDAO;
	}

	public void setHamletBoothDAO(IHamletBoothDAO hamletBoothDAO) {
		this.hamletBoothDAO = hamletBoothDAO;
	}

	//  @Override
	public List<VoterVO> getVoterDetails(Long publicationDateId, Long boothId,
			Long panchayatId, Long hamletId,Integer startIndex , Integer maxRecords , String order,
			String columnName,Long userId,Long customwardId,Long constiId) {

		if (log.isDebugEnabled())
			log.debug("Excecuting getVoterDetails() method in RegionServiceDataImp service");

		Map<Long,VoterVO> voters = new HashMap<Long, VoterVO>();
		List<VoterVO> returnValue = new ArrayList<VoterVO>();
		List<Object[]> votersList = new ArrayList<Object[]>();;
		Long totalCount = 0L;
		VoterVO voterVO = null;
		List<Long> votersIdsList = new ArrayList<Long>(0);
		Map<Long, Long> serialNoMap = new HashMap<Long, Long>(0);
		List<Object[]> serialNosList = new ArrayList<Object[]>(0);
		List<Object[]> mobileNosList = null;
		Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
		
		try {  
			
			if(columnName != null && columnName.equalsIgnoreCase("firstName"))
			{
				columnName = "name";
			}
			if(columnName != null && columnName.equalsIgnoreCase("relativeFirstName"))
			{
				columnName = "relativeName";
			}
			if(hamletId != null && hamletId.longValue() != 0)
			{
				List<?> votersList1 = userVoterDetailsDAO.getVotersDetailsByHamletPublication(hamletId, userId,startIndex,maxRecords,order,columnName);
				 votersList =(List<Object[]>) userVoterDetailsDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId,votersList1,columnName,order);
				 totalCount=(Long) userVoterDetailsDAO.getVotersCountByHamlet(hamletId,userId).get(0);
			}
			
			if(customwardId != null && customwardId.longValue() != 0)
			{
				
				 votersList = boothPublicationVoterDAO
							.getVotersDetailsByCustomWardId(customwardId,publicationDateId,constiId,userId,startIndex, maxRecords, order, columnName);
				 totalCount=(Long) boothPublicationVoterDAO.getVotersCountByCustomWardId(customwardId,publicationDateId,constiId,userId).get(0);
				
			}
			
			else
			{
				if(boothId != null && panchayatId == null){
					votersList = boothPublicationVoterDAO
							.getVotersDetailsByBoothId( boothId ,startIndex, maxRecords, order, columnName);
					
							  
					 totalCount = (Long) boothPublicationVoterDAO.getVotersCountByBoothId(boothId).get(0);
					 
				}else if(boothId == null && panchayatId != null && panchayatId.longValue() > 0 ){
					 votersList = boothPublicationVoterDAO
							.getVotersDetailsForPanchayatByPublicationId(
									 panchayatId,  publicationDateId,  startIndex,
									 maxRecords,  order,  columnName);
					 totalCount = (Long) boothPublicationVoterDAO.getVotersCountForPanchayat(panchayatId,publicationDateId).get(0);
					
				}
			}
				if(votersList != null && votersList.size() > 0)
				{
					for(Object[] voterDetails : votersList)
					{
						Voter voter = (Voter)voterDetails[0];
						votersIdsList.add(voter.getVoterId());
					}
					
				}
				if(votersIdsList != null && votersIdsList.size() > 0)
					serialNosList = boothPublicationVoterDAO.getSerialNoByVoterIdsList(votersIdsList);
				
				if(votersIdsList != null && votersIdsList.size() > 0)
				 mobileNosList = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(votersIdsList,userId);
				
				if(mobileNosList != null && mobileNosList.size() > 0)
				 for(Object[] params :mobileNosList)
				  mobileNosMap.put((Long)params[0], params[1] != null?params[1].toString():"N/A"); 
				
				if(serialNosList != null && serialNosList.size() > 0)
				{
					for(Object[] param : serialNosList)
						serialNoMap.put((Long)param[0], (Long)param[1]);
				}
				
				Long count = new Long(startIndex);
				List<Long> voterIdsList = new ArrayList<Long>();
				
				for (Object[] voterDetails : votersList) {
					
					Voter voter = (Voter)voterDetails[0];
					
					
					voterVO = new VoterVO();
					voterVO.setVoterIds(voter.getVoterId());
					voterIdsList.add(voter.getVoterId());
					voterVO.setVoterId((Long.valueOf(++count).toString()));
					
					voterVO.setFirstName(voter.getName());
					//voterVO.setFirstName(voter.getVoterNames().getFirstName() +"" +voter.getVoterNames().getLastName());
					voterVO.setAge(voter.getAge());
					voterVO.setGender(voter.getGender());
					voterVO.setHouseNo(voter.getHouseNo());
					voterVO.setRelativeFirstName(voter.getRelativeName());
					//voterVO.setRelativeFirstName(voter.getVoterNames().getRelativeFirstName()+""+voter.getVoterNames().getRelativeLastName());
					voterVO.setRelationshipType(voter.getRelationshipType());
					voterVO.setVoterIDCardNo(voter.getVoterIDCardNo());
					if(mobileNosMap.get(voter.getVoterId()) != null)
					 voterVO.setMobileNo(mobileNosMap.get(voter.getVoterId()));
					else
						voterVO.setMobileNo("N/A");
					if(!(hamletId != null && hamletId.longValue() != 0))
					{
						voterVO.setPartNo(Long.valueOf(voterDetails[1].toString()));
						voterVO.setSerialNo((Long)voterDetails[2]);
					}
					else
					{
						//voterVO.setSerialNo(serialNoMap.get(voter.getVoterId()));
						voterVO.setPartNo(Long.valueOf(voterDetails[1].toString()));
						voterVO.setSerialNo((Long)voterDetails[2]);
					}
					
					/*if(voter.getInfluencingPeople() != null){
						voterVO.setInfluencePerson(true);
						voterVO.setInfluencePerson("true");
					}
					else{
						voterVO.setInfluencePerson(false);
						voterVO.setInfluencePerson("false");
					}*/
					
					voters.put(voter.getVoterId(), voterVO);
					
					returnValue.add(voterVO);
	
				}
				List<Long> influencingPeopleList = influencingPeopleDAO.findInfluencingPeopleDetails(voterIdsList,userId);
				if(influencingPeopleList != null && influencingPeopleList.size() > 0)
				{
					for (Long influencingPeople : influencingPeopleList) {
					   voterVO = voters.get(influencingPeople);
						if(voterVO != null)
						{
							voterVO.setInfluencePerson(true);
						}
					}
				}
				List<Long> cadrePeopleList = cadreDAO.findCadrePeopleDetails(voterIdsList,userId);
				if(cadrePeopleList != null && cadrePeopleList.size() > 0)
				{
					for (Long cadrePeople : cadrePeopleList) {
						voterVO = voters.get(cadrePeople);
						if(voterVO != null)
						{
							voterVO.setIsCadrePerson(true);
						}
					}
				}
				List<Long> candidatePeopleList = candidateDAO.findCandidatePeopleDetails(voterIdsList);
				if(candidatePeopleList != null && candidatePeopleList.size() > 0)
				{
					for (Long candidatePeople : candidatePeopleList) {
						voterVO = voters.get(candidatePeople);
						if(voterVO != null)
						{
							voterVO.setIsPoliticion(true);
						}
					}
				}
				Map<Long,List<VoterVO>> flagMap = new HashMap<Long, List<VoterVO>>();
				List<Object[]> flagDetails = voterFlagDAO.getFlagInfoByBoterIds(voterIdsList);
				if(flagDetails != null && flagDetails.size() > 0)
				{
						for(Object[] params : flagDetails) {
						List<VoterVO> list = flagMap.get((Long)params[0]);
						VoterVO flagvo = new VoterVO();
						if(list == null)
						{
							list = new ArrayList<VoterVO>();
							flagMap.put((Long)params[0], list);
						}
						flagvo.setName(params[2]!=null?params[2].toString():"");
						flagvo.setColor(params[3]!=null?params[3].toString().substring(1):"");
						list.add(flagvo);
						
				}
				}
					if(flagMap != null)
					for(Long voterId : flagMap.keySet())
					{
						voterVO  = voters.get(voterId);
						List<VoterVO> list = flagMap.get(voterId);
						voterVO.setFlagList(list);
						
					}
				
				if(voters != null && voters.size() > 0)
				{
					returnValue.get(0).setTotalVoters(totalCount);
				}
		} catch (Exception e) {
			
			log.error("Exception Occured in getVoterDetails() method - " + e);
			return null;
		}
		return returnValue;
	}
	/**
	 * @return publicationDetails
	 * @author prasad Thiragabathina
	 * @param constituencyId
	 */
	public List<SelectOptionVO> publicationDetailsBasedOnConstituency(Long constituencyId)
	{
		List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(); 
			
		SelectOptionVO selectOptionVO = null;
		//List<Object[]> publicationDetails = boothPublicationVoterDAO.getPublicationDetailsBasedOnConstituency(constituencyId);
		
		List<Object[]> publicationDetails = voterInfoDAO.getPublicationDetailsBasedOnConstituencyId(constituencyId);
		
		if(publicationDetails != null && publicationDetails.size() > 0)
		{
			for(Object[] publicationDetail : publicationDetails)
			{
				Date date = (Date)publicationDetail[1];
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long)publicationDetail[0]);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				selectOptionVO.setName(calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR));
				
				selectOptionVOList.add(selectOptionVO);
			}
		}
		return selectOptionVOList;
	}
	
	/**
	 * 
	 * @param id
	 * @param publicationDateId
	 * @param name
	 * @return returnVal
	 * @author prasad Thiragabathina
	 */
	public List<Long> getImpFamiles(Long id,Long publicationDateId,String name){
		List<Object[]>  impFamilesList = null;
		
		if(name.equalsIgnoreCase("constituency")){
			impFamilesList = boothPublicationVoterDAO.findImpFamilesBasedOnConstituencyId(id, publicationDateId);
		}
		else if(name.equalsIgnoreCase("panchayat")){
			impFamilesList = boothPublicationVoterDAO.findImpFamilesBasedOnBoothId(id, publicationDateId);
		}
		else if(name.equalsIgnoreCase("booth")){
			impFamilesList = boothPublicationVoterDAO.findImpFamilesBasedOnPanchayat(id, publicationDateId);
		}
		Map<String,List<VoterVO>> resultMap = new LinkedHashMap<String,List<VoterVO>>();
		resultMap.put("Below-3", new ArrayList<VoterVO>(0));
		resultMap.put("7-5", new ArrayList<VoterVO>(0));
		resultMap.put("10-7", new ArrayList<VoterVO>(0));
		resultMap.put("Above-10", new ArrayList<VoterVO>(0));
	    List<Long> returnVal = new ArrayList<Long>();
		Long below3 = 0l;
		Long between4To6 = 0l;
		Long between7To10 = 0l;
		Long above10 = 0l;
		Long count = 0l;
		Long above10Count = 0l;
		Long between7T10Count = 0l;
		Long between4To6Count = 0l;
		Long below3Count = 0l;
		for (Object[] impFamiles : impFamilesList) {
			count = (Long) impFamiles[1];	
			if(count.longValue() > 10){
				above10 = above10+1;
				above10Count = count + above10Count;
			}
			else if(count.longValue() < 10 && count.longValue() >= 7){
				between7To10 = between7To10+1;
				between7T10Count = count + between7T10Count;
			}
			else if(count.longValue() < 7 && count.longValue() >=4){
				between4To6 = between4To6 + 1;
				between4To6Count = count + between4To6Count;	
			}
			else{
				below3 = below3 + 1;
				below3Count = count + below3Count;
			}
		}
		returnVal.add(above10);
		returnVal.add(above10Count);
		returnVal.add(below3);
		returnVal.add(below3Count);
		returnVal.add(between4To6);
		returnVal.add(between4To6Count);
		returnVal.add(between7To10);
		returnVal.add(between7T10Count);
		return returnVal;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public IHamletBoothPublicationDAO getHamletBoothPublicationDAO() {
		return hamletBoothPublicationDAO;
	}

	public void setHamletBoothPublicationDAO(
			IHamletBoothPublicationDAO hamletBoothPublicationDAO) {
		this.hamletBoothPublicationDAO = hamletBoothPublicationDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public IPublicationDateDAO getPublicationDateDAO() {
		return publicationDateDAO;
	}
	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
	}
	
	
	/*public VotersInfoForMandalVO getVotersCount(String type,Long id,Long publicationDateId){
		try{
			List<Election> electionIds = getPrevElections(publicationDateId);
			if(type.equalsIgnoreCase("constituency")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForConstituency(type,id,publicationDateId);
				if(electionIds != null && electionIds.size() > 0)
					getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"constituency");
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("mandal")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForMandal(type,id,publicationDateId);
				if(electionIds != null && electionIds.size() > 0)
					getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"mandal");
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("booth")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForBooth(type,id,publicationDateId,"main");
				getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"booth");
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("panchayat")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForPanchayat(id,publicationDateId,"main");
				getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"panchayat");
				getBoothsComparisionInfo(electionIds,id,publicationDateId,votersInfoForMandalVO);
				return votersInfoForMandalVO;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("Exception rised in getVotersCount method : ",e);
		}
		VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
		   votersInfoForMandalVO.setDatapresent(false);
		   return votersInfoForMandalVO;
	}*/
	
	public ICustomVoterDAO getCustomVoterDAO() {
		return customVoterDAO;
	}

	public void setCustomVoterDAO(ICustomVoterDAO customVoterDAO) {
		this.customVoterDAO = customVoterDAO;
	}

	public ICadreDAO getCadreDAO() {
		return cadreDAO;
	}

	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}
	
	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	public ILocalElectionBodyWardDAO getLocalElectionBodyWardDAO() {
		return localElectionBodyWardDAO;
	}
	public void setLocalElectionBodyWardDAO(
			ILocalElectionBodyWardDAO localElectionBodyWardDAO) {
		this.localElectionBodyWardDAO = localElectionBodyWardDAO;
	}

	public VotersInfoForMandalVO getVotersCount(Long userId , String type,Long id,Long publicationDateId,Long constituencyId,String resultFor){
		try{
			List<Election> electionIds = getPrevElections(publicationDateId);
			if(type.equalsIgnoreCase("constituency")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersBasicInfoForConstituency(type, id, publicationDateId,"main");
				if(!votersInfoForMandalVO.isDatapresent())
				  votersInfoForMandalVO = getVotersCountForConstituency(type,id,publicationDateId);
				if(electionIds != null && electionIds.size() > 0)
					getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"constituency",constituencyId);
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("mandal")){
				if(resultFor.equalsIgnoreCase("muncipalityCustomWard"))
				{
					VotersInfoForMandalVO votersInfoForMandalVO = getCustomWardWiseVotersCount(constituencyId, id, publicationDateId, userId);
					return votersInfoForMandalVO;
				}
				else
				{
					VotersInfoForMandalVO votersInfoForMandalVO = getVotersBasicInfoForMandal(type, id, publicationDateId, "main",constituencyId,resultFor,userId);
						if(!votersInfoForMandalVO.isDatapresent())
							votersInfoForMandalVO = getVotersCountForMandal(type,id,publicationDateId,constituencyId,userId);
						if(electionIds != null && electionIds.size() > 0)
							getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"mandal",constituencyId);
						return votersInfoForMandalVO;
				}
			}
			else if(type.equalsIgnoreCase("booth")){
				VotersInfoForMandalVO  votersInfoForMandalVO = null;
				if(resultFor.equalsIgnoreCase("hamlet"))
				{
					votersInfoForMandalVO  =new  VotersInfoForMandalVO();
					
					getVoterDetailsForHamletsInBooth(id, votersInfoForMandalVO,publicationDateId,userId);
				}else{
				votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.BOOTH), id, publicationDateId,"booth-"+boothDAO.get(id).getPartNo(),"main",constituencyId);
				if(!votersInfoForMandalVO.isDatapresent())
				  votersInfoForMandalVO = getVotersCountForBooth(type,id,publicationDateId,"main");
				getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"booth",constituencyId);
				}
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("panchayat")){
			
				VotersInfoForMandalVO votersInfoForMandalVO  =new  VotersInfoForMandalVO();
				
				if(resultFor.equalsIgnoreCase("booth")){
				
				 votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.BOOTH), id, publicationDateId,"booth-"+boothDAO.get(id).getPartNo(),"main",constituencyId);
				if(!votersInfoForMandalVO.isDatapresent())
					 votersInfoForMandalVO = getVotersCountForPanchayat(id,publicationDateId,"main",userId);
					 //getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"panchayat",constituencyId);
				getBoothsComparisionInfo(electionIds,id,publicationDateId,votersInfoForMandalVO);
				}else if(resultFor.equalsIgnoreCase("hamlet")){
					 votersInfoForMandalVO = getVotersBasicInfoForPanchayat1(userId,id, publicationDateId, "main",constituencyId);

				}
				
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("hamlet") || type.equalsIgnoreCase(IConstants.CUSTOMWARD)){
				
				VotersInfoForMandalVO votersInfoForMandalVO  =new  VotersInfoForMandalVO();
				
		      
				if(resultFor.equalsIgnoreCase("localArea")){
					  getVoterDetailsForLocalAreasInHamlet(id, votersInfoForMandalVO,publicationDateId,userId,type);

				
				
                	//getVoterDetailsForLocalAreasInHamlet(id, votersInfoForMandalVO,publicationDateId,userId);
				List<Object[]> objlist=boothPublicationVoterDAO.getAssignedAndUnassignedVtrsOfLclBdy(id,userId,type);
				if(objlist != null && objlist.size() > 0)
				   {
					   for(Object[] params : objlist){
						   votersInfoForMandalVO.setAssignedVotersForLocalBodies((Long) params[0]);
						   votersInfoForMandalVO.setUnassignedVotersForLocalBodies((Long) params[1]);
						   
					   }
				   }
				List<Object[]> obj= boothPublicationVoterDAO.getPublicationUserCount(userId,publicationDateId,id,type);
				
				if(obj != null && obj.size() > 0)
				   {
							for(Object[] params1 : obj){
						   votersInfoForMandalVO.setPublicationVoters((Long) params1[0]);
						   votersInfoForMandalVO.setAssignedVotersByUser((Long) params1[1]);
						   votersInfoForMandalVO.setUnassignedVotersByUser((Long) params1[2]);
						   
					   }
				   }
				
			//	votersInfoForMandalVO.setPublicationVoters((Long));
				//votersInfoForMandalVO.setAssignedVotersByUser(assignedVotersByUser)
				}else
					
					if(resultFor.equalsIgnoreCase("booth")){
						getVoterDetailsForHamletsByBooths(id, votersInfoForMandalVO,publicationDateId,userId,type,constituencyId);

				
					}
					
			
				return votersInfoForMandalVO;
				}
			else if(type.equalsIgnoreCase("ward")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersBasicInfoForWard(id, publicationDateId, "main",constituencyId);
				if(!votersInfoForMandalVO.isDatapresent())
				 votersInfoForMandalVO = getVotersCountForWard(id,publicationDateId,"main");
				//getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"panchayat");
				//getBoothsComparisionInfo(electionIds,id,publicationDateId,votersInfoForMandalVO);
				return votersInfoForMandalVO;
			}
			/*else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
			{
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForCustomWardBooths(id,publicationDateId,constituencyId,userId);
				return votersInfoForMandalVO;
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("Exception rised in getVotersCount method : ",e);
		}
		VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
		   votersInfoForMandalVO.setDatapresent(false);
		   return votersInfoForMandalVO;
	}
	
	public VotersInfoForMandalVO getVotersCountForConstituency(String type,Long id,Long publicationDateId){
		
		List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountByPublicationId(type,id,publicationDateId,null);
		if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
			Constituency constituency = constituencyDAO.get(id);
		  VotersInfoForMandalVO votersInfoForMandalVO = populateDataToVotersInfoForMandalVO(votersCountList,id,constituency.getName(),"Constituency");
		   //getting  all mandals and muncipalities and ghmcs in constituency
		  List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
		  if("URBAN".equalsIgnoreCase(constituency.getAreaType()) && mandalsList != null && mandalsList.size() == 1){
			  String localBodyId = mandalsList.get(0).getId().toString();
			  if(localBodyId.substring(0,1).trim().equalsIgnoreCase("1")){
				  getVotersCountForMultipleWards(new Long(localBodyId.substring(1)),publicationDateId,votersInfoForMandalVO,id);
			  }
			  return votersInfoForMandalVO;
		  }else{
			  //getting voters count for all mandals and muncipalities and ghmcs in constituency
			  getVotersCountForMultipleMandal(mandalsList,publicationDateId,votersInfoForMandalVO,id);
			  calculatePercentage(votersInfoForMandalVO);
			  return votersInfoForMandalVO;
		  }
	   }else{
		   VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
		   votersInfoForMandalVO.setDatapresent(false);
		   return votersInfoForMandalVO;
	   }
	}
	
	public VotersInfoForMandalVO getVotersCountForMandal(String type,Long id,Long publicationDateId,Long constituencyId,Long userId){
		String name = "";
		if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
		  List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(id.toString().substring(1));
		  Object[] reqName = assemblyLocalElectionBodyName.get(0);
		  name = reqName[0].toString()+" "+reqName[1].toString();
		}
		else{
			name = tehsilDAO.get(new Long(id.toString().substring(1))).getTehsilName()+" Mandal/Tehsil";
		}
		VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForSelectedMandal("mandal",id.toString(),publicationDateId,name,"main",constituencyId);
		if(id.toString().substring(0,1).trim().equalsIgnoreCase("2") && votersInfoForMandalVO.isDatapresent()){
			//getting voters count for all panchayats in mandal
		     getVotersCountForMultiplePanchayat(new Long(id.toString().substring(01)),publicationDateId,votersInfoForMandalVO,userId);
		     calculatePercentage(votersInfoForMandalVO);
		}else{
			 getVotersCountForMultipleWards(new Long(id.toString().substring(01)),publicationDateId,votersInfoForMandalVO,constituencyId);
		     calculatePercentage(votersInfoForMandalVO);
		}
		
		return votersInfoForMandalVO;
	}
	
	public VotersInfoForMandalVO getVotersCountForLocality(Long userId ,Long hamletId,Long  id,Long  publicationDateId,String reqType,String name ,String  query){
		//List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountByPublicationId(type,id,publicationDateId,null);
		
		List<Object[]> votersCountList = userVoterDetailsDAO.getVotersCountByGenderForLocalityInHamlet(userId , hamletId,id ,publicationDateId,query);
		
		if((!votersCountList.isEmpty() && votersCountList.get(0)[1] != null) || reqType.equalsIgnoreCase("sub")){
		   return populateDataToVotersInfoForMandalVO(votersCountList,id,name,"Locality");
		}else{
			   VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
			   votersInfoForMandalVO.setDatapresent(false);
			   return votersInfoForMandalVO;
		   }
	}
	
	public VotersInfoForMandalVO getVotersCountForBooth(String type,Long id,Long publicationDateId,String reqType){
		List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountByPublicationId(type,id,publicationDateId,null);
		if((!votersCountList.isEmpty() && votersCountList.get(0)[1] != null) || reqType.equalsIgnoreCase("sub")){
		   return populateDataToVotersInfoForMandalVO(votersCountList,id,"booth-"+boothDAO.get(id).getPartNo(),"Booth");
		}else{
			   VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
			   votersInfoForMandalVO.setDatapresent(false);
			   return votersInfoForMandalVO;
		   }
	}
	
public VotersInfoForMandalVO getVotersCountForPanchayat(Long id,Long publicationDateId,String reqType,Long userId ){
		
		List<Long> list = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatId(id,publicationDateId);
		
		List<Object[]> votersCountList = null;
		
		if(list != null && list.size() > 0)
		{
			 votersCountList = boothPublicationVoterDAO.getVotersCountForPanchayatByPublicationIdAndHamlet(id,publicationDateId,userId);

		}else
			 votersCountList = boothPublicationVoterDAO.getVotersCountForPanchayatByPublicationId(id,publicationDateId);

			
		 
		if(reqType.equalsIgnoreCase("main")){
		if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
		  VotersInfoForMandalVO votersInfoForMandalVO =  populateDataToVotersInfoForMandalVO(votersCountList,id,panchayatDAO.get(id).getPanchayatName(),"Panchayat");
		  getVotersCountForMultipleBooths(id,publicationDateId,votersInfoForMandalVO);
		  calculatePercentage(votersInfoForMandalVO);
		  return votersInfoForMandalVO;
		}else{
			 VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
			 votersInfoForMandalVO.setDatapresent(false);
			 return votersInfoForMandalVO;
		 }
	  }else{
		  return populateDataToVotersInfoForMandalVO(votersCountList,id,panchayatDAO.get(id).getPanchayatName(),"Panchayat");
	  }
	}
	
	public VotersInfoForMandalVO getVotersCountForWard(Long id,Long publicationDateId,String reqType ){
		 VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
		 votersInfoForMandalVO.setDatapresent(false);

			List<Object[]> votersCountList = boothPublicationVoterDAO.getGenderWiseVotersCountForWard(id,publicationDateId);
			if(reqType.equalsIgnoreCase("main")){
			if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
			   votersInfoForMandalVO =  populateDataToVotersInfoForMandalVO(votersCountList,id,constituencyDAO.get(id).getName(),"Ward");
			  List<Long> boothsList = boothDAO.getBoothIdsForWard(id,publicationDateId);
			   for(Long boothId:boothsList)
			     votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForBooth("booth",boothId,publicationDateId,"sub")); 
			  calculatePercentage(votersInfoForMandalVO);
			  return votersInfoForMandalVO;
			}
		  }
			return populateDataToVotersInfoForMandalVO(votersCountList,id,constituencyDAO.get(id).getName(),"Ward");
	}
	public VotersInfoForMandalVO getVotersCountForCustomWard(Long id,Long publicationDateId,String reqType ){
		 VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
		 votersInfoForMandalVO.setDatapresent(false);
//18111change
			List<Object[]> votersCountList = userVoterDetailsDAO.getGenderWiseVoterDetailsForCustomWard(id,publicationDateId,1L);
			return populateDataToVotersInfoForMandalVO(votersCountList,id,constituencyDAO.get(id).getName(),"customWard");
	}
	
	public void getVotersCountForMultipleMandal(List<SelectOptionVO> mandalsList,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO,Long constituencyId){
		 for(SelectOptionVO mandal : mandalsList){
			 String id = mandal.getId().toString();
			 votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForSelectedMandal("mandal",id,publicationDateId,mandal.getName(),"sub",constituencyId));
		 }
	}
	
	public VotersInfoForMandalVO getVotersCountForSelectedMandal(String type,String id,Long publicationDateId,String name,String reqType,Long constituencyId){
		
			 List<Object[]> votersCountList = null;
			 if(id.substring(0,1).trim().equalsIgnoreCase("1")){
				 //getting voters count for muncipality and ghmc
				 //List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.substring(1).trim()));
					
				votersCountList =  boothPublicationVoterDAO.getVotersCountFromLocalElectionBody(new Long(id.substring(1).trim()),publicationDateId,constituencyId);
			 }else if(id.substring(0,1).trim().equalsIgnoreCase("2")){
				 //getting voters count for mandal
				 votersCountList =  boothPublicationVoterDAO.getVotersCountByPublicationId("mandal",new Long(id.substring(1).trim()),publicationDateId,constituencyId);
			 }
			 if(reqType.equalsIgnoreCase("main")){
				 if(votersCountList !=null && !votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
				   return populateDataToVotersInfoForMandalVO(votersCountList,new Long(id),name,"Mandal");
				 }else{
					 VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
					 votersInfoForMandalVO.setDatapresent(false);
					 return votersInfoForMandalVO;
				 }
			 }else{
				 return populateDataToVotersInfoForMandalVO(votersCountList,new Long(id),name,"Mandal"); 
			 }
			
	}
	
    public void getVotersCountForMultipleBooths(Long panchayatId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO){
		
		
		List<Long> list = partialBoothPanchayatDAO.getDistinctPartialBoothsByPanchayatIdAndPublicationDateId(panchayatId,publicationDateId);
		
		List<Long> partialBoothIds = new ArrayList<Long>();
		List<Long> boothIds = new ArrayList<Long>();
		
		if(list != null && list.size() >0)
			for(Long boothId:list)
				partialBoothIds.add(boothId);
		
		List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(panchayatId,publicationDateId);
		
		
	     for(Object[] booth : boothsList)
	     {
	    	 boothIds.add((Long)booth[0]);
	    	 votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForBooth("booth",(Long)booth[0],publicationDateId,"sub")); 
	     }
	     
	     if(partialBoothIds.size() >0)
	     for(Long boothId:partialBoothIds)	     
	      if(!boothIds.contains(boothId))
	    	 votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForBooth("booth",boothId,publicationDateId,"sub")); 

	}
	
	public VotersInfoForMandalVO populateDataToVotersInfoForMandalVO(List<Object[]> votersCountList,Long id,String name,String type)
	{	
		VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
		try{
		Long maleVotersCount = 0l;
		Long femaleVotersCount = 0l;
		Long unknownsCount = 0l;
	    for(Object[] votersCount : votersCountList){
	    	if(votersCount[1] != null && votersCount[1].toString().trim().equalsIgnoreCase("M")){
	    		maleVotersCount = (Long)votersCount[0];
	    	}else if(votersCount[1] != null && votersCount[1].toString().trim().equalsIgnoreCase("F")){
	    		femaleVotersCount = (Long)votersCount[0];
	    	}else if(votersCount[1] != null && votersCount[1].toString().trim().equalsIgnoreCase("")){
	    		unknownsCount = (Long)votersCount[0];
	    	}
	    }
	    votersInfoForMandalVO.setTotalMaleVoters(maleVotersCount.toString());
	    votersInfoForMandalVO.setTotalFemaleVoters(femaleVotersCount.toString());
	    votersInfoForMandalVO.setUnKnowVoters(unknownsCount.toString());
	    votersInfoForMandalVO.setMaleVoters(maleVotersCount);
	    votersInfoForMandalVO.setFemaleVoters(femaleVotersCount);
	    votersInfoForMandalVO.setId(id);
		votersInfoForMandalVO.setName(name);
		votersInfoForMandalVO.setType(type);
	    BigDecimal totalCount = new BigDecimal(maleVotersCount.longValue()+femaleVotersCount.longValue()+unknownsCount.longValue());
	    votersInfoForMandalVO.setTotVoters(totalCount);
	    
	    return votersInfoForMandalVO;
		}catch (Exception e) {
			log.error("Exception occured in populateDataToVotersInfoForMandalVO() Method",e);
			return votersInfoForMandalVO;
		}
		
	}
	
	
	public VotersInfoForMandalVO populateDataToVotersInfoForMandalVOForBooth(List<Object[]> votersCountList,Long id,String name,String type){
		Long maleVotersCount = 0l;
		Long femaleVotersCount = 0l;
		Long totalCount = 0l;
		
		Object[] votersCount = votersCountList.get(0);
		
	   // for(Object[] votersCount : votersCountList){
	    	if(votersCount[0] != null){
	    		maleVotersCount = (Long)votersCount[0];
	    	}
	    	if(votersCount[1] != null){
	    		femaleVotersCount = (Long)votersCount[1];
	    	}
	    	if(votersCount[2] != null){
	    		totalCount = (Long)votersCount[2];
	    	}
	   // }
	    VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
	    votersInfoForMandalVO.setTotalMaleVoters(maleVotersCount.toString());
	    votersInfoForMandalVO.setTotalFemaleVoters(femaleVotersCount.toString());
	  //  votersInfoForMandalVO.setUnKnowVoters(unknownsCount.toString());
	    votersInfoForMandalVO.setId(id);
		votersInfoForMandalVO.setName(name);
		votersInfoForMandalVO.setType(type);
	  //  BigDecimal totalCount = new BigDecimal(maleVotersCount.longValue()+femaleVotersCount.longValue()+unknownsCount.longValue());
	    votersInfoForMandalVO.setTotVoters(new BigDecimal(totalCount.longValue()));
	    
	    return votersInfoForMandalVO;
	}
	
	public void getVotersCountForMultiplePanchayat(Long mandalId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO,Long userId){
		List<Object[]> panchayaties = panchayatDAO.getPanchayatsBymandalId(mandalId);
		for (Object[] panchayat : panchayaties){
			votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForPanchayat((Long)panchayat[0],publicationDateId,"sub",userId));
		}
	}
	
	public void getVotersCountForMultipleWards(Long assemblyLocalBodyId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO,Long constituencyId){
		List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(assemblyLocalBodyId);
		List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId,constituencyId);
		for (Object[] ward : wardsList){
			votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForWard((Long)ward[0],publicationDateId,"sub"));
		}
	}
	
	public void calculatePercentage(VotersInfoForMandalVO votersInfoForMandalVO)
	{
		try{
			if(!votersInfoForMandalVO.getVotersInfoForMandalVOList().isEmpty())
			{
				votersInfoForMandalVO.setSubLevelExists(true);
				
				for(VotersInfoForMandalVO vo : votersInfoForMandalVO.getVotersInfoForMandalVOList())
				{ 
				  if(!(votersInfoForMandalVO.getTotVoters().compareTo( BigDecimal.ZERO) == 0))
				  {
					  Double votes = Double.valueOf(vo.getTotVoters().toString());
					  Double totalVotes = Double.valueOf(votersInfoForMandalVO.getTotVoters().toString());
					  vo.setPercent(new BigDecimal(votes*(100.0)/totalVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					  vo.setTotPercent(new BigDecimal(votes*(100.0)/totalVotes).setScale(2, BigDecimal.ROUND_HALF_UP));
				  }
				  else{
					  vo.setPercent("0");
					  vo.setTotPercent(new BigDecimal("0.00"));
				  }
			  }
			}
		}catch (Exception e) {
			log.error("Exception Occured in calculatePercentage() Method, Exception is - "+e);
		}
	}
	
	
	public VoterCastInfoVO getVotersCastWiseDetailsInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId,String queryType)
	{
		List<String> types = new ArrayList<String>();
		types.add("hamlet");
		types.add(IConstants.CUSTOMWARD);
		VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
		String locationType1 = locationType;
		try{
			if(locationType.equalsIgnoreCase("mandal"))
			{
				String mandalId= locationId.toString();
				String id=mandalId.substring(1);
				locationId = new Long(id);
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					locationType = "mandal";
					locationType1 = "mandal";
				}else if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
					locationType = "localElectionBody";
					locationType1 = IConstants.LOCALELECTIONBODY;
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationId);
					locationId = (Long) list.get(0);
				}
			}
			Long totalVoters = 0L;
			Long reportLvlId = getReportLevelId(locationType1);
			if("panchayat".equalsIgnoreCase(locationType1)){
				boolean isPartial = false;
				Long count = partialBoothPanchayatDAO.getPartialBoothPanchayatDetails(locationId, publicationDateId);
				 if(count > 0){
					 isPartial = true;
				 }
				 if(isPartial){ 
					 totalVoters = boothPublicationVoterDAO.findVotersCountByPublicationIdForPartialPanchayat(userId,locationId,publicationDateId);
			    }else
			    	totalVoters = voterInfoDAO.getVotersCountInALocation(reportLvlId,locationId,publicationDateId,constituencyId);
			
			}else{
			 totalVoters = voterInfoDAO.getVotersCountInALocation(reportLvlId,locationId,publicationDateId,constituencyId);
			}
			 if(totalVoters == null){
				totalVoters  = getVotersCountByPublicationIdInALocation(locationType,locationId,publicationDateId,userId);
			}
			 Long votesConsidered = 0L;
			
			if(types.contains(locationType)){
				
				List<Long> hamlets = new ArrayList<Long>();
				hamlets.add(locationId);
			//List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletIds(userId , hamlets);
				  List<Long> voterIds  =(List<Long>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(locationId, userId ,locationType);		
				  
				  List<Long> countList = new ArrayList<Long>(0);
				  int fromIndex = 0;
				  int toIndex = 1000;
				  if(voterIds.size() >= 1000)
				  {
					  while(fromIndex <= toIndex)
					  {
						  List<Long> countListSub = boothPublicationVoterDAO.getTotalVotersCountForHamletByVoterIds(voterIds.subList(fromIndex, toIndex),publicationDateId);
						  countList.addAll(countListSub);
						  fromIndex += 1000;
						  toIndex += 1000;
						  if(toIndex >= voterIds.size())
							toIndex = voterIds.size();
					  }
				  }
				  else
					  countList = boothPublicationVoterDAO.getTotalVotersCountForHamletByVoterIds(voterIds,publicationDateId);
				  
		   if(countList != null && countList.size() >0)
			   totalVoters = 0l;
			 for(Long total:countList)
			 totalVoters = totalVoters+total;
			}
						
			if(!"main".equalsIgnoreCase(queryType) && !"booth".equalsIgnoreCase(locationType1) && !types.contains(locationType))
				   voterReportService.getVotersCastWiseDetailsInALocationFromIntermediateTable(userId, reportLvlId, locationId, publicationDateId, constituencyId, voterCastInfoVO);
				
				if("main".equalsIgnoreCase(queryType) || "booth".equalsIgnoreCase(locationType1) || types.contains(locationType)){
				   voterCastInfoVO.setCastCategoryWiseVotersList(getCastCategoryWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId,constituencyId));
				   voterCastInfoVO.setVoterCastInfoVOList(getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId,constituencyId));
				}
			
			voterCastInfoVO.setTotalCasts(voterCastInfoVO.getVoterCastInfoVOList().size());
			voterCastInfoVO.setTotalVoters(totalVoters);
			
			for(VoterCastInfoVO castInfoVO : voterCastInfoVO.getVoterCastInfoVOList())
				votesConsidered = votesConsidered + castInfoVO.getTotalVoters();
			
			voterCastInfoVO.setMaleVoters(votesConsidered);
			voterCastInfoVO.setFemaleVoters(totalVoters - votesConsidered);
			
			//voterCastInfoVO.setCastVOs(getCastWisePartyCount(userId,locationType,locationId,publicationDateId));
			return voterCastInfoVO;
		}catch (Exception e) {
			log.error("Exception Occured in getVotersCastWiseDetailsInALocation() Method, Exception is - "+e);
			return voterCastInfoVO;
		}
	}
	
	public VoterCastInfoVO getVotersPartyDetailsInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId,String queryType)
	{
		List<String> types = new ArrayList<String>();
		types.add("hamlet");
		types.add(IConstants.CUSTOMWARD);
		VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
		String locationType1 = locationType;
		try{
			if(locationType.equalsIgnoreCase("mandal"))
			{
				String mandalId= locationId.toString();
				String id=mandalId.substring(1);
				locationId = new Long(id);
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					locationType = "mandal";
					locationType1 = "mandal";
				}else if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
					locationType = "localElectionBody";
					locationType1 = IConstants.LOCALELECTIONBODY;
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationId);
					locationId = (Long) list.get(0);
				}
			}
			Long partyWisevotesConsidered = 0L;
			Long reportLvlId = getReportLevelId(locationType1);
			Long totalVoters = 0l;//voterInfoDAO.getVotersCountInALocation(reportLvlId,locationId,publicationDateId,constituencyId);
			
			if(types.contains(locationType) ){//18111party
				
				 List<Long> hamlets = new ArrayList<Long>();
				  hamlets.add(locationId);
			 // List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletIds(userId , hamlets);
				  List<Long> voterIds  =(List<Long>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(locationId, userId ,locationType);	
				  List<Long> countList = new ArrayList<Long>(0);
				  int fromIndex = 0;
				  int toIndex = 1000;
				  if(voterIds.size() >= 1000)
				  {
					  List<Long> countListTemp = 	boothPublicationVoterDAO.getTotalVotersCountForHamletByVoterIds(voterIds.subList(fromIndex, toIndex),publicationDateId);
					  countList.addAll(countListTemp);
					  fromIndex += 1000;
					  toIndex += 1000;
					  if(toIndex >= voterIds.size())
							toIndex = voterIds.size();
				  }
				  else
					  countList = 	boothPublicationVoterDAO.getTotalVotersCountForHamletByVoterIds(voterIds,publicationDateId);
				  
				  
		      if(countList != null && countList.size() >0)
			  totalVoters = countList.get(0);
			}
			else{
				if(locationType.equalsIgnoreCase("panchayat")){
					boolean isPartial = false;
					Long count = partialBoothPanchayatDAO.getPartialBoothPanchayatDetails(locationId, publicationDateId);
					 if(count > 0){
						 isPartial = true;
					 }
					 if(isPartial){ 
						 totalVoters =  boothPublicationVoterDAO.findVotersCountByPublicationIdForPartialPanchayat(userId,locationId,publicationDateId);
				    }else{
				    	 totalVoters = voterInfoDAO.getVotersCountInALocation(reportLvlId,locationId,publicationDateId,constituencyId);
				    }
				}else{
			       totalVoters = voterInfoDAO.getVotersCountInALocation(reportLvlId,locationId,publicationDateId,constituencyId);
				}
			}
			if(!"main".equalsIgnoreCase(queryType) && !"booth".equalsIgnoreCase(locationType1) && !types.contains(locationType)){
				voterReportService.getPartyNGenderWiseVotersCountByPublIdInALocFromIntermedTable(userId, reportLvlId, locationId, publicationDateId, constituencyId, voterCastInfoVO);
			   }
			   if("main".equalsIgnoreCase(queryType) || "booth".equalsIgnoreCase(locationType1) || types.contains(locationType) ){
				  voterCastInfoVO.setPartyWisevoterCastInfoVOList(getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId,constituencyId));
			   }
			    for(VoterCastInfoVO partyWisecastInfoVO : voterCastInfoVO.getPartyWisevoterCastInfoVOList())
				 partyWisevotesConsidered = partyWisevotesConsidered + partyWisecastInfoVO.getTotalVoters();
				 voterCastInfoVO.setPartyWiseAssignedVoters(partyWisevotesConsidered);
				 if(totalVoters == null)
					 totalVoters = 0l;
				 voterCastInfoVO.setPartyWiseNotAssignedVoters(totalVoters - partyWisevotesConsidered);
				return voterCastInfoVO;
		}catch (Exception e) {
			log.error("Exception Occured in getVotersCastWiseDetailsInALocation() Method, Exception is - "+e);
			return voterCastInfoVO;
		}
	}
    public Long getVotersCountByPublicationIdInALocation(String locationType,Long locationId,Long publicationDateId,Long userId)
	{
		try{
			if(locationType.equalsIgnoreCase("panchayat")){
				boolean isPartial = false;
				Long count = partialBoothPanchayatDAO.getPartialBoothPanchayatDetails(locationId, publicationDateId);
				 if(count > 0){
					 isPartial = true;
				 }
				 if(isPartial){ 
				   return boothPublicationVoterDAO.findVotersCountByPublicationIdForPartialPanchayat(userId,locationId,publicationDateId);
			    }else
				   return boothPublicationVoterDAO.findVotersCountByPublicationIdInALocation(locationType, locationId, publicationDateId);
			}
			else{
			  return boothPublicationVoterDAO.findVotersCountByPublicationIdInALocation(locationType, locationId, publicationDateId);
			}
		}catch (Exception e) {
			log.error("Exception Occured in getVotersCountByPublicationIdInALocation() method, Exception is - "+e);
			return 0L;
		}
	}
	
	public List<SelectOptionVO> getCastCategoryWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId)
	{
		List<SelectOptionVO> castCategoryWiseList = new ArrayList<SelectOptionVO>(0);
		try{
			List<Object[]> list = null;
			if(locationType.equalsIgnoreCase("panchayat")){
			boolean isPartial = false;
			Long count = partialBoothPanchayatDAO.getPartialBoothPanchayatDetails(locationId, publicationDateId);
			 if(count > 0){
				 isPartial = true;
			 }
				 if(isPartial){
				 list =  boothPublicationVoterDAO.getCastCategoryWiseVotersCountByPublicationIdForPartialPanchayat(userId,locationId, publicationDateId);
				 }else
					 list = boothPublicationVoterDAO.getCastCategoryWiseVotersCountByPublicationIdInALocation(userId, locationType, locationId, publicationDateId,constituencyId);
			}else{
			list = boothPublicationVoterDAO.getCastCategoryWiseVotersCountByPublicationIdInALocation(userId, locationType, locationId, publicationDateId,constituencyId);
			}
			if(list != null && list.size() > 0)
				for(Object[] params : list)
					castCategoryWiseList.add(new SelectOptionVO((Long)params[1],params[0].toString()));
			
			return castCategoryWiseList;
		}catch (Exception e) {
			log.error("Exception Ocuured in getCastCategoryWiseVotersCountByPublicationIdInALocation() Method, Exception is - "+e);
			return castCategoryWiseList;
		}
	}
	
	public List<VoterCastInfoVO> getCastAndGenderWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId)
	{
		List<VoterCastInfoVO> resultList = new ArrayList<VoterCastInfoVO>(0);
		try{
			List<Object[]> list = null;
			if(locationType.equalsIgnoreCase("panchayat")){
				boolean isPartial = false;
				Long count = partialBoothPanchayatDAO.getPartialBoothPanchayatDetails(locationId, publicationDateId);
				 if(count > 0){
					 isPartial = true;
				 }
					 if(isPartial){					
					 list =  boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdForPartialPanchayat(userId,locationId, publicationDateId);
					 }else{
						 list = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId,constituencyId);
					 }
				}else{
					list = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId,constituencyId);
				}
			if(list != null && list.size() > 0)
			{
				VoterCastInfoVO voterCastInfoVO = null;
				Long totalVotes = 0L;
				
				for(Object[] params : list)
				{
					voterCastInfoVO = getVoterCastInfoVOBasedOnCastName(params[0].toString(),resultList);
					boolean isNew = false;
					if(voterCastInfoVO == null)
					{
						voterCastInfoVO = new VoterCastInfoVO();
						voterCastInfoVO.setCastName(params[0].toString());
						isNew = true;
					}
					
					String gender = params[1].toString();
					if(params[4] != null)
					voterCastInfoVO.setCasteCategoryName(params[4].toString());
					if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male"))
						voterCastInfoVO.setMaleVoters((Long)params[2]);
					else
						voterCastInfoVO.setFemaleVoters((Long)params[2]);
					
					voterCastInfoVO.setTotalVoters(voterCastInfoVO.getMaleVoters() + voterCastInfoVO.getFemaleVoters());
					totalVotes = totalVotes + (Long)params[2];
					voterCastInfoVO.setCasteStateId((Long)params[3]);
					
					if(isNew)
						resultList.add(voterCastInfoVO);
				}
				
				for(VoterCastInfoVO castInfoVO : resultList)
				{
					String percentage = "0.00";
					try{
						percentage = (new BigDecimal(castInfoVO.getTotalVoters()*(100.0)/totalVotes.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					}catch (Exception e) {}
					finally{
						castInfoVO.setVotesPercent(percentage);
					}
				}
			}
			return resultList;
		}catch (Exception e) {
			log.error("Exception Occured in getCastAndGenderWiseVotersCountByPublicationIdInALocation() Method, Exception is - "+e);
			return resultList;
		}
	}
	
	
	public List<VoterCastInfoVO> getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId)
	{
		List<VoterCastInfoVO> resultList = new ArrayList<VoterCastInfoVO>(0);
		try{

			List<Object[]> list  = null;
			
			if(locationType.equalsIgnoreCase("panchayat"))
			{
				List<Long> partialPanchayatDetails = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatId(locationId, publicationDateId);
				
				if(partialPanchayatDetails != null && partialPanchayatDetails.size() >0)
					list = boothPublicationVoterDAO.getPartyAndGenderWiseVotersCountByPublicationIdInAndPanchayatWithHamlets(userId,locationId,publicationDateId);
				else
				   list = boothPublicationVoterDAO.getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId,constituencyId);

			}else
			{
				list = boothPublicationVoterDAO.getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId,constituencyId);
			}
			
			
			if(list != null && list.size() > 0)
			{
				VoterCastInfoVO voterCastInfoVO = null;
				Long totalVotes = 0L;
				
				for(Object[] params : list)
				{
					voterCastInfoVO = getPartyWiseCastInfoVOBasedOnPartyName(params[0].toString(),resultList);
					boolean isNew = false;
					if(voterCastInfoVO == null)
					{
						voterCastInfoVO = new VoterCastInfoVO();
						
						if(params[0] != null)
						voterCastInfoVO.setPartyName(params[0].toString());
						isNew = true;
					}
					
					String gender = params[1].toString();
					
					if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male"))
						voterCastInfoVO.setMaleVoters((Long)params[2]);
					else
						voterCastInfoVO.setFemaleVoters((Long)params[2]);
					
					voterCastInfoVO.setTotalVoters(voterCastInfoVO.getMaleVoters() + voterCastInfoVO.getFemaleVoters());
					totalVotes = totalVotes + (Long)params[2];
					voterCastInfoVO.setPartyId((Long)params[3]);
					
					if(isNew)
						resultList.add(voterCastInfoVO);
				}
				
				for(VoterCastInfoVO castInfoVO : resultList)
				{
					String percentage = "0.00";
					try{
						percentage = (new BigDecimal(castInfoVO.getTotalVoters()*(100.0)/totalVotes.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					}catch (Exception e) {}
					finally{
						castInfoVO.setVotesPercent(percentage);
					}
				}
			}
			return resultList;
		}catch (Exception e) {
			log.error("Exception Occured in getCastAndGenderWiseVotersCountByPublicationIdInALocation() Method, Exception is - "+e);
			return resultList;
		}
	}
	
	public VoterCastInfoVO getVoterCastInfoVOBasedOnCastName(String casteName,List<VoterCastInfoVO> list)
	{
		try{
			if(list != null && list.size() > 0)
			{
				for(VoterCastInfoVO voterCastInfoVO : list)
					if(voterCastInfoVO.getCastName().equalsIgnoreCase(casteName))
						return voterCastInfoVO;
				return null;
			}
			
			else 
				return null;
		}catch (Exception e) {
			log.error("Exception Occured in getVoterCastInfoVOBasedOnCastName(), Exception is - "+e);
			return null;
		}
	}
	public VoterCastInfoVO getPartyWiseCastInfoVOBasedOnPartyName(String partyName,List<VoterCastInfoVO> list)
	{
		try{
			if(list != null && list.size() > 0)
			{
				for(VoterCastInfoVO voterCastInfoVO : list)
					if(voterCastInfoVO.getPartyName().equalsIgnoreCase(partyName))
						return voterCastInfoVO;
				return null;
			}
			
			else 
				return null;
		}catch (Exception e) {
			log.error("Exception Occured in getVoterCastInfoVOBasedOnCastName(), Exception is - "+e);
			return null;
		}
	}
	//get CastInfo For Constituency/Mandal/Panchayat/Booth
	
	public VoterCastInfoVO getVotersCastDetails(Long id,Long publicationDateId,String type)
	
	{
		VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
		if(type.equalsIgnoreCase("constituency"))
		{
			
			
			return calculatePercentageForCast(boothPublicationVoterDAO.getGenderWiseCountInConstituency(id,publicationDateId));
			
		
		}
		else if(type.equalsIgnoreCase("mandal"))
		{
			
			if(id.toString().substring(0,1).trim().equalsIgnoreCase("2"))
			{
			return calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByMandalAndPublicationDate(new Long(id.toString().substring(1)),publicationDateId));
			}
			else
			{
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1)));
				
			return calculatePercentageForCast(boothPublicationVoterDAO.getVotersCastInfoFromLocalElectionBody((Long) list.get(0),publicationDateId));
			}
			
		}
		
		else if(type.equalsIgnoreCase("panchayat"))
		{
			
			return calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByPanchayatAndPublicationDate(id,publicationDateId));	
		}
		else if(type.equalsIgnoreCase("booth"))
		{
			
			return calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByBoothIdAndPublicationDate(id,publicationDateId));	
		}
		else
			return voterCastInfoVO;
		
		
	}
	

	public VoterCastInfoVO calculatePercentageForUserCast(List params,Long totalcount,Long totalSubCount)
	{
	VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
	SortedMap<String,CastVO> castsMap = new TreeMap<String,CastVO>();
	
	 
	//Set<String> casts = new HashSet<String>();
	CastVO castvo = null;
	
	
	Long totalVoters = 0l;
	String cast ="";
	
	int TotalCasts =0;
	for(int i=0;i<params.size();i++)
	{
		Object[] voterInfo =(Object[])params.get(i);
		
		totalVoters = totalVoters + (Long) voterInfo[2];
		
		String gender = (String)voterInfo[1];
		cast = (String) voterInfo[0];
		
		if(cast.equals(""))
		{
			
			cast = "N/A";
			
		}
		if(castsMap.get(cast) == null){
			castvo = new CastVO();
			if(voterInfo[4]!= null)
			castvo.setCasteCategoryName(voterInfo[4].toString());
			castvo.setCastName(cast);
			castvo.setCastStateId((Long) voterInfo[3]);
			castvo.setCastCount((Long) voterInfo[2]);
			//castvo.setGender(voterInfo[1].toString());
			if(gender.equalsIgnoreCase("m"))
			{
				castvo.setMalevoters((Long)voterInfo[2]);
			}
			else if(gender.equalsIgnoreCase("f"))
			{
				castvo.setFemalevoters((Long)voterInfo[2]);
			}
			
			castsMap.put(cast, castvo);
		}
		else{
		   castvo = castsMap.get(cast);
		   if(voterInfo[4]!= null)
		   castvo.setCasteCategoryName(voterInfo[4].toString());
		   castvo.setCastName(cast);
		   castvo.setCastCount(castvo.getCastCount()+(Long) voterInfo[2]);
		   //castvo.setGender(voterInfo[1].toString());
		   if(gender.equalsIgnoreCase("m"))
		   {
			castvo.setMalevoters((Long)voterInfo[2]);
		   }
		   else if(gender.equalsIgnoreCase("f"))
		   {
			castvo.setFemalevoters((Long)voterInfo[2]);
		   }
		 }
		
		
	}
	
	List<CastVO> castVOs = new ArrayList<CastVO>(castsMap.values());
	List<CastVO> castVOs1 = new ArrayList<CastVO>(castsMap.values());
	//Collections.sort(castVOs);
	// Calculate Percentage
	for(int i=0;i<castVOs.size();i++)
	{
		String castPercentage = "0.00";
		if(totalcount > 0)
		   castPercentage = new BigDecimal((castVOs.get(i).getCastCount()*(100.0))/totalcount.doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
		castVOs.get(i).setCastPercentage(castPercentage);
		
		
	}
	voterCastInfoVO.setCastVOs(castVOs);
	
	TotalCasts = removeCastNoneElements(castVOs1);
	voterCastInfoVO.setTotalCasts(TotalCasts);
	voterCastInfoVO.setTotalVoters(totalSubCount);
	voterCastInfoVO.setTotalCastKnownVoters(totalVoters);
	
	
	return voterCastInfoVO;
	
	
}
	
		public VoterCastInfoVO calculatePercentageForCast(List params)
		{
		VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
		SortedMap<String,CastVO> castsMap = new TreeMap<String,CastVO>();
		
		 
		//Set<String> casts = new HashSet<String>();
		CastVO castvo = null;
		
		
		Long totalVoters = 0l;
		String cast ="";
		
		Long maleVoters=0L;
		Long femaleVoters=0L;
		int TotalCasts =0;
		for(int i=0;i<params.size();i++)
		{
			Object[] voterInfo =(Object[])params.get(i);
			
			totalVoters = totalVoters + (Long) voterInfo[0];
			
			String gender = (String)voterInfo[1];
			//cast = (String) voterInfo[2];
			cast = "";
			if(cast != null)
			{
			if(cast.equals(""))
			{
				
				cast = "N/A";
				
			}
			if(castsMap.get(cast) == null){
				castvo = new CastVO();
				castvo.setCastName(cast);
				castvo.setCastCount((Long) voterInfo[0]);
				//castvo.setGender(voterInfo[1].toString());
				if(gender.equalsIgnoreCase("m"))
				{
					castvo.setMalevoters((Long)voterInfo[0]);
				}
				else if(gender.equalsIgnoreCase("f"))
				{
					castvo.setFemalevoters((Long)voterInfo[0]);
				}
				
				castsMap.put(cast, castvo);
			}
			else{
			   castvo = castsMap.get(cast);
			   castvo.setCastName(cast);
			   castvo.setCastCount(castvo.getCastCount()+(Long) voterInfo[0]);
			   //castvo.setGender(voterInfo[1].toString());
			   if(gender.equalsIgnoreCase("m"))
			   {
				castvo.setMalevoters((Long)voterInfo[0]);
			   }
			   else if(gender.equalsIgnoreCase("f"))
			   {
				castvo.setFemalevoters((Long)voterInfo[0]);
			   }
			 }
			
	}
		}
		
		List<CastVO> castVOs = new ArrayList<CastVO>(castsMap.values());
		List<CastVO> castVOs1 = new ArrayList<CastVO>(castsMap.values());
		//Collections.sort(castVOs);
		// Calculate Percentage
		for(int i=0;i<castVOs.size();i++)
		{
			String castPercentage = "0";
			if(totalVoters > 0)
			   castPercentage = new BigDecimal((castVOs.get(i).getCastCount()*100.0)/totalVoters).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			castVOs.get(i).setCastPercentage(castPercentage);
			
			
		}
		voterCastInfoVO.setCastVOs(castVOs);
		
		TotalCasts = removeCastNoneElements(castVOs1);
		voterCastInfoVO.setTotalCasts(TotalCasts);
		voterCastInfoVO.setTotalVoters(totalVoters);
		
		
		
		return voterCastInfoVO;
		
		
	}
	
		
	public int removeCastNoneElements(List<CastVO> list)
		{
			int totalCasts = 0;
			
				for(int i=0;i<list.size();i++)
				{
				if(list.get(i).getCastName().equals("N/A"))
				{
					list.remove(i);
				}
					
					
				}
				
				totalCasts = list.size();
			return totalCasts;
			
		}
		public  List<VoterCastInfoVO> getVotersCastDetailsForSubLevels(Long id,Long publicationDateId,String type,Long userId,Long constituencyId,String buildType,String queryType)
		
		{
			List<VoterCastInfoVO> mandalCasts = new ArrayList<VoterCastInfoVO>();
			try{
			VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
			SelectOptionVO selectOptionVO = null;
			List<SelectOptionVO> booths1 = new ArrayList<SelectOptionVO>();
			
			List<Long> values = new ArrayList<Long>();
			if(type.equalsIgnoreCase("constituency"))
			{
				List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
				if(!"main".equalsIgnoreCase(queryType)){
					   mandalCasts = voterReportService.getVotersCastInfoForMultipleMandal(mandalsList,publicationDateId,userId,constituencyId);
					}
				if("main".equalsIgnoreCase(queryType)){
					Long castCount = boothPublicationVoterDAO.getTotalCastCountInALocation(userId, type, id, publicationDateId, constituencyId);
					   mandalCasts = getVotersCastInfoForMultipleMandal(mandalsList,publicationDateId,userId,constituencyId,castCount);
					}
			}
			
			if(type.equalsIgnoreCase("mandal") && !buildType.equalsIgnoreCase("muncipalityCustomWard"))
			{
				if(id.toString().substring(0,1).trim().equalsIgnoreCase("2"))
				{
				   List<SelectOptionVO> panchayatList= staticDataService.getPanchayatiesByMandalId(new Long(id.toString().substring(1)));
				   if(!"main".equalsIgnoreCase(queryType)){
					   mandalCasts = voterReportService.getVotersCastInfoForMultipleValues(panchayatList,publicationDateId,userId,constituencyId,getReportLevelId("Panchayat"));
					}
				   if("main".equalsIgnoreCase(queryType)){
					   Long castCount = boothPublicationVoterDAO.getTotalCastCountInALocation(userId, type, id, publicationDateId, constituencyId);
				       mandalCasts = getVotersCastInfoForMultiplePanchayats(panchayatList,publicationDateId,userId,castCount,constituencyId);
					}    
				}else{
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1)));
					
					String electionType = localElectionBodyDAO.get((Long) list.get(0)).getElectionType().getElectionType();
					
					if(electionType.equalsIgnoreCase(IConstants.GHMC)){
					
						List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId,constituencyId);
						if(!"main".equalsIgnoreCase(queryType)){
							List<SelectOptionVO> wards = new ArrayList<SelectOptionVO>();
							SelectOptionVO optionVO = null;
							for(Object[] ward:wardsList){
								optionVO = new SelectOptionVO();
								optionVO.setName(ward[1] != null?ward[1].toString():"");
								optionVO.setId((Long)ward[0]);
								wards.add(optionVO);
							}
						   mandalCasts = voterReportService.getVotersCastInfoForMultipleValues(wards,publicationDateId,userId,constituencyId,getReportLevelId("Ward"));
						}
						if("main".equalsIgnoreCase(queryType)){
							Long castCount = boothPublicationVoterDAO.getTotalCastCountInALocation(userId, "localElectionBody", id, publicationDateId, constituencyId);
						   mandalCasts = getVotersCastInfoForMultipleWards(wardsList,publicationDateId,userId,castCount,constituencyId);
						}
					}else{
						
						Long castCount = boothPublicationVoterDAO.getTotalCastCountInALocation(userId, "localElectionBody", (Long) list.get(0), publicationDateId, constituencyId);

						

						List<Object[]> boothsList = boothDAO.getBoothsInAMunicipality((Long) list.get(0),publicationDateId,constituencyId);
						
						List<SelectOptionVO> localities = new ArrayList<SelectOptionVO>();
						SelectOptionVO option = null;
						for(Object[] booth:boothsList){
							option = new SelectOptionVO();
							option.setId((Long)booth[0]);
							option.setName(booth[1]!=null?booth[1].toString():"");
							localities.add(option);
						}                                     
						mandalCasts = getVotersCastInfoForMultipleBooths(localities,publicationDateId,userId,castCount,constituencyId);
						
						if(mandalCasts != null && mandalCasts.size() >0)
						mandalCasts.get(0).setMuncipalityType(electionType);
						
					}
				}				
			}
			if(type.equalsIgnoreCase("panchayat"))
			{
				boolean isPartial = false;
				Long castCount = 0l;
				Long count = partialBoothPanchayatDAO.getPartialBoothPanchayatDetails(id, publicationDateId);
				if(count > 0){
					 isPartial = true;
				 }
				if(isPartial){
					castCount = boothPublicationVoterDAO.getTotalCastCountForPartialPanchayat(userId,id,publicationDateId);
				}else{
				  castCount = boothPublicationVoterDAO.getTotalCastCountInALocation(userId, type, id, publicationDateId, constituencyId);
				}
				if(buildType.equalsIgnoreCase("booth")){
			
				List<SelectOptionVO> booths = getBoothsByPanchayatId(id,publicationDateId,isPartial);
				   /*if(!"main".equalsIgnoreCase(queryType)){
					   mandalCasts = voterReportService.getVotersCastInfoForMultipleValues(booths,publicationDateId,userId,constituencyId,getReportLevelId(IConstants.BOOTH));
					}
				   if("main".equalsIgnoreCase(queryType) || mandalCasts.size() == 0){*/
					   mandalCasts = getVotersCastInfoForMultipleBooths(booths,publicationDateId,userId,castCount, constituencyId);
					//}
				
				}else if (buildType.equalsIgnoreCase("hamlet")){
					
					List<Long> hamlets = userVoterDetailsDAO.getUserHamletsByPanchayatId(userId ,id );
					mandalCasts = getVotersCastInfoForMultipleHamlets(hamlets,publicationDateId,userId,castCount,IConstants.HAMLET,0l,type);
				}
				
				
			}
			if(type.equalsIgnoreCase("ward"))
			{
				Long castCount = boothPublicationVoterDAO.getTotalCastCountInALocation(userId, type, id, publicationDateId, constituencyId);
				List<Object[]> boothsList = boothDAO.getBoothsForWard(id,publicationDateId);
				List<SelectOptionVO> booths = new ArrayList<SelectOptionVO>();
				SelectOptionVO option = null;
				for(Object[] booth:boothsList){
					option = new SelectOptionVO();
					option.setId((Long)booth[0]);
					option.setName(booth[1]!=null?booth[1].toString():"");
					booths.add(option);
				}
				/*if(!"main".equalsIgnoreCase(queryType)){
					   mandalCasts = voterReportService.getVotersCastInfoForMultipleValues(booths,publicationDateId,userId,constituencyId,getReportLevelId(IConstants.BOOTH));
					}
				if("main".equalsIgnoreCase(queryType) || mandalCasts.size() == 0){*/
					   mandalCasts = getVotersCastInfoForMultipleBooths(booths,publicationDateId,userId,castCount,constituencyId);
					//}
			}
			
			if(type.equalsIgnoreCase("hamlet") || type.equalsIgnoreCase(IConstants.CUSTOMWARD) )
			{
				String queryCond = null;
				if(type.equalsIgnoreCase(IConstants.HAMLET)){
					queryCond =" model.hamlet.hamletId = :id ";
					
				}
					else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD)){
						queryCond =" model.ward.constituencyId = :id ";	
						
					}
				
				Long castCount = boothPublicationVoterDAO.getTotalCastCountInALocation(userId, type, id, publicationDateId, constituencyId);
				if(buildType.equalsIgnoreCase("localArea")){
			
				//List<Object[]> boothsList = boothDAO.getBoothsForWard(id,publicationDateId);
					List<Object[]> localitiesList = null;
					if(type.equalsIgnoreCase(IConstants.HAMLET))
						localitiesList = localityDAO.getAllLocalitiesForHamlet(userId, id,type);
					else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
						localitiesList = userVoterDetailsDAO.getAllLocalitiesForHamletOrWard(type,userId, id,publicationDateId,queryCond);
				
				List<SelectOptionVO> localities = new ArrayList<SelectOptionVO>();
				SelectOptionVO option = null;
				for(Object[] booth:localitiesList){
					option = new SelectOptionVO();
					option.setId((Long)booth[0]);
					option.setName(booth[1]!=null?booth[1].toString():"");
					localities.add(option);
				}
				mandalCasts = getVotersCastInfoForMultipleLocalities(localities,publicationDateId,userId,id,castCount,type);
				}else if(buildType.equalsIgnoreCase("booth")){
					//Long castCount = boothPublicationVoterDAO.getTotalCastCountInALocation(userId, type, id, publicationDateId, constituencyId);
					//List<SelectOptionVO> booths2 = getBoothsByHamletId(id,publicationDateId,userId);
					//List<Long> PollingBooths =  userVoterDetailsDAO.getUserBoothsByHamletId(userId,id,publicationDateId);
					List<Long> PollingBooths = boothPublicationVoterDAO.getAllBoothsInHamletByUser(userId, id, publicationDateId, constituencyId, queryCond);//.getAllBoothsInHamletByUser
					mandalCasts = getVotersCastInfoForMultipleHamlets(PollingBooths,publicationDateId,userId,castCount,"hamletBooths",id,type);	
			}
			}
		
			if(type.equalsIgnoreCase("booth"))
			{
				
				
				Long castCount = boothPublicationVoterDAO.getTotalCastCountInALocation(userId, type, id, publicationDateId, constituencyId);
				List<Long> hamlets = userVoterDetailsDAO.getUserHamletsByBoothId(userId ,id,publicationDateId );
				mandalCasts = getVotersCastInfoForMultipleHamlets(hamlets,publicationDateId,userId,castCount,IConstants.BOOTH,id,type);
			
				
				
			}
			if(type.equalsIgnoreCase("mandal") && buildType.equalsIgnoreCase("muncipalityCustomWard"))
			{
				if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1)));
					
					Long castCount = boothPublicationVoterDAO.getCasteCountForLocalEleBody(userId, publicationDateId, constituencyId, (Long) list.get(0));
					
					List<Object[]> wardsList = boothPublicationVoterDAO.getWardsByLocalEleBodyIdId(userId, publicationDateId, (Long) list.get(0), constituencyId);
					List<SelectOptionVO> Wards = new ArrayList<SelectOptionVO>();
					if(wardsList != null && wardsList.size() > 0)
					{
					  for(Object[] ward:wardsList)
						Wards.add(new SelectOptionVO((Long)ward[0],ward[1]!=null?ward[1].toString():""));
					
					mandalCasts = getVotersCastInfoForMultipleLocalities(Wards,publicationDateId,userId,(Long) list.get(0),castCount,"muncipalityCustomWard");
					}
				  }
			}
			
			if(mandalCasts != null && mandalCasts.size() >0){
				
				for(VoterCastInfoVO vo:mandalCasts){
					
					List<CastVO> list = vo.getVoterCastInfoVO().getCastVOs();
					
					Long totalCount = 0L;
					
					for(CastVO caste :list)
						totalCount +=caste.getCastCount();
					
					vo.setTotalCastKnownVoters(totalCount);
					
				}
				
			}
			
			 Collections.sort(mandalCasts,wardslistSort);
			 
			}
			catch(Exception e)
			{
				e.printStackTrace();
				log.error(" Exception Occured in getVotersCastDetailsForSubLevels() Method, Exception - "+e);
			}
			return mandalCasts;
			
		}
		 public static Comparator<VoterCastInfoVO> wardslistSort = new Comparator<VoterCastInfoVO>()
					{	  
							  public int compare(VoterCastInfoVO arg1,VoterCastInfoVO arg2)
								{  
								  String first = arg1.getMandalName().trim().toUpperCase();
								  String last = arg2.getMandalName().trim().toUpperCase();
								  if(first.indexOf("WARD-") != 0)
									  return 0;
									return new Integer(Integer.parseInt(first.substring(first.indexOf("-")+1))).compareTo(Integer.parseInt(last.substring(last.indexOf("-")+1)));
								
								}
					};
		
		/**
		 * @author Sravanthi
		 */
		public List<SelectOptionVO> getBoothsByPanchayatIdandConstituencyId(Long id,Long publicationDateId,Long constituencyId,String type,Long tehsilId)
		{
			List<SelectOptionVO> booths = new ArrayList<SelectOptionVO>();
			//List<Object[]> PollingBooths = hamletBoothPublicationDAO.getBoothsInPanchayatByPublicationId(id,publicationDateId);
			List<Object[]> PollingBooths =  boothDAO.getBoothsInAPanchayatUsingConstituencyId(id,publicationDateId,constituencyId,type,tehsilId);
			SelectOptionVO hamlet = null;
			for (Object[] panchayat : PollingBooths) {
				hamlet = new SelectOptionVO((Long)panchayat[0],panchayat[1].toString());
				hamlet.setLocation(panchayat[2] != null?panchayat[2].toString():"");
				hamlet.setVillageCovered(panchayat[3] != null?panchayat[3].toString():"");
				booths.add(hamlet);
			}
			return booths;	
		}
		public List<SelectOptionVO> getBoothsByPanchayatId(Long id,Long publicationDateId)
		{
			List<SelectOptionVO> booths = new ArrayList<SelectOptionVO>();
			//List<Object[]> PollingBooths = hamletBoothPublicationDAO.getBoothsInPanchayatByPublicationId(id,publicationDateId);
			List<Object[]> PollingBooths =  boothDAO.getBoothsInAPanchayat(id,publicationDateId);
			SelectOptionVO hamlet = null;
			for (Object[] panchayat : PollingBooths) {
				hamlet = new SelectOptionVO((Long)panchayat[0],panchayat[1].toString());
				hamlet.setLocation(panchayat[2] != null?panchayat[2].toString():"");
				hamlet.setVillageCovered(panchayat[3] != null?panchayat[3].toString():"");
				booths.add(hamlet);
			}
			return booths;	
		}
		
		public List<SelectOptionVO> getBoothsByPanchayatId(Long id,Long publicationDateId,boolean isPartial)
		{
			List<SelectOptionVO> booths = new ArrayList<SelectOptionVO>();
			//List<Object[]> PollingBooths = hamletBoothPublicationDAO.getBoothsInPanchayatByPublicationId(id,publicationDateId);

			List<Object[]> boothsList1 = boothDAO.getBoothsInAPanchayat(id,publicationDateId);
			List<Object[]> boothsList2 = null;
			if(isPartial)
			  boothsList2 = partialBoothPanchayatDAO.getPartialBoothsDetails(id, publicationDateId);
		
			   SelectOptionVO hamlet = null;
				if(boothsList1 != null && boothsList1.size() > 0)
				  for(Object[] params:boothsList1)
				  {
					 	hamlet = new SelectOptionVO((Long)params[0],params[1].toString());
						hamlet.setLocation(params[2] != null?params[2].toString():"");
						hamlet.setVillageCovered(params[3] != null?params[3].toString():"");
						booths.add(hamlet);
				  }
				
				if(boothsList2 != null && boothsList2.size() > 0)
				{
					SelectOptionVO  optionVO  = null;
					for(Object[] params:boothsList2)
					  {
						optionVO = checkSelectOptionVOExist((Long)params[0],booths);
						if(optionVO == null)
						{
							optionVO = new SelectOptionVO((Long)params[0],params[1].toString());
							optionVO.setLocation(params[2] != null?params[2].toString():"");
							optionVO.setVillageCovered(params[3] != null?params[3].toString():"");
							booths.add(optionVO);
						}
					  }
				}
			return booths;	
		}
	//getting All Mandals For Constituency
		
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleMandal(List<SelectOptionVO> mandalList,Long publicationDateId,Long userId,Long constituencyId,Long totalVoters)
	{
		VoterCastInfoVO voterCastInfoVO = null;
		List<VoterCastInfoVO> mandalCasts = new ArrayList<VoterCastInfoVO>();
		if(mandalList != null && mandalList.size() > 0){
			for(SelectOptionVO mandals : mandalList)
			{
				voterCastInfoVO = new VoterCastInfoVO();
				String mandalId= mandals.getId().toString();
				String id=mandalId.substring(1);
				String mandalName = mandals.getName();
				voterCastInfoVO.setMandalName(mandalName);
				// For Mandal
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					List<Object[]> mandalCastDetails = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,"mandal",new Long(id),publicationDateId,constituencyId);
					Long totalSubVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId("mandal"),new Long(id),publicationDateId,constituencyId);
					if(totalSubVoters == null)
						totalSubVoters = getVotersCountByPublicationIdInALocation("mandal",new Long(id),publicationDateId,userId);
					voterCastInfoVO.setVoterCastInfoVO(calculatePercentageForUserCast(mandalCastDetails,totalVoters,totalSubVoters));
					//voterCastInfoVO.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByMandalAndPublicationDate(new Long(id),publicationDateId)));
				}
				//Muncipality
				if(mandalId.substring(0, 1).toString().trim().equalsIgnoreCase("1")){
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id));
					
					List<Object[]> mandalCastDetails = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,"localElectionBody",(Long)list.get(0),publicationDateId,constituencyId);
					Long totalSubVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId("localElectionBody"),new Long(id),publicationDateId,constituencyId);
					if(totalSubVoters == null)
						totalSubVoters = getVotersCountByPublicationIdInALocation("localElectionBody",new Long(id),publicationDateId,userId);
				voterCastInfoVO.setVoterCastInfoVO(calculatePercentageForUserCast(mandalCastDetails,totalVoters,totalSubVoters));
				//voterCastInfoVO.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.getVotersCastInfoFromLocalElectionBody(new Long(id),publicationDateId))); 
				}
				mandalCasts.add(voterCastInfoVO);
				
		}
		}
		return mandalCasts;
		
		
	}
	
//getting All Panchayaties For Mandal
	
	public List<VoterCastInfoVO> getVotersCastInfoForMultiplePanchayats(List<SelectOptionVO> panchayatList,Long publicationDateId,Long userId,Long totalVoters,Long constituencyId)
	{
		VoterCastInfoVO voterCastInfo = null;
		List<VoterCastInfoVO> panchayatsList = new ArrayList<VoterCastInfoVO>();
		if(panchayatList != null)
		{
			for(SelectOptionVO pancahyats :panchayatList)
			{
				voterCastInfo = new VoterCastInfoVO();
				Long panchayatId = pancahyats.getId();
				String panchayatName = pancahyats.getName();
				voterCastInfo.setMandalName(panchayatName);
				voterCastInfo.setLocationId(panchayatId);
				List<Object[]> panchayatCastDetails = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,"panchayat",panchayatId,publicationDateId,null);
				Long totalSubVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId("Panchayat"),panchayatId,publicationDateId,constituencyId);
				 if(totalSubVoters == null)
				  totalSubVoters = getVotersCountByPublicationIdInALocation("panchayat",panchayatId,publicationDateId,userId);
				voterCastInfo.setVoterCastInfoVO(calculatePercentageForUserCast(panchayatCastDetails,totalVoters,totalSubVoters));
				
				//voterCastInfo.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByPanchayatAndPublicationDate(new Long(panchayatId),publicationDateId)));
				panchayatsList.add(voterCastInfo);
			}
		}
		return panchayatsList;
	}
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleWards(List<Object[]> wardList,Long publicationDateId,Long userId,Long totalVoters,Long constituencyId)
	{
		VoterCastInfoVO voterCastInfo = null;
		List<VoterCastInfoVO> wardsList = new ArrayList<VoterCastInfoVO>();
		if(wardList != null)
		{
			for(Object[] ward :wardList)
			{
				voterCastInfo = new VoterCastInfoVO();
				voterCastInfo.setMandalName(ward[1] != null?ward[1].toString():"");
				voterCastInfo.setLocationId((Long)ward[0]);
				List<Object[]> wardCastDetails = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,"ward",(Long)ward[0],publicationDateId,null);
				Long totalSubVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId("Ward"),(Long)ward[0],publicationDateId,constituencyId);
				if(totalSubVoters != null)
				totalSubVoters = getVotersCountByPublicationIdInALocation("ward",(Long)ward[0],publicationDateId,userId);
				voterCastInfo.setVoterCastInfoVO(calculatePercentageForUserCast(wardCastDetails,totalVoters,totalSubVoters));
				
				//voterCastInfo.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByPanchayatAndPublicationDate(new Long(panchayatId),publicationDateId)));
				wardsList.add(voterCastInfo);
			}
		}
		return wardsList;
	}
	
	
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleHamlets(List<Long> hamlets,Long publicationDateId,Long userId,Long totalVoters,String type , Long boothId ,String myType)
	{
		VoterCastInfoVO voterCastInfo = null;
		
		List<VoterCastInfoVO> boothInfo = new ArrayList<VoterCastInfoVO>();
		if(hamlets != null)
		{
			for(Long hamletId :hamlets)
			{
				voterCastInfo = new VoterCastInfoVO();
				//Long hamletId=booths.getId();
				//String boothPartNo = booths.getId().toString();
				List<Object[]> hamletCastDetails=null;
				Long totalSubVoters=0l;
			//List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletIds(userId , hamlets);
				if(type.equalsIgnoreCase("booth")){
					 hamletCastDetails = boothPublicationVoterDAO
					.getCastAndGenderWiseVotersCountByPublicationIdInALocationByBooth(
							userId,"booth", hamletId, publicationDateId,
							boothId ,type);
					 totalSubVoters = boothPublicationVoterDAO.getTotalVotersCountForHamletByBooth(userId,hamletId,publicationDateId,type,boothId,myType);
					 
				}
					else if(type.equalsIgnoreCase("hamlet")){
			         hamletCastDetails = boothPublicationVoterDAO
					.getCastAndGenderWiseVotersCountByPublicationIdInALocation(
							userId,"hamlet", hamletId, publicationDateId,
							null);
		
			         
			 totalSubVoters = boothPublicationVoterDAO
					.getTotalVotersCountForHamlet(userId, hamletId,
							publicationDateId,"hamlet");
					}
				if(type.equalsIgnoreCase("hamletBooths")){
					 hamletCastDetails = boothPublicationVoterDAO
					.getCastAndGenderWiseVotersCountByPublicationIdInALocationByBooth(
							userId,"hamletBooths",boothId, publicationDateId,
							 hamletId,myType);
					 totalSubVoters = boothPublicationVoterDAO.getTotalVotersCountForHamletByBooth(userId,boothId,publicationDateId,"hamletBooths",hamletId,myType);
					 
				}
			voterCastInfo
					.setVoterCastInfoVO(calculatePercentageForUserCast(
							hamletCastDetails, totalVoters,totalSubVoters));

			if(type.equalsIgnoreCase("hamletBooths"))
				voterCastInfo.setMandalName("Booth-"+boothDAO.get(hamletId).getPartNo());
			
			else
				voterCastInfo.setMandalName(hamletDAO.get(hamletId).getHamletName());
			voterCastInfo.setLocationId(hamletId);
			boothInfo.add(voterCastInfo);
			}
		}
		return boothInfo;
		
	}

	
	//getting SubLevel Data For Booths
		public List<VoterCastInfoVO> getVotersCastInfoForMultipleLocalities(List<SelectOptionVO> localitiesList,Long publicationDateId,Long userId,Long hamletId,Long totalVoters,String type)
		{
			VoterCastInfoVO voterCastInfo = null;
			
			List<VoterCastInfoVO> localityInfo = new ArrayList<VoterCastInfoVO>();
			if(localitiesList != null)
			{
				for(SelectOptionVO locality :localitiesList)
				{
					voterCastInfo = new VoterCastInfoVO();
					Long localityId=locality.getId();
					
					List<Object[]> hamletCastDetails = boothPublicationVoterDAO
							.getCastAndGenderWiseVotersCountByPublicationIdForLocality(
									userId, localityId, hamletId, publicationDateId,type);
					
					Long totalSubVoters = boothPublicationVoterDAO
							.getTotalVotersCountForHamlet(userId, hamletId,
									publicationDateId, type);
					voterCastInfo
					.setVoterCastInfoVO(calculatePercentageForUserCast(
							hamletCastDetails, totalVoters,totalSubVoters));

			
			voterCastInfo.setMandalName(locality.getName());
			voterCastInfo.setLocationId(locality.getId());
			voterCastInfo.setHamletId(hamletId);
			localityInfo.add(voterCastInfo);
		}
	}
	return localityInfo;
	
}

//getting SubLevel Data For Booths
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleBooths(List<SelectOptionVO> boothsList,Long publicationDateId,Long userId,Long totalVoters,Long constituencyId)
	{
		VoterCastInfoVO voterCastInfo = null;
		
		List<VoterCastInfoVO> boothInfo = new ArrayList<VoterCastInfoVO>();
		if(boothsList != null)
		{
			for(SelectOptionVO booths :boothsList)
			{
				voterCastInfo = new VoterCastInfoVO();
				Long boothId=booths.getId();
				//String boothPartNo = booths.getId().toString();
				List<Object[]> boothCastDetails = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,"booth",boothId,publicationDateId,null);
				Long totalSubVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId("booth"),boothId,publicationDateId,constituencyId);
				if(totalSubVoters == null)
				totalSubVoters = getVotersCountByPublicationIdInALocation("booth",boothId,publicationDateId,userId);
				voterCastInfo.setVoterCastInfoVO(calculatePercentageForUserCast(boothCastDetails,totalVoters,totalSubVoters));
				
				//voterCastInfo.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByBoothIdAndPublicationDate(new Long(boothId),publicationDateId)));
				voterCastInfo.setMandalName(booths.getName());
				voterCastInfo.setLocationId(boothId);
				boothInfo.add(voterCastInfo);
			}
		}
		return boothInfo;
		
	}
	
	public List<VoterHouseInfoVO> getFamilyInfo(Long boothId, Long publicationDateId,String houseNo,Long userId)
	{
		List<VoterHouseInfoVO> voterHouseInfoVOList = new ArrayList<VoterHouseInfoVO>();
		VoterHouseInfoVO voterHouseInfoVO = null;
		List<Voter> votersInfoList = boothPublicationVoterDAO.findFamiliesInfo(boothId, publicationDateId, houseNo);
	    
		List<Long> voterIdsList = new ArrayList<Long>(0);
		Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
		if(votersInfoList != null && votersInfoList.size() > 0)
		{
		  for(Voter voter:votersInfoList)
		   voterIdsList.add(voter.getVoterId());
		  
		  List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIdsList, userId);
		 if(list != null && list.size() > 0)
			for(Object[] params:list)
			 mobileNosMap.put((Long)params[0], params[1]!= null?params[1].toString():"N/A");
		}
		long sno = 1;
		for(Voter voter : votersInfoList){
	    	voterHouseInfoVO = new VoterHouseInfoVO();
	    	voterHouseInfoVO.setsNo(sno);
	    	//voterHouseInfoVO.setName(voter.getFirstName()+" "+voter.getLastName());
	    	voterHouseInfoVO.setName(voter.getName());
	    	voterHouseInfoVO.setGender(voter.getGender());
	    	voterHouseInfoVO.setAge(voter.getAge());
	    	voterHouseInfoVO.setHouseNo(voter.getHouseNo());
	    	//voterHouseInfoVO.setGaurdian(voter.getRelativeFirstName()+" "+voter.getRelativeLastName());
	    	voterHouseInfoVO.setGaurdian(voter.getRelativeName());
	    	voterHouseInfoVO.setRelationship(voter.getRelationshipType());
	    	
	    	voterHouseInfoVO.setVoterId(voter.getVoterId());
	    	voterHouseInfoVO.setBoothId(boothId);
	    	if(mobileNosMap.get(voter.getVoterId()) != null)
	    	 voterHouseInfoVO.setMobileNo(mobileNosMap.get(voter.getVoterId()));
	    	else
	    	 voterHouseInfoVO.setMobileNo("N/A");
	    	voterHouseInfoVOList.add(voterHouseInfoVO);
	    	sno = sno+1;
	    }
		return voterHouseInfoVOList;
	} 
	
	/**
	 * This method will get all the voters information in a family
	 * 
	 * @author Samba Penugonda
	 * @param Long  boothId ,boothId of the house
	 * @param Long publicationDateId , the voters publication date Id
	 * @param Long houseNo ,houseNo  of the family
	 * @param Long userId , userId of logged in user
	 * @return the voter details of a family
	 * 
	 */

	public List<VoterHouseInfoVO> getFamilyInformation(Long hamletId , Long boothId, Long publicationDateId,String houseNo,Long userId ,String selectType)
	{		
		log.debug("Entered into the getFamilyInformation method");
		
		List<VoterHouseInfoVO> voterHouseInfoVOList = new ArrayList<VoterHouseInfoVO>();		
		Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
		try{
		
			VoterHouseInfoVO voterHouseInfoVO = null;
			List<Voter> votersInfoList = boothPublicationVoterDAO.findFamiliesInfo(boothId, publicationDateId, houseNo);
		    long sno = 1;
		    
		    List<Long> voterIds = new ArrayList<Long>();
		    for(Voter voter : votersInfoList)
		    	voterIds.add(voter.getVoterId());
		    
		    if(voterIds != null && voterIds.size() > 0)
		    {
		      List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIds, userId);
		      for(Object[] params:list)
		    	  mobileNosMap.put((Long)params[0], params[1] != null?params[1].toString():"N/A");
		    }
		    
		    List<Object[]> votersCategoriesList = 
					 voterCategoryValueDAO.getVoterCategoryValuesForVoters(userId,voterIds);
			 
		    
		   Map<Long, VoterHouseInfoVO> voterCastePartyDetails =  getUserCasteAndSelectedPartyVoters(voterIds,userId);
		    	
			for(Voter voter : votersInfoList){
		    	voterHouseInfoVO = new VoterHouseInfoVO();
		    	voterHouseInfoVO.setsNo(sno);
		    	voterHouseInfoVO.setName(voter.getName());
		    	voterHouseInfoVO.setGender(voter.getGender());
		    	voterHouseInfoVO.setAge(voter.getAge());
		    	voterHouseInfoVO.setHouseNo(voter.getHouseNo());
		    	voterHouseInfoVO.setGaurdian(voter.getRelativeName());
		    	voterHouseInfoVO.setRelationship(voter.getRelationshipType());
		    	voterHouseInfoVO.setVoterIdCardNo(voter.getVoterIDCardNo());
		    	
		    	voterHouseInfoVO.setVoterId(voter.getVoterId());
		    	voterHouseInfoVO.setBoothId(boothId);
		    	if(mobileNosMap.get(voter.getVoterId()) != null)
		    	 voterHouseInfoVO.setMobileNo(mobileNosMap.get(voter.getVoterId()));
		    	else
		    	 voterHouseInfoVO.setMobileNo("N/A");
		    	voterHouseInfoVO.setBoothName(boothDAO.getPartNoByBoothId(voterHouseInfoVO.getBoothId()).get(0).toString());
		    	VoterHouseInfoVO voterCastPartyVO = voterCastePartyDetails.get(voter.getVoterId());
		    	
		    	setVotersCategories(votersCategoriesList,voter,voterHouseInfoVO);
		    	setCastePartyDetails(voterHouseInfoVO,voterCastPartyVO);
		    	
		    
		    	voterHouseInfoVOList.add(voterHouseInfoVO);
		    	//getUserCasteAndSelectedParty(voterHouseInfoVO , voter.getVoterId(),userId);
		    	
		    	
		    	sno = sno+1;
	    }
		
		}catch(Exception e){
			log.error("Exception raised in getFamilyInformation method");
			e.printStackTrace();
		}
		return voterHouseInfoVOList;
	}
	/**
	 * This method will get all the voters information in a family
	 * 
	 * @author Samba Penugonda
	 * @param Long  boothId ,boothId of the house
	 * @param Long publicationDateId , the voters publication date Id
	 * @param Long houseNo ,houseNo  of the family
	 * @param Long userId , userId of logged in user
	 * @return the voter details of a family
	 * 
	 */

	public HHSurveyVO getFamilyInformationForHHSurvey(Long hamletId , Long boothId, Long publicationDateId,String houseNo,Long userId ,String selectType,Long voterId)
	{		
		log.debug("Entered into the getFamilyInformationForHHSurvey method");
		
		List<VoterHouseInfoVO> voterHouseInfoVOList = new ArrayList<VoterHouseInfoVO>();		
		Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
		try{
		
			VoterHouseInfoVO voterHouseInfoVO = null;
			List<Voter> votersInfoList = boothPublicationVoterDAO.findFamiliesInfo(boothId, publicationDateId, houseNo);
		    long sno = 1;
		    
		    List<Long> voterIds = new ArrayList<Long>();
		    for(Voter voter : votersInfoList)
		    	voterIds.add(voter.getVoterId());
		    
		    List<Long> vtrsWithSameHH=new ArrayList<Long>();
		    List<Long> vtrsWithDiffHH=new ArrayList<Long>();
		    List<Long> ttlVtrIds=new ArrayList<Long>();
		    
		    List<Booth> boothDtls=boothDAO.getModelByBoothId(boothId);
			Long muncipalityId=null;
			Long panchayatId=null;
			Long constituencyId=null;
			for(Booth booth:boothDtls){
				if(booth.getLocalBody()!=null){
					muncipalityId=booth.getLocalBody().getLocalElectionBodyId();
					constituencyId=booth.getConstituency().getConstituencyId();
				}else if(booth.getPanchayat()!=null){
					panchayatId=booth.getPanchayat().getPanchayatId();
					constituencyId=booth.getConstituency().getConstituencyId();
				}
			}
		    
		    List<Object[]> houseHoldIds=houseHoldVoterDAO.getHouseHoldIdOfFamilyHeadForVoter(houseNo,panchayatId,muncipalityId);
		    //List<Object[]> hhIds=houseHoldVoterDAO.getHouseHoldIdOfVoter(houseNo);
		    
		    
		    Map<Long,List<Long>> hhMap=new HashMap<Long, List<Long>>();
		    if(houseHoldIds.size()>0){
				
				for(Object[] obj:houseHoldIds){
					if(hhMap.get(Long.valueOf(obj[2].toString()))!=null){
						List<Long> vtrids=hhMap.get(Long.valueOf(obj[2].toString()));
						if(obj[0]!=null){
							vtrids.add(Long.valueOf(obj[0].toString()));
						}
						hhMap.put(Long.valueOf(obj[2].toString()), vtrids);
						ttlVtrIds.addAll(vtrids);
					}else{
						List<Long> vtrids=new ArrayList<Long>();
						if(obj[0]!=null){
							vtrids.add(Long.valueOf(obj[0].toString()));
						}
						hhMap.put(Long.valueOf(obj[2].toString()), vtrids);
						ttlVtrIds.addAll(vtrids);
					}
					
				}
			}
			
		    Long houseHoldId=houseHoldVoterDAO.getHouseHoldIdForVoter(voterId);

			for (Entry<Long, List<Long>> entry : hhMap.entrySet())
			{
				List<Long> vtrIds=entry.getValue();
				if(houseHoldId==null){
					vtrsWithDiffHH.addAll(vtrIds);
				}else{
					if(houseHoldId.equals(entry.getKey())){
						vtrsWithSameHH.addAll(vtrIds);
					}else{
						vtrsWithDiffHH.addAll(vtrIds);
					}
				}
			}
			
			
			//IN THE CASE WHEN HOUSE HOLD ID EXIST WHEN PERSON ADDED TO OTHER HOUSE  
			if(hhMap.size()<=0 && houseHoldId !=null){
				List<HouseHoldVoter> hhVtrList=houseHoldVoterDAO.getHouseHoldsVoterdDetailsByHouseHoldId(houseHoldId);
				if(hhVtrList.size()>0){
					for(HouseHoldVoter param:hhVtrList){
						vtrsWithSameHH.add(param.getVoterId());
					}
				}
			}
			
			//
			if(hhMap.size()<=0 && houseHoldId ==null){
				List<Long> vtrTempList= houseHoldVoterDAO.getVoterIdsExistByVoterIds(voterIds);
				if(vtrTempList.size()>0){
					for(Long param:vtrTempList){
						vtrsWithDiffHH.add(param);
					}
				}
			}
		    
		   
			
		    List<HouseHoldVoter> vtrsInHHList=new ArrayList<HouseHoldVoter>();
		    List<VoterHouseInfoVO> categoriesSvdList=new ArrayList<VoterHouseInfoVO>();
		    List<VoterHouseInfoVO> childrenList=new ArrayList<VoterHouseInfoVO>();
		    
		    if(houseHoldId!=null){
		    	vtrsInHHList=houseHoldVoterDAO.getHouseHoldsVoterdDetailsByHouseHoldId1(houseHoldId);
		    }
		    if(vtrsInHHList.size()>0){
		    	VoterHouseInfoVO vtrInfo=null;
		    	for(HouseHoldVoter hh:vtrsInHHList){
		    		if(hh.getVoterId()!=null){
		    		if(hh.getEducationId()!=null){
		    			vtrInfo=new VoterHouseInfoVO();
		    			vtrInfo.setVoterId(hh.getVoterId());
		    			vtrInfo.setCategoryValuesId(IConstants.HOUSE_HOLD_VOTER_EDUCATION);
		    			//vtrInfo.setName(name);
		    			vtrInfo.setCategoryValueId(hh.getEducationId());
		    			
		    			categoriesSvdList.add(vtrInfo);
		    		}
		    		if(hh.getOccupationId()!=null){
		    			vtrInfo=new VoterHouseInfoVO();
		    			vtrInfo.setVoterId(hh.getVoterId());
		    			vtrInfo.setCategoryValuesId(IConstants.HOUSE_HOLD_VOTER_OCCUPATION);
		    			//vtrInfo.setName(name);
		    			vtrInfo.setCategoryValueId(hh.getOccupationId());
		    			
		    			categoriesSvdList.add(vtrInfo);
		    		}if(hh.getSocialCategoryId()!=null){
		    			vtrInfo=new VoterHouseInfoVO();
		    			vtrInfo.setVoterId(hh.getVoterId());
		    			vtrInfo.setCategoryValuesId(IConstants.HOUSE_HOLD_VOTER_SOCIAL_POSITIONS);
		    			//vtrInfo.setName(name);
		    			vtrInfo.setCategoryValueId(hh.getSocialCategoryId());
		    			
		    			categoriesSvdList.add(vtrInfo);
		    		}
		    		/*if(hh.getVoterFamilyRelation()!=null){
		    			vtrInfo=new VoterHouseInfoVO();
		    			vtrInfo.setVoterId(hh.getVoterId());
		    			vtrInfo.setCategoryValuesId(hh.getVoterFamilyRelation().getId());
		    			vtrInfo.setName(hh.getVoterFamilyRelation().getRelation());
		    			vtrInfo.setCategoryValueId(hh.getVoterFamilyRelationId());
		    			
		    			categoriesSvdList.add(vtrInfo);
		    		}*/
		    		//vtrsWithSameHH.add(hh.getVoterId());
		    		
		    	}
		    		
		    		else{
		    			List<VoterHouseInfoVO> categList=new ArrayList<VoterHouseInfoVO>();
		    			VoterHouseInfoVO childVO=new VoterHouseInfoVO();
		    			if(hh.getHouseHoldsFamilyDetails()!=null){
		    				childVO.setName(hh.getHouseHoldsFamilyDetails().getName());
		    				childVO.setGaurdian(hh.getHouseHoldsFamilyDetails().getRelativeName());
		    				childVO.setVtrFamilyRelTypeId(hh.getVoterFamilyRelationId());
		    				childVO.setHHFamilyDetailsId(hh.getHouseHoldsFamilyDetailsId());
		    				childVO.setAge(hh.getHouseHoldsFamilyDetails().getAge());
		    				childVO.setChildren(true);
		    			}
		    			
		    			vtrInfo=new VoterHouseInfoVO();
		    			if(hh.getEducationId()!=null){
			    			vtrInfo=new VoterHouseInfoVO();
			    			vtrInfo.setCategoryValuesId(IConstants.HOUSE_HOLD_VOTER_EDUCATION);
			    			vtrInfo.setCategoryValueId(hh.getEducationId());
			    			categList.add(vtrInfo);
			    		}
			    		if(hh.getOccupationId()!=null){
			    			vtrInfo=new VoterHouseInfoVO();
			    			vtrInfo.setCategoryValuesId(IConstants.HOUSE_HOLD_VOTER_OCCUPATION);
			    			vtrInfo.setCategoryValueId(hh.getOccupationId());
			    			
			    			categList.add(vtrInfo);
			    		}if(hh.getSocialCategoryId()!=null){
			    			vtrInfo=new VoterHouseInfoVO();
			    			vtrInfo.setVoterId(hh.getVoterId());
			    			vtrInfo.setCategoryValuesId(IConstants.HOUSE_HOLD_VOTER_SOCIAL_POSITIONS);
			    			//vtrInfo.setName(name);
			    			vtrInfo.setCategoryValueId(hh.getSocialCategoryId());
			    			
			    			categList.add(vtrInfo);
			    		}
			    		if(hh.getVoterFamilyRelation()!=null){
			    			vtrInfo=new VoterHouseInfoVO();
			    			vtrInfo.setVoterId(hh.getVoterId());
			    			vtrInfo.setCategoryValuesId(hh.getVoterFamilyRelation().getId());
			    			vtrInfo.setName(hh.getVoterFamilyRelation().getRelation());
			    			vtrInfo.setCategoryValueId(hh.getVoterFamilyRelation().getVoterFamilyRelationId());
			    			childVO.setVoterFamilyRelId(hh.getVoterFamilyRelation().getVoterFamilyRelationId());
			    			categList.add(vtrInfo);
			    		}
			    		
			    		childVO.setCategoriesList(categList);
			    		
			    		List<Long> ctgrysReqForHHSurveyList=new ArrayList<Long>();
				    	ctgrysReqForHHSurveyList.add(IConstants.HOUSE_HOLD_VOTER_OCCUPATION);
				    	ctgrysReqForHHSurveyList.add(IConstants.HOUSE_HOLD_VOTER_EDUCATION);
				    	ctgrysReqForHHSurveyList.add(IConstants.HOUSE_HOLD_VOTER_SOCIAL_POSITIONS);
				    
				    	List<Object[]> relationsList= voterFamilyRelationDAO.getAllRelations();
				 	   	List<GenericVO> relsList=new ArrayList<GenericVO>();
				 	   	GenericVO defaultGvo = new GenericVO();
				 	   	defaultGvo.setId(0l);
				 	   	defaultGvo.setName("Select");
				 	   	
				 	   	relsList.add(defaultGvo);
				 		
				 	    for(Object[] ob:relationsList){
				 	    	GenericVO gvo=new GenericVO();
				 	    	gvo.setId(Long.valueOf(ob[0].toString()));
				 	    	gvo.setName(ob[1].toString());
				 	    	
				 	    	relsList.add(gvo);
				 	    }
				    	
				    	Map<Long,List<GenericVO>> categoriesMap=getCategoriesForHHSurvey();
				    	List<GenericVO> occupationList=new ArrayList<GenericVO>();
				    	List<GenericVO> educationList=new ArrayList<GenericVO>();
				    	List<GenericVO> socialPositionList=new ArrayList<GenericVO>();
				    	
				    	GenericVO gvo = new GenericVO();
						gvo.setId(0l);
						gvo.setName("Select");
						occupationList.add(gvo);
						educationList.add(gvo);
						socialPositionList.add(gvo);
				    	
				    	
			    		for (Entry<Long, List<GenericVO>> entry : categoriesMap.entrySet())
						{
							//System.out.println(entry.getKey() + "/" + entry.getValue());
							if(entry.getKey()==IConstants.HOUSE_HOLD_VOTER_OCCUPATION){
								occupationList.addAll(entry.getValue());
							}else if(entry.getKey()==IConstants.HOUSE_HOLD_VOTER_EDUCATION){
								educationList.addAll(entry.getValue());
							}else if(entry.getKey()==IConstants.HOUSE_HOLD_VOTER_SOCIAL_POSITIONS){
								socialPositionList.addAll(entry.getValue());
							}
						}
			    		
			    		
						
			    		childVO.setOccupationList(occupationList);
			    		childVO.setEducationList(educationList);
			    		childVO.setSocialPositionList(socialPositionList);
						childVO.setFamilyRelsList(relsList);
						
			    		
			    		childrenList.add(childVO);
		    		}
		    	}
		    }
			
			houseHoldVoterChildVO=new HHSurveyVO();
			voterHouseInfoVOList=getTheVtrList(sno,boothId,voterIds,vtrsWithSameHH,vtrsWithDiffHH,ttlVtrIds,votersInfoList,categoriesSvdList,constituencyId,publicationDateId);
			
		    houseHoldVoterChildVO.setParentsList(voterHouseInfoVOList);
		    houseHoldVoterChildVO.setChildrenList(childrenList);
		    
			
			//voterHouseInfoVOList.addAll(childrenList);	
		
		}catch(Exception e){
			log.error("Exception raised in getFamilyInformationForHHSurvey method");
			e.printStackTrace();
		}
		return houseHoldVoterChildVO;
	}
	
	public List<VoterHouseInfoVO> getTheVtrList(Long sno,Long boothId,List<Long> voterIds,List<Long> vtrsWithSameHH,List<Long> vtrsWithDiffHH,List<Long> totalVtrIds,List<Voter> votersInfoList,List<VoterHouseInfoVO> categoriesSvdList,Long constituencyId,Long publicationDateId){
		
		if(vtrsWithDiffHH.size()<=0){
			if(vtrsWithSameHH.size()>0){
				voterIds=vtrsWithSameHH;
			}
		}
		
		List<Long> vtrIdsAlrdyIn=new ArrayList<Long>();
		if(vtrsWithDiffHH.size()>0 && vtrsWithSameHH.size()<=0){
			vtrIdsAlrdyIn.addAll(vtrsWithDiffHH);
		}
		
		if(vtrsWithDiffHH.size()>0 && vtrsWithSameHH.size()>0){
			voterIds=vtrsWithSameHH;
		}
		
		
		//CREATING MAP HAVING PARAMETERS WITH VOTERID AND ITS VoterHouseInfoVO OF CATEGORIES
		Map<Long,List<VoterHouseInfoVO>> categoriesForSavedVotersMap=new HashMap<Long, List<VoterHouseInfoVO>>();
		if(categoriesSvdList.size()>0){
		for(VoterHouseInfoVO param:categoriesSvdList){
			if(categoriesForSavedVotersMap.get(param.getVoterId())!=null){
				List<VoterHouseInfoVO> vhListTemp=categoriesForSavedVotersMap.get(param.getVoterId());
				vhListTemp.add(param);
				
				categoriesForSavedVotersMap.put(param.getVoterId(), vhListTemp);
			}else{
				List<VoterHouseInfoVO> vhListTemp=new ArrayList<VoterHouseInfoVO>();
				vhListTemp.add(param);
				
				categoriesForSavedVotersMap.put(param.getVoterId(), vhListTemp);
			}
		}
		}
		
		
		Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
		List<VoterHouseInfoVO> voterHouseInfoVOList=new ArrayList<VoterHouseInfoVO>();
		
		VoterHouseInfoVO voterHouseInfoVO=null;
		if(voterIds != null && voterIds.size() > 0)
	    {
	      List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIds, 1l);
	      for(Object[] params:list)
	    	  mobileNosMap.put((Long)params[0], params[1] != null?params[1].toString():"N/A");
	    }
	    
	    	List<Long> ctgrysReqForHHSurveyList=new ArrayList<Long>();
	    	ctgrysReqForHHSurveyList.add(IConstants.HOUSE_HOLD_VOTER_OCCUPATION);
	    	ctgrysReqForHHSurveyList.add(IConstants.HOUSE_HOLD_VOTER_EDUCATION);
	    	ctgrysReqForHHSurveyList.add(IConstants.HOUSE_HOLD_VOTER_SOCIAL_POSITIONS);
	    
	    	
	    	
	   List<Object[]> votersCategoriesList = 
				 voterCategoryValueDAO.getVoterCategoryValuesForVotersForHHSurvey(1l,voterIds,ctgrysReqForHHSurveyList);
	   
	    
	   List<Object[]> hhVoterRelations =  houseHoldVoterDAO.getVoterRelationsByVoterIds(voterIds);
	   Map<Long,Long> hhVoterRelMap=new HashMap<Long, Long>();
	   if(hhVoterRelations.size()>0){
		   for(Object[] obj:hhVoterRelations){
			   hhVoterRelMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[1].toString()));
		   }
		   
	   }
	   List<Object[]> relationsList= voterFamilyRelationDAO.getAllRelations();
	   List<GenericVO> relsList=new ArrayList<GenericVO>();
	   	GenericVO defaultGvo = new GenericVO();
	   	defaultGvo.setId(0l);
	   	defaultGvo.setName("Select");
	   	
	   	relsList.add(defaultGvo);
		
	    for(Object[] ob:relationsList){
	    	GenericVO gvo=new GenericVO();
	    	gvo.setId(Long.valueOf(ob[0].toString()));
	    	gvo.setName(ob[1].toString());
	    	
	    	relsList.add(gvo);
	    }
	    
	   List<Voter> vtrsInfoList=boothPublicationVoterDAO.getVoterInfoByVoterIds(constituencyId, publicationDateId, voterIds);		
		
	   //Map<Long, VoterHouseInfoVO> voterCastePartyDetails =  getUserCasteAndSelectedPartyVoters(voterIds,userId);
	  // for(Voter voter : votersInfoList){	
		for(Voter voter : vtrsInfoList){
			//if(voterIds.contains(voter.getVoterId())){
				
				if(!vtrIdsAlrdyIn.contains(voter.getVoterId())){
	    	voterHouseInfoVO = new VoterHouseInfoVO();
	    	voterHouseInfoVO.setsNo(sno);
	    	voterHouseInfoVO.setName(voter.getName());
	    	voterHouseInfoVO.setGender(voter.getGender());
	    	voterHouseInfoVO.setAge(voter.getAge());
	    	voterHouseInfoVO.setHouseNo(voter.getHouseNo());
	    	voterHouseInfoVO.setGaurdian(voter.getRelativeName());
	    	voterHouseInfoVO.setRelationship(voter.getRelationshipType());
	    	voterHouseInfoVO.setVoterIdCardNo(voter.getVoterIDCardNo());
	    	
	    	voterHouseInfoVO.setVoterId(voter.getVoterId());
	    	voterHouseInfoVO.setBoothId(boothId);
	    	if(mobileNosMap.get(voter.getVoterId()) != null)
	    	 voterHouseInfoVO.setMobileNo(mobileNosMap.get(voter.getVoterId()));
	    	else
	    	voterHouseInfoVO.setMobileNo("N/A");
	    	voterHouseInfoVO.setBoothName(boothDAO.getPartNoByBoothId(voterHouseInfoVO.getBoothId()).get(0).toString());
	    	//VoterHouseInfoVO voterCastPartyVO = voterCastePartyDetails.get(voter.getVoterId());
	    	
	    	/*if(vtrIdsAlrdyIn.contains(voter.getVoterId())){
	    		voterHouseInfoVO.setDisable(true);
			}else{
				voterHouseInfoVO.setDisable(false);
			}*/
	    	
	    	if(vtrsWithSameHH.contains(voter.getVoterId())){
	    		voterHouseInfoVO.setMakeItEnable(true);
	    	}else{
	    		voterHouseInfoVO.setMakeItEnable(false);
	    	}
	    	
	    	Map<Long,List<GenericVO>> categoriesMap=getCategoriesForHHSurvey();
	    	List<GenericVO> occupationList=new ArrayList<GenericVO>();
	    	List<GenericVO> educationList=new ArrayList<GenericVO>();
	    	List<GenericVO> socialPositionList=new ArrayList<GenericVO>();
	    	
	    	GenericVO gvo = new GenericVO();
			gvo.setId(0l);
			gvo.setName("Select");
			occupationList.add(gvo);
			educationList.add(gvo);
			socialPositionList.add(gvo);
	    	

			for (Entry<Long, List<GenericVO>> entry : categoriesMap.entrySet())
			{
				//System.out.println(entry.getKey() + "/" + entry.getValue());
				if(entry.getKey()==IConstants.HOUSE_HOLD_VOTER_OCCUPATION){
					occupationList.addAll(entry.getValue());
				}else if(entry.getKey()==IConstants.HOUSE_HOLD_VOTER_EDUCATION){
					educationList.addAll(entry.getValue());
				}else if(entry.getKey()==IConstants.HOUSE_HOLD_VOTER_SOCIAL_POSITIONS){
					socialPositionList.addAll(entry.getValue());
				}
			}
			
			voterHouseInfoVO.setOccupationList(occupationList);
			voterHouseInfoVO.setEducationList(educationList);
			voterHouseInfoVO.setSocialPositionList(socialPositionList);
			
			voterHouseInfoVO.setFamilyRelsList(relsList);
			
			if(hhVoterRelMap.get(voter.getVoterId())!=null){
				voterHouseInfoVO.setVoterFamilyRelId(hhVoterRelMap.get(voter.getVoterId()));
			}else{
				voterHouseInfoVO.setVoterFamilyRelId(0l);
			}
			
			
	    	try {
	    		if(categoriesForSavedVotersMap.get(voter.getVoterId())==null){
	    			setVotersCategories(votersCategoriesList,voter,voterHouseInfoVO);
	    		}
	    		else{
	    			//setVotersCategories(categoriesSvdList,voter,voterHouseInfoVO);
	    			voterHouseInfoVO.setCategoriesList(categoriesForSavedVotersMap.get(voter.getVoterId()));
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//setCastePartyDetails(voterHouseInfoVO,voterCastPartyVO);
	    	
	    	voterHouseInfoVOList.add(voterHouseInfoVO);
	    	//getUserCasteAndSelectedParty(voterHouseInfoVO , voter.getVoterId(),userId);
	    	
	    	sno = sno+1;
		//}
		}
		}
		return voterHouseInfoVOList;
	}
	
	/**
	 * This method will give voter selected categories values Map for House Hold Survey Form
	 *  
	 */
	public Map<Long,List<GenericVO>> getCategoriesForHHSurvey(){
		List<Long> ctgrysReqForHHSurveyList=new ArrayList<Long>();
    	ctgrysReqForHHSurveyList.add(IConstants.HOUSE_HOLD_VOTER_OCCUPATION);
    	ctgrysReqForHHSurveyList.add(IConstants.HOUSE_HOLD_VOTER_EDUCATION);
    	ctgrysReqForHHSurveyList.add(IConstants.HOUSE_HOLD_VOTER_SOCIAL_POSITIONS);
    	
    	List<Object[]> categoriesList=userVoterCategoryValueDAO.getCatergoryAndValues(ctgrysReqForHHSurveyList,1l);
    	Map<Long,List<GenericVO>> categoryMap=new HashMap<Long, List<GenericVO>>();
    	for(Object[] list:categoriesList){
    		if(categoryMap.get(Long.valueOf(list[0].toString()))!=null){
    			List<GenericVO> genList=categoryMap.get(Long.valueOf(list[0].toString()));
    			GenericVO gvo = new GenericVO();
    			gvo.setId(Long.valueOf(list[2].toString()));
    			gvo.setName(list[3].toString());
    			
    			genList.add(gvo);
    			
    			categoryMap.put(Long.valueOf(list[0].toString()), genList);
    			
    		}else{
    			GenericVO gvo = new GenericVO();
    			gvo.setId(Long.valueOf(list[2].toString()));
    			gvo.setName(list[3].toString());
    			List<GenericVO> genList=new ArrayList<GenericVO>();
    			genList.add(gvo);
    			
    			categoryMap.put(Long.valueOf(list[0].toString()), genList);
    		}
    	}
    	
    	return categoryMap;
	}
	
	 
	
	/**
	 * This method will set the voter selected categories values
	 * @author Samba Penugonda
	 * @param votersCategoriesList ,voters selected categories list
	 * @param voter , voter details
	 * @param voterHouseInfoVO , category values need to set to this vo
	 */
	public void setVotersCategories(List<Object[]> votersCategoriesList,
			Voter voter, VoterHouseInfoVO voterHouseInfoVO) throws Exception{
		
		try{
		
		 VoterHouseInfoVO category = null;
			
		 for(Object[] voterDetails:votersCategoriesList){
			 
			 Long voterId = (Long)voterDetails[0];
			 
			 if(voterId.longValue() == voter.getVoterId()){
				 
				 List<VoterHouseInfoVO> categoriesList1 = voterHouseInfoVO.getCategoriesList();
				 
				 if(categoriesList1 == null){
					 categoriesList1 = new ArrayList<VoterHouseInfoVO>();
					 voterHouseInfoVO.setCategoriesList(categoriesList1);
					 
				 }
				  category = new VoterHouseInfoVO();
	    		  categoriesList1.add(category);
	    		  category.setCategoryValuesId((Long)voterDetails[1]);
	    		  category.setName(voterDetails[2]!=null?voterDetails[2].toString():"");
				  category.setCategoryValueId(voterDetails[3]!=null?Long.valueOf(voterDetails[3].toString()):0l);
			 }
	     }
		 
		}catch(Exception e){
			throw new Exception();			
		}
	}
	
	/**
	 * This method will set the voter's party and caste details
	 * @author Samba Penugonda
	 * @param voterHouseInfoVO , voters party and caste details need to set to this vo
	 * @param voterCastPartyVO , contains voter's party and caste details
	 */
	public void setCastePartyDetails(VoterHouseInfoVO voterHouseInfoVO,
			VoterHouseInfoVO voterCastPartyVO) throws Exception {
		try{
			if(voterCastPartyVO != null){
	    		
	    		if(voterCastPartyVO.getCast() !=null)
	    			voterHouseInfoVO.setCast(voterCastPartyVO.getCast());
	    		
	    		if(voterCastPartyVO.getParty() != null)
	    			voterHouseInfoVO.setParty(voterCastPartyVO.getParty());
	    		
	    	}
		}catch(Exception e){
			throw new Exception();
		}
		
	}
	
	/**
	 * This method will get the caste and party details of voters of logged in user
	 * @author Samba Penugonda
	 * @param voterIds , total voterIds to get the caste and party details
	 * @param userId , logged in user id
	 * @return caste and party details of voters as map having key as voter id
	 */
	 public Map<Long,VoterHouseInfoVO> getUserCasteAndSelectedPartyVoters(List<Long> voterIds,Long userId){

		  Map<Long,VoterHouseInfoVO> voterCasteDetailsMap = new HashMap<Long, VoterHouseInfoVO>();

		  List<UserVoterDetails> userVoterDetailsList = userVoterDetailsDAO.getUserVoterDtlsVoterIds(voterIds,userId);
		  
		  
		  for(UserVoterDetails userVoterDetails:userVoterDetailsList){
			  VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
			  
			  if(userVoterDetails.getParty() != null)
				  voterHouseInfoVO.setParty(userVoterDetails.getParty().getShortName());
			  
			  if(userVoterDetails.getCasteState() != null)
				  voterHouseInfoVO.setCast(userVoterDetails.getCasteState().getCaste().getCasteName());
			  
			  
			  voterHouseInfoVO.setVoterId(userVoterDetails.getVoter().getVoterId());
			  
			  voterCasteDetailsMap.put(voterHouseInfoVO.getVoterId(), voterHouseInfoVO); 
		  }
		 
		  return voterCasteDetailsMap;
	  }
	
	public List<VoterHouseInfoVO> getVoterDetailsByCaste(Long id,Long publicationDateId,Long casteStateId,String type,String buildType,Long userId,Long hamletId,Long constituencyId)
	{
		String queryStr = null;
		List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
		List<Voter> list = null;
		List<Object[]> listVal = null;
		List<Object[]> voterIdAndMobileNosList = null;
		Map<Long,String> hashMap = null;
		String mobileNoExist = null;
		
		//List<Voter> list = boothPublicationVoterDAO.getVoterDetailsByCaste(id, publicationDateId, caste);
		try{
		if(type.equalsIgnoreCase("boothHamlet"))
		{
			listVal = userVoterDetailsDAO.getVoterIdsForuserinHamletByBoothsandByCasteId(userId,hamletId,casteStateId,id.longValue(),publicationDateId.longValue());
			mobileNoExist = "true";
		}else
		if(type.equalsIgnoreCase("booth") && !buildType.equalsIgnoreCase("customWardBooths"))
		{
			listVal = boothPublicationVoterDAO.getVoterDetailsByCasteStateForBooth(id,publicationDateId,casteStateId,userId,constituencyId);
			mobileNoExist = "true";
		}
		else if(type.equalsIgnoreCase("booth") && buildType.equalsIgnoreCase("customWardBooths"))
		{
			listVal = boothPublicationVoterDAO.getVoterDetailsForCustomWardBooths(hamletId, id, userId, publicationDateId, casteStateId);
			mobileNoExist = "true";
		}
		
		else if(type.equalsIgnoreCase("hamletLocality") || type.equalsIgnoreCase("wardLocality"))
		{
			if(type.equalsIgnoreCase("hamletLocality"))
				queryStr = " model.hamlet.hamletId = :id ";
			else if(type.equalsIgnoreCase("wardLocality"))
				queryStr = " model.ward.constituencyId = :id ";
			
			/*List<Long> voterIds = userVoterDetailsDAO.getVoterIdsByLocalityForUser(id,hamletId,userId,casteStateId,queryStr);
			
			listVal = boothPublicationVoterDAO.getVoterDetailsByCasteStateForPanchayatByHamlet(voterIds,publicationDateId);*/
			
			voterIdAndMobileNosList = userVoterDetailsDAO.getVoterIdAndMobileNoByLocalityForUser(id,hamletId,userId,casteStateId,queryStr);
			
			if(voterIdAndMobileNosList != null && voterIdAndMobileNosList.size() > 0)
			{
			  hashMap = new HashMap<Long, String>(0);
			  for(Object[] params:voterIdAndMobileNosList)
				hashMap.put((Long)params[0], params[1] != null?params[1].toString():"N/A");
			  /*for(Object[] params:voterIdAndMobileNosList)
			  {
				  String mobileNo = "N/A";
				  if(params[1] != null)
					  mobileNo =  params[1].toString(); 
				hashMap.put((Long)params[0], mobileNo);
			  }*/
			
			   List<Long> voterIdsList = new ArrayList<Long>(hashMap.keySet());
			
			   listVal = boothPublicationVoterDAO.getVoterDetailsByCasteStateForPanchayatByHamlet(voterIdsList,publicationDateId);
			}
			
			mobileNoExist = null;
		
		}else if(type.equalsIgnoreCase("hamlet") && !buildType.equalsIgnoreCase("panchayatHamlet"))
		{
			/*List<Long> voterIds = userVoterDetailsDAO.getVoterIdsForuserByHamletIdsByCaste(userId , id,casteStateId);

			listVal = boothPublicationVoterDAO.getVoterDetailsByCasteStateForPanchayatByHamlet(voterIds,publicationDateId);*/
			voterIdAndMobileNosList = userVoterDetailsDAO.getVoterIdAndMobileNoForuserByHamletIdsByCaste(userId , id,casteStateId);
			if(voterIdAndMobileNosList != null && voterIdAndMobileNosList.size() > 0)
			{
			  hashMap = new HashMap<Long, String>(0);
			  for(Object[] params:voterIdAndMobileNosList)
				hashMap.put((Long)params[0], params[1] != null?params[1].toString():"N/A");
			
			   List<Long> voterIdsList = new ArrayList<Long>(hashMap.keySet());
			
			   listVal = boothPublicationVoterDAO.getVoterDetailsByCasteStateForPanchayatByHamlet(voterIdsList,publicationDateId);
			}
			mobileNoExist = null;
			
		}
		
		else if(type.equalsIgnoreCase("ward") && buildType.equalsIgnoreCase("muncipalityCustomWard"))
		{
			listVal = boothPublicationVoterDAO.getVoterDetailsForCustomWard(id, publicationDateId, userId, casteStateId);
			mobileNoExist = "true";
		}
		else if(type.equalsIgnoreCase("wardbooth") && buildType.equalsIgnoreCase("muncipalityCustomWard"))
		{
			listVal = userVoterDetailsDAO.getVoterDetailsForCustomWardByBooth(id,publicationDateId, userId, casteStateId);
			mobileNoExist = "true";
		}
		else if(type.equalsIgnoreCase("hamlet") && buildType.equalsIgnoreCase("panchayatHamlet"))
		{
			listVal = boothPublicationVoterDAO.getVoterDetailsByCasteStateForHamlet(id,publicationDateId,casteStateId,userId,constituencyId);
			mobileNoExist = "true";
		}
		else
		{
			mobileNoExist = "true";
			boolean isPartial = false;
			Long count = partialBoothPanchayatDAO.getPartialBoothPanchayatDetails(id, publicationDateId);
			 if(count > 0){
				 isPartial = true;
			 }
			if(!isPartial){
				
				listVal = boothPublicationVoterDAO.getVoterDetailsByCasteStateForPanchayat(id,publicationDateId,casteStateId,userId);
			}else{	
				listVal = boothPublicationVoterDAO.getVoterDetailsByCasteStateForPartialPanchayat(userId,id,publicationDateId,casteStateId);			
			  
			}
			
				
		}
		VoterHouseInfoVO voterHouseInfoVO = null;
		long sno = 1;
		if(listVal != null && listVal.size() > 0)
		{
			
			for(Object[] voter1 : listVal)
			{
				if(voter1[0] != null)
				{
				Voter voter = (Voter)voter1[0];
				voterHouseInfoVO = new VoterHouseInfoVO();
				voterHouseInfoVO.setBoothId((Long)voter1[1]);
				voterHouseInfoVO.setVoterId(voter.getVoterId());
				voterHouseInfoVO.setsNo(sno);
				//voterHouseInfoVO.setName(voter.getFirstName()+" "+voter.getLastName());
				voterHouseInfoVO.setName(voter.getName());
				voterHouseInfoVO.setGender(voter.getGender());
				voterHouseInfoVO.setAge(voter.getAge());
				voterHouseInfoVO.setHouseNo(voter.getHouseNo());
				//voterHouseInfoVO.setGaurdian(voter.getRelativeFirstName()+" "+voter.getRelativeLastName());
				voterHouseInfoVO.setGaurdian(voter.getRelativeName());
				voterHouseInfoVO.setRelationship(voter.getRelationshipType());
				voterHouseInfoVO.setVoterIdCardNo(voter.getVoterIDCardNo());
				
				if(mobileNoExist != null && mobileNoExist.equalsIgnoreCase("true"))
				 voterHouseInfoVO.setMobileNo(voter1[2]!=null ? voter1[2].toString() : "N/A");
				else
				 voterHouseInfoVO.setMobileNo(hashMap.get(voter.getVoterId()));
				
				votersList.add(voterHouseInfoVO);
				sno = sno + 1;
			}
			}
		}
			return votersList;
		}catch(Exception e){
			e.printStackTrace();
			log.error(" Exception Occured in getVoterDetailsByCaste() Method, Exception - ",e);
			return votersList;
		}
	}
	/**
	 * This method will get overview voters details for a constituency or for a mandal
	 */
	@SuppressWarnings("unchecked")
	public List<VotersDetailsVO> getVotersDetailsByAgewise(Long constituencyId, Long tehsilId,Long panchayatId,Long boothId,
			 Long publicationDateId , String type,Long userId) {		

		List<VotersDetailsVO> constituencyVotersList = new ArrayList<VotersDetailsVO>();
		
		try{ 
			if(type.equalsIgnoreCase("localElectionBody")){
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(tehsilId);
				tehsilId = (Long) list.get(0);
			}
			List<Long> boothsList = null;
			if(type.equalsIgnoreCase("ward")){
				 boothsList = boothDAO.getBoothIdsForWard(panchayatId,publicationDateId);
			}
			if(type.equalsIgnoreCase(IConstants.HAMLET) || type.equalsIgnoreCase(IConstants.CUSTOMWARD) )
			{ 
			
				/*boothsList =(List<Long>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(panchayatId, boothId ,type);
			
				
				if(boothsList == null || boothsList.size()==0)
					   return new ArrayList<VotersDetailsVO>();*/
				
				List<Long> filter = (List<Long>) userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(publicationDateId,panchayatId,boothId,type);
				if(filter == null || filter.size()==0)
					   return new ArrayList<VotersDetailsVO>();
				
				//List<Object[]> list = userVoterDetailsDAO.getAgeWiseInfoForUser(filter);
				
				List<Object[]> list = new ArrayList<Object[]>(0);
				int fromIndex = 0;
				int toIndex = 2000;
				if(filter.size() >= 2000)
				{
					while(fromIndex <= toIndex)
					{
						List<Object[]> listTemp = userVoterDetailsDAO.getAgeWiseInfoForUser(filter.subList(fromIndex, toIndex),boothId,IConstants.MALE,IConstants.FEMALE,IConstants.AGE18,IConstants.AGE25,IConstants.AGE23,IConstants.AGE30,IConstants.AGE31,IConstants.AGE45,IConstants.AGE46,IConstants.AGE60);
						list.addAll(listTemp);
						fromIndex += 2000;
						toIndex += 2000;
						if(toIndex >= filter.size())
							toIndex = filter.size();
					}
				}
				else
					list = userVoterDetailsDAO.getAgeWiseInfoForUser(filter,boothId,IConstants.MALE,IConstants.FEMALE,IConstants.AGE18,IConstants.AGE25,IConstants.AGE23,IConstants.AGE30,IConstants.AGE31,IConstants.AGE45,IConstants.AGE46,IConstants.AGE60);
				
				if(list == null || list.size()==0)	
				{   
					return constituencyVotersList;	
				}else{
					
					getCustomWardWiseYoungVoterDetails(constituencyVotersList,publicationDateId,panchayatId,boothId,type,(long)filter.size());
					myBusinessDelegator1(list, constituencyVotersList);
					return constituencyVotersList;
				}
			}
			/*if(type.equalsIgnoreCase(IConstants.BOOTH))
			{
				boothsList =(List<Long>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(panchayatId, boothId);
				if(boothsList == null || boothsList.size()==0)
					   return new ArrayList<VotersDetailsVO>();
				
				List<?> filter =        userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(publicationDateId,boothsList);
				if(filter == null || filter.size()==0)
					   return new ArrayList<VotersDetailsVO>();
				
				List<Object[]> list = userVoterDetailsDAO.getAgeWiseInfoForUser(filter);
				
				if(list == null || list.size()==0)	
				{   
					return constituencyVotersList;	
				}else{
					
					myBusinessDelegator(list, constituencyVotersList);
					return constituencyVotersList;
				}
			}*/
			if(type.equalsIgnoreCase("boothHamlets"))
			{
				type="booth";
				boothId=panchayatId;
			}
			getDetailsOfYoungVoters(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList,userId);
	        getDetailsOfVotersHasAgeBetween18And25(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList,userId);		
			getDetailsOfVotersHasAgeBetween26And35(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList,userId);			
			getDetailsOfVotersHasAgeBetween36And45(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList,userId);		
			getDetailsOfVotersHasAgeBetween46And60(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList,userId);       
			getDetailsOfVotersHasAgeAbove60(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList,userId);
     
			Long totalVoters = 0L;
			for(VotersDetailsVO vtersDetailsVO:constituencyVotersList){			
				if(!vtersDetailsVO.getAgeRange().equalsIgnoreCase("Young Voters"))
				totalVoters +=vtersDetailsVO.getTotalVoters();
			}
			
			for(VotersDetailsVO votersDetailsVO:constituencyVotersList){
				if(totalVoters != 0){					
				votersDetailsVO.setTotalVotersPercent((float)votersDetailsVO.getTotalVoters()*100f/totalVoters);
				}
			}
			
		}catch(Exception e){			
			e.printStackTrace();
		}
		return constituencyVotersList;
	}
	public List<VotersDetailsVO> getVotersDetailsByAgewiseNew(Long constituencyId, Long tehsilId,Long panchayatId,Long boothId,
			 Long publicationDateId , String type,Long userId) {		

		List<VotersDetailsVO> constituencyVotersList = new ArrayList<VotersDetailsVO>();
		VotersDetailsVO vo = new VotersDetailsVO();
		try{ 
			if(type.equalsIgnoreCase("localElectionBody")){
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(tehsilId);
				tehsilId = (Long) list.get(0);
				List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(tehsilId.toString());
				  Object[] reqName = assemblyLocalElectionBodyName.get(0);
				  String name = reqName[0].toString()+" "+reqName[1].toString();
				  vo.setBoothName(name);
			}
			List<Long> boothsList = null;
			if(type.equalsIgnoreCase("ward")){
				 boothsList = boothDAO.getBoothIdsForWard(panchayatId,publicationDateId);
				 vo.setBoothName(constituencyDAO.get(panchayatId).getName());
			}
			
			getDetailsOfYounVotersHasAgeBetween18And22(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,vo,type,boothsList);
	        getDetailsOfVotersHasAgeBetween18And25(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,vo,type,boothsList);		
			getDetailsOfVotersHasAgeBetween26And35(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,vo,type,boothsList);			
			getDetailsOfVotersHasAgeBetween36And45(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,vo,type,boothsList);		
			getDetailsOfVotersHasAgeBetween46And60(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,vo,type,boothsList);       
			getDetailsOfVotersHasAgeAbove60(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,vo,type,boothsList);
			
			Long totalVoters = 0l;
			if(vo.getTotalVotersFor18To25() != null )
			 totalVoters = totalVoters+vo.getTotalVotersFor18To25();
			if(vo.getTotalVotersFor26To35() != null)
				 totalVoters = totalVoters+vo.getTotalVotersFor26To35();
			if(vo.getTotalVotersFor36To45() != null)
				totalVoters = totalVoters+vo.getTotalVotersFor36To45();
			if(vo.getTotalVotersFor46To60() != null)
				totalVoters = totalVoters+vo.getTotalVotersFor46To60();
			if(vo.getTotalVotersForAbove60() != null)
				totalVoters = totalVoters+vo.getTotalVotersForAbove60();
			
			vo.setTotalVoters(totalVoters);
			
			vo.setVotersPercentFor18To25(vo.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)vo.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
			vo.setVotersPercentFor26To35(vo.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)vo.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
			vo.setVotersPercentFor36To45(vo.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)vo.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
			vo.setVotersPercentFor46To60(vo.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)vo.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
			vo.setVotersPercentForAbove60(vo.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)vo.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
			vo.setVotersPercentForYoungerVoters(vo.getTotalVotersForYoungerVoters() != null? roundTo2DigitsFloatValue((float)vo.getTotalVotersForYoungerVoters()*100f/totalVoters):"0.00");
			
			constituencyVotersList.add(vo);
			
			
		}catch(Exception e){			
			e.printStackTrace();
		}
		return constituencyVotersList;
	}
	
	/**
	 * This method will calculate the voters count who have age between 18 to 25
	 * @param constituencyId
	 * @param tehsilId
	 * @param publicationDateId
	 * @param constituencyVotersList
	 * @param type 
	 * @return constituencyVotersList
	 */
	
	public void getDetailsOfVotersHasAgeBetween18And25(
			Long constituencyId,Long tehsilId,Long panchayatId, Long boothId, Long publicationDateId,
			List<VotersDetailsVO> constituencyVotersList,String type,List<Long> boothsList,Long userId) {		
		
		VotersDetailsVO voterDetailsForAgeBetween18To25 = new VotersDetailsVO();
		List<Object[]> votersListOf18To25 = null;
		
		try{
		
			if(type.equalsIgnoreCase("constituency"))
			    votersListOf18To25 = boothPublicationVoterDAO
					.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
							constituencyId, publicationDateId,IConstants.AGE18, IConstants.AGE25);
			 else if(type.equalsIgnoreCase("mandal"))
				 votersListOf18To25 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
						 tehsilId, publicationDateId,IConstants.AGE18, IConstants.AGE25,constituencyId);
			 else if(type.equalsIgnoreCase("panchayat"))
			 {
			List<PartialBoothPanchayat> partialPanchayatDetails = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(panchayatId, publicationDateId);
			
			if(partialPanchayatDetails != null && partialPanchayatDetails.size() >0)
			{
				
				votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationIdInHamlets(panchayatId, publicationDateId,IConstants.AGE18,IConstants.AGE25 , userId);
				
			}				
			else
			     votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,IConstants.AGE18,IConstants.AGE25);
			 }
			 else if(type.equalsIgnoreCase("booth"))
			    votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,IConstants.AGE18,IConstants.AGE25);
			 else if(type.equalsIgnoreCase("localElectionBody"))
				 votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
						 tehsilId, publicationDateId,IConstants.AGE18,IConstants.AGE25,constituencyId);
			 else if(type.equalsIgnoreCase("ward"))
				 votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,IConstants.AGE18,IConstants.AGE25);
			 else if(type.equalsIgnoreCase(IConstants.HAMLET) && boothsList !=null && boothsList.size()>0 )
			 {
				/* votersListOf18To25 = boothPublicationVoterDAO.getVotersCountInSpecifiedRangeForHamletByPublicationId(
						 panchayatId, publicationDateId,IConstants.AGE18,IConstants.AGE22,boothId);*/
				// List<Object> ids =	boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(5l, 1l);
				 votersListOf18To25=boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId, IConstants.AGE18,IConstants.AGE25, boothsList);
				 }
			 else return;	 
			
			Long maleVotersBetween18To25 = 0L;
			Long femaleVotersBetween18To25 = 0L;
			Long unKnownVotersBetween18To25 = 0L;
			
			for(Object[] obj:votersListOf18To25){			
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersBetween18To25 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersBetween18To25 = (Long)obj[0];
				else 
					unKnownVotersBetween18To25 = (Long)obj[0];
				
			}
		
			 Long totalVotersBetween18To25 = maleVotersBetween18To25
					+ femaleVotersBetween18To25 + unKnownVotersBetween18To25;
			
			 voterDetailsForAgeBetween18To25.setTotalMaleVoters(maleVotersBetween18To25);
			 voterDetailsForAgeBetween18To25.setTotalFemaleVoters(femaleVotersBetween18To25);
			 voterDetailsForAgeBetween18To25.setTotalUnknownVoters(unKnownVotersBetween18To25);
			 voterDetailsForAgeBetween18To25.setTotalVoters(totalVotersBetween18To25);
			 voterDetailsForAgeBetween18To25.setAgeRange(IConstants.AGE18to25);
			 
			  if(totalVotersBetween18To25 != 0){
				  
						
				 voterDetailsForAgeBetween18To25.setTotalMaleVotersPercent((float) (maleVotersBetween18To25 *100f/ totalVotersBetween18To25 ));
				 voterDetailsForAgeBetween18To25.setTotalFemaleVotersPercent((float)(femaleVotersBetween18To25 *100f / totalVotersBetween18To25 ));
				 voterDetailsForAgeBetween18To25.setTotalUnknownVotersPercent((float) (unKnownVotersBetween18To25 *100f / totalVotersBetween18To25 ));
			  }
				 
			
			 
			 constituencyVotersList.add(voterDetailsForAgeBetween18To25);
		 
		}catch(Exception e){
			e.printStackTrace();
			
		}
		 
		
	}
	
	/**
	 * This method will calculate the voters count who have age between 26 to 35
	 * @param constituencyId
	 * @param tehsilId
	 * @param publicationDateId
	 * @param constituencyVotersList
	 * @param type 
	 * @return constituencyVotersList
	 */
	
	
	public void getDetailsOfVotersHasAgeBetween26And35(Long constituencyId,Long tehsilId,
			Long panchayatId,Long boothId, Long publicationDateId, List<VotersDetailsVO> constituencyVotersList ,String type,List<Long> boothsList,Long userId) {		
		
       VotersDetailsVO voterDetailsForAgeBetween26To35 = new VotersDetailsVO();
       List<Object[]> votersListOf26To35 = null;
       
       try
       {
      
			if(type.equalsIgnoreCase("constituency"))
			 votersListOf26To35 = boothPublicationVoterDAO
					.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
							constituencyId, publicationDateId,IConstants.AGE23, IConstants.AGE30);
			else if(type.equalsIgnoreCase("mandal"))
				votersListOf26To35 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
						tehsilId, publicationDateId,IConstants.AGE23, IConstants.AGE30,constituencyId);
			 else if(type.equalsIgnoreCase("panchayat"))
			 {
				 List<PartialBoothPanchayat> partialPanchayatDetails = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(panchayatId, publicationDateId);
					
					if(partialPanchayatDetails != null && partialPanchayatDetails.size() >0)
						votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationIdInHamlets(panchayatId, publicationDateId,IConstants.AGE23,IConstants.AGE30 , userId);
					else				 
				        votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,IConstants.AGE23, IConstants.AGE30);
			 }
			 else if(type.equalsIgnoreCase("booth"))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,IConstants.AGE23, IConstants.AGE30);
			 else if(type.equalsIgnoreCase("localElectionBody"))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
						 tehsilId, publicationDateId,IConstants.AGE23, IConstants.AGE30,constituencyId);
			 else if(type.equalsIgnoreCase("ward"))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,IConstants.AGE23, IConstants.AGE30);
			/* else if(type.equalsIgnoreCase(IConstants.HAMLET))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountInSpecifiedRangeForHamletByPublicationId(
						 panchayatId, publicationDateId,IConstants.AGE26,IConstants.AGE35,boothId);*/
			 else if(type.equalsIgnoreCase(IConstants.HAMLET) && boothsList !=null && boothsList.size()>0 )
			 {				
				 votersListOf26To35=boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId,IConstants.AGE23,IConstants.AGE30, boothsList);
				 }
			 else return;	
			
			Long maleVotersBetween26To35 = 0L;
			Long femaleVotersBetween26To35 = 0L;
			Long unKnownVotersBetween26To35 = 0L;
			
			for(Object[] obj:votersListOf26To35){
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersBetween26To35 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersBetween26To35 = (Long)obj[0];
				else 
					unKnownVotersBetween26To35 = (Long)obj[0];
			}
			
			
			Long totalVotersBetween26To35 = maleVotersBetween26To35
						+ femaleVotersBetween26To35 + unKnownVotersBetween26To35;
			
			
			voterDetailsForAgeBetween26To35.setTotalMaleVoters(maleVotersBetween26To35);
			voterDetailsForAgeBetween26To35.setTotalFemaleVoters(femaleVotersBetween26To35);
			voterDetailsForAgeBetween26To35.setTotalUnknownVoters(unKnownVotersBetween26To35);
			voterDetailsForAgeBetween26To35.setTotalVoters(totalVotersBetween26To35);
			voterDetailsForAgeBetween26To35.setAgeRange(IConstants.AGE23to30);
			
				if(totalVotersBetween26To35 != 0){
					
					
					voterDetailsForAgeBetween26To35.setTotalMaleVotersPercent((float) (maleVotersBetween26To35 *100f/ totalVotersBetween26To35 ));
					voterDetailsForAgeBetween26To35.setTotalFemaleVotersPercent((float)(femaleVotersBetween26To35 *100f/ totalVotersBetween26To35 ));
					voterDetailsForAgeBetween26To35.setTotalUnknownVotersPercent((float) (unKnownVotersBetween26To35 *100f/ totalVotersBetween26To35 ));
					}
			
			
			constituencyVotersList.add(voterDetailsForAgeBetween26To35);
		
       }catch(Exception e){    	   
    	   e.printStackTrace();    	   
       }		
	}
	
	
	/**
	 * This method will calculate the voters count who have age between 36 to 45
	 * @param constituencyId
	 * @param tehsilId
	 * @param publicationDateId
	 * @param constituencyVotersList
	 * @param type 
	 * @return constituencyVotersList
	 */
	
	
	public void getDetailsOfVotersHasAgeBetween36And45(
			Long constituencyId,Long tehsilId,Long panchayatId ,Long boothId, Long publicationDateId,
			List<VotersDetailsVO> constituencyVotersList , String type,List<Long> boothsList,Long userId){

		VotersDetailsVO voterDetailsForAgeBetween36To45 = new VotersDetailsVO();
		List<Object[]> votersListOf36To45 = null;
		
		try{
		
				if(type.equalsIgnoreCase("constituency"))
				 votersListOf36To45 = boothPublicationVoterDAO
						.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
								constituencyId, publicationDateId,IConstants.AGE31, IConstants.AGE45);
				else if(type.equalsIgnoreCase("mandal"))
					votersListOf36To45 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
							tehsilId, publicationDateId,IConstants.AGE31, IConstants.AGE45,constituencyId);
				 else if(type.equalsIgnoreCase("panchayat"))
				 {
					 List<PartialBoothPanchayat> partialPanchayatDetails = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(panchayatId, publicationDateId);
						
						if(partialPanchayatDetails != null && partialPanchayatDetails.size() >0)
							votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationIdInHamlets(panchayatId, publicationDateId,IConstants.AGE31,IConstants.AGE45 , userId);
						else							
					        votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
						panchayatId, publicationDateId,IConstants.AGE31, IConstants.AGE45);
				 }
				 else if(type.equalsIgnoreCase("booth"))
					 votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
							boothId, publicationDateId,IConstants.AGE31, IConstants.AGE45);
				else if(type.equalsIgnoreCase("localElectionBody"))
					votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
							tehsilId, publicationDateId,IConstants.AGE31, IConstants.AGE45,constituencyId);
				 else if(type.equalsIgnoreCase("ward"))
					 votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
							 boothsList, publicationDateId,IConstants.AGE31, IConstants.AGE45);
				/* else if(type.equalsIgnoreCase(IConstants.HAMLET))
					 votersListOf36To45 = boothPublicationVoterDAO.getVotersCountInSpecifiedRangeForHamletByPublicationId(
							 panchayatId, publicationDateId,IConstants.AGE36,IConstants.AGE45,boothId);*/
				else if(type.equalsIgnoreCase(IConstants.HAMLET) && boothsList !=null && boothsList.size()>0 )
				 {				
					votersListOf36To45 =boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId, IConstants.AGE31,IConstants.AGE45, boothsList);
					 }
				 else return;
				
				Long maleVotersBetween36To45 = 0L;
				Long femaleVotersBetween36To45 = 0L;
				Long unKnownVotersBetween36To45 = 0L;
				
				
		        for(Object[] obj:votersListOf36To45){
					
					if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
						maleVotersBetween36To45 = (Long)obj[0];
					else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
						femaleVotersBetween36To45 = (Long)obj[0];
					else 
						unKnownVotersBetween36To45 = (Long)obj[0];
				}
		        
					Long totalVotersBetween36To45 = maleVotersBetween36To45
							+ femaleVotersBetween36To45 + unKnownVotersBetween36To45;
			
			
			voterDetailsForAgeBetween36To45.setTotalMaleVoters(maleVotersBetween36To45);
			voterDetailsForAgeBetween36To45.setTotalFemaleVoters(femaleVotersBetween36To45);
			voterDetailsForAgeBetween36To45.setTotalUnknownVoters(unKnownVotersBetween36To45);
			voterDetailsForAgeBetween36To45.setTotalVoters(totalVotersBetween36To45);
			voterDetailsForAgeBetween36To45.setAgeRange(IConstants.AGE31to45);

			if(totalVotersBetween36To45 != 0){
				voterDetailsForAgeBetween36To45.setTotalMaleVotersPercent((float) (maleVotersBetween36To45 *100f/ totalVotersBetween36To45 ));
				voterDetailsForAgeBetween36To45.setTotalFemaleVotersPercent((float)(femaleVotersBetween36To45 *100f/ totalVotersBetween36To45 ));
				voterDetailsForAgeBetween36To45.setTotalUnknownVotersPercent((float) (unKnownVotersBetween36To45 *100f/ totalVotersBetween36To45 ));
			}
			constituencyVotersList.add(voterDetailsForAgeBetween36To45);
			
		
		}catch(Exception e){
			e.printStackTrace();			
		}		
	}
	
	
	/**
	 * This method will calculate the voters count who have age between 46 to 60
	 * @param constituencyId
	 * @param tehsilId
	 * @param publicationDateId
	 * @param constituencyVotersList
	 * @param type 
	 * @return constituencyVotersList
	 */
	
	public void  getDetailsOfVotersHasAgeBetween46And60(
			Long constituencyId,Long tehsilId,Long panchayatId,Long boothId, Long publicationDateId,
			List<VotersDetailsVO> constituencyVotersList ,String type,List<Long> boothsList,Long userId){
		
		    List<Object[]> votersListOf46To60 = null;
		    VotersDetailsVO voterDetailsForAgeBetween46To60 = new VotersDetailsVO();
		    
		try{
		
			if(type.equalsIgnoreCase("constituency"))
			   votersListOf46To60 = boothPublicationVoterDAO
						.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
								constituencyId, publicationDateId,IConstants.AGE46, IConstants.AGE60);
			else if(type.equalsIgnoreCase("mandal"))
				votersListOf46To60 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
						tehsilId, publicationDateId,IConstants.AGE46, IConstants.AGE60,constituencyId);
			else if(type.equalsIgnoreCase("panchayat"))
			{
				 List<PartialBoothPanchayat> partialPanchayatDetails = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(panchayatId, publicationDateId);
					
					if(partialPanchayatDetails != null && partialPanchayatDetails.size() >0)
						votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationIdInHamlets(panchayatId, publicationDateId,IConstants.AGE46,IConstants.AGE60 , userId);
					else	
				       votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,IConstants.AGE46, IConstants.AGE60);
			}
			 else if(type.equalsIgnoreCase("booth"))
				 votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,IConstants.AGE46, IConstants.AGE60);
			else if(type.equalsIgnoreCase("localElectionBody"))
				votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
						tehsilId, publicationDateId,IConstants.AGE46, IConstants.AGE60,constituencyId);
			 else if(type.equalsIgnoreCase("ward"))
				 votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,IConstants.AGE46, IConstants.AGE60);
			/* else if(type.equalsIgnoreCase(IConstants.HAMLET))
				 votersListOf46To60 = boothPublicationVoterDAO.getVotersCountInSpecifiedRangeForHamletByPublicationId(
						 panchayatId, publicationDateId,IConstants.AGE46,IConstants.AGE60,boothId);*/
				else if(type.equalsIgnoreCase(IConstants.HAMLET) && boothsList !=null && boothsList.size()>0 )
				 {				
					votersListOf46To60 =boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId, IConstants.AGE46,IConstants.AGE60, boothsList);
					 }
				 else return;
			
			
			
			Long maleVotersBetween46To60 = 0L;
			Long femaleVotersBetween46To60 = 0L;
			Long unKnownVotersBetween46To60 = 0L;
			
	       for(Object[] obj:votersListOf46To60){
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersBetween46To60 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersBetween46To60 = (Long)obj[0];
				else 
					unKnownVotersBetween46To60 = (Long)obj[0];
			}
	       
	       Long totalVotersBetween46To60 = maleVotersBetween46To60
					+ femaleVotersBetween46To60 + unKnownVotersBetween46To60;
	       
	   
	       voterDetailsForAgeBetween46To60.setTotalMaleVoters(maleVotersBetween46To60);
	       voterDetailsForAgeBetween46To60.setTotalFemaleVoters(femaleVotersBetween46To60);
	       voterDetailsForAgeBetween46To60.setTotalUnknownVoters(unKnownVotersBetween46To60);
	       voterDetailsForAgeBetween46To60.setTotalVoters(totalVotersBetween46To60);
	       voterDetailsForAgeBetween46To60.setAgeRange(IConstants.AGE46to60);
	       
	       if(totalVotersBetween46To60 != 0){
		       voterDetailsForAgeBetween46To60.setTotalMaleVotersPercent((float) (maleVotersBetween46To60 *100f/ totalVotersBetween46To60 ));
		       voterDetailsForAgeBetween46To60.setTotalFemaleVotersPercent((float)(femaleVotersBetween46To60 *100f/ totalVotersBetween46To60 ));
		       voterDetailsForAgeBetween46To60.setTotalUnknownVotersPercent((float) (unKnownVotersBetween46To60 *100f/ totalVotersBetween46To60 ));
	       }    
				       
	       constituencyVotersList.add(voterDetailsForAgeBetween46To60);
       
		}catch(Exception e){
			e.printStackTrace();			
		}
	}
	
	/**
	 * This method will calculate the voters count who have age above 60
	 * @param constituencyId
	 * @param tehsilId
	 * @param publicationDateId
	 * @param constituencyVotersList
	 * @param type 
	 * @return constituencyVotersList
	 */
	
		
	public void  getDetailsOfVotersHasAgeAbove60(
			Long constituencyId,Long tehsilId,Long panchayatId,Long boothId,Long  publicationDateId,
			List<VotersDetailsVO> constituencyVotersList ,String type,List<Long> boothsList,Long userId){
		

		VotersDetailsVO voterDetailsForAgeAbove60 = new VotersDetailsVO();
	    List<Object[]> votersListOfAbove60  = null;
	    
	    try{
	       
	        if(type.equalsIgnoreCase("constituency"))
		         votersListOfAbove60 = boothPublicationVoterDAO
						.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
								constituencyId, publicationDateId,IConstants.AGE61 , IConstants.AGE160);
	       else if(type.equalsIgnoreCase("mandal"))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
	    			   tehsilId, publicationDateId,IConstants.AGE61 , IConstants.AGE160,constituencyId);
	       else if(type.equalsIgnoreCase("panchayat"))
	       {
	    	   List<PartialBoothPanchayat> partialPanchayatDetails = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(panchayatId, publicationDateId);
				
				if(partialPanchayatDetails != null && partialPanchayatDetails.size() >0)
					votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationIdInHamlets(panchayatId, publicationDateId,IConstants.AGE46,IConstants.AGE60 , userId);
				else	
	    	      votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,IConstants.AGE61 , IConstants.AGE160);
	       }
			 else if(type.equalsIgnoreCase("booth"))
				 votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,IConstants.AGE61 , IConstants.AGE160);
	       else if(type.equalsIgnoreCase("localElectionBody"))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
	    			   tehsilId, publicationDateId,IConstants.AGE61 , IConstants.AGE160,constituencyId);
	       else if(type.equalsIgnoreCase("ward"))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,IConstants.AGE61 , IConstants.AGE160);
	       /*else if(type.equalsIgnoreCase(IConstants.HAMLET))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountInSpecifiedRangeForHamletByPublicationId(
	    			   panchayatId, publicationDateId,IConstants.AGE61,IConstants.AGE160,boothId);*/
	       else if(type.equalsIgnoreCase(IConstants.HAMLET) && boothsList !=null && boothsList.size()>0 )
			 {				
	    	   votersListOfAbove60 =boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId, IConstants.AGE61,IConstants.AGE160 , boothsList);
				 }
			 else return;

	        Long maleVotersAbove60 = 0L;
			Long femaleVotersAbove60 = 0L;
			Long unKnownVotersAbove60 = 0L;
			
			
	          for(Object[] obj:votersListOfAbove60){
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersAbove60 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersAbove60 = (Long)obj[0];
				else 
					unKnownVotersAbove60 = (Long)obj[0];
			}
	          
	          Long totalVotersAbove60 = maleVotersAbove60
	  				+ femaleVotersAbove60 + unKnownVotersAbove60;
	          
	          
	          voterDetailsForAgeAbove60.setTotalMaleVoters(maleVotersAbove60);
	          voterDetailsForAgeAbove60.setTotalFemaleVoters(femaleVotersAbove60);
	          voterDetailsForAgeAbove60.setTotalUnknownVoters(unKnownVotersAbove60);
	          voterDetailsForAgeAbove60.setTotalVoters(totalVotersAbove60);	 
	          voterDetailsForAgeAbove60.setAgeRange("60-Above");
	          if(totalVotersAbove60 != 0){
		          voterDetailsForAgeAbove60.setTotalMaleVotersPercent((float) (maleVotersAbove60 *100f/ totalVotersAbove60 ));
		          voterDetailsForAgeAbove60.setTotalFemaleVotersPercent((float)(femaleVotersAbove60 *100f/ totalVotersAbove60 ));
		          voterDetailsForAgeAbove60.setTotalUnknownVotersPercent((float) (unKnownVotersAbove60 *100f/ totalVotersAbove60 ));
		       }

	   		
	          constituencyVotersList.add(voterDetailsForAgeAbove60);
	          
	    }catch(Exception e){
	    	
	    	e.printStackTrace();	    	
	    }
	}
	
	
	//retrieve all the low level stage voters details
	
	public List<VotersDetailsVO> getAgewiseVotersDetailsForTehsilsByConstituencyId(Long constituencyId,Long publicationDateId,Long userId){
		
		List<Object[]> tehsilIds = tehsilDAO.findTehsilsByConstituencyIdAndPublicationDateId(constituencyId,publicationDateId);
		List<VotersDetailsVO> mandalVotersList = new ArrayList<VotersDetailsVO>();
		
		
			for(Object[] obj:tehsilIds){
				
				VotersDetailsVO voterDetailsVO = new VotersDetailsVO();
				
				getDetailsOfYounVotersHasAgeBetween18And22(constituencyId,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal",null);
				getDetailsOfVotersHasAgeBetween18And25(constituencyId,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal",null);
				getDetailsOfVotersHasAgeBetween26And35(constituencyId,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal",null);			
				getDetailsOfVotersHasAgeBetween36And45(constituencyId,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal",null);		
				getDetailsOfVotersHasAgeBetween46And60(constituencyId,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal",null);       
				getDetailsOfVotersHasAgeAbove60(constituencyId,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal",null);
				
				
			/*Long totalVoters = voterDetailsVO.getTotalVotersFor18To25()
					+ voterDetailsVO.getTotalVotersFor26To35()
					+ voterDetailsVO.getTotalVotersFor36To45()
					+ voterDetailsVO.getTotalVotersFor46To60()
					+ voterDetailsVO.getTotalVotersForAbove60();*/
						
				voterDetailsVO.setTehsilName(obj[1].toString());
				/*voterDetailsVO.setTotalVoters(totalVoters);*/
				Long totalVoters = 0l;
				if(voterDetailsVO.getTotalVotersFor18To25() != null )
				 totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor18To25();
				if(voterDetailsVO.getTotalVotersFor26To35() != null)
					 totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor26To35();
				if(voterDetailsVO.getTotalVotersFor36To45() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor36To45();
				if(voterDetailsVO.getTotalVotersFor46To60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor46To60();
				if(voterDetailsVO.getTotalVotersForAbove60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersForAbove60();
				
				voterDetailsVO.setTotalVoters(totalVoters);
				
				/*voterDetailsVO.setVotersPercentFor18To25((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters);
				voterDetailsVO.setVotersPercentFor26To35((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters);
				voterDetailsVO.setVotersPercentFor36To45((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters);
				voterDetailsVO.setVotersPercentFor46To60((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters);
				voterDetailsVO.setVotersPercentForAbove60((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters);
			*/	
				
				voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentForYoungerVoters(voterDetailsVO.getTotalVotersForYoungerVoters() != null ?roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForYoungerVoters()*100f/totalVoters):"0.00");
				mandalVotersList.add(voterDetailsVO);
			}
			Collections.sort(mandalVotersList,sortByName);
			return mandalVotersList;
	}
	
	public void getDetailsOfVotersHasAgeBetween18And25(
			Long constituencyId,Long tehsilId,Long panchayatId, Long boothId, Long publicationDateId,
			VotersDetailsVO votersDetailsVO,String type,List<Long> boothsList) {		
		
		List<Object[]> votersListOf18To25 = null;
		
		try{
		
			if(type.equalsIgnoreCase("constituency"))
			    votersListOf18To25 = boothPublicationVoterDAO
					.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
							constituencyId, publicationDateId,IConstants.AGE18, IConstants.AGE25);
			 else if(type.equalsIgnoreCase("mandal"))
				 votersListOf18To25 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
						 tehsilId, publicationDateId,IConstants.AGE18, IConstants.AGE25,constituencyId);
			 else if(type.equalsIgnoreCase("panchayat"))
			     votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,IConstants.AGE18, IConstants.AGE25);
			 else if(type.equalsIgnoreCase("booth"))
			    votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,IConstants.AGE18, IConstants.AGE25);
			 else if(type.equalsIgnoreCase("localElectionBody"))
				 votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
						 tehsilId, publicationDateId,IConstants.AGE18, IConstants.AGE25,constituencyId);
			 else if(type.equalsIgnoreCase("ward"))
				 votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,IConstants.AGE18, IConstants.AGE25);
			 /*else if(type.equalsIgnoreCase(IConstants.HAMLET))
				 votersListOf18To25 = boothPublicationVoterDAO.getVotersCountInSpecifiedRangeForHamletByPublicationId(
						 boothId, publicationDateId,IConstants.AGE18,IConstants.AGE22,votersDetailsVO.getUserId());*/
			 else if(type.equalsIgnoreCase(IConstants.HAMLET) && boothsList !=null && boothsList.size()>0 )
			 {	 votersListOf18To25=boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId, IConstants.AGE18,IConstants.AGE25, boothsList);
				 }
			 else return;	 
			Long maleVotersBetween18To25 = 0L;
			Long femaleVotersBetween18To25 = 0L;
			Long unKnownVotersBetween18To25 = 0L;
			
			for(Object[] obj:votersListOf18To25){			
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersBetween18To25 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersBetween18To25 = (Long)obj[0];
				else 
					unKnownVotersBetween18To25 = (Long)obj[0];
				
			}
		
			 Long totalVotersBetween18To25 = maleVotersBetween18To25
					+ femaleVotersBetween18To25 + unKnownVotersBetween18To25;
			
			 votersDetailsVO.setTotalMaleVotesFor18To25(maleVotersBetween18To25);
			 votersDetailsVO.setTotalFemaleVotersFor18To25(femaleVotersBetween18To25);
			 votersDetailsVO.setTotalUnknownVotersFor18To25(unKnownVotersBetween18To25);
			 votersDetailsVO.setTotalVotersFor18To25(totalVotersBetween18To25);
			 
			  if(totalVotersBetween18To25 != 0){
				votersDetailsVO.setMaleVotersPercentFor18To25(roundTo2DigitsFloatValue((float) (maleVotersBetween18To25 *100f/ totalVotersBetween18To25 )));
				votersDetailsVO.setFemaleVotersPercentFor18To25(roundTo2DigitsFloatValue((float)(femaleVotersBetween18To25 *100f/ totalVotersBetween18To25 )));
			  }else{
				votersDetailsVO.setMaleVotersPercentFor18To25("0.00");
				votersDetailsVO.setFemaleVotersPercentFor18To25("0.00");
			}
		 
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	public void getDetailsOfVotersHasAgeBetween26And35(Long constituencyId,Long tehsilId,
			Long panchayatId,Long boothId, Long publicationDateId, VotersDetailsVO votersDetailsVO ,String type,List<Long> boothsList) {		
		
       List<Object[]> votersListOf26To35 = null;       
       try
       {      
			if(type.equalsIgnoreCase("constituency"))
			 votersListOf26To35 = boothPublicationVoterDAO
					.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
							constituencyId, publicationDateId,IConstants.AGE23, IConstants.AGE30);
			else if(type.equalsIgnoreCase("mandal"))
				votersListOf26To35 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
						tehsilId, publicationDateId,IConstants.AGE23, IConstants.AGE30,constituencyId);
			 else if(type.equalsIgnoreCase("panchayat"))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,IConstants.AGE23, IConstants.AGE30);
			 else if(type.equalsIgnoreCase("booth"))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,IConstants.AGE23, IConstants.AGE30);
			 else if(type.equalsIgnoreCase("localElectionBody"))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
						 boothId, publicationDateId,IConstants.AGE23, IConstants.AGE30,constituencyId);
			 else if(type.equalsIgnoreCase("ward"))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,IConstants.AGE23, IConstants.AGE30);
		/*	 else if(type.equalsIgnoreCase(IConstants.HAMLET))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountInSpecifiedRangeForHamletByPublicationId(
						 boothId, publicationDateId,IConstants.AGE26,IConstants.AGE35,votersDetailsVO.getUserId());*/
			 else if(type.equalsIgnoreCase(IConstants.HAMLET) && boothsList !=null && boothsList.size()>0 )
			 {				
				 votersListOf26To35=boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId,IConstants.AGE23,IConstants.AGE30, boothsList);
				 }
			
			Long maleVotersBetween26To35 = 0L;
			Long femaleVotersBetween26To35 = 0L;
			Long unKnownVotersBetween26To35 = 0L;
			
			for(Object[] obj:votersListOf26To35){
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersBetween26To35 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersBetween26To35 = (Long)obj[0];
				else 
					unKnownVotersBetween26To35 = (Long)obj[0];
			}
			
			
			Long totalVotersBetween26To35 = maleVotersBetween26To35
						+ femaleVotersBetween26To35 + unKnownVotersBetween26To35;			
			
			votersDetailsVO.setTotalMaleVotersFor26To35(maleVotersBetween26To35);
			votersDetailsVO.setTotalFemaleVotersFor26To35(femaleVotersBetween26To35);
			votersDetailsVO.setTotalUnknownVotersFor26To35(unKnownVotersBetween26To35);
			votersDetailsVO.setTotalVotersFor26To35(totalVotersBetween26To35);
			
				if(totalVotersBetween26To35 != 0){
					votersDetailsVO.setMaleVotersPercentFor26To35(roundTo2DigitsFloatValue((float) (maleVotersBetween26To35 *100f/ totalVotersBetween26To35 )));
					votersDetailsVO.setFemaleVotersPercentFor26To35(roundTo2DigitsFloatValue((float)(femaleVotersBetween26To35 *100f/ totalVotersBetween26To35 )));
				}else{
					votersDetailsVO.setMaleVotersPercentFor26To35("0.00");
					votersDetailsVO.setFemaleVotersPercentFor26To35("0.00");
			    }
		
       }catch(Exception e){    	   
    	   e.printStackTrace();    	   
       }		
	}
	
	public void getDetailsOfVotersHasAgeBetween36And45(
			Long constituencyId,Long tehsilId,Long panchayatId ,Long boothId, Long publicationDateId,
			VotersDetailsVO votersDetailsVO , String type,List<Long> boothsList){

		List<Object[]> votersListOf36To45 = null;
		
		try{
		
				if(type.equalsIgnoreCase("constituency"))
				 votersListOf36To45 = boothPublicationVoterDAO
						.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
								constituencyId, publicationDateId,IConstants.AGE31, IConstants.AGE45);
				else if(type.equalsIgnoreCase("mandal"))
					votersListOf36To45 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
							tehsilId, publicationDateId,IConstants.AGE31, IConstants.AGE45,constituencyId);
				 else if(type.equalsIgnoreCase("panchayat"))
					 votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
						panchayatId, publicationDateId,IConstants.AGE31, IConstants.AGE45);
				 else if(type.equalsIgnoreCase("booth"))
					 votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
							boothId, publicationDateId,IConstants.AGE31, IConstants.AGE45);
				else if(type.equalsIgnoreCase("localElectionBody"))
					votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
							tehsilId, publicationDateId,IConstants.AGE31, IConstants.AGE45,constituencyId);
				else if(type.equalsIgnoreCase("ward"))
					votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
							 boothsList, publicationDateId,IConstants.AGE31, IConstants.AGE45);
			/*	 else if(type.equalsIgnoreCase(IConstants.HAMLET))
					 votersListOf36To45 = boothPublicationVoterDAO.getVotersCountInSpecifiedRangeForHamletByPublicationId(
							 boothId, publicationDateId,IConstants.AGE36,IConstants.AGE45,votersDetailsVO.getUserId());*/
				 else if(type.equalsIgnoreCase(IConstants.HAMLET) && boothsList !=null && boothsList.size()>0 )
				 {				
					votersListOf36To45 =boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId, IConstants.AGE31,IConstants.AGE45, boothsList);
					 }
				 else return;
					
				Long maleVotersBetween36To45 = 0L;
				Long femaleVotersBetween36To45 = 0L;
				Long unKnownVotersBetween36To45 = 0L;
				
				
		        for(Object[] obj:votersListOf36To45){
					
					if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
						maleVotersBetween36To45 = (Long)obj[0];
					else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
						femaleVotersBetween36To45 = (Long)obj[0];
					else 
						unKnownVotersBetween36To45 = (Long)obj[0];
				}
		        
					Long totalVotersBetween36To45 = maleVotersBetween36To45
							+ femaleVotersBetween36To45 + unKnownVotersBetween36To45;
			
			
					votersDetailsVO.setTotalMaleVotersFor36To45(maleVotersBetween36To45);
					votersDetailsVO.setTotalFemaleVotersFor36To45(femaleVotersBetween36To45);
					votersDetailsVO.setTotalUnknownVotersFor36To45(unKnownVotersBetween36To45);
					votersDetailsVO.setTotalVotersFor36To45(totalVotersBetween36To45);

			if(totalVotersBetween36To45 != 0){
				votersDetailsVO.setMaleVotersPercentFor36To45(roundTo2DigitsFloatValue((float) (maleVotersBetween36To45 *100f/ totalVotersBetween36To45 )));
				votersDetailsVO.setFemaleVotersPercentFor36To45(roundTo2DigitsFloatValue((float)(femaleVotersBetween36To45 *100f/ totalVotersBetween36To45)));
			}else{
				votersDetailsVO.setMaleVotersPercentFor36To45("0.00");
				votersDetailsVO.setFemaleVotersPercentFor36To45("0.00");
			}
			
		
		}catch(Exception e){
			e.printStackTrace();			
		}		
	}
	
	public void  getDetailsOfVotersHasAgeBetween46And60(
			Long constituencyId,Long tehsilId,Long panchayatId,Long boothId, Long publicationDateId,
			VotersDetailsVO votersDetailsVO ,String type,List<Long> boothsList){
		
		    List<Object[]> votersListOf46To60 = null;
		    
		try{
		
			if(type.equalsIgnoreCase("constituency"))
			   votersListOf46To60 = boothPublicationVoterDAO
						.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
								constituencyId, publicationDateId,IConstants.AGE46, IConstants.AGE60,constituencyId);
			else if(type.equalsIgnoreCase("mandal"))
				votersListOf46To60 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
						tehsilId, publicationDateId,IConstants.AGE46, IConstants.AGE60,constituencyId);
			else if(type.equalsIgnoreCase("panchayat"))
				votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,IConstants.AGE46, IConstants.AGE60);
			 else if(type.equalsIgnoreCase("booth"))
				 votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,IConstants.AGE46, IConstants.AGE60);
			else if(type.equalsIgnoreCase("localElectionBody"))
				votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
						tehsilId, publicationDateId,IConstants.AGE46, IConstants.AGE60,constituencyId);
			else if(type.equalsIgnoreCase("ward"))
			 votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
					 boothsList, publicationDateId,IConstants.AGE46, IConstants.AGE60);
			 /*else if(type.equalsIgnoreCase(IConstants.HAMLET))
				 votersListOf46To60 = boothPublicationVoterDAO.getVotersCountInSpecifiedRangeForHamletByPublicationId(
						 boothId, publicationDateId,IConstants.AGE46,IConstants.AGE60,votersDetailsVO.getUserId());*/
			else if(type.equalsIgnoreCase(IConstants.HAMLET) && boothsList !=null && boothsList.size()>0 )
			 {				
				votersListOf46To60 =boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId, IConstants.AGE46,IConstants.AGE60, boothsList);
				 }
			 else return;
			Long maleVotersBetween46To60 = 0L;
			Long femaleVotersBetween46To60 = 0L;
			Long unKnownVotersBetween46To60 = 0L;
			
	       for(Object[] obj:votersListOf46To60){
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersBetween46To60 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersBetween46To60 = (Long)obj[0];
				else 
					unKnownVotersBetween46To60 = (Long)obj[0];
			}
	       
	       Long totalVotersBetween46To60 = maleVotersBetween46To60
					+ femaleVotersBetween46To60 + unKnownVotersBetween46To60;	       
	   
	       votersDetailsVO.setTotalMaleVotersFor46To60(maleVotersBetween46To60);
	       votersDetailsVO.setTotalFemaleVotersFor46To60(femaleVotersBetween46To60);
	       votersDetailsVO.setTotalUnknownVotersFor46To60(unKnownVotersBetween46To60);
	       votersDetailsVO.setTotalVotersFor46To60(totalVotersBetween46To60);
       
	       if(totalVotersBetween46To60 != 0){
				votersDetailsVO.setMaleVotersPercentFor46To60(roundTo2DigitsFloatValue((float) (maleVotersBetween46To60 *100f/ totalVotersBetween46To60 )));
				votersDetailsVO.setFemaleVotersPercentFor46To60(roundTo2DigitsFloatValue((float)(femaleVotersBetween46To60 *100f/ totalVotersBetween46To60 )));
			}else{
				votersDetailsVO.setMaleVotersPercentFor46To60("0.00");
				votersDetailsVO.setFemaleVotersPercentFor46To60("0.00");
			}    
				       
       
		}catch(Exception e){
			e.printStackTrace();			
		}
	}
	
	public void  getDetailsOfVotersHasAgeAbove60(
			Long constituencyId,Long tehsilId,Long panchayatId,Long boothId,Long  publicationDateId,
			VotersDetailsVO votersDetailsVO ,String type,List<Long> boothsList){

	    List<Object[]> votersListOfAbove60  = null;
	    
	    try{
	       
	        if(type.equalsIgnoreCase("constituency"))
		         votersListOfAbove60 = boothPublicationVoterDAO
						.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
								constituencyId, publicationDateId,IConstants.AGE61, IConstants.AGE160);
	       else if(type.equalsIgnoreCase("mandal"))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
	    			   tehsilId, publicationDateId,IConstants.AGE61, IConstants.AGE160,constituencyId);
	       else if(type.equalsIgnoreCase("panchayat"))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,IConstants.AGE61, IConstants.AGE160);
			 else if(type.equalsIgnoreCase("booth"))
				 votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,IConstants.AGE61, IConstants.AGE160);
	       else if(type.equalsIgnoreCase("localElectionBody"))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
	    			   tehsilId, publicationDateId,IConstants.AGE61, IConstants.AGE160,constituencyId);
	       else if(type.equalsIgnoreCase("ward"))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,IConstants.AGE61, IConstants.AGE160);
	     /*  else if(type.equalsIgnoreCase(IConstants.HAMLET))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountInSpecifiedRangeForHamletByPublicationId(
						 boothId, publicationDateId,IConstants.AGE60,IConstants.AGE160,votersDetailsVO.getUserId());*/
	       else if(type.equalsIgnoreCase(IConstants.HAMLET) && boothsList !=null && boothsList.size()>0 )
			 {				
	    	   votersListOfAbove60 =boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId, IConstants.AGE61,IConstants.AGE160 , boothsList);
				 }
			 else return;
	        Long maleVotersAbove60 = 0L;
			Long femaleVotersAbove60 = 0L;
			Long unKnownVotersAbove60 = 0L;
			
			
	          for(Object[] obj:votersListOfAbove60){
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersAbove60 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersAbove60 = (Long)obj[0];
				else 
					unKnownVotersAbove60 = (Long)obj[0];
			}
	          
	          Long totalVotersAbove60 = maleVotersAbove60
	  				+ femaleVotersAbove60 + unKnownVotersAbove60;
	          
	          
	          votersDetailsVO.setTotalMaleVotersForAbove60(maleVotersAbove60);
	          votersDetailsVO.setTotalFemaleVotersForAbove60(femaleVotersAbove60);
	          votersDetailsVO.setTotalUnknownVotersForAbove60(unKnownVotersAbove60);
	          votersDetailsVO.setTotalVotersForAbove60(totalVotersAbove60);	 
	        //  votersDetailsVO.setAgeRange("60-Above");
	          if(totalVotersAbove60 != 0){
	        		votersDetailsVO.setMaleVotersPercentForAbove60(roundTo2DigitsFloatValue((float) (maleVotersAbove60 *100f/ totalVotersAbove60 )));
					votersDetailsVO.setFemaleVotersPercentForAbove60(roundTo2DigitsFloatValue((float)(femaleVotersAbove60 *100f/ totalVotersAbove60 )));	        	  
	          }else{
	        	    votersDetailsVO.setMaleVotersPercentForAbove60("0.00");
					votersDetailsVO.setFemaleVotersPercentForAbove60("0.00");	        	  
	          }

	   		
	         // constituencyVotersList.add(voterDetailsForAgeAbove60);
	          
	    }catch(Exception e){
	    	
	    	e.printStackTrace();	    	
	    }
	}
	
	
	
	public List<VotersDetailsVO>   getAgewiseVotersDetailsForPanchayatisByTehsilId(Long tehsilId,Long publicationDateId,Long userId){
		
		
		List<Object[]> panchayatis = panchayatDAO.getPanchayatsByTehsilId(tehsilId);
		
		List<Long> panchayatIds = new ArrayList<Long>();
		
		for(Object[] obj:panchayatis)
			panchayatIds.add((Long)obj[0]);
		
		List<Long> partialIds = partialBoothPanchayatDAO.getPanchayatIdsForPartialPanchayat(panchayatIds, publicationDateId);
		
		List<VotersDetailsVO> constituencyVotersList = new ArrayList<VotersDetailsVO>();
		for(Object[] obj:panchayatis){
			
			if(!partialIds.contains((Long)obj[0]))
			{

	            VotersDetailsVO voterDetailsVO = new VotersDetailsVO();
	            getDetailsOfYounVotersHasAgeBetween18And22(null,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"panchayat",null);
				getDetailsOfVotersHasAgeBetween18And25(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat",null);
				getDetailsOfVotersHasAgeBetween26And35(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat",null);			
				getDetailsOfVotersHasAgeBetween36And45(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat",null);		
				getDetailsOfVotersHasAgeBetween46And60(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat",null);       
				getDetailsOfVotersHasAgeAbove60(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat",null);
				
				voterDetailsVO.setPanchayatname(obj[1].toString());
				Long totalVoters = 0l;
				if(voterDetailsVO.getTotalVotersFor18To25() != null )
				 totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor18To25();
				if(voterDetailsVO.getTotalVotersFor26To35() != null)
					 totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor26To35();
				if(voterDetailsVO.getTotalVotersFor36To45() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor36To45();
				if(voterDetailsVO.getTotalVotersFor46To60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor46To60();
				if(voterDetailsVO.getTotalVotersForAbove60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersForAbove60();
				
				voterDetailsVO.setTotalVoters(totalVoters);
				
				/*voterDetailsVO.setVotersPercentFor18To25(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor26To35(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor36To45(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor46To60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters));
				voterDetailsVO.setVotersPercentForAbove60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters));
				
	*/
				
				
				voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentForYoungerVoters(voterDetailsVO.getTotalVotersForYoungerVoters() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForYoungerVoters()*100f/totalVoters):"0.00");
				
				constituencyVotersList.add(voterDetailsVO);
			}
			
			
		}
		
			if(partialIds != null && partialIds.size() > 0)
				constituencyVotersList.addAll(voterReportService.caluculateAgeWiseDetailsForPanchayatByHamlets(userId, partialIds, publicationDateId, true));
			
			return constituencyVotersList;
			
		}
		
		public List<VotersDetailsVO> getAgewiseVotersDetailsForBoothsByPanchayatId(Long panchayatId,Long publicationDateId,Long userId){
		
		List<Long> boothIds = new ArrayList<Long>();
		
		
		List<Object[]> booths = boothDAO.getBoothsInAPanchayat(panchayatId, publicationDateId);
		
		
		for(Object[] boothDetails:booths)
			boothIds.add((Long)boothDetails[0]);
		
		List<Object[]> list = partialBoothPanchayatDAO.getPartialBoothDetailsByPanchayatIdAndPublicationDateId(panchayatId, publicationDateId);
		
		if(list != null && list.size() >0)
         for(Object[] partialPanchayatDetails:list)        	
         {
        	 
        	 if(!boothIds.contains(partialPanchayatDetails[0]))
        	 {
	        	 Object[] obj = new Object[4];
	        	 obj[0] = partialPanchayatDetails[0];
	        	 obj[1] = partialPanchayatDetails[1];
	        	 obj[2] = partialPanchayatDetails[2];
	        	 obj[3] = partialPanchayatDetails[3];
	        	 
	        	 booths.add(obj);

        	 }
        	 
         }
    
		
		List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
		
		for(Object[] obj:booths){
			
			VotersDetailsVO voterDetailsVO = new VotersDetailsVO();
			
			getDetailsOfYounVotersHasAgeBetween18And22(null,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"booth",null);
			getDetailsOfVotersHasAgeBetween18And25(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth",null);
			getDetailsOfVotersHasAgeBetween26And35(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth",null);			
			getDetailsOfVotersHasAgeBetween36And45(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth",null);		
			getDetailsOfVotersHasAgeBetween46And60(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth",null);       
			getDetailsOfVotersHasAgeAbove60(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth",null);
			
			voterDetailsVO.setBoothName(obj[1].toString());
			Long totalVoters = 0l;
			  if(voterDetailsVO.getTotalVotersFor18To25() != null)
			   totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor18To25();
			  if(voterDetailsVO.getTotalVotersFor26To35() != null)
			    totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor26To35();
			  if(voterDetailsVO.getTotalVotersFor36To45() != null)
				totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor36To45();
			  if(voterDetailsVO.getTotalVotersFor46To60() != null)
				totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor46To60();
			  if(voterDetailsVO.getTotalVotersForAbove60() != null)
				totalVoters = totalVoters+voterDetailsVO.getTotalVotersForAbove60();
			
			voterDetailsVO.setTotalVoters(totalVoters);
			
			/*voterDetailsVO.setVotersPercentFor18To25(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor26To35(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor36To45(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor46To60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters));
			voterDetailsVO.setVotersPercentForAbove60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters));
			*/		
			
			
			voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
			voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
			voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentForYoungerVoters(voterDetailsVO.getTotalVotersForYoungerVoters() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForYoungerVoters()*100f/totalVoters):"0.00");
			
			boothVotersList.add(voterDetailsVO);
			
		}
		return boothVotersList;
		
	}
	
	public List<VotersDetailsVO> getAgewiseVotersDetailsForBoothsByLocalElectionBodyId(Long wardId,Long publicationDateId,Long userId){
		List<Object[]> booths= boothDAO.getBoothsForWard(wardId,publicationDateId);
		List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
		
			
			for(Object[] obj:booths){
				
				VotersDetailsVO voterDetailsVO = new VotersDetailsVO();
				
				getDetailsOfYoungVoters(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth",null,userId);
				getDetailsOfVotersHasAgeBetween18And25(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth",null,userId);
				getDetailsOfVotersHasAgeBetween26And35(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth",null,userId);			
				getDetailsOfVotersHasAgeBetween36And45(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth",null,userId);		
				getDetailsOfVotersHasAgeBetween46And60(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth",null,userId);       
				getDetailsOfVotersHasAgeAbove60(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth",null,userId);
				
				voterDetailsVO.setBoothName(obj[1].toString());
				Long totalVoters = 0l;
				  if(voterDetailsVO.getTotalVotersFor18To25() != null)
				   totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor18To25();
				  if(voterDetailsVO.getTotalVotersFor26To35() != null)
				    totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor26To35();
				  if(voterDetailsVO.getTotalVotersFor36To45() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor36To45();
				  if(voterDetailsVO.getTotalVotersFor46To60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor46To60();
				  if(voterDetailsVO.getTotalVotersForAbove60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersForAbove60();
				
				voterDetailsVO.setTotalVoters(totalVoters);
				
				/*voterDetailsVO.setVotersPercentFor18To25(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor26To35(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor36To45(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor46To60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters));
				voterDetailsVO.setVotersPercentForAbove60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters));
				*/	
				
				
				voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentForYoungerVoters(voterDetailsVO.getTotalVotersForYoungerVoters() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForYoungerVoters()*100f/totalVoters):"0.00");
				
				boothVotersList.add(voterDetailsVO);
				
			}
			
			return boothVotersList;
		
	}
	public List<VotersDetailsVO> getAgewiseVotersDetailsForWardsByLocalElectionBodyId(Long assemblyLocalBodyId,Long publicationDateId,Long constituencyId,Long userId){
		List<VotersDetailsVO> wardWiseVotersDetails = new ArrayList<VotersDetailsVO>();
		 try{
			 List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(assemblyLocalBodyId);
			 List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId,constituencyId);
			for (Object[] ward : wardsList){
			  List<VotersDetailsVO> wareData = getVotersDetailsByAgewiseNew(constituencyId,null,(Long)ward[0],null,publicationDateId,"ward",userId);
			  wardWiseVotersDetails.addAll(wareData);
			}
		 }catch(Exception e){
			 log.error("Exception rised in getAgewiseVotersDetailsForWardsByLocalElectionBodyId method : ",e);
		 }
		return wardWiseVotersDetails;
	}
public List<VotersDetailsVO> getAgewiseVotersDetailsByHamletId(Long hamletId,Long publicationDateId,Long userId){
	
		//List<Object[]> booths = boothDAO.getBoothsInAPanchayat(hamletId, publicationDateId);
		List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
		
		
			
			VotersDetailsVO voterDetailsVO = new VotersDetailsVO();
			voterDetailsVO.setUserId(userId);
			
			getDetailsOfYounVotersHasAgeBetween18And22(null,null,null,hamletId, publicationDateId,voterDetailsVO,IConstants.HAMLET,null);
			getDetailsOfVotersHasAgeBetween18And25(null,null,null,hamletId, publicationDateId,voterDetailsVO,IConstants.HAMLET,null);
			getDetailsOfVotersHasAgeBetween26And35(null,null,null,hamletId, publicationDateId,voterDetailsVO,IConstants.HAMLET,null);		
			getDetailsOfVotersHasAgeBetween36And45(null,null,null,hamletId, publicationDateId,voterDetailsVO,IConstants.HAMLET,null);	
			getDetailsOfVotersHasAgeBetween46And60(null,null,null,hamletId, publicationDateId,voterDetailsVO,IConstants.HAMLET,null);    
			getDetailsOfVotersHasAgeAbove60(null,null,null,hamletId, publicationDateId,voterDetailsVO,IConstants.HAMLET,null);
			
			//voterDetailsVO.setBoothName(hamletId.toString());
			Long totalVoters = 0l;
			  if(voterDetailsVO.getTotalVotersFor18To25() != null)
			   totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor18To25();
			  if(voterDetailsVO.getTotalVotersFor26To35() != null)
			    totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor26To35();
			  if(voterDetailsVO.getTotalVotersFor36To45() != null)
				totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor36To45();
			  if(voterDetailsVO.getTotalVotersFor46To60() != null)
				totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor46To60();
			  if(voterDetailsVO.getTotalVotersForAbove60() != null)
				totalVoters = totalVoters+voterDetailsVO.getTotalVotersForAbove60();
			
			voterDetailsVO.setTotalVoters(totalVoters);
			
			/*voterDetailsVO.setVotersPercentFor18To25(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor26To35(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor36To45(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor46To60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters));
			voterDetailsVO.setVotersPercentForAbove60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters));
			*/		
			
			
			voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
			voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
			voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentForYoungerVoters(voterDetailsVO.getTotalVotersForYoungerVoters() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForYoungerVoters()*100f/totalVoters):"0.00");
			
			boothVotersList.add(voterDetailsVO);
			
	
		return boothVotersList;
		
	}

			
	//Retrieving important families information
	/*public ImportantFamiliesInfoVo getImportantFamiliesInfo(String type,Long id,Long publicationDateId){	
		try{
			if(type.equalsIgnoreCase("constituency")){
				return getImportantFamiliesForConstituency(type,id,publicationDateId);
				
			}
			else if(type.equalsIgnoreCase("mandal")){
				return getImportantFamiliesForMandal(type,id,publicationDateId,"main");
			}
			else if(type.equalsIgnoreCase("booth")){
				return getImportantFamiliesForBooth(type,id,publicationDateId,"main");
			}
			else if(type.equalsIgnoreCase("panchayat")){
				return getImportantFamiliesForPanchayat(id,publicationDateId,"","main");
			}
		}catch(Exception e){
			log.error("Exception rised in getImportantFamiliesInfo method : ",e);
		}
		return null;
	}*/
	
	//Retrieving important families information
	public ImportantFamiliesInfoVo getImportantFamiliesInfo(Long userId , String type,Long id,Long publicationDateId,Long constituencyId ,String requestFor,String buildType){	
		try{
			if(type.equalsIgnoreCase("constituency"))
			{
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImportantFamilyDetailsForConstituency(type, id, publicationDateId);
				if(!importantFamiliesInfoVo.isDataPresent())
					importantFamiliesInfoVo =  getImportantFamiliesForConstituency(type,id,publicationDateId,userId);
				return importantFamiliesInfoVo;
			}
			else if(type.equalsIgnoreCase("mandal"))
			{
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImpFamiliesForMandal(type,id,publicationDateId,"main",constituencyId,userId);
				if(!importantFamiliesInfoVo.isDataPresent())
					importantFamiliesInfoVo = getImportantFamiliesForMandal(type,id,publicationDateId,"main",constituencyId,userId);
				return importantFamiliesInfoVo;
			}
			else if(type.equalsIgnoreCase("localBody"))
			{
				 if(requestFor.equalsIgnoreCase(IConstants.MUNCIPALITYWARDS))
						return  getImportantFamiliesDetailsForWardFromMuncipality(userId, id, publicationDateId, constituencyId, type);
				
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImpFamiliesForMandal(type,id,publicationDateId,"main",constituencyId,userId);
				if(!importantFamiliesInfoVo.isDataPresent())
					importantFamiliesInfoVo = getImportantFamiliesForMandal(type,id,publicationDateId,"main",constituencyId,userId);
				return importantFamiliesInfoVo;
			}
			else if(type.equalsIgnoreCase("booth"))
			{
				if(requestFor.equalsIgnoreCase("hamletBooth"))					
					return 	getImportantFamiliesDetailsForHamletFromBooth(userId , id , publicationDateId ,  constituencyId);	
					
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImpFamiliesForBooth(type,id,publicationDateId,"main",constituencyId);
				if(!importantFamiliesInfoVo.isDataPresent())
					importantFamiliesInfoVo = getImportantFamiliesForBooth(type,id,publicationDateId,"main",constituencyId);
				return importantFamiliesInfoVo;
			}
			else if(type.equalsIgnoreCase("hamlet") || type.equalsIgnoreCase("customWard"))
			{
				//FOR BOOTHS START
				 if(requestFor.equalsIgnoreCase("booth"))
				 {
					 ImportantFamiliesInfoVo importantFamiliesInfoVo  = new ImportantFamiliesInfoVo();
				    
					 getImpFamilesInfoDetailsForHamlet(userId,id,publicationDateId,importantFamiliesInfoVo,type);
					 
					 String queryCond = null;
					 if(type.equalsIgnoreCase(IConstants.HAMLET))
					 {
						queryCond =" model.hamlet.hamletId = :id ";
						importantFamiliesInfoVo.setName(hamletDAO.get(id).getHamletName());
					 }
					 else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
					 {
						queryCond =" model.ward.constituencyId = :id ";	
						importantFamiliesInfoVo.setName(constituencyDAO.get(id).getName());
					 }
				
					 importantFamiliesInfoVo.setType(type);
						
					 List<Long> booths = boothPublicationVoterDAO.getAllBoothsInHamletByUser(userId,id,publicationDateId,constituencyId,queryCond);
				    
					 for(Long boothId:booths)
					 {
						  List<Object[]> boothFamilyDetails =  boothPublicationVoterDAO.getFamiliesInBooth(userId ,id, boothId , publicationDateId,constituencyId,queryCond);
						  
						  ImportantFamiliesInfoVo importantFamiliesInfoVo1 = new ImportantFamiliesInfoVo();
						  
						  long upTo3 = 0;
						  long upTo3Count = 0;
						  long between4And6 = 0;
						  long between4And6Count = 0;
						  long between7And10 = 0;
						  long between7And10Count = 0;
						  long above10 = 0;
						  long above10Count = 0;						     

						  
						  for(Object[] details:boothFamilyDetails)
						  {
							  
							  long count =(Long)details[0];
							  
							  if(count <= 3 ){
								  upTo3++;
								  upTo3Count = upTo3Count + count;
							  }
							  else if(count > 3 && count <=6){
							      between4And6++;
							      between4And6Count = between4And6Count + count;
							  }
							  else if(count > 6 && count <10){
								  between7And10++;
								  between7And10Count =between7And10Count + count;
							  }
							  else{
							    above10++;
							    above10Count=above10Count + count;
							  }
							  
						  }
						  
						  List<Object[]> votersCount =  boothPublicationVoterDAO.getVotersCountByGenderInBooth(userId , id , boothId , publicationDateId,constituencyId ,queryCond);
			     
					      for(Object[] obj1:votersCount)
					      {
					    	  if(obj1[1].toString().equalsIgnoreCase("M"))
					    		  importantFamiliesInfoVo1.setTotalMaleVoters(obj1[0].toString());
					    	  else if(obj1[1].toString().equalsIgnoreCase("F"))
					    		  importantFamiliesInfoVo1.setTotalFemaleVoters(obj1[0].toString());						    	  
					      }

					     importantFamiliesInfoVo1.setBelow3(upTo3);
					     importantFamiliesInfoVo1.setBelow3Popul(upTo3Count);					     
					     importantFamiliesInfoVo1.setBetwn4to6(between4And6);
					     importantFamiliesInfoVo1.setBetwn4to6Popul(between4And6Count);
					     importantFamiliesInfoVo1.setBetwn7to10(between7And10);
					     importantFamiliesInfoVo1.setBetwn7to10Popul(between7And10Count);
					     importantFamiliesInfoVo1.setAbove10(above10);
					     importantFamiliesInfoVo1.setAbove10Popul(above10Count);
					     
					     importantFamiliesInfoVo1.setTotalVoters(upTo3Count + between4And6Count + between7And10Count + above10Count);
					     
					     importantFamiliesInfoVo1.setName("booth -"+boothDAO.getPartNoByBoothId(boothId));
					     importantFamiliesInfoVo1.setType("booth");
					     
					     calculatePercentage(importantFamiliesInfoVo1);
					     
					     importantFamiliesInfoVo.getSubList().add(importantFamiliesInfoVo1);
						}
				    return importantFamiliesInfoVo;
				 }
				    
				 //FOR BOOTHS END
				
				
				/*ImportantFamiliesInfoVo importantFamiliesInfoVo = getImpFamiliesForBooth(type,id,publicationDateId,"main",constituencyId);
				if(!importantFamiliesInfoVo.isDataPresent())*/
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImportantFamiliesForhamlet(userId , type,id,publicationDateId,"main",constituencyId);
				if(importantFamiliesInfoVo == null )
					return null;
				//updated by sasi -- Start
								
				List<Object[]> objlist=boothPublicationVoterDAO.getAssignedAndUnassignedVtrsOfLclBdy(id,userId,IConstants.HAMLET);
				if(objlist != null && objlist.size() > 0)
				   {
					   for(Object[] params : objlist){
						   importantFamiliesInfoVo.setAssignedVotersForLocalBodies((Long) params[0]);
						   importantFamiliesInfoVo.setUnassignedVotersForLocalBodies((Long) params[1]);
						   
					   }
				   }
				//updated by sasi -- End	
				
				return importantFamiliesInfoVo;
			}
			else if(type.equalsIgnoreCase("panchayat"))
			{
				Long count = partialBoothPanchayatDAO.checkPanchayatIsPartial(id, publicationDateId);
				ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
				importantFamiliesInfoVo.setDataPresent(false);
				if(count == null || count == 0)
				 importantFamiliesInfoVo = getImpFamiliesForPanchayat(id,publicationDateId,"","main",constituencyId);
				try{
				if(!importantFamiliesInfoVo.isDataPresent())
					importantFamiliesInfoVo = getImportantFamiliesForPanchayat1(userId ,id,publicationDateId,"","main",constituencyId);
				if(buildType.equalsIgnoreCase(IConstants.HAMLET)){
				
				List<Long> hamlets = userVoterDetailsDAO.getUserHamletsByPanchayatId(userId ,id );
				
				List<Object[]> assignedAndUnAssignedVotersOfUser = boothPublicationVoterDAO.getVotersListInPanchayat(publicationDateId,id,userId);
				
				if(assignedAndUnAssignedVotersOfUser != null && assignedAndUnAssignedVotersOfUser.size() > 0)
				{
					   for(Object[] params : assignedAndUnAssignedVotersOfUser)
					   {
						   importantFamiliesInfoVo.setAssignedVotersByUser((Long) params[0]);
						   importantFamiliesInfoVo.setUnassignedVotersByUser((Long) params[1]);
					   }
				}
				
				for(Long  hamletId:hamlets)			
					 importantFamiliesInfoVo.getSubListForHamlets().add(getImportantFamiliesDetailsForHamlet(userId , "hamlet",hamletId,publicationDateId,"sub",constituencyId));
				}
				return importantFamiliesInfoVo;
				}catch (Exception e) {
					log.error("Exception occured - ",e);
					return importantFamiliesInfoVo;
				}
			}
			else if(type.equalsIgnoreCase("ward"))
			{
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImpFamiliesForWard(id,publicationDateId,"main",constituencyId);
				
				if(!importantFamiliesInfoVo.isDataPresent())
					importantFamiliesInfoVo = getImportantFamiliesForWard(id,publicationDateId,"main",constituencyId);
				return importantFamiliesInfoVo;
			}
			/*else if(type.equalsIgnoreCase("customWard"))
			{
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImpFamiliesForWard(id,publicationDateId,"main",constituencyId);
				if(!importantFamiliesInfoVo.isDataPresent());
				importantFamiliesInfoVo = getImportantFamiliesForCustomWard(id,publicationDateId,"main",constituencyId);
				return importantFamiliesInfoVo;
			}*/
		}catch(Exception e){
			log.error("Exception rised in getImportantFamiliesInfo method : ",e);
		}
		return null;
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesForConstituency(String type,Long id,Long publicationDateId,Long userId){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		Constituency constituency = constituencyDAO.get(id);
		importantFamiliesInfoVo.setType("Constituency");
		importantFamiliesInfoVo.setName(constituency.getName());
		importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCount(id,publicationDateId,"constituency"));
		 getImpFamilesInfo(type,id,publicationDateId,importantFamiliesInfoVo,"","main",null,id);
		 
		 VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForConstituency(type, id, publicationDateId);
		 importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForMandalVO.getTotalMaleVoters());
		 importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForMandalVO.getTotalFemaleVoters());
		 importantFamiliesInfoVo.setUnKnowVoters(votersInfoForMandalVO.getUnKnowVoters());

		 if(importantFamiliesInfoVo.isDataPresent()){
		    List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
		    if("URBAN".equalsIgnoreCase(constituency.getAreaType()) && mandalsList != null && mandalsList.size() == 1){
				  String localBodyId = mandalsList.get(0).getId().toString();
				  if(localBodyId.substring(0,1).trim().equalsIgnoreCase("1")){
					  List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(localBodyId.substring(1)));
					  List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long)list.get(0),publicationDateId,id);
						for (Object[] ward : wardsList){
							importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForWard((Long)ward[0],publicationDateId,"sub",id));
						}
				  }
			  }else{
			    for(SelectOptionVO mandal : mandalsList){
					
			    	ImportantFamiliesInfoVo mandalList = getImportantFamiliesForMandal("mandal",mandal.getId(),publicationDateId,"sub",id,userId);
			    	VotersInfoForMandalVO votersInfoForMandal = getVotersCountForMandal("mandal", mandal.getId(), publicationDateId,id,userId);
			    	mandalList.setTotalMaleVoters(votersInfoForMandal.getTotalMaleVoters());
			    	mandalList.setTotalFemaleVoters(votersInfoForMandal.getTotalFemaleVoters());
			    	mandalList.setUnKnowVoters(votersInfoForMandal.getUnKnowVoters());
			    	importantFamiliesInfoVo.getSubList().add(mandalList);
			    }
			  }
		 }
		 return  importantFamiliesInfoVo;
	}
	
	public void getImpFamilesInfo(String type,Long id,Long publicationDateId,ImportantFamiliesInfoVo importantFamiliesInfoVo,String queryToexe,String exeType,List<Long> ids,Long constituencyId){
		
        String query = "";
        Long[] totalFamilies = getFamiliesCount(type,id,publicationDateId,null,queryToexe,exeType,ids,constituencyId);
		if(totalFamilies != null){
          importantFamiliesInfoVo.setTotalFamalies(totalFamilies[0]);
		}else{
			importantFamiliesInfoVo.setDataPresent(false);
			return;
		}
	     query = " having count(model.voter.voterId) <= 3 ";
	     Long[] count = getFamiliesCount(type,id,publicationDateId,query,queryToexe,exeType,ids,constituencyId);
	     importantFamiliesInfoVo.setBelow3(count[0]);
	     importantFamiliesInfoVo.setBelow3Popul(count[1]);
	     
	     query = " having count(model.voter.voterId) > 3 and count(model.voter.voterId) <= 6 ";
	     count = getFamiliesCount(type,id,publicationDateId,query,queryToexe,exeType,ids,constituencyId);
	     importantFamiliesInfoVo.setBetwn4to6(count[0]);
	     importantFamiliesInfoVo.setBetwn4to6Popul(count[1]);
	    
	     query = " having count(model.voter.voterId) > 6 and count(model.voter.voterId) <= 10 ";
	     count = getFamiliesCount(type,id,publicationDateId,query,queryToexe,exeType,ids,constituencyId);
	     importantFamiliesInfoVo.setBetwn7to10(count[0]);
	     importantFamiliesInfoVo.setBetwn7to10Popul(count[1]);
	    
	     query = " having count(model.voter.voterId) > 10 ";
	     count = getFamiliesCount(type,id,publicationDateId,query,queryToexe,exeType,ids,constituencyId);
	     importantFamiliesInfoVo.setAbove10(count[0]);
	     importantFamiliesInfoVo.setAbove10Popul(count[1]);
	     
	     if(importantFamiliesInfoVo.getTotalVoters() > 0)
	       calculatePercentage(importantFamiliesInfoVo);
	     
	}
	
	public Long[] getFamiliesCount(String type,Long id,Long publicationDateId,String query,String queryToexe,String exeType,List<Long> ids,Long constituencyId){
		
		Long[] count = {0l,0l};
		List<Object[]> totalFamiliesList = null;
		if(queryToexe.equalsIgnoreCase(""))
			totalFamiliesList = boothPublicationVoterDAO.findAllImpFamiles(id,publicationDateId,type,query,constituencyId);
		else if(queryToexe.equalsIgnoreCase("local"))
			totalFamiliesList = boothPublicationVoterDAO.getVotersImpFamilesForLocalElectionBody(id,publicationDateId,query,constituencyId);
		else if(queryToexe.equalsIgnoreCase("panchayat"))
			totalFamiliesList = boothPublicationVoterDAO.getImpFamilesForPanchayatByPublicationId(id,publicationDateId,query);
		else if(queryToexe.equalsIgnoreCase("ward"))
			totalFamiliesList = boothPublicationVoterDAO.getImpFamilesForWard(ids,publicationDateId,query);
		else if(queryToexe.equalsIgnoreCase("customward"))
			totalFamiliesList = userVoterDetailsDAO.getImpFamilesForCustomWard(ids,publicationDateId,query,1L);//18111change
		if(!totalFamiliesList.isEmpty() && totalFamiliesList.get(0)[1] != null){
	    	count[0] = new Long(totalFamiliesList.size());
	       if(query != null){
	    	Long totalVoters = 0l;
	    	for(Object[] family : totalFamiliesList){
	    		if(family[0] != null){
	    			totalVoters = totalVoters+(Long)family[0];
	    		}
	    	 }
	    	count[1] = totalVoters;
	       }
	    }
		else if(query == null && exeType.equalsIgnoreCase("main")){
			return null;
		}
	    return count;
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesForMandal(String type,Long id,Long publicationDateId,String exeType,Long constituencyId,Long userId){
		if(id.toString().substring(0,1).trim().equalsIgnoreCase("2"))
		{
			ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
			importantFamiliesInfoVo.setType("Mandal/Tehsil");
			importantFamiliesInfoVo.setName(tehsilDAO.get(new Long(id.toString().substring(1))).getTehsilName());
			importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCount(new Long(id.toString().substring(1).trim()),publicationDateId,"mandal"));
			
			VotersInfoForMandalVO votersInfoForMandal = getVotersCountForMandal("mandal", id, publicationDateId,constituencyId,userId);
			importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForMandal.getTotalMaleVoters());
			importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForMandal.getTotalFemaleVoters());
			importantFamiliesInfoVo.setUnKnowVoters(votersInfoForMandal.getUnKnowVoters());
			
			 getImpFamilesInfo(type,new Long(id.toString().substring(1).trim()),publicationDateId,importantFamiliesInfoVo,"",exeType,null,constituencyId);
			 if(exeType.equalsIgnoreCase("main") && importantFamiliesInfoVo.isDataPresent()){
			 List<Object[]> panchayaties = panchayatDAO.getPanchayatsBymandalId(new Long(id.toString().substring(1).trim()));
				for (Object[] panchayat : panchayaties){
					importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForPanchayat((Long)panchayat[0],publicationDateId,"","sub",constituencyId,userId));
				}
			 }
			 return importantFamiliesInfoVo;
		}else{
			ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
			importantFamiliesInfoVo.setType("Muncipality/Corporation");
			List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1).trim()));
			List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(id.toString().substring(1));
			  Object[] reqName = assemblyLocalElectionBodyName.get(0);
			  String name = reqName[0].toString()+" "+reqName[1].toString();
			importantFamiliesInfoVo.setName(name);
			importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getVotersCountForLocalElectionBody((Long) list.get(0),publicationDateId));
			 getImpFamilesInfo(type,(Long) list.get(0),publicationDateId,importantFamiliesInfoVo,"local",exeType,null,constituencyId);
			 
			 VotersInfoForMandalVO votersInfoForMandal = getVotersCountForMandal("mandal", id, publicationDateId,constituencyId,userId);
			 importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForMandal.getTotalMaleVoters());
			 importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForMandal.getTotalFemaleVoters());
			 importantFamiliesInfoVo.setUnKnowVoters(votersInfoForMandal.getUnKnowVoters());
			 if(exeType.equalsIgnoreCase("main") && importantFamiliesInfoVo.isDataPresent()){
				 
				 if(reqName[1].toString().equalsIgnoreCase(IConstants.GHMC)){
					List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long)list.get(0),publicationDateId,constituencyId);
					for (Object[] ward : wardsList){
						importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForWard((Long)ward[0],publicationDateId,"sub",constituencyId));
					}
				 }else{
					List<Object[]> boothsList = boothDAO.getBoothsInAMunicipality((Long) list.get(0),publicationDateId,constituencyId);
				     for(Object[] booth : boothsList){
				    	 importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForBooth("booth",(Long)booth[0],publicationDateId,"sub",constituencyId));
				     }
				 }
			  }
			 return importantFamiliesInfoVo;
		}
		
	}
	
	
	public ImportantFamiliesInfoVo getImportantFamiliesForhamlet(Long userId ,String type,Long id,Long publicationDateId,String exeType,Long constituencyId){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		
		String queryCond = "";
		importantFamiliesInfoVo.setType(type);
		if(type.equalsIgnoreCase(IConstants.HAMLET)){
			queryCond =" model.hamlet.hamletId = :id ";
			importantFamiliesInfoVo.setName(hamletDAO.get(id).getHamletName());
		}
			else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD)){
				queryCond =" model.ward.constituencyId = :id ";	
				importantFamiliesInfoVo.setName(constituencyDAO.get(id).getName());
			}
	
		//importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCountForHamlet(userId,id,publicationDateId,"hamlet"));
		
		List<Object[]> localitiesList = localityDAO.getAllLocalitiesForHamlet(userId,id,type,queryCond);
		
		
		for(Object[] obj:localitiesList)		
		 importantFamiliesInfoVo.getSubListForHamlets().add(getImportantFamiliesForLocalities(userId,id,type,obj[1].toString(),(Long)obj[0],publicationDateId,"sub",constituencyId ,queryCond));
		
		
		 getImpFamilesInfoDetailsForHamlet(userId,id,publicationDateId,importantFamiliesInfoVo ,type);
		 if(importantFamiliesInfoVo.getTotalVoters() == null || importantFamiliesInfoVo.getTotalVoters() == 0  )
			return null;
	
		 return importantFamiliesInfoVo;
	}
	
	public void getImpFamilesInfoDetailsForHamlet(Long userId,Long id,Long publicationDateId,ImportantFamiliesInfoVo importantFamiliesInfoVo ,String  type){
		List<Object[]>  impFamilesList = null;
		List<Object[]> hamletVotersCountByGender = null;
		
		List<Long> hamlets = new ArrayList<Long>();
		hamlets.add(id);
		//18111 families
       //  List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletIds(userId , hamlets);
		/*  List<Long> voterIds  =(List<Long>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(id, userId ,type);	
		if(voterIds == null || voterIds.isEmpty())
			return ;*/
         impFamilesList = boothPublicationVoterDAO.getImpFamilesForPanchayatByPublicationIdAndVoters(publicationDateId,userId,type,hamlets);
 		
         if(impFamilesList == null || impFamilesList.isEmpty())
 			return ;
 		//hamletVotersCountByGender = boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublicationAndGender(publicationDateId,voterIds);
 		
 		
 		
		//	impFamilesList = boothPublicationVoterDAO.getImpFamilesForPanchayatByPublicationIdAndHamlet(userId,id,publicationDateId,null);
			
		
		Long below3 = 0l;
		Long between4To6 = 0l;
		Long between7To10 = 0l;
		Long above10 = 0l;
		Long count = 0l;
		Long above10Count = 0l;
		Long between7T10Count = 0l;
		Long between4To6Count = 0l;
		Long below3Count = 0l;
		Long totalMaleVoters=0l;
		Long totalFemaleVoters=0l;
		for (Object[] impFamiles : impFamilesList) {
			count = (Long) impFamiles[0];	
			if(count.longValue() > 10){
				above10 = above10+1;
				above10Count = count + above10Count;
			}
			else if(count.longValue() <= 10 && count.longValue() >= 7){
				between7To10 = between7To10+1;
				between7T10Count = count + between7T10Count;
			}
			else if(count.longValue() < 7 && count.longValue() >=4){
				between4To6 = between4To6 + 1;
				between4To6Count = count + between4To6Count;	
			}
			else if(count <= 3){
				below3 = below3 + 1;
				below3Count = count + below3Count;
			}
			totalMaleVoters += (Long) impFamiles[2];	
			totalFemaleVoters += (Long) impFamiles[3];	
		}
		//object processing
		
		/* if(hamletVotersCountByGender != null && hamletVotersCountByGender.size() > 0)
		   {
			   for(Object[] params : hamletVotersCountByGender){
				   String gender=params[1].toString();
				   if(gender.equalsIgnoreCase("M")){
					   importantFamiliesInfoVo.setTotalMaleVoters(params[0].toString());
				   }
				   else{
					   importantFamiliesInfoVo.setTotalFemaleVoters(params[0].toString());
				   }
			   }
			   
		   }*/
		
		 importantFamiliesInfoVo.setTotalFemaleVoters(totalMaleVoters.toString());
		   importantFamiliesInfoVo.setTotalMaleVoters(totalFemaleVoters.toString());

		importantFamiliesInfoVo.setAbove10(above10);
		importantFamiliesInfoVo.setAbove10Popul(above10Count);
		importantFamiliesInfoVo.setBetwn7to10(between7To10);
		importantFamiliesInfoVo.setBetwn7to10Popul(between7T10Count);
		importantFamiliesInfoVo.setBetwn4to6Popul(between4To6Count);
		importantFamiliesInfoVo.setBetwn4to6(between4To6);
		importantFamiliesInfoVo.setBelow3(below3);
		importantFamiliesInfoVo.setBelow3Popul(below3Count);
		importantFamiliesInfoVo.setTotalFamalies(above10+between7To10+between4To6+below3);
		importantFamiliesInfoVo.setTotalVoters(above10Count+between7T10Count+between4To6Count+below3Count);
		calculatePercentage(importantFamiliesInfoVo);
	}
	
	
	public ImportantFamiliesInfoVo getImportantFamiliesForBooth(String type,Long id,Long publicationDateId,String exeType,Long constituencyId){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setType("Booth");
		//importantFamiliesInfoVo.setName("booth-"+boothDAO.get(id).getPartNo());
		importantFamiliesInfoVo.setName(boothDAO.get(id).getPartNo());
		importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCount(id,publicationDateId,"booth"));
		
		VotersInfoForMandalVO votersInfoForBooth = getVotersCountForBooth(type, id, publicationDateId, exeType);
		importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForBooth.getTotalMaleVoters());
		importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForBooth.getTotalFemaleVoters());
		importantFamiliesInfoVo.setUnKnowVoters(votersInfoForBooth.getUnKnowVoters());
		
		 getImpFamilesInfo(type,id,publicationDateId,importantFamiliesInfoVo,"",exeType,null,constituencyId);
		 return importantFamiliesInfoVo;
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesForLocalities(Long userId  , Long hamletId,String type ,String name,Long id,Long publicationDateId,String exeType,Long constituencyId ,String query){
		
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setType("Locality");
		importantFamiliesInfoVo.setName(name);
		importantFamiliesInfoVo.setTotalVoters((Long)userVoterDetailsDAO.getVotersCountForALocality(hamletId,id,userId,query,publicationDateId).get(0));
		
		VotersInfoForMandalVO votersInfoForBooth = getVotersCountForLocality(userId ,hamletId, id, publicationDateId, exeType,name ,query);
		//VotersInfoForMandalVO votersInfoForBooth = getVotersCountForBooth(type, id, publicationDateId, exeType);
		importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForBooth.getTotalMaleVoters());
		importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForBooth.getTotalFemaleVoters());
		importantFamiliesInfoVo.setUnKnowVoters(votersInfoForBooth.getUnKnowVoters());
		
		getImpFamilesInfoForLocality(userId,hamletId,id,publicationDateId,importantFamiliesInfoVo,query,exeType,null,constituencyId);
		 return importantFamiliesInfoVo;
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesForPanchayat1(Long userId , Long id,Long publicationDateId,String reqType,String exeType,Long constituencyId){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setType("Panchayat");
		importantFamiliesInfoVo.setName(panchayatDAO.get(id).getPanchayatName());
		importantFamiliesInfoVo.setTotalVoters((Long)boothPublicationVoterDAO.getVotersCountForPanchayat(id,publicationDateId).get(0));
		
		VotersInfoForMandalVO VotersInfoForPanchayat = getVotersCountForPanchayat(id, publicationDateId, "Panchayat",userId);
		importantFamiliesInfoVo.setTotalMaleVoters(VotersInfoForPanchayat.getTotalMaleVoters());
		importantFamiliesInfoVo.setTotalFemaleVoters(VotersInfoForPanchayat.getTotalFemaleVoters());
		importantFamiliesInfoVo.setUnKnowVoters(VotersInfoForPanchayat.getUnKnowVoters());
		boolean isPartial = false;
		Long count = partialBoothPanchayatDAO.getPartialBoothPanchayatDetails(id, publicationDateId);
		 if(count > 0){
			 isPartial = true;
		 }
		//getImpFamilesInfo("",id,publicationDateId,importantFamiliesInfoVo,"panchayat",exeType);
		getImpFamilesForPanchayat(id,publicationDateId,importantFamiliesInfoVo,isPartial,userId,"family");
		 if(exeType.equalsIgnoreCase("main")  && importantFamiliesInfoVo.isDataPresent()){
			 Set<Long> boothIds = new HashSet<Long>();
			 List<Long> boothsList = boothDAO.getBoothsInAPanchayat1(id,publicationDateId);
			 if(boothsList != null && boothsList.size() > 0)
				 boothIds.addAll(boothsList);
			 boothsList = partialBoothPanchayatDAO.getPartialBooths(id, publicationDateId);
			 if(boothsList != null && boothsList.size() > 0)
				 boothIds.addAll(boothsList);
			 for(Long booth : boothIds){
		    	 importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForBooth("booth",booth,publicationDateId,"sub",constituencyId));
		     }
		 }
		 return importantFamiliesInfoVo;
	}
	public ImportantFamiliesInfoVo getImportantFamiliesForPanchayat(Long id,Long publicationDateId,String reqType,String exeType,Long constituencyId,Long userId){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setType("Panchayat");
		importantFamiliesInfoVo.setName(panchayatDAO.get(id).getPanchayatName());
		importantFamiliesInfoVo.setTotalVoters((Long)boothPublicationVoterDAO.getVotersCountForPanchayat(id,publicationDateId).get(0));
		
		VotersInfoForMandalVO VotersInfoForPanchayat = getVotersCountForPanchayat(id, publicationDateId, "Panchayat",userId);
		importantFamiliesInfoVo.setTotalMaleVoters(VotersInfoForPanchayat.getTotalMaleVoters());
		importantFamiliesInfoVo.setTotalFemaleVoters(VotersInfoForPanchayat.getTotalFemaleVoters());
		importantFamiliesInfoVo.setUnKnowVoters(VotersInfoForPanchayat.getUnKnowVoters());
		boolean isPartial = false;
		Long count = partialBoothPanchayatDAO.getPartialBoothPanchayatDetails(id, publicationDateId);
		 if(count > 0){
			 isPartial = true;
		 }
		 //getImpFamilesInfo("",id,publicationDateId,importantFamiliesInfoVo,"panchayat",exeType);
		getImpFamilesForPanchayat(id,publicationDateId,importantFamiliesInfoVo,isPartial,userId,"family");
		 if(exeType.equalsIgnoreCase("main")  && importantFamiliesInfoVo.isDataPresent()){
			 Set<Long> boothIds = new HashSet<Long>();
			 List<Long> boothsList = boothDAO.getBoothsInAPanchayat1(id,publicationDateId);
			 if(boothsList != null && boothsList.size() > 0)
				 boothIds.addAll(boothsList);
			 boothsList = partialBoothPanchayatDAO.getPartialBooths(id, publicationDateId);
			 if(boothsList != null && boothsList.size() > 0)
				 boothIds.addAll(boothsList);
			 for(Long booth : boothIds){
		    	 importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForBooth("booth",booth,publicationDateId,"sub",constituencyId));
		     }
		 }
		 return importantFamiliesInfoVo;
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesForWard(Long id,Long publicationDateId,String reqType,Long constituencyId){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setType("Ward");
		importantFamiliesInfoVo.setName(constituencyDAO.get(id).getName());
		//importantFamiliesInfoVo.setName(panchayatDAO.get(id).getPanchayatName());
		List<Long> boothsList = boothDAO.getBoothIdsForWard(id,publicationDateId);
		/*List<Long> votersCountList = boothPublicationVoterDAO.getVotersCountForMultipleBooths(boothsList,publicationDateId);
		 if(votersCountList != null && votersCountList.size() >0){ 
		   importantFamiliesInfoVo.setTotalVoters((Long)votersCountList.get(0));
		 }*/
		VotersInfoForMandalVO votersInfoForWard = getVotersCountForWard(id, publicationDateId, "sub");
		importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForWard.getTotalMaleVoters());
		importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForWard.getTotalFemaleVoters());
		importantFamiliesInfoVo.setUnKnowVoters(votersInfoForWard.getUnKnowVoters());
		importantFamiliesInfoVo.setTotalVoters(votersInfoForWard.getTotVoters().longValue());
		 getImpFamilesInfo("",id,publicationDateId,importantFamiliesInfoVo,"ward",reqType,boothsList,constituencyId);
		 if(reqType.equalsIgnoreCase("main")  && importantFamiliesInfoVo.isDataPresent()){
		     for(Long booth : boothsList){
		    	 importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForBooth("booth",booth,publicationDateId,"sub",constituencyId));
		     }
		 }
		 return importantFamiliesInfoVo;
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesForCustomWard(Long id,Long publicationDateId,String reqType,Long constituencyId){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setType("customWard");
		importantFamiliesInfoVo.setName(constituencyDAO.get(id).getName());
		List<Long> wardIds = new ArrayList<Long>();
		wardIds.add(id);
		VotersInfoForMandalVO votersInfoForWard = getVotersCountForCustomWard(id, publicationDateId, "sub");
		importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForWard.getTotalMaleVoters());
		importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForWard.getTotalFemaleVoters());
		importantFamiliesInfoVo.setUnKnowVoters(votersInfoForWard.getUnKnowVoters());
		importantFamiliesInfoVo.setTotalVoters(votersInfoForWard.getTotVoters().longValue());
		 getImpFamilesInfo("",id,publicationDateId,importantFamiliesInfoVo,"customward",reqType,wardIds,constituencyId);
		 
		 return importantFamiliesInfoVo;
	}
	public void calculatePercentage(ImportantFamiliesInfoVo importantFamiliesInfoVo){
		 DecimalFormat df = new DecimalFormat("#.##");
		if(importantFamiliesInfoVo.getTotalVoters() != null && importantFamiliesInfoVo.getTotalVoters() > 0){
		    importantFamiliesInfoVo.setBelow3perc(new Double(df.format(importantFamiliesInfoVo.getBelow3Popul().doubleValue()/importantFamiliesInfoVo.getTotalVoters()*100)));
		    importantFamiliesInfoVo.setBetwn4to6perc(new Double(df.format(importantFamiliesInfoVo.getBetwn4to6Popul().doubleValue()/importantFamiliesInfoVo.getTotalVoters()*100)));
		    importantFamiliesInfoVo.setBetwn7to10perc(new Double(df.format(importantFamiliesInfoVo.getBetwn7to10Popul().doubleValue()/importantFamiliesInfoVo.getTotalVoters()*100)));
		    importantFamiliesInfoVo.setAbove10perc(new Double(df.format(importantFamiliesInfoVo.getAbove10Popul().doubleValue()/importantFamiliesInfoVo.getTotalVoters()*100)));
		 }else{
			    importantFamiliesInfoVo.setBelow3perc(0d);
			    importantFamiliesInfoVo.setBetwn4to6perc(0d);
			    importantFamiliesInfoVo.setBetwn7to10perc(0d);
			    importantFamiliesInfoVo.setAbove10perc(0d); 
		 }
	}
	
	
	public void calculatePercentageForFamiles(ImportantFamiliesInfoVo importantFamiliesInfoVo){
		 DecimalFormat df = new DecimalFormat("#.##");
		if(importantFamiliesInfoVo.getTotalVoters() != null && importantFamiliesInfoVo.getTotalVoters() > 0){
		    importantFamiliesInfoVo.setBelow3perc(new Double(df.format(importantFamiliesInfoVo.getBelow3().doubleValue()/importantFamiliesInfoVo.getTotalFamalies()*100)));
		    importantFamiliesInfoVo.setBetwn4to6perc(new Double(df.format(importantFamiliesInfoVo.getBetwn4to6().doubleValue()/importantFamiliesInfoVo.getTotalFamalies()*100)));
		    importantFamiliesInfoVo.setBetwn7to10perc(new Double(df.format(importantFamiliesInfoVo.getBetwn7to10().doubleValue()/importantFamiliesInfoVo.getTotalFamalies()*100)));
		    importantFamiliesInfoVo.setAbove10perc(new Double(df.format(importantFamiliesInfoVo.getAbove10().doubleValue()/importantFamiliesInfoVo.getTotalFamalies()*100)));
		 }else{
			    importantFamiliesInfoVo.setBelow3perc(0d);
			    importantFamiliesInfoVo.setBetwn4to6perc(0d);
			    importantFamiliesInfoVo.setBetwn7to10perc(0d);
			    importantFamiliesInfoVo.setAbove10perc(0d); 
		 }
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterHouseInfoVO> getVoterHouseInfoDetails(Long userId,Long id, Long publicationDateId,String checkedEle , String buildType,String requestFor)
	{
		List voters = null;
		List<Long> voterIdsList = new ArrayList<Long>(0);
		List<SelectOptionVO> casteList = new ArrayList<SelectOptionVO>();
		SelectOptionVO caste = null;
		Map<Long,VoterVO> votersMap = new HashMap<Long, VoterVO>();
		if(checkedEle.equalsIgnoreCase("panchayat"))
		{
			
			
			if(buildType.equalsIgnoreCase("hamlet"))
			{
			//	List<Long> hamlets = userVoterDetailsDAO.getUserHamletsByPanchayatId(userId ,id );
				
			//	List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletIds(userId , hamlets);
				
				//voters = boothPublicationVoterDAO.getVoterDetailsByVoterIds(voterIds,publicationDateId);
				voters =	 boothPublicationVoterDAO.getVoterDetailsByPanchayatIds(id,publicationDateId,userId);
			}
			else				
			 voters = boothPublicationVoterDAO.findFamiliesVotersInfoForPanchayat(id,publicationDateId);
		}
		if(checkedEle.equalsIgnoreCase("pollingstation"))
		{
			if(requestFor.equalsIgnoreCase("hamletBooth"))
				voters = boothPublicationVoterDAO.findFamiliesVotersInfoForBoothForUser(id,publicationDateId,userId);
			else
			    voters = boothPublicationVoterDAO.findFamiliesVotersInfoForBooth(id,publicationDateId);
		}
		if(checkedEle.equalsIgnoreCase("hamlet") || checkedEle.equalsIgnoreCase(IConstants.CUSTOMWARD) )
		{
			String queryCond ="";
			if(checkedEle.equalsIgnoreCase(IConstants.HAMLET)){
				queryCond =" model1.hamlet.hamletId = :id ";
				
			}
				else if(checkedEle.equalsIgnoreCase(IConstants.CUSTOMWARD)){
					queryCond =" model1.ward.constituencyId = :id ";	
					
				}
			if(requestFor.equalsIgnoreCase("booth")){
				
			voters = boothPublicationVoterDAO.getVoterDetailsByHamletForUser(userId,id,publicationDateId,queryCond);
	      /*  List<?> boothsList =(List<Long>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(id, userId ,checkedEle);
			
				
				if(boothsList == null || boothsList.size()==0)
					 return null;
				
				 voters =        userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(publicationDateId,boothsList);*/
			}
			else
		//	List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletForLocalities(userId , id);			
			voters = boothPublicationVoterDAO.getVoterDetailsByHamletId(id,publicationDateId,userId,queryCond);
			
		}
		Map<Long,Map<String, List<VoterVO>>> boothMap = new HashMap<Long,Map<String, List<VoterVO>>>();
		Map<String, List<VoterVO>> voterByHouseNoMap = null;
		List<VoterHouseInfoVO> voterHouseInfoVOs = new ArrayList<VoterHouseInfoVO>();
		VoterHouseInfoVO voterHouseInfoVO = null;
		List<VoterVO> voterVOs = null;
		VoterVO voterVO = null;
		String houseNo = "";
		
		if(voters != null && voters.size() > 0)
		for(Object[] voter : (List<Object[]>)voters)
		voterIdsList.add((Long)voter[4]);
		
		if(voterIdsList.size() > 0)
		{
			List<Long> subList = null;
			int startIndex = 0;
			int nextIndex = 1000;
			for(;;)
			{
				try{
				if(startIndex >= voterIdsList.size()-1)
					break;
				if(nextIndex >= voterIdsList.size())
					nextIndex = voterIdsList.size() - 1;
				
				subList = voterIdsList.subList(startIndex,nextIndex);
				
			    List<Object[]> votercastList = userVoterDetailsDAO.getCasteByVoterId(userId,subList);
			    if(votercastList!= null)
		        for(Object[] params : votercastList)
				{
		        	if(params[1].toString() != null && params[1].toString()!= "")
					caste = new SelectOptionVO((Long)params[0],params[1].toString());	
					casteList.add(caste);
				}
			    startIndex = nextIndex;
			    nextIndex = nextIndex + 1000;
				}catch (Exception e) {
					log.error(e);
				}
			}
		}
		
		if(voters != null && voters.size() > 0){
		for(Object[] voter : (List<Object[]>)voters){
			houseNo = voter[1].toString();
			voterVO = new VoterVO();
			voterVO.setFirstName(voter[0].toString());
			voterVO.setAge((Long)voter[2]);
			//voterVO.setCast(voter[3] != null ? voter[3].toString() : "");
			voterVO.setBoothNo((Long)voter[3]);
			voterVO.setVoterId(voter[4].toString());
			voterVO.setGender(voter[5].toString());
			voterVO.setAge(voter[6] != null ? (Long)voter[6]:18l);
			voterVO.setBoothName(voter[7].toString());
			if(!requestFor.equalsIgnoreCase("booth")){
				if( checkedEle.equalsIgnoreCase("panchayat") && buildType.equalsIgnoreCase("hamlet"))
					voterVO.setHamlet(voter[8].toString());
				if( checkedEle.equalsIgnoreCase("hamlet")  || checkedEle.equalsIgnoreCase("customWard") )
					voterVO.setLocalArea(voter[8].toString());
			}/*else
				voterVO.setHamlet("HamletName");*/
			if(requestFor.equalsIgnoreCase("hamletBooth"))
				voterVO.setHamlet(voter[8].toString());
				voterVO.setCast(getCasteNameByVoterID(casteList,(Long)voter[4]));
			voterByHouseNoMap = boothMap.get((Long)voter[3]);
			if( voterByHouseNoMap == null){
				voterByHouseNoMap = new HashMap<String, List<VoterVO>>();
				boothMap.put((Long)voter[3], voterByHouseNoMap);
			}
			voterVOs = voterByHouseNoMap.get(houseNo);
			if(voterVOs ==null){
				voterVOs = new ArrayList<VoterVO>();
				voterByHouseNoMap.put(houseNo, voterVOs);
			}
			voterVOs.add(voterVO);
			votersMap.put((Long)voter[4], voterVO);
			//voterByHouseNoMap.put(houseNo, voterVOs);
			
		}
		List<Long> influencingPeopleList = new ArrayList<Long>(0);
		
		
		int fromIndex = 0;
		int toIndex = 1000;
		if(voterIdsList.size() >= 1000){
		while(fromIndex <= toIndex)
		{
			List<Long> iPList = influencingPeopleDAO.findInfluencingPeopleDetails(voterIdsList.subList(fromIndex,toIndex),userId);
			influencingPeopleList.addAll(iPList);
			fromIndex += 1000;
			toIndex += 1000;
			if(toIndex >= voterIdsList.size())
				toIndex = voterIdsList.size();
		}
		}
		else
		{
			influencingPeopleList = influencingPeopleDAO.findInfluencingPeopleDetails(voterIdsList,userId);
		}
		
		if(influencingPeopleList != null && influencingPeopleList.size() > 0)
		{
			for (Long influencingPeople : influencingPeopleList) {
				voterVO = votersMap.get(influencingPeople);
				if(voterVO != null)
				{
					voterVO.setInfluencePerson(true);
				}
			}
		}
		
		fromIndex = 0;
		toIndex = 999;
		List<Long> cadrePeopleList = new ArrayList<Long>(0);
		if(voterIdsList.size() >= 1000){
		while(fromIndex <= toIndex)
		{
			List<Long> cPList = cadreDAO.findCadrePeopleDetails(voterIdsList.subList(fromIndex,toIndex),userId);
			cadrePeopleList.addAll(cPList);
			fromIndex += 1000;
			toIndex += 1000;
			if(toIndex >= voterIdsList.size())
				toIndex = voterIdsList.size()-1;
		}
		}else{
			cadrePeopleList = cadreDAO.findCadrePeopleDetails(voterIdsList,userId);
		}
		if(cadrePeopleList != null && cadrePeopleList.size() > 0)
		{
			for (Long cadrePeople : cadrePeopleList) 
			{
				voterVO = votersMap.get(cadrePeople);
				if(voterVO != null)
					voterVO.setIsCadrePerson(true);
			}
		}
		
		fromIndex = 0;
		toIndex = 999;
		List<Long> candidatePeopleList = new ArrayList<Long>(0);
		if(voterIdsList.size() >= 1000){
		while(fromIndex <= toIndex)
		{
			List<Long> cdPList = candidateDAO.findCandidatePeopleDetails(voterIdsList.subList(fromIndex, toIndex));
			candidatePeopleList.addAll(cdPList);
			fromIndex += 1000;
			toIndex += 1000;
			if(toIndex >= voterIdsList.size())
				toIndex = voterIdsList.size()-1;
			
		}
		}else{
			candidatePeopleList = candidateDAO.findCandidatePeopleDetails(voterIdsList);
		}
		if(candidatePeopleList != null && candidatePeopleList.size() > 0)
		{
			for (Long candidatePeople : candidatePeopleList) {
				voterVO = votersMap.get(candidatePeople);
				if(voterVO != null)
				{
					voterVO.setIsPoliticion(true);
				}
			}
		}
		}
		Set<Long> keys = boothMap.keySet();
		for(Long key:keys){
			voterByHouseNoMap = boothMap.get(key);
		for(Map.Entry<String, List<VoterVO>> entry:voterByHouseNoMap.entrySet()){
			voterHouseInfoVO = new VoterHouseInfoVO();
			voterVOs = entry.getValue();
			if(voterVOs.size() == 0)
				continue;
			Collections.sort(voterVOs,sortData);
			voterHouseInfoVO.setBoothId(voterVOs.get(0).getBoothNo());
			//voterHouseInfoVO.setBoothName("Booth - "+voterVOs.get(0).getBoothName());
			voterHouseInfoVO.setBoothName(voterVOs.get(0).getBoothName());
			voterHouseInfoVO.setHouseNo(entry.getKey());
			voterHouseInfoVO.setNumberOfPeople(voterVOs.size());
			voterHouseInfoVO.setCast(voterVOs.get(voterVOs.size()-1).getCast() != null ? 
					voterVOs.get(voterVOs.size()-1).getCast() : "N/A");
			voterHouseInfoVO.setElderGender(voterVOs.get(voterVOs.size()-1).getGender());
			voterHouseInfoVO.setElderAge(voterVOs.get(voterVOs.size()-1).getAge());
			voterHouseInfoVO.setElder(voterVOs.get(voterVOs.size()-1).getFirstName());
			List<String> names = new ArrayList<String>();
			List<String> names1 = new ArrayList<String>();
			List<String> names2 = new ArrayList<String>();
			for(int i=0;i<voterVOs.size();i++){
			if(voterVOs.get(i).getIsInfluencePerson() != null && voterVOs.get(i).getIsInfluencePerson()){
				voterHouseInfoVO.setIsInfluencePerson(voterVOs.get(i).getIsInfluencePerson()); 
				names.add(voterVOs.get(i).getFirstName());
				String partyName = influencingPeopleDAO.getPartyIdUsingVoterId(new Long(voterVOs.get(i).getVoterId()));
				voterHouseInfoVO.setInfluenceNames(names);
				voterHouseInfoVO.setInfluencePartyName(partyName);
			}
			if(voterVOs.get(i).getIsCadrePerson()!= null &&  voterVOs.get(i).getIsCadrePerson()){
				voterHouseInfoVO.setIsCadrePerson(voterVOs.get(i).getIsCadrePerson());
				names1.add(voterVOs.get(i).getFirstName());
				voterHouseInfoVO.setCadreNames(names1);
			}
			if(voterVOs.get(i).getIsPoliticion() != null && voterVOs.get(i).getIsPoliticion()){
				voterHouseInfoVO.setIsPoliticion(voterVOs.get(i).getIsPoliticion());
				names2.add(voterVOs.get(i).getFirstName());
				voterHouseInfoVO.setPoliticianNames(names2);
			}
			}
			if( checkedEle.equalsIgnoreCase("panchayat") && buildType.equalsIgnoreCase("hamlet"))
				voterHouseInfoVO.setHamletName(voterVOs.get(voterVOs.size()-1).getHamlet());
			if( checkedEle.equalsIgnoreCase(IConstants.HAMLET) || checkedEle.equalsIgnoreCase(IConstants.CUSTOMWARD) )
				voterHouseInfoVO.setLocalAreaName(voterVOs.get(voterVOs.size()-1).getLocalArea());
			VoterVO younger = null;
			if( requestFor.equalsIgnoreCase("hamletBooth") )
				voterHouseInfoVO.setHamletName(voterVOs.get(voterVOs.size()-1).getHamlet());
			
			
			younger = voterVOs.get(0);
			
			if(!younger.getGender().equalsIgnoreCase(IConstants.MALE))
			{
				for(int i=1;i<(voterVOs.size()-1);i++)
					if(voterVOs.get(i).getGender().equalsIgnoreCase(IConstants.MALE)){
							younger = voterVOs.get(i);
							break;
					}
			}
			voterHouseInfoVO.setYounger(younger.getFirstName());
			voterHouseInfoVO.setYoungerAge(younger.getAge());
			voterHouseInfoVO.setYoungerGender(younger.getGender());
			
			voterHouseInfoVOs.add(voterHouseInfoVO);
		}
		}
		
	/*	if(voterHouseInfoVOs.size() > 0)
			voterHouseInfoVOs.get(0).setTotalHousesCount(totalRecords);*/
		return voterHouseInfoVOs;
	}
	
	public String getCasteNameByVoterID(List<SelectOptionVO> casteList,Long voterId)
	{
		String casteName = null;
		try{
			if(casteList != null && casteList.size() > 0)
			for(SelectOptionVO params : casteList)
				if(params.getId().equals(voterId))
					casteName = params.getName().toString();
			return casteName;
		}catch (Exception e) {
			log.error("Exception Occured in getCasteNameByVoterID() Method, Exception is - "+e);
			return casteName;
		}
	}
	
	public static Comparator<VoterVO> sortData = new Comparator<VoterVO>()
		    {
		   
		        public int compare(VoterVO voterVO1, VoterVO voterVO2)
		        {
		            return (voterVO1.getAge().intValue()) - (voterVO2.getAge().intValue());
		        }
		    };
		    
		    public void getImpFamilesForPanchayat(Long id,Long publicationDateId,ImportantFamiliesInfoVo importantFamiliesInfoVo,boolean isPartial,Long userId,String type){
                List<Object[]>  impFamilesList = null;
				
				/*if(name.equalsIgnoreCase("constituency")){
					impFamilesList = boothPublicationVoterDAO.findImpFamilesBasedOnConstituencyId(id, publicationDateId);
				}*/
				//else if(name.equalsIgnoreCase("panchayat")){
				if(!isPartial)
					impFamilesList = boothPublicationVoterDAO.getImpFamilesForPanchayatByPublicationId(id,publicationDateId,null);
				else 
					impFamilesList = boothPublicationVoterDAO.getImpFamilesForPartialPanchayatByPublicationId(id,publicationDateId,userId);
				//}
				Long below3 = 0l;
				Long between4To6 = 0l;
				Long between7To10 = 0l;
				Long above10 = 0l;
				Long count = 0l;
				Long above10Count = 0l;
				Long between7T10Count = 0l;
				Long between4To6Count = 0l;
				Long below3Count = 0l;
				for (Object[] impFamiles : impFamilesList) {
					count = (Long) impFamiles[0];	
					if(count.longValue() > 10){
						above10 = above10+1;
						above10Count = count + above10Count;
					}
					else if(count.longValue() >=7 && count.longValue() <= 10){
						between7To10 = between7To10+1;
						between7T10Count = count + between7T10Count;
					}
					else if(count.longValue() >=4 && count.longValue() <= 6){
						between4To6 = between4To6 + 1;
						between4To6Count = count + between4To6Count;	
					}
					else{
						below3 = below3 + 1;
						below3Count = count + below3Count;
					}
					
					/*if(count.longValue() <=3)
						  lessThan3Count++;
					  else if(familyCount.longValue() >=4 && familyCount.longValue() <= 6)
						  between4To6Count++;
					  else if(familyCount.longValue() >=7 && familyCount.longValue() <= 10)
						  between7To10Count++;
					  else  if(familyCount.longValue() >10)
						  above10Count++;*/
				}
				
				importantFamiliesInfoVo.setAbove10(above10);
				importantFamiliesInfoVo.setAbove10Popul(above10Count);
				importantFamiliesInfoVo.setBetwn7to10(between7To10);
				importantFamiliesInfoVo.setBetwn7to10Popul(between7T10Count);
				importantFamiliesInfoVo.setBetwn4to6Popul(between4To6Count);
				importantFamiliesInfoVo.setBetwn4to6(between4To6);
				importantFamiliesInfoVo.setBelow3(below3);
				importantFamiliesInfoVo.setBelow3Popul(below3Count);
				importantFamiliesInfoVo.setTotalFamalies(above10+between7To10+between4To6+below3);
				importantFamiliesInfoVo.setTotalVoters(above10Count+between7T10Count+between4To6Count+below3Count);
				if(type.equalsIgnoreCase("family"))
				{
					calculatePercentageForFamiles(importantFamiliesInfoVo);
				}
				else
				{
					calculatePercentage(importantFamiliesInfoVo);
				}
				
			}
		    
		    
public String roundTo2DigitsFloatValue(Float number){
	
	NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
	f.setMaximumFractionDigits(2);  
	f.setMinimumFractionDigits(2);
	
	return f.format(number);
	
}


public VoterHouseInfoVO getVoterPersonalDetailsByVoterId(Long voterId,Long userId){
	
 VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
 try{
	   SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
	   defaultSelectOptionVO.setId(0l);
	   defaultSelectOptionVO.setName("Select");
	
	   getVoterBasicInfo(voterHouseInfoVO,voterId,userId);
	   
	   getPartiesAndCastsInVotersState(voterHouseInfoVO,voterId,userId,defaultSelectOptionVO);
	   
	   voterSelectedCastAndPartyDetails(voterHouseInfoVO,voterId,userId);
	   
    List<Object[]> userCategoryValuesList = userVoterCategoryDAO.getCategoryValuesList(userId);
	//List<UserVoterDetails> userVoterDetails=userVoterDetailsDAO.getUserVoterDetails(voterId, userId);
	List<VoterHouseInfoVO> categoriesList = new ArrayList<VoterHouseInfoVO>();
	getVoterSelectedCategoryValues(userCategoryValuesList,defaultSelectOptionVO,userId,voterId,categoriesList);
	voterHouseInfoVO.setCategoriesList(categoriesList);
  }catch(Exception e){
	  log.error("Exception rised in getVoterPersonalDetailsByVoterId",e);
  }
	return voterHouseInfoVO;
	}

  public VoterHouseInfoVO getVoterPersonalDetailsList(List<VoterHouseInfoVO> voterIds,Long userId){
	  VoterHouseInfoVO votersHouseInfoVO = new VoterHouseInfoVO();
	 
	  
	  Map<Long,Map<String, List<VoterHouseInfoVO>>> boothMap = new HashMap<Long,Map<String, List<VoterHouseInfoVO>>>();
		Map<String, List<VoterHouseInfoVO>> voterByHouseNoMap = null;
		
		List<VoterHouseInfoVO> voterVOs = null;
		
	  try{
		  if(voterIds != null && voterIds.size() >0){
			  
			
			
	 	    SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
	 	    defaultSelectOptionVO.setId(0l);
	 	    defaultSelectOptionVO.setName("Select");
	 	
	 	    getPartiesAndCastsInVotersState(votersHouseInfoVO,voterIds.get(0).getVoterId(),userId,defaultSelectOptionVO);
	 	   
	 	    List<Object[]> userCategoryValuesList = userVoterCategoryDAO.getCategoryValuesList(userId);
	 	  
	 	   VoterHouseInfoVO voterHouseInfoVO = null;
	 	   
	 	    for(VoterHouseInfoVO voter : voterIds){
	 	    	
	 	     voterHouseInfoVO = new VoterHouseInfoVO();
	 	     
	 	     getVoterBasicInfo(voterHouseInfoVO,voter.getVoterId(),userId);
	 	   
	 	     
	 	    voterByHouseNoMap = boothMap.get(voter.getBoothId());
			if( voterByHouseNoMap == null){
				voterByHouseNoMap = new HashMap<String, List<VoterHouseInfoVO>>();
				boothMap.put(voter.getBoothId(), voterByHouseNoMap);
			}
			voterVOs = voterByHouseNoMap.get(voterHouseInfoVO.getHouseNo());
			if(voterVOs ==null){
				voterVOs = new ArrayList<VoterHouseInfoVO>();
				voterByHouseNoMap.put(voterHouseInfoVO.getHouseNo(), voterVOs);
			}
			voterVOs.add(voterHouseInfoVO);
	 	     
	 	     voterSelectedCastAndPartyDetails(voterHouseInfoVO,voter.getVoterId(),userId);
	 	   	       
	 	     List<VoterHouseInfoVO> categoriesList = new ArrayList<VoterHouseInfoVO>();
	 	   
	 	     getVoterSelectedCategoryValues(userCategoryValuesList,defaultSelectOptionVO,userId,voter.getVoterId(),categoriesList);
	 	   
	 	     voterHouseInfoVO.setCategoriesList(categoriesList);
	 	     
	 	     
	 	   }
	 	   List<VoterHouseInfoVO> boothsList = new ArrayList<VoterHouseInfoVO>();
	 	  List<VoterHouseInfoVO> familiesList = null;
	 	    for(Long key:boothMap.keySet()){
	 	    	VoterHouseInfoVO booth = new VoterHouseInfoVO();
	 	    	boothsList.add(booth);
	 	    	booth.setBoothId(key);
	 	    	VoterHouseInfoVO boothInfo = getBoothDetailsForVoter(key);
	 	    	booth.setBoothName(boothInfo.getBoothName());
	 	    	booth.setVilliageCovered(boothInfo.getVilliageCovered());
	 	    	booth.setPanchayatName(boothInfo.getPanchayatName());
	 	    	booth.setMobileNo(boothInfo.getMobileNo()!=null ? boothInfo.getMobileNo():" ");
	 	    	Map<String, List<VoterHouseInfoVO>> families = boothMap.get(key);
	 	    	familiesList = new ArrayList<VoterHouseInfoVO>();
	 	    	for(String familyKey:families.keySet()){
	 	    		VoterHouseInfoVO family = new VoterHouseInfoVO();
	 	    		familiesList.add(family);
	 	    		family.setHouseNo(familyKey);
	 	    		family.setVotersList(families.get(familyKey));
	 	    	}
	 	    	booth.setFamiliesList(familiesList);
	 	    }
	 	    
	 	   votersHouseInfoVO.setBoothsList(boothsList);
		  }
	   }catch(Exception e){
	 	  log.error("Exception rised in getVoterPersonalDetailsList ",e);
	   }
	 	return votersHouseInfoVO;
	  
  }
  
  public void getVoterBasicInfo(VoterHouseInfoVO voterHouseInfoVO,Long voterId,Long userId){
	  
	List<Long> voterIdsList = new ArrayList<Long>(0);
	Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
	 voterIdsList.add(voterId);
	 
	 List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIdsList, userId);
	  if(list != null && list.size() > 0)
		for(Object[] params:list)
		 mobileNosMap.put((Long)params[0], params[1]!= null?params[1].toString():"N/A");
	
	List<Voter> voterDetails = voterDAO.getVoterPersonalDetailsByVoterId(voterId);
	if(voterDetails != null && voterDetails.size() >0)
	{
		Voter voterInfo = voterDetails.get(0);
	 voterHouseInfoVO.setName(voterInfo.getName());
	 voterHouseInfoVO.setVoterId(voterInfo.getVoterId());
	 voterHouseInfoVO.setGender(voterInfo.getGender());
	 voterHouseInfoVO.setAge(voterInfo.getAge());
	 voterHouseInfoVO.setHouseNo(voterInfo.getHouseNo());
	 voterHouseInfoVO.setGaurdian(voterInfo.getRelativeName());
	 voterHouseInfoVO.setRelationship(voterInfo.getRelationshipType());
	 voterHouseInfoVO.setVoterIdCardNo(voterInfo.getVoterIDCardNo());
	
	 if(mobileNosMap.get(voterInfo.getVoterId()) != null)	
	  voterHouseInfoVO.setMobileNo(mobileNosMap.get(voterInfo.getVoterId()));
	 else
		 voterHouseInfoVO.setMobileNo("N/A");
	 
	 //voterHouseInfoVO.setCast(voterInfo.getCast());
	 
	 //List<SelectOptionVO> casteList = socialService.getAllCasteDetails();
	 
	
	 //voterHouseInfoVO.setCastCategory(voterInfo.getCastCatagery());
	 //voterHouseInfoVO.setUserId(userId);
	}
	
  }
  
  public void getVoterBasicInfo1(VoterHouseInfoVO voterHouseInfoVO,Long voterId,Long publicationDateId,Long userId){
		//List<Voter> voterDetails = voterDAO.getVoterPersonalDetailsByVoterId(voterId);
		  List<Object[]> voterDetails = boothPublicationVoterDAO.getVoterPersonalDetailsByVoterIdAndPuclicationId(voterId, publicationDateId);
		
		  List<Long> voterIdsList = new ArrayList<Long>(0);
		  Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
		  voterIdsList.add(voterId);
		  List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIdsList, userId);
		  if(list != null && list.size() > 0)
		  	for(Object[] params:list)
		  	 mobileNosMap.put((Long)params[0], params[1]!= null?params[1].toString():"N/A");
		  				
		  
		if(voterDetails != null && voterDetails.size() >0)
		{
			Voter voterInfo =(Voter) voterDetails.get(0)[0];
			Long serialNo = (Long) voterDetails.get(0)[1];
			
		 voterHouseInfoVO.setName(voterInfo.getName());
		 voterHouseInfoVO.setVoterId(voterInfo.getVoterId());
		 voterHouseInfoVO.setGender(voterInfo.getGender());
		 voterHouseInfoVO.setAge(voterInfo.getAge());
		 voterHouseInfoVO.setHouseNo(voterInfo.getHouseNo());
		 voterHouseInfoVO.setGaurdian(voterInfo.getRelativeName());
		 voterHouseInfoVO.setRelationship(voterInfo.getRelationshipType());
		 voterHouseInfoVO.setFromSno(serialNo);
		 if(mobileNosMap.get(voterInfo.getVoterId()) != null)
		  voterHouseInfoVO.setMobileNo(mobileNosMap.get(voterInfo.getVoterId()));
		 else
		  voterHouseInfoVO.setMobileNo("N/A");
		 
		}
		
	  }
  
  public void getCustomVoterGroups(VoterHouseInfoVO voterHouseInfoVO,Long voterId,Long userId,SelectOptionVO defaultSelectOptionVO ,Long constituencyId)
  {
	  try
	  {
		  List<Long> locationValuesList = new ArrayList<Long>();	
		  List<Object[]> customGroups = null;
           
		  if(voterHouseInfoVO.isMandal()){
			  
			  				  
			  locationValuesList.add(voterHouseInfoVO.getGroupLocationValue());				 
			  
			  customGroups =   customVoterGroupDAO.getCustomVoterGroupsByLocationValueAndAreaTypeAndConstituencyId(userId, locationValuesList,IConstants.AREA_TYPE_RURAL,constituencyId);

		  }else if(voterHouseInfoVO.isMuncipality()){
			  
			  Long lid = (Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(voterHouseInfoVO.getGroupLocationValue()).get(0); 
			  locationValuesList.add(lid);
			  //locationValuesList.add(voterHouseInfoVO.getGroupLocationValue());	
			  customGroups =   customVoterGroupDAO.getCustomVoterGroupsByLocationValueAndAreaTypeAndConstituencyId(userId, locationValuesList,IConstants.AREA_TYPE_URBAN,constituencyId);		  
			  
		  }else if(voterHouseInfoVO.isConstituency()){
			  List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(voterHouseInfoVO.getConstituencyId());
				Long delimitationConstituencyID = delimitationConstituency.get(0).getDelimitationConstituencyID();
				List<Tehsil> mandals = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);
				if(mandals != null && mandals.size() > 0)
				{						SelectOptionVO selectOptionVO = null;
					for (Tehsil tehsil : mandals)						
						locationValuesList.add(tehsil.getTehsilId());
					
				}
		   customGroups =   customVoterGroupDAO.getCustomVoterGroupsByLocationValueAndAreaTypeAndConstituencyId(userId, locationValuesList,IConstants.AREA_TYPE_RURAL,constituencyId);
		  }
		  
		  
		  //customGroups =   customVoterGroupDAO.getCustomVoterGroupsByLocationValue(userId, locationValuesList);
				
		List<SelectOptionVO> customGroupsList = new ArrayList<SelectOptionVO>();		 
		SelectOptionVO defaultVo = new SelectOptionVO();
		 defaultVo.setId(0L);
		 defaultVo.setName("Select");
		 customGroupsList.add(defaultVo);
		 
		 for(Object[] obj:customGroups)
		 {
			 SelectOptionVO vo = new SelectOptionVO();
			 vo.setId((Long)obj[0]);
			 vo.setName(obj[1].toString());
			 customGroupsList.add(vo);
			 
		 }
		 
		 voterHouseInfoVO.setCustomGroups(customGroupsList);
		  
	  }catch(Exception e)
	  {
		  
		  
	  }
	  
  }
  
public void getPartiesAndCastsInVotersState(VoterHouseInfoVO voterHouseInfoVO,Long voterId,Long userId,SelectOptionVO defaultSelectOptionVO){
	  
	  List<Long> stateIdsList = boothPublicationVoterDAO.getVoterStateId(voterId);
	    if(stateIdsList != null && stateIdsList.size() > 0 ){
		  List<SelectOptionVO> partiesList = staticDataService.getStaticPartiesListForAState(stateIdsList.get(0));
		  if(partiesList != null){
			  partiesList.add(0, defaultSelectOptionVO);
		     voterHouseInfoVO.setParties(partiesList);
		  }else{
			  List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>();
			  parties.add(defaultSelectOptionVO);
			  voterHouseInfoVO.setParties(parties); ;
		  }
		   List<SelectOptionVO> castsVo = new ArrayList<SelectOptionVO>();
		   SelectOptionVO selectOptionVO = null;
		     
		     castsVo.add(defaultSelectOptionVO);
		   //List<Object[]> castsList = casteStateDAO.getAllCasteDetailsForVoters(stateIdsList.get(0));
		     List<Object[]> castsList = casteStateDAO.getAllCastesForVoters(stateIdsList.get(0), userId);
		   for(Object[] casts:castsList){
			   selectOptionVO = new SelectOptionVO();
			   selectOptionVO.setId((Long)casts[0]);
			   selectOptionVO.setName(casts[1]!=null?casts[1].toString():"");
			   castsVo.add(selectOptionVO);
		   }
		   voterHouseInfoVO.setCasteGroupNameList(castsVo);
	    }else{
	    	
	    	List<SelectOptionVO> partiesList = new ArrayList<SelectOptionVO>();
	    	SelectOptionVO  party = new SelectOptionVO();
	    	party.setId(0l);
	    	party.setName("Select");
	    	partiesList.add(party);
	    	voterHouseInfoVO.setParties(partiesList);
	    	voterHouseInfoVO.setCasteGroupNameList(partiesList);
	    }
  }


  public void voterSelectedCastAndPartyDetails(VoterHouseInfoVO voterHouseInfoVO,Long voterId,Long userId){
	  List<UserVoterDetails> userVoterDetailsList = userVoterDetailsDAO.getUserVoterDetails(voterId,userId);
		if(userVoterDetailsList != null && userVoterDetailsList.size() >0){
			UserVoterDetails userVoterDetails = userVoterDetailsList.get(0);
			if(userVoterDetails.getParty() != null){
				voterHouseInfoVO.setPartyId(userVoterDetails.getParty().getPartyId());
			}else{
				voterHouseInfoVO.setPartyId(0l);
			}
			if(userVoterDetails.getCasteState() != null){
				voterHouseInfoVO.setCasteStateId(userVoterDetails.getCasteState().getCasteStateId());
				voterHouseInfoVO.setCasteName(userVoterDetails.getCasteState().getCaste().getCasteName()!=null?userVoterDetails.getCasteState().getCaste().getCasteName():"");
			}
			else
				voterHouseInfoVO.setCasteStateId(0l);
			if(userVoterDetails.getHamlet() !=null)
			{
				voterHouseInfoVO.setHamletId(userVoterDetails.getHamlet().getHamletId());
			}
			
			if(userVoterDetails.getWard() != null)
			{
				voterHouseInfoVO.setHamletId(userVoterDetails.getWard().getConstituencyId());
				if(userVoterDetails.getWard().getLocalElectionBody()!= null)
				{
					List<SelectOptionVO> localities =   getLocalitiesForWards(userVoterDetails.getWard().getLocalElectionBody().getLocalElectionBodyId(),userId," model.localElectionBody.localElectionBodyId =:id " );					
				   
				   voterHouseInfoVO.setSubLocalities(localities);
				}
				
			}
			
			if(userVoterDetails.getLocality() != null)
				voterHouseInfoVO.setSubLocalityId(userVoterDetails.getLocality().getLocalityId());
			
			
		}else{
			
			voterHouseInfoVO.setPartyId(0l);
			voterHouseInfoVO.setCasteStateId(0l);
		}
  }
  
  public void getVoterSelectedCategoryValues(List<Object[]> userCategoryValuesList,SelectOptionVO defaultSelectOptionVO,Long userId,Long voterId,List<VoterHouseInfoVO> categoriesList){
	  VoterHouseInfoVO category = null;
		for(Object[] userCategoryValue : userCategoryValuesList)
		{ 
			 category = new VoterHouseInfoVO();
			 getAllCategoryOptions(userCategoryValue,category,defaultSelectOptionVO,userId);
			 List<Long> idsList = voterCategoryValueDAO.getVoterCategoryValue(userId,voterId,(Long)userCategoryValue[0]);
			 if(idsList != null && idsList.size() > 0 && idsList.get(0) != null){
				 category.setCategoryValuesId(idsList.get(0));
			 }
			 
			 categoriesList.add(category);	 
		}	  
  }
  
  public void getAllCategoryOptions(Object[] userCategoryValue,VoterHouseInfoVO category,SelectOptionVO defaultSelectOptionVO,Long userId){
	  
	     category.setUserCategoryValueId((Long)userCategoryValue[0]);
		 category.setUserCategoryValueName(userCategoryValue[1]!=null?userCategoryValue[1].toString():"");
		 List<Object[]> categoryValuesList =  userVoterCategoryValueDAO.getCategoryValuesByUserIdCategId(userId,(Long)userCategoryValue[0]);
		 List<SelectOptionVO> categoryOptionsList = new ArrayList<SelectOptionVO>();
		 categoryOptionsList.add(defaultSelectOptionVO);
		 SelectOptionVO categoryOption = null;
		 for(Object[] categoryValue : categoryValuesList){
			 categoryOption = new SelectOptionVO();
			 categoryOption.setId((Long)categoryValue[0]);
			 categoryOption.setName(categoryValue[1]!=null?categoryValue[1].toString():"");
			 categoryOptionsList.add(categoryOption);
	     }
		 category.setCategory(categoryOptionsList);
	  
  }
public void updateVoterDetails(VoterHouseInfoVO voterHouseInfoVO,String partyCast , boolean groupPresent){
	
	   if(voterHouseInfoVO != null){
			try{
				if(voterHouseInfoVO.getVoterId() == null || voterHouseInfoVO.getUserId() == null )
					return ;
							
				Voter voter =  voterDAO.get(voterHouseInfoVO.getVoterId());
				User user =  userDAO.get(voterHouseInfoVO.getUserId());
				/*if(voterHouseInfoVO.isMobileNoPresent())
				{
				String mobileNo = voterHouseInfoVO.getMobileNo();
				userVoterDetailsDAO.updateVoterMobileNo(voterHouseInfoVO.getMobileNo(),voterHouseInfoVO.getVoterId());
				voterDAO.updateVoterMobileNo(voterHouseInfoVO.getMobileNo(),voterHouseInfoVO.getVoterId());
				}*/
		if(voterHouseInfoVO.getCategoriesList() != null && voterHouseInfoVO.getCategoriesList().size() >0){
			for(VoterHouseInfoVO category : voterHouseInfoVO.getCategoriesList()){
				
				  Long userCategoryValueId = category.getUserCategoryValueId();
				  UserVoterCategoryValue userVoterCategoryValue = null;
				    if(category.getCategoryValuesId() != null && category.getCategoryValuesId().longValue() >0l)
				   userVoterCategoryValue = userVoterCategoryValueDAO.get(category.getCategoryValuesId());
				   List<VoterCategoryValue> categoryValuesIds = voterCategoryValueDAO.getVoterCategoryValues(voterHouseInfoVO.getUserId(),voterHouseInfoVO.getVoterId(),userCategoryValueId);
				   VoterCategoryValue voterCategoryVal = null;
				   if(categoryValuesIds != null && categoryValuesIds.size() > 0 && categoryValuesIds.get(0) != null){
					   voterCategoryVal = categoryValuesIds.get(0);
					   voterCategoryVal.setUser(user);
						  voterCategoryVal.setVoter(voter);
						  voterCategoryVal.setUserVoterCategoryValue(userVoterCategoryValue);
						  voterCategoryValueDAO.save(voterCategoryVal);
				  }else if(userVoterCategoryValue != null){
					  voterCategoryVal = new VoterCategoryValue();
					  voterCategoryVal.setUser(user);
					  voterCategoryVal.setVoter(voter);
					  voterCategoryVal.setUserVoterCategoryValue(userVoterCategoryValue);
					  voterCategoryValueDAO.save(voterCategoryVal);
				  }
				      
				
			}
		}
		if(partyCast != null){
			List<UserVoterDetails> userVoterDetailsList = userVoterDetailsDAO.getUserVoterDetails(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId());
			Long partyId = voterHouseInfoVO.getPartyId();
			Long casteStateId = voterHouseInfoVO.getCasteStateId();
			Long localitityId =voterHouseInfoVO.getLocalitityId();
			Long hamletId =voterHouseInfoVO.getHamletId();
			String mobileNmbr = voterHouseInfoVO.getMobileNo();			
			if(partyId != null &&  partyId.longValue() == 0l)
				partyId = null;
			if(casteStateId != null &&  casteStateId.longValue() == 0l)
				casteStateId = null;
			if(hamletId != null &&  hamletId.longValue() == 0l)
				hamletId = null;
			if(mobileNmbr != null && mobileNmbr.trim().length()==0)
				mobileNmbr = null;
			if(userVoterDetailsList != null && userVoterDetailsList.size() > 0){
			   if(partyCast.equalsIgnoreCase("all")){
				   
				   if(voterHouseInfoVO.getSelType().equalsIgnoreCase("muncipality"))
					 userVoterDetailsDAO.updateUserVoterDetailsWithWard(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),partyId,casteStateId,localitityId,hamletId,mobileNmbr);
				   else
				     userVoterDetailsDAO.updateUserVoterDetails(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),partyId,casteStateId,localitityId,hamletId,mobileNmbr);
			   }
		   else if(partyCast.equalsIgnoreCase("partyCast"))
		       userVoterDetailsDAO.updateUserVoterDetails(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),partyId,casteStateId);
		   else if(partyCast.equalsIgnoreCase("partyLocality")){
			   if(voterHouseInfoVO.getSelType().equalsIgnoreCase("muncipality"))
				   userVoterDetailsDAO.updateUserVoterDetails2WithWard(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),partyId,localitityId,hamletId);
			   else
			       userVoterDetailsDAO.updateUserVoterDetails2(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),partyId,localitityId,hamletId);
		   }
		   else if(partyCast.equalsIgnoreCase("castLocality")){
			   if(voterHouseInfoVO.getSelType().equalsIgnoreCase("muncipality"))
				   userVoterDetailsDAO.updateUserVoterDetails3WithWard(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),casteStateId,localitityId,hamletId);
			   else
			    userVoterDetailsDAO.updateUserVoterDetails3(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),casteStateId,localitityId,hamletId);
		   }
			       else if(partyCast.equalsIgnoreCase("party")){
				   userVoterDetailsDAO.updateUserVoterPartyDetails(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),partyId,casteStateId);  
			   }else if(partyCast.equalsIgnoreCase("cast")){
				   userVoterDetailsDAO.updateUserVoterCastDetails(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),partyId,casteStateId);
			   }else if(partyCast.equalsIgnoreCase("localityPresent")  ){
				   if(voterHouseInfoVO.getSelType().equalsIgnoreCase("muncipality"))
					   userVoterDetailsDAO.updateUserVoterDetailsForLocalityWithWard(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),localitityId,hamletId);
				   else
					userVoterDetailsDAO.updateUserVoterDetailsForLocality(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),localitityId,hamletId);
				    }
			}else{
				
				Long locality = null;
				Long hamlet = null;
				Long ward = null;
				
				 if(voterHouseInfoVO.getSelType().equalsIgnoreCase("muncipality")){
					 
					 locality = voterHouseInfoVO.getLocalitityId();
				     ward = voterHouseInfoVO.getHamletId();
					 
				 }else{
				     locality = voterHouseInfoVO.getLocalitityId();
				     hamlet = voterHouseInfoVO.getHamletId();
				 }
				    
				    
				if(casteStateId != null || partyId != null || voterHouseInfoVO.getLocalitityId()!= null || voterHouseInfoVO.getHamletId()!=null || ward !=null || mobileNmbr != null ){
				 UserVoterDetails userVoterDtls = new UserVoterDetails();
				 userVoterDtls.setUser(user);
				 userVoterDtls.setVoter(voter);
				 if(casteStateId != null)
				 {
					 userVoterDtls.setCasteState(casteStateDAO.get(casteStateId));
					 userVoterDtls.setCasteInsertType(casteInsertTypeDAO.get(1l));
				 }
				 if(partyId != null)
					 userVoterDtls.setParty(partyDAO.get(partyId));
				 if(locality !=null)
					 userVoterDtls.setLocality(localityDAO.get(locality));
				 if(hamlet !=null)
					 userVoterDtls.setHamlet(hamletDAO.get(hamlet));
				 
				 if(ward !=null)
					 userVoterDtls.setWard(constituencyDAO.get(ward));
				 if(mobileNmbr!=null)
					 userVoterDtls.setMobileNo(mobileNmbr);
				 
				 
				 userVoterDetailsDAO.save(userVoterDtls);
				}
			}
		}
		
		if(groupPresent){			
			
			List<Long> list = customVoterDAO.getCustomVoterIdByVoterIdAndUserId(voterHouseInfoVO.getVoterId(), voterHouseInfoVO.getUserId());
			
			if(voterHouseInfoVO.getCustomGroupId().longValue() == 0 && list != null && list.size() >0)
				customVoterDAO.removeCustomVoterDetails(list.get(0));
			else
			{
				CustomVoter customVoter  = null;
				
				if(list != null && list.size() >0)
					customVoter = customVoterDAO.get(list.get(0));
				else{
					if(voterHouseInfoVO.getCustomGroupId().longValue() !=0){
						customVoter = new CustomVoter();
					    customVoter.setVoter(voter);
					    customVoter.setCustomVoterGroup(customVoterGroupDAO.get(voterHouseInfoVO.getCustomGroupId()));
					    customVoterDAO.save(customVoter);
					}
				}
			}
		}
			
		voterDAO.flushAndclearSession();
	}
	catch (Exception e) {
		log.error("Exception rised in updateVoterDetails : ",e);
	}
   }
	
}

 public boolean updateMultipleVoterDetails(List<VoterHouseInfoVO> voterHouseInfoVOs,String partyCast,boolean groupPresent){
   try{
	for(VoterHouseInfoVO voterHouseInfoVO : voterHouseInfoVOs){
		updateVoterDetails(voterHouseInfoVO,partyCast,groupPresent);
	}
   }catch(Exception e){
	   log.error("Exception rised in updateMultipleVoterDetails : ",e);
	   return false;
   }
	return true;
 }

public VoterHouseInfoVO getVoterFullInformation(Long voterId){
	VoterHouseInfoVO voterHouseInfoVO=new VoterHouseInfoVO();
	
	try{
		
		getVoterDetails(voterId,voterHouseInfoVO);	
		
	}catch (Exception e) {
		System.out.println(e);
	}
	
	return voterHouseInfoVO;
}

public void saveVoterDetails(VoterHouseInfoVO voterHouseInfoVO,Voter voter,UserVoterDetails userVoterDetails,VoterCategoryValue voterCategoryValues,UserVoterCategoryValue categoryValues){
	// userVoterDetailsDAO = null;
	/*if(voterHouseInfoVO.getCast() != null && !voterHouseInfoVO.getCast().equalsIgnoreCase("")){
		voter.setCast(voterHouseInfoVO.getCast());
	}*/
	if(voterHouseInfoVO.getPartyId() != null && !voterHouseInfoVO.getPartyId().equals(0)){
		
		userVoterDetails.setParty(partyDAO.get(voterHouseInfoVO.getPartyId()));
		userVoterDetails.setUser(userDAO.get(voterHouseInfoVO.getUserId()));
		userVoterDetails.setVoter(voterDAO.get(voterHouseInfoVO.getVoterId()));
		userVoterDetailsDAO.save(userVoterDetails);
	}
	
	if(voterHouseInfoVO.getCategory() != null){
		for(int i=0;i<voterHouseInfoVO.getCategory().size();i++){
			
			if(voterHouseInfoVO.getCategoryValuesId() !=null && !voterHouseInfoVO.getCategoryValuesId().equals(0)){
				
				categoryValues.setUserVoterCategory(userVoterCategoryDAO.get(voterHouseInfoVO.getUserCategoryValuesId1()));
				categoryValues.setCategoryValue(voterHouseInfoVO.getCategory().get(i).getValue());
				categoryValues.setUser(userDAO.get(voterHouseInfoVO.getUserId()));
				userVoterCategoryValueDAO.save(categoryValues);
			}
			if(voterHouseInfoVO.getCategory().get(i).getValue() != null && !voterHouseInfoVO.getCategory().get(i).getValue().equalsIgnoreCase("")){
				voterCategoryValues.setUserVoterCategoryValue(userVoterCategoryValueDAO.get(voterHouseInfoVO.getCategoryValuesId()));
				voterCategoryValues.setVoter(voterDAO.get(voterHouseInfoVO.getVoterId()));
				voterCategoryValues.setUser(userDAO.get(voterHouseInfoVO.getUserId()));
				voterCategoryValueDAO.save(voterCategoryValues);
			}
	}
	}
	//if(voterHouseInfoVO.getCategory() != null)
	//for(int i=0;i<voterHouseInfoVO.getCategory().size();i++){
		/*
	if(voterHouseInfoVO.getCategoryValuesId() !=null && !voterHouseInfoVO.getCategoryValuesId().equals(0)){
		
		categoryValues.setUserCategoryValues(userVoterCategoryDAO.get(voterHouseInfoVO.getUserCategoryValuesId()));
		categoryValues.setCategoryValue(voterHouseInfoVO.getCategory().get(i).getValue());
		userVoterCategoryValueDAO.save(categoryValues);
	}*/
	
	//if(voterHouseInfoVO.getCategory() != null)
	//if(voterHouseInfoVO.getCategory().get(i).getValue() != null && !voterHouseInfoVO.getCategory().get(i).getValue().equalsIgnoreCase("")){
		//userVoterCategoryValueDAO.get(voterHouseInfoVO.getCategoryValuesId())
		//		voterDAO.get(voterHouseInfoVO.getVoterId())
		//voterCategoryValues.setCategoryValues(userVoterCategoryValueDAO.get(1l));
		//voterCategoryValues.setVoter(voterDAO.get(1l));
		//voterCategoryValueDAO.save(voterCategoryValues);
		//voterCategoryValueDAO.flushAndclearSession();
		//}
	//}
}

public void getVoterDetails(Long voterId,VoterHouseInfoVO voterHouseInfoVO){
	
	List<Voter> voterDetails=voterDAO.getVoterPersonalDetailsByVoterId(voterId);
	/*if(voterDetails.size()>0){
		for(Voter voterInfo:voterDetails){
			voterHouseInfoVO.setCast(voterInfo.getCast());
		}	
	}*/	
	
}

public ResultStatus updateSerialNo(Long constituencyId,Long publicationDateId,Integer startIndex, Integer maxResults)
{
	ResultStatus resultStatus = new ResultStatus();
	try{
		List<Object[]> list = voterDAO.getSnoFromVoterTemp(constituencyId);
		if(list != null && list.size() > 0)
		{
			voterDAO.flushAndclearSession();
			for(final Object[] params : list)
			{
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						boothPublicationVoterDAO.updateSerialNoByVoterId((Long)params[1],(Long)params[0]);
				}});
			}
		}
		return resultStatus;
	}catch (Exception e) {
		log.error("Exception Occured in updateSerialNo() meyhod");
		log.error("Exception is - "+e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		return resultStatus;
	}
}
public ResultStatus insertVoterData(Long constituencyId,Long publicationDateId,Integer startIndex, Integer maxResults)
{
	ResultStatus resultStatus = new ResultStatus();
	try{
		Date d3 = new Date();
		int max = 1000;
		Map<Long,Long> boothsMap = getBoothsMapInAConstituency(constituencyId,publicationDateId);
		for(;;)
		{
			Date d1 = new Date(); 
			List<VoterTemp> voterTempData = voterTempDAO.getVotersInAConstituency(constituencyId,startIndex,max);
			List<String> voterIdsCardNosList = new ArrayList<String>(0);
			Map<String,Long> voterIdsCardNosMap = null;
			
			if(voterTempData == null || voterTempData.size() == 0)
			{
				return resultStatus;
			}
			for(VoterTemp voterTemp : voterTempData)
			{
				voterIdsCardNosList.add(voterTemp.getVoterId());
			}
			
			voterIdsCardNosMap = getVoterIdsAndCardNosMap(voterIdsCardNosList);
			
			if(voterTempData != null && voterTempData.size() > 0)
			{
				Voter voter = null;
				BoothPublicationVoter boothPublicationVoter = null;

				for(VoterTemp voterTemp : voterTempData)
				{
					try{
					if(voterIdsCardNosMap.get(voterTemp.getVoterId()) == null)
					{	
						voter = new Voter();
						voter.setVoterIDCardNo(voterTemp.getVoterId());
						voter.setName(voterTemp.getName());
						voter.setHouseNo(voterTemp.getHouseNo());
						voter.setRelativeName(voterTemp.getGuardianName());
						voter.setRelationshipType(voterTemp.getRelationShip());
						voter.setGender(voterTemp.getSex().equalsIgnoreCase("Male") ? IConstants.MALE : IConstants.FEMALE);
						voter.setAge(Long.parseLong(voterTemp.getAge().trim()));
						voter = voterDAO.save(voter);
					}
					else
						voter = voterDAO.get(voterIdsCardNosMap.get(voterTemp.getVoterId()));
					
					boothPublicationVoter = new BoothPublicationVoter();
					boothPublicationVoter.setVoter(voter);
					boothPublicationVoter.setBoothId(boothsMap.get(voterTemp.getPartNo()));
					boothPublicationVoter.setSerialNo(voterTemp.getSerialNo());
					boothPublicationVoterDAO.save(boothPublicationVoter);
					}catch (Exception e) {}
				}
				voterDAO.flushAndclearSession();
				Date d2 = new Date();
				Double diff = (double)(d2.getTime()-d1.getTime());
				System.out.println("1000 Records inserted in "+(diff)/(1000*60*60)+" Seconds");
				maxResults = maxResults - 1000;
				startIndex = startIndex + 1000;
				if(maxResults <= 0)
					break;
				if(maxResults <= 1000)
					max = maxResults;
			}
		}
		
		Date d4 = new Date();
		Double d5 = (new Double(d4.getTime() - d3.getTime()))/(1000*60);
		System.out.println("Time Taken - "+d5+" Mins");
		return resultStatus;
	}catch (Exception e) {
		log.error("Exception Occured in insertVoterData() Method, Exception is - "+e);
		return resultStatus;
	}
}
	
	public Map<String,Long> getVoterIdsAndCardNosMap(List<String> voterIdsCardNosList)
	{
		Map<String,Long> voterIdsCardNosMap = new HashMap<String, Long>(0);
		try{
			List<Object[]> list = voterDAO.getVoterIdsByCardNos(voterIdsCardNosList);
			
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					if(voterIdsCardNosMap.get(params[1].toString()) == null)
						voterIdsCardNosMap.put(params[1].toString(), (Long)params[0]);
				}
			}
			return voterIdsCardNosMap;
		}catch (Exception e) {
			log.error("Exception Occured in getVoterIdsAndCardNosMap() Method - "+e);
			return voterIdsCardNosMap;
		}
	}
	/**
	 * This method will Map the Voter Data From One Publication To Another Publication 
	 * @param Long Constituency Id
	 * @param Long From Publication Id
	 * @param Long To Publication Id
	 * @param Boolean boothCreateFlag (This will create the Booth if not available)
	 * @author Kamalakar Dandu
	 * @return {@link ResultStatus}
	 * 
	 */
	public ResultStatus mapVoterDataFromOnePublicationToAnotherPublication(Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,Boolean boothCreateFlag)
	{
		log.info("Entered into mapVoterDataFromOnePublicationToAnotherPublication() Method ");
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<Object[]> list = boothDAO.getBoothIdsAndPartNosOfAConstituencyInAPublication(constituencyId,fromPublicationDateId);
			List<Object[]> list2 = boothDAO.getBoothIdsAndPartNosOfAConstituencyInAPublication(constituencyId,toPublicationDateId);
			
			if(list == null || list.size() == 0)
			{
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setMessage("Booths Not Available for this Publication");
				return resultStatus;
			}
			
			if(list2 == null || list2.size() < list.size())
			{
				if(!boothCreateFlag)
				{
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					resultStatus.setMessage("Booths Not Available for To Publication");
					return resultStatus;
				}
				else
				{
					List<String> missedPartNos = new ArrayList<String>(0);
					for(Object[] params : list)
					{
						boolean flag = true;
						for(Object[] params2 : list2)
							if(params[1].toString().equalsIgnoreCase(params2[1].toString()))
								flag = false;
						if(flag)
							missedPartNos.add(params[1].toString());
					}
					List<Booth> previousBoothsList = boothDAO.getBoothsByPartNosAndPublicationIdInAConstituency(missedPartNos,constituencyId,fromPublicationDateId);
					
					for(Booth previousBooth : previousBoothsList)
					{
						Booth booth = copyBoothObject(previousBooth);
						booth.setPublicationDate(publicationDateDAO.get(toPublicationDateId));
						boothDAO.save(booth);
					}
					voterDAO.flushAndclearSession();
				}
			}
			
			list2 = boothDAO.getBoothIdsAndPartNosOfAConstituencyInAPublication(constituencyId,toPublicationDateId);
			Map<Long,List<Long>> addedVoterIdsMap = getModifiedVotersMapByStatusInAConstituency(constituencyId,fromPublicationDateId,IConstants.STATUS_ADDED);
			Map<Long,List<Long>> deletedVoterIdsMap = getModifiedVotersMapByStatusInAConstituency(constituencyId,fromPublicationDateId,IConstants.STATUS_DELETED);
			List<Object[]> votersAndPartNosList = boothPublicationVoterDAO.getPartNoAndVoterIdByConstituencyInAPublication(constituencyId, fromPublicationDateId);
			
			Map<String,List<BoothVoterVO>> votersAndPartNosMap = new HashMap<String, List<BoothVoterVO>>(0);
			
			for(Object[] params : votersAndPartNosList)
			{
				List<BoothVoterVO> vIdsList = votersAndPartNosMap.get(params[0].toString());
				if(vIdsList == null)
				{
					vIdsList = new ArrayList<BoothVoterVO>(0);
					votersAndPartNosMap.put(params[0].toString(),vIdsList);
				}
				vIdsList.add(new BoothVoterVO((Long)params[1],(Long)params[2]));
				votersAndPartNosMap.put(params[0].toString(),vIdsList);
			}
			
			for(Map.Entry<String,List<BoothVoterVO>> entry : votersAndPartNosMap.entrySet())
			{
				Long toBoothId = null;
				for(Object[] params : list2)
				if(params[1].toString().equalsIgnoreCase(entry.getKey()))
				{
					toBoothId = (Long)params[0];
					break;
				}
				
				for(BoothVoterVO boothVoterVO : entry.getValue())
				{
					if(!(addedVoterIdsMap.get(Long.valueOf(entry.getKey())) != null && 
							addedVoterIdsMap.get(Long.valueOf(entry.getKey())).contains(boothVoterVO.getVoterId())))
					{
						try{
						BoothPublicationVoter boothPublicationVoter = new BoothPublicationVoter();
						boothPublicationVoter.setBoothId(toBoothId);
						boothPublicationVoter.setVoter(voterDAO.get(boothVoterVO.getVoterId()));
						boothPublicationVoter.setSerialNo(boothVoterVO.getSerialNo());
						boothPublicationVoterDAO.save(boothPublicationVoter);
						}catch (Exception e) {}
					}
				}
				try{
				voterDAO.flushAndclearSession();
				}catch(Exception e){}
			}
			
			if(deletedVoterIdsMap.size() > 0)
			{
				for(Map.Entry<Long,List<Long>> entry : deletedVoterIdsMap.entrySet())
				{
					try{
						Long boothId = boothDAO.getBoothIdByConstituencyPublicationPartNo(constituencyId,toPublicationDateId, entry.getKey().toString());
						long serailNo = boothPublicationVoterDAO.getMaxSerialNoOfABooth(boothId);
						
						if(boothId != null)
						{
							for(Long voterId : entry.getValue())
							{
								BoothPublicationVoter boothPublicationVoter = new BoothPublicationVoter();
								boothPublicationVoter.setBoothId(boothId);
								boothPublicationVoter.setVoter(voterDAO.get(voterId));
								boothPublicationVoter.setSerialNo(++serailNo);
								boothPublicationVoterDAO.save(boothPublicationVoter);
							}
						}
					}catch(Exception e)
					{
						log.error("Exception Occured, Exception is ",e);
					}
				}
				
			}
			
			if(deletedVoterIdsMap.size() > 0)
			{
				for(Map.Entry<Long,List<Long>> entry : deletedVoterIdsMap.entrySet())
				{
					try{
						List<Long> bPVIDSList =  boothPublicationVoterDAO.getBoothPublicationVoterIdsByVoterIdsList(entry.getKey().toString(),entry.getValue(),fromPublicationDateId);
						if(bPVIDSList != null && bPVIDSList.size() > 0)
							boothPublicationVoterDAO.deleteByIdsList(bPVIDSList);
					}catch (Exception e) {}
				}
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			resultStatus.setMessage("Voter Data Mapped Successfully");
			return resultStatus;
		}catch (Exception e) {
			log.error("Exception Occured in mapVoterDataFromOnePublicationToAnotherPublication() Method");
			log.error("Exception is - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setMessage("Exception Occured..");
			return resultStatus;
		}
	}
	
	
	public ResultStatus getModifiedVotersBetweenTwoPublications(Long constituencyId,Long fromPublicationDateId, Long toPublicationDateId)
	{
	ResultStatus resultStatus = new ResultStatus();
	try{
		
	List<VoterModificationVO> resultList = new ArrayList<VoterModificationVO>(0);
	List<Object[]> addedVotersList = boothPublicationVoterDAO.getAddedVotersBetweenTwoPublications(constituencyId, fromPublicationDateId, toPublicationDateId);
	
	if(addedVotersList != null && addedVotersList.size() > 0)
	 setVotersList(resultList, addedVotersList, IConstants.STATUS_ADDED, toPublicationDateId, constituencyId);
	
	List<Object[]> deletedVotersList = boothPublicationVoterDAO.getDeletedVotersBetweenTwoPublications(constituencyId, fromPublicationDateId, toPublicationDateId);
	if(deletedVotersList != null && deletedVotersList.size() > 0)
	 setVotersList(resultList, deletedVotersList, IConstants.STATUS_DELETED, toPublicationDateId, constituencyId);
		
	
	if(resultList != null && resultList.size() > 0)
	 resultStatus = saveVoterModificationDetails(resultList);
	
	return resultStatus;
	}catch(Exception e)
	{
	log.error("Exception occured in getModifiedVotersBetweenTwoPublications() Method");
	log.error("Exception is - "+e);
	return resultStatus;
	}
	}
	
	public void setVotersList(List<VoterModificationVO> resultList,List<Object[]> list,String status,Long publicationDateId,Long constituencyId)
	{
		try{
		
		Long voterStatusId = voterStatusDAO.getVoterStatusIdByStatus(status);
		if(list != null && list.size() > 0)
		{
		  for(Object[] params: list)
		  {
			VoterModificationVO modificationVO = new VoterModificationVO();
			modificationVO.setId(Long.parseLong(params[1].toString()));
			modificationVO.setPartNo(Long.parseLong(params[0].toString()));
			modificationVO.setPresentPublicationId(publicationDateId);
			modificationVO.setStatus(status);
			modificationVO.setConstituencyId(constituencyId);
			modificationVO.setLocationId(voterStatusId);
			resultList.add(modificationVO);
		  }
		}
			
		}catch (Exception e) {
		 e.printStackTrace();
		 log.error(" Exception Occured in setVotersList() method, Exception - "+e);
		}
	}
	
	public ResultStatus saveVoterModificationDetails(final List<VoterModificationVO> resultList)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(TransactionStatus status) 
				{
				 if(resultList != null && resultList.size() > 0)
				 {
				  for(VoterModificationVO modificationVO :resultList)
				  {
					 VoterModification modification = new VoterModification();
					 modification.setVoterId(modificationVO.getId());
					 modification.setConstituencyId(modificationVO.getConstituencyId());
					 modification.setPublicationDateId(modificationVO.getPresentPublicationId());
					 modification.setStatus(modificationVO.getStatus());
					 modification.setPartNo(modificationVO.getPartNo());
					 modification.setVoterStatusId(modificationVO.getLocationId());
					 voterModificationDAO.save(modification);
				  }
				 }
					
				}});
		 return resultStatus;
		}catch (Exception e) {
		  e.printStackTrace();
		  log.error(" Exception Occured in saveVoterModificationDetails() method, Exception - "+e);
		  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		  return resultStatus;
		}
	}
	
	
	public Map<Long,List<Long>> getModifiedVotersMapByStatusInAConstituency(Long constituencyId, Long publicationDateId, String status)
	{
		Map<Long,List<Long>> resultMap = new HashMap<Long, List<Long>>(0);
		try{
			List<Object[]> list = voterModificationDAO.getModifiedVotersByConstituency(constituencyId,publicationDateId,status);
			
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					List<Long> voterIdsList = resultMap.get((Long)params[1]);
					if(voterIdsList == null)
						voterIdsList = new ArrayList<Long>(0);
					voterIdsList.add((Long)params[0]);
					resultMap.put((Long)params[1], voterIdsList);
				}
			}
			return resultMap;
		}catch(Exception e){
			log.error(e);
			return resultMap;
		}
	}
	public Booth copyBoothObject(Booth booth)
	{
		try{
			Booth newBooth = new Booth();
			if(booth == null)
				return null;
			newBooth.setPartNo(booth.getPartNo());
			newBooth.setPartName(booth.getPartName());
			newBooth.setLocation(booth.getLocation());
			newBooth.setVillagesCovered(booth.getVillagesCovered());
			newBooth.setTehsil(booth.getTehsil());
			newBooth.setMaleVoters(booth.getMaleVoters());
			newBooth.setFemaleVoters(booth.getFemaleVoters());
			newBooth.setTotalVoters(booth.getTotalVoters());
			newBooth.setConstituency(booth.getConstituency());
			newBooth.setYear(booth.getYear());
			newBooth.setLocalBody(booth.getLocalBody());
			newBooth.setPublicationDate(booth.getPublicationDate());
			newBooth.setPanchayat(booth.getPanchayat());
			newBooth.setLocalBodyWard(booth.getLocalBodyWard());
			return newBooth;
			
		}catch (Exception e) {
			log.error("Exception Occured in copyBoothObject() Method");
			log.error("Exception is - "+e);
			return null;
		}
	}
public ResultStatus updateVoterData(Long constituencyId,Integer startIndex, Integer maxResults)
{
	ResultStatus resultStatus = new ResultStatus();
	try{
		Date d3 = new Date();
		int max = 1000;
		for(;;)
		{
			Date d1 = new Date(); 
			List<VoterTemp> voterTempData = voterTempDAO.getVotersInAConstituency(constituencyId,startIndex,max);
			
			if(voterTempData != null && voterTempData.size() > 0)
			{
				List<String> voterIdsList = new ArrayList<String>(0);
				
				for(VoterTemp voterTemp : voterTempData)
					voterIdsList.add(voterTemp.getVoterId());
				
				List<Object[]> voterIds = voterDAO.getVoterIdsByVoterIdCardNos(voterIdsList);
				
				int index = 1;
				for(Object[] voterId : voterIds)
				{
				try{
					VoterTemp voterTemp = getVoterTemp(voterTempData,voterId[1].toString());
					if(voterTemp != null)
					{   
						index++;
						final String name = voterTemp.getName();
						final String relativeName = voterTemp.getGuardianName();
						final Long votetId = (Long)voterId[0];
						final int j = index; 
						transactionTemplate.execute(new TransactionCallbackWithoutResult() {
							public void doInTransactionWithoutResult(TransactionStatus status) {
						int result = voterDAO.updateVoterNameAndRelativeName(name,relativeName,votetId);
						System.out.println(j+") "+result +" Records Updated");
						log.warn(j+") "+result +" Records Updated");
						}});
					}
				    }catch (Exception e) {}
				}
				
				voterDAO.flushAndclearSession();
				Date d2 = new Date();
				Double diff = (double)(d2.getTime()-d1.getTime());
				System.out.println("1000 Records updated in "+(diff)/(1000)+" Seconds");
				log.warn("1000 Records updated in "+(diff)/(1000)+" Seconds");
				maxResults = maxResults - 1000;
				startIndex = startIndex + 1000;
				if(maxResults <= 0)
					break;
				if(maxResults <= 1000)
					max = maxResults;
			}
		}
		
		Date d4 = new Date();
		Double d5 = (new Double(d4.getTime() - d3.getTime()))/(1000*60);
		System.out.println("Time Taken - "+d5+" Mins");
		log.warn("Time Taken - "+d5+" Mins");
		return resultStatus;
	}catch (Exception e) {
		log.error("Exception Occured in updateVoterData() Method, Exception is - "+e);
		return resultStatus;
	}
}
public VoterHouseInfoVO getBoothDetailsForVoter(Long boothId){
	List<Booth> boothDetails=boothDAO.getBoothDetailsByBoothId(boothId);
	VoterHouseInfoVO voterHouseInfoVO = null;
	
		for(Booth booth : boothDetails)
		{
			voterHouseInfoVO = new VoterHouseInfoVO();
			voterHouseInfoVO.setBoothName(booth.getLocation());
			voterHouseInfoVO.setVilliageCovered(booth.getVillagesCovered());
			if(booth.getPanchayat() != null)
			  voterHouseInfoVO.setPanchayatName(booth.getPanchayat().getPanchayatName());
			else
				voterHouseInfoVO.setPanchayatName("");
		}
	
	return voterHouseInfoVO;
}

public List<VoterHouseInfoVO> getUserCategoryValues(){
	List<VoterHouseInfoVO> voterHouseInfoValues = new ArrayList<VoterHouseInfoVO>();
	VoterHouseInfoVO voterHouseInfoVO = null;
	List<UserVoterCategory> categoryValues=userVoterCategoryDAO.getUserCategoryValues();
	if(categoryValues != null && categoryValues.size()>0)
		for(UserVoterCategory categoryValue : categoryValues){
			if(categoryValue.getUserVoterCategoryId() != null){
				voterHouseInfoVO = new VoterHouseInfoVO();
			voterHouseInfoVO.setsNo(categoryValue.getUserVoterCategoryId());
			voterHouseInfoVO.setName(categoryValue.getCategoryName());
			voterHouseInfoValues.add(voterHouseInfoVO);
			}
		}
	return voterHouseInfoValues;
	
}

public List<SelectOptionVO> getVoterCategoryValues(Long voterCategoryId,String letters){
		
	List<SelectOptionVO> voterCategoryValues = new ArrayList<SelectOptionVO>();
	List<Object[]> categoryValues =userVoterCategoryValueDAO.getVoterCategoryValues(voterCategoryId,letters);
	
	for(Object[] categoryValue:categoryValues){
		   if(categoryValue[0]!= null && categoryValue[1] != null)
			   voterCategoryValues.add(new SelectOptionVO((Long)categoryValue[0], categoryValue[1].toString().trim().toUpperCase()));
	}
	return voterCategoryValues;	
}


public Map<Long,Long> getBoothsMapInAConstituency(Long constituencyId,Long publicationDateId)
{
	Map<Long,Long> boothsMap = new HashMap<Long, Long>(0);
	try{
		List<Object[]> list = boothDAO.getBoothsInAConstituencyByPublication(constituencyId,publicationDateId);
		
		if(list != null && list.size() > 0)
			for(Object[] params : list)
			{
				try{
				boothsMap.put(Long.parseLong(params[1].toString().trim()),(Long)params[0]);
				}catch (Exception e) {
					log.error("Exception Occured - "+e);
				}
			}
				
		return boothsMap;
	}catch (Exception e) {
		log.error("Exception occured in getBoothsMapInAConstituency(), Exception is - "+e);
		return boothsMap;
	}
}
public SelectOptionVO storeGroupName(final Long userId ,final String name)
{
	
	SelectOptionVO selectOptionVO = new SelectOptionVO();
	
	UserVoterCategory userCategoryValues = (UserVoterCategory)transactionTemplate.execute(new TransactionCallback() {
		
	public Object doInTransaction(TransactionStatus status) {
	List<Long> count = userVoterCategoryDAO.checkCategoryExist(userId, name);
	if(count != null && count.size()> 0 && ((Long)count.get(0))==0){
		UserVoterCategory userCategoryValues = new UserVoterCategory();
	userCategoryValues.setCategoryName(name);
	userCategoryValues.setUser(userDAO.get(userId));
	userCategoryValues = userVoterCategoryDAO.save(userCategoryValues);
	return userCategoryValues;
	}
	else
	 return null;	
		}
		});
	if(userCategoryValues != null){
	selectOptionVO.setId(userCategoryValues.getUserVoterCategoryId());
	selectOptionVO.setName(userCategoryValues.getCategoryName());
	}
	return  selectOptionVO;
	
}

@SuppressWarnings("null")
public List<SelectOptionVO> findVoterCategoryValues(Long UserId)
{
	List<SelectOptionVO> voterCategoryValues = new ArrayList<SelectOptionVO>();
	try{
		log.debug("enter into the voterCategoryValues method of VoterAnalysisService");
	SelectOptionVO selectOptionVO = null;
	List<Object[]> categoryValues = userVoterCategoryDAO.getCategoryValuesList(UserId);
	if(categoryValues !=null && categoryValues.size()> 0)
	{
		for (Object[] categoryValue : categoryValues)
		{
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId((Long) categoryValue[0]);
			selectOptionVO.setName((String)categoryValue[1]);
			voterCategoryValues.add(selectOptionVO);
		}
	}
	}catch (Exception e) {
		log.error("exception raised in voterCategoryValues method of VoterAnalysisService" +e );
	}
	return voterCategoryValues;
	
}

public SelectOptionVO storeCategoryVakues(final Long userId, final String name, final Long id)
{
	SelectOptionVO selectOptionVO = new SelectOptionVO();
	
				List<Long> count = userVoterCategoryValueDAO.checkCategoryExist(userId, name,id);
				if(count != null && count.size()> 0 && ((Long)count.get(0))==0){
				 UserVoterCategoryValue categoryValues = new UserVoterCategoryValue();
				 UserVoterCategory userCategoryValues = new UserVoterCategory();
				categoryValues.setUser(userDAO.get(userId));
				categoryValues.setCategoryValue(name);
				categoryValues.setUserVoterCategory(userVoterCategoryDAO.get(id));
				categoryValues = userVoterCategoryValueDAO.save(categoryValues);
				
				}
				
				return selectOptionVO;
}


	public List<SelectOptionVO> getAllPublicationDates()
	{
		List<SelectOptionVO> publicationDateslist =  new ArrayList<SelectOptionVO>(0);
		SelectOptionVO selectOptionVO = null;
		try{
			
			List<Object[]> list = publicationDateDAO.getAllPublicationDates();
			if(list != null && list.size() > 0)
			{
				
				for(Object[] params : list)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					publicationDateslist.add(selectOptionVO);
				}
			}
			return publicationDateslist;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAllPublicationDates() Method, Exception - "+e);
			return publicationDateslist;
		}
	}
	
	public List<SelectOptionVO> getConstituenciesList()
	{
		List<SelectOptionVO> constituencyList = new ArrayList<SelectOptionVO>(0);;
		SelectOptionVO selectOptionVO = null;
		try{
			
			List<Object> list = voterTempDAO.getconstituencyNames();
			
			if(list != null && list.size() > 0)
			{
				List<String> constituencyNamesList = new ArrayList<String>(0);
				for(Object param : list)
					constituencyNamesList.add(param.toString());
				
				List<Object[]> list2 = voterTempDAO.getConstituencies(constituencyNamesList);
				for(Object[] params : list2)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					constituencyList.add(selectOptionVO);
				}
			}
			return constituencyList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error(" Exception Occured in getConstituenciesList() Method, Exception - "+e);
			return constituencyList;
		}
	}
	
		
	public ResultStatus saveCasteName(Long userId, Long stateId, Long casteCategoryGroupId, String casteName)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			CasteState casteState = null;
			Caste caste = casteDAO.getCasteByCastName(casteName);
			
			if(caste == null)
			{
				caste = new Caste();
				caste.setCasteName(casteName);
				caste = casteDAO.save(caste);
			}
			CasteState casteState2 = casteStateDAO.getCasteStateByCasteId(userId, stateId, caste.getCasteId(), casteCategoryGroupId);
			if(casteState2 == null)
			{
				casteState = new CasteState();
				casteState.setUser(userDAO.get(userId));
				casteState.setCasteCategoryGroup(casteCategoryGroupDAO.get(casteCategoryGroupId));
				casteState.setIsGlobal(IConstants.FALSE);
				casteState.setState(stateDAO.get(stateId));
				casteState.setCaste(caste);
				casteStateDAO.save(casteState);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			}
			else
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in saveCasteName() Method, Exception - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		
	}
	public ResultStatus saveLocality(Long userId,Long hamletId,String name,Long localbody,Long wardId)
	{
	ResultStatus resultStatus = new ResultStatus();
	Locality locality = null;
	
	try{
		locality = new Locality();
		if(hamletId > 0)
		locality.setHamlet(hamletDAO.get(hamletId));
		if(wardId > 0)
		 locality.setWard(constituencyDAO.get(wardId));
	
		if(localbody.toString().substring(0,1).trim().equalsIgnoreCase("1"))
		{
		localbody = new Long(localbody.toString().substring(1));
		locality.setLocalElectionBody(assemblylocalElectionBodyDAO.get(localbody).getLocalElectionBody());
		}
		locality.setIsGlobal("false");
		locality.setUser(userDAO.get(userId));
		locality.setName(name);
		localityDAO.save(locality);
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		return resultStatus;
		}		
		catch(Exception e)
		{
		e.printStackTrace();
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		return resultStatus;
		}
	
	}
	public List<SelectOptionVO> getcastCategoryGroups()
	{
		List<SelectOptionVO> castCategoryList = new ArrayList<SelectOptionVO>(0);
		try{
			List<Object[]> list = casteCategoryGroupDAO.getAllCasteCategoryGroupInfoDetails();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					castCategoryList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				}
			}
			return castCategoryList;
		}catch (Exception e) {
			log.error("Exception Occured in getcastCategoryGroups() Method, Exception - "+e);
			return castCategoryList;
		}
	}

	 public List<SelectOptionVO> getBoothsInMunicipality(Long lclElecBodyId,Long publicationDateId,Long constituencyId){
		 List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		try{
			List<Object> listId = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(lclElecBodyId);
			
		 List<Object[]> booths = boothDAO.getBoothsInAMunicipality((Long)listId.get(0),publicationDateId,constituencyId);
		 SelectOptionVO selectOptionVO = null;
		 for(Object[] booth:booths){
			 selectOptionVO = new SelectOptionVO();
			 selectOptionVO.setId((Long)booth[0]);
			 selectOptionVO.setName(booth[1]!=null?booth[1].toString():"");
			 selectOptionVO.setValue(booth[1].toString());
			 selectOptionVO.setLocation("Booth Location:"+booth[2]!= null?"Booth Location:"+booth[2].toString():"");
			 selectOptionVO.setVillageCovered("Areas Covered :"+booth[3] != null?"Areas Covered :"+booth[3].toString():"");
			 list.add(selectOptionVO);
		 }
		 Collections.sort(list,arraySort);
		}catch(Exception e){
			log.error("Exception rised in getBoothsInMunicipality ",e);
		}
		 return list;
		 
	 }
	 
	 public List<SelectOptionVO> getWardsMunicipality(Long lclElecBodyId,Long publicationDateId, Long constituencyId ,Long userId)
	 {
		 List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		 try{
			 Long lid = (Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(lclElecBodyId).get(0);
			 String type =	localElectionBodyDAO.getLocationTypeForLocalEleBodyByLocalEleBodyId(lid);
			 List<Object[]> wards = null;
			 
			 if(type.equalsIgnoreCase(IConstants.GHMC))
				 wards = wardDAO.getWardsListByLocalEleBodyIdAndConstituencyId(lclElecBodyId, publicationDateId,constituencyId);		 
			 else
				 wards = userVoterDetailsDAO.getWardsBYLocalElectionBodyId(lid, publicationDateId, userId);
	 
			 for(Object[] ward:wards)
			 {
				 SelectOptionVO selectOptionVO = new SelectOptionVO();
				 selectOptionVO.setType(type);
				 selectOptionVO.setId((Long)ward[0]);
				 selectOptionVO.setName(ward[1]!=null? ward[1].toString():"");
				 selectOptionVO.setValue(ward[1].toString());
				 
				 if(type.equalsIgnoreCase("Greater Municipal Corp"))
				 {
					 List<SelectOptionVO> list2 = getBoothForWard((Long)ward[0],publicationDateId);
					 if(list2 != null && list2.size()>0)
					 selectOptionVO.setSelectOptionsList(list2);
				 }
				 list.add(selectOptionVO);
			 }
			Collections.sort(list,wardsSort);
		 }catch(Exception e){
			log.error("Exception rised in getwardsInMunicipality ",e);
		}
		 return list;
	 }
	 
	public static Comparator<SelectOptionVO> wardsSort = new Comparator<SelectOptionVO>()
	{	  
		public int compare(SelectOptionVO arg1,SelectOptionVO arg2)
		{  
			String first = arg1.getName().trim().toUpperCase();
			String last = arg2.getName().trim().toUpperCase();
		 	if(first.indexOf("WARD-") != 0)
			  return 0;
			return new Integer(Integer.parseInt(first.substring(first.indexOf("-")+1))).compareTo(Integer.parseInt(last.substring(last.indexOf("-")+1)));
		}
	};
	 
	 
	 /*
	   *This method gives hamlets and as well as booths for that hamlet
	 */
	 public List<SelectOptionVO> getHamletsForPanchayat(Long panchatayId,Long publicationDateId){
		 List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		try{
			List<Object[]> listId =  getHamletsOfAPanchayat(panchatayId);
			
		// List<Object[]> booths = boothDAO.getBoothsInAMunicipality((Long)listId.get(0),publicationDateId);
		// SelectOptionVO selectOptionVO = null;
		 for(Object[] ward:listId){
			 SelectOptionVO  selectOptionVO = new SelectOptionVO();
			
			 selectOptionVO.setId((Long)ward[0]);
			 selectOptionVO.setName(ward[1]!=null? ward[1].toString():"");
			 selectOptionVO.setValue(ward[1].toString());
			 List<SelectOptionVO> list2=	getBoothsForHamlet((Long)ward[0],publicationDateId);
				if(list2 != null && list2.size()>0)
					selectOptionVO.setSelectOptionsList(list2);
				
					 list.add(selectOptionVO);
		 }
		// Collections.sort(list,arraySort);
		}catch(Exception e){
			log.error("Exception rised in getBoothsInMunicipality ",e);
		}
		 return list;
		 
	 }
	 
	 public List<SelectOptionVO> getWardsMunicipality1(Long lclElecBodyId,Long publicationDateId){
		 List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		
		 try{
			

	List<Object[]> wards = wardDAO.findByWardsByAssemblyLocalElectionBodyId(lclElecBodyId, publicationDateId);
	 for(Object[] ward:wards){
		 SelectOptionVO  selectOptionVO = new SelectOptionVO();
		
		 selectOptionVO.setId((Long)ward[0]);
		 selectOptionVO.setName(ward[1]!=null? ward[1].toString():"");
		 selectOptionVO.setValue(ward[1].toString());
		 List<SelectOptionVO> list2=	getBoothForWard((Long)ward[0],publicationDateId);
			if(list2 != null && list2.size()>0)
				selectOptionVO.setSelectOptionsList(list2);
			
				 list.add(selectOptionVO);
	 }
		 }catch(Exception e){
			log.error("Exception rised in getwardsInMunicipality ",e);
		}
		 return list;
		 
	 }
	 public List<SelectOptionVO> getBoothForWard(Long wardId, Long publicationDateId)
	 {    List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		 List<Object[]> booths = boothDAO.getBoothInWard(wardId, publicationDateId);
		   SelectOptionVO selectOptionVO = null;
			 for(Object[] booth:booths){
				 selectOptionVO = new SelectOptionVO();
				 selectOptionVO.setId((Long)booth[0]);
				 selectOptionVO.setName(booth[1]!=null?booth[1].toString():"");
				 selectOptionVO.setValue(booth[1].toString());
			
				 list.add(selectOptionVO);
			 }
			 Collections.sort(list,arraySort);
		
			 return list;
		 
	 }
	 public List<SelectOptionVO> getBoothsForHamlet(Long hamletID, Long publicationDateId)
	 {    List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
	 List<Object[]> booths=	hamletBoothPublicationDAO.getBoothsInHamlet(hamletID, publicationDateId);
		   SelectOptionVO selectOptionVO = null;
			 for(Object[] booth:booths){
				 selectOptionVO = new SelectOptionVO();
				 selectOptionVO.setId((Long)booth[0]);
				 selectOptionVO.setName(booth[1]!=null?"Booth No- "+booth[1].toString():"");
				 selectOptionVO.setValue(booth[1].toString());
			
				 list.add(selectOptionVO);
			 }
			 Collections.sort(list,arraySort);
		
			 return list;
		 
	 }
	 
	 public VoterTemp getVoterTemp(List<VoterTemp> list,String voterID)
	 {
		 for(VoterTemp voterTemp : list)
			 if(voterID.equalsIgnoreCase(voterTemp.getVoterId()))
				 return voterTemp;
		 return null;
	 }
	 
	 public List<VoterHouseInfoVO> getMultipleFamiliesInfo(List<VoterHouseInfoVO> familiesList,Long userId){
		 List<VoterHouseInfoVO> votersInfo = new ArrayList<VoterHouseInfoVO>();
		 for(VoterHouseInfoVO family : familiesList){
			 votersInfo.addAll(getFamilyInfo(family.getBoothId(),family.getPublicationId(),family.getHouseNo(),userId));
		 }
		 for(int i =0; i<votersInfo.size();i++){
			 VoterHouseInfoVO voter = votersInfo.get(i);
			 voter.setsNo(new Long(i+1));
		 }
		 return votersInfo;
	 }
	 
	 public ResultStatus insertVotersInfoOfAConstituency()
	 {
		 try{
			 
		 }catch(Exception e)
		 {
			 
		 }
		 return null;
	 }
	 
	 public VoterHouseInfoVO getVotersInfoBySearchCriteria(VoterHouseInfoVO searchInfo,String type,Long id,List<Long> categories,Long userId){
		 VoterHouseInfoVO returnValue = new VoterHouseInfoVO();
		 List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
		 returnValue.setVotersList(votersList);
		 try{
			  StringBuilder query = new StringBuilder();
			  
			    if(type.equalsIgnoreCase("constituency")){
			    	query.append(" and model.booth.constituency.constituencyId = :id ");
				}
				else if(type.equalsIgnoreCase("mandal")){
					if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1).trim()));
						query.append(" and model.booth.localBody.localElectionBodyId = :id ");
						id = (Long)list.get(0);
					 }else if(id.toString().substring(0,1).trim().equalsIgnoreCase("2")){
						 query.append(" and model.booth.tehsil.tehsilId = :id and model.booth.localBody is null ");
						 id = new Long(id.toString().substring(1).trim());
					 }
				}
				else if(type.equalsIgnoreCase("booth")){
					query.append(" and model.booth.boothId = :id ");
				}
				else if(type.equalsIgnoreCase("panchayat")){
					query.append(" and model.booth.boothId in(:id) ");
				}else if(type.equalsIgnoreCase("ward")){
					query.append(" and model.booth.localBodyWard.constituencyId = :id ");
				}
			    
			    //For Custom Ward
				else if(type != null && type.equalsIgnoreCase(IConstants.CUSTOMWARD)){
					 query.append(" and model2.ward.constituencyId =:id ");
				}
				
				else if(type.equalsIgnoreCase("hamlet")){
					//i know this is worst but for code reusability
				List<Object> ids=	hamletBoothPublicationDAO.getBoothsIds(id, searchInfo.getPublicationId());
					StringBuffer s= new StringBuffer();
					int count =0;
				     id=0l;
						int length=ids.size();
						for(Object hid: ids){
							count++;
							if(count != length){
							s=s.append(hid);
							s=s.append(",");}
							else 
								s=s.append(hid);
						}	
					
					query.append(" and model.booth.boothId in("+s+")");
				}
			    
			    if(searchInfo.getQueryType() != null && searchInfo.getQueryType().equalsIgnoreCase("or")){
			    	if(searchInfo.getVoterIdCardNo() != null)
			    		query.append(" or model.voter.voterIDCardNo = '"+searchInfo.getVoterIdCardNo()+"'");
			    	if(searchInfo.getName() != null){
			    	  if(searchInfo.getSetValue() != null && searchInfo.getSetValue().equalsIgnoreCase("start"))
			    		query.append(" or model.voter.name like '"+searchInfo.getName()+"%'");
			    	  else
			    		query.append(" or model.voter.name like '%"+searchInfo.getName()+"%'");
			    	}
			    	if(searchInfo.getGaurdian() != null){
			    		query.append(" or model.voter.relativeName like '%"+searchInfo.getGaurdian()+"%'");
			    	}
			    	if(searchInfo.getGender() != null){
			    		 query.append(" or model.voter.gender = '"+searchInfo.getGender()+"'");
			    	}
			    	if(searchInfo.getAge() != null){
			    		 query.append(" or model.voter.age >= "+searchInfo.getAge());
			    	}
			    	if(searchInfo.getToAge() != null){
			    		 query.append(" or model.voter.age <= "+searchInfo.getToAge());
			    	}
			    	if(searchInfo.getHouseNo() != null){
			    		 query.append(" or model.voter.houseNo = '"+searchInfo.getHouseNo()+"'");
			    	}
			    	if(searchInfo.getFromSno() != null){
			    		 query.append(" or model.serialNo >= "+searchInfo.getFromSno());
			    	}
			    	if(searchInfo.getToSno() != null){
			    		 query.append(" or model.serialNo <= "+searchInfo.getToSno());
			    	}
			    }else{
			    	if(searchInfo.getVoterIdCardNo() != null)
			    		query.append(" and model.voter.voterIDCardNo = '"+searchInfo.getVoterIdCardNo()+"'");
			    	if(searchInfo.getName() != null){
			    	  if(searchInfo.getSetValue() != null && searchInfo.getSetValue().equalsIgnoreCase("start"))
			    		query.append(" and model.voter.name like '"+searchInfo.getName()+"%'");
			    	  else
			    		query.append(" and model.voter.name like '%"+searchInfo.getName()+"%'");
			    	}
			    	if(searchInfo.getGaurdian() != null){
			    		query.append(" and model.voter.relativeName like '%"+searchInfo.getGaurdian()+"%'");
			    	}
			    	if(searchInfo.getGender() != null){
			    		 query.append(" and model.voter.gender = '"+searchInfo.getGender()+"'");
			    	}
			    	if(searchInfo.getAge() != null){
			    		 query.append(" and model.voter.age >= "+searchInfo.getAge());
			    	}
			    	if(searchInfo.getToAge() != null){
			    		 query.append(" and model.voter.age <= "+searchInfo.getToAge());
			    	}
			    	if(searchInfo.getHouseNo() != null){
			    		 query.append(" and model.voter.houseNo = '"+searchInfo.getHouseNo()+"'");
			    	}
			    	if(searchInfo.getFromSno() != null){
			    		 query.append(" and model.serialNo >= "+searchInfo.getFromSno());
			    	}
			    	if(searchInfo.getToSno() != null){
			    		 query.append(" and model.serialNo <= "+searchInfo.getToSno());
			    	}
			    }
			    if(searchInfo.getSortByColum().equalsIgnoreCase("name"))
			          query.append(" order by model.voter.name "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("voterIdCardNo"))
				      query.append(" order by model.voter.voterIDCardNo "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("houseNo"))
				      query.append(" order by model.voter.houseNo "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("boothName"))
				      query.append(" order by cast(model.booth.partNo, int) "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("gender"))
				      query.append(" order by model.voter.gender "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("age"))
				      query.append(" order by cast(model.voter.age, int) "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("gaurdian"))
				      query.append(" order by model.voter.relativeName "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("relationship"))
				      query.append(" order by model.voter.relationshipType "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("fromSno"))
				      query.append(" order by model.serialNo "+searchInfo.getSortBy());
			   /* else if(searchInfo.getSortByColum().trim().length() >1 && searchInfo.getSortIds() != null &&  searchInfo.getSortIds().length >0){			    	
			    	String arrayId = searchInfo.getSortByColum().substring(searchInfo.getSortByColum().length() - 1); 
			        String sortEle = searchInfo.getSortIds()[new Integer(arrayId)];
			        if(sortEle != null){
			        	searchInfo.setSortReq(true);
			        	searchInfo.setSortEle(sortEle);
			        }
			    }*/
			    
			    if(type != null && type.equalsIgnoreCase(IConstants.CUSTOMWARD))
			    {
			    	List<Long> countList = userVoterDetailsDAO.getVotersCountBySearchCriteria(searchInfo.getPublicationId(), id, query.toString());
			    	if(countList != null && countList.get(0) != null && ((Long)countList.get(0)).longValue() > 0l){
				    	 returnValue.setTotalHousesCount((Long)countList.get(0));
				    	  List<Object[]> votersData = userVoterDetailsDAO.getVotersDetailsBySearchCriteria(searchInfo.getPublicationId(),id,searchInfo.getStartIndex(),searchInfo.getMaxIndex(),query.toString());
				    	  populateVotersDataToVoForSearch(votersData,votersList,categories,searchInfo,userId);
				     }
			    	
			    }
			    else{
			    
			    	List<Long> boothIds = null;
			    	List<Long> partialPanchayatBooths = null;
			    	 if(type.equalsIgnoreCase("panchayat")){
			    		boothIds = boothDAO.getBoothsByPanchayatId(id, searchInfo.getPublicationId());
			    		partialPanchayatBooths = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatId(id,searchInfo.getPublicationId());
			    		if(boothIds != null && boothIds.size() > 0){
				    		for (Long boothId : partialPanchayatBooths) {
				    			if(!boothIds.contains(boothId))
				    					boothIds.add(boothId);
							}
			    		}else{
			    			boothIds = partialPanchayatBooths;
			    		}
			    	}
					
			    	if(type.equalsIgnoreCase("panchayat") && (boothIds == null || boothIds.size() == 0)){
			    		
			    		 return returnValue;
			    	}
				    List<Long> countList = boothPublicationVoterDAO.getVotersCountBySearchCriteria(searchInfo.getPublicationId(),id,query.toString(),boothIds,type);

			     if(countList != null && countList.get(0) != null && ((Long)countList.get(0)).longValue() > 0l){
			    	 returnValue.setTotalHousesCount((Long)countList.get(0));
			    	  List<Object[]> votersData = boothPublicationVoterDAO.getVotersDetailsBySearchCriteria(searchInfo.getPublicationId(),id,searchInfo.getStartIndex(),searchInfo.getMaxIndex(),query.toString(),type,boothIds);
			    	  populateVotersDataToVoForSearch(votersData,votersList,categories,searchInfo,userId);
			     }
			    }
			    
			    if(searchInfo.getSortByColum().equalsIgnoreCase("cast"))
			    	if(searchInfo.getSortBy().equalsIgnoreCase("asc"))
			    	 Collections.sort(returnValue.getVotersList());
			    	else
			    	 Collections.sort(returnValue.getVotersList(),castSortDesc);
			     
		 }catch(Exception e){
			 log.error("Exception rised in getVotersInfoBySearchCriteria ",e);
		 }
		 
		 return returnValue;
	 }
	 
	   public static Comparator<VoterHouseInfoVO> castSortDesc = new Comparator<VoterHouseInfoVO>()
		{
					  
	      public int compare (VoterHouseInfoVO m1, VoterHouseInfoVO m2){
	    	  
	    	  if(m1.getCast() == null || m2.getCast() == null)
	    		  return -1;
	    	 
            return m2.getCast().compareTo(m1.getCast());
           }
		};
	 
	 public void populateVotersDataToVoForSearch(List<Object[]> votersData,List<VoterHouseInfoVO> votersList,List<Long> categories,VoterHouseInfoVO searchInfo,Long userId){
		 VoterHouseInfoVO voterHouseInfoVO = null;
		 Map<Long,VoterHouseInfoVO> votersMap = new HashMap<Long,VoterHouseInfoVO>();
		 List<Long> voterIds = new ArrayList<Long>();
		 Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
		 if(votersData != null && votersData.size() > 0)
		 {
		  for(Object[] params:votersData)
		  {
			Voter voter = (Voter)params[0];
			 voterIds.add(voter.getVoterId());
		  }
		  
		  List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIds, userId);
		  if(list != null && list.size() > 0)
		   for(Object[] params:list)
			 mobileNosMap.put((Long)params[0], params[1]!=null?params[1].toString():"N/A");
		  
		 }
		 for(Object[] voters : votersData){
			    Voter voter = (Voter)voters[0];
		    	voterHouseInfoVO = new VoterHouseInfoVO();
		    	//voterHouseInfoVO.setName(voter.getFirstName()+" "+voter.getLastName());
		    	voterHouseInfoVO.setName(voter.getName());
		    	voterHouseInfoVO.setGender(voter.getGender());
		    	voterHouseInfoVO.setAge(voter.getAge());
		    	voterHouseInfoVO.setHouseNo(voter.getHouseNo());
		    	//voterHouseInfoVO.setGaurdian(voter.getRelativeFirstName()+" "+voter.getRelativeLastName());
		    	voterHouseInfoVO.setGaurdian(voter.getRelativeName());
		    	voterHouseInfoVO.setRelationship(voter.getRelationshipType());
		    	if(mobileNosMap.get(voter.getVoterId())!= null)
		    	 voterHouseInfoVO.setMobileNo(mobileNosMap.get(voter.getVoterId()));
		    	else
		    	 voterHouseInfoVO.setMobileNo("N/A");
		    	
		    	voterHouseInfoVO.setVoterId(voter.getVoterId());
		    	//voterIds.add(voter.getVoterId());
		    	voterHouseInfoVO.setBoothId((Long)voters[1]);
		    	voterHouseInfoVO.setBoothName(voters[2]!=null?voters[2].toString():"");
		    	voterHouseInfoVO.setVoterIdCardNo(voter.getVoterIDCardNo());
		    	voterHouseInfoVO.setFromSno((Long)voters[3]);
		    	votersMap.put(voter.getVoterId(),voterHouseInfoVO);
		    	votersList.add(voterHouseInfoVO);
		    	
		    }
		    //Checking whether the voter is politician,cadre,influencing people
		     if(voterIds != null && voterIds.size() > 0){
		    	 getCadreInfluencingPeopleCandidateInfo(voterIds,searchInfo.getUserId(), votersMap);
		     }
			 if((searchInfo.isPartyPresent() || searchInfo.isCastPresent() ||searchInfo.isLocalityPresent() || searchInfo.isVoterGroupPresent() || (categories != null && categories.size() > 0)) && voterIds.size() >0){
				 if(searchInfo.isPartyPresent() || searchInfo.isCastPresent() || searchInfo.isLocalityPresent() ){
					 List<UserVoterDetails> votersPartyCastList = userVoterDetailsDAO.getAllUserVoterDetails(voterIds,searchInfo.getUserId());
				     for(UserVoterDetails voterDetails:votersPartyCastList){
				    	 VoterHouseInfoVO voterObj = votersMap.get(voterDetails.getVoter().getVoterId());
				    	 if(voterObj != null){
				    		 if(voterDetails.getCasteState()!= null && voterDetails.getCasteState().getCaste()!= null){
				    			 voterObj.setCast(voterDetails.getCasteState().getCaste().getCasteName());
				    		 }
				    		 if(voterDetails.getParty() != null){
				    			 voterObj.setParty(voterDetails.getParty().getShortName());
				    		 }
				    			 if( voterDetails.getLocality()!= null && voterDetails.getLocality().getName() !=null)
				    				 voterObj.setLocalAreaName(voterDetails.getLocality().getName());
				    			 
				    			 if(voterDetails.getHamlet()!= null && voterDetails.getHamlet().getHamletName() !=null )
				    			 voterObj.setHamletName(voterDetails.getHamlet().getHamletName());
				    						   
				    			 if(voterDetails.getWard() != null && voterDetails.getWard().getName() != null)
				    				 voterObj.setWardName(voterDetails.getWard().getName());

				    			 
				    	 }
				     }
				 }
				 
				 if(searchInfo.isVoterGroupPresent())
				 {
					 List<Object[]> list = customVoterDAO.getVoterGroupNamesByVoterIdsList(voterIds);
					 if(list != null && list.size() > 0)
					 {
						for(Object[] params : list)
						{
						  VoterHouseInfoVO voterDet = votersMap.get((Long)params[0]);
							if(voterDet != null)
							  voterDet.setVoterGroup(params[1] != null ? params[1].toString(): " ");
						}
					 }
				 }
				 if(categories != null && categories.size() > 0){
					 VoterHouseInfoVO category = null;
					 List<Object[]> votersPartyCastList = voterCategoryValueDAO.getVoterCategoryValuesForVoters(searchInfo.getUserId(),voterIds);
				     for(Object[] voterDetails:votersPartyCastList){
				    	 VoterHouseInfoVO voterObj = votersMap.get((Long)voterDetails[0]);
				    	 if(voterObj != null){
				    		 List<VoterHouseInfoVO> categoriesList = voterObj.getCategoriesList();
				    		 if(categoriesList == null){
				    			 categoriesList = new ArrayList<VoterHouseInfoVO>();
				    			 voterObj.setCategoriesList(categoriesList);
				    		 }
				    		  category = new VoterHouseInfoVO();
				    		  categoriesList.add(category);
				    		  category.setCategoryValuesId((Long)voterDetails[1]);
				    		  category.setName(voterDetails[2]!=null?voterDetails[2].toString():"");
				    	 }
				     }
				 }
			 }
			  if(searchInfo.getSortByColum().equalsIgnoreCase("LocalArea") )
				  sortVotersList(votersList, searchInfo.getSortBy());
			  if(searchInfo.getSortByColum().equalsIgnoreCase("Hamlet") )
				  sortVotersList1(votersList, searchInfo.getSortBy());
			  if(searchInfo.getSortByColum().equalsIgnoreCase("Ward"))
				  sortVotersWardList(votersList, searchInfo.getSortBy());

			/*  if(searchInfo.getSortByColum().equalsIgnoreCase("LocalArea") && searchInfo.getSortBy().equalsIgnoreCase("desc"))
				  sortVotersListDesc(votersList);
			  */
			/* if(searchInfo.isSortReq()){
				 if(searchInfo.getSortEle().equalsIgnoreCase("party")){
					 if(searchInfo.getSortBy().equalsIgnoreCase("asc"))
						Collections.sort(votersList,sortByPartyAsc);
					 else
						Collections.sort(votersList,sortByPartyDesc);
				 }
				 else if(searchInfo.getSortEle().equalsIgnoreCase("cast")){
					 if(searchInfo.getSortBy().equalsIgnoreCase("asc"))
						Collections.sort(votersList,sortByCastAsc);
					 else
						Collections.sort(votersList,sortByCastDesc);
				 }else{
					 
				 }
			 }*/
	 }
	 public void sortVotersList(List<VoterHouseInfoVO> localitiesList,final String order)
		{
			 Collections.sort(localitiesList, new Comparator<VoterHouseInfoVO>() {

					public int compare(VoterHouseInfoVO arg0,
							VoterHouseInfoVO arg1) {
						String a =arg0.getLocalAreaName();
						String b =arg1.getLocalAreaName();
						if(a==null)a="";	if(b==null)b="";
						if(order.equalsIgnoreCase("asc"))
						return a.trim().toUpperCase().compareTo(b.trim().toUpperCase());
						else 
							return b.trim().toUpperCase().compareTo(a.trim().toUpperCase());
					}   
				});
		}
	 public void sortVotersList1(List<VoterHouseInfoVO> localitiesList,final String order)
		{
			 Collections.sort(localitiesList, new Comparator<VoterHouseInfoVO>() {

					public int compare(VoterHouseInfoVO arg0,
							VoterHouseInfoVO arg1) {
						String a =arg0.getHamletName();
						String b =arg1.getHamletName();
						if(a==null)a="";	if(b==null)b="";
						if(order.equalsIgnoreCase("asc"))
						return a.trim().toUpperCase().compareTo(b.trim().toUpperCase());
						else 
							return b.trim().toUpperCase().compareTo(a.trim().toUpperCase());
					}   
				});
		}
	 
	 public void sortVotersWardList(List<VoterHouseInfoVO> wardList,final String order)
	 {
		 Collections.sort(wardList, new Comparator<VoterHouseInfoVO>() {

			 public int compare(VoterHouseInfoVO arg0,
					 	VoterHouseInfoVO arg1) {
				 	String a =arg0.getWardName();
				 	String b =arg1.getWardName();
				 	if(a==null)a=""; if(b==null)b="";
				 	if(order.equalsIgnoreCase("asc"))
				 	return a.trim().toUpperCase().compareTo(b.trim().toUpperCase());
				 	else
				 		return b.trim().toUpperCase().compareTo(a.trim().toUpperCase());
			 	}
		 	});
	 }
	 
	 public void sortVotersListDesc(List<VoterHouseInfoVO> localitiesList)
		{
			 Collections.sort(localitiesList, new Comparator<VoterHouseInfoVO>() {

					public int compare(VoterHouseInfoVO arg0,
							VoterHouseInfoVO arg1) {
						
						return arg0.getLocalAreaName().trim().toUpperCase().compareTo(arg1.getLocalAreaName().trim().toUpperCase());
					}
				});
		}
	 public void sortVotersListAsc(List<VoterHouseInfoVO> localitiesList)
		{
			 Collections.sort(localitiesList, new Comparator<VoterHouseInfoVO>() {

					public int compare(VoterHouseInfoVO arg0,
							VoterHouseInfoVO arg1) {
						
						return arg1.getLocalAreaName().trim().toUpperCase().compareTo(arg0.getLocalAreaName().trim().toUpperCase());
					}
				});
		}
	 public void populateVotersDataToVo(List<Object[]> votersData,List<VoterHouseInfoVO> votersList){
		 VoterHouseInfoVO voterHouseInfoVO = null;
		 for(Object[] voters : votersData){
			    Voter voter = (Voter)voters[0];
		    	voterHouseInfoVO = new VoterHouseInfoVO();
		    	//voterHouseInfoVO.setName(voter.getFirstName()+" "+voter.getLastName());
		    	voterHouseInfoVO.setName(voter.getName());
		    	voterHouseInfoVO.setGender(voter.getGender());
		    	voterHouseInfoVO.setAge(voter.getAge());
		    	voterHouseInfoVO.setHouseNo(voter.getHouseNo());
		    	//voterHouseInfoVO.setGaurdian(voter.getRelativeFirstName()+" "+voter.getRelativeLastName());
		    	voterHouseInfoVO.setGaurdian(voter.getRelativeName());
		    	voterHouseInfoVO.setRelationship(voter.getRelationshipType());
		    	
		    	voterHouseInfoVO.setVoterId(voter.getVoterId());
		    	voterHouseInfoVO.setBoothId((Long)voters[1]);
		    	voterHouseInfoVO.setBoothName(voters[2]!=null?voters[2].toString():"");
		    	voterHouseInfoVO.setVoterIdCardNo(voter.getVoterIDCardNo());
		    	votersList.add(voterHouseInfoVO);
		    	
		    }
	 }
	 
	 public List<SelectOptionVO> getElectionIdAndTypeByPublicationId(Long publicationDateId){
		 
		 List<SelectOptionVO> selectOptionVOs = null;  
		 try{
			   
		    PublicationDate publicationDate = publicationDateDAO.get(publicationDateId);
			List<Long> stateIds = boothDAO.getStateIdByPublicationId(publicationDateId);
			if(stateIds != null && stateIds.size() >0)
			{
				List<Object[]> list = electionDAO.getPreviousElectionsByStateIdYearAndDate(stateIds.get(0),publicationDate.getYear().toString(),publicationDate.getDate());
				if(list != null && list.size() > 0)
				{
					selectOptionVOs = new ArrayList<SelectOptionVO>(0);
					for(Object[] params : list)
						selectOptionVOs.add(new SelectOptionVO((Long)params[0],params[1].toString()));
					
				}
				return selectOptionVOs;
			}
			  
			else
				return selectOptionVOs;
			}catch(Exception e){
			   log.error("Exception rised in getElectionIdAndTypeByPublicationId ",e);
		   }
		   return selectOptionVOs;
	 }
	 
	 public List<Election> getPrevElections(Long publicationDateId){
		   try{
		    PublicationDate publicationDate = publicationDateDAO.get(publicationDateId);
			List<Long> stateIds = boothDAO.getStateIdByPublicationId(publicationDateId);
			if(stateIds != null && stateIds.size() >0){
			  return electionDAO.getPreviousElections(stateIds.get(0),publicationDate.getYear().toString(),publicationDate.getDate());  
			}else
				return new ArrayList<Election>();
			}catch(Exception e){
			   log.error("Exception rised in getPrevElections ",e);
		   }
		   return new ArrayList<Election>();
	 }
	 
	 public void getPrevElectVotersCount(List<Election> elections,Long id,VotersInfoForMandalVO votersInfoForMandalVO,String type,Long constituencyId){
		 List<VotersInfoForMandalVO> previousElectInfoList = new ArrayList<VotersInfoForMandalVO>();
		 votersInfoForMandalVO.setPreviousElectInfoList(previousElectInfoList);
		 List<Long> constituencyIdsList = new ArrayList<Long>(0);
			
			List parliamentList = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
			if(parliamentList != null && parliamentList.size() > 0)
			{
				for(Object[] params : (List<Object[]>)parliamentList)
				{
					if(!constituencyIdsList.contains(params[0]))
						constituencyIdsList.add((Long)params[0]);
				}
			}
			if(!constituencyIdsList.contains(constituencyId))
				constituencyIdsList.add(constituencyId);
		  for(Election election : elections){
		   try{
			  List<Object[]> prevElecVotersInfoList = null;
			  if(type.equalsIgnoreCase("constituency")){
			    prevElecVotersInfoList = boothConstituencyElectionDAO.getVotersCountInAConstituency(election.getElectionId(),id);
			  }else if(type.equalsIgnoreCase("mandal")){
				  if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
					  List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1).trim()));
					  prevElecVotersInfoList = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(election.getElectionId(),(Long)list.get(0),"localElec",null,constituencyId,null);
				  }else{
				     prevElecVotersInfoList = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(election.getElectionId(),new Long(id.toString().substring(1).trim()),"mandal",null, null,constituencyIdsList);
			  
				  }
			  }else if(type.equalsIgnoreCase("panchayat")){
			    prevElecVotersInfoList = hamletBoothElectionDAO.getVotersCountInAPanchayat(election.getElectionId(),id);
			  }else if(type.equalsIgnoreCase("booth")){
				    Booth booth = boothDAO.get(id);
				    prevElecVotersInfoList = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(election.getElectionId(),booth.getTehsil().getTehsilId(),"booth",booth.getPartNo(),null,null);
				  }
			   if(prevElecVotersInfoList != null && prevElecVotersInfoList.size() > 0){
				 Object[] prevElecVotersInfo = prevElecVotersInfoList.get(0);
				 if(prevElecVotersInfo[2] != null && ((Long)prevElecVotersInfo[2]).longValue() > 0){
					 VotersInfoForMandalVO votersInfo = new VotersInfoForMandalVO();
					 votersInfo.setTotalMaleVoters(((Long)prevElecVotersInfo[0]).toString());
					 votersInfo.setTotalFemaleVoters(((Long)prevElecVotersInfo[1]).toString());
					 votersInfo.setTotalVoters(((Long)prevElecVotersInfo[2]).toString());
					 votersInfo.setElectionYear(election.getElectionYear());
					 votersInfo.setType(election.getElecSubtype()+"-ELECTION");
					 Long totalVoters = 0l;
					 if(votersInfoForMandalVO.getTotalMaleVoters() != null){
						 Long voters =  new Long(votersInfoForMandalVO.getTotalMaleVoters());
						 totalVoters = totalVoters+voters;
						 votersInfo.setMaleVotersDiff((voters.longValue() -((Long)prevElecVotersInfo[0]).longValue()));
						
						 votersInfoForMandalVO.setMaleVotersDiff((voters.longValue() -((Long)prevElecVotersInfo[0]).longValue()));
					 }
					 if(votersInfoForMandalVO.getTotalFemaleVoters() != null){
						 Long voters =  new Long(votersInfoForMandalVO.getTotalFemaleVoters());
						 totalVoters = totalVoters+voters;
						 votersInfo.setFemaleVotersDiff((voters.longValue() -((Long)prevElecVotersInfo[1]).longValue()));
						
						 votersInfoForMandalVO.setFemaleVotersDiff((voters.longValue() -((Long)prevElecVotersInfo[1]).longValue()));
					 }
					 if(totalVoters != null && totalVoters.longValue() > 0){
						 votersInfo.setTotalVotersDiff((totalVoters.longValue() -((Long)prevElecVotersInfo[2]).longValue()));
						 
						 votersInfoForMandalVO.setTotalVotersDiff((totalVoters.longValue() -((Long)prevElecVotersInfo[2]).longValue()));
					 }
					 previousElectInfoList.add(votersInfo);
					
				 }
			 }
		    }catch(Exception e){
				 log.error("Exception rised in getPrevElectVotersCountForConstituencyForMandal ",e);
			}
		  }
		
	 }
	 
	 public VoterCastInfoVO getCastWisePartyCount(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId)
		{
		    VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
			List<CastVO> resultList = null;
			try{
				if(locationType.equalsIgnoreCase("mandal"))
				{
					String mandalId= locationId.toString();
					String id=mandalId.substring(1);
					locationId = new Long(id);
					if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
						locationType = "mandal";
					}else if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						locationType = "localElectionBody";
						List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationId);
						locationId = (Long) list.get(0);
					}
				}
				List<Object[]> castList = boothPublicationVoterDAO.getCastWiseCount(userId,locationType,locationId,publicationDateId,constituencyId);
				
				
				
				List<Object[]> partiesList = null;
				
				if(locationType.equalsIgnoreCase("panchayat"))
				{
					
					List<PartialBoothPanchayat> partialPanchayatDetails = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(locationId, publicationDateId);
					
					if(partialPanchayatDetails != null && partialPanchayatDetails.size() > 0 )
						partiesList = boothPublicationVoterDAO.getPartyWiseCountForPanchayatByHamlets(userId,locationId,publicationDateId);
					else
						partiesList = boothPublicationVoterDAO.getPartyWiseCount(userId,locationType,locationId,publicationDateId,constituencyId);
				}else
				 partiesList = boothPublicationVoterDAO.getPartyWiseCount(userId,locationType,locationId,publicationDateId,constituencyId);
				
				
				//List<Object[]> parties = boothPublicationVoterDAO.getParties(userId,locationType,locationId,publicationDateId,constituencyId);
				Map<String,CastVO> castsMap = new HashMap<String,CastVO>();
				CastVO castVO = null;
				CastVO partyVO = null;
				for(Object[] castInfo : castList){
					if(castsMap.get(castInfo[0].toString()) != null){
						castVO = castsMap.get(castInfo[0].toString());
					}else{
						castVO = new CastVO();
						Map<Long,CastVO> partiesMap = new HashMap<Long,CastVO>();
						for(Object[] party:partiesList){
						  if(partiesMap.get((Long)party[3]) == null){
							partyVO = new CastVO();
							partyVO.setPartyId((Long)party[3]);
							partyVO.setPartyName(party[1].toString());
							partiesMap.put((Long)party[3],partyVO);
						  }
						}
						castVO.setPartiesMap(partiesMap);
						castsMap.put(castInfo[0].toString(), castVO);
					}
					castVO.setCastName(castInfo[0].toString());
					castVO.setCastCount((Long)castInfo[1]);
					castVO.setPartyNotAssigCount((Long)castInfo[1]);
					castVO.setCastStateId((Long)castInfo[2]);
				}
				
				for(Object[] party : partiesList){
					CastVO cast = castsMap.get(party[0].toString());
					if(cast != null){
						CastVO partyVo = cast.getPartiesMap().get((Long)party[3]);
						if(partyVo != null){
							partyVo.setPartyCount((Long)party[2]);
							cast.setPartyCount(cast.getPartyCount()+(Long)party[2]);
							cast.setPartyNotAssigCount(cast.getPartyNotAssigCount()-(Long)party[2]);
						}
					}
				}
				resultList = new ArrayList<CastVO>(castsMap.values());
				if(resultList != null && resultList.size() >0){
					for(CastVO result:resultList){
						result.setPartiesList(new ArrayList<CastVO>(result.getPartiesMap().values()));
						Collections.sort(result.getPartiesList(),castVOSort);
					}
				}
				voterCastInfoVO.setCastVOs(resultList);
				return voterCastInfoVO;
			}catch (Exception e) {
				log.error("Exception Occured in getCastWisePartyCount() Method, Exception is - ",e);
				return voterCastInfoVO;
			}
		     
		 }
	     public static Comparator<CastVO> castVOSort = new Comparator<CastVO>()
				{
							  
			      public int compare (CastVO m1, CastVO m2){
	                return m1.getPartyName().compareTo(m2.getPartyName());
	               }
				};
				
				 public List<SelectOptionVO> getElectionIdAndTypeInAConstituencyByPublicationId(Long publicationDateId, Long constituencyId)
				 {
					 List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>(0);
					 try{
						 PublicationDate publicationDate = publicationDateDAO.get(publicationDateId);
						 List<Long> mainElectionId = constituencyElectionDAO.getNearestPreviousMainElectionIdFromADateInAConstituency(constituencyId, publicationDate.getDate());
						 
						 List<Object[]> list = constituencyElectionDAO.getElectionIdAndSubTypeByConstituencyIdAndDate(constituencyId, electionDAO.get(mainElectionId.get(0)).getElectionDate(), publicationDate.getDate());
						 if(list != null && list.size() > 0)
						 {
							 for(Object[] params : list)
								 resultList.add(new SelectOptionVO((Long)params[0],params[1].toString())); 
							 
						 }
						 
						 return resultList;
					 }catch (Exception e) {
						 log.error("Exception Occured in getElectionIdAndTypeInAConstituencyByPublicationId() Method - "+e);
						 return resultList;
					}
				 }
				 
				 public Long getReportLevelId(String type)
				 {
					 Long reportLevelId = 0l;
					 try{
						 if(type.equalsIgnoreCase("localElectionBody"))
							 type = IConstants.LOCALELECTIONBODY;
						 
						 reportLevelId = voterReportLevelDAO.getReportLevelIdByType(type);
						 return reportLevelId;
					 }catch (Exception e) {
						e.printStackTrace();
						log.error("Exception Occured in getReportLevelId() Method, Exception - "+e);
						return reportLevelId;
					}
				 }
				 
				 public String getReportLevelById(Long id)
				 {
					 try{
						return voterReportLevelDAO.getReportLevelTypeById(id);
					 }catch (Exception e) {
						 e.printStackTrace();
						 log.error("Exception Occured in getReportLevelId() Method, Exception - "+e);
						 return null;
					}
				 }
				 
				 public List<VotersDetailsVO> getCountList(Long publicationDateId,Long id,String type,Long constituencyId,Long tehsilId,Long userId)
				 {
					 List<SelectOptionVO> namesList = new ArrayList<SelectOptionVO>(0);
					 List<SelectOptionVO> mandalList = new ArrayList<SelectOptionVO>(0);
					 List<SelectOptionVO> muncipalityList = new ArrayList<SelectOptionVO>();
					 List<SelectOptionVO> panchayatiesList = new ArrayList<SelectOptionVO>();
					 List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>();
					 List<SelectOptionVO> panchayatList1 = new ArrayList<SelectOptionVO>(0);
					 List<SelectOptionVO> boothsList1 = new ArrayList<SelectOptionVO>(0);
					 List<SelectOptionVO> localbody1 = new ArrayList<SelectOptionVO>(0);
					 List<SelectOptionVO> localbody2 = new ArrayList<SelectOptionVO>(0);
					 List<SelectOptionVO> hamlets = new ArrayList<SelectOptionVO>(0);
					 List<SelectOptionVO> customWardBooths = new ArrayList<SelectOptionVO>(0);
					 List<Long> panchayatIdsList = null;
					 SelectOptionVO panchayat;
					 SelectOptionVO booth = null;
					 VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
					 List<VotersDetailsVO> resultlist = new ArrayList<VotersDetailsVO>();
					 SelectOptionVO panchayat1;
					 String mandalName = "";
					 if(type.equalsIgnoreCase("constituency"))
					   votersDetailsVO.setAreaType(constituencyDAO.get(id).getAreaType());
					
				 try
					 {
					 if(type.equalsIgnoreCase("mandal"))
					 	{
							if(id.toString().substring(0,1).trim().equalsIgnoreCase("2"))
							{
								type = "mandal";	
								id = new Long(id.toString().substring(1));
							}
							else if(id.toString().substring(0,1).trim().equalsIgnoreCase("1"))
							{
								
								List<Object> listId = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1)));
								id =(Long)listId.get(0); 
								type = "localElectionBody";
							}
						}
					 
					 if(type.equalsIgnoreCase("constituency"))
					 {  
					  try{	 
						 int totalHamlets = 0;
						namesList = regionServiceDataImp.getSubRegionsInConstituency(id, IConstants.PRESENT_YEAR, null);
						mandalList = getMandals(namesList);
						muncipalityList = getMuncipalities(namesList);
						panchayatIdsList = new ArrayList<Long>(0);
						
						if(mandalList != null && mandalList.size() > 0)
						{
							votersDetailsVO.setMandalList(mandalList);
							votersDetailsVO.setTotalmandals(new Long(mandalList.size()));
							
							List<Long> mandalIdsList = new ArrayList<Long>(0);
							for(SelectOptionVO vo : mandalList)
								mandalIdsList.add(new Long(vo.getId().toString().substring(1)));
							
							List<SelectOptionVO> mandalPanchayatsList = staticDataService.getPanchayatiesByMandalIdsListAndConstituencyId(mandalIdsList,constituencyId,publicationDateId);
							
							for(SelectOptionVO vo2 : mandalPanchayatsList)
								for(SelectOptionVO vo3 : vo2.getSelectOptionsList())
									panchayatIdsList.add(vo3.getId());
							
							for(SelectOptionVO mandals: mandalList)
							{
								SelectOptionVO vo = staticDataService.getSelectOptionVOFromResultList(mandalPanchayatsList, new Long(mandals.getId().toString().substring(1)));
								
								if(vo != null)
								{
									mandals.setSelectOptionsList(vo.getSelectOptionsList());
									if(vo.getSelectOptionsList() == null)
										mandals.setValue("0");
									else
									mandals.setValue(new Integer(vo.getSelectOptionsList().size()).toString());
								}
							}
							
							List<SelectOptionVO> panchayatHamletsList = staticDataService.getHamletsByPanchayatIdsList(panchayatIdsList);
							List<SelectOptionVO> panchayatBoothsList = staticDataService.getBoothsByPanchayatIdsListAndConstituencyIdInAPublication(panchayatIdsList,constituencyId,publicationDateId);
							
							for(SelectOptionVO mandals: mandalList)
							{
								for(SelectOptionVO panchayatRef : mandals.getSelectOptionsList())
								{
									SelectOptionVO vo = staticDataService.getSelectOptionVOFromResultList(panchayatHamletsList, panchayatRef.getId());
									
									if(vo != null && vo.getSelectOptionsList() != null){
										panchayatRef.setSelectOptionsList1(vo.getSelectOptionsList());
										totalHamlets += vo.getSelectOptionsList().size();
									}
									
									SelectOptionVO vo2 = staticDataService.getSelectOptionVOFromResultList(panchayatBoothsList, panchayatRef.getId());
									panchayatRef.setSelectOptionsList(vo2.getSelectOptionsList());
									
									if(vo2 != null && vo2.getSelectOptionsList() != null){
										if(vo2.getSelectOptionsList() == null)
											panchayatRef.setValue("0");
										else
											panchayatRef.setValue(new Integer(vo2.getSelectOptionsList().size()).toString());
									}else{
										panchayatRef.setValue("0");
									}
									
								}
							}
							votersDetailsVO.setTotalNoOfHamlets(totalHamlets);
						}
						
					 	//each Muncipality boothList
						if(muncipalityList != null && muncipalityList.size() > 0)
						{
							int totalWards=0;
							
							for(SelectOptionVO localbody : muncipalityList)
							{
							try{
								localbody1 = getWardsMunicipality(new Long(localbody.getId().toString().substring(1)),publicationDateId, constituencyId , userId);
								
								if(localbody1 == null ||localbody1.size() <= 0)
									localbody2 = getBoothsInMunicipality(new Long(localbody.getId().toString().substring(1)), publicationDateId,constituencyId);
								else 
								{
									if(!localbody1.get(0).getType().equalsIgnoreCase("Greater Municipal Corp") )
										localbody2 = getBoothsInMunicipality(new Long(localbody.getId().toString().substring(1)), publicationDateId,constituencyId);
									
									if(localbody1!= null && localbody1.size() > 0)
										localbody1 = getBoothsInCustomWardsOfALocalElectionBody(localbody1, constituencyId, publicationDateId, userId);
									
									localbody.setSelectOptionsList(localbody1);	
									totalWards = totalWards +localbody1.size();
									localbody.setValue(new Long(localbody1.size()).toString());
									localbody.setType(localbody1.get(0).getType());
								}
								
								if(localbody2!= null && localbody2.size() > 0)
								{
									localbody.setSelectOptionsList1(localbody2);
									localbody.setValue(new Long(localbody2.size()).toString()); 
								}
								else
									localbody.setValue("0");
								}catch(Exception e){}
							 }
							
							if(totalWards > 0)
								votersDetailsVO.setTotalNoOfWards(totalWards);
						}
					  }catch(Exception e){}			
				 }
					 if(!type.equalsIgnoreCase("panchayat") && !type.equalsIgnoreCase("localElectionBody") && !type.equalsIgnoreCase("ward") && !type.equalsIgnoreCase("hamlet") && !type.equalsIgnoreCase(IConstants.CUSTOMWARD))
					 {
						    List<Object[]> panchayatiesList1 = null;
						    try{
						    	 //panchayatiesList1 = panchayatDAO.getPanchayatiesCount(id,type,publicationDateId);
						    	 panchayatiesList1 = boothDAO.getPanchayatiesCountByTahsilAndConstituencyId(constituencyId,id,publicationDateId,type);
						    }catch(Exception e){
						    	log.error("Exception Occured in getting panchayaties  - ",e);
						    }
							if(panchayatiesList1 !=null && panchayatiesList1.size() > 0)
							{
							 for(Object[] params :panchayatiesList1)
							 {
							 panchayat = new SelectOptionVO((Long)params[0],params[1].toString()+" Panchayat");
							 panchayatiesList.add(panchayat);
							 }
							 if(!type.equalsIgnoreCase("constituency"))
							 for(SelectOptionVO panchayats : panchayatiesList)
								{
								
									 boothsList1 = getBoothsByPanchayatIdandConstituencyId((Long)panchayats.getId(),publicationDateId,constituencyId,type,id);
									 panchayats.setSelectOptionsList(boothsList1);	
								 }
								 
							 votersDetailsVO.setPanchayatList(panchayatiesList);
							 votersDetailsVO.setTotalPanchayats(new Long(panchayatIdsList.size()));
							}else{
								votersDetailsVO.setPanchayatList(panchayatiesList);
								votersDetailsVO.setTotalPanchayats(new Long(panchayatiesList.size()));
							}
						
					 }
					 if(type.equalsIgnoreCase("panchayat"))
							 {
					List<Object> count=  panchayatHamletDAO.getHamletsCountOfAPanchayat(id);
						  int counts= count.size();
						if(count != null && counts>0 )
						votersDetailsVO.setTotalNoOfHamlets(Integer.parseInt(count.get(0).toString()));
							 }
					
					 if(!type.equalsIgnoreCase("hamlet") && !type.equalsIgnoreCase(IConstants.CUSTOMWARD) ){
						 List<Object[]> booths = null;
						 if(tehsilId != 0){
							 booths = boothDAO.getBoothsCount(id,publicationDateId,type,constituencyId,new Long(tehsilId.toString().substring(1)));
						 }
					 	if(tehsilId == 0){
					 		booths = boothDAO.getBoothsCount(id,publicationDateId,type,constituencyId,tehsilId);
					 	}
					if(booths != null && booths.size() > 0)
					{
						for(Object[] params :booths)
						{
						booth = new SelectOptionVO((Long)params[0],params[1].toString());
						booth.setLocation(params[2] != null?params[2].toString():"");
						booth.setVillageCovered(params[3] != null?params[3].toString():"");
						boothsList.add(booth);
						}
						votersDetailsVO.setTotalBooths(new Long(boothsList.size()));
						votersDetailsVO.setBoothsList(boothsList);
					}
					 }
					if(type.equalsIgnoreCase("localElectionBody"))
					{
						List<Object> count=null;
						if(!constituencyDAO.get(constituencyId).getAreaType().equalsIgnoreCase("RURAL-URBAN"))
						{//getCount for wards
							count = boothDAO.getNoOfWardsInMuncipality(id, publicationDateId,constituencyId);
						}
						else
						{
							count = userVoterDetailsDAO.getDistinctWardsOfLocalElectionBodyId(id, publicationDateId, userId);
						}
						 if(count != null && count.size()>0 ){
							 votersDetailsVO.setTotalNoOfWards(((Long)count.get(0)).intValue());
						 }
					}
					if(type.equalsIgnoreCase("customWard")){
						List<Object[]> booths= userVoterDetailsDAO.getBoothsForCustomWard(id,constituencyId,publicationDateId,userId);
						if(booths != null && booths.size() > 0)
						{
							for(Object[] params :booths)
							{
							booth = new SelectOptionVO((Long)params[0],params[1].toString());
							boothsList.add(booth);
							}
							votersDetailsVO.setTotalBooths(new Long(boothsList.size()));
						}
					}
					if(muncipalityList != null && muncipalityList.size() > 0)
					{
						votersDetailsVO.setNoOfLocalBodies(new Long(muncipalityList.size()));
						votersDetailsVO.setLocalbodiesList(muncipalityList);
					}
					resultlist.add(votersDetailsVO);
						
					return resultlist;
					}
					 
					
					catch(Exception e)
					{
						e.printStackTrace();
						log.error("Exception Occured in getCountList() method - ",e);
						return resultlist;
					}
					
			}
				 
				 public List<SelectOptionVO> getBoothsInCustomWardsOfALocalElectionBody(List<SelectOptionVO> wardsList, Long constituencyId, Long publicationDateId, Long userId)
				 {
					 try{
						List<Long> wardIdsList = new ArrayList<Long>(0);
						if(wardsList == null || wardsList.size() == 0)
						 return wardsList;
						
						for(SelectOptionVO ward : wardsList)
							wardIdsList.add(ward.getId());
						
						List<Object[]> list = userVoterDetailsDAO.getBoothsForCustomWardIdsList(wardIdsList, constituencyId, publicationDateId, userId);
						
						if(list != null && list.size() > 0)
						{
							for(Object[] params : list)
							{
								try{
								SelectOptionVO wradRef = staticDataService.getSelectOptionVOFromResultList(wardsList, (Long)params[0]);
								if(wradRef != null)
								{
									List<SelectOptionVO> boothsList = wradRef.getSelectOptionsList();
									boothsList.add(new SelectOptionVO((Long)params[1],params[2].toString()));
									wradRef.setSelectOptionsList(boothsList);
								}
									
								}catch (Exception e) {}
							}
						}
						
						return wardsList;
					 }catch(Exception e)
					 {
						 return wardsList;
					 }
				 }
				 
				 public List<SelectOptionVO> getCustomWardBooths(Long wardId,Long constituencyId, Long userId,Long publicationDateId)
				 {
					 List<SelectOptionVO> selectOptionVOList = null;
					 try{
						 List<Object[]> list = userVoterDetailsDAO.getBoothsForCustomWard(wardId, constituencyId, publicationDateId, userId);
						 if(list != null && list.size() > 0)
						 {
							 selectOptionVOList = new ArrayList<SelectOptionVO>(0);
							 for(Object[] params : list)
								 selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
							
						 }
						 return selectOptionVOList;
					 }catch (Exception e) {
						e.printStackTrace();
						log.error("Exception Occured in getCustomWardBooths() method,Exception - "+e);
						return selectOptionVOList;
					}
					 
				 }
					public List<SelectOptionVO> getMandals(List<SelectOptionVO> list)
					{
					List<SelectOptionVO> resultlist = new ArrayList<SelectOptionVO>();
					try{
					if(list != null && list.size() > 0)
					{
					for(SelectOptionVO params : list)
					{
						if(params.getId().toString().substring(0,1).trim().equalsIgnoreCase("2"))
							resultlist.add(new SelectOptionVO(params.getId(),params.getName().toString()));	
					}
					}
					return resultlist;
					}
					catch(Exception e)
					{
						e.printStackTrace();
						log.error("Exception Occured in getMandals() - "+e);
						return resultlist;
					}
					
					}
					public List<SelectOptionVO> getMuncipalities(List<SelectOptionVO> list)
					{
					List<SelectOptionVO> resultlist = new ArrayList<SelectOptionVO>();
					try{
					if(list != null && list.size() > 0)
					{
					for(SelectOptionVO params : list)
					{
						if(params.getId().toString().substring(0,1).trim().equalsIgnoreCase("1"))
							resultlist.add(new SelectOptionVO(params.getId(),params.getName().toString()));	
					}
					}
					return resultlist;
					}
					catch(Exception e)
					{
						e.printStackTrace();
						log.error("Exception Occured in getMuncipalities() - "+e);
						return resultlist;
					}
					
					}
					
					public List<PartyVotesEarnedVO> getPreviousElectionVotingTrends(Long id, Long publicationDateId,Long constituencyId, String type)
					{
						Long assemblyLclEleBdyId = 0l;
						List<Long> wardIds = null;
						List<PartyVotesEarnedVO> partyVotesEarnedVOList = new ArrayList<PartyVotesEarnedVO>(0);
						List<PartyVotesEarnedVO> returnVOList = new ArrayList<PartyVotesEarnedVO>(0);
						try{
							List<Object[]> list = null;
							List<Object[]> localEleclist = null;
							List<Object[]> mptczptcList = null;
							Constituency constituency = constituencyDAO.get(constituencyId);
							String type2 = null;
                            List<Long> constituencyIdsList = new ArrayList<Long>(0);
                            List<Long> tehsilIds = new ArrayList<Long>();
    						List<SelectOptionVO> mandals = new ArrayList<SelectOptionVO>();
    						if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
    						{
                            mandals =regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, null);
    						mandals =regionServiceDataImp.getMandals(mandals);
    						if(mandals != null && mandals.size() > 0)
    						{
    						for(SelectOptionVO mandalId : mandals)
    							tehsilIds.add(new Long(mandalId.getId().toString().substring(1)));
    						}
						}
						
							List parliamentList = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
							if(parliamentList != null && parliamentList.size() > 0)
							{
								for(Object[] params : (List<Object[]>)parliamentList)
								{
									if(!constituencyIdsList.contains(params[0]))
										constituencyIdsList.add((Long)params[0]);
								}
							}
							if(!constituencyIdsList.contains(constituencyId))
								constituencyIdsList.add(constituencyId);
							/*if(type.equalsIgnoreCase(IConstants.CONSTITUENCY) || "booth".equalsIgnoreCase(type) || "ward".equalsIgnoreCase(type) || type.equalsIgnoreCase(IConstants.CUSTOMWARD))
							{
								if(constituencyIdsList != null && constituencyIdsList.size() > 0)
									list = constituencyElectionDAO.findAllEleHappendInAConstituency(constituencyIdsList);
								
							}*/
							if("booth".equalsIgnoreCase(type) || "ward".equalsIgnoreCase(type) || type.equalsIgnoreCase(IConstants.CUSTOMWARD))
							{
								if(constituencyIdsList != null && constituencyIdsList.size() > 0)
									list = constituencyElectionDAO.findAllEleHappendInAConstituency(constituencyIdsList);
								
							}
							else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
							{
							list = constituencyElectionDAO.findAllEleHappendInAConstituency(constituencyIdsList);
							mptczptcList = electionDAO.findMptcZptcElections(constituency.getState().getStateId());
							list.addAll(mptczptcList);
							}
							else if(type.equalsIgnoreCase(IConstants.MANDAL))
							{
								if(id.toString().trim().substring(0, 1).equalsIgnoreCase("2"))
								{
									id = new Long(id.toString().trim().substring(1));
									list = hamletBoothElectionDAO.findAllElectionsHappendInAMandal(id,constituencyIdsList);
									mptczptcList = electionDAO.findMptcZptcElections(constituency.getState().getStateId());
									list.addAll(mptczptcList);
									type2 = IConstants.RURAL;
									tehsilIds.add(id);
								}
								else if(id.toString().trim().substring(0, 1).equalsIgnoreCase("1"))
								{
									assemblyLclEleBdyId = new Long(id.toString().trim().substring(1));
									List<Object> list2 = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().trim().substring(1)));
									id = (Long)list2.get(0);
									list = hamletBoothElectionDAO.findAllElectionsHappendInALocalElectionBody(id);
									wardIds = assemblyLocalElectionBodyWardDAO.getWardIdsByAssemblyLocalElectionBody(assemblyLclEleBdyId,constituencyId);
									if(wardIds != null && wardIds.size() > 0 && wardIds.get(0) != null){
										localEleclist = constituencyElectionDAO.findAllLocalEleHappendInAConstituency(wardIds);	
									}
									if(list == null || list.size() == 0){
										list = localEleclist;
									}else if(localEleclist != null && localEleclist.size() >0){
										list.addAll(localEleclist);
									}
										
									type2 = IConstants.URBAN;
								}
								
								
							}
							else if(type.equalsIgnoreCase("panchayat"))
							 list = hamletBoothElectionDAO.findAllElectionsHappendInAPanchayat(id);
							
							if(type.equalsIgnoreCase("ward") || type.equalsIgnoreCase(IConstants.CUSTOMWARD)){
								wardIds = new ArrayList<Long>();
								wardIds.add(id);
								localEleclist = constituencyElectionDAO.findAllLocalEleHappendInAConstituency(wardIds);	
								if(list == null || list.size() == 0){
									list = localEleclist;
								}else if(localEleclist != null && localEleclist.size() >0){
									list.addAll(localEleclist);
								}
								
							}
								
							if(list != null && list.size() > 0)
							{
								PartyVotesEarnedVO partyVotesEarnedVO = null;
								List<String> partiesList = new ArrayList<String>(0);
								List<Long> totalVoters = new ArrayList<Long>(0); 
								for(Object[] params : list)
								{
									Long polledVotes = 0l;
									partyVotesEarnedVO = new PartyVotesEarnedVO();
									Election electionObj = electionDAO.get((Long)params[0]);
									partyVotesEarnedVO.setElectionYear(electionObj.getElectionYear());
									partyVotesEarnedVO.setElectionType(params[1].toString());
									String elecType ="";
									if(electionObj.getElectionScope().getElectionType().getElectionType().equalsIgnoreCase("Parliament")){
										elecType ="Parliament";
									}else if("Assembly".equalsIgnoreCase(electionObj.getElectionScope().getElectionType().getElectionType())){
										elecType = "Assembly";
									}else{
										elecType = electionObj.getElectionScope().getElectionType().getElectionType();
									}
									if(params[1].toString().equalsIgnoreCase("BYE"))
										partyVotesEarnedVO.setReqType(elecType+" (Bye)");
									else
									    partyVotesEarnedVO.setReqType(elecType+"");
									String boothIdStr = "";
									
									List<Long> boothIdsList = null;
									if(type.equalsIgnoreCase(IConstants.CONSTITUENCY) )
										boothIdsList =  boothConstituencyElectionDAO.getBoothIdsByConstituencyId(id, (Long)params[0]);
									
									else if(type.equalsIgnoreCase(IConstants.MANDAL))
									{
										if(type2.equalsIgnoreCase(IConstants.RURAL))
										 boothIdsList = hamletBoothElectionDAO.getBoothIdsByMandalId(id, (Long)params[0]);
										else if(type2.equalsIgnoreCase(IConstants.URBAN)){
											  if("Parliament".equalsIgnoreCase(elecType) || "Assembly".equalsIgnoreCase(elecType))
												boothIdsList = boothConstituencyElectionDAO.getBoothIdsByLocalEleBodyId(id, (Long)params[0],constituencyId);
											  else{	
												getLocalElectionResults((Long)params[0],wardIds,id,partyVotesEarnedVO,partiesList);
												 if(partyVotesEarnedVO.getPartyVotesEarnedVOs() != null && partyVotesEarnedVO.getPartyVotesEarnedVOs().size() >0)
													 partyVotesEarnedVOList.add(partyVotesEarnedVO);
												  continue;
											  }
										 }
								   }
									
									else if(type.equalsIgnoreCase("panchayat"))
										boothIdsList = hamletBoothElectionDAO.getBoothIdsByPanchayatId(id, (Long)params[0]);
									
									else if(type.equalsIgnoreCase("booth"))
									{
										Booth booth = boothDAO.get(id);
										boothIdsList = boothConstituencyElectionDAO.getBoothIdsByConstituencyIdPartNo(constituencyId,(Long)params[0],booth.getPartNo());
									}
									else if(type.equalsIgnoreCase("ward") || type.equalsIgnoreCase(IConstants.CUSTOMWARD))
									{  
										if("Parliament".equalsIgnoreCase(elecType) || "Assembly".equalsIgnoreCase(elecType)){
											 try{
												 if(type.equalsIgnoreCase("ward"))
												   boothIdsList = boothConstituencyElectionDAO.getBoothIdsByWardId(id, (Long)params[0],constituencyId);
												 /*else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
													 boothIdsList = userVoterDetailsDAO.getBoothIdsByCustomWardId(id, constituencyId, publicationDateId);*/
												 
											 }catch(Exception e){}
										 }else{	
											getLocalElectionResults((Long)params[0],wardIds,null,partyVotesEarnedVO,partiesList);
											 if(partyVotesEarnedVO.getPartyVotesEarnedVOs() != null && partyVotesEarnedVO.getPartyVotesEarnedVOs().size() >0)
												 partyVotesEarnedVOList.add(partyVotesEarnedVO);
											  continue;
										  }
									}
									 if(boothIdsList != null && boothIdsList.size() > 0)
									{
										 partyVotesEarnedVO.setTotalBooths(boothIdsList.size());
											for(Long boothId :boothIdsList)
												boothIdStr = boothIdStr + boothId.toString()+",";
											boothIdStr = boothIdStr.substring(0,boothIdStr.length()-1);
											totalVoters = boothDAO.getTotalaVotesByBoothIds(boothIdsList);
									}
									
									if(boothIdStr.isEmpty() && !elecType.equalsIgnoreCase("MPTC") && !elecType.equalsIgnoreCase("ZPTC"))
										continue;
									
									if(totalVoters != null && totalVoters.size() > 0 && !totalVoters.isEmpty() && !elecType.equalsIgnoreCase("MPTC") && !elecType.equalsIgnoreCase("ZPTC"))
										partyVotesEarnedVO.setTotalVotes(totalVoters.get(0));
											
									//List<PartyVotesEarnedVO> votesEarnedVOs = constituencyPageService.getPanchayatWiseElectionsForTehsil(boothIdStr,(Long)params[0]);
									if(elecType.equalsIgnoreCase("MPTC") || elecType.equalsIgnoreCase("ZPTC"))
									{
										boothIdStr = "";
										
										partyVotesEarnedVO.setTotalVotes(0l);
									}
									
									List<PartyVotesEarnedVO> votesEarnedVOs  = new ArrayList<PartyVotesEarnedVO>();
									List nominationResult = null;
									if(elecType.equalsIgnoreCase("Assembly") && type.equalsIgnoreCase("constituency"))
									{
									  //votesEarnedVOs = constituencyPageService.getPanchayatWiseElectionsForTehsilforPreviousEle(boothIdStr,(Long)params[0],elecType,tehsilIds);
									
										
										
										 nominationResult = nominationDAO
												.getCandidatesInfoForTheGivenConstituency(
														constituencyId.toString(), electionObj.getElectionYear(),
														"Assembly");	
										
										if(nominationResult != null && nominationResult.size() >0)
										{
											for(Object obj:nominationResult)
											{
												Object[] result = (Object[])obj;
												
												PartyVotesEarnedVO vo = new PartyVotesEarnedVO();
												
												vo.setPartyId((Long)result[5]);
												vo.setPartyName(result[8].toString());
												
												Double d  =(Double)result[2];
												vo.setVotesEarned(Math.round(d));	
												
												if(result[4].toString().equalsIgnoreCase("1"))
													vo.setWonStatus(true);
												else
													vo.setWonStatus(false);
													
												
												votesEarnedVOs.add(vo);
											}
										}
										
										if(nominationResult != null && nominationResult.size() >0){
										
											Object[] obj =(Object[]) nominationResult.get(0);
											
											Double d  =(Double)obj[13];
										  // partyVotesEarnedVO.setTotalVotes(Math.round(d));
										}
										
										
										Long indVotes = 0L;
										for(int i=0;i<votesEarnedVOs.size();i++)
										{
											
											if(votesEarnedVOs.get(i).getPartyName().equalsIgnoreCase("IND"))
												indVotes += votesEarnedVOs.get(i).getVotesEarned();
											
										}
										
										for(int i=0;i<votesEarnedVOs.size();i++)
										{
											
											if(votesEarnedVOs.get(i).getPartyName().equalsIgnoreCase("IND"))
												votesEarnedVOs.get(i).setVotesEarned(indVotes);
											
										}
										
									}else{	
										if(!(elecType.equalsIgnoreCase("ZPTC") || elecType.equalsIgnoreCase("MPTC")) ){
										   votesEarnedVOs = constituencyPageService.getPanchayatWiseElectionsForTehsilforPreviousEle(boothIdStr,(Long)params[0],elecType,tehsilIds);
										}else if((elecType.equalsIgnoreCase("ZPTC") || elecType.equalsIgnoreCase("MPTC")) && tehsilIds != null && tehsilIds.size() > 0 ){
											votesEarnedVOs = constituencyPageService.getPanchayatWiseElectionsForTehsilforPreviousEle(boothIdStr,(Long)params[0],elecType,tehsilIds);
										}
											
									}
									for(PartyVotesEarnedVO partyVoters : votesEarnedVOs)
										polledVotes += partyVoters.getVotesEarned();
									

	              List<String> staticParties = Arrays.asList("INC","PRP","TDP","TRS","YSRC","CPI","CPM","AIMIM","BJP");
									
																		
	                            List<PartyVotesEarnedVO> votesEarnedVOs1  = new ArrayList<PartyVotesEarnedVO>();
	                                for(int i=0;i<votesEarnedVOs.size();i++)
	                                	if(staticParties.contains(votesEarnedVOs.get(i).getPartyName()))
	                                		votesEarnedVOs1.add(votesEarnedVOs.get(i));
								
	                                votesEarnedVOs = votesEarnedVOs1;
									for(PartyVotesEarnedVO partyVoters : votesEarnedVOs)
									{
										if(!partiesList.contains(partyVoters.getPartyName()))
											partiesList.add(partyVoters.getPartyName());
									}
								
									
									partyVotesEarnedVO.setPartyVotesEarnedVOs(votesEarnedVOs);
									partyVotesEarnedVOList.add(partyVotesEarnedVO);
									
									
									if(elecType.equalsIgnoreCase("Assembly") && type.equalsIgnoreCase("constituency"))
									{
										
										if(nominationResult != null && nominationResult.size() >0)
										{
											
											Object[] obj = (Object[])nominationResult.get(0);
											Double d  =(Double)obj[14];
										  partyVotesEarnedVO.setPolledVotes(Math.round(d));
										}
										
									}else
										  partyVotesEarnedVO.setPolledVotes(polledVotes);

								}
								Collections.sort(partiesList);
								for(PartyVotesEarnedVO  votesEarnedVO : partyVotesEarnedVOList)
									votesEarnedVO.setPartiesList(partiesList);
								
								for(PartyVotesEarnedVO  votesEarnedVO : partyVotesEarnedVOList)
								{
									List<PartyVotesEarnedVO> resultList = new ArrayList<PartyVotesEarnedVO>(0);
									for(String partyInList :partiesList)
									{
										PartyVotesEarnedVO partyResult = new PartyVotesEarnedVO();
										partyResult.setPartyName(partyInList);
										partyResult.setVotesEarned(0L);										
										for(PartyVotesEarnedVO  partiesResult : votesEarnedVO.getPartyVotesEarnedVOs())
										{
											if(partiesResult.getPartyName().equalsIgnoreCase(partyInList)){
												partyResult.setVotesEarned(partiesResult.getVotesEarned());		
												partyResult.setWonStatus(partiesResult.isWonStatus());
											}
											
										}
										resultList.add(partyResult);
									}
									
									votesEarnedVO.setPartyVotesEarnedVOs(resultList);
								}
								}
							
							if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))								
							for(PartyVotesEarnedVO  votesEarnedVO : partyVotesEarnedVOList)
							{
								String constType = votesEarnedVO.getReqType();
								String electionYear = votesEarnedVO.getElectionYear(); 
								Long totalVotes = votesEarnedVO.getTotalVotes();
								
								if(constType.equalsIgnoreCase("Parliament"))								
									for(PartyVotesEarnedVO  vo : partyVotesEarnedVOList)
										if(vo.getElectionYear().equalsIgnoreCase(electionYear) && vo.getReqType().equalsIgnoreCase("Assembly"))
											votesEarnedVO.setTotalVotes(vo.getTotalVotes());
										
								
							}
								
                            if(partyVotesEarnedVOList != null){
                            	for(PartyVotesEarnedVO vo:partyVotesEarnedVOList){
                            		if(vo.getPolledVotes() != null && vo.getPolledVotes() > 0){
                            			returnVOList.add(vo);
                            		}
                            	}
                            }
							
							return returnVOList;
						}catch (Exception e) {
							e.printStackTrace();
							log.error("Exception Occured in getPreviousElectionVotingTrends() Method, Exception - "+e);
							return null;
						}
						
					}
					
					/*public void addMptcResultToPreviousElectionVoting(List<Object[]> mptczptcList,List<PartyVotesEarnedVO> resultList,Long constituencyId)
					{
						Constituency constituency = constituencyDAO.get(constituencyId);
						List<String> partiesList = new ArrayList<String>(0);
						List<String> partiesList1 = new ArrayList<String>(0);
						List<Long> tehsilIds = new ArrayList<Long>();
						List<Long> electionIds = new ArrayList<Long>();
						List<SelectOptionVO> mandals = new ArrayList<SelectOptionVO>();
						List<Object[]> result = null;
						mandals =regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, null);
						mandals =regionServiceDataImp.getMandals(mandals);
						if(mandals != null && mandals.size() > 0)
						{
						for(SelectOptionVO mandalId : mandals)
							tehsilIds.add(new Long(mandalId.getId().toString().substring(1)));
						}
						if(mptczptcList != null && mptczptcList.size() > 0)
						{
						for(Object[] params : mptczptcList)
							electionIds.add((Long)params[0]);
						}
						for(Object[] params : mptczptcList)
						result = nominationDAO.findAllMptcAndZptcElectionsInfo(electionIds,tehsilIds);
						
					
						if(result != null && result.size() > 0)
							for(Object[] params : result)
							{
							PartyVotesEarnedVO partyResult = new PartyVotesEarnedVO();
							partyResult.setTotalBooths(0);
							partyResult.setTotalVotes(0l);
							partyResult.setPolledVotes(0l);
							partyResult.setPartyName(params[0].toString());
							partyResult.setElectionType(params[5].toString());									
							partyResult.setVotesEarned(((Double)params[1]).longValue());	
							partyResult.setReqType(params[3].toString() +"(" +params[5].toString() + ")");
							partyResult.setElectionYear(params[2].toString());
							partyResult.setPartyId((Long)params[6]);
							partyResult.setPartiesList(resultList.get(0).getPartiesList());
							resultList.add(partyResult);
							}
						
					
					}
					
		*/
	 public void getBoothsComparisionInfo(List<Election> electionIds,Long panchayatId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO){
		try{ 
		 List<Object[]> presentBoothsList = boothDAO.getBoothsInAPanchayat(panchayatId,publicationDateId);
		 Panchayat panchayat = panchayatDAO.get(panchayatId);
		 PublicationDate publicationDate = publicationDateDAO.get(publicationDateId);
		 
		 List<Object[]> previousBoothsList = null;
		 Election electionObj = null;
		 Map<Long,String> boothsAdded = new HashMap<Long,String>();
		 Map<Long,String> boothsRemoved = new HashMap<Long,String>();
		 List<String> newlyAdded = new ArrayList<String>();
		 List<String> completelyRemoved = new ArrayList<String>();
		 List<String> otherComment = new ArrayList<String>();
		 Integer presentBoothsCount = presentBoothsList.size();
		 Integer prevBoothsCount = null;
		 String presentBooths = "";
		 String prevBooths = "";
		 String prevElecYear = null;

		 for(Election election:electionIds){
			 if("MAIN".equalsIgnoreCase(election.getElecSubtype())){
				 electionObj = election;
				 prevElecYear = election.getElectionYear();
				 previousBoothsList = hamletBoothElectionDAO.getBoothsInAPanchayat(election.getElectionId(),panchayatId);
			 }
			 
		 }
		 if(previousBoothsList == null || previousBoothsList.size() == 0)
			 return;
		 votersInfoForMandalVO.setPanchayatInfoPresent(true);
		  prevBoothsCount = previousBoothsList.size();
		  
		  for(Object[] previousBooth:previousBoothsList){
			   boolean boothRemoved = true;
			  for(Object[] presentBooth:presentBoothsList){
				  if(presentBooth[1].toString().trim().equalsIgnoreCase(previousBooth[1].toString().trim()))
					  boothRemoved = false;
			  }
			  if(boothRemoved)
				  boothsRemoved.put((Long)previousBooth[0], previousBooth[1].toString().trim());
			  prevBooths = prevBooths+" "+previousBooth[1].toString();
		  }
		  
		  for(Object[] presentBooth:presentBoothsList){
			   boolean boothAdded = true;
			  for(Object[] previousBooth:previousBoothsList){
				  if(presentBooth[1].toString().trim().equalsIgnoreCase(previousBooth[1].toString().trim()))
					  boothAdded = false;
			  }
			  if(boothAdded)
				  boothsAdded.put((Long)presentBooth[0], presentBooth[1].toString().trim());
			  presentBooths = presentBooths+" "+presentBooth[1].toString();
		  }
		  for(Long key:boothsRemoved.keySet()){
			  String partNo = boothsRemoved.get(key);
			  Booth booth = boothDAO.get(key);
			   List<Object[]> boothInfo = boothDAO.getBoothInfo(publicationDateId,booth.getConstituency().getConstituencyId(),partNo);
			   if(boothInfo != null && boothInfo.size() >0)
				   otherComment.add("In "+publicationDate.getYear()+" booth-"+partNo+" is moved from "+panchayat.getPanchayatName()+" Panchayat to "+boothInfo.get(0)[1].toString()+" Panchayat");
			   else
				   completelyRemoved.add(partNo); 
		  }
		  for(Long key:boothsAdded.keySet()){
			  String partNo = boothsAdded.get(key);
			  Booth booth = boothDAO.get(key);
			   List<Object[]> boothInfo = hamletBoothElectionDAO.getPanchayatByBoothElec(electionObj.getElectionId(),partNo,booth.getConstituency().getConstituencyId());
			   if(boothInfo != null && boothInfo.size() >0)
				   otherComment.add("In "+publicationDate.getYear()+" booth-"+partNo+" is moved from "+boothInfo.get(0)[1].toString()+" Panchayat to "+panchayat.getPanchayatName()+" Panchayat");
			   else
				   newlyAdded.add(partNo); 
		  }
		  votersInfoForMandalVO.setNewlyAdded(newlyAdded);
		  votersInfoForMandalVO.setCompletelyRemoved(completelyRemoved);
		  votersInfoForMandalVO.setOtherComment(otherComment);
		  votersInfoForMandalVO.setPresentBoothsCount(presentBoothsCount);
		  votersInfoForMandalVO.setPrevBoothsCount(prevBoothsCount);
		  votersInfoForMandalVO.setPresentBooths(presentBooths);
		  votersInfoForMandalVO.setPrevBooths(prevBooths);
		  votersInfoForMandalVO.setElectionYear(electionObj.getElectionYear());
		}catch(Exception e){
			log.error("Exception Occured in getBoothsComparisionInfo Method, Exception is - ",e);
		}
	 }
	 
	 
	 public List<SelectOptionVO> getConstituenciesFromBoothPublicationVoter()
	 {
		 List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>(0);
		 
		 SelectOptionVO list = null;
		 try{
			List<Object[]> constituencies = boothPublicationVoterDAO.getConstituencies();
			if(constituencies!=null && constituencies.size() > 0)
			{
				for(Object[] params : constituencies)
				{
					list = new SelectOptionVO((Long)params[0],params[1].toString());
					resultList.add(list);
				}
			}
			return resultList;
		 }
		 catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getConstituenciesFromBoothPublicationVoter() method -"+e);
			return resultList;
		}
		
	 }
	 public List<SelectOptionVO> getConstituenciesFromVoterDataAvaliableConstituencies()
	 {
		 List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>(0);
		 
		 SelectOptionVO list = null;
		 try{
			List<Object[]> constituencies = voterDataAvailableConstituenciesDAO.getConstituencies();
			if(constituencies!=null && constituencies.size() > 0)
			{
				for(Object[] params : constituencies)
				{
					list = new SelectOptionVO((Long)params[0],params[1].toString());
					resultList.add(list);
				}
			}
			return resultList;
		 }
		 catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getConstituenciesFromBoothPublicationVoter() method -"+e);
			return resultList;
		}
		
	 }
	 public VoterHouseInfoVO getUserVoterCategories(Long userId){
		 VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
		  try{
			 List<SelectOptionVO> categoriesList = new ArrayList<SelectOptionVO>();
			 voterHouseInfoVO.setCategory(categoriesList);
			 List<Object[]> userCategoryValuesList = userVoterCategoryDAO.getCategoryValuesList(userId);
			 SelectOptionVO selectOptionVO = null;
			 for(Object[] category:userCategoryValuesList){
				 selectOptionVO = new SelectOptionVO();
				 selectOptionVO.setId((Long)category[0]);
				 selectOptionVO.setName(category[1]!=null?category[1].toString():"");
				 categoriesList.add(selectOptionVO);
			 }
		  }catch(Exception e){
			  log.error("Exception Occured in getUserVoterCategories Method, Exception is - ",e);
		  }
		 return voterHouseInfoVO;
	 }
	 
	 public VoterHouseInfoVO getSelectedCategoryOptions(VoterHouseInfoVO parameters){
		  VoterHouseInfoVO votersHouseInfoVO = parameters;
		  try{
		 	    SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
		 	    defaultSelectOptionVO.setId(0l);
		 	    defaultSelectOptionVO.setName("Select");
		 	    
		 	    
		 	   if( parameters.isAll() || parameters.isGroupPresent())
		 	   { 
		 		    
                   
			 	  List<Long> locationValuesList = new ArrayList<Long>();
					// locationValuesList.add(locationValue);
					 
			 	 List<Object[]> customGroups = null;
		           
				  if(parameters.isMandal()){
						  locationValuesList.add(parameters.getGroupLocationValue());			 
					  
					  customGroups =   customVoterGroupDAO.getCustomVoterGroupsByLocationValueAndAreaTypeAndConstituencyId (parameters.getUserId(), locationValuesList,IConstants.AREA_TYPE_RURAL,parameters.getConstituencyId());

				  }else if(parameters.isMuncipality()){
					  
					  
					  Long lid = (Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(parameters.getGroupLocationValue()).get(0); 
					  locationValuesList.add(lid);
					 // locationValuesList.add(parameters.getGroupLocationValue());		
					  customGroups =   customVoterGroupDAO.getCustomVoterGroupsByLocationValueAndAreaTypeAndConstituencyId(parameters.getUserId(), locationValuesList,IConstants.AREA_TYPE_URBAN,parameters.getConstituencyId());		  
				  }else  if(parameters.isConstituency()){
					  List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(parameters.getConstituencyId());
						Long delimitationConstituencyID = delimitationConstituency.get(0).getDelimitationConstituencyID();
						List<Tehsil> mandals = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);
						if(mandals != null && mandals.size() > 0)
						{						SelectOptionVO selectOptionVO = null;
							for (Tehsil tehsil : mandals)						
								locationValuesList.add(tehsil.getTehsilId());
							
						}	
						  customGroups =   customVoterGroupDAO.getCustomVoterGroupsByLocationValueAndAreaTypeAndConstituencyId(parameters.getUserId(), locationValuesList,IConstants.AREA_TYPE_RURAL,parameters.getConstituencyId());

				  }	
			 	  
			 	  
					//customGroups = customVoterGroupDAO.getCustomVoterGroupsByLocationValue(parameters.getUserId() ,locationValuesList);
					 
					 List<SelectOptionVO> customGroupsList = new ArrayList<SelectOptionVO>();
					 SelectOptionVO defaultVo = new SelectOptionVO();
					 defaultVo.setId(0L);
					 defaultVo.setName("Select");
					 customGroupsList.add(defaultVo);
					 
					 for(Object[] obj:customGroups)
					 {
						 SelectOptionVO vo = new SelectOptionVO();
						 vo.setId((Long)obj[0]);
						 vo.setName(obj[1].toString());
						 customGroupsList.add(vo);
						 
					 }
					 
					 votersHouseInfoVO.setCustomGroups(customGroupsList);
			 	   
		 	   }
		 	   
		 	   
		 	   if( parameters.isAll() || parameters.isLocalityPresent())
		 	   { 
		 		    SelectOptionVO defaultSelectOptionVO1 = new SelectOptionVO();
			 	    defaultSelectOptionVO1.setId(0l);
			 	    defaultSelectOptionVO1.setName("0l");
			 	   defaultSelectOptionVO1.setValue("select");
			 	   if(parameters.getSelType().equalsIgnoreCase("muncipality") && parameters.getSelectedType().equalsIgnoreCase("booth"))
				 	 buildLocalitiesBasedOnTypeAndIdForMuncipality(votersHouseInfoVO,parameters.getVoterId(),parameters.getUserId(),defaultSelectOptionVO1);  
  		 	       else
		 		     buildLocalitiesBasedOnTypeAndId(votersHouseInfoVO,parameters.getVoterId(),parameters.getUserId(),defaultSelectOptionVO1);  
		 		  
		 	   }
		 	    if(parameters.isPartyPresent() || parameters.isCastPresent() || parameters.isAll() || parameters.isLocalityPresent())
		 	       getPartiesAndCastsInVotersState(votersHouseInfoVO,parameters.getVoterId(),parameters.getUserId(),defaultSelectOptionVO);
		 	     if(parameters.isAll() || parameters.getIds().size() > 0){
		 	       List<Object[]> userCategoryValuesList = userVoterCategoryDAO.getCategoryValuesList(parameters.getUserId());
		 	       VoterHouseInfoVO category = null;
		 	       List<VoterHouseInfoVO> categoriesList = new ArrayList<VoterHouseInfoVO>();
		 	       for(Object[] userCategoryValue : userCategoryValuesList)
		 			{ 
		 	    	     if(parameters.isAll() || parameters.getIds().contains((Long)userCategoryValue[0])){
			 				 category = new VoterHouseInfoVO();
			 				 getAllCategoryOptions(userCategoryValue,category,defaultSelectOptionVO,parameters.getUserId());
			 				 categoriesList.add(category);	
		 	    	     }
		 			}	
		 	      votersHouseInfoVO.setCategoriesList(categoriesList);
		 	     }
		   }catch(Exception e){
		 	  log.error("Exception rised in getSelectedCategoryOptions ",e);
		   }
		 	return votersHouseInfoVO;
		  
	  }
	 
	
	  public VoterHouseInfoVO getSelectedCategoryOptionsForIndividual(List<VoterHouseInfoVO> voterIds,VoterHouseInfoVO parameters,Long userId){
		  VoterHouseInfoVO votersHouseInfoVO = parameters;
		 
		  
		  Map<Long,Map<String, List<VoterHouseInfoVO>>> boothMap = new HashMap<Long,Map<String, List<VoterHouseInfoVO>>>();
			Map<String, List<VoterHouseInfoVO>> voterByHouseNoMap = null;
			
			List<VoterHouseInfoVO> voterVOs = null;
			Map<Long , Long> groupDetailsMap = null;
			
		  try{
			  if(voterIds != null && voterIds.size() >0){

		 	    SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
		 	    defaultSelectOptionVO.setId(0l);
		 	    defaultSelectOptionVO.setName("Select");
		 	   if( parameters.isAll() || parameters.isLocalityPresent())
		 	   {
		 		    SelectOptionVO defaultSelectOptionVO1 = new SelectOptionVO();
		 		    defaultSelectOptionVO1.setId(0l);
			 	    defaultSelectOptionVO1.setName("0l");
			 	    defaultSelectOptionVO1.setValue("select");
 
			 	     
			 	 if(parameters.getSelType().equalsIgnoreCase("muncipality") && parameters.getSelectedType().equalsIgnoreCase("booth"))
					 buildLocalitiesBasedOnTypeAndIdForMuncipality(votersHouseInfoVO,parameters.getVoterId(),parameters.getUserId(),defaultSelectOptionVO1);  
	  		 	 else   
		 		     buildLocalitiesBasedOnTypeAndId(votersHouseInfoVO,voterIds.get(0).getVoterId(),parameters.getUserId(),defaultSelectOptionVO1);  
		 		
		 	   }
		 	   if(parameters.isPartyPresent() || parameters.isCastPresent() || parameters.isAll() || parameters.isLocalityPresent())
		 	    getPartiesAndCastsInVotersState(votersHouseInfoVO,voterIds.get(0).getVoterId(),parameters.getUserId(),defaultSelectOptionVO);
		 	   
		 	   if(parameters.isGroupPresent()){
		 		   
		 		  groupDetailsMap = new HashMap<Long, Long>();
		 		  
		 		  getCustomVoterGroups(votersHouseInfoVO,voterIds.get(0).getVoterId(),parameters.getUserId(),defaultSelectOptionVO,parameters.getConstituencyId());
		 		  
		 		  List<Long> votersIds = new ArrayList<Long>();
		 		  for(VoterHouseInfoVO vo:voterIds)
		 			  votersIds.add(vo.getVoterId());
		 		  
		 		  List<Object[]> votersGroupDetails =  customVoterDAO.getAllVotersGroups(votersIds , votersHouseInfoVO.getUserId());
		 		  
		 		  for(Object[] obj:votersGroupDetails)
		 			 groupDetailsMap.put((Long)obj[0], (Long)obj[1]);
		 	   }
		 	   
		 	   
		 	    List<Object[]> userCategoryValuesList = null;
		 	   List<Object[]> selectedUserCategoryValuesList = new ArrayList<Object[]>();
		 	   if(parameters.isAll() || parameters.getIds().size() > 0){
		 	     userCategoryValuesList = userVoterCategoryDAO.getCategoryValuesList(parameters.getUserId());
			 	   if(parameters.isAll()){
			 		  selectedUserCategoryValuesList = userCategoryValuesList;
			 	   }else{
		 	        for(Long catId : parameters.getIds()){
			 	    	 for(Object[] userCategoryValue:userCategoryValuesList){
			 	    		 if(catId.longValue() == ((Long)userCategoryValue[0]).longValue()){
			 	    			selectedUserCategoryValuesList.add(userCategoryValue);
			 	    		 }
			 	    	 }
			 	     }
			 	   }
		 	   }
		 	   VoterHouseInfoVO voterHouseInfoVO = null;
		 	   
		 	    for(VoterHouseInfoVO voter : voterIds){
		 	    	
		 	     voterHouseInfoVO = new VoterHouseInfoVO();
		 	     
		 	     if((groupDetailsMap !=null) && groupDetailsMap.get(voter.getVoterId()) != null)
		 	    	voterHouseInfoVO.setCustomGroupId(groupDetailsMap.get(voter.getVoterId()));
		 	     
		 	     getVoterBasicInfo1(voterHouseInfoVO,voter.getVoterId(),parameters.getPublicationId(),userId);
		 	   
		 	     
		 	    voterByHouseNoMap = boothMap.get(voter.getBoothId());
				if( voterByHouseNoMap == null){
					voterByHouseNoMap = new HashMap<String, List<VoterHouseInfoVO>>();
					boothMap.put(voter.getBoothId(), voterByHouseNoMap);
				}
				voterVOs = voterByHouseNoMap.get(voterHouseInfoVO.getHouseNo());
				if(voterVOs ==null){
					voterVOs = new ArrayList<VoterHouseInfoVO>();
					voterByHouseNoMap.put(voterHouseInfoVO.getHouseNo(), voterVOs);
				}
				voterVOs.add(voterHouseInfoVO);
				if(parameters.isPartyPresent() || parameters.isCastPresent() || parameters.isAll()  || parameters.isLocalityPresent())
		 	       voterSelectedCastAndPartyDetails(voterHouseInfoVO,voter.getVoterId(),parameters.getUserId());
				if(parameters.isAll() || selectedUserCategoryValuesList.size() > 0){    
			 	     List<VoterHouseInfoVO> categoriesList = new ArrayList<VoterHouseInfoVO>();
			 	   
			 	     getVoterSelectedCategoryValues(selectedUserCategoryValuesList,defaultSelectOptionVO,parameters.getUserId(),voter.getVoterId(),categoriesList);
			 	   
			 	     voterHouseInfoVO.setCategoriesList(categoriesList);
				}
		 	     
		 	   }
		 	   List<VoterHouseInfoVO> boothsList = new ArrayList<VoterHouseInfoVO>();
		 	  List<VoterHouseInfoVO> familiesList = null;
		 	    for(Long key:boothMap.keySet()){
		 	    	VoterHouseInfoVO booth = new VoterHouseInfoVO();
		 	    	boothsList.add(booth);
		 	    	booth.setBoothId(key);
		 	    	VoterHouseInfoVO boothInfo = getBoothDetailsForVoter(key);
		 	    	booth.setBoothName(boothInfo.getBoothName());
		 	    	booth.setVilliageCovered(boothInfo.getVilliageCovered());
		 	    	booth.setPanchayatName(boothInfo.getPanchayatName());
		 	    	Map<String, List<VoterHouseInfoVO>> families = boothMap.get(key);
		 	    	familiesList = new ArrayList<VoterHouseInfoVO>();
		 	    	for(String familyKey:families.keySet()){
		 	    		VoterHouseInfoVO family = new VoterHouseInfoVO();
		 	    		familiesList.add(family);
		 	    		family.setHouseNo(familyKey);
		 	    		family.setVotersList(families.get(familyKey));
		 	    	}
		 	    	booth.setFamiliesList(familiesList);
		 	    }
		 	    
		 	   votersHouseInfoVO.setBoothsList(boothsList);
			  }
		   }catch(Exception e){
		 	  log.error("Exception rised in getSelectedCategoryOptionsForIndividual ",e);
		   }
		 	return votersHouseInfoVO;
		  
	  }
	  
	  public void updateSelectedFieldsForAllVoters(VoterHouseInfoVO voterHouseInfoVO,String[] voterIds,String partyCast,boolean groupPresent){
		try{ 
		  for(String voterId:voterIds){ 
			 voterHouseInfoVO.setVoterId(new Long(voterId));
		    updateVoterDetails(voterHouseInfoVO,partyCast,groupPresent);
		  }
		}catch(Exception e){
			log.error("Exception rised in updateSelectedFieldsForAllVoters ",e);
		}
	  }
	  
	  public Double calculateMaleAndFemalePercentage(Long totalVoters, Long MaleOrFemaleVoters)
		{
			try{
				if(totalVoters != null && totalVoters != 0)
					return (MaleOrFemaleVoters.doubleValue()*100.0)/totalVoters.doubleValue();
				else
					return 0.00;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in calculateMaleAndFemalePercentage() Method, Exception - "+e);
				return 0.00;
			}
			
		}
	  
	  public Long getVoterFamilyRangeIdByFamilyRange(String familyRange)
		{
			try{
				return voterFamilyRangeDAO.getVoterFamilyRangeIdByFamilyRange(familyRange);
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVoterFamilyRangeIdByFamilyRange() Method, Exception - "+e);
				return 0l;
			}
		}
	  
	  public Long getVoterAgeRangeId(String ageRange)
		 {
			 try{
				 return voterAgeRangeDAO.getVoterAgeRangeIdByType(ageRange);
			 }catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVoterAgeRangeId() Method, Exception - "+e);
				return 0l;
			}
		 }
	  
	  
	  public VotersInfoForMandalVO getVotersDetailsByVoterReportLevelId(Long reportLevelId, Long reportLevelValue, Long publicationDateId, String name, String type,Long constituencyId)
		 {
			VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
			try{
				
				List<VoterInfo> list = voterInfoDAO.getVotersCount(reportLevelId, reportLevelValue, publicationDateId,constituencyId);
				if(list == null || list.size() == 0)
				{
					votersInfoForMandalVO.setDatapresent(false);
					return votersInfoForMandalVO;
				}
				if(list != null && list.size() > 0)
				{
					for(VoterInfo voterDetails : list)
					{
						BigDecimal unknowCount = new BigDecimal(voterDetails.getTotalVoters().longValue()-(voterDetails.getMaleVoters().longValue() + voterDetails.getFemaleVoters().longValue()));
						votersInfoForMandalVO.setTotVoters(voterDetails.getTotalVoters() != null ? new BigDecimal(voterDetails.getTotalVoters()):new BigDecimal(0.00));
						votersInfoForMandalVO.setTotalVoters(voterDetails.getTotalVoters() != null ? voterDetails.getTotalVoters().toString():"0.00");
						votersInfoForMandalVO.setTotalMaleVoters(voterDetails.getMaleVoters() != null ?voterDetails.getMaleVoters().toString():"0.00");
						votersInfoForMandalVO.setTotalFemaleVoters(voterDetails.getFemaleVoters() != null?voterDetails.getFemaleVoters().toString():"0.00");
					    votersInfoForMandalVO.setTotalVotersPercentage(voterDetails.getTotalVotersPercentage() != null?voterDetails.getTotalVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setTotalFemalePercentage(voterDetails.getFemaleVotersPercentage() != null? voterDetails.getFemaleVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setTotalMalePercentage(voterDetails.getMaleVotersPercentage() != null ? voterDetails.getMaleVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setUnKnowVoters(unknowCount != null ? unknowCount.toString(): "0.00");
						votersInfoForMandalVO.setPercent(voterDetails.getTotalVotersPercentage() != null?voterDetails.getTotalVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setMaleVoters(voterDetails.getMaleVoters());
						votersInfoForMandalVO.setFemaleVoters(voterDetails.getFemaleVoters());
						votersInfoForMandalVO.setTotPercent(voterDetails.getTotalVotersPercentage()!=null?(new BigDecimal(voterDetails.getTotalVotersPercentage()).setScale(2, BigDecimal.ROUND_HALF_UP)):new BigDecimal("0.00") );
						votersInfoForMandalVO.setType(type);
						votersInfoForMandalVO.setName(name);
						votersInfoForMandalVO.setDatapresent(true);
					}
				}
				return votersInfoForMandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersCount() Method, Exception - "+e);
				return null;
			}
		 } 
	  
	  public ResultStatus insertVotersDataInIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId,boolean hamletChecked,boolean hamletBoothChecked,boolean localityChecked)
	  {
		  log.info(" Entered into insertVotersDataInIntermediateTables() Method, with Values - Report Level Value - "+reportLevelValue+" and Publicarion Date Id - "+publicationDateId);
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  List<Long> mandalIdsList = new ArrayList<Long>(0);
			  List<SelectOptionVO> panchayatsList = new ArrayList<SelectOptionVO>(0);
			  List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>(0);
			  List<SelectOptionVO> wardsList = new ArrayList<SelectOptionVO>(0);
			  List<Long>panchayatIdsList = new ArrayList<Long>(0);
			  List<Long> localBodiesList = new ArrayList<Long>(0);
			  List<Long> boothIdsList = new ArrayList<Long>(0);
			  List<SelectOptionVO> partialPanchayatsList = new ArrayList<SelectOptionVO>(0);
			  List<Long> partialPanchayatIdsList = new ArrayList<Long>(0);
			  
			  resultStatus = calculateAndInsertVoterInfoForALocation(IConstants.CONSTITUENCY,reportLevelValue,null,publicationDateId,reportLevelValue,userId,false);
			  calculateAndInsertVoterFamilyInfoForALocation(IConstants.CONSTITUENCY,reportLevelValue,publicationDateId,reportLevelValue,userId,false);
			  //calculateAndInsertVoterAgeInfoForALocation(IConstants.CONSTITUENCY, reportLevelValue, publicationDateId,reportLevelValue,userId);
			  
			  List<Long> constituencyIdsList = new ArrayList<Long>();
			  constituencyIdsList.add(reportLevelValue);
			  calculateAndInsertVoterAgeInfo(IConstants.CONSTITUENCY,reportLevelValue,publicationDateId,userId,constituencyIdsList,false);
		
			  List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(reportLevelValue,IConstants.PRESENT_YEAR, null);
			  
			  if(mandalsList == null || mandalsList.size() == 0)
				  return null;
			  
			  for(SelectOptionVO selectOptionVO : mandalsList)
			  {
				  if(selectOptionVO.getId().toString().substring(0,1).equalsIgnoreCase(IConstants.RURAL_TYPE))
					  mandalIdsList.add(new Long(selectOptionVO.getId().toString().substring(1)));
				  else
					  localBodiesList.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(selectOptionVO.getId().toString().substring(1))).get(0));
			  }
			  List<Object[]> list = null;
			  List<Object[]> partialList = null;
			  if(mandalIdsList != null && mandalIdsList.size() >0){ 
				   partialList =  partialBoothPanchayatDAO.getPartialPanchayats(reportLevelValue,publicationDateId);
				   if(partialList != null && partialList.size() >0){
						 for(Object[] params : partialList)
						 {
						  partialPanchayatsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
						  partialPanchayatIdsList.add((Long)params[0]);
						 }
				   }
				   if(partialPanchayatIdsList.size() > 0)
					 list = panchayatDAO.getPanchayatIdsForMandals(mandalIdsList,new HashSet<Long>(partialPanchayatIdsList));
				   else
				     list = panchayatDAO.getPanchayatIdsByMandalIdsList(mandalIdsList);
			  }
			 if(list != null && list.size() > 0)
			  {
				 for(Object[] params : list)
				 {
				  panchayatsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				  panchayatIdsList.add((Long)params[0]);
				 }
			  }
			 
			 if(mandalIdsList != null && mandalIdsList.size() > 0)
			  calculateAndInsertVoterAgeInfo(IConstants.MANDAL, reportLevelValue, publicationDateId, userId, mandalIdsList,false);
			  
			  for(Long mandalId : mandalIdsList)
			  {
				  calculateAndInsertVoterInfoForALocation(IConstants.MANDAL,mandalId,reportLevelValue, publicationDateId,reportLevelValue,userId,false);
				  calculateAndInsertVoterFamilyInfoForALocation(IConstants.MANDAL,mandalId,publicationDateId,reportLevelValue,userId,false);
				  //calculateAndInsertVoterAgeInfoForALocation(IConstants.MANDAL,mandalId,publicationDateId,reportLevelValue,userId);
			  }
			 
			 if(panchayatIdsList != null && panchayatIdsList.size() > 0)
			  calculateAndInsertVoterAgeInfo(IConstants.PANCHAYAT, reportLevelValue, publicationDateId, userId, panchayatIdsList,false);
			  
			 if(partialPanchayatIdsList != null && partialPanchayatIdsList.size() > 0)
			  calculateAndInsertVoterAgeInfo(IConstants.PANCHAYAT, reportLevelValue, publicationDateId, userId, partialPanchayatIdsList,true);
				  
			 //if(partialPanchayatIdsList != null && partialPanchayatIdsList.size() > 0)
				 // calculateAndInsertVoterAgeInfo(IConstants.PANCHAYAT, reportLevelValue, publicationDateId, userId, panchayatIdsList);
				  
			  for(SelectOptionVO selectOptionVO : panchayatsList)
			  {
				  calculateAndInsertVoterInfoForALocation(IConstants.PANCHAYAT,selectOptionVO.getId(),new Long(selectOptionVO.getName()), publicationDateId,reportLevelValue,userId,false);
				  calculateAndInsertVoterFamilyInfoForALocation(IConstants.PANCHAYAT,selectOptionVO.getId(),publicationDateId,reportLevelValue,userId,false);
				 //calculateAndInsertVoterAgeInfoForALocation(IConstants.PANCHAYAT,selectOptionVO.getId(),publicationDateId,reportLevelValue,userId);
			  }
			  for(SelectOptionVO selectOptionVO : partialPanchayatsList)
			  {
				  calculateAndInsertVoterInfoForALocation(IConstants.PANCHAYAT,selectOptionVO.getId(),new Long(selectOptionVO.getName()), publicationDateId,reportLevelValue,userId,true);
				  calculateAndInsertVoterFamilyInfoForALocation(IConstants.PANCHAYAT,selectOptionVO.getId(),publicationDateId,reportLevelValue,userId,true);
				 //calculateAndInsertVoterAgeInfoForALocation(IConstants.PANCHAYAT,selectOptionVO.getId(),publicationDateId,reportLevelValue,userId);
			  }
			  List<Object[]> list2 = null;
			  if(panchayatIdsList.size() > 0){
				  if(partialPanchayatIdsList.size() > 0){
					  panchayatIdsList.addAll(partialPanchayatIdsList);
				  }
				  list2 = boothDAO.getBoothIdsByPanchayatIdsInAPublication(panchayatIdsList, publicationDateId);
			  }
			  if(list2 != null && list2.size() > 0)
			  {
				  for(Object[] params : list2)
					  boothsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
			  }
			  
			  
			 // List<Long> wardsList = new ArrayList<Long>();
			  if(localBodiesList != null && localBodiesList.size() >0){
				  List<Long> ghmcLocalElecBodies = new ArrayList<Long>();
				  List<Long> otherLocalElecBodies = new ArrayList<Long>();
				  List<Object[]> localElecBodyTypes = localElectionBodyDAO.getLocalElectionBodyType(new HashSet<Long>(localBodiesList));
				for(Object[] localElecBody:localElecBodyTypes){
					if(((Long)localElecBody[0]).longValue() != 7){
						otherLocalElecBodies.add((Long)localElecBody[1]);
					  }else{
						  ghmcLocalElecBodies.add((Long)localElecBody[1]);
					  }
				}
				if(ghmcLocalElecBodies.size() > 0){
					  List<Object[]> wards = boothDAO.getWardsByLocalElecBodyIds(
							  ghmcLocalElecBodies, publicationDateId,reportLevelValue);
					  voterReportService.saveWardBoothData(ghmcLocalElecBodies,reportLevelValue,publicationDateId,"ward");
					if(wards != null && wards.size() >0){
						
						for(Object[] ward:wards)
						if(ward[0] != null){
							wardsList.add(new SelectOptionVO((Long)ward[0],ward[1].toString()));
						}		
					}
				}
				if(otherLocalElecBodies.size() > 0){
					try{
						voterReportService.saveWardBoothData(otherLocalElecBodies,reportLevelValue,publicationDateId,"cutomward");
						voterReportService.saveVoterFamilyInfoForCustomWards(otherLocalElecBodies,userId,publicationDateId,reportLevelValue,6l,"ward");
						voterReportService.saveVoterInfoForCustomWards(otherLocalElecBodies, userId, publicationDateId,reportLevelValue,6l,"ward");
						voterReportService.saveVoterAgeInfoForCustomWards(otherLocalElecBodies,userId,publicationDateId,reportLevelValue,6l,"ward");
						voterReportService.saveVoterFamilyInfoForCustomWards(otherLocalElecBodies,userId,publicationDateId,reportLevelValue,10l,"wardbooth");
						voterReportService.saveVoterInfoForCustomWards(otherLocalElecBodies, userId, publicationDateId,reportLevelValue,10l,"wardbooth");
						voterReportService.saveVoterAgeInfoForCustomWards(otherLocalElecBodies,userId,publicationDateId,reportLevelValue,10l,"wardbooth");
					}catch(Exception e){
						log.error("Exception Occured in insertVoterInfoDataToIntermediateTables() otherLocalElecBodies population, Exception is -",e);
					}
				}
				
			  }

			  if(localBodiesList != null && localBodiesList.size() > 0)
			  {
				  calculateAndInsertVoterAgeInfo(IConstants.LOCALELECTIONBODY, reportLevelValue, publicationDateId, userId, localBodiesList,false);
				  
				  for(Long localBodyId : localBodiesList)
				  {
					  calculateAndInsertVoterInfoForALocation(IConstants.LOCALELECTIONBODY,localBodyId,reportLevelValue, publicationDateId,reportLevelValue,userId,false);
					  calculateAndInsertVoterFamilyInfoForALocation(IConstants.LOCALELECTIONBODY,localBodyId,publicationDateId,reportLevelValue,userId,false);
					 // calculateAndInsertVoterAgeInfoForALocation(IConstants.LOCALELECTIONBODY,localBodyId,publicationDateId,reportLevelValue,userId);
				  }
				  List<Object[]> list3 = boothDAO.getBoothIdsInLocalBodiesForAPublication(localBodiesList,publicationDateId,reportLevelValue);
				  
				  if(list3 != null && list3.size() > 0)
				  {
					  for(Object[] params : list3)
						  boothsList.add(new SelectOptionVO((Long)params[0],params[1].toString())); 
				  }
				  
			  }
			  List<Long> wardIdsList = new ArrayList<Long>(0);
			  if(wardsList != null && wardsList.size() > 0)
			  {
               for(SelectOptionVO selectOptionVO:wardsList){
				  
            	   wardIdsList.add(selectOptionVO.getId());
				  calculateAndInsertVoterInfoForALocation(
						  IConstants.WARD,selectOptionVO.getId(),new Long(selectOptionVO.getName()), publicationDateId,reportLevelValue,userId,false);
				  calculateAndInsertVoterFamilyInfoForALocation(IConstants.WARD,new Long(selectOptionVO.getId()),publicationDateId,reportLevelValue,userId,false);
				  //calculateAndInsertVoterAgeInfoForALocation(IConstants.WARD,new Long(selectOptionVO.getId()),publicationDateId,reportLevelValue,userId);
			 
				  
			    }
			  }
			   if(wardIdsList != null && wardIdsList.size() > 0)
               calculateAndInsertVoterAgeInfo(IConstants.WARD, reportLevelValue, publicationDateId, userId, wardIdsList,false); 
               
			  for(SelectOptionVO selectOptionVO : boothsList)
				  if(!boothIdsList.contains(selectOptionVO.getId()))
					  boothIdsList.add(selectOptionVO.getId());
			  
			  calculateAndInsertVoterAgeInfo(IConstants.BOOTH, reportLevelValue, publicationDateId, userId, boothIdsList,false);
			  
			  for(Long boothId :boothIdsList)
			  {
				  SelectOptionVO selectOptionVO = null;
				  for(SelectOptionVO optionVO : boothsList)
				  if(optionVO.getId().equals(boothId))
				  {
					  selectOptionVO = optionVO;
					  break;
				  }
				  calculateAndInsertVoterInfoForALocation(IConstants.BOOTH,selectOptionVO.getId(),new Long(selectOptionVO.getName()), publicationDateId,reportLevelValue,userId,false);
				  calculateAndInsertVoterFamilyInfoForALocation(IConstants.BOOTH,selectOptionVO.getId(),publicationDateId,reportLevelValue,userId,false);
				  //calculateAndInsertVoterAgeInfoForALocation(IConstants.BOOTH,selectOptionVO.getId(),publicationDateId,reportLevelValue,userId);
			  }
			  List<Object[]> hamlets = null;
			  List<SelectOptionVO> hamletList = new ArrayList<SelectOptionVO>();
			  if(hamletChecked)
			  {
			  if(panchayatIdsList.size() > 0)
			  {
				  hamlets =panchayatHamletDAO.getHamletsOfPanchayats(panchayatIdsList); 
			  }
			  
			  if(hamlets != null && hamlets.size() > 0)
			  {
				  for(Object[] params :hamlets)
					  hamletList.add(new SelectOptionVO((Long)params[0],params[1].toString())); 
				  if(hamletList != null && hamletList.size() > 0)
					  for(SelectOptionVO option : hamletList)
					  {
				  calculateAndInsertVoterInfoForALocation(IConstants.HAMLET,option.getId(),new Long(option.getName()), publicationDateId,reportLevelValue,userId,false);
				  calculateAndInsertVoterFamilyInfoForALocation(IConstants.HAMLET,option.getId(),publicationDateId,reportLevelValue,userId,false);
				  //calculateAndInsertVoterAgeInfoForALocation(IConstants.HAMLET,option.getId(),publicationDateId,reportLevelValue,userId);
				  }
			  }
			  
			  if(hamlets != null && hamlets.size() >0)
			  {
				  List<Long> hamletIdsList = new ArrayList<Long>(0);
				  for(Object[] params:hamlets)
					hamletIdsList.add((Long)params[0]);
				  calculateAndInsertVoterAgeInfo(IConstants.HAMLET, reportLevelValue, publicationDateId, userId, hamletIdsList,false);
				  
			  }
			  }
			  
			  //HamletBooth
			  if(hamletBoothChecked)
			    getHamletBoothDataByConstituencyIdAndPublicationDateId(reportLevelValue,publicationDateId,userId);
			  if(localityChecked)
			    calculateAndInsertLocalityWiseVoterInfo(reportLevelValue,publicationDateId,userId);
 
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			  log.error("Exception Occured in insertVoterInfoDataToIntermediateTables(), Exception is -",e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		  }
	  }
	  
	  
	  //voterAgeInfo From Voter Table 
	  
	  public ResultStatus calculateAndInsertVoterAgeInfo(String locationType,Long constituencyId,Long publicationDateId,Long userId,List<Long> locationIdsList,boolean isPartial)
	  {
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			Long reportLevelId = getReportLevelId(locationType);
			Map<Long,Long> totalVotersMap = new HashMap<Long, Long>(0);//<locationId,totalVoters>
			List<Object[]> list = null;
			 
			if(locationType != null && !locationType.equalsIgnoreCase(IConstants.HAMLET)){
				 if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT) && isPartial){
					 list= boothPublicationVoterDAO.getVoterAgeDetailsForPartialPanchayats(userId,publicationDateId,locationIdsList);
				 }else{
				     list = boothPublicationVoterDAO.getVoterAgeDetailsForSelectedLocation(constituencyId, publicationDateId, locationIdsList, locationType);
				 }
			}else if(locationType != null && locationType.equalsIgnoreCase(IConstants.HAMLET))
			 list = userVoterDetailsDAO.getVoterAgeDetailsForHamlet(constituencyId, publicationDateId, locationIdsList, userId);
				
			if(list != null && list.size() > 0)
			 {
				List<VoterAgeRangeVO> ageRangeVOsList = new ArrayList<VoterAgeRangeVO>();
				VoterAgeRangeVO ageRangeVO = null;
				for(Object[] params:list)
				{
					ageRangeVO = checkVoterAgeRangeVOExist((Long)params[3],(Long)params[2],ageRangeVOsList);
					if(ageRangeVO == null)
					{
					    ageRangeVO = new VoterAgeRangeVO();
						ageRangeVO.setReportLevelValue((Long)params[3]);
						ageRangeVO.setAgeRangeId((Long)params[2]);
						ageRangeVO.setPublicationDateId(publicationDateId);
						ageRangeVO.setConstituencyId(constituencyId);
						ageRangeVO.setReportLevelId(reportLevelId);
						ageRangeVOsList.add(ageRangeVO);
					}
					if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.MALE))
					 ageRangeVO.setMaleVoters((Long)params[0]);
					else if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					 ageRangeVO.setFemaleVoters((Long)params[0]);
					
					ageRangeVO.setTotalVotersInARange(ageRangeVO.getMaleVoters()+ageRangeVO.getFemaleVoters());
					Long total = totalVotersMap.get((Long)params[3]);
					if(total == null)
					 totalVotersMap.put((Long)params[3], (Long)params[0]);
					else
					 totalVotersMap.put((Long)params[3], total+(Long)params[0]);
				}
				
				getYoungVotersDetails(constituencyId,publicationDateId,locationIdsList,userId,locationType,ageRangeVOsList,reportLevelId);
				
				for(VoterAgeRangeVO rangeVO :ageRangeVOsList)
				{
				 rangeVO.setMalePercentage(new BigDecimal((rangeVO.getMaleVoters().doubleValue()*100.0)/rangeVO.getTotalVotersInARange().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()); 
				 rangeVO.setFemalePercentage(new BigDecimal((rangeVO.getFemaleVoters().doubleValue()*100.0)/rangeVO.getTotalVotersInARange().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
				 rangeVO.setPercentage(new BigDecimal((rangeVO.getTotalVotersInARange().doubleValue()*100.0)/totalVotersMap.get(rangeVO.getReportLevelValue()).doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
				}
				
				saveVoterAgeDetailsInVoterAgeInfo(ageRangeVOsList);
				
			 }
			 
			 return resultStatus;
		  }catch (Exception e) {
			  e.printStackTrace();
			  log.error(" Exception Occured in calculateAndInsertVoterAgeInfo() method, Exception - "+e);
			  return resultStatus;
		}
	  }
	  
	
	  
	  public ResultStatus saveVoterAgeDetailsInVoterAgeInfo(final List<VoterAgeRangeVO> ageRangeVOsList)
	  {
		  log.info("Entered into saveVoterAgeDetailsInVoterAgeInfo() Method...");
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus status) 
			{
			  for(VoterAgeRangeVO voterAgeRangeVO:ageRangeVOsList)
			  {
				  try{
				  VoterAgeInfo voterAgeInfo = new VoterAgeInfo();
					voterAgeInfo.setVoterReportLevel(voterReportLevelDAO.get(voterAgeRangeVO.getReportLevelId()));
					voterAgeInfo.setReportLevelValue(voterAgeRangeVO.getReportLevelValue());
					if(voterAgeRangeVO.getAgeRangeId() != null)
					voterAgeInfo.setVoterAgeRange(voterAgeRangeDAO.get(voterAgeRangeVO.getAgeRangeId()));
					voterAgeInfo.setPublicationDate(publicationDateDAO.get(voterAgeRangeVO.getPublicationDateId()));
					voterAgeInfo.setTotalVoters(voterAgeRangeVO.getTotalVotersInARange());
					voterAgeInfo.setTotalVotersPercentage(new Double(voterAgeRangeVO.getPercentage()));
					voterAgeInfo.setMaleVoters(voterAgeRangeVO.getMaleVoters());
					voterAgeInfo.setMaleVotersPercentage(voterAgeRangeVO.getMalePercentage());
					voterAgeInfo.setFemaleVoters(voterAgeRangeVO.getFemaleVoters());
					voterAgeInfo.setFemaleVotersPercentage(voterAgeRangeVO.getFemalePercentage());
					voterAgeInfo.setConstituencyId(voterAgeRangeVO.getConstituencyId());
		
					voterAgeInfoDAO.save(voterAgeInfo);
				  }
				  catch(Exception e)
				  {
					  log.error("Exception Occured in saveVoterAgeDetailsInVoterAgeInfo() Method, Exception - "+e);  
				  }
			}
			}});
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  voterDAO.flushAndclearSession();
			  return resultStatus;
		  }catch (Exception e) {
			  e.printStackTrace();
			  log.error("Exception Occured in saveVoterAgeDetailsInVoterAgeInfo() Method, Exception - "+e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		}
	  }
	  
	  
	  public VoterAgeRangeVO checkVoterAgeRangeVOExist(Long locationId,Long voterAgeId,List<VoterAgeRangeVO> list)
	  {
		  try{
			 if(list == null || list.size() == 0)
			  return null;
			 for(VoterAgeRangeVO vo:list)
			  if(vo.getAgeRangeId().equals(voterAgeId) && vo.getReportLevelValue().equals(locationId))
				return vo;
			  
			 return null; 
		  }catch (Exception e) {
			e.printStackTrace();
			log.error(" Exception Occured in checkVoterAgeRangeVOExist() method, Exception - "+e);
			return null;
		}
	  }

	 //End

	  public ResultStatus calculateAndInsertVoterInfoForALocation(String locationType, Long locationValue, Long parentLocationId, Long publicationDateId,Long constituencyId,Long userId,boolean isPartial)
	  {
		  log.info(" Entered into calculateAndInsertVoterInfoForALocation() Method, with Values - Location Type - "+locationType+" - Location Value - "+locationValue+", Parent Location Id - "+parentLocationId+" and Publicarion Date Id - "+publicationDateId);
		  ResultStatus resultStatus = new ResultStatus();
		  List<Object[]> resultList = null;
		  try{
			  if(!locationType.equalsIgnoreCase(IConstants.HAMLET)){
				  if(!isPartial)
			        resultList = boothPublicationVoterDAO.findVotersGenderWiseCountByPublicationIdInALocation(locationType,locationValue, publicationDateId,constituencyId);
				  else
					resultList = boothPublicationVoterDAO.getVotersCountByHamletForPartialPanchayat(locationValue, userId, publicationDateId, constituencyId);
			  }else{
				resultList = boothPublicationVoterDAO.getVotersCountForHamlet(locationValue,userId,publicationDateId);
			  }
			 if(resultList != null && resultList.size() > 0)
			  {
				  VotersInfoForMandalVO votersInfo = new VotersInfoForMandalVO();
				  for(Object[] params : resultList)
					  if(params[1].toString().equalsIgnoreCase(IConstants.MALE))
						  votersInfo.setTotalMaleVoters(params[0].toString());
					  else
						  votersInfo.setTotalFemaleVoters(params[0].toString());
				  Long totalVoters = (Long)resultList.get(0)[0]+(Long)resultList.get(1)[0];
				  votersInfo.setReportLevelId(getReportLevelId(locationType));
				  votersInfo.setReportLevelValue(locationValue);
				  votersInfo.setPublicationDateId(publicationDateId);
				  votersInfo.setTotalVoters(totalVoters.toString());
				  votersInfo.setTotalMalePercentage(new BigDecimal((new Double(votersInfo.getTotalMaleVoters())*100)/totalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  votersInfo.setTotalFemalePercentage(new BigDecimal((new Double(votersInfo.getTotalFemaleVoters())*100)/totalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  votersInfo.setTotalFamilies(getFamiliesCountInALocation(locationType,locationValue,publicationDateId,userId));
				  votersInfo.setConstituencyId(constituencyId);
				  
				  if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
				  {
					  votersInfo.setTotalVotersPercentage(null);
					  votersInfo.setTotalFamilyPercentage(null);
				  }
				  else if(locationType.equalsIgnoreCase(IConstants.MANDAL) || 
						  locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
				  {
					  Long parentTotalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId(IConstants.CONSTITUENCY),parentLocationId,publicationDateId,constituencyId);
					  votersInfo.setTotalVotersPercentage(new BigDecimal((new Double(votersInfo.getTotalVoters())*100)/parentTotalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					  Long parentTotalfamilies = voterInfoDAO.getFamiliesCountInALocation(getReportLevelId(IConstants.CONSTITUENCY), parentLocationId, publicationDateId,constituencyId);
					  votersInfo.setTotalFamilyPercentage(new BigDecimal((new Double(votersInfo.getTotalFamilies())*100)/parentTotalfamilies).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  }
				  else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				  {
					  Long parentTotalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId(IConstants.MANDAL),parentLocationId,publicationDateId,constituencyId);
					  votersInfo.setTotalVotersPercentage(new BigDecimal((new Double(votersInfo.getTotalVoters())*100)/parentTotalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					  Long parentTotalfamilies = voterInfoDAO.getFamiliesCountInALocation(getReportLevelId(IConstants.MANDAL), parentLocationId, publicationDateId,constituencyId);
					  votersInfo.setTotalFamilyPercentage(new BigDecimal((new Double(votersInfo.getTotalFamilies())*100)/parentTotalfamilies).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  }
				  else if(locationType.equalsIgnoreCase(IConstants.WARD))
				  {
					  Long parentTotalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId(IConstants.LOCALELECTIONBODY),parentLocationId,publicationDateId,constituencyId);
					  votersInfo.setTotalVotersPercentage(new BigDecimal((new Double(votersInfo.getTotalVoters())*100)/parentTotalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					  Long parentTotalfamilies = voterInfoDAO.getFamiliesCountInALocation(getReportLevelId(IConstants.LOCALELECTIONBODY), parentLocationId, publicationDateId,constituencyId);
					  votersInfo.setTotalFamilyPercentage(new BigDecimal((new Double(votersInfo.getTotalFamilies())*100)/parentTotalfamilies).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  }
				  else if(locationType.equalsIgnoreCase(IConstants.BOOTH))
				  {
					  List<Long> boothIds = boothDAO.checkForUrbanBooth(locationValue,publicationDateId);
					  String lType = "";
					  if(boothIds.size() > 0)
						  lType = IConstants.LOCALELECTIONBODY;
					  else
						  lType = IConstants.PANCHAYAT;
					  
					  Long parentTotalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId(lType),parentLocationId,publicationDateId,constituencyId);
					  votersInfo.setTotalVotersPercentage(new BigDecimal((new Double(votersInfo.getTotalVoters())*100)/parentTotalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					  Long parentTotalfamilies = voterInfoDAO.getFamiliesCountInALocation(getReportLevelId(lType), parentLocationId, publicationDateId,constituencyId);
					  votersInfo.setTotalFamilyPercentage(new BigDecimal((new Double(votersInfo.getTotalFamilies())*100)/parentTotalfamilies).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  }
				  else if(locationType.equalsIgnoreCase(IConstants.HAMLET))
				  {
					  Long parentTotalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId(IConstants.PANCHAYAT),parentLocationId,publicationDateId,constituencyId);
					  votersInfo.setTotalVotersPercentage(new BigDecimal((new Double(votersInfo.getTotalVoters())*100)/parentTotalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					  Long parentTotalfamilies = voterInfoDAO.getFamiliesCountInALocation(getReportLevelId(IConstants.PANCHAYAT), parentLocationId, publicationDateId,constituencyId);
					  votersInfo.setTotalFamilyPercentage(new BigDecimal((new Double(votersInfo.getTotalFamilies())*100)/parentTotalfamilies).setScale(2, BigDecimal.ROUND_HALF_UP).toString());  
				  }
				  saveVotersDataInVoterInfoTable(votersInfo);
				  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  }
			 else
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		  }catch (Exception e) {
			  log.error(" Exception Occured in calculateAndInsertVoterInfoForALocation() Method, with Values - Location Type - "+locationType+" - Location Value - "+locationValue+", Parent Location Id - "+parentLocationId+" and Publicarion Date Id - "+publicationDateId);
			  log.error(" Exception is -"+e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		  }
	  }
	  
	  public Long getFamiliesCountInALocation(String locationType, Long locationValue,  Long publicationDateId,Long userId)
	  {
		  log.info("Entered into getFamiliesCountInALocation method with Values, Location Type - "+locationType+" - Location Value - "+locationValue+" and Publicarion Date Id - "+publicationDateId);
		  Long familiesCount = 0L;
		  List<Long> familiesCountList = null;
		  try{
			  if(!locationType.equalsIgnoreCase(IConstants.HAMLET))
			  familiesCountList = boothPublicationVoterDAO.findFamiliesCountByPublicationIdInALocation(locationType,locationValue,publicationDateId);
			  else
				  familiesCountList = boothPublicationVoterDAO.findFamiliesCountByPublicationIdInAHamlet(locationValue,publicationDateId,userId);
			  if(familiesCountList != null && familiesCountList.size() > 0)
			  for(Long fCount : familiesCountList)
				  familiesCount = familiesCount + fCount;
			  return familiesCount;
		  }catch (Exception e) {
			  log.error("Exception Occured in getFamiliesCountInALocation() method with Values, Location Type - "+locationType+" - Location Value - "+locationValue+" and Publicarion Date Id - "+publicationDateId);
			  log.error("Exception is - "+e);
			  return familiesCount;
		  }
	  }
	  
	  public ResultStatus saveVotersDataInVoterInfoTable(final VotersInfoForMandalVO votersInfo)
	  {
		  log.info("Entered into saveVotersDataInVoterInfoTable() Method...");
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus status) 
			{
				VoterInfo voterInfo = new VoterInfo();
				voterInfo.setTotalVoters(votersInfo.getTotalVoters() != null?new Long(votersInfo.getTotalVoters()):0L);
				voterInfo.setMaleVoters(votersInfo.getTotalMaleVoters()!= null ?new Long(votersInfo.getTotalMaleVoters()):0L);
				voterInfo.setFemaleVoters(votersInfo.getTotalFemaleVoters() != null?new Long(votersInfo.getTotalFemaleVoters()):0L);
				voterInfo.setTotalFamilies(votersInfo.getTotalFamilies()!= null?votersInfo.getTotalFamilies():0L);
				voterInfo.setTotalVotersPercentage((votersInfo.getTotalVotersPercentage() == null ||
						votersInfo.getTotalVotersPercentage().equalsIgnoreCase(""))? null : new Double(votersInfo.getTotalVotersPercentage()));
				voterInfo.setMaleVotersPercentage(votersInfo.getTotalMalePercentage()!= null?new Double(votersInfo.getTotalMalePercentage()):0L);
				voterInfo.setFemaleVotersPercentage(votersInfo.getTotalFemalePercentage() != null ?new Double(votersInfo.getTotalFemalePercentage()):0L);
				voterInfo.setFamiliesPercentage((votersInfo.getTotalFamilyPercentage() == null ||
						votersInfo.getTotalFamilyPercentage().equalsIgnoreCase(""))? new Double(0.00) : new Double(votersInfo.getTotalFamilyPercentage()));
				voterInfo.setReportLevelValue(votersInfo.getReportLevelValue());
				voterInfo.setVoterReportLevel(voterReportLevelDAO.get(votersInfo.getReportLevelId()));
				voterInfo.setPublicationDate(publicationDateDAO.get(votersInfo.getPublicationDateId()));
				voterInfo.setConstituencyId(votersInfo.getConstituencyId());
				voterInfoDAO.save(voterInfo);
			}});
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  voterDAO.flushAndclearSession();
			  return resultStatus;
		  }catch (Exception e) {
			  e.printStackTrace();
			  log.error("Exception Occured in saveVotersDataInVoterInfoTable() Method, Exception - "+e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		}
	  }
	  
	  /** -- Voter Family Info --**/
	  
	  public ResultStatus calculateAndInsertVoterFamilyInfoForALocation(String locationType, Long locationValue,  Long publicationDateId, Long constituencyId,Long userId,boolean isPartial)
	  {
		  log.info("Entered into calculateAndInsertVoterFamilyInfoForALocation method with Values, Location Type - "+locationType+" - Location Value - "+locationValue+" and Publicarion Date Id - "+publicationDateId);
		  ResultStatus resultStatus = new ResultStatus();
		  List<Long> list = new ArrayList<Long>(0);
		  try{
			  ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
			  
			  importantFamiliesInfoVo.setReportLevelId(getReportLevelId(locationType));
			  importantFamiliesInfoVo.setReportLevelValue(locationValue);
			  importantFamiliesInfoVo.setPublicationDateId(publicationDateId);
			  importantFamiliesInfoVo.setConstituencyId(constituencyId);
			  if(!locationType.equalsIgnoreCase(IConstants.HAMLET)){
				 if(!isPartial)
			         list = boothPublicationVoterDAO.getAllImpFamilesCount(locationType, locationValue, publicationDateId,constituencyId);
				 else
					 list = boothPublicationVoterDAO.getAllImpFamilesCountForPartialPanchayat(locationValue, publicationDateId, userId, constituencyId);
			  }else{
			      list = boothPublicationVoterDAO.getAllImpFamilesCountForHamlet(locationValue, publicationDateId,userId);  
			  } 
			  if(list == null || list.size() == 0)
				  return null;
			  
			  Long totalFamilies = new Long(list.size());
			  Long lessThan3Count = 0l;
			  Long between4To6Count = 0l;
			  Long between7To10Count = 0l;
			  Long above10Count = 0l;
			  
			  for(Long familyCount : list)
			  {
				  if(familyCount.longValue() <=3)
					  lessThan3Count++;
				  else if(familyCount.longValue() >=4 && familyCount.longValue() <= 6)
					  between4To6Count++;
				  else if(familyCount.longValue() >=7 && familyCount.longValue() <= 10)
					  between7To10Count++;
				  else  if(familyCount.longValue() >10)
					  above10Count++;
			  }
			  
			  importantFamiliesInfoVo.setTotalFamalies(lessThan3Count);
			  importantFamiliesInfoVo.setTypeId(getVoterFamilyRangeIdByFamilyRange("0-3"));
			  importantFamiliesInfoVo.setTotalPercentage(new BigDecimal(lessThan3Count.doubleValue()*100/totalFamilies.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			  saveVotersDataInVoterFamilyInfoTable(importantFamiliesInfoVo);
			  
			  importantFamiliesInfoVo.setTotalFamalies(between4To6Count);
			  importantFamiliesInfoVo.setTypeId(getVoterFamilyRangeIdByFamilyRange("4-6"));
			  importantFamiliesInfoVo.setTotalPercentage(new BigDecimal(between4To6Count.doubleValue()*100/totalFamilies.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			  saveVotersDataInVoterFamilyInfoTable(importantFamiliesInfoVo);
			  
			  importantFamiliesInfoVo.setTotalFamalies(between7To10Count);
			  importantFamiliesInfoVo.setTypeId(getVoterFamilyRangeIdByFamilyRange("7-10"));
			  importantFamiliesInfoVo.setTotalPercentage(new BigDecimal(between7To10Count.doubleValue()*100/totalFamilies.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			  saveVotersDataInVoterFamilyInfoTable(importantFamiliesInfoVo);
			  
			  importantFamiliesInfoVo.setTotalFamalies(above10Count);
			  importantFamiliesInfoVo.setTypeId(getVoterFamilyRangeIdByFamilyRange("10-Above"));
			  importantFamiliesInfoVo.setTotalPercentage(new BigDecimal(above10Count.doubleValue()*100/totalFamilies.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			  saveVotersDataInVoterFamilyInfoTable(importantFamiliesInfoVo);
			  
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			log.error("Exception Occured in calculateAndInsertVoterFamilyInfoForALocation() method with Values, Location Type - "+locationType+" - Location Value - "+locationValue+" and Publicarion Date Id - "+publicationDateId);
			log.error("Exception is - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	  }
	  
	  public ResultStatus saveVotersDataInVoterFamilyInfoTable(final ImportantFamiliesInfoVo importantFamiliesInfoVo)
	  {
		  log.info("Entered into saveVotersDataInVoterFamilyInfoTable() Method");
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
						VoterFamilyInfo familyInfo = new VoterFamilyInfo();
						familyInfo.setVoterReportLevel(voterReportLevelDAO.get(importantFamiliesInfoVo.getReportLevelId()));
						familyInfo.setReportLevelValue(importantFamiliesInfoVo.getReportLevelValue());
						familyInfo.setVoterFamilyRange(voterFamilyRangeDAO.get(importantFamiliesInfoVo.getTypeId()));
						familyInfo.setTotalFamilies(importantFamiliesInfoVo.getTotalFamalies());
						familyInfo.setFamiliesPercentage(importantFamiliesInfoVo.getTotalPercentage());
						familyInfo.setPublicationDate(publicationDateDAO.get(importantFamiliesInfoVo.getPublicationDateId()));
						familyInfo.setConstituencyId(importantFamiliesInfoVo.getConstituencyId());
						
						voterFamilyInfoDAO.save(familyInfo);
						
					}});
			  voterDAO.flushAndclearSession();
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			  e.printStackTrace();
			  log.error("Exception Occured in saveVotersDataInVoterFamilyInfoTable() Method, Exception - "+e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		}
	  }
	  
	  public ResultStatus calculateAndInsertVoterAgeInfoForALocation(String locationType, Long locationValue,  Long publicationDateId, Long constituencyId,Long userId)
	  {
		  log.info("Entered into calculateAndInsertVoterAgeInfoTable Method with locationType - "+locationType+" locationValue - "+locationValue+" publicationDateId - "+publicationDateId);
		  ResultStatus resultStatus = new ResultStatus();
		  Long totalVoters = 0l;
		  try{
			  if(!locationType.equalsIgnoreCase(IConstants.HAMLET))
			  totalVoters = voterInfoDAO.getTotalVotersByReportLevelValue(getReportLevelId(locationType),locationValue, publicationDateId,constituencyId);
			  else
				  totalVoters = boothPublicationVoterDAO.getTotalVotersCountForHamlet(userId,locationValue,publicationDateId,locationType); 
			  if(totalVoters == null || totalVoters.longValue() == 0)
				  return null;
			  VoterAgeRangeVO voterAgeRangeVO = new VoterAgeRangeVO();
			  voterAgeRangeVO.setReportLevel(locationType);
			  voterAgeRangeVO.setReportLevelId(getReportLevelId(locationType));
			  voterAgeRangeVO.setReportLevelValue(locationValue);
			  voterAgeRangeVO.setPublicationDateId(publicationDateId);
			  voterAgeRangeVO.setTotalVoters(totalVoters);
			  voterAgeRangeVO.setConstituencyId(constituencyId);
			  
			  voterAgeRangeVO = getVoterAgeInfoForAPerticularRange(voterAgeRangeVO,IConstants.AGE18to22,IConstants.AGE18,IConstants.AGE22,constituencyId,userId);
			  saveVotersDataInVoterAgeInfoTable(voterAgeRangeVO);
			  
			  voterAgeRangeVO = getVoterAgeInfoForAPerticularRange(voterAgeRangeVO,IConstants.AGE23to30,IConstants.AGE23,IConstants.AGE30,constituencyId,userId);
			  saveVotersDataInVoterAgeInfoTable(voterAgeRangeVO);
			  
			  voterAgeRangeVO = getVoterAgeInfoForAPerticularRange(voterAgeRangeVO,IConstants.AGE31to45,IConstants.AGE31,IConstants.AGE45,constituencyId,userId);
			  saveVotersDataInVoterAgeInfoTable(voterAgeRangeVO);
			  
			  voterAgeRangeVO = getVoterAgeInfoForAPerticularRange(voterAgeRangeVO,IConstants.AGE46to60,IConstants.AGE46,IConstants.AGE60,constituencyId,userId);
			  saveVotersDataInVoterAgeInfoTable(voterAgeRangeVO);
			  
			  voterAgeRangeVO = getVoterAgeInfoForAPerticularRange(voterAgeRangeVO,"60-Above",60l,null,constituencyId,userId);
			  saveVotersDataInVoterAgeInfoTable(voterAgeRangeVO);
			  
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			  	log.error("Exception Occured in calculateAndInsertVoterAgeInfoForALocation() Method with locationType - "+locationType+" locationValue - "+locationValue+" publicationDateId - "+publicationDateId);
				log.error(" Exception is - ",e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
	}
	  
	  public ResultStatus saveVotersDataInVoterAgeInfoTable(final VoterAgeRangeVO voterAgeRangeVO)
	  {
		  log.info("Entred into saveVotersDataInVoterAgeInfoTable() Method ");
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
						VoterAgeInfo voterAgeInfo = new VoterAgeInfo();
						voterAgeInfo.setVoterReportLevel(voterReportLevelDAO.get(voterAgeRangeVO.getReportLevelId()));
						voterAgeInfo.setReportLevelValue(voterAgeRangeVO.getReportLevelValue());
						voterAgeInfo.setVoterAgeRange(voterAgeRangeDAO.get(voterAgeRangeVO.getAgeRangeId()));
						voterAgeInfo.setPublicationDate(publicationDateDAO.get(voterAgeRangeVO.getPublicationDateId()));
						voterAgeInfo.setTotalVoters(voterAgeRangeVO.getTotalVotersInARange());
						voterAgeInfo.setTotalVotersPercentage(new Double(voterAgeRangeVO.getPercentage()));
						voterAgeInfo.setMaleVoters(voterAgeRangeVO.getMaleVoters());
						voterAgeInfo.setMaleVotersPercentage(voterAgeRangeVO.getMalePercentage());
						voterAgeInfo.setFemaleVoters(voterAgeRangeVO.getFemaleVoters());
						voterAgeInfo.setFemaleVotersPercentage(voterAgeRangeVO.getFemalePercentage());
						voterAgeInfo.setConstituencyId(voterAgeRangeVO.getConstituencyId());
			
						voterAgeInfoDAO.save(voterAgeInfo);
					}});
			  voterDAO.flushAndclearSession();
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			  log.error("Exception Occured in saveVotersDataInVoterAgeInfoTable() Method, Exception - "+e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		}
	  }
	  
	  public VoterAgeRangeVO getVoterAgeInfoForAPerticularRange(VoterAgeRangeVO voterAgeRangeVO, String range,Long ageFrom,Long ageTo,Long constituencyId,Long userId)
	  {
		  log.info("Entered into getVoterAgeInfoForAPerticularRange()");
		  try{
			  voterAgeRangeVO.setAgeRangeId(getVoterAgeRangeId(range));
			  if(!voterAgeRangeVO.getReportLevel().equals(IConstants.HAMLET))
			  voterAgeRangeVO.setTotalVotersInARange(boothPublicationVoterDAO.getVotersCountInAAgeRange(voterAgeRangeVO.getReportLevel(), voterAgeRangeVO.getReportLevelValue(), voterAgeRangeVO.getPublicationDateId(),ageFrom,ageTo,null,constituencyId));
			  else
				  voterAgeRangeVO.setTotalVotersInARange(boothPublicationVoterDAO.getVotersCountInAAgeRangeByHamlet(voterAgeRangeVO.getReportLevelValue(), voterAgeRangeVO.getPublicationDateId(),userId,ageFrom,ageTo,null));  
			  voterAgeRangeVO.setPercentage(new BigDecimal((voterAgeRangeVO.getTotalVotersInARange().doubleValue()*100)/voterAgeRangeVO.getTotalVoters().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
			  if(!voterAgeRangeVO.getReportLevel().equals(IConstants.HAMLET))
			  voterAgeRangeVO.setMaleVoters(boothPublicationVoterDAO.getVotersCountInAAgeRange(voterAgeRangeVO.getReportLevel(), voterAgeRangeVO.getReportLevelValue(), voterAgeRangeVO.getPublicationDateId(),ageFrom,ageTo,IConstants.MALE,constituencyId));
			  else
				  voterAgeRangeVO.setMaleVoters(boothPublicationVoterDAO.getVotersCountInAAgeRangeByHamlet(voterAgeRangeVO.getReportLevelValue(), voterAgeRangeVO.getPublicationDateId(),userId,ageFrom,ageTo,IConstants.MALE));  
			  voterAgeRangeVO.setFemaleVoters(voterAgeRangeVO.getTotalVotersInARange()-voterAgeRangeVO.getMaleVoters());
			  voterAgeRangeVO.setMalePercentage(new BigDecimal((voterAgeRangeVO.getMaleVoters().doubleValue()*100)/voterAgeRangeVO.getTotalVotersInARange().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			  voterAgeRangeVO.setFemalePercentage(new BigDecimal((voterAgeRangeVO.getFemaleVoters().doubleValue()*100)/voterAgeRangeVO.getTotalVotersInARange().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			  return voterAgeRangeVO;  
		  }catch (Exception e) {
			log.error("Exception Occured in getVoterAgeInfoForAPerticularRange() Method, Exception is - "+e);
			return voterAgeRangeVO;
		}
	  }
	  
		public List<SelectOptionVO> getConstituencyList(List<SelectOptionVO> userAccessConstituencyList)
		{
			List<SelectOptionVO> constituencyList = new ArrayList<SelectOptionVO>(0);;
			try{
				List<Long> constituencyIds =  voterInfoDAO.getConstituencyIds();
				if(userAccessConstituencyList != null && userAccessConstituencyList.size() > 0 && constituencyIds != null)
				{
					for(SelectOptionVO selectOptionVO : userAccessConstituencyList)
					{
						if(constituencyIds.contains(selectOptionVO.getId()))
						{
							selectOptionVO.setName(selectOptionVO.getName().toUpperCase());
							constituencyList.add(selectOptionVO);
						}
					}
				}
				
				return constituencyList;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getConstituencyList() Method, Exception - "+e);
				return constituencyList;
			}
		}
		  
		  
		public List<SelectOptionVO> getAllElectionsInAConsti(Long electionTypeId,Long constiId){
			List<SelectOptionVO> options = new ArrayList<SelectOptionVO>();
			SelectOptionVO selectOptionVO = null;
			try{
			List<Object[]> electionsList = villageBoothElectionDAO.findElectionsForElectionTypeConstiId( electionTypeId, constiId);
			if(electionsList != null && electionsList.size() > 0){
				for(Object[] year:electionsList){
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)year[0]);
					selectOptionVO.setName(year[1]!=null?year[1].toString():"");
					options.add(selectOptionVO);
				}
					
			}
			}catch(Exception e){
				log.error("Exception Occured in getAllElectionsInAConsti() Method, Exception - ",e);
			}
			return options;
		}
		
		/* Voters Info */
		
		
		public VotersInfoForMandalVO getVotersBasicInfoForConstituency(String type,Long id,Long publicationDateId,String reqType){
			
			try{
				Constituency constituency = constituencyDAO.get(id);
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(type), id, publicationDateId, constituency.getName(),"Constituency",id);
				if(votersInfoForMandalVO.isDatapresent() && reqType.equalsIgnoreCase("main"))
				{
					List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
					//getVotersBasicInfoForMultipleMandal(mandalsList,publicationDateId,votersInfoForMandalVO);
					 //calculatePercentage(votersInfoForMandalVO);
					Map<Long,String> mandalIds = new HashMap<Long,String>();
					Map<Long,String> localBodyIds = new HashMap<Long,String>();
					List<Long> urban = new ArrayList<Long>();
					for (SelectOptionVO mandal : mandalsList){
						if(mandal.getId().toString().substring(0,1).equalsIgnoreCase("2"))
						mandalIds.put(new Long(mandal.getId().toString().trim().substring(1)),mandal.getName());
						else
						{
							List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(mandal.getId().toString().substring(1)));
							 localBodyIds.put(new Long(list.get(0).toString()),mandal.getName());
							 if("URBAN".equalsIgnoreCase(constituency.getAreaType()))
								 urban.add(new Long(list.get(0).toString()));
						}
						
					}
					if(mandalIds.size() > 0)
						votersInfoForMandalVO.setVotersInfoForMandalVOList(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId(IConstants.MANDAL),mandalIds, publicationDateId,"Mandal",id));
					if(localBodyIds.size() > 0)
					{
						if("URBAN".equalsIgnoreCase(constituency.getAreaType()) && urban != null && urban.size() == 1){
							List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId(urban.get(0),publicationDateId,id);
							
							Map<Long,String> wardIds = new HashMap<Long,String>();
							for (Object[] ward : wardsList){
								wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
							}
							if(wardIds.size() > 0)
								votersInfoForMandalVO.getVotersInfoForMandalVOList().addAll(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId("Ward"), wardIds, publicationDateId,"Ward",id));
							
						}else{
							List<VotersInfoForMandalVO> localElec = getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId(IConstants.LOCALELECTIONBODY),localBodyIds, publicationDateId,IConstants.LOCALELECTIONBODY,id);
							if(localElec != null && localElec.size() > 0)
							votersInfoForMandalVO.getVotersInfoForMandalVOList().addAll(localElec);
						}
					}
					if(votersInfoForMandalVO != null && votersInfoForMandalVO.getVotersInfoForMandalVOList() != null && votersInfoForMandalVO.getVotersInfoForMandalVOList().size() > 0)
						votersInfoForMandalVO.setSubLevelExists(true);
					return votersInfoForMandalVO;
				}
				
				return votersInfoForMandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersBasicInfoForConstituency() Method, Exception - "+e);
				return null;
			}
			 
		}	
				
		public void getVotersBasicInfoForMultipleMandal(List<SelectOptionVO> mandalsList, Long publicationDateId, VotersInfoForMandalVO votersInfoForMandalVO,Long constituencyId)
		{
			try{
				if(mandalsList != null && mandalsList.size() > 0)
				{
					for(SelectOptionVO mandal : mandalsList)
					{
						String id = mandal.getId().toString();
						votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersBasicInfoForSelectedMandal(IConstants.MANDAL, id, publicationDateId, mandal.getName(), "sub",constituencyId));
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception occured in getVotersBasicInfoForMultipleMandal() Method, Exception - "+e);
			}
		}
		
		public VotersInfoForMandalVO getVotersBasicInfoForSelectedMandal(String type,String id,Long publicationDateId,String name,String reqType,Long constituencyId)
		{
			try{
				String typeVar = IConstants.MANDAL;
				if(id.toString().substring(0,1).equalsIgnoreCase("2"))
					id = id.toString().trim().substring(1);
					
				else
				{
					typeVar = IConstants.LOCALELECTIONBODY;
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1)));
					 id = list.get(0).toString();
				}
				VotersInfoForMandalVO mandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(typeVar), new Long(id), publicationDateId, name, typeVar,constituencyId);
				if(reqType.equalsIgnoreCase("main") && !mandalVO.isDatapresent()){
						 VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
						 votersInfoForMandalVO.setDatapresent(false);
						 return votersInfoForMandalVO;
					 }
				return mandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersBasicInfoForSelectedMandal() Method, Exception - "+e);
				return null;
			}
		}
			
		public VotersInfoForMandalVO getVotersBasicInfoForMandal(String type,Long id,Long publicationDateId,String reqType,Long constituencyId,String resultFor,Long userId){
			String name = "";
			if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
			  List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(id.toString().substring(1));
			  Object[] reqName = assemblyLocalElectionBodyName.get(0);
			  name = reqName[0].toString()+" "+reqName[1].toString();
			}
			else{
				name = tehsilDAO.get(new Long(id.toString().substring(1))).getTehsilName()+" Mandal/Tehsil";
			}
			VotersInfoForMandalVO votersInfoForMandalVO = getVotersBasicInfoForSelectedMandal("mandal",id.toString(),publicationDateId,name,"main",constituencyId);
			if(id.toString().substring(0,1).trim().equalsIgnoreCase("2") && votersInfoForMandalVO.isDatapresent() && reqType.equalsIgnoreCase("main")){
				//getting voters count for all panchayats in mandal
			    // getVotersCountForMultiplePanchayat(new Long(id.toString().substring(01)),publicationDateId,votersInfoForMandalVO);
			    // calculatePercentage(votersInfoForMandalVO);
				Map<Long,String> panchayatIds = new HashMap<Long,String>();
				Map<Long,String> partialPancMap = new HashMap<Long,String>();
				
				voterReportService.getPartialAndNormalPanchayats(publicationDateId, id, panchayatIds, partialPancMap);
				
				if(panchayatIds.size() > 0)
					votersInfoForMandalVO.setVotersInfoForMandalVOList(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId("Panchayat"), panchayatIds, publicationDateId,"Panchayat",constituencyId));
				if(partialPancMap.size() > 0){
					List<VotersInfoForMandalVO> partialResults = voterReportService.getDataForPartialPanchayats(constituencyId,Long.valueOf(id.toString().trim().substring(1)),partialPancMap,publicationDateId,userId);
					if(partialResults != null && partialResults.size() > 0){
						if(votersInfoForMandalVO.getVotersInfoForMandalVOList() != null){
							votersInfoForMandalVO.getVotersInfoForMandalVOList().addAll(partialResults);
						}else{
							votersInfoForMandalVO.setVotersInfoForMandalVOList(partialResults);
						}
						try{
						   Collections.sort(votersInfoForMandalVO.getVotersInfoForMandalVOList(),sortByInfoName);
						}catch(Exception e){}
					}
				}
			}
			if(id.toString().substring(0,1).trim().equalsIgnoreCase("1") && votersInfoForMandalVO.isDatapresent() && reqType.equalsIgnoreCase("main")){
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(01)));
				List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId,constituencyId);
					if(wardsList.size() <=0 && resultFor.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY_BOOTHS)){
						List<Object[]> boothsList = boothDAO.getBoothsInAMunicipality((Long) list.get(0),publicationDateId,constituencyId);
						Map<Long,String> boothIds = new HashMap<Long,String>();													
						for (Object[] booth : boothsList)
							boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
						if(boothIds.size() > 0)		
						votersInfoForMandalVO.setVotersInfoForMandalVOList(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId("BOOTH"), boothIds, publicationDateId,"Booth",constituencyId));
					}
				Map<Long,String> wardIds = new HashMap<Long,String>();
				for (Object[] ward : wardsList){
					wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
				}
				if(wardIds.size() > 0)
					votersInfoForMandalVO.setVotersInfoForMandalVOList(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId("Ward"), wardIds, publicationDateId,"Ward",constituencyId));
			}
			if(votersInfoForMandalVO != null && votersInfoForMandalVO.getVotersInfoForMandalVOList() != null && votersInfoForMandalVO.getVotersInfoForMandalVOList().size() > 0)
				votersInfoForMandalVO.setSubLevelExists(true);
			return votersInfoForMandalVO;
		}
		
		
		public void getVotersBasicInfoForMultiplePanchayat(Long mandalId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO,Long userId){
			try{
				List<Object[]> panchayaties = panchayatDAO.getPanchayatsBymandalId(mandalId);
				if(panchayaties != null && panchayaties.size() > 0)
				{
					for (Object[] panchayat : panchayaties){
						votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForPanchayat((Long)panchayat[0],publicationDateId,"sub",userId));
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersBasicInfoForMultiplePanchayat() Method, Exception - "+e);
			}
			
		}
		
		
		public VotersInfoForMandalVO getVotersBasicInfoForPanchayat(Long id,Long publicationDateId,String reqType,Long constituencyId ){
			try{
				
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId("Panchayat"), id, publicationDateId, panchayatDAO.get(id).getPanchayatName(), "Panchayat",constituencyId);
				if(reqType.equalsIgnoreCase("main")){
					if(votersInfoForMandalVO.isDatapresent())
					{
					  //getVotersCountForMultipleBooths(id,publicationDateId,votersInfoForMandalVO);
					  //calculatePercentage(votersInfoForMandalVO);
						List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(id,publicationDateId);
						Map<Long,String> boothIds = new HashMap<Long,String>();
						for (Object[] booth : boothsList){
							boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
						}
						if(boothIds.size() > 0)
							votersInfoForMandalVO.setVotersInfoForMandalVOList(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId(IConstants.BOOTH),boothIds, publicationDateId,"booth",constituencyId));
					}
					if(votersInfoForMandalVO != null && votersInfoForMandalVO.getVotersInfoForMandalVOList() != null && votersInfoForMandalVO.getVotersInfoForMandalVOList().size() > 0)
						votersInfoForMandalVO.setSubLevelExists(true);
					return votersInfoForMandalVO;
				}
				return votersInfoForMandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersBasicInfoForPanchayat() Method, Exception - "+e);
				return null;
			}
		}
		
		public VotersInfoForMandalVO getVotersBasicInfoForWard(Long id,Long publicationDateId,String reqType,Long constituencyId){
           try{
				
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId("Ward"), id, publicationDateId, constituencyDAO.get(id).getName(), "Ward",constituencyId);
				if(reqType.equalsIgnoreCase("main")){
					if(votersInfoForMandalVO.isDatapresent())
					{
						List<Object[]> boothsList = boothDAO.getBoothsForWard(id,publicationDateId);
						Map<Long,String> boothIds = new HashMap<Long,String>();
						for (Object[] booth : boothsList){
							boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
						}
						if(boothIds.size() > 0)
							votersInfoForMandalVO.setVotersInfoForMandalVOList(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId(IConstants.BOOTH),boothIds, publicationDateId,"booth",constituencyId));
					}
					if(votersInfoForMandalVO != null && votersInfoForMandalVO.getVotersInfoForMandalVOList() != null && votersInfoForMandalVO.getVotersInfoForMandalVOList().size() > 0)
						votersInfoForMandalVO.setSubLevelExists(true);
					return votersInfoForMandalVO;
				}
				return votersInfoForMandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersBasicInfoForPanchayat() Method, Exception - "+e);
				return null;
			}
		}
		
		public void getVotersBasicInfoForMultipleBooths(Long panchayatId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO){
			List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(panchayatId,publicationDateId);
		     for(Object[] booth : boothsList){
		    	 votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForBooth("booth",(Long)booth[0],publicationDateId,"sub")); 
		     }
		}
		
		
		/*public VotersInfoForMandalVO getVotersBasicInfoForBooth(String type,Long id,Long publicationDateId,String reqType){
			VotersInfoForMandalVO votersInfoForBooth = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.BOOTH), id, publicationDateId,"booth-"+boothDAO.get(id).getPartNo(),reqType);
			if(votersInfoForBooth.isDatapresent() || reqType.equalsIgnoreCase("sub"))
				   return votersInfoForBooth;
			else{
				   VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
				   votersInfoForMandalVO.setDatapresent(false);
				   return votersInfoForMandalVO;
			   }
		}*/
		
	/* Voters Info */	
	 
	 /* Age wise Details */
		public List<VotersDetailsVO> getVoterAgeWiseDetails(Long constituencyId, Long mandalId,
				 Long panchayatId , Long boothId, Long publicationDateId, String type,Long userId)
		 {
			 try{
				 if(type.equalsIgnoreCase("constituency"))
					 return getAgeWiseVoterDetails(type, constituencyId, publicationDateId,constituencyId);
				 else if(type.equalsIgnoreCase("mandal") || type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || type.equalsIgnoreCase("localElectionBody"))
					 return getAgeWiseVoterDetails(type, mandalId, publicationDateId,constituencyId);
				 else if(type.equalsIgnoreCase("panchayat"))
				 {
					List<Long> partialBoothIds =  partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatId(panchayatId,publicationDateId);
					
					if(partialBoothIds != null && partialBoothIds.size() >0)
					{
						List<Long> panchayatIds = new ArrayList<Long>();
						panchayatIds.add(panchayatId);
						
					return 	voterReportService.caluculateAgeWiseDetailsForPanchayatByHamlets(userId,panchayatIds,publicationDateId,false);
					}
					 
					 return getAgeWiseVoterDetails(type, panchayatId, publicationDateId,constituencyId);
				 }
				 else if(type.equalsIgnoreCase("booth") )
					 return getAgeWiseVoterDetails(type, boothId, publicationDateId,constituencyId);
				 else if(type.equalsIgnoreCase("ward"))
					 return getAgeWiseVoterDetails(type, panchayatId, publicationDateId,constituencyId);
				 else if(type.equalsIgnoreCase("boothHamlets") )
					 return getAgeWiseVoterDetails("booth", panchayatId, publicationDateId,constituencyId);
				 else
				 return null;
			 }catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVoterAgeWiseDetails() Method, Exception - "+e);
				return null;
			}
		 }
	 
	 public List<VotersDetailsVO> getAgeWiseVoterDetails(String type, Long reportLevelValue, Long publicationDateId,Long constituencyId)
	 {
	 	List<VotersDetailsVO> votersDetailsVOList = null;
	 	try{
	 		List<VoterAgeInfo> voterAgeInfoList = new ArrayList<VoterAgeInfo>(0);
	 		
	 		if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || type.equalsIgnoreCase("localElectionBody"))
	 		{
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(reportLevelValue);
					reportLevelValue = (Long) list.get(0);
					type = IConstants.LOCALELECTIONBODY;
	 		}
	 		 voterAgeInfoList = voterAgeInfoDAO.getVoterAgeInfoByPublicationDateAndReportLevelId(getReportLevelId(type), reportLevelValue, publicationDateId,constituencyId);
	 		
	 		if(voterAgeInfoList != null && voterAgeInfoList.size() > 0)
	 		{
	 			votersDetailsVOList = new ArrayList<VotersDetailsVO>(0);
	 			for(VoterAgeInfo ageInfo : voterAgeInfoList)
	 			{
	 				VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
	 				if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().equals(1l))
	 					votersDetailsVO.setAgeRange(IConstants.YOUNGER_VOTERS);
	 				else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().equals(2l))
	 					votersDetailsVO.setAgeRange(IConstants.AGE18to25);
	 				else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().equals(3l))
	 					votersDetailsVO.setAgeRange(IConstants.AGE23to30);
	 				else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().equals(4l))
	 					votersDetailsVO.setAgeRange(IConstants.AGE31to45);
	 				else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().equals(5l))
	 					votersDetailsVO.setAgeRange(IConstants.AGE46to60);
	 				else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().equals(6l))
	 					votersDetailsVO.setAgeRange("60-Above");
	 					votersDetailsVO.setTotalVoters(ageInfo.getTotalVoters());
	 					votersDetailsVO.setTotalVotersPercent(ageInfo.getTotalVotersPercentage() != null ? new Float(ageInfo.getTotalVotersPercentage()):0.0f);
	 					votersDetailsVO.setTotalMaleVotersPercent(ageInfo.getMaleVotersPercentage() != null ?new Float(ageInfo.getMaleVotersPercentage()):0.0f);
	 					votersDetailsVO.setTotalFemaleVoters(ageInfo.getFemaleVoters());
	 					votersDetailsVO.setTotalFemaleVotersPercent(ageInfo.getFemaleVotersPercentage() != null ?new Float(ageInfo.getFemaleVotersPercentage()):0.0f);
	 					votersDetailsVO.setTotalMaleVoters(ageInfo.getMaleVoters());
	 					//if(!votersDetailsVO.getAgeRange().equalsIgnoreCase("Young Voters"))
	 					votersDetailsVOList.add(votersDetailsVO);
	 					
	 			}
	 		}
	 		return votersDetailsVOList;
	 	}catch (Exception e) {
	 		e.printStackTrace();
	 		log.error("Exception Occured in getAgeWiseVoterDetails() Method, Exception - "+e);
	 		return votersDetailsVOList;
	 	}
	 }
	 
	 


	public void getDetailsOfVotersHasAgeBetween18To25(Long reportLevelId, Long reportLevelValue, Long publicationDateId,VotersDetailsVO votersDetailsVO)
	{
		try{
			
			List<VoterAgeInfo> ageInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(reportLevelId, reportLevelValue, publicationDateId, getVoterAgeRangeId(IConstants.AGE18to22));
			if(ageInfoList != null && ageInfoList.size() > 0)
			{
				for(VoterAgeInfo ageInfo : ageInfoList)
				{
					votersDetailsVO.setAgeRange(IConstants.AGE18to22);
					votersDetailsVO.setTotalMaleVotesFor18To25(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters() :0l);
					votersDetailsVO.setMaleVotersPercentFor18To25(ageInfo.getMaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalFemaleVotersFor18To25(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters():0l);
					votersDetailsVO.setFemaleVotersPercentFor18To25(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalVotersFor18To25(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() :0l);
					votersDetailsVO.setVotersPercentFor18To25(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
					Long unKnowVoters = votersDetailsVO.getTotalVotersFor18To25()-(votersDetailsVO.getTotalMaleVotesFor18To25()+votersDetailsVO.getTotalFemaleVotersFor18To25());
					votersDetailsVO.setTotalUnknownVotersFor18To25(unKnowVoters != null ?unKnowVoters : 0l);
					//votersDetailsVO.setTotalUnknownVotersPercent(unKnowVoters != null ? (float) (unKnowVoters.floatValue() *100f / votersDetailsVO.getTotalVotersFor18To25 ()) : 0.0f);
					
				}
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getDetailsOfVotersHasAgeBetween18To25() Method, Exception - "+e);
			
		}
	}
	
	
	public void getDetailsOfVotersHasAgeBetween26To35(Long reportLevelId, Long reportLevelValue, Long publicationDateId,VotersDetailsVO votersDetailsVO)
	{
		try{
			
			List<VoterAgeInfo> ageInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(reportLevelId, reportLevelValue, publicationDateId, getVoterAgeRangeId(IConstants.AGE23to30));
			if(ageInfoList != null && ageInfoList.size() > 0)
			{
				for(VoterAgeInfo ageInfo : ageInfoList)
				{
					votersDetailsVO.setAgeRange(IConstants.AGE23to30);
					votersDetailsVO.setTotalMaleVotersFor26To35(ageInfo.getMaleVoters() != null ?ageInfo.getMaleVoters() : 0l);
					votersDetailsVO.setMaleVotersPercentFor26To35(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalFemaleVotersFor26To35(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters() : 0L);
					votersDetailsVO.setFemaleVotersPercentFor26To35(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalVotersFor26To35(ageInfo.getTotalVoters() != null ?ageInfo.getTotalVoters() : 0l);
					votersDetailsVO.setVotersPercentFor26To35(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
					Long unKnowVoters = votersDetailsVO.getTotalVotersFor26To35()-(votersDetailsVO.getTotalMaleVotersFor26To35()+votersDetailsVO.getTotalFemaleVotersFor26To35());
					votersDetailsVO.setTotalUnknownVotersFor26To35(unKnowVoters != null ?unKnowVoters : 0l);
					//votersDetailsVO.setTotalUnknownVotersPercent(unKnowVoters != null ? (float) (unKnowVoters.floatValue() *100f / votersDetailsVO.getTotalVotersFor26To35 ()) : 0.0f);
					
					
				}
				
			}
			
			
			 
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getDetailsOfVotersHasAgeBetween26To35() Method, Exception - "+e);
			
		}
	}
	
	
	public void getDetailsOfVotersHasAgeBetween36To45(Long reportLevelId, Long reportLevelValue, Long publicationDateId,VotersDetailsVO votersDetailsVO)
	{
		try{
			
			List<VoterAgeInfo> ageInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(reportLevelId, reportLevelValue, publicationDateId, getVoterAgeRangeId(IConstants.AGE31to45));
			if(ageInfoList != null && ageInfoList.size() > 0)
			{
				for(VoterAgeInfo ageInfo : ageInfoList)
				{
					votersDetailsVO.setAgeRange(IConstants.AGE31to45);
					votersDetailsVO.setTotalMaleVotersFor36To45(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters() : 0l);
					votersDetailsVO.setMaleVotersPercentFor36To45(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalFemaleVotersFor36To45(ageInfo.getFemaleVoters() != null ?ageInfo.getFemaleVoters() : 0l);
					votersDetailsVO.setFemaleVotersPercentFor36To45(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) :"0.00");
					votersDetailsVO.setTotalVotersFor36To45(ageInfo.getTotalVoters() != null ?ageInfo.getTotalVoters() : 0l);					 
					votersDetailsVO.setVotersPercentFor36To45(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
					Long unKnowVoters = votersDetailsVO.getTotalVotersFor36To45()-(votersDetailsVO.getTotalMaleVotersFor36To45()+votersDetailsVO.getTotalFemaleVotersFor36To45());
					votersDetailsVO.setTotalUnknownVotersFor36To45(unKnowVoters != null ?unKnowVoters : 0l);
					//votersDetailsVO.setTotalUnknownVotersPercent(unKnowVoters != null ? (float) (unKnowVoters.floatValue() *100f / votersDetailsVO.getTotalVotersFor36To45 ()) : 0.0f);
					
				}
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getDetailsOfVotersHasAgeBetween36To45() Method, Exception - "+e);
			
		}
	}
	
	public void getDetailsOfVotersHasAgeBetween46To60(Long reportLevelId, Long reportLevelValue, Long publicationDateId,VotersDetailsVO votersDetailsVO)
	{
		try{
			
			List<VoterAgeInfo> ageInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(reportLevelId, reportLevelValue, publicationDateId, getVoterAgeRangeId(IConstants.AGE46to60));
			if(ageInfoList != null && ageInfoList.size() > 0)
			{
				for(VoterAgeInfo ageInfo : ageInfoList)
				{
					votersDetailsVO.setAgeRange(IConstants.AGE46to60);
					votersDetailsVO.setTotalMaleVotersFor46To60(ageInfo.getMaleVoters() != null ?ageInfo.getMaleVoters():0l);
					votersDetailsVO.setMaleVotersPercentFor46To60(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalFemaleVotersFor46To60(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters() :0l);						
					votersDetailsVO.setFemaleVotersPercentFor46To60(ageInfo.getFemaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalVotersFor46To60(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() : 0l);
				    votersDetailsVO.setVotersPercentFor46To60(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
				    Long unKnowVoters = votersDetailsVO.getTotalVotersFor46To60()-(votersDetailsVO.getTotalMaleVotersFor46To60()+votersDetailsVO.getTotalFemaleVotersFor46To60());
					votersDetailsVO.setTotalUnknownVotersFor46To60(unKnowVoters != null ?unKnowVoters : 0l);
					//votersDetailsVO.setTotalUnknownVotersPercent(unKnowVoters != null ? (float) (unKnowVoters.floatValue() *100f / votersDetailsVO.getTotalVotersFor46To60 ()) : 0.0f);
					
				}
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getDetailsOfVotersHasAgeBetween36To45() Method, Exception - "+e);
			
		}
	}
	
	
	public void getDetaOfVotersHasAgeAbove60(Long reportLevelId, Long reportLevelValue, Long publicationDateId,VotersDetailsVO votersDetailsVO)
	{
		try{
			
			List<VoterAgeInfo> ageInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(reportLevelId, reportLevelValue, publicationDateId, getVoterAgeRangeId("60-Above"));
			if(ageInfoList != null && ageInfoList.size() > 0)
			{
				for(VoterAgeInfo ageInfo : ageInfoList)
				{
					votersDetailsVO.setAgeRange("60-Above");
					votersDetailsVO.setTotalMaleVotersForAbove60(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters():0l);
					votersDetailsVO.setMaleVotersPercentForAbove60(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())): "0.00");
					votersDetailsVO.setTotalFemaleVotersForAbove60(ageInfo.getFemaleVoters() != null ?ageInfo.getFemaleVoters():0l);
					votersDetailsVO.setFemaleVotersPercentForAbove60(ageInfo.getFemaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())):"0.00");
					votersDetailsVO.setTotalVotersForAbove60(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() : 0l);
					votersDetailsVO.setVotersPercentForAbove60(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
					Long unKnowVoters = votersDetailsVO.getTotalVotersForAbove60()-(votersDetailsVO.getTotalMaleVotersForAbove60()+votersDetailsVO.getTotalFemaleVotersForAbove60());
					votersDetailsVO.setTotalUnknownVotersForAbove60(unKnowVoters != null ?unKnowVoters : 0l);
					//votersDetailsVO.setTotalUnknownVotersPercent(unKnowVoters != null ? (float) (unKnowVoters.floatValue() *100f / votersDetailsVO.getTotalVotersForAbove60()) : 0.0f);
					
				}
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getDetailsOfVotersHasAgeBetween36To45() Method, Exception - "+e);
			
		}
	}
	
	public List<VotersDetailsVO> getAllAgesWiseVotersDetails(Long reportLevelId, Map<Long,String> reportLevelValues, Long publicationDateId,String type,Long constituencyId){
		Map<Long,VotersDetailsVO> ageWiseMap = new HashMap<Long,VotersDetailsVO>();
		Long age18to22 = getVoterAgeRangeId(IConstants.YOUNGER_VOTERS);
		Long age18to25 = getVoterAgeRangeId(IConstants.AGE18to25);
		Long age26to35 = getVoterAgeRangeId(IConstants.AGE23to30);
		Long age36to45 = getVoterAgeRangeId(IConstants.AGE31to45);
		Long age46to60 = getVoterAgeRangeId(IConstants.AGE46to60);
		Long above60 = getVoterAgeRangeId("60-Above");
		List<VoterAgeInfo> ageInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInAllRange(reportLevelId, reportLevelValues.keySet(), publicationDateId,constituencyId);
		if(ageInfoList != null && ageInfoList.size() > 0){
			VotersDetailsVO  votersDetailsVO = new VotersDetailsVO();
			  for(Long key : reportLevelValues.keySet()){
				  votersDetailsVO = new VotersDetailsVO();
				  votersDetailsVO.setTotalVoters(0l);
				  votersDetailsVO.setId(key);
				  votersDetailsVO.setAreaType(type);
				  if(type.equalsIgnoreCase("booth") || type.equalsIgnoreCase("ward")){
				      votersDetailsVO.setBoothName(reportLevelValues.get(key));
				      votersDetailsVO.setTehsilName(reportLevelValues.get(key));
				  }else if(type.equalsIgnoreCase("panchayat")){
					  votersDetailsVO.setPanchayatname(reportLevelValues.get(key));
				  }else if(type.equalsIgnoreCase("mandal") || type.equalsIgnoreCase("localElec" )|| type.equalsIgnoreCase("ward")){
					  votersDetailsVO.setTehsilName(reportLevelValues.get(key));
				  }
				  ageWiseMap.put(key, votersDetailsVO);
			  }
			  
			  for(VoterAgeInfo ageInfo:ageInfoList){
				  votersDetailsVO = ageWiseMap.get(ageInfo.getReportLevelValue());
				  if(votersDetailsVO != null){
					  
					 if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().longValue() == age18to22.longValue())
					 {
						 votersDetailsVO.setAgeRange(IConstants.YOUNGER_VOTERS);
						 votersDetailsVO.setTotalMaleVotersForYoungerVoters(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters() :0l);
						 votersDetailsVO.setMaleVotersPercentForYoungerVoters(ageInfo.getMaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalFemaleVotersForYoungerVoters(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters():0l);
							votersDetailsVO.setFemaleVotersPercentForYoungerVoters(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalVotersForYoungerVoters(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() :0l);
							votersDetailsVO.setVotersPercentForYoungerVoters(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
							Long unKnowVoters = votersDetailsVO.getTotalVotersForYoungerVoters()-(votersDetailsVO.getTotalMaleVotersForYoungerVoters()+votersDetailsVO.getTotalFemaleVotersForYoungerVoters());
							votersDetailsVO.setTotalUnknownVotersForYoungerVoters(unKnowVoters != null ?unKnowVoters : 0l); 
							if(ageInfo.getTotalVoters() != null)
							 votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalVoters()+ageInfo.getTotalVoters());  
					 }
					  
				  else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().longValue() == age18to25.longValue()){
						     votersDetailsVO.setAgeRange(IConstants.AGE18to25);
							votersDetailsVO.setTotalMaleVotesFor18To25(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters() :0l);
							votersDetailsVO.setMaleVotersPercentFor18To25(ageInfo.getMaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalFemaleVotersFor18To25(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters():0l);
							votersDetailsVO.setFemaleVotersPercentFor18To25(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalVotersFor18To25(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() :0l);
							votersDetailsVO.setVotersPercentFor18To25(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
							Long unKnowVoters = votersDetailsVO.getTotalVotersFor18To25()-(votersDetailsVO.getTotalMaleVotesFor18To25()+votersDetailsVO.getTotalFemaleVotersFor18To25());
							votersDetailsVO.setTotalUnknownVotersFor18To25(unKnowVoters != null ?unKnowVoters : 0l); 
							if(ageInfo.getTotalVoters() != null)
							 votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalVoters()+ageInfo.getTotalVoters());
					  }else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().longValue() == age26to35.longValue()){
						  votersDetailsVO.setAgeRange(IConstants.AGE23to30);
							votersDetailsVO.setTotalMaleVotersFor26To35(ageInfo.getMaleVoters() != null ?ageInfo.getMaleVoters() : 0l);
							votersDetailsVO.setMaleVotersPercentFor26To35(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalFemaleVotersFor26To35(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters() : 0L);
							votersDetailsVO.setFemaleVotersPercentFor26To35(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalVotersFor26To35(ageInfo.getTotalVoters() != null ?ageInfo.getTotalVoters() : 0l);
							votersDetailsVO.setVotersPercentFor26To35(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
							Long unKnowVoters = votersDetailsVO.getTotalVotersFor26To35()-(votersDetailsVO.getTotalMaleVotersFor26To35()+votersDetailsVO.getTotalFemaleVotersFor26To35());
							votersDetailsVO.setTotalUnknownVotersFor26To35(unKnowVoters != null ?unKnowVoters : 0l);
							if(ageInfo.getTotalVoters() != null)
							 votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalVoters()+ageInfo.getTotalVoters());
					  }else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().longValue() == age36to45.longValue()){
						    votersDetailsVO.setAgeRange(IConstants.AGE31to45);
							votersDetailsVO.setTotalMaleVotersFor36To45(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters() : 0l);
							votersDetailsVO.setMaleVotersPercentFor36To45(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalFemaleVotersFor36To45(ageInfo.getFemaleVoters() != null ?ageInfo.getFemaleVoters() : 0l);
							votersDetailsVO.setFemaleVotersPercentFor36To45(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) :"0.00");
							votersDetailsVO.setTotalVotersFor36To45(ageInfo.getTotalVoters() != null ?ageInfo.getTotalVoters() : 0l);					 
							votersDetailsVO.setVotersPercentFor36To45(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
							Long unKnowVoters = votersDetailsVO.getTotalVotersFor36To45()-(votersDetailsVO.getTotalMaleVotersFor36To45()+votersDetailsVO.getTotalFemaleVotersFor36To45());
							votersDetailsVO.setTotalUnknownVotersFor36To45(unKnowVoters != null ?unKnowVoters : 0l);
							if(ageInfo.getTotalVoters() != null) 
							 votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalVoters()+ageInfo.getTotalVoters());
					  }else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().longValue() == age46to60.longValue()){
						    votersDetailsVO.setAgeRange(IConstants.AGE46to60);
							votersDetailsVO.setTotalMaleVotersFor46To60(ageInfo.getMaleVoters() != null ?ageInfo.getMaleVoters():0l);
							votersDetailsVO.setMaleVotersPercentFor46To60(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalFemaleVotersFor46To60(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters() :0l);						
							votersDetailsVO.setFemaleVotersPercentFor46To60(ageInfo.getFemaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalVotersFor46To60(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() : 0l);
						    votersDetailsVO.setVotersPercentFor46To60(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
						    Long unKnowVoters = votersDetailsVO.getTotalVotersFor46To60()-(votersDetailsVO.getTotalMaleVotersFor46To60()+votersDetailsVO.getTotalFemaleVotersFor46To60());
							votersDetailsVO.setTotalUnknownVotersFor46To60(unKnowVoters != null ?unKnowVoters : 0l);
							if(ageInfo.getTotalVoters() != null)
							 votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalVoters()+ageInfo.getTotalVoters());
					  }else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().longValue() == above60.longValue()){
						    votersDetailsVO.setAgeRange("60-Above");
							votersDetailsVO.setTotalMaleVotersForAbove60(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters():0l);
							votersDetailsVO.setMaleVotersPercentForAbove60(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())): "0.00");
							votersDetailsVO.setTotalFemaleVotersForAbove60(ageInfo.getFemaleVoters() != null ?ageInfo.getFemaleVoters():0l);
							votersDetailsVO.setFemaleVotersPercentForAbove60(ageInfo.getFemaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())):"0.00");
							votersDetailsVO.setTotalVotersForAbove60(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() : 0l);
							votersDetailsVO.setVotersPercentForAbove60(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
							Long unKnowVoters = votersDetailsVO.getTotalVotersForAbove60()-(votersDetailsVO.getTotalMaleVotersForAbove60()+votersDetailsVO.getTotalFemaleVotersForAbove60());
							votersDetailsVO.setTotalUnknownVotersForAbove60(unKnowVoters != null ?unKnowVoters : 0l);
							if(ageInfo.getTotalVoters() != null)
							 votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalVoters()+ageInfo.getTotalVoters());
					  }
				  }
			  }
		}
		return new ArrayList<VotersDetailsVO>(ageWiseMap.values());
	}
	
	public List<VotersDetailsVO> getAgewiseVotersDetForTehsilsByConstituencyId(Long constituencyId,Long publicationDateId, String type){
		List<VotersDetailsVO> votersDetailsVOList = new ArrayList<VotersDetailsVO>();
		try{
			List<Object[]> mandalsList = tehsilDAO.findAllTehsilsByConstituencyIdAndPublicationDateId(constituencyId,publicationDateId);
			if(mandalsList != null && mandalsList.size() > 0)
			{
				
				/*votersDetailsVOList = new ArrayList<VotersDetailsVO>(0);
				for(Object[] params : tehsilIds)
				{
					VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
					getDetailsOfVotersHasAgeBetween18To25(getReportLevelId(IConstants.MANDAL), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween26To35(getReportLevelId(IConstants.MANDAL), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween36To45(getReportLevelId(IConstants.MANDAL), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween46To60(getReportLevelId(IConstants.MANDAL), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetaOfVotersHasAgeAbove60(getReportLevelId(IConstants.MANDAL), (Long)params[0], publicationDateId, votersDetailsVO);
					votersDetailsVO.setTehsilName(params[1].toString());
					
					Long totalVoters = votersDetailsVO.getTotalVotersFor18To25()+ votersDetailsVO.getTotalVotersFor26To35()+ votersDetailsVO.getTotalVotersFor36To45()+ votersDetailsVO.getTotalVotersFor46To60()+ votersDetailsVO.getTotalVotersForAbove60();
					votersDetailsVO.setTotalVoters(totalVoters);
					
					votersDetailsVO.setVotersPercentFor18To25(votersDetailsVO.getTotalVotersFor18To25() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
					votersDetailsVO.setVotersPercentFor26To35(votersDetailsVO.getTotalVotersFor26To35() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
					votersDetailsVO.setVotersPercentFor36To45(votersDetailsVO.getTotalVotersFor36To45() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
					votersDetailsVO.setVotersPercentFor46To60(votersDetailsVO.getTotalVotersFor46To60() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
					votersDetailsVO.setVotersPercentForAbove60(votersDetailsVO.getTotalVotersForAbove60() != 0? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
					
					votersDetailsVOList.add(votersDetailsVO);
				}*/
				
				Map<Long,String> mandalIds = new HashMap<Long,String>();
				
				for (Object[] mandal : mandalsList){
					mandalIds.put((Long)mandal[0],mandal[1]!=null?mandal[1].toString():"");
				}
				  if(mandalIds.size() > 0){
					  votersDetailsVOList = getAllAgesWiseVotersDetails(getReportLevelId(IConstants.MANDAL), mandalIds, publicationDateId,"Mandal",constituencyId);
				  }
					
			}
			List<Object[]> localBodyList = tehsilDAO.findAllLocalElecBodyByConstituencyIdAndPublicationDateId(constituencyId,publicationDateId);
			Constituency constituency = constituencyDAO.get(constituencyId);
			if(localBodyList != null && localBodyList.size() > 0)
			{
			  Map<Long,String> localBodyIds = new HashMap<Long,String>();
			  List<Long> urban = new ArrayList<Long>();
			  for (Object[] localBody : localBodyList){
				  localBodyIds.put((Long)localBody[0],localBody[1]!=null?localBody[1].toString()+" "+localBody[2].toString():"");
				  if("URBAN".equalsIgnoreCase(constituency.getAreaType()))
						 urban.add((Long)localBody[0]);
			  }
				if(localBodyIds.size() > 0)
				{
					if("URBAN".equalsIgnoreCase(constituency.getAreaType()) && urban != null && urban.size() == 1){
						List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId(urban.get(0),publicationDateId,constituencyId);
						
						Map<Long,String> wardIds = new HashMap<Long,String>();
						for (Object[] ward : wardsList){
							wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
						}
						if(wardIds.size() > 0)
							votersDetailsVOList.addAll(getAllAgesWiseVotersDetails(getReportLevelId("Ward"), wardIds, publicationDateId,"Ward",constituencyId));
						
					}else{
						List<VotersDetailsVO> localElec = getAllAgesWiseVotersDetails(getReportLevelId(IConstants.LOCALELECTIONBODY), localBodyIds, publicationDateId,"localElec",constituencyId);
						if(localElec != null && localElec.size() > 0)
							votersDetailsVOList.addAll(localElec);
					}
				}
			}
			Collections.sort(votersDetailsVOList,sortByName);
			return votersDetailsVOList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAgewiseVotersDetForTehsilsByConstituencyId() Method, Exception - "+e);
			return votersDetailsVOList;
		}
	}
	public static Comparator<VotersInfoForMandalVO> sortByInfoName = new Comparator<VotersInfoForMandalVO>()
			{	  
					  public int compare(VotersInfoForMandalVO arg1,VotersInfoForMandalVO arg2)
						{
						  return arg1.getName().trim().toUpperCase().compareTo(arg2.getName().trim().toUpperCase());
						}
			};
	public static Comparator<ImportantFamiliesInfoVo> sortByImpFamName = new Comparator<ImportantFamiliesInfoVo>()
					{	  
							  public int compare(ImportantFamiliesInfoVo arg1,ImportantFamiliesInfoVo arg2)
								{
								  return arg1.getName().trim().toUpperCase().compareTo(arg2.getName().trim().toUpperCase());
								}
					};	
	public static Comparator<VotersDetailsVO> sortByName = new Comparator<VotersDetailsVO>()
					{	  
							  public int compare(VotersDetailsVO arg1,VotersDetailsVO arg2)
								{
								  return arg1.getTehsilName().trim().toUpperCase().compareTo(arg2.getTehsilName().trim().toUpperCase());
								}
					};			
	public List<VotersDetailsVO> getAgewiseVotersDetaForPanchayatisByTehsilId(Long tehsilId,Long publicationDateId, String type,Long constituencyId,Long userId){
		  try{
			 List<VotersDetailsVO> votersDetailsVOList = new ArrayList<VotersDetailsVO>();
			 List<Object[]> panchayatiesList = panchayatDAO.getPanchayatsByTehsilId(tehsilId);
			 if(panchayatiesList != null && panchayatiesList.size() >0)
			 {
				/* votersDetailsVOList = new ArrayList<VotersDetailsVO>();
				for(Object[] params : panchayatis)
				{
					VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
					getDetailsOfVotersHasAgeBetween18To25(getReportLevelId("PANCHAYAT"), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween26To35(getReportLevelId("PANCHAYAT"), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween36To45(getReportLevelId("PANCHAYAT"), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween46To60(getReportLevelId("PANCHAYAT"), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetaOfVotersHasAgeAbove60(getReportLevelId("PANCHAYAT"), (Long)params[0], publicationDateId, votersDetailsVO);
					
					votersDetailsVO.setPanchayatname(params[1].toString());
					
						Long totalVoters = votersDetailsVO.getTotalVotersFor18To25()
							+ votersDetailsVO.getTotalVotersFor26To35()
							+ votersDetailsVO.getTotalVotersFor36To45()
							+ votersDetailsVO.getTotalVotersFor46To60()
							+ votersDetailsVO.getTotalVotersForAbove60();
					
					votersDetailsVO.setTotalVoters(totalVoters);
					
					votersDetailsVO.setVotersPercentFor18To25(votersDetailsVO.getTotalVotersFor18To25() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
					votersDetailsVO.setVotersPercentFor26To35(votersDetailsVO.getTotalVotersFor26To35() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
					votersDetailsVO.setVotersPercentFor36To45(votersDetailsVO.getTotalVotersFor36To45() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
					votersDetailsVO.setVotersPercentFor46To60(votersDetailsVO.getTotalVotersFor46To60() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
					votersDetailsVO.setVotersPercentForAbove60(votersDetailsVO.getTotalVotersForAbove60() != 0? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
					
					votersDetailsVOList.add(votersDetailsVO);
				}*/	
				 
				 
            List<Long> ids = new ArrayList<Long>();
            
            for (Object[] panchayat : panchayatiesList)
				ids.add((Long)panchayat[0]);
            
            List<Long> partialIds = null;
            
            if(ids != null && ids.size() >0)
            {            
			  partialIds = partialBoothPanchayatDAO.getPanchayatIdsForPartialPanchayat(ids, publicationDateId);
            }
            
			Map<Long,String> panchayatIds = new HashMap<Long,String>();
			
					for (Object[] panchayat : panchayatiesList){
						if(!partialIds.contains((Long)panchayat[0]))
						 panchayatIds.put((Long)panchayat[0], panchayat[1]!= null?panchayat[1].toString():"");
					}
					if(panchayatIds.size() > 0){
						
						votersDetailsVOList = getAllAgesWiseVotersDetails(getReportLevelId("Panchayat"), panchayatIds, publicationDateId,"Panchayat",constituencyId);
					}
					
					if(partialIds != null && partialIds.size() >0)
						votersDetailsVOList.addAll(voterReportService.caluculateAgeWiseDetailsForPanchayatByHamlets(userId,partialIds,publicationDateId,true));
				
			 }
			 return votersDetailsVOList;
				  
			 }catch (Exception e) {
				e.printStackTrace();
				log.error(" Exception Occured in getAgewiseVotersDetaForPanchayatisByTehsilId() Method, Exception - "+e);
				return null;
				}
			}
		
	
	public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByPanchayatId(Long panchayatId,Long publicationDateId, String type,Long constituencyId){
		
		try{
			Map<Long,String> boothIds = new HashMap<Long,String>();
			List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
			List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(panchayatId, publicationDateId);
			
			
			List<PartialBoothPanchayat> list = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(panchayatId, publicationDateId);
			
			if(list != null && list.size() >0)
             for(PartialBoothPanchayat partialPanchayatDetails:list)
            	 boothIds.put(partialPanchayatDetails.getBooth().getBoothId(), "booth-"+partialPanchayatDetails.getBooth().getPartNo());
            	 
			if(boothsList != null)
			{
				/*boothVotersList = new ArrayList<VotersDetailsVO>();
				for(Object[] params:booths){
					VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
					getDetailsOfVotersHasAgeBetween18To25(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween26To35(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween36To45(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween46To60(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetaOfVotersHasAgeAbove60(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
					Long totalVoters = 0l;
					votersDetailsVO.setBoothName(params[1].toString());
					if(votersDetailsVO.getTotalVotersFor18To25() != null && votersDetailsVO.getTotalVotersFor26To35()  != null && votersDetailsVO.getTotalVotersFor36To45() != null && votersDetailsVO.getTotalVotersFor46To60() != null && votersDetailsVO.getTotalVotersForAbove60() != null)
					{	 totalVoters = votersDetailsVO.getTotalVotersFor18To25()
						+ votersDetailsVO.getTotalVotersFor26To35()
						+ votersDetailsVO.getTotalVotersFor36To45()
						+ votersDetailsVO.getTotalVotersFor46To60()
						+ votersDetailsVO.getTotalVotersForAbove60();
				
					votersDetailsVO.setTotalVoters(totalVoters);
					}else{
						return null;
					}
					votersDetailsVO.setVotersPercentFor18To25(votersDetailsVO.getTotalVotersFor18To25() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
					votersDetailsVO.setVotersPercentFor26To35(votersDetailsVO.getTotalVotersFor26To35() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
					votersDetailsVO.setVotersPercentFor36To45(votersDetailsVO.getTotalVotersFor36To45() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
					votersDetailsVO.setVotersPercentFor46To60(votersDetailsVO.getTotalVotersFor46To60() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
					votersDetailsVO.setVotersPercentForAbove60(votersDetailsVO.getTotalVotersForAbove60() != 0? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
					boothVotersList.add(votersDetailsVO);
				}*/
			
				for (Object[] booth : boothsList){
					boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
				}
				if(boothIds.size() > 0)
					boothVotersList = getAllAgesWiseVotersDetails(getReportLevelId(IConstants.BOOTH), boothIds, publicationDateId,"Booth",constituencyId);
			
			}
		  return boothVotersList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAgewiseVotersDetForBoothsByPanchayatId() Method, Exception - "+e);
			return null;
		}
	}
public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByWardId(Long id,Long publicationDateId,Long constituencyId){
		
		try{
			List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
			List<Object[]> boothsList = boothDAO.getBoothsForWard(id,publicationDateId);
			if(boothsList != null)
			{
				Map<Long,String> boothIds = new HashMap<Long,String>();
				for (Object[] booth : boothsList){
					boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
				}
				if(boothIds.size() > 0)
					boothVotersList = getAllAgesWiseVotersDetails(getReportLevelId(IConstants.BOOTH), boothIds, publicationDateId,"Booth",constituencyId);
			
			}
		  return boothVotersList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAgewiseVotersDetForBoothsByPanchayatId() Method, Exception - "+e);
			return null;
		}
	}	
		public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByLocalElectionBodyId(Long localElectionBodyId,Long publicationDateId, String type,Long constituencyId){
			
			try{
				List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(localElectionBodyId);
				
				String electionType = localElectionBodyDAO.get((Long) list.get(0)).getElectionType().getElectionType();
				List<Object[]> wardsList = null;
				String tempTypeVar = "";
				if(electionType.equalsIgnoreCase(IConstants.GHMC))
				{
					wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId,constituencyId);
					tempTypeVar = "ward";
				}
				else
				{
					wardsList = boothDAO.getBoothIdsByLocalEleBodyId((Long) list.get(0),publicationDateId,constituencyId);
					tempTypeVar = "booth";
				}
				  if(wardsList != null && wardsList.size() >0)
				  {
					
					  Map<Long,String> wardIds = new HashMap<Long,String>();
					  for (Object[] ward : wardsList)
					  {
					    wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
					  }
					  if(wardIds.size() > 0)
						boothVotersList = getAllAgesWiseVotersDetails(getReportLevelId(tempTypeVar), wardIds, publicationDateId,tempTypeVar,constituencyId);
				
				  }
				
				return boothVotersList;
				}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getAgewiseVotersDetForBoothsByLocalElectionBodyId() Method, Exception - "+e);
				return null;
			}
		}
		
	/* Age wise Details */	
		
	/* Important Family */
		
		public ImportantFamiliesInfoVo getImportantFamilyInfo(Long reportLevelId, Long reportLevelValue, Long publicationDateId, String exeType,Long constituencyId)
		{
			ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();;
			try{
				List<VoterFamilyInfo> familyInfoList = voterFamilyInfoDAO.getVoterFamilyDetails(reportLevelId, reportLevelValue, publicationDateId,constituencyId);
				if(familyInfoList == null || familyInfoList.size() == 0)
				{
					importantFamiliesInfoVo.setDataPresent(false);
					return importantFamiliesInfoVo;
				}
				if(familyInfoList != null && familyInfoList.size() > 0)
				{
					Long totalFamilies = 0l;
					for(VoterFamilyInfo familyInfo : familyInfoList)
					{
						
						if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(1l))
						{
							importantFamiliesInfoVo.setBelow3(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies() : 0l);
							importantFamiliesInfoVo.setBelow3perc(familyInfo.getFamiliesPercentage() != null ?familyInfo.getFamiliesPercentage(): 0.00);
							totalFamilies += importantFamiliesInfoVo.getBelow3();
							importantFamiliesInfoVo.setDataPresent(true);
						}
						else if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(2l))
						{
							importantFamiliesInfoVo.setBetwn4to6(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies():0l);
							importantFamiliesInfoVo.setBetwn4to6perc(familyInfo.getFamiliesPercentage()!= null ?familyInfo.getFamiliesPercentage(): 0.00);
							totalFamilies +=importantFamiliesInfoVo.getBetwn4to6();
							importantFamiliesInfoVo.setDataPresent(true);
						}
						else if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(3l))
						{
							importantFamiliesInfoVo.setBetwn7to10(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies():0l);
							importantFamiliesInfoVo.setBetwn7to10perc(familyInfo.getFamiliesPercentage() != null ?familyInfo.getFamiliesPercentage(): 0.00);
							totalFamilies +=importantFamiliesInfoVo.getBetwn7to10();
							importantFamiliesInfoVo.setDataPresent(true);
						}
						else if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(4l))
						{
							importantFamiliesInfoVo.setAbove10(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies():0l);
							importantFamiliesInfoVo.setAbove10perc(familyInfo.getFamiliesPercentage() != null ?familyInfo.getFamiliesPercentage(): 0.00);
							totalFamilies +=importantFamiliesInfoVo.getAbove10();
							importantFamiliesInfoVo.setDataPresent(true);
						}
					}
					
					importantFamiliesInfoVo.setTotalFamalies(totalFamilies);
				}
					
				
				return importantFamiliesInfoVo;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImportantFamilyInfo() Method, Exception -"+e);
				return importantFamiliesInfoVo;
			} 
		}
		
		public List<ImportantFamiliesInfoVo> getImportantFamilyInfoForMultiple(Long reportLevelId, Map<Long,String> reportLevelValues, Long publicationDateId,String type,Long constituencyId)
		{
			List<ImportantFamiliesInfoVo> importantFamiliesInfoVoList = new ArrayList<ImportantFamiliesInfoVo>();
			ImportantFamiliesInfoVo importantFamiliesInfoVo = null;
			try{
				List<VoterFamilyInfo> familyInfoList = voterFamilyInfoDAO.getMultipleVoterFamilyDetails(reportLevelId, reportLevelValues.keySet(), publicationDateId,constituencyId);
				
				if(familyInfoList == null || familyInfoList.size() == 0)
				{
					//importantFamiliesInfoVo.setDataPresent(false);
					return importantFamiliesInfoVoList;
				}
				List<VotersInfoForMandalVO> votersInfoList = getVotersDetailsByVoterMultipleReportLevelIds(reportLevelId,reportLevelValues, publicationDateId,"",constituencyId);
				Map<Long,ImportantFamiliesInfoVo> importantFamiliesInfoVoMap = new HashMap<Long,ImportantFamiliesInfoVo>();
				 for(Long key : reportLevelValues.keySet()){ 
					 for(VotersInfoForMandalVO votersInfo : votersInfoList){
						  if(votersInfo.getId().longValue() == key.longValue()){
							  importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
							  importantFamiliesInfoVo.setTotalMaleVoters(votersInfo.getTotalMaleVoters());
							  importantFamiliesInfoVo.setTotalFemaleVoters(votersInfo.getTotalFemaleVoters());
							  importantFamiliesInfoVo.setUnKnowVoters(votersInfo.getUnKnowVoters());
							  importantFamiliesInfoVo.setTotalFamalies(0l);
							  importantFamiliesInfoVo.setType(type);
							  importantFamiliesInfoVo.setDataPresent(false);
							  if(votersInfo.getTotalVoters() != null){
								  try{
								   importantFamiliesInfoVo.setTotalVoters(new Long(votersInfo.getTotalVoters()));
								  }catch(Exception e){
									  importantFamiliesInfoVo.setTotalVoters(0l);
								  }
							  }else{
								  importantFamiliesInfoVo.setTotalVoters(0l);
							  }
							  importantFamiliesInfoVo.setName(reportLevelValues.get(key).toString());
							  importantFamiliesInfoVoMap.put(key,importantFamiliesInfoVo);
						  }
					 }
					 
					 
				 }
				if(familyInfoList != null && familyInfoList.size() > 0)
				{
					for(VoterFamilyInfo familyInfo : familyInfoList)
					{
						importantFamiliesInfoVo = importantFamiliesInfoVoMap.get(familyInfo.getReportLevelValue());
						if(importantFamiliesInfoVo == null)
							continue;
						if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(1l))
						{
							importantFamiliesInfoVo.setBelow3(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies() : 0l);
							importantFamiliesInfoVo.setBelow3perc(familyInfo.getFamiliesPercentage() != null ?familyInfo.getFamiliesPercentage(): 0.00);
							if(importantFamiliesInfoVo.getTotalFamalies() != null && familyInfo.getTotalFamilies() != null)
							importantFamiliesInfoVo.setTotalFamalies(importantFamiliesInfoVo.getTotalFamalies()+familyInfo.getTotalFamilies());
							importantFamiliesInfoVo.setDataPresent(true);
						}
						else if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(2l))
						{
							importantFamiliesInfoVo.setBetwn4to6(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies():0l);
							importantFamiliesInfoVo.setBetwn4to6perc(familyInfo.getFamiliesPercentage()!= null ?familyInfo.getFamiliesPercentage(): 0.00);
							if(importantFamiliesInfoVo.getTotalFamalies() != null && familyInfo.getTotalFamilies() != null)
							importantFamiliesInfoVo.setTotalFamalies(importantFamiliesInfoVo.getTotalFamalies()+familyInfo.getTotalFamilies());
							importantFamiliesInfoVo.setDataPresent(true);
						}
						else if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(3l))
						{
							importantFamiliesInfoVo.setBetwn7to10(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies():0l);
							importantFamiliesInfoVo.setBetwn7to10perc(familyInfo.getFamiliesPercentage() != null ?familyInfo.getFamiliesPercentage(): 0.00);
							if(importantFamiliesInfoVo.getTotalFamalies() != null && familyInfo.getTotalFamilies() != null)
							importantFamiliesInfoVo.setTotalFamalies(importantFamiliesInfoVo.getTotalFamalies()+familyInfo.getTotalFamilies());
							importantFamiliesInfoVo.setDataPresent(true);
						}
						else if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(4l))
						{
							importantFamiliesInfoVo.setAbove10(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies():0l);
							importantFamiliesInfoVo.setAbove10perc(familyInfo.getFamiliesPercentage() != null ?familyInfo.getFamiliesPercentage(): 0.00);
							if(importantFamiliesInfoVo.getTotalFamalies() != null && familyInfo.getTotalFamilies() != null)
							importantFamiliesInfoVo.setTotalFamalies(importantFamiliesInfoVo.getTotalFamalies()+familyInfo.getTotalFamilies());
							importantFamiliesInfoVo.setDataPresent(true);
						}
					}
					
					
				}
					
				
				return new ArrayList<ImportantFamiliesInfoVo>(importantFamiliesInfoVoMap.values());
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImportantFamilyInfoForMultiple() Method, Exception -",e);
				return importantFamiliesInfoVoList;
			} 
		}
		public ImportantFamiliesInfoVo getImportantFamilyDetailsForConstituency(String type, Long id, Long publicationDateId)
		{
			ImportantFamiliesInfoVo importantFamiliesInfoVo = null;
			try{
				importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
				importantFamiliesInfoVo = getImportantFamilyInfo(getReportLevelId(IConstants.CONSTITUENCY), id, publicationDateId,"main",id);
				Constituency constituency = constituencyDAO.get(id);
				importantFamiliesInfoVo.setType("Constituency");
				importantFamiliesInfoVo.setName(constituency.getName());
				importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCount(id,publicationDateId,"constituency"));
				VotersInfoForMandalVO votersInfoForConstituency = getVotersBasicInfoForConstituency(type, id, publicationDateId,"sub");
				importantFamiliesInfoVo.setTotalVoters(votersInfoForConstituency.getTotVoters() != null ? votersInfoForConstituency.getTotVoters().longValue() : 0l);
				importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForConstituency.getTotalMaleVoters());
				importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForConstituency.getTotalFemaleVoters());
				importantFamiliesInfoVo.setUnKnowVoters(votersInfoForConstituency.getUnKnowVoters());
				if(importantFamiliesInfoVo.isDataPresent())
				{
					List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
					Map<Long,String> mandalIds = new HashMap<Long,String>();
					Map<Long,String> localBodyIds = new HashMap<Long,String>();
					List<Long> urban = new ArrayList<Long>();
					for (SelectOptionVO mandal : mandalsList){
						if(mandal.getId().toString().substring(0,1).equalsIgnoreCase("2"))
						mandalIds.put(new Long(mandal.getId().toString().trim().substring(1)),mandal.getName());
						else
						{
							List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(mandal.getId().toString().substring(1)));
							 localBodyIds.put(new Long(list.get(0).toString()),mandal.getName());
							 if("URBAN".equalsIgnoreCase(constituency.getAreaType()))
								 urban.add(new Long(list.get(0).toString()));
						}
						
					}
					if(mandalIds.size() > 0)
						
						importantFamiliesInfoVo.setSubList(getImportantFamilyInfoForMultiple(getReportLevelId(IConstants.MANDAL),mandalIds, publicationDateId,"Mandal",id));
					if(localBodyIds.size() > 0)
					{
						if("URBAN".equalsIgnoreCase(constituency.getAreaType()) && urban != null && urban.size() == 1){
							List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId(urban.get(0),publicationDateId,id);
							
							Map<Long,String> wardIds = new HashMap<Long,String>();
							for (Object[] ward : wardsList){
								wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
							}
							if(wardIds.size() > 0)
								importantFamiliesInfoVo.getSubList().addAll(getImportantFamilyInfoForMultiple(getReportLevelId("Ward"), wardIds, publicationDateId,"Ward",id));
							
						}else{
							List<ImportantFamiliesInfoVo> localElec = getImportantFamilyInfoForMultiple(getReportLevelId(IConstants.LOCALELECTIONBODY),localBodyIds, publicationDateId,"Mandal",id);
							if(localElec != null && localElec.size() > 0)
								importantFamiliesInfoVo.getSubList().addAll(localElec);
						}
					}
				}
				
				return importantFamiliesInfoVo;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImportantFamilyDetailsForConstituency() Method, Exception - "+e);
				return importantFamiliesInfoVo;
			}
		}
		
		public ImportantFamiliesInfoVo getImpFamiliesForMandal(String type,Long id,Long publicationDateId, String exeType,Long constituencyId,Long userId)
		{
			ImportantFamiliesInfoVo importantFamiliesInfoVo = null;
			try{
				if(id.toString().substring(0, 1).trim().equalsIgnoreCase("2"))
				{
					
					
					importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
					importantFamiliesInfoVo = getImportantFamilyInfo(getReportLevelId(IConstants.MANDAL), new Long(id.toString().trim().substring(1)), publicationDateId,exeType,constituencyId);
					importantFamiliesInfoVo.setType("Mandal/Tehsil");
					importantFamiliesInfoVo.setName(tehsilDAO.get(new Long(id.toString().substring(1))).getTehsilName());
					VotersInfoForMandalVO votersInfoForMandal = getVotersBasicInfoForMandal("mandal", id, publicationDateId,"sub",constituencyId,null,userId);
					importantFamiliesInfoVo.setTotalVoters(votersInfoForMandal.getTotVoters() != null ? votersInfoForMandal.getTotVoters().longValue():0l);
					importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForMandal.getTotalMaleVoters());
					importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForMandal.getTotalFemaleVoters());
					importantFamiliesInfoVo.setUnKnowVoters(votersInfoForMandal.getUnKnowVoters());
					
					if(exeType.equalsIgnoreCase("main")&& importantFamiliesInfoVo.isDataPresent())
					{
						Map<Long,String> panchayatIds = new HashMap<Long,String>();
						Map<Long,String> partialPancMap = new HashMap<Long,String>();
						voterReportService.getPartialAndNormalPanchayats(publicationDateId, id, panchayatIds, partialPancMap);
						
						if(panchayatIds.size() > 0)
							importantFamiliesInfoVo.setSubList(getImportantFamilyInfoForMultiple(getReportLevelId("Panchayat"),panchayatIds, publicationDateId,"Panchayat",constituencyId));
						if(partialPancMap.size() > 0){
							for(Long key:partialPancMap.keySet()){
								importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForPanchayat(key,publicationDateId,"sub","sub",constituencyId,userId));
							}
								try{
								   Collections.sort(importantFamiliesInfoVo.getSubList(),sortByImpFamName);
								}catch(Exception e){}
						}
					}
					return importantFamiliesInfoVo;
				}
				else{
					
					importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1).trim()));
					List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(id.toString().trim().substring(1));
					Object[] reqName = assemblyLocalElectionBodyName.get(0);
					importantFamiliesInfoVo = getImportantFamilyInfo(getReportLevelId(IConstants.LOCALELECTIONBODY), (Long)list.get(0), publicationDateId,exeType,constituencyId);
				    String name = reqName[0].toString()+" "+reqName[1].toString();
					importantFamiliesInfoVo.setName(name);
					importantFamiliesInfoVo.setType("Muncipality/Corporation");
					VotersInfoForMandalVO votersInfoForMandal = getVotersBasicInfoForMandal("mandal", id, publicationDateId,"sub",constituencyId,null,userId);
					importantFamiliesInfoVo.setTotalVoters(votersInfoForMandal.getTotVoters()!= null ? votersInfoForMandal.getTotVoters().longValue():0l);
					importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForMandal.getTotalMaleVoters());
					importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForMandal.getTotalFemaleVoters());
					importantFamiliesInfoVo.setUnKnowVoters(votersInfoForMandal.getUnKnowVoters());
					
					if(exeType.equalsIgnoreCase("main")&& importantFamiliesInfoVo.isDataPresent())
					{
						
						if(reqName[1].toString().equalsIgnoreCase(IConstants.GHMC)){
							  List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId,constituencyId);
						
							Map<Long,String> wardIds = new HashMap<Long,String>();
							for (Object[] ward : wardsList){
								wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
							}
							if(wardIds.size() > 0)
								importantFamiliesInfoVo.setSubList(getImportantFamilyInfoForMultiple(getReportLevelId("Ward"),wardIds, publicationDateId,"Ward",constituencyId));
						}else{
								
							List<Object[]> boothsList = boothDAO.getBoothsInAMunicipality((Long) list.get(0),publicationDateId,constituencyId);

						
							Map<Long,String> boothIds = new HashMap<Long,String>();
							
							for (Object[] booth : boothsList)
								boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");

							if(boothIds.size() > 0)
								importantFamiliesInfoVo.setSubList(getImportantFamilyInfoForMultiple(getReportLevelId(IConstants.BOOTH),boothIds, publicationDateId,"Booth",constituencyId));
						
						}

					}
					return importantFamiliesInfoVo;
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImpFamiliesForMandal() Method, Exception - "+e);
				return importantFamiliesInfoVo;
			}
		}
		
		
		public ImportantFamiliesInfoVo getImpFamiliesForPanchayat(Long id,Long publicationDateId,String reqType,String exeType,Long constituencyId){
			try{
				
			ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
			importantFamiliesInfoVo = getImportantFamilyInfo(getReportLevelId("PANCHAYAT"), id, publicationDateId, exeType,constituencyId);
			importantFamiliesInfoVo.setType("Panchayat");
			importantFamiliesInfoVo.setName(panchayatDAO.get(id).getPanchayatName());
			
			VotersInfoForMandalVO VotersInfoForPanchayat = getVotersBasicInfoForPanchayat(id, publicationDateId, "sub",constituencyId);
			importantFamiliesInfoVo.setTotalMaleVoters(VotersInfoForPanchayat.getTotalMaleVoters());
			importantFamiliesInfoVo.setTotalFemaleVoters(VotersInfoForPanchayat.getTotalFemaleVoters());
			importantFamiliesInfoVo.setUnKnowVoters(VotersInfoForPanchayat.getUnKnowVoters());
			importantFamiliesInfoVo.setTotalVoters(VotersInfoForPanchayat.getTotVoters() != null?VotersInfoForPanchayat.getTotVoters().longValue():0l);
			
			 if(exeType.equalsIgnoreCase("main")  && importantFamiliesInfoVo.isDataPresent()){
				 /*List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(id,publicationDateId);
			     for(Object[] booth : boothsList){
			    	 importantFamiliesInfoVo.getSubList().add(getImpFamiliesForBooth("booth",(Long)booth[0],publicationDateId,"sub"));
			     }*/
			     
			     List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(id,publicationDateId);
					Map<Long,String> boothIds = new HashMap<Long,String>();
					for (Object[] booth : boothsList){
						boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
					}
					boothsList = partialBoothPanchayatDAO.getPartialBoothsDetails(id, publicationDateId);
					for (Object[] booth : boothsList){
						boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
					}
					if(boothIds.size() > 0)
					importantFamiliesInfoVo.setSubList(getImportantFamilyInfoForMultiple(getReportLevelId(IConstants.BOOTH),boothIds, publicationDateId,"Booth",constituencyId));
			 }
			 return importantFamiliesInfoVo;
			}
			catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImpFamiliesForPanchayat() Method, Exception - "+e);
				return null;
			}
		}
		
		public ImportantFamiliesInfoVo getImpFamiliesForWard(Long id,Long publicationDateId,String exeType,Long constituencyId){
			try{
				
			ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
			importantFamiliesInfoVo = getImportantFamilyInfo(getReportLevelId("Ward"), id, publicationDateId, exeType,constituencyId);
			importantFamiliesInfoVo.setType("Ward");
			importantFamiliesInfoVo.setName(constituencyDAO.get(id).getName());
			VotersInfoForMandalVO VotersInfoForPanchayat = getVotersBasicInfoForWard(id, publicationDateId, "sub",constituencyId);
			importantFamiliesInfoVo.setTotalMaleVoters(VotersInfoForPanchayat.getTotalMaleVoters());
			importantFamiliesInfoVo.setTotalFemaleVoters(VotersInfoForPanchayat.getTotalFemaleVoters());
			importantFamiliesInfoVo.setUnKnowVoters(VotersInfoForPanchayat.getUnKnowVoters());
			importantFamiliesInfoVo.setTotalVoters(VotersInfoForPanchayat.getTotVoters() != null?VotersInfoForPanchayat.getTotVoters().longValue():0l);
			
			 if(exeType.equalsIgnoreCase("main")  && importantFamiliesInfoVo.isDataPresent()){
			     List<Object[]> boothsList = boothDAO.getBoothsForWard(id,publicationDateId);
					Map<Long,String> boothIds = new HashMap<Long,String>();
					for (Object[] booth : boothsList){
						boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
					}
					if(boothIds.size() > 0)
					importantFamiliesInfoVo.setSubList(getImportantFamilyInfoForMultiple(getReportLevelId(IConstants.BOOTH),boothIds, publicationDateId,"Booth",constituencyId));
			 }
			 return importantFamiliesInfoVo;
			}
			catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImpFamiliesForPanchayat() Method, Exception - "+e);
				return null;
			}
		}
		
		public ImportantFamiliesInfoVo getImpFamiliesForBooth(String type,Long id,Long publicationDateId,String exeType,Long constituencyId){
			ImportantFamiliesInfoVo importantFamiliesInfoVo = null;
			try{
				importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
				importantFamiliesInfoVo = getImportantFamilyInfo(getReportLevelId(IConstants.BOOTH), id, publicationDateId, exeType,constituencyId);
				importantFamiliesInfoVo.setType("Booth");
				importantFamiliesInfoVo.setName(boothDAO.get(id).getPartNo());
				VotersInfoForMandalVO votersInfoForBooth = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.BOOTH), id, publicationDateId,"booth-"+boothDAO.get(id).getPartNo(),"main",constituencyId);
				importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForBooth.getTotalMaleVoters());
				importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForBooth.getTotalFemaleVoters());
				importantFamiliesInfoVo.setUnKnowVoters(votersInfoForBooth.getUnKnowVoters());
				importantFamiliesInfoVo.setTotalVoters(votersInfoForBooth.getTotVoters() != null ?votersInfoForBooth.getTotVoters().longValue():0l);
				return importantFamiliesInfoVo;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImpFamiliesForBooth() Method, Exception - "+e);
				return importantFamiliesInfoVo;
			}
		}
		
		
	/* Important Family */	
		
		
		
		
/*public List<VotersInfoForMandalVO> getPreviousVotersCountDetailsForAllLevels(
				 Long constituencyId,Long mandalId, Long panchayatId, Long boothId , String type) {
			
			log.debug("Entered into the getVotersCountDetailsForAllLevels service method");	
			
			List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>();
			
			try{
				List<VoterVO> previousDetailsList = null;
				
				List<VoterVO> previousDetailsList = 
						getAllElectionAndPublicationsForConstituencyId(constituencyId);
				
				if(type.equalsIgnoreCase("constituency"))
				  previousDetailsList = getAllElectionAndPublications(constituencyId , type,constituencyId);
				else if(type.equalsIgnoreCase("mandal"))
					  previousDetailsList = getAllElectionAndPublications(mandalId , type,constituencyId);
				else if(type.equalsIgnoreCase("panchayat"))
					  previousDetailsList = getAllElectionAndPublications(panchayatId , type , constituencyId);
				else if(type.equalsIgnoreCase("booth") || type.equalsIgnoreCase("ward"))
					  previousDetailsList = getAllElectionAndPublications(boothId , type,constituencyId);
				
				for(VoterVO voterVO:previousDetailsList){
					
					VotersInfoForMandalVO votersInfoForMandalVO = null;
					
					if(voterVO.getType().equalsIgnoreCase("Election"))
						votersInfoForMandalVO = getVotersDetailsByElectionBasis(
								constituencyId,mandalId, panchayatId, boothId , type,voterVO);
				    else if(voterVO.getType().equalsIgnoreCase("Publication")){
				    	votersInfoForMandalVO = getVotersDetailsByPublicationBasis(
				   			  constituencyId, mandalId,  panchayatId,  boothId , type,voterVO);
				    }
					if(votersInfoForMandalVOList.size() > 0){
						
						VotersInfoForMandalVO previousResults = votersInfoForMandalVOList.get(votersInfoForMandalVOList.size()-1);
						VotersInfoForMandalVO currentResult = votersInfoForMandalVO;
						
						if(currentResult.getTotalMaleVoters() != null){							
							votersInfoForMandalVO.setMaleVotersDiff(Long.parseLong(currentResult.getTotalMaleVoters()) - Long.parseLong(previousResults.getTotalMaleVoters()));
						}else{
							if(previousResults.getTotalMaleVoters() != null)
							 votersInfoForMandalVO.setMaleVotersDiff(Long.parseLong("0") - Long.parseLong(previousResults.getTotalMaleVoters()));
							else
							 votersInfoForMandalVO.setMaleVotersDiff(Long.parseLong(previousResults.getTotalMaleVoters()));
                        }
						
						if(currentResult.getTotalFemaleVoters() != null){							
							votersInfoForMandalVO.setFemaleVotersDiff(Long.parseLong(currentResult.getTotalFemaleVoters()) - Long.parseLong(previousResults.getTotalFemaleVoters()));
						}else{
							
							if(previousResults.getTotalFemaleVoters() != null)
							  votersInfoForMandalVO.setFemaleVotersDiff(Long.parseLong("0") - Long.parseLong(previousResults.getTotalFemaleVoters()));
							else
							  votersInfoForMandalVO.setFemaleVotersDiff(Long.parseLong(previousResults.getTotalFemaleVoters()));
								
                        }
						
						if(votersInfoForMandalVO.getTotVoters() != null){
							votersInfoForMandalVO.setTotalVotersDiff(currentResult.getTotVoters().longValue() - previousResults.getTotVoters().longValue());
						}else{
							if(previousResults.getTotVoters() != null)
							  votersInfoForMandalVO.setTotalVotersDiff(Long.parseLong("0") - previousResults.getTotVoters().longValue());
							else
							  votersInfoForMandalVO.setTotalVotersDiff(previousResults.getTotVoters().longValue());


						}
					}
					if(votersInfoForMandalVO.isDatapresent())				
					  votersInfoForMandalVOList.add(votersInfoForMandalVO);
					
				}
				
				
				
			}catch(Exception e){
				log.error("Exception raised in getVotersCountDetailsForAllLevels service method");
				e.printStackTrace();
				
			}
			
			return votersInfoForMandalVOList;
		}*/
		
		public List<VotersInfoForMandalVO> getPreviousVotersCountDetailsForAllLevels(Long constituencyId,Long mandalId, Long panchayatId, Long boothId , String type,Long userId) 
		{
			
			log.debug("Entered into the getVotersCountDetailsForAllLevels service method");	
			List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>();
			try{
				List<VoterVO> previousDetailsList = null;
				
				if(type.equalsIgnoreCase("constituency"))
				  previousDetailsList = getAllElectionAndPublications(constituencyId , type,constituencyId);
				else if(type.equalsIgnoreCase("mandal"))
					  previousDetailsList = getAllElectionAndPublications(mandalId , type,constituencyId);
				else if(type.equalsIgnoreCase("panchayat"))
					  previousDetailsList = getAllElectionAndPublications(panchayatId , type , constituencyId);
				else if(type.equalsIgnoreCase("booth") || type.equalsIgnoreCase("ward"))
					  previousDetailsList = getAllElectionAndPublications(boothId , type,constituencyId);
				
				for(VoterVO voterVO:previousDetailsList)
				{
					
					VotersInfoForMandalVO votersInfoForMandalVO = null;
					
					if(voterVO.getType().equalsIgnoreCase("Election"))
						votersInfoForMandalVO = getVotersDetailsByElectionBasis(
								constituencyId,mandalId, panchayatId, boothId , type,voterVO);
				    else if(voterVO.getType().equalsIgnoreCase("Publication")){
				    	votersInfoForMandalVO = getVotersDetailsByPublicationBasis(
				   			  constituencyId, mandalId,  panchayatId,  boothId , type,voterVO,userId);
				    }
					if(votersInfoForMandalVOList.size() > 0){
						
						VotersInfoForMandalVO previousResults = votersInfoForMandalVOList.get(votersInfoForMandalVOList.size()-1);
						VotersInfoForMandalVO currentResult = votersInfoForMandalVO;
						
						if(currentResult.getTotalMaleVoters() != null){							
							votersInfoForMandalVO.setMaleVotersDiff(Long.parseLong(currentResult.getTotalMaleVoters()) - Long.parseLong(previousResults.getTotalMaleVoters()));
						}else{
							if(previousResults.getTotalMaleVoters() != null)
							 votersInfoForMandalVO.setMaleVotersDiff(Long.parseLong("0") - Long.parseLong(previousResults.getTotalMaleVoters()));
							else
							 votersInfoForMandalVO.setMaleVotersDiff(Long.parseLong(previousResults.getTotalMaleVoters()));
                       }
						
						if(currentResult.getTotalFemaleVoters() != null){							
							votersInfoForMandalVO.setFemaleVotersDiff(Long.parseLong(currentResult.getTotalFemaleVoters()) - Long.parseLong(previousResults.getTotalFemaleVoters()));
						}else{
							
							if(previousResults.getTotalFemaleVoters() != null)
							  votersInfoForMandalVO.setFemaleVotersDiff(Long.parseLong("0") - Long.parseLong(previousResults.getTotalFemaleVoters()));
							else
							  votersInfoForMandalVO.setFemaleVotersDiff(Long.parseLong(previousResults.getTotalFemaleVoters()));
								
                       }
						
						if(votersInfoForMandalVO.getTotVoters() != null){
							votersInfoForMandalVO.setTotalVotersDiff(currentResult.getTotVoters().longValue() - previousResults.getTotVoters().longValue());
						}else{
							if(previousResults.getTotVoters() != null)
							  votersInfoForMandalVO.setTotalVotersDiff(Long.parseLong("0") - previousResults.getTotVoters().longValue());
							else
							  votersInfoForMandalVO.setTotalVotersDiff(previousResults.getTotVoters().longValue());


						}
					}
					if(votersInfoForMandalVO.isDatapresent())				
					  votersInfoForMandalVOList.add(votersInfoForMandalVO);
					
				}
				
				
				
			}catch(Exception e){
				log.error("Exception raised in getVotersCountDetailsForAllLevels service method");
				e.printStackTrace();
				
			}
			return votersInfoForMandalVOList;
		}

    public List<VoterVO> getAllElectionAndPublications(Long id , String type , Long constituencyId){
    	log.debug("Entered into the getAllElectionAndPublications service method");
    	List<VoterVO> previousDetailsList = new ArrayList<VoterVO>();
    	List<Long> constituencyIds = new ArrayList<Long>();
    	constituencyIds.add(constituencyId);
    	List parliamentList = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
		Long parliamentConstId = 0l;
		if(parliamentList != null && parliamentList.size() > 0)
		{
			Object[] ParliamentDetails = (Object[])parliamentList.get(0);
			if((Long)ParliamentDetails[0]!= null)
			{
				constituencyIds.add((Long)ParliamentDetails[0]);
				parliamentConstId = (Long)ParliamentDetails[0];
			}
		}
    	
    	try{
    		List<Object[]> list = null;
    		String type2 = null;
    		
    		if(type.equalsIgnoreCase("constituency")){
    			//list  = constituencyElectionDAO.findAllElectionsHappendInAConstituency(id);
    			list  = constituencyElectionDAO.findAllElectionsHappendInAConstByConstIds(constituencyIds);
    			
    		}else if(type.equalsIgnoreCase("mandal")){
    			if(id.toString().trim().substring(0, 1).equalsIgnoreCase("2"))
    			{
    				id = new Long(id.toString().trim().substring(1));
    				list = hamletBoothElectionDAO.findAllElectionsHappendInAMandal(id,constituencyIds);
    				type2 = IConstants.RURAL;
    			}
    			else if(id.toString().trim().substring(0, 1).equalsIgnoreCase("1"))
    			{
    				List<Object> list2 = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().trim().substring(1)));
    				id = (Long)list2.get(0);
    				list = hamletBoothElectionDAO.findAllElectionsHappendInALocalElectionBody(id);
    				type2 = IConstants.URBAN;
    			}
    			
    		}else if(type.equalsIgnoreCase("panchayat")){
    			 list = hamletBoothElectionDAO.findAllElectionsHappendInAPanchayat(id);
    		}else if(type.equalsIgnoreCase("booth") || type.equalsIgnoreCase("ward")){
    			
    			/*List<Long> constElectionIds  = constituencyElectionDAO.
    					getConstituencyElectionsForConstituency(constituencyId);
    			
    			list = boothConstituencyElectionDAO.findAllElectionsHappenedInABooth1(id,constElectionIds);
    			*/
    			//list = boothConstituencyElectionDAO.findAllElectionsHappenedInABooth(id);
    			list  = constituencyElectionDAO.findAllElectionsHappendInAConstituency(constituencyId);
    		}
    		
    		List<Long> electionIds = new ArrayList<Long>();
    		Map<Date,Election> map = new HashMap<Date, Election>();
    		List<Election> newElectionList = new ArrayList<Election>();
    	
    		
    		for(Object[] obj:list)
    			electionIds.add((Long)obj[0]);
    		
    		List<Election> electionList = electionDAO.getElectionDetailsForElections(electionIds);
    		
    		for(Election election:electionList){
    			if("Parliament".equalsIgnoreCase(election.getElectionScope().getElectionType().getElectionType()))
    			  map.put(election.getElectionDate(),election);
    		}
    		for(Election election:electionList){
    		   if("Assembly".equalsIgnoreCase(election.getElectionScope().getElectionType().getElectionType()))
    			map.put(election.getElectionDate(),election);
    		}
    		
    		for (Map.Entry<Date, Election> entry : map.entrySet()) {
    			newElectionList.add(entry.getValue());
    		}
    		
    		for(Election election:newElectionList){
				
				VoterVO voterVO = new VoterVO();				
				voterVO.setElectionId(election.getElectionId());
				voterVO.setElectionDate(election.getElectionDate());
				voterVO.setType("Election");
				if(election.getElectionScope().getElectionType().getElectionType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
				 voterVO.setAssemblyConstituencyId(constituencyId);
				else if(election.getElectionScope().getElectionType().getElectionType().equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
					voterVO.setParliamentConstituencyId(parliamentConstId);
				voterVO.setElectionType(election.getElectionScope().getElectionType().getElectionType());
					
				previousDetailsList.add(voterVO);			
			}
    		
    		List<Long> publicationIdsList = boothDAO
					.getAllPublicationDetailsForConstituency(constituencyId);			
			
			for(Long publicationId:publicationIdsList){
				
				PublicationDate publicationDate = publicationDAO.get(publicationId);
				
				VoterVO voterVO = new VoterVO();				
				voterVO.setPublicationDateId(publicationDate.getPublicationDateId());
				voterVO.setElectionDate(publicationDate.getDate());
				voterVO.setPublicationDate(publicationDate.getDate());
			    voterVO.setType("Publication");				 
				previousDetailsList.add(voterVO);			
				
			}
			
			Collections.sort(previousDetailsList);
    		
    		
    	}catch(Exception e){
    		
    		log.error("Exception raised in getAllElectionAndPublications service method");
    		e.printStackTrace();
    		
    	}
    	return previousDetailsList;
    	
    } 
		
	public List<VoterVO> getAllElectionAndPublicationsForConstituencyId(Long constituencyId){
			
			log.debug("Entered into the getAllElectionAndPublicationsForConstituencyId service method");
			List<VoterVO> previousDetailsList = new ArrayList<VoterVO>();
			
			try{
				List<Object[]> electionIdsList = constituencyElectionDAO
						.getAllElectionIdsForConstituency(constituencyId);			
				
				for(Object[]obj:electionIdsList){
					
					VoterVO voterVO = new VoterVO();				
					voterVO.setElectionId((Long)obj[0]);
					voterVO.setElectionDate((Date)obj[1]);
					voterVO.setType("Election");				
					previousDetailsList.add(voterVO);			
				}
				
				List<Long> publicationIdsList = boothDAO
						.getAllPublicationDetailsForConstituency(constituencyId);			
				
				for(Long publicationId:publicationIdsList){
					
					PublicationDate publicationDate = publicationDAO.get(publicationId);
					
					VoterVO voterVO = new VoterVO();				
					voterVO.setPublicationDateId(publicationDate.getPublicationDateId());
					voterVO.setElectionDate(publicationDate.getDate());
					voterVO.setPublicationDate(publicationDate.getDate());
				    voterVO.setType("Publication");				 
					previousDetailsList.add(voterVO);			
					
				}
				
				Collections.sort(previousDetailsList);
				
				//getVotersCountDetailsForAllLevels(previousDetailsList);
				
			}catch(Exception e){
				log.error("Exception rised in getAllElectionAndPublicationsForConstituencyId method : ",e);
				e.printStackTrace();
				return null;
			}
			
			return previousDetailsList;
		}
		
		
		public VotersInfoForMandalVO getVotersDetailsByPublicationBasis(
				 Long constituencyId,Long mandalId, Long panchayatId, Long boothId , String type,VoterVO voterVO,Long userId){
			
	            log.debug("Entered into the getVotersDetailsByPublicationBasis service method");
	            
	            VotersInfoForMandalVO votersInfoForMandalVO = null;
	            Long totalBooths = 0l;
	            
	            try{
					if(type.equalsIgnoreCase("constituency")){
						votersInfoForMandalVO = getConstituencyWiseVotersDetailsByPublicationBasis(type,constituencyId,voterVO.getPublicationDateId());
					}else if(type.equalsIgnoreCase("booth")){
						votersInfoForMandalVO = getBoothWiseVotersDetailsByPublicationBasis(boothId,voterVO.getPublicationDateId(),constituencyId);
				    }else if(type.equalsIgnoreCase("panchayat")){
				    	votersInfoForMandalVO = getPanchayatWiseVotersDetailsByPublicationBasis(type,panchayatId,voterVO.getPublicationDateId(),constituencyId,userId);
					}else if(type.equalsIgnoreCase("mandal")){
						votersInfoForMandalVO = getMandalVotersDetailsByPublicationBasis(type,mandalId,voterVO.getPublicationDateId(),constituencyId);
					}else if(type.equalsIgnoreCase("ward")){
						votersInfoForMandalVO = getWardWiseVotersDetailsByPublicationBasis(boothId,voterVO.getPublicationDateId(),constituencyId);
				    }
					String maleVoters = votersInfoForMandalVO.getTotalMaleVoters();
					String femaleVoters = votersInfoForMandalVO.getTotalFemaleVoters();
					
					//if(maleVoters == null || femaleVoters == null || Long.parseLong(maleVoters) == 0 || Long.parseLong(femaleVoters) == 0)
					if(maleVoters == null || femaleVoters == null ||(maleVoters.equalsIgnoreCase("0") && femaleVoters.equalsIgnoreCase("0")))
						votersInfoForMandalVO.setDatapresent(false);
					
					votersInfoForMandalVO.setIsPublication("true");
					votersInfoForMandalVO.setPublicationDate(voterVO.getPublicationDate().toString());
					votersInfoForMandalVO.setElectinYear(voterVO.getPublicationDate().getYear()+1900);
			
	            }catch(Exception e){
	            	
	            	log.error("Exception raised in getVotersDetailsByPublicationBasis service method");
	            	e.printStackTrace();
	            	
	            }
	            
	            return votersInfoForMandalVO;
			
		}
		
		public VotersInfoForMandalVO getConstituencyWiseVotersDetailsByPublicationBasis(String type,Long constituencyId,Long publicationId){
			 VotersInfoForMandalVO votersInfoForMandalVO = null;
	         Long totalBooths = 0l;
	         
	         //getting total booths count
			List<Long> boothsCount = boothDAO.getBoothsCountByPublicationId(type,constituencyId,publicationId,constituencyId);
			if(boothsCount != null &&  boothsCount.size() >0)
				totalBooths = (Long)boothsCount.get(0);
			
			//getting data from intermediate table
			votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.CONSTITUENCY),constituencyId,publicationId, "", "",constituencyId);
			
			//if data not present in intermediate table calculating data
			if(votersInfoForMandalVO == null || !votersInfoForMandalVO.isDatapresent())
			{	
				List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountByPublicationId(type,constituencyId,publicationId,null);
			    votersInfoForMandalVO = populateDataToVotersInfoForMandalVO(votersCountList,constituencyId,constituencyDAO.get(constituencyId).getName(),"Constituency");
			}
			
			if(votersInfoForMandalVO != null)
			   votersInfoForMandalVO.setTotalBooths(totalBooths);
			
			return votersInfoForMandalVO;
		}
		
		public VotersInfoForMandalVO getPanchayatWiseVotersDetailsByPublicationBasis(String type,Long panchayatId,Long publicationId,Long constituencyId,Long userId){
	      	  VotersInfoForMandalVO votersInfoForMandalVO = null;
	      	  List<Object[]> votersCountList = null;
		            Long totalBooths = 0l;
		            
		          //getting total booths count
				  List<PartialBoothPanchayat> list = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(panchayatId, publicationId);
		        	List<Long> boothsCount = boothDAO.getBoothsCountByPublicationId(type,panchayatId,publicationId,constituencyId);
				if(boothsCount != null &&  boothsCount.size() >0 && boothsCount.get(0) != null)
					totalBooths = (Long)boothsCount.get(0);
				
				
				if(list != null && list.size() >0){
				     Long count = partialBoothPanchayatDAO.getPartialBoothsCount(panchayatId, publicationId);
					if(count != null)
						totalBooths = totalBooths+count;
					 votersCountList = boothPublicationVoterDAO.getVotersCountForPanchayatByPublicationIdAndHamlet(panchayatId,publicationId,userId);

					 if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
							votersInfoForMandalVO = populateDataToVotersInfoForMandalVO(votersCountList,panchayatId,panchayatDAO.get(panchayatId).getPanchayatName(),"Panchayat");						
							
						}else{
							  votersInfoForMandalVO = new VotersInfoForMandalVO();
							  votersInfoForMandalVO.setDatapresent(false);						
						 }
					
					 if(votersInfoForMandalVO != null)
							votersInfoForMandalVO.setTotalBooths(totalBooths);
							
						return votersInfoForMandalVO;
				}
		    	
				//getting data from intermediate table
				votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId("Panchayat"),panchayatId,publicationId, "", "",constituencyId);
				
				//if data not present in intermediate table calculating data
				if(votersInfoForMandalVO == null || !votersInfoForMandalVO.isDatapresent())
				{
					
					/*List<Object[]> votersCountList =
					List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountForPanchayatByPublicationId(panchayatId,publicationId);
					*/
				/*	List<PartialBoothPanchayat> list = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(panchayatId, publicationId);
					
					List<Object[]> votersCountList = null;
					
					if(list != null && list.size() > 0)
					{
						 votersCountList = boothPublicationVoterDAO.getVotersCountForPanchayatByPublicationIdAndHamlet(panchayatId,publicationId,userId);

					}else*/
						 votersCountList = boothPublicationVoterDAO.getVotersCountForPanchayatByPublicationId(panchayatId,publicationId);
					
					if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
						votersInfoForMandalVO = populateDataToVotersInfoForMandalVO(votersCountList,panchayatId,panchayatDAO.get(panchayatId).getPanchayatName(),"Panchayat");						
						
					}else{
						  votersInfoForMandalVO = new VotersInfoForMandalVO();
						  votersInfoForMandalVO.setDatapresent(false);						
					 }
				}
				if(votersInfoForMandalVO != null)
					votersInfoForMandalVO.setTotalBooths(totalBooths);
					
				return votersInfoForMandalVO;
			}
        
        public VotersInfoForMandalVO getBoothWiseVotersDetailsByPublicationBasis(Long boothId,Long publicationId,Long constituencyId){
        	  VotersInfoForMandalVO votersInfoForMandalVO = null;
	            
	          //getting data from intermediate table    
        	votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.BOOTH),boothId,publicationId, "", "",constituencyId);
			
        	 //if data not present in intermediate table calculating data
        	if(votersInfoForMandalVO == null || !votersInfoForMandalVO.isDatapresent())
			{
			 Booth booth = boothDAO.get(boothId);
			 List<Object[]> votersCountList =  boothPublicationVoterDAO.getVotersDetailsForBoothForPublication(publicationId,booth.getPartNo(),booth.getTehsil().getTehsilId());				
			 votersInfoForMandalVO = populateDataToVotersInfoForMandalVOForBooth(votersCountList,boothId,"booth-"+boothDAO.get(boothId).getPartNo(),"Booth");
			}
        	
			return votersInfoForMandalVO;
		}
        
        public VotersInfoForMandalVO getWardWiseVotersDetailsByPublicationBasis(Long boothId,Long publicationId,Long constituencyId){
        	  VotersInfoForMandalVO votersInfoForMandalVO = null;
	            Long totalBooths = 0l;
	            
	          //getting total booths count
	            List<Long> boothsCount = boothDAO.getBoothsCountByPublicationId("ward",boothId,publicationId,constituencyId);
				if(boothsCount != null &&  boothsCount.size() >0)
					totalBooths = (Long)boothsCount.get(0);
				
	         //getting data from intermediate table     
        	votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId("Ward"),boothId,publicationId, "", "",constituencyId);
			
        	//if data not present in intermediate table calculating data
        	if(votersInfoForMandalVO == null || !votersInfoForMandalVO.isDatapresent())
			{
			   List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountByPublicationId("ward",boothId,publicationId,null);
			   votersInfoForMandalVO =  populateDataToVotersInfoForMandalVO(votersCountList,boothId,constituencyDAO.get(boothId).getName(),"Ward");
			}
        	if(votersInfoForMandalVO != null)
        		votersInfoForMandalVO.setTotalBooths(totalBooths);
        	
			return votersInfoForMandalVO;
		}
        
        public VotersInfoForMandalVO getMandalVotersDetailsByPublicationBasis(String type,Long mandalId,Long publicationId,Long constituencyId){
        	  VotersInfoForMandalVO votersInfoForMandalVO = null;
	            Long totalBooths = 0l;
	            
	            //getting data from intermediate table      
	            //for local body
        	if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				 List list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(mandalId.toString().substring(1).trim()));
				 if(!list.isEmpty()){
				    votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.LOCALELECTIONBODY),(Long) list.get(0),publicationId, "", "",constituencyId);
				  
				  //getting total booths count
				    List<Long> boothsCount = boothDAO.getBoothsCountByPublicationId("localBody",(Long) list.get(0),publicationId,constituencyId);
					if(boothsCount != null &&  boothsCount.size() >0)
					 totalBooths = (Long)boothsCount.get(0);
				 }
			}else{
				  //for mandal
				  votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.MANDAL),new Long(mandalId.toString().substring(1).trim()),publicationId, "", "",constituencyId);	
				  //getting total booths count
				    List<Long> boothsCount = boothDAO.getBoothsCountByPublicationId("mandal",new Long(mandalId.toString().substring(1).trim()),publicationId,constituencyId);
					if(boothsCount != null &&  boothsCount.size() >0)
					 totalBooths = (Long)boothsCount.get(0);
			}
        	
        	//if data not present in intermediate table calculating data
			if(votersInfoForMandalVO == null || !votersInfoForMandalVO.isDatapresent())
			{	
				String name="";
				
				
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						  List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(mandalId.toString().substring(1));
						  Object[] reqName = assemblyLocalElectionBodyName.get(0);
						  name = reqName[0].toString()+" "+reqName[1].toString();
				}
				else{
					      
					      name = tehsilDAO.get(new Long(mandalId.toString().substring(1))).getTehsilName()+" Mandal/Tehsil";
				}
					
				List<Object[]> votersCountList = null;
				 if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){								
					//for local body
					 votersCountList =  boothPublicationVoterDAO.getVotersCountFromLocalElectionBody(new Long(mandalId.toString().substring(1).trim()),publicationId,constituencyId);
				 }else if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					//for mandal
					 votersCountList =  boothPublicationVoterDAO.getVotersCountByPublicationId("mandal",new Long(mandalId.toString().substring(1).trim()),publicationId,constituencyId);
				 }
				 
				 
				 if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
					  votersInfoForMandalVO = populateDataToVotersInfoForMandalVO(votersCountList,new Long(mandalId),name,"Mandal");
				 }else{
					 votersInfoForMandalVO = new VotersInfoForMandalVO();
					 votersInfoForMandalVO.setDatapresent(false);
				 }
			}
			if(votersInfoForMandalVO != null)
				   votersInfoForMandalVO.setTotalBooths(totalBooths);
			return votersInfoForMandalVO;
		}
		public VotersInfoForMandalVO getVotersDetailsByElectionBasis(Long constituencyId,Long mandalId,Long  panchayatId,Long  boothId ,String type,VoterVO voterVO){
			
			 log.debug("Entered into the getVotersDetailsByElectionBasis service method");
			 VotersInfoForMandalVO votersInfoForMandalVO  = null;
			 List<Object[]> votersCountDetails = null;
			
	         try{
					if(type.equalsIgnoreCase("constituency")){
						List<Long> boothIdsList =  boothConstituencyElectionDAO.getBoothIdsByConstituencyId(constituencyId, voterVO.getElectionId());
						 if(boothIdsList != null && boothIdsList.size() > 0)
						votersCountDetails = boothDAO.getTotalaVotesByBooths(boothIdsList);
						
						/*if(voterVO.getElectionType() != null && voterVO.getElectionType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
							votersCountDetails = boothConstituencyElectionDAO.getVotersCountInAConstituency(voterVO.getElectionId(), voterVO.getAssemblyConstituencyId());
						else if(voterVO.getElectionType() != null && voterVO.getElectionType().equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
							votersCountDetails = boothConstituencyElectionDAO.getVotersCountInAConstituencyByParliamentConsId(voterVO.getElectionId(), constituencyId, voterVO.getParliamentConstituencyId());*/
					}else if(type.equalsIgnoreCase("mandal")){
						
						if (mandalId.toString().substring(0, 1).trim()
								.equalsIgnoreCase("1")) {
							
							List list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(
									new Long(mandalId.toString().substring(1).trim()));
							
							votersCountDetails = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(
									        voterVO.getElectionId(),(Long) list.get(0), "localElec",null,constituencyId,null);
						}else{
                            List<Long> constituencyIdsList = new ArrayList<Long>(0);
							
							List parliamentList = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
							if(parliamentList != null && parliamentList.size() > 0)
							{
								for(Object[] params : (List<Object[]>)parliamentList)
								{
									if(!constituencyIdsList.contains(params[0]))
										constituencyIdsList.add((Long)params[0]);
								}
							}
							if(!constituencyIdsList.contains(constituencyId))
								constituencyIdsList.add(constituencyId);
							votersCountDetails = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(voterVO.getElectionId(), new Long(mandalId
											.toString().substring(1).trim()),"mandal", null, null,constituencyIdsList);
					  
						  }
					}else if(type.equalsIgnoreCase("panchayat"))						
						votersCountDetails =  staticDataService.getVotersCountInAPanchayatForAnElection(
							 voterVO.getElectionId(),panchayatId);
					else if(type.equalsIgnoreCase("booth")){						
						 Booth booth = boothDAO.get(boothId);
						 votersCountDetails = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(
								voterVO.getElectionId(), booth.getTehsil().getTehsilId(), "booth", booth.getPartNo(), null, null);
			         }
					else if(type.equalsIgnoreCase("ward")){						
						 votersCountDetails = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(
								voterVO.getElectionId(), boothId, "ward", null, null, null);
			         }
					 if(votersCountDetails != null && votersCountDetails.size() >0){
						 votersInfoForMandalVO = new VotersInfoForMandalVO();
						 
						 Object[] voterDtls = votersCountDetails.get(0);
						 
						 if(voterDtls[0] == null && voterDtls[1] == null && voterDtls[2] == null){
							 votersInfoForMandalVO.setDatapresent(false);
						 
						 }else{
							 votersInfoForMandalVO.setTotalMaleVoters(voterDtls[0].toString());
							 votersInfoForMandalVO.setTotalFemaleVoters(voterDtls[1].toString());
							 votersInfoForMandalVO.setTotalBooths((Long)voterDtls[3]);
						     BigDecimal totalCount = new BigDecimal(((Long)voterDtls[2]).longValue());

							 votersInfoForMandalVO.setTotVoters(totalCount);
							 votersInfoForMandalVO.setIsPublication("false");
							 votersInfoForMandalVO.setElectionDate(voterVO.getElectionDate().toString());
							 votersInfoForMandalVO.setElectinYear(voterVO.getElectionDate().getYear()+1900);
						 }
						 
					 }else{
						 votersInfoForMandalVO = new VotersInfoForMandalVO();
						 votersInfoForMandalVO.setDatapresent(false);
					 }
	         }catch(Exception e){
	        	 log.error("Exception raised in getVotersDetailsByElectionBasis service method");
	        	 e.printStackTrace();        	 
	         }
	         return votersInfoForMandalVO;
		}
		
		
		 
		public List<VotersInfoForMandalVO> getVotersDetailsByVoterMultipleReportLevelIds(Long reportLevelId, Map<Long,String> reportLevelValueIds, Long publicationDateId,String type,Long constituencyId)
		 {
		  List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>();
			try{
				VotersInfoForMandalVO votersInfoForMandalVO = null;
				List<VoterInfo> list = voterInfoDAO.getVotersMultipleCount(reportLevelId, reportLevelValueIds.keySet(), publicationDateId,constituencyId);
				if(list == null || list.size() == 0)
				{
					return votersInfoForMandalVOList;
				}
				if(list != null && list.size() > 0)
				{
					for(VoterInfo voterDetails : list)
					{   votersInfoForMandalVO = new VotersInfoForMandalVO();
						BigDecimal unknowCount = new BigDecimal(voterDetails.getTotalVoters().longValue()-(voterDetails.getMaleVoters().longValue() + voterDetails.getFemaleVoters().longValue()));
						votersInfoForMandalVO.setTotVoters(voterDetails.getTotalVoters() != null ? new BigDecimal(voterDetails.getTotalVoters()):new BigDecimal(0.00));
						votersInfoForMandalVO.setTotalVoters(voterDetails.getTotalVoters() != null ? voterDetails.getTotalVoters().toString():"0.00");
						votersInfoForMandalVO.setTotalMaleVoters(voterDetails.getMaleVoters() != null ?voterDetails.getMaleVoters().toString():"0.00");
						votersInfoForMandalVO.setTotalFemaleVoters(voterDetails.getFemaleVoters() != null?voterDetails.getFemaleVoters().toString():"0.00");
					    votersInfoForMandalVO.setTotalVotersPercentage(voterDetails.getTotalVotersPercentage() != null?voterDetails.getTotalVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setTotalFemalePercentage(voterDetails.getFemaleVotersPercentage() != null? voterDetails.getFemaleVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setTotalMalePercentage(voterDetails.getMaleVotersPercentage() != null ? voterDetails.getMaleVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setUnKnowVoters(unknowCount != null ? unknowCount.toString(): "0.00");
						votersInfoForMandalVO.setMaleVoters(voterDetails.getMaleVoters());
						votersInfoForMandalVO.setFemaleVoters(voterDetails.getFemaleVoters());
						votersInfoForMandalVO.setType(type);
						votersInfoForMandalVO.setName(reportLevelValueIds.get(voterDetails.getReportLevelValue()));
						votersInfoForMandalVO.setId(voterDetails.getReportLevelValue());
						votersInfoForMandalVO.setPercent(voterDetails.getTotalVotersPercentage() != null?voterDetails.getTotalVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setTotPercent(voterDetails.getTotalVotersPercentage()!=null?(new BigDecimal(voterDetails.getTotalVotersPercentage()).setScale(2, BigDecimal.ROUND_HALF_UP)):new BigDecimal("0.00") );
						votersInfoForMandalVOList.add(votersInfoForMandalVO);
					}
				}
				return votersInfoForMandalVOList;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersDetailsByVoterMultipleReportLevelIds() Method, Exception - ",e);
				return null;
			}
		 }

		/**
		 * This method is used to get the all booth details based on constituencyId and publicationId
		 * @author Prasad Thiragabathina
		 * @param constituencyId
		 * @param publicationId
		 * @return List<SelectOptionVO>
		 */
		public List<SelectOptionVO> getBoothsForConstituencyAndPublication(
				Long constituencyId, Long publicationId) {
			List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>();
			
			log.debug("Entered into the getBoothsForConstituencyAndPublication service method");
			if(constituencyId != 0 && publicationId!=0)
			{
				List<Object[]> booths = boothDAO.getBoothsBasedOnConstituencyAndPublicationDate(constituencyId,publicationId);
				for (Object[] booth : booths) {
					SelectOptionVO selectOptions = new SelectOptionVO();
					selectOptions.setId((Long) booth[0]);
					selectOptions.setName((String) booth[1].toString());
					boothsList.add(selectOptions);
				}
			}
			return boothsList;
		}

		/**
		 * This method is used to store the voter details into the voter table and booth_voter_publication table
		 * @author Prasad Thiragabathina
		 * @param name
		 * @param voterCardNo
		 * @param houseNo
		 * @param gaurdian
		 * @param relationShip
		 * @param gender
		 * @param age
		 * @param boothId
		 * @return ResultStatus
		 */
		public ResultStatus saveVoters(final String name, final String voterCardNo,
				final String houseNo, final String gaurdian, final String relationShip,
				final String gender, final Long serialNo, final Long age,final Long boothId) {
				
			  log.info("Entered into saveVoters Method...");
			  ResultStatus resultStatus = new ResultStatus();
			  try{
				List<Voter> voters = voterDAO.getVoterByVoterCardNo(voterCardNo);
					if(voters != null && voters.size() > 0)
					{
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						return resultStatus;
					}
					else
					{
					transactionTemplate.execute(new TransactionCallbackWithoutResult() {
						protected void doInTransactionWithoutResult(TransactionStatus status) 
						{
							Voter voter = new Voter();
							voter.setName(name);
							voter.setVoterIDCardNo(voterCardNo);
							voter.setHouseNo(houseNo);
							voter.setGender(gaurdian);
							voter.setRelationshipType(relationShip);
							voter.setGender(gender);
							voter.setAge(age);
							//voter.setMobileNo(serialNo);
							//voterDAO.save(voter);
							BoothPublicationVoter boothPublicationVoter = new BoothPublicationVoter();
							if(boothId != null)
							{
							boothPublicationVoter.setBoothId(boothId);
							boothPublicationVoter.setSerialNo(serialNo);
							boothPublicationVoter.setVoter(voter);
							boothPublicationVoterDAO.save(boothPublicationVoter);
							}
							
						}});
					}
						resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						return resultStatus;	
					
					
			  }catch (Exception e) {
				  e.printStackTrace();
				  log.error("Exception Occured in saveVoters Method, Exception - "+e);
				  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				  return resultStatus;
			}
		  
			
		} 
		
		public List<SelectOptionVO> getElectionYearsByMandalId(String type,Long id)
		{
			List<SelectOptionVO> electionYearsList = new ArrayList<SelectOptionVO>(0);
			try{
				List<Object> list = null;
				if(type.equalsIgnoreCase(IConstants.MANDAL))
				{
					if(id.toString().trim().substring(0, 1).equalsIgnoreCase("2"))
					{
						id = new Long(id.toString().trim().substring(1));
						type = IConstants.MANDAL;
					}
					else
					{
						List<Object> list2 = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().trim().substring(1)));
						if(list2 != null)
							id = (Long)list2.get(0);
							type = IConstants.LOCALELECTIONBODY;
					}
					
				 }
				list = boothConstituencyElectionDAO.getElectionYearsByMandalId(type, id);
				
				if(list != null && list.size() > 0)
				{
					for(Object params : list)
						electionYearsList.add(new SelectOptionVO(new Long(params.toString()), params.toString()));
				}
				return electionYearsList;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getElectionYearsByMandalId() Method, Exception - "+e);
				return electionYearsList;
			}
		}
		
		public CrossVotingConsolidateVO getCrossVotingReportByMandalIdAndEleYear(String type, Long id, String year, String includeAliance)
		{
			CrossVotingConsolidateVO consolidateVO = new CrossVotingConsolidateVO();
			List<CrossVotedMandalVO> crossVotedMandalVOList = new ArrayList<CrossVotedMandalVO>(0);
			try{
			    List<Long> partyIdsList = null;
			    Long assemblyConValidVotes = 0l;
			    Long parliamentConValidVotes = 0l;
			   			    
			    if(type.equalsIgnoreCase(IConstants.MANDAL))
			    {
			    	if(id.toString().trim().substring(0,1).equalsIgnoreCase("2"))
				  	{
			    		id = new Long(id.toString().trim().substring(1));
			    		type = IConstants.MANDAL;
				  	}
					
				    else
				    {
					   List<Object> list2 = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().trim().substring(1)));
					   if(list2 != null)
						   id = (Long)list2.get(0);
					   type = IConstants.LOCALELECTIONBODY;
				    }	
			    }
			   List<Object[]> assemblyIdsList = boothConstituencyElectionDAO.getConstituencyIdAndElectionYearByElectionType(type, id, year, IConstants.ASSEMBLY_ELECTION_TYPE);
			   List<Object[]> parliamentIdsList = boothConstituencyElectionDAO.getConstituencyIdAndElectionYearByElectionType(type, id, year, IConstants.PARLIAMENT_ELECTION_TYPE);
			   
			   if(assemblyIdsList != null && assemblyIdsList.size() >0 && parliamentIdsList != null && parliamentIdsList.size() > 0)
			   {
				   
					  partyIdsList = candidateBoothResultDAO.getPartyIdsListByEleIdAndYearAndConstId((Long)assemblyIdsList.get(0)[0], (Long)assemblyIdsList.get(0)[1], year);
							
					  if(partyIdsList != null && partyIdsList.size() >0)
					  {
						  List<Object[]> acList = null;
						  List<Object[]> pcList = null;
						  List acValidVotesList = null;
						  List pcValidVotesList = null;
					
						  acValidVotesList = candidateBoothResultDAO.getValidVotesByEleTypeAndConstituencyId(type,id, (Long)assemblyIdsList.get(0)[0], year);
						  pcValidVotesList = candidateBoothResultDAO.getValidVotesByEleTypeAndConstituencyId(type,id, (Long)parliamentIdsList.get(0)[0], year);
					
						  acList  = candidateBoothResultDAO.getAllPartiesCrossVotingReportByEleYearAndConstituencyId(type,id, (Long)assemblyIdsList.get(0)[0], year, partyIdsList);
						  pcList = candidateBoothResultDAO.getAllPartiesCrossVotingReportByEleYearAndConstituencyId(type, id, (Long)parliamentIdsList.get(0)[0], year, partyIdsList);
						
						  if(acValidVotesList != null && acValidVotesList.size() > 0)
							  assemblyConValidVotes = ((Double)acValidVotesList.get(0)).longValue();
						  if(pcValidVotesList != null && pcValidVotesList.size() > 0)
							  parliamentConValidVotes = ((Double)pcValidVotesList.get(0)).longValue();
						
						   getCrossVotingPartyWiseDetails(acList, pcList, assemblyConValidVotes, parliamentConValidVotes, crossVotedMandalVOList);
						   consolidateVO.setMandals(crossVotedMandalVOList);
					   
				   }
				   
							
			}
				  
				return consolidateVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getCrossVotingReportByMandalIdAndEleYear() Method, Exception - "+e);
				return null;
			}
			
		}

		
		public List<CrossVotedMandalVO> getCrossVotingPartyWiseDetails(List<Object[]> acList, List<Object[]> pcList, Long acValidVotes, Long pcValidVotes,List<CrossVotedMandalVO> crossVotedMandalVOList)
		{
			try{
				
				List<String> partyList = new ArrayList<String>(0);
				
				if(acList != null && acList.size() >0)
				{
					for(Object[] params : acList)
					{
						if(!partyList.contains(params[1]))
						  partyList.add(params[1].toString());
					}
				}
				if(pcList != null && pcList.size() > 0)
				{
					for(Object[] params : pcList)
					{
					  if(!partyList.contains(params[1]))
							partyList.add(params[1].toString());
					}
				}
				Collections.sort(partyList);
				
				for(String partyInList : partyList)
				{
					String partyName = partyInList;
					Long acVotesEarned = 0l;
					Long pcVotesEarned = 0l;
					Double acPercentageDiff = 0.00;
					Double pcPercentageDiff = 0.00;
					Double percentageImpactOnConstituency = 0.00;
					
					CrossVotedMandalVO crossVotedMandalVO = new CrossVotedMandalVO();
					crossVotedMandalVO.setPartyName(partyInList);
					
					for(Object[] objects : acList)
					{
						if(objects[1].toString().equalsIgnoreCase(partyName))
						  acVotesEarned += (Long)objects[3];
					}
					
					for(Object[] obj : pcList)
					{
						if(obj[1].toString().equalsIgnoreCase(partyName))
							pcVotesEarned += (Long)obj[3];
							
					}
					
					crossVotedMandalVO.setPolledVotes(acValidVotes);
					
					if(acValidVotes != null && acValidVotes > 0)
						acPercentageDiff = (acVotesEarned.doubleValue()*100.0)/acValidVotes.doubleValue();
					
					if(pcValidVotes != null && pcValidVotes > 0)
					  pcPercentageDiff = (pcVotesEarned.doubleValue()*100.0)/pcValidVotes.doubleValue();
					
					percentageImpactOnConstituency = acPercentageDiff-pcPercentageDiff;
					
					crossVotedMandalVO.setAcPercentageInMandal(acPercentageDiff != null ? new BigDecimal(acPercentageDiff).setScale(2, BigDecimal.ROUND_HALF_UP).toString() :"0.00");
					crossVotedMandalVO.setPcPercentageInMandal(pcPercentageDiff != null ? new BigDecimal(pcPercentageDiff).setScale(2, BigDecimal.ROUND_HALF_UP).toString() :"0.00");
					crossVotedMandalVO.setPercentageDifferenceInMandal(percentageImpactOnConstituency != null? new BigDecimal(percentageImpactOnConstituency).setScale(2, BigDecimal.ROUND_HALF_UP).toString():"0.00");
					
					if(acValidVotes != null && acValidVotes > 0)
					{
						crossVotedMandalVO.setPercentageImpactOnConstituency(new BigDecimal(percentageImpactOnConstituency*100.0/acValidVotes).
							setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					else
					 crossVotedMandalVO.setPercentageImpactOnConstituency("0.00");
					
					System.out.println(percentageImpactOnConstituency +" "+ acValidVotes);
					
					crossVotedMandalVOList.add(crossVotedMandalVO);
					
				}
				
				return crossVotedMandalVOList;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getCrossVotingPartyWiseDetails() Method, Exception - "+e);
				return null;
			}
		
		}	
		
		public static Comparator<SelectOptionVO> arraySort = new Comparator<SelectOptionVO>()
				{	  
					public int compare(SelectOptionVO arg1,SelectOptionVO arg2)
					{
						Integer num1 ;
						Integer num2 ;

						if(arg1.getValue().contains("Booth No- "))
							num1 = new Integer(arg1.getValue().replaceAll("Booth No- ", "").trim());
						else
							 num1 = new Integer(arg1.getValue());
						 if(arg2.getValue().contains("Booth No- "))
							  num2 = new Integer(arg2.getValue().replaceAll("Booth No- ", "").trim());
						 else
							  num2 = new Integer(arg2.getValue());

							  return num1 - num2;

						}
				};
				public static Comparator<SelectOptionVO> arraySort1 = new Comparator<SelectOptionVO>()
						{	  
								  public int compare(SelectOptionVO arg1,SelectOptionVO arg2)
									{
									  return arg1.getName().trim().toUpperCase().compareTo(arg2.getName().trim().toUpperCase());
									}
						};
				
		public ResultStatus deleteVotersDataFromIntermediateTables(Long reportLevelValue, Long publicationDateId)
		{
			ResultStatus resultStatus = new ResultStatus();
			try{
				
				List<Long> constituencyIdsList = new ArrayList<Long>(0);
				List<Long> mandalIdsList = new ArrayList<Long>(0);
				List<Long> localEleBodyIdsList = new ArrayList<Long>(0);
				List<Long> panchayatIdsList = new ArrayList<Long>(0);
				List<Long> boothIdsList = new ArrayList<Long>(0);
				List<Long> wardIdsList = new ArrayList<Long>(0);
				
				constituencyIdsList.add(reportLevelValue);
				
				List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(reportLevelValue ,IConstants.PRESENT_YEAR, null);
				if(mandalsList == null || mandalsList.size() == 0)
					return null;
				
				for(SelectOptionVO selectOptionVO : mandalsList)
				{
					if(selectOptionVO.getId().toString().substring(0, 1).equalsIgnoreCase(IConstants.RURAL_TYPE))
						mandalIdsList.add(new Long(selectOptionVO.getId().toString().substring(1)));
					else
						localEleBodyIdsList.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(selectOptionVO.getId().toString().substring(1))).get(0));
				}
				
				/*if(mandalIdsList == null || mandalIdsList.size() == 0)
					return null;*/
						
				if(mandalIdsList != null && mandalIdsList.size() > 0)
					panchayatIdsList = panchayatDAO.getPanchayatIdsBytehsilIdsList(mandalIdsList);
				
				if(panchayatIdsList != null && panchayatIdsList.size() > 0)
					boothIdsList = boothDAO.getBoothIdsByPanchayatIdsListOrLocalEleBodyIdsList(IConstants.PANCHAYAT, publicationDateId, panchayatIdsList);
				
				if(localEleBodyIdsList != null && localEleBodyIdsList.size() > 0)
				{
					List<Long> boothIds = boothDAO.getBoothIdsByPanchayatIdsListOrLocalEleBodyIdsList(IConstants.LOCALELECTIONBODY, publicationDateId, localEleBodyIdsList);
					if(boothIds != null && boothIds.size() > 0)
					{
						for(Long boothId : boothIds)
						{
							if(!boothIdsList.contains(boothId))
								boothIdsList.add(boothId);	
						}
					}
				
				}
				
				if(localEleBodyIdsList != null && localEleBodyIdsList.size() >0)
					wardIdsList = boothDAO.getWardIdsByLocalEleBodyIdsList(localEleBodyIdsList, publicationDateId);
					
				if(constituencyIdsList != null && constituencyIdsList.size() > 0)
					deleteVoterInfoFromIntermediateTables(IConstants.CONSTITUENCY, publicationDateId, constituencyIdsList);
				if(mandalIdsList != null && mandalIdsList.size() > 0)
					deleteVoterInfoFromIntermediateTables(IConstants.MANDAL, publicationDateId, mandalIdsList);
				if(localEleBodyIdsList != null && localEleBodyIdsList.size() > 0)
					deleteVoterInfoFromIntermediateTables(IConstants.LOCALELECTIONBODY, publicationDateId, localEleBodyIdsList);
				if(panchayatIdsList != null && panchayatIdsList.size() > 0)
					deleteVoterInfoFromIntermediateTables(IConstants.PANCHAYAT, publicationDateId, panchayatIdsList);
				if(boothIdsList != null && boothIdsList.size() > 0)
					deleteVoterInfoFromIntermediateTables(IConstants.BOOTH, publicationDateId, boothIdsList);
				if(wardIdsList != null && wardIdsList.size() > 0)
					deleteVoterInfoFromIntermediateTables(IConstants.WARD, publicationDateId, wardIdsList);
				
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				return resultStatus;
				
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in deleteVotersDataInIntermediateTables() Method, Exception - "+e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
		}
		
		public ResultStatus deleteVoterInfoFromIntermediateTables(String type, Long publicationDateId,List<Long> reportLevelValue)
		{
			ResultStatus resultStatus = new ResultStatus();
			try{
				
				if(reportLevelValue != null && reportLevelValue.size() > 0)
				{
					voterInfoDAO.deleteVotersInfoByReportLevelValue(getReportLevelId(type), reportLevelValue, publicationDateId);
					voterFamilyInfoDAO.deleteVoterFamilyDetByReportLevelValAndVoterAgeRange(getReportLevelId(type), reportLevelValue, publicationDateId);
					voterAgeInfoDAO.deleteVoterAgeInfoByReportLevelIdAndReportLevelValue(getReportLevelId(type), reportLevelValue, publicationDateId);
				}
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				return resultStatus;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in deleteVoterInfoFromIntermediateTables() Method, Exception - "+e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
		}
		/**
		 * 	This method is used to get the all the details of the voter based on voterId and 
		 * 	This voter is added eaithe cader or a influencing prople
		 * @author Prasad Thiragabathina
		 * @param Long voterId
		 * @param Long userId
		 * @return InfluencingPeopleBeanVO
		 */

		public InfluencingPeopleBeanVO getDetailsByVoterId(Long voterId ,Long userId) {
			
			try {
				log.info("Entered into getDetailsByVoterId Method...");
				InfluencingPeopleBeanVO influencingPeopleVO = new InfluencingPeopleBeanVO();
				influencingPeopleVO.setInfluencingRange("0");
				List<Voter> voters = new ArrayList<Voter>();
				
				List<Long> voterIdsList = new ArrayList<Long>(0);
				Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
				voterIdsList.add(voterId);
				List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIdsList, userId);
				if(list != null && list.size() > 0)
				 for(Object[] params:list)
					 mobileNosMap.put((Long)params[0], params[1]!= null?params[1].toString():"N/A");
				
				
				voters = voterDAO.findVoterDetailsBasedOnVoterId(voterId);
					if(voters != null && voters.size() > 0)
					{
						Voter voter = voters.get(0);
							influencingPeopleVO.setFirstName(voter.getName());
							influencingPeopleVO.setHouseNo(voter.getHouseNo());
							influencingPeopleVO.setFatherOrSpouseName(voter.getRelativeName());
							if(voter.getGender() != null)
							{
								String gender = voter.getGender();
								if(voter.getGender().equalsIgnoreCase("m"))
									 gender = "Male";
									else
									 gender = "Female";
									
									influencingPeopleVO.setGender(gender);
							}
							else
							{
								influencingPeopleVO.setGender("Male");
							}
							if(mobileNosMap.get(voter.getVoterId()) != null)
							 influencingPeopleVO.setMobile(mobileNosMap.get(voter.getVoterId()));
							else
								influencingPeopleVO.setMobile("N/A");
							
					}
					
					List<BoothPublicationVoter> voterContactDetails= boothPublicationVoterDAO.findVoterContactDetails(voterId);
					if(voterContactDetails != null & voterContactDetails.size() > 0)
					{
						BoothPublicationVoter boothPublicationVoter = voterContactDetails.get(0);
							if(boothPublicationVoter.getBooth().getConstituency().getState() != null)
							{
							influencingPeopleVO.setState(boothPublicationVoter.getBooth().getConstituency().getState().getStateId().toString());
							influencingPeopleVO.setStateName(boothPublicationVoter.getBooth().getConstituency().getState().getStateName());
							}
							if(boothPublicationVoter.getBooth().getConstituency().getDistrict() != null)
							{
							influencingPeopleVO.setDistrict(boothPublicationVoter.getBooth().getConstituency().getDistrict().getDistrictId().toString());
							influencingPeopleVO.setDistrictName(boothPublicationVoter.getBooth().getConstituency().getDistrict().getDistrictName());
							}
							if(boothPublicationVoter.getBooth().getConstituency() != null)
							{
							influencingPeopleVO.setConstituency(boothPublicationVoter.getBooth().getConstituency().getConstituencyId().toString());
							influencingPeopleVO.setConstituencyName(boothPublicationVoter.getBooth().getConstituency().getName());
							}
							if(boothPublicationVoter.getBooth().getTehsil() != null)
							{
							  if(boothPublicationVoter.getBooth().getLocalBody() == null)
								{
								influencingPeopleVO.setMandal(IConstants.RURAL_TYPE+boothPublicationVoter.getBooth().getTehsil().getTehsilId().toString());
								influencingPeopleVO.setMandalName(boothPublicationVoter.getBooth().getTehsil().getTehsilName());
								}
							  else
							  	{
								 Long localElectionBodyId = boothPublicationVoter.getBooth().getLocalBody().getLocalElectionBodyId();
								 List assemblyLocalElectionBodyId = assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyIdsList(localElectionBodyId,Long.valueOf(influencingPeopleVO.getConstituency()));
								 if(assemblyLocalElectionBodyId != null && assemblyLocalElectionBodyId.size() > 0)
								 {
								 Long assemblyLocalElectionBodyIds = (Long)assemblyLocalElectionBodyId.get(0);
								 influencingPeopleVO.setMandal(IConstants.URBAN_TYPE+assemblyLocalElectionBodyIds.toString());
								 }
							   }
							  if(boothPublicationVoter.getBooth() != null)
							  {
								  influencingPeopleVO.setBooth(boothPublicationVoter.getBooth().getPartNo().toString());
								  influencingPeopleVO.setBoothName(boothPublicationVoter.getBooth().getPartNo());
							  }
							}
							Long boothId = (Long) boothPublicationVoter.getBooth().getBoothId();
							List<HamletBoothPublication> hamletBoothPublicationList = hamletBoothPublicationDAO.getHameletDetailsByBoothId(boothId);
							if(hamletBoothPublicationList != null & hamletBoothPublicationList .size() > 0)
							{
								HamletBoothPublication hamletBoothPublication = hamletBoothPublicationList.get(0);
								if(hamletBoothPublication.getBooth().getLocalBody() == null)
								{
									List<Object[]> hamletDetails = userVoterDetailsDAO.getHamletOrWardList(userId,voterId,IConstants.HAMLET);
									if(hamletDetails != null && hamletDetails.size() > 0)
									{
										for (Object[] parms : hamletDetails) {
											influencingPeopleVO.setWardOrHamlet(IConstants.RURAL_TYPE+parms[0]);
											influencingPeopleVO.setWardOrHamletName(parms[1].toString());
										}
									}
									
								}
								else
								{
									List<Object[]> hamletDetails = userVoterDetailsDAO.getHamletOrWardList(userId,voterId,IConstants.WARD);
									if(hamletDetails != null && hamletDetails.size() > 0)
									{
										for (Object[] parms : hamletDetails) {
											influencingPeopleVO.setWardOrHamlet(IConstants.RURAL_TYPE+parms[0]);
											influencingPeopleVO.setWardOrHamletName(parms[1].toString());
										}
									}
								}
							}
							else
							{
								List<HamletBoothElection> hamletDetailsList = hamletBoothElectionDAO.getHamletOrWardDetails(boothId);
								if(hamletDetailsList != null && hamletDetailsList.size() > 0)
								
								{
									HamletBoothElection hamletBoothElection	= hamletDetailsList.get(0);
									if(hamletBoothElection.getBoothConstituencyElection().getBooth().getLocalBody() != null)
									{
										List<Object[]> hamletDetails = userVoterDetailsDAO.getHamletOrWardList(userId,voterId,IConstants.HAMLET);
										if(hamletDetails != null && hamletDetails.size() > 0)
										{
											for (Object[] parms : hamletDetails) {
												influencingPeopleVO.setWardOrHamlet(IConstants.RURAL_TYPE+parms[0]);
												influencingPeopleVO.setWardOrHamletName(parms[1].toString());
											}
										}
									}
									else
									{
										List<Object[]> hamletDetails = userVoterDetailsDAO.getHamletOrWardList(userId,voterId,IConstants.WARD);
										if(hamletDetails != null && hamletDetails.size() > 0)
										{
											for (Object[] parms : hamletDetails) {
												influencingPeopleVO.setWardOrHamlet(IConstants.RURAL_TYPE+parms[0]);
												influencingPeopleVO.setWardOrHamletName(parms[1].toString());
											}
										}
									}
								}
							}
						}
							List<Object[]> voterPartyAndCaste = userVoterDetailsDAO.getPartyAndCasteDetails(voterId,userId);
							if(voterPartyAndCaste != null && voterPartyAndCaste.size() > 0)
							{
								Object[] 	voterPartyAndCasteDetails = voterPartyAndCaste.get(0);
								influencingPeopleVO.setParty(voterPartyAndCasteDetails[2].toString());
								influencingPeopleVO.setPartyName(voterPartyAndCasteDetails[3].toString());
								influencingPeopleVO.setCast(voterPartyAndCasteDetails[0].toString());
								influencingPeopleVO.setCastType(voterPartyAndCasteDetails[1].toString());
							}
				return influencingPeopleVO;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception raised in getDetailsByVoterId Method...");
				return null;
			}
			
		}
		/**
		 * This method is used to get all the details of the voter based on voter id and set all 
		 * these details as cadre details
		 * @author Prasad Thiragabathina
		 * @param  Long voterId
		 * @return CadreInfo
		 */
		public CadreInfo getCadreDetailsByVoterId(Long voterId,Long userId) {
			try {
				log.info("Entered into getCadreDetailsByVoterId Method...");
				CadreInfo cadreInfo = new CadreInfo();
				SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
				List<Voter> voters = new ArrayList<Voter>();
				voters = voterDAO.findVoterDetailsBasedOnVoterId(voterId);
				List<Long> voterIdsList = new ArrayList<Long>(0);
				Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
				voterIdsList.add(voterId);
				List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIdsList, userId);
				if(list != null && list.size() > 0)
				 for(Object[] params:list)
					 mobileNosMap.put((Long)params[0], params[1]!= null?params[1].toString():"N/A");
				
				if(voters != null && voters.size() > 0)
				{
					Voter voter = voters.get(0);
					cadreInfo.setFirstName(voter.getName());
					cadreInfo.setHouseNo(voter.getHouseNo());
					cadreInfo.setFatherOrSpouseName(voter.getRelativeName());
					String gender = voter.getGender();
					if(gender.equalsIgnoreCase("m"))
					gender= "Male";
					else
					gender = "Female";
					cadreInfo.setGender(gender);
					if(mobileNosMap.get(voter.getVoterId())!= null)
					 cadreInfo.setMobile(mobileNosMap.get(voter.getVoterId()));
					else
						cadreInfo.setMobile("N/A");
					//cadreInfo.setDateOfBirth(sdf.format(dob));
					Long age = voter.getAge();
					Date today = new Date();
					Long year = (long) today.getYear();
					Long dobYear = year-age;
					cadreInfo.setDateOfBirth(sdf.format(dobYear));
					cadreInfo.setAge(voter.getAge().toString());
					cadreInfo.setDobOption("Age");
				}
				List<BoothPublicationVoter> voterContactDetails= boothPublicationVoterDAO.findVoterContactDetails(voterId);
				if(voterContactDetails != null & voterContactDetails.size() > 0)
				{
					BoothPublicationVoter boothPublicationVoter = voterContactDetails.get(0);
						if(boothPublicationVoter.getBooth().getConstituency().getState() != null)
						{
							cadreInfo.setState(boothPublicationVoter.getBooth().getConstituency().getState().getStateId().toString());
							cadreInfo.setPstate(cadreInfo.getState());
							cadreInfo.setStateName(boothPublicationVoter.getBooth().getConstituency().getState().getStateName());
						}
						if(boothPublicationVoter.getBooth().getConstituency().getDistrict() != null)
						{
							cadreInfo.setDistrict(boothPublicationVoter.getBooth().getConstituency().getDistrict().getDistrictId().toString());
							cadreInfo.setPdistrict(cadreInfo.getDistrict());
							cadreInfo.setDistrictName(boothPublicationVoter.getBooth().getConstituency().getDistrict().getDistrictName());
						}
						if(boothPublicationVoter.getBooth().getConstituency() != null)
						{
							cadreInfo.setConstituencyID(boothPublicationVoter.getBooth().getConstituency().getConstituencyId());
							cadreInfo.setPconstituencyID(cadreInfo.getConstituencyID());
							cadreInfo.setConstituencyName(boothPublicationVoter.getBooth().getConstituency().getName());
							List parliamentList = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(cadreInfo.getConstituencyID());
							if(parliamentList != null && parliamentList.size() > 0){
						    	   Object[] parliament = (Object[])(parliamentList.get(0));
						    	   cadreInfo.setParliament(parliament[0] != null?parliament[0].toString():"0");
						    	   cadreInfo.setPParliament(parliament[0] != null?parliament[0].toString():"0");
									cadreInfo.setParliamentName(parliament[1]!=null?parliament[1].toString()+" (Parliament)":"");
						
							}
						}
						if(boothPublicationVoter.getBooth().getTehsil() != null)
						{
						  if(boothPublicationVoter.getBooth().getLocalBody() == null)
							{
							  cadreInfo.setMandal(IConstants.RURAL_TYPE+boothPublicationVoter.getBooth().getTehsil().getTehsilId().toString());
							  cadreInfo.setPmandal(cadreInfo.getMandal());
							  cadreInfo.setMandalName(boothPublicationVoter.getBooth().getTehsil().getTehsilName());
							}
						  else
						  	{
							 Long localElectionBodyId = boothPublicationVoter.getBooth().getLocalBody().getLocalElectionBodyId();
							 List<Object[]> assemblyLocalElectionBodyId = assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyDetails(localElectionBodyId);
							 if(assemblyLocalElectionBodyId != null && assemblyLocalElectionBodyId.size() > 0)
							 {
								for (Object[] assemblyLocalElectionBodyIds : assemblyLocalElectionBodyId) {
									cadreInfo.setMandal(IConstants.URBAN_TYPE+assemblyLocalElectionBodyIds[0].toString());
									cadreInfo.setPmandal(cadreInfo.getMandal());
									cadreInfo.setMandalName(assemblyLocalElectionBodyIds[1]+"assemblyLocalElectionBodyIds[2]");
								} 
							 }
						   }
						}
						Long boothId = (Long) boothPublicationVoter.getBooth().getBoothId();
						List<HamletBoothPublication> hamletBoothPublicationList = hamletBoothPublicationDAO.getHameletDetailsByBoothId(boothId);
						if(hamletBoothPublicationList != null & hamletBoothPublicationList .size() > 0)
						{
							HamletBoothPublication hamletBoothPublication = hamletBoothPublicationList.get(0);
							if(hamletBoothPublication.getBooth().getLocalBody() == null)
							{
								cadreInfo.setVillage(IConstants.RURAL_TYPE+hamletBoothPublication.getHamlet().getHamletId().toString());
								cadreInfo.setPvillage(cadreInfo.getVillage());
								cadreInfo.setVillageName(hamletBoothPublication.getHamlet().getHamletName().toString());
							}
							else
							{
								cadreInfo.setVillage(IConstants.URBAN_TYPE+hamletBoothPublication.getHamlet().getHamletId().toString());
								cadreInfo.setPvillage(cadreInfo.getVillage());
								cadreInfo.setVillageName(hamletBoothPublication.getHamlet().getHamletName().toString());
							}
						}
						List<Long> famileyMemberCount = boothPublicationVoterDAO.getFamilyMemberCount(cadreInfo.getHouseNo(), boothPublicationVoter.getBooth().getBoothId());
						cadreInfo.setNoOfFamilyMembers(famileyMemberCount.get(0).toString());
						cadreInfo.setNoOfVoters(famileyMemberCount.get(0).toString());
						List<Object[]> famileyMembersDetails = boothPublicationVoterDAO.getFamileyMembersDetailsForHouseNo(cadreInfo.getHouseNo(), boothPublicationVoter.getBooth().getBoothId(),voterId);
						int count = 0;
						if(famileyMembersDetails != null && famileyMembersDetails.size() > 0)
						{
							for (Object[] objects : famileyMembersDetails) {
								
								if(count == 0)
								{
									cadreInfo.setFirstFamilyMemberName(objects[1].toString());
									//cadreInfo.setFirstFamilyMemberRelation(objects[2].toString());
									//List<Long> relationId = userRelationDAO.getRelationId( objects[2].toString());
									//cadreInfo.setFirstFamilyMemberRelationId(relationId.toString());
								}
								if(count == 1)
								{
									cadreInfo.setSecondFamilyMemberName(objects[1].toString());
									//cadreInfo.setSecondFamilyMemberRelation(objects[2].toString());
									//List<Long> relationId = userRelationDAO.getRelationId(objects[2].toString());
									//cadreInfo.setSecondFamilyMemberRelationId(relationId.toString());
								}	
								if(count == 2)
								{
									cadreInfo.setThirdFamilyMemberName(objects[1].toString());
									//cadreInfo.setThirdFamilyMemberRelation(objects[2].toString());	
									//List<Long> relationId = userRelationDAO.getRelationId(objects[2].toString());
									//cadreInfo.setThirdFamilyMemberRelationId(relationId.toString());
								}
								if(count > 2)
								{
									break;
								}
								count++;
							}
						}
						
					}
						
						cadreInfo.setSameAsCA(true);
				return cadreInfo;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception raised in getCadreDetailsByVoterId Method...");
				return null;
			}
			
		}
		public Long getParliamentConstituencyId(String type, Long id, Long year)
		{
			Long parliamentConId = null;
			try{
				if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				{
					List pcsInfoList = delimitationConstituencyAssemblyDetailsDAO.findParliamentByAssemblyIdAndElectionYear(id,year);
					
					List<Object[]> pcsInfo = (List<Object[]>)pcsInfoList;
					if(pcsInfo != null && pcsInfo.size() >0)
						parliamentConId = (Long)pcsInfo.get(0)[0];
				}
				return parliamentConId;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getParliamentConstituencyId() Method, Exception - "+e);
				return parliamentConId;
			}
		}
				
/*		public List<SelectOptionVO> getWardsMunicipality(Long lclElecBodyId,Long publicationDateId){
			 List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
			try{
				List<Object> listId = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(lclElecBodyId);
			//List<Object[]> wards =  constituencyDAO.getWardsInMuncipality(lclElecBodyId);
			 List<Object[]> wards = boothDAO.getWardsInMuncipality(lclElecBodyId, publicationDateId);
			 SelectOptionVO selectOptionVO = null;
			 for(Object[] ward:wards){
				 selectOptionVO = new SelectOptionVO();
				 selectOptionVO.setId((Long)ward[0]);
				 if(listId.get(0) != null ){
				List<Object> name	=  localElectionBodyWardDAO.findWardName((Long)ward[0]);
				    if(name.size()>0){
				String wardName =(String) name.get(0);
				           
				 selectOptionVO.setName(ward[1]!=null? wardName!=null? wardName+"("+ward[1].toString()+")" : ward[1].toString() : "");
				    }
				 else
					 selectOptionVO.setName(ward[1]!=null? ward[1].toString():"");
				    }else
				 selectOptionVO.setName(ward[1]!=null? ward[1].toString():"");
				 
				 selectOptionVO.setValue(ward[1].toString());
			//List<SelectOptionVO>	allbooths= getBoothsInMunicipality(lclElecBodyId, publicationDateId);
			 List<SelectOptionVO> list2=	getBoothForWard((Long)ward[0],publicationDateId);
			if(list2 != null && list2.size()>0)
				selectOptionVO.setSelectOptionsList(list2);
			
				 list.add(selectOptionVO);
			 }
			 Collections.sort(list,arraySort1);
			}catch(Exception e){
				log.error("Exception rised in getwardsInMunicipality ",e);
			}
			 return list;
			 
		 }
		 
		 public List<SelectOptionVO> getBoothForWard(Long wardId, Long publicationDateId)
		 {    List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
			 List<Object[]> booths = boothDAO.getBoothInWard(wardId, publicationDateId);
			   SelectOptionVO selectOptionVO = null;
				 for(Object[] booth:booths){
					 selectOptionVO = new SelectOptionVO();
					 selectOptionVO.setId((Long)booth[0]);
					 selectOptionVO.setName(booth[1]!=null?booth[1].toString():"");
					 selectOptionVO.setValue(booth[1].toString());
					 selectOptionVO.setLocation(booth[2] != null?booth[2].toString():"");
					 selectOptionVO.setVillageCovered(booth[3] != null?booth[3].toString():"");
					 list.add(selectOptionVO);
				 }
				 Collections.sort(list,arraySort);
			
				 return list;
			 
		 }*/
  		 
		 
		 /**
		 * This method move the Voters Modification Data from Temporary Table(voter_modification_temp) 
		 * to Main Table(voter_modification)
		 * 
		 * @author Kamalakar Dandu
		 * @param Long Constituency Id
		 * @param Long Publication Date Id
		 * @return {@link ResultStatus}
		 * 
		 */
		 public ResultStatus moveVotersModificationDataFromTempToMainTable(Long constituencyId,Long publicationDateId)
		 {
			 log.debug("Entered into moveVotersModificationDataFromTempToMainTable() Method");
			 ResultStatus resultStatus = new ResultStatus();
			 try{
				 
				 List<Object[]> result = voterModificationTempDAO.getVoterIDAndStatusFromVoterModificationTempByConstituencyId(constituencyId);
				 
				 if(result != null && result.size() > 0)
				 {
					 VoterVO voterVO = null;
					 List<String> voterIdCardNosList = new ArrayList<String>(0);
					 List<VoterVO> votersList = new ArrayList<VoterVO>(0);
					 Map<Long,VoterStatus> voterStatusMap = new HashMap<Long, VoterStatus>(0);
					 
					 for(Object[] params : result)
					 {
						 voterVO = new VoterVO();
						 voterVO.setVoterIDCardNo(params[0].toString());
						 voterVO.setStatus(params[1].toString());
						 voterVO.setPartNo((Long)params[2]);
						 votersList.add(voterVO);
						 voterIdCardNosList.add(params[0].toString());
					 }
					 
					 for(VoterVO voterVO2 : votersList)
					 {
						 Integer count = getExistanceCountOfVoterId(voterVO2.getVoterIDCardNo(),votersList);
						 if(count == 1)
						 {
							 if(voterVO2.getStatus().equalsIgnoreCase(IConstants.STATUS_ADDED))
								 voterVO2.setStatusId(1l);
							 else if(voterVO2.getStatus().equalsIgnoreCase(IConstants.STATUS_DELETED))
								 voterVO2.setStatusId(2l);
						 }
						 else if(count == 2)
						 {
							 if(voterVO2.getStatus().equalsIgnoreCase(IConstants.STATUS_ADDED))
								 voterVO2.setStatusId(4l);
							 else if(voterVO2.getStatus().equalsIgnoreCase(IConstants.STATUS_DELETED))
								 voterVO2.setStatusId(3l);
						 }
					 }
					 
					 List<VoterStatus> voterStatusList = voterStatusDAO.getAll();
					 
					 for(VoterStatus voterStatus : voterStatusList)
						 voterStatusMap.put(voterStatus.getVoterStatusId(),voterStatus);
					 
					 List<SelectOptionVO> voterIdsList = getVoterIdsByCardNosList(voterIdCardNosList);
					 
					 if(voterIdsList != null && voterIdsList.size() > 0)
					 {
						 for(VoterVO voterVO2 : votersList)
						 {
							 Long voterId = getVoterIdByVoterIdCardNo(voterVO2.getVoterIDCardNo(),voterIdsList);
							 if(voterId != null)
							 {
								 VoterModification voterModification = new VoterModification();
								 voterModification.setVoterId(voterId);
								 voterModification.setPublicationDateId(publicationDateId);
								 voterModification.setPartNo(voterVO2.getPartNo());
								 voterModification.setStatus(voterVO2.getStatus());
								 voterModification.setConstituencyId(constituencyId);
								 voterModification.setVoterStatusId(voterVO2.getStatusId());
								 voterModificationDAO.save(voterModification);
							 }
						 }
						 voterDAO.flushAndclearSession();
					 }
				 }
				 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				 return resultStatus;
				 
			 }catch (Exception e) {
				 log.error("Exception Occured in moveVotersModificationDataFromTempToMainTable() Method");
				 log.error("Exception is - "+e);
				 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				 resultStatus.setExceptionEncountered(e);
				 return resultStatus;
			 }
		 }
		 
		 public Integer getExistanceCountOfVoterId(String cardNo,List<VoterVO> votersList)
		 {
			 try{
				 int count = 0;
				 for(VoterVO voterVO : votersList)
				 {
					 if(voterVO.getVoterIDCardNo().equalsIgnoreCase(cardNo))
						 count++; 
				 }
				 return count;
			 }catch (Exception e) {
				 log.error(e);
				 return 1;
			 }
		 }
		 /**
		 * This method will return Voter Ids and their Voter Id Card Numbers, when we pass 
		 * Voter Id Card Numbers List as Parameter
		 * 
		 * @author Kamalakar Dandu
		 * @param List<String> voterIdCardNosList
		 * @return List<{@link SelectOptionVO}>
		 * 
		 */
		 public List<SelectOptionVO> getVoterIdsByCardNosList(List<String> voterIdCardNosList)
		 {
			 log.debug("Entered into getVoterIdsByCardNosList() Method");
			 List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
			 try{
				 List<Object[]> list = new ArrayList<Object[]>(0);
				 List<Object[]> list2 = new ArrayList<Object[]>(0);
				 
				 int startindex = 0;
				 int maxResults = 1000;
				 
				 for(;;)
				 {
					 if(voterIdCardNosList.size() <= 1000)
					 {
						 list2 = voterDAO.getVoterIdsByCardNos(voterIdCardNosList);
						 list.addAll(list2);
						 break;
					 }
					 else
					 {
						 list2 = voterDAO.getVoterIdsByCardNos(voterIdCardNosList.subList(startindex, maxResults));
						 list.addAll(list2);
						 for(int delIndex=0;delIndex<maxResults;delIndex++)
							 voterIdCardNosList.remove(0);
					 }
				 }
				 
				 if(list != null && list.size() > 0)
				 {
					 for(Object[] params : list)
						 resultList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				 }
				 return resultList;
			 }catch (Exception e) {
				 log.error("Exception Occured in getVoterIdsByCardNosList() Method");
				 log.error("Exception is - "+e);
				 return resultList;
			 }
		 }
		 
		 /**
		 * This method will return Voter Id, when we pass Voter Id Card Number as Parameter
		 * 
		 * @author Kamalakar Dandu
		 * @param String voterIdCardNo
		 * @param List<String> voterIdsList
		 * @return List<{@link SelectOptionVO}>
		 * 
		 */
		 public Long getVoterIdByVoterIdCardNo(String voterIdCardNo,List<SelectOptionVO> voterIdsList)
		 {
			 try{
				 for(SelectOptionVO optionVO : voterIdsList)
					 if(optionVO.getName().equalsIgnoreCase(voterIdCardNo))
						 return optionVO.getId();
				 return null;
			 }catch (Exception e) {
				 log.error("Exception Occured in getVoterIdByVoterIdCardNo() Method");
				 log.error("Exception is - "+e);
				 return null;
			 }
		 }
		 
		 /**
		 * This method will return List of Constituencies, which are to be the mapped for
		 * Modified Voter data
		 * 
		 * @author Kamalakar Dandu
		 * @return List<{@link SelectOptionVO}>
		 * 
		 */
		 public List<SelectOptionVO> getConstituenciesToBeMappedForVoterChanges()
		 {
			 log.debug("Entered into getConstituenciesToBeMappedForVoterChanges() Method");
			 List<SelectOptionVO> result = new ArrayList<SelectOptionVO>(0);
			 try{
				 List<Object[]> list = voterModificationTempDAO.getConstituenciesToBeMappedForVoterChanges();
				 
				 if(list != null && list.size() > 0)
					 for(Object[] params : list)
						 result.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				 return result;
			 }catch (Exception e) {
				 log.error("Exception Occured in getConstituenciesToBeMappedForVoterChanges() Method");
				 log.error("Exception is - "+e);
				 return result;
			 }
		 }
		 
		 
			
	 /**This method will return multiple families information to edit
	  * @author Samba Penugonda
	  * @param familiesList , families information
	  * @param userId , logged in user id
	  * @return all the existing voters details of selected families 
	  */
	public List<VoterHouseInfoVO> getMultipleFamiliesInformation(
			List<VoterHouseInfoVO> familiesList, Long userId) {
		log.debug("Entered into the getMultipleFamiliesInformation service method");
		 List<VoterHouseInfoVO> votersInfo = new ArrayList<VoterHouseInfoVO>();
		 
		 try{
			 for(VoterHouseInfoVO family : familiesList){
				 votersInfo.addAll(getFamilyInformation(null,family.getBoothId(),family.getPublicationId(),family.getHouseNo(),userId,null));
			 }
			 for(int i =0; i<votersInfo.size();i++){
				 VoterHouseInfoVO voter = votersInfo.get(i);
				 voter.setsNo(new Long(i+1));
			 }
		 }catch(Exception e){			 
			 log.error("Exception raised in  getMultipleFamiliesInformation service method");
			 e.printStackTrace();
		 }
		 return votersInfo;
	 }
	
	public List<VoterHouseInfoVO> getMultipleFamiliesInformationForHamlet(
			List<VoterHouseInfoVO> familiesList, Long userId , String selectType) {
		log.debug("Entered into the getMultipleFamiliesInformationForHamlet service method");
		 List<VoterHouseInfoVO> votersInfo = new ArrayList<VoterHouseInfoVO>();
		 
		 try{
			 for(VoterHouseInfoVO family : familiesList){
				 votersInfo.addAll(getFamilyInformation(family.getHamletId(),family.getBoothId(),family.getPublicationId(),family.getHouseNo(),userId,selectType));
			 }
			 for(int i =0; i<votersInfo.size();i++){
				 VoterHouseInfoVO voter = votersInfo.get(i);
				 voter.setsNo(new Long(i+1));
			 }
		 }catch(Exception e){			 
			 log.error("Exception raised in  getMultipleFamiliesInformationForHamlet service method");
			 e.printStackTrace();
		 }
		 return votersInfo;
	 }
	 
	 
	/**
	 * This method will return all the categories exist for logged in user
	 * @author Samba Penugonda
	 * @param userId , logged in user id
	 * @return all the categories of logged in user	 
	 */
	 public List<SelectOptionVO> getUserCategoryValuesByUserId(Long userId){
			log.debug("Entered into the getUserCategoryValuesByUserId service method");
			List<SelectOptionVO> optionsList = new ArrayList<SelectOptionVO>();
			
			try{
				List<UserVoterCategory> categories =userVoterCategoryDAO.getUserCategoriesByUserId(userId);
				
				for(UserVoterCategory userVoterCategory:categories){
					SelectOptionVO optionVO = new SelectOptionVO();
					
					optionVO.setId(userVoterCategory.getUserVoterCategoryId());
					optionVO.setName(userVoterCategory.getCategoryName());
					optionsList.add(optionVO);
				}
				
			}catch(Exception e){
			  log.debug("Exception raised in  getUserCategoryValuesByUserId service method");
			   e.printStackTrace();
			}
			
			return optionsList;
		}
	 
	 public List<SelectOptionVO> getBoothBasicInfo(Long boothId){
			try{
				 List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
				 Calendar calendar = Calendar.getInstance();
				 SelectOptionVO selectOptionVO = null;
				 Booth booth = boothDAO.get(boothId);
				 Long publicationId = booth.getPublicationDate().getPublicationDateId();
				 List<Object[]> boothLocationInfoList = boothDAO.getBoothLocations(booth.getPartNo(),booth.getConstituency().getConstituencyId());
				 for(Object[] part : boothLocationInfoList){
					 selectOptionVO = new SelectOptionVO();
					 selectOptionVO.setId((Long)part[2]);
					 calendar.setTime((Date)part[3]);
					 selectOptionVO.setName(calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR));
				     if(publicationId.longValue() == ((Long)part[2]).longValue()){
				    	 selectOptionVO.setType("true");
				     }else{
				    	 selectOptionVO.setType("false");
				     }
				     selectOptionVO.setLocation("<div id='boothlocval'>In "+selectOptionVO.getName()+" <b>Booth Location  :</b> "+part[0].toString()+" <b>&nbsp;&nbsp;Areas Covered :</b> "+part[1].toString()+"</div>");
				     returnList.add(selectOptionVO);
				 }
			   return returnList;
			}catch(Exception e){
				log.error("Exception rised in getBoothBasicInfo  ",e);
			}
			return null;
		 }
	 
	/**
	 * This method will get the influencing people of logged in user by search criteria 
	 * @author Samba Penugonda
	 * @param userId , logged in user id
	 * @param influencingPeopleVO , it has the details of search criteria i.e constituency ,tehsil,name ... etc
	 * @return return all the influence people who matched with search criteria
	 */
	 public List<InfluencingPeopleVO> getInfluencingPeopleBySearch(Long userId,
				InfluencingPeopleVO influencingPeopleVO) {	
			
			log.debug("Entered into the getInfluencingPeopleBySearch service method");
			
			List<InfluencingPeopleVO> influencePeopleList = new ArrayList<InfluencingPeopleVO>();		
			
			try{
				
				StringBuffer queryString = new StringBuffer();
				queryString.append("select model from InfluencingPeople model where model.user.userId = :userId  and ");
				
				
				if(influencingPeopleVO.getPersonName() != null && !influencingPeopleVO.getPersonName().equalsIgnoreCase("")){
					queryString.append("( model.firstName like '%"+influencingPeopleVO.getPersonName()+"%'");
					queryString.append(" or model.lastName like '%"+influencingPeopleVO.getPersonName()+"%'");
				}
				
				if(influencingPeopleVO.getFatherOrSpouceName() != null && !influencingPeopleVO.getFatherOrSpouceName().equalsIgnoreCase(""))
					queryString.append(" or model.fatherOrSpouseName like '%"+influencingPeopleVO.getPersonName()+"%'");
				
				queryString.append(")");
				
				if(influencingPeopleVO.getStateId().longValue() != 0)
					queryString.append(" and model.influencingScope = 'STATE' and model.influencingScopeValue = "+influencingPeopleVO.getStateId().toString());
				
				if(influencingPeopleVO.getDistrictId().longValue() != 0)
					queryString.append(" and model.influencingScope = 'DISTRICT' and model.influencingScopeValue = "+influencingPeopleVO.getDistrictId().toString());

				if(influencingPeopleVO.getConstituencyId().longValue() != 0)
					queryString.append(" and model.influencingScope = 'CONSTITUENCY' and model.influencingScopeValue = "+influencingPeopleVO.getConstituencyId().toString());
				
				if(influencingPeopleVO.getMandalId().longValue() != 0)
					queryString.append(" and model.influencingScope = 'MANDAL' and model.influencingScopeValue = "+influencingPeopleVO.getMandalId().toString());

				if(influencingPeopleVO.getMuncipalityId().longValue() != 0){
					
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(influencingPeopleVO.getMuncipalityId());
					queryString.append(" and  model.influencingScope = 'MUNCIPALITY/CORPORATION' and model.influencingScopeValue = "+list.get(0).toString());
				}
				
				if(influencingPeopleVO.getHamletId().longValue() != 0)
					queryString.append(" and model.influencingScope = 'VILLAGE' and model.influencingScopeValue = "+influencingPeopleVO.getHamletId().toString());
				
				
				if(influencingPeopleVO.getWardId().longValue() != 0)
					queryString.append(" and model.influencingScope = 'WARD' and model.influencingScopeValue = "+influencingPeopleVO.getWardId().toString());
				
				if(influencingPeopleVO.getBoothId().longValue() != 0)
					queryString.append(" and model.influencingScope = 'BOOTH' and model.influencingScopeValue = "+influencingPeopleVO.getBoothId().toString());
				
				influencingPeopleVO.setUserId(userId);
				
				List<InfluencingPeople> influencePeoplesList = influencingPeopleDAO.getInfluencePeopleBySearch(influencingPeopleVO , queryString.toString());
				
				for(InfluencingPeople influencePeople:influencePeoplesList){
					
					InfluencingPeopleVO influencePeopleVO = new InfluencingPeopleVO();
					
					influencePeopleVO.setFatherOrSpouceName(influencePeople.getFatherOrSpouseName());
					influencePeopleVO.setFirstName(influencePeople.getFirstName());
					influencePeopleVO.setLastName(influencePeople.getLastName());
					influencePeopleVO.setGender(influencePeople.getGender());
					influencePeopleVO.setInfluencePersonId(influencePeople.getInfluencingPeopleId());
					influencePeopleVO.setContactNumber(influencePeople.getPhoneNo());
					
					if(influencePeople.getParty() != null)
					 influencePeopleVO.setParty(influencePeople.getParty().getShortName());
					
					influencePeopleList.add(influencePeopleVO);
				}
				
			}catch(Exception e){
				log.error("Exception raised in getInfluencingPeopleBySearch service method",e);
				e.printStackTrace();
			}
			return influencePeopleList;
		}
		
	
	 /**
	  * This method will map the voter as influencing person
	  * @author Samba Penugonda
	  * @param influencePeopleId , this is the id of influenced person to whom the voter is going to be mapped
	  * @param voterId , this is the id of voter who is going to be mapped
	  */
	public void mapVoterAsInfluencingPerson(Long influencePeopleId , Long voterId){
		
		log.debug("Entered into the mapVoterAsInfluencingPerson service method");
		
		try{
	
			InfluencingPeople influencingPeople = influencingPeopleDAO.get(influencePeopleId);
			
			influencingPeople.setVoter(voterDAO.get(voterId));
			
			influencingPeopleDAO.save(influencingPeople);
		}catch(Exception e){
			log.error("Exception raised in mapVoterAsInfluencingPerson service method");
			e.printStackTrace();
			
		}
	}
		 /**
		  * This Method Is Used To Check Weater The influencing people  is Already Avalibale Or Not with the 
		  * voterId
		  * @author Prasad Thiragabathina
		  * @param Long voterId
		  * @param String type
		  * @return ResultStatus
		  */
		public ResultStatus checkForVoter(Long voterId, String type) {
			ResultStatus resultStatus = new ResultStatus();
			Long count = 0l;
			List<Long> voterIds = null;
			if(voterId != null && voterId > 0)
			{	
				if(type.equalsIgnoreCase("influencingPeople"))
				{
					voterIds = influencingPeopleDAO.getinfluencingPeopleVoterId(voterId);
					
				}
				else if(type.equalsIgnoreCase("cadre"))
				{
					 voterIds = cadreDAO.getinfluencingPeopleVoterId(voterId);
					
				}
				else if(type.equalsIgnoreCase("candidate"))
				{
					 voterIds = candidateDAO.getinfluencingPeopleVoterId(voterId);
					
				}
				if(voterIds != null && voterIds.size() > 0 && voterIds.get(0) != null)
				{
					 count = voterIds.get(0);
					 if(count > 0)
						{
							resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						}
						else
						{
							resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						}
				}
				else
				{
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				}
			}
			return resultStatus;
		}

		public void getLocalElectionResults(Long electionId,List<Long> wardIds,Long localElecBodyId,PartyVotesEarnedVO partyVotesEarnedVO,List<String> partiesList){
		 try{
			 List<PartyVotesEarnedVO> partyResults = new ArrayList<PartyVotesEarnedVO>();
				PartyVotesEarnedVO partyVotesVO = null;
				 boolean firstIterationFlag = true ;
				partyVotesEarnedVO.setTotalBooths(wardIds.size());
			   //boothConstituencyElectionDAO.getBoothIdsByElectionIdWardIds(electionId,wardIds);
			   List<Object[]> partiesResultsList = nominationDAO.getLocalBodyWiseResultsOfAllPartiesInLocalElectionBodies(localElecBodyId,electionId,wardIds);	
			   List<Object[]> basicInfo = constituencyElectionDAO.getVotesInfoForLocalBodyElection(localElecBodyId,electionId,wardIds);
			   if(basicInfo != null && basicInfo.size() > 0){
				   Object[] basicDetails = basicInfo.get(0);
				   partyVotesEarnedVO.setTotalVotes(((Double)basicDetails[1]).longValue());
				   partyVotesEarnedVO.setPolledVotes(((Double)basicDetails[0]).longValue());
			   }
			   if(partiesResultsList != null){
				   for(Object[] params:partiesResultsList){
					   partyVotesVO = new PartyVotesEarnedVO();
					   partyVotesVO.setPartyId((Long)params[0]);
					   partyVotesVO.setPartyName(params[1].toString());
					   partyVotesVO.setVotesEarned(((Double)params[2]).longValue());
					   if(firstIterationFlag){
						   firstIterationFlag = false ;
						   partyVotesVO.setWonStatus(true);
					   }
					   partyResults.add(partyVotesVO);
					   if(!partiesList.contains(params[1].toString()))
							partiesList.add(params[1].toString());
				   }
			   }
			   partyVotesEarnedVO.setPartyVotesEarnedVOs(partyResults);
		 }catch(Exception e){
			 log.error("Exception raised in getLocalElectionResults",e);
		 }
		}
		
		public void buildLocalitiesBasedOnTypeAndIdForMuncipality(VoterHouseInfoVO voterHouseInfoVO,Long voterId,Long userId,SelectOptionVO defaultSelectOptionVO)
		{
			 if(voterHouseInfoVO.getSelType().equalsIgnoreCase("muncipality"))
				   getWardsForMuncipalities(voterHouseInfoVO,voterHouseInfoVO.getSelTypeId(),defaultSelectOptionVO);	
			
		}

		
		public void buildLocalitiesBasedOnTypeAndId(VoterHouseInfoVO voterHouseInfoVO,Long voterId,Long userId,SelectOptionVO defaultSelectOptionVO)
		{      String selectedType=voterHouseInfoVO.getSelectedType();
		       Long selectedTypeId=voterHouseInfoVO.getSelectedTypeId();
			// logic to bind localities to voterHouseInfoVo
			   if( selectedType!=null && selectedTypeId != null && ! selectedType.equalsIgnoreCase(""))
			   {
				   if(voterHouseInfoVO.getSelectedType().equalsIgnoreCase("constituency")){
					   // logic to get all localtions inside constituency ()
					List<Object[]> localities =  boothPublicationVoterDAO.getLocalitiesForConstituency(selectedTypeId);
					List<Object[]> hamlets    =  boothPublicationVoterDAO.getHamletsForConstituency(selectedTypeId);
					 List<SelectOptionVO> localitiesList = new ArrayList<SelectOptionVO>();
					 
					if(localities !=null && localities.size()>0){
						
						  processLocalities(localities, localitiesList);
						   }
					   if(hamlets !=null && hamlets.size()>0){
						 processHamlets(hamlets, localitiesList,false);
					   }
					    //sort localitiesList
					   sortSelectionOptionVoList(localitiesList);
					   localitiesList.add(0, defaultSelectOptionVO);
					   voterHouseInfoVO.setLocalitiesList(localitiesList);
					 
				   }

				   if(voterHouseInfoVO.getSelectedType().equalsIgnoreCase("mandal") || voterHouseInfoVO.getSelectedType().equalsIgnoreCase("muncipality") )
				   {
					   // logic to get all locations inside mondal 
					   
					   List<Object[]> hamlets = null; 
					   List<Object[]> wards = null;    
					   List<Object[]> localities=null;
					   localities =  boothPublicationVoterDAO.getLocalitiesForMandals(selectedTypeId);
					   boolean flag=true;
					   if(selectedType.equalsIgnoreCase("mandal")){
						  
					    hamlets =  hamletDAO.findHamletsByTehsilId(selectedTypeId);
					  }else if(selectedType.equalsIgnoreCase("muncipality"))
					  {
						hamlets=   boothDAO.getWardsInMuncipality(selectedTypeId, voterHouseInfoVO.getPublicationId());
						
						flag=false;// if(hamlets.size()<0)					 
					  }
					   /* if(selectedType.equalsIgnoreCase("muncipality"))
						    hamlets =  hamletDAO.findHamletsByTehsilId(selectedTypeId);*/
									  
					      List<SelectOptionVO> localitiesList = new ArrayList<SelectOptionVO>();
					       if(localities !=null && localities.size()>0){
							
							  processLocalities(localities, localitiesList);
							   }
				      if(hamlets !=null && hamlets.size()>0){
							 processHamlets(hamlets, localitiesList,flag);
						   }
					     
					         //sort localitiesList
					       sortSelectionOptionVoList(localitiesList);
					       localitiesList.add(0, defaultSelectOptionVO);
					         voterHouseInfoVO.setLocalitiesList(localitiesList);
					   
				   }
				   

     	      if(voterHouseInfoVO.getSelectedType().equalsIgnoreCase("panchayat")){
					   
     	    	   //List<Long> panchayatList = panchayatDAO.getPanchayatIdsListByMandalId(voterHouseInfoVO.getGroupLocationValue());
				   //List<Object[]> hamlets = panchayatHamletDAO.getHamletsOfPanchayats(panchayatList);
     	    	  Map<Long,Object[]> hamletMap = new HashMap<Long,Object[]>();
				   List<Object[]> hamlets =  panchayatHamletDAO.getHamletsOfAPanchayat(selectedTypeId);
				   if(hamlets != null && hamlets.size() > 0){
               		for(Object[] hamlet:hamlets){
               			hamletMap.put(new Long(hamlet[0].toString()), hamlet);
               		}
               	   }
				   List<Object[]> partialhamlets =  boothPublicationVoterDAO.getPartialBoothHamlets(selectedTypeId, voterHouseInfoVO.getPublicationId()); 
               	if(partialhamlets != null && partialhamlets.size() > 0){
               		for(Object[] hamlet:partialhamlets){
               			hamletMap.put(new Long(hamlet[0].toString()), hamlet);
               		}
               	}
               	hamlets = new ArrayList<Object[]>(hamletMap.values());
				   List<SelectOptionVO> localitiesList = new ArrayList<SelectOptionVO>();
			
					   if(hamlets !=null && hamlets.size()>0){
						 processHamlets(hamlets, localitiesList,true);
					   }
					    //sort localitiesList
					   sortSelectionOptionVoList(localitiesList);
					   localitiesList.add(0, defaultSelectOptionVO);
					   voterHouseInfoVO.setLocalitiesList(localitiesList);
				   
			   }

				   if(voterHouseInfoVO.getSelectedType().equalsIgnoreCase("booth")){
					   // logic to get  all locations based on booth id belonging to hamlet
					   List<Object[]> hamlets =null;
					   Map<Long,Object[]> hamletMap = new HashMap<Long,Object[]>();
					  
					   List<Object[]> localities =null ;
					   
					                            Booth booth  =  boothDAO.get(selectedTypeId);
					                            if(booth.getLocalBody() == null ){
					                            	hamlets = boothPublicationVoterDAO.getHamletsForBooth(selectedTypeId);
					                            	List<Object[]> partialhamlets = boothPublicationVoterDAO.getHamletsForPartialBooth(selectedTypeId);
					                            	if(hamlets != null && hamlets.size() > 0){
					                            		for(Object[] hamlet:hamlets){
					                            			hamletMap.put(new Long(hamlet[0].toString()), hamlet);
					                            		}
					                            	}
					                            	if(partialhamlets != null && partialhamlets.size() > 0){
					                            		for(Object[] hamlet:partialhamlets){
					                            			hamletMap.put(new Long(hamlet[0].toString()), hamlet);
					                            		}
					                            	}
					                            	hamlets = new ArrayList<Object[]>(hamletMap.values());
					                            }else {
					                           
					                            	localities =   boothPublicationVoterDAO.getLocalitiesForBooth(selectedTypeId,userId);
					                            }
					                            
					//   List<Object[]> localities =   boothPublicationVoterDAO.getLocalitiesForBooth(selectedTypeId);
					//   List<Object[]>  wards     =
				   //identify whether booth is muncipality or not 
					                      
					   
					   List<SelectOptionVO> localitiesList = new ArrayList<SelectOptionVO>();
					   //List<Object[]> hamlets =null;
					 if(localities !=null && localities.size()>0){
							
							  processLocalities(localities, localitiesList);
							   }
						 if(hamlets !=null && hamlets.size()>0){
							 processHamlets(hamlets, localitiesList,true);
						   }
						    //sort localitiesList
						   sortSelectionOptionVoList(localitiesList);
						   localitiesList.add(0, defaultSelectOptionVO);
						   voterHouseInfoVO.setLocalitiesList(localitiesList);
				   
				   
				   }
  
			   
			   }
		}
		
		public void  getWardsForMuncipalities(VoterHouseInfoVO voterHouseInfoVO , Long muncipalityId , SelectOptionVO defaultSelectOptionVO){
			
		 List<SelectOptionVO> localitiesList = new ArrayList<SelectOptionVO>();

			Long localBodyId = assemblylocalElectionBodyDAO.get(muncipalityId).getLocalElectionBody().getLocalElectionBodyId();
			
			//List<Object[]> localities = constituencyDAO.getWardsInMuncipality(muncipalityId);
		 
		 List<Object[]> localities = constituencyDAO.getWardsInMuncipalityFomConstituency(localBodyId);
			
			  processLocalities(localities, localitiesList);
			  
			   localitiesList.add(0, defaultSelectOptionVO);
			   voterHouseInfoVO.setLocalitiesList(localitiesList);
			
		}
		
		public List<SelectOptionVO> getLocalitiesForWards(Long wardId , Long userId ,String query)
		{
			/*String queryCondition="model.localElectionBody.localElectionBodyId in(select a.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody a " +
					" where a.assemblyLocalElectionBodyId = :id) ";*/
			//List<Object[]> localities =  localityDAO. getLocalitiesForWard(wardId , userId);
			List<Object[]>  localities        =      localityDAO.getAllLocalitiesForHamlet( userId , wardId ,"" ,query);
			
			 List<SelectOptionVO> localitiesList = new ArrayList<SelectOptionVO>();
			   //List<Object[]> hamlets =null;
			   if(localities !=null && localities.size()>0){
					
				   processLocalities(localities, localitiesList);
					   }
			   SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
			   defaultSelectOptionVO.setId(0l);
			   defaultSelectOptionVO.setName("Select");
			   defaultSelectOptionVO.setValue("Select");
			   
			   sortSelectionOptionVoList(localitiesList);
			   localitiesList.add(0, defaultSelectOptionVO);
			   
			    return localitiesList;
		}

		
		public List<SelectOptionVO> getLocalities(Long hamletId)
		{
			List<Object[]> localities =   getLocalitiesForHamlet(hamletId);
			 List<SelectOptionVO> localitiesList = new ArrayList<SelectOptionVO>();
			   //List<Object[]> hamlets =null;
			   if(localities !=null && localities.size()>0){
					
				   processLocalities(localities, localitiesList);
					   }
			   SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
			   defaultSelectOptionVO.setId(0l);
			   defaultSelectOptionVO.setName("Select");
			   defaultSelectOptionVO.setValue("Select");
			   
			   sortSelectionOptionVoList(localitiesList);
			   localitiesList.add(0, defaultSelectOptionVO);
			   
			    return localitiesList;
		}
		public List<SelectOptionVO> getLocalities(Long hamletId,Long userId)
		{
			List<Object[]> localities =   getLocalitiesForHamlet(hamletId,userId);
			 List<SelectOptionVO> localitiesList = new ArrayList<SelectOptionVO>();
			   //List<Object[]> hamlets =null;
			   if(localities !=null && localities.size()>0){
					
				   processLocalities(localities, localitiesList);
					   }
			   SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
			   defaultSelectOptionVO.setId(0l);
			   defaultSelectOptionVO.setName("Select");
			   defaultSelectOptionVO.setValue("Select");
			   
			   sortSelectionOptionVoList(localitiesList);
			   localitiesList.add(0, defaultSelectOptionVO);
			   
			    return localitiesList;
		}
		
	public List<SelectOptionVO> getWards(Long muncipalityId,Long publicationId,Long constituencyId)
		{
			List<Object[]> wards= findByWardsByAssemblyLocalElectionBodyId(muncipalityId,publicationId,constituencyId);
			 List<SelectOptionVO> localitiesList = new ArrayList<SelectOptionVO>();
			utilBusinessDelegator(wards,localitiesList,false);
			return localitiesList;
		}
	public List<SelectOptionVO> getHamlets(Long panchayatId)
	{
		List<Object[]> hamlets= getHamletsOfAPanchayat(panchayatId);
		 List<SelectOptionVO> localitiesList = new ArrayList<SelectOptionVO>();
		utilBusinessDelegator(hamlets,localitiesList,true);
		return localitiesList;
	}
	public List<Object[]>  getLocalitiesForHamlet (Long hamletId)
	{
		
	return  boothPublicationVoterDAO.getLocalitiesForHamlet(hamletId);
	}
	
		public List<Object[]>  getLocalitiesForHamlet (Long hamletId,Long userId)
		{
			
		return  boothPublicationVoterDAO.getLocalitiesForHamlet(hamletId);
		}
		
		public List<Object[]>  getWardsInMuncipality(Long mincipalityId, Long PublicationId)
		{
		return   boothDAO.getWardsInMuncipality(mincipalityId, PublicationId);	
		}
		public List<Object[]>  findByWardsByAssemblyLocalElectionBodyId(Long mincipalityId, Long publicationId, Long constituencyId)
		{
		return   wardDAO.getWardsListByLocalEleBodyIdAndConstituencyId(mincipalityId, publicationId,constituencyId);	
		}
		public List<Object[]>  getHamletsOfAPanchayat(Long panchayatId)
		{
		return     panchayatHamletDAO.getHamletsOfAPanchayat(panchayatId);  
		}
		public void processConstitutes(){
			
		}
		public void utilBusinessDelegator(List<Object[]> localities , List<SelectOptionVO> localitiesList ,boolean flag)
		{
			// List<SelectOptionVO> localitiesList = new ArrayList<SelectOptionVO>();
			   //List<Object[]> hamlets =null;
			   if(localities !=null && localities.size()>0){
					
				   processHamlets(localities, localitiesList,flag);
					   }
			   SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
			   defaultSelectOptionVO.setId(0l);
			   defaultSelectOptionVO.setName("Select");
			   defaultSelectOptionVO.setValue("Select");
			   
			 //  sortSelectionOptionVoList(localitiesList);
			   localitiesList.add(0, defaultSelectOptionVO);
			   
			   
		}
		
		public void sortSelectionOptionVoList(List<SelectOptionVO> localitiesList)
		{
			 Collections.sort(localitiesList, new Comparator<SelectOptionVO>() {

					public int compare(SelectOptionVO arg0,
							SelectOptionVO arg1) {
						
						return arg0.getValue().trim().toUpperCase().compareTo(arg1.getValue().trim().toUpperCase());
					}
				});
		}
		
		
		public void processLocalities(List<Object[]> localities , List<SelectOptionVO> localitiesList ){
			
			  for (Object[] a : localities)
			    {  SelectOptionVO sv =new  SelectOptionVO();
				   // a[0]="x"+a[0];
			       try
			       {
				    sv.setId((Long)a[0]);
			       }catch(Exception e){
				    sv.setId(new BigInteger(a[0].toString()).longValue());
			       }
				    sv.setName(a[1]!=null?a[1].toString():"");
				    sv.setValue(a[1]!=null?a[1].toString():"");
				    localitiesList.add(sv);
				 
		        	}
		}
		
		public void processHamlets(List<Object[]> hamlets , List<SelectOptionVO> localitiesList , boolean flag)
		{
			
			for (Object[] a : hamlets)
		    {  SelectOptionVO sv =new  SelectOptionVO();
			   // a[0]="x"+a[0];
		      if(a[0] instanceof BigInteger)
			    sv.setId(((BigInteger)a[0]).longValue());
			  if(a[0] instanceof Long)  
				sv.setId((Long)a[0]);
		        sv.setName(a[1]!=null?a[1].toString():"");
			    sv.setValue(a[1]!=null?a[1].toString():"");
			    sv.setHampletPresent(flag);
			    localitiesList.add(sv);
	        	}
			if(localitiesList != null && localitiesList.size() > 0){
				Collections.sort(localitiesList,arraySort1);
			}
		}

		
		//Influencing People  for voters Page 
		public List<InfluencingPeopleBeanVO> getInfluencingPeopleCount(Long userId,Long locationValue,String type,Long publicationDateId)
		{
		log.debug("Entered into getProblemDetailsForVoterPage().....");
			
			List<String> locationValues = new ArrayList<String>(0);
			List<Long> cadreLevelValues = new ArrayList<Long>(0);
			List<Long> politicianValues = new ArrayList<Long>(0);
			List<SelectOptionVO> selectOptionVOs = new ArrayList<SelectOptionVO>();
			List<InfluencingPeopleBeanVO> result = new ArrayList<InfluencingPeopleBeanVO>(0);
			InfluencingPeopleBeanVO influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
			List<Long> locationIds = new ArrayList<Long>();
			Long constiId = null;
			String partNo = null;
		try{
			if(type.equalsIgnoreCase("constituency"))
			{
				locationValues.add(locationValue.toString());
				cadreLevelValues.add(locationValue);
				politicianValues.add(locationValue);
			}
			
			else if(type.equalsIgnoreCase("mandal") )
			{
				if(locationValue != null && locationValue.toString().substring(0,1).trim().equalsIgnoreCase("2"))
				{
					type = "MANDAL";
					
					locationValues.add(locationValue.toString().substring(1));
					cadreLevelValues.add(new Long(locationValue.toString().substring(1)));
					politicianValues.add(new Long(locationValue.toString().substring(1)));
				}
				else
				{
					type="MUNCIPALITY/CORPORATION";
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationValue.toString().substring(1).trim()));
					 if(list != null)
						 locationValues.add(list.get(0).toString());
					 cadreLevelValues.add(new Long(list.get(0).toString()));
					 politicianValues.add(new Long(list.get(0).toString()));
				}
				
			}
			
			else if(type.equalsIgnoreCase("booth"))
			{
				Booth booth = boothDAO.get(locationValue);
				partNo = booth.getPartNo();
				constiId = booth.getConstituency().getConstituencyId();
				locationValues.add(booth.getPartNo());
				cadreLevelValues.add(new Long(booth.getPartNo().trim().toString()));
				 politicianValues.add(locationValue);
				
			}
			
			else if(type.equalsIgnoreCase("ward")||type.equalsIgnoreCase("customWard"))
			{
				locationValues.add(locationValue.toString());
				
				locationValue =new Long("1"+locationValue);
				cadreLevelValues.add(locationValue);
				politicianValues.add(locationValue);
				
			}
			else if(type.equalsIgnoreCase("panchayat"))
			{
				
				politicianValues.add(locationValue);
				List<Object[]> booths = boothDAO.getBoothsInAPanchayat(locationValue,publicationDateId);
				//List<Object[]> hamlets = userVoterDetailsDAO.getHamletsIdsForUser(locationValue, userId);
				List<Object[]> hamlets = panchayatHamletDAO.getHamletsOfAPanchayat(locationValue);
				if(booths != null && booths.size() > 0)
				{
					for (Object[] booth : booths){
						/*if(booth[1] != null)
							locationValues.add(booth[1].toString());*/
						
						cadreLevelValues.add(new Long(booth[1].toString()));
					}
					for(Object[] hamlet : hamlets)
					{
						locationValues.add(hamlet[0].toString());
					}
				}
				
				
			}
			else if(type.equalsIgnoreCase("hamlet"))
			{
					
					locationValues.add(locationValue+"");
						cadreLevelValues.add(locationValue);
						politicianValues.add(locationValue);
					
								
			}
			if(locationValues != null)
			{
			List<Long> influencingPeopleCount = null;
			
			if(type.equalsIgnoreCase("panchayat"))
			{
				List<Long> locValues = new ArrayList<Long>(0);
				locValues.add(0L);
				for(String locStr : locationValues)
					locValues.add(Long.valueOf(locStr));
				influencingPeopleCount =  influencingPeopleDAO.getInfluencingPeopleCountInHamlets(userId,locValues);
			}
			else
			{
				
				for (String parms : locationValues) {
					Long values = Long.valueOf(parms);
					locationIds.add(values);
				}
				influencingPeopleCount =  influencingPeopleDAO.getInfluencingPeopleCount(userId,locationIds,type,constiId,partNo);
			}
				
			if(type.equalsIgnoreCase("panchayat"))
			{
				List<Long> panchayitIds = new ArrayList<Long>(0);
				panchayitIds.add(locationValue);
				cadreLevelValues = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIds);
			}
			//influencingPeopleBeanVO.setCadreCount(cadreDAO.getCadreCountInALocation(userId,cadreLevelValues,type));
			influencingPeopleBeanVO.setCadreCount(tdpCadreDAO.getTdpCadreCountInALocation(cadreLevelValues,type,2014l));
			
		    List<Long> politicians = boothPublicationVoterDAO.getCandidateCount(politicianValues, publicationDateId,type);
		    if(politicians != null && politicians.size() > 0)
		    	influencingPeopleBeanVO.setPoliticianCount(politicians.get(0));
		    else
		    	influencingPeopleBeanVO.setPoliticianCount(0l);
		   
			 if(influencingPeopleCount != null && influencingPeopleCount.size() > 0)
				 influencingPeopleBeanVO.setInfluencePeopleCount(new Long(influencingPeopleCount.get(0)));
				else
					influencingPeopleBeanVO.setInfluencePeopleCount((0l));
			 result.add(influencingPeopleBeanVO);
			}
			
		   }
		catch(Exception e)
		{
			
			log.error("Exception Occured in getInfluencingPeopleCount() - ",e);
		}
		return result;
		
}
		public List<InfluencingPeopleBeanVO> getCadrePeopleCountByLocation(Long userId,Long locationValue,String type,Long publicationDateId)
		{
		log.debug("Entered into getCadrePeopleCountByLocation().....");
			
			
			List<Long> cadreLevelValues = new ArrayList<Long>(0);
			
			List<InfluencingPeopleBeanVO> result = new ArrayList<InfluencingPeopleBeanVO>(0);
			
			Long constiId = null;
			String partNo = null;
		try{
			if(type.equalsIgnoreCase("constituency"))
			{
				cadreLevelValues.add(locationValue);
			}
			
			else if(type.equalsIgnoreCase("mandal") )
			{
				if(locationValue != null && locationValue.toString().substring(0,1).trim().equalsIgnoreCase("2"))
				{
					type = "MANDAL";
					cadreLevelValues.add(new Long(locationValue.toString().substring(1)));
					
				}
				else
				{
					type="MUNCIPALITY/CORPORATION";
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationValue.toString().substring(1).trim()));
					cadreLevelValues.add(new Long(list.get(0).toString()));
					
				}
				
			}
			
			else if(type.equalsIgnoreCase("ward")||type.equalsIgnoreCase("customWard"))
			{
				locationValue =new Long("1"+locationValue);
				cadreLevelValues.add(locationValue);
			}
			
			else if(type.equalsIgnoreCase("hamlet") || type.equalsIgnoreCase("panchayat")
					|| type.equalsIgnoreCase("booth"))
			{
				cadreLevelValues.add(locationValue);
							
			}
			
			InfluencingPeopleBeanVO influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
			influencingPeopleBeanVO.setAccessType("2014");
			influencingPeopleBeanVO.setCadreCount(0l);
			result.add(influencingPeopleBeanVO);
			InfluencingPeopleBeanVO influencingPeopleBeanVO1 = new InfluencingPeopleBeanVO();
			influencingPeopleBeanVO1.setAccessType("2012");
			influencingPeopleBeanVO1.setCadreCount(0l);
			result.add(influencingPeopleBeanVO1);
			//influencingPeopleBeanVO.setCadreCount(cadreDAO.getCadreCountInALocation(userId,cadreLevelValues,type));
			List<Object[]> cadreInfo = tdpCadreDAO.getTdpCadreCountInALocationForEnrollment(cadreLevelValues,type);
			if(cadreInfo != null && cadreInfo.size() > 0)
			{
				
			for(Object[] params  : cadreInfo)	
			{
				if(params[0].toString().equalsIgnoreCase("2014"))
					result.get(0).setCadreCount((Long)params[1]);
				if(params[0].toString().equalsIgnoreCase("2012"))
					result.get(1).setCadreCount((Long)params[1]);
			}
				
				
				
			}
		}
		catch(Exception e)
		{
			
			log.error("Exception Occured in getCadrePeopleCountByLocation() - ",e);
		}
		return result;
		
}
		
public List<VoterVO> getInfluencingPeopleVoterDetails(Long userId,Long locationValue,String type,String buttonName,
	Long publicationId,Integer startIndex , Integer maxRecords,String columnName,String order)
{
List<String> locationValues = new ArrayList<String>(0);
List<VoterVO> voters = new ArrayList<VoterVO>();
List<Long> cadreLevelValues = new ArrayList<Long>(0);
List<Long> politicianValues = new ArrayList<Long>(0);
List<Voter> votersList = new ArrayList<Voter>();
Long totalCount = 0L;
List<SelectOptionVO> selectOptionVOs = new ArrayList<SelectOptionVO>();
List<Long> values = new ArrayList<Long>(0);
List<InfluencingPeople> influencingDetails = new ArrayList<InfluencingPeople>();
Long Id = 0l;
String name = null;
Long constiId = null;
String partNo = null;
try{
	if(type.equalsIgnoreCase("constituency"))
	{
		
		locationValues.add(locationValue.toString());
		
		Id = locationValue;
		cadreLevelValues.add(locationValue);
		politicianValues.add(locationValue);
	}
	else if(type.equalsIgnoreCase("mandal") )
	{
		if(locationValue != null && locationValue.toString().substring(0,1).trim().equalsIgnoreCase("2"))
		{
			type = "MANDAL";
			locationValues.add(locationValue.toString().substring(1));
			
			Id = new Long(locationValue.toString().substring(1));
			cadreLevelValues.add(new Long(locationValue.toString().substring(1)));
			politicianValues.add(new Long(locationValue.toString().substring(1)));
		}
		else{
			
			
			type="MUNCIPALITY/CORPORATION";
			List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationValue.toString().substring(1).trim()));
			if(list != null)
				locationValues.add(list.get(0).toString());
			Id = (Long)list.get(0);
			cadreLevelValues.add(new Long(list.get(0).toString()));
			politicianValues.add(new Long(list.get(0).toString()));
		}
				
	}
	
	else if(type.equalsIgnoreCase("booth"))
	{
		Booth booth = boothDAO.get(locationValue);
		constiId = booth.getConstituency().getConstituencyId();
		//List partNo =boothDAO.getPartNoByBoothId(locationValue);
		partNo = booth.getPartNo();
		locationValues.add(booth.getPartNo());
		cadreLevelValues.add(new Long(booth.getPartNo().trim()));
		politicianValues.add(locationValue);
	}
	else if(type.equalsIgnoreCase("ward")||type.equalsIgnoreCase("customWard"))
	{
		locationValues.add(locationValue.toString());
		
		Id = locationValue;
		locationValue =new Long("1"+locationValue);
		cadreLevelValues.add(locationValue);
		politicianValues.add(locationValue);
		
	}
	else if(type.equalsIgnoreCase("panchayat"))
	{
		politicianValues.add(locationValue);
		Id = locationValue;
		List<Object[]> booths = boothDAO.getBoothsInAPanchayat(locationValue,publicationId);
		
		/*if(booths != null && booths.size() > 0)
		{
			for (Object[] booth : booths){
				if(booth[1] != null)
					locationValues.add(booth[1].toString());
					cadreLevelValues.add(new Long(booth[1].toString()));
					
				}
		}*/
		
		List<Object[]> hamlets = userVoterDetailsDAO.getHamletsIdsForUser(locationValue, userId);
		if(booths != null && booths.size() > 0)
		{
			for (Object[] booth : booths){
				/*if(booth[1] != null)
					locationValues.add(booth[1].toString());*/
				
				cadreLevelValues.add(new Long(booth[1].toString()));
			}
			for(Object[] hamlet : hamlets)
			{
				locationValues.add(hamlet[0].toString());
			}
		}
		List<Long> panchayitIds = new ArrayList<Long>(0);
		panchayitIds.add(locationValue);
		cadreLevelValues = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIds);
		
				
	}

	else if(type.equalsIgnoreCase("hamlet"))
	{
		politicianValues.add(locationValue);
		Id = locationValue;
		
		locationValues.add(locationValue.toString());
		cadreLevelValues.add(locationValue);
					
					
	}
	if(Id > 0 && !type.equalsIgnoreCase("BOOTH") && !type.equalsIgnoreCase("panchayat") && !type.equalsIgnoreCase("hamlet"))
	 name = (String) constituencyDAO.getNameByInfluenceScopeValue(Id,type).get(0);
	if(type.equalsIgnoreCase("panchayat"))
		name = panchayatDAO.get(Id).getPanchayatName().toString();
	if(type.equalsIgnoreCase("hamlet"))
		name = hamletDAO.get(Id).getHamletName().toString();
	
	if(buttonName.equalsIgnoreCase("InfluencePeople"))
		voters = getInfluencePeopleDetails(userId,locationValues,type,startIndex,maxRecords,name,columnName,order,constiId,partNo);
	 if(buttonName.equalsIgnoreCase("Cadre"))
		 voters =  getCadrePeopleDetails(userId,cadreLevelValues,type,startIndex,maxRecords,name,columnName,order);
	 if(buttonName.equalsIgnoreCase("Politician"))
		 voters = getPoliticianDetails(politicianValues, type, startIndex,maxRecords,name,publicationId,columnName,order,userId);
		
}

  catch(Exception e)
  {
	  e.printStackTrace();
  }
return voters;


}
public List<VoterVO> getInfluencePeopleDetails(Long userId,List<String> locationValues,String type,Integer startIndex,Integer maxRecords,String name,String columnName,String order,Long constiId,String partNo)
{
	List<VoterVO> voters = new ArrayList<VoterVO>();
	List<Voter> votersList = new ArrayList<Voter>();;
	Long totalCount = 0L;
	List<InfluencingPeople> influencingDetails = new ArrayList<InfluencingPeople>();
	List<Long> locationIds = new ArrayList<Long>();
	List<Long> influencyDetailsCount = new ArrayList<Long>();
	Map<Long,VoterVO> influencingMap = new HashMap<Long, VoterVO>();
	
	if(columnName.equalsIgnoreCase("relativeFirstName"))
	{
		columnName = "relativeName";
	}
	else if(columnName.equalsIgnoreCase("mobileNo"))
	{
		columnName = "phoneNo";
	}
	else if(columnName.equalsIgnoreCase("influencingRange"))
	{
		columnName = "influencingScope";
	}else if(columnName.equalsIgnoreCase("partyName"))
	{
		columnName = "party.shortName";
	}else if(columnName.equalsIgnoreCase("cast")){
		columnName = "caste";
	}
	for (String parms : locationValues) {
		Long id = Long.valueOf(parms);
		locationIds.add(id);
	}
	if(locationValues != null)
		influencingDetails =  influencingPeopleDAO.getAllDetailsOfInfluencingPeople(userId,locationIds,type,startIndex, maxRecords,columnName,order);
		//influencingDetails =  influencingPeopleDAO.getInfluencingPeopleVoterIDs(userId,locationValues,type,startIndex, maxRecords);
		Long count = new Long(startIndex);
		
		if(influencingDetails != null)
		{
			//Influencing people voter Details (VoterIds not avilable) 
			
			for(InfluencingPeople params : influencingDetails)
				{
				//Influencing people voter Details Count
				//List<Long> influencyDetailsCount = influencingPeopleDAO.getInfluencingPeopleCountByLocation(userId,locationValues,type);
				if(type.equalsIgnoreCase("panchayat"))
					influencyDetailsCount = influencingPeopleDAO.getInfluencingPeopleCountInHamlets(userId,locationIds);	
				else
					influencyDetailsCount = influencingPeopleDAO.getInfluencingPeopleCount(userId,locationIds,type,constiId,partNo);
				totalCount =new Long(influencyDetailsCount.get(0));
				VoterVO voterVO = new VoterVO();
				if(params.getVoter() == null)
				{
					voterVO.setVoterId((++count)+"");
					voterVO.setFirstName(params.getFirstName()+" "+params.getLastName());
					voterVO.setGender(params.getGender());
					voterVO.setHouseNo("# "+params.getUserAddress().getHouseNo());
					voterVO.setCast(params.getCaste());
					voterVO.setMobileNo(params.getPhoneNo()!=null ? params.getPhoneNo() :" ");
					voterVO.setInfluencingRange(params.getInfluencingScope());
					voterVO.setPosition(params.getInfluencingPeoplePosition().getPosition());
					if(params.getParty() != null){
					  voterVO.setPartyName(params.getParty().getShortName());
					}else{
					  voterVO.setPartyName("");
					}
					voterVO.setCast("N/A");
						
					
						StringBuilder location=new StringBuilder();
						//voterVO.setLocalArea(name);
						UserAddress address=userAddressDAO.get(params.getUserAddress().getUserAddressId());
						if(address!=null){
							if(type.equalsIgnoreCase("constituency")){
								if(address.getTehsil()!=null){
									location.append(address.getTehsil().getTehsilName()!=null?address.getTehsil().getTehsilName()+"(Mandal) ":"");
								}
							}
							
							if(!type.equalsIgnoreCase("booth")){
								if(address.getHamlet()!=null){
									location.append(address.getHamlet().getHamletName()+"(Hamlet) ");
								}
							}
														
							if(address.getBooth()!=null){
								Booth booth = boothDAO.get(address.getBooth().getBoothId());
							
								if(booth.getPartNo().length()!=0){
									location.append(" BOOTH -"+booth.getPartNo());
								}
							}
							if(location.length()!=0){
								voterVO.setLocalArea(location.toString());
							}
							else{
								voterVO.setLocalArea(name);
							}
						}
						else
							voterVO.setLocalArea(name);
					
				}
				//Influencing people voter Details (VoterIds avilable) 
				Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
				if(params.getVoter() != null)
				{
				  List<Long> voterIdsList = new ArrayList<Long>(0);
				  
				  voterIdsList.add(params.getVoter().getVoterId());
					List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIdsList, userId);
					if(list != null && list.size() > 0)
					 for(Object[] obj:list)
				        mobileNosMap.put((Long)obj[0], obj[1]!= null?obj[1].toString():"");
				}
				
				if(params.getVoter() != null)
				{
					voterVO.setVoterIds(params.getVoter().getVoterId());
					voterVO.setVoterId((++count)+"");
					voterVO.setFirstName(params.getVoter().getName());
					voterVO.setAge(params.getVoter().getAge());
					voterVO.setGender(params.getVoter().getGender());
					voterVO.setHouseNo("# "+params.getVoter().getHouseNo());
					voterVO.setRelativeFirstName(params.getVoter().getRelativeName());
					voterVO.setRelationshipType(params.getVoter().getRelationshipType());
					voterVO.setVoterIDCardNo(params.getVoter().getVoterIDCardNo());
					if(params.getParty() != null){
						  voterVO.setPartyName(params.getParty().getShortName());
					}else{
						  voterVO.setPartyName("");
					}
					if(mobileNosMap.get(params.getVoter().getVoterId()) != null)
					 voterVO.setMobileNo(mobileNosMap.get(params.getVoter().getVoterId()));
					
					if(voterVO.getMobileNo() == null || voterVO.getMobileNo().length()==0){
						voterVO.setMobileNo(params.getPhoneNo()!=null?params.getPhoneNo():"");
					}
					
						
					
						StringBuilder location=new StringBuilder();
						//voterVO.setLocalArea(name);
						UserAddress address=userAddressDAO.get(params.getUserAddress().getUserAddressId());
						if(address!=null){
							if(type.equalsIgnoreCase("constituency")){
								if(address.getTehsil()!=null){
									location.append(address.getTehsil().getTehsilName()!=null?address.getTehsil().getTehsilName()+"(Mandal) ":"");
								}
							}
							
							if(!type.equalsIgnoreCase("booth")){
								if(address.getHamlet()!=null){
									location.append(address.getHamlet().getHamletName()+"(Hamlet) ");
								}
							}
														
							if(address.getBooth()!=null){
								Booth booth = boothDAO.get(address.getBooth().getBoothId());
							
								if(booth.getPartNo().length()!=0){
									location.append(" BOOTH -"+booth.getPartNo());
								}
							}
							if(location.length()!=0){
								voterVO.setLocalArea(location.toString());
							}
							else{
								voterVO.setLocalArea(name);
							}
							
							
						}
						else
							voterVO.setLocalArea(name);
					
						
					}
				   // voterVO.setCast();
				 	String infScope = params.getInfluencingScope();
					String infScopeValue = params.getInfluencingScopeValue();
					voterVO.setInfluencingRange(params.getInfluencingScope());
					voterVO.setInfluencingRegion(getRegionNameBasedOnScope(infScope,infScopeValue));
					influencingMap.put(voterVO.getVoterIds(), voterVO);
			        voters.add(voterVO);  
				}
			List<Long> voterids = new ArrayList<Long>(influencingMap.keySet());
			
			List<Object[]> castesList = userVoterDetailsDAO.getcasteForVoter(voterids,userId);
			if(castesList != null && castesList.size() > 0)
			{
				for (Object[] parms : castesList) {
					VoterVO voterVO1 = influencingMap.get((Long)parms[1]);
					voterVO1.setCast(parms[0].toString());
				}
			}
			
			
			if(voters != null)
				if(voters.size() > 0)
					voters.get(0).setTotalVoters(totalCount);
		}
		return voters;	
	
}

public String getInfluencingPeopleCasteCategory(String id){
	if(id == null){
		return "";
	}else if("1".equals(id)){
		return "ST";
	}else if("2".equals(id)){
		return "SC";
	}else if("3".equals(id)){
		return "BC";
	}else if("4".equals(id)){
		return "Minority";
	}else if("5".equals(id)){
		return "General";
	}else if("6".equals(id)){
		return "N/A";
	}else{
		return "";
	}
}

public String getRegionNameBasedOnScope(String infScope,String regionId){
	
	if(infScope.equalsIgnoreCase(IConstants.STATE)){
		State state = stateDAO.get(new Long(regionId));
		return state.getStateName();
	}else if(infScope.equalsIgnoreCase(IConstants.DISTRICT)){
		District district = districtDAO.get(new Long(regionId));
		return district.getDistrictName();
	}else if(infScope.equalsIgnoreCase(IConstants.CONSTITUENCY)){
		Constituency constituency = constituencyDAO.get(new Long(regionId));
		return constituency.getName();
	}else if(infScope.equalsIgnoreCase("MUNCIPALITY/CORPORATION") || infScope.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION)){
		LocalElectionBody localBody = localElectionBodyDAO.get(new Long(regionId));
		String localBodyName = localBody.getName() + " (" + localBody.getElectionType().getElectionType() + " )";
		return localBodyName;
	}else if(infScope.equalsIgnoreCase(IConstants.MANDAL) || infScope.equalsIgnoreCase(IConstants.TEHSIL)){
		if(regionId.length() !=0 && regionId != null)
		{
			Tehsil tehsil = tehsilDAO.get(new Long(regionId));
			return tehsil.getTehsilName();
		}
	}else if(infScope.equalsIgnoreCase(IConstants.VILLAGE) || infScope.equalsIgnoreCase(IConstants.HAMLET)){
		Hamlet hamlet = hamletDAO.get(new Long(regionId));
		return hamlet.getHamletName();
	}else if(infScope.equalsIgnoreCase(IConstants.WARD)){
		Constituency constituency = constituencyDAO.get(new Long(regionId));
		return constituency.getName();
	}else if(infScope.equalsIgnoreCase(IConstants.BOOTH)){
		Booth booth = boothDAO.get(new Long(regionId));
		return booth.getPartNo().toString()+" Booth";
	}
 return null;
}

public List<VoterVO> getCadrePeopleDetails(Long userId,List<Long> locationValues,String type,Integer startIndex,Integer maxRecords,String name,String columnName, String order)
{
	List<VoterVO> voters = new ArrayList<VoterVO>();
	List<Voter> votersList = new ArrayList<Voter>();;
	Long totalCount = 0L;
	List<Object[]> cadreDetails = null;
	Map<Long,VoterVO> cadreMap = new HashMap<Long, VoterVO>();
	
	if(columnName.equalsIgnoreCase("relativeFirstName"))
	{
		columnName = "relativename";
	}
	else if(columnName.equalsIgnoreCase("mobileNo"))
	{
		columnName = "mobileNo";
	}
	else if(columnName.equalsIgnoreCase("influencingRange"))
	{
		columnName = "influencingScope";
	}
	else if(columnName.equalsIgnoreCase("firstName"))
	{
		columnName = "firstname";
	}
	
	if(locationValues != null)
		
		//cadreDetails =  cadreDAO.getCadreVoterIDs(userId,locationValues,type,startIndex, maxRecords,columnName,order);
		cadreDetails =  tdpCadreDAO.getTdpCadreVoterIDs(locationValues,type,startIndex, maxRecords,columnName,order,2014l);
		Long count = new Long(startIndex);
		if(cadreDetails != null)
		{
			Long influencyDetailsCount = tdpCadreDAO.getTdpCadreCountInALocation(locationValues,type,2014l);
			totalCount =influencyDetailsCount;
			//Cadre voter Details (VoterIds not avilable) 
			for(Object[] params : cadreDetails)
				{
				//Cadre voter Details Count
				//Long influencyDetailsCount = cadreDAO.getCadreCountInALocation(userId,locationValues,type);
				
				VoterVO voterVO = new VoterVO();
				if(params[4] == null)//voter is null
				{
					voterVO.setVoterId((++count)+"");
					voterVO.setFirstName(params[0] != null ? params[0].toString() : ""+" "+params[1] != null ? params[1].toString():"");
					voterVO.setGender(params[2] != null ? params[2].toString() : "");
					voterVO.setMobileNo(params[3]!=null ? params[3].toString():" ");
					if(params[5]!=null)
					voterVO.setCast(params[5].toString());
					
						StringBuilder location=new StringBuilder();
						//voterVO.setLocalArea(name);
						UserAddress address=userAddressDAO.get((Long)params[6]);
						if(address!=null){
							if(type.equalsIgnoreCase("constituency")){
								if(address.getTehsil()!=null){
									location.append(address.getTehsil().getTehsilName()!=null?address.getTehsil().getTehsilName()+"(Mandal) ":"");
								}
							}
							
							if(!type.equalsIgnoreCase("booth")){
								if(address.getHamlet()!=null){
									location.append(address.getHamlet().getHamletName()+"(Hamlet) ");
								}
							}
							
							if(address.getBooth()!=null){
								Booth booth = boothDAO.get(address.getBooth().getBoothId());
							
								if(booth.getPartNo().length()!=0){
									location.append(" BOOTH -"+booth.getPartNo());
								}
							}
							if(location.length()!=0){
								voterVO.setLocalArea(location.toString());
							}
							else{
								voterVO.setLocalArea(name);
							}
						}
						else
							voterVO.setLocalArea(name);
						
					
						
				}
				
				
				if(params[4] != null)
				{
					Voter voter = voterDAO.get((Long)params[4]);
					voterVO.setVoterIds((Long)params[4]);
					voterVO.setVoterId((++count)+"");
					voterVO.setFirstName(voter.getName());
					voterVO.setAge(voter.getAge());
					voterVO.setGender(voter.getGender());
					voterVO.setHouseNo("# "+voter.getHouseNo());
					voterVO.setRelativeFirstName(voter.getRelativeName());
					voterVO.setRelationshipType(voter.getRelationshipType());
					voterVO.setVoterIDCardNo(voter.getVoterIDCardNo());
					voterVO.setMobileNo(params[3]!=null?params[3].toString():"");
				
						StringBuilder location=new StringBuilder();
						//voterVO.setLocalArea(name);
						UserAddress address=userAddressDAO.get((Long)params[6]);
						if(address!=null){
							if(type.equalsIgnoreCase("constituency")){
								if(address.getTehsil()!=null){
									location.append(address.getTehsil().getTehsilName()!=null?address.getTehsil().getTehsilName()+" (Mandal)":"");
								}
							}
							
							if(!type.equalsIgnoreCase("booth")){
								if(address.getHamlet()!=null){
									location.append(address.getHamlet().getHamletName()+" (Hamlet)");
								}
							}
							
							if(address.getBooth()!=null){
								Booth booth = boothDAO.get(address.getBooth().getBoothId());
							
								if(booth.getPartNo().length()!=0){
									location.append(" BOOTH -"+booth.getPartNo());
								}
							}
							if(location.length()!=0){
								voterVO.setLocalArea(location.toString());
							}
							else{
								voterVO.setLocalArea(name);
							}
						}
						else
							voterVO.setLocalArea(name);
					
							
					}
					cadreMap.put(voterVO.getVoterIds(), voterVO);
			        voters.add(voterVO);
			        System.out.println(new Date());
				}
			
			//Influencing people voter Details (VoterIds avilable)
			
			/*Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
			List<Long> voterIds = new ArrayList<Long>(cadreMap.keySet());
			  List<Long> voterIdsList = new ArrayList<Long>(0);
			
				List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIds, userId);
				if(list != null && list.size() > 0)
				 for(Object[] obj:list)
				 {
					 VoterVO voterVO1 = cadreMap.get((Long)obj[0]);
					 voterVO1.setMobileNo(obj[1] != null ? obj[1].toString() : "");
				 }*/
			
			
			/*List<Object[]> castesList = userVoterDetailsDAO.getcasteForVoter(voterIds,userId);
			if(castesList != null && castesList.size() > 0)
			{
				for (Object[] parms : castesList) {
					VoterVO voterVO1 = cadreMap.get((Long)parms[1]);
					voterVO1.setCast(parms[0].toString());
				}
			}*/
			if(voters != null)
				if(voters.size() > 0)
					voters.get(0).setTotalVoters(totalCount);
		}
		return voters;	
	
}

public List<VoterVO> getPoliticianDetails(List<Long> locationValues,String type,Integer startIndex,Integer maxRecords,String name,Long publicationId,String columnName,String order,Long userId)
{
	List<VoterVO> voters = new ArrayList<VoterVO>();
	List<Voter> votersList = new ArrayList<Voter>();;
	Long totalCount = 0L;
	Long Id = locationValues.get(0);
	if(columnName.equalsIgnoreCase("relativeFirstName"))
	{
		columnName = "relativeName";
	}
	else if(columnName.equalsIgnoreCase("mobileNo"))
	{
		columnName = "mobileNo";
	}
	else if(columnName.equalsIgnoreCase("influencingRange"))
	{
		columnName = "influencingScope";
	}
	List<Object[]> politicianDetails =new ArrayList<Object[]>(0);
	if(locationValues != null)
		politicianDetails =  boothPublicationVoterDAO.getPoliticianDetails(locationValues,publicationId,type,startIndex, maxRecords,columnName,order);
		Long count = new Long(startIndex);
		if(type.equalsIgnoreCase("booth"))
			name ="Booth - " + boothDAO.get(Id).getPartNo();
		
		Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
		if(politicianDetails != null && politicianDetails.size() > 0)
		{
		 List<Long> voterIdsList = new ArrayList<Long>(0);
		  for(Object[] params:politicianDetails)
			  voterIdsList.add((Long)params[1]);
		  
		  List<Object[]> mobileNosList = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIdsList, userId);
		  if(mobileNosList != null && mobileNosList.size() > 0)
			for(Object[] params:mobileNosList)
				mobileNosMap.put((Long)params[0], params[1]!= null?params[1].toString():"N/A");
		}
		
		if(politicianDetails != null)
		{
			
			for(Object[] params : politicianDetails)
				{
				List<Long> politicianCount = boothPublicationVoterDAO.getCandidateCount(locationValues,publicationId,type);
				totalCount =new Long(politicianCount.get(0));
				VoterVO voterVO = new VoterVO();
				//Politician Details  
				
				
				if(params != null)
				{
					voterVO.setVoterIds((Long)params[1]);
					voterVO.setVoterId((++count)+"");
					voterVO.setFirstName(params[0]!=null ? params[0].toString() : " ");
					voterVO.setAge((Long) params[5]);
					voterVO.setGender(params[2]!=null ? params[2].toString() : " ");
					voterVO.setHouseNo("# "+params[6]!=null ? params[6].toString() : " ");
					voterVO.setRelativeFirstName(params[4]!=null ? params[4].toString() : " ");
					voterVO.setRelationshipType(params[7]!=null ? params[7].toString() : " ");
					//voterVO.setCast(params[9]!=null ? params[9].toString() : " ");
					//voterVO.setCastCatagery(params[10]!=null ? params[10].toString() : " ");
					voterVO.setVoterIDCardNo(params[8]!=null ? params[8].toString() : " ");
					if(mobileNosMap.get((Long)params[1]) != null)
					 voterVO.setMobileNo(mobileNosMap.get((Long)params[1]));
					else
					 voterVO.setMobileNo("N/A");
					
					voterVO.setCandidateId((Long) params[9]);
						voterVO.setLocalArea(name);	
					voters.add(voterVO);
				}
			if(voters != null)
				if(voters.size() > 0)
					voters.get(0).setTotalVoters(totalCount);
		}
  }
		return voters;



}

		public void getCadreInfluencingPeopleCandidateInfo(List<Long> voterIdsList,Long userId,Map<Long,VoterHouseInfoVO> votersMap){
		  try{	
			VoterHouseInfoVO voterHouseInfoVO = null;
			// checking for influencing people
			List<Long> influencingPeopleList = influencingPeopleDAO.findInfluencingPeopleDetails(voterIdsList,userId);
			if(influencingPeopleList != null && influencingPeopleList.size() > 0)
			{
				for (Long influencingPeople : influencingPeopleList) {
					voterHouseInfoVO = votersMap.get(influencingPeople);
					if(voterHouseInfoVO != null)
					{
						voterHouseInfoVO.setIsInfluencePerson(true);
					}
				}
			}
			// checking for cadre people
			List<Long> cadrePeopleList = cadreDAO.findCadrePeopleDetails(voterIdsList,userId);
			if(cadrePeopleList != null && cadrePeopleList.size() > 0)
			{
				for (Long cadrePeople : cadrePeopleList) {
					voterHouseInfoVO = votersMap.get(cadrePeople);
					if(voterHouseInfoVO != null)
					{
						voterHouseInfoVO.setIsCadrePerson(true);
					}
				}
			}
			// checking for politician
			List<Long> candidatePeopleList = candidateDAO.findCandidatePeopleDetails(voterIdsList);
			if(candidatePeopleList != null && candidatePeopleList.size() > 0)
			{
				for (Long candidatePeople : candidatePeopleList) {
					voterHouseInfoVO = votersMap.get(candidatePeople);
					if(voterHouseInfoVO != null)
					{
						voterHouseInfoVO.setIsPoliticion(true);
					}
				}
			}
		  }catch(Exception e){
			  log.error("Exception rised in getCadreInfluencingPeopleCandidateInfo", e);
		  }
		}
       
		public VotersInfoForMandalVO getVotersCountDetailsForHamlet(Long userId , String type,Long id,Long publicationDateId,String reqType)
		{
			try{
			List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountForHamlet(id,userId,publicationDateId);
			
			if((!votersCountList.isEmpty() && votersCountList.get(0)[1] != null) || reqType.equalsIgnoreCase("sub")){
			   return populateDataToVotersInfoForMandalVO(votersCountList,id,hamletDAO.get(id).getHamletName(),"Hamlet");
			}else{
				   VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
				   votersInfoForMandalVO.setDatapresent(false);
				   return votersInfoForMandalVO;
			   }
			}catch (Exception e) {
				log.error("Exception Occured in getVotersCountDetailsForHamlet()",e);
				return null;
			}
		}
		
/*		public ImportantFamiliesInfoVo getImportantFamaliesDetailsForHamlet(Long userId,String type,Long id,Long publicationDateId,Long constituencyId)
		{
			try
			{	
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImportantFamiliesForHamlet(
						userId,type, id, publicationDateId, "main", constituencyId);
				
			        importantFamiliesInfoVo.setDataPresent(true);
				     return importantFamiliesInfoVo;
				
			}
			catch(Exception e)
			{	
				log.error("Exception rised in getImportantFamiliesInfo method : ",e);
				e.printStackTrace();
				return null;
			}
		}*/
		
    public void getImpFamilesInfoForLocality(Long userId ,Long hamletId,Long id,Long publicationDateId,ImportantFamiliesInfoVo importantFamiliesInfoVo,String queryToexe,String exeType,List<Long> ids,Long constituencyId){
			
	      
    	String query = "";
	        Long[] totalFamilies = getFamiliesCountForLocality(userId,hamletId,id,publicationDateId,null,queryToexe,exeType,ids,constituencyId);
			if(totalFamilies != null){
	          importantFamiliesInfoVo.setTotalFamalies(totalFamilies[0]);
			}else{
				importantFamiliesInfoVo.setDataPresent(false);
				return;
			}
		     query = " having count(model.voter.voterId) <= 3 ";
		     Long[] count = getFamiliesCountForLocality(userId,hamletId,id,publicationDateId,query,queryToexe,exeType,ids,constituencyId);
		     importantFamiliesInfoVo.setBelow3(count[0]);
		     importantFamiliesInfoVo.setBelow3Popul(count[1]);
		     
		     query = " having count(model.voter.voterId) > 3 and count(model.voter.voterId) <= 6 ";
		     count = getFamiliesCountForLocality(userId,hamletId,id,publicationDateId,query,queryToexe,exeType,ids,constituencyId);
		     importantFamiliesInfoVo.setBetwn4to6(count[0]);
		     importantFamiliesInfoVo.setBetwn4to6Popul(count[1]);
		    
		     query = " having count(model.voter.voterId) > 6 and count(model.voter.voterId) <= 10 ";
		     count = getFamiliesCountForLocality(userId,hamletId,id,publicationDateId,query,queryToexe,exeType,ids,constituencyId);
		     importantFamiliesInfoVo.setBetwn7to10(count[0]);
		     importantFamiliesInfoVo.setBetwn7to10Popul(count[1]);
		    
		     query = " having count(model.voter.voterId) > 10 ";
		     count = getFamiliesCountForLocality(userId , hamletId,id,publicationDateId,query,queryToexe,exeType,ids,constituencyId);
		     importantFamiliesInfoVo.setAbove10(count[0]);
		     importantFamiliesInfoVo.setAbove10Popul(count[1]);
		     
		     if(importantFamiliesInfoVo.getTotalVoters() > 0)
		       calculatePercentage(importantFamiliesInfoVo);
		     
		}
		
		public void getImpFamilesInfoForHamlet(Long userId , String type,Long id,Long publicationDateId,ImportantFamiliesInfoVo importantFamiliesInfoVo,String queryToexe,String exeType,List<Long> ids,Long constituencyId)
		{
			try{
	        String query = "";
	        Long[] totalFamilies = getFamiliesCountForHamlet(userId,type,id,publicationDateId,null,queryToexe,exeType,ids,constituencyId);
			if(totalFamilies != null){
	          importantFamiliesInfoVo.setTotalFamalies(totalFamilies[0]);
			}else{
				importantFamiliesInfoVo.setDataPresent(false);
				return;
			}
		     query = " having count(model.voter.voterId) <= 3 ";
		     Long[] count = getFamiliesCountForHamlet(userId,type,id,publicationDateId,query,queryToexe,exeType,ids,constituencyId);
		     importantFamiliesInfoVo.setBelow3(count[0]);
		     importantFamiliesInfoVo.setBelow3Popul(count[1]);
		     
		     query = " having count(model.voter.voterId) > 3 and count(model.voter.voterId) <= 6 ";
		     count = getFamiliesCountForHamlet(userId,type,id,publicationDateId,query,queryToexe,exeType,ids,constituencyId);
		     importantFamiliesInfoVo.setBetwn4to6(count[0]);
		     importantFamiliesInfoVo.setBetwn4to6Popul(count[1]);
		    
		     query = " having count(model.voter.voterId) > 6 and count(model.voter.voterId) <= 10 ";
		     count = getFamiliesCountForHamlet(userId,type,id,publicationDateId,query,queryToexe,exeType,ids,constituencyId);
		     importantFamiliesInfoVo.setBetwn7to10(count[0]);
		     importantFamiliesInfoVo.setBetwn7to10Popul(count[1]);
		    
		     query = " having count(model.voter.voterId) > 10 ";
		     count = getFamiliesCountForHamlet(userId , type,id,publicationDateId,query,queryToexe,exeType,ids,constituencyId);
		     importantFamiliesInfoVo.setAbove10(count[0]);
		     importantFamiliesInfoVo.setAbove10Popul(count[1]);
		     
		     if(importantFamiliesInfoVo.getTotalVoters() > 0)
		       calculatePercentage(importantFamiliesInfoVo);
			}catch(Exception e)
			{
				log.error("Exception Occured - ",e);
			}
		     
		}
		
		public Long[] getFamiliesCountForHamlet(Long userId , String type,Long id,Long publicationDateId,String query,String queryToexe,String exeType,List<Long> ids,Long constituencyId)
		{
			Long[] count = {0l,0l};
			try{
			List<Object[]> totalFamiliesList = new ArrayList<Object[]>();
				//totalFamiliesList = boothPublicationVoterDAO.getImpFamilesForHamlet(userId,id,publicationDateId,query);
			List<Long> voterIds = boothPublicationVoterDAO.getVotersInHamletForUser(userId,id);
			if(voterIds!= null && voterIds.size() >0){
				
				if(voterIds.size() > 1000)
				{
					int fromIndex = 0;
					int toIndex = 1000;
					while(fromIndex <= toIndex)
					{
						List<Object[]> tFList = boothPublicationVoterDAO.getFamilyDetailsForHamlet(userId,voterIds.subList(fromIndex,toIndex),publicationDateId,query);
						totalFamiliesList.addAll(tFList);
						fromIndex += 1000;
						toIndex += 1000;
						if(toIndex >= voterIds.size())
							toIndex = voterIds.size();
					}
				}
				else
				{
					totalFamiliesList = boothPublicationVoterDAO.getFamilyDetailsForHamlet(userId,voterIds,publicationDateId,query);
				}
			}
			if(!totalFamiliesList.isEmpty() && totalFamiliesList.get(0)[1] != null){
		    	count[0] = new Long(totalFamiliesList.size());
		       if(query != null){
		    	Long totalVoters = 0l;
		    	for(Object[] family : totalFamiliesList){
		    		if(family[0] != null){
		    			totalVoters = totalVoters+(Long)family[0];
		    		}
		    	 }
		    	count[1] = totalVoters;
		       }
		    }
			else if(query == null && exeType.equalsIgnoreCase("main")){
				return null;
			}
		    return count;
			}catch (Exception e) {
				log.error("Exception ocuured in getFamiliesCountForHamlet() Method ",e);
				return null;
			}
		}
		
    public Long[] getFamiliesCountForLocality(Long userId ,Long hamletId,Long id,Long publicationDateId,String query,String queryToexe,String exeType,List<Long> ids,Long constituencyId){
			
			Long[] count = {0l,0l};
			List<Object[]> totalFamiliesList = new ArrayList<Object[]>();
				//totalFamiliesList = boothPublicationVoterDAO.getImpFamilesForHamlet(userId,id,publicationDateId,query);
			List<Long> voterIds = userVoterDetailsDAO.getVoterIdsBasedOnHamletAndLocality(hamletId, id,userId,queryToexe);
		//	  List<Long> voterIds  =(List<Long>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(locationId, userId ,locationType);		
			if(voterIds!= null && voterIds.size() >0){
			totalFamiliesList = boothPublicationVoterDAO.getFamilyDetailsForHamlet(userId,voterIds,publicationDateId,query);
			}
			if(!totalFamiliesList.isEmpty() && totalFamiliesList.get(0)[1] != null){
		    	count[0] = new Long(totalFamiliesList.size());
		       if(query != null){
		    	Long totalVoters = 0l;
		    	for(Object[] family : totalFamiliesList){
		    		if(family[0] != null){
		    			totalVoters = totalVoters+(Long)family[0];
		    		}
		    	 }
		    	count[1] = totalVoters;
		       }
		    }
			else if(query == null && exeType.equalsIgnoreCase("main")){
				return null;
			}
		    return count;
		}
		
		public ImportantFamiliesInfoVo getImportantFamaliesDetailsForPanchayatByHamlet(Long userId,String type,Long id,Long publicationDateId,Long constituencyId)
		{
			try
			{	
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImportantFamiliesForPanchayt(
						userId,type, id, publicationDateId, "main", constituencyId);
				
			        importantFamiliesInfoVo.setDataPresent(true);
				     return importantFamiliesInfoVo;
				
			}
			catch(Exception e)
			{	
				log.error("Exception rised in getImportantFamiliesInfo method : ",e);
				e.printStackTrace();
				return null;
			}
		}
		
		
		public ImportantFamiliesInfoVo getImportantFamiliesForPanchayt(Long userId , String type,Long id,Long publicationDateId,String exeType,Long constituencyId){
			
			ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
			importantFamiliesInfoVo.setType("Panchayat");
			importantFamiliesInfoVo.setName(panchayatDAO.get(id).getPanchayatName());
			
			//importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCountForHamlet(userId,id,publicationDateId,"hamlet"));
			
			
			VotersInfoForMandalVO votersInfoForHamlet = getVotersCountForPanchayatByHamlet(userId,type, id, publicationDateId, exeType,constituencyId);
			importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForHamlet.getTotalMaleVoters());
			importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForHamlet.getTotalFemaleVoters());
			importantFamiliesInfoVo.setUnKnowVoters(votersInfoForHamlet.getUnKnowVoters());
			
			getImpFamilesForPanchayatByHamlet(userId,id,publicationDateId,importantFamiliesInfoVo);
			List<Long> hamlets = userVoterDetailsDAO.getUserHamletsByPanchayatId(userId ,id );
			
			for(Long  hamletId:hamlets)			
				 importantFamiliesInfoVo.getSubList().add(getImportantFamiliesDetailsForHamlet(userId , "hamlet",hamletId,publicationDateId,"sub",constituencyId));
			getImpFamilesForPanchayatByHamlet(userId,id,publicationDateId,importantFamiliesInfoVo);
			 return importantFamiliesInfoVo;
		}
		
		public VotersInfoForMandalVO getVotersCountForPanchayatByHamlet(Long userId,String type,Long id,Long publicationDateId,String reqType,Long constituencyId){
			
			List<Long> hamlets = userVoterDetailsDAO.getUserHamletsByPanchayatId(userId ,id );
			
			List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletIds(userId , hamlets);
			
			List<Object[]> votersCountList = boothPublicationVoterDAO.getAllVoterDetailsByPublicationAndVoterId(publicationDateId,voterIds);
			
			//List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountByPublicationIdForPanchayatByHamlets(userId,type,hamlets,publicationDateId,constituencyId);
			
			if((!votersCountList.isEmpty() && votersCountList.get(0)[1] != null) || reqType.equalsIgnoreCase("sub")){
			   return populateDataToVotersInfoForMandalVO(votersCountList,id,panchayatDAO.get(id).getPanchayatName(),"Panchayat");
			}else{
				   VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
				   votersInfoForMandalVO.setDatapresent(false);
				   return votersInfoForMandalVO;
			   }
		}
		
		
		public ImportantFamiliesInfoVo getImportantFamiliesDetailsForHamlet(Long userId , String type,Long id,Long publicationDateId,String exeType,Long constituencyId)
		{
			ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
			try{
			importantFamiliesInfoVo.setType("Hamlet");
			//importantFamiliesInfoVo.setName("booth-"+boothDAO.get(id).getPartNo());
			importantFamiliesInfoVo.setName(hamletDAO.get(id).getHamletName());
			importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCountForHamlet(userId , id,publicationDateId,"hamlet"));
			
			
			VotersInfoForMandalVO votersInfoForBooth = getVotersCountDetailsForHamlet(userId , type, id, publicationDateId, exeType);
			importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForBooth.getTotalMaleVoters());
			importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForBooth.getTotalFemaleVoters());
			importantFamiliesInfoVo.setUnKnowVoters(votersInfoForBooth.getUnKnowVoters());
			
			getImpFamilesInfoForHamlet(userId,type,id,publicationDateId,importantFamiliesInfoVo,"",exeType,null,constituencyId);
			 return importantFamiliesInfoVo;
			}catch (Exception e) {
				log.error("Exception Occured in getImportantFamiliesDetailsForHamlet() ",e);
				return importantFamiliesInfoVo;
			}
		}
		
		@SuppressWarnings("unchecked")
		public List<VoterHouseInfoVO> getVoterHouseInfoDetailsForPanchayatByHamlet(Long userId,Long id, Long publicationDateId,String checkedEle)
		{
			List voters = null;
			List<Long> voterIdsList = new ArrayList<Long>(0);
			List<SelectOptionVO> casteList = new ArrayList<SelectOptionVO>();
			SelectOptionVO caste = null;
			if(checkedEle.equalsIgnoreCase("panchayat"))
			{
				List<Object[]> hamletDetails = getHamletsOfAPanchayat(id);
				List<Long> hamletIds = new ArrayList<Long>();
				
				for(Object[] obj:hamletDetails)
					hamletIds.add((Long)obj[0]);
				voters = boothPublicationVoterDAO.findFamiliesVotersInfoForPanchayatByHamlet(hamletIds,publicationDateId);
			}
			if(checkedEle.equalsIgnoreCase("pollingstation"))
			{
				voters = boothPublicationVoterDAO.findFamiliesVotersInfoForBooth(id,publicationDateId);
			}
			Map<Long,Map<String, List<VoterVO>>> boothMap = new HashMap<Long,Map<String, List<VoterVO>>>();
			Map<String, List<VoterVO>> voterByHouseNoMap = null;
			List<VoterHouseInfoVO> voterHouseInfoVOs = new ArrayList<VoterHouseInfoVO>();
			VoterHouseInfoVO voterHouseInfoVO = null;
			List<VoterVO> voterVOs = null;
			VoterVO voterVO = null;
			String houseNo = "";
			
			if(voters != null && voters.size() > 0)
			for(Object[] voter : (List<Object[]>)voters)
			voterIdsList.add((Long)voter[5]);
			
			if(voterIdsList.size() > 0)
			{
				List<Long> subList = null;
				int startIndex = 0;
				int nextIndex = 1000;
				for(;;)
				{
					try{
					if(startIndex >= voterIdsList.size()-1)
						break;
					if(nextIndex >= voterIdsList.size())
						nextIndex = voterIdsList.size() - 1;
					
					subList = voterIdsList.subList(startIndex,nextIndex);
					
				    List<Object[]> votercastList = userVoterDetailsDAO.getCasteByVoterId(userId,subList);
				    if(votercastList!= null)
			        for(Object[] params : votercastList)
					{
			        	if(params[1].toString() != null && params[1].toString()!= "")
						caste = new SelectOptionVO((Long)params[0],params[1].toString());	
						casteList.add(caste);
					}
				    startIndex = nextIndex;
				    nextIndex = nextIndex + 1000;
					}catch (Exception e) {
						log.error(e);
					}
				}
			}
			if(voters != null)
			for(Object[] voter : (List<Object[]>)voters){
				houseNo = voter[1].toString();
				voterVO = new VoterVO();
				voterVO.setFirstName(voter[0].toString());
				voterVO.setAge((Long)voter[2]);
				voterVO.setCast(voter[3] != null ? voter[3].toString() : "");
				voterVO.setBoothNo((Long)voter[4]);
				voterVO.setVoterId(voter[5].toString());
				voterVO.setGender(voter[6].toString());
				voterVO.setAge(voter[7] != null ? (Long)voter[7]:18l);
				voterVO.setBoothName(voter[8].toString());
				voterVO.setCast(getCasteNameByVoterID(casteList,(Long)voter[5]));
				voterByHouseNoMap = boothMap.get((Long)voter[4]);
				if( voterByHouseNoMap == null){
					voterByHouseNoMap = new HashMap<String, List<VoterVO>>();
					boothMap.put((Long)voter[4], voterByHouseNoMap);
				}
				voterVOs = voterByHouseNoMap.get(houseNo);
				if(voterVOs ==null){
					voterVOs = new ArrayList<VoterVO>();
					voterByHouseNoMap.put(houseNo, voterVOs);
				}
				voterVOs.add(voterVO);
				//voterByHouseNoMap.put(houseNo, voterVOs);
				
			}
			Set<Long> keys = boothMap.keySet();
			for(Long key:keys){
				voterByHouseNoMap = boothMap.get(key);
			for(Map.Entry<String, List<VoterVO>> entry:voterByHouseNoMap.entrySet()){
				voterHouseInfoVO = new VoterHouseInfoVO();
				voterVOs = entry.getValue();
				if(voterVOs.size() == 0)
					continue;
				Collections.sort(voterVOs,sortData);
				voterHouseInfoVO.setBoothId(voterVOs.get(0).getBoothNo());
				//voterHouseInfoVO.setBoothName("Booth - "+voterVOs.get(0).getBoothName());
				voterHouseInfoVO.setBoothName(voterVOs.get(0).getBoothName());
				voterHouseInfoVO.setHouseNo(entry.getKey());
				voterHouseInfoVO.setNumberOfPeople(voterVOs.size());
				voterHouseInfoVO.setCast(voterVOs.get(voterVOs.size()-1).getCast() != null ? 
						voterVOs.get(voterVOs.size()-1).getCast() : "N/A");
				voterHouseInfoVO.setElderGender(voterVOs.get(voterVOs.size()-1).getGender());
				voterHouseInfoVO.setElderAge(voterVOs.get(voterVOs.size()-1).getAge());
				voterHouseInfoVO.setElder(voterVOs.get(voterVOs.size()-1).getFirstName());
				
				VoterVO younger = null;
				
				younger = voterVOs.get(0);
				
				if(!younger.getGender().equalsIgnoreCase(IConstants.MALE))
				{
					for(int i=1;i<(voterVOs.size()-1);i++)
						if(voterVOs.get(i).getGender().equalsIgnoreCase(IConstants.MALE))
								younger = voterVOs.get(i);
				}
				voterHouseInfoVO.setYounger(younger.getFirstName());
				voterHouseInfoVO.setYoungerAge(younger.getAge());
				voterHouseInfoVO.setYoungerGender(younger.getGender());
				
				voterHouseInfoVOs.add(voterHouseInfoVO);
			}
			}
			
		/*	if(voterHouseInfoVOs.size() > 0)
				voterHouseInfoVOs.get(0).setTotalHousesCount(totalRecords);*/
			return voterHouseInfoVOs;
		}
		
		public void getImpFamilesForPanchayatByHamlet(Long userId,Long id,Long publicationDateId,ImportantFamiliesInfoVo importantFamiliesInfoVo){
			List<Object[]>  impFamilesList = null;
			
			List<Long> hamlets = userVoterDetailsDAO.getUserHamletsByPanchayatId(userId ,id );
			
             //List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletIds(userId , hamlets);
             
			impFamilesList = boothPublicationVoterDAO.getImpFamilesForPanchayatByPublicationIdAndVoters(publicationDateId,userId,"HAMLET",hamlets);
			
			
			
			//	impFamilesList = boothPublicationVoterDAO.getImpFamilesForPanchayatByPublicationIdAndHamlet(userId,id,publicationDateId,null);
				
			
			Long below3 = 0l;
			Long between4To6 = 0l;
			Long between7To10 = 0l;
			Long above10 = 0l;
			Long count = 0l;
			Long above10Count = 0l;
			Long between7T10Count = 0l;
			Long between4To6Count = 0l;
			Long below3Count = 0l;
			for (Object[] impFamiles : impFamilesList) {
				count = (Long) impFamiles[0];	
				if(count.longValue() > 10){
					above10 = above10+1;
					above10Count = count + above10Count;
				}
				else if(count.longValue() < 10 && count.longValue() >= 7){
					between7To10 = between7To10+1;
					between7T10Count = count + between7T10Count;
				}
				else if(count.longValue() < 7 && count.longValue() >=4){
					between4To6 = between4To6 + 1;
					between4To6Count = count + between4To6Count;	
				}
				else{
					below3 = below3 + 1;
					below3Count = count + below3Count;
				}
			}
			
			importantFamiliesInfoVo.setAbove10(above10);
			importantFamiliesInfoVo.setAbove10Popul(above10Count);
			importantFamiliesInfoVo.setBetwn7to10(between7To10);
			importantFamiliesInfoVo.setBetwn7to10Popul(between7T10Count);
			importantFamiliesInfoVo.setBetwn4to6Popul(between4To6Count);
			importantFamiliesInfoVo.setBetwn4to6(between4To6);
			importantFamiliesInfoVo.setBelow3(below3);
			importantFamiliesInfoVo.setBelow3Popul(below3Count);
			importantFamiliesInfoVo.setTotalFamalies(above10+between7To10+between4To6+below3);
			importantFamiliesInfoVo.setTotalVoters(above10Count+between7T10Count+between4To6Count+below3Count);
			calculatePercentage(importantFamiliesInfoVo);
		}
		
		
		public VotersInfoForMandalVO getVotersBasicInfoForPanchayat1(Long userId , Long id,Long publicationDateId,String reqType ,Long constituencyId){
			try{
				VotersInfoForMandalVO votersInfoForMandalVO =new VotersInfoForMandalVO();
				//VotersInfoForMandalVO votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId("Panchayat"), id, publicationDateId, panchayatDAO.get(id).getPanchayatName(), "Panchayat",constituencyId);
				
				if(reqType.equalsIgnoreCase("main")){
					if(votersInfoForMandalVO.isDatapresent())
					{
					  //getVotersCountForMultipleBooths(id,publicationDateId,votersInfoForMandalVO);
					  //calculatePercentage(votersInfoForMandalVO);
						/*List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(id,publicationDateId);
						Map<Long,String> boothIds = new HashMap<Long,String>();
						for (Object[] booth : boothsList){
							boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
						}
						if(boothIds.size() > 0)
							votersInfoForMandalVO.setVotersInfoForMandalVOList(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId(IConstants.BOOTH),boothIds, publicationDateId,"booth"));
				*/	
					
						votersInfoForMandalVO.setDatapresent(true);
					getVoterDetailsForHamletsInPanchayat(userId,id,votersInfoForMandalVO,publicationDateId);
					
					
					}
					if(votersInfoForMandalVO != null && votersInfoForMandalVO.getVotersInfoForMandalVOList() != null && votersInfoForMandalVO.getVotersInfoForMandalVOList().size() > 0)
						votersInfoForMandalVO.setSubLevelExists(true);
					return votersInfoForMandalVO;
				}
				return votersInfoForMandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersBasicInfoForPanchayat() Method, Exception - "+e);
				return null;
			}
		}
		
		public void getVoterDetailsForHamletsInPanchayat(Long userId , Long panchayatId,VotersInfoForMandalVO votersInfoForMandalVO1,Long publicationDateId)
		{
			
			
			List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>();
			List<Long> hamletIds = new ArrayList<Long>();
			
			List<Object[]> hamletsList = panchayatHamletDAO.getHamletsOfAPanchayat(panchayatId);
			
			for(Object[] obj:hamletsList)
			{
				
				VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
				
				List<Object[]> hamletDetails = userVoterDetailsDAO.getVotersCountByGenderForHamlet((Long)obj[0],userId);
				
				Long totalFemaleVoters = 0L;
				Long totalMaleVoters = 0L;
				Long totalUnknownVoters = 0L;
				Long totalVoters = 0L;
				
				
				for(Object[] obj1:hamletDetails){
					
					if(obj1[0].toString().equalsIgnoreCase("M"))
						totalMaleVoters = (Long)obj1[1];
					else if(obj1[0].toString().equalsIgnoreCase("F"))
						totalFemaleVoters = (Long) obj1[1];
					else
						totalUnknownVoters = (Long) obj1[1];
					
//votersInfoForMandalVO.setTotVoters(voterDetails.getTotalVoters() != null ? new BigDecimal(voterDetails.getTotalVoters()):new BigDecimal(0.00));

					
					
					totalVoters = totalMaleVoters + totalFemaleVoters;
					
				}
				
				votersInfoForMandalVO.setTotVoters(new BigDecimal(totalVoters));
				votersInfoForMandalVO.setTotalVoters(totalVoters.toString()) ;
				votersInfoForMandalVO.setTotalMaleVoters(totalMaleVoters.toString());
				votersInfoForMandalVO.setTotalFemaleVoters(totalFemaleVoters.toString());
				votersInfoForMandalVO.setTotalVotersPercentage("0.00");
				votersInfoForMandalVO.setUnKnowVoters(totalUnknownVoters.toString());
				votersInfoForMandalVO.setType("Hamlet");
				votersInfoForMandalVO.setName(obj[1].toString());
				
				
				if(totalVoters.longValue() == 0)
					votersInfoForMandalVO.setDatapresent(false);
				else
					votersInfoForMandalVOList.add(votersInfoForMandalVO);
			}
			
			List<Object[]> assignedAndUnAssignedVotersOfUser=boothPublicationVoterDAO.getVotersListInPanchayat(publicationDateId,panchayatId,userId);
			if(assignedAndUnAssignedVotersOfUser != null && assignedAndUnAssignedVotersOfUser.size() > 0)
			   {
				   for(Object[] params : assignedAndUnAssignedVotersOfUser){
					   votersInfoForMandalVO1.setAssignedVotersByUser((Long) params[0]);
					   votersInfoForMandalVO1.setUnassignedVotersByUser((Long) params[1]);
					   
				   }
			   }
			
			
			votersInfoForMandalVO1.setVotersInfoForMandalVOList(votersInfoForMandalVOList);
			calculatePercentage1(votersInfoForMandalVO1);
		}
		
		public void calculatePercentage1(VotersInfoForMandalVO votersInfoForMandalVO)
		{
			try{
				Long totalVoters = 0L;
				for(VotersInfoForMandalVO vo: votersInfoForMandalVO.getVotersInfoForMandalVOList()){
					totalVoters = totalVoters.longValue() + new Long(vo.getTotalVoters());
				}
				
				if(!votersInfoForMandalVO.getVotersInfoForMandalVOList().isEmpty())
				{
					votersInfoForMandalVO.setSubLevelExists(true);
					
					for(VotersInfoForMandalVO vo : votersInfoForMandalVO.getVotersInfoForMandalVOList())
					{ 
					  if(!(totalVoters.longValue() == 0))
					  {
						  Double votes = Double.valueOf(vo.getTotVoters().toString());
						  Double totalVotes = Double.valueOf(totalVoters.toString());
						  vo.setPercent(new BigDecimal(votes*(100.0)/totalVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						  vo.setTotPercent(new BigDecimal(votes*(100.0)/totalVotes).setScale(2, BigDecimal.ROUND_HALF_UP));
					  }
					  else{
						  vo.setPercent("0"); 
						  vo.setTotPercent(new BigDecimal("0.00"));
					  }
				  }
				}
			}catch (Exception e) {
				log.error("Exception Occured in calculatePercentage() Method, Exception is - "+e);
			}
		}      

		public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByHamletId(Long hamletId,Long publicationDateId, String type){
			
			try{
				List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
				List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(hamletId, publicationDateId);
				if(boothsList != null)
				{
					/*boothVotersList = new ArrayList<VotersDetailsVO>();
					for(Object[] params:booths){
						VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
						getDetailsOfVotersHasAgeBetween18To25(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
						getDetailsOfVotersHasAgeBetween26To35(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
						getDetailsOfVotersHasAgeBetween36To45(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
						getDetailsOfVotersHasAgeBetween46To60(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
						getDetaOfVotersHasAgeAbove60(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
						Long totalVoters = 0l;
						votersDetailsVO.setBoothName(params[1].toString());
						if(votersDetailsVO.getTotalVotersFor18To25() != null && votersDetailsVO.getTotalVotersFor26To35()  != null && votersDetailsVO.getTotalVotersFor36To45() != null && votersDetailsVO.getTotalVotersFor46To60() != null && votersDetailsVO.getTotalVotersForAbove60() != null)
						{	 totalVoters = votersDetailsVO.getTotalVotersFor18To25()
							+ votersDetailsVO.getTotalVotersFor26To35()
							+ votersDetailsVO.getTotalVotersFor36To45()
							+ votersDetailsVO.getTotalVotersFor46To60()
							+ votersDetailsVO.getTotalVotersForAbove60();
					
						votersDetailsVO.setTotalVoters(totalVoters);
						}else{
							return null;
						}
						votersDetailsVO.setVotersPercentFor18To25(votersDetailsVO.getTotalVotersFor18To25() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
						votersDetailsVO.setVotersPercentFor26To35(votersDetailsVO.getTotalVotersFor26To35() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
						votersDetailsVO.setVotersPercentFor36To45(votersDetailsVO.getTotalVotersFor36To45() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
						votersDetailsVO.setVotersPercentFor46To60(votersDetailsVO.getTotalVotersFor46To60() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
						votersDetailsVO.setVotersPercentForAbove60(votersDetailsVO.getTotalVotersForAbove60() != 0? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
						boothVotersList.add(votersDetailsVO);
					}*/
					Map<Long,String> boothIds = new HashMap<Long,String>();
					for (Object[] booth : boothsList){
						boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
					}
					if(boothIds.size() > 0)
						boothVotersList = getAllAgesWiseVotersDetails(getReportLevelId(IConstants.BOOTH), boothIds, publicationDateId,"Booth",null);
				
				}
			  return boothVotersList;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getAgewiseVotersDetForBoothsByPanchayatId() Method, Exception - "+e);
				return null;
			}
		}
		public List<VotersDetailsVO> getVotersDetailsByAgewise1(Long hamletId , Long publicationDateId , String type) {		

			List<VotersDetailsVO> constituencyVotersList = new ArrayList<VotersDetailsVO>();
			
		      /*  getDetailsOfVotersHasAgeBetween18And25(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList);		
				getDetailsOfVotersHasAgeBetween26And35(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList);			
				getDetailsOfVotersHasAgeBetween36And45(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList);		
				getDetailsOfVotersHasAgeBetween46And60(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList);       
				getDetailsOfVotersHasAgeAbove60(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList);
	     
				Long totalVoters = 0L;
				for(VotersDetailsVO vtersDetailsVO:constituencyVotersList){				
					totalVoters +=vtersDetailsVO.getTotalVoters();
				}
				
				for(VotersDetailsVO votersDetailsVO:constituencyVotersList){
					if(totalVoters != 0){					
					votersDetailsVO.setTotalVotersPercent((float)votersDetailsVO.getTotalVoters()*100f/totalVoters);
					}
				}
				
			}catch(Exception e){			
				e.printStackTrace();
			}*/
			return constituencyVotersList;
		}
		//18111 family
		public List<VotersDetailsVO> getAgewiseVotersDetailsForHamletByPanchayatId(Long panchayatId,Long publicationDateId,Long userId , String type){
			List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
			List<Object> hamlets = null;
			List<Long> ids = new ArrayList<Long>(); 
			   if(type.equalsIgnoreCase(IConstants.HAMLET)){
				  List<Long> list =  new ArrayList<Long>();
				  list.add(panchayatId);
				    ids = panchayatHamletDAO.getHamletsOfPanchayitis(list);
			 //hamlets =  userVoterDetailsDAO.getHamletsIdsForUserByPanchayat(panchayatId, userId);
			   }
			  
			   else if (type.equalsIgnoreCase(IConstants.CUSTOMWARD)){
				   Long  lId = (Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(panchayatId).get(0);
				    
				    List<Object[]> list1 = userVoterDetailsDAO.getWardsBYLocalElectionBodyId(lId,publicationDateId , userId);
				    
				    for(Object[] obj:list1)
				    	ids.add((Long)obj[0]);
				    	
				    
				 //  List<Object[]> hanletIds =  userVoterDetailsDAO.getWardsBYLocalElectionBodyId(lid,publicationDateId,userId);
			      // hamlets = boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(lid, userId ,type);
				   //hamlets =(List<Object>)  userVoterDetailsDAO.getVoterIdsBYLocalElectionBodyId(lid, publicationDateId ,userId ,type);
			   }
				/*List<?> filter =        userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(publicationDateId,hamlets);
				   
				if(filter == null || filter.size()==0)
					   return new ArrayList<VotersDetailsVO>();*/
				
				
				//List<Object[]> list=    userVoterDetailsDAO.getAgeDataForPanchayatUser(filter);
			   // List<Object[]> list=    userVoterDetailsDAO.getAgeDataForPanchayatUser(filter,userId,type,IConstants.MALE,IConstants.FEMALE,IConstants.AGE18,IConstants.AGE25,IConstants.AGE26,IConstants.AGE35,IConstants.AGE36,IConstants.AGE45,IConstants.AGE46,IConstants.AGE60);
				 List<Object[]> list=    userVoterDetailsDAO.getAgeDataForPanchayatUser(ids,publicationDateId,hamlets,userId,type,IConstants.MALE,IConstants.FEMALE,IConstants.AGE18,IConstants.AGE25,IConstants.AGE23,IConstants.AGE30,IConstants.AGE31,IConstants.AGE45,IConstants.AGE46,IConstants.AGE60);
			    if(list == null || list.size()==0)
				{   
					return boothVotersList;
				}else
				{
					buildHamletWiseYoungerVoterDetails(boothVotersList,ids,publicationDateId,userId,type);
					helperBusinessDelegator(list,boothVotersList);
					//return boothVotersList;	
				}
		/*	
			
			//List<Object[]> booths = boothDAO.getBoothsInAPanchayat(panchayatId, publicationDateId);
			                        
		    List<Object[]> hamlets =  userVoterDetailsDAO.getHamletsIdsForUser(panchayatId, userId);
			List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
			
			//Long [] hamlets =(Long[]) hamlets;
			 if(hamlets ==null ||hamlets.size()==0 )
				return  boothVotersList;
			for(Object[] obj: hamlets) {
				
			List<Long>	voterIds =(List<Long>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId((Long)obj[0], userId);
				VotersDetailsVO voterDetailsVO = new VotersDetailsVO();
				
				
				getDetailsOfVotersHasAgeBetween18And25(null,null,null,null, publicationDateId,voterDetailsVO,IConstants.HAMLET,voterIds);
				getDetailsOfVotersHasAgeBetween26And35(null,null,null,null, publicationDateId,voterDetailsVO,IConstants.HAMLET,voterIds);			
				getDetailsOfVotersHasAgeBetween36And45(null,null,null,null, publicationDateId,voterDetailsVO,IConstants.HAMLET,voterIds);		
				getDetailsOfVotersHasAgeBetween46And60(null,null,null,null, publicationDateId,voterDetailsVO,IConstants.HAMLET,voterIds);       
				getDetailsOfVotersHasAgeAbove60(null,null,null,null, publicationDateId,voterDetailsVO,IConstants.HAMLET,voterIds);
				
				//voterDetailsVO.setBoothName(obj[1].toString());
				voterDetailsVO.setHamletName(obj[1].toString());
				Long totalVoters = 0l;
				  if(voterDetailsVO.getTotalVotersFor18To25() != null)
				   totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor18To25();
				  if(voterDetailsVO.getTotalVotersFor26To35() != null)
				    totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor26To35();
				  if(voterDetailsVO.getTotalVotersFor36To45() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor36To45();
				  if(voterDetailsVO.getTotalVotersFor46To60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor46To60();
				  if(voterDetailsVO.getTotalVotersForAbove60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersForAbove60();
				
				voterDetailsVO.setTotalVoters(totalVoters);
				*/
			//	/*voterDetailsVO.setVotersPercentFor18To25(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters));
			//	voterDetailsVO.setVotersPercentFor26To35(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters));
			//	voterDetailsVO.setVotersPercentFor36To45(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters));
			//	voterDetailsVO.setVotersPercentFor46To60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters));
			//	voterDetailsVO.setVotersPercentForAbove60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters));
			//	*/		
				
				/*
				voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
				boothVotersList.add(voterDetailsVO);
				
				*/
				
			//}
			return boothVotersList;
			
		}
		
		public List<VotersDetailsVO> getAgewiseVotersDetailsForLocalAreaByHamletId(Long hamletId,Long publicationDateId,Long userId ,String type){
			//[];
			//List<Object[]> booths = boothDAO.getBoothsInAPanchayat(panchayatId, publicationDateId);
			                        
		   // List<Object[]> hamlets =  userVoterDetailsDAO.getHamletsIdsForUser(hamletId, userId);
			
			Map m =new HashMap();
			    m.put("hamletLocalArea",IConstants.HAMLET);
			    m.put("customWardLocalArea", IConstants.CUSTOMWARD);
			   if(!m.containsKey(type))
				   return null;
			
			List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
			
			List<?> voterIds= null;//	userVoterDetailsDAO.getVotersIdsByHamletId(hamletId,userId);
			
			/*voterIds  =   boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(hamletId, userId,(String) m.get(type));
			   if(voterIds == null || voterIds.size()==0)
				   return new ArrayList<VotersDetailsVO>();*/
			List<?> filter =        userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(publicationDateId,hamletId, userId,(String) m.get(type));
			if(filter == null || filter.size()==0)
				   return new ArrayList<VotersDetailsVO>();
			List<Object[]> hamlets = new ArrayList<Object[]>();
			int fromIndex = 0;
			  int toIndex = 2000;
			//List<Object[]> hamlets = userVoterDetailsDAO.getLocalityIdsForUser(hamletId, userId,filter);
			if(filter.size() > 2000)
			{
				while(fromIndex <= toIndex)
				  {
					List<Object[]> hamletsList = userVoterDetailsDAO.getLocalityIdsForUser(hamletId, userId,filter.subList(fromIndex, toIndex),IConstants.MALE,IConstants.FEMALE,IConstants.AGE18,IConstants.AGE22,IConstants.AGE23,IConstants.AGE30,IConstants.AGE31,IConstants.AGE45,IConstants.AGE46,IConstants.AGE60);
					if(hamletsList != null)
					{
						hamlets.addAll(hamletsList);
					}
					
					fromIndex += 2000;
					toIndex   += 2000;
					if(toIndex > filter.size())
						toIndex = filter.size();	
				  }
				
			}
			else
			{
				hamlets = userVoterDetailsDAO.getLocalityIdsForUser(hamletId, userId,filter,IConstants.MALE,IConstants.FEMALE,IConstants.AGE18,IConstants.AGE22,IConstants.AGE23,IConstants.AGE30,IConstants.AGE31,IConstants.AGE45,IConstants.AGE46,IConstants.AGE60);
			}
			   
		   
			
			//Long [] hamlets =(Long[]) hamlets;
			 if(hamlets ==null ||hamlets.size()==0 )
				return  boothVotersList;
			for(Object[] obj: hamlets) {
				 int length=obj.length;
				
					VotersDetailsVO voterDetailsVO = new VotersDetailsVO();		
				 //System.out.println(objects[--length]);
				//setting total voters agewise
					
					
					
				 voterDetailsVO.setTotalVotersForAbove60((Long)obj[length-1]);
				 voterDetailsVO.setTotalVotersFor46To60((Long)obj[length-2]);
				 voterDetailsVO.setTotalVotersFor36To45((Long)obj[length-3]);
				 voterDetailsVO.setTotalVotersFor26To35((Long)obj[length-4]);
				 voterDetailsVO.setTotalVotersFor18To25((Long)obj[length-5]);
			
				 //above 60 age realated Data
				 
				 voterDetailsVO.setTotalUnknownVotersForAbove60((Long)obj[length-6]);
				 voterDetailsVO.setTotalMaleVotersForAbove60((Long)obj[length-7]);
				 voterDetailsVO.setTotalFemaleVotersForAbove60((Long)obj[length-8]);
				 
                     //46-50 age realated Data
				 
				 voterDetailsVO.setTotalUnknownVotersFor46To60((Long)obj[length-9]);
				 voterDetailsVO.setTotalMaleVotersFor46To60((Long)obj[length-10]);
				 voterDetailsVO.setTotalFemaleVotersFor46To60((Long)obj[length-11]);
				 
				 
			        //36-45 age realated Data
				 
				 
				 			 voterDetailsVO.setTotalUnknownVotersFor36To45((Long)obj[length-12]);
							 voterDetailsVO.setTotalMaleVotersFor36To45((Long)obj[length-13]);
							 voterDetailsVO.setTotalFemaleVotersFor36To45((Long)obj[length-14]);
				 
							//26-35 age realated Data		 
							 voterDetailsVO.setTotalUnknownVotersFor26To35((Long)obj[length-15]);
							 voterDetailsVO.setTotalMaleVotersFor26To35((Long)obj[length-16]);
							 voterDetailsVO.setTotalFemaleVotersFor26To35((Long)obj[length-17]);
							 
			             //18-22 age realated Data				 
							 voterDetailsVO.setTotalUnknownVotersFor18To25((Long)obj[length-18]);
							 voterDetailsVO.setTotalMaleVotesFor18To25((Long)obj[length-19]);
							 voterDetailsVO.setTotalFemaleVotersFor18To25((Long)obj[length-20]);
							 
							  long maleVoters =0; 
							  long femaleVoters =0; 
							  long totalVotersCase=0;
							  
							 if( voterDetailsVO.getTotalVotersForAbove60() != 0){
								 maleVoters=(long)voterDetailsVO.getTotalMaleVotersForAbove60();
								 femaleVoters=(long)voterDetailsVO.getTotalFemaleVotersForAbove60();
								 totalVotersCase=voterDetailsVO.getTotalVotersForAbove60();
								 voterDetailsVO.setMaleVotersPercentForAbove60(roundTo2DigitsFloatValue((float) (maleVoters*100f/ totalVotersCase )));
								 voterDetailsVO.setFemaleVotersPercentForAbove60(roundTo2DigitsFloatValue((float)(femaleVoters *100f/ totalVotersCase )));
								  }else{
									  voterDetailsVO.setMaleVotersPercentForAbove60("0.00");
									  voterDetailsVO.setFemaleVotersPercentForAbove60("0.00");
								}
				
							 if( voterDetailsVO.getTotalVotersFor46To60() != 0){
								 maleVoters=(long)voterDetailsVO.getTotalMaleVotersFor46To60();
								 femaleVoters=(long)voterDetailsVO.getTotalFemaleVotersFor46To60();
								 totalVotersCase=voterDetailsVO.getTotalVotersFor46To60();
									voterDetailsVO.setMaleVotersPercentFor46To60(roundTo2DigitsFloatValue((float) (maleVoters*100f/ totalVotersCase )));
									voterDetailsVO.setFemaleVotersPercentFor46To60(roundTo2DigitsFloatValue((float)(femaleVoters *100f/ totalVotersCase )));
								  }else{
									voterDetailsVO.setMaleVotersPercentFor46To60("0.00");
									voterDetailsVO.setFemaleVotersPercentFor46To60("0.00");
								}
							 if( voterDetailsVO.getTotalVotersFor36To45() != 0){
								 maleVoters=(long)voterDetailsVO.getTotalMaleVotersFor36To45();
								 femaleVoters=(long)voterDetailsVO.getTotalFemaleVotersFor36To45();
								 totalVotersCase=voterDetailsVO.getTotalVotersFor36To45();
									voterDetailsVO.setMaleVotersPercentFor36To45(roundTo2DigitsFloatValue((float) (maleVoters*100f/ totalVotersCase )));
									voterDetailsVO.setFemaleVotersPercentFor36To45(roundTo2DigitsFloatValue((float)(femaleVoters *100f/ totalVotersCase )));
								  }else{
									voterDetailsVO.setMaleVotersPercentFor36To45("0.00");
									voterDetailsVO.setFemaleVotersPercentFor36To45("0.00");
								}
							 if( voterDetailsVO.getTotalVotersFor26To35() != 0){
								 maleVoters=(long)voterDetailsVO.getTotalMaleVotersFor26To35();
								 femaleVoters=(long)voterDetailsVO.getTotalFemaleVotersFor26To35();
								 totalVotersCase=voterDetailsVO.getTotalVotersFor26To35();
									voterDetailsVO.setMaleVotersPercentFor26To35(roundTo2DigitsFloatValue((float) (maleVoters*100f/ totalVotersCase )));
									voterDetailsVO.setFemaleVotersPercentFor26To35(roundTo2DigitsFloatValue((float)(femaleVoters *100f/ totalVotersCase )));
								  }else{
									voterDetailsVO.setMaleVotersPercentFor26To35("0.00");
									voterDetailsVO.setFemaleVotersPercentFor26To35("0.00");
								}
							 if( voterDetailsVO.getTotalVotersFor18To25() != 0){
								 maleVoters=(long)voterDetailsVO.getTotalMaleVotesFor18To25();
								 femaleVoters=(long)voterDetailsVO.getTotalFemaleVotersFor18To25();
								 totalVotersCase=voterDetailsVO.getTotalVotersFor18To25();
									voterDetailsVO.setMaleVotersPercentFor18To25(roundTo2DigitsFloatValue((float) (maleVoters*100f/ totalVotersCase )));
									voterDetailsVO.setFemaleVotersPercentFor18To25(roundTo2DigitsFloatValue((float)(femaleVoters *100f/ totalVotersCase )));
								  }else{
									voterDetailsVO.setMaleVotersPercentFor18To25("0.00");
									voterDetailsVO.setFemaleVotersPercentFor18To25("0.00");
								}	 
							 
		//	List<Long>	voterIds =(List<Long>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId((Long)obj[0], userId);
				
				
				
			//	getDetailsOfVotersHasAgeBetween18And25(null,null,null,null, publicationDateId,voterDetailsVO,IConstants.HAMLET,voterIds);
			//	getDetailsOfVotersHasAgeBetween26And35(null,null,null,null, publicationDateId,voterDetailsVO,IConstants.HAMLET,voterIds);			
			//	getDetailsOfVotersHasAgeBetween36And45(null,null,null,null, publicationDateId,voterDetailsVO,IConstants.HAMLET,voterIds);		
			//	getDetailsOfVotersHasAgeBetween46And60(null,null,null,null, publicationDateId,voterDetailsVO,IConstants.HAMLET,voterIds);       
			//	getDetailsOfVotersHasAgeAbove60(null,null,null,null, publicationDateId,voterDetailsVO,IConstants.HAMLET,voterIds);
				
				//voterDetailsVO.setBoothName(obj[1].toString());
				// Long totalVotersBetween18To25 = maleVotersBetween18To25
					//		+ femaleVotersBetween18To25 + unKnownVotersBetween18To25;
					
				/*	 votersDetailsVO.setTotalMaleVotesFor18To25(maleVotersBetween18To25);
					 votersDetailsVO.setTotalFemaleVotersFor18To25(femaleVotersBetween18To25);
					 votersDetailsVO.setTotalUnknownVotersFor18To25(unKnownVotersBetween18To25);
					 votersDetailsVO.setTotalVotersFor18To25(totalVotersBetween18To25);*/
					 
					/*  if(totalVotersBetween18To25 != 0){
						votersDetailsVO.setMaleVotersPercentFor18To25(roundTo2DigitsFloatValue((float) (maleVotersBetween18To25 *100f/ totalVotersBetween18To25 )));
						votersDetailsVO.setFemaleVotersPercentFor18To25(roundTo2DigitsFloatValue((float)(femaleVotersBetween18To25 *100f/ totalVotersBetween18To25 )));
					  }else{
						votersDetailsVO.setMaleVotersPercentFor18To25("0.00");
						votersDetailsVO.setFemaleVotersPercentFor18To25("0.00");
					}*/
				
				voterDetailsVO.setLocalityName(obj[1].toString());
				
				/*Long totalVoters = 0l;
				  if(voterDetailsVO.getTotalVotersFor18To25() != null)
				   totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor18To25();
				  if(voterDetailsVO.getTotalVotersFor26To35() != null)
				    totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor26To35();
				  if(voterDetailsVO.getTotalVotersFor36To45() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor36To45();
				  if(voterDetailsVO.getTotalVotersFor46To60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor46To60();
				  if(voterDetailsVO.getTotalVotersForAbove60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersForAbove60();*/
				Long totalVoters=(Long)obj[2];
				voterDetailsVO.setTotalVoters(totalVoters);
				
				/*voterDetailsVO.setVotersPercentFor18To25(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor26To35(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor36To45(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor46To60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters));
				voterDetailsVO.setVotersPercentForAbove60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters));
				*/		
				
				
				voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
				voterDetailsVO.setId((Long)obj[0]);
				boothVotersList.add(voterDetailsVO);
				
			}
			
			//Young Voters Implementation
			List<Object[]> youngerVotersList = userVoterDetailsDAO.getYoungerVotersForLocality(publicationDateId, hamletId, userId, (String)m.get(type),IConstants.YOUNG_VOTERS_AGE_FROM,IConstants.YOUNG_VOTERS_AGE_TO);
			if(youngerVotersList != null && youngerVotersList.size() > 0)
			 setYoungerVoterDetails(boothVotersList, youngerVotersList, (long)filter.size(), null, "fromTotalVotersCount");
			
			return boothVotersList;
			
		}
		
		//Updated by gayathri to get HamletLevel VotersBasicInfo
		
				public List<VotersInfoForMandalVO>  getPreviousVotersCountDetailsForHamlet( Long constituencyId, Long mandalId,Long  panchayatId,Long boothId ,Long hamletId,Long userID ,String type){
					log.debug("Entered into the getPreviousVotersCountDetailsForHamlet service method");	
					List<VotersInfoForMandalVO> votersInfoForMandalVOListHamlet = new ArrayList<VotersInfoForMandalVO>();
//1111
					try{
						List<VoterVO> previousDetailsListHamlet = null;
						previousDetailsListHamlet =  getAllPublicationsForHamlet(constituencyId);
							
						List<Object>  votersIdList = null;
						VotersInfoForMandalVO votersInfoForMandalVOHamlet = null;
						
						// For getting VotersDetails For HamletLevel
						// votersIdList =  userVoterDetailsDAO.getVoterIdsBasedOnHamletId(hamletId,userID);
						votersIdList  =(List<Object>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(hamletId, userID ,type);	
						for(VoterVO voterVO:previousDetailsListHamlet){
							 if(voterVO.getType().equalsIgnoreCase("Publication")){
								 if(votersIdList != null && votersIdList.size()>0){
								 votersInfoForMandalVOHamlet = getVoterDetailsByVoterIdForHamlet(voterVO,votersIdList);
								 }
								
						    }
									if(votersInfoForMandalVOListHamlet.size() > 0){
								
								VotersInfoForMandalVO previousResults = votersInfoForMandalVOListHamlet.get(votersInfoForMandalVOListHamlet.size()-1);
								VotersInfoForMandalVO currentResult = votersInfoForMandalVOHamlet;
								
								if(currentResult.getTotalMaleVoters() != null){							
									votersInfoForMandalVOHamlet.setMaleVotersDiff(Long.parseLong(currentResult.getTotalMaleVoters()) - Long.parseLong(previousResults.getTotalMaleVoters()));
								}else{
									if(previousResults.getTotalMaleVoters() != null)
										votersInfoForMandalVOHamlet.setMaleVotersDiff(Long.parseLong("0") - Long.parseLong(previousResults.getTotalMaleVoters()));
									else
										votersInfoForMandalVOHamlet.setMaleVotersDiff(Long.parseLong(previousResults.getTotalMaleVoters()));
		                        }
								
								if(currentResult.getTotalFemaleVoters() != null){							
									votersInfoForMandalVOHamlet.setFemaleVotersDiff(Long.parseLong(currentResult.getTotalFemaleVoters()) - Long.parseLong(previousResults.getTotalFemaleVoters()));
								}else{
									
									if(previousResults.getTotalFemaleVoters() != null)
										votersInfoForMandalVOHamlet.setFemaleVotersDiff(Long.parseLong("0") - Long.parseLong(previousResults.getTotalFemaleVoters()));
									else
										votersInfoForMandalVOHamlet.setFemaleVotersDiff(Long.parseLong(previousResults.getTotalFemaleVoters()));
										
		                        }
								
								if(votersInfoForMandalVOHamlet.getTotVoters() != null){
									votersInfoForMandalVOHamlet.setTotalVotersDiff(currentResult.getTotVoters().longValue() - previousResults.getTotVoters().longValue());
								}else{
									if(previousResults.getTotVoters() != null)
										votersInfoForMandalVOHamlet.setTotalVotersDiff(Long.parseLong("0") - previousResults.getTotVoters().longValue());
									else
										votersInfoForMandalVOHamlet.setTotalVotersDiff(previousResults.getTotVoters().longValue());


								}//else recent
							}//if parent
							
									 if(votersInfoForMandalVOHamlet !=null && votersInfoForMandalVOHamlet.isDatapresent()){			
									votersInfoForMandalVOListHamlet.add(votersInfoForMandalVOHamlet);
								}
							}//for
							}//try 
					 catch(Exception e){
						log.error("Exception raised in getPreviousVotersCountDetailsForHamlet service method");
						e.printStackTrace();
					}
				
				return votersInfoForMandalVOListHamlet;
				}	
				/** This Method is Used to get VotersBasicInfo For HamletLevel */
			public  VotersInfoForMandalVO	getVoterDetailsByVoterIdForHamlet(VoterVO voterVO,List<Object> voterIdList){
					
				 log.debug("Entered into the getVoterDetailsByVoterIdForHamlet service method");
		         
		         VotersInfoForMandalVO votersInfoForMandalVO = null;
		         try{
		        	 List<Object[]> VotersCont = new ArrayList<Object[]>(0);
		        	 int fromIndex = 0;
		        	 int toIndex = 1000;
		        	 if(voterIdList.size() >= 1000)
		        	 {
		        		 while(fromIndex <= toIndex)
						  {
		        		 List<Object[]> VotersContTemp = boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(voterVO.getPublicationDateId(),voterIdList.subList(fromIndex, toIndex));
		        		 VotersCont.addAll(VotersContTemp);
		        		 fromIndex += 1000;
		        		toIndex += 1000;
		        		if(toIndex >= voterIdList.size())
		        			toIndex = voterIdList.size();
						  }
		        	 }
		        	 else
		        		 VotersCont = boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(voterVO.getPublicationDateId(),voterIdList);
		        	 Long maleVotersCount = 0l;
		     		Long femaleVotersCount = 0l;
		     		Long totalCount = 0l;
		     		
		     		if(VotersCont !=null){
		     	   for(Object[] votersCount : VotersCont){
		     		  
		     		  if(votersCount[0] != null){
		     			  if(votersCount[1].equals("F"))
		   	    		     femaleVotersCount = femaleVotersCount+(Long)votersCount[0];
		     			  else
		     				 maleVotersCount = maleVotersCount+(Long)votersCount[0];
		   	    	}
		     	    	
		     	    		
		     	   }
		     	  totalCount = femaleVotersCount + maleVotersCount;
		     	}
		     		votersInfoForMandalVO = new VotersInfoForMandalVO();
		     	    votersInfoForMandalVO.setTotalMaleVoters(maleVotersCount.toString());
		     	    votersInfoForMandalVO.setTotalFemaleVoters(femaleVotersCount.toString());
		     	    votersInfoForMandalVO.setTotVoters(new BigDecimal(totalCount.longValue()));
		     	    
		     	    
		     	    
		     	   String maleVoters = votersInfoForMandalVO.getTotalMaleVoters();
					String femaleVoters = votersInfoForMandalVO.getTotalFemaleVoters();
					
					if(maleVoters == null || femaleVoters == null ||(maleVoters.equalsIgnoreCase("0") && femaleVoters.equalsIgnoreCase("0")))
						votersInfoForMandalVO.setDatapresent(false);
					
					votersInfoForMandalVO.setIsPublication("true");
					votersInfoForMandalVO.setPublicationDate(voterVO.getPublicationDate().toString());
					votersInfoForMandalVO.setElectinYear(voterVO.getPublicationDate().getYear()+1900);
		        	 
		         }catch(Exception e){
						log.error("Exception raised in getVoterDetailsByVoterIdForHamlet service method");
						e.printStackTrace();
					}
					
				return votersInfoForMandalVO;	
				}
				
				
				/** This Method is used to get PublicationsDateId For Hamlet */
				public List<VoterVO> getAllPublicationsForHamlet(Long constituencyId){
					log.debug("Entered into the getAllPublicationsForHamlet service method");
					
					List<VoterVO> previousDetailsListForHamlet = new ArrayList<VoterVO>();
					VoterVO voterVO = null;
					
					try{
						
						//For getting publicationIds Based on constituencyId
						
						List<Long>  publicationIdsListForHamlet = boothDAO
								.getAllPublicationDetailsForConstituency(constituencyId);	
								
						for(Long publicationId:publicationIdsListForHamlet){
							voterVO = new VoterVO();
							PublicationDate publicationDate = publicationDAO.get(publicationId);
							
							voterVO.setPublicationDateId(publicationDate.getPublicationDateId());
							voterVO.setElectionDate(publicationDate.getDate());
							voterVO.setPublicationDate(publicationDate.getDate());
						    voterVO.setType("Publication");				 
						    previousDetailsListForHamlet.add(voterVO);
						}
							Collections.sort(previousDetailsListForHamlet);
					}catch(Exception e){
						log.error("Exception raised in getAllPublicationsForHamlet service method");
						e.printStackTrace();
					}
					
					return previousDetailsListForHamlet;
					
				}




		
				public ResultStatus deleteVotersCastDataFromIntermediateTables(Long constituencyId,Long publicationDateId,Long userId)
				{
					ResultStatus resultStatus = new ResultStatus();
					try{
						if(constituencyId != null && constituencyId > 0)
						{
						voterCastInfoDAO.deleteVotersCastInfoByReportLevelValue(constituencyId, publicationDateId,userId);
						voterCastBasicInfoDAO.deleteVotersCastInfoByReportLevelValue(constituencyId, publicationDateId,userId);
						}
							resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						return resultStatus;
						
					}catch (Exception e) {
						e.printStackTrace();
						log.error("Exception Occured in deleteVotersCastDataFromIntermediateTables() Method, Exception - "+e);
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						return resultStatus;
					}
				}
				
			   
			   public ResultStatus deleteVotersPartyDataFromIntermediateTables(Long constituencyId,Long publicationDateId,Long userId)
			   {
				   ResultStatus resultStatus = new ResultStatus();
				   try{
				   if(constituencyId != null && constituencyId > 0)
				   {
					   voterPartyInfoDAO.deleteVotersPartyInfoByConstituencyId(constituencyId, publicationDateId,userId);
				   }
				   resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				   return resultStatus;
				   
			   }catch(Exception e)
			   {
				   log.error("Exception Occured in deleteVotersPartyDataFromIntermediateTables() Method, Exception - "+e);
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					return resultStatus;  
			   }
				
			 }
			   
			   public ResultStatus deleteVoterInfoFromIntermediateTablesByConstituencyId(Long constituencyId,Long publicationDateId)
				{
					ResultStatus resultStatus = new ResultStatus();
					try{
						
						if(constituencyId != null && constituencyId > 0)
						{
							voterInfoDAO.deleteVotersInfoByConstituencyId(constituencyId, publicationDateId);
							voterFamilyInfoDAO.deleteVoterFamilyDetByConstituencyId(constituencyId, publicationDateId);
							voterAgeInfoDAO.deleteVoterAgeInfoByConstituencyId(constituencyId, publicationDateId);
						}
						resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						return resultStatus;
					}catch (Exception e) {
						e.printStackTrace();
						log.error("Exception Occured in deleteVoterInfoFromIntermediateTablesByConstituencyId() Method, Exception - "+e);
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						return resultStatus;
					}
				}
			
       public List<SelectOptionVO> getConstituenciesToMapPublicationData(Long fromPublicationId,Long toPublicationId)
	   {
		   List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>();
		   SelectOptionVO selectOptionVO = null;
		   try{
			  List<Object[]> list = boothPublicationVoterDAO.getConstituenciesToMapPublicationData(fromPublicationId,toPublicationId);
			  if(list != null && list.size() > 0)
			  {
			  for(Object[] params : list)
			  {
			  if(params[0] != null)
			  selectOptionVO = new SelectOptionVO((Long)params[0],params[1].toString()); 
			 
			  constituencies.add(selectOptionVO);
			 
			  }
		  }
		   }
		   catch(Exception e)
		   {
			  e.printStackTrace();
		   }
		return constituencies;
	   }

	public VotersInfoForMandalVO getVotersBasicInfoForHamlet(Long id,Long publicationDateId,String reqType ,Long constituencyId){
			try{
				VotersInfoForMandalVO votersInfoForMandalVO =new VotersInfoForMandalVO();  
				//VotersInfoForMandalVO votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId("Panchayat"), id, publicationDateId, panchayatDAO.get(id).getPanchayatName(), "Panchayat",constituencyId);
				if(reqType.equalsIgnoreCase("main")){
					
				//	getVoterDetailsForHamletsInPanchayat(id,votersInfoForMandalVO);
					
				
					if(votersInfoForMandalVO != null && votersInfoForMandalVO.getVotersInfoForMandalVOList() != null && votersInfoForMandalVO.getVotersInfoForMandalVOList().size() > 0)
						votersInfoForMandalVO.setSubLevelExists(true);
					return votersInfoForMandalVO;
				}
				return votersInfoForMandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersBasicInfoForPanchayat() Method, Exception - "+e);
				return null;
			}
		}
	
		public void getVoterDetailsForLocalAreasInHamlet(Long hamletId,VotersInfoForMandalVO votersInfoForMandalVO1,Long publicationDateId, Long userId,String type)
		{  
			List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>();
			   
			
			/*
			   List<?> voterIds=	userVoterDetailsDAO.getVotersIdsByHamletId(hamletId,userId,type);
			   
			   if(voterIds == null || voterIds.size()==0)
				   return ;*/
			   
					List<?> filter =   userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(publicationDateId,hamletId,userId,type);
					if(filter == null || filter.size()==0)
						   return ;
			
			List<Long> hamletIds = new ArrayList<Long>();
			List<Object[]> hamletsList = new ArrayList<Object[]>();
			  int fromIndex = 0;
			  int toIndex = 1000;
			  if(filter.size() > 1000)
			  {
				  while(fromIndex <= toIndex)
				  {
					 List<Object[]> hamlets = userVoterDetailsDAO.getVotersCountByGenderForLocalAreas(filter.subList(fromIndex, toIndex),userId);
					 if(hamlets != null)
					 {
						 hamletsList.addAll(hamlets);
					 }
					 fromIndex += 1000;
					  toIndex += 1000;
					  if(toIndex >= filter.size())
						toIndex = filter.size();
				  }
			  }
			  else
			  {
				  hamletsList = userVoterDetailsDAO.getVotersCountByGenderForLocalAreas(filter,userId); 
			  }
			  
			  hamletsList = processDataForRemovingDuplication(hamletsList);
			int totalFemaleVoters = 0;
			int totalMaleVoters = 0;
			int totalUnknownVoters = 0;
			int totalVoters = 0;
		
			for(Object[] obj:hamletsList)
			{
				
				VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
				
			//	List<Object[]> hamletDetails = userVoterDetailsDAO.getVotersCountByGenderForHamlet((Long)obj[0]);
				
				
				totalVoters =Integer.parseInt(obj[1].toString()) + Integer.parseInt(obj[2].toString());
					
				//}
				
				votersInfoForMandalVO.setTotVoters(new BigDecimal(totalVoters));
				votersInfoForMandalVO.setTotalVoters(totalVoters+"") ;
				votersInfoForMandalVO.setTotalMaleVoters(obj[2].toString());
				votersInfoForMandalVO.setTotalFemaleVoters(obj[1].toString());
				votersInfoForMandalVO.setTotalVotersPercentage("0.00");
				//votersInfoForMandalVO.setUnKnowVoters(totalUnknownVoters.toString());
				votersInfoForMandalVO.setType("LocalArea");
				votersInfoForMandalVO.setName(obj[0].toString());
				
				
				if(totalVoters  == 0)
					votersInfoForMandalVO.setDatapresent(false);
				else
					votersInfoForMandalVOList.add(votersInfoForMandalVO);
			}
			
			votersInfoForMandalVO1.setVotersInfoForMandalVOList(votersInfoForMandalVOList);
			  calculatePercentage1(votersInfoForMandalVO1);
		}
		
		
		List<Object[]> processDataForRemovingDuplication(List<Object[]> list)
		{
			List<Object[]> result = new ArrayList<Object[]>(0);
			Map<String,SelectOptionVO> resultMap = new HashMap<String, SelectOptionVO>(0);
			try{
				SelectOptionVO selectOptionVO = null;
				for(Object[] params : list)
				{
					selectOptionVO = resultMap.get(params[0].toString());
					if(selectOptionVO == null)
					{
						selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)params[1]);
						selectOptionVO.setOrderId((Long)params[2]);
					}
					else
					{
						selectOptionVO.setId((Long)params[1]+selectOptionVO.getId());
						selectOptionVO.setOrderId((Long)params[2]+selectOptionVO.getOrderId());
					}
					resultMap.put(params[0].toString(), selectOptionVO);
				}
				for(Map.Entry<String,SelectOptionVO> entry : resultMap.entrySet())
				{
					Object[] ar = new Object[]{entry.getKey(),entry.getValue().getId(),entry.getValue().getOrderId()};
					result.add(ar);
				}
				return result;
			}catch(Exception e){
				log.error("Exception Occured in processDataForRemovingDuplication()",e);
				return result;
			}
		}
		
		
		   /**
		    * This Method Is Used to Store Missing Voters
		    * @param Map<String , VoterVO> voterMap
		    * @param Long boothId
		    * @return  List<String>
		    * @date 13/03/2013
		    */
		public List<String> storeVoterDetails(final Map<String , VoterVO> voterMap, final Long boothId) {
			 log.info("Entered into saveVoters Method...");
			 List<String> voters = null;
			  try{		
				  if(voterMap !=null && voterMap.size() > 0)
				  {
					  voters  = voterDAO.checkForVoterCardId(voterMap.keySet());
					 if(voters != null && voters.size() > 0)
					 {
						 for (String voterKey : voters) {
							 
							 voterMap.remove(voterKey);
						}  
					 }
					 if(voterMap != null && voterMap.size() > 0)
					 {
					 for (final String key : voterMap.keySet()) {

						transactionTemplate.execute(new TransactionCallbackWithoutResult() {
						protected void doInTransactionWithoutResult(TransactionStatus status) 
						{ 
						VoterVO voterVO =  voterMap.get(key);
						 Voter voter = new Voter();
						 voter.setName(voterVO.getFirstName());
						 voter.setAge(voterVO.getAge());
						 voter.setVoterIDCardNo(voterVO.getVoterIDCardNo());
						 voter.setHouseNo(voterVO.getHouseNo());
						 voter.setGender(voterVO.getGender());
						 voter.setRelationshipType(voterVO.getRelationshipType());
						 voter.setRelativeName(voterVO.getRelativeFirstName());
						 BoothPublicationVoter boothPublicationVoter = new BoothPublicationVoter();
							if(boothId != null)
							{
								boothPublicationVoter.setBoothId(boothId);
								boothPublicationVoter.setSerialNo(voterVO.getSerialNo());
								boothPublicationVoter.setVoter(voter);
								boothPublicationVoterDAO.save(boothPublicationVoter);
							}
						}
						
						});
				  
				  	
				}	
		
				  }
				  }
					
			  }catch (Exception e) {
				  e.printStackTrace();
				  log.error("Exception Occured in saveVoters Method, Exception - "+e);
			}
		  
			return voters;
		}

		/**
		 * This Method Is used To Check Weather Voters Is Avaliable Or Nor For The Given Voter Id
		 * @param String voetrCardId
		 * @return ResultStatus
		 * @date 13/03/2013
		 */
		public ResultStatus checkForVoterId(String voetrCardId) {
			ResultStatus resultStatus = new ResultStatus();
			if(voetrCardId != null)
			{
				List<Voter> voters = voterDAO.getVoterByVoterCardNo(voetrCardId);
				if(voters != null && voters.size() > 0)
				 {
				  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				 }
				else
				{
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				}
			}
			return resultStatus;
		}

		/**
		 * This Method Is used To Check For Serial No For Voter In The Given Booth No
		 * @param List<Long> serialNos
		 * @param Long boothId
		 * @return List<Long>
		 * @date 13/03/2013
		 */
		public List<Long> checkForSerialNos(List<Long> serialNos, Long boothId) {
			List<Long> snos = null;
			snos = boothPublicationVoterDAO.checkForSerialNosDao(serialNos,boothId);
			return snos;
		} 
		
		public void myBusinessDelegator(List<Object[]> obj1, List<VotersDetailsVO> resultReturn )
		{   
		   
		   int length=0;
		  
		   for(Object[] obj:obj1){
		   
			 length=obj.length;
			VotersDetailsVO voterDetailsVO =new VotersDetailsVO();
			
			//above 60 age realated Data
			 voterDetailsVO.setTotalVoters((Long)obj[length-1]);
			 voterDetailsVO.setTotalUnknownVoters((Long)obj[length-6]);
			 voterDetailsVO.setTotalMaleVoters((Long)obj[length-7]);
			 voterDetailsVO.setTotalFemaleVoters((Long)obj[length-8]);
			 
			 VotersDetailsVO voterDetailsVO4 = new VotersDetailsVO();	
                //46-50 age realated Data
			 voterDetailsVO4.setTotalVoters((Long)obj[length-2]);
			 voterDetailsVO4.setTotalUnknownVoters((Long)obj[length-9]);
			 voterDetailsVO4.setTotalMaleVoters((Long)obj[length-10]);
			 voterDetailsVO4.setTotalFemaleVoters((Long)obj[length-11]);
			 
			   VotersDetailsVO voterDetailsVO3 = new VotersDetailsVO();	
		        //36-45 age realated Data
			 
			              voterDetailsVO3.setTotalVoters((Long)obj[length-3]);
			 			 voterDetailsVO3.setTotalUnknownVoters((Long)obj[length-12]);
						 voterDetailsVO3.setTotalMaleVoters((Long)obj[length-13]);
						 voterDetailsVO3.setTotalFemaleVoters((Long)obj[length-14]);
						 
						 VotersDetailsVO voterDetailsVO2 = new VotersDetailsVO();	
						//26-35 age realated Data	
						 voterDetailsVO2.setTotalVoters((Long)obj[length-4]);
						 voterDetailsVO2.setTotalUnknownVoters((Long)obj[length-15]);
						 voterDetailsVO2.setTotalMaleVoters((Long)obj[length-16]);
						 voterDetailsVO2.setTotalFemaleVoters((Long)obj[length-17]);
						 
						 VotersDetailsVO voterDetailsVO1 = new VotersDetailsVO();	
		             //18-25 age realated Data				 
						 voterDetailsVO1.setTotalVoters((Long)obj[length-5]);
						 voterDetailsVO1.setTotalUnknownVoters((Long)obj[length-18]);
						 voterDetailsVO1.setTotalMaleVoters((Long)obj[length-19]);
						 voterDetailsVO1.setTotalFemaleVoters((Long)obj[length-20]);
						 
						  long maleVoters =0; 
						  long femaleVoters =0; 
						  long totalVotersCase=0;
						  
						 if( voterDetailsVO.getTotalVoters() != 0){
							 maleVoters=(long)voterDetailsVO.getTotalMaleVoters();
							 femaleVoters=(long)voterDetailsVO.getTotalFemaleVoters();
							 totalVotersCase=voterDetailsVO.getTotalVoters();
							 voterDetailsVO.setTotalMaleVotersPercent((float) (maleVoters*100f/ totalVotersCase ));
							 voterDetailsVO.setTotalFemaleVotersPercent((float)(femaleVoters *100f/ totalVotersCase ));
							  }else{
								  voterDetailsVO.setTotalMaleVotersPercent(0.0f);
								  voterDetailsVO.setTotalFemaleVotersPercent(0.0f);
							}
			
						 if( voterDetailsVO4.getTotalVoters() != 0){
							 maleVoters=(long)voterDetailsVO4.getTotalMaleVoters();
							 femaleVoters=(long)voterDetailsVO4.getTotalFemaleVoters();
							 totalVotersCase=voterDetailsVO4.getTotalVoters();
							 voterDetailsVO4.setTotalMaleVotersPercent((float) (maleVoters*100f/ totalVotersCase ));
							 voterDetailsVO4.setTotalFemaleVotersPercent((float)(femaleVoters *100f/ totalVotersCase ));
							  }else{
								  voterDetailsVO4.setTotalMaleVotersPercent(0.0f);
								  voterDetailsVO4.setTotalFemaleVotersPercent(0.0f);
							}
						 if( voterDetailsVO3.getTotalVoters() != 0){
							 maleVoters=(long)voterDetailsVO3.getTotalMaleVoters();
							 femaleVoters=(long)voterDetailsVO3.getTotalFemaleVoters();
							 totalVotersCase=voterDetailsVO3.getTotalVoters();
							 voterDetailsVO3.setTotalMaleVotersPercent((float) (maleVoters*100f/ totalVotersCase ));
							 voterDetailsVO3.setTotalFemaleVotersPercent((float)(femaleVoters *100f/ totalVotersCase ));
							  }else{
								  voterDetailsVO3.setTotalMaleVotersPercent(0.0f);
								  voterDetailsVO3.setTotalFemaleVotersPercent(0.0f);
							}
						 if(
								 voterDetailsVO2.getTotalVoters() != 0){
							 maleVoters=(long)voterDetailsVO2.getTotalMaleVoters();
							 femaleVoters=(long)voterDetailsVO2.getTotalFemaleVoters();
							 totalVotersCase=voterDetailsVO2.getTotalVoters();
							 voterDetailsVO2.setTotalMaleVotersPercent((float) (maleVoters*100f/ totalVotersCase ));
							 voterDetailsVO2.setTotalFemaleVotersPercent((float)(femaleVoters *100f/ totalVotersCase ));
							  }else{
								  voterDetailsVO2.setTotalMaleVotersPercent(0.0f);
								  voterDetailsVO2.setTotalFemaleVotersPercent(0.0f);
							}
						 if( voterDetailsVO1.getTotalVoters() != 0){
							 maleVoters=(long)voterDetailsVO1.getTotalMaleVoters();
							 femaleVoters=(long)voterDetailsVO1.getTotalFemaleVoters();
							 totalVotersCase=voterDetailsVO1.getTotalVoters();
							 voterDetailsVO1.setTotalMaleVotersPercent((float) (maleVoters*100f/ totalVotersCase ));
							 voterDetailsVO1.setTotalFemaleVotersPercent((float)(femaleVoters *100f/ totalVotersCase ));
							  }else{
								  voterDetailsVO1.setTotalMaleVotersPercent(0.0f);
								  voterDetailsVO1.setTotalFemaleVotersPercent(0.0f);
							}	 
						 
	//	List<Long>	voterIds =(List<Long>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId((Long)obj[0], userId);
			
			
			
		
			
			//voterDetailsVO.setLocalityName(obj[1].toString());
			
		
			Long totalVoters=(Long)obj[0];
			//voterDetailsVO.setTotalVoters(totalVoters);
			
		
			
			
			voterDetailsVO1.setTotalVotersPercent(voterDetailsVO1.getTotalVoters() != null ? (float)voterDetailsVO1.getTotalVoters()*100f/totalVoters : 0.0f);
			voterDetailsVO2.setTotalVotersPercent(voterDetailsVO2.getTotalVoters() != null ? ((float)voterDetailsVO2.getTotalVoters()*100f/totalVoters):0.0f);
			voterDetailsVO3.setTotalVotersPercent(voterDetailsVO3.getTotalVoters() != null ? ((float)voterDetailsVO3.getTotalVoters()*100f/totalVoters):0.0f);
			voterDetailsVO4.setTotalVotersPercent(voterDetailsVO4.getTotalVoters() != null ? ((float)voterDetailsVO4.getTotalVoters()*100f/totalVoters) :0.0f);
			voterDetailsVO.setTotalVotersPercent(voterDetailsVO.getTotalVoters() != null?  ((float)voterDetailsVO.getTotalVoters()*100f/totalVoters):0.0f);
			
			voterDetailsVO1.setAgeRange(IConstants.AGE18to25);
			voterDetailsVO2.setAgeRange(IConstants.AGE23to30);
			voterDetailsVO3.setAgeRange(IConstants.AGE31to45);
			voterDetailsVO4.setAgeRange(IConstants.AGE46to60);
			voterDetailsVO.setAgeRange(IConstants.Above60);
			
			
			  resultReturn.add(voterDetailsVO1);
			  resultReturn.add(voterDetailsVO2);
			  resultReturn.add(voterDetailsVO3);
			  resultReturn.add(voterDetailsVO4);
			  resultReturn.add(voterDetailsVO);
		   }
		 
		}
		public void myBusinessDelegator1(List<Object[]> obj1, List<VotersDetailsVO> resultReturn )
		{   
		   
		   int length=0;
		   Long totalVoters= 0l;
		   VotersDetailsVO voterDetailsVO =new VotersDetailsVO();
		   VotersDetailsVO voterDetailsVO4 = new VotersDetailsVO();	
		   VotersDetailsVO voterDetailsVO3 = new VotersDetailsVO();
		   VotersDetailsVO voterDetailsVO2 = new VotersDetailsVO();
		   VotersDetailsVO voterDetailsVO1 = new VotersDetailsVO();
		   for(Object[] obj:obj1){
		   
			 length=obj.length;
			
			
			//above 60 age realated Data
			 voterDetailsVO.setTotalVoters(voterDetailsVO.getTotalVoters() != null?voterDetailsVO.getTotalVoters()+(Long)obj[length-1]:(Long)obj[length-1]);
			 voterDetailsVO.setTotalUnknownVoters(voterDetailsVO.getTotalUnknownVoters() != null?voterDetailsVO.getTotalUnknownVoters()+(Long)obj[length-6]:(Long)obj[length-6]);
			 voterDetailsVO.setTotalMaleVoters(voterDetailsVO.getTotalMaleVoters() != null? voterDetailsVO.getTotalMaleVoters()+(Long)obj[length-7]:(Long)obj[length-7]);
			 voterDetailsVO.setTotalFemaleVoters(voterDetailsVO.getTotalFemaleVoters() != null ?voterDetailsVO.getTotalFemaleVoters()+(Long)obj[length-8]:(Long)obj[length-8]);
			 
			 
                //46-50 age realated Data
			 voterDetailsVO4.setTotalVoters(voterDetailsVO4.getTotalVoters() != null?voterDetailsVO4.getTotalVoters()+(Long)obj[length-2]:(Long)obj[length-2]);
			 voterDetailsVO4.setTotalUnknownVoters(voterDetailsVO4.getTotalUnknownVoters() != null?voterDetailsVO4.getTotalUnknownVoters()+(Long)obj[length-9]:(Long)obj[length-9]);
			 voterDetailsVO4.setTotalMaleVoters(voterDetailsVO4.getTotalMaleVoters() != null? voterDetailsVO4.getTotalMaleVoters()+(Long)obj[length-10]:(Long)obj[length-10]);
			 voterDetailsVO4.setTotalFemaleVoters(voterDetailsVO4.getTotalFemaleVoters() != null ?voterDetailsVO4.getTotalFemaleVoters()+(Long)obj[length-11]:(Long)obj[length-11]);
			 
			   	
		        //36-45 age realated Data
			 
			              voterDetailsVO3.setTotalVoters(voterDetailsVO3.getTotalVoters() != null?voterDetailsVO3.getTotalVoters()+(Long)obj[length-3]:(Long)obj[length-3]);
			 			 voterDetailsVO3.setTotalUnknownVoters(voterDetailsVO3.getTotalUnknownVoters() != null?voterDetailsVO3.getTotalUnknownVoters()+(Long)obj[length-12]:(Long)obj[length-12]);
						 voterDetailsVO3.setTotalMaleVoters(voterDetailsVO3.getTotalMaleVoters() != null? voterDetailsVO3.getTotalMaleVoters()+(Long)obj[length-13]:(Long)obj[length-13]);
						 voterDetailsVO3.setTotalFemaleVoters(voterDetailsVO3.getTotalFemaleVoters() != null ?voterDetailsVO3.getTotalFemaleVoters()+(Long)obj[length-14]:(Long)obj[length-14]);
						 
						 	
						//26-35 age realated Data	
						 voterDetailsVO2.setTotalVoters(voterDetailsVO2.getTotalVoters() != null?voterDetailsVO2.getTotalVoters()+(Long)obj[length-4]:(Long)obj[length-4]);
						 voterDetailsVO2.setTotalUnknownVoters(voterDetailsVO2.getTotalUnknownVoters() != null?voterDetailsVO2.getTotalUnknownVoters()+(Long)obj[length-15]:(Long)obj[length-15]);
						 voterDetailsVO2.setTotalMaleVoters(voterDetailsVO2.getTotalMaleVoters() != null? voterDetailsVO2.getTotalMaleVoters()+(Long)obj[length-16]:(Long)obj[length-16]);
						 voterDetailsVO2.setTotalFemaleVoters(voterDetailsVO2.getTotalFemaleVoters() != null ?voterDetailsVO2.getTotalFemaleVoters()+(Long)obj[length-17]:(Long)obj[length-17]);
						 
						 	
		             //18-25 age realated Data				 
						 voterDetailsVO1.setTotalVoters(voterDetailsVO1.getTotalVoters() != null?voterDetailsVO1.getTotalVoters()+(Long)obj[length-5]:(Long)obj[length-5]);
						 voterDetailsVO1.setTotalUnknownVoters(voterDetailsVO1.getTotalUnknownVoters() != null?voterDetailsVO1.getTotalUnknownVoters()+(Long)obj[length-18]:(Long)obj[length-18]);
						 voterDetailsVO1.setTotalMaleVoters(voterDetailsVO1.getTotalMaleVoters() != null? voterDetailsVO1.getTotalMaleVoters()+(Long)obj[length-19]:(Long)obj[length-19]);
						 voterDetailsVO1.setTotalFemaleVoters(voterDetailsVO1.getTotalFemaleVoters() != null ?voterDetailsVO1.getTotalFemaleVoters()+(Long)obj[length-20]:(Long)obj[length-20]);
						 
						  totalVoters=totalVoters+(Long)obj[0];
		   }	 
						  long maleVoters =0; 
						  long femaleVoters =0; 
						  long totalVotersCase=0;
						  
						 if( voterDetailsVO.getTotalVoters() != 0){
							 maleVoters=(long)voterDetailsVO.getTotalMaleVoters();
							 femaleVoters=(long)voterDetailsVO.getTotalFemaleVoters();
							 totalVotersCase=voterDetailsVO.getTotalVoters();
							 voterDetailsVO.setTotalMaleVotersPercent((float) (maleVoters*100f/ totalVotersCase ));
							 voterDetailsVO.setTotalFemaleVotersPercent((float)(femaleVoters *100f/ totalVotersCase ));
							  }else{
								  voterDetailsVO.setTotalMaleVotersPercent(0.0f);
								  voterDetailsVO.setTotalFemaleVotersPercent(0.0f);
							}
			
						 if( voterDetailsVO4.getTotalVoters() != 0){
							 maleVoters=(long)voterDetailsVO4.getTotalMaleVoters();
							 femaleVoters=(long)voterDetailsVO4.getTotalFemaleVoters();
							 totalVotersCase=voterDetailsVO4.getTotalVoters();
							 voterDetailsVO4.setTotalMaleVotersPercent((float) (maleVoters*100f/ totalVotersCase ));
							 voterDetailsVO4.setTotalFemaleVotersPercent((float)(femaleVoters *100f/ totalVotersCase ));
							  }else{
								  voterDetailsVO4.setTotalMaleVotersPercent(0.0f);
								  voterDetailsVO4.setTotalFemaleVotersPercent(0.0f);
							}
						 if( voterDetailsVO3.getTotalVoters() != 0){
							 maleVoters=(long)voterDetailsVO3.getTotalMaleVoters();
							 femaleVoters=(long)voterDetailsVO3.getTotalFemaleVoters();
							 totalVotersCase=voterDetailsVO3.getTotalVoters();
							 voterDetailsVO3.setTotalMaleVotersPercent((float) (maleVoters*100f/ totalVotersCase ));
							 voterDetailsVO3.setTotalFemaleVotersPercent((float)(femaleVoters *100f/ totalVotersCase ));
							  }else{
								  voterDetailsVO3.setTotalMaleVotersPercent(0.0f);
								  voterDetailsVO3.setTotalFemaleVotersPercent(0.0f);
							}
						 if(
								 voterDetailsVO2.getTotalVoters() != 0){
							 maleVoters=(long)voterDetailsVO2.getTotalMaleVoters();
							 femaleVoters=(long)voterDetailsVO2.getTotalFemaleVoters();
							 totalVotersCase=voterDetailsVO2.getTotalVoters();
							 voterDetailsVO2.setTotalMaleVotersPercent((float) (maleVoters*100f/ totalVotersCase ));
							 voterDetailsVO2.setTotalFemaleVotersPercent((float)(femaleVoters *100f/ totalVotersCase ));
							  }else{
								  voterDetailsVO2.setTotalMaleVotersPercent(0.0f);
								  voterDetailsVO2.setTotalFemaleVotersPercent(0.0f);
							}
						 if( voterDetailsVO1.getTotalVoters() != 0){
							 maleVoters=(long)voterDetailsVO1.getTotalMaleVoters();
							 femaleVoters=(long)voterDetailsVO1.getTotalFemaleVoters();
							 totalVotersCase=voterDetailsVO1.getTotalVoters();
							 voterDetailsVO1.setTotalMaleVotersPercent((float) (maleVoters*100f/ totalVotersCase ));
							 voterDetailsVO1.setTotalFemaleVotersPercent((float)(femaleVoters *100f/ totalVotersCase ));
							  }else{
								  voterDetailsVO1.setTotalMaleVotersPercent(0.0f);
								  voterDetailsVO1.setTotalFemaleVotersPercent(0.0f);
							}	 
						 
	//	List<Long>	voterIds =(List<Long>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId((Long)obj[0], userId);
			
			
			
		
			
			//voterDetailsVO.setLocalityName(obj[1].toString());
			
		
			
			//voterDetailsVO.setTotalVoters(totalVoters);
			
		
			
			
			voterDetailsVO1.setTotalVotersPercent(voterDetailsVO1.getTotalVoters() != null ? (float)voterDetailsVO1.getTotalVoters()*100f/totalVoters : 0.0f);
			voterDetailsVO2.setTotalVotersPercent(voterDetailsVO2.getTotalVoters() != null ? ((float)voterDetailsVO2.getTotalVoters()*100f/totalVoters):0.0f);
			voterDetailsVO3.setTotalVotersPercent(voterDetailsVO3.getTotalVoters() != null ? ((float)voterDetailsVO3.getTotalVoters()*100f/totalVoters):0.0f);
			voterDetailsVO4.setTotalVotersPercent(voterDetailsVO4.getTotalVoters() != null ? ((float)voterDetailsVO4.getTotalVoters()*100f/totalVoters) :0.0f);
			voterDetailsVO.setTotalVotersPercent(voterDetailsVO.getTotalVoters() != null?  ((float)voterDetailsVO.getTotalVoters()*100f/totalVoters):0.0f);
			
			voterDetailsVO1.setAgeRange(IConstants.AGE18to25);
			voterDetailsVO2.setAgeRange(IConstants.AGE23to30);
			voterDetailsVO3.setAgeRange(IConstants.AGE31to45);
			voterDetailsVO4.setAgeRange(IConstants.AGE46to60);
			voterDetailsVO.setAgeRange(IConstants.Above60);
			
			
			  resultReturn.add(voterDetailsVO1);
			  resultReturn.add(voterDetailsVO2);
			  resultReturn.add(voterDetailsVO3);
			  resultReturn.add(voterDetailsVO4);
			  resultReturn.add(voterDetailsVO);
		   
		 
		}
		public void helperBusinessDelegator(List<Object[]> hamlets,List< VotersDetailsVO> boothVotersList){
			//[];
			//List<Object[]> booths = boothDAO.getBoothsInAPanchayat(panchayatId, publicationDateId);
			                        
		   // List<Object[]> hamlets =  userVoterDetailsDAO.getHamletsIdsForUser(hamletId, userId);
			
			 		   
			
			//Long [] hamlets =(Long[]) hamlets;
		
			for(Object[] obj: hamlets) {
				 int length=obj.length;
				
					VotersDetailsVO voterDetailsVO = new VotersDetailsVO();		
				 //System.out.println(objects[--length]);
				//setting total voters agewise
					
					
					
				 voterDetailsVO.setTotalVotersForAbove60((Long)obj[length-1]);
				 voterDetailsVO.setTotalVotersFor46To60((Long)obj[length-2]);
				 voterDetailsVO.setTotalVotersFor36To45((Long)obj[length-3]);
				 voterDetailsVO.setTotalVotersFor26To35((Long)obj[length-4]);
				 voterDetailsVO.setTotalVotersFor18To25((Long)obj[length-5]);
			
				 //above 60 age realated Data
				 
				 voterDetailsVO.setTotalUnknownVotersForAbove60((Long)obj[length-6]);
				 voterDetailsVO.setTotalMaleVotersForAbove60((Long)obj[length-7]);
				 voterDetailsVO.setTotalFemaleVotersForAbove60((Long)obj[length-8]);
				 
                     //46-50 age realated Data
				 
				 voterDetailsVO.setTotalUnknownVotersFor46To60((Long)obj[length-9]);
				 voterDetailsVO.setTotalMaleVotersFor46To60((Long)obj[length-10]);
				 voterDetailsVO.setTotalFemaleVotersFor46To60((Long)obj[length-11]);
				 
				 
			        //36-45 age realated Data
				 
				 
				 			 voterDetailsVO.setTotalUnknownVotersFor36To45((Long)obj[length-12]);
							 voterDetailsVO.setTotalMaleVotersFor36To45((Long)obj[length-13]);
							 voterDetailsVO.setTotalFemaleVotersFor36To45((Long)obj[length-14]);
				 
							//26-35 age realated Data		 
							 voterDetailsVO.setTotalUnknownVotersFor26To35((Long)obj[length-15]);
							 voterDetailsVO.setTotalMaleVotersFor26To35((Long)obj[length-16]);
							 voterDetailsVO.setTotalFemaleVotersFor26To35((Long)obj[length-17]);
							 
			             //18-25 age realated Data				 
							 voterDetailsVO.setTotalUnknownVotersFor18To25((Long)obj[length-18]);
							 voterDetailsVO.setTotalMaleVotesFor18To25((Long)obj[length-19]);
							 voterDetailsVO.setTotalFemaleVotersFor18To25((Long)obj[length-20]);
							 
							  long maleVoters =0; 
							  long femaleVoters =0; 
							  long totalVotersCase=0;
							  
							 if( voterDetailsVO.getTotalVotersForAbove60() != 0){
								 maleVoters=(long)voterDetailsVO.getTotalMaleVotersForAbove60();
								 femaleVoters=(long)voterDetailsVO.getTotalFemaleVotersForAbove60();
								 totalVotersCase=voterDetailsVO.getTotalVotersForAbove60();
								 voterDetailsVO.setMaleVotersPercentForAbove60(roundTo2DigitsFloatValue((float) (maleVoters*100f/ totalVotersCase )));
								 voterDetailsVO.setFemaleVotersPercentForAbove60(roundTo2DigitsFloatValue((float)(femaleVoters *100f/ totalVotersCase )));
								  }else{
									  voterDetailsVO.setMaleVotersPercentForAbove60("0.00");
									  voterDetailsVO.setFemaleVotersPercentForAbove60("0.00");
								}
				
							 if( voterDetailsVO.getTotalVotersFor46To60() != 0){
								 maleVoters=(long)voterDetailsVO.getTotalMaleVotersFor46To60();
								 femaleVoters=(long)voterDetailsVO.getTotalFemaleVotersFor46To60();
								 totalVotersCase=voterDetailsVO.getTotalVotersFor46To60();
									voterDetailsVO.setMaleVotersPercentFor46To60(roundTo2DigitsFloatValue((float) (maleVoters*100f/ totalVotersCase )));
									voterDetailsVO.setFemaleVotersPercentFor46To60(roundTo2DigitsFloatValue((float)(femaleVoters *100f/ totalVotersCase )));
								  }else{
									voterDetailsVO.setMaleVotersPercentFor46To60("0.00");
									voterDetailsVO.setFemaleVotersPercentFor46To60("0.00");
								}
							 if( voterDetailsVO.getTotalVotersFor36To45() != 0){
								 maleVoters=(long)voterDetailsVO.getTotalMaleVotersFor36To45();
								 femaleVoters=(long)voterDetailsVO.getTotalFemaleVotersFor36To45();
								 totalVotersCase=voterDetailsVO.getTotalVotersFor36To45();
									voterDetailsVO.setMaleVotersPercentFor36To45(roundTo2DigitsFloatValue((float) (maleVoters*100f/ totalVotersCase )));
									voterDetailsVO.setFemaleVotersPercentFor36To45(roundTo2DigitsFloatValue((float)(femaleVoters *100f/ totalVotersCase )));
								  }else{
									voterDetailsVO.setMaleVotersPercentFor36To45("0.00");
									voterDetailsVO.setFemaleVotersPercentFor36To45("0.00");
								}
							 if( voterDetailsVO.getTotalVotersFor26To35() != 0){
								 maleVoters=(long)voterDetailsVO.getTotalMaleVotersFor26To35();
								 femaleVoters=(long)voterDetailsVO.getTotalFemaleVotersFor26To35();
								 totalVotersCase=voterDetailsVO.getTotalVotersFor26To35();
									voterDetailsVO.setMaleVotersPercentFor26To35(roundTo2DigitsFloatValue((float) (maleVoters*100f/ totalVotersCase )));
									voterDetailsVO.setFemaleVotersPercentFor26To35(roundTo2DigitsFloatValue((float)(femaleVoters *100f/ totalVotersCase )));
								  }else{
									voterDetailsVO.setMaleVotersPercentFor26To35("0.00");
									voterDetailsVO.setFemaleVotersPercentFor26To35("0.00");
								}
							 if( voterDetailsVO.getTotalVotersFor18To25() != 0){
								 maleVoters=(long)voterDetailsVO.getTotalMaleVotesFor18To25();
								 femaleVoters=(long)voterDetailsVO.getTotalFemaleVotersFor18To25();
								 totalVotersCase=voterDetailsVO.getTotalVotersFor18To25();
									voterDetailsVO.setMaleVotersPercentFor18To25(roundTo2DigitsFloatValue((float) (maleVoters*100f/ totalVotersCase )));
									voterDetailsVO.setFemaleVotersPercentFor18To25(roundTo2DigitsFloatValue((float)(femaleVoters *100f/ totalVotersCase )));
								  }else{
									voterDetailsVO.setMaleVotersPercentFor18To25("0.00");
									voterDetailsVO.setFemaleVotersPercentFor18To25("0.00");
								}	 
							 
		
				
				voterDetailsVO.setHamletName(obj[1].toString());
				voterDetailsVO.setId((Long)obj[0]);
				
				Long totalVoters=(Long)obj[2];
				voterDetailsVO.setTotalVoters(totalVoters);
				
			
				
				
				voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
				boothVotersList.add(voterDetailsVO);
				
			}
		
			
		}
		
		public List<VoterHouseInfoVO> getVotersFamilyDetailsByConstituencyId(Long frompublicationId,Long toPublicationId,Long partNo,String hno,Long userId,Long constituencyId)
		{
			
			List<VoterHouseInfoVO> resultList = new ArrayList<VoterHouseInfoVO>();
			List<Long> publicationId = boothPublicationVoterDAO.getVoterPublicationIdsBetweenTwoPublications(frompublicationId,toPublicationId);
			
			try{
				
				resultList = getFamilyInformationByPartNo(partNo, publicationId.get(0),hno,userId,constituencyId);
			}
			catch(Exception e)
			{
				log.error("Exception Occured in getVotersFamilyDetailsByConstituencyId() - method" +e);
			}
			return resultList;
			
		}
		
		//This method is used get VoterDetails by partno and houseno
		public List<VoterHouseInfoVO> getFamilyInformationByPartNo(Long partNo, Long publicationDateId,String houseNo,Long userId,Long constituencyId)
		{		
			log.debug("Entered into the getFamilyInformation method");
			
			List<VoterHouseInfoVO> voterHouseInfoVOList = new ArrayList<VoterHouseInfoVO>();		
			
			try{
			   
				VoterHouseInfoVO voterHouseInfoVO = null;
				List<Voter> votersInfoList = boothPublicationVoterDAO.findFamiliesInfoBypartNo(partNo.toString(), publicationDateId, houseNo,constituencyId);
			    long sno = 1;
			    
			    List<Long> voterIds = new ArrayList<Long>();
			    for(Voter voter : votersInfoList)
			    	voterIds.add(voter.getVoterId());
			    
			    Map<Long,String> mobileMap = new HashMap<Long, String>(0);
			    if(voterIds != null && voterIds.size() > 0)
			    {
			      List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIds, userId);
			      if(list != null && list.size() > 0)
			       for(Object[] params:list)
			    	   mobileMap.put((Long)params[0], params[1] != null?params[1].toString():"N/A");
			    }
			    
			    List<Object[]> votersCategoriesList = 
						 voterCategoryValueDAO.getVoterCategoryValuesForVoters(userId,voterIds);
				 
			    
			   Map<Long, VoterHouseInfoVO> voterCastePartyDetails =  getUserCasteAndSelectedPartyVoters(voterIds,userId);
			    	
				for(Voter voter : votersInfoList){
			    	voterHouseInfoVO = new VoterHouseInfoVO();
			    	voterHouseInfoVO.setsNo(sno);
			    	voterHouseInfoVO.setName(voter.getName());
			    	voterHouseInfoVO.setGender(voter.getGender());
			    	voterHouseInfoVO.setAge(voter.getAge());
			    	voterHouseInfoVO.setHouseNo(voter.getHouseNo());
			    	voterHouseInfoVO.setGaurdian(voter.getRelativeName());
			    	voterHouseInfoVO.setRelationship(voter.getRelationshipType());
			    	
			    	voterHouseInfoVO.setVoterId(voter.getVoterId());
			    	voterHouseInfoVO.setBoothId((Long)boothDAO.getBoothIdByPartNo(partNo.toString()).get(0));
			    	if(mobileMap.get(voter.getVoterId())!= null)
			    	 voterHouseInfoVO.setMobileNo(mobileMap.get(voter.getVoterId()));
			    	else
			    	 voterHouseInfoVO.setMobileNo("N/A");	 
			    	
			    	voterHouseInfoVO.setBoothName(partNo.toString());
			    	VoterHouseInfoVO voterCastPartyVO = voterCastePartyDetails.get(voter.getVoterId());
			    	
			    	setVotersCategories(votersCategoriesList,voter,voterHouseInfoVO);
			    	setCastePartyDetails(voterHouseInfoVO,voterCastPartyVO);
			    	
			    
			    	voterHouseInfoVOList.add(voterHouseInfoVO);
			    	//getUserCasteAndSelectedParty(voterHouseInfoVO , voter.getVoterId(),userId);
			    	
			    	
			    	sno = sno+1;
		    }
			
			}catch(Exception e){
				log.error("Exception raised in getFamilyInformation method");
				e.printStackTrace();
			}
			return voterHouseInfoVOList;
		}
		
		public void getVoterDetailsForHamletsInBooth(Long boothId,VotersInfoForMandalVO votersInfoForMandalVO1,Long publicationDateId, Long userId)
		{  
			
			
			List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>();
			   
			
			
			List<Object[]> hamletsList =	userVoterDetailsDAO.getTotalVotersCountInABooth(userId,boothId,publicationDateId);
					
			//int totalFemaleVoters = 0;
			//int totalMaleVoters = 0;
			//int totalUnknownVoters = 0;
			int totalVoters = 0;
		
			for(Object[] obj:hamletsList)
			{
				
				VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
				
			//	List<Object[]> hamletDetails = userVoterDetailsDAO.getVotersCountByGenderForHamlet((Long)obj[0]);
				
				
				totalVoters =Integer.parseInt(obj[1].toString()) + Integer.parseInt(obj[2].toString());
					
				//}
				
				votersInfoForMandalVO.setTotVoters(new BigDecimal(totalVoters));
				votersInfoForMandalVO.setTotalVoters(totalVoters+"") ;
				votersInfoForMandalVO.setTotalMaleVoters(obj[2].toString());
				votersInfoForMandalVO.setTotalFemaleVoters(obj[1].toString());
				votersInfoForMandalVO.setTotalVotersPercentage("0.00");
				//votersInfoForMandalVO.setUnKnowVoters(totalUnknownVoters.toString());
				votersInfoForMandalVO.setType("Hamlet");
				votersInfoForMandalVO.setName(obj[0].toString());
				
				
				if(totalVoters  == 0)
					votersInfoForMandalVO.setDatapresent(false);
				else
					votersInfoForMandalVOList.add(votersInfoForMandalVO);
			}
			
			votersInfoForMandalVO1.setVotersInfoForMandalVOList(votersInfoForMandalVOList);
			  calculatePercentage1(votersInfoForMandalVO1);
		}
		
		public List<SelectOptionVO> getPublicationListForVoterData(Long constituencyId)
		{
			List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
			
			try{
				List<Object[]> publicationDetails = boothPublicationVoterDAO.getPublicationDetailsBasedOnConstituency(constituencyId);
				if(publicationDetails != null && publicationDetails.size() > 0)
				{
					for(Object[] param : publicationDetails)
					{
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						Date date = (Date)param[1];
						selectOptionVO.setId((Long)param[0]);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						selectOptionVO.setName(calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR));
						selectOptionVOList.add(selectOptionVO);
					}
				}
				
				return selectOptionVOList;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getPublicationListForVoterData() Method, Exception - "+e);
				return selectOptionVOList;
			}
		}
		
		
		public void getVoterDetailsForHamletsByBooths(Long hamletId,VotersInfoForMandalVO votersInfoForMandalVO1,Long publicationDateId, Long userId,String type,Long constituencyId)
		{  
			
			
			List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>();
			   
			
			
			List<Object[]> hamletsList =	userVoterDetailsDAO.getTotalVotersCountInABoothForHamlet(userId,hamletId,publicationDateId,type,constituencyId);
					
			//int totalFemaleVoters = 0;
			//int totalMaleVoters = 0;
			//int totalUnknownVoters = 0;
			int totalVoters = 0;
		
			for(Object[] obj:hamletsList)
			{
				
				VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
				
			//	List<Object[]> hamletDetails = userVoterDetailsDAO.getVotersCountByGenderForHamlet((Long)obj[0]);
				
				
				totalVoters =Integer.parseInt(obj[1].toString()) + Integer.parseInt(obj[2].toString());
					
				//}
				
				votersInfoForMandalVO.setTotVoters(new BigDecimal(totalVoters));
				votersInfoForMandalVO.setTotalVoters(totalVoters+"") ;
				votersInfoForMandalVO.setTotalMaleVoters(obj[2].toString());
				votersInfoForMandalVO.setTotalFemaleVoters(obj[1].toString());
				votersInfoForMandalVO.setTotalVotersPercentage("0.00");
				//votersInfoForMandalVO.setUnKnowVoters(totalUnknownVoters.toString());
				votersInfoForMandalVO.setType("Booth");
				votersInfoForMandalVO.setName(obj[0].toString());
				
				
				if(totalVoters  == 0)
					votersInfoForMandalVO.setDatapresent(false);
				else
					votersInfoForMandalVOList.add(votersInfoForMandalVO);
			}
			
			votersInfoForMandalVO1.setVotersInfoForMandalVOList(votersInfoForMandalVOList);
			  calculatePercentage1(votersInfoForMandalVO1);
		}
	/*	public List<SelectOptionVO> getBoothsByHamletId(Long id,Long publicationDateId,Long userId)
		{
			List<SelectOptionVO> booths = new ArrayList<SelectOptionVO>();
			//List<Object[]> PollingBooths = hamletBoothPublicationDAO.getBoothsInPanchayatByPublicationId(id,publicationDateId);
			List<Object[]> PollingBooths =  userVoterDetailsDAO.getUserBoothsByHamletId(userId,id,publicationDateId);
			SelectOptionVO hamlet = null;
			for (Object[] panchayat : PollingBooths) {
				hamlet = new SelectOptionVO((Long)panchayat[0],panchayat[1].toString());
			//	hamlet.setLocation(panchayat[2] != null?panchayat[2].toString():"");
				//hamlet.setVillageCovered(panchayat[3] != null?panchayat[3].toString():"");
				booths.add(hamlet);
			}
			return booths;	
		}*/
		/*public List<VoterCastInfoVO> getVotersCastInfoForMultipleHamlets(List<Long> hamlets,Long publicationDateId,Long userId,Long totalVoters,String type , Long boothId)
		{
			VoterCastInfoVO voterCastInfo = null;
			
			List<VoterCastInfoVO> boothInfo = new ArrayList<VoterCastInfoVO>();
			if(hamlets != null)
			{
				for(Long hamletId :hamlets)
				{
					voterCastInfo = new VoterCastInfoVO();
					//Long hamletId=booths.getId();
					//String boothPartNo = booths.getId().toString();
					List<Object[]> hamletCastDetails=null;
					Long totalSubVoters=0l;
				//List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletIds(userId , hamlets);
					if(type.equalsIgnoreCase("booth")){
						 hamletCastDetails = boothPublicationVoterDAO
						.getCastAndGenderWiseVotersCountByPublicationIdInALocationByBooth(
								userId,"booth", hamletId, publicationDateId,
								boothId);
						 totalSubVoters = boothPublicationVoterDAO.getTotalVotersCountForHamletByBooth(userId,hamletId,publicationDateId,"",boothId);
						 
					}
						else if(type.equalsIgnoreCase("hamlet")){
				         hamletCastDetails = boothPublicationVoterDAO
						.getCastAndGenderWiseVotersCountByPublicationIdInALocation(
								userId,"hamlet", hamletId, publicationDateId,
								null);
			
				 totalSubVoters = boothPublicationVoterDAO
						.getTotalVotersCountForHamlet(userId, hamletId,
								publicationDateId,"hamlet");
						}
						else	if(type.equalsIgnoreCase("hamletBooths")){
						 hamletCastDetails = boothPublicationVoterDAO
						.getCastAndGenderWiseVotersCountByPublicationIdInALocationByBooth(
								userId,"booth", hamletId, publicationDateId,
								boothId);
						 totalSubVoters = boothPublicationVoterDAO.getTotalVotersCountForHamletByBooth(userId,hamletId,publicationDateId,"",boothId);
						 
					}
					
				voterCastInfo
						.setVoterCastInfoVO(calculatePercentageForUserCast(
								hamletCastDetails, totalVoters,totalSubVoters));

				
				voterCastInfo.setMandalName(hamletDAO.get(hamletId).getHamletName());
				voterCastInfo.setLocationId(hamletId);
				boothInfo.add(voterCastInfo);
				}
			}
			return boothInfo;
			
		}*/
	public void utilForPublicationVoterData(Long userId,Long publicationDateId,Long id ,VotersInfoForMandalVO votersInfoForMandalVO ){
		List<Object[]> obj= boothPublicationVoterDAO.getPublicationUserCount(userId,publicationDateId,id,IConstants.HAMLET);
		
		if(obj != null && obj.size() > 0)
		   {
			   for(Object[] params1 : obj){
				   votersInfoForMandalVO.setPublicationVoters((Long) params1[0]);
				   votersInfoForMandalVO.setAssignedVotersByUser((Long) params1[1]);
				   votersInfoForMandalVO.setUnassignedVotersByUser((Long) params1[2]);
				   
			   }
		   }else return;
	}
	
	public String checkLocalityDataExist(Long hamletId, Long userId,String type,Long publicationDateId)
	{
		try{
			String queryCond = null;
			if(type.equalsIgnoreCase(IConstants.HAMLET))
				queryCond =" model.hamlet.hamletId = :id ";
		    else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
					queryCond =" model.ward.constituencyId = :id ";
		    else if(type.equalsIgnoreCase("muncipalityCustomWard"))
		    {
		    	List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(hamletId.toString().substring(1)));
		    	hamletId = (Long)list.get(0);
		    	queryCond =" model.ward.localElectionBody.localElectionBodyId =:id ";
		    }
					
			List<Object[]> list = null;
			if(type.equalsIgnoreCase(IConstants.HAMLET))
			 list = localityDAO.getAllLocalitiesForHamlet(userId, hamletId,IConstants.HAMLET);
			else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD) || type.equalsIgnoreCase("muncipalityCustomWard"))
				list = userVoterDetailsDAO.getAllLocalitiesForHamletOrWard(type,userId, hamletId,publicationDateId, queryCond);
			if(list == null || list.size() == 0)
				return "false";
				return "true";
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in checkLocalityDataExist() Method, Exception - "+e);
				return "false";
			}
	}
	public List<VotersDetailsVO> getAgewiseVotersDetailsForHamletByBoothId(Long constituencyId,Long boothId,Long publicationDateId,Long userId,String type){
		List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
		
		
		
		//List<Object[]> list=    userVoterDetailsDAO.getAgeDataForBoothByHamlets(userId,publicationDateId,boothId,type);
		List<Object[]> list=    userVoterDetailsDAO.getAgeDataForBoothByHamlets(constituencyId,userId,publicationDateId,boothId,type,IConstants.MALE,IConstants.FEMALE,IConstants.AGE18,IConstants.AGE25,IConstants.AGE23,IConstants.AGE30,IConstants.AGE31,IConstants.AGE45,IConstants.AGE46,IConstants.AGE60);
	    if(list == null || list.size()==0)	
		{   
			return boothVotersList;	
		}else
		{
			helperBusinessDelegator(list,boothVotersList);
			getHamletWiseYoungVoterDetails(boothVotersList,constituencyId,userId,publicationDateId,boothId,type);
			//return boothVotersList;	
		}

		return boothVotersList;
		
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesDetailsForHamletFromBooth(
			Long userId, Long id, Long publicationDateId, Long constituencyId) {
		
		 ImportantFamiliesInfoVo importantFamiliesInfoVo  = new ImportantFamiliesInfoVo();
		
		log.debug("Entered into the getImportantFamiliesDetailsForHamletFromBooth service method");
		
		try{
			
			importantFamiliesInfoVo.setType("Booth");
		    importantFamiliesInfoVo.setName("Booth -" +boothDAO.get(id).getPartNo());
		    
		    
		   List<Long> hanletIds =  userVoterDetailsDAO.getHamletsExistedInABoothForUser(userId,id,publicationDateId,constituencyId);
		   
		   for(Long hamletId :hanletIds){

			   
				  List<Object[]> boothFamilyDetails =  boothPublicationVoterDAO.getFamiliesInBooth(userId ,hamletId, id , publicationDateId,constituencyId," model.hamlet.hamletId = :id ");
				  
				  ImportantFamiliesInfoVo importantFamiliesInfoVo1 = new ImportantFamiliesInfoVo();
				  
				  long upTo3 = 0;
				  long upTo3Count = 0;
				  long between4And6 = 0;
				  long between4And6Count = 0;
				  long between7And10 = 0;
				  long between7And10Count = 0;
				  long above10 = 0;
				  long above10Count = 0;						     

				  
				  for(Object[] details:boothFamilyDetails){
					  
					  long count =(Long)details[0];
					  
					  if(count <= 3 ){
						  upTo3++;
						  upTo3Count = upTo3Count + count;
					  }
					  else if(count > 3 && count <=6){
					      between4And6++;
					      between4And6Count = between4And6Count + count;
					  }
					  else if(count > 6 && count <10){
						  between7And10++;
						  between7And10Count =between7And10Count + count;
					  }
					  else{
					    above10++;
					    above10Count=above10Count + count;
					  }
					  
				  }
				  
	     List<Object[]> votersCount =  boothPublicationVoterDAO.getVotersCountByGenderInBooth(userId , hamletId , id , publicationDateId,constituencyId," model.hamlet.hamletId = :id ");
	     
	     
				      for(Object[] obj1:votersCount){
				    	  
				    	  if(obj1[1].toString().equalsIgnoreCase("M"))
				    		  importantFamiliesInfoVo1.setTotalMaleVoters(obj1[0].toString());
				    	  else if(obj1[1].toString().equalsIgnoreCase("F"))
				    		  importantFamiliesInfoVo1.setTotalFemaleVoters(obj1[0].toString());						    	  
				    	  
				      }

				  
				     importantFamiliesInfoVo1.setBelow3(upTo3);
				     importantFamiliesInfoVo1.setBelow3Popul(upTo3Count);					     
				     importantFamiliesInfoVo1.setBetwn4to6(between4And6);
				     importantFamiliesInfoVo1.setBetwn4to6Popul(between4And6Count);
				     importantFamiliesInfoVo1.setBetwn7to10(between7And10);
				     importantFamiliesInfoVo1.setBetwn7to10Popul(between7And10Count);
				     importantFamiliesInfoVo1.setAbove10(above10);
				     importantFamiliesInfoVo1.setAbove10Popul(above10Count);
				     
				     importantFamiliesInfoVo1.setTotalVoters(upTo3Count + between4And6Count + between7And10Count + above10Count);
				     
				     importantFamiliesInfoVo1.setName(hamletDAO.get(hamletId).getHamletName());
				     importantFamiliesInfoVo1.setType("hamlet");
				     
				     calculatePercentage(importantFamiliesInfoVo1);
				     
				     importantFamiliesInfoVo.getSubList().add(importantFamiliesInfoVo1);;
				
			   
		   }
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("Exception raised in  getImportantFamiliesDetailsForHamletFromBooth service method");		
		}
		
		return importantFamiliesInfoVo;
    }
	
	public ResultStatus updateVoterStatusInVoterModification(Long constituencyId)
	{
		ResultStatus resultstatus = new ResultStatus();
		Map<Long,Long> map = new HashMap<Long, Long>();
		try{
		List<Object[]> result = voterModificationDAO.getVoterModificationsByConstituencyId2(constituencyId);
		List<Long> add = new ArrayList<Long>(0);
		List<Long> del = new ArrayList<Long>(0);
		List<Long> mov = new ArrayList<Long>(0);
		List<Long> rel = new ArrayList<Long>(0);
		
		if(result != null && result.size() > 0)
		{
			for(Object[] params : result)
			{
				if(map.get((Long)params[1]) == null)
					map.put((Long)params[1],1L);
				else
					map.put((Long)params[1],2L);
			}
			
			for(Object[] params : result)
			{
				if(map.get((Long)params[1]).equals(1L))
				{
					if(params[2].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
						add.add((Long)params[0]);
					else if(params[2].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
						del.add((Long)params[0]);
				}
				else
				{
					if(params[2].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
						rel.add((Long)params[0]);
					else if(params[2].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
						mov.add((Long)params[0]);
				}
			}
			
			for(Long vid :add)
			{
				VoterModification modification = voterModificationDAO.get(vid);
				modification.setVoterStatusId(1l);
				voterModificationDAO.save(modification);
			}
			for(Long vid :del)
			{
				VoterModification modification = voterModificationDAO.get(vid);
				modification.setVoterStatusId(2l);
				voterModificationDAO.save(modification);
			}
			for(Long vid :mov)
			{
				VoterModification modification = voterModificationDAO.get(vid);
				modification.setVoterStatusId(3l);
				voterModificationDAO.save(modification);
			}
			for(Long vid :rel)
			{
				VoterModification modification = voterModificationDAO.get(vid);
				modification.setVoterStatusId(4l);
				voterModificationDAO.save(modification);
			}
			
		}
		return resultstatus;
		}catch (Exception e) {
			return null;
			
			
		}
	}
	
	 public VoterHouseInfoVO getSelectedVotersDetails(List<VoterHouseInfoVO> votersDetails , VoterHouseInfoVO parameters,Long userId)
	 { 
		 log.debug("Entered into the getSelectedVotersDetails service method");
		 
		 VoterHouseInfoVO votersHouseInfoVO = parameters;		 
		 try
		 {
			 Map<Long,Map<String, List<VoterHouseInfoVO>>> boothMap = new HashMap<Long,Map<String, List<VoterHouseInfoVO>>>();
			 Map<String, List<VoterHouseInfoVO>> voterByHouseNoMap = null;
			 List<VoterHouseInfoVO> voterVOs = null;
			 
			 VoterHouseInfoVO voterHouseInfoVO = null;
			 //GETTING  AREA TYPES  START SAMBA
			 
			 List<Long> locationValuesList = new ArrayList<Long>();
			// locationValuesList.add(locationValue);
			 
			 
			 List<Object[]> customGroups = null;
	           
			  if(parameters.isMandal()){
				  
				  locationValuesList.add(parameters.getGroupLocationValue()); 
				  
				  customGroups =   customVoterGroupDAO.getCustomVoterGroupsByLocationValueAndAreaTypeAndConstituencyId(parameters.getUserId(), locationValuesList,IConstants.AREA_TYPE_RURAL,parameters.getConstituencyId());

			  }else if(parameters.isMuncipality()){
				  
				  Long lid = (Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(parameters.getGroupLocationValue()).get(0); 
				  locationValuesList.add(lid);	
				  customGroups =   customVoterGroupDAO.getCustomVoterGroupsByLocationValueAndAreaTypeAndConstituencyId(parameters.getUserId(), locationValuesList,IConstants.AREA_TYPE_URBAN,parameters.getConstituencyId());		  
				  
			  }else  if(parameters.isConstituency()){
				  List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(parameters.getConstituencyId());
					Long delimitationConstituencyID = delimitationConstituency.get(0).getDelimitationConstituencyID();
					List<Tehsil> mandals = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);
					if(mandals != null && mandals.size() > 0)
					{		SelectOptionVO selectOptionVO = null;
						for (Tehsil tehsil : mandals)						
							locationValuesList.add(tehsil.getTehsilId());
						
					}	
					  customGroups =   customVoterGroupDAO.getCustomVoterGroupsByLocationValueAndAreaTypeAndConstituencyId(parameters.getUserId(), locationValuesList,IConstants.AREA_TYPE_RURAL,parameters.getConstituencyId());
			  }	
			 
			// List<Object[]> customGroups = customVoterGroupDAO.getCustomVoterGroupsByLocationValue(userId ,locationValuesList);
			 
			 List<SelectOptionVO> customGroupsList = new ArrayList<SelectOptionVO>();
			 SelectOptionVO defaultVo = new SelectOptionVO();
			 defaultVo.setId(0L);
			 defaultVo.setName("Select");
			 customGroupsList.add(defaultVo);
			 
			 for(Object[] obj:customGroups)
			 {
				 SelectOptionVO vo = new SelectOptionVO();
				 vo.setId((Long)obj[0]);
				 vo.setName(obj[1].toString());
				 customGroupsList.add(vo);
				 
			 }
			 
			 votersHouseInfoVO.setCustomGroups(customGroupsList);
			 
			 
			 //GETTING  AREA TYPES END
			 
			 
			 //GETTING  LOCALITIES DETAILS START			 
			    SelectOptionVO defaultSelectOptionVO1 = new SelectOptionVO();
	 		    defaultSelectOptionVO1.setId(0l);
		 	    defaultSelectOptionVO1.setName("0l");
		 	    defaultSelectOptionVO1.setValue("select");
		 	    
		 	    if(parameters.getSelType().equalsIgnoreCase("muncipality"))
			     buildLocalitiesBasedOnTypeAndIdForMuncipality(votersHouseInfoVO,
					parameters.getVoterId(), parameters.getUserId(),
					defaultSelectOptionVO1);
		 	    else			 	 
			      buildLocalitiesBasedOnTypeAndId(parameters, votersDetails
					.get(0).getVoterId(), parameters.getUserId(), defaultSelectOptionVO1);			
			 //GETTING  LOCALITIES DETAILS END
			
			
			//GETTING PARTIES AND CASTE  DETAILS START
			    SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
		 	    defaultSelectOptionVO.setId(0l);
		 	    defaultSelectOptionVO.setName("Select");		 	    
			    getPartiesAndCastsInVotersState(votersHouseInfoVO, votersDetails.get(0)
					.getVoterId(), parameters.getUserId(),defaultSelectOptionVO);
			//GETTING PARTIES AND CASTE  DETAILS END
			
			
			//GETTING USER CATEGORIES DETAIS START			
			   List<Object[]> userCategoryValuesList =  userVoterCategoryDAO
					.getCategoryValuesList(parameters.getUserId());
			
			   List<SelectOptionVO> categoriesList1 = new ArrayList<SelectOptionVO>();			
			   for(Object[] obj:userCategoryValuesList){
				SelectOptionVO vo = new SelectOptionVO();
				vo.setId((Long)obj[0]);
				vo.setName(obj[1].toString());				
				categoriesList1.add(vo);
			   }			
			   votersHouseInfoVO.setUserCategoriesList(categoriesList1);			
			//GETTING USER CATEGORIES DETAIS END
			
			 
			 for(VoterHouseInfoVO voter : votersDetails){
				 
				 voterHouseInfoVO = new VoterHouseInfoVO();
				/* getVoterBasicInfo1(voterHouseInfoVO,voter.getVoterId(),parameters.getPublicationId());
				 
				 voterByHouseNoMap = boothMap.get(voter.getBoothId());
				 
				 if(voterByHouseNoMap == null){
					 voterByHouseNoMap = new HashMap<String, List<VoterHouseInfoVO>>();
					 boothMap.put(voter.getBoothId(), voterByHouseNoMap);
				 }
				 
				 voterVOs =  voterByHouseNoMap.get(voterHouseInfoVO.getHouseNo());
				 
				 if(voterVOs == null){						 
					 voterVOs = new ArrayList<VoterHouseInfoVO>();
					 voterByHouseNoMap.put(voter.getHouseNo(), voterVOs);					 
				 }
					 
				 voterVOs.add(voterHouseInfoVO);*/
				 
				 getVoterBasicInfo1(voterHouseInfoVO,voter.getVoterId(),parameters.getPublicationId(),userId);
			 	   
			 	    voterByHouseNoMap = boothMap.get(voter.getBoothId());
					if( voterByHouseNoMap == null){
						voterByHouseNoMap = new HashMap<String, List<VoterHouseInfoVO>>();
						boothMap.put(voter.getBoothId(), voterByHouseNoMap);
					}
					voterVOs = voterByHouseNoMap.get(voterHouseInfoVO.getHouseNo());
					if(voterVOs ==null){
						voterVOs = new ArrayList<VoterHouseInfoVO>();
						voterByHouseNoMap.put(voterHouseInfoVO.getHouseNo(), voterVOs);
					}
					voterVOs.add(voterHouseInfoVO);
				 
		 	    // voterSelectedCastAndPartyDetails(voterHouseInfoVO,voter.getVoterId(),parameters.getUserId());
				 voterSelectedCastAndPartyDetailsWithSubLocalities(voterHouseInfoVO,voter.getVoterId(),parameters.getUserId());
		 	     
				 getVoterCustomGroupDetails(voterHouseInfoVO,voter.getVoterId(),parameters.getUserId());
		 	     
		 	    List<VoterHouseInfoVO> categoriesList = new ArrayList<VoterHouseInfoVO>();
		 	  // List<Object[]> selectedUserCategoryValuesList = new ArrayList<Object[]>();
			 	   
				getVoterSelectedCategoryValues(userCategoryValuesList,
						defaultSelectOptionVO, parameters.getUserId(), voter.getVoterId(),
						categoriesList);
		 	   
		 	     voterHouseInfoVO.setCategoriesList(categoriesList);
		 	     
		 	     
		 	    List<VoterHouseInfoVO> boothsList = new ArrayList<VoterHouseInfoVO>();
			 	  List<VoterHouseInfoVO> familiesList = null;
			 	    for(Long key:boothMap.keySet()){
			 	    	VoterHouseInfoVO booth = new VoterHouseInfoVO();
			 	    	boothsList.add(booth);
			 	    	booth.setBoothId(key);
			 	    	VoterHouseInfoVO boothInfo = getBoothDetailsForVoter(key);
			 	    	booth.setBoothName(boothInfo.getBoothName());
			 	    	booth.setPartNo(boothDAO.getPartNoByBoothId(key).get(0).toString());

			 	    	booth.setVilliageCovered(boothInfo.getVilliageCovered());
			 	    	booth.setPanchayatName(boothInfo.getPanchayatName());
			 	    	Map<String, List<VoterHouseInfoVO>> families = boothMap.get(key);
			 	    	familiesList = new ArrayList<VoterHouseInfoVO>();
			 	    	for(String familyKey:families.keySet()){
			 	    		VoterHouseInfoVO family = new VoterHouseInfoVO();
			 	    		familiesList.add(family);
			 	    		family.setHouseNo(familyKey);
			 	    		family.setVotersList(families.get(familyKey));
			 	    	}
			 	    	booth.setFamiliesList(familiesList);
			 	    }
			 	    
			 	   votersHouseInfoVO.setBoothsList(boothsList);
			 }
		 }
		 catch(Exception e)
		 {	
			 log.error("Exception raised in  getSelectedVotersDetails service method",e);

		 }
		 return votersHouseInfoVO;
	 
	 }
	
	
	public boolean updateAllSelectedVotersDetails(List<VoterHouseInfoVO> votersDetailsList , String isMuncipality)
	  {	
		  log.debug("Entered into the updateAllSelectedVotersDetails service method");
		  try
		  {		  
			  for(VoterHouseInfoVO voterDetails:votersDetailsList)
				  updateVoterInformation(voterDetails , isMuncipality);
			  
		  }catch(Exception e)
		  {	 
			  log.debug("Exception raised in updateAllSelectedVotersDetails service method");
			 e.printStackTrace(); 
			 return false;
		  }
		  return true;
	  }


	  public void updateVoterInformation(VoterHouseInfoVO votersDetails,String isMuncipality) throws Exception
	  {
		  log.debug("Entered into the updateVoterInformation service method");
		  try
		  {
			   Voter voter = voterDAO.get(votersDetails.getVoterId());
			   //voter.setMobileNo(votersDetails.getMobileNo());
			   voterDAO.save(voter);
			   User user = userDAO.get(votersDetails.getUserId());
			   
			   //SAVING user_voter_details TABLE DETAILS START
			  
				List<UserVoterDetails> userVoterDetailsList = userVoterDetailsDAO
						.getUserVoterDetails(votersDetails.getVoterId(),
								votersDetails.getUserId());
				
				UserVoterDetails userVoterDetails = null;
				if(userVoterDetailsList != null && userVoterDetailsList.size() >0)			
					 userVoterDetails = userVoterDetailsList.get(0);
				else
					userVoterDetails = new UserVoterDetails();
					
					userVoterDetails.setVoter(voter);
					userVoterDetails.setUser(user);
					userVoterDetails.setMobileNo(votersDetails.getMobileNo());
					
					if(votersDetails.getCasteStateId() != null && votersDetails.getCasteStateId().longValue() != 0)
					{
						userVoterDetails.setCasteState(casteStateDAO.get(votersDetails.getCasteStateId()));
						userVoterDetails.setCasteInsertType(casteInsertTypeDAO.get(1l));
					}
					else
					{
					 userVoterDetails.setCasteState(null);
					 userVoterDetails.setCasteInsertType(null);
					}
					
					if(votersDetails.getLocalitityId() != null && votersDetails.getLocalitityId().longValue() != 0)
					 userVoterDetails.setLocality(localityDAO.get(votersDetails.getLocalitityId()));
					else
					 userVoterDetails.setLocality(null);
					
					if(votersDetails.getHamletId() != null && votersDetails.getHamletId().longValue() != 0){
						if(isMuncipality.equalsIgnoreCase("muncipality")){
							userVoterDetails.setWard(constituencyDAO.get(votersDetails.getHamletId()));
							 userVoterDetails.setHamlet(null);
						}
						else{							
					       userVoterDetails.setHamlet(hamletDAO.get(votersDetails.getHamletId()));
					       userVoterDetails.setWard(null);
						}
					}
					else
					 userVoterDetails.setHamlet(null);
					
					if(votersDetails.getPartyId() != null && votersDetails.getPartyId().longValue() != 0)
						userVoterDetails.setParty(partyDAO.get(votersDetails.getPartyId()));
					else
					 userVoterDetails.setParty(null);
					userVoterDetailsDAO.save(userVoterDetails);
					
				//SAVING user_voter_details TABLE DETAILS END
					
				//SAVING voter_category_value TABLE DETAILS START
					
				Map<Long ,VoterCategoryValue > savedDetails = new HashMap<Long, VoterCategoryValue>();
					
				List<VoterCategoryValue> voterAllSavedCategories = voterCategoryValueDAO
						.getVoterAllCategoryValues(votersDetails.getUserId(),
								votersDetails.getVoterId());
				
				for(VoterCategoryValue category:voterAllSavedCategories){
					if(category.getUserVoterCategoryValue() != null)
					savedDetails.put(category.getUserVoterCategoryValue().getUserVoterCategoryValueId(), category);
				}
				
				Map<Long ,VoterHouseInfoVO > newDetails = new HashMap<Long, VoterHouseInfoVO>();
				
				for(VoterHouseInfoVO voterVO:votersDetails.getCategoriesList()){
					if(voterVO.getCategoryValuesId().longValue() != 0)
					newDetails.put(voterVO.getCategoryValuesId(), voterVO);
				}
				
				removeOrUpdateCustomVoterDetails(voter , votersDetails.getUserId() ,votersDetails.getCustomGroupId());
				
				//CHECKING ALL THE SAVED CATEGORIES ARE EXIST OTHER WISE DELETE THOSE 
				removeOrUpdateTheSavedVoterCategoryValues(savedDetails ,newDetails , voter , user );
				
				//SAVE NEWLY ADDED CATEGORY VALUES DETAILS
				
				saveNewlySelectedVoterCategoryValues(savedDetails ,newDetails , voter , user );
				
				
				//SAVING voter_category_value TABLE DETAILS END
				
		  }
		  catch(Exception e)
		  {
			  log.error("Exception raised in  updateVoterInformation service method");
			  e.printStackTrace();
			  throw new Exception();
			  
		  }
		  
	  }
	  
	  public void removeOrUpdateCustomVoterDetails(Voter voter , Long userId ,Long customGroupId)
	  {
		  log.debug("Entered into the removeOrUpdateCustomVoterDetails service method");
		try
		{
			List<CustomVoter> customVoters = null;
			
			//if(customGroupId.longValue() != 0 )
			 try
			 {
			  customVoters = customVoterDAO.getCustomVoterByVoterIdAndUserId(voter.getVoterId(),1L);
			 }catch(Exception e)
			 {}
			
			
			if(customGroupId.longValue() == 0&& customVoters != null && customVoters.size() >0)
			  customVoterDAO.removeCustomVoterDetails(customVoters.get(0).getCustomVoterId());
			
			else{	 
				CustomVoter customVoter = null;
				if(customVoters != null && customVoters.size() >0)
					customVoter = customVoters.get(0);				 
				else
					 customVoter = new CustomVoter();
				
				if(customGroupId.longValue() != 0){
				
				 customVoter.setVoter(voter);
				 customVoter.setCustomVoterGroup(customVoterGroupDAO.get(customGroupId));				
				 customVoterDAO.save(customVoter);
				}
			}
				
			
		}catch(Exception e)
		{
			log.debug("Exception raised  in the removeOrUpdateCustomVoterDetails service method");
			e.printStackTrace();
			
		}
		  
	  }

	  public void removeOrUpdateTheSavedVoterCategoryValues(
				Map<Long, VoterCategoryValue> savedDetails,
				Map<Long, VoterHouseInfoVO> newDetails, Voter voter, User user)  throws Exception{
			
			log.debug("Entered into the removeOrUpdateTheSavedVoterCategoryValues service method");
			
			try
			{	  
		        Set<Long> savedSet = savedDetails.keySet();
			
				for(Long voterCategoryValue:savedSet){
					
					VoterHouseInfoVO vo = newDetails.get(voterCategoryValue);
					/*if(vo != null)
					{
						VoterCategoryValue voterCategoryVal = savedDetails.get(voterCategoryValue);
						voterCategoryVal.setUser(user);
						voterCategoryVal.setVoter(voter);
						voterCategoryVal.setUserVoterCategoryValue(userVoterCategoryValueDAO.get(voterCategoryValue));
						voterCategoryValueDAO.save(voterCategoryVal);
						
					}
					else*/
					if(vo == null)
						//voterCategoryValueDAO.remove(savedDetails.get(voterCategoryValue).getVoterCategoryValueId());
						voterCategoryValueDAO.removeVoterCategoryValue(savedDetails.get(voterCategoryValue).getVoterCategoryValueId());
					
					newDetails.remove(voterCategoryValue);				

				}
			}
			catch(Exception e)
			{
				log.error("Exception raised in  removeOrUpdateTheSavedVoterCategoryValues service method");
				e.printStackTrace();
				throw new Exception();
				
			}
	  }

	   public void saveNewlySelectedVoterCategoryValues(
				Map<Long, VoterCategoryValue> savedDetails,
				Map<Long, VoterHouseInfoVO> newDetails, Voter voter, User user)  throws Exception
	  {
		  log.debug("Entered into the saveNewlySelectedVoterCategoryValues service method");
		  try
		  {
		
		    Set<Long> newSet = newDetails.keySet();
			
			for(Long categoryValueId:newSet){
				
				  VoterHouseInfoVO vo = newDetails.get(categoryValueId);
				
				  VoterCategoryValue voterCategoryVal = new VoterCategoryValue();
				  voterCategoryVal.setUser(user);
				  voterCategoryVal.setVoter(voter);
				  voterCategoryVal.setUserVoterCategoryValue(userVoterCategoryValueDAO.get(categoryValueId));
				  voterCategoryValueDAO.save(voterCategoryVal);
			}
		  }
		  catch(Exception e)
		  {
			  log.error("Exception raised in saveNewlySelectedVoterCategoryValues service method");
			  e.printStackTrace();
			  throw new Exception();
		  }
		  
	  }
	   
	   public void getVoterCustomGroupDetails(VoterHouseInfoVO voterHouseInfoVO , Long voterId , Long userId)
	   {
		  List<Long> list =  customVoterDAO.getCustomGroupIdByVoterIdAndUserId(voterId , userId);
		  
		  if(list != null && list.size() >0)
			  voterHouseInfoVO.setCustomGroupId(list.get(0));
		   
	   }

	  public void voterSelectedCastAndPartyDetailsWithSubLocalities(VoterHouseInfoVO voterHouseInfoVO,Long voterId,Long userId){
		
		  List<UserVoterDetails> userVoterDetailsList = userVoterDetailsDAO.getUserVoterDetails(voterId,userId);
			if(userVoterDetailsList != null && userVoterDetailsList.size() >0){
				UserVoterDetails userVoterDetails = userVoterDetailsList.get(0);
				if(userVoterDetails.getParty() != null){
					voterHouseInfoVO.setPartyId(userVoterDetails.getParty().getPartyId());
				}else{
					voterHouseInfoVO.setPartyId(0l);
				}
				if(userVoterDetails.getCasteState() != null){
					voterHouseInfoVO.setCasteStateId(userVoterDetails.getCasteState().getCasteStateId());
				}
				
				if(userVoterDetails.getHamlet() !=null)
				{
					voterHouseInfoVO.setHamletId(userVoterDetails.getHamlet().getHamletId());
					
					List<Object[]> localities =   getLocalitiesForHamlet(userVoterDetails.getHamlet().getHamletId(),userId);
					 List<SelectOptionVO> localitiesList = new ArrayList<SelectOptionVO>();
					 processLocalities1(localities, localitiesList);
					 
					 SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
					   defaultSelectOptionVO.setId(0l);
					   defaultSelectOptionVO.setName("Select");
					   defaultSelectOptionVO.setValue("Select");
					   
					   sortSelectionOptionVoList(localitiesList);
					   localitiesList.add(0, defaultSelectOptionVO);
					   
					   voterHouseInfoVO.setSubLocalities(localitiesList);
					
				}
				
				if(userVoterDetails.getWard() != null)
				{
					voterHouseInfoVO.setHamletId(userVoterDetails.getWard().getConstituencyId());
					if(userVoterDetails.getWard().getLocalElectionBody() != null)
					{
					List<SelectOptionVO> localities =   getLocalitiesForWards(userVoterDetails.getWard().getLocalElectionBody().getLocalElectionBodyId(),userId,"model.localElectionBody.localElectionBodyId =:id");					
					   
					   voterHouseInfoVO.setSubLocalities(localities);
					
				
					}
				}
				
				/*else
					voterHouseInfoVO.setCasteStateId(0l);*/
				
				if(userVoterDetails.getLocality() != null)
					voterHouseInfoVO.setSubLocalityId(userVoterDetails.getLocality().getLocalityId());
				
				
			}else{
				
				voterHouseInfoVO.setPartyId(0l);
				voterHouseInfoVO.setCasteStateId(0l);
			}
	} 
	  
	  public void processLocalities1(List<Object[]> localities , List<SelectOptionVO> localitiesList ){
			
		  for (Object[] a : localities)
		    {  SelectOptionVO sv =new  SelectOptionVO();
			   // a[0]="x"+a[0];
			   // sv.setId((Long)a[0]);
		       sv.setId(new BigInteger(a[0].toString()).longValue());
			    sv.setName(a[1]!=null?a[1].toString():"");
			    sv.setValue(a[1]!=null?a[1].toString():"");
			    localitiesList.add(sv);
			 
	        	}
	}
	
	public List<Long> getCountOfHamletAndBoothsInAPanchayat(Long panchayatId)
	{
		List<Long> count  = new ArrayList<Long>();
		Long boothsCount = null;
		List<Object> hamletsCount = null;
		boothsCount = boothDAO.getBoothsInPanchayatDAO(panchayatId);
		if(boothsCount != null)
		{
			count.add(boothsCount);
		}
		else
			count.add(0l);
		hamletsCount = panchayatHamletDAO.getHamletsCountOfAPanchayat(panchayatId);
		if(hamletsCount != null && hamletsCount.size() > 0)
		{
			Long hCount = (Long)(hamletsCount.get(0));
			count.add(hCount);
		}
		else
			count.add(0l);
		return count;
		
	}

	/**
	 * this service is used for getting all voter data for populating the voter details in panchayat ,booth and hamlet lavel
	 * @param VoterDataVO voterDataVO
	 * @param Long userId
	 * @param List<Long> categories
	 * @return List<VoterVO>
	 * @date 20-04-2013
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public List<VoterVO> getVoterData(VoterDataVO voterDataVO , Long userId , List<Long> categories)
	{
		List<VoterVO> voterData = new ArrayList<VoterVO>();
		List<Object[]> voters = null;
		VoterVO voterVO = null;
		List<Long> voterIds = new ArrayList<Long>();
		Long totalCount = 0l;
		Map<Long , VoterVO> voterMap = new HashMap<Long, VoterVO>();
		try {
			log.debug("entered into the getVoterData() method in VotersAnalysisSevice");
			if(voterDataVO.getBuildType().equalsIgnoreCase("panchayat"))
			{
				/*voters     = new ArrayList<Object[]>();
				voters     = boothPublicationVoterDAO.getVotersDetailsForPanchayatByPublicationId(voterDataVO.getId() , voterDataVO.getPublicationId() ,voterDataVO.getStartIndex().intValue(), voterDataVO.getMaxIndex().intValue() , voterDataVO.getDir(),voterDataVO.getSort() );
				totalCount = (Long) boothPublicationVoterDAO.getVotersCountForPanchayat(voterDataVO.getId() , voterDataVO.getPublicationId()).get(0);
		*/
				voterData = voterReportService.getVoterDataForPanchayat(voterDataVO , userId , categories ,voterDataVO.getSearchColumnName(), voterDataVO.getSearchString());
				return voterData;
			}
			else if(voterDataVO.getBuildType().equalsIgnoreCase("booth"))
			{
				voters     = new ArrayList<Object[]>();
				
				voterData = voterReportService.getVoterDataForBooth(voterDataVO.getId() ,userId,voterDataVO.getStartIndex().intValue(), voterDataVO.getMaxIndex().intValue() , voterDataVO.getDir(),voterDataVO.getSort(),categories ,voterDataVO.getSearchColumnName(), voterDataVO.getSearchString());
				
				return voterData;
				
				/*voters     = boothPublicationVoterDAO.getVotersDetailsByBoothId(voterDataVO.getId() ,voterDataVO.getStartIndex().intValue(), voterDataVO.getMaxIndex().intValue() , voterDataVO.getDir(),voterDataVO.getSort());
				totalCount = (Long) boothPublicationVoterDAO.getVotersCountByBoothId(voterDataVO.getId()).get(0);
		*/	}
			else if (voterDataVO.getBuildType().equalsIgnoreCase("hamlet"))
			{
				voters     = new ArrayList<Object[]>();	
				voterData  = voterReportService.getVoterDataForHamlet(voterDataVO , userId , categories ,voterDataVO.getSearchColumnName(), voterDataVO.getSearchString());
				return voterData;
				
				
				/*voters     =  new ArrayList<Object[]>();
				List<?> votersList1 = userVoterDetailsDAO.getVotersDetailsByHamletPublication(voterDataVO.getId(), userId,voterDataVO.getStartIndex().intValue(),voterDataVO.getMaxIndex().intValue(),voterDataVO.getDir(),voterDataVO.getSort());
				voters     =  (List<Object[]>) userVoterDetailsDAO.getVotersBasedOnVoterIdsAndPublication(voterDataVO.getPublicationId(),votersList1,voterDataVO.getSort(),voterDataVO.getDir());
				totalCount =  (Long) userVoterDetailsDAO.getVotersCountByHamlet(voterDataVO.getId(),userId).get(0);
		*/	}
			
			else if(voterDataVO.getBuildType().equalsIgnoreCase("customWard"))
			{
				
				
				/*voters = boothPublicationVoterDAO
							.getVotersDetailsByCustomWardId(voterDataVO.getId(),voterDataVO.getPublicationId(),voterDataVO.getConstituencyId(),userId,voterDataVO.getStartIndex().intValue(), voterDataVO.getMaxIndex().intValue(), voterDataVO.getDir(), voterDataVO.getSort());
				 totalCount=new Long(voters.size());*/
				voterData =  voterReportService.getVoterDataForWard(voterDataVO , userId , categories ,voterDataVO.getSearchColumnName(), voterDataVO.getSearchString());
				return voterData;
			}
			
			List<Long> voterIdsList = new ArrayList<Long>(0);
			Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
			if(voters != null && voters.size() > 0)
			 for(Object[] params:voters)
			 {
				Voter voter = (Voter)params[0];
				voterIdsList.add(voter.getVoterId());
			 }
			if(voterIdsList != null && voterIdsList.size() > 0)
			{
			  List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIdsList, userId);
			  for(Object[] params:list)
				mobileNosMap.put((Long)params[0], params[1] != null?params[1].toString():"N/A");
			}
			
			if(voters != null && voters.size() > 0)
			{
				for (Object[] voterDetails : voters) {
					Voter voterInfo = (Voter) voterDetails[0];
					
					voterVO = new VoterVO();
					voterVO.setVoterId(voterInfo.getVoterIDCardNo());
					voterVO.setName(voterInfo.getName());
					voterVO.setGender(voterInfo.getGender());
					voterVO.setAge(voterInfo.getAge());
					voterVO.setHouseNo(voterInfo.getHouseNo());
					if(mobileNosMap.get(voterInfo.getVoterId()) != null)
					 voterVO.setMobileNo(mobileNosMap.get(voterInfo.getVoterId()));
					else
						voterVO.setMobileNo("N/A");
					voterVO.setRelativeFirstName(voterInfo.getRelativeName());
					voterVO.setPartNo(Long.valueOf(voterDetails[1].toString()));
					voterVO.setTotalVoters(totalCount);
					voterVO.setVoterIds(voterInfo.getVoterId());
					voterIds.add(voterInfo.getVoterId());
					voterMap.put(voterInfo.getVoterId(), voterVO);
					voterData.add(voterVO);
					//voterVO.setPartNo(Long.valueOf(voterDetails[1].toString()));
					voterVO.setSerialNo((Long)voterDetails[2]);
				}
			}
			
			List<Long> influencingPeopleList = influencingPeopleDAO.findInfluencingPeopleDetails(voterIds,userId);
			if(influencingPeopleList != null && influencingPeopleList.size() > 0)
			{
				
				for (Long influencingPeople : influencingPeopleList) {
					if(influencingPeople != null)
					{
						voterVO = voterMap.get(influencingPeople);
						voterVO.setInfluencePerson(true);
					}
				}
			}
			
			List<Long> cadrePeopleList = cadreDAO.findCadrePeopleDetails(voterIds,userId);
			if(cadrePeopleList != null && cadrePeopleList.size() > 0)
			{
				for (Long cadrePeople : cadrePeopleList) {
					if(cadrePeople != null)
					{
						voterVO = voterMap.get(cadrePeople);
						voterVO.setIsCadrePerson(true);
					}
				}
			}
			
			List<Long> candidatePeopleList = candidateDAO.findCandidatePeopleDetails(voterIds);
			if(candidatePeopleList != null && candidatePeopleList.size() > 0)
			{
				for (Long candidatePeople : candidatePeopleList) {
					if(candidatePeople != null)
					{
						voterVO = voterMap.get(candidatePeople);
						voterVO.setIsPoliticion(true);
					}
				}
			}
			if(voterDataVO.getPartyPresent() || voterDataVO.getCastePresent())
			{
				List<UserVoterDetails> votersPartyCastList = userVoterDetailsDAO.getAllUserVoterDetails(voterIds,userId);
				if(votersPartyCastList != null && votersPartyCastList.size() > 0)
				{
					for (UserVoterDetails voterPartyAndCasteDetails : votersPartyCastList) {
						voterVO = voterMap.get(voterPartyAndCasteDetails.getVoter().getVoterId());
						if(voterPartyAndCasteDetails != null)
						{
							
							voterVO.setPartyName(voterPartyAndCasteDetails.getParty()!=null ? voterPartyAndCasteDetails.getParty().getShortName():"");
							voterVO.setCasteName(voterPartyAndCasteDetails.getCasteState().getCaste() != null ? voterPartyAndCasteDetails.getCasteState().getCaste().getCasteName():"");
						}
						else
						{
							voterVO.setPartyName("");
							voterVO.setCasteName("");
						}
					}
				}
			}
			if(categories != null && categories.size() > 0){
				VoterVO category = null;
				 List<Object[]> votersPartyCastList = voterCategoryValueDAO.getVoterCategoryValuesForVoters(userId,voterIds);
			     for(Object[] voterDetails:votersPartyCastList){
			    	 VoterVO voterObj = voterMap.get((Long)voterDetails[0]);
			    	 if(voterObj != null){
			    		 List<VoterVO> categoriesList = voterObj.getCategoriesList();
			    		 if(categoriesList == null){
			    			 categoriesList = new ArrayList<VoterVO>();
			    			 voterObj.setCategoriesList(categoriesList);
			    		 }
			    		  category = new VoterVO();
			    		  categoriesList.add(category);
			    		  category.setCategoryValuesId((Long)voterDetails[1]);
			    		  category.setName(voterDetails[2]!=null?voterDetails[2].toString():"");
			    	 }
			     }
			 }
		} catch (Exception e) {
			log.error("error occured in the getVoterData() method in VotersAnalysis" , e) ;
		}
		return voterData;
	}
	/**
	 * This service is used for getting all categoery values for given user id and categoeri id
	 * @param Long userId
	 * @param Long categoeryId
	 * @return List<SelectOptionVO>
	 * @date 24-04-2013
	 */
	public List<SelectOptionVO> getCategoeryValuesService(Long userId,Long categoeryId)
	{
		SelectOptionVO selectOptionVO =  null;
		List<SelectOptionVO> retuenValue = new ArrayList<SelectOptionVO>();
		List<Object[]> categoeryValues = userVoterCategoryValueDAO.getCategoeryValuesDAO(userId, categoeryId);
		if(categoeryValues != null && categoeryValues.size() > 0)
		{
			for (Object[] parms : categoeryValues) {
				selectOptionVO =  new SelectOptionVO();
				selectOptionVO.setId((Long)parms[0]);
				selectOptionVO.setName(parms[1].toString());
				selectOptionVO.setOrderId((Long) parms[2]);
				retuenValue.add(selectOptionVO);
			}
		}
		return retuenValue;
	}
	/**
	 * This Service is used for storing list of categoery values 
	 * @param List<SelectOptionVO>
	 * @param Long
	 * @param Long
	 * @return ResultStatus
	 * @date 24-04-2013
	 */
	public ResultStatus storeCategoeryData(List<SelectOptionVO> categoeryValues,Long userId,Long categoeryId) {
		ResultStatus resultStatus = new ResultStatus();
		try {
			log.debug("Entered into the storeCategoeryData() of VotersAnalysis Service Class");
			if(categoeryValues != null)
			{
				for (SelectOptionVO parms : categoeryValues) {
				UserVoterCategoryValue userVoterCategoryValue = null;
				if(parms.getId() != null && parms.getId() > 0l)
				{
					userVoterCategoryValue = userVoterCategoryValueDAO.get(parms.getId());
					
				}
					
				else
				{
					userVoterCategoryValue = new UserVoterCategoryValue();
				}
				
					userVoterCategoryValue.setUserVoterCategoryValueId(parms.getId());
					userVoterCategoryValue.setCategoryValue(parms.getValue());
					userVoterCategoryValue.setOrderNo(parms.getOrderId());
					userVoterCategoryValue.setUser(userDAO.get(userId));
					userVoterCategoryValue.setUserVoterCategory(userVoterCategoryDAO.get(categoeryId));
					userVoterCategoryValueDAO.save(userVoterCategoryValue);
				
				}
				
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			resultStatus.setMessage("Categoery Values Saved Successfully");
			return resultStatus;
		} catch (Exception e) {
			log.error("Exception raised in getCategoeryValuesService() method in VotersAnalysis Service", e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setMessage("Categoery Values Not Saved");
			return resultStatus;
		}
		
	}
	/**
	 * This Service is used for checking the categoery values are alredy assigned to voter or not if not 
	 * assigned then delete those categores
	 * @param List<SelectOptionVO>
	 * @param Long
	 * @return List<SelectOptionVO>
	 * @date 24-04-2013
	 */
	public List<SelectOptionVO> checkForCategoeryValues(List<SelectOptionVO> categoeryValuesList , Long userId)
	{
		List<SelectOptionVO> returnValues = new ArrayList<SelectOptionVO>();
		try {
			log.debug("enterd into the checkForCategoeryValues() method in VotersAnalysis Service");
			SelectOptionVO selectOptionVO = null;
			List<Long> categoeryIds = new ArrayList<Long>();
			for (SelectOptionVO categoeryList : categoeryValuesList) {
				
				categoeryIds.add(categoeryList.getId());
			}
			List<Long> categoeryValuesIds = new ArrayList<Long>();
			List<Long> delCategoeryIds = new ArrayList<Long>();
			List<Object[]> categoeryList = voterCategoryValueDAO.checkCategoeryValues(categoeryIds, userId);
			if(categoeryList != null && categoeryList.size() > 0)
			{
				for (Object[] parms : categoeryList) {
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setName(parms[0].toString());
					categoeryValuesIds.add((Long)parms[1]);
					returnValues.add(selectOptionVO);
				}
				categoeryIds.removeAll(categoeryValuesIds);
				for (Long list : categoeryIds) {
					delCategoeryIds.add(list);
				}
				if(delCategoeryIds != null && delCategoeryIds.size() > 0)
				{
					userVoterCategoryValueDAO.deleteCategoeryValues(delCategoeryIds);
				}
			}
			else
			{
				userVoterCategoryValueDAO.deleteCategoeryValues(categoeryIds);
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setValue("deleted");
				returnValues.add(selectOptionVO);
			}
		} catch (Exception e) {
			log.error("Exception Raised in the checkForCategoeryValues() method in VotersAnalysis Service" , e);
		}
		
		return returnValues;
	}
	
	public VotersInfoForMandalVO getVotersCountForCustomWardBooths(Long wardId,Long publicationDateId,Long constituencyId,Long userId)
	{
		VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
		try{
			List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>(0);
			List<Object[]> list = boothPublicationVoterDAO.getVotersCountForCustomWardBooths(constituencyId, wardId, publicationDateId, userId);
			Long wardTotalVoters = 0L ;	
			if(list != null && list.size() > 0)
				{
					VotersInfoForMandalVO mandalVO = null;
					for(Object[] params : list)
					{
						mandalVO = checkVotersInfoForMandalVOExist(params[3].toString(),votersInfoForMandalVOList);
						if(mandalVO == null)
						{
							mandalVO = new VotersInfoForMandalVO();
							mandalVO.setName(params[3] != null ?"booth-"+params[3].toString():"");
							mandalVO.setId((Long)params[2]);
							mandalVO.setType("Booth");
							votersInfoForMandalVOList.add(mandalVO);
						}
						if(params[1] != null && params[1].toString().equalsIgnoreCase("M"))
						  mandalVO.setTotalMaleVoters(params[0] != null ?params[0].toString():"0");
						else if(params[1] != null && params[1].toString().equalsIgnoreCase("F"))
						  mandalVO.setTotalFemaleVoters(params[0] != null ?params[0].toString():"0");
						else
						  mandalVO.setUnKnowVoters(params[0] != null ?params[0].toString():"0");
				    }
					
					votersInfoForMandalVO.setVotersInfoForMandalVOList(votersInfoForMandalVOList);
					
					for(VotersInfoForMandalVO vO :votersInfoForMandalVOList)
					{
						Long maleVotersCount = 0L;
						Long femaleVoters = 0L;
						Long unknowVoters = 0L;
						if(vO.getTotalMaleVoters() != null)
							maleVotersCount = new Long(vO.getTotalMaleVoters());
						if(vO.getTotalFemaleVoters() != null)
							femaleVoters = new Long(vO.getTotalFemaleVoters());
						if(vO.getUnKnowVoters() != null)
							unknowVoters = new Long(vO.getUnKnowVoters());
						
						vO.setTotVoters(new BigDecimal(maleVotersCount.longValue()+femaleVoters.longValue()+ unknowVoters.longValue()));
						wardTotalVoters += (maleVotersCount.longValue()+femaleVoters.longValue()+ unknowVoters.longValue());
					}
				}
				votersInfoForMandalVO.setName(constituencyDAO.get(wardId).getName());
				votersInfoForMandalVO.setType("Ward");
				votersInfoForMandalVO.setTotalVoters(wardTotalVoters.toString());
				votersInfoForMandalVO.setTotVoters(new BigDecimal(wardTotalVoters.longValue()));
				calculatePercentage(votersInfoForMandalVO);
		return votersInfoForMandalVO;
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getVotersCountForCustomWardBooths() Method, Exception is - "+e);
			return votersInfoForMandalVO;
		}
	}
	
	public VotersInfoForMandalVO checkVotersInfoForMandalVOExist(String partNo, List<VotersInfoForMandalVO> list)
	{
		try{
			if(list == null || list.size() == 0)
				return null;
			for(VotersInfoForMandalVO mandalVO : list)
				if(mandalVO.getName() != null && mandalVO.getName().equalsIgnoreCase("booth-"+partNo))
					return mandalVO;
			return null;
		}catch (Exception e) {
			log.error("Exception Occured in checkVotersInfoForMandalVOExist() method, Exception - "+e);
			return null;
		}
	}
		
	public Long getLatestPublicationId(){
		try{
		return publicationDateDAO.getLatestPublicationId();
		}catch(Exception e){
			log.error("Exception Raised in the getLatestPublicationId() method in VotersAnalysis Service" , e);
			return 8l;
		}
	}
	public List<SelectOptionVO> getCasteWisePercentage(List<VoterCastInfoVO> list)
	{
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>(0);
		try{
			if(list == null || list.size() == 0)
				return result;
			Map<String,Long> castVotes = new HashMap<String, Long>(0);
			Long totalVoters = 0L;
			for(VoterCastInfoVO voterCastInfoVO : list)
			{
				for(CastVO castVO : voterCastInfoVO.getVoterCastInfoVO().getCastVOs())
				{
					Long casteCount = castVotes.get(castVO.getCastName());
					totalVoters = totalVoters + castVO.getCastCount();
					if(casteCount == null)
						castVotes.put(castVO.getCastName(), castVO.getCastCount());
					else
						castVotes.put(castVO.getCastName(), castVO.getCastCount().longValue()
								+casteCount.longValue());
				}
			}
			
			for(Map.Entry<String,Long> values : castVotes.entrySet())
			{
				result.add(new SelectOptionVO(values.getValue()*100/totalVoters,values.getKey()));
			}
			return result;
		}catch (Exception e) {
			log.error("Exception Occured in getCasteWisePercentage() Method",e);
			return result;
		}
	}
	public ConstituencyManagementVO getCasteWisePercentsInLocations(List<VoterCastInfoVO> list){
		CastLocationVO castLocationVO;
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>(0);
		ConstituencyManagementVO constituencyManagementVO=new ConstituencyManagementVO();
		List<Long> castes_uniqueId=new ArrayList<Long>();
		
		List<String> locNames=new ArrayList<String>();
		List<Long> castCount_unique=null;
		boolean castExist;
		List<CastLocationVO> castLocationVOs=new ArrayList<CastLocationVO>();
		List<CastLocationVO> castLocationVOsForSort=new ArrayList<CastLocationVO>();
		
		try{
			
			if(list != null || list.size() != 0)
			{
			for(VoterCastInfoVO voterCastInfoVO : list)
			{
				locNames.add(voterCastInfoVO.getMandalName());
								
				for(CastVO castVO : voterCastInfoVO.getVoterCastInfoVO().getCastVOs())
				{
					//castes_unique.add(castVO.getCastName());
					if(!castes_uniqueId.contains(castVO.getCastStateId())){
						castes_uniqueId.add(castVO.getCastStateId());
						castLocationVO=new CastLocationVO();
						castLocationVO.setCaste(castVO.getCastName());
						castLocationVO.setCasteStateId(castVO.getCastStateId());
						castLocationVOsForSort.add(castLocationVO);
					}
				}
			}
			
			Collections.sort(castLocationVOsForSort,sortCaste);			
			Collections.sort(locNames);
			
			Long cstId=null;
			String cstName="";
			for(CastLocationVO cstLocationVO:castLocationVOsForSort){
				cstId=cstLocationVO.getCasteStateId();
				cstName=cstLocationVO.getCaste();
				castCount_unique=new ArrayList<Long>();
				String caste="";
				for(String location:locNames){
					for(VoterCastInfoVO voterCastInfoVO:list){
						if(voterCastInfoVO.getMandalName().equalsIgnoreCase(location)){
							castExist=false;
							for(CastVO castVO : voterCastInfoVO.getVoterCastInfoVO().getCastVOs())
							{
								if(castVO.getCastStateId().equals(cstId)){
									castCount_unique.add(castVO.getCastCount());
									caste=castVO.getCastName();
									castExist=true;
									break;
								}
							}
							if(!castExist){
								castCount_unique.add(null);
							}
						}
					}
					
				}
				castLocationVO = new CastLocationVO();
				//castes_unique.add(caste);
				castLocationVO.setCaste(caste);
				castLocationVO.setLocationWiseCastesCount(castCount_unique);
				castLocationVOs.add(castLocationVO);
			}

			
			constituencyManagementVO.setLocations(locNames);
			constituencyManagementVO.setLocWiseCastePrcts(castLocationVOs);
			
			//result.add(new SelectOptionVO(locNames,castes_unique,cstePrcntInLoc));
			//constituencyManagementVO.setCstWisePrcntsInLctns(result);
			}
		}catch (Exception e) {
			log.error("Exception Occured in getCasteWisePercentage() Method",e);
		}
		return constituencyManagementVO;
	}
	
	public List<SelectOptionVO> getMandalsInConstituency(Long constituencyId)
	{
		List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		try{
			list = regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, null);
			if(list != null && list.size() > 0)
				list = getMandals(list);
		}
		catch(Exception e)
		{
			log.error("Exception Occured in getCasteWisePercentage() Method",e);
		}
		return list;
	}

	public ImportantFamiliesInfoVo getImportantFamiliesDetailsForWardFromMuncipality(
			Long userId, Long id, Long publicationDateId, Long constituencyId ,String  type) {
		
		 ImportantFamiliesInfoVo importantFamiliesInfoVo  = new ImportantFamiliesInfoVo();
		
		log.debug("Entered into the getImportantFamiliesDetailsForWardFromMuncipality service method");
		
		try{
			
			importantFamiliesInfoVo.setType("MuniciPality");
			      
			  Long lid = (Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(id).get(0);

		    importantFamiliesInfoVo.setName(localElectionBodyDAO.get(lid).getName());
		    
		    
		   List<Object[]> hanletIds =  userVoterDetailsDAO.getWardsBYLocalElectionBodyId(lid,publicationDateId,userId);
		   
		   for(Object[] hamletId1 :hanletIds){
            Long hamletId =(Long) hamletId1[0];
			   
				  List<Object[]> boothFamilyDetails =  boothPublicationVoterDAO.getFamiliesInWard(userId ,hamletId,  publicationDateId,constituencyId,type);
				  
				  ImportantFamiliesInfoVo importantFamiliesInfoVo1 = new ImportantFamiliesInfoVo();
				  
				  long upTo3 = 0;
				  long upTo3Count = 0;
				  long between4And6 = 0;
				  long between4And6Count = 0;
				  long between7And10 = 0;
				  long between7And10Count = 0;
				  long above10 = 0;
				  long above10Count = 0;						     

				  
				  for(Object[] details:boothFamilyDetails){
					  
					  long count =(Long)details[0];
					  
					  if(count <= 3 ){
						  upTo3++;
						  upTo3Count = upTo3Count + count;
					  }
					  else if(count > 3 && count <=6){
					      between4And6++;
					      between4And6Count = between4And6Count + count;
					  }
					  else if(count > 6 && count <10){
						  between7And10++;
						  between7And10Count =between7And10Count + count;
					  }
					  else{
					    above10++;
					    above10Count=above10Count + count;
					  }
					  
				  }
				  
	     List<Object[]> votersCount =  boothPublicationVoterDAO.getVotersCountByGender(userId , hamletId , publicationDateId,constituencyId,type);
	     
	     
				      for(Object[] obj1:votersCount){
				    	  
				    	  if(obj1[1].toString().equalsIgnoreCase("M"))
				    		  importantFamiliesInfoVo1.setTotalMaleVoters(obj1[0].toString());
				    	  else if(obj1[1].toString().equalsIgnoreCase("F"))
				    		  importantFamiliesInfoVo1.setTotalFemaleVoters(obj1[0].toString());						    	  
				    	  
				      }

				  
				     importantFamiliesInfoVo1.setBelow3(upTo3);
				     importantFamiliesInfoVo1.setBelow3Popul(upTo3Count);					     
				     importantFamiliesInfoVo1.setBetwn4to6(between4And6);
				     importantFamiliesInfoVo1.setBetwn4to6Popul(between4And6Count);
				     importantFamiliesInfoVo1.setBetwn7to10(between7And10);
				     importantFamiliesInfoVo1.setBetwn7to10Popul(between7And10Count);
				     importantFamiliesInfoVo1.setAbove10(above10);
				     importantFamiliesInfoVo1.setAbove10Popul(above10Count);
				     
				     importantFamiliesInfoVo1.setTotalVoters(upTo3Count + between4And6Count + between7And10Count + above10Count);
				     
				     importantFamiliesInfoVo1.setName(constituencyDAO.get(hamletId).getName());
				     importantFamiliesInfoVo1.setType("Ward");
				     
				     calculatePercentage(importantFamiliesInfoVo1);
				     
				     importantFamiliesInfoVo.getSubList().add(importantFamiliesInfoVo1);;
				
			   
		   }
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("Exception raised in  getImportantFamiliesDetailsForHamletFromBooth service method");		
		}
		
		return importantFamiliesInfoVo;
    }
	
	public VotersInfoForMandalVO getCustomWardWiseVotersCount(Long constituencyId,Long localEleBodyId,Long publicationId,Long userId)
	{
		VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
		
		if(localEleBodyId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
			List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(localEleBodyId.toString().substring(1)));
			localEleBodyId = (Long) list.get(0);
			}
		try{
			List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>(0);
			List<Object[]> list = boothPublicationVoterDAO.getCustomWardWiseVotersInfoByLocalEleId(constituencyId, publicationId, localEleBodyId, userId);
			Long muncipalityTotal = 0l;
			if(list != null && list.size() > 0)
			{
				VotersInfoForMandalVO mandalVO = null;
				
				for(Object[] params : list)
				{
					mandalVO = checkVotersInfoForMandalVOIsExist((Long)params[0],votersInfoForMandalVOList);
					if(mandalVO == null)
					{
						mandalVO = new VotersInfoForMandalVO();
						mandalVO.setId((Long)params[0]);
						mandalVO.setName(params[1].toString());
						mandalVO.setType("CustomWard");
						votersInfoForMandalVOList.add(mandalVO);
					}
					
					if(params[3] != null && params[3].toString().equalsIgnoreCase("M"))
						mandalVO.setTotalMaleVoters(params[2] != null ?params[2].toString() :"0");
					else if(params[3] != null && params[3].toString().equalsIgnoreCase("F"))
						mandalVO.setTotalFemaleVoters(params[2] != null ?params[2].toString() :"0");
					else 
						mandalVO.setUnKnowVoters(params[2] != null ?params[2].toString() :"0");
					
				}
				
				votersInfoForMandalVO.setVotersInfoForMandalVOList(votersInfoForMandalVOList);
				
				for(VotersInfoForMandalVO vO :votersInfoForMandalVOList)
				{
					Long maleVotersCount = 0L;
					Long femaleVoters = 0L;
					Long unknowVoters = 0L;
					if(vO.getTotalMaleVoters() != null)
						maleVotersCount = new Long(vO.getTotalMaleVoters());
					if(vO.getTotalFemaleVoters() != null)
						femaleVoters = new Long(vO.getTotalFemaleVoters());
					if(vO.getUnKnowVoters() != null)
						unknowVoters = new Long(vO.getUnKnowVoters());
					
					vO.setTotVoters(new BigDecimal(maleVotersCount.longValue()+femaleVoters.longValue()+ unknowVoters.longValue()));
					muncipalityTotal += (maleVotersCount.longValue()+femaleVoters.longValue()+ unknowVoters.longValue()); 
				}
				
				
			}
			
			votersInfoForMandalVO.setName(localElectionBodyDAO.get(localEleBodyId).getName());
			votersInfoForMandalVO.setType("Muncipality");
			votersInfoForMandalVO.setTotalVoters(muncipalityTotal.toString());
			votersInfoForMandalVO.setTotVoters(new BigDecimal(muncipalityTotal.longValue()));
			calculatePercentage(votersInfoForMandalVO);
			
			return votersInfoForMandalVO;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getCustomWardWiseVotersCount() method,Exception - "+e);
			return votersInfoForMandalVO;
		}
	}
	
	public VotersInfoForMandalVO checkVotersInfoForMandalVOIsExist(Long id,List<VotersInfoForMandalVO> list)
	{
		try{
			if(list == null || list.size() == 0)
				return null;
			for(VotersInfoForMandalVO mandalVO : list)
				if(mandalVO.getId() != null && mandalVO.getId().equals(id))
					return mandalVO;
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in checkVotersInfoForMandalVOIsExist() method, Exception - "+e);
			return null;
		}
	}
	
	public VoterInfo getTotalVotersDetailsbyLocation(Long userId,Long reportLevelValue,String locationType,Long publicationDateId,Long constituencyId){
		VoterInfo voterInfo = new VoterInfo();
		if(locationType.equalsIgnoreCase(IConstants.HAMLET)){
			 List<Object[]> votersCount =  boothPublicationVoterDAO.getVotersCountByGenderInHamlet(userId,reportLevelValue,publicationDateId,constituencyId);
		      for(Object[] obj1:votersCount){
		    	  if(obj1[1].toString().equalsIgnoreCase("M"))
		    		  voterInfo.setMaleVoters((Long) obj1[0]);
		    	  else if(obj1[1].toString().equalsIgnoreCase("F"))
		    		  voterInfo.setFemaleVoters((Long) obj1[0]);						    	  
		      }
		      voterInfo.setTotalVoters(voterInfo.getMaleVoters()+voterInfo.getFemaleVoters());
		}
		else if(locationType.equalsIgnoreCase(IConstants.CUSTOMWARD)){
			 List<Object[]> votersCount =  userVoterDetailsDAO.getVotersCountBasedOnGenderForSelectedWard(userId,reportLevelValue,publicationDateId);
		      for(Object[] obj1:votersCount){
		    	  if(obj1[1].toString().equalsIgnoreCase("M"))
		    		  voterInfo.setMaleVoters((Long) obj1[0]);
		    	  else if(obj1[1].toString().equalsIgnoreCase("F"))
		    		  voterInfo.setFemaleVoters((Long) obj1[0]);						    	  
		      }
		      voterInfo.setTotalVoters(voterInfo.getMaleVoters()+voterInfo.getFemaleVoters());
		}
		else{
			Long reportLvlId= getReportLevelId(locationType);		
		List<Object[]> votersDetailesCount = voterInfoDAO.getVoterDetailedCountByLocation(reportLvlId,reportLevelValue,publicationDateId,constituencyId);
			if(votersDetailesCount!=null && votersDetailesCount.size()>0){
				for (Object[] objects : votersDetailesCount) {
					voterInfo.setTotalVoters((Long) objects[0]);
					voterInfo.setMaleVoters((Long) objects[1]);
					voterInfo.setFemaleVoters((Long)objects[2]);
				}
			}
		}
		return voterInfo;		
	}
	
	public static Comparator<CastLocationVO> sortCaste = new Comparator<CastLocationVO>()
		    {
		        public int compare(CastLocationVO castVo1, CastLocationVO castVo2)
		        {
		        	 return castVo1.getCaste().compareToIgnoreCase(castVo2.getCaste());
		        }
		    };
		    
		    
		    public DataVerificationVO getCountForConstituency(String locationType,Long constituencyId,Long locationId,Long publicationDateId,Long userId)
		    {
		    	DataVerificationVO dataVerificationVO = new DataVerificationVO();
		    	try{
		    	
		    	   if(locationType.equalsIgnoreCase(IConstants.MANDAL) && locationId.toString().substring(0,1).equalsIgnoreCase("2"))
		    	   {
		    		   locationId = new Long(locationId.toString().substring(1));
		    		   
		    		   List<Long> panchayatIds = boothDAO.getPanchayatsMandalId(locationId, constituencyId, publicationDateId);
		    		   if(panchayatIds != null && panchayatIds.size() >0)
		    		   {
		    			  List<Long>  partialPanchayts = partialBoothPanchayatDAO.getPanchayatIdsForPartialBooths(panchayatIds,publicationDateId,constituencyId);
		    			  if(partialPanchayts != null && partialPanchayts.size() > 0)
		    			  {
		    				  for (Long panchayatId : partialPanchayts) {
		    					  panchayatIds.add(panchayatId);
		    				}
		    			  }
		    		   }
		    		  // dataVerificationVO.setTotalPanchayats(boothDAO.getPanchayatsCountByMandalId(locationId, constituencyId, publicationDateId) != null?(Long)boothDAO.getPanchayatsCountByMandalId(locationId, constituencyId, publicationDateId).get(0):0L);
		    		   dataVerificationVO.setTotalPanchayats(panchayatIds != null ? Long.valueOf(panchayatIds.size()):0l);
		    		   dataVerificationVO.setTotalBooths(boothDAO.getBoothsCountByLocationId(IConstants.MANDAL, locationId, constituencyId, publicationDateId)!= null?(Long)boothDAO.getBoothsCountByLocationId(IConstants.MANDAL, locationId, constituencyId, publicationDateId).get(0):0L);
		    		   dataVerificationVO.setTotalHamlets(panchayatHamletDAO.getHamletsCountByLocationId(IConstants.MANDAL, constituencyId, locationId, publicationDateId) !=null?(Long)panchayatHamletDAO.getHamletsCountByLocationId(IConstants.MANDAL, constituencyId, locationId, publicationDateId).get(0):0L);
		    	   }
		    	   else if(locationType.equalsIgnoreCase(IConstants.MANDAL) && locationId.toString().substring(0,1).equalsIgnoreCase("1"))
		    	   {
		    		  locationId = (Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationId.toString().substring(1))).get(0);
		    		  String type=localElectionBodyDAO.getLocationTypeForLocalEleBodyByLocalEleBodyId(locationId);
		    		    
		    		  if(type.equalsIgnoreCase(IConstants.GHMC))
		    		    dataVerificationVO.setTotalWards(boothDAO.getWardsCountByLocalEleBodyId(locationId, publicationDateId, constituencyId) !=null?(Long)boothDAO.getWardsCountByLocalEleBodyId(locationId, publicationDateId, constituencyId).get(0):0L);
		    		  else
		    		   dataVerificationVO.setTotalWards(userVoterDetailsDAO.getWardsCountByLocalEleBodyId(locationId, constituencyId, userId, publicationDateId) != null?userVoterDetailsDAO.getWardsCountByLocalEleBodyId(locationId, constituencyId, userId, publicationDateId).get(0):0L);
		    		    
		    		   dataVerificationVO.setTotalBooths(boothDAO.getBoothsCountByLocationId(IConstants.PANCHAYAT, locationId, constituencyId, publicationDateId)!= null?(Long)boothDAO.getBoothsCountByLocationId(IConstants.LOCALELECTIONBODY, locationId, constituencyId, publicationDateId).get(0):0L);
		    	   }
		    	   else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		    	   {
		    		   dataVerificationVO.setTotalBooths(boothDAO.getBoothsCountByLocationId(IConstants.PANCHAYAT, locationId, constituencyId, publicationDateId)!= null?(Long)boothDAO.getBoothsCountByLocationId(IConstants.PANCHAYAT, locationId, constituencyId, publicationDateId).get(0):0L);
		    		   dataVerificationVO.setTotalHamlets(panchayatHamletDAO.getHamletsCountByLocationId(IConstants.PANCHAYAT, constituencyId, locationId, publicationDateId) !=null?(Long)panchayatHamletDAO.getHamletsCountByLocationId(IConstants.PANCHAYAT, constituencyId, locationId, publicationDateId).get(0):0L);  
		    	   }
		    	   else if(locationType.equalsIgnoreCase(IConstants.CUSTOMWARD))
		    		 dataVerificationVO.setTotalBooths(userVoterDetailsDAO.getBoothsCountForCustomWard(locationId, constituencyId, publicationDateId, userId) != null?(Long)userVoterDetailsDAO.getBoothsCountForCustomWard(locationId, constituencyId, publicationDateId, userId).get(0):0L);
		    	   else if(locationType.equalsIgnoreCase(IConstants.WARD))
		    		   dataVerificationVO.setTotalBooths(boothDAO.getBoothsCountByLocationId(IConstants.PANCHAYAT, locationId, constituencyId, publicationDateId)!= null?(Long)boothDAO.getBoothsCountByLocationId(IConstants.WARD, locationId, constituencyId, publicationDateId).get(0):0L);
		    		
		    		
		    	 return dataVerificationVO;
		    	}catch (Exception e) {
		    	 log.error("Exception Occured in getCountForConstituency() method, Exception - "+e);
		    	 return dataVerificationVO;
		    	}
		    }
public List<SelectOptionVO> getLocalAreaWiseAgeDetailsForCustomWard(String type,Long constituencyId,Long publicationDateId,Long customWardId,Long userId)
{
	List<SelectOptionVO> selectOptionVOsList = new ArrayList<SelectOptionVO>(0);
	
	try{
		List<String> localityNamesList = userVoterDetailsDAO.getLocalityByCustomWardId(constituencyId, publicationDateId, customWardId, userId, type);
		if(localityNamesList == null || localityNamesList.size() == 0)
			return selectOptionVOsList;
		
		List<VoterAgeRange> ageRangesList = voterAgeRangeDAO.getVoterAgeRangeList();
		
		for(String locality :localityNamesList)
		  selectOptionVOsList.add(new SelectOptionVO(0L,locality));
		
	
		for(VoterAgeRange ageRange:ageRangesList)
		{
			List<String> namesList = new ArrayList<String>(0);
		  String age = ageRange.getMinValue()+"-"+ageRange.getMaxValue();
		  List<Object[]> list = userVoterDetailsDAO.getLocalAreaWiseAgeDetails(constituencyId, publicationDateId, customWardId, userId, ageRange.getMinValue(), ageRange.getMaxValue(),type);
		  
		  
		  if(list != null)
		  {
		    for(Object[] params : list)
			 {
				  SelectOptionVO selectOptionVO = checkSelectOptionVOExists(params[1].toString(),selectOptionVOsList);
				  selectOptionVO.setId(selectOptionVO.getId() + (Long)params[2]);
				  List<SelectOptionVO> tempList = selectOptionVO.getSelectOptionsList();
				  tempList.add(new SelectOptionVO((Long)params[2],ageRange.getAgeRange()));
				  selectOptionVO.setSelectOptionsList(tempList);
				  
				  List<SelectOptionVO> tempList1 = selectOptionVO.getSelectOptionsList1();
				  tempList1.add(new SelectOptionVO((Long)params[2],ageRange.getAgeRange()));
				  selectOptionVO.setSelectOptionsList1(tempList1);
			 }
			 
			 for(Object[] params : list)
			   namesList.add(params[1].toString());
			 
			 for(String name:localityNamesList)
				  if(!namesList.contains(name))
				  {
					 SelectOptionVO selectOptionVO = checkSelectOptionVOExists(name,selectOptionVOsList);
					 List<SelectOptionVO> tempList = selectOptionVO.getSelectOptionsList();
					 tempList.add(new SelectOptionVO(0L,ageRange.getAgeRange()));
					 selectOptionVO.setSelectOptionsList(tempList);
					 
					 List<SelectOptionVO> tempList1 = selectOptionVO.getSelectOptionsList1();
					 tempList1.add(new SelectOptionVO(0L,ageRange.getAgeRange()));
					 selectOptionVO.setSelectOptionsList1(tempList1);
				  }
		 }
		
	   }
		
		if(selectOptionVOsList != null && selectOptionVOsList.size() > 0)
		{ 
		  for(SelectOptionVO optionVO:selectOptionVOsList)
		  {
			  for(SelectOptionVO optionVO2:optionVO.getSelectOptionsList())
			  {
				 if(optionVO.getId()!=null && optionVO.getId()>0)
				   optionVO2.setUrl(roundTo2DigitsFloatValue((float)optionVO2.getId()*100f/optionVO.getId()));
				 else
				  optionVO2.setUrl("0.00");
			  }
		  }
		}
		return selectOptionVOsList;
	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getLocalAreaWiseAgeDetailsForCustomWard() method,Excepiton - "+e);
		return selectOptionVOsList;
	}
	
}


 public SelectOptionVO checkSelectOptionVOExists(String localityName,List<SelectOptionVO> list)
 {
	try{
		if(list == null || list.size() == 0)
		 return null;
		for(SelectOptionVO optionVO:list)
		 if(optionVO.getName().equalsIgnoreCase(localityName))
			 return optionVO;
		return null;
	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in checkSelectOptionVOExists() method,Excepiton - "+e);
		return null;
	}
 }
	
 public SelectOptionVO checkSelectOptionVOExist(Long boothId,List<SelectOptionVO> booths)
	{
		try{
			if(booths == null || booths.size() == 0)
				return null;
			for(SelectOptionVO option : booths)
				if(boothId.equals(option.getId().longValue()))
				return option;
			
		 return null;
		}catch (Exception e) {
			return null;
		}
		
	}
 
 public SelectOptionVO getConstiInfo(List<Long> constiIds){
	 try{
		 List<Object[]> result = nominationDAO.getLatestElectionDetails(constiIds);
		 if(result != null && result.size() > 0){
			 Object[] data = result.get(0);
			 SelectOptionVO vo = new SelectOptionVO();
			 vo.setId((Long)data[0]);
			 vo.setName(data[1].toString());
			 if("BYE".equalsIgnoreCase(data[2].toString()))
			  vo.setType(data[2].toString()+" election");
			 else
			  vo.setType("");
			 return vo;
		 }
	 }catch(Exception e){
		 
	 }
	 return null;
 }
 
 public List<SelectOptionVO> getMandalsInConstituencys(List<SelectOptionVO> constituencyList){
	 List<SelectOptionVO> mandalNames=new ArrayList<SelectOptionVO>();
	 List<Long> delimitationConstituencyIDs = new ArrayList<Long>(0);
		List<Long> constituencyIds = new ArrayList<Long>();
		if(constituencyList != null && constituencyList.size() > 0)
		{  
			for (SelectOptionVO constituency : constituencyList) {
				
				Long constituencyId = constituency.getId();
				constituencyIds.add(constituencyId);
			}
			
		}
		List<DelimitationConstituency> delimitationConstituencys = delimitationConstituencyDAO.findLatestDelimitationConstituencyByConstituencyIDs(constituencyIds);
		for(DelimitationConstituency delimitationConstituency:delimitationConstituencys){
		delimitationConstituencyIDs.add(delimitationConstituency.getDelimitationConstituencyID());
		
		}
		List<Object[]> mandals = delimitationConstituencyMandalDAO.getTehsilByDelimitationConstituencyIds(delimitationConstituencyIDs);
		
		for(Object[] tehsil : mandals){
			SelectOptionVO objVO = new SelectOptionVO();
			objVO.setId((Long)tehsil[0]);
			objVO.setName(tehsil[1]!=null?tehsil[1].toString()+"("+tehsil[2].toString()+")":"");
			mandalNames.add(objVO);
		}	
	 return mandalNames;
 }
 
 public List<SelectOptionVO> getMandalOrMuncipalityList(Long constituencyId,String tempVar)
 {
	 try{
		 List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		 if(tempVar != null && tempVar.equalsIgnoreCase("mandalList"))
		 {
		   selectOptionVOList = regionServiceDataImp.getSubRegionsInConstituency(new Long(constituencyId), IConstants.PRESENT_YEAR, null);
		   selectOptionVOList.add(0, new SelectOptionVO(0L,"Select Mandal"));
		 }
		 else
		 {
			List<Object[]> list = assemblylocalElectionBodyDAO.geLocalElectionBodyListForVotersAnalysis(constituencyId);
			if(list != null && list.size()> 0 )
			 for(Object[] params : list)
				 selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1] != null?params[1].toString():""));
			selectOptionVOList.add(0, new SelectOptionVO(0L,"Select Muncipality"));
		 }
		return selectOptionVOList; 
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error(" Exception Occured in getMandalOrMuncipalityList() method, Exception - "+e);
	  return null;
	}
 }
 
 public String getElectionTypeForMuncipalityByConstituencyId(Long constituencyId,Long localEleBodyId)
 {
	 try{
		 if(localEleBodyId != null)
		  return assemblylocalElectionBodyDAO.getElectionTypeForMuncipality(constituencyId, localEleBodyId);
       
		 return null;		
	 }catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getElectionTypeForMuncipalityByConstituencyId() method, Exception - "+e);
		return null;
	}
 }
 
 
 public List<SelectOptionVO> getWardsListForMuncipality(Long constituencyId,Long localEleBodyId,Long publicationDateId,Long userId)
 {
	 try{
		 List<Object[]> list = null;
		 List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		 String electionType = getElectionTypeForMuncipalityByConstituencyId(constituencyId, localEleBodyId);
		 if(electionType != null && electionType.equalsIgnoreCase(IConstants.GHMC))
		   list = boothDAO.getBoothsForLocalEleBodyByCOnstituencyId(constituencyId, localEleBodyId,publicationDateId);  
		 else
		   list = userVoterDetailsDAO.getWardsForMuncByConsIdAndUserId(constituencyId, localEleBodyId, publicationDateId, userId);
		 
		 if(list != null && list.size() > 0)
		  for(Object[] params : list)
		   selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1] != null ?params[1].toString():" "));
		 
		 if(selectOptionVOList != null && selectOptionVOList.size() > 0)
			 Collections.sort(selectOptionVOList,wardsSort);
		 
		 return selectOptionVOList;
	 }catch (Exception e) {
		 e.printStackTrace();
		 log.error("Exception Occured in getWardsListForMuncipality() method, Exception - "+e);
		 return null;
	}
 }
 
 
 public VoterHouseInfoVO getVoterDetailsForSelectedLocation(VoterHouseInfoVO houseInfoVO,Long locationValue)
 {
	 VoterHouseInfoVO voterHouseInfoVO = null;
	try{
		
		String locationType = houseInfoVO.getLocation();
		StringBuilder queryStr = new StringBuilder();
		if(locationType != null && locationType.equalsIgnoreCase("constituency"))
		  queryStr.append(" and model.booth.constituency.constituencyId =:locationValue ");
		else if(locationType != null && locationType.equalsIgnoreCase("mandal"))
		{
		  if(locationValue.toString().trim().substring(0, 1).equalsIgnoreCase("2"))
		  {
			queryStr.append(" and model.booth.tehsil.tehsilId =:locationValue and model.booth.localBody is null ");
		    locationValue = new Long(locationValue.toString().trim().substring(1));
		  }
		  else
		  {
			 
			 List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationValue.toString().trim().substring(1)));
			 queryStr.append(" and model.booth.localBody.localElectionBodyId = :locationValue ");
			 locationValue = (Long) list.get(0);  
		  }
		}
		else if(locationType !=null && locationType.equalsIgnoreCase("panchayat"))
		  queryStr.append(" and model.booth.panchayat.panchayatId =:locationValue ");
		else if(locationType != null && locationType.equalsIgnoreCase("booth"))
		  queryStr.append(" and model.booth.boothId =:locationValue ");
		
		else if(locationType != null && locationType.equalsIgnoreCase("ward"))
		  queryStr.append(" and model.booth.localBodyWard.constituencyId =:locationValue ");
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.CUSTOMWARD))
		  queryStr.append(" and model2.ward.constituencyId =:locationValue ");
		else if(locationType != null && locationType.equalsIgnoreCase("hamlet"))
		  queryStr.append(" and model2.hamlet.hamletId =:locationValue ");
		
		List<Object[]> list = boothPublicationVoterDAO.getVoterDetailsForMessageCenter(houseInfoVO.getConstituencyId(), houseInfoVO.getPublicationId(), locationValue, queryStr.toString(), houseInfoVO.getStartIndex(), houseInfoVO.getMaxIndex(), houseInfoVO.getUserId());
		if(list != null && list.size() > 0)
		{
		  List<Long> voterIdsList = new ArrayList<Long>(0);
		  voterHouseInfoVO = new VoterHouseInfoVO();
		  List<VoterHouseInfoVO> resultList = new ArrayList<VoterHouseInfoVO>(0);
		  Map<Long,String> locationMap = new HashMap<Long, String>(0);
		  for(Object[] params:list)
		   voterIdsList.add(((Voter) params[0]).getVoterId());
		  
		  
		  
		  for(Object[] params:list)
		  {
			  VoterHouseInfoVO infoVO = new VoterHouseInfoVO();
			  Voter voter = (Voter)params[0];
			  infoVO.setVoterId(voter.getVoterId());
			  infoVO.setName(voter.getName() != null ?voter.getName():" ");
			  infoVO.setHouseNo(voter.getHouseNo());
			  infoVO.setMobileNo(params[1]!= null ?params[1].toString():" ");
			  infoVO.setBoothId((Long)params[2]);
			  infoVO.setPartNo(params[3] != null?params[3].toString():" ");
			  resultList.add(infoVO);
			  
		  }
		  voterHouseInfoVO.setVotersList(resultList);
		  voterHouseInfoVO.setTotalHousesCount((long)boothPublicationVoterDAO.getVoterDetailsForMessageCenter(houseInfoVO.getConstituencyId(), houseInfoVO.getPublicationId(), locationValue, queryStr.toString(), null, null, houseInfoVO.getUserId()).size());
		}
		
	 return voterHouseInfoVO;
	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getVoterDetailsForSelectedLocation() method, Exception - "+e);
		return voterHouseInfoVO;
	}
 }
 
 public void getLocationName(Map<Long,String> locationMap,List<Long> voterIdsList,String locationType)
 {
	 try{
	
		 if(locationType != null && locationType.equalsIgnoreCase("mandal"))
		 {
			 
		 }
		 
		 
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error("Exception Occured in getLocationName() method, Exception- "+e);
	}
 }
 
 public ResultStatus calculateAndInsertVoterCasteInfoForHamletBooth(Long constituencyId,Long publicationDateId,Long userId,Long reportLevelId)
 {
	 ResultStatus resultStatus = new ResultStatus();
	 try{
		
		 List<Object[]> list = hamletBoothDAO.getCasteWiseVoterDetailsForHamletBooth(constituencyId, publicationDateId, userId);
		 if(list != null && list.size() > 0)
		 {
			
			//voter Cast info
			List<VotersInfoForMandalVO> votersInfoForMandalVOsList = new ArrayList<VotersInfoForMandalVO>(0);
			VotersInfoForMandalVO voterCastInfo = null;
			Map<Long,Long> totalCastMap = new HashMap<Long, Long>(0);
			
			for(Object[] params:list)
			{
				  voterCastInfo = getVotersInfoForMandalVO((Long)params[4], (Long)params[2], votersInfoForMandalVOsList);
				  if(voterCastInfo == null)
				  { 
					  voterCastInfo = new VotersInfoForMandalVO();
					  voterCastInfo.setReportLevelId(reportLevelId);
					  voterCastInfo.setReportLevelValue((Long)params[4]);
					  voterCastInfo.setConstituencyId(constituencyId);
					  voterCastInfo.setPublicationDateId(publicationDateId);
					  voterCastInfo.setCasteStateId((Long)params[2]);
					  votersInfoForMandalVOsList.add(voterCastInfo);
				  }
				  
				  if(params[0] != null && params[0].toString().equalsIgnoreCase(IConstants.MALE))
					  voterCastInfo.setMaleVoters((Long)params[1]);
				  else if(params[0] != null && params[0].toString().equalsIgnoreCase(IConstants.FEMALE))
					  voterCastInfo.setFemaleVoters((Long)params[1]);
				  
				  voterCastInfo.setTotalVotersDiff(voterCastInfo.getMaleVoters()+voterCastInfo.getFemaleVoters());
				  
		    }
			
			List<Object[]> totalCastList = hamletBoothDAO.getCastesForHamletBooth(constituencyId, publicationDateId, userId);
			if(totalCastList!= null && totalCastList.size() > 0)
			  for(Object[] params:totalCastList)
				  totalCastMap.put((Long)params[0], (Long)params[1]);
			
			saveVoterCastInfoForHamletBooth(votersInfoForMandalVOsList,userId);
			
			//voter cast basic info
			
				List<Object[]> totalVotersList = voterInfoDAO.getTotalVotersForHamletBooth(constituencyId, publicationDateId, reportLevelId);
				Map<Long,Long> totalVotersMap = new HashMap<Long, Long>(0);
				if(totalVotersList != null && totalVotersList.size() > 0)
				  for(Object[] params :totalVotersList)
				   totalVotersMap.put((Long)params[0], (Long)params[1]); 
			
			
			List<VotersInfoForMandalVO> voterCastBasicList = new ArrayList<VotersInfoForMandalVO>(0);
			VotersInfoForMandalVO infoForMandalVO = null;
			for(Object[] params:list)
			{
				infoForMandalVO = checkVoterCasteInfoExists((Long)params[4], voterCastBasicList);
				if(infoForMandalVO == null)
				{
					infoForMandalVO = new VotersInfoForMandalVO();
					infoForMandalVO.setReportLevelId(reportLevelId);
					infoForMandalVO.setReportLevelValue((Long)params[4]);
					infoForMandalVO.setPublicationDateId(publicationDateId);
					infoForMandalVO.setConstituencyId(constituencyId);
					infoForMandalVO.setTotalCasts(totalCastMap.get((Long)params[4])!= null?totalCastMap.get((Long)params[4]):0L);
					infoForMandalVO.setTotalVotersDiff(totalVotersMap.get((Long)params[4]) != null?totalVotersMap.get((Long)params[4]):0L);
					voterCastBasicList.add(infoForMandalVO);
				}
				if(params[3] != null && params[3].toString().equalsIgnoreCase("OC"))
				 infoForMandalVO.setOCVoters((Long)params[1]);
				else if(params[3] != null && params[3].toString().equalsIgnoreCase("BC"))
				 infoForMandalVO.setBCVoters((Long)params[1]);
				else if(params[3] != null && params[3].toString().equalsIgnoreCase("SC"))
				 infoForMandalVO.setSCVoters((Long)params[1]);
				else if(params[3] != null && params[3].toString().equalsIgnoreCase("ST"))
				  infoForMandalVO.setSTVoters((Long)params[1]);
				
				infoForMandalVO.setCastAssignedVoters(infoForMandalVO.getOCVoters()+infoForMandalVO.getBCVoters()+infoForMandalVO.getSCVoters()+infoForMandalVO.getSTVoters());
				
			}
			
			if(voterCastBasicList != null && voterCastBasicList.size() >0)
			{
			 for(VotersInfoForMandalVO mandalVO :voterCastBasicList)
			  mandalVO.setTotalVotersDiff(mandalVO.getTotalVotersDiff()-mandalVO.getCastAssignedVoters());
			
			  saveVoterCastBasicInfoForHamletBoothModel(voterCastBasicList,userId);
			}
		 
		 }
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		return resultStatus;
		 
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error("Exception Occured in calculateAndInsertVoterCasteInfoForHamletBooth() method, Exception - "+e);
	  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	  return resultStatus;
	}
 }
 
 public ResultStatus saveVoterCastBasicInfoForHamletBoothModel(final List<VotersInfoForMandalVO> list,final Long userId)
 {
	 ResultStatus resultStatus = new ResultStatus();
	 try{
		 transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(TransactionStatus status) 
				{
			  log.info(" Entered into saveVoterCastInfoForHamletBooth() method... ");
			  if(list != null && list.size() > 0)
			  {
				  for(VotersInfoForMandalVO mandalVO:list)
				  {
					  VoterCastBasicInfo castInfo = new VoterCastBasicInfo();
					  castInfo.setReportLevelValue(mandalVO.getReportLevelValue());
					  castInfo.setVoterReportLevel(voterReportLevelDAO.get(mandalVO.getReportLevelId()));
					  castInfo.setUserId(userId);
					  castInfo.setConstituency(constituencyDAO.get(mandalVO.getConstituencyId()));
					  castInfo.setPublicationDateId(mandalVO.getPublicationDateId());
					  castInfo.setOcVoters(mandalVO.getOCVoters() != null?mandalVO.getOCVoters():0L);
					  castInfo.setBcVoters(mandalVO.getBCVoters()!= null?mandalVO.getBCVoters():0L);
					  castInfo.setScVoters(mandalVO.getSCVoters() != null?mandalVO.getSCVoters():0L);
					  castInfo.setStVoters(mandalVO.getSTVoters()!= null?mandalVO.getSTVoters():0L);
					  castInfo.setTotalCastes(mandalVO.getTotalCasts() != null?mandalVO.getTotalCasts():0L);
					  castInfo.setCasteAssignedVoters(mandalVO.getCastAssignedVoters() != null?mandalVO.getCastAssignedVoters():0L);
					  castInfo.setCasteNotAssignedVoters(mandalVO.getTotalVotersDiff() != null?mandalVO.getTotalVotersDiff():0L);
					  voterCastBasicInfoDAO.save(castInfo);
				  }
			  }
				}}); 
			
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
			 
		 }catch (Exception e) {
		  e.printStackTrace();
		  log.error("Exception Occured in saveVoterCastBasicInfoForHamletBoothModel() method, Exception - "+e);
		  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		  return resultStatus;
	 }
 }

 public ResultStatus saveVoterCastInfoForHamletBooth(final List<VotersInfoForMandalVO> list,final Long userId)
 {
	ResultStatus resultStatus = new ResultStatus();
	try{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus status) 
			{
		  log.info(" Entered into saveVoterCastInfoForHamletBooth() method... ");
		  if(list != null && list.size() > 0)
		  {
			  for(VotersInfoForMandalVO mandalVO:list)
			  {
				  VoterCastInfo castInfo = new VoterCastInfo();
				  castInfo.setReportLevelValue(mandalVO.getReportLevelValue());
				  castInfo.setVoterReportLevel(voterReportLevelDAO.get(mandalVO.getReportLevelId()));
				  castInfo.setUserId(userId);
				  castInfo.setConstituency(constituencyDAO.get(mandalVO.getConstituencyId()));
				  castInfo.setPublicationDateId(mandalVO.getPublicationDateId());
				  castInfo.setCasteState(casteStateDAO.get(mandalVO.getCasteStateId()));
				  castInfo.setCasteMaleVoters(mandalVO.getMaleVoters());
				  castInfo.setCasteFemaleVoters(mandalVO.getFemaleVoters());
				  castInfo.setCasteVoters(mandalVO.getTotalVotersDiff());
				  if(mandalVO.getTotalVotersPercentage() != null && !mandalVO.getTotalVotersPercentage().equalsIgnoreCase(""))
				   castInfo.setCastePercentage(new Double(mandalVO.getTotalVotersPercentage()));
				  
				  voterCastInfoDAO.save(castInfo);
			  }
		  }
			}}); 
		
	  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	  return resultStatus;
	}catch (Exception e) {
	 e.printStackTrace();
	 log.error(" Exception Occured in saveVoterCastInfoForHamletBooth() method, Exception - "+e);
	 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	 return resultStatus;
	}
 }
 
 public VotersInfoForMandalVO checkVoterCasteInfoExists(Long hamletBoothId, List<VotersInfoForMandalVO> list)
 {
	 try{
		
		 if(list == null || list.size() == 0)
			return null;
		 for(VotersInfoForMandalVO castInfo : list)
		  if(castInfo.getReportLevelValue().equals(hamletBoothId))
			 return castInfo;
		 return null;
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error(" Exception Occured in checkVoterCasteInfoExists() method, Exception - "+e);
	  return null;
	 }
 }
 
 public ResultStatus getHamletBoothDataByConstituencyIdAndPublicationDateId(Long constituencyId,Long publicationDateId,Long userId)
 {
	  ResultStatus resultStatus = new ResultStatus();
	  try{
		  
		 List<Long> boothIdsList = boothDAO.getBoothIdsByConstituencyIdAndPublicationId(constituencyId, publicationDateId);
		 if(boothIdsList != null && boothIdsList.size() > 0)
		  hamletBoothDAO.deleteHamletBoothsByBoothIdsList(boothIdsList);
		 
		 insertHamletIdAndBoothIdInHamletBoothTable(constituencyId,publicationDateId,userId);
		 
		 Long reportLevelId = voterReportLevelDAO.getReportLevelIdByType(IConstants.Hamlet_Booth);
		 calculateTheHamletBoothVotersBasicData(constituencyId,publicationDateId,userId,reportLevelId);
		 calculateAndInsertVoterAgeInfoForHamletBooth(constituencyId,publicationDateId,userId,reportLevelId);
		 calculateAndInsertVoterFamilyInfoForHamletBooth(constituencyId,publicationDateId,userId,reportLevelId);
		 //calculateAndInsertVoterCasteInfoForHamletBooth(constituencyId, publicationDateId, userId, reportLevelId);
			 
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		 return resultStatus;
	  }catch (Exception e) {
		 e.printStackTrace();
		 log.error("Exception Occured in getHamletBoothDataByConstituencyIdAndPublicationDateId() method, Exception - "+e);
		 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 return resultStatus;
		 
	  }
 }

 public ResultStatus calculateAndInsertVoterFamilyInfoForHamletBooth(Long constituencyId,Long publicationDateId,Long userId,Long reportLevelId)
 {
	  ResultStatus resultStatus = new ResultStatus();
	  try{
		  
		  List<Object[]> familyList = hamletBoothDAO.getVoterFamilyInfoForHamletBooth(userId, constituencyId, publicationDateId);
		  if(familyList != null && familyList.size() > 0)
		  {
			  Map<Long,Map<Long,Long>> familyMap = new HashMap<Long, Map<Long,Long>>(0);//<hamletBoothId,<familyRange,totalFamilies>>
			  Map<Long,Long> totalVotersCountMap = new HashMap<Long, Long>(0);//<hamletBoothId,totalFamilies>
			  
			  for(Object[] params :familyList)
			  {
				  Map<Long,Long> totalFamilies = familyMap.get((Long)params[1]);
				  if(totalFamilies == null)
				  {
					  totalFamilies = new HashMap<Long,Long>(0);
					  familyMap.put((Long)params[1], totalFamilies);
				  }
					  Long totalFamiliesCount = 0L;
					  Long familyRangeId = 0L;
					  Long familyCount = (Long)params[0]; 
					  if(familyCount <=3)
					  {
						  familyRangeId = getVoterFamilyRangeIdByFamilyRange("0-3");
						  totalFamiliesCount =  totalFamilies.get(familyRangeId); 
						  if(totalFamiliesCount == null)
							  totalFamilies.put(familyRangeId, 1L);
						  else
							  totalFamilies.put(familyRangeId, totalFamiliesCount+1);
					  }
					  else if(familyCount.longValue() >=4 && familyCount.longValue() <= 6)
					  {
						  familyRangeId = getVoterFamilyRangeIdByFamilyRange("4-6");
						  totalFamiliesCount =  totalFamilies.get(familyRangeId); 
						  if(totalFamiliesCount == null)
							  totalFamilies.put(familyRangeId, 1L);
						  else
							  totalFamilies.put(familyRangeId, totalFamiliesCount+1);  
					  }
					  else if(familyCount.longValue() >=7 && familyCount.longValue() <= 10)
					  {
						  familyRangeId = getVoterFamilyRangeIdByFamilyRange("7-10");
						  totalFamiliesCount =  totalFamilies.get(familyRangeId); 
						  if(totalFamiliesCount == null)
							  totalFamilies.put(familyRangeId, 1L);
						  else
							  totalFamilies.put(familyRangeId, totalFamiliesCount+1); 
					  }
					  else  if(familyCount.longValue() >10)
					  {
						  familyRangeId = getVoterFamilyRangeIdByFamilyRange("10-Above");
						  totalFamiliesCount =  totalFamilies.get(familyRangeId); 
						  if(totalFamiliesCount == null)
							  totalFamilies.put(familyRangeId, 1L);
						  else
							  totalFamilies.put(familyRangeId, totalFamiliesCount+1);  
					  }
					  
				  
				  Long totalVoters = totalVotersCountMap.get((Long)params[1]);
				  if(totalVoters == null)
					  totalVotersCountMap.put((Long)params[1], 1L);
				  else
					  totalVotersCountMap.put((Long)params[1], totalVoters+1);
				  
			  }
			  
			  List<ImportantFamiliesInfoVo> importantFamiliesInfoVoList = new ArrayList<ImportantFamiliesInfoVo>(0);
			  if(familyMap != null && familyMap.size() > 0)
			  {
				  for(Long hamletBoothId :familyMap.keySet())
				  {
					 Map<Long,Long> totalVotersMap = familyMap.get(hamletBoothId);
					 if(totalVotersMap != null)
					 {
						for(Long familyRangeId :totalVotersMap.keySet())
						{
						  ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
						  importantFamiliesInfoVo.setReportLevelId(reportLevelId);
						  importantFamiliesInfoVo.setReportLevelValue(hamletBoothId);
						  importantFamiliesInfoVo.setTypeId(familyRangeId);
						  importantFamiliesInfoVo.setTotalFamalies(totalVotersMap.get(familyRangeId));
						  importantFamiliesInfoVo.setConstituencyId(constituencyId);
						  importantFamiliesInfoVo.setPublicationDateId(publicationDateId);
						  importantFamiliesInfoVo.setTotalPercentage(new BigDecimal(importantFamiliesInfoVo.getTotalFamalies().doubleValue()*100.0/totalVotersCountMap.get(hamletBoothId).doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						  importantFamiliesInfoVoList.add(importantFamiliesInfoVo);
						  
						}
					 }
				  }
			  }
			  
			  for(ImportantFamiliesInfoVo familiesInfoVo:importantFamiliesInfoVoList)
				saveVotersDataInVoterFamilyInfoTable(familiesInfoVo);
			  
			  
		  }
		  
		  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		  return resultStatus;
	  }catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in calculateAndInsertVoterFamilyInfoForHamletBooth() method, Exception - "+e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		return resultStatus;
	  }
 }
 
 
 public ResultStatus calculateAndInsertVoterAgeInfoForHamletBooth(Long constituencyId,Long publicationDateId,Long userId,Long reportLevelId)
 {
	  ResultStatus resultStatus = new ResultStatus();
	  try{
		  
		  List<VoterAgeRange> ageRangesList = voterAgeRangeDAO.getVoterAgeRangeList();
		  
		  List<VoterAgeRangeVO> ageRangeVOsList = new ArrayList<VoterAgeRangeVO>();
			
		  if(ageRangesList != null && ageRangesList.size() > 0)
		  {
			  for(VoterAgeRange ageRange:ageRangesList)
			  {
			    List<Object[]> list = hamletBoothDAO.getAgeWiseHamletBoothList(userId, constituencyId, publicationDateId, ageRange.getMinValue(), ageRange.getMaxValue());
			    if(list != null && list.size() > 0)
			    {
			    	VoterAgeRangeVO ageRangeVO = null;
			      for(Object[] params:list)
			      {
			    	  ageRangeVO = checkAgeInfoVOExists((Long)params[2],ageRange.getVoterAgeRangeId(),ageRangeVOsList);
			    	  if(ageRangeVO == null)
			    	  {
			    		  ageRangeVO = new VoterAgeRangeVO();
			    		  ageRangeVO.setReportLevelValue((Long)params[2]);
			    		  ageRangeVO.setAgeRangeId(ageRange.getVoterAgeRangeId());
			    		  ageRangeVO.setReportLevelId(reportLevelId);
			    		  ageRangeVO.setConstituencyId(constituencyId);
			    		  ageRangeVO.setPublicationDateId(publicationDateId);
			    		  ageRangeVOsList.add(ageRangeVO);
			    	  }
			    	  if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.MALE))
			    		ageRangeVO.setMaleVoters((Long)params[0]);
			    	  else if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.FEMALE))
			    		ageRangeVO.setFemaleVoters((Long)params[0]);
			    	  
			    	  ageRangeVO.setTotalVotersInARange(ageRangeVO.getMaleVoters()+ageRangeVO.getFemaleVoters());
			    	  
			      }
			    }
			  }
		  }
		  
		  List<Object[]> totalVotersList = voterInfoDAO.getTotalVotersForHamletBooth(constituencyId, publicationDateId, reportLevelId);
		  if(totalVotersList != null && totalVotersList.size() > 0)
		  {
			  Map<Long,Long> totalVotersMap = new HashMap<Long, Long>(0);
			  for(Object[] params :totalVotersList)
				totalVotersMap.put((Long)params[0], (Long)params[1]); 
			  
			  if(ageRangeVOsList != null && ageRangeVOsList.size() > 0)
			  {
				 for(VoterAgeRangeVO rangeVO:ageRangeVOsList)
				 {
					 rangeVO.setMalePercentage(new BigDecimal((rangeVO.getMaleVoters().doubleValue()*100.0)/rangeVO.getTotalVotersInARange().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()); 
					 rangeVO.setFemalePercentage(new BigDecimal((rangeVO.getFemaleVoters().doubleValue()*100.0)/rangeVO.getTotalVotersInARange().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
					 rangeVO.setPercentage(new BigDecimal((rangeVO.getTotalVotersInARange().doubleValue()*100)/totalVotersMap.get(rangeVO.getReportLevelValue()).doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
				 }
					
				 saveVoterAgeDetailsInVoterAgeInfo(ageRangeVOsList);
			  }
			  
		  }
		  return resultStatus;
	  }catch (Exception e) {
		 e.printStackTrace();
		 log.error("Exception Occured in calculateAndInsertVoterAgeInfoForHamletBooth() method, Exception - "+e);
		 return resultStatus;
	  }
 }
 
 public VoterAgeRangeVO checkAgeInfoVOExists(Long hamletBoothId,Long ageRangeId,List<VoterAgeRangeVO> list)
 {
	try{
		if(list == null || list.size() == 0)
		 return null;
		for(VoterAgeRangeVO ageRangeVO:list)
		 if(ageRangeVO.getReportLevelValue().equals(hamletBoothId) && ageRangeVO.getAgeRangeId().equals(ageRangeId))
			return ageRangeVO;
		
	  return null;
	}catch (Exception e) {
	 e.printStackTrace();
	 log.error("Exception Occured in checkAgeInfoVOExists() method, Exception - "+e);
	 return null;
	}
 }
 
 public ResultStatus calculateTheHamletBoothVotersBasicData(Long constituencyId,Long publicationDateId,Long userId,Long reportLevelId)
 {
	  ResultStatus resultStatus = new ResultStatus();
	  try{
		  
		  List<VotersInfoForMandalVO> votersInfoMandalVOList = new ArrayList<VotersInfoForMandalVO>(0);
		  
		  Map<Long,Long> totalFamilies = new HashMap<Long, Long>(0);//<hamletBoothId,count(HNO)>
		  Map<Long,Long> totalVotersMap = new HashMap<Long, Long>(0);//<hamletBoothId,totalVoters>
		  
		  List<Object[]> familiesList = hamletBoothDAO.getFamiliesCountByHamletBoothIdsList(userId, constituencyId, publicationDateId);
		  if(familiesList != null && familiesList.size() > 0)
			for(Object[] params:familiesList)
			 totalFamilies.put((Long)params[1], (Long)params[0]);
			
		  
		  List<Object[]> hamletBoothList = hamletBoothDAO.getVotersCountForHamletBooth(userId, constituencyId, publicationDateId);
		  if(hamletBoothList != null && hamletBoothList.size() > 0)
		  {
			  
			 VotersInfoForMandalVO infoForMandalVO = null;
			for(Object[] params:hamletBoothList)
			{
			  infoForMandalVO = checkVotersInfoForMandalVOExist((Long)params[2],votersInfoMandalVOList);
			  if(infoForMandalVO == null)
			  {
				  infoForMandalVO = new VotersInfoForMandalVO();
				  infoForMandalVO.setReportLevelId(reportLevelId);
				  infoForMandalVO.setReportLevelValue((Long)params[2]);
				  infoForMandalVO.setConstituencyId(constituencyId);
				  infoForMandalVO.setPublicationDateId(publicationDateId);
				  infoForMandalVO.setTotalFamilies(totalFamilies.get((Long)params[2]) != null?totalFamilies.get((Long)params[2]):0);
				  votersInfoMandalVOList.add(infoForMandalVO);
			  }
			  if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.FEMALE))
				infoForMandalVO.setTotalFemaleVoters(params[0]!= null?params[0].toString():"0");
			  
			  else if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.MALE))
				infoForMandalVO.setTotalMaleVoters(params[0]!= null?params[0].toString():"0");
			  
			  Long totalVotersCount = totalVotersMap.get((Long)params[2]);
			  if(totalVotersCount == null)
				totalVotersMap.put((Long)params[2], (Long)params[0]);
			  else
				totalVotersMap.put((Long)params[2], totalVotersCount+(Long)params[0]);
			}
			
			for(VotersInfoForMandalVO mandalVO:votersInfoMandalVOList)
			 mandalVO.setTotalVoters(totalVotersMap.get(mandalVO.getReportLevelValue())!= null?totalVotersMap.get(mandalVO.getReportLevelValue()).toString():"");
			
			for(VotersInfoForMandalVO mandalVO:votersInfoMandalVOList)
			{
				Long totalVoters = totalVotersMap.get(mandalVO.getReportLevelValue());
				mandalVO.setTotalVoters(totalVoters != null?totalVoters.toString():"0");
				if(totalVoters != null && totalVoters > 0)
				{
				  mandalVO.setTotalMalePercentage(new BigDecimal((new Double(mandalVO.getTotalMaleVoters())*100)/totalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  mandalVO.setTotalFemalePercentage(new BigDecimal((new Double(mandalVO.getTotalFemaleVoters())*100)/totalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  //mandalVO.setTotalFamilyPercentage(new BigDecimal((new Double(mandalVO.getTotalFamilies())*100)/totalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
			
			if(votersInfoMandalVOList != null && votersInfoMandalVOList.size() > 0)
			 for(VotersInfoForMandalVO votersInfo:votersInfoMandalVOList)
				saveVotersDataInVoterInfoTable(votersInfo);
			
		  }
		  
		  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		 return resultStatus;
	  }catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in calculateTheHamletBoothVotersBasicData() method, Exception - "+e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 return resultStatus;
	  }
 }
 
 public VotersInfoForMandalVO checkVotersInfoForMandalVOExist(Long hamletBoothId,List<VotersInfoForMandalVO> list)
 {
	 try{
	  if(list == null || list.size() == 0)
		return null;;
		 for(VotersInfoForMandalVO mandalVO:list)
			if(mandalVO.getReportLevelValue().equals(hamletBoothId))
			  return mandalVO;
		 return null;
	  }catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in checkVotersInfoForMandalVOExist() method, Exception - "+e);
		return null;
	}
	  
 }
 
 
 public ResultStatus insertHamletIdAndBoothIdInHamletBoothTable(final Long constituencyId,final Long publicationDateId,final Long userId)
 {
	  log.info("Entered into saveHamletBoothDetails() Method...");
	  ResultStatus resultStatus = new ResultStatus();
	  try{
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		protected void doInTransactionWithoutResult(TransactionStatus status) 
		{
		    List<Object[]> list = boothPublicationVoterDAO.getBoothAndHamletIdsByConstituencyId(constituencyId, publicationDateId, userId);
		    if(list != null && list.size() > 0)
			{
			  for(Object[] params:list)
			  {
				 HamletBooth hamletBooth = new HamletBooth();
				 hamletBooth.setBooth(boothDAO.get((Long)params[0]));
				 hamletBooth.setHamlet(hamletDAO.get((Long)params[1]));
				 hamletBoothDAO.save(hamletBooth);
			  }
			}	
		
		}});
		  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		  
		  return resultStatus;
	  }catch (Exception e) {
		  e.printStackTrace();
		  log.error("Exception Occured in saveHamletBoothDetails() Method, Exception - "+e);
		  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		  return resultStatus;
	}
 }
 public List<SelectOptionVO> getPartialBoothDetails(Long userId,Long id,Long publicationId,Long constituencyId,String type){
	 List<SelectOptionVO> partialBoothList = new ArrayList<SelectOptionVO>();
	 List<Object[]> values = null;
	 List<Object[]> boothArea = null;
	 String areaType = null;
	 Long votersCount = 0L;
	 log.error("entered into getPartialBoothDetails() Method");
	 try{
	 if(type.equalsIgnoreCase("mandal")){
		 id = new Long(id.toString().substring(1));
		values = boothDAO.getDescriptionForMandalLevel(id,publicationId);
	 }else if(type.equalsIgnoreCase("panchayat")){
		values = boothDAO.getDescriptionForPanchayatLevel(id,publicationId);
		if(values == null || values.size() == 0){
			values = getPartialBoothsInPanchayat(id,publicationId,constituencyId,type);			
		}
	 } else if(type.equalsIgnoreCase("booth")){
		 values = boothDAO.getDescriptionForBoothLevel(constituencyId,id);
	 }
	 else if(type.equalsIgnoreCase("boothHamlets")){
		 values = boothDAO.getDescriptionForHamletLevel(publicationId,id);		 
	 }
		 if(values != null && values.size() > 0){
			 
			 if(type.equalsIgnoreCase("booth")){
				 Long locationId = null;
				 boothArea = boothDAO.getPanchayatByBoothId(id,publicationId);
				 if(boothArea != null || boothArea.size()> 0){
					 for (Object[] parms : boothArea) {
						 locationId = Long.valueOf(parms[1].toString());
					}
					 if(locationId.longValue() != constituencyId.longValue())
						 areaType = "notsame";
				 }
			 }
			 else if(type.equalsIgnoreCase("panchayat")){
				 votersCount= boothDAO.findVotersCountByPublicationIdForPartialPanchayat(userId,id,publicationId);
			 }
			 SelectOptionVO selectOptionVO = null;
			 for(Object[] param:values)
			 {
				 selectOptionVO = checkSelectOptionVOExist((Long)param[7],(Long)param[6],partialBoothList);
				 if(selectOptionVO == null)
				 {
				     selectOptionVO = new SelectOptionVO();
				     selectOptionVO.setUrl(param[0]!=null ? param[0].toString():"");
				     selectOptionVO.setId(param[5]!=null ? Long.valueOf(param[5].toString()):0L);
					 selectOptionVO.setLocation(param[1]!=null ?param[1].toString():"");//present panchayat
					 selectOptionVO.setName(param[2]!=null ?param[2].toString():""); // Partial mapped panchayat
					 //selectOptionVO.setValue(param[3]!=null ?param[3].toString():""); // Partial mapped panchayat hamlet
					 selectOptionVO.setPartno(param[4]!=null ?param[4].toString():"");// Booth No
					 selectOptionVO.setType(areaType !=null?areaType:"");
					 selectOptionVO.setOrderId(Long.valueOf(votersCount.toString()));
					 selectOptionVO.setMainAccountId((Long)param[6]);	
					 selectOptionVO.setParentUserId((Long)param[7]);
					 partialBoothList.add(selectOptionVO);
				 }
				 
				 String name = selectOptionVO.getValue();
				 if(name == null && param[3] != null)
				  name = param[3].toString();
				 else if(param[3] != null)
				  name += ","+param[3].toString();
				 selectOptionVO.setValue(name);
				 
			 }
		 }
	 
	 }catch(Exception e){		 
		 e.printStackTrace();
		  log.error("Exception Occured in getPartialBoothDetails() Method, Exception - "+e);
		  partialBoothList = null;
	 }
	 return partialBoothList;
 }
 
 public SelectOptionVO checkSelectOptionVOExist(Long panchayatId,Long boothId,List<SelectOptionVO> list)
 {
	 try{
	  if(list == null || list.size() == 0)
	    return null;
	  
	  for(SelectOptionVO optionVO:list)
	   if(optionVO.getParentUserId().equals(panchayatId) && optionVO.getMainAccountId().equals(boothId))
		  return optionVO;
	  
	  return null;
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error(" Exception Occured in checkSelectOptionVOExist() method, Exception - "+e);
	  return null;
	}
 }
 
 public List<Object[]> getPartialBoothsInPanchayat(Long id,Long publicationId,Long constituencyId,String type){
	 log.error("entered into getPartialBoothsInPanchayat() Method");
	 List<Long> boothsList = null;
	 List<Object[]> partialBoothsDetails = null;
	 try{
		 boothsList = boothDAO.getPartialBoothsForPanchayatLevel(id,publicationId);
		 if(boothsList!= null && boothsList.size() > 0)
			 partialBoothsDetails = boothDAO.getPartialBoothsDetailsOfPanchayat(boothsList,publicationId);
		 
	 }catch (Exception e) {
		
		log.error("Exception Occured in getPartialBoothsInPanchayat() Method, Exception - ",e);
		partialBoothsDetails = null;
		
	}
	 return partialBoothsDetails;
 }

 public void buildHamletWiseYoungerVoterDetails(List<VotersDetailsVO> resultList,List<Long> locationIds,Long publicationDateId,Long userId,String type)
 {
	try{
	List<Object[]> list = userVoterDetailsDAO.getYoungerVoterDetailsForHamletBylocationIdsList(locationIds, publicationDateId, userId, type,IConstants.YOUNG_VOTERS_AGE_FROM,IConstants.YOUNG_VOTERS_AGE_TO);
	if(list != null && list.size() > 0 && resultList != null && resultList.size() > 0)
	{
      Map<Long,Long> totalVotersMap = new HashMap<Long, Long>(0);
      List<Object[]> totalVotersList = userVoterDetailsDAO.getTotalVoterForHamletBylocationIdsList(locationIds, publicationDateId, userId, type);
    	for(Object[] obj:totalVotersList)
    	{
    		 Long total = totalVotersMap.get((Long)obj[0]);
    		 if(total == null)
    		  totalVotersMap.put((Long)obj[0], (Long)obj[1]);
    		 else
    		  totalVotersMap.put((Long)obj[0], total+(Long)obj[1]);
    	}
    	 
    	setYoungerVoterDetails(resultList, list, 0L, totalVotersMap, "totalVotersFromResultMap");
	}
	
	}catch (Exception e) {
     e.printStackTrace();
     log.error("Exception Occured in buildHamletWiseYoungerVoterDetails() method, Exception - "+e);
	}
 }
 
 
 public void setYoungerVoterDetails(List<VotersDetailsVO> resultList,List<Object[]> list,Long totalVotersCount,Map<Long,Long> totalVotersMap,String tempVar)
 {
	 try{
		   if(resultList != null && resultList.size() > 0)
		   {
		      for(Object[] params:list)
		      {
		    	   VotersDetailsVO detailsVO = getVoterDetailsVO((Long)params[0], resultList);
		    	   if(detailsVO != null)
		    	   {
		    		  if(params[3] != null && params[3].toString().equalsIgnoreCase(IConstants.MALE))
		    		    detailsVO.setTotalMaleVotersForYoungerVoters((Long)params[2]);
		    		  else if(params[3] != null && params[3].toString().equalsIgnoreCase(IConstants.FEMALE))
		    			detailsVO.setTotalFemaleVotersForYoungerVoters((Long)params[2]);
		    		  else
		    		   detailsVO.setTotalUnknownVotersForYoungerVoters((Long)params[2]);
		    		  
		    		  detailsVO.setTotalVotersForYoungerVoters(detailsVO.getTotalMaleVotersForYoungerVoters()+detailsVO.getTotalFemaleVotersForYoungerVoters()+detailsVO.getTotalUnknownVotersForYoungerVoters());
		    		  
		    	   }
		    	 }
		    	 
		    	 for(VotersDetailsVO votersDetailsVO:resultList)
		    	 {
		    		
		    		if(tempVar != null && tempVar.equalsIgnoreCase("totalVotersFromResultMap"))
		    			totalVotersCount = totalVotersMap.get(votersDetailsVO.getId());
		    			 
		    		if(votersDetailsVO.getTotalVotersForYoungerVoters() > 0)
		    		{
		    			votersDetailsVO.setMaleVotersPercentForYoungerVoters(roundTo2DigitsFloatValue((float) ((long)votersDetailsVO.getTotalMaleVotersForYoungerVoters()*100f/ votersDetailsVO.getTotalVotersForYoungerVoters())));
		    			votersDetailsVO.setFemaleVotersPercentForYoungerVoters(roundTo2DigitsFloatValue((float) ((long)votersDetailsVO.getTotalFemaleVotersForYoungerVoters()*100f/ votersDetailsVO.getTotalVotersForYoungerVoters())));
		    		}else{
		    			votersDetailsVO.setMaleVotersPercentForYoungerVoters("0.00");
		    			votersDetailsVO.setFemaleVotersPercentForYoungerVoters("0.00");	
		    		}
		    		votersDetailsVO.setVotersPercentForYoungerVoters(votersDetailsVO.getTotalVotersForYoungerVoters() != null? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersForYoungerVoters()*100f/totalVotersCount):"0.00");
		    		
		    	 }
		    	 
		     }
			
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error("Exception Occured in setYoungerVoterDetails() method, Exception - "+e);
	 }
 }
 
 public VotersDetailsVO getVoterDetailsVO(Long locationId,List<VotersDetailsVO> resultList)
 {
	 try{
	  if(resultList == null || resultList.size() == 0)
		 return null;
	 for(VotersDetailsVO detailsVO:resultList)
	  if(detailsVO.getId().equals(locationId))
		return detailsVO;
		 
	   return null;	 
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error(" Exception Occured in getVoterDetailsVO() method, Exception - "+e); 
	  return null;
	}
 }
 
	
	
	public void getDetailsOfYoungVoters(
			Long constituencyId,Long tehsilId,Long panchayatId, Long boothId, Long publicationDateId,
			List<VotersDetailsVO> constituencyVotersList,String type,List<Long> boothsList,Long userId) {		
		
		VotersDetailsVO voterDetailsForYoungVoters = new VotersDetailsVO();
		List<Object[]> votersListOfYoungVoters = null;
		
		try{
		
			if(type.equalsIgnoreCase("constituency"))
				votersListOfYoungVoters = boothPublicationVoterDAO
					.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
							constituencyId, publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO);
			 else if(type.equalsIgnoreCase("mandal"))
				 votersListOfYoungVoters = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
						 tehsilId, publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO,constituencyId);
			 else if(type.equalsIgnoreCase("panchayat"))
			 {
				 List<Long> partialPanchayatDetails = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatId(panchayatId, publicationDateId);
					
					if(partialPanchayatDetails != null && partialPanchayatDetails.size() >0)
						votersListOfYoungVoters = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationIdInHamlets(panchayatId, publicationDateId,IConstants.AGE18,IConstants.AGE22 , userId);
					else				 
				       votersListOfYoungVoters = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM,IConstants.YOUNG_VOTERS_AGE_TO);
			 }
			
			 else if(type.equalsIgnoreCase("booth"))
				 votersListOfYoungVoters = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM,IConstants.YOUNG_VOTERS_AGE_TO);
			 else if(type.equalsIgnoreCase("localElectionBody"))
				 votersListOfYoungVoters = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
						 tehsilId, publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM,IConstants.YOUNG_VOTERS_AGE_TO,constituencyId);
			 else if(type.equalsIgnoreCase("ward"))
				 votersListOfYoungVoters = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM,IConstants.YOUNG_VOTERS_AGE_TO);
			 else if(type.equalsIgnoreCase(IConstants.HAMLET) && boothsList !=null && boothsList.size()>0 )
			 {
				/* votersListOf18To25 = boothPublicationVoterDAO.getVotersCountInSpecifiedRangeForHamletByPublicationId(
						 panchayatId, publicationDateId,IConstants.AGE18,IConstants.AGE22,boothId);*/
				// List<Object> ids =	boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(5l, 1l);
				 votersListOfYoungVoters = boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId, IConstants.YOUNG_VOTERS_AGE_FROM,IConstants.YOUNG_VOTERS_AGE_TO, boothsList);
				 }
			 else return;	 
			
			Long maleVotersBetween18To22 = 0L;
			Long femaleVotersBetween18To22 = 0L;
			Long unKnownVotersBetween18To22 = 0L;
			
			for(Object[] obj:votersListOfYoungVoters){			
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersBetween18To22 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersBetween18To22 = (Long)obj[0];
				else 
					unKnownVotersBetween18To22 = (Long)obj[0];
				
			}
		
			 Long totalVotersBetween18To22 = maleVotersBetween18To22
					+ femaleVotersBetween18To22 + unKnownVotersBetween18To22;
			
			 voterDetailsForYoungVoters.setTotalMaleVoters(maleVotersBetween18To22);
			 voterDetailsForYoungVoters.setTotalFemaleVoters(femaleVotersBetween18To22);
			 voterDetailsForYoungVoters.setTotalUnknownVoters(unKnownVotersBetween18To22);
			 voterDetailsForYoungVoters.setTotalVoters(totalVotersBetween18To22);
			 voterDetailsForYoungVoters.setAgeRange(IConstants.YOUNGER_VOTERS);
			 
			  if(totalVotersBetween18To22 != 0){
				  
						
				  voterDetailsForYoungVoters.setTotalMaleVotersPercent((float) (maleVotersBetween18To22 *100f/ totalVotersBetween18To22 ));
				  voterDetailsForYoungVoters.setTotalFemaleVotersPercent((float)(femaleVotersBetween18To22 *100f / totalVotersBetween18To22 ));
				  voterDetailsForYoungVoters.setTotalUnknownVotersPercent((float) (unKnownVotersBetween18To22 *100f / totalVotersBetween18To22 ));
			  }
				 
			
			 
			 constituencyVotersList.add(voterDetailsForYoungVoters);
		 
		}catch(Exception e){
			e.printStackTrace();
			
		}
		 
		
	}
	
	  public void getYoungVotersDetails(Long constituencyId,Long publicationDateId,List<Long> locationIdsList,Long userId,String locationType, List<VoterAgeRangeVO> ageRangeVOsList,Long reportLevelId)
	  {
		  try{
			 
			List<Object[]> list  = null;  
			if(locationType != null && !locationType.equalsIgnoreCase(IConstants.HAMLET) && !locationType.equalsIgnoreCase("localityWiseYoungVotersImpl"))
			 list = boothPublicationVoterDAO.getYoungVotersCount(constituencyId, publicationDateId, locationIdsList, locationType, IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO);
			
			else if(locationType != null && locationType.equalsIgnoreCase("localityWiseYoungVotersImpl"))
			 list = userVoterDetailsDAO.getLocalityWiseYoungVotersAgeInfo(constituencyId, publicationDateId, userId, IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO);
			
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.HAMLET))
			 list = userVoterDetailsDAO.getYoungVoterAgeDetailsForHamlet(constituencyId, publicationDateId, locationIdsList, userId,  IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO);
			
						
			Long voterAgeRangeId = voterAgeRangeDAO.getVoterAgeRangeIdByType(IConstants.YOUNGER_VOTERS);
			
			
			if(list != null && list.size() > 0)
			 {
				VoterAgeRangeVO ageRangeVO = null;
				for(Object[] params:list)
				{
					ageRangeVO = checkVoterAgeRangeVOExist((Long)params[2],voterAgeRangeId,ageRangeVOsList);
					if(ageRangeVO == null)
					{
					    ageRangeVO = new VoterAgeRangeVO();
						ageRangeVO.setReportLevelValue((Long)params[2]);
						ageRangeVO.setAgeRangeId(voterAgeRangeId);
						ageRangeVO.setPublicationDateId(publicationDateId);
						ageRangeVO.setConstituencyId(constituencyId);
						ageRangeVO.setReportLevelId(reportLevelId);
						ageRangeVOsList.add(ageRangeVO);
					}
					if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.MALE))
					 ageRangeVO.setMaleVoters((Long)params[0]);
					else if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					 ageRangeVO.setFemaleVoters((Long)params[0]);
					
					ageRangeVO.setTotalVotersInARange(ageRangeVO.getMaleVoters()+ageRangeVO.getFemaleVoters());
					
				}
						
			 }
			  
		  }catch (Exception e) {
			 e.printStackTrace();
			 log.error("Exception Occured in getYoungVotersDetails() method, Exception - "+e);
		  }
	  }
		public void getDetailsOfYounVotersHasAgeBetween18And22(
				Long constituencyId,Long tehsilId,Long panchayatId, Long boothId, Long publicationDateId,
				VotersDetailsVO votersDetailsVO,String type,List<Long> boothsList) {		
			
			List<Object[]> votersListOf18To22 = null;
			
			try{
			
				if(type.equalsIgnoreCase("constituency"))
					votersListOf18To22 = boothPublicationVoterDAO
						.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
								constituencyId, publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO);
				 else if(type.equalsIgnoreCase("mandal"))
					 votersListOf18To22 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
							 tehsilId, publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO,constituencyId);
				 else if(type.equalsIgnoreCase("panchayat"))
					 votersListOf18To22 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
						panchayatId, publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO);
				 else if(type.equalsIgnoreCase("booth"))
					 votersListOf18To22 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
							boothId, publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO);
				 else if(type.equalsIgnoreCase("localElectionBody"))
					 votersListOf18To22 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
							 tehsilId, publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO,constituencyId);
				 else if(type.equalsIgnoreCase("ward"))
					 votersListOf18To22 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
							 boothsList, publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO);
				 /*else if(type.equalsIgnoreCase(IConstants.HAMLET))
					 votersListOf18To25 = boothPublicationVoterDAO.getVotersCountInSpecifiedRangeForHamletByPublicationId(
							 boothId, publicationDateId,IConstants.AGE18,IConstants.AGE22,votersDetailsVO.getUserId());*/
				 else if(type.equalsIgnoreCase(IConstants.HAMLET) && boothsList !=null && boothsList.size()>0 )
				 {	 votersListOf18To22=boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublication(publicationDateId, IConstants.YOUNG_VOTERS_AGE_FROM,IConstants.YOUNG_VOTERS_AGE_TO, boothsList);
					 }
				 else return;	 
				Long maleVotersBetween18To22 = 0L;
				Long femaleVotersBetween18To22 = 0L;
				Long unKnownVotersBetween18To22 = 0L;
				
				for(Object[] obj:votersListOf18To22){			
					
					if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
						maleVotersBetween18To22 = (Long)obj[0];
					else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
						femaleVotersBetween18To22 = (Long)obj[0];
					else 
						unKnownVotersBetween18To22 = (Long)obj[0];
					
				}
			
				 Long totalVotersBetween18To22 = maleVotersBetween18To22
						+ femaleVotersBetween18To22 + unKnownVotersBetween18To22;
				
				 votersDetailsVO.setTotalMaleVotersForYoungerVoters(maleVotersBetween18To22);
				 votersDetailsVO.setTotalFemaleVotersForYoungerVoters(femaleVotersBetween18To22);
				 votersDetailsVO.setTotalUnknownVotersForYoungerVoters(unKnownVotersBetween18To22);
				 votersDetailsVO.setTotalVotersForYoungerVoters(totalVotersBetween18To22);
				 
				  if(totalVotersBetween18To22 != 0){
					votersDetailsVO.setMaleVotersPercentForYoungerVoters(roundTo2DigitsFloatValue((float) (maleVotersBetween18To22 *100f/ totalVotersBetween18To22 )));
					votersDetailsVO.setFemaleVotersPercentForYoungerVoters(roundTo2DigitsFloatValue((float)(femaleVotersBetween18To22 *100f/ totalVotersBetween18To22 )));
				  }else{
					votersDetailsVO.setMaleVotersPercentForYoungerVoters("0.00");
					votersDetailsVO.setFemaleVotersPercentForYoungerVoters("0.00");
				}
			 
			}catch(Exception e){
				e.printStackTrace();
				
			}
		}
		public void getHamletWiseYoungVoterDetails(List<VotersDetailsVO> resultList,Long constituencyId,Long userId,Long publicationDateId,Long boothId,String type)
		{
			try{
				
				List<Object[]> list= userVoterDetailsDAO.getYoungVotersForHamlet(constituencyId, userId, publicationDateId, boothId, IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO, type);
				if(list != null && list.size() > 0 && resultList != null && resultList.size() > 0)
				{
				    Map<Long,Long> totalVotersMap = new HashMap<Long, Long>(0);
				    List<Object[]> totalVotersList = userVoterDetailsDAO.getTotalYoungVotersForHamlet(constituencyId, userId, publicationDateId, boothId, type);
				    for(Object[] obj:totalVotersList)
				    {
				    	Long total = totalVotersMap.get((Long)obj[0]);
				    	if(total == null)
				    	  totalVotersMap.put((Long)obj[0], (Long)obj[1]);
				    	else
				    	 totalVotersMap.put((Long)obj[0], total+(Long)obj[1]);
				     }
				  setYoungerVoterDetails(resultList, list, 0l, totalVotersMap, "totalVotersFromResultMap");
				    	 
				}	
				
				
			}catch (Exception e) {
			 e.printStackTrace();
			 log.error("Exception Occured in getHamletWiseYoungVoterDetails() method, Exception - "+e);
			}
		}
		
		 //hamlet or customward
		 public void getCustomWardWiseYoungVoterDetails(List<VotersDetailsVO> constituencyVotersList,Long publicationDateId,Long locationValue,Long userId,String type,Long totalVotersCount)
			{
				try{
				List<Object[]> list = userVoterDetailsDAO.getYoungerVoterDetailsForHamlet(publicationDateId, locationValue, userId, type, IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO);	
				
				if(list != null && list.size() > 0)
				{
				  VotersDetailsVO detailsVO = new VotersDetailsVO();
				  for(Object[] params:list)
				  {
					 detailsVO.setId((Long)params[0]);
					 detailsVO.setName(params[1] != null ?params[1].toString():" ");
					 if(params[3] != null && params[3].toString().equalsIgnoreCase(IConstants.MALE))
					  detailsVO.setTotalMaleVoters(detailsVO.getTotalMaleVoters() != null?detailsVO.getTotalMaleVoters()+(Long)params[2]:(Long)params[2]);
					 else if(params[3] != null && params[3].toString().equalsIgnoreCase(IConstants.FEMALE))
					  detailsVO.setTotalFemaleVoters(detailsVO.getTotalFemaleVoters() != null?detailsVO.getTotalFemaleVoters()+(Long)params[2]:(Long)params[2]);
					 else
					  detailsVO.setTotalUnknownVoters(detailsVO.getTotalUnknownVoters() != null?detailsVO.getTotalUnknownVoters()+(Long)params[2]:(Long)params[2]);
				  }
				  detailsVO.setTotalVoters(detailsVO.getTotalMaleVoters()+detailsVO.getTotalFemaleVoters()+detailsVO.getTotalUnknownVoters());
				  
				  if( detailsVO.getTotalVoters() != 0){
						 
						 detailsVO.setTotalMaleVotersPercent((float) ((long)detailsVO.getTotalMaleVoters()*100f/ detailsVO.getTotalVoters() ));
						 detailsVO.setTotalFemaleVotersPercent((float)((long)detailsVO.getTotalFemaleVoters() *100f/ detailsVO.getTotalVoters() ));
						  }else{
							  detailsVO.setTotalMaleVotersPercent(0.0f);
							  detailsVO.setTotalFemaleVotersPercent(0.0f);
						}
				  detailsVO.setTotalVotersPercent(detailsVO.getTotalVoters() != null ? (float)detailsVO.getTotalVoters()*100f/totalVotersCount : 0.0f);
				  detailsVO.setAgeRange(IConstants.YOUNG_VOTERS);
				  constituencyVotersList.add(detailsVO);
				}
				}catch (Exception e) {
					e.printStackTrace();
					log.error("Exception Occured in testgetCustomWardWiseYoungVoterDetails() method, Exception - "+e);
				}
				
			}		
	
	public ResultStatus calculateAndInsertLocalityWiseVoterInfo(Long constituencyId, Long publicationDateId,Long userId)
	{
		
	  ResultStatus resultStatus = new ResultStatus();
	 try{
		
		 Long reportLevelId = voterReportLevelDAO.getReportLevelIdByType(IConstants.LOCALITY);
		 
		 List<Object[]> hamletList = userVoterDetailsDAO.getLocalityIdsForHamletAndBooth(constituencyId, publicationDateId, userId, "hamletLocalities");
		 List<Object[]> wardList = userVoterDetailsDAO.getLocalityIdsForHamletAndBooth(constituencyId, publicationDateId, userId, "wardLocalities");
		 
		 List<SelectOptionVO> hamletLocalityList = null;
		 List<SelectOptionVO> wardLocalityList = null;
		 
		 if(hamletList != null && hamletList.size() > 0)
			 hamletLocalityList = getSelectOptionVOListForLocality(hamletList);
		 if(wardList != null && wardList.size() > 0)
			wardLocalityList = getSelectOptionVOListForLocality(wardList);
         
	     calculateAndInsertTheLocalityDataInVoterInfoTable(constituencyId,publicationDateId,userId,reportLevelId,hamletLocalityList,wardLocalityList);
	     calculateAndInsertTheLocalityDataInVoterAgeInfoTable(constituencyId,publicationDateId,userId,reportLevelId);
	     calculateAndInsertTheLocalityDataInVoterFamilyInfoTable(constituencyId,publicationDateId,userId,reportLevelId);
	     //calculateAndInsertVoterCasteInfoForLocality(constituencyId, publicationDateId, userId, reportLevelId);
	     
		 
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	  return resultStatus;
	 }catch (Exception e) {
      e.printStackTrace();
      log.error(" Exception Occured in calculateAndInsertLocalityWiseVoterInfo() method, Exception - "+e);
      resultStatus.setResultCode(ResultCodeMapper.FAILURE);
      return resultStatus;
	 }
	}
	
	public SelectOptionVO checkSelectOptionVOExists(Long reportLevelValue,List<SelectOptionVO> list)
	{
	  try{
		 if(list == null || list.size() == 0)
		  return null;
		 for(SelectOptionVO optionVO:list)
		  if(optionVO.getId().equals(reportLevelValue))
			 return optionVO;
		  
		  return null;  
	  }catch (Exception e) {
       e.printStackTrace();
       log.error(" Exception Occured in checkSelectOptionVOExists() method, Exception - "+e);
       return null;
	  }
	}
	
	public List<SelectOptionVO> getSelectOptionVOListForLocality(List<Object[]> list)
	{
		List<SelectOptionVO> selectOptionVOList = null;
		try{
		 if(list == null || list.size() == 0)
			return selectOptionVOList;	 
		 
		 selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		 SelectOptionVO optionVO = null;
		 for(Object[] params:list)
		 {
			 optionVO = checkSelectOptionVOExists((Long)params[0],selectOptionVOList);  
			 if(optionVO == null)
			 {
				 optionVO = new SelectOptionVO();
				 optionVO.setId((Long)params[0]);
				 selectOptionVOList.add(optionVO);
			 }
			 List<Long> localityIdsList = optionVO.getLocationValuesList();
			 if(localityIdsList == null)
				localityIdsList = new ArrayList<Long>(0);
			 if(!localityIdsList.contains((Long)params[1]))
				 localityIdsList.add((Long)params[1]);
			 
			 optionVO.setLocationValuesList(localityIdsList);	 
		 }
			
			return selectOptionVOList;	
		}catch (Exception e) {
		 e.printStackTrace();
		 log.error(" Exception Occured in getSelectOptionVOListForLocality() method, Exception - "+e);
		 return selectOptionVOList;
		}
	}
	
	
	public ResultStatus calculateAndInsertTheLocalityDataInVoterInfoTable(Long constituencyId,Long publicationDateId,Long userId,Long reportLevelId,List<SelectOptionVO> hamletLocalityList,List<SelectOptionVO> wardLocalityList)
	{
		ResultStatus resultStatus = new ResultStatus();
		 try{
			 
			 List<VotersInfoForMandalVO> votersInfoMandalVOList = null;
			 List<Object[]> familiesList = userVoterDetailsDAO.getFamiliesCountForLocality(constituencyId, publicationDateId, userId);
			 Map<Long,Long> familiesMap = new HashMap<Long, Long>(0);
			 if(familiesList != null && familiesList.size() > 0)
			  for(Object[] params:familiesList)
				familiesMap.put((Long)params[1], (Long)params[0]);
			 
			 List<Object[]> list = userVoterDetailsDAO.getGenderWiseVotersCountForLocality(constituencyId, publicationDateId, userId);
			 if(list != null && list.size() > 0)
			 {
				Map<Long,Long> totalVotersMap = new HashMap<Long, Long>(0);//<localityId,totalVoters>
				votersInfoMandalVOList = new ArrayList<VotersInfoForMandalVO>(0);
				VotersInfoForMandalVO infoForMandalVO = null;
				for(Object[] params:list)
				{
				  infoForMandalVO = checkVotersInfoForMandalVOExist((Long)params[2],votersInfoMandalVOList);
				  if(infoForMandalVO == null)
				  {
					  infoForMandalVO = new VotersInfoForMandalVO();
					  infoForMandalVO.setConstituencyId(constituencyId);
					  infoForMandalVO.setReportLevelId(reportLevelId);
					  infoForMandalVO.setPublicationDateId(publicationDateId);
					  infoForMandalVO.setReportLevelValue((Long)params[2]);
					  infoForMandalVO.setFemaleVoters(familiesMap.get((Long)params[2]));
					  votersInfoMandalVOList.add(infoForMandalVO);
				  }
				  
				  if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.FEMALE))
						infoForMandalVO.setTotalFemaleVoters(params[0]!= null?params[0].toString():"0");
					  
					  else if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.MALE))
						infoForMandalVO.setTotalMaleVoters(params[0]!= null?params[0].toString():"0");
					  
					  Long totalVotersCount = totalVotersMap.get((Long)params[2]);
					  if(totalVotersCount == null)
						totalVotersMap.put((Long)params[2], (Long)params[0]);
					  else
						totalVotersMap.put((Long)params[2], totalVotersCount+(Long)params[0]);
					}
					for(VotersInfoForMandalVO mandalVO:votersInfoMandalVOList)
					{
						Long totalVoters = totalVotersMap.get(mandalVO.getReportLevelValue());
						mandalVO.setTotalVoters(totalVoters != null?totalVoters.toString():"0");
						if(totalVoters != null && totalVoters > 0)
						{
						  if(mandalVO.getTotalMaleVoters() != null)
						   mandalVO.setTotalMalePercentage(new BigDecimal((new Double(mandalVO.getTotalMaleVoters())*100.0)/totalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						  if(mandalVO.getTotalFemaleVoters() != null)
						  mandalVO.setTotalFemalePercentage(new BigDecimal((new Double(mandalVO.getTotalFemaleVoters())*100.0)/totalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						  //mandalVO.setTotalFamilyPercentage(new BigDecimal((new Double(mandalVO.getTotalFamilies())*100)/totalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						}
					}
					
					Map<Long,Long> localityIdsMap = new HashMap<Long, Long>(0);//<LocalityId,HamletOrWardId>
					if(hamletLocalityList != null && hamletLocalityList.size() > 0)
						getLocalityAndWardOrHamletIdsMap(localityIdsMap, hamletLocalityList);
					
					if(wardLocalityList != null && wardLocalityList.size() > 0)
						getLocalityAndWardOrHamletIdsMap(localityIdsMap, wardLocalityList);
					
					Map<Long,Long> wardOrHamletTotalVotersMap = new HashMap<Long, Long>(0);//<hamletOrWardId,totalVoters>
					 getTotalVotersForHamletOrWard(totalVotersMap,hamletLocalityList,wardLocalityList,wardOrHamletTotalVotersMap);
					
					
					if(votersInfoMandalVOList != null && votersInfoMandalVOList.size() > 0)
					{
					
					  for(VotersInfoForMandalVO mandalVO :votersInfoMandalVOList)
					  {
						  Long hamletOrWardId = localityIdsMap.get(mandalVO.getReportLevelValue());
						  Long parentTotalVoters = wardOrHamletTotalVotersMap.get(hamletOrWardId);
						  if(parentTotalVoters != null && parentTotalVoters > 0)
						   mandalVO.setTotalVotersPercentage(new BigDecimal((new Double(mandalVO.getTotalVoters())*100.0)/parentTotalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					  }
					  
					  for(VotersInfoForMandalVO votersInfo:votersInfoMandalVOList)
					    saveVotersDataInVoterInfoTable(votersInfo);
					}
				 
			 }
			 
			 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
		      e.printStackTrace();
		      log.error(" Exception Occured in calculateAndInsertTheLocalityDataInVoterInfoTable() method, Exception - "+e);
		      resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		      return resultStatus;
			 }
		 
	}
	
	public void getLocalityAndWardOrHamletIdsMap(Map<Long,Long> localityIdsMap,List<SelectOptionVO> list)
	{
	  try{
		
		  for(SelectOptionVO optionVO:list)
		  {
			if(optionVO.getLocationValuesList() != null && optionVO.getLocationValuesList().size() > 0)
			  for(Long localityId:optionVO.getLocationValuesList())
				  localityIdsMap.put(localityId, optionVO.getId());
		  }
		
	  }catch (Exception e) {
		e.printStackTrace();
		log.error(" Exception Occured in getLocalityAndWardOrHamletIdsMap() method, Exception - "+e);
	  }
	}
	
	
	public void getTotalVotersForHamletOrWard(Map<Long,Long> localityTotalVotersMap,List<SelectOptionVO> hamletLocalityList,List<SelectOptionVO> wardLocalityList,Map<Long,Long> wardOrHamletTotalVotersMap)
	{
	  try{
		
	  if(localityTotalVotersMap != null && localityTotalVotersMap.size() > 0)
	  {
		  if(hamletLocalityList != null && hamletLocalityList.size() > 0)
		    getLocalityWiseTotalVotersMap(localityTotalVotersMap, hamletLocalityList, wardOrHamletTotalVotersMap);
		  
		  if(wardLocalityList != null && wardLocalityList.size() > 0)
		    getLocalityWiseTotalVotersMap(localityTotalVotersMap, wardLocalityList, wardOrHamletTotalVotersMap);
		  
	  }
		  
	  }catch (Exception e) {
       e.printStackTrace();
       log.error(" Exception Occured in getTotalVotersForHamletOrWard() method, Exception - "+e);
	  }
	}
	
	public void getLocalityWiseTotalVotersMap(Map<Long,Long> localityTotalVotersMap,List<SelectOptionVO> list,Map<Long,Long> wardOrHamletTotalVotersMap)
	{
	  try{
			 for(Long localityId:localityTotalVotersMap.keySet())
			 {
				  for(SelectOptionVO optionVO :list)
				  {
					  if(optionVO.getLocationValuesList()!= null && optionVO.getLocationValuesList().size() > 0 
							  && optionVO.getLocationValuesList().contains(localityId))
					  {
						Long totalVoters = wardOrHamletTotalVotersMap.get(optionVO.getId());
						if(totalVoters == null)
							wardOrHamletTotalVotersMap.put(optionVO.getId(), localityTotalVotersMap.get(localityId));
						else
							wardOrHamletTotalVotersMap.put(optionVO.getId(), totalVoters+localityTotalVotersMap.get(localityId));
					  }
				  }
			 }
		  
	  }catch (Exception e) {
		e.printStackTrace();
		log.error(" Exception Occured in getLocalityWiseTotalVotersMap() method, Exception - "+e);
	   }
	}
	
	//Locality Age info
	
	public ResultStatus calculateAndInsertTheLocalityDataInVoterAgeInfoTable(Long constituencyId,Long publicationDateId,Long userId,Long reportLevelId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
		
		  List<VoterAgeRangeVO> ageRangeVOsList = null;
		  List<Object[]> list = userVoterDetailsDAO.getLocalityWiseVoterAgeInfo(constituencyId, publicationDateId, userId);
		 
		  Map<Long,Long> totalVotersMap = new HashMap<Long, Long>(0);
		  if(list != null && list.size() > 0)
		  {
			 ageRangeVOsList = new ArrayList<VoterAgeRangeVO>(0);
			 setLocalityWiseVotersData(constituencyId, publicationDateId, reportLevelId, list, ageRangeVOsList);
			 
			 for(Object[] params:list)
			 {
			    Long totalVoters = totalVotersMap.get((Long)params[2]);
			     if(totalVoters == null)
				 totalVotersMap.put((Long)params[2], (Long)params[0]);
				else
				 totalVotersMap.put((Long)params[2], totalVoters+(Long)params[0]);
			 }
			
		  //young voters implementation 
		   List<Object[]> youngVotersList = userVoterDetailsDAO.getLocalityWiseYoungVotersAgeInfo(constituencyId, publicationDateId, userId, IConstants.YOUNG_VOTERS_AGE_FROM, IConstants.YOUNG_VOTERS_AGE_TO);
		   if(youngVotersList != null && youngVotersList.size() > 0)
			 getYoungVotersDetails(constituencyId, publicationDateId, null, userId, "localityWiseYoungVotersImpl", ageRangeVOsList, reportLevelId);
		   
		   for(VoterAgeRangeVO rangeVO :ageRangeVOsList)
		   {
			 rangeVO.setMalePercentage(new BigDecimal((rangeVO.getMaleVoters().doubleValue()*100.0)/rangeVO.getTotalVotersInARange().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()); 
			 rangeVO.setFemalePercentage(new BigDecimal((rangeVO.getFemaleVoters().doubleValue()*100.0)/rangeVO.getTotalVotersInARange().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			 rangeVO.setPercentage(new BigDecimal((rangeVO.getTotalVotersInARange().doubleValue()*100.0)/totalVotersMap.get(rangeVO.getReportLevelValue()).doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
		   }
			
			saveVoterAgeDetailsInVoterAgeInfo(ageRangeVOsList);
		  
		  }
			
		  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
		 return resultStatus;
		}catch (Exception e) {
		   e.printStackTrace();
		   log.error(" Exception Occured in calculateAndInsertTheLocalityDataInVoterAgeInfoTable() method, Exception - "+e);
		   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		  return resultStatus;
		}
	}
	
	public void setLocalityWiseVotersData(Long constituencyId,Long publicationDateId,Long reportLevelId,List<Object[]> list,List<VoterAgeRangeVO> ageRangeVOsList)
	{
		try{
		 if(list != null && list.size() > 0)
		 {
			 VoterAgeRangeVO ageRangeVO = null;
				for(Object[] params:list)
				{
					ageRangeVO = checkAgeInfoVOExists((Long)params[2],(Long)params[3],ageRangeVOsList);
					if(ageRangeVO == null)
					{
						ageRangeVO = new VoterAgeRangeVO();
						ageRangeVO.setConstituencyId(constituencyId);
						ageRangeVO.setReportLevelId(reportLevelId);
						ageRangeVO.setPublicationDateId(publicationDateId);
						ageRangeVO.setReportLevelValue((Long)params[2]);
						ageRangeVO.setAgeRangeId((Long)params[3]);
						ageRangeVOsList.add(ageRangeVO);
					}
					
					if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.MALE))
					 ageRangeVO.setMaleVoters((Long)params[0]);
					else if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					 ageRangeVO.setFemaleVoters((Long)params[0]);
					
					ageRangeVO.setTotalVotersInARange(ageRangeVO.getMaleVoters()+ageRangeVO.getFemaleVoters());
				} 
		 }
			
		}catch (Exception e) {
		 e.printStackTrace();
		 log.error(" Exception Occured in setLocalityWiseVotersData() method, Exception - "+e);
		}
	}
	
  //Locality Family data
	
	public ResultStatus calculateAndInsertTheLocalityDataInVoterFamilyInfoTable(Long constituencyId,Long publicationDateId,Long userId,Long reportLevelId)
	{
	  ResultStatus resultStatus = new ResultStatus();
	  try{
		
		List<Object[]> list = userVoterDetailsDAO.getLocalityWiseFamilyDetails(constituencyId, publicationDateId, userId);

		if(list != null && list.size() > 0)
		{
			Map<Long,Map<Long,Long>> familyMap = new HashMap<Long, Map<Long,Long>>(0);//<localityId,<familyRange,totalFamilies>>
			Map<Long,Long> totalVotersCountMap = new HashMap<Long, Long>(0);//<localityId,totalFamilies>
		
		    for(Object[] params :list)
			  {
				  Map<Long,Long> totalFamilies = familyMap.get((Long)params[0]);
				  if(totalFamilies == null)
				  {
					  totalFamilies = new HashMap<Long,Long>(0);
					  familyMap.put((Long)params[0], totalFamilies);
				  }
					  Long totalFamiliesCount = 0L;
					  Long familyRangeId = 0L;
					  Long familyCount = (Long)params[2]; 
					  if(familyCount <=3)
					  {
						  familyRangeId = getVoterFamilyRangeIdByFamilyRange("0-3");
						  totalFamiliesCount =  totalFamilies.get(familyRangeId); 
						  if(totalFamiliesCount == null)
							  totalFamilies.put(familyRangeId, 1L);
						  else
							  totalFamilies.put(familyRangeId, totalFamiliesCount+1);
					  }
					  else if(familyCount.longValue() >=4 && familyCount.longValue() <= 6)
					  {
						  familyRangeId = getVoterFamilyRangeIdByFamilyRange("4-6");
						  totalFamiliesCount =  totalFamilies.get(familyRangeId); 
						  if(totalFamiliesCount == null)
							  totalFamilies.put(familyRangeId, 1L);
						  else
							  totalFamilies.put(familyRangeId, totalFamiliesCount+1);  
					  }
					  else if(familyCount.longValue() >=7 && familyCount.longValue() <= 10)
					  {
						  familyRangeId = getVoterFamilyRangeIdByFamilyRange("7-10");
						  totalFamiliesCount =  totalFamilies.get(familyRangeId); 
						  if(totalFamiliesCount == null)
							  totalFamilies.put(familyRangeId, 1L);
						  else
							  totalFamilies.put(familyRangeId, totalFamiliesCount+1); 
					  }
					  else  if(familyCount.longValue() >10)
					  {
						  familyRangeId = getVoterFamilyRangeIdByFamilyRange("10-Above");
						  totalFamiliesCount =  totalFamilies.get(familyRangeId); 
						  if(totalFamiliesCount == null)
							  totalFamilies.put(familyRangeId, 1L);
						  else
							  totalFamilies.put(familyRangeId, totalFamiliesCount+1);  
					  }
					  
				//  }
				  
				  Long totalVoters = totalVotersCountMap.get((Long)params[0]);
				  if(totalVoters == null)
					  totalVotersCountMap.put((Long)params[0], 1L);
				  else
					  totalVotersCountMap.put((Long)params[0], totalVoters+1);
				  
			  }
			  
			  List<ImportantFamiliesInfoVo> importantFamiliesInfoVoList = new ArrayList<ImportantFamiliesInfoVo>(0);
			  if(familyMap != null && familyMap.size() > 0)
			  {
				  for(Long localityId :familyMap.keySet())
				  {
					 Map<Long,Long> totalVotersMap = familyMap.get(localityId);
					 if(totalVotersMap != null)
					 {
						for(Long familyRangeId :totalVotersMap.keySet())
						{
						  ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
						  importantFamiliesInfoVo.setReportLevelId(reportLevelId);
						  importantFamiliesInfoVo.setReportLevelValue(localityId);
						  importantFamiliesInfoVo.setTypeId(familyRangeId);
						  importantFamiliesInfoVo.setTotalFamalies(totalVotersMap.get(familyRangeId));
						  importantFamiliesInfoVo.setConstituencyId(constituencyId);
						  importantFamiliesInfoVo.setPublicationDateId(publicationDateId);
						  importantFamiliesInfoVo.setTotalPercentage(new BigDecimal(importantFamiliesInfoVo.getTotalFamalies().doubleValue()*100.0/totalVotersCountMap.get(localityId).doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						  importantFamiliesInfoVoList.add(importantFamiliesInfoVo);
						  
						}
					 }
				  }
			  }
			  
			  for(ImportantFamiliesInfoVo familiesInfoVo:importantFamiliesInfoVoList)
				saveVotersDataInVoterFamilyInfoTable(familiesInfoVo);
		    
		}
		  
		  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		 return resultStatus;
	   }catch (Exception e) {
		e.printStackTrace();
		log.error(" Exception Occured in calculateAndInsertTheLocalityDataInVoterFamilyInfoTable() method, Exception - "+e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		return resultStatus;
	   }
	}
	
	//locality caste data
	public ResultStatus calculateAndInsertVoterCasteInfoForLocality(Long constituencyId,Long publicationDateId,Long userId,Long reportLevelId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			List<Object[]> list =  userVoterDetailsDAO.getLocalityWiseCasteDetails(constituencyId, publicationDateId, userId);
			
			if(list != null && list.size() > 0)
			{
			//voter Cast info
			List<VotersInfoForMandalVO> votersInfoForMandalVOsList = new ArrayList<VotersInfoForMandalVO>(0);
			VotersInfoForMandalVO voterCastInfo = null;
			Map<Long,Long> totalCastMap = new HashMap<Long, Long>(0);
			Map<Long,Long> localityTotalCastesMap = new HashMap<Long, Long>(0);
			
			for(Object[] params:list)
			{
				  
				  voterCastInfo = getVotersInfoForMandalVO((Long)params[0], (Long)params[3], votersInfoForMandalVOsList);
				  if(voterCastInfo == null)
				  { 
					  voterCastInfo = new VotersInfoForMandalVO();
					  voterCastInfo.setReportLevelId(reportLevelId);
					  voterCastInfo.setReportLevelValue((Long)params[0]);
					  voterCastInfo.setConstituencyId(constituencyId);
					  voterCastInfo.setPublicationDateId(publicationDateId);
					  voterCastInfo.setCasteStateId((Long)params[3]);
					  votersInfoForMandalVOsList.add(voterCastInfo);
				  }
				  
				  if(params[2] != null && params[2].toString().equalsIgnoreCase(IConstants.MALE))
					  voterCastInfo.setMaleVoters((Long)params[1]);
				  else if(params[2] != null && params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
					  voterCastInfo.setFemaleVoters((Long)params[1]);
				  
				  voterCastInfo.setTotalVotersDiff(voterCastInfo.getMaleVoters()+voterCastInfo.getFemaleVoters());
				 
				  
				  //locality totalCastes
				  Long totalCasteVoters = localityTotalCastesMap.get((Long)params[0]);
				  if(totalCasteVoters == null)
					  localityTotalCastesMap.put((Long)params[0], (Long)params[1]);
				  else
					  localityTotalCastesMap.put((Long)params[0], totalCasteVoters+(Long)params[1]); 
				  
		    }
			
			List<Object[]> totalCastList = userVoterDetailsDAO.getLocalityTotalCastes(constituencyId, publicationDateId, userId);
			if(totalCastList!= null && totalCastList.size() > 0)
			  for(Object[] params:totalCastList)
				  totalCastMap.put((Long)params[0], (Long)params[1]);
			
			if(votersInfoForMandalVOsList != null && votersInfoForMandalVOsList.size() > 0)
			{
			  for(VotersInfoForMandalVO mandalVO:votersInfoForMandalVOsList)
				mandalVO.setTotalVotersPercentage(new BigDecimal((new Double(mandalVO.getTotalVotersDiff())*100.0)/localityTotalCastesMap.get(mandalVO.getReportLevelValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  
			}
			
			
			 saveVoterCastInfoForHamletBooth(votersInfoForMandalVOsList,userId);
			 
			 
			//voter cast basic info
				
				List<Object[]> totalVotersList = voterInfoDAO.getTotalVotersForHamletBooth(constituencyId, publicationDateId, reportLevelId);
				Map<Long,Long> totalVotersMap = new HashMap<Long, Long>(0);
				if(totalVotersList != null && totalVotersList.size() > 0)
				  for(Object[] params :totalVotersList)
				   totalVotersMap.put((Long)params[0], (Long)params[1]); 
			
			
			List<VotersInfoForMandalVO> voterCastBasicList = new ArrayList<VotersInfoForMandalVO>(0);
			VotersInfoForMandalVO infoForMandalVO = null;
			for(Object[] params:list)
			{
				infoForMandalVO = checkVoterCasteInfoExists((Long)params[0], voterCastBasicList);
				if(infoForMandalVO == null)
				{
					infoForMandalVO = new VotersInfoForMandalVO();
					infoForMandalVO.setReportLevelId(reportLevelId);
					infoForMandalVO.setReportLevelValue((Long)params[0]);
					infoForMandalVO.setPublicationDateId(publicationDateId);
					infoForMandalVO.setConstituencyId(constituencyId);
					infoForMandalVO.setTotalCasts(totalCastMap.get((Long)params[0])!= null?totalCastMap.get((Long)params[0]):0L);
					infoForMandalVO.setTotalVotersDiff(totalVotersMap.get((Long)params[0]) != null?totalVotersMap.get((Long)params[0]):0L);
					voterCastBasicList.add(infoForMandalVO);
				}
				if(params[4] != null && params[4].toString().equalsIgnoreCase("OC"))
				 infoForMandalVO.setOCVoters((Long)params[1]);
				else if(params[4] != null && params[4].toString().equalsIgnoreCase("BC"))
				 infoForMandalVO.setBCVoters((Long)params[1]);
				else if(params[4] != null && params[4].toString().equalsIgnoreCase("SC"))
				 infoForMandalVO.setSCVoters((Long)params[1]);
				else if(params[4] != null && params[4].toString().equalsIgnoreCase("ST"))
				  infoForMandalVO.setSTVoters((Long)params[1]);
				
				infoForMandalVO.setCastAssignedVoters(infoForMandalVO.getOCVoters()+infoForMandalVO.getBCVoters()+infoForMandalVO.getSCVoters()+infoForMandalVO.getSTVoters());
				
			}
			
			if(voterCastBasicList != null && voterCastBasicList.size() >0)
			{
			 for(VotersInfoForMandalVO mandalVO :voterCastBasicList)
			  mandalVO.setTotalVotersDiff(mandalVO.getTotalVotersDiff()-mandalVO.getCastAssignedVoters());
			
			  saveVoterCastBasicInfoForHamletBoothModel(voterCastBasicList,userId);
			}
		 
		 }	
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;	
		}catch (Exception e) {
		 e.printStackTrace();
		 log.error(" Exception Occured in calculateAndInsertVoterCasteInfoForLocality() method, Exception - "+e);
		 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 return resultStatus;
		}
	}
	
	public VotersInfoForMandalVO getVotersInfoForMandalVO(Long reportLevelValue,Long castStateId,List<VotersInfoForMandalVO> list)
	{
		try{
			if(list == null || list.size() == 0)
				return null;
			for(VotersInfoForMandalVO mandalVO:list)
			 if(mandalVO.getReportLevelValue().equals(reportLevelValue)&&mandalVO.getCasteStateId().equals(castStateId))
				return mandalVO;
			
			return null;	
		}catch (Exception e) {
         e.printStackTrace();
         log.error(" Exception Occured in getVotersInfoForMandalVO() method, Exception - "+e);
         return null;
		}
	}
	public List<SelectOptionVO> getPublicationListForVoterDataByConstituency(Long constituencyId)
	{
		List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		
		try{
			//List<Object[]> publicationDetails = boothPublicationVoterDAO.getPublicationDetailsBasedOnConstituency(constituencyId);
			List<Object[]> publicationDetails = voterDataAvailableConstituenciesDAO.getPublicationDatesBasedOnConstituency(constituencyId);
			if(publicationDetails != null && publicationDetails.size() > 0)
			{
				for(Object[] param : publicationDetails)
				{
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					Date date = (Date)param[1];
					selectOptionVO.setId((Long)param[0]);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					selectOptionVO.setName(calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR));
					selectOptionVOList.add(selectOptionVO);
				}
			}
			
			return selectOptionVOList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getPublicationListForVoterDataByConstituency() Method, Exception - "+e);
			return selectOptionVOList;
		}
	}
	
	
	public List<SelectOptionVO> getConstituencyList1(List<SelectOptionVO> userAccessConstituencyList)
	{
		List<SelectOptionVO> constituencyList = new ArrayList<SelectOptionVO>(0);
		List<Long> constituencyIds = new ArrayList<Long>();
			try{		
			for (SelectOptionVO selectOptionVO : userAccessConstituencyList) 
		    {
					Long constituenciesId = selectOptionVO.getId();
					constituencyIds.add(constituenciesId);
			}
		
			List<Object[]> constituencyId =  constituencyDAO.getRuralAndRuralUrbanConstiencies(constituencyIds);
			SelectOptionVO selectOptionVO = null;
			if(constituencyId != null && constituencyId.size() > 0)
			{
				for(Object[] param : constituencyId)
				{					
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)param[0]);
					selectOptionVO.setName(param[1].toString());
					constituencyList.add(selectOptionVO);
				}
			}
			Collections.sort(constituencyList);
			return constituencyList;
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getConstituencyList1() Method, Exception - "+e);
			return constituencyList;
		}
	}
	
	public List<SelectOptionVO> getPartilaBoothsMappedConstituencies(List<SelectOptionVO> userAccessConstituencyList)
	{
		try
		{
			List<Long> constituencyIds = partialBoothPanchayatDAO.getPartilaBoothsMappedConstituencies();

			java.util.Iterator<SelectOptionVO> itr = userAccessConstituencyList.iterator();
			
			while(itr.hasNext())
			{
				SelectOptionVO vo = itr.next();
				
				if(!constituencyIds.contains(vo.getId()))
					itr.remove();
				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			log.error("Exception Occured in getPartilaBoothsMappedConstituencies() Method, Exception - "+e);
		}
		return userAccessConstituencyList;
	}
	
	public List<VoterVO> getFlagVoterDetails(Long constituneycId,Long locationId,
			Long publicationId,String type,Long flagId,Integer startIndex,Integer maxRecords,
			Long userId)
	{
		List<VoterVO> resultList = new ArrayList<VoterVO>();
		List<Object[]> dataList = null;
		List count =null;
		try{
			
			if(type.equalsIgnoreCase(IConstants.MANDAL) || type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
			{
				if((locationId.toString().substring(0, 1).toString().trim().equalsIgnoreCase("1")))
					
						{
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationId.toString().substring(1)));
				locationId = (Long) list.get(0);
						}
				else
				locationId = new Long(locationId.toString());
				
			}
			if(type.equalsIgnoreCase(IConstants.HAMLET) ||  type.equalsIgnoreCase("customWard"))
			{
			
				dataList =  voterFlagDAO.getFlagVoterDetailsForHamlet(constituneycId,locationId,flagId,type,publicationId, startIndex,
						 maxRecords);
				count = voterFlagDAO. getFlagVoterDetailsForHamletCount(constituneycId,locationId,flagId,type,publicationId
						);
			}
			else
			{
			dataList = voterFlagDAO.getFlagVoterDetailsByLocationId(constituneycId,locationId,flagId,type,publicationId, startIndex,
					 maxRecords);
			count = voterFlagDAO.getFlagVoterDetailsByLocationIdCount(constituneycId,locationId,flagId,type,publicationId
					);
			}
			 if(dataList != null && dataList.size() > 0)
			 {
				for(Object[] params : dataList)
				{
					Voter voter = (Voter) params[0];
					VoterVO voterVO = new VoterVO();
					voterVO.setName(voter.getName());
					voterVO.setVoterId(voter.getVoterId().toString());
					voterVO.setAge(voter.getAge());
					voterVO.setMobileNo(voter.getMobileNo() != null ?voter.getMobileNo() : " ");
					voterVO.setVoterIDCardNo(voter.getVoterIDCardNo());
					voterVO.setFlagName(params[2].toString());
					voterVO.setColor(params[3].toString().substring(1));
					voterVO.setGender(voter.getGender());
					voterVO.setHouseNo(voter.getHouseNo());
					voterVO.setFirstName(voter.getRelativeName());
					resultList.add(voterVO);
					
					
				}
				if(count != null && count.size() > 0)
					resultList.get(0).setTotalVoters((Long)count.get(0));
			 }
		}
		catch(Exception e)
		{
			log.error("Exception Occured in getFlagVoterDetails () Method, Exception - "+e);
		}
		return resultList;
	}
	
	public List<VoterHouseInfoVO> getVotersOfSearchedCriteriaForHH(String voterCardNo,String voterName,Long boothId,Long publicationId){
		List<Long> cnstncyList=boothDAO.getConstituneycId(boothId);
		Long constiuencyId=cnstncyList.get(0).longValue();
		
		StringBuilder qryString=new StringBuilder();
		
		qryString.append(" and model.booth.constituency.constituencyId ="+constiuencyId+"");
		
		if(voterCardNo.trim().length()>0 ){
			qryString.append(" and model.voter.voterIDCardNo = '"+voterCardNo+"'");
		}else if(voterName.trim().length()>0){
			qryString.append(" and model.voter.name like '%"+voterName+"%'");
		}
		
		qryString.append(" order by model.voter.name ");
		
		
		List<Object[]> voterDataList=userVoterDetailsDAO.getVotersDetailsBySearchCriteriaForHouseHolds(publicationId,qryString.toString());
		List<Long> voterIds=new ArrayList<Long>();
		
		if(voterDataList.size()>0){
			for(Object[] vData:voterDataList){
				Voter voter=(Voter)vData[0];
				Long voterId=voter.getVoterId();
				Long bid=Long.valueOf(vData[1].toString());
				Long boothPartNo=Long.valueOf(vData[2].toString());
				Long serailNo=Long.valueOf(vData[3].toString());
				
				voterIds.add(voterId);
				//Voter voter=vData[0].toString();
			}
		}
		
		
		List<Long> ctgrysReqForHHSurveyList=new ArrayList<Long>();
    	ctgrysReqForHHSurveyList.add(IConstants.HOUSE_HOLD_VOTER_OCCUPATION);
    	ctgrysReqForHHSurveyList.add(IConstants.HOUSE_HOLD_VOTER_EDUCATION);
    	ctgrysReqForHHSurveyList.add(IConstants.HOUSE_HOLD_VOTER_SOCIAL_POSITIONS);
        List<Object[]> votersCategoriesList=new ArrayList<Object[]>();
        List<Object[]> hhVoterRelations =new ArrayList<Object[]>();
		if(voterIds != null && voterIds.size()>0){
		 votersCategoriesList = 
				 voterCategoryValueDAO.getVoterCategoryValuesForVotersForHHSurvey(1l,voterIds,ctgrysReqForHHSurveyList);		
	             hhVoterRelations =  houseHoldVoterDAO.getVoterRelationsByVoterIds(voterIds);
		}
	   Map<Long,Long> hhVoterRelMap=new HashMap<Long, Long>();
	   if(hhVoterRelations.size()>0){
		   for(Object[] obj:hhVoterRelations){
			   hhVoterRelMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[1].toString()));
		   }
		   
	   }
	   List<Object[]> relationsList= voterFamilyRelationDAO.getAllRelations();
	   List<GenericVO> relsList=new ArrayList<GenericVO>();
	   	GenericVO defaultGvo = new GenericVO();
	   	defaultGvo.setId(0l);
	   	defaultGvo.setName("Select");
	   	
	   	relsList.add(defaultGvo);
		
	    for(Object[] ob:relationsList){
	    	GenericVO gvo=new GenericVO();
	    	gvo.setId(Long.valueOf(ob[0].toString()));
	    	gvo.setName(ob[1].toString());
	    	
	    	relsList.add(gvo);
	    }
	    List<VoterHouseInfoVO> voterHouseInfoVOList=new ArrayList<VoterHouseInfoVO>();
	    for(Object[] obj:voterDataList){
	    	VoterHouseInfoVO voterHouseInfoVO=new VoterHouseInfoVO();
	    	Voter voter=(Voter)obj[0];
	    	voterHouseInfoVO = new VoterHouseInfoVO();
	    	voterHouseInfoVO.setsNo(Long.valueOf(obj[3].toString()));
	    	voterHouseInfoVO.setName(voter.getName());
	    	voterHouseInfoVO.setGender(voter.getGender());
	    	voterHouseInfoVO.setAge(voter.getAge());
	    	voterHouseInfoVO.setHouseNo(voter.getHouseNo());
	    	voterHouseInfoVO.setGaurdian(voter.getRelativeName());
	    	voterHouseInfoVO.setRelationship(voter.getRelationshipType());
	    	voterHouseInfoVO.setVoterIdCardNo(voter.getVoterIDCardNo());
	    	
	    	voterHouseInfoVO.setVoterId(voter.getVoterId());
	    	voterHouseInfoVO.setBoothId(Long.valueOf(obj[1].toString()));
	    	/*if(mobileNosMap.get(voter.getVoterId()) != null)
	    	 voterHouseInfoVO.setMobileNo(mobileNosMap.get(voter.getVoterId()));
	    	else
	    	 voterHouseInfoVO.setMobileNo("N/A");*/
	    	//voterHouseInfoVO.setBoothName(boothDAO.getPartNoByBoothId(voterHouseInfoVO.getBoothId()).get(0).toString());
	    	voterHouseInfoVO.setBoothName(obj[2].toString());
	    	//VoterHouseInfoVO voterCastPartyVO = voterCastePartyDetails.get(voter.getVoterId());
	    	
	    	Map<Long,List<GenericVO>> categoriesMap=getCategoriesForHHSurvey();
	    	List<GenericVO> occupationList=new ArrayList<GenericVO>();
	    	List<GenericVO> educationList=new ArrayList<GenericVO>();
	    	List<GenericVO> socialPositionList=new ArrayList<GenericVO>();
	    	
	    	GenericVO gvo = new GenericVO();
			gvo.setId(0l);
			gvo.setName("Select");
			occupationList.add(gvo);
			educationList.add(gvo);
			socialPositionList.add(gvo);
	    	

			for (Entry<Long, List<GenericVO>> entry : categoriesMap.entrySet())
			{
				//System.out.println(entry.getKey() + "/" + entry.getValue());
				if(entry.getKey()==IConstants.HOUSE_HOLD_VOTER_OCCUPATION){
					occupationList.addAll(entry.getValue());
				}else if(entry.getKey()==IConstants.HOUSE_HOLD_VOTER_EDUCATION){
					educationList.addAll(entry.getValue());
				}else if(entry.getKey()==IConstants.HOUSE_HOLD_VOTER_SOCIAL_POSITIONS){
					socialPositionList.addAll(entry.getValue());
				}
			}
			
			voterHouseInfoVO.setOccupationList(occupationList);
			voterHouseInfoVO.setEducationList(educationList);
			voterHouseInfoVO.setSocialPositionList(socialPositionList);
			
			voterHouseInfoVO.setFamilyRelsList(relsList);
			
			if(hhVoterRelMap.get(voter.getVoterId())!=null){
				voterHouseInfoVO.setVoterFamilyRelId(hhVoterRelMap.get(voter.getVoterId()));
			}else{
				voterHouseInfoVO.setVoterFamilyRelId(0l);
			}
			
			
	    	try {
				setVotersCategories(votersCategoriesList,voter,voterHouseInfoVO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//setCastePartyDetails(voterHouseInfoVO,voterCastPartyVO);
	    	
	    
	    	voterHouseInfoVOList.add(voterHouseInfoVO);
	    	//getUserCasteAndSelectedParty(voterHouseInfoVO , voter.getVoterId(),userId);
	    	
	    	
	    	//sno = sno+1;
	    	
	    }
		
		return voterHouseInfoVOList;
	}
	
	public List<GenericVO> getHHLeadersList(Long boothId){
		
		List<HHBoothLeader> leadersList=hhBoothLeaderDAO.getAllLeaderModelByBoothId(boothId);
		List<GenericVO> hbLdrList=new ArrayList<GenericVO>();
		
		GenericVO gvo_defa=new GenericVO();
		gvo_defa.setId(0l);
		gvo_defa.setName("Select");
		
		hbLdrList.add(gvo_defa);
		
		
		
		for(HHBoothLeader hbLdr:leadersList){
			GenericVO gvo=new GenericVO();
			
			gvo.setId(hbLdr.getHhLeader().getId());
			gvo.setName(hbLdr.getHhLeader().getName() +"-"+ hbLdr.getHhLeader().getVoterId());
			
			hbLdrList.add(gvo);
		}
		return hbLdrList;
		
		
	}
	
	public List<GenericVO> getHHLeadersBooksList(Long leaderId){
		List<Object[]> bookList = hhLeaderBooksDAO.getBooksOfLeader(leaderId);
		List<GenericVO> booksList = new ArrayList<GenericVO>();
		
		if(bookList!=null && bookList.size()>0){
				for(Object[] ob:bookList){
	    			GenericVO gv = new GenericVO();
	    			gv.setId(Long.valueOf(ob[0].toString()));
	    			gv.setName(ob[1].toString());
	    			booksList.add(gv);
	    		}
	    	}
		booksList.add(0, new GenericVO(0l, "Select Book"));
		return booksList;
	}
	
	public Long bookIdOfVoter(Long voterId){
		Long bookId = null;
		
		bookId = houseHoldVoterDAO.getBookIdOfVoter(voterId);
		
		return bookId;
	}
	  
	  public List<SelectOptionVO> insertVotersDataInIntermediateTablesForDistrict(Long districtId, Long publicationDateId,Long userId)
	  {
		  log.info(" Entered into insertVotersDataInIntermediateTables() Method, with Values - Report Level Value - "+districtId+" and Publicarion Date Id - "+publicationDateId);
		  List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  SelectOptionVO mainvo = new SelectOptionVO();
			 
			  List<SelectOptionVO> constituencies = staticDataService.getConstituenciesFordistricts(districtId);
			  
				
			  if(constituencies != null && constituencies.size() > 0)
			  {
			  for(SelectOptionVO vo :constituencies )
			  {
				  resultStatus=insertVotersDataInIntermediateTables(vo.getId(), publicationDateId,userId,false,false,false);
			  if(resultStatus.getResultCode() == 1)
			  {
				  result.add(new SelectOptionVO(vo.getId(),vo.getName())); 
			  }
			  }
			  mainvo.setTotalCount(new Long(constituencies.size()));
			  result.add(mainvo);
			  result.get(0).setTotalCount(new Long(constituencies.size()));
			  }
			 
			  
		  }catch (Exception e) {
			  log.error("Exception Occured in insertVoterInfoDataToIntermediateTables(), Exception is -",e);
			  
		 }
		return result;
	  }	
	  public ResultStatus deleteVotersDataInIntermediateTablesForDistrict(Long districtId, Long publicationDateId)
	  {
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  SelectOptionVO mainvo = new SelectOptionVO();
			  List<Long> constiIds = new ArrayList<Long>();
			
			  List<SelectOptionVO> constituencies = staticDataService.getConstituenciesFordistricts(districtId);
			  if(constituencies != null && constituencies.size() > 0)
				  for(SelectOptionVO vo : constituencies )
					  constiIds.add(vo.getId());
			  if(constiIds != null && constiIds.size() > 0)
			  for(Long constituencyId :constiIds )
				  resultStatus = deleteVoterInfoFromIntermediateTablesByConstituencyId(constituencyId,publicationDateId);
		  }
		  catch (Exception e) {
			  log.error("Exception Occured in deleteVotersDataInIntermediateTablesForDistrict(), Exception is -",e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 }
		return resultStatus;
	  }

	  
	  public Long getVoterCountForToPublication(Long constituencyId,Long publicationDateId)
		 {
		  
			
			 try{
				 return boothPublicationVoterDAO.getVoterCountForToPublication(constituencyId,publicationDateId);
			 }catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVoterCountForToPublication() Method, Exception - "+e);
				return 0l;
			}
		 }
	  
	  
	  
	 
	  

	  
	  public List<SelectOptionVO> getDistrictsList(Long stateId)
	  {
		  List<SelectOptionVO> returnList = null;
		  try {
			List<Object[]> districtsList = districtDAO.getDistrictIdAndNameByState(stateId);
			if(districtsList != null&& districtsList.size() > 0)
			{
				returnList = new ArrayList<SelectOptionVO>();
				fillSelectOptionVO(districtsList,returnList);
			}
		} catch (Exception e) {
			log.error("Exception Occured in getDistrictsList(), Exception is -",e);
		}
		  return returnList;
	  }
	  
	  public List<SelectOptionVO> getPublicationList()
	  {
		  List<SelectOptionVO> returnList = null;
		  try {
			List<Object[]> publicationDatesList = publicationDateDAO.getAllPublicationDates();
			if(publicationDatesList != null&& publicationDatesList.size() > 0)
			{
				returnList = new ArrayList<SelectOptionVO>();
				fillSelectOptionVO(publicationDatesList,returnList);
			}
		} catch (Exception e) {
			log.error("Exception Occured in getDistrictsList(), Exception is -",e);
		}
		  return returnList;
	  }
	  
	  public void fillSelectOptionVO(List<Object[]> list ,List<SelectOptionVO> returnList)
	  {
		  for (Object[] objects : list) {
			  SelectOptionVO selectOptionVO = new SelectOptionVO();
			  selectOptionVO.setId((Long)objects[0]);
			  selectOptionVO.setName(objects[1].toString());
			  returnList.add(selectOptionVO);
		  }
	  }

	  public SelectOptionVO getCountList1(Long publicationDateId,Long id,String type,String path)
	  {
		  SelectOptionVO result = new SelectOptionVO();
		  List<Object[]> mandalwiseDetails = null;
		  //publicationDateId = voterInfoDAO.getLatestPublicationDate(id);
		  publicationDateId =8l;
		  String constituencyType = constituencyDAO.get(id).getAreaType();
		  if(constituencyType.equalsIgnoreCase("RURAL") || constituencyType.equalsIgnoreCase("RURAL-URBAN"))
		  {
			  mandalwiseDetails= boothPublicationVoterDAO.getConstituencyDetails(publicationDateId,id,"constituency");
			  setData(result,mandalwiseDetails,"constituency");
			  
			  mandalwiseDetails= boothPublicationVoterDAO.getConstituencyDetails(publicationDateId,id,"mandal");
			  setData(result,mandalwiseDetails,"mandal");
			 
			  
			  mandalwiseDetails= boothPublicationVoterDAO.getConstituencyDetails(publicationDateId,id,"panchayat");
			  setData(result,mandalwiseDetails,"panchayat");
			  
			  mandalwiseDetails= boothPublicationVoterDAO.getConstituencyDetails(publicationDateId,id,"booth");
			  setData(result,mandalwiseDetails,"booth");
		  }
		  if(constituencyType.equalsIgnoreCase("RURAL-URBAN"))
		  {
			  mandalwiseDetails= boothPublicationVoterDAO.getConstituencyDetails(publicationDateId,id,"muncipality");
			  setData(result,mandalwiseDetails,"muncipality");
			  
			  mandalwiseDetails= boothPublicationVoterDAO.getConstituencyDetails(publicationDateId,id,"muncipalityBooth");
			  setData(result,mandalwiseDetails,"muncipalityBooth");
		  }
		  if(constituencyType.equalsIgnoreCase("URBAN"))
		  {
			  mandalwiseDetails= boothPublicationVoterDAO.getConstituencyDetails(publicationDateId,id,"constituency");
			  setData(result,mandalwiseDetails,"constituency");
			 
			  mandalwiseDetails= boothPublicationVoterDAO.getConstituencyDetails(publicationDateId,id,"muncipalityBooth");
			  setData(result,mandalwiseDetails,"muncipalityBooth");
		  }
		 
		  if(result != null)
		  {
			  Document document = null;
				try 
				{
					document = new Document();
					Object[] values = constituencyDAO.constituencyName(id).get(0);
			    	String constituenyName = values[0].toString().toUpperCase();
			    	String districtName = values[1].toString().toUpperCase();
			    	Long constituenyNo = delimitationConstituencyDAO.getConstituencyNo(id,2009l);
			    	//String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\PartyAnalyst\\";
				    String filePath = "VMR"+"/"+""+districtName+"_"+constituenyNo+"_"+constituenyName+" Voter Avg Age Report.pdf";
				    String FILE = path+filePath;
				    File file  = new File(FILE);
				    try {
						file.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    result.setUrl(filePath);
				  	try {
				  		PdfWriter.getInstance(document, new FileOutputStream(FILE));
				  	} catch (FileNotFoundException e) {
				  		e.printStackTrace();
				  	} catch (DocumentException e) {
				  		e.printStackTrace();
				  	}
				  	
				  	document.open();
					
					
					pdfReportService.voterAvgAgeReport(document,result);
					
					
				} 
				catch (Exception e)
				{
					
				}
				finally
				{
					if(document != null)
					document.close();
				}
		  }
		  return result;
	  }
	  
	  public void setData(SelectOptionVO resultVO, List<Object[]> dataList,String type){
		  List<SelectOptionVO> mandalsList = new ArrayList<SelectOptionVO>();
		  List<SelectOptionVO> panchayatsList = new ArrayList<SelectOptionVO>();
		  List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>();
		  List<SelectOptionVO> munciplaityWiseList = new ArrayList<SelectOptionVO>();
		  List<SelectOptionVO> munciplaityWiseBoothList = new ArrayList<SelectOptionVO>();
		  DecimalFormat df = new DecimalFormat("#.##");
		  for(Object[] param:dataList){
			  if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			  {
				  resultVO.setId((Long)param[1]);
				  resultVO.setTotalCount((Long)param[0]);
				  resultVO.setPerc(new Double(df.format((Double)param[3])));
				  resultVO.setName(param[2].toString());
				 // resultVO.setValidCount(((Long)param[2])*100/(Long)param[0]);
			  }
			  if(type.equalsIgnoreCase(IConstants.MANDAL))
			  {
				  SelectOptionVO votersDetailsVO2 = new SelectOptionVO();
				  votersDetailsVO2.setId((Long)param[1]);
				  votersDetailsVO2.setName(param[2].toString());
				  votersDetailsVO2.setTotalCount((Long)param[0]);
				  votersDetailsVO2.setPerc(new Double(df.format((Double)param[3])));
				  //votersDetailsVO2.setValidCount(((Long)param[3])*100/(Long)param[0]);
				  mandalsList.add(votersDetailsVO2);
			  }
			  
			  if(type.equalsIgnoreCase("muncipality"))
			  {
				  SelectOptionVO votersDetailsVO2 = new SelectOptionVO();
				  votersDetailsVO2.setId((Long)param[1]);
				  votersDetailsVO2.setName(param[2].toString() + " Muncipality");
				  votersDetailsVO2.setTotalCount((Long)param[0]);
				  votersDetailsVO2.setPerc(new Double(df.format((Double)param[3])));
				  munciplaityWiseList.add(votersDetailsVO2);
			  }
			  
			  if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			  {
				  SelectOptionVO votersDetailsVO3 = null;
				  votersDetailsVO3 = checkVOExist((Long)param[1],panchayatsList);//mandalList
				  if(votersDetailsVO3 == null){
					  votersDetailsVO3 = new SelectOptionVO();
					  votersDetailsVO3.setId((Long)param[1]);
					  votersDetailsVO3.setName(param[2].toString());
					  panchayatsList.add(votersDetailsVO3);
				  }
				  SelectOptionVO votersDetailsVO4 = null;
				  if(votersDetailsVO3.getSelectOptionsList().size() > 0)
					  votersDetailsVO4 = checkVOExist((Long)param[3],votersDetailsVO3.getSelectOptionsList());//panchayatlist
				  if(votersDetailsVO4 == null && param[2] != null){
					  votersDetailsVO4 = new SelectOptionVO();
					  votersDetailsVO4.setId((Long)param[3]);
					  votersDetailsVO4.setName(param[4].toString());
					  votersDetailsVO4.setTotalCount((Long)param[0]);
					  votersDetailsVO4.setPerc(new Double(df.format((Double)param[5])));
					  //votersDetailsVO4.setValidCount(((Long)param[5])*100/(Long)param[0]);
					  votersDetailsVO3.getSelectOptionsList().add(votersDetailsVO4);
				  }
			  }
			  
			  
			  if(type.equalsIgnoreCase(IConstants.BOOTH))
			  {
				  SelectOptionVO votersDetailsVO3 = new SelectOptionVO();
				  votersDetailsVO3 = checkVOExist((Long)param[1],boothsList);
				  if(votersDetailsVO3 == null){
					  votersDetailsVO3 = new SelectOptionVO();
					  votersDetailsVO3.setId((Long)param[1]);
					  votersDetailsVO3.setName(param[2].toString());
					  boothsList.add(votersDetailsVO3);
				  }
				  SelectOptionVO votersDetailsVO4 = null;
				  if(votersDetailsVO3.getSelectOptionsList().size() > 0)
					  votersDetailsVO4 = checkVOExist((Long)param[3],votersDetailsVO3.getSelectOptionsList());//panchayatlist
				  if(votersDetailsVO4 == null && param[2] != null){
					  votersDetailsVO4 = new SelectOptionVO();
					  votersDetailsVO4.setId((Long)param[3]);
					  votersDetailsVO4.setName(param[4].toString());
					  votersDetailsVO4.setTotalCount((Long)param[0]);
					  votersDetailsVO4.setPerc(new Double(df.format((Double)param[5])));
					  //votersDetailsVO4.setValidCount(((Long)param[5])*100/(Long)param[0]);
				  votersDetailsVO3.getSelectOptionsList().add(votersDetailsVO4);
				  }
			  }
			  if(type.equalsIgnoreCase("muncipalityBooth"))
			  {
				  SelectOptionVO votersDetailsVO3 = new SelectOptionVO();
				  votersDetailsVO3 = checkVOExist((Long)param[1],munciplaityWiseBoothList);
				  if(votersDetailsVO3 == null){
					  votersDetailsVO3 = new SelectOptionVO();
					  votersDetailsVO3.setId((Long)param[1]);
					  votersDetailsVO3.setName(param[2].toString() + " Muncipality");
					  munciplaityWiseBoothList.add(votersDetailsVO3);
				  }
				  SelectOptionVO votersDetailsVO4 = null;
				  if(votersDetailsVO3.getSelectOptionsList().size() > 0)
					  votersDetailsVO4 = checkVOExist((Long)param[3],votersDetailsVO3.getSelectOptionsList());//panchayatlist
				  if(votersDetailsVO4 == null && param[2] != null){
					  votersDetailsVO4 = new SelectOptionVO();
					  votersDetailsVO4.setId((Long)param[3]);
					  votersDetailsVO4.setName(param[4].toString());
					  votersDetailsVO4.setTotalCount((Long)param[0]);
					  votersDetailsVO4.setPerc(new Double(df.format((Double)param[5])));
					  //votersDetailsVO4.setValidCount(((Long)param[5])*100/(Long)param[0]);
				  votersDetailsVO3.getSelectOptionsList().add(votersDetailsVO4);
				  }
			  }
		  }
		  if(mandalsList.size() > 0)
		  resultVO.setSelectOptionsList(mandalsList);
		  if(panchayatsList.size() > 0)
		  resultVO.setSelectOptionsList1(panchayatsList);
		  if(boothsList.size() > 0)
		  resultVO.setSelectOptionsList2(boothsList);
		  if(munciplaityWiseList.size() > 0)
		  {
			  resultVO.getSelectOptionsList().addAll(munciplaityWiseList);
			  //resultVO.setSelectOptionsList(mandalsList);
		  }
		  if(munciplaityWiseBoothList.size() > 0)
		  {
			  resultVO.getSelectOptionsList2().addAll(munciplaityWiseBoothList);
		  }
			  
	  }
	  
	  public SelectOptionVO checkVOExist(Long locationId,List<SelectOptionVO> list)
		{
			try{
			if(list == null)
			 return null;
			for(SelectOptionVO positionVO:list)
			if(positionVO.getId() != null)
			 if(positionVO.getId().equals(locationId))
			  return positionVO;
				
			 return null;
			}catch (Exception e) {
			 e.printStackTrace();
			 log.error(" ExceptionOccured in checkPartyPositionVOExist() method, Exception - "+e);
			 return null;
			}
		}
	  
	  
	  public List<VoterHouseInfoVO> getVotersOfSearchedCriteria(String voterCardNo,String voterName,Long constituencyId,Long publicationId){
			
			StringBuilder qryString=new StringBuilder();
			
			qryString.append(" and model.booth.constituency.constituencyId ="+constituencyId+"");
			
			if(voterCardNo.trim().length()>0 ){
				qryString.append(" and model.voter.voterIDCardNo = '"+voterCardNo+"'");
			}else if(voterName.trim().length()>0){
				qryString.append(" and model.voter.name like '%"+voterName+"%'");
			}
			
			qryString.append(" order by model.voter.name ");
			
			
			List<Object[]> voterDataList=userVoterDetailsDAO.getVotersDetailsBySearchCriteriaForHouseHolds(publicationId,qryString.toString());
			List<Long> voterIds=new ArrayList<Long>();
			List<VoterHouseInfoVO> resultList = new ArrayList<VoterHouseInfoVO>();
			if(voterDataList.size()>0){
				for(Object[] vData:voterDataList){
					VoterHouseInfoVO vo = new VoterHouseInfoVO();
					Voter voter=(Voter)vData[0];
					vo.setVoterId(voter.getVoterId());
					vo.setName(voter.getName());
					vo.setBoothId(Long.valueOf(vData[1].toString()));
					vo.setPartNo(vData[2].toString());
					vo.setToSno(Long.valueOf(vData[3].toString()));
					vo.setVoterIdCardNo(voter.getVoterIDCardNo());
					resultList.add(vo);
				}
			}
			return resultList;
	  }
	  
	  public List<SelectOptionVO> getVoterNamestempConstituencies()
		{
			List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
			List<Object[]> list = new ArrayList<Object[]>();
			try{
				 list = voterNamesTempDAO.getConstituencies();
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					resultList.add(new SelectOptionVO((Long)params[0],params[1].toString()));	
				}
				
			}catch (Exception e) {
				 e.printStackTrace();
				
				}
			return resultList;
		}
	  
	  public ResultStatus insertVoterDataIntoVoterNamesTemp(Long constituencyId)
	  {
		  ResultStatus result = new ResultStatus();
		  log.info("Entered into insertVoterDataIntoVoterNamesTemp() Method");
		  try{
			  List<VoterVO> list = new ArrayList<VoterVO>(0);
			  List<String> voterIdCardNos = new ArrayList<String>();
			  Map<String,Long> voteIdMap = new HashMap<String, Long>();
			  
			  Integer maxIndex = 1000;
			  Integer startIndex = 0;
			  
			  Long voterCount =  voterNamesTempDAO.getVotersCountACNO(constituencyId);  
		  
			  if(voterCount > 0)
			  {
				  for(;;)
				  {
					try{
					if(startIndex >= Integer.valueOf(voterCount.intValue())-1)
						break;
					if(maxIndex >= Integer.valueOf(voterCount.intValue()))
						maxIndex = Integer.valueOf(voterCount.intValue()) - 1;
				  
					List<VoterNamesTemp> voterTemp = voterNamesTempDAO.getVotersByACNO(startIndex,1000,constituencyId);
					log.warn("Start index --> "+startIndex);
				  
					if(voterTemp != null && voterTemp.size() > 0)
					{
						for(VoterNamesTemp data : voterTemp) 
						{
							if(!voterIdCardNos.contains(data.getVoterIdCardNo()))
								voterIdCardNos.add(data.getVoterIdCardNo());
						 
							VoterVO vo = new VoterVO();
							vo.setFirstName(data.getFirstName());
							vo.setName(data.getLastName() != null ? data.getLastName().toString() : "");
							vo.setRelativeFirstName(data.getRelativeFirstName() != null ?data.getRelativeFirstName() : "");
							vo.setRelativeLastName(data.getRelativeLastName() != null ? data.getRelativeLastName(): "");
							vo.setVoterIDCardNo(data.getVoterIdCardNo());
							list.add(vo);
						}
					}
					
					if(voterIdCardNos != null && voterIdCardNos.size() > 0)
					{
						List<Object[]> voterIds = voterDAO.getVoterIdCardNo(voterIdCardNos);
						if(voterIds != null && voterIds.size() > 0)
						{
							for(Object[] params : voterIds)
								voteIdMap.put(params[0].toString(),(Long)params[1]);  
						}
					}
				  
					if(list != null && list.size() > 0)
					{
						for(VoterVO voter : list)
						{
							try{
								VoterNames voterNames = null;
								List<VoterNames> voterNamesList = voterNamesDAO.gerVoterNamesObjByVoterId(voteIdMap.get(voter.getVoterIDCardNo()));
								if(voterNamesList != null && voterNamesList.size() > 0 && voterNamesList.get(0) != null)
								{
									 voterNames = voterNamesList.get(0);
								}
							if(voterNames == null)
								voterNames = new VoterNames();
						  
								voterNames.setFirstName(voter.getFirstName().trim());
								voterNames.setLastName(voter.getName().trim());
								voterNames.setRelativeFirstName(voter.getRelativeFirstName().trim());
								voterNames.setRelativeLastName(voter.getRelativeLastName().trim());
								voterNames.setLanguage(languageDAO.get(3l));
								voterNames.setVoterId(voteIdMap.get(voter.getVoterIDCardNo()));
								voterNamesDAO.save(voterNames);
							}catch(Exception e)
							{
								log.error(e);
							}
						}
					}
					voterDAO.flushAndclearSession();
					startIndex = startIndex + 1000;
					maxIndex = maxIndex + 1000;
				  
					}catch (Exception e)
					{
						log.error(" Exception Occured in insertVoterDataIntoVoterNamesTemp() method, Exception - "+e);
						result.setResultCode(ResultCodeMapper.FAILURE);
						return result;
					}
				 }
			  }
			  result.setResultCode(ResultCodeMapper.SUCCESS);
			  log.info("Executed successfully and existing from insertVoterDataIntoVoterNamesTemp() Method");
			  return result;
		  }catch(Exception e)
		  {
			  log.error(e);
			  result.setResultCode(ResultCodeMapper.FAILURE);
			  return result;
		  }
		}
	  
	  public List<VoterVO> getCadrePeopleDetailsForYear(Long userId,Long locationValue,String type,String buttonName,
				Long publicationId,Integer startIndex , Integer maxRecords,String columnName,String order,Long year)
	  {
		 
			  List<Long> cadreLevelValues = new ArrayList<Long>(0);
				 List<VoterVO> voters = new ArrayList<VoterVO>();
			  List<InfluencingPeople> influencingDetails = new ArrayList<InfluencingPeople>();
			  Long Id = locationValue;;
			  String name = null;
			
			  try{
			  	if(type.equalsIgnoreCase("constituency"))
			  	{
			  	
			  		cadreLevelValues.add(locationValue);
			  	}
			  	else if(type.equalsIgnoreCase("mandal") )
			  	{
			  		if(locationValue != null && locationValue.toString().substring(0,1).trim().equalsIgnoreCase("2"))
			  		{
			  			type = "MANDAL";
			  			Id = new Long(locationValue.toString().substring(1));
			  			cadreLevelValues.add(new Long(locationValue.toString().substring(1)));
			  		}
			  		else{
			  			type="MUNCIPALITY/CORPORATION";
			  			List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationValue.toString().substring(1).trim()));
			  			Id = (Long)list.get(0);
			  			cadreLevelValues.add(new Long(list.get(0).toString()));
			  		}
			  				
			  	}
			  	else if(type.equalsIgnoreCase("ward")||type.equalsIgnoreCase("customWard"))
				{
					locationValue =new Long("1"+locationValue);
					cadreLevelValues.add(locationValue);
				}
				
				else if(type.equalsIgnoreCase("hamlet") || type.equalsIgnoreCase("panchayat")
						|| type.equalsIgnoreCase("booth"))
				{
					cadreLevelValues.add(locationValue);
								
				}
			  
			  	if(Id > 0 && !type.equalsIgnoreCase("BOOTH") && !type.equalsIgnoreCase("panchayat") && !type.equalsIgnoreCase("hamlet"))
			  	 name = (String) constituencyDAO.getNameByInfluenceScopeValue(Id,type).get(0);
			  	if(type.equalsIgnoreCase("panchayat"))
			  		name = panchayatDAO.get(Id).getPanchayatName().toString();
			  	if(type.equalsIgnoreCase("hamlet"))
			  		name = hamletDAO.get(Id).getHamletName().toString();
			  	if(buttonName.equalsIgnoreCase("Cadre"))
			  		voters =  setCadreInfo(userId,cadreLevelValues,type,startIndex,maxRecords,name,columnName,order,year);
			  
		  }
		  
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
			return voters;
	  }
	  public List<VoterVO> setCadreInfo(Long userId,List<Long> locationValues,String type,Integer startIndex,Integer maxRecords,String name,String columnName, String order,Long year)
	  {
	  	List<VoterVO> voters = new ArrayList<VoterVO>();
	  	List<Voter> votersList = new ArrayList<Voter>();;
	  	Long totalCount = 0L;
	  	List<Object[]> cadreDetails = null;
	  	Map<Long,VoterVO> cadreMap = new HashMap<Long, VoterVO>();
	  	
	  	if(columnName.equalsIgnoreCase("relativeFirstName"))
	  	{
	  		columnName = "relativename";
	  	}
	  	else if(columnName.equalsIgnoreCase("mobileNo"))
	  	{
	  		columnName = "mobileNo";
	  	}
	  	else if(columnName.equalsIgnoreCase("influencingRange"))
	  	{
	  		columnName = "influencingScope";
	  	}
	  	else if(columnName.equalsIgnoreCase("firstName"))
	  	{
	  		columnName = "firstname";
	  	}
	  	
	  	if(locationValues != null)
	  		
	  		
	  		cadreDetails =  tdpCadreDAO.getTdpCadreVoterIDs(locationValues,type,startIndex, maxRecords,columnName,order,year);
	  		Long count = new Long(startIndex);
	  		if(cadreDetails != null)
	  		{
	  			Long influencyDetailsCount = tdpCadreDAO.getTdpCadreCountInALocation(locationValues,type,year);
	  			totalCount =influencyDetailsCount;
	  			//Cadre voter Details (VoterIds not avilable) 
	  			for(Object[] params : cadreDetails)
	  				{
	  				//Cadre voter Details Count
	  				//Long influencyDetailsCount = cadreDAO.getCadreCountInALocation(userId,locationValues,type);
	  				
	  				VoterVO voterVO = new VoterVO();
	  				if(params[4] == null)//voter is null
	  				{
	  					voterVO.setVoterId((++count)+"");
	  					voterVO.setFirstName(params[0] != null ? params[0].toString() : ""+" "+params[1] != null ? params[1].toString():"");
	  					voterVO.setGender(params[2] != null ? params[2].toString() : "");
	  					voterVO.setMobileNo(params[3]!=null ? params[3].toString():" ");
	  					if(params[5]!=null)
	  					voterVO.setCast(params[5].toString());
	  					
	  						StringBuilder location=new StringBuilder();
	  						if(type.equalsIgnoreCase("constituency"))
	  								location.append(params[6]!=null?params[6].toString()+"(Mandal) ":"");
	  						if(!type.equalsIgnoreCase("booth")){
	  								if(params[7]!=null){
	  									location.append(params[7].toString()+"(Hamlet) ");
	  								}
	  							}
	  							
	  							if(params[8]!=null){
	  								location.append(" BOOTH -"+params[8].toString());
	  								
	  							}
	  							if(location.length()!=0){
	  								voterVO.setLocalArea(location.toString());
	  							}
	  							else{
	  								voterVO.setLocalArea(name);
	  							}
	  					}
	  				
	  				
	  				if(params[4] != null)
	  				{
	  					Voter voter = voterDAO.get((Long)params[4]);
	  					voterVO.setVoterIds((Long)params[4]);
	  					voterVO.setVoterId((++count)+"");
	  					voterVO.setFirstName(voter.getName());
	  					voterVO.setAge(voter.getAge());
	  					voterVO.setGender(voter.getGender());
	  					voterVO.setHouseNo("# "+voter.getHouseNo());
	  					voterVO.setRelativeFirstName(voter.getRelativeName());
	  					voterVO.setRelationshipType(voter.getRelationshipType());
	  					voterVO.setVoterIDCardNo(voter.getVoterIDCardNo());
	  					voterVO.setMobileNo(params[3]!=null?params[3].toString():"");
	  				
	  					StringBuilder location=new StringBuilder();
  						if(type.equalsIgnoreCase("constituency"))
  								location.append(params[6]!=null?params[6].toString()+"(Mandal) ":"");
  						if(!type.equalsIgnoreCase("booth")){
  								if(params[7]!=null){
  									location.append(params[7].toString()+"(Hamlet) ");
  								}
  							}
  							
  							if(params[8]!=null){
  								location.append(" BOOTH -"+params[8].toString());
  								
  							}
  							if(location.length()!=0){
  								voterVO.setLocalArea(location.toString());
  							}
  							else{
  								voterVO.setLocalArea(name);
  							}
	  					
	  							
	  					}
	  					cadreMap.put(voterVO.getVoterIds(), voterVO);
	  			        voters.add(voterVO);
	  			        System.out.println(new Date());
	  				}
	  			
	  			//Influencing people voter Details (VoterIds avilable)
	  			
	  			/*Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
	  			List<Long> voterIds = new ArrayList<Long>(cadreMap.keySet());
	  			  List<Long> voterIdsList = new ArrayList<Long>(0);
	  			
	  				List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIds, userId);
	  				if(list != null && list.size() > 0)
	  				 for(Object[] obj:list)
	  				 {
	  					 VoterVO voterVO1 = cadreMap.get((Long)obj[0]);
	  					 voterVO1.setMobileNo(obj[1] != null ? obj[1].toString() : "");
	  				 }*/
	  			
	  			
	  			/*List<Object[]> castesList = userVoterDetailsDAO.getcasteForVoter(voterIds,userId);
	  			if(castesList != null && castesList.size() > 0)
	  			{
	  				for (Object[] parms : castesList) {
	  					VoterVO voterVO1 = cadreMap.get((Long)parms[1]);
	  					voterVO1.setCast(parms[0].toString());
	  				}
	  			}*/
	  			if(voters != null)
	  				if(voters.size() > 0)
	  					voters.get(0).setTotalVoters(totalCount);
	  		}
	  		return voters;	
	  	
	  }
}