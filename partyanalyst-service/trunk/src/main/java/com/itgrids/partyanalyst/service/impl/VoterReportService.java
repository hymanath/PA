package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;

import java.math.BigInteger;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.IFlagDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMobileNumbersDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartialBoothPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.IQueryTempDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterDataInsertDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterFlagDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterPartyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dao.IVoterTagDAO;
import com.itgrids.partyanalyst.dao.IVotingTrendzDAO;
import com.itgrids.partyanalyst.dao.IVotingTrendzPartiesResultDAO;
import com.itgrids.partyanalyst.dao.IWardBoothDAO;
import com.itgrids.partyanalyst.dao.IWardDAO;
import com.itgrids.partyanalyst.dao.hibernate.CasteStateDAO;
import com.itgrids.partyanalyst.dao.hibernate.PanchayatHamletDAO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.FlagVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VoterReportVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Flag;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.PartialBoothPanchayat;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PublicationDate;
import com.itgrids.partyanalyst.model.QueryTemp;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterAgeInfo;
import com.itgrids.partyanalyst.model.VoterBasicInfo;
import com.itgrids.partyanalyst.model.VoterCastBasicInfo;
import com.itgrids.partyanalyst.model.VoterCastInfo;
import com.itgrids.partyanalyst.model.VoterDataInsert;
import com.itgrids.partyanalyst.model.VoterFamilyInfo;
import com.itgrids.partyanalyst.model.VoterFlag;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.model.VoterPartyInfo;
import com.itgrids.partyanalyst.model.VoterReportLevel;
import com.itgrids.partyanalyst.model.VoterTag;
import com.itgrids.partyanalyst.model.VotingTrendz;
import com.itgrids.partyanalyst.model.VotingTrendzPartiesResult;
import com.itgrids.partyanalyst.model.WardBooth;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;


public class VoterReportService implements IVoterReportService{
	private static final Logger LOG = Logger.getLogger(VoterReportService.class);
	private IBoothDAO boothDAO;
	private RegionServiceDataImp regionServiceDataImp;
	private IPanchayatDAO panchayatDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IVoterPartyInfoDAO voterPartyInfoDAO;
	private TransactionTemplate transactionTemplate = null;
	private IVoterReportLevelDAO voterReportLevelDAO;
	private IVotersAnalysisService votersAnalysisService;
	private IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO;
	private IVoterModificationInfoDAO voterModificationInfoDAO;
	private ICasteStateDAO casteStateDAO;
	private IConstituencyDAO constituencyDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private IVoterCastBasicInfoDAO voterCastBasicInfoDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IPartyDAO partyDAO;
	private IVoterModificationDAO voterModificationDAO;
	private IVoterDAO voterDAO;
	private IWardDAO wardDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IPublicationDateDAO publicationDateDAO;
	private IQueryTempDAO queryTempDAO;
	IUserVoterCategoryValueDAO userVoterCategoryValueDAO;
	private IHamletDAO hamletDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private ITehsilDAO tehsilDAO;
	private IUserAddressDAO userAddressDAO;
	private IVoterBasicInfoDAO voterBasicInfoDAO;
	
	private IElectionTypeDAO electionTypeDAO;
	private IVotingTrendzDAO votingTrendzDAO;
	
	private IVotingTrendzPartiesResultDAO votingTrendzPartiesResultDAO;
	private PanchayatHamletDAO panchayatHamletDAO;
	
	
	private IInfluencingPeopleDAO influencingPeopleDAO;
	private ICadreDAO cadreDAO;
	private ICandidateDAO candidateDAO;
    private IPartialBoothPanchayatDAO partialBoothPanchayatDAO;
    private IHamletBoothDAO hamletBoothDAO;
    private IVoterFamilyInfoDAO voterFamilyInfoDAO;
    private IVoterFamilyRangeDAO voterFamilyRangeDAO;
    private IVoterAgeRangeDAO voterAgeRangeDAO;
    private IVoterAgeInfoDAO voterAgeInfoDAO;
    
    private IFlagDAO flagDAO;
    private IUserDAO userDAO;
    private IVoterFlagDAO voterFlagDAO;
    private IWardBoothDAO wardBoothDAO;
    private IVoterDataInsertDAO voterDataInsertDAO;
    private IStaticDataService staticDataService;
    private IMobileService mobileService;
    private IMobileNumbersDAO mobileNumbersDAO;
    private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
    
    @Autowired
    private IVoterTagDAO voterTagDAO;
	public IMobileNumbersDAO getMobileNumbersDAO() {
		return mobileNumbersDAO;
	}

	public void setMobileNumbersDAO(IMobileNumbersDAO mobileNumbersDAO) {
		this.mobileNumbersDAO = mobileNumbersDAO;
	}

	public IMobileService getMobileService() {
		return mobileService;
	}

	public void setMobileService(IMobileService mobileService) {
		this.mobileService = mobileService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IVoterDataInsertDAO getVoterDataInsertDAO() {
		return voterDataInsertDAO;
	}

	public void setVoterDataInsertDAO(IVoterDataInsertDAO voterDataInsertDAO) {
		this.voterDataInsertDAO = voterDataInsertDAO;
	}

	public IVoterFlagDAO getVoterFlagDAO() {
		return voterFlagDAO;
	}

	public void setVoterFlagDAO(IVoterFlagDAO voterFlagDAO) {
		this.voterFlagDAO = voterFlagDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IFlagDAO getFlagDAO() {
		return flagDAO;
	}

	public void setFlagDAO(IFlagDAO flagDAO) {
		this.flagDAO = flagDAO;
	}

	public IPartialBoothPanchayatDAO getPartialBoothPanchayatDAO() {
		return partialBoothPanchayatDAO;
	}

	public void setPartialBoothPanchayatDAO(
			IPartialBoothPanchayatDAO partialBoothPanchayatDAO) {
		this.partialBoothPanchayatDAO = partialBoothPanchayatDAO;
	}	
	
	public IVoterFamilyInfoDAO getVoterFamilyInfoDAO() {
		return voterFamilyInfoDAO;
	}

	public void setVoterFamilyInfoDAO(IVoterFamilyInfoDAO voterFamilyInfoDAO) {
		this.voterFamilyInfoDAO = voterFamilyInfoDAO;
	}

	public IVoterAgeInfoDAO getVoterAgeInfoDAO() {
		return voterAgeInfoDAO;
	}

	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}

	public IInfluencingPeopleDAO getInfluencingPeopleDAO() {
		return influencingPeopleDAO;
	}

	public void setInfluencingPeopleDAO(IInfluencingPeopleDAO influencingPeopleDAO) {
		this.influencingPeopleDAO = influencingPeopleDAO;
	}

	public IVoterAgeRangeDAO getVoterAgeRangeDAO() {
		return voterAgeRangeDAO;
	}

	public void setVoterAgeRangeDAO(IVoterAgeRangeDAO voterAgeRangeDAO) {
		this.voterAgeRangeDAO = voterAgeRangeDAO;
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

	public PanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}

	public void setPanchayatHamletDAO(PanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}

	public IVotingTrendzPartiesResultDAO getVotingTrendzPartiesResultDAO() {
		return votingTrendzPartiesResultDAO;
	}

	public void setVotingTrendzPartiesResultDAO(
			IVotingTrendzPartiesResultDAO votingTrendzPartiesResultDAO) {
		this.votingTrendzPartiesResultDAO = votingTrendzPartiesResultDAO;
	}

	public IVotingTrendzDAO getVotingTrendzDAO() {
		return votingTrendzDAO;
	}

	public void setVotingTrendzDAO(IVotingTrendzDAO votingTrendzDAO) {
		this.votingTrendzDAO = votingTrendzDAO;
	}

	public IElectionTypeDAO getElectionTypeDAO() {
		return electionTypeDAO;
	}

	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}

	public IVoterBasicInfoDAO getVoterBasicInfoDAO() {
		return voterBasicInfoDAO;
	}

	public void setVoterBasicInfoDAO(IVoterBasicInfoDAO voterBasicInfoDAO) {
		this.voterBasicInfoDAO = voterBasicInfoDAO;
	}

	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public IQueryTempDAO getQueryTempDAO() {
		return queryTempDAO;
	}

	public void setQueryTempDAO(IQueryTempDAO queryTempDAO) {
		this.queryTempDAO = queryTempDAO;
	}

	public IWardDAO getWardDAO() {
		return wardDAO;
	}

	public void setWardDAO(IWardDAO wardDAO) {
		this.wardDAO = wardDAO;
	}

	public static Logger getLog() {
		return LOG;
	}

	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
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

	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public ICasteStateDAO getCasteStateDAO() {
		return casteStateDAO;
	}

	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IVoterModificationInfoDAO getVoterModificationInfoDAO() {
		return voterModificationInfoDAO;
	}

	public void setVoterModificationInfoDAO(
			IVoterModificationInfoDAO voterModificationInfoDAO) {
		this.voterModificationInfoDAO = voterModificationInfoDAO;
	}

	public IVoterModificationAgeInfoDAO getVoterModificationAgeInfoDAO() {
		return voterModificationAgeInfoDAO;
	}

	public void setVoterModificationAgeInfoDAO(
			IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO) {
		this.voterModificationAgeInfoDAO = voterModificationAgeInfoDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public IVoterPartyInfoDAO getVoterPartyInfoDAO() {
		return voterPartyInfoDAO;
	}

	public void setVoterPartyInfoDAO(IVoterPartyInfoDAO voterPartyInfoDAO) {
		this.voterPartyInfoDAO = voterPartyInfoDAO;
	}

	public IBoothDAO getBoothDAO() {
			return boothDAO;
		}

		public void setBoothDAO(IBoothDAO boothDAO) {
			this.boothDAO = boothDAO;
		}

		public RegionServiceDataImp getRegionServiceDataImp() {
			return regionServiceDataImp;
		}

		public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
			this.regionServiceDataImp = regionServiceDataImp;
		}

		public IPanchayatDAO getPanchayatDAO() {
			return panchayatDAO;
		}

		public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
			this.panchayatDAO = panchayatDAO;
		}

		public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
			return assemblyLocalElectionBodyDAO;
		}

		public void setAssemblyLocalElectionBodyDAO(
				IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
			this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
		}

		public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
			return boothPublicationVoterDAO;
		}

		public void setBoothPublicationVoterDAO(
				IBoothPublicationVoterDAO boothPublicationVoterDAO) {
			this.boothPublicationVoterDAO = boothPublicationVoterDAO;
		}

		
		public IVoterReportLevelDAO getVoterReportLevelDAO() {
			return voterReportLevelDAO;
		}

		public void setVoterReportLevelDAO(IVoterReportLevelDAO voterReportLevelDAO) {
			this.voterReportLevelDAO = voterReportLevelDAO;
		}
		public IVoterModificationDAO getVoterModificationDAO() {
				return voterModificationDAO;
			}
		
		public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
			this.voterModificationDAO = voterModificationDAO;
		}
	
	    public IPartyDAO getPartyDAO() {
			return partyDAO;
		}

		public void setPartyDAO(IPartyDAO partyDAO) {
			this.partyDAO = partyDAO;
		}

	    public IUserVoterCategoryValueDAO getUserVoterCategoryValueDAO() {
			return userVoterCategoryValueDAO;
		}

		public void setUserVoterCategoryValueDAO(
				IUserVoterCategoryValueDAO userVoterCategoryValueDAO) {
			this.userVoterCategoryValueDAO = userVoterCategoryValueDAO;
		}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
			return userVoterDetailsDAO;
		}

		public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
			this.userVoterDetailsDAO = userVoterDetailsDAO;
		}

		
	    public IPublicationDateDAO getPublicationDateDAO() {
			return publicationDateDAO;
		}

		public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
			this.publicationDateDAO = publicationDateDAO;
		}

		
	public IHamletDAO getHamletDAO() {
			return hamletDAO;
		}

		public void setHamletDAO(IHamletDAO hamletDAO) {
			this.hamletDAO = hamletDAO;
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

		public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
			return localElectionBodyDAO;
		}

		public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
			this.localElectionBodyDAO = localElectionBodyDAO;
		}

		public ITehsilDAO getTehsilDAO() {
			return tehsilDAO;
		}

		public void setTehsilDAO(ITehsilDAO tehsilDAO) {
			this.tehsilDAO = tehsilDAO;
		}


	public IHamletBoothDAO getHamletBoothDAO() {
			return hamletBoothDAO;
		}

		public void setHamletBoothDAO(IHamletBoothDAO hamletBoothDAO) {
			this.hamletBoothDAO = hamletBoothDAO;
		}

	    public IVoterFamilyRangeDAO getVoterFamilyRangeDAO() {
			return voterFamilyRangeDAO;
		}

		public void setVoterFamilyRangeDAO(IVoterFamilyRangeDAO voterFamilyRangeDAO) {
			this.voterFamilyRangeDAO = voterFamilyRangeDAO;
		}

	public IWardBoothDAO getWardBoothDAO() {
			return wardBoothDAO;
		}

		public void setWardBoothDAO(IWardBoothDAO wardBoothDAO) {
			this.wardBoothDAO = wardBoothDAO;
		}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
				IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
			this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
		}

	public VoterReportVO getVoterDetailsInaLocation(String range,Long rangeValue)
	{
		try{
			VoterReportVO voterReportVO = new VoterReportVO();
			
			
			return voterReportVO;
		}catch (Exception e) {
			LOG.error("Exception Occured in getVoterDetailsInaLocation() method with arguements Range - " +
					range+" Value - "+rangeValue);
			return null;
		}
	}
	
	public ResultStatus insertVotersPartyDataToIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId)
	{
		
		ResultStatus resultStatus = new ResultStatus();
		  try{
			  List<Long> mandalIdsList = new ArrayList<Long>(0);
			  List<SelectOptionVO> panchayatsList = new ArrayList<SelectOptionVO>(0);
			  List<Long> wardsList = new ArrayList<Long>(0);
			  List<Long> localBodiesList = new ArrayList<Long>(0);
			  Set<Long> boothIdsList = new HashSet<Long>(0);
			  List<Long> constiIds = new ArrayList<Long>();
			  List<Long> panchayatIds = new ArrayList<Long>();
			  
			  constiIds.add(reportLevelValue);
			  InsertVoterPartyInfoForALocation(IConstants.CONSTITUENCY,constiIds,publicationDateId,reportLevelValue,userId,false);
			 
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
			  Set<Long> partialPanchayatIds = new HashSet<Long>();
			  if(mandalIdsList != null && mandalIdsList.size() >0){ 
				  getPanchayatsWithPartialBooths(publicationDateId,reportLevelValue,partialPanchayatIds);
				   if(partialPanchayatIds.size() > 0){
						  list = panchayatDAO.getPanchayatIdsForMandals(mandalIdsList,partialPanchayatIds);
					  }else{
						  list = panchayatDAO.getPanchayatIdsByMandalIdsList(mandalIdsList);
					  } 
			  }
			  
			  
			 if(list != null && list.size() > 0)
			  {
				 for(Object[] params : list){
					 panchayatIds.add((Long)params[0]);
				    panchayatsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				 }
			  }
			 
			 if(mandalIdsList.size() > 0)
			  {
				  InsertVoterPartyInfoForALocation(IConstants.MANDAL,mandalIdsList, publicationDateId,reportLevelValue,userId,false);
				
			  }
			  
			  if(panchayatIds.size() > 0){
				  InsertVoterPartyInfoForALocation(IConstants.PANCHAYAT,panchayatIds, publicationDateId,reportLevelValue,userId,false);
					 
			  }
			  if(partialPanchayatIds.size() > 0){
				  InsertVoterPartyInfoForALocation(IConstants.PANCHAYAT,new ArrayList<Long>(partialPanchayatIds), publicationDateId,reportLevelValue,userId,true);
				  panchayatIds.addAll(partialPanchayatIds);
			  }
			  
			  List<Object[]> list2 = null;
			  if(panchayatIds.size() > 0)
				  list2 = boothDAO.getBoothIdsByPanchayatIdsInAPublication(panchayatIds, publicationDateId);
			  
			  if(list2 != null && list2.size() > 0)
			  {
				  for(Object[] params : list2)
					  boothIdsList.add((Long)params[0]);
			  }
			  
			   // List<Long> wardsList = new ArrayList<Long>();
			  if(localBodiesList != null && localBodiesList.size() >0){
				  
				List<Object[]> wards = boothDAO.getWardsByLocalElecBodyIds(
						localBodiesList, publicationDateId,reportLevelValue);
				
				if(wards != null && wards.size() >0){
					
					for(Object[] ward:wards)
					if(ward[0] != null){
						wardsList.add((Long)ward[0]);
					}		
				}
				  
			  }
			 if(localBodiesList.size() > 0)
			  {
				  
					  InsertVoterPartyInfoForALocation(IConstants.LOCALELECTIONBODY,localBodiesList, publicationDateId,reportLevelValue,userId,false);
				
				  List<Object[]> list3 = boothDAO.getBoothIdsInLocalBodiesForAPublication(localBodiesList,publicationDateId,reportLevelValue);
				  
				  if(list3 != null && list3.size() > 0)
				  {
					  for(Object[] params : list3)
						  boothIdsList.add((Long)params[0]); 
				  }
				  
			  }
            if(wardsList.size() > 0){
				  
            	InsertVoterPartyInfoForALocation(
						  IConstants.WARD,wardsList, publicationDateId,reportLevelValue,userId,false);
          	 
				 
			  }
              List<Long> booths = null;
              if(boothIdsList.size() > 0)
              booths = new ArrayList<Long>(boothIdsList);
			 if(booths != null && booths.size() > 0)
			  //InsertVoterPartyInfoForALocation(IConstants.BOOTH,booths, publicationDateId,reportLevelValue,userId);
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			 
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		  }
	}
		  public ResultStatus InsertVoterPartyInfoForALocation(String locationType, List<Long> locationValue, Long publicationDateId,Long constituencyId,Long userId,boolean isPartial)
		  {
			  
			  	ResultStatus resultStatus = new ResultStatus();
			  
			  try{
				 
				  List<VoterPartyInfo> partyCategoryWiseList = getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,locationType,locationValue,publicationDateId,constituencyId,isPartial);
				 if(partyCategoryWiseList != null && partyCategoryWiseList.size() > 0)
				  {
					 voterPartyInfoDAO.saveAllObjects(partyCategoryWiseList);
				  }
				
				  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
			  }catch (Exception e) {
				  
				  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				  return resultStatus;
			  }
		  }
		  
		  public ResultStatus saveVotersDataInVoterPartyInfoTable(final VoterCastInfoVO votersInfo)
		  {
			 
			  ResultStatus resultStatus = new ResultStatus();
			  try{
				  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
						protected void doInTransactionWithoutResult(TransactionStatus status) 
						{
							VoterPartyInfo voterPartyInfo = new VoterPartyInfo();
							voterPartyInfo.setVoterReportLevel(voterReportLevelDAO.get(votersInfo.getReportLevelId()));
							voterPartyInfo.setReportLevelValue(votersInfo.getReportLevelValue());
							voterPartyInfo.setUserId(votersInfo.getUserId());
							voterPartyInfo.setPartyVoters(votersInfo.getTotalVoters());
							voterPartyInfo.setPartyMaleVoters(votersInfo.getMaleVoters());
							voterPartyInfo.setPartyFemaleVoters(votersInfo.getFemaleVoters());
							voterPartyInfo.setPartyPercentage(votersInfo.getPartyPercentage());
							voterPartyInfo.setPublicationDateId(votersInfo.getPublicationDateId());
							voterPartyInfo.setConstituencyId(votersInfo.getLocationId());
							voterPartyInfo.setParty(partyDAO.get(votersInfo.getPartyId()));
							voterPartyInfoDAO.save(voterPartyInfo);
							}
						});
				  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  return resultStatus;
			  }catch (Exception e) {
				  e.printStackTrace();
				  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				  return resultStatus;
			}
		  }
		  
		 public ResultStatus deletevotermodificationFromIntermediateTables(Long constituencyId,Long publicationId)
		  {
			  ResultStatus resultStatus = new ResultStatus();
				try{
					
					if(constituencyId != null && constituencyId > 0)
					{
						List<Long> voterModificationInfoIds = voterModificationInfoDAO.getVoterModificationInfoIds(constituencyId, publicationId);
						if(voterModificationInfoIds != null && voterModificationInfoIds.size() > 0)
						voterModificationAgeInfoDAO.deleteVoterModicationAgeInfoById(voterModificationInfoIds);
						voterModificationInfoDAO.deletevotermodificationInfoByConstituencyId(constituencyId,publicationId);
						
						
					}
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					return resultStatus;
				}catch (Exception e) {
					e.printStackTrace();
					
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					return resultStatus;
				}  
		  }
		 
		 
		 public ResultStatus insertVotersCasteDataInIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId,boolean hamletChecked,boolean boothChecked,boolean hamletBoothChecked,boolean localityChecked,boolean wardChecked)
			{
				
				  ResultStatus resultStatus = new ResultStatus();
				  try{
					  Map<Long,Long> mandalIdsList = new HashMap<Long,Long>(0);
					  Map<Long,Long> wardsList = new HashMap<Long,Long>(0);
					  Map<Long,Long> panchayatIdsList = new HashMap<Long,Long>(0);//contains<panchayatId,totalvoters for mandal of corresponding panchayat> used to calculate perc of panchayat voters share in this mandal
					  Map<Long,Long> localBodiesList = new HashMap<Long,Long>(0);
					  Set<Long> boothIdsList = new HashSet<Long>(0);
					  Map<Long,Long> constituencyIds = new HashMap<Long,Long>();
					  List<Long> panchayatIds = new ArrayList<Long>();
					  List<Long> partialBoothPanchayatIds = new ArrayList<Long>();
					  List<Object[]> list2 = null;
					  List<Object[]> list3 = null;
					  Map<Long,Long> hamletIds =new HashMap<Long, Long>(0);
					  List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>(0);
					  constituencyIds.put(reportLevelValue,1l);
					  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.CONSTITUENCY,constituencyIds,publicationDateId,reportLevelValue,false);
					  //InsertVoterCasteBasicInfoForALocation(IConstants.CONSTITUENCY,reportLevelValue,publicationDateId,reportLevelValue,userId);
					  List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(reportLevelValue,IConstants.PRESENT_YEAR, null);
					  Map<Long,Long> boothIds = new HashMap<Long, Long>(0);
					  Map<Long,Long> localbodyboothIds = new HashMap<Long, Long>(0);
					  if(mandalsList == null || mandalsList.size() == 0)
						  return null;
					  Long totalVoters = voterCastInfoDAO.getVotersCastCount(votersAnalysisService.getReportLevelId(IConstants.CONSTITUENCY), reportLevelValue, reportLevelValue, publicationDateId, userId);
					  for(SelectOptionVO selectOptionVO : mandalsList)
					  {
						  if(selectOptionVO.getId().toString().substring(0,1).equalsIgnoreCase(IConstants.RURAL_TYPE))
							  mandalIdsList.put(new Long(selectOptionVO.getId().toString().substring(1)),totalVoters);
						  else
							  localBodiesList.put((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(selectOptionVO.getId().toString().substring(1))).get(0),totalVoters);
					  }
					  List<Object[]> list = null;
					  Set<Long> partialPanchayatIds = new HashSet<Long>();
					  if(mandalIdsList != null && mandalIdsList.size() >0){ 
						  List<Long> ids = new ArrayList<Long>();
						  ids.addAll(mandalIdsList.keySet());
						  getPanchayatsWithPartialBooths(publicationDateId,reportLevelValue,partialPanchayatIds);
						  if(partialPanchayatIds.size() > 0){
							  list = panchayatDAO.getPanchayatIdsForMandals(ids,partialPanchayatIds);
						  }else{
							  list = panchayatDAO.getPanchayatIdsByMandalIdsList(ids);
						  }  
					  }
					 
					  if(mandalIdsList != null && mandalIdsList.size() >0)
					  {
						// InsertVoterCasteInfoForALocation(IConstants.MANDAL,mandalId,reportLevelValue, publicationDateId,reportLevelValue,userId);
						 //InsertVoterCasteBasicInfoForALocation(IConstants.MANDAL,mandalId,publicationDateId,reportLevelValue,userId);
						  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.MANDAL,mandalIdsList,publicationDateId,reportLevelValue,false);
					  }
					 if(list != null && list.size() > 0)
					  {
						  
						 Map<Long,Long> mandalTotalVotersMap = new HashMap<Long,Long>();
						 for(Object[] params : list){
							 Long total = mandalTotalVotersMap.get((Long)params[1]);
						    if(total == null){
						    	total = voterCastInfoDAO.getVotersCastCount(votersAnalysisService.getReportLevelId(IConstants.MANDAL), (Long)params[1], reportLevelValue, publicationDateId, userId);
						    	if(total == null)
						    		total = 0l;
						    	mandalTotalVotersMap.put((Long)params[1],total);
						    }
						   panchayatIdsList.put((Long)params[0],total);
						   panchayatIds.add((Long)params[0]);
						 }
						
					  }
					 
					  
					  
					  if(panchayatIdsList != null && panchayatIdsList.size() > 0)
					     saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.PANCHAYAT,panchayatIdsList,publicationDateId,reportLevelValue,false);
					
					  
					  
					  if(panchayatIds != null && panchayatIds.size() > 0)
					  {
						  if(partialPanchayatIds.size() > 0){
							  list = panchayatDAO.getMandalAndPanchayatIds(partialPanchayatIds);
							  Map<Long,Long> mandalTotalVotersMap = new HashMap<Long,Long>();
							  Map<Long,Long> partialPanchayatIdsList = new HashMap<Long,Long>(0);
								 for(Object[] params : list){
									 Long total = mandalTotalVotersMap.get((Long)params[1]);
								    if(total == null){
								    	total = voterCastInfoDAO.getVotersCastCount(votersAnalysisService.getReportLevelId(IConstants.MANDAL), (Long)params[1], reportLevelValue, publicationDateId, userId);
								    	if(total == null)
								    		total = 0l;
								    	mandalTotalVotersMap.put((Long)params[1],total);
								    }
								    partialPanchayatIdsList.put((Long)params[0],total);
								 }
							  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.PANCHAYAT,partialPanchayatIdsList,publicationDateId,reportLevelValue,true);
							  panchayatIds.addAll(partialPanchayatIds);
						  }
					  list3 =panchayatHamletDAO.getHamletsOfPanchayats(panchayatIds); 
					  list2 = boothDAO.getBoothIdsByPanchayatIdsInAPublication(panchayatIds, publicationDateId);
					  }
					  
					  // booths 
					  if(boothChecked)
					  {
					if(list2 != null && list2.size() > 0)
					{
						Map<Long,Long> boothTotalVotersMap = new HashMap<Long, Long>();
						for(Object[] params1 : list2)
						{
							Long total = boothTotalVotersMap.get((Long)params1[1]);
							if(total == null)
							{
								total = voterCastInfoDAO.getVotersCastCount(votersAnalysisService.getReportLevelId(IConstants.PANCHAYAT), (Long)params1[1], reportLevelValue, publicationDateId, userId);
					    	if(total == null)
					    		total = 0l;	
					    	boothTotalVotersMap.put((Long)params1[1], total);
							}
					    	boothIds.put((Long)params1[0], total);
						}
						
					}
					
					
					  if(boothIds != null && boothIds.size() > 0)
					  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.BOOTH,boothIds,publicationDateId,reportLevelValue,false);
					  }
					   // hamlets 
					  if(hamletChecked)
					  {
					  if(list3 != null && list3.size() > 0)
					  {
						  Map<Long,Long> hamletTotalVotersMap = new HashMap<Long, Long>(); 
						  for(Object[] params2 : list3)
							{
								Long total = hamletTotalVotersMap.get((Long)params2[1]);
								if(total == null)
								{
									total = voterCastInfoDAO.getVotersCastCount(votersAnalysisService.getReportLevelId(IConstants.PANCHAYAT), (Long)params2[1], reportLevelValue, publicationDateId, userId);
						    	if(total == null)
						    		total = 0l;	
						    	hamletTotalVotersMap.put((Long)params2[1], total);
								}
						    	hamletIds.put((Long)params2[0], total);
							}
						  
					  }
					  if(hamletIds != null && hamletIds.size() > 0)
						  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.HAMLET,hamletIds,publicationDateId,reportLevelValue,false);
					  }
					  if(localBodiesList != null && localBodiesList.size() >0){
						  List<Long> ids = new ArrayList<Long>();
						  ids.addAll(localBodiesList.keySet());
						List<Object[]> wards = boothDAO.getWardsByLocalElecBodyIds(
								ids, publicationDateId,reportLevelValue);
						
						if(wards != null && wards.size() >0){
							Map<Long,Long> lclBdyTotalVotersMap = new HashMap<Long,Long>();
							for(Object[] ward:wards)	
							if(ward[0] != null){
								Long total = lclBdyTotalVotersMap.get((Long)ward[1]);
							    if(total == null){
							    	//total = voterInfoDAO.getVotersCountInALocation(votersAnalysisService.getReportLevelId(IConstants.LOCALELECTIONBODY),(Long)ward[1],publicationDateId,reportLevelValue);
							    	total = voterCastInfoDAO.getVotersCastCount(votersAnalysisService.getReportLevelId(IConstants.LOCALELECTIONBODY), (Long)ward[1], reportLevelValue, publicationDateId, userId);
							    	if(total == null)
							    		total = 0l;
							    	lclBdyTotalVotersMap.put((Long)ward[1],total);
							    }	
								wardsList.put((Long)ward[0],total);
							}		
						}
						  
					  }
					  List<Object[]> list4 = null;
					 if(localBodiesList.size() > 0)
					  {
						  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.LOCALELECTIONBODY,localBodiesList,publicationDateId,reportLevelValue,false);
						  List<Long> localbodyids = new ArrayList<Long>();
						  localbodyids.addAll(localBodiesList.keySet());
						  list4 = boothDAO.getBoothIdsInLocalBodiesForAPublication(localbodyids,publicationDateId,reportLevelValue);
						   if(list4 != null && list4.size() > 0)
						  {
							  for(Object[] params : list4)
								  boothsList.add(new SelectOptionVO((Long)params[0],params[1].toString())); 
						  }
						   
						  }
					 
					 // localbody booths
					 if(boothChecked)
					 {
					 if(boothsList != null && boothsList.size() > 0)
					 {
						 Map<Long,Long> localBodyboothTotalVotersMap = new HashMap<Long, Long>();
							for(Object[] params4 : list4)
							{
								Long total = localBodyboothTotalVotersMap.get((Long)params4[1]);
								if(total == null)
								{
									total = voterCastInfoDAO.getVotersCastCount(votersAnalysisService.getReportLevelId("localElectionBody"), (Long)params4[1], reportLevelValue, publicationDateId, userId);
						    	if(total == null)
						    		total = 0l;	
						    	localBodyboothTotalVotersMap.put((Long)params4[1], total);
								}
								localbodyboothIds.put((Long)params4[0], total);
							} 
					 }
					 if(localbodyboothIds != null && localbodyboothIds.size() > 0)
						  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.BOOTH,localbodyboothIds,publicationDateId,reportLevelValue,false);
					 
					 }
		              if(wardsList != null && wardsList.size() > 0){
						  
		            	  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.WARD,wardsList,publicationDateId,reportLevelValue,false);
		            	  //InsertVoterCasteBasicInfoForALocation(IConstants.WARD,new Long(selectOptionVO.getId()),publicationDateId,reportLevelValue,userId);
						 
					  }
		             
					   if(boothIdsList != null && boothIdsList.size() > 0){
						  //InsertVoterCasteInfoForALocation(IConstants.BOOTH,selectOptionVO.getId(),new Long(selectOptionVO.getName()), publicationDateId,reportLevelValue,userId);
						  //InsertVoterCasteBasicInfoForALocation(IConstants.BOOTH,selectOptionVO.getId(),publicationDateId,reportLevelValue,userId);
						   //saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.BOOTH,new ArrayList<Long>(boothIdsList),publicationDateId,reportLevelValue);
					   }  
					   
					   if(localityChecked)
						votersAnalysisService.calculateAndInsertVoterCasteInfoForLocality(reportLevelValue, publicationDateId, userId, voterReportLevelDAO.getReportLevelIdByType(IConstants.LOCALITY));
					   
					   if(hamletBoothChecked)
					   {
						   List<Long> boothIdList = boothDAO.getBoothIdsByConstituencyIdAndPublicationId(reportLevelValue, publicationDateId);
							 if(boothIdList != null && boothIdList.size() > 0)
							  hamletBoothDAO.deleteHamletBoothsByBoothIdsList(boothIdList);
							 
						 votersAnalysisService.insertHamletIdAndBoothIdInHamletBoothTable(reportLevelValue,publicationDateId,userId);
						 votersAnalysisService.calculateAndInsertVoterCasteInfoForHamletBooth(reportLevelValue, publicationDateId, userId, voterReportLevelDAO.getReportLevelIdByType(IConstants.Hamlet_Booth));
					   }
					  if(wardChecked && localBodiesList.size() >0){
						  List<Long> localElecIds = new ArrayList<Long>(); 
						  List<Long> ghmcLocalElecIds = new ArrayList<Long>(); 
						  Set<Long> localElecBodyIds = localBodiesList.keySet();
						  List<Object[]> electionTypesList = localElectionBodyDAO.getLocalElectionBodyType(localElecBodyIds);
						  for(Object[] electionType:electionTypesList){ 
						    if(((Long)electionType[0]).longValue() != 7){
						 	  localElecIds.add((Long)electionType[1]);
						    }else{
						    	ghmcLocalElecIds.add((Long)electionType[1]);
						    }
						  }
						  if(ghmcLocalElecIds.size() > 0){
							  saveWardBoothData(ghmcLocalElecIds,reportLevelValue,publicationDateId,"ward");
						  }
						  if(localElecIds.size() > 0){
							  saveWardBoothData(localElecIds,reportLevelValue,publicationDateId,"cutomward");
							  insertCasteDataForCustomWards(localElecIds,userId,publicationDateId,reportLevelValue,6l,"ward");
							  insertCasteDataForCustomWards(localElecIds,userId,publicationDateId,reportLevelValue,10l,"wardbooth");
						  }
					  }
					  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					  return resultStatus;
				  }catch (Exception e) {
					  
					  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					  return resultStatus;
				  }
			}
		   
		  /* public ResultStatus InsertVoterCasteInfoForALocation(String locationType, Long locationValue, Long parentLocationId, Long publicationDateId,Long constituencyId,Long userId)
			  {
				
				  ResultStatus resultStatus = new ResultStatus();
				  
				  try{
					  List<VoterCastInfoVO> castCategoryWiseList = null;
					 
					  castCategoryWiseList = votersAnalysisService.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationValue,publicationDateId,constituencyId);
					 if(castCategoryWiseList != null && castCategoryWiseList.size() > 0)
					  {
						  VoterCastInfoVO votersInfo = new VoterCastInfoVO();
						  for(VoterCastInfoVO voters : castCategoryWiseList){
							
						  votersInfo.setReportLevelId(votersAnalysisService.getReportLevelId(locationType));
						  votersInfo.setReportLevelValue(locationValue);
						  votersInfo.setPublicationDateId(publicationDateId);
					      votersInfo.setFemaleVoters(voters.getFemaleVoters());
						  votersInfo.setMaleVoters(voters.getMaleVoters());
						  votersInfo.setLocationId(constituencyId);
						  votersInfo.setUserId(userId);
						  votersInfo.setCasteStateId(voters.getCasteStateId());
						  votersInfo.setTotalVoters(voters.getTotalVoters());
						  votersInfo.setCastePercentage(Double.parseDouble(voters.getVotesPercent()));
						 
						  saveVotersDataInVoterCasteInfoTable(votersInfo);
					  }
					}
					  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  return resultStatus;
				  }catch (Exception e) {
					 
					  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					  return resultStatus;
				  }
			  }*/
			 
			public ResultStatus saveVotersDataInVoterCasteInfoTable(final VoterCastInfoVO votersInfo)
			{
				
				  ResultStatus resultStatus = new ResultStatus();
				  try{
					  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
							protected void doInTransactionWithoutResult(TransactionStatus status) 
							{
								
								VoterCastInfo votercastInfo = new VoterCastInfo();
								votercastInfo.setVoterReportLevel(voterReportLevelDAO.get(votersInfo.getReportLevelId()));
								votercastInfo.setReportLevelValue(votersInfo.getReportLevelValue());
								votercastInfo.setUserId(votersInfo.getUserId());
								votercastInfo.setCasteState(casteStateDAO.get(votersInfo.getCasteStateId()));
								votercastInfo.setCasteVoters(votersInfo.getTotalVoters());
								votercastInfo.setCasteMaleVoters(votersInfo.getMaleVoters());
								votercastInfo.setCasteFemaleVoters(votersInfo.getFemaleVoters());
								votercastInfo.setCastePercentage(votersInfo.getCastePercentage());
								votercastInfo.setPublicationDateId(votersInfo.getPublicationDateId());
								votercastInfo.setConstituency(constituencyDAO.get(votersInfo.getLocationId()));
								voterCastInfoDAO.save(votercastInfo);
							}
					  });
					 
					  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					  return resultStatus;
				  }catch (Exception e) {
					  e.printStackTrace();
					  
					  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					  return resultStatus;
				}
			  }
			/*public ResultStatus InsertVoterCasteBasicInfoForALocation(String locationType, Long locationValue,Long publicationDateId,Long constituencyId,Long userId)
			  {
				//log.info(" Entered into calculateAndInsertVoterInfoForALocation() Method, with Values - Location Type - "+locationType+" - Location Value - "+locationValue+", Parent Location Id - "+parentLocationId+" and Publicarion Date Id - "+publicationDateId);
				  ResultStatus resultStatus = new ResultStatus();
				  
				  try{
					  List<VoterCastInfoVO> castCategoryWiseList = null;
					  Long totalVoters = 0L;
						
						 totalVoters = voterInfoDAO.getVotersCountInALocation(votersAnalysisService.getReportLevelId(locationType),locationValue,publicationDateId,constituencyId);
						
						if(locationType.equalsIgnoreCase("hamlet")){
							
							List<Long> hamlets = new ArrayList<Long>();
							hamlets.add(locationValue);
						List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletIds(userId , hamlets);
							
					    List<Long> countList = 	boothPublicationVoterDAO.getTotalVotersCountForHamletByVoterIds(voterIds,publicationDateId);
					   if(countList != null && countList.size() >0)
						 totalVoters = countList.get(0);
						}
					  castCategoryWiseList = votersAnalysisService.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationValue,publicationDateId,constituencyId);
					  List<SelectOptionVO> selectoptionList = votersAnalysisService.getCastCategoryWiseVotersCountByPublicationIdInALocation(userId,locationType,locationValue,publicationDateId,constituencyId);
					 
					  Long votesConsidered = 0l;
					  
					 if(castCategoryWiseList != null && castCategoryWiseList.size() > 0)
					  {
						  VoterCastInfoVO votersInfo = new VoterCastInfoVO();
						  for(VoterCastInfoVO voters : castCategoryWiseList)
							  votesConsidered = votesConsidered + voters.getTotalVoters();
						  for(VoterCastInfoVO voters : castCategoryWiseList){
						  votersInfo.setReportLevelId(votersAnalysisService.getReportLevelId(locationType));
						  votersInfo.setReportLevelValue(locationValue);
						  votersInfo.setPublicationDateId(publicationDateId);
						  votersInfo.setTotalCasts(castCategoryWiseList.size());
						  votersInfo.setCasteAssignedVoters(votesConsidered);
						  if(totalVoters != null)
						  votersInfo.setCasteNotAssignedVoters(totalVoters - votesConsidered);
						  votersInfo.setLocationId(constituencyId);
						  votersInfo.setUserId(userId);
						  
						  votersInfo.setFemaleVoters(voters.getFemaleVoters());
						  votersInfo.setMaleVoters(voters.getMaleVoters());
						  votersInfo.setCasteStateId(voters.getCasteStateId());
						  votersInfo.setTotalVoters(voters.getTotalVoters());
						  votersInfo.setCastePercentage(Double.parseDouble(voters.getVotesPercent()));
						  
						  
						  saveVotersDataInVoterCasteInfoTable(votersInfo);
					  }
						  saveVotersDataInVoterCasteBasicInfoTable(votersInfo,selectoptionList);
					}
					  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  return resultStatus;
				  }catch (Exception e) {
					  
					  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					  return resultStatus;
				  }
			  }*/
				  
				  
				  
				  public ResultStatus saveVotersDataInVoterCasteBasicInfoTable(final VoterCastInfoVO votersInfo,final List<SelectOptionVO> list)
					{
						 
						  ResultStatus resultStatus = new ResultStatus();
						  try{
							  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
									protected void doInTransactionWithoutResult(TransactionStatus status) 
									{
										
										VoterCastBasicInfo voterCastBasicInfo = new VoterCastBasicInfo();
										voterCastBasicInfo.setReportLevelValue(votersInfo.getReportLevelValue());
										voterCastBasicInfo.setVoterReportLevel(voterReportLevelDAO.get(votersInfo.getReportLevelId()));
										voterCastBasicInfo.setUserId(votersInfo.getUserId());
										voterCastBasicInfo.setTotalCastes(new Long(votersInfo.getTotalCasts()));
										voterCastBasicInfo.setCasteAssignedVoters(votersInfo.getCasteAssignedVoters());
										voterCastBasicInfo.setCasteNotAssignedVoters(votersInfo.getCasteNotAssignedVoters());
										voterCastBasicInfo.setPublicationDateId(votersInfo.getPublicationDateId());
										voterCastBasicInfo.setConstituency(constituencyDAO.get(votersInfo.getLocationId()));
										for(SelectOptionVO params : list)
										{
											if(params.getName().equalsIgnoreCase("OC"))
												voterCastBasicInfo.setOcVoters(params.getId());
											if(params.getName().equalsIgnoreCase("BC"))
												voterCastBasicInfo.setBcVoters(params.getId());
											if(params.getName().equalsIgnoreCase("SC"))
												voterCastBasicInfo.setScVoters(params.getId());
											if(params.getName().equalsIgnoreCase("ST"))
											voterCastBasicInfo.setStVoters(params.getId());
											
										}
										voterCastBasicInfoDAO.save(voterCastBasicInfo);
									}
							  });
							 
							  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
							  return resultStatus;
						  }catch (Exception e) {
							  e.printStackTrace();
							 
							  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
							  return resultStatus;
						}
					  }
				  
				  public ResultStatus deleteVoterModifiedData(Long constituencyId,Long publicationDateId)
				  {
					LOG.info(" Entered into deleteVoterModifiedData() method"); 
					ResultStatus resultStatus = new ResultStatus();
					try{
						if(constituencyId != null && constituencyId > 0)
						{
						voterModificationDAO.deleteVoterModifiedDataByCOnstituencyId(constituencyId, publicationDateId);
						resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						}
						
					}
					catch(Exception e)
					{
					LOG.error("Exception Occured in deleteVoterModifiedData()", e);	
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					}
					return resultStatus;
				  }
				//get voters count by cast category like OC BC SC ST from intermediate table by location level(constituency,mandal,booth etc) 
					public void getCastCategoryWiseVotersCountByPublicationIdInALocationFromIntermediateTable(Long userId,Long levelId,Long levelValue,Long publicationDateId,Long constituencyId,VoterCastInfoVO voterCastInfoVO)
					{
						final List<SelectOptionVO> castCategoryWiseList = new ArrayList<SelectOptionVO>(0);
						try{
							final List<VoterCastBasicInfo>  voterCastBasicInfoList =  voterCastBasicInfoDAO.getVotersCastBasicInfo(levelId,levelValue,constituencyId,publicationDateId,userId);
							if(voterCastBasicInfoList != null && !voterCastBasicInfoList.isEmpty() && voterCastBasicInfoList.get(0) != null){
								voterCastInfoVO.setDataPresent(true);
								final VoterCastBasicInfo voterCastBasicInfo = voterCastBasicInfoList.get(0);
									castCategoryWiseList.add(new SelectOptionVO(voterCastBasicInfo.getBcVoters(),"BC"));
									castCategoryWiseList.add(new SelectOptionVO(voterCastBasicInfo.getOcVoters(),"OC"));
									castCategoryWiseList.add(new SelectOptionVO(voterCastBasicInfo.getScVoters(),"SC"));
									castCategoryWiseList.add(new SelectOptionVO(voterCastBasicInfo.getStVoters(),"ST "));
							}
							voterCastInfoVO.setCastCategoryWiseVotersList(castCategoryWiseList);
							
						}catch (Exception e) {
							LOG.error("Exception Occured in getCastCategoryWiseVotersCountByPublicationIdInALocation() Method, Exception is - ",e);
							
						}
					}
					
					// getting cast wise voters information for a user
					public void  getVotersCastWiseDetailsInALocationFromIntermediateTable(Long userId,Long reportLvlId,Long locationId,Long publicationDateId,Long constituencyId,VoterCastInfoVO voterCastInfoVO){
						getCastCategoryWiseVotersCountByPublicationIdInALocationFromIntermediateTable(userId,reportLvlId,locationId,publicationDateId,constituencyId,voterCastInfoVO);
						//if(voterCastInfoVO.isDataPresent()){
						  voterCastInfoVO.setVoterCastInfoVOList(getCastNGenderWiseVotersCountByPublIdInALocFromIntermedTable(userId,reportLvlId,locationId,publicationDateId,constituencyId));
						/*}
						if(!voterCastInfoVO.isDataPresent()){
							final Long count = voterCastInfoDAO.getRecordsCountToCheckDataPresent(constituencyId);
							if(count != null && count.longValue() > 0l){
								voterCastInfoVO.setDataPresent(true);
							}
						}*/
					}
					
					//getting male and female voters count by cast wise from intermediate table by location level(constituency,mandal,booth etc) 
					public List<VoterCastInfoVO> getCastNGenderWiseVotersCountByPublIdInALocFromIntermedTable(Long userId,Long reportLvlId,Long levelValue,Long publicationDateId,Long constituencyId)
					{
						final List<VoterCastInfoVO> resultList = new ArrayList<VoterCastInfoVO>(0);
						try{
							final List<VoterCastInfo> list = voterCastInfoDAO.getVotersCastInfo(reportLvlId, levelValue, constituencyId, publicationDateId, userId);
							
							VoterCastInfoVO voterCastInfoVO = null;
							for(VoterCastInfo voterCastInfo:list)
							{
								voterCastInfoVO = new VoterCastInfoVO();
								voterCastInfoVO.setCastName(voterCastInfo.getCasteState() != null ? voterCastInfo.getCasteState().getCaste().getCasteName() : "");
								voterCastInfoVO.setMaleVoters(voterCastInfo.getCasteMaleVoters());
								voterCastInfoVO.setFemaleVoters(voterCastInfo.getCasteFemaleVoters());
								voterCastInfoVO.setTotalVoters(voterCastInfo.getCasteVoters());
								voterCastInfoVO.setCasteStateId(voterCastInfo.getCasteState().getCasteStateId());
								voterCastInfoVO.setVotesPercent(voterCastInfo.getCastePercentage().toString());
								voterCastInfoVO.setCasteCategoryName(voterCastInfo.getCasteState().getCasteCategoryGroup().getCasteCategory().getCategoryName());
								resultList.add(voterCastInfoVO);
							}
							
						}catch (Exception e) {
							LOG.error("Exception Occured in getCastNGenderWiseVotersCountByPublIdInALocFromIntermedTable() Method, Exception is - ",e);
						}
						return resultList;
					}
					
					//getting male and female voters count by party wise from intermediate table by location level(constituency,mandal,booth etc) 
					public void getPartyNGenderWiseVotersCountByPublIdInALocFromIntermedTable(Long userId,Long reportLvlId,Long levelValue,Long publicationDateId,Long constituencyId,VoterCastInfoVO mainVO)
					{
						final List<VoterCastInfoVO> resultList = new ArrayList<VoterCastInfoVO>(0);
						try{
							final List<VoterPartyInfo> list = voterPartyInfoDAO.getVotersPartyInfo(reportLvlId, levelValue, constituencyId, publicationDateId, userId);
							
							if(list != null && !list.isEmpty() && list.get(0) != null){
								/*mainVO.setDataPresent(true);
							}else{
								final Long count = voterPartyInfoDAO.getRecordsCountToCheckDataPresent(constituencyId);
								if(count != null && count.longValue() > 0l){
									mainVO.setDataPresent(true);
								}
				                return;	
							}*/
								VoterCastInfoVO voterCastInfoVO = null;
								
								for(VoterPartyInfo voterPartyInfo:list)
								{
									voterCastInfoVO = new VoterCastInfoVO();
									voterCastInfoVO.setPartyName(voterPartyInfo.getParty().getShortName());
									voterCastInfoVO.setMaleVoters(voterPartyInfo.getPartyMaleVoters());
									voterCastInfoVO.setFemaleVoters(voterPartyInfo.getPartyFemaleVoters());
									voterCastInfoVO.setTotalVoters(voterPartyInfo.getPartyVoters());
									voterCastInfoVO.setPartyId(voterPartyInfo.getParty().getPartyId());
									voterCastInfoVO.setVotesPercent(voterPartyInfo.getPartyPercentage().toString());
									resultList.add(voterCastInfoVO);
								}
							}
							mainVO.setPartyWisevoterCastInfoVOList(resultList);
							
						}catch (Exception e) {
							LOG.error("Exception Occured in getPartyNGenderWiseVotersCountByPublIdInALocFromIntermedTable() Method, Exception is - ",e);
							
						}
					}
					
					public List<VoterCastInfoVO> getVotersCastInfoForMultipleMandal(List<SelectOptionVO> mandalsList,Long publicationDateId,Long userId,Long constituencyId){
						final Map<Long,String> mandalIds = new HashMap<Long,String>();
						final Map<Long,String> localBodyIds = new HashMap<Long,String>();
						final List<VoterCastInfoVO> mandalCasts = new ArrayList<VoterCastInfoVO>();
						for (SelectOptionVO mandal : mandalsList){
							if(mandal.getId().toString().substring(0,1).equalsIgnoreCase("2")){
								
							  mandalIds.put(Long.valueOf(mandal.getId().toString().trim().substring(1)),mandal.getName());
							
							}else{
							
								 List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(Long.valueOf(mandal.getId().toString().substring(1)));
								 localBodyIds.put(Long.valueOf(list.get(0).toString()),mandal.getName());
							}
							
						}
						
						if(!mandalIds.isEmpty()){
							Long reportLvlId = votersAnalysisService.getReportLevelId(IConstants.MANDAL);
							final List<VoterCastInfo> mandalWiseInfo =  voterCastInfoDAO.getVotersCastInfoByMultipleLevelValues(reportLvlId,mandalIds.keySet(),constituencyId,publicationDateId,userId);
							 if(mandalWiseInfo != null && !mandalWiseInfo.isEmpty()){
								 mandalCasts.addAll(populateCastDetailsToVo(mandalWiseInfo ,mandalIds,reportLvlId,publicationDateId,constituencyId));
							 }
						}
						if(!localBodyIds.isEmpty()){
							Long reportLvlId = votersAnalysisService.getReportLevelId("localElectionBody");
							final List<VoterCastInfo> lclBodyWiseInfo =  voterCastInfoDAO.getVotersCastInfoByMultipleLevelValues(reportLvlId,localBodyIds.keySet(),constituencyId,publicationDateId,userId);
							if(lclBodyWiseInfo != null && !lclBodyWiseInfo.isEmpty()){
								mandalCasts.addAll(populateCastDetailsToVo(lclBodyWiseInfo,localBodyIds,reportLvlId,publicationDateId,constituencyId));
						    }
						}
						return mandalCasts;
					}
					
					public List<VoterCastInfoVO> populateCastDetailsToVo(List<VoterCastInfo> castInfo ,Map<Long,String> mandalIds,Long reportLvlId,Long publicationDateId,Long constituencyId){
				        final Map<Long,VoterCastInfoVO> mandalMap = new HashMap<Long,VoterCastInfoVO>();
				        VoterCastInfoVO voterCastInfoVO = null;
				        VoterCastInfoVO voterCastInfoVO1 = null;
				        List<CastVO> castVOs = null;
				        CastVO castVO = null;
						for(VoterCastInfo voterCastInfo:castInfo){
							voterCastInfoVO = mandalMap.get(voterCastInfo.getReportLevelValue());
							
							if(voterCastInfoVO == null){
								voterCastInfoVO = new VoterCastInfoVO();
								voterCastInfoVO1 =  new VoterCastInfoVO();
								castVOs = new ArrayList<CastVO>();
								voterCastInfoVO1.setCastVOs(castVOs);
								voterCastInfoVO1.setTotalVoters(voterInfoDAO.getVotersCountInALocation(reportLvlId,voterCastInfo.getReportLevelValue(),publicationDateId,constituencyId));
								voterCastInfoVO.setVoterCastInfoVO(voterCastInfoVO1);
								voterCastInfoVO.setMandalName(mandalIds.get(voterCastInfo.getReportLevelValue()));
								voterCastInfoVO.setLocationId(voterCastInfo.getReportLevelValue());
								mandalMap.put(voterCastInfo.getReportLevelValue(), voterCastInfoVO);
							}else{
								voterCastInfoVO1 = voterCastInfoVO.getVoterCastInfoVO();
								castVOs = voterCastInfoVO1.getCastVOs();
							}
							castVO = new CastVO();
							//castvo.setCasteCategoryName("");
							castVO.setCastName(voterCastInfo.getCasteState().getCaste().getCasteName());
							castVO.setCastStateId(voterCastInfo.getCasteState().getCasteStateId());
							castVO.setCastCount(voterCastInfo.getCasteVoters());
							castVO.setMalevoters(voterCastInfo.getCasteMaleVoters());
							castVO.setFemalevoters(voterCastInfo.getCasteFemaleVoters());
							castVO.setCasteCategoryName(voterCastInfo.getCasteState().getCasteCategoryGroup().getCasteCategory().getCategoryName());
							castVO.setCastPercentage(voterCastInfo.getCastePercentage().toString());
							castVOs.add(castVO);
						}
						
						return new ArrayList<VoterCastInfoVO>(mandalMap.values());
					}
					
					public List<VoterCastInfoVO> getVotersCastInfoForMultipleValues(List<SelectOptionVO> subList,Long publicationDateId,Long userId,Long constituencyId,Long locationLvl){
						Map<Long,String> ids = new HashMap<Long,String>();
						
						List<VoterCastInfoVO> castList = new ArrayList<VoterCastInfoVO>();
						for (SelectOptionVO id : subList){
							ids.put(id.getId(),id.getName());
						}
						
						if(!ids.isEmpty()){
							List<VoterCastInfo> mandalWiseInfo =  voterCastInfoDAO.getVotersCastInfoByMultipleLevelValues(locationLvl,ids.keySet(),constituencyId,publicationDateId,userId);
							 if(mandalWiseInfo != null && !mandalWiseInfo.isEmpty()){
								 castList.addAll(populateCastDetailsToVo(mandalWiseInfo ,ids,locationLvl,publicationDateId,constituencyId));
							 }
						}
						
						return castList;
					}
					public List<VoterPartyInfo> getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(Long userId,String locationType,List<Long> locationIds,Long publicationDateId,Long constituencyId,boolean isPartial)
					{
						List<VoterPartyInfo> resultList = new ArrayList<VoterPartyInfo>();
						
						Map<Long,Map<Long,VoterPartyInfo>> locationsMap = new HashMap<Long,Map<Long,VoterPartyInfo>>();
						try{
							List<Object[]> list = null;
							if(!isPartial)
							 list = boothPublicationVoterDAO.getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,locationType,locationIds,publicationDateId,constituencyId);
							if(isPartial) 
							 list = boothPublicationVoterDAO.getPartyWiseCastAndGenderWiseVotersCountByPublicationIdForPartialPanchayat(userId,locationIds,publicationDateId,constituencyId);
							if(list != null && list.size() > 0)
							{
								VoterPartyInfo voterPartyInfo = null;
								Map<Long,VoterPartyInfo> partiesMap = null;
								Map<Long,Party> parties = new HashMap<Long,Party>();
								Map<Long,Long> locationTotalCount = new HashMap<Long,Long>();
								String location = locationType;
								if("localElectionBody".equalsIgnoreCase(location)){
									location = "Local Election Body";
								}
								VoterReportLevel voterReportLevel = voterReportLevelDAO.getReportLevelByType(location);
								
								for(Object[] params : list)
								{
									partiesMap = locationsMap.get((Long)params[4]);
									if(partiesMap == null){
										partiesMap = new HashMap<Long,VoterPartyInfo>();
										locationsMap.put((Long)params[4], partiesMap);
									}
									
									voterPartyInfo = partiesMap.get((Long)params[3]);
									
									if(voterPartyInfo == null){
										voterPartyInfo = new VoterPartyInfo();
										Party party = parties.get((Long)params[3]);
										if(party == null)
											party = partyDAO.get((Long)params[3]);
										voterPartyInfo.setParty(party);
										voterPartyInfo.setUserId(userId);
										voterPartyInfo.setVoterReportLevel(voterReportLevel);
										voterPartyInfo.setReportLevelValue((Long)params[4]);
										voterPartyInfo.setConstituencyId(constituencyId);
										voterPartyInfo.setPublicationDateId(publicationDateId);
										partiesMap.put((Long)params[3], voterPartyInfo);
									}
									
									String gender = params[1].toString();
									
									if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")){
										voterPartyInfo.setPartyMaleVoters((Long)params[2]);
										
									}else if(gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female")){
										voterPartyInfo.setPartyFemaleVoters((Long)params[2]);
										
									}
									if(voterPartyInfo.getPartyVoters() != null){
										voterPartyInfo.setPartyVoters(voterPartyInfo.getPartyVoters()+(Long)params[2]);
									}else{
										voterPartyInfo.setPartyVoters((Long)params[2]);
									}
									if(locationTotalCount.get((Long)params[4]) != null){
										locationTotalCount.put((Long)params[4],(locationTotalCount.get((Long)params[4])+(Long)params[2]));
									}else{
										locationTotalCount.put((Long)params[4],(Long)params[2]);
									}
									
								}
								
								if(locationsMap.size() > 0){
									for(Long locationKey:locationsMap.keySet()){
										partiesMap = locationsMap.get(locationKey);
										Long locationCount = locationTotalCount.get(locationKey);
										if(partiesMap.size() > 0){
											resultList.addAll(partiesMap.values());
											for(Long partyKey:partiesMap.keySet()){
												voterPartyInfo = partiesMap.get(partyKey);
												if(voterPartyInfo.getPartyFemaleVoters() == null)
													voterPartyInfo.setPartyFemaleVoters(0l);
												if(voterPartyInfo.getPartyMaleVoters() == null)
													voterPartyInfo.setPartyMaleVoters(0l);
												if(voterPartyInfo.getPartyVoters() == null)
													voterPartyInfo.setPartyVoters(0l);
												String percentage = "0.00";
												try{
													percentage = (new BigDecimal(voterPartyInfo.getPartyVoters()*(100.0)/locationCount.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
												}catch (Exception e) {}
												finally{
													voterPartyInfo.setPartyPercentage(Double.parseDouble(percentage));
												}
											}
										}
									}
								}
								
							}
							return resultList;
						}catch (Exception e) {
							LOG.error("Exception Occured in getCastAndGenderWiseVotersCountByPublicationIdInALocation() Method, Exception is - ",e);
							return resultList;
						}
					}
					
					public void saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(Long userId,String locationType,Map<Long,Long> locationIds,Long publicationDateId,Long constituencyId,boolean isPartial)
					{
						List<VoterCastInfo> resultList = new ArrayList<VoterCastInfo>();
						Map<Long,VoterCastBasicInfo> casteBasicInfoMap = new HashMap<Long,VoterCastBasicInfo>();
						VoterCastBasicInfo voterCastBasicInfo = null;
						Constituency constituency = constituencyDAO.get(constituencyId);
						Map<Long,Map<Long,VoterCastInfo>> locationsMap = new HashMap<Long,Map<Long,VoterCastInfo>>();
						try{
							List<Long> ids = new ArrayList<Long>();
							ids.addAll(locationIds.keySet());
							List<Object[]> list = null;
							if(!isPartial)
							 list = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdMultipleALocation(userId,locationType,ids,publicationDateId,constituencyId);
							else 
							 list = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdForPartialPanchayat(userId,new ArrayList<Long>(locationIds.keySet()),publicationDateId,constituencyId);
							
							String location = locationType;
							if("localElectionBody".equalsIgnoreCase(location)){
								location = "Local Election Body";
							}
							VoterReportLevel voterReportLevel = voterReportLevelDAO.getReportLevelByType(location);
							
							for(Long id:ids){
								populateDataToVoterCastBasicInfo(voterReportLevel,id,userId,publicationDateId,constituencyId,constituency,casteBasicInfoMap);
							}
							
							if(list != null && list.size() > 0)
							{
								VoterCastInfo voterCastInfo = null;
								Map<Long,VoterCastInfo> casteMap = null;
								
								
								Map<Long,Long> locationTotalCount = new HashMap<Long,Long>();
								
								for(Object[] params : list)
								{
									casteMap = locationsMap.get((Long)params[5]);
									voterCastBasicInfo = casteBasicInfoMap.get((Long)params[5]);
									if(voterCastBasicInfo == null){
										populateDataToVoterCastBasicInfo(voterReportLevel,(Long)params[5],userId,publicationDateId,constituencyId,constituency,casteBasicInfoMap);
									}
									if(casteMap == null){
										casteMap = new HashMap<Long,VoterCastInfo>();
										locationsMap.put((Long)params[5], casteMap);
									}
									
									voterCastInfo = casteMap.get((Long)params[3]);
									
									if(voterCastInfo == null){
										voterCastInfo = new VoterCastInfo();
										if(params[0] != null)
										 voterCastInfo.setCasteState((CasteState)params[0]);
										voterCastInfo.setUserId(userId);
										voterCastInfo.setVoterReportLevel(voterReportLevel);
										voterCastInfo.setReportLevelValue((Long)params[5]);
										voterCastInfo.setConstituency(constituency);
										voterCastInfo.setPublicationDateId(publicationDateId);
										casteMap.put((Long)params[3], voterCastInfo);
									}
									
									String gender = params[1].toString();
									
									if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")){
										voterCastInfo.setCasteMaleVoters((Long)params[2]);
										
									}else if(gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female")){
										voterCastInfo.setCasteFemaleVoters((Long)params[2]);
										
									}
									setRemainingFields(voterCastInfo,voterCastBasicInfo,params,locationTotalCount);	
									
								}
								
								if(locationsMap.size() > 0){
									for(Long locationKey:locationsMap.keySet()){
										casteMap = locationsMap.get(locationKey);
										
										Long locationCount = locationTotalCount.get(locationKey);
										Long parentCount =  locationIds.get(locationKey);
										if(casteMap.size() > 0){
											voterCastBasicInfo = casteBasicInfoMap.get(locationKey);
											voterCastBasicInfo.setTotalCastes(new Long(casteMap.size()));
											voterCastBasicInfo.setCasteAssignedVoters(voterCastBasicInfo.getOcVoters()+voterCastBasicInfo.getBcVoters()+voterCastBasicInfo.getScVoters()+voterCastBasicInfo.getStVoters());
											//Long totalVoters = voterInfoDAO.getVotersCountInALocation(voterReportLevel.getVoterReportLevelId(),locationKey,publicationDateId,constituencyId);
											//if(totalVoters != null)
											 voterCastBasicInfo.setCasteNotAssignedVoters(voterCastBasicInfo.getCasteNotAssignedVoters() -voterCastBasicInfo.getCasteAssignedVoters());
											resultList.addAll(casteMap.values());
											for(Long casteKey:casteMap.keySet()){
												voterCastInfo = casteMap.get(casteKey);
												if(voterCastInfo.getCasteFemaleVoters() == null)
													voterCastInfo.setCasteFemaleVoters(0l);
												if(voterCastInfo.getCasteMaleVoters() == null)
													voterCastInfo.setCasteMaleVoters(0l);
												if(voterCastInfo.getCasteVoters() == null)
													voterCastInfo.setCasteVoters(0l);
												String percentage = "0.00";
												String subLvlpercentage = "0.00";
												try{
												   if(voterCastInfo.getCasteVoters() != null){
													 if(locationCount != null && locationCount > 0l)
													   percentage = (new BigDecimal(voterCastInfo.getCasteVoters()*(100.0)/locationCount.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
													 if(parentCount != null && parentCount > 0l)
													   subLvlpercentage = (new BigDecimal(voterCastInfo.getCasteVoters()*(100.0)/parentCount.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
													}
												}catch (Exception e) {}
												finally{
													voterCastInfo.setCastePercentage(Double.parseDouble(percentage));
													if(!"constituency".equalsIgnoreCase(locationType))
													   voterCastInfo.setSubLeveCastePercentage(Double.parseDouble(subLvlpercentage));
												}
											}
										}
									}
								}
								
							}
							
						}catch (Exception e) {
							LOG.error("Exception Occured in saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation() Method, Exception is - ",e);
						}
					   if(resultList != null && resultList.size() > 0){
						   //voterCastInfoDAO.saveAllObjects(resultList);
						   for(VoterCastInfo voterCastInfo : resultList)
							   voterCastInfoDAO.save(voterCastInfo);
					   }
					   if(casteBasicInfoMap != null && casteBasicInfoMap.size() > 0){
						   voterCastBasicInfoDAO.saveAllObjects(new ArrayList<VoterCastBasicInfo>(casteBasicInfoMap.values()));
					   }
					}
		 public void setRemainingFields(VoterCastInfo voterCastInfo,VoterCastBasicInfo voterCastBasicInfo,Object[] params,Map<Long,Long> locationTotalCount){
			    if(voterCastInfo.getCasteVoters() != null){
					voterCastInfo.setCasteVoters(voterCastInfo.getCasteVoters()+(Long)params[2]);
				}else{
					voterCastInfo.setCasteVoters((Long)params[2]);
				}
				if(locationTotalCount.get((Long)params[5]) != null){
					locationTotalCount.put((Long)params[5],(locationTotalCount.get((Long)params[5])+(Long)params[2]));
				}else{
					locationTotalCount.put((Long)params[5],(Long)params[2]);
				}
				if("OC".equalsIgnoreCase(params[4].toString()))
					voterCastBasicInfo.setOcVoters(voterCastBasicInfo.getOcVoters()+(Long)params[2]);
				if("BC".equalsIgnoreCase(params[4].toString()))
					voterCastBasicInfo.setBcVoters(voterCastBasicInfo.getBcVoters()+(Long)params[2]);
				if("SC".equalsIgnoreCase(params[4].toString()))
					voterCastBasicInfo.setScVoters(voterCastBasicInfo.getScVoters()+(Long)params[2]);
				if("ST".equalsIgnoreCase(params[4].toString()))
				    voterCastBasicInfo.setStVoters(voterCastBasicInfo.getStVoters()+(Long)params[2]);
			 
		 }

				  
				  public List<VoterVO> getVoterDetailsForAdminEdit(Long boothId,Long userId,Long startIndex,Long endIndex){
					 
					  List<VoterVO> resultvalue = new ArrayList<VoterVO>();
					  List<Object[]> serialNosAndVIdsList = new ArrayList<Object[]>(0);
					  Map<Long, Long> serialNoMap = new HashMap<Long, Long>(0);
					  List<Voter> votersDetails = new ArrayList<Voter>(0);
					  VoterVO voterVO = null;
					  Map<Long,VoterVO> voterMap = new HashMap<Long, VoterVO>();
					  Long totalCount = 0L;
					  long sNo = 0l;
					  serialNosAndVIdsList = boothPublicationVoterDAO.getVIdsAndSerialNoByBoothId(boothId,startIndex,endIndex);
					  if(serialNosAndVIdsList != null && serialNosAndVIdsList.size() > 0)
					  {
					  for(Object[] param : serialNosAndVIdsList)
					  serialNoMap.put((Long)param[0], (Long)param[1]);
					  }
					  Long count = new Long(0);
					  List<Long> voterIds = new ArrayList<Long>(0);

					  if(serialNosAndVIdsList != null && serialNosAndVIdsList.size() > 0){
					  for(Object[] param1 : serialNosAndVIdsList){
					  voterIds.add((Long) param1[0]);
					  }
					  }
					
					  Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
					  if(voterIds !=null && voterIds.size() > 0){
						  
						  List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIds, userId);
						  if(list != null && list.size() > 0)
						   for(Object[] params:list)
							 mobileNosMap.put((Long)params[0], params[1]!=null?params[1].toString():"N/A");
						  
						  votersDetails = voterDAO.getVoterInfoByVoterId(voterIds);
						  totalCount = new Long(votersDetails.size());
						  }
						  if(votersDetails != null && votersDetails.size() > 0){

							  for(Voter voters : votersDetails){

								  voterVO = new VoterVO();
								  voterVO.setsNo(++sNo);
								  if(voters.getVoterId()!= null)
								  voterVO.setVoterIds(voters.getVoterId());

								  if(voters.getName()!= null)
								  voterVO.setFirstName(voters.getName());

								  voterVO.setVoterId((++count)+"");

								  if(voters.getAge()!= null)
								  voterVO.setAge(voters.getAge());

								  if(voters.getGender()!= null)
								  voterVO.setGender(voters.getGender());

								  if(voters.getHouseNo()!= null)
								  voterVO.setHouseNo(voters.getHouseNo());

								  if(voters.getRelativeName()!= null)
								  voterVO.setRelativeFirstName(voters.getRelativeName());

								  if(voters.getRelationshipType()!= null)
								  voterVO.setRelationshipType(voters.getRelationshipType());

								 /* if(voters.getCast()!= null)
								  voterVO.setCast(voters.getCast());

								  if(voters.getCastCatagery()!= null)
								  voterVO.setCastCatagery(voters.getCastCatagery());*/

								  if(voters.getVoterIDCardNo()!= null)
								  voterVO.setVoterIDCardNo(voters.getVoterIDCardNo());

								  if(mobileNosMap.get(voters.getVoterId()) != null)
								   voterVO.setMobileNo(mobileNosMap.get(voters.getVoterId()));
								  else
									  voterVO.setMobileNo("N/A");
								  if(serialNoMap.get(voters.getVoterId())!= null)
								  voterVO.setSerialNo(serialNoMap.get(voters.getVoterId()));

								  if(voterVO!= null)
									  resultvalue.add(voterVO);
								   }
								  }
						  
					  return resultvalue;  
				  }
				  
			public VoterVO saveVoterDetailsList(List<VoterVO> voterIds,Long userId,Long boothId){
					  List<Long> serialnos = new ArrayList<Long>();
					  VoterVO voterVO = new VoterVO();
					  Voter voter = new Voter();
					  
					  for(VoterVO list : voterIds)
					  {
						  serialnos = boothPublicationVoterDAO.checkSerialNoandVoterIdDuplicates(list.getSerialNo(),new Long(list.getVoterId()),boothId);
							if( serialnos !=null && serialnos.size()>0){
								voterVO.setNumbers(serialnos);
							}
					  }
							if(voterVO.getNumbers() != null)
								return voterVO;
							else
						 for(VoterVO list : voterIds)
							 {	
						  voterVO.setVoterId(list.getVoterId());
						  voterVO.setSerialNo(list.getSerialNo());
						  Voter voterValue = voterDAO.getVoterByVoterID(new Long(list.getVoterId()));
						 
						  if(list.getFirstName() != null && !list.getFirstName().equalsIgnoreCase(""))
								voterValue.setName(list.getFirstName());
						  if(list.getVoterIDCardNo() != null && !list.getVoterIDCardNo().equalsIgnoreCase(""))
								voterValue.setVoterIDCardNo(list.getVoterIDCardNo());
						  if(list.getGender() != null && !list.getGender().equalsIgnoreCase(""))
								voterValue.setGender(list.getGender());
						  if(list.getAge() != null && list.getAge()> 0)
								voterValue.setAge(list.getAge());
						  if(list.getHouseNo() != null && !list.getHouseNo().equalsIgnoreCase(""))
								voterValue.setHouseNo(list.getHouseNo());
						  if(list.getRelativeFirstName() != null && !list.getRelativeFirstName().equalsIgnoreCase(""))
								voterValue.setRelativeName(list.getRelativeFirstName());
						  if(list.getRelationshipType() != null && !list.getRelationshipType().equalsIgnoreCase(""))
								voterValue.setRelationshipType(list.getRelationshipType());
								voterValue.setMobileNo(list.getMobileNo());
						  if(voterValue != null)
								voterDAO.save(voterValue);
				  
					  boothPublicationVoterDAO.updateSerialNoByVIdBId(voterVO.getSerialNo(),new Long(voterVO.getVoterId()),boothId);
							 }
					  	voterVO.setStatus( "success");
					  return voterVO;
				  }
				  
			public List<VoterHouseInfoVO> getVoterInfoByBIdandVId(List<VoterHouseInfoVO> votersList,Long publicationDateId,Long userId){
					  List<VoterHouseInfoVO> voterHouseInfoVOList = new ArrayList<VoterHouseInfoVO>();
					  List<Long> voterIds =new ArrayList<Long>();
					  Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
					  
					  long count = 0;
					 for(VoterHouseInfoVO list : votersList)
					 {
						 VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
						 voterHouseInfoVO.setVoterId(list.getVoterId());
						 voterHouseInfoVO.setBoothId(list.getBoothId());
						 voterIds.add(voterHouseInfoVO.getVoterId());
					 }
					 
					 if(voterIds != null && voterIds.size() > 0)
					 {
					  List<Object[]> mobileNosList = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIds, userId);
					  if(mobileNosList != null && mobileNosList.size() > 0)
					   for(Object[] params:mobileNosList)
						  mobileNosMap.put((Long)params[0], params[1]!=null?params[1].toString():"N/A");
					 }
					 
					 List<BoothPublicationVoter> values = boothPublicationVoterDAO.getAllVoterDetailsByVoterIds(voterIds,publicationDateId);
					 for(BoothPublicationVoter list : values)
					 {
						 VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
						 	 voterHouseInfoVO.setCount(++count);
						 if(list.getSerialNo() != null)
							 voterHouseInfoVO.setsNo(list.getSerialNo());
						 if(list.getBoothId() != null)
							 voterHouseInfoVO.setBoothId(list.getBoothId());
						 if(list.getVoter().getName() != null)
						 voterHouseInfoVO.setName(list.getVoter().getName());
						 if(list.getVoter().getVoterIDCardNo() != null)
						 voterHouseInfoVO.setVoterIdCardNo(list.getVoter().getVoterIDCardNo());
						 if( list.getVoter().getHouseNo() != null)
						 voterHouseInfoVO.setHouseNo(list.getVoter().getHouseNo());
						 if( list.getVoter().getAge() != null)
						 voterHouseInfoVO.setAge(new Long(list.getVoter().getAge()));
						 /*if( list.getVoter().getCast() != null)
						 voterHouseInfoVO.setCast(list.getVoter().getCast());*/
						 if( list.getVoter().getVoterId() != null)
						 voterHouseInfoVO.setVoterId(new Long(list.getVoter().getVoterId()));
						 if( list.getVoter().getGender() != null)
						 voterHouseInfoVO.setGender(list.getVoter().getGender());
						 if( list.getVoter().getRelationshipType() != null)
						 voterHouseInfoVO.setRelationship(list.getVoter().getRelationshipType());
						 if( list.getVoter().getRelativeName() != null)
						 voterHouseInfoVO.setGaurdian(list.getVoter().getRelativeName());
						 
						 if(mobileNosMap.get(list.getVoter().getVoterId()) != null)
						  voterHouseInfoVO.setMobileNo(mobileNosMap.get(list.getVoter().getVoterId()));
						 else
						  voterHouseInfoVO.setMobileNo("N/A");
						 if( voterHouseInfoVO != null)
						 voterHouseInfoVOList.add(voterHouseInfoVO);
					 }	
					 return voterHouseInfoVOList;
				  }
			public VoterVO saveVoterSearchDetailsList(List<VoterVO> voterIds,Long userId){
					  List<Long> serialnos = new ArrayList<Long>();
					  VoterVO voterVO = new VoterVO();
					  Voter voter = new Voter();
					  for(VoterVO list : voterIds)
					  {
						  serialnos = boothPublicationVoterDAO.checkSerialNoandVoterIdDuplicates(list.getSerialNo(),new Long(list.getVoterId()),list.getBoothId());
							if( serialnos !=null && serialnos.size()>0){
								voterVO.setNumbers(serialnos);
							}
					  }
						if(voterVO.getNumbers() != null)
							return voterVO;
						else
					 for(VoterVO list : voterIds)
						 {	
					  voterVO.setBoothId(list.getBoothId());
					  voterVO.setVoterId(list.getVoterId());
					  voterVO.setSerialNo(list.getSerialNo());
					  Voter voterValue = voterDAO.getVoterByVoterID(new Long(list.getVoterId()));
					 
					  if(list.getFirstName() != null && !list.getFirstName().equalsIgnoreCase(""))
							voterValue.setName(list.getFirstName());
					  if(list.getVoterIDCardNo() != null && !list.getVoterIDCardNo().equalsIgnoreCase(""))
							voterValue.setVoterIDCardNo(list.getVoterIDCardNo());
					  if(list.getGender() != null && !list.getGender().equalsIgnoreCase(""))
							voterValue.setGender(list.getGender());
					  if(list.getAge() != null && list.getAge()> 0)
							voterValue.setAge(list.getAge());
					  if(list.getHouseNo() != null && !list.getHouseNo().equalsIgnoreCase(""))
							voterValue.setHouseNo(list.getHouseNo());
					  if(list.getRelativeFirstName() != null && !list.getRelativeFirstName().equalsIgnoreCase(""))
							voterValue.setRelativeName(list.getRelativeFirstName());
					  if(list.getRelationshipType() != null && !list.getRelationshipType().equalsIgnoreCase(""))
							voterValue.setRelationshipType(list.getRelationshipType());
							voterValue.setMobileNo(list.getMobileNo());
					  if(voterValue != null)
							voterDAO.save(voterValue);
			  
				  boothPublicationVoterDAO.updateSerialNoByVIdBId(voterVO.getSerialNo(),new Long(voterVO.getVoterId()),voterVO.getBoothId());
						 }
				  	voterVO.setStatus( "success");
				  return voterVO;
				  }
			 public List<SelectOptionVO> getWardsInMunicipality(Long lclElecBodyId,Long publicationDateId){
				 List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
				
				 try{
					 List<Object[]> wards = wardDAO.findByWardsByAssemblyLocalElectionBodyId(lclElecBodyId, publicationDateId);
			 for(Object[] ward:wards){
				 SelectOptionVO  selectOptionVO = new SelectOptionVO();
				
				 selectOptionVO.setId((Long)ward[0]);
				 selectOptionVO.setName(ward[1]!=null? ward[1].toString():"");
				 selectOptionVO.setValue(ward[1].toString());
				 List<SelectOptionVO> list2 = votersAnalysisService.getBoothForWard((Long)ward[0],publicationDateId);
					if(list2 != null && list2.size()>0)
						selectOptionVO.setSelectOptionsList(list2);
					
						 list.add(selectOptionVO);
			 }
				
				 }catch(Exception e){
					 LOG.error("Exception rised in getwardsInMunicipality ",e);
				}
				 return list;
				 
			 }
			 
			 public List<VoterCastInfoVO> getVoterAttributeDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId){
				 Map<Long,VoterCastInfoVO> category = new HashMap<Long,VoterCastInfoVO>();
				 try{
					 Map<Long,Map<Long,VoterCastInfoVO>> categoryValues = new HashMap<Long,Map<Long,VoterCastInfoVO>>();
					 List<Object[]> categoriesAndValues = userVoterCategoryValueDAO.getCatergoryAndValues(attributeIds,userId);
					 String locationType1 = locationType;
					 Long totalVoters = 0l;
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
					 if(locationType.equalsIgnoreCase("hamlet")){
							
							List<Long> hamlets = new ArrayList<Long>();
							hamlets.add(locationId);
						List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletIds(userId , hamlets);
					    if(voterIds != null && voterIds.size() >0){	
						    List<Long> countList = 	boothPublicationVoterDAO.getTotalVotersCountForHamletByVoterIds(voterIds,publicationId);
						    if(countList != null && countList.size() >0)
							 totalVoters = countList.get(0);
					    }
					}
					 
					 if(locationType.equalsIgnoreCase("customWard")){
							
						 VotersInfoForMandalVO votersInfoForMandalVO = votersAnalysisService.getVotersCountForCustomWard(locationId, publicationId, "customWard");
						
					    if(votersInfoForMandalVO.getTotVoters() != null){	
							 totalVoters = votersInfoForMandalVO.getTotVoters().longValue();
					    }
					}
					 boolean isPartial = false;
					 if(!locationType.equalsIgnoreCase("hamlet") && !locationType.equalsIgnoreCase("customWard")){
					  Long reportLvlId = votersAnalysisService.getReportLevelId(locationType1);
						  if("panchayat".equalsIgnoreCase(locationType1)){
							  Long count = partialBoothPanchayatDAO.checkPanchayatIsPartial(locationId, publicationId);
							  if(count > 0){
								  isPartial = true;
								  totalVoters = boothPublicationVoterDAO.findVotersCountByPublicationIdForPartialPanchayat(userId, locationId, publicationId);
							  }else{
								  totalVoters = voterInfoDAO.getVotersCountInALocation(reportLvlId,locationId,publicationId,constituencyId); 
							  }
						  }else{
						     totalVoters = voterInfoDAO.getVotersCountInALocation(reportLvlId,locationId,publicationId,constituencyId);
						  }
					 }
					 
					  Map<Long,VoterCastInfoVO> categoryValue = null;
					 VoterCastInfoVO voterCastInfoVO = null;
					 
					 for(Object[] value:categoriesAndValues){
						 if(categoryValues.get((Long)value[0]) == null){
							 voterCastInfoVO = new VoterCastInfoVO();
							 voterCastInfoVO.setId((Long)value[0]);
							 voterCastInfoVO.setName(value[1] != null?value[1].toString():"");
							 if(totalVoters != null)
							 voterCastInfoVO.setTotalVoters(totalVoters);
							 category.put((Long)value[0],voterCastInfoVO);
							 categoryValue = new HashMap<Long,VoterCastInfoVO>();
							 categoryValues.put((Long)value[0], categoryValue);
						 }else{
							 categoryValue = categoryValues.get((Long)value[0]);
						 }
						 
						 if(categoryValue.get((Long)value[2]) == null){
							 voterCastInfoVO = new VoterCastInfoVO();
							 categoryValue.put((Long)value[2], voterCastInfoVO);
						 }else{
							 voterCastInfoVO =  categoryValue.get((Long)value[2]);
						 }
						 
						 voterCastInfoVO.setId((Long)value[2]);
						 voterCastInfoVO.setName(value[3] != null?value[3].toString():"");
						 if(value[4] != null)
						 voterCastInfoVO.setOrderNo(new Long(value[4].toString()));
					 }
					 
					 List<Object[]> attributeValuesList = new ArrayList<Object[]>();
					 if(!locationType.equalsIgnoreCase("hamlet")  && !locationType.equalsIgnoreCase("customWard")){
						 if("panchayat".equalsIgnoreCase(locationType1) && isPartial){
							 /*List<Long> pancIds = new ArrayList<Long>();
							 pancIds.add(locationId);
							 List<Long> hamlets =panchayatHamletDAO.getHamletsOfPanchayitis(pancIds);
							 if(hamlets != null && hamlets.size() > 0)*/
							  attributeValuesList = boothPublicationVoterDAO.getVoterAttributeDetailsForPartialPanchayat(userId, attributeIds, locationId, constituencyId, publicationId);
						 }else{
						    attributeValuesList = boothPublicationVoterDAO.getVoterAttributeDetails(userId,attributeIds,locationType,locationId,constituencyId,publicationId);
						 }
					 }else{
						 attributeValuesList = boothPublicationVoterDAO.getVoterAttributeDetailsForHamlet(userId,attributeIds,locationType,locationId,constituencyId,publicationId);
					 }
					 
					 for(Object[] value:attributeValuesList){
						 categoryValue = categoryValues.get((Long)value[1]);
						 if(categoryValue != null){
							 voterCastInfoVO = categoryValue.get((Long)value[2]);
							 if(voterCastInfoVO != null){
								 if(value[3] != null){
									 if("M".equalsIgnoreCase(value[3].toString()) || "Male".equalsIgnoreCase(value[3].toString())){
										 voterCastInfoVO.setMaleVoters((Long)value[0]);
									 }
									 else if("F".equalsIgnoreCase(value[3].toString()) || "Female".equalsIgnoreCase(value[3].toString())){
										 voterCastInfoVO.setFemaleVoters((Long)value[0]);
									 }
								 }
								 voterCastInfoVO.setTotalVoters(voterCastInfoVO.getTotalVoters()+(Long)value[0]);
								 voterCastInfoVO = category.get((Long)value[1]);
								 if(voterCastInfoVO != null){
									 voterCastInfoVO.setPartyWiseAssignedVoters(voterCastInfoVO.getPartyWiseAssignedVoters()+(Long)value[0]);
								 }
							 }
						 }
					 }
					 
					 for(Long categoryKey:categoryValues.keySet()){
						 Map<Long,VoterCastInfoVO> mainCategory1 = categoryValues.get(categoryKey);
						 VoterCastInfoVO mainCategory2 = category.get(categoryKey);
						 mainCategory2.setPartyWiseNotAssignedVoters(mainCategory2.getTotalVoters().longValue() - mainCategory2.getPartyWiseAssignedVoters());
						 for(Long categoryValueKey:mainCategory1.keySet()){
							 voterCastInfoVO = mainCategory1.get(categoryValueKey);
							 if( mainCategory2.getPartyWiseAssignedVoters().longValue() > 0l){
								 voterCastInfoVO.setPartyPercentage(Double.parseDouble((new BigDecimal(voterCastInfoVO.getTotalVoters()*(100.0)/mainCategory2.getPartyWiseAssignedVoters().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
							 }else{
								 voterCastInfoVO.setPartyPercentage(Double.parseDouble("0.00"));
							 }
						 }
						 mainCategory2.setPartyWisevoterCastInfoVOList(new ArrayList<VoterCastInfoVO>(mainCategory1.values()));
					 }
				 }catch(Exception e){
					 LOG.error("Exception rised in getVoterAttributeDetails ",e);
				 }
				 return new ArrayList<VoterCastInfoVO>(category.values());
			 }
			 
			 public List<VoterCastInfoVO> getVoterAttributeSubDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId){
				 
				 List<Object[]> categoriesAndValues = userVoterCategoryValueDAO.getCatergoryAndValues(attributeIds,userId);
				 if(categoriesAndValues != null && categoriesAndValues.size() > 0){
					 if("constituency".equalsIgnoreCase(locationType)){
						 return getMandalAndMunicipalDetailsForConstituency(userId,attributeIds.get(0),constituencyId,publicationId,categoriesAndValues);
					 }else if("mandal".equalsIgnoreCase(locationType)){
						 return getWardsAndPanchayatDetailsForMandal(userId, attributeIds.get(0),locationId,publicationId,constituencyId,categoriesAndValues);
					 }else if("panchayat".equalsIgnoreCase(locationType)){
						 return getBoothsInPanchayat(userId,attributeIds.get(0),locationId,publicationId,constituencyId,categoriesAndValues);
					 }else if("ward".equalsIgnoreCase(locationType)){
						 return getBoothsInWard(userId,attributeIds.get(0),locationId,publicationId,constituencyId,categoriesAndValues);
					 }
				 }
				 return null;
			 }
			 
			 public List<VoterCastInfoVO> getMandalAndMunicipalDetailsForConstituency(Long userId,Long attributeId,Long constituencyId,Long publicationId,List<Object[]> categoriesAndValues){
				 List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId,IConstants.PRESENT_YEAR, null);
					Map<Long,String> mandalIds = new HashMap<Long,String>();
					Map<Long,String> localBodyIds = new HashMap<Long,String>();
					List<VoterCastInfoVO> returnVal = new ArrayList<VoterCastInfoVO>();
					for (SelectOptionVO mandal : mandalsList){
						if(mandal.getId().toString().substring(0,1).equalsIgnoreCase("2"))
						mandalIds.put(new Long(mandal.getId().toString().trim().substring(1)),mandal.getName());
						else
						{
							List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(mandal.getId().toString().substring(1)));
							localBodyIds.put(new Long(list.get(0).toString()),mandal.getName());
						}
						
					}
					if(mandalIds.size() > 0){
					  List<Object[]> mandalValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForDifferentLocations(userId, attributeId,"mandal", mandalIds.keySet(), constituencyId, publicationId);
					  returnVal.addAll(populateAttributesDetailToVo(mandalValuesList,mandalIds,userId,categoriesAndValues,"Mandal/Municipality"));
					}
					if(localBodyIds.size() > 0){
						 List<Object[]> localBodyValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForDifferentLocations(userId, attributeId,"localElectionBody", localBodyIds.keySet(), constituencyId, publicationId);
						 returnVal.addAll(populateAttributesDetailToVo(localBodyValuesList,localBodyIds,userId,categoriesAndValues,"Mandal/Municipality"));
					}
					return returnVal;
			 }
			 
			 public List<VoterCastInfoVO> getWardsAndPanchayatDetailsForMandal(Long userId,Long attributeId,Long id,Long publicationDateId,Long constituencyId,List<Object[]> categoriesAndValues){
				 List<VoterCastInfoVO> returnVal = new ArrayList<VoterCastInfoVO>();
				 if(id.toString().substring(0, 1).trim().equalsIgnoreCase("2"))
					{
						/*List<Object[]> panchayaties = panchayatDAO.getPanchayatsBymandalId(new Long(id.toString().substring(1).trim()));
						for (Object[] panchayat : panchayaties){
							panchayatIds.put((Long)panchayat[0], panchayat[1]!= null?panchayat[1].toString():"");
						}*/
					    Map<Long,String> panchayatIds = new HashMap<Long,String>();
					    Map<Long,String> partialPancMap = new HashMap<Long,String>();
					    getPartialAndNormalPanchayats(publicationDateId,id,panchayatIds,partialPancMap);
					    List<Object[]> panchayatValuesList = new ArrayList<Object[]>();
					    if(panchayatIds.size() > 0){
							 panchayatValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForDifferentLocations(userId, attributeId,"panchayat", panchayatIds.keySet(), constituencyId, publicationDateId);
						}
						if(partialPancMap.size() > 0){
							List<Object[]> parPanchayatValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForPartialPanchayat(userId, attributeId,"panchayat", partialPancMap.keySet(), constituencyId, publicationDateId);
							if(parPanchayatValuesList != null && parPanchayatValuesList.size() > 0){
								if(panchayatValuesList != null){
									panchayatValuesList.addAll(parPanchayatValuesList);
								}else{
									panchayatValuesList = parPanchayatValuesList;
								}
							}
						}
						returnVal.addAll(populateAttributesDetailToVo(panchayatValuesList,panchayatIds,userId,categoriesAndValues,"Panchayat"));
					}
					else{
						List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1).trim()));
						List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId,constituencyId);
						
						Map<Long,String> wardIds = new HashMap<Long,String>();
						for (Object[] ward : wardsList){
							wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
						}
						if(wardIds.size() > 0){
							List<Object[]> wardValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForDifferentLocations(userId, attributeId,"ward", wardIds.keySet(), constituencyId, publicationDateId);
							returnVal.addAll(populateAttributesDetailToVo(wardValuesList,wardIds,userId,categoriesAndValues,"Ward"));
						}else{
							List<Object[]> wards = userVoterDetailsDAO.getWardsBYLocalElectionBodyId((Long) list.get(0) ,publicationDateId ,userId);
							for (Object[] ward : wards){
								wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
							}
							List<Object[]> wardValuesList = boothPublicationVoterDAO.getVoterAttributeDetailsForHamletForDifferentLocations(userId, attributeId,"customWard",wardIds.keySet(),constituencyId,publicationDateId);
							returnVal.addAll(populateAttributesDetailToVo(wardValuesList,wardIds,userId,categoriesAndValues,"Ward"));
						}
					}
				 return returnVal;
			 } 
			 
			 public List<VoterCastInfoVO> getBoothsInWard(Long userId,Long attributeId,Long id,Long publicationDateId,Long constituencyId,List<Object[]> categoriesAndValues){
				 List<VoterCastInfoVO> returnVal = new ArrayList<VoterCastInfoVO>();
				 List<Object[]> boothsList = boothDAO.getBoothsForWard(id,publicationDateId);
					Map<Long,String> boothIds = new HashMap<Long,String>();
					for (Object[] booth : boothsList){
						boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
					}
					if(boothIds.size() > 0){
						List<Object[]> boothValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForDifferentLocations(userId, attributeId,"booth", boothIds.keySet(), constituencyId, publicationDateId);
						returnVal.addAll(populateAttributesDetailToVo(boothValuesList,boothIds,userId,categoriesAndValues,"Booth"));
					}
				return returnVal;
			 } 
			 
			 public List<VoterCastInfoVO> getBoothsInPanchayat(Long userId,Long attributeId,Long id,Long publicationDateId,Long constituencyId,List<Object[]> categoriesAndValues){
				 List<VoterCastInfoVO> returnVal = new ArrayList<VoterCastInfoVO>();
				 List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(id,publicationDateId);
					Map<Long,String> boothIds = new HashMap<Long,String>();
					for (Object[] booth : boothsList){
						boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
					}
					if(boothIds.size() > 0){
						List<Object[]> boothValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForDifferentLocations(userId, attributeId,"booth", boothIds.keySet(), constituencyId, publicationDateId);
						returnVal.addAll(populateAttributesDetailToVo(boothValuesList,boothIds,userId,categoriesAndValues,"Booth"));
					}
				return returnVal;
			 }
			 
			 public List<VoterCastInfoVO> populateAttributesDetailToVo(List<Object[]> attributeValuesList,Map<Long,String> locations,Long userId,List<Object[]> categoriesAndValues,String locationTyp){
				  Map<Long,VoterCastInfoVO> location = new HashMap<Long,VoterCastInfoVO>();
					 try{
						 Map<Long,Map<Long,VoterCastInfoVO>> locationValues = new HashMap<Long,Map<Long,VoterCastInfoVO>>();
						 
						  Map<Long,VoterCastInfoVO> locationValue = null;
						 VoterCastInfoVO voterCastInfoVO = null;
						 Double totalVoters = 0d;
						 for(Long locationId:locations.keySet()){
							
							 voterCastInfoVO = new VoterCastInfoVO();
							 voterCastInfoVO.setId(locationId);
							 voterCastInfoVO.setName(locations.get(locationId));
							 voterCastInfoVO.setMandalName(locationTyp);
							 voterCastInfoVO.setCastName((categoriesAndValues.get(0)[1]).toString());
							 location.put(locationId,voterCastInfoVO);
							 locationValue = new HashMap<Long,VoterCastInfoVO>();
							 locationValues.put(locationId, locationValue);
							 
						      for(Object[] value:categoriesAndValues){
								voterCastInfoVO = new VoterCastInfoVO();
								locationValue.put((Long)value[2], voterCastInfoVO);
								voterCastInfoVO.setId((Long)value[2]);
								voterCastInfoVO.setName(value[3] != null?value[3].toString():"");
						      }
						 
						 }
						 for(Object[] value:attributeValuesList){
							 locationValue = locationValues.get((Long)value[1]);
							 if(locationValue != null){
								 voterCastInfoVO = locationValue.get((Long)value[2]);
								 if(voterCastInfoVO != null){
									 if(value[3] != null){
										 if("M".equalsIgnoreCase(value[3].toString()) || "Male".equalsIgnoreCase(value[3].toString())){
											 voterCastInfoVO.setMaleVoters((Long)value[0]);
										 }
										 else if("F".equalsIgnoreCase(value[3].toString()) || "Female".equalsIgnoreCase(value[3].toString())){
											 voterCastInfoVO.setFemaleVoters((Long)value[0]);
										 }
									 }
									 voterCastInfoVO = location.get((Long)value[1]);
									 voterCastInfoVO.setTotalVoters(voterCastInfoVO.getTotalVoters()+(Long)value[0]);
									 totalVoters = totalVoters+((Long)value[0]).doubleValue();
								 }
							 }
						 }
						 
						 for(Long locationKey:location.keySet()){
							 Map<Long,VoterCastInfoVO> mainCategory1 = locationValues.get(locationKey);
							  voterCastInfoVO = location.get(locationKey);
								 if( totalVoters.longValue() > 0l){
									 voterCastInfoVO.setPartyPercentage(Double.parseDouble((new BigDecimal(voterCastInfoVO.getTotalVoters()*(100.0)/totalVoters.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
								 }else{
									 voterCastInfoVO.setPartyPercentage(Double.parseDouble("0.00"));
								 }
								 List<VoterCastInfoVO> locationVals =  new ArrayList<VoterCastInfoVO>(mainCategory1.values());
								 Collections.sort(locationVals,sortCategoryValues);
								 voterCastInfoVO.setPartyWisevoterCastInfoVOList(locationVals);
						 }
					 }catch(Exception e){
						 LOG.error("Exception rised in populateAttributesDetailToVo ",e);
					 }
					  List<VoterCastInfoVO> returnVal = new ArrayList<VoterCastInfoVO>(location.values());
					  Collections.sort(returnVal,sortByLocation);
					 return returnVal;
			 }
			 
			 public static Comparator<VoterCastInfoVO> sortCategoryValues = new Comparator<VoterCastInfoVO>()
					    {
					   
					        public int compare(VoterCastInfoVO voterCastInfoVO1, VoterCastInfoVO voterCastInfoVO2)
					        {
					            return (voterCastInfoVO1.getId().intValue()) - (voterCastInfoVO2.getId().intValue());
					        }
					    };
			public static Comparator<VoterCastInfoVO> sortByLocation = new Comparator<VoterCastInfoVO>()
				{
							   
				 public int compare(VoterCastInfoVO voterCastInfoVO1, VoterCastInfoVO voterCastInfoVO2)
				{
		           return voterCastInfoVO1.getName().compareTo(voterCastInfoVO2.getName());
			    }
			 };
			 
		public List<SelectOptionVO> getPanchayatsByTehsilId(Long tehsilId){
			 List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
			 SelectOptionVO selectOptionVO = null;
			 try{
				 List<Object[]> panchayatiesList = panchayatDAO.getPanchayatsByTehsilId(tehsilId);
				 for(Object[] panchayat:panchayatiesList){
					 selectOptionVO = new SelectOptionVO((Long)panchayat[0],panchayat[1].toString());
					 returnVal.add(selectOptionVO);
				 }
			 }catch(Exception e){
				 LOG.error("Exception rised in getPanchayatsByTehsilId ",e);
			 }
			 returnVal.add(0,new SelectOptionVO(0l,"Select Location"));
			 return returnVal;
		}
		
		public List<SelectOptionVO> getBoothsByPanchayatIDConstiId(Long panchayatId,Long constituencyId){
			 List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
			 SelectOptionVO selectOptionVO = null;
			 try{
				 Long publicationId = getLatestPublicationIdByConstiId(constituencyId);
				 List<Object[]> boothsList = boothDAO.getBoothsByPanchayatIDConstiId(panchayatId,constituencyId,publicationId);
				 for(Object[] booth : boothsList){
					 selectOptionVO = new SelectOptionVO((Long)booth[0],"Booth-"+booth[1].toString());
					 returnVal.add(selectOptionVO);
				 }
			 }catch(Exception e){
				 LOG.error("Exception rised in getBoothsByPanchayatIDConstiId ",e);
			 }
			 returnVal.add(0,new SelectOptionVO(0l,"Select Location"));
			 return returnVal;
		}
		
		public Long getLatestPublicationIdByConstiId(Long constituencyId){
			 return publicationDateDAO.getLatestPublicationIdByConstiId(constituencyId);
		}
		/**
		 * This Service is used for Getting All Selected User Categoery Volues Based on Selection
		 * @param Long userId
		 * @param List<Long> ids
		 * @param String type
		 * @param String status
		 * @param Long constituencyId
		 * @return List<SelectOptionVO>
		 */
		public List<SelectOptionVO> getSelectedUserCategoeryDetails(Long userId,List<Long> ids , String type,String status,Long constituencyId,Long publicationId)
		{
			Map<Long,Long> attributeKeys = new HashMap<Long,Long>();
			List<SelectOptionVO> resultData = new ArrayList<SelectOptionVO>();
			SelectOptionVO selectOptionVO = null;
			try {
				LOG.debug("entered into getSelectedUserCategoeryDetails() method in VoterReportService");
				
				
				if(status.equalsIgnoreCase("cast") || status.equalsIgnoreCase("party"))
				{
					if(type.equalsIgnoreCase("mandal"))
					{
						List<Long> mandalIds      = new ArrayList<Long>();
						Long muncipalityId = 0l;
						for (Long values : ids) {
							Long mandalid = 0l;
							
							if(values.toString().substring(0,1).trim().equalsIgnoreCase("1"))
							{
								muncipalityId = Long.valueOf(values.toString().substring(1).trim());
							}
							else
							{
								mandalid =Long.valueOf(values.toString().substring(1).trim());
								mandalIds.add(mandalid);
							}
						}
						if(mandalIds != null && mandalIds.size() > 0)
						{
							//List<Object[]> castOrPartyDetails = boothPublicationVoterDAO.getPartysOrCatstesForSelectedLevel(userId,mandalIds,type,status);
							List<Object[]> castOrPartyDetails = voterCastInfoDAO.getCastAndPartyForSelectedLevel(userId,2l,mandalIds,publicationId);
							if(castOrPartyDetails != null && castOrPartyDetails.size() > 0)
							{
								
								for (Object[] parms : castOrPartyDetails) {
								  if(attributeKeys.get((Long)parms[0]) == null){
									selectOptionVO = new SelectOptionVO();
									selectOptionVO.setId((Long)parms[0]);
									attributeKeys.put((Long)parms[0], (Long)parms[0]);
									selectOptionVO.setName(parms[1].toString());
									resultData.add(selectOptionVO);
								  }
								}
							}
						}
						if(muncipalityId != null && muncipalityId > 0)
						{
							List list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(muncipalityId);
							if(list != null)
							{
								Long localBodyId = (Long)list.get(0);
								List<Long> localBodyIds = new ArrayList<Long>();
								localBodyIds.add(localBodyId);
								//List<Object[]> castesList = boothPublicationVoterDAO.getCastesListForSelectedMuncipality(userId, localBodyId, constituencyId,status);
								List<Object[]> castesList = voterCastInfoDAO.getCastAndPartyForSelectedLevel(userId, 5l, localBodyIds,publicationId);
								for (Object[] parms : castesList) {
								 if(attributeKeys.get((Long)parms[0]) == null){
									 attributeKeys.put((Long)parms[0], (Long)parms[0]);
									selectOptionVO = new SelectOptionVO();
									selectOptionVO.setId((Long)parms[0]);
									selectOptionVO.setName(parms[1].toString());
									resultData.add(selectOptionVO);
								 }
								}
							}
						}
					}
					else if(type.equalsIgnoreCase("panchayat"))
					{
						//List<Object[]> castOrPartyDetails = boothPublicationVoterDAO.getPartysOrCatstesForSelectedLevel(userId,ids,type,status);
						List<Object[]> castOrPartyDetails = voterCastInfoDAO.getCastAndPartyForSelectedLevel(userId,3l,ids,publicationId);
						if(castOrPartyDetails != null && castOrPartyDetails.size() > 0)
						{
							
							for (Object[] parms : castOrPartyDetails) {
							  if(attributeKeys.get((Long)parms[0]) == null){
								 attributeKeys.put((Long)parms[0], (Long)parms[0]);
								selectOptionVO = new SelectOptionVO();
								selectOptionVO.setId((Long)parms[0]);
								selectOptionVO.setName(parms[1].toString());
								resultData.add(selectOptionVO);
							  }
							}
						}
					}
					else if(type.equalsIgnoreCase("booth"))
					{
						List<Object[]> castOrPartyDetails = null;
						synchronized (this) {
							
						
						for(Long boIds : ids)
							queryTempDAO.save(new QueryTemp(boIds));
						voterDAO.flushAndclearSession();
						
						 castOrPartyDetails = boothPublicationVoterDAO.getCatstesForBooths(userId, ids, publicationId);
						queryTempDAO.deleteAll();
						voterDAO.flushAndclearSession();
						}
						//List<Object[]> castOrPartyDetails = boothPublicationVoterDAO.getPartysOrCatstesForSelectedLevel(userId,ids,type,status,publicationId);
						//List<Object[]> castOrPartyDetails = voterCastInfoDAO.getCastAndPartyForSelectedLevel(userId,3l,ids);
						if(castOrPartyDetails != null && castOrPartyDetails.size() > 0)
						{
							
							for (Object[] parms : castOrPartyDetails) {
							 if(attributeKeys.get((Long)parms[0]) == null){
								attributeKeys.put((Long)parms[0], (Long)parms[0]);
								selectOptionVO = new SelectOptionVO();
								selectOptionVO.setId((Long)parms[0]);
								selectOptionVO.setName(parms[1].toString());
								resultData.add(selectOptionVO);
							 }
							}
						}
					}
					
					
				}
				else
				{
					Long categoeryId = Long.valueOf(status); 
					List<Object[]> categoeryValues = userVoterCategoryValueDAO.getCategoeryValuesDAO(userId, categoeryId);
					if(categoeryValues != null && categoeryValues.size() >0)
					{
						for (Object[] parms : categoeryValues) {
						 if(attributeKeys.get((Long)parms[0]) == null){
							attributeKeys.put((Long)parms[0], (Long)parms[0]);
							selectOptionVO = new SelectOptionVO();
							selectOptionVO.setId((Long)parms[0]);
							selectOptionVO.setName(parms[1].toString());
							resultData.add(selectOptionVO);
						 }
						}
					}
				}
			} catch (Exception e) {
				LOG.error("exception raised in  getSelectedUserCategoeryDetails() method in VoterReportService",e);
			}
			Collections.sort(resultData);
			return resultData;
		}
		/**
		 * This Service is Used For Getting All Wards in a Urban Constituency Selection
		 * @param Long constituencyId
		 * @return List<SelectOptionVO>
		 */
		public List<SelectOptionVO> getAllWardsInUrbanConstituency(Long constituencyId,Long publicationId)
		{
			List<SelectOptionVO> returnData = new ArrayList<SelectOptionVO>();
			List result = assemblyLocalElectionBodyDAO.findByConstituencyId(constituencyId);
			Object[] localBodyObject = (Object[]) result.get(0);
			Long localBodyId = (Long) localBodyObject[0];
				List<Object[]> wardsList = wardDAO.getWardsListByLocalEleBodyIdAndConstituencyId(localBodyId,8l,constituencyId);
				if(wardsList != null && wardsList.size() > 0)
				{
					SelectOptionVO selectOptionVO = null;
					for (Object[] parms : wardsList) {
						selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)parms[0]);
						selectOptionVO.setName(parms[1].toString());
						returnData.add(selectOptionVO);
					}
					
				}
			
			return returnData;
		}
		/**
		 * This Service is used for getting All User Categiery Values for Selected Wards
		 * @param Long userId
		 * @param Long constituencyId
		 * @param List<Long> ids
		 * @param String status
		 * @return List<SelectOptionVO>
		 */
		public List<SelectOptionVO> getUserCategoeryValuesForWards(Long userId,Long constituencyId,List<Long> ids ,String status,Long publicationId)
		{
			List<SelectOptionVO> returnData = new ArrayList<SelectOptionVO>();
			//List<Object[]> userCategoeres = boothPublicationVoterDAO.getAllCastesOrPartesForSelectedWards(userId,ids,constituencyId,status);
			List<Object[]> userCategoeres = voterCastInfoDAO.getCastAndPartyForSelectedLevel(userId,6l,ids,publicationId);
			if(userCategoeres != null && userCategoeres.size() > 0)
			{
				SelectOptionVO selectOptionVO = null;
				for (Object[] parms : userCategoeres) {
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					returnData.add(selectOptionVO);
				}
			}
			return returnData;
			
		}
		/**
		 * This Service is used for Getting All Booths In a Selectd Wards in Urban Constituency
		 * @param List<Long> ids
		 * @return List<SelectOptionVO>
		 */
		public List<SelectOptionVO> getAllBoothsForSelectedWards(List<Long> ids,Long publicationId)
		{
			List<SelectOptionVO> returnData = new ArrayList<SelectOptionVO>();
			List<Object[]> boothsList = boothDAO.getBoothsForSelectedWards(ids,publicationId);
			{
				if(boothsList != null && boothsList.size() > 0)
				{
					SelectOptionVO selectOptionVO = null;
					for (Object[] parms : boothsList) {
						selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)parms[0]);
						String name = "BOOTH-"+parms[1].toString();
						selectOptionVO.setName(name);
						returnData.add(selectOptionVO);
					}
				}
			}
			return returnData;
		}
		/**
		 * This Service is used For Getting All User Categoery values in a Selected Muncipal wards
		 * @param Long userId
		 * @param Long constituencyId
		 * @param String type
		 * @param List<Long> ids
		 * @return List<SelectOptionVO>
		 */
		public List<SelectOptionVO> getUserCategoeryValuesForMuncipalWards(Long userId,Long constituencyId,String type,List<Long> ids,Long publicationId)
		{
			List<SelectOptionVO> resultData = new ArrayList<SelectOptionVO>();
			List<Object[]> userCategValues = boothPublicationVoterDAO.getUserCategValuesForSelectedMuncipalWards(userId,constituencyId,type,ids,publicationId);
			if(userCategValues != null && userCategValues.size() > 0)
			{
				SelectOptionVO selectOptionVO = null;
				for (Object[] parms : userCategValues) {
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					resultData.add(selectOptionVO);
				}
			}
			return resultData;
		}
		
		/**
		 * This Service is used for getting Counts (Cadre,Influencing People and Candidate) For Selected Level(booth or Panchayat)
		 * @param Long userId
		 * @param Long id
		 * @param Long constituencyId
		 * @param String selLevel
		 * @param Long publicationDateId
		 * @return List<InfluencingPeopleBeanVO>
		 */
		public List<InfluencingPeopleBeanVO>  getCountsOfSelectedLevel(Long userId,Long id,Long constituencyId,String selLevel,Long publicationDateId)
		{
			List<InfluencingPeopleBeanVO> resultCount = new ArrayList<InfluencingPeopleBeanVO>();
			InfluencingPeopleBeanVO influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
			List<Long> boothids = new ArrayList<Long>();
			if(selLevel.equalsIgnoreCase("booth"))
			{
				boothids.add(id);
			}
			else if(selLevel.equalsIgnoreCase("panchayat"))
			{
				List<Object[]> booths = boothDAO.getBoothsInAPanchayat(id,publicationDateId); 
				if(booths != null && booths.size() > 0)
				{
					for (Object[] parms : booths) {
						boothids.add((Long)parms[0]);
					}
				}
			}
			
			
			List<Long> inluencingPeopleCount = boothPublicationVoterDAO.getInfluencingPeopleCountForSelectedLevel(boothids,constituencyId,userId);
			if(inluencingPeopleCount != null)
			{
				for (Long influeneCount : inluencingPeopleCount) {
					influencingPeopleBeanVO.setInfluencePeopleCount(influeneCount.longValue());
					//resultCount.add(influencingPeopleBeanVO);
				}
				
			}
			
			List<Long> cadreCount = boothPublicationVoterDAO.getCadreCountForSelectedLevel(boothids,constituencyId,userId);
			if(cadreCount != null && cadreCount.size() > 0)
			{
				for (Long countCadre : cadreCount) {
					influencingPeopleBeanVO.setCadreCount(countCadre.longValue());
					//resultCount.add(influencingPeopleBeanVO);
				}
			}
			
			List<Long> politicanCount = boothPublicationVoterDAO.getPoliticianCountForSelectedLevel(boothids,constituencyId);
			if(politicanCount != null && politicanCount.size() > 0)
			{
				for (Long countPolitican : politicanCount) {
					influencingPeopleBeanVO.setPoliticianCount(countPolitican.longValue());
					
				}
			}
			List<Long> flagCount =voterFlagDAO.getFlagCountForSelectedLevel(boothids,constituencyId,userId);
			if(flagCount !=null && flagCount.size() > 0)
				for(Long flag : flagCount)
					influencingPeopleBeanVO.setFlagsCount(flag.longValue());
			
			resultCount.add(influencingPeopleBeanVO);
			return resultCount;
		}
		
		/**
		 * This Service is used for Showing Details (Cadre,Influencing People and Candidate) For Selected Level(booth or Panchayat)
		 * @param Long userId
		 * @param Long id
		 * @param Long constituencyId
		 * @param String selLevel
		 * @param Long publicationDateId
		 * @param String type
		 * @param Integer startIndex
		 * @param Integer maxIndex
		 * @param String order
		 * @param String columnName
		 * @return List<VoterVO>
		 */
		public List<VoterVO> showVoterDetailsForSelcetedLevel(Long userId,Long id,Long constituencyId,String selLevel,Long publicationDateId,String type,Integer startIndex,Integer maxIndex,String order,String columnName)
		{
			List<VoterVO> resultData = new ArrayList<VoterVO>();
			List<Long> boothids = new ArrayList<Long>();
			if(selLevel.equalsIgnoreCase("booth"))
			{
				boothids.add(id);
			}
			else if(selLevel.equalsIgnoreCase("panchayat"))
			{
				List<Object[]> booths = boothDAO.getBoothsInAPanchayat(id,publicationDateId); 
				if(booths != null && booths.size() > 0)
				{
					for (Object[] parms : booths) {
						boothids.add((Long)parms[0]);
					}
				}
			}
			if(type.equalsIgnoreCase("InfluencePeople"))
			{
				
				List<InfluencingPeople> influencingData = boothPublicationVoterDAO.getInfluencingPeopleDetailsForSelectedlevel(boothids,constituencyId,userId,startIndex,maxIndex,order,columnName);
				if(influencingData != null && influencingData.size() > 0)
				{
					List<Long> inluencingPeopleCount = boothPublicationVoterDAO.getInfluencingPeopleCountForSelectedLevel(boothids,constituencyId,userId);
					Long totalRecords = inluencingPeopleCount.get(0).longValue();
					resultData = storeInfluencingPeopleDetails(influencingData,selLevel,id,totalRecords,userId);
				}
			}
			else if(type.equalsIgnoreCase("Cadre"))
			{
				List<Cadre> cadreDetails = boothPublicationVoterDAO.getCadreDetailsForSelectedlevel(boothids,constituencyId,userId,startIndex,maxIndex,order,columnName);
				if(cadreDetails != null && cadreDetails.size() > 0)
				{
					List<Long> cadreCount = boothPublicationVoterDAO.getCadreCountForSelectedLevel(boothids,constituencyId,userId);
					Long totalRecords = cadreCount.get(0).longValue();
					resultData = storeCadrePeopleDetails(cadreDetails,selLevel,id,totalRecords,userId);
				}
				
			}
			else if(type.equalsIgnoreCase("Politician"))
			{
				List<Candidate> candidateDetails = boothPublicationVoterDAO.getPoliticanDetailsForSelectedlevel(boothids,constituencyId,startIndex,maxIndex,order,columnName);
				if(candidateDetails != null && candidateDetails.size() > 0)
				{
					List<Long> politicanCount = boothPublicationVoterDAO.getPoliticianCountForSelectedLevel(boothids,constituencyId);
					Long totalRecords = politicanCount.get(0).longValue();
					resultData = storeCandidateDetails(candidateDetails,selLevel,id,totalRecords,userId);
				}
			}
			else if(type.equalsIgnoreCase("flag"))	
			{
				List<VoterFlag> flagDetails = boothPublicationVoterDAO.getFlagDetailsForSelectedlevel(boothids,constituencyId,startIndex,maxIndex,order,columnName);
				if(flagDetails != null && flagDetails.size() > 0)
				{
					List<Long> flagCount = voterFlagDAO.getFlagCountForSelectedLevel(boothids ,constituencyId,userId);
					Long totalRecords = flagCount.get(0).longValue();
					resultData = storeFlagDetails(flagDetails,selLevel,id,totalRecords,userId);
				}
			}
			
			return resultData;
		}
		/**
		 * This Service is used for getting counts for (cadre,influencing people and candidate) for selected hamlet
		 * @param Long hamletId
		 * @param userId userId
		 * @return List<InfluencingPeopleBeanVO> resultCount
		 */
		public List<InfluencingPeopleBeanVO> getcountForSelectedTypeInHamlet(Long hamletId,Long userId,String selLevel)
		{
			List<InfluencingPeopleBeanVO> resultCount = new ArrayList<InfluencingPeopleBeanVO>();
			InfluencingPeopleBeanVO influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
			
			List<Long> influencingPeopleCount = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(hamletId,userId,"InfluencePeople",selLevel);
			if(influencingPeopleCount != null && influencingPeopleCount.size() > 0)
			{
				for (Long influenceCount : influencingPeopleCount) {
					influencingPeopleBeanVO.setInfluencePeopleCount(influenceCount.longValue());
				}
			}
			
			List<Long> cadrePeopleCount = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(hamletId,userId,"Cadre",selLevel);
			if(cadrePeopleCount != null && cadrePeopleCount.size() > 0)
			{
				for (Long cadreCount : cadrePeopleCount) {
					influencingPeopleBeanVO.setCadreCount(cadreCount.longValue());
				}
			}
			
			List<Long> candidatePeopleCount = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(hamletId,userId,"Politician",selLevel);
			if(candidatePeopleCount != null && candidatePeopleCount.size() > 0)
			{
				for (Long candidateCount : candidatePeopleCount) {
					influencingPeopleBeanVO.setPoliticianCount(candidateCount.longValue());
				}
			}
			List<Long> flagCount = voterFlagDAO.getCountForSelectedTypeInHamlet(hamletId,userId,selLevel);
			if(flagCount != null && flagCount.size() > 0)
			{
				for (Long count : flagCount) {
					influencingPeopleBeanVO.setFlagsCount(count.longValue());
				}
			}
			resultCount.add(influencingPeopleBeanVO);
			return resultCount;
		}
		
		/**
		 * This Service is used for Showing Details (Cadre,Influencing People and Candidate) For Selected Hamlet
		 * @param Long userId
		 * @param Long hamletId
		 * @param String selLevel
		 * @param String type
		 * @param Integer startIndex
		 * @param Integer maxIndex
		 * @param String order
		 * @param String columnName
		 * @return List<VoterVO>
		 */
		public List<VoterVO> showingVoterDetailsForSelectedHamlet(Long hamletId,Long userId,String selLevel,String type,Integer startIndex,Integer maxIndex,String order,String columnName)
		{
			List<VoterVO> resultList = null;
			
			if(type.equalsIgnoreCase("InfluencePeople"))
			{
				List<InfluencingPeople> influencingpeopleData = userVoterDetailsDAO.getInfluencingPeopleDetailsForSelectedHamlet(hamletId,userId,startIndex,maxIndex,order,columnName,selLevel);
				if(influencingpeopleData != null && influencingpeopleData.size() > 0)
				{
					List<Long> influencingPeopleCount = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(hamletId,userId,"InfluencePeople",selLevel);
					Long totalRecods = influencingPeopleCount.get(0).longValue();
					resultList = storeInfluencingPeopleDetails(influencingpeopleData,selLevel,hamletId,totalRecods,userId);
				}
				
				
			}
			else if(type.equalsIgnoreCase("Cadre"))
			{
				List<Cadre> cadrepeopleData = userVoterDetailsDAO.getCadreDetailsForSelectedHamlet(hamletId,userId,startIndex,maxIndex,order,columnName,selLevel);
				if(cadrepeopleData != null && cadrepeopleData.size() > 0)
				{
					List<Long> cadrePeopleCount = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(hamletId,userId,"Cadre",selLevel);
					Long totalRecods = cadrePeopleCount.get(0).longValue();
					resultList = storeCadrePeopleDetails(cadrepeopleData,selLevel,hamletId,totalRecods,userId);
				}
			}
			else if(type.equalsIgnoreCase("Politician"))
			{
				List<Candidate> candidatepeopleData = userVoterDetailsDAO.getCandidateDetailsForSelectedHamlet(hamletId,userId,startIndex,maxIndex,order,columnName,selLevel);
				if(candidatepeopleData != null && candidatepeopleData.size() > 0)
				{
					List<Long> candidatePeopleCount = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(hamletId,userId,"Politician",selLevel);
					Long totalRecods = candidatePeopleCount.get(0).longValue();
					resultList = storeCandidateDetails(candidatepeopleData,selLevel,hamletId,totalRecods,userId);
				}
			}
			else if(type.equalsIgnoreCase("flag"))
			{
				List<VoterFlag> flagpeopleData = userVoterDetailsDAO.getFlagDetailsForSelectedHamlet(hamletId,userId,startIndex,maxIndex,order,columnName,selLevel);
				if(flagpeopleData != null && flagpeopleData.size() > 0)
				{
					List<Long> flagPeopleCount = voterFlagDAO.getCountForSelectedTypeInHamlet(hamletId,userId,selLevel);
					Long totalRecods = flagPeopleCount.get(0).longValue();
					resultList = storeFlagDetails(flagpeopleData,selLevel,hamletId,totalRecods,userId);
				}
			}
			
			return resultList;
		}
		/**
		 * This Service is used for storing all Influencing People Details into VoterVO.
		 * @param List<InfluencingPeople> influencingData
		 * @param String type
		 * @param Long id
		 * @return List<VoterVO>
		 */
		public List<VoterVO> storeInfluencingPeopleDetails(List<InfluencingPeople> influencingData,String type,Long id,Long totalRecords,Long userId)
		{
			List<VoterVO> resultData = null;
			VoterVO voterVO = null;
			Map<Long,VoterVO> influencingMap = new HashMap<Long, VoterVO>();
			Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
			List<Long> voterIdsList = new ArrayList<Long>(0);
			
			if(influencingData != null && influencingData.size() > 0)
			 for(InfluencingPeople influencingPeople:influencingData)
				voterIdsList.add(influencingPeople.getVoter().getVoterId());
			
			if(voterIdsList != null && voterIdsList.size() > 0)
			{
			 List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIdsList, userId);
			 if(list != null && list.size() > 0)
			  for(Object[] params:list)
				 mobileNosMap.put((Long)params[0], params[1] != null?params[1].toString():"");
			}
			
			if(influencingData != null && influencingData.size() > 0)
			{
				resultData = new ArrayList<VoterVO>();
				Long count = 1l;
				for (InfluencingPeople influencingPeople : influencingData) {
						voterVO = new VoterVO();
						voterVO.setVoterId((Long.valueOf(count).toString()));
						voterVO.setFirstName(influencingPeople.getVoter().getName());
						voterVO.setVoterIDCardNo(influencingPeople.getVoter().getVoterIDCardNo());
						voterVO.setVoterIds(influencingPeople.getVoter().getVoterId());
						voterVO.setGender(influencingPeople.getVoter().getGender());
						voterVO.setAge(influencingPeople.getVoter().getAge());
						voterVO.setHouseNo(influencingPeople.getVoter().getHouseNo());
						voterVO.setRelativeFirstName(influencingPeople.getVoter().getRelativeName());
						/*if(influencingPeople.getCaste()!=null){
							voterVO.setCast(getInfluencingPeopleCasteCategory(influencingPeople.getCaste()));
						}*/
						if(mobileNosMap.get(influencingPeople.getVoter().getVoterId()) != null)
						 voterVO.setMobileNo(mobileNosMap.get(influencingPeople.getVoter().getVoterId()));
						if(voterVO.getMobileNo() == null || voterVO.getMobileNo().length()==0){
							voterVO.setMobileNo(influencingPeople.getPhoneNo()!=null?influencingPeople.getPhoneNo():"N/A");
						}
						
						++count;
						voterVO.setTotalVoters(totalRecords);
						voterVO.setInfluencingRange(influencingPeople.getInfluencingScope());
						voterVO.setInfluencingRegion(getRegionNameBasedOnScope(influencingPeople.getInfluencingScope(),influencingPeople.getInfluencingScopeValue()));
						voterVO.setPosition(influencingPeople.getInfluencingPeoplePosition().getPosition());
						/*if(type.equalsIgnoreCase("booth"))
						{
							Booth booth = boothDAO.get(id);
							voterVO.setLocalArea("BOOTH - " + booth.getPartNo());
						}
						else if(type.equalsIgnoreCase("panchayat"))
						{
							voterVO.setLocalArea(panchayatDAO.get(id).getPanchayatName().toString());
						}
						else if(type.equalsIgnoreCase("hamlet"))
						{
							voterVO.setLocalArea(hamletDAO.get(id).getHamletName().toString());
						}
						else if(type.equalsIgnoreCase("customWard"))
						{
							voterVO.setLocalArea(constituencyDAO.get(id).getName().toString());
						}*/
						
						StringBuilder location=new StringBuilder();
						//voterVO.setLocalArea(name);
						UserAddress address=userAddressDAO.get(influencingPeople.getUserAddress().getUserAddressId());
						if(address!=null){
							if(!type.equalsIgnoreCase("booth")){
								if(address.getHamlet()!=null)
									location.append(address.getHamlet().getHamletName()+"(Hamlet) ");
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
								voterVO.setLocalArea(address.getConstituency().getName()!=null?address.getConstituency().getName():"");
							}
						}
						else
							voterVO.setLocalArea(address.getConstituency().getName()!=null?address.getConstituency().getName():"");
						influencingMap.put(voterVO.getVoterIds(), voterVO);
						resultData.add(voterVO);
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
			}
			return resultData;
			
		}
		/**
		 * This Service is used for storing all Cadre People Details into VoterVO.
		 * @param List<Cadre> cadreDetails
		 * @param String type
		 * @param Long id
		 * @return List<VoterVO>
		 */
		public List<VoterVO> storeCadrePeopleDetails(List<Cadre> cadreDetails,String type,Long id,Long totalRecords,Long userId)
		{
			List<VoterVO> resultData = null;
			VoterVO voterVO = null;
			Map<Long,VoterVO> cadreMap = new HashMap<Long, VoterVO>();
			List<Long> voterIdsList = new ArrayList<Long>(0);
			Map<Long,String> mobileMap = new HashMap<Long, String>(0);
			if(cadreDetails != null && cadreDetails.size() > 0)
				for (Cadre cadre : cadreDetails)
				 voterIdsList.add(cadre.getVoter().getVoterId());
			
			if(voterIdsList != null && voterIdsList.size() > 0)
			{
			 List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIdsList, userId);
			 for(Object[] params:list)
				 mobileMap.put((Long)params[0], params[1] != null?params[1].toString():"");
			}
			
			if(cadreDetails != null && cadreDetails.size() > 0)
			{
				resultData = new ArrayList<VoterVO>();
				Long count = 1l;
				for (Cadre cadre : cadreDetails) {
						voterVO = new VoterVO();
						voterVO.setVoterId((Long.valueOf(count).toString()));
						voterVO.setFirstName(cadre.getVoter().getName());
						voterVO.setVoterIDCardNo(cadre.getVoter().getVoterIDCardNo());
						voterVO.setVoterIds(cadre.getVoter().getVoterId());
						voterVO.setCast(cadre.getCasteCategory().getCategory());
						voterVO.setGender(cadre.getVoter().getGender());
						voterVO.setAge(cadre.getVoter().getAge());
						voterVO.setHouseNo(cadre.getVoter().getHouseNo());
						voterVO.setRelativeFirstName(cadre.getVoter().getRelativeName());
						if(mobileMap.get(cadre.getVoter().getVoterId()) != null)
						 voterVO.setMobileNo(mobileMap.get(cadre.getVoter().getVoterId()));
						if(voterVO.getMobileNo() == null || voterVO.getMobileNo().length()==0){
							voterVO.setMobileNo(cadre.getMobile()!=null?cadre.getMobile():"N/A");
						}
						++count;
						voterVO.setTotalVoters(totalRecords);
						/*if(type.equalsIgnoreCase("booth"))
						{
							Booth booth = boothDAO.get(id);
							voterVO.setLocalArea("BOOTH - " + booth.getPartNo());
						}
						else if(type.equalsIgnoreCase("panchayat"))
						{
							voterVO.setLocalArea(panchayatDAO.get(id).getPanchayatName().toString());
						}
						else if(type.equalsIgnoreCase("hamlet"))
						{
							voterVO.setLocalArea(hamletDAO.get(id).getHamletName().toString());
						}
						else if(type.equalsIgnoreCase("customWard"))
						{
							voterVO.setLocalArea(constituencyDAO.get(id).getName().toString());
						}*/
						StringBuilder location=new StringBuilder();
						//voterVO.setLocalArea(name);
						UserAddress address=userAddressDAO.get(cadre.getPermanentAddress().getUserAddressId());
						if(address!=null){
							if(!type.equalsIgnoreCase("booth")){
								if(address.getHamlet()!=null)
									location.append(address.getHamlet().getHamletName()+"(Hamlet) ");
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
								voterVO.setLocalArea(address.getConstituency().getName()!=null?address.getConstituency().getName():"");
							}
						}
						else
							voterVO.setLocalArea(address.getConstituency().getName()!=null?address.getConstituency().getName():"");
						cadreMap.put(voterVO.getVoterIds(), voterVO);
						resultData.add(voterVO);
				}
				List<Long> voterids = new ArrayList<Long>(cadreMap.keySet());
				List<Object[]> castesList = userVoterDetailsDAO.getcasteForVoter(voterids,userId);
				if(castesList != null && castesList.size() > 0)
				{
					for (Object[] parms : castesList) {
						VoterVO voterVO1 = cadreMap.get((Long)parms[1]);
						voterVO1.setCast(parms[0].toString());
					}
				}
			}
			return resultData;
		}
		/**
		 * This Service is used for storing all Candidates Details into VoterVO.
		 * @param List<Candidate> candidateDetails
		 * @param String type
		 * @param Long id
		 * @return List<VoterVO>
		 */
		public List<VoterVO> storeCandidateDetails(List<Candidate> candidateDetails,String type,Long id,Long totalRecords,Long userId)
		{
			List<VoterVO> resultData = null;
			VoterVO voterVO = null;
			Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
			List<Long> voterIdsList = new ArrayList<Long>(0);
			if(candidateDetails != null && candidateDetails.size() > 0)
				for (Candidate candidate : candidateDetails)
				 voterIdsList.add(candidate.getVoter().getVoterId());
			
			if(voterIdsList != null && voterIdsList.size() > 0)
			{
			 List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIdsList, userId);
			 for(Object[] params:list)
			  mobileNosMap.put((Long)params[0], params[1]!= null?params[1].toString():"N/A");
			}
				 
			if(candidateDetails != null && candidateDetails.size() > 0)
			{
				resultData = new ArrayList<VoterVO>();
				Long count = 1l;
				for (Candidate candidate : candidateDetails) {
						voterVO = new VoterVO();
						voterVO.setVoterId((Long.valueOf(count).toString()));
						voterVO.setFirstName(candidate.getVoter().getName());
						voterVO.setVoterIDCardNo(candidate.getVoter().getVoterIDCardNo());
						voterVO.setGender(candidate.getVoter().getGender());
						voterVO.setAge(candidate.getVoter().getAge());
						voterVO.setHouseNo(candidate.getVoter().getHouseNo());
						voterVO.setRelativeFirstName(candidate.getVoter().getRelativeName());
						if(mobileNosMap.get(candidate.getVoter().getVoterId()) != null)
						 voterVO.setMobileNo(mobileNosMap.get(candidate.getVoter().getVoterId()));
						else
						 voterVO.setMobileNo("N/A");
						voterVO.setTotalVoters(totalRecords);
						++count;
						if(type.equalsIgnoreCase("booth"))
						{
							Booth booth = boothDAO.get(id);
							voterVO.setLocalArea("BOOTH - " + booth.getPartNo());
						}
						else if(type.equalsIgnoreCase("panchayat"))
						{
							voterVO.setLocalArea(panchayatDAO.get(id).getPanchayatName().toString());
						}
						else if(type.equalsIgnoreCase("hamlet"))
						{
							voterVO.setLocalArea(hamletDAO.get(id).getHamletName().toString());
						}
						else if(type.equalsIgnoreCase("customWard"))
						{
							voterVO.setLocalArea(constituencyDAO.get(id).getName().toString());
						}
						resultData.add(voterVO);
				}
			}
			return resultData;
		}
		
		
		public List<VoterVO> storeFlagDetails(List<VoterFlag> flagDetails,String type,Long id,Long totalRecords,Long userId)
		{
			List<VoterVO> resultData = null;
			VoterVO voterVO = null;
			Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
			List<Long> voterIdsList = new ArrayList<Long>(0);
			if(flagDetails != null && flagDetails.size() > 0)
				for (VoterFlag flag : flagDetails)
				 voterIdsList.add(flag.getVoter().getVoterId());
			
			if(voterIdsList != null && voterIdsList.size() > 0)
			{
			 List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIdsList, userId);
			 for(Object[] params:list)
			  mobileNosMap.put((Long)params[0], params[1]!= null?params[1].toString():"N/A");
			}
				 
			if(flagDetails != null && flagDetails.size() > 0)
			{
				resultData = new ArrayList<VoterVO>();
				Long count = 1l;
				for (VoterFlag flag : flagDetails) {
						voterVO = new VoterVO();
						voterVO.setVoterId((Long.valueOf(count).toString()));
						voterVO.setFirstName(flag.getVoter().getName());
						voterVO.setVoterIDCardNo(flag.getVoter().getVoterIDCardNo());
						voterVO.setGender(flag.getVoter().getGender());
						voterVO.setAge(flag.getVoter().getAge());
						voterVO.setHouseNo(flag.getVoter().getHouseNo());
						voterVO.setRelativeFirstName(flag.getVoter().getRelativeName());
						if(mobileNosMap.get(flag.getVoter().getVoterId()) != null)
						 voterVO.setMobileNo(mobileNosMap.get(flag.getVoter().getVoterId()));
						else
						 voterVO.setMobileNo("N/A");
						voterVO.setTotalVoters(totalRecords);
						++count;
						if(type.equalsIgnoreCase("booth"))
						{
							Booth booth = boothDAO.get(id);
							voterVO.setLocalArea("BOOTH - " + booth.getPartNo());
						}
						else if(type.equalsIgnoreCase("panchayat"))
						{
							voterVO.setLocalArea(panchayatDAO.get(id).getPanchayatName().toString());
						}
						else if(type.equalsIgnoreCase("hamlet"))
						{
							voterVO.setLocalArea(hamletDAO.get(id).getHamletName().toString());
						}
						else if(type.equalsIgnoreCase("customWard"))
						{
							voterVO.setLocalArea(constituencyDAO.get(id).getName().toString());
						}
						resultData.add(voterVO);
				}
			}
			return resultData;
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
		/**
		 * This Service is Used For Getting All Custom Wards Age Wise Details
		 * @param Long wardId
		 * @param Long userId
		 * @param Long publicationDateId
		 * @param Long constituencyId
		 * @return List<VotersDetailsVO> returnList
		 */
		public List<VotersDetailsVO> getAgeWiseDetailsOfBoothsInSelectedCustomWard(Long wardId,Long userId,Long publicationDateId,Long constituencyId)
		{
			List<VotersDetailsVO> returnList = new ArrayList<VotersDetailsVO>();
			VotersDetailsVO votersDetailsVO = null;
			Map<Long ,VotersDetailsVO> ageWiseDetails = new HashMap<Long, VotersDetailsVO>();
			/*List<Long> boothsIds = new ArrayList<Long>();
			List<Long> boothIds = userVoterDetailsDAO.getBoothsInACustomWard(wardId,userId,publicationDateId,constituencyId);
			for (Long booths : boothIds) {
				boothsIds.add(booths);
			}*/
			
			List<Object[]> ageDeatilsBt18To22 = userVoterDetailsDAO.getAgeWiseDetailsInSelectdCustomWard(wardId,userId,publicationDateId,IConstants.YOUNG_VOTERS_AGE_FROM,IConstants.YOUNG_VOTERS_AGE_TO,constituencyId);
			if(ageDeatilsBt18To22 != null && ageDeatilsBt18To22.size() >0)
			{
				for (Object[] parms : ageDeatilsBt18To22) {
					votersDetailsVO = ageWiseDetails.get((Long)parms[0]);
					if(votersDetailsVO == null)
					{
						votersDetailsVO = new VotersDetailsVO();
						ageWiseDetails.put((Long)parms[0], votersDetailsVO);
					}
					//votersDetailsVO.setAgeRange("18-22");
					votersDetailsVO.setId((Long)parms[0]);
					votersDetailsVO.setBoothName("Booth -" +parms[2]);
					votersDetailsVO.setTotalVotersForYoungerVoters((Long)parms[1]);
					//returnList.add(votersDetailsVO);
				}
			}
			
			List<Object[]> ageDeatilsBt18To25 = userVoterDetailsDAO.getAgeWiseDetailsInSelectdCustomWard(wardId,userId,publicationDateId,IConstants.AGE18,IConstants.AGE25,constituencyId);
			if(ageDeatilsBt18To25 != null && ageDeatilsBt18To25.size() >0)
			{
				for (Object[] parms : ageDeatilsBt18To25) {
					votersDetailsVO = ageWiseDetails.get((Long)parms[0]);
					if(votersDetailsVO == null)
					{
						votersDetailsVO = new VotersDetailsVO();
						ageWiseDetails.put((Long)parms[0], votersDetailsVO);
					}
					//votersDetailsVO.setAgeRange("18-22");
					votersDetailsVO.setId((Long)parms[0]);
					votersDetailsVO.setBoothName("Booth -" +parms[2]);
					votersDetailsVO.setTotalVotersFor18To25((Long)parms[1]);
					//returnList.add(votersDetailsVO);
				}
			}
			
			List<Object[]> ageDeatilsB26To35 = userVoterDetailsDAO.getAgeWiseDetailsInSelectdCustomWard(wardId,userId,publicationDateId,IConstants.AGE23,IConstants.AGE30,constituencyId);
			if(ageDeatilsB26To35 != null && ageDeatilsB26To35.size() >0)
			{
				for (Object[] parms : ageDeatilsB26To35) {
					votersDetailsVO = ageWiseDetails.get((Long)parms[0]);
					if(votersDetailsVO == null)
					{
						votersDetailsVO = new VotersDetailsVO();
						ageWiseDetails.put((Long)parms[0], votersDetailsVO);
					}
					//votersDetailsVO.setAgeRange("26-35");
					votersDetailsVO.setId((Long)parms[0]);
					votersDetailsVO.setBoothName("Booth -" +parms[2]);
					votersDetailsVO.setTotalVotersFor26To35((Long)parms[1]);
					//returnList.add(votersDetailsVO);
				}
			}
			
			List<Object[]> ageDeatilsB36To45 = userVoterDetailsDAO.getAgeWiseDetailsInSelectdCustomWard(wardId,userId,publicationDateId,IConstants.AGE31,IConstants.AGE45,constituencyId);
			if(ageDeatilsB36To45 != null && ageDeatilsB36To45.size() >0)
			{
				for (Object[] parms : ageDeatilsB36To45) {
					votersDetailsVO = ageWiseDetails.get((Long)parms[0]);
					if(votersDetailsVO == null)
					{
						votersDetailsVO = new VotersDetailsVO();
						ageWiseDetails.put((Long)parms[0], votersDetailsVO);
					}
					//votersDetailsVO.setAgeRange("36-45");
					votersDetailsVO.setId((Long)parms[0]);
					votersDetailsVO.setBoothName("Booth -" +parms[2]);
					votersDetailsVO.setTotalVotersFor36To45((Long)parms[1]);
					//returnList.add(votersDetailsVO);
				}
			}
			
			List<Object[]> ageDeatilsBt46To60 = userVoterDetailsDAO.getAgeWiseDetailsInSelectdCustomWard(wardId,userId,publicationDateId,IConstants.AGE46,IConstants.AGE60,constituencyId);
			if(ageDeatilsBt46To60 != null && ageDeatilsBt46To60.size() >0)
			{
				for (Object[] parms : ageDeatilsBt46To60) {
					votersDetailsVO = ageWiseDetails.get((Long)parms[0]);
					if(votersDetailsVO == null)
					{
						votersDetailsVO = new VotersDetailsVO();
						ageWiseDetails.put((Long)parms[0], votersDetailsVO);
					}
					//votersDetailsVO.setAgeRange("46-60");
					votersDetailsVO.setId((Long)parms[0]);
					votersDetailsVO.setBoothName("Booth -" +parms[2]);
					votersDetailsVO.setTotalVotersFor46To60((Long)parms[1]);
					//returnList.add(votersDetailsVO);
				}
			}
			
			List<Object[]> ageDeatilsabove60 = userVoterDetailsDAO.getAbove60AgeWiseDetailsInSelectdCustomWard(wardId,userId,publicationDateId,IConstants.AGE61,constituencyId);
			if(ageDeatilsabove60 != null && ageDeatilsabove60.size() >0)
			{
				for (Object[] parms : ageDeatilsabove60) {
					votersDetailsVO = ageWiseDetails.get((Long)parms[0]);
					if(votersDetailsVO == null)
					{
						votersDetailsVO = new VotersDetailsVO();
						ageWiseDetails.put((Long)parms[0], votersDetailsVO);
					}
					//votersDetailsVO.setAgeRange("above60");
					votersDetailsVO.setId((Long)parms[0]);
					votersDetailsVO.setBoothName("Booth -" +parms[2]);
					votersDetailsVO.setTotalVotersForAbove60((Long)parms[1]);
					//returnList.add(votersDetailsVO);
				}
			}
			
			if(ageWiseDetails.size() > 0)
			{
				for (VotersDetailsVO  objects : ageWiseDetails.values()) {
					//Long totalVoters = objects.getTotalVotersFor18To25()+objects.getTotalVotersFor26To35()+objects.getTotalVotersFor36To45()+objects.getTotalVotersFor46To60()+objects.getTotalVotersForAbove60();
					objects.setBoothName(objects.getBoothName());
					//objects.setAgeRange(objects.getAgeRange());
					objects.setTotalVotersFor18To25(objects.getTotalVotersFor18To25()   != null ? Long.valueOf(objects.getTotalVotersFor18To25())  :0l);
					objects.setTotalVotersFor26To35(objects.getTotalVotersFor26To35()   != null ? Long.valueOf(objects.getTotalVotersFor26To35())  :0l);
					objects.setTotalVotersFor36To45(objects.getTotalVotersFor36To45()   != null ? Long.valueOf(objects.getTotalVotersFor36To45())  :0l);
					objects.setTotalVotersFor46To60(objects.getTotalVotersFor46To60()   != null ? Long.valueOf(objects.getTotalVotersFor46To60())  :0l);
					objects.setTotalVotersForAbove60(objects.getTotalVotersForAbove60() != null ? Long.valueOf(objects.getTotalVotersForAbove60()) :0l);
					objects.setId(objects.getId());
					Long totalVoters = objects.getTotalVotersFor18To25()+objects.getTotalVotersFor26To35()+objects.getTotalVotersFor36To45()+objects.getTotalVotersFor46To60()+objects.getTotalVotersForAbove60();
					objects.setTotalVoters(totalVoters);
					objects.setVotersPercentFor18To25(objects.getTotalVotersFor18To25()  != 0 ? roundTo2DigitsFloatValue((float)objects.getTotalVotersFor18To25() *100f/totalVoters) :"0.00");
					objects.setVotersPercentFor26To35(objects.getTotalVotersFor26To35()  != 0 ? roundTo2DigitsFloatValue((float)objects.getTotalVotersFor26To35() *100f/totalVoters) :"0.00");
					objects.setVotersPercentFor36To45(objects.getTotalVotersFor36To45()  != 0 ? roundTo2DigitsFloatValue((float)objects.getTotalVotersFor36To45() *100f/totalVoters) :"0.00");
					objects.setVotersPercentFor46To60(objects.getTotalVotersFor46To60()  != 0 ? roundTo2DigitsFloatValue((float)objects.getTotalVotersFor46To60() *100f/totalVoters) :"0.00");
					objects.setVotersPercentForAbove60(objects.getTotalVotersForAbove60()!= 0 ? roundTo2DigitsFloatValue ((float)objects.getTotalVotersForAbove60()*100f/totalVoters) :"0.00");
					
					objects.setTotalVotersForYoungerVoters(objects.getTotalVotersForYoungerVoters()!= null? Long.valueOf(objects.getTotalVotersForYoungerVoters())  :0l);
					objects.setVotersPercentForYoungerVoters(objects.getTotalVotersForYoungerVoters()!= 0 ? roundTo2DigitsFloatValue ((float)objects.getTotalVotersForYoungerVoters()*100f/totalVoters) :"0.00");
					returnList.add(objects);
				}
				Collections.sort(returnList,sortBoothIds);
			}
			return returnList;
		}
		
		 public static Comparator<VotersDetailsVO> sortBoothIds = new Comparator<VotersDetailsVO>()
				    {
				   
				        public int compare(VotersDetailsVO votersDetailsVO1, VotersDetailsVO votersDetailsVO2)
				        {
				            return (votersDetailsVO1.getId().intValue()) - (votersDetailsVO1.getId().intValue());
				        }
				    };
		public String roundTo2DigitsFloatValue(Float number){
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			return f.format(number);
			
		}
		/**
		 * this Service is used for Getting All Custom Wards in  Selected Muncipality
		 * @param Long constituencyId
		 * @return List<SelectOptionVO> returnList
		 */
		public List<SelectOptionVO> getTotalWardsInLocalBody(Long constituencyId)
		{
			List<SelectOptionVO> returnList = null;
			SelectOptionVO  selectOptionVO = null;
			Long localBodyId = assemblyLocalElectionBodyDAO.getLocalBodyIdBasedOnConstituencyId(constituencyId);
			List<Object[]> wardsList = constituencyDAO.getWardsInALocalBody(localBodyId);
			if(wardsList != null && wardsList.size() > 0)
			{	
				returnList = new ArrayList<SelectOptionVO>();
				for (Object[] parms : wardsList) {
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long) parms[0]);
					selectOptionVO.setName(parms[1].toString());
					returnList.add(selectOptionVO);
				}
			}
			return returnList;
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
		
		public ResultStatus insertVotersBasicInfoToIntermediateTables(Long reportLevelValue,Long publicationDateId,Long userId)
		{
			ResultStatus resultStatus = new ResultStatus();
			try{
				  List<Long> mandalIdsList = new ArrayList<Long>(0);
				  List<SelectOptionVO> panchayatsList = new ArrayList<SelectOptionVO>(0);
				  List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>(0);
				  List<SelectOptionVO> wardsList = new ArrayList<SelectOptionVO>(0);
				  List<SelectOptionVO> ghmcWardsList = new ArrayList<SelectOptionVO>(0);
				  List<Long> panchayatIdsList = new ArrayList<Long>(0);
				  List<Long> localBodiesList = new ArrayList<Long>(0);
				  List<Long> assemblylocalBodiesList = new ArrayList<Long>(0);
				  List<Long> boothIdsList = new ArrayList<Long>(0);
				  List<Object[]> list = null;
				  List<Object[]> list2 = null;
				  List<Long> hamlets = new ArrayList<Long>();
				  
				  List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(reportLevelValue,IConstants.PRESENT_YEAR, null);
				  calculateAndInsertVoterBasicInfoForALocation(IConstants.CONSTITUENCY,reportLevelValue,null,reportLevelValue,userId);
				  
				  if(mandalsList == null || mandalsList.size() == 0)
					  return null;
				  for(SelectOptionVO selectOptionVO : mandalsList)
				  {
					  if(selectOptionVO.getId().toString().substring(0,1).equalsIgnoreCase(IConstants.RURAL_TYPE))
						  mandalIdsList.add(new Long(selectOptionVO.getId().toString().substring(1)));
					  else
					  {
						  assemblylocalBodiesList.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(selectOptionVO.getId().toString().substring(1))).get(0));
						  localBodiesList.add(new Long(selectOptionVO.getId()));
					  }
					  
				  }
				  
				  for(Long mandalId : mandalIdsList)
					  calculateAndInsertVoterBasicInfoForALocation(IConstants.MANDAL,mandalId,reportLevelValue,reportLevelValue,userId);
				  
				  if(mandalIdsList != null && mandalIdsList.size() >0)
					   list = panchayatDAO.getPanchayatIdsByMandalIdsList(mandalIdsList);
				  
				  if(list != null && list.size() > 0)
				  {
					  for(Object[] params : list)
						  panchayatsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				  }
				  
				  for(SelectOptionVO selectOptionVO : panchayatsList)
				  {
				  calculateAndInsertVoterBasicInfoForALocation(IConstants.PANCHAYAT,selectOptionVO.getId(),new Long(selectOptionVO.getName()),reportLevelValue,userId);  
				  panchayatIdsList.add(selectOptionVO.getId());
				  }
				  if(panchayatIdsList.size() > 0)
				  {
				  list2 = boothDAO.getBoothIdsByPanchayatIdsInAPublication(panchayatIdsList, publicationDateId);
				  hamlets =panchayatHamletDAO.getHamletsOfPanchayitis(panchayatIdsList);
				  }
				  if(list2 != null && list2.size() > 0)
					  for(Object[] params : list2)
						  boothsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				  // panchayat booths
				  if(boothsList != null && boothsList.size() > 0)
					  for(SelectOptionVO boothId : boothsList)
						  calculateAndInsertVoterBasicInfoForALocation(IConstants.BOOTH,boothId.getId(),new Long(boothId.getName()),reportLevelValue,userId);
				  //localBody Data
				  if(localBodiesList != null && localBodiesList.size() >0)
				  {
					  for(Long localbodyId: localBodiesList)
					  
						  calculateAndInsertVoterBasicInfoForALocation(IConstants.LOCALELECTIONBODY,localbodyId,reportLevelValue,reportLevelValue,userId);
				  }
				  if(assemblylocalBodiesList != null && assemblylocalBodiesList.size() > 0)
				  {
					 List<Long> localElebodyIds = new ArrayList<Long>();
					 List<Long> localElebodyIds1 = new ArrayList<Long>();
					 List<Object[]> wards = null;
					 List<Object[]> ghmcWards = null;
					 List<Object[]> localEleIds = localElectionBodyDAO.getLocationTypeForLocalEleBodyByLocalEleBodyId(assemblylocalBodiesList);
					  if(localEleIds != null && localEleIds.size() > 0)
					  {
						  for(Object[] params : localEleIds)
						  {
							  if(params[0].toString().equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE) || (params[0].toString().equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE)))
									localElebodyIds.add((Long) params[1]);
							  else
								  localElebodyIds1.add((Long)params[1]); 
						  }
					  }
					  if(localElebodyIds1 != null && localElebodyIds1.size() > 0)
						  ghmcWards = boothDAO.getWardsByLocalElecBodyIds(localElebodyIds1, publicationDateId,reportLevelValue);
					  if(localElebodyIds != null && localElebodyIds.size() > 0)
					 wards = userVoterDetailsDAO.getWardsBYLocalElectionBodyId(localElebodyIds, publicationDateId,userId);  
					  if(wards != null && wards.size() >0)
					  {
						  for(Object[] ward:wards)
							  if(ward[0] != null)
								  wardsList.add(new SelectOptionVO((Long)ward[0],ward[1].toString()));
					 }
					  if(ghmcWards != null && ghmcWards.size() >0)
					  {
						  for(Object[] ward:ghmcWards)
							  if(ward[0] != null)
								  ghmcWardsList.add(new SelectOptionVO((Long)ward[0],ward[1].toString()));
					 }
					  
				  }
				  
				 if(assemblylocalBodiesList.size() > 0)
				  {
					List<Object[]> list3 = boothDAO.getBoothIdsInLocalBodiesForAPublication(assemblylocalBodiesList,publicationDateId,reportLevelValue);
					  
					  if(list3 != null && list3.size() > 0)
					  {
						  for(Object[] params : list3)
							  boothsList.add(new SelectOptionVO((Long)params[0],params[1].toString())); 
					  }
					  
				  }
	              
				 for(SelectOptionVO selectOptionVO:wardsList)
	            	   calculateAndInsertVoterBasicInfoForALocation(IConstants.WARD,selectOptionVO.getId(),0l,reportLevelValue,userId);				 
				 for(SelectOptionVO selectOptionVO:ghmcWardsList)
	            	   calculateAndInsertVoterBasicInfoForALocation("GhmcWard",selectOptionVO.getId(),0l,reportLevelValue,userId);
				
				 for(SelectOptionVO selectOptionVO : boothsList)
					  if(!boothIdsList.contains(selectOptionVO.getId()))
						  boothIdsList.add(selectOptionVO.getId());
				  
				 List<Object[]> allBoothsList = boothDAO.getBoothsInAConstituencyByPublication(reportLevelValue, publicationDateId);
				 
				 if(allBoothsList != null && allBoothsList.size() > 0)
					 for(Object[] params : allBoothsList)
						 calculateAndInsertVoterBasicInfoForALocation(IConstants.BOOTH,(Long)params[0],0L,reportLevelValue,userId);
				 
				if(hamlets != null && hamlets.size() > 0)
					for(Long hamletId : hamlets)
					calculateAndInsertVoterBasicInfoForALocation(IConstants.HAMLET,hamletId,0L,reportLevelValue,userId);
				 /*for(Long boothId :boothIdsList)
				 {
					  SelectOptionVO selectOptionVO = null;
					  for(SelectOptionVO optionVO : boothsList)
						  if(optionVO.getId().equals(boothId))
						  {
							  selectOptionVO = optionVO;
							  break;
						  }
					  //calculateAndInsertVoterBasicInfoForALocation(IConstants.BOOTH,selectOptionVO.getId(),new Long(selectOptionVO.getName()),reportLevelValue);
				 }*/
				 
				  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  return resultStatus;
			}
			catch (Exception e) {
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
			
		}
		
		
		public ResultStatus calculateAndInsertVoterBasicInfoForALocation(String locationType,Long locationValue,Long parentLocationId,Long constituencyId,Long userId)
		{
			 ResultStatus resultStatus = new ResultStatus();
			 List<VotersInfoForMandalVO> result = null;
			 VotersInfoForMandalVO votersInfoForMandalVO = null;
			try{
				if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
					result = votersAnalysisService.getPreviousVotersCountDetailsForAllLevels(constituencyId,0l, 0l,0l ,locationType,userId);
				else if(locationType.equalsIgnoreCase(IConstants.MANDAL))
					result = votersAnalysisService.getPreviousVotersCountDetailsForAllLevels(constituencyId,Long.valueOf(IConstants.RURAL_TYPE+locationValue.toString()), 0l,0l ,locationType,userId);
				else if(locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
					result = votersAnalysisService.getPreviousVotersCountDetailsForAllLevels(constituencyId,locationValue, 0l,0l ,IConstants.MANDAL,userId);
				else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
					result = votersAnalysisService.getPreviousVotersCountDetailsForAllLevels(constituencyId,0l, locationValue,0l ,locationType,userId);
				else if(locationType.equalsIgnoreCase(IConstants.BOOTH))
					result = votersAnalysisService.getPreviousVotersCountDetailsForAllLevels(constituencyId,0l, 0l,locationValue ,locationType,userId);
				else if(locationType.equalsIgnoreCase(IConstants.WARD))
					result = votersAnalysisService.getPreviousVotersCountDetailsForHamlet(constituencyId,0l,0l,0l ,locationValue,userId ,IConstants.CUSTOMWARD);
				else if(locationType.equalsIgnoreCase(IConstants.HAMLET))
					result = votersAnalysisService.getPreviousVotersCountDetailsForHamlet(constituencyId,0l,0l,0l ,locationValue,userId ,IConstants.HAMLET);
				else if(locationType.equalsIgnoreCase("GhmcWard")){
					result = votersAnalysisService.getPreviousVotersCountDetailsForAllLevels(constituencyId,0l,0l,locationValue,"ward",userId);
				    locationType = IConstants.WARD;
				}
				if(result != null && result.size() > 0)
				{	
					int orderNo = result.size();
					for(VotersInfoForMandalVO data :result)
					{
						try{
							votersInfoForMandalVO = new VotersInfoForMandalVO();
							votersInfoForMandalVO.setConstituencyId(constituencyId);
							
							votersInfoForMandalVO.setReportLevelId(votersAnalysisService.getReportLevelId(locationType));
							if(!locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
							votersInfoForMandalVO.setReportLevelValue(locationValue);
							else
								votersInfoForMandalVO.setReportLevelValue((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationValue.toString().substring(1))).get(0));
							votersInfoForMandalVO.setElectinYear(data.getElectinYear());
							votersInfoForMandalVO.setIsPublication(data.getIsPublication());
							votersInfoForMandalVO.setTotalBooths(data.getTotalBooths());
							votersInfoForMandalVO.setTotalVoters(data.getTotVoters().toString());
							votersInfoForMandalVO.setMaleVoters(Long.valueOf(data.getTotalMaleVoters()));
							votersInfoForMandalVO.setFemaleVoters(Long.valueOf(data.getTotalFemaleVoters()));
							votersInfoForMandalVO.setTotalVotersDiff(data.getTotalVotersDiff());
							votersInfoForMandalVO.setFemaleVotersDiff(data.getFemaleVotersDiff());
							votersInfoForMandalVO.setMaleVotersDiff(data.getMaleVotersDiff());
							votersInfoForMandalVO.setOrderNo(orderNo--);
							saveVoterInfoToVoterBasicInfoIntermediateTable(votersInfoForMandalVO);
						}catch(Exception e){
							LOG.error("Exception Occured - ",e);
						}
					}
				}
			}
			catch (Exception e) {
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			}
			return resultStatus;
		}
		
		public ResultStatus saveVoterInfoToVoterBasicInfoIntermediateTable(final VotersInfoForMandalVO votersInfoForMandalVO)
		{
			 ResultStatus resultStatus = new ResultStatus();
			  try{
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(TransactionStatus status) 
				{
					VoterBasicInfo voterBasicInfo = new VoterBasicInfo();
					voterBasicInfo.setConstituency(constituencyDAO.get(votersInfoForMandalVO.getConstituencyId()));
					voterBasicInfo.setReportLevelValue(votersInfoForMandalVO.getReportLevelValue());
					voterBasicInfo.setVoterReportLevel(voterReportLevelDAO.get(votersInfoForMandalVO.getReportLevelId()));
					voterBasicInfo.setYear(new Long(votersInfoForMandalVO.getElectinYear()));
					voterBasicInfo.setType(votersInfoForMandalVO.getIsPublication().equalsIgnoreCase(IConstants.TRUE)?IConstants.PUBLICATION : IConstants.ELECTION);
					voterBasicInfo.setBooths(votersInfoForMandalVO.getTotalBooths());
					voterBasicInfo.setTotalVoters(new Long(votersInfoForMandalVO.getTotalVoters()));
					voterBasicInfo.setMaleVoters(votersInfoForMandalVO.getMaleVoters());
					voterBasicInfo.setFemaleVoters(votersInfoForMandalVO.getFemaleVoters());
					voterBasicInfo.setTotalDiff(votersInfoForMandalVO.getTotalVotersDiff());
					voterBasicInfo.setMaleDiff(votersInfoForMandalVO.getMaleVotersDiff());
					voterBasicInfo.setFemaleDiff(votersInfoForMandalVO.getFemaleVotersDiff());
					voterBasicInfo.setOrderNo(votersInfoForMandalVO.getOrderNo());
					voterBasicInfoDAO.save(voterBasicInfo);
				}
				});
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
			  public ResultStatus deleteVotersBasicInfoFromIntermediateTables(Long constituencyId)
			  {
				  ResultStatus resultStatus = new ResultStatus();
				  try{
						  if(constituencyId != null && constituencyId > 0)
						  {
						  voterBasicInfoDAO.deleteVoterBasicInfoByConstituencyId(constituencyId);
						  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						  }
						  else
						  resultStatus.setResultCode(ResultCodeMapper.FAILURE);  
				  	}
				  catch (Exception e) {
					  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					  e.printStackTrace();
					
				  }
				return resultStatus;
			}
	
			  public ResultStatus insertVotingTrendzToIntermediateTables(Long reportLevelValue,Long publicationDateId,Long userId)
				{
				  ResultStatus resultStatus = new ResultStatus();
					try{
						  List<Long> mandalIdsList = new ArrayList<Long>(0);
						  List<SelectOptionVO> panchayatsList = new ArrayList<SelectOptionVO>(0);
						  List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>(0);
						  List<SelectOptionVO> wardsList = new ArrayList<SelectOptionVO>(0);
						  List<Long>panchayatIdsList = new ArrayList<Long>(0);
						  List<Long> localBodiesList = new ArrayList<Long>(0);
						  List<Long> assemblylocalBodiesList = new ArrayList<Long>(0);
						  List<Long> boothIdsList = new ArrayList<Long>(0);
						  List<Object[]> list = null;
						  List<Object[]> list2 = null;
						  List<Long> hamlets = new ArrayList<Long>();
						  
						  List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(reportLevelValue,IConstants.PRESENT_YEAR, null);
						  calculateAndInsertVotingTrendzInfoForALocation(IConstants.CONSTITUENCY,reportLevelValue,null,reportLevelValue,publicationDateId);
						  
						  if(mandalsList == null || mandalsList.size() == 0)
							  return null;
						  for(SelectOptionVO selectOptionVO : mandalsList)
						  {
							  if(selectOptionVO.getId().toString().substring(0,1).equalsIgnoreCase(IConstants.RURAL_TYPE))
								  mandalIdsList.add(new Long(selectOptionVO.getId().toString().substring(1)));
							  else
							  {
								  assemblylocalBodiesList.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(selectOptionVO.getId().toString().substring(1))).get(0));
								  localBodiesList.add(new Long(selectOptionVO.getId()));
							  }
							  
						  }
						  
						  for(Long mandalId : mandalIdsList)
							  calculateAndInsertVotingTrendzInfoForALocation(IConstants.MANDAL,mandalId,reportLevelValue,reportLevelValue,publicationDateId);
						  
						  if(mandalIdsList != null && mandalIdsList.size() >0)
							   list = panchayatDAO.getPanchayatIdsByMandalIdsList(mandalIdsList);
						  
						  if(list != null && list.size() > 0)
						  {
							  for(Object[] params : list)
								  panchayatsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
						  }
						  
						  for(SelectOptionVO selectOptionVO : panchayatsList)
						  {
						  calculateAndInsertVotingTrendzInfoForALocation(IConstants.PANCHAYAT,selectOptionVO.getId(),new Long(selectOptionVO.getName()),reportLevelValue,publicationDateId);  
						  panchayatIdsList.add(selectOptionVO.getId());
						  }
						  if(panchayatIdsList.size() > 0)
						  {
						  list2 = boothDAO.getBoothIdsByPanchayatIdsInAPublication(panchayatIdsList, publicationDateId);
						 // hamlets =panchayatHamletDAO.getHamletsOfPanchayitis(panchayatIdsList);
						  }
						  if(list2 != null && list2.size() > 0)
							  for(Object[] params : list2)
								  boothsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
						  // panchayat booths
						  if(boothsList != null && boothsList.size() > 0)
							  for(SelectOptionVO boothId : boothsList)
								  calculateAndInsertVotingTrendzInfoForALocation(IConstants.BOOTH,boothId.getId(),new Long(boothId.getName()),reportLevelValue,publicationDateId);
						  //localBody Data
						  if(localBodiesList != null && localBodiesList.size() >0)
						  {
							  for(Long localbodyId: localBodiesList)
							  
								  calculateAndInsertVotingTrendzInfoForALocation(IConstants.LOCALELECTIONBODY,localbodyId,reportLevelValue,reportLevelValue,publicationDateId);
						  }
						  if(assemblylocalBodiesList != null && assemblylocalBodiesList.size() > 0)
						  {
							 
							 List<Long> localElebodyIds = new ArrayList<Long>();
							 List<Object[]> wards = null;
							 List<Object[]> localEleIds = localElectionBodyDAO.getLocationTypeForLocalEleBodyByLocalEleBodyId(assemblylocalBodiesList);
							  if(localEleIds != null && localEleIds.size() > 0)
							  {
								  for(Object[] params : localEleIds)
								  {
									  if(!(params[0].toString().equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE) || (params[0].toString().equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE))))
										  localElebodyIds.add((Long)params[1]); 
								  }
							  }
							  if(localElebodyIds != null && localElebodyIds.size() > 0)
							 wards = boothDAO.getWardsByLocalElecBodyIds(localElebodyIds, publicationDateId,reportLevelValue);
							  if(wards != null && wards.size() >0)
							  {
								  for(Object[] ward:wards)
									  if(ward[0] != null)
										  wardsList.add(new SelectOptionVO((Long)ward[0],ward[1].toString()));
							 }
							  
						  }
						  
						 if(assemblylocalBodiesList.size() > 0)
						  {
							List<Object[]> list3 = boothDAO.getBoothIdsInLocalBodiesForAPublication(assemblylocalBodiesList,publicationDateId,reportLevelValue);
							  
							  if(list3 != null && list3.size() > 0)
							  {
								  for(Object[] params : list3)
									  boothsList.add(new SelectOptionVO((Long)params[0],params[1].toString())); 
							  }
							  
						  }
			              
						 for(SelectOptionVO selectOptionVO:wardsList)
							 calculateAndInsertVotingTrendzInfoForALocation(IConstants.WARD,selectOptionVO.getId(),0l,reportLevelValue,userId);
						  
						 for(SelectOptionVO selectOptionVO : boothsList)
							  if(!boothIdsList.contains(selectOptionVO.getId()))
								  boothIdsList.add(selectOptionVO.getId());
						  
						 List<Object[]> allBoothsList = boothDAO.getBoothsInAConstituencyByPublication(reportLevelValue, publicationDateId);
						 
						 if(allBoothsList != null && allBoothsList.size() > 0)
							 for(Object[] params : allBoothsList)
								 calculateAndInsertVotingTrendzInfoForALocation(IConstants.BOOTH,(Long)params[0],0L,reportLevelValue,publicationDateId);
						 
						/*if(hamlets != null && hamlets.size() > 0)
							for(Long hamletId : hamlets)
								calculateAndInsertVotingTrendzInfoForALocation(IConstants.HAMLET,hamletId,0L,reportLevelValue,userId);*/
						 
						  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						  return resultStatus;
					}
					catch (Exception e) {
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						return resultStatus;
					}
					
					
				}	
			 
			  public ResultStatus calculateAndInsertVotingTrendzInfoForALocation(String locationType,Long locationValue,Long parentLocationId,Long constituencyId,Long publicationId)
				{
					 ResultStatus resultStatus = new ResultStatus();
					 List<PartyVotesEarnedVO> result = null;
					 PartyVotesEarnedVO partyVotesEarnedVO = null;
					try{
						if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
							result = votersAnalysisService.getPreviousElectionVotingTrends(locationValue, publicationId, constituencyId,locationType);
						else if(locationType.equalsIgnoreCase(IConstants.MANDAL))
							result = votersAnalysisService.getPreviousElectionVotingTrends(Long.valueOf(IConstants.RURAL_TYPE+locationValue.toString()),publicationId,constituencyId ,locationType);
						else if(locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
							result = votersAnalysisService.getPreviousElectionVotingTrends(locationValue, publicationId,constituencyId ,IConstants.MANDAL);
						else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT) || locationType.equalsIgnoreCase(IConstants.BOOTH))
							result = votersAnalysisService.getPreviousElectionVotingTrends(locationValue,publicationId, constituencyId,locationType);
						else if(locationType.equalsIgnoreCase(IConstants.WARD))
							result = votersAnalysisService.getPreviousElectionVotingTrends(locationValue,publicationId, constituencyId,"ward");
					
						
						
						if(result != null && result.size() > 0)
						{	
							int orderNo = result.size();
							for(PartyVotesEarnedVO data :result)
							{
							  if(data.getPolledVotes() != null && data.getPolledVotes() > 0){
								try{
									partyVotesEarnedVO = new PartyVotesEarnedVO();
									partyVotesEarnedVO.setConstituencyId(constituencyId);
									
									partyVotesEarnedVO.setReportLevelId(votersAnalysisService.getReportLevelId(locationType));
									if(!locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
										partyVotesEarnedVO.setReportLevelValue(locationValue);
									else
										partyVotesEarnedVO.setReportLevelValue((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationValue.toString().substring(1))).get(0));
									partyVotesEarnedVO.setElectionYear(data.getElectionYear().toString());;
									partyVotesEarnedVO.setTotalBooths(data.getTotalBooths());
									partyVotesEarnedVO.setReqType(data.getReqType());
									partyVotesEarnedVO.setTotalVotes(data.getTotalVotes());
									partyVotesEarnedVO.setPolledVotes(data.getPolledVotes());
									partyVotesEarnedVO.setPartyVotesEarnedVOs(data.getPartyVotesEarnedVOs());
									partyVotesEarnedVO.setOrderNo(orderNo--);
									saveVotingTrendzInfoIntermediateTable(partyVotesEarnedVO);
								}catch(Exception e){
									LOG.error("Exception Occured - ",e);
								}
							  }
							}
						}
					}
					catch (Exception e) {
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					}
					return resultStatus;
				}
			  public ResultStatus saveVotingTrendzInfoIntermediateTable(final PartyVotesEarnedVO  partyVotesEarnedVO)
			  {
				  ResultStatus resultStatus = new ResultStatus();
			  try{
					transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
						 
						VotingTrendz votingTrendz = new VotingTrendz();
						votingTrendz.setConstituency(constituencyDAO.get(partyVotesEarnedVO.getConstituencyId()));
						votingTrendz.setVoterReportLevel(voterReportLevelDAO.get(partyVotesEarnedVO.getReportLevelId()));
						votingTrendz.setReportLevelValue(partyVotesEarnedVO.getReportLevelValue());
						
						if(partyVotesEarnedVO.getReqType().contains("Parliament"))
							votingTrendz.setElectionType(electionTypeDAO.get(1l));
						else if(partyVotesEarnedVO.getReqType().contains("Assembly"))
							votingTrendz.setElectionType(electionTypeDAO.get(2l));
						else if(partyVotesEarnedVO.getReqType().contains("MPTC"))
							votingTrendz.setElectionType(electionTypeDAO.get(3l));
						else if(partyVotesEarnedVO.getReqType().contains("ZPTC"))
							votingTrendz.setElectionType(electionTypeDAO.get(4l));
						else if(partyVotesEarnedVO.getReqType().contains("MUNCIPALITY"))
							votingTrendz.setElectionType(electionTypeDAO.get(5l));
						else if(partyVotesEarnedVO.getReqType().contains("CORPORATION"))
							votingTrendz.setElectionType(electionTypeDAO.get(6l));
						else if(partyVotesEarnedVO.getReqType().contains("Greater Municipal Corp"))
							votingTrendz.setElectionType(electionTypeDAO.get(7l));
						votingTrendz.setYear(new Long(partyVotesEarnedVO.getElectionYear()));
						votingTrendz.setTotalBooths(new Long(partyVotesEarnedVO.getTotalBooths()));
						votingTrendz.setTotalVotes(partyVotesEarnedVO.getTotalVotes());
						votingTrendz.setVotesPolled(partyVotesEarnedVO.getPolledVotes());
						votingTrendz.setOrderNo(partyVotesEarnedVO.getOrderNo());
						votingTrendz = votingTrendzDAO.save(votingTrendz);
						
						List<PartyVotesEarnedVO> partyList = partyVotesEarnedVO.getPartyVotesEarnedVOs();
						
						if(partyList != null && partyList.size() > 0)
						for(PartyVotesEarnedVO party : partyList)
						{
							VotingTrendzPartiesResult votingTrendzPartiesResult = new VotingTrendzPartiesResult();
							votingTrendzPartiesResult.setVotingTrendz(votingTrendz);
							Party party1 = partyDAO.getPartyByShortName(party.getPartyName());
							votingTrendzPartiesResult.setParty(partyDAO.get(party1.getPartyId()));
							votingTrendzPartiesResult.setVotesGained(party.getVotesEarned());
							votingTrendzPartiesResultDAO.save(votingTrendzPartiesResult);
						}
					}
					});
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  }
				catch(Exception e)
				{
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				  
					e.printStackTrace();
				}
			return resultStatus;
			  }
			 
			  public ResultStatus insertVotingTrendzPanchayatInfoToIntermediateTables(final Long reportLevelValue,Long publicationDateId)
				{
					ResultStatus resultStatus = new ResultStatus();
					try{
						  List<Long> mandalIdsList = new ArrayList<Long>(0);
						  List<Long> panchayatIdsList = new ArrayList<Long>(0);
						 
						  List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(reportLevelValue,IConstants.PRESENT_YEAR, null);
						  
						  if(mandalsList == null || mandalsList.size() == 0)
							  return null;
						  for(SelectOptionVO selectOptionVO : mandalsList)
						  {
							  if(selectOptionVO.getId().toString().substring(0,1).equalsIgnoreCase(IConstants.RURAL_TYPE))
								  mandalIdsList.add(new Long(selectOptionVO.getId().toString().substring(1)));
						  }
						  
						  List<Object[]>list = panchayatDAO.getPanchayatIdsByMandalIdsList(mandalIdsList);
						  
						  if(list != null && list.size() > 0)
						  {
							
							  for(Object[] params : list)
								  panchayatIdsList.add((Long)params[0]);
							  
							  for(final Long panchayatId : panchayatIdsList)
							  {
							 List<PartyVotesEarnedVO> resultList = votersAnalysisService.getPreviousElectionVotingTrends(panchayatId, 8l, reportLevelValue,"panchayat");
								
								  if(resultList != null && resultList.size() >0)
								  {
									  final int orderNo = 0;
									
									  for(final PartyVotesEarnedVO  partyVotesEarnedVO : resultList)
									  {
									  try{
											transactionTemplate.execute(new TransactionCallbackWithoutResult() {
											protected void doInTransactionWithoutResult(TransactionStatus status) 
											{
												 
												VotingTrendz votingTrendz = new VotingTrendz();
												votingTrendz.setConstituency(constituencyDAO.get(reportLevelValue));
												votingTrendz.setVoterReportLevel(voterReportLevelDAO.get(3l));
												votingTrendz.setReportLevelValue(panchayatId);
												
												if(partyVotesEarnedVO.getReqType().contains("Parliament"))
													votingTrendz.setElectionType(electionTypeDAO.get(1l));
												else
													votingTrendz.setElectionType(electionTypeDAO.get(2l));
												
												votingTrendz.setYear(new Long(partyVotesEarnedVO.getElectionYear()));
												votingTrendz.setTotalBooths(new Long(partyVotesEarnedVO.getTotalBooths()));
												votingTrendz.setTotalVotes(partyVotesEarnedVO.getTotalVotes());
												votingTrendz.setVotesPolled(partyVotesEarnedVO.getPolledVotes());
												votingTrendz.setOrderNo(orderNo);
												votingTrendz = votingTrendzDAO.save(votingTrendz);
												
												List<PartyVotesEarnedVO> partyList = partyVotesEarnedVO.getPartyVotesEarnedVOs();
												
												if(partyList != null && partyList.size() > 0)
												for(PartyVotesEarnedVO party : partyList)
												{
													VotingTrendzPartiesResult votingTrendzPartiesResult = new VotingTrendzPartiesResult();
													votingTrendzPartiesResult.setVotingTrendz(votingTrendz);
													Party party1 = partyDAO.getPartyByShortName(party.getPartyName());
													votingTrendzPartiesResult.setParty(partyDAO.get(party1.getPartyId()));
													votingTrendzPartiesResult.setVotesGained(party.getVotesEarned());
													votingTrendzPartiesResultDAO.save(votingTrendzPartiesResult);
												}
											}
											});
											
										  }
										catch(Exception e)
										{
											e.printStackTrace();
										}
									  }
								  }
							  }
						  }
						 
						  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						  return resultStatus;
					}
					catch (Exception e) {
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						return resultStatus;
					}
					
				}	
			  public ResultStatus deletePreviousEleVotingIntoIntermediateTables(Long constituencyId)
			  {
				  ResultStatus resultStatus = new ResultStatus();
				  try{
					if(constituencyId != null && constituencyId > 0)  
					{
					 List<Long> votingTrendzIds = votingTrendzPartiesResultDAO.getVotingTrendzIds(constituencyId);
					 if(votingTrendzIds != null && votingTrendzIds.size() > 0)
						votingTrendzPartiesResultDAO.deletePartyResultByConstituencyId(votingTrendzIds);
						votingTrendzDAO.deleteVotingTrendzByConstituencyId(constituencyId);
						resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					}
					else
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				  }
				  catch(Exception e)
				  {
					  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					  e.printStackTrace();
				  }
				return resultStatus;
				
			  } 
			  
				
	  public List<VoterVO> getVoterDataForBooth(Long boothId,Long userId, Integer startIndex,Integer maxRecords, String order, String columnName,List<Long> categories ,String searchColumn , String searchString)
	  {
			LOG.debug("entered into the getVoterDataForBooth method in VotersAnalysisSevice");
		  
			List<VoterVO> voterData = new ArrayList<VoterVO>();
			List<Object[]> voters = null;
			VoterVO voterVO = null;
			List<Long> voterIds = new ArrayList<Long>();
			Long totalCount = 0l;
			Map<Long , VoterVO> voterMap = new HashMap<Long, VoterVO>();
			
			try {
					StringBuffer queryForCategories = new StringBuffer();
					StringBuffer queryForselect = new StringBuffer();
					
					prepareQueryForCategories(categories, queryForCategories, queryForselect);
					
					StringBuffer str = prepareQueryForSearchCriteria(searchColumn , searchString);
					
					voters  = new ArrayList<Object[]>();
					
					voters = boothPublicationVoterDAO.getVotersDetailsAndCountDetailsByBoothId(userId,boothId , startIndex, maxRecords , order,columnName ,str.toString(),queryForCategories.toString(),queryForselect.toString(),false);
					List countList = boothPublicationVoterDAO.getVotersDetailsAndCountDetailsByBoothId(userId,boothId ,startIndex, maxRecords , order,columnName ,str.toString(),queryForCategories.toString(),queryForselect.toString(),true);
					
					totalCount = ((BigInteger)countList.get(0)).longValue();
				
				if(voters != null && voters.size() > 0)
				{
					for (Object[] voterDetails : voters) {
						
						voterVO = new VoterVO();
						voterVO.setVoterId(voterDetails[1].toString());
						voterVO.setName(voterDetails[2].toString());
						voterVO.setGender(voterDetails[5].toString());
						voterVO.setAge(Long.parseLong(voterDetails[6].toString()));
						voterVO.setHouseNo(voterDetails[3].toString());
						if(voterDetails[11] != null)
						 voterVO.setMobileNo(voterDetails[11].toString());
						else
							voterVO.setMobileNo("N/A");
						voterVO.setRelativeFirstName(voterDetails[4].toString());
						voterVO.setPartNo(Long.valueOf(voterDetails[7].toString()));
						voterVO.setTotalVoters(totalCount);
						voterVO.setVoterIds(Long.parseLong(voterDetails[0].toString()));
						voterIds.add(Long.parseLong(voterDetails[0].toString()));
						voterMap.put(Long.parseLong(voterDetails[0].toString()), voterVO);
						voterData.add(voterVO);
						voterVO.setSerialNo(Long.parseLong(voterDetails[11].toString()));
					}
				}
				
				if(voterIds != null && voterIds.size() >0)
				 getInfuelcePeopleAndCadreDetails(voterIds, userId, voterMap);
				
				getCastePartyAndCategoriesDetails(voters, categories, voterMap);
				
			} catch (Exception e) {
				LOG.error("Exception raised in  getVoterDataForBooth method in VotersAnalysis" , e) ;
				e.printStackTrace();
			}
			return voterData;
		}
	  

      /**
       * This method is used to get voter details of a panchayat
       * @param voterDataVO 
       * @param userId
       * @param categories
       * @param searchColumn
       * @param searchString
       * @return List<VoterVO>
       */
	  public List<VoterVO> getVoterDataForPanchayat(VoterDataVO voterDataVO , Long userId , List<Long> categories ,String searchColumn,String searchString)
		{
			List<VoterVO> voterData = new ArrayList<VoterVO>();
			List<Object[]> voters = null;
			VoterVO voterVO = null;
			List<Long> voterIds = new ArrayList<Long>();
			Long totalCount = 0l;
			Map<Long , VoterVO> voterMap = new HashMap<Long, VoterVO>();
			try {
				LOG.debug("Enterd into the getVoterDataForPanchayat service method");
				if(voterDataVO.getBuildType().equalsIgnoreCase("panchayat"))
				{					
				    StringBuffer str = prepareQueryForSearchCriteria(searchColumn , searchString);
					
					StringBuffer queryForCategories = new StringBuffer();
					StringBuffer queryForselect = new StringBuffer();
					
				    prepareQueryForCategories(categories, queryForCategories, queryForselect);
				  
					voters  = new ArrayList<Object[]>();
					
					
					List<PartialBoothPanchayat> list = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(voterDataVO.getId(), voterDataVO.getPublicationId());

					
					List<Long> partialBoothIds = new ArrayList<Long>();
					List<Long> boothIds = new ArrayList<Long>();
					
					if(list != null && list.size() >0)
						for(PartialBoothPanchayat partialPanchayat:list)
							partialBoothIds.add(partialPanchayat.getBooth().getBoothId());
					
					List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(voterDataVO.getId(),voterDataVO.getPublicationId());
					
					if(boothsList.size() >0)
						for(Object[] obj:boothsList)
							boothIds.add((Long)obj[0]);
					
					if(partialBoothIds.size() >0)
						for(Long id:partialBoothIds)
							if(!boothIds.contains(id))
								boothIds.add(id);
						
						
						voters = boothPublicationVoterDAO.getVotersDetailsAnCountDetailsForPanchayatByPublicationId(userId,voterDataVO.getId() , voterDataVO.getPublicationId() ,voterDataVO.getStartIndex().intValue(), voterDataVO.getMaxIndex().intValue() , voterDataVO.getDir(),voterDataVO.getSort() ,str.toString(),queryForCategories.toString(),queryForselect.toString(),false,boothIds);
						List countList = boothPublicationVoterDAO.getVotersDetailsAnCountDetailsForPanchayatByPublicationId(userId,voterDataVO.getId() , voterDataVO.getPublicationId() ,voterDataVO.getStartIndex().intValue(), voterDataVO.getMaxIndex().intValue() , voterDataVO.getDir(),voterDataVO.getSort() ,str.toString(),queryForCategories.toString(),queryForselect.toString(),true,boothIds);
						
						totalCount = ((BigInteger)countList.get(0)).longValue();
					}
					
					if(voters != null && voters.size() > 0)
					{
					for (Object[] voterDetails : voters) {
						
						voterVO = new VoterVO();
						voterVO.setVoterId(voterDetails[1].toString());
						voterVO.setName(voterDetails[2].toString());
						voterVO.setGender(voterDetails[5].toString());
						voterVO.setAge(Long.parseLong(voterDetails[6].toString()));
						voterVO.setHouseNo(voterDetails[3].toString());
						if(voterDetails[12] != null)
						 voterVO.setMobileNo(voterDetails[12].toString());
						else
							voterVO.setMobileNo("N/A");
						voterVO.setRelativeFirstName(voterDetails[4].toString());
						voterVO.setPartNo(Long.valueOf(voterDetails[7].toString()));
						voterVO.setTotalVoters(totalCount);
						voterVO.setVoterIds(Long.parseLong(voterDetails[0].toString()));
						voterIds.add(Long.parseLong(voterDetails[0].toString()));
						voterMap.put(Long.parseLong(voterDetails[0].toString()), voterVO);
						voterData.add(voterVO);
						voterVO.setSerialNo(Long.parseLong(voterDetails[11].toString()));
					}
				}
				
				if(voterIds != null && voterIds.size() >0)
				 getInfuelcePeopleAndCadreDetails(voterIds,userId,voterMap);
				
				getCastePartyAndCategoriesDetails(voters, categories, voterMap);
				
			} catch (Exception e) {
				LOG.error("Exception raised in getVoterDataForPanchayat service method" , e) ;
				e.printStackTrace();
			}
			return voterData;
		}
	   /**
	    * This method will set all the influence people , cadre and candidate details
	    * @param voterIds
	    * @param userId
	    * @param voterMap
	    */
		public void getInfuelcePeopleAndCadreDetails(List<Long> voterIds ,Long userId , Map<Long , VoterVO> voterMap)
		{
			LOG.debug("Entered into getInfuelcePeopleAndCadreDetails service method");
			VoterVO voterVO = null;
			
			try
			{
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
			}catch(Exception e)
			{
				LOG.error("Exception raised in getInfuelcePeopleAndCadreDetails service method");
				e.printStackTrace();
			}
		}
		
		/**
		 * This method will set the caste and party details of voters
		 * @param voters
		 * @param categories
		 * @param voterMap
		 */
		public void getCastePartyAndCategoriesDetails(List<Object[]> voters,List<Long> categories, Map<Long , VoterVO> voterMap)
		{
			LOG.debug("Exception raised in getCastePartyAndCategoriesDetails method");

			try
			{
				if(voters != null && voters.size() > 0)
				{
						for (Object[] voterDetails : voters) {
							
							VoterVO voterDtls = voterMap.get(Long.parseLong(voterDetails[0].toString()));
							
							if(voterDtls != null)
							{
								voterDtls.setCasteName(voterDetails[9] != null ? voterDetails[9].toString() :"");
								voterDtls.setPartyName(voterDetails[10] != null ? voterDetails[10].toString() :"");
								
							}
							for(int i=13;i<categories.size()*2+12;i = i+2)
							{
								if(voterDetails[i] != null && voterDetails[i+1] != null)
								{
									VoterVO category = null;
									 if(voterDtls != null){
							    		 List<VoterVO> categoriesList = voterDtls.getCategoriesList();
							    		 if(categoriesList == null){
							    			 categoriesList = new ArrayList<VoterVO>();
							    			 voterDtls.setCategoriesList(categoriesList);
							    		 }
							    		  category = new VoterVO();
							    		  categoriesList.add(category);
							    		  category.setCategoryValuesId(Long.parseLong(voterDetails[i].toString()));
							    		  category.setName(voterDetails[i+1]!=null?voterDetails[i+1].toString():"");
							    	 }
								}
							}
						}
					}
				
			}
			catch(Exception e)
			{
				LOG.error("Exception raised in getCastePartyAndCategoriesDetails method");
				e.printStackTrace();
			}
		}
		
		/**
		 * This method will prepare the query for categories selected by the user to display
		 * @param categories
		 * @param queryForCategories
		 * @param queryForselect
		 */
		public void prepareQueryForCategories(List<Long> categories,StringBuffer queryForCategories,StringBuffer queryForselect )
		{
			LOG.debug("Entered into the prepareQueryForCategories service method");
			try
			{
				for(Long categoryId:categories)
				{
					queryForCategories.append("LEFT JOIN (voter_category_value vcu"+categoryId+" JOIN user_voter_category_value uvcu"+categoryId+" on uvcu"+categoryId+".user_voter_category_value_id = vcu"+categoryId+".user_voter_category_value_id and uvcu"+categoryId+".user_voter_category_id = "+categoryId+")on v.voter_id = vcu"+categoryId+".voter_id  ");
					queryForselect.append(" , uvcu"+categoryId+".user_voter_category_id as category"+categoryId+",uvcu"+categoryId+".category_value as value"+categoryId+" ");
				}
				
			}catch(Exception e)
			{
				LOG.error("Exception raised in prepareQueryForCategories service method");
				e.printStackTrace();
			}
		}
		
		
		/**
		 * This method will prepare the query to get the search results.
		 * @param searchColumn
		 * @param searchString
		 * @return
		 */
		public StringBuffer prepareQueryForSearchCriteria(String searchColumn ,String searchString )
		{
			LOG.debug("Entered into the prepareQueryForSearchCriteria service method");
			StringBuffer str = new StringBuffer();

			try
			{
				List<String> voterSearchCriteria = new ArrayList<String>();
				
				voterSearchCriteria.add("name");
				voterSearchCriteria.add("voter_id_card_no");
				voterSearchCriteria.add("house_no");
				voterSearchCriteria.add("relative_name");
				voterSearchCriteria.add("gender");
				//voterSearchCriteria.add("age");
				
				if(searchColumn != null && !searchColumn.equalsIgnoreCase(""))
				if(voterSearchCriteria.contains(searchColumn))
					str.append("and v."+searchColumn+" like '%"+searchString+"%'");	
				else if(searchColumn.equalsIgnoreCase("serial_no"))
					str.append("and bpv.serial_no ="+searchString);
				else if(searchColumn.equalsIgnoreCase("part_no"))
					str.append("and b.part_no ="+searchString);
				else if(searchColumn.equalsIgnoreCase("party"))
					str.append("and pa.short_name like '%"+searchString+"%'");
				else if(searchColumn.equalsIgnoreCase("cast"))
					str.append("and c.caste_name like '%"+searchString+"%'");
				else if(searchColumn.equalsIgnoreCase("age"))
					str.append("and v.age = "+searchString);
				else 
					str.append("and uvcu"+searchColumn+".category_value"+"='"+searchString+"'");
				
			}catch(Exception e)
			{
				LOG.error("Exception raised in prepareQueryForSearchCriteria service method");
				e.printStackTrace();
			}
			return str;
		}
		
		/**
	       * This method is used to get voter details of a panchayat
	       * @param voterDataVO 
	       * @param userId
	       * @param categories
	       * @param searchColumn
	       * @param searchString
	       * @return List<VoterVO>
	       */
		  public List<VoterVO> getVoterDataForHamlet(VoterDataVO voterDataVO , Long userId , List<Long> categories ,String searchColumn,String searchString)
			{
				LOG.debug("Enterd into the getVoterDataForHamlet service method ");

				List<VoterVO> voterData = new ArrayList<VoterVO>();
				List<Object[]> voters = null;
				VoterVO voterVO = null;
				List<Long> voterIds = new ArrayList<Long>();
				Long totalCount = 0l;
				Map<Long , VoterVO> voterMap = new HashMap<Long, VoterVO>();
				try {
					
					    StringBuffer str = prepareQueryForSearchCriteria(searchColumn , searchString);
						
						StringBuffer queryForCategories = new StringBuffer();
						StringBuffer queryForselect = new StringBuffer();
						
					   // prepareQueryForCategories(categories, queryForCategories, queryForselect);
					    
					    for(Long categoryId:categories)
						{
							queryForCategories.append("LEFT JOIN (voter_category_value vcu"+categoryId+" JOIN user_voter_category_value uvcu"+categoryId+" on uvcu"+categoryId+".user_voter_category_value_id = vcu"+categoryId+".user_voter_category_value_id and uvcu"+categoryId+".user_voter_category_id = "+categoryId+")on uvd.voter_id = vcu"+categoryId+".voter_id  ");
							queryForselect.append(" , uvcu"+categoryId+".user_voter_category_id as category"+categoryId+",uvcu"+categoryId+".category_value as value"+categoryId+" ");
						}
					
						voters = boothPublicationVoterDAO.getVotersDetailsAnCountDetailsForHamletByPublicationId(userId,voterDataVO.getId() , voterDataVO.getPublicationId() ,voterDataVO.getStartIndex().intValue(), voterDataVO.getMaxIndex().intValue() , voterDataVO.getDir(),voterDataVO.getSort() ,str.toString(),queryForCategories.toString(),queryForselect.toString(),false);
						List countList = boothPublicationVoterDAO.getVotersDetailsAnCountDetailsForHamletByPublicationId(userId,voterDataVO.getId(), voterDataVO.getPublicationId() ,voterDataVO.getStartIndex().intValue(), voterDataVO.getMaxIndex().intValue() , voterDataVO.getDir(),voterDataVO.getSort() ,str.toString(),queryForCategories.toString(),queryForselect.toString(),true);
					
						totalCount = ((BigInteger)countList.get(0)).longValue();
						//totalCount = 316L;
					
					if(voters != null && voters.size() > 0)
					{
						for (Object[] voterDetails : voters) {
							
							voterVO = new VoterVO();
							voterVO.setVoterIds(Long.parseLong(voterDetails[0].toString()));
							voterVO.setVoterId(voterDetails[1].toString());
							voterVO.setName(voterDetails[2].toString());
							voterVO.setHouseNo(voterDetails[3].toString());
							voterVO.setRelativeFirstName(voterDetails[4].toString());
							voterVO.setGender(voterDetails[5].toString());
							voterVO.setAge(Long.parseLong(voterDetails[6].toString()));
							voterVO.setPartNo(Long.valueOf(voterDetails[7].toString()));
							voterVO.setSerialNo(Long.parseLong(voterDetails[11].toString()));

							if(voterDetails[12] != null)
							   voterVO.setMobileNo(voterDetails[12].toString());
							else
								voterVO.setMobileNo("N/A");
							
							voterVO.setTotalVoters(totalCount);
							voterIds.add(Long.parseLong(voterDetails[0].toString()));
							voterMap.put(Long.parseLong(voterDetails[0].toString()), voterVO);
							voterData.add(voterVO);
						}
					}
					
					if(voterIds != null && voterIds.size() >0)
					 getInfuelcePeopleAndCadreDetails(voterIds,userId,voterMap);
					
					getCastePartyAndCategoriesDetails(voters, categories, voterMap);
					
				} catch (Exception e) {
					LOG.error("Exception raised in  getVoterDataForHamlet service method " , e) ;
					e.printStackTrace();
				}
				return voterData;
			}
		  
		  public List<VoterVO> getVoterDataForWard(VoterDataVO voterDataVO , Long userId , List<Long> categories ,String searchColumn,String searchString)
			{
				LOG.debug("Enterd into the getVoterDataForWard service method ");

				List<VoterVO> voterData = new ArrayList<VoterVO>();
				List<Object[]> voters = null;
				VoterVO voterVO = null;
				List<Long> voterIds = new ArrayList<Long>();
				Long totalCount = 0l;
				Map<Long , VoterVO> voterMap = new HashMap<Long, VoterVO>();
				try {
					
					    StringBuffer str = prepareQueryForSearchCriteria(searchColumn , searchString);
						
						StringBuffer queryForCategories = new StringBuffer();
						StringBuffer queryForselect = new StringBuffer();
						
					   // prepareQueryForCategories(categories, queryForCategories, queryForselect);
					    
					    for(Long categoryId:categories)
						{
							queryForCategories.append(" LEFT JOIN (voter_category_value vcu"+categoryId+" JOIN user_voter_category_value uvcu"+categoryId+" on uvcu"+categoryId+".user_voter_category_value_id = vcu"+categoryId+".user_voter_category_value_id and uvcu"+categoryId+".user_voter_category_id = "+categoryId+")on uvd.voter_id = vcu"+categoryId+".voter_id  ");
							queryForselect.append(" , uvcu"+categoryId+".user_voter_category_id as category"+categoryId+",uvcu"+categoryId+".category_value as value"+categoryId+" ");
						}
					
						voters = boothPublicationVoterDAO.getVotersDetailsAnCountDetailsForWardByPublicationId(voterDataVO.getId(),userId,voterDataVO.getConstituencyId() , voterDataVO.getPublicationId() ,voterDataVO.getStartIndex().intValue(), voterDataVO.getMaxIndex().intValue() , voterDataVO.getDir(),voterDataVO.getSort() ,str.toString(),queryForCategories.toString(),queryForselect.toString(),false);
						List countList = boothPublicationVoterDAO.getVotersDetailsAnCountDetailsForWardByPublicationId(voterDataVO.getId(),userId,voterDataVO.getConstituencyId(), voterDataVO.getPublicationId() ,voterDataVO.getStartIndex().intValue(), voterDataVO.getMaxIndex().intValue() , voterDataVO.getDir(),voterDataVO.getSort() ,str.toString(),queryForCategories.toString(),queryForselect.toString(),true);
					
						totalCount = ((BigInteger)countList.get(0)).longValue();
						//totalCount = 316L;
					
					if(voters != null && voters.size() > 0)
					{
						for (Object[] voterDetails : voters) {
							
							voterVO = new VoterVO();
							voterVO.setVoterIds(Long.parseLong(voterDetails[0].toString()));
							voterVO.setVoterId(voterDetails[1].toString());
							voterVO.setName(voterDetails[2].toString());
							voterVO.setHouseNo(voterDetails[3].toString());
							voterVO.setRelativeFirstName(voterDetails[4].toString());
							voterVO.setGender(voterDetails[5].toString());
							voterVO.setAge(Long.parseLong(voterDetails[6].toString()));
							voterVO.setPartNo(Long.valueOf(voterDetails[7].toString()));
							voterVO.setSerialNo(Long.parseLong(voterDetails[11].toString()));

							if(voterDetails[12] != null)
							   voterVO.setMobileNo(voterDetails[12].toString());
							else
								voterVO.setMobileNo("N/A");
							
							voterVO.setTotalVoters(totalCount);
							voterIds.add(Long.parseLong(voterDetails[0].toString()));
							voterMap.put(Long.parseLong(voterDetails[0].toString()), voterVO);
							voterData.add(voterVO);
						}
					}
					
					if(voterIds != null && voterIds.size() >0)
					 getInfuelcePeopleAndCadreDetails(voterIds,userId,voterMap);
					
					getCastePartyAndCategoriesDetails(voters, categories, voterMap);
					
				} catch (Exception e) {
					LOG.error("Exception raised in  getVoterDataForWard service method " , e) ;
					e.printStackTrace();
				}
				return voterData;
			}
		  
		  public List<VotersDetailsVO> caluculateAgeWiseDetailsForPanchayatByHamlets(Long userId , List<Long> panchayatIds ,Long publicationDateId,boolean isSublevel)
		  {
		 	 LOG.debug("Entered into the caluculateAgeWiseDetailsForPanchayatByHamlets service method");
		 	 
		 	 List<VotersDetailsVO> votersDetailsVOList = null;
		 	 
		 	 try
		 	 {
		 		List<Object[]> list =  boothPublicationVoterDAO.getPanchayatAgeWiseDetailsByHamletWise(userId, publicationDateId, panchayatIds);
		 		
		 		if(!isSublevel)
		 		{
		 		if(list != null && list.size() >0)
		 		{
		 			Object[] obj = list.get(0);
		 			
		 			Long totalVoters =  Long.parseLong(obj[30].toString()) ;
		 			
		 			if(totalVoters == 0L)
		 				return null;
		 					
		 			
		 			votersDetailsVOList = new ArrayList<VotersDetailsVO>();
		 			
		 			 VotersDetailsVO youngVoters = new VotersDetailsVO();
			 			
		 			youngVoters.setAgeRange(IConstants.YOUNG_VOTERS);
		 			youngVoters.setTotalVoters((Long)obj[33]);
		 			youngVoters.setTotalVotersPercent(Long.parseLong(obj[33].toString())*100f/totalVoters);
		 			youngVoters.setTotalMaleVoters((Long)obj[35]);
		 			youngVoters.setTotalMaleVotersPercent(((Long)obj[35] != 0L && (Long)obj[37] != 0L)|| (Long)obj[35] != 0L ?(float)(Long)obj[35]*100f/((Long)obj[35] +(Long)obj[37]):0.00f);
		 			youngVoters.setTotalFemaleVoters((Long)obj[37]);
		 			youngVoters.setTotalFemaleVotersPercent(((Long)obj[35] != 0L && (Long)obj[37] != 0L)|| (Long)obj[37] != 0L ?(float)(Long)obj[37]*100f/((Long)obj[35] +(Long)obj[37]):0.00f);
		 			//youngVoters.setTotalVoters(totalVoters);
		             
		 			
		 			VotersDetailsVO age18to22Details = new VotersDetailsVO();
		 			
		 			age18to22Details.setAgeRange(IConstants.AGE18to22);
		 			age18to22Details.setTotalVoters((Long)obj[0]);
		 			age18to22Details.setTotalVotersPercent((float)(Long)obj[0]*100f/totalVoters);
		 			age18to22Details.setTotalMaleVoters((Long)obj[2]);
		 			age18to22Details.setTotalMaleVotersPercent(((Long)obj[2] != 0L && (Long)obj[4] != 0L)||(Long)obj[2] != 0L ?(float)(Long)obj[2]*100f/((Long)obj[2] + (Long)obj[4]):0.00f);
		 			age18to22Details.setTotalFemaleVoters((Long)obj[4]);
		 			age18to22Details.setTotalFemaleVotersPercent(((Long)obj[4] != 0L && (Long)obj[2] != 0L)||(Long)obj[4] != 0L ?(float)(Long)obj[4]*100f/((Long)obj[2] + (Long)obj[4]):0.00f);
		 			//age18to22Details.setTotalVoters(totalVoters);
		             
		 			
		 			
		             VotersDetailsVO age23To30Details = new VotersDetailsVO();
		 			
		             age23To30Details.setAgeRange(IConstants.AGE23to30);
		             age23To30Details.setTotalVoters((Long)obj[6]);
		             age23To30Details.setTotalVotersPercent((float)Long.parseLong(obj[6].toString())*100f/totalVoters);
		             age23To30Details.setTotalMaleVoters((Long)obj[8]);
		             age23To30Details.setTotalMaleVotersPercent(((Long)obj[8] != 0L && (Long)obj[10] != 0L)||(Long)obj[8] != 0L ?(float)(Long)obj[8]*100f/((Long)obj[8] + (Long)obj[10] ):0.00f);
		             age23To30Details.setTotalFemaleVoters((Long)obj[10]);
		             age23To30Details.setTotalFemaleVotersPercent(((Long)obj[8] != 0L && (Long)obj[10] != 0L)||(Long)obj[10] != 0L ?(float)(Long)obj[10]*100f/((Long)obj[8] + (Long)obj[10] ):0.00f);
		             //age23To30Details.setTotalVoters(totalVoters);
		             
		             VotersDetailsVO age31To45Details = new VotersDetailsVO();
		 			
		             age31To45Details.setAgeRange(IConstants.AGE31to45);
		             age31To45Details.setTotalVoters((Long)obj[12]);
		             age31To45Details.setTotalVotersPercent((float)Long.parseLong(obj[12].toString())*100f/totalVoters);
		             age31To45Details.setTotalMaleVoters((Long)obj[14]);
		             age31To45Details.setTotalMaleVotersPercent(((Long)obj[14] != 0L && (Long)obj[16] != 0L)|| (Long)obj[14] !=0L ?(float)(Long)obj[14]*100f/((Long)obj[14]+(Long)obj[16]):0.00f);
		             age31To45Details.setTotalFemaleVoters((Long)obj[16]);
		             age31To45Details.setTotalFemaleVotersPercent(((Long)obj[14] != 0L && (Long)obj[16] != 0L)|| (Long)obj[16] !=0L ?(float)(Long)obj[16]*100f/((Long)obj[14]+(Long)obj[16]):0.00f);
		            // age31To45Details.setTotalVoters(totalVoters);
		             
		             VotersDetailsVO age46To60Details = new VotersDetailsVO();
		 			
		             age46To60Details.setAgeRange(IConstants.AGE46to60);
		             age46To60Details.setTotalVoters((Long)obj[18]);
		             age46To60Details.setTotalVotersPercent(Long.parseLong(obj[18].toString())!=0L ?(float)Long.parseLong(obj[18].toString())*100f/totalVoters:0.00F);
		             age46To60Details.setTotalMaleVoters((Long)obj[20]);
		             age46To60Details.setTotalMaleVotersPercent(((Long)obj[20] != 0L && (Long)obj[22] != 0L)|| (Long)obj[20] != 0L ?(float)(Long)obj[20]*100f/((Long)obj[20]+(Long)obj[22]):0.00f);
		             age46To60Details.setTotalFemaleVoters((Long)obj[22]);
		             age46To60Details.setTotalFemaleVotersPercent(((Long)obj[20] != 0L && (Long)obj[22] != 0L)|| (Long)obj[22] != 0L ?(float)(Long)obj[22]*100f/((Long)obj[20]+(Long)obj[22]):0.00f);
		            // age46To60Details.setTotalVoters(totalVoters);

		 			
		             VotersDetailsVO above60Details = new VotersDetailsVO();
		 			
		             above60Details.setAgeRange("60-Above");
		             above60Details.setTotalVoters((Long)obj[24]);
		             above60Details.setTotalVotersPercent(Long.parseLong(obj[24].toString())*100f/totalVoters);
		             above60Details.setTotalMaleVoters((Long)obj[26]);
		             above60Details.setTotalMaleVotersPercent(((Long)obj[26] != 0L && (Long)obj[28] != 0L)|| (Long)obj[26] != 0L ? (float)(Long)obj[26]*100f/((Long)obj[26] +(Long)obj[28]):0.00f);
		             above60Details.setTotalFemaleVoters((Long)obj[28]);
		             above60Details.setTotalFemaleVotersPercent(((Long)obj[26] != 0L && (Long)obj[28] != 0L)|| (Long)obj[28] != 0L ?(float)(Long)obj[28]*100f/((Long)obj[26] +(Long)obj[28]):0.00f);
		            // above60Details.setTotalVoters(totalVoters);

		             
		             votersDetailsVOList.add(age18to22Details);
		             votersDetailsVOList.add(age23To30Details);
		             votersDetailsVOList.add(age31To45Details);
		             votersDetailsVOList.add(age46To60Details);
		             votersDetailsVOList.add(above60Details);
		             votersDetailsVOList.add(youngVoters);
		 			
		 		}
		 		}
		 		else
		 		{
		 			votersDetailsVOList = new ArrayList<VotersDetailsVO>();

		 		
	 			for(Object[] obj:list)
	 			{
	 				Long totalVoters =  Long.parseLong(obj[30].toString()) ;
	 			
		 			if(totalVoters == 0L)
		 				return null;
	 				
	 				VotersDetailsVO votersDetailsVO =new VotersDetailsVO();
	 				
	 			    votersDetailsVO.setTotalVoters(0l);
	 			    votersDetailsVO.setId((Long)obj[31]);
	 			    votersDetailsVO.setAreaType(IConstants.PANCHAYAT);
	 			  
	 		        votersDetailsVO.setPanchayatname(obj[32].toString());
	 				 
	 				
	 				votersDetailsVO.setTotalMaleVotesFor18To25((Long)obj[2]);
	 				votersDetailsVO.setMaleVotersPercentFor18To25(((Long)obj[2] != 0L && (Long)obj[4] != 0L)||(Long)obj[2] != 0L ? roundTo2DigitsFloatValue((float)(Long)obj[2]*100f/((Long)obj[2] + (Long)obj[4])):"0.00");
	 				votersDetailsVO.setTotalFemaleVotersFor18To25((Long)obj[4]);
	 				votersDetailsVO.setFemaleVotersPercentFor18To25(((Long)obj[2] != 0L && (Long)obj[4] != 0L)||(Long)obj[4] != 0L ?roundTo2DigitsFloatValue((float)(Long)obj[4]*100f/((Long)obj[2] + (Long)obj[4])):"0.00");
	 				votersDetailsVO.setTotalVotersFor18To25((Long)obj[0]);
	 				votersDetailsVO.setVotersPercentFor18To25((Long)obj[0] != 0L ? roundTo2DigitsFloatValue((float)(Long)obj[0]*100f/totalVoters):"0.00");
	 				
	 				votersDetailsVO.setTotalMaleVotersFor26To35((Long)obj[8]);
	 				votersDetailsVO.setMaleVotersPercentFor26To35(((Long)obj[8] != 0L && (Long)obj[10] != 0L)||(Long)obj[8] != 0L ? roundTo2DigitsFloatValue((float)(Long)obj[8]*100f/((Long)obj[8] + (Long)obj[10] )):"0.00");
	 				votersDetailsVO.setTotalFemaleVotersFor26To35((Long)obj[10]);
	 				votersDetailsVO.setFemaleVotersPercentFor26To35(((Long)obj[8] != 0L && (Long)obj[10] != 0L)||(Long)obj[10] != 0L ?roundTo2DigitsFloatValue((float)(Long)obj[10]*100f/((Long)obj[8] + (Long)obj[10] )):"0.00");
	 				votersDetailsVO.setTotalVotersFor26To35((Long)obj[6]);
	 				votersDetailsVO.setVotersPercentFor26To35(Long.parseLong(obj[7].toString()) != 0L ?roundTo2DigitsFloatValue((float)Long.parseLong(obj[7].toString())*100f/totalVoters):"0.00");
	 				
	 				votersDetailsVO.setTotalMaleVotersFor36To45((Long)obj[14]);
	 				votersDetailsVO.setMaleVotersPercentFor36To45(((Long)obj[14] != 0L && (Long)obj[16] != 0L)||(Long)obj[14] != 0L ? roundTo2DigitsFloatValue((float)(Long)obj[14]*100f/((Long)obj[14]+(Long)obj[16])):"0.00");
	 				votersDetailsVO.setTotalFemaleVotersFor36To45((Long)obj[16]);
	 				votersDetailsVO.setFemaleVotersPercentFor36To45(((Long)obj[14] != 0L && (Long)obj[16] != 0L)||(Long)obj[16] != 0L ? roundTo2DigitsFloatValue((float)(Long)obj[16]*100f/((Long)obj[14]+(Long)obj[16])):"0.00");
	 				votersDetailsVO.setTotalVotersFor36To45((Long)obj[12]);					 
	 				votersDetailsVO.setVotersPercentFor36To45(Long.parseLong(obj[13].toString()) != 0L ? roundTo2DigitsFloatValue((float)Long.parseLong(obj[13].toString())*100f/totalVoters):"0.00");
	 				
	 				votersDetailsVO.setTotalMaleVotersFor46To60((Long)obj[20]);
	 				votersDetailsVO.setMaleVotersPercentFor46To60(((Long)obj[20] != 0L && (Long)obj[20] != 0L)||(Long)obj[20] != 0L ? roundTo2DigitsFloatValue((float)(Long)obj[20]*100f/((Long)obj[20]+(Long)obj[22])):"0.00");
	 				votersDetailsVO.setTotalFemaleVotersFor46To60((Long)obj[22]);						
	 				votersDetailsVO.setFemaleVotersPercentFor46To60(((Long)obj[20] != 0L && (Long)obj[20] != 0L)||(Long)obj[22] != 0L ? roundTo2DigitsFloatValue((float)(Long)obj[22]*100f/((Long)obj[20]+(Long)obj[22])):"0.00");
	 				votersDetailsVO.setTotalVotersFor46To60((Long)obj[18]);
	 			    votersDetailsVO.setVotersPercentFor46To60(Long.parseLong(obj[18].toString()) != 0L ? roundTo2DigitsFloatValue((float)Long.parseLong(obj[18].toString())*100f/totalVoters):"0.00");
	 			
	 				
	 				votersDetailsVO.setTotalMaleVotersForAbove60((Long)obj[26]);
	 				votersDetailsVO.setMaleVotersPercentForAbove60(((Long)obj[26]  != 0L && (Long)obj[28] != 0L )||(Long)obj[26] != 0L ? roundTo2DigitsFloatValue((float)(Long)obj[26]*100f/((Long)obj[26] +(Long)obj[28])):"0.00");
	 				votersDetailsVO.setTotalFemaleVotersForAbove60((Long)obj[28]);
	 				votersDetailsVO.setFemaleVotersPercentForAbove60(((Long)obj[26]  != 0L && (Long)obj[28] != 0L )||(Long)obj[28] != 0L ?roundTo2DigitsFloatValue((float)(Long)obj[28]*100f/((Long)obj[26] +(Long)obj[28])):"0.00");
	 				votersDetailsVO.setTotalVotersForAbove60((Long)obj[24]);
	 				votersDetailsVO.setVotersPercentForAbove60(Long.parseLong(obj[24].toString()) != 0L ?roundTo2DigitsFloatValue(Long.parseLong(obj[24].toString())*100f/totalVoters):"0.00");
	 				votersDetailsVO.setTotalVoters(totalVoters);
	 				
	 				
	 				votersDetailsVO.setMaleVotersCountForYoungerVoters((Long)obj[35]);
	 				votersDetailsVO.setMaleVotersPercentForYoungerVoters((Long)obj[35] != null ? roundTo2DigitsFloatValue((float)(Long)obj[35]*100f/((Long)obj[35] +(Long)obj[37])):"0.00");
	 				votersDetailsVO.setFemaleVotersCountForYoungerVoters((Long)obj[37]);
	 				votersDetailsVO.setFemaleVotersPercentForYoungerVoters((Long)obj[37] != null ? roundTo2DigitsFloatValue((float)(Long)obj[37]*100f/((Long)obj[35] +(Long)obj[37])):"0.00");
	 				votersDetailsVO.setTotalVotersForYoungerVoters((Long)obj[33]);
	 				votersDetailsVO.setVotersPercentForYoungerVoters(Long.parseLong(obj[33].toString()) != 0L ?roundTo2DigitsFloatValue(Long.parseLong(obj[33].toString())*100f/totalVoters):"0.00");
	 				
	 				
	 				votersDetailsVOList.add(votersDetailsVO);
	 				
	 				
	 			}
		 		}
		 		
		 	
		 		 
		 	 }catch(Exception e)
		 	 {
		 		 LOG.error("Exception raised in caluculateAgeWiseDetailsForPanchayatByHamlets service method");
		 		 e.printStackTrace();		 
		 	 }
		 	 
		 	 return votersDetailsVOList;
		  }
		  
		  public void getPanchayatsWithPartialBooths(Long publicationDateId,Long constituencyId,Set<Long> panchayatIds){
			  List<Long> boothIds = new ArrayList<Long>();
			  List<Object[]> partialPanchayats =  boothDAO.getPartialPanchayats(publicationDateId,constituencyId);
			  
			  if(partialPanchayats != null && partialPanchayats.size() > 0){
				  for(Object[] data : partialPanchayats){
					  panchayatIds.add((Long)data[0]);
					  boothIds.add((Long)data[1]);
				  }
			  }
			  
			  if(boothIds.size() > 0 && panchayatIds.size() > 0){
				  List<Long> newPanchayatIds = partialBoothPanchayatDAO.getPartialPanchayatIds(boothIds, panchayatIds);
				  if(newPanchayatIds != null && newPanchayatIds.size() > 0){
					  panchayatIds.addAll(newPanchayatIds);
				  }
			  }
		  }
		 
		  public void populateDataToVoterCastBasicInfo(VoterReportLevel voterReportLevel,Long id,Long userId,Long publicationDateId,Long constituencyId,Constituency constituency,Map<Long,VoterCastBasicInfo> casteBasicInfoMap){
			  VoterCastBasicInfo voterCastBasicInfo = null;
			  Long totalVoters = voterInfoDAO.getVotersCountInALocation(voterReportLevel.getVoterReportLevelId(),id,publicationDateId,constituencyId);
			  if(totalVoters == null)
			  	totalVoters = 0l;
			  voterCastBasicInfo = new VoterCastBasicInfo();
			  voterCastBasicInfo.setVoterReportLevel(voterReportLevel);
			  voterCastBasicInfo.setReportLevelValue(id);
			  voterCastBasicInfo.setUserId(userId);
			  voterCastBasicInfo.setPublicationDateId(publicationDateId);
			  voterCastBasicInfo.setConstituency(constituency);
			  voterCastBasicInfo.setOcVoters(0l);
			  voterCastBasicInfo.setBcVoters(0l);
			  voterCastBasicInfo.setScVoters(0l);
			  voterCastBasicInfo.setStVoters(0l);
			  voterCastBasicInfo.setTotalCastes(0l);
			  voterCastBasicInfo.setCasteNotAssignedVoters(totalVoters);
			  voterCastBasicInfo.setCasteAssignedVoters(0l);
			  casteBasicInfoMap.put(id,voterCastBasicInfo);
		  }
		  
		  public List<VotersInfoForMandalVO> getDataForPartialPanchayats(Long constituencyId,Long mandalId,Map<Long,String> panchayatIds,Long publicationDateId,Long userId){
			 Map<Long,VotersInfoForMandalVO> dataMap = new HashMap<Long,VotersInfoForMandalVO>();
			 try{ 
			  List<Object[]> list = boothPublicationVoterDAO.getVotersCountForMultiplePartialPanchayats(panchayatIds.keySet(), publicationDateId, userId);
			  Long totalMandalVoters = voterInfoDAO.getVotersCountInALocation(2l, mandalId, publicationDateId, constituencyId);
			  
			  for(Object[] voterDetails : list){  
				   VotersInfoForMandalVO  vo = dataMap.get((Long)voterDetails[2]);
				   if(vo == null){
					   vo = new VotersInfoForMandalVO();
					   vo.setType("Panchayat");
					   vo.setName(panchayatIds.get((Long)voterDetails[2]));
					   vo.setId((Long)voterDetails[2]);
					   vo.setPercent("0.00");
					   vo.setTotPercent(new BigDecimal("0.00") );
						
					   dataMap.put((Long)voterDetails[2],vo);
				   }
                  if(voterDetails[1].toString().trim().equalsIgnoreCase("M") || voterDetails[1].toString().trim().equalsIgnoreCase("Male")){
				     vo.setTotalMaleVoters(voterDetails[0].toString());
				     vo.setMaleVoters((Long)voterDetails[0]);
                  }else if(voterDetails[1].toString().trim().equalsIgnoreCase("F") || voterDetails[1].toString().trim().equalsIgnoreCase("Female")){
				   vo.setTotalFemaleVoters(voterDetails[0].toString());
				   vo.setFemaleVoters((Long)voterDetails[0]);
                  }
			  }
			  for(VotersInfoForMandalVO vo:dataMap.values()){
				  
				  if(vo.getMaleVoters() != null && vo.getFemaleVoters() != null){
					  vo.setTotVoters(new BigDecimal(vo.getMaleVoters()+vo.getFemaleVoters()));
					  vo.setTotalVotersDiff(vo.getMaleVoters()+vo.getFemaleVoters());
					  vo.setTotalVoters(vo.getTotalVotersDiff().toString());
				  }else if(vo.getMaleVoters() != null){
					  vo.setTotVoters(new BigDecimal(vo.getMaleVoters()));
					  vo.setTotalVotersDiff(vo.getMaleVoters());
					  vo.setTotalVoters(vo.getTotalVotersDiff().toString());
				  }else{
					  vo.setTotVoters(new BigDecimal(vo.getFemaleVoters()));
					  vo.setTotalVotersDiff(vo.getFemaleVoters());
					  vo.setTotalVoters(vo.getTotalVotersDiff().toString());
				  }
				  if(totalMandalVoters != null && vo.getTotVoters() != null && totalMandalVoters > 0){
					  vo.setPercent(new BigDecimal(vo.getTotalVotersDiff()*(100.0)/totalMandalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					  vo.setTotPercent(new BigDecimal(vo.getTotalVotersDiff()*(100.0)/totalMandalVoters).setScale(2, BigDecimal.ROUND_HALF_UP));
				 
				  }
			  }
			 }catch(Exception e){
				 LOG.error("Exception raised in  getDataForPartialPanchayats service method " , e) ;
			 }
			  return new ArrayList<VotersInfoForMandalVO>(dataMap.values());
		  }
		  
		  public void getPartialAndNormalPanchayats(Long publicationDateId,Long id,Map<Long,String> panchayatIds,Map<Long,String> partialPancMap){
			  List<Object[]> panchayatiesList = panchayatDAO.getPanchayatsBymandalId(new Long(id.toString().substring(01)));
				
				
				for (Object[] panchayat : panchayatiesList){
					panchayatIds.put((Long)panchayat[0], panchayat[1]!= null?panchayat[1].toString():"");
				}
				if(panchayatIds.size() > 0){
				  List<Long> partialPancIds = partialBoothPanchayatDAO.getPartialPanchayats(publicationDateId,panchayatIds.keySet());
				  for(Long partialPanc:partialPancIds){
					  partialPancMap.put(partialPanc, panchayatIds.get(partialPanc));
					  panchayatIds.remove(partialPanc);
				  }
				}
		  }
		  
		  public ResultStatus saveFlagDetails(String name,String desc,String color,Long userId,Long flagId)
		  {
			  ResultStatus resultStatus = new ResultStatus();
			  try{
				  if(flagId == 0)
				  {
				  List<Object> flagName = flagDAO.checkFlagName(name);
				  if(flagName != null && flagName.size() > 0 )
				  {
					  resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
					  return resultStatus;
				  }
				  }
				     Flag flag = null; 
					  if(flagId == 0)//create Flag
					 flag = new Flag();
					  else
					 flag = flagDAO.get(flagId);//update Flag
					 flag.setName(name);
					 flag.setDescription(desc);
					 if(color.matches(".*\\d+.*"))
					 flag.setColor("#"+color);
					 else
						 flag.setColor(color); 
					 flag.setUser(userDAO.get(userId));
					 flagDAO.save(flag);
					  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			
			  }
			  catch (Exception e) {
				  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			}
			return resultStatus;
		  }
		  
		  public List<VoterVO> getAllFlags()
		  {
			  List<VoterVO> result = new ArrayList<VoterVO>();
			  try{
				List<Flag> flag = flagDAO.getAllFlags();  
				if(flag != null)
					for(Flag obj : flag)
					{
						VoterVO voterVO = new VoterVO();
						voterVO.setFirstName(obj.getName());
						voterVO.setDescription(obj.getDescription());
						if(obj.getColor().contains("#"))
						voterVO.setColor(obj.getColor().substring(1));	
						else
							voterVO.setColor(obj.getColor());	
						voterVO.setStatusId(obj.getFlagId());
						result.add(voterVO);
					}
			  }
			  catch (Exception e) {
				  LOG.error("Exception Occured in getAllFlags() method" , e);
			}
			return result;
		  }
		  
		  public ResultStatus deleteFlag(Long flagId)
		  {
			  ResultStatus resultStatus = new ResultStatus();
			  try{
				 voterFlagDAO.deleteVoterFlag(flagId);
				flagDAO.deleteFlag(flagId);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  }
			  catch (Exception e) {
				  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				  LOG.error("Exception Occured in deleteFlag() method" , e);
			}
			return resultStatus;
		  }		  
		  public void insertCasteDataForCustomWards(List<Long> localElectionBodyIds,Long userId,Long publicationDateId,Long constituencyId,Long reportLvlId,String type){
			try{  
			  for(Long localElectionBodyId:localElectionBodyIds){
				  Map<Long,Long> totalVotersCount = new HashMap<Long,Long>();//Map<wardId,totalVotersCount>
				  Map<Long,VoterCastInfoVO> casteBasicInfoMap = new HashMap<Long,VoterCastInfoVO>();//Map<wardId,>
				  Map<Long,Long> totalCastsAssignedCount = new HashMap<Long,Long>();//Map<wardId,casteAssignedCount>
				  Map<Long,Map<Long,VoterCastInfoVO>> casteInfoMap = new HashMap<Long,Map<Long,VoterCastInfoVO>>();//Map<wardId,Map<casteStateId,casteInfoVO>>
				  Map<Long,VoterCastInfoVO> casteStateMap = null;
				  VoterCastInfoVO vo = null;
				  List<Object[]>  totalVotersCountList = null;
				  List<Object[]>  casteGroupCountList = null;
				  List<Object[]>  genderWiseCasteCountList = null;
				  if(type.equalsIgnoreCase("ward")){
				  //getting total voters count in all wards
				    totalVotersCountList = boothPublicationVoterDAO.getCustomWardWiseTotalVotersCount(localElectionBodyId,userId,publicationDateId,constituencyId);
				  
				  //getting caste group wise voters count in all wards
				  // 0wardId 1categoryName 2count
				    casteGroupCountList =  boothPublicationVoterDAO.getCasteGroupContsByCustomWardWise(userId,localElectionBodyId,publicationDateId,constituencyId);
				  
				 //getting caste gender wise caste count in all wards
				 // 0wardId 1casteStateId 2gender 3count
				    genderWiseCasteCountList =  boothPublicationVoterDAO.getCasteWiseGenderWiseContsByCustomWardWise(userId,localElectionBodyId,publicationDateId,constituencyId);
				  }else{
					//getting total voters count in all wardbooths
					    totalVotersCountList = boothPublicationVoterDAO.getWardBoothWiseTotalVotersCount(localElectionBodyId,userId,publicationDateId,constituencyId);
					  
					  //getting caste group wise voters count in all wardbooths
					  // 0wardboothId 1categoryName 2count
					    casteGroupCountList =  boothPublicationVoterDAO.getCasteGroupContsByWardBoothWise(userId,localElectionBodyId,publicationDateId,constituencyId);
					  
					 //getting caste gender wise caste count in all wardbooths
					 // 0wardboothId 1casteStateId 2gender 3count
					    genderWiseCasteCountList =  boothPublicationVoterDAO.getCasteWiseGenderWiseContsByWardBoothWise(userId,localElectionBodyId,publicationDateId,constituencyId);
					  
				  }
                  for(Object[] count:totalVotersCountList){
                	  totalVotersCount.put((Long)count[1], (Long)count[0]);
                  }
                  for(Object[] genderWiseCount:genderWiseCasteCountList){
                	  casteStateMap = casteInfoMap.get((Long)genderWiseCount[0]);
                	  if(casteStateMap == null){
                		  casteStateMap = new HashMap<Long,VoterCastInfoVO>();
                		  casteInfoMap.put((Long)genderWiseCount[0],casteStateMap);
                		  totalCastsAssignedCount.put((Long)genderWiseCount[0], 0l);
                	  }
                	  vo = casteStateMap.get((Long)genderWiseCount[1]);
                	  if(vo == null){
                		  vo = new VoterCastInfoVO();
                		  casteStateMap.put((Long)genderWiseCount[1],vo);
                	  }
                	  vo.setTotalVoters(vo.getTotalVoters()+(Long)genderWiseCount[3]);
                	  totalCastsAssignedCount.put((Long)genderWiseCount[0], totalCastsAssignedCount.get((Long)genderWiseCount[0])+(Long)genderWiseCount[3]);
                	  String gender = genderWiseCount[2].toString();
                	  if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")){
                		  vo.setMaleVoters(vo.getMaleVoters()+(Long)genderWiseCount[3]);
                	  }else{
                		  vo.setFemaleVoters(vo.getFemaleVoters()+(Long)genderWiseCount[3]);
                	  }
                  }
                  
                  for(Object[] casteGroupCount:casteGroupCountList){
                	  vo = casteBasicInfoMap.get((Long)casteGroupCount[0]);
                	  if(vo == null){
                		  vo = new VoterCastInfoVO();
                		  if(casteInfoMap.get((Long)casteGroupCount[0]) != null)
                		  vo.setTotalCasts(casteInfoMap.get((Long)casteGroupCount[0]).size());
                		  if( totalCastsAssignedCount.get((Long)casteGroupCount[0]) != null ){
                			  vo.setCasteAssignedVoters(totalCastsAssignedCount.get((Long)casteGroupCount[0]));
                			  if(totalVotersCount.get((Long)casteGroupCount[0]) != null){
                			    vo.setCasteNotAssignedVoters(totalVotersCount.get((Long)casteGroupCount[0])-totalCastsAssignedCount.get((Long)casteGroupCount[0]));
                			  }
                		  }
                		  List<SelectOptionVO> castCategoryWiseVotersList = new ArrayList<SelectOptionVO>();
                		  castCategoryWiseVotersList.add(new SelectOptionVO());
                		  castCategoryWiseVotersList.add(new SelectOptionVO());
                		  castCategoryWiseVotersList.add(new SelectOptionVO());
                		  castCategoryWiseVotersList.add(new SelectOptionVO());
                		  vo.setCastCategoryWiseVotersList(castCategoryWiseVotersList);
                		  casteBasicInfoMap.put((Long)casteGroupCount[0], vo);
                	  }
                	  String casteGroup = casteGroupCount[1].toString();
                	  if(casteGroup.equalsIgnoreCase("OC")){
                		  vo.getCastCategoryWiseVotersList().get(0).setTotalCount((Long)casteGroupCount[2]);
                	  }else if(casteGroup.equalsIgnoreCase("BC")){
                		  vo.getCastCategoryWiseVotersList().get(1).setTotalCount((Long)casteGroupCount[2]);
                	  }else if(casteGroup.equalsIgnoreCase("SC")){
                		  vo.getCastCategoryWiseVotersList().get(2).setTotalCount((Long)casteGroupCount[2]);
                	  }else if(casteGroup.equalsIgnoreCase("ST")){
                		  vo.getCastCategoryWiseVotersList().get(3).setTotalCount((Long)casteGroupCount[2]);
                	  }
                  }
                  Constituency constituency = constituencyDAO.get(constituencyId);
                  VoterReportLevel level = voterReportLevelDAO.get(reportLvlId);
                  saveDataToVoterCastBasicInfo(casteBasicInfoMap,constituency,publicationDateId,level,userId);
                  saveVotersCasteInfo(casteInfoMap,totalCastsAssignedCount,constituency,publicationDateId,level,userId);
			  }
			}catch(Exception e){
				LOG.error("Exception raised in  insertCasteDataForCustomWards  method " , e) ;
			}
		  }
		  public void saveDataToVoterCastBasicInfo(Map<Long,VoterCastInfoVO> casteBasicInfoMap,Constituency constituency,Long publicationId,VoterReportLevel level,Long userId){
        	  Set<Long> wardIds = casteBasicInfoMap.keySet();
        	  VoterCastInfoVO vo = null;
			  for(Long wardId:wardIds){
				  VoterCastBasicInfo voterCastBasicInfo = new VoterCastBasicInfo();
				  voterCastBasicInfo.setVoterReportLevel(level);
				  voterCastBasicInfo.setReportLevelValue(wardId);
				  voterCastBasicInfo.setUserId(userId);
				  vo = casteBasicInfoMap.get(wardId);
				  if(vo != null){
					  voterCastBasicInfo.setTotalCastes(new Long(vo.getTotalCasts())) ;
					  voterCastBasicInfo.setCasteAssignedVoters(vo.getCasteAssignedVoters());
					  voterCastBasicInfo.setCasteNotAssignedVoters(vo.getCasteNotAssignedVoters());
					  if(vo.getCastCategoryWiseVotersList() != null){
						  voterCastBasicInfo.setOcVoters(vo.getCastCategoryWiseVotersList().get(0).getTotalCount());
						  voterCastBasicInfo.setBcVoters(vo.getCastCategoryWiseVotersList().get(1).getTotalCount());
						  voterCastBasicInfo.setScVoters(vo.getCastCategoryWiseVotersList().get(2).getTotalCount());
						  voterCastBasicInfo.setStVoters(vo.getCastCategoryWiseVotersList().get(3).getTotalCount());
						  voterCastBasicInfo.setPublicationDateId(publicationId);
						  voterCastBasicInfo.setConstituency(constituency);
						  voterCastBasicInfoDAO.save(voterCastBasicInfo);
					  }
				  }
        	  }
          }
		  
		  public void saveVotersCasteInfo(Map<Long,Map<Long,VoterCastInfoVO>> casteInfoMap,Map<Long,Long> totalCastsAssignedCount,Constituency constituency,Long publicationId,VoterReportLevel level,Long userId){
			  DecimalFormat df = new DecimalFormat("###.##");
			  Set<Long> wardIds = casteInfoMap.keySet();
			  VoterCastInfoVO vo = null;
			  Map<Long,VoterCastInfoVO> casteStateMap = null;
			  for(Long wardId:wardIds){
				  Long totalVoters = totalCastsAssignedCount.get(wardId);
				  casteStateMap = casteInfoMap.get(wardId);
				  Set<Long> casteStateIds = casteStateMap.keySet();
				  for(Long casteStateId:casteStateIds){
					  vo = casteStateMap.get(casteStateId);
					  VoterCastInfo voterCastInfo = new VoterCastInfo();
					  voterCastInfo.setVoterReportLevel(level);
					  voterCastInfo.setReportLevelValue(wardId);
					  voterCastInfo.setUserId(userId);
					  voterCastInfo.setCasteState(casteStateDAO.get(casteStateId));
					  voterCastInfo.setCasteVoters(vo.getTotalVoters());
					  voterCastInfo.setCasteMaleVoters(vo.getMaleVoters());
					  voterCastInfo.setCasteFemaleVoters(vo.getFemaleVoters());
					  voterCastInfo.setPublicationDateId(publicationId);
					  voterCastInfo.setConstituency(constituency);
					  if(totalVoters != null && totalVoters >0){
						  voterCastInfo.setCastePercentage(new Double(df.format(vo.getTotalVoters()*100/totalVoters.doubleValue())));
					  }
					  voterCastInfoDAO.save(voterCastInfo);
				  }
			  }
		  }
		  
		  public void saveVoterInfoForCustomWards(List<Long> localElecBodyIds,Long userId,Long publicationId,Long constituencyId,Long reportLevelId,String type){
			  DecimalFormat df = new DecimalFormat("###.##");
			  VoterReportLevel voterReportLevel = voterReportLevelDAO.get(reportLevelId);
			  PublicationDate publicationDate = publicationDateDAO.get(publicationId);
			  for(Long localElectionBodyId:localElecBodyIds){
				  List<Object[]> wardWiseVotersCountList = null;
				  Long count = null;
				  // 0 count 1wardId 2female 3male
			   if(type.equalsIgnoreCase("ward")){
				   // 0 count 1wardId 2female 3male
				   wardWiseVotersCountList = boothPublicationVoterDAO.getCustomWardWiseTotalMaleFemaleVotersCount(localElectionBodyId, userId, publicationId, constituencyId);
				    
				    List<Object[]> votersInfo = boothPublicationVoterDAO.findVotersGenderWiseCountByPublicationIdInALocation("localElectionBody",localElectionBodyId,publicationId,constituencyId);
				    count = 0l;
				    for(Object[] info:votersInfo){
				    	if(info[0] != null){
				    		count = count+(Long)info[0];
				    	}
				    }
			   }else{
				   // 0 count 1wardboothId 2female 3male
				   wardWiseVotersCountList = boothPublicationVoterDAO.getWardBoothTotalMaleFemaleVotersCount(localElectionBodyId, userId, publicationId, constituencyId);
			   }
			   for(Object[] wardVoterCount:wardWiseVotersCountList){
				   VoterInfo voterInfo = new VoterInfo();
					voterInfo.setTotalVoters((Long)wardVoterCount[0]);
					voterInfo.setMaleVoters((Long)wardVoterCount[3]);
					voterInfo.setFemaleVoters((Long)wardVoterCount[2]);
					Long totalFamilies = voterFamilyInfoDAO.getTotalFamiliesCount(constituencyId, publicationId, reportLevelId, (Long)wardVoterCount[1]);
					if(totalFamilies != null)
					 voterInfo.setTotalFamilies(totalFamilies);
					else
						voterInfo.setTotalFamilies(0l);
					if(type.equalsIgnoreCase("ward") && count != null && count.longValue() > 0 && wardVoterCount[0] != null){
					  voterInfo.setTotalVotersPercentage(new Double(df.format((Long)wardVoterCount[0]*100/count.doubleValue())));
					}else{
						 voterInfo.setTotalVotersPercentage(0d);
					}
					voterInfo.setMaleVotersPercentage(new Double(df.format((Long)wardVoterCount[3]*100/((Long)wardVoterCount[0]).doubleValue())));
					voterInfo.setFemaleVotersPercentage(new Double(df.format((Long)wardVoterCount[2]*100/((Long)wardVoterCount[0]).doubleValue())));
					
					voterInfo.setReportLevelValue((Long)wardVoterCount[1]);
					voterInfo.setVoterReportLevel(voterReportLevel);
					voterInfo.setPublicationDate(publicationDate);
					voterInfo.setConstituencyId(constituencyId);
					voterInfoDAO.save(voterInfo);
			   }
			  }
			  voterDAO.flushAndclearSession();
		  }
		  
		  public void saveVoterFamilyInfoForCustomWards(List<Long> localElecBodyIds,Long userId,Long publicationId,Long constituencyId,Long reportLevelId,String type){
			  VoterReportLevel voterReportLevel = voterReportLevelDAO.get(reportLevelId);
			  PublicationDate publicationDate = publicationDateDAO.get(publicationId);
			  for(Long localElectionBodyId:localElecBodyIds){
				  Map<Long,Long> totalFamilies = new HashMap<Long,Long>();
				  Map<Long,Long> famlies0to3 = new HashMap<Long,Long>();
				  Map<Long,Long> famlies4to6 = new HashMap<Long,Long>();
				  Map<Long,Long> famlies7to10 = new HashMap<Long,Long>();
				  Map<Long,Long> above10 = new HashMap<Long,Long>();
				  List<Object[]> wardWiseFamilyCountList = null;
				  // 0 count 1wardId
			     if(type.equalsIgnoreCase("ward")){
			        wardWiseFamilyCountList = boothPublicationVoterDAO.getCustomWardWiseFamilyVotersCount(localElectionBodyId, userId, publicationId, constituencyId);
			     }else{
			    	 wardWiseFamilyCountList = boothPublicationVoterDAO.getWardBoothWiseFamilyVotersCount(localElectionBodyId, userId, publicationId, constituencyId); 
			     }
			     for(Object[] familyCount:wardWiseFamilyCountList){
				   Long count = (Long)familyCount[0];
				   if(count != null){
					  
					  if(totalFamilies.get((Long)familyCount[1]) != null){
						  totalFamilies.put((Long)familyCount[1], totalFamilies.get((Long)familyCount[1])+1l);
					  }else{
						  totalFamilies.put((Long)familyCount[1],1l);
					  }
					  if(count.longValue() <= 3 ){
						  if(famlies0to3.get((Long)familyCount[1]) != null){
							  famlies0to3.put((Long)familyCount[1], famlies0to3.get((Long)familyCount[1])+1l);
						  }else{
							  famlies0to3.put((Long)familyCount[1],1l);
						  }
					  }else if(count.longValue() > 3 && count.longValue() <= 6){
						  if(famlies4to6.get((Long)familyCount[1]) != null){
							  famlies4to6.put((Long)familyCount[1], famlies4to6.get((Long)familyCount[1])+1l);
						  }else{
							  famlies4to6.put((Long)familyCount[1],1l);
						  }
					  }else if(count.longValue() > 6 && count.longValue() <= 10){
						  if(famlies7to10.get((Long)familyCount[1]) != null){
							  famlies7to10.put((Long)familyCount[1], famlies7to10.get((Long)familyCount[1])+1l);
						  }else{
							  famlies7to10.put((Long)familyCount[1],1l);
						  }
					  }else if(count.longValue() > 10 ){
						  if(above10.get((Long)familyCount[1]) != null){
							  above10.put((Long)familyCount[1], above10.get((Long)familyCount[1])+1l);
						  }else{
							  above10.put((Long)familyCount[1],1l);
						  }
					  }
				  }
			    }
			     saveVoterFamilyInfo(constituencyId,publicationDate,voterReportLevel,totalFamilies,famlies0to3,1l);
			     saveVoterFamilyInfo(constituencyId,publicationDate,voterReportLevel,totalFamilies,famlies4to6,2l);
			     saveVoterFamilyInfo(constituencyId,publicationDate,voterReportLevel,totalFamilies,famlies7to10,3l);
			     saveVoterFamilyInfo(constituencyId,publicationDate,voterReportLevel,totalFamilies,above10,4l);
			     voterDAO.flushAndclearSession();
			  }
		  }
		  
		  public void saveVoterFamilyInfo(Long constituencyId,PublicationDate publicationDate,VoterReportLevel voterReportLevel,Map<Long,Long> totalFamilies,Map<Long,Long> rangeFamilies,Long range){
			  DecimalFormat df = new DecimalFormat("###.##");
			  for(Long wardId:rangeFamilies.keySet()){ 
				    VoterFamilyInfo familyInfo = new VoterFamilyInfo();
					familyInfo.setVoterReportLevel(voterReportLevel);
					familyInfo.setReportLevelValue(wardId);
					familyInfo.setVoterFamilyRange(voterFamilyRangeDAO.get(range));
					familyInfo.setTotalFamilies(rangeFamilies.get(wardId));
					familyInfo.setFamiliesPercentage(new Double(df.format(rangeFamilies.get(wardId)*100/(totalFamilies.get(wardId)).doubleValue())));
					familyInfo.setPublicationDate(publicationDate);
					familyInfo.setConstituencyId(constituencyId);
					
					voterFamilyInfoDAO.save(familyInfo);
					
			   }
		  }
		  public void saveVoterAgeInfoForCustomWards(List<Long> localElecBodyIds,Long userId,Long publicationId,Long constituencyId,Long reportLevelId,String type){
			  Map<Long,Long> votersCountMap = new HashMap<Long,Long>();
			  DecimalFormat df = new DecimalFormat("###.##");
			  VoterReportLevel voterReportLevel = voterReportLevelDAO.get(reportLevelId);
			  PublicationDate publicationDate = publicationDateDAO.get(publicationId);
			  List<Object[]> totalCountList = voterInfoDAO.getVotersCountInCustomWards(constituencyId, publicationId,reportLevelId);
			  for(Object[]count:totalCountList){
				  votersCountMap.put((Long)count[0], (Long)count[1]);
			  }
			  for(Long localElectionBodyId:localElecBodyIds){
				  Map<Long,Map<Long,VoterAgeInfo>> voterAgeInfoMap = new HashMap<Long,Map<Long,VoterAgeInfo>>();
				  
				  List<VoterAgeInfo> voterAgeInfoList = new ArrayList<VoterAgeInfo>();
				  List<Object[]> wardWiseAgeCountList = null;
				  List<Object[]>  wardWiseYoungVotersCountList = null;
				  if(type.equalsIgnoreCase("ward")){
					  // 0count 1gender 2 agerange 3wardid
					  wardWiseAgeCountList = boothPublicationVoterDAO.getCustomWardAgeCount(localElectionBodyId, userId, publicationId, constituencyId);
					  // 0count 1gender 2 agerange 3wardid
					  wardWiseYoungVotersCountList = boothPublicationVoterDAO.getCustomWard18To22AgeCount(localElectionBodyId, userId, publicationId, constituencyId);
				  }else{
					// 0count 1gender 2 agerange 3wardboothid
					  wardWiseAgeCountList = boothPublicationVoterDAO.getWardBoothAgeCount(localElectionBodyId, userId, publicationId, constituencyId);
					  // 0count 1gender 2 agerange 3wardboothid
					  wardWiseYoungVotersCountList = boothPublicationVoterDAO.getWard18BoothTo22AgeCount(localElectionBodyId, userId, publicationId, constituencyId);
				  }
				  populateAgeInfoToVo(wardWiseYoungVotersCountList,voterAgeInfoMap,voterAgeInfoList,voterReportLevel,publicationDate,constituencyId);
				  populateAgeInfoToVo(wardWiseAgeCountList,voterAgeInfoMap,voterAgeInfoList,voterReportLevel,publicationDate,constituencyId);
				  
				  for(VoterAgeInfo voterAgeInfo:voterAgeInfoList){
					 Long totalVoters =  votersCountMap.get(voterAgeInfo.getReportLevelValue());
					 if(voterAgeInfo.getMaleVoters() == null){
						 voterAgeInfo.setMaleVoters(0l);
					 }
					 if(voterAgeInfo.getFemaleVoters() == null){
						 voterAgeInfo.setFemaleVoters(0l);
					 }
					 if(voterAgeInfo.getTotalVoters() != null){
						 if(totalVoters != null && totalVoters > 0){
							 voterAgeInfo.setTotalVotersPercentage(new Double(df.format(voterAgeInfo.getTotalVoters()*100/totalVoters.doubleValue())));
							 voterAgeInfo.setMaleVotersPercentage(new Double(df.format(voterAgeInfo.getMaleVoters()*100/voterAgeInfo.getTotalVoters().doubleValue())));
							 voterAgeInfo.setFemaleVotersPercentage(new Double(df.format(voterAgeInfo.getFemaleVoters()*100/voterAgeInfo.getTotalVoters().doubleValue())));
						 }
					 }
					 try{
					 voterAgeInfoDAO.save(voterAgeInfo);
					 voterDAO.flushAndclearSession();
					 }catch(Exception e){
						 
						 LOG.error("Exception occured with values "+voterAgeInfo.getVoterReportLevel()+","+voterAgeInfo.getReportLevelValue()+","+voterAgeInfo.getTotalVoters()+","+voterAgeInfo.getMaleVoters()+","+voterAgeInfo.getFemaleVoters()+","+voterAgeInfo.getFemaleVotersPercentage()+","+voterAgeInfo.getMaleVotersPercentage()+","+voterAgeInfo.getTotalVotersPercentage()+"",e);
					 }
				  }
			  }
			  
		  }
		  
		  public void populateAgeInfoToVo( List<Object[]> wardWiseAgeCountList,Map<Long,Map<Long,VoterAgeInfo>> voterAgeInfoMap,List<VoterAgeInfo> voterAgeInfoList,VoterReportLevel voterReportLevel,PublicationDate publicationDate,Long constituencyId){
			  Map<Long,VoterAgeInfo> rangeMap = null;
			  VoterAgeInfo voterAgeInfo = null;
			  for(Object[] wardWiseAgeCount:wardWiseAgeCountList){
				  Long ageRange = new Long(wardWiseAgeCount[2].toString());
		    	  rangeMap = voterAgeInfoMap.get((Long)wardWiseAgeCount[3]);
		    	  if(rangeMap == null){
		    		  rangeMap = new HashMap<Long,VoterAgeInfo>();
		    		  voterAgeInfoMap.put((Long)wardWiseAgeCount[3],rangeMap);
		    	  }
		    	  voterAgeInfo = rangeMap.get(ageRange);
		    	  if(voterAgeInfo == null){
		    		  voterAgeInfo = new VoterAgeInfo();
		    		  voterAgeInfo.setVoterReportLevel(voterReportLevel);
		    		  voterAgeInfo.setPublicationDate(publicationDate);
		    		  voterAgeInfo.setReportLevelValue((Long)wardWiseAgeCount[3]);
		    		  voterAgeInfo.setTotalVoters(0l);
		    		  voterAgeInfo.setFemaleVoters(0l);
		    		  voterAgeInfo.setMaleVoters(0l);
		    		  voterAgeInfo.setVoterAgeRange(voterAgeRangeDAO.get(ageRange));
		    		  voterAgeInfo.setConstituencyId(constituencyId);
		    		  rangeMap.put(ageRange,voterAgeInfo);
		    		  voterAgeInfoList.add(voterAgeInfo);
		    	  }
		    	  if(wardWiseAgeCount[1].toString().equalsIgnoreCase("M")){
		    		  voterAgeInfo.setMaleVoters((Long)wardWiseAgeCount[0]);
		    	  }else{
		    		  voterAgeInfo.setFemaleVoters((Long)wardWiseAgeCount[0]);
		    	  }
		    	
		    	    voterAgeInfo.setTotalVoters(voterAgeInfo.getTotalVoters()+(Long)wardWiseAgeCount[0]);
		    	 
		      }
		  }
		  
		  public List<VoterVO> getVoterFlagDetailsForALocation(Long locationId,Long constitunecyId,String type,Long publicationId,String requestFor)
		  {
			  List<VoterVO> resultList = new ArrayList<VoterVO>();
				 List<Object[]> dataList = null;
			  try{
				Map<Long,VoterVO>  flagMap = new HashMap<Long,VoterVO>(); 
				if(type.equalsIgnoreCase(IConstants.MANDAL) || type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
				{
					if((locationId.toString().substring(0, 1).toString().trim().equalsIgnoreCase("1")))
						
							{
						type = IConstants.LOCALELECTIONBODY; 
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationId.toString().substring(1)));
					locationId = (Long) list.get(0);
							}
					else
					locationId = new Long(locationId.toString().substring(1));
					
				}
				if(type.equalsIgnoreCase(IConstants.HAMLET) || type.equalsIgnoreCase("customWard"))
				{
				
					dataList = voterFlagDAO.getFlagWiseVotersCountByLocationIdForHamlet(locationId,constitunecyId,type,publicationId);	
				}
				else
				{
				dataList = voterFlagDAO.getFlagWiseVotersCountByLocationId(locationId,constitunecyId,type,publicationId);
				}
				 if(dataList != null && dataList.size() > 0)
				 {
					 long i = 0;
					
					 for(Object[] params : dataList)
					 {
					
						 VoterVO voterVO = flagMap.get((Long)params[1]);
						 if(voterVO == null)
						 {
							 i++;
								
							 voterVO =new VoterVO();
							 flagMap.put((Long)params[1], voterVO) ;
						 }
						 voterVO.setName(params[2]!=null?params[2].toString() : "");
						 voterVO.setColor(params[3]!=null?params[3].toString().substring(1) : "");
						 if(params[4].toString().equalsIgnoreCase("M"))
						 {
							
							 voterVO.setMaleVoters( (Long)params[0]);
						 }
						 else if(params[4].toString().equalsIgnoreCase("F"))
						 {
						
							 voterVO.setFemaleVoters((Long)params[0]);
						 }
						 voterVO.setTotalVoters(voterVO.getMaleVoters() +voterVO.getFemaleVoters());
						 voterVO.setGender(params[4].toString());
						 voterVO.setStatusId((Long)params[1]);
						 voterVO.setType(type);
						 voterVO.setLocationId(locationId);
						 voterVO.setSerialNo(i);
						
					 }
				 }
				 resultList = new ArrayList<VoterVO>(flagMap.values());
				 
			  }
			  catch (Exception e) {
				e.printStackTrace();
				LOG.error(" Exception Occured in getVoterFlagDetailsForALocation() method in VoterReport Service.....");
			}
			return resultList;
		  }
		  
		  public List<SelectOptionVO> getFlagsList(Long voterId)
		  {
			  List<SelectOptionVO> flags = new ArrayList<SelectOptionVO>();
			  List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
			  try{
				List<Long> voterflagIds = voterFlagDAO.getFlagsByVoterIds(voterId); 
				List<Object[]> list =  flagDAO.getAllFlagsList(); 
				
				if(list != null && list.size() > 0)
					for(Object[] params : list)
					{
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long) params[0]);
						selectOptionVO.setName(params[1].toString());
						selectOptionVO.setValue(params[2].toString().substring(1));
						//flags.add(new SelectOptionVO((Long) params[0],params[1].toString()));
						flags.add(selectOptionVO);
					}
				if(voterflagIds != null && voterflagIds.size() > 0)
				{
					for(SelectOptionVO vo : flags)
					{
						if(voterflagIds.contains(vo.getId()))
							vo.setFlag(true);
						    result.add(vo);	
					}
				}
				else
					return flags;
				
			  }
			  catch (Exception e) {
				  LOG.error(" Exception Occured in getFlags() method in VoterReport Service.....");
			}
			return result;
		  }
		  public ResultStatus addFlagToVoter(Long voterId,List<Long> checkedflagIds,List<Long> uncheckedflagIds,Long userId)
		  {
			  ResultStatus resultStatus = new ResultStatus();
			  try{
				  if(checkedflagIds != null && checkedflagIds.size() > 0)
				  for(Long flagId : checkedflagIds)
				  {
					  List<Object> voterFlagID = voterFlagDAO.getvoterFlagByFlagIdAndUser(flagId,userId,voterId);
					  if(voterFlagID == null || voterFlagID.size() == 0)
					  {	  
					  VoterFlag voterFlag = new VoterFlag();
					  voterFlag.setFlag(flagDAO.get(flagId));
					  voterFlag.setVoter(voterDAO.get(voterId));
					  voterFlag.setUser(userDAO.get(userId));
					  voterFlagDAO.save(voterFlag);
					  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					  }
				  }
				  
				  for(Long flagId1 : uncheckedflagIds)
				  {
					  List<Object> voterFlagID = voterFlagDAO.getvoterFlagByFlagIdAndUser(flagId1,userId,voterId);
					  if(voterFlagID != null && voterFlagID.size() > 0)
						  voterFlagDAO.deleteVoterFlagById((Long)voterFlagID.get(0));
				  }
				  
			  }
			  catch (Exception e) {
				  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				  LOG.error(" Exception Occured in addFlagToVoter() method in VoterReport Service.....");
				}
			return resultStatus;
		  }
			
		 public void saveWardBoothData(List<Long> localElecIds,Long reportLevelValue,Long publicationDateId,String type){
			 for(Long id:localElecIds){
			  if(type.equalsIgnoreCase("cutomward")){
				  List<Long> wardIds = userVoterDetailsDAO.getAllWardIdsByLocalEleBodyIdPublicationId(reportLevelValue,1l,publicationDateId,id);
				  List<Object[]> wardBooths = userVoterDetailsDAO.getBoothsForCustomWardIdsList(wardIds,reportLevelValue,publicationDateId,1l);
				  for(Object[] booth:wardBooths){
					  Long count = wardBoothDAO.getWardsCount((Long)booth[0], (Long)booth[1], publicationDateId);
					  if(count == 0 || count == null){
						  WardBooth wardBooth = new WardBooth();
						  wardBooth.setPublicationDate(publicationDateDAO.get(publicationDateId));
						  wardBooth.setBooth(boothDAO.get((Long)booth[1]));
						  wardBooth.setWard(constituencyDAO.get((Long)booth[0]));
						  wardBooth.setConstituency(constituencyDAO.get(reportLevelValue));
						  wardBoothDAO.save(wardBooth);
					  }
				  }
			  }else{
				  List<Object[]> wardDetails = boothDAO.getWardDetailsByLocalEleBodyId(id, publicationDateId, reportLevelValue);
					 for(Object[] ward:wardDetails){
						 Long count = wardBoothDAO.getWardsCount((Long)ward[0],(Long)ward[4], publicationDateId);
						  if(count == 0 || count == null){
							  WardBooth wardBooth = new WardBooth();
							  wardBooth.setPublicationDate(publicationDateDAO.get(publicationDateId));
							  wardBooth.setBooth(boothDAO.get((Long)ward[4]));
							  wardBooth.setWard(constituencyDAO.get((Long)ward[0]));
							  wardBooth.setConstituency(constituencyDAO.get(reportLevelValue));
							  wardBoothDAO.save(wardBooth);
						  }
					 }
				 }
			 }
		 }
		 
		 public Long getVoterCasteCategoryIdByUserId(Long userId,Long voterId)
		 {
			 Long returnVal = 0l;
			 try{
				List list =  userVoterDetailsDAO.getCasteCategory(userId,voterId);
			    Long casteCategoryId = 0l;
				if(list != null && list.size() > 0)
				{
				 casteCategoryId = (Long) userVoterDetailsDAO.getCasteCategory(userId,voterId).get(0);
				 if(casteCategoryId == 1)//oc
				  returnVal = 5l ;
				 else if(casteCategoryId == 2)//bc
				returnVal = 3l ;
				 else if(casteCategoryId == 3)//sc
						returnVal = 2l ;
				 else if(casteCategoryId == 4)//st
						returnVal = 1l ;
				 else
					 returnVal = 0l ; 
				}
					 
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			return returnVal;
		 }
		 
		 public void addFlagToVoterFromMobileApp(final List<FlagVO> flagDetails,String uniqueCode) 
		 {
			 LOG.debug("Entered into the addFlagToVoter service method");
			
			
			/* transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{*/
			 for(FlagVO flag:flagDetails)
			 {
				 //String[] flagDtls = flag.split("-");
				 
				 //String[] voters = flagDtls[1].split(",");
				 
				 List<String> voterIDs = new ArrayList<String>();
				 
				 for(String voterID:flag.getVoterIDS())
					 voterIDs.add(voterID);
				 
				List<Object[]> voterDetails = voterDAO.getVoterIdsByVoterIDCardNumbers(voterIDs);
				
				Map<String,Long> voterMap = new HashMap<String, Long>();
				
				for(Object[] voter:voterDetails)
					voterMap.put(voter[0].toString(), Long.parseLong(voter[1].toString()));

				List<Object> flagLst =  flagDAO.checkFlagName(flag.getFlagName());
				
				Long flagId  = null;
				Flag flagDetls = null;
				User user = userDAO.get(IConstants.ADMIN_USER_ID);
				boolean newFlag = false;
				
				if(flagLst != null & flagLst.size() >0)
				{
					 flagId = (Long)flagLst.get(0);
					 flagDetls = flagDAO.get(flagId);
				
				}else
				{
					newFlag = true;
					flagDetls = new Flag();
					
					flagDetls.setName(flag.getFlagName());
					flagDetls.setColor(flag.getColour());
					
					
					flagDetls.setUser(user);
					
					flagDetls = flagDAO.save(flagDetls);
				}
				
				for(String voter:voterIDs)
				{
					VoterFlag voterFlag = new VoterFlag();
					
					voterFlag.setFlag(flagDetls);
					voterFlag.setUser(user);
					voterFlag.setVoter(voterDAO.get(voterMap.get(voter)));
					
					
					if(!newFlag)
					{
					 List<Long> cntList = voterFlagDAO.checkFlagExistanceForVoter(
							flagDetls.getFlagId(), voterMap.get(voter),
							IConstants.ADMIN_USER_ID);
					 
					 if(cntList == null || cntList.size()== 0 || cntList.get(0) == 0)
					 {
						 voterFlag =  voterFlagDAO.save(voterFlag);
						// saveMobileVoterFlagDetails(voterFlag, mobileUserId);
					 }
					}else
					{
						voterFlag = voterFlagDAO.save(voterFlag);
					 // saveMobileVoterFlagDetails(voterFlag, mobileUserId);
					}
				}
				 
			 }
			 
           /* }});*/
			
		 }
		 
		 /*public void saveMobileVoterFlagDetails(VoterFlag voterFlag,Long mobileUserId)
		 {
			 MobileVoterFlag mobileVoterFlag = new  MobileVoterFlag();
			 
			 mobileVoterFlag.setMobileTypeId(IConstants.MOBILE_TYPE_ANDROID);
			 mobileVoterFlag.setVoterFlagId(voterFlag.getVoterFlagId());
			 mobileVoterFlag.setMobileUserId(mobileUserId);
			 
			 mobileVoterFlagDAO.save(mobileVoterFlag);
			 
		 }*/
		 
		 public String updateVoterMobileNumberAndCaste(String voterID,
					Long casteStateId,
					String mobileNo,String uniqueId)
		 {
				LOG.debug("entered into updateVoterMobileNumberAndCaste() method in VoterReportService");

			try {
				List<Long> voterIds =  voterDAO.getVoterIdByVoterIDCardNumber(voterID);
				
				if(voterIds != null && voterIds.size() >0)
				{
					List<UserVoterDetails> details = userVoterDetailsDAO.getVoterDetailsByUserIdAndVoterId(voterIds.get(0),IConstants.ADMIN_USER_ID);
					
					if(details != null && details.size() >0)
					{
						
						VoterDataInsert voterDataInsert = new VoterDataInsert();
						
						voterDataInsert.setMobileNumber(mobileNo);
						voterDataInsert.setVoterId(voterIds.get(0));
						voterDataInsert.setCasteStateId(casteStateId);
                        voterDataInsert.setUniqueId(uniqueId);
                        
                        voterDataInsertDAO.save(voterDataInsert);
						
					}else
					{
						
						UserVoterDetails userVoterDtls = new UserVoterDetails();
						
						userVoterDtls.setCasteState(casteStateDAO.get(casteStateId));
						userVoterDtls.setMobileNo(mobileNo);
						userVoterDtls.setUniqueId(uniqueId);
						
						userVoterDetailsDAO.save(userVoterDtls);
						
						
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				  LOG.error(" Exception Occured in updateVoterMobileNumberAndCaste() method in VoterReport Service.....");
				return "error";
			}
			return "success";
			 
		 }
		
		 public ResultStatus deletevotermodificationFromIntermediateTablesForDistrict(Long districtId, Long publicationDateId)
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
					  resultStatus = deletevotermodificationFromIntermediateTables(constituencyId,publicationDateId);
			  }
			  catch (Exception e) {
				  LOG.error("Exception Occured in deletevotermodificationFromIntermediateTablesForDistrict(), Exception is -",e);
				  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			 }
			return resultStatus;
		  }
		 
		public ResultStatus getCasteVotersAvailableConstituencyIds()
		{ 
			List<SelectOptionVO> voterAvailableConstIds = new ArrayList<SelectOptionVO>();
			ResultStatus resultStatus = new ResultStatus();
		  try{
			List<Object[]> constituencyIds = constituencyDAO.getConstituencyByState(1L);
			
			for(Object[] param : constituencyIds)
			{
				  SelectOptionVO vo = new SelectOptionVO();
			      vo.setId((Long)param[0]);
			      vo.setName(param[1].toString());
			      Long publicationId = publicationDateDAO.getLatestPublicationIdByConstiId((Long)param[0]);
			      Long count = userVoterDetailsDAO.getCasteVoterNamesOfAConstituency((Long)param[0], publicationId,1l);
			      vo.setOrderId(publicationId);
			      if(count > 0){			    	
			    	  voterAvailableConstIds.add(vo);
			      }			     
			}
			for(SelectOptionVO vo1 : voterAvailableConstIds)
			{				
				resultStatus = insertVotersCasteDataInIntermediateTables(vo1.getId(), vo1.getOrderId(),1l,false,false,false,false,false);			
			}
	      }catch (Exception e) {
				  LOG.error("Exception Occured in getVotersAvailableConstituencyIds(), Exception is -",e);
			 }
		return resultStatus;
		}
		
		
	public ResultStatus updateVoterNamesAndRelativeNames(Integer startIndex,Integer voterCount)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
		 LOG.warn("updateVoterNamesAndRelativeNames --> SI ->"+startIndex+" VC ->"+voterCount);	
		 Integer maxIndex = 100000;
		 if(voterCount > 0)
		 {
			 for(;;)
			 {
				 try{
					if(startIndex >= Integer.valueOf(voterCount.intValue())-1)
							break;
					if(maxIndex >= Integer.valueOf(voterCount.intValue()))
							maxIndex = Integer.valueOf(voterCount.intValue()) - 1;
					
					 LOG.warn("Start Index -->"+startIndex);
					 List<Object[]> list = voterDAO.getVoterNames(startIndex,100000);
					 if(list != null && list.size() > 0)
					 {
							 for(Object[]  params : list)
							 {
								 try{
									String name =mobileService.replaceSpecialChars(params[1] != null ?params[1].toString().trim() : ""); 
									String relativeName = mobileService.replaceSpecialChars(params[2] != null ?params[2].toString().trim() : ""); 
									Integer value = voterDAO.updateVoter((Long)params[0],name,relativeName);
									if(value == 1)
										resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
										
								 }catch(Exception e)
								 {
									 LOG.error("Exception Occured in updateVoter() method in VoterReportService, Exception is -",e);
									 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
								 }
								 
							 }
					 }
					 LOG.warn(maxIndex+" --> Records updated");
					 startIndex = startIndex + 100000;
					 maxIndex = maxIndex + 100000;
			 	}catch(Exception e)
			 	{
			 		 LOG.error("Exception Occured in getVoterNames() Block in VoterReportService, Exception is -",e);	
			 	}
				 
			    }
		  }
		}
		catch (Exception e) {
			  LOG.error("Exception Occured in updateVoterNamesAndRelativeNames() Block in VoterReportService, Exception is -",e);
		 }
		return resultStatus;
	}
	
	public ResultStatus updateMobileNos(Long districtId)
	{
		ResultStatus result = new ResultStatus();
		
		Map<Long,String> availablemobileNoMap = null;
		Long userId = 1l;
		try{
			String districtarr[] = {districtId.toString()};
			for(String distictId : districtarr)
			{
				LOG.warn("Selected District Id is  - "+districtId);
				availablemobileNoMap = new HashMap<Long, String>();
				
				List<Long> constituencyIds = mobileNumbersDAO.getConstituencysByDistictID(Long.parseLong(distictId));
				
			    if(constituencyIds != null && constituencyIds.size() > 0)
				{
			    	for(Long constituencyId : constituencyIds)
			    	{
			    		LOG.warn("Reading Costituency - "+constituencyId+" Data");
			    		List<Object[]> mobileVoters = mobileNumbersDAO.getMobileNoforVoter(constituencyId);
			    		LOG.warn("Total "+mobileVoters.size()+" Mobile Nos Selected");
			    		
			    		if(mobileVoters != null && mobileVoters.size() > 0)
			    		{
			    			for(Object[] params : mobileVoters)
			    				availablemobileNoMap.put((Long)params[1] ,params[0].toString());
			    		}
				
			    		List<Long> voterIds = new ArrayList<Long>(availablemobileNoMap.keySet());
				
			    		if(voterIds != null && voterIds.size() > 0)
			    		{
			    			List<Object[]>  uvdDetails = mobileNumbersDAO.getUservoterDetailsByUserId(userId,voterIds);
			    			List<Long> avilablevoterIds = new ArrayList<Long>();
			    			List<VoterVO> mobileList = new ArrayList<VoterVO>();
			    			
			    			if(uvdDetails !=null && uvdDetails.size() > 0)
			    			{
			    				for(Object[] uvd : uvdDetails)
			    				{
			    					VoterVO vo = new VoterVO();
			    					BigInteger voterId = (BigInteger)uvd[0];
			    					BigInteger uvdId = (BigInteger)uvd[2];
			    					avilablevoterIds.add(voterId.longValue());
			    					vo.setMobileNo(uvd[1].toString());
			    					vo.setUvdId(uvdId.longValue());
			    					mobileList.add(vo);
			    				}
						
			    			}			
			    			
			    			if(mobileList != null && mobileList.size() > 0)
			    			{
			    				LOG.warn(mobileList.size()+" Mobile Nos are already Existed");
			    				for(VoterVO vo : mobileList)
			    				{
			    					if(vo.getMobileNo() == null || vo.getMobileNo().equalsIgnoreCase("N/A") || vo.getMobileNo().contains("99999") || vo.getMobileNo().contains("NA") || vo.getMobileNo().length() <= 5)
			    					{
			    						mobileNumbersDAO.updateMobileNo(vo.getMobileNo(),vo.getUvdId());
			    					}
			    				}
			    			}
				
			    			for(Map.Entry<Long,String> entry : availablemobileNoMap.entrySet())
			    			{
			    				try{
			    					if(!avilablevoterIds.contains(entry.getKey()))
			    					{
										Long voterId = entry.getKey();
										String mobileNo = entry.getValue();
										UserVoterDetails userVoterDetails = new UserVoterDetails();
										userVoterDetails.setVoter(voterDAO.get(voterId));
										userVoterDetails.setMobileNo(mobileNo);
										userVoterDetails.setUser(userDAO.get(userId));
										userVoterDetails.setConstituency(constituencyDAO.get(constituencyId));
										userVoterDetailsDAO.save(userVoterDetails);
			    					}
			    				}catch(Exception e)
			    				{
									result.setResultCode(ResultCodeMapper.FAILURE);
									e.printStackTrace();
			    				}
			    			}
			    			
			    		}
			    		LOG.warn("Reading Costituency - "+constituencyId+" Data Completed");
			    		voterDAO.flushAndclearSession();
					}
					
				}
			}
			result.setResultCode(ResultCodeMapper.SUCCESS);
			return result;
		  }catch(Exception e)
		  {
			  System.out.println("Exception Occured in updateMobileNo()");
			  System.out.println("Exception is -"+e);
			  return result;
		  }
	}
	
	public VoterTagVO getCmsAdminReportData()
	{
		VoterTagVO voterVO = new VoterTagVO();
		Long totalTaggedVoters = 0l;
		Long totalCadre = 0l;
		Long totalInfluencePeople = 0l;
		Long insertedTaggedVoters = 0l;
		Long insertedCadre = 0l;
		Long insertedInfluencePeople = 0l;
		Long notInsertedTaggedVoters = 0l;
		Long notInsertedCadre = 0l;
		Long notInsertedInfluencePeople = 0l;
		
		try{
			totalTaggedVoters = voterTagDAO.getTotalTaggedVoters();
			totalCadre = voterTagDAO.getVotersByType("cadre");
			totalInfluencePeople = voterTagDAO.getVotersByType("influencePeople");
			
			insertedTaggedVoters =  voterTagDAO.getTotalInsertedTaggedVoters("inserted");
			insertedCadre =  voterTagDAO.getInsertedVotersByType("cadre","inserted");
			insertedInfluencePeople =  voterTagDAO.getInsertedVotersByType("influencePeople","inserted");
			
			notInsertedTaggedVoters =  voterTagDAO.getTotalInsertedTaggedVoters("notInserted");
			notInsertedCadre =  voterTagDAO.getInsertedVotersByType("cadre","notInserted");
			notInsertedInfluencePeople =  voterTagDAO.getInsertedVotersByType("influencePeople","notInserted");
			
			
			voterVO.setTotalTagged(totalTaggedVoters);
			voterVO.setTotalCadre(totalCadre);
			voterVO.setTotalInfluencePeople(totalInfluencePeople);
			
			voterVO.setInfluencePeopleInserted(insertedInfluencePeople);
			voterVO.setCadreInserted(insertedCadre);
			voterVO.setTotalInserted(insertedTaggedVoters);
			
			voterVO.setTotalNotInserted(notInsertedTaggedVoters);
			voterVO.setCadreNotInserted(notInsertedCadre);
			voterVO.setInfluencePeopleNotInserted(notInsertedInfluencePeople);
			
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getCmsAdminReportData() method in VoteReportService", e);
		}
		return voterVO;
	}
		
	/** type as cadre,influencePeople,tagged and  typeOfData - total or inserted**/
	public List<VoterTagVO> getCmsAdminReportDrtails(String isType,String typeOfData)
	{
		List<VoterTagVO> resultList = new ArrayList<VoterTagVO>();
	try{
		List<Object[]> list = voterTagDAO.getTotalTaggedVoterDetails(isType, typeOfData);
		if(list != null && list.size() > 0)
			for(Object[] params : list)
			{
				VoterTagVO voterTagVO = new VoterTagVO();
				voterTagVO.setVoterId((Long)params[0]);
				voterTagVO.setConstituencyId((Long)params[1]);
				voterTagVO.setConstituency(params[2] != null ? params[2].toString() : "");
				voterTagVO.setName(params[3] != null ? params[3].toString() : "");
				voterTagVO.setGender(params[4] != null ? params[4].toString() : "");
				voterTagVO.setAge(params[5] != null ? params[5].toString() : "");
				voterTagVO.setMobileNo(params[6] != null ? params[6].toString() : "");
				voterTagVO.setVoterIdCardNo(params[7] != null ? params[7].toString() : "");
				resultList.add(voterTagVO);
				
			}
		
		
	}
	catch (Exception e) {
		LOG.error("Exception Occured in getCmsAdminReportDrtails() method in VoteReportService", e);	
	}
	return resultList;
	}
	
	public ResultStatus saveTaggedVoterDetails(String type,List<VoterTagVO> inputList,Long userId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			if(type.equalsIgnoreCase("cadre"))
			{
			saveCadre(inputList,userId);
			}
			else if(type.equalsIgnoreCase("influencePeople"))
				saveInfluencePeople(inputList,userId);
			else if(type.equalsIgnoreCase("tagged"))
				saveTaggedVoters(inputList,userId);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}
		catch (Exception e) {
			LOG.error("Exception Occured in .() method in VoteReportService", e);	
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		return resultStatus;
	}
	
	public void saveCadre(List<VoterTagVO> inputList,Long userId)
	{
	
		DateUtilService dateService = new DateUtilService();
		try{
			for(VoterTagVO vo : inputList)
			{
				Long voter = voterDAO.getVoterIdByIdCardNo(vo.getVoterIdCardNo().toString());
				List cadreExist = cadreDAO.getCadreByVoter(voter);
				if(cadreExist == null || cadreExist.size() == 0)
				{
				Cadre cadre = new Cadre();
				UserAddress currentAddress = new UserAddress();
				cadre.setFirstName(vo.getName());
				cadre.setMobile(vo.getMobileNo());
				cadre.setGender(vo.getGender());
				cadre.setVoter(voterDAO.get(voter));
				cadre.setUser(userDAO.get(userId));
				cadre.setAge(new Long(vo.getAge()));
				cadre.setInsertType("CMS");
				//cadre.setActiveDateField(dateService.getCurrentDateAndTime());
				{
				if(vo.getConstituencyId() != null && vo.getConstituencyId().longValue() > 0)
						  currentAddress.setConstituency(constituencyDAO.get(vo.getConstituencyId()));
				currentAddress = userAddressDAO.save(currentAddress);
				cadre.setCurrentAddress(currentAddress);
				}
				cadre = cadreDAO.save(cadre);
				}
				Long voterTagId = voterTagDAO.getVoterTagId(voter);
				VoterTag voterTag = voterTagDAO.get(voterTagId);
				voterTag.setIsCadreInserted("Y");
				voterTagDAO.save(voterTag);
			}
		}
		catch (Exception e) {
			LOG.error("Exception Occured in saveCadre() method in VoteReportService", e);	
		}
	}
	public void saveInfluencePeople(List<VoterTagVO> inputList,Long userId)
	{
		try{
			for(VoterTagVO vo : inputList)
			{
				Long voter = voterDAO.getVoterIdByIdCardNo(vo.getVoterIdCardNo().toString());
				List influencePeopleExist = influencingPeopleDAO.checkVoterExistByVoterId(voter);
				if(influencePeopleExist == null || influencePeopleExist.size() == 0)
				{
				UserAddress address = new UserAddress();
				InfluencingPeople influencingPeople = new InfluencingPeople();
				influencingPeople.setFirstName(vo.getName());
				influencingPeople.setPhoneNo(vo.getMobileNo());
				influencingPeople.setGender(vo.getGender());
				influencingPeople.setInfluencingScope("CONSTITUENCY");
				influencingPeople.setInfluencingScopeValue(vo.getConstituencyId().toString());
				influencingPeople.setVoter(voterDAO.get(voter));
				{
					if(vo.getConstituencyId() != null && vo.getConstituencyId().longValue() > 0)
						address.setConstituency(constituencyDAO.get(vo.getConstituencyId()));
					address = userAddressDAO.save(address);
					influencingPeople.setUserAddress(address);
				}
				influencingPeople.setUser(userDAO.get(userId));
				influencingPeopleDAO.save(influencingPeople);
				}
				Long voterTagId = voterTagDAO.getVoterTagId(voter);
				VoterTag voterTag = voterTagDAO.get(voterTagId);
				voterTag.setIsInfluenceInserted("Y");
				voterTagDAO.save(voterTag);
			}
			
		}
		catch (Exception e) {
			LOG.error("Exception Occured in saveInfluencePeople() method in VoteReportService", e);
		}
	}
	
	
	public void saveTaggedVoters(List<VoterTagVO> inputList,Long userId)
	{
		try{
			for(VoterTagVO vo : inputList)
			{
				Long defaultFlag = 1l;
				Long voter = voterDAO.getVoterIdByIdCardNo(vo.getVoterIdCardNo().toString());
				List flagPeopleExist = voterFlagDAO.getFlagsByVoterIds(voter);
				if(flagPeopleExist == null || flagPeopleExist.size() ==0)
				{
				VoterFlag voterFlag = new VoterFlag();
				voterFlag.setVoter(voterDAO.get(voter));
				voterFlag.setUser(userDAO.get(userId));
				voterFlag.setFlag(flagDAO.get(defaultFlag));//defaultFlag
				voterFlagDAO.save(voterFlag);
				}
				Long voterTagId = voterTagDAO.getVoterTagId(voter);
				VoterTag voterTag = voterTagDAO.get(voterTagId);
				voterTag.setIsTaggedInserted("Y");
				voterTagDAO.save(voterTag);
			}
		}
		catch (Exception e) {
			LOG.error("Exception Occured in saveTaggedVoters() method in VoteReportService", e);
		}
	}
	
	public List<VotersInfoForMandalVO> getvotersInfoByPublicationConstiId(Long constituencyId,Long publicationId,String constiType,List<VotersInfoForMandalVO> returnList){
		if(returnList == null){
			returnList = new ArrayList<VotersInfoForMandalVO>();
		}
		try{
		 List<VotersInfoForMandalVO> publicationVoList = new ArrayList<VotersInfoForMandalVO>();
		if(constiType == null){
			Constituency constituency = constituencyDAO.get(constituencyId);
			if(constituency.getElectionScope().getElectionType().getElectionType().equalsIgnoreCase("Parliament")){
				constiType ="parliament";
			}else{
				constiType ="assembly";
			}
		}
		
		if(constiType.equalsIgnoreCase("assembly")){
			List<Long> constiIds = new ArrayList<Long>();
			constiIds.add(constituencyId);
			List<Object[]> publicationsList = boothDAO.getAllPublicationsForConstituencies(constiIds);
			for(Object[] publication:publicationsList){
				VotersInfoForMandalVO publicationVo = new VotersInfoForMandalVO();
				publicationVo.setId((Long)publication[0]);
				publicationVo.setName(publication[1].toString());
				publicationVoList.add(publicationVo);
			}
			if(publicationId == null){
				publicationId = publicationVoList.get(0).getId();
			}
			getvotersInfoByPublicationConstiIdForAssem(constituencyId,publicationId,returnList);
		}else{
			List<Long> constiIds = new ArrayList<Long>();
			List<Long> parliamentConstituencyIds = new ArrayList<Long>();
			parliamentConstituencyIds.add(constituencyId);
			List<Object[]> constisList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesDetailsByParliamentList(parliamentConstituencyIds);
			LinkedHashMap<Long,String> constituencyMap = new LinkedHashMap<Long,String>();
			for(Object[] consti:constisList){
				constituencyMap.put((Long)consti[0], consti[1].toString());
			}
			constiIds.addAll(constituencyMap.keySet());
			List<Object[]> publicationsList = boothDAO.getAllPublicationsForConstituencies(constiIds);
			for(Object[] publication:publicationsList){
				VotersInfoForMandalVO publicationVo = new VotersInfoForMandalVO();
				publicationVo.setId((Long)publication[0]);
				publicationVo.setName(publication[1].toString());
				publicationVoList.add(publicationVo);
			}
			if(publicationId == null){
				publicationId = publicationVoList.get(0).getId();
			}
			getvotersInfoByPublicationConstiIdForParl(constituencyMap,publicationId,returnList);
		}
		if(returnList.size() > 0){
			returnList.get(0).setVotersInfoForMandalVOList(publicationVoList);
			returnList.get(0).setId(publicationId);
		}
		}catch(Exception e){
			LOG.error("Exception rised in getvotersInfoByPublicationConstiId",e);
		}
		return returnList;
	}
	
	public void getvotersInfoByPublicationConstiIdForAssem(Long constituencyId,Long publicationId,List<VotersInfoForMandalVO> votersInfoForMandalVO){
		
		LinkedHashMap<Long,String> tehsilMap = new LinkedHashMap<Long,String>();
		LinkedHashMap<Long,VotersInfoForMandalVO> tehsilVOMap = new LinkedHashMap<Long,VotersInfoForMandalVO>();
		
		LinkedHashMap<Long,String> localBdyMap = new LinkedHashMap<Long,String>();
		LinkedHashMap<Long,VotersInfoForMandalVO> localBdyVOMap = new LinkedHashMap<Long,VotersInfoForMandalVO>();
		
		
		List<Object[]> tehsilList =  boothDAO.getAllTehsilsDetailsInAConstituency(constituencyId,publicationId);
		List<Object[]> localBodisList = boothDAO.getAllLocalBodies(constituencyId,publicationId);
		for(Object[] location:tehsilList){
			tehsilMap.put((Long)location[0], (location[1].toString()+" Mandal").toUpperCase());
		}
		for(Object[] location:localBodisList){
			localBdyMap.put((Long)location[0], (location[1].toString()).toUpperCase());
		}
		if(tehsilMap.size() > 0){
			populateRespectiveLocationVoForTehsil(tehsilMap,tehsilVOMap,votersInfoForMandalVO);
		   List<VoterInfo> voterInfoList = voterInfoDAO.getVotersCountForMultipleLocs(2l, tehsilMap.keySet(), publicationId, constituencyId);
		   Set<Long> dataNAlocations = new HashSet<Long>(tehsilMap.keySet());
		   populateVotersInfo(tehsilVOMap,voterInfoList,dataNAlocations);
		   if(dataNAlocations.size() > 0){
			   populateCalculateVoterInfo("mandal",dataNAlocations,publicationId,constituencyId,tehsilVOMap);
		   }
		}
		if(localBdyMap.size() > 0){
			populateRespectiveLocationVoForLOclBdy(localBdyMap,localBdyVOMap,votersInfoForMandalVO);
		   List<VoterInfo> voterInfoList = voterInfoDAO.getVotersCountForMultipleLocs(5l, localBdyMap.keySet(), publicationId, constituencyId);
		   Set<Long> dataNAlocations = new HashSet<Long>(localBdyMap.keySet());
		   populateVotersInfo(localBdyVOMap,voterInfoList,dataNAlocations);
		   if(dataNAlocations.size() > 0){
			   populateCalculateVoterInfo("localbody",dataNAlocations,publicationId,constituencyId,localBdyVOMap);
		   }
		}
	}
	
    public void getvotersInfoByPublicationConstiIdForParl(LinkedHashMap<Long,String> constituencyMap,Long publicationId,List<VotersInfoForMandalVO> votersInfoForMandalVO){
		LinkedHashMap<Long,VotersInfoForMandalVO> constituencyVOMap = new LinkedHashMap<Long,VotersInfoForMandalVO>();
		
		if(constituencyMap.size() > 0){
			populateRespectiveLocationVoForLOclBdy(constituencyMap,constituencyVOMap,votersInfoForMandalVO);
		   List<VoterInfo> voterInfoList = voterInfoDAO.getVotersCountForMultipleLocs(1l, constituencyMap.keySet(), publicationId, null);
		   Set<Long> dataNAlocations = new HashSet<Long>(constituencyMap.keySet());
		   populateVotersInfo(constituencyVOMap,voterInfoList,dataNAlocations);
		   if(dataNAlocations.size() > 0){
			   populateCalculateVoterInfo("constituency",dataNAlocations,publicationId,null,constituencyVOMap);
		   }
		}
	}
    
	public void populateRespectiveLocationVoForLOclBdy(LinkedHashMap<Long,String> locationMap,LinkedHashMap<Long,VotersInfoForMandalVO> locationVOMap
			,List<VotersInfoForMandalVO> valuesList){
		for(Long key:locationMap.keySet()){
			VotersInfoForMandalVO reqVO = null;
			for(VotersInfoForMandalVO vo:valuesList){
				if(!vo.getIsMandal() && vo.getMandalId().equalsIgnoreCase(key.toString())){
					reqVO = vo;
				}
				if(reqVO != null){
					break;
				}
			}
			if(reqVO == null){
				reqVO = new VotersInfoForMandalVO();
				reqVO.setMandalId(key.toString());
				reqVO.setMandalName(locationMap.get(key));
				valuesList.add(reqVO);
			}
			locationVOMap.put(key, reqVO);
		}
	}
	
	public void populateRespectiveLocationVoForTehsil(LinkedHashMap<Long,String> locationMap,LinkedHashMap<Long,VotersInfoForMandalVO> locationVOMap
			,List<VotersInfoForMandalVO> valuesList){
		for(Long key:locationMap.keySet()){
			VotersInfoForMandalVO reqVO = null;
			for(VotersInfoForMandalVO vo:valuesList){
				if(vo.getIsMandal() && vo.getMandalId().equalsIgnoreCase(key.toString())){
					reqVO = vo;
				}
				if(reqVO != null){
					break;
				}
			}
			if(reqVO == null){
				reqVO = new VotersInfoForMandalVO();
				reqVO.setMandalId(key.toString());
				reqVO.setMandalName(locationMap.get(key));
				reqVO.setIsMandal(true);
				valuesList.add(reqVO);
			}
			locationVOMap.put(key, reqVO);
		}
	}
	
	public void populateCalculateVoterInfo(String type,Set<Long> dataNAlocations,Long publicationId,Long constituencyId,LinkedHashMap<Long,VotersInfoForMandalVO> locationVOMap){
		 //0 count,1gender,2location
		   List<Object[]> genderCountList = boothPublicationVoterDAO.getVotersCountByPublicationIdLocationIds(type, dataNAlocations, publicationId, constituencyId);
		   for(Object[] genderCount:genderCountList){
			   VotersInfoForMandalVO vo = locationVOMap.get((Long)genderCount[2]);
			   if(genderCount[1].toString().equalsIgnoreCase("F") || genderCount[1].toString().equalsIgnoreCase("Female")){
				   vo.setFemaleVoters(vo.getFemaleVoters()+(Long)genderCount[0]);
				   vo.setTotalVotersDiff(vo.getTotalVotersDiff()+(Long)genderCount[0]);
			   }else{
				   vo.setMaleVoters(vo.getMaleVoters()+(Long)genderCount[0]);
				   vo.setTotalVotersDiff(vo.getTotalVotersDiff()+(Long)genderCount[0]);
			   }
		   }
	}
	
	public void populateVotersInfo(LinkedHashMap<Long,VotersInfoForMandalVO> locationVOMap,List<VoterInfo> voterInfoList,Set<Long> dataNAlocations){
		for(VoterInfo voterInfo:voterInfoList){
			dataNAlocations.remove(voterInfo.getReportLevelValue());
			VotersInfoForMandalVO locationInfo = locationVOMap.get(voterInfo.getReportLevelValue());
			if(voterInfo.getMaleVoters() != null){
				locationInfo.setMaleVoters(voterInfo.getMaleVoters());
			}
			if(voterInfo.getFemaleVoters() != null){
				locationInfo.setFemaleVoters(voterInfo.getFemaleVoters());
			}
			if(voterInfo.getTotalVoters() != null){
				locationInfo.setTotalVotersDiff(voterInfo.getTotalVoters());
			}
		}
	}
    
}
