package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.IGroupDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IInformationSourceDAO;
import com.itgrids.partyanalyst.dao.ILanguageDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultWithAllianceDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionStateResultDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionStateResultWithAllianceDAO;
import com.itgrids.partyanalyst.dao.IProblemStatusDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ISocialCategoryDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dao.hibernate.HamletBoothElectionDAO;
import com.itgrids.partyanalyst.dto.AlliancePartiesInElection;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateElectionCompleteResultsVO;
import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidateWonVO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionCompleteResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyResultsInElectionVO;
import com.itgrids.partyanalyst.dto.ConstituencyWinnerInfoVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.dto.ElectionBasicInfoVO;
import com.itgrids.partyanalyst.dto.ElectionBasicInformationVO;
import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;
import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzReportVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.PartyElectionCompleteResultsVO;
import com.itgrids.partyanalyst.dto.PartyElectionDetailsVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.dto.TownshipBoothDetailsVO;
import com.itgrids.partyanalyst.model.AllianceGroup;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionAlliance;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Group;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.InformationSource;
import com.itgrids.partyanalyst.model.Language;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.model.PartyElectionStateResult;
import com.itgrids.partyanalyst.model.ProblemStatus;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.SocialCategory;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IElectionAnalyzeService;
import com.itgrids.partyanalyst.service.IPartyStrengthService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.CandidateElecResultVOComparator;
import com.itgrids.partyanalyst.utils.ConstituencyNamesComparator;
import com.itgrids.partyanalyst.utils.DistrictNamesComparator;
import com.itgrids.partyanalyst.utils.ElectionResultTypeComparator;
import com.itgrids.partyanalyst.utils.ElectionYearsComparator;
import com.itgrids.partyanalyst.utils.GenericException;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyElectionResultComparator;
import com.itgrids.partyanalyst.utils.PartyResultVOComparator;
import com.itgrids.partyanalyst.utils.SelectOptionVOByIdComparator;
import com.itgrids.partyanalyst.utils.SelectOptionVOComparator;
import com.itgrids.partyanalyst.utils.TehsilVotingTrendsComparator;

public class StaticDataService implements IStaticDataService {

	private IElectionDAO electionDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IPartyDAO partyDAO;
	private IStateDAO stateDAO;
	private IElectionAllianceDAO electionAllianceDAO;
	private IDistrictDAO districtDAO;
	private IAllianceGroupDAO allianceGroupDAO;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private INominationDAO nominationDAO;
	private ITownshipDAO townshipDAO;
	private ITehsilDAO tehsilDAO;
	private IHamletDAO hamletDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IPartyElectionResultDAO partyElectionResultDAO;
	private IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO;
	private IPartyElectionStateResultDAO partyElectionStateResultDAO;
	private final static Logger log = Logger.getLogger(StaticDataService.class);
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IVillageBoothElectionDAO villageBoothElectionDAO;
	private TransactionTemplate transactionTemplate;
	private IGroupDAO groupDAO;
	private IBoothResultDAO boothResultDAO;
	private ICommentCategoryCandidateDAO commentCategoryCandidateDAO;
	private IElectionTypeDAO electionTypeDAO;
	private ICandidateResultDAO candidateResultDAO;
	private IConstituencyPageService constituencyPageService;
	private ISocialCategoryDAO socialCategoryDAO;
	private IEducationalQualificationsDAO educationalQualificationsDAO;
	private IOccupationDAO occupationDAO;
	private ILanguageDAO languageDAO;
	private IPartyElectionDistrictResultWithAllianceDAO partyElectionDistrictResultWithAllianceDAO;
	private IPartyElectionStateResultWithAllianceDAO partyElectionStateResultWithAllianceDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IInformationSourceDAO informationSourceDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IElectionAnalyzeService electionAnalyzeService;
	private IProblemStatusDAO problemStatusDAO;
	private IPartyStrengthService partyStrengthService;
	private IHamletBoothElectionDAO hamletBoothElectionDAO;

	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}

	public IPartyStrengthService getPartyStrengthService() {
		return partyStrengthService;
	}

	public void setPartyStrengthService(
			IPartyStrengthService partyStrengthService) {
		this.partyStrengthService = partyStrengthService;
	}

	public IProblemStatusDAO getProblemStatusDAO() {
		return problemStatusDAO;
	}

	public void setProblemStatusDAO(IProblemStatusDAO problemStatusDAO) {
		this.problemStatusDAO = problemStatusDAO;
	}

	private IRegistrationDAO registrationDAO;

	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}

	/**
	 * @param partyDAO the partyDAO to set
	 */
  
  
  	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public IElectionAnalyzeService getElectionAnalyzeService() {
		return electionAnalyzeService;
	}

	public void setElectionAnalyzeService(
			IElectionAnalyzeService electionAnalyzeService) {
		this.electionAnalyzeService = electionAnalyzeService;
	}

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public IPartyElectionDistrictResultWithAllianceDAO getPartyElectionDistrictResultWithAllianceDAO() {
		return partyElectionDistrictResultWithAllianceDAO;
	}

	public void setPartyElectionDistrictResultWithAllianceDAO(
			IPartyElectionDistrictResultWithAllianceDAO partyElectionDistrictResultWithAllianceDAO) {
		this.partyElectionDistrictResultWithAllianceDAO = partyElectionDistrictResultWithAllianceDAO;
	}

	public IPartyElectionStateResultWithAllianceDAO getPartyElectionStateResultWithAllianceDAO() {
		return partyElectionStateResultWithAllianceDAO;
	}

	public void setPartyElectionStateResultWithAllianceDAO(
			IPartyElectionStateResultWithAllianceDAO partyElectionStateResultWithAllianceDAO) {
		this.partyElectionStateResultWithAllianceDAO = partyElectionStateResultWithAllianceDAO;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	/**
	 * @param stateDAO the stateDAO to set
	 */
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public void setElectionAllianceDAO(IElectionAllianceDAO electionAllianceDAO) {
		this.electionAllianceDAO = electionAllianceDAO;
	}

	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
	}

	public IBoothResultDAO getBoothResultDAO() {
		return boothResultDAO;
	}

	public void setBoothResultDAO(IBoothResultDAO boothResultDAO) {
		this.boothResultDAO = boothResultDAO;
	}

	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public IGroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(IGroupDAO groupDAO) {
		this.groupDAO = groupDAO;
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

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}

	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}

	public IElectionTypeDAO getElectionTypeDAO() {
		return electionTypeDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IPartyElectionResultDAO getPartyElectionResultDAO() {
		return partyElectionResultDAO;
	}

	public void setPartyElectionResultDAO(
			IPartyElectionResultDAO partyElectionResultDAO) {
		this.partyElectionResultDAO = partyElectionResultDAO;
	}

	public IPartyElectionDistrictResultDAO getPartyElectionDistrictResultDAO() {
		return partyElectionDistrictResultDAO;
	}

	public void setPartyElectionDistrictResultDAO(
			IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO) {
		this.partyElectionDistrictResultDAO = partyElectionDistrictResultDAO;
	}

	public IPartyElectionStateResultDAO getPartyElectionStateResultDAO() {
		return partyElectionStateResultDAO;
	}

	public void setPartyElectionStateResultDAO(
			IPartyElectionStateResultDAO partyElectionStateResultDAO) {
		this.partyElectionStateResultDAO = partyElectionStateResultDAO;
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

	public ICommentCategoryCandidateDAO getCommentCategoryCandidateDAO() {
		return commentCategoryCandidateDAO;
	}

	public void setCommentCategoryCandidateDAO(
			ICommentCategoryCandidateDAO commentCategoryCandidateDAO) {
		this.commentCategoryCandidateDAO = commentCategoryCandidateDAO;
	}

	public IVillageBoothElectionDAO getVillageBoothElectionDAO() {
		return villageBoothElectionDAO;
	}

	public void setVillageBoothElectionDAO(
			IVillageBoothElectionDAO villageBoothElectionDAO) {
		this.villageBoothElectionDAO = villageBoothElectionDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public ICandidateResultDAO getCandidateResultDAO() {
		return candidateResultDAO;
	}

	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}

	public ISocialCategoryDAO getSocialCategoryDAO() {
		return socialCategoryDAO;
	}

	public void setSocialCategoryDAO(ISocialCategoryDAO socialCategoryDAO) {
		this.socialCategoryDAO = socialCategoryDAO;
	}

	public IEducationalQualificationsDAO getEducationalQualificationsDAO() {
		return educationalQualificationsDAO;
	}

	public void setEducationalQualificationsDAO(
			IEducationalQualificationsDAO educationalQualificationsDAO) {
		this.educationalQualificationsDAO = educationalQualificationsDAO;
	}

	public IOccupationDAO getOccupationDAO() {
		return occupationDAO;
	}

	public void setOccupationDAO(IOccupationDAO occupationDAO) {
		this.occupationDAO = occupationDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(
			ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public IInformationSourceDAO getInformationSourceDAO() {
		return informationSourceDAO;
	}

	public void setInformationSourceDAO(
			IInformationSourceDAO informationSourceDAO) {
		this.informationSourceDAO = informationSourceDAO;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * This method returns all the election years by electionType.
	 */
	public List<SelectOptionVO> getAllElectionYearsBasedOnElectionType(
			String electionType) {
		List<SelectOptionVO> electionTypes = new ArrayList<SelectOptionVO>(0);
		List<SelectOptionVO> allYears = new ArrayList<SelectOptionVO>(0);
		Long electionId = 0l;
		try {
			electionTypes = getAllElectionTypes();
			for (SelectOptionVO eleTypes : electionTypes) {
				if (eleTypes.getName().equalsIgnoreCase(electionType))
					electionId = eleTypes.getId();
			}

			allYears.add(0, new SelectOptionVO(0l, "Select Year"));

			allYears.addAll(getElectionIdsAndYears(electionId));

			return allYears;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return null;
		}

	}

	public List<SelectOptionVO> getElectionScopesByElectionType(
			Long electionTypeId) {
		List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		ElectionType electionType = electionTypeDAO.get(electionTypeId);
		List<ElectionScope> electionScopes = electionScopeDAO
				.findByPropertyElectionTypeId(electionTypeId);
		if (IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType
				.getElectionType()))
			for (ElectionScope electionScope : electionScopes)
				list.add(new SelectOptionVO(electionScope.getElectionScopeId(),
						electionScope.getCountry().getCountryName()));
		else if (IConstants.ASSEMBLY_ELECTION_TYPE
				.equalsIgnoreCase(electionType.getElectionType()))
			for (ElectionScope electionScope : electionScopes)
				list.add(new SelectOptionVO(electionScope.getElectionScopeId(),
						electionScope.getState().getStateName()));
		Collections.sort(list);
		return list;
	}

	/**
	 * This method returns a list of Objects that contains electionIds and
	 * election years based on the Election-Scopes Ids and Party Id.
	 */
	public List<SelectOptionVO> getElectionIdsAndYearsByElectionScope(
			Long electionScopeId, Long partyId) {
		List<SelectOptionVO> electionYearslist;
		List elections;
		try {
			electionYearslist = new ArrayList<SelectOptionVO>();
			elections = nominationDAO.findByElectionScopeIdAndPartyId(
					electionScopeId, IConstants.ELECTION_SUBTYPE_MAIN, partyId);
			for (int i = 0; i < elections.size(); i++) {
				Object[] parms = (Object[]) elections.get(i);
				electionYearslist.add(new SelectOptionVO(Long.parseLong(parms[0].toString()), parms[1].toString()));
			}
			return electionYearslist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<SelectOptionVO> electionYearsForstateAndElectionType(Long stateId, String electionType)
	{
		try{
			List<SelectOptionVO> yearsList = null;
			List<Object[]> electionYears = electionDAO.getElectionYearsBasedOnElectionTypeAndState(stateId,electionType);
			if(electionYears != null && electionYears.size() > 0)
			{
				yearsList = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for(Object[] params : electionYears)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					yearsList.add(selectOptionVO);
				}
			}
			
			return yearsList;
		}catch(Exception e)
		{
			log.error("Exception Encoutered during fetching Election Years from A state with state id - "+stateId +" For Election Type - "+ electionType);
			return null;
		}
	}

	public List<SelectOptionVO> getAllElectionScopes() {
		List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		List electionScopes = electionScopeDAO.getElectionScopes();
		for (Object[] values : (List<Object[]>) electionScopes)
			list.add(new SelectOptionVO((Long) values[0], values[1].toString()));
		return list;
	}

	public List<SelectOptionVO> getAllElectionScopes(Long districtId) {
		List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();

		District district = districtDAO.get(districtId);
		List<ElectionScope> electionScopes = electionScopeDAO.getElectionScopes(district.getState().getStateId());
		for (ElectionScope scope : electionScopes)
		   list.add(new SelectOptionVO(scope.getElectionScopeId(),WordUtils.capitalize(scope.getElectionType().getElectionType().toLowerCase())));
		return list;
	}

	public List<SelectOptionVO> getAllElectionScopesForAState(Long stateID) {
		List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		if (stateID != null) {
			List<ElectionScope> electionScopes = electionScopeDAO
					.getElectionScopes(stateID);
			if (electionScopes != null) {
				for (ElectionScope scope : electionScopes) {
					list.add(new SelectOptionVO(scope.getElectionType().getElectionTypeId(), scope.getElectionType().getElectionType()));
				}
			}
		}
		Collections.sort(list);
		return list;
	}

	public List getAllAssemblyElectionsInDistrict(Long districtId, String type) {
		List elections = nominationDAO.getAllAssemblyElectionsInDistrict(districtId, type);
		return elections;
	}

	public List getAllElectionsInDistrict(Long districtId) {
		List elections = nominationDAO.getAllElectionsInDistrict(districtId);
		return elections;
	}

	public List<SelectOptionVO> getElectionIdsAndYearsForConstituency(Long constituencyId) {
		List elections = boothConstituencyElectionDAO.findElectionsHappendInConstituency(constituencyId);
		List<SelectOptionVO> years = new ArrayList<SelectOptionVO>();
		for (Object[] election : (List<Object[]>) elections) {
			years.add(new SelectOptionVO((Long) election[0], election[1].toString()));
		}
		return years;
	}

	/**
	 * This method returns all the election type ids and election type names.
	 * 
	 * @return
	 */
	public List<SelectOptionVO> getAllElectionTypes() {
		List<ElectionType> electionTypes = electionTypeDAO.getAll();
		List<SelectOptionVO> electionTypeData = new ArrayList<SelectOptionVO>(0);
		for (ElectionType electionType : electionTypes) {
			SelectOptionVO selectOption = new SelectOptionVO();
			selectOption.setId(electionType.getElectionTypeId());
			selectOption.setName(electionType.getElectionType());
			electionTypeData.add(selectOption);
		}
		return electionTypeData;
	}

	public List<ElectionScope> getElectionScope(Long electionType) {
		return electionScopeDAO.findByPropertyElectionTypeId(electionType);
	}

	public List<SelectOptionVO> getElectionIdsAndYears(Long electionTypeId) {
		List elections = villageBoothElectionDAO
				.findElectionsForElectionType(electionTypeId);
		List<SelectOptionVO> years = new ArrayList<SelectOptionVO>();

		for (Object[] election : (List<Object[]>) elections)
			years.add(new SelectOptionVO((Long) election[0], election[1].toString()));

		return years;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getElectionIdsAndYearsInfo(Long elecType,
			Long stateId) {
		List<SelectOptionVO> electionYears = new ArrayList<SelectOptionVO>();
		List elections = null;

		ElectionType electionType = electionTypeDAO.get(elecType);
		if (electionType.getElectionType().equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
			elections = electionDAO.findElectionAndYearForParliamentElectionType(elecType);
		else
			elections = electionDAO.findElectionAndYearForElectionTypeAndState(elecType, stateId);
		if (elections != null) {
			for (int i = 0; i < elections.size(); i++) {
				Object[] params = (Object[]) elections.get(i);
				Long electionId = (Long) params[0];
				String elecYear = (String) params[1];

				SelectOptionVO selectOption = new SelectOptionVO();
				selectOption.setId(electionId);
				selectOption.setName(elecYear);

				electionYears.add(selectOption);
			}
		}

		return electionYears;
	}

	@SuppressWarnings("unchecked")
	public List<String> getElectionYears(Long electionType,
			Boolean incByeElection) {
		List<Election> elections = null;
		if (!incByeElection)
			elections = electionDAO.findByElectionType(electionType,IConstants.ELECTION_SUBTYPE_MAIN);
		else
			elections = electionDAO.findByElectionType(electionType);
		List<String> years = new ArrayList<String>();
		for (Election election : elections) {
			years.add(election.getElectionYear());
		}

		Collections.sort(years, new ElectionYearsComparator());
		return years;
	}
	/**
	 * This method returns all the election years based on parties.
	 * params stateId,eletionTypeId,partyId
	 * @return
	 */
	
	public List<SelectOptionVO> getElectionYearByPartyId(Long stateId,Long partyId,Long electionTypeId)
	{
		try{
			List<SelectOptionVO> electionYears = null;
			List<Object> list = nominationDAO.getElectionYearsBasedOnParty(stateId, partyId, electionTypeId);
			
			if(list != null && list.size() > 0)
			{
				long i = 0l;
				electionYears = new ArrayList<SelectOptionVO>(0);
				for(Object obj : list)
					electionYears.add(new SelectOptionVO(++i,obj.toString()));
			}
			return electionYears;			
		}
		catch(Exception e){
			return null;
		}

	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public List<SelectOptionVO> findTownshipsByTehsilID(Long mandalID) {
		List<SelectOptionVO> townshipVOs = new ArrayList<SelectOptionVO>();
		SelectOptionVO townshipVO = null;
		List<Township> townships = townshipDAO.findByTehsilID(mandalID);
		for (Township township : townships) {
			townshipVO = new SelectOptionVO(township.getTownshipId(), township.getTownshipName());
			townshipVOs.add(townshipVO);
		}
		return townshipVOs;
	}

	public List<SelectOptionVO> getStates(Long electionType) {
		List<ElectionScope> electionScopes = electionScopeDAO
				.findByPropertyElectionTypeId(electionType);
		List<SelectOptionVO> stateList = new ArrayList<SelectOptionVO>();
		for (ElectionScope scope : electionScopes) {
			State state = scope.getState();
			stateList.add(new SelectOptionVO(state.getStateId(), state.getStateName()));
		}
		return stateList;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getParticipatedStatesForAnElectionType(
			Long electionType) {
		List<SelectOptionVO> stateList = new ArrayList<SelectOptionVO>();
		ElectionType electionTypeObj = electionTypeDAO.get(electionType);
		List resultsList;
		if (IConstants.MUNCIPLE_ELECTION_TYPE.equals(electionTypeObj.getElectionType())|| IConstants.CORPORATION_ELECTION_TYPE.equals(electionTypeObj.getElectionType())) {
			resultsList = electionDAO.findStatesByElectionType(electionType);
		} else {
			resultsList = constituencyElectionDAO
					.getParticipatedStateDetailsForAnElectionType(electionType);
		}
		if (resultsList != null && resultsList.size() > 0) {
			Iterator listIt = resultsList.listIterator();
			while (listIt.hasNext()) {
				Object[] values = (Object[]) listIt.next();
				SelectOptionVO option = new SelectOptionVO();
				option.setId((Long) values[0]);
				option.setName((String) values[1]);

				stateList.add(option);
			}
		}

		return stateList;
	}

	public List<String> getElectionYears() {
		return electionDAO.listOfYears();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.itgrids.partyanalyst.service.IStaticDataService#getDistricts(java
	 * .lang.Long) Method to get all districts in a state
	 */
	public List<SelectOptionVO> getDistricts(Long stateId) {
		List<District> list = districtDAO.findByStateId(stateId);
		List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
		for (District district : list) {
			
			districts.add(new SelectOptionVO(district.getDistrictId(), WordUtils.capitalize(district.getDistrictName().toLowerCase())));
		}
		return districts;
	}

	public List<State> getAllStates() {
		return stateDAO.getAll();
	}

	public ILanguageDAO getLanguageDAO() {
		return languageDAO;
	}

	public void setLanguageDAO(ILanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
	}

	// Returns Null If No alliance Exists For Party
	public AlliancePartyResultsVO getAlliancePartiesByElectionAndParty(
			Long electionId, Long partyId) {
		AlliancePartyResultsVO alliancePartiesVO = new AlliancePartyResultsVO();
		List allianceParites = allianceGroupDAO
				.findAlliancePartiesByElectionAndParty(electionId, partyId);
		List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>();
		if (allianceParites.size() == 0)
			return null;
		Object[] values = (Object[]) allianceParites.get(0);
		alliancePartiesVO.setGroupId((Long) values[0]);
		alliancePartiesVO.setAllianceGroupName(values[1].toString());
		for (Object[] dbValues : (List<Object[]>) allianceParites)
			parties.add(new SelectOptionVO((Long) dbValues[2], dbValues[3].toString()));
		alliancePartiesVO.setAllianceParties(parties);
		return alliancePartiesVO;
	}

	public AlliancePartyResultsVO getAlliancePartiesByElectionAndPartyForState(
			Long electionId, Long partyId, Long stateId) {
		AlliancePartyResultsVO alliancePartiesVO = new AlliancePartyResultsVO();
		List allianceParites = allianceGroupDAO.findAlliancePartiesByElectionAndParty(electionId, partyId);
		List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>();
		if (allianceParites.size() == 0)
			return null;
		Object[] values = (Object[]) allianceParites.get(0);
		alliancePartiesVO.setGroupId((Long) values[0]);
		alliancePartiesVO.setAllianceGroupName(values[1].toString());
		for (Object[] dbValues : (List<Object[]>) allianceParites)
			parties.add(new SelectOptionVO((Long) dbValues[2], dbValues[3]
					.toString()));
		alliancePartiesVO.setAllianceParties(parties);
		return alliancePartiesVO;
	}

	// Need refactoring the code and unit testing- Ashok
	public List<SelectOptionVO> getAlliancePartiesAsVO(String electionYear,
			Long electionType, Long partyId, Long stateId) {
		List<SelectOptionVO> allianceParties = new ArrayList<SelectOptionVO>();

		Long groupId = getGroupIdIfPartyHasAlliances(partyId, electionYear,
				electionType,stateId);

		if (groupId != null) {
			List<AllianceGroup> allianceGroupList = allianceGroupDAO
					.findByGroupId(groupId);
			for (AllianceGroup allianceGroup : allianceGroupList) {
				allianceParties
						.add(new SelectOptionVO(allianceGroup.getParty()
								.getPartyId(), allianceGroup.getParty()
								.getShortName()));
			}
			System.out.println("allianceParties"+allianceParties);
			return allianceParties;
		}

		return null;
	}

	// Need refactoring the code and unit testing- Ashok
	public Long getGroupIdIfPartyHasAlliances(Long partyId,
			String electionYear, Long electionType,Long stateId) {
		
		List<ElectionAlliance> allianceList = null;
		ElectionType electnType = null;
		
		if(electionType != null && !electionType.equals(0L))
			electnType = electionTypeDAO.get(electionType);
		
		if(electnType.getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE) || stateId.equals(0L))
			allianceList = electionAllianceDAO.findByElectionYearAndType(electionYear, electionType);
		else
			allianceList = electionAllianceDAO.findByElectionYearAndType(electionYear, electionType,stateId);

		for (ElectionAlliance alliance : allianceList) {
			Long groupId = alliance.getGroup().getGroupId();
			List<AllianceGroup> allianceGroupList = allianceGroupDAO
					.findByGroupId(groupId);
			for (AllianceGroup allianceGroup : allianceGroupList) {
				if (allianceGroup.getParty().getPartyId().equals(partyId)) {
					return allianceGroup.getGroup().getGroupId();
				}
			}
		}
		return null;
	}

	// Need refactoring the code and unit testing- Ashok
	public List<Party> getAllianceParties(String electionYear,
			Long electionType, Long partyId,Long stateId) {
		List<Party> allianceParties = null;

		Long groupId = getGroupIdIfPartyHasAlliances(partyId, electionYear,
				electionType,stateId);
		
		if (groupId != null) {
			allianceParties = new ArrayList<Party>();
			List<AllianceGroup> allianceGroupList = allianceGroupDAO
					.findByGroupId(groupId);
			for (AllianceGroup allianceGroup : allianceGroupList) {
				allianceParties.add(allianceGroup.getParty());
			}
		}
		return allianceParties;
	}

	// Need refactoring the code and unit testing- Ashok
	public boolean hasAlliances(String electionYear, Long electionType,
			Long partyId) {
		if (getGroupIdIfPartyHasAlliances(partyId, electionYear, electionType,1L) != null)
			return true;

		return false;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public List<SelectOptionVO> getConstituencies(Long stateId) {
		List<Constituency> constList = constituencyDAO.findByStateId(stateId);
		List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>();

		for (Constituency constituency : constList)
			constituencies.add(new SelectOptionVO(constituency
					.getConstituencyId(), constituency.getName()));

		return constituencies;
	}

	public List<ConstituencyElection> getConstituencyElections(Long electionID,
			Long stateId, Long districtID) {
		List<ConstituencyElection> constituencyElectionList = null;
		if (districtID == null || districtID == 0L) {
			System.out.println(" DAO ...1");
			// constituencyElectionList =
			// constituencyElectionDAO.findByElection(electionID);
			constituencyElectionList = constituencyElectionDAO
					.findByElectionAndState(electionID, stateId);
		} else {
			System.out.println("DAO ... 2");
			// constituencyElectionList =
			// constituencyElectionDAO.findByElectionAndDistrict(electionID,
			// districtID);
			constituencyElectionList = constituencyElectionDAO
					.findByElectionAndStateAndDistrict(electionID, stateId,
							districtID);
		}
		return constituencyElectionList;
	}

	public List<SelectOptionVO> getPartiesForConstituency(Long constituencyId,
			String electionYear) {
		List<Party> parties = nominationDAO
				.findPartiesByConstituencyAndElection(constituencyId,
						electionYear);
		List<SelectOptionVO> partyVOs = new ArrayList<SelectOptionVO>();
		for (Party party : parties) {
			SelectOptionVO partyVO = new SelectOptionVO(party.getPartyId(),
					party.getShortName());
			partyVOs.add(partyVO);
		}
		return partyVOs;
	}

	/*
	 * This method takes District Id as input and displays all the Assembly
	 * Candidates that are present in that District.
	 */
	@SuppressWarnings("unchecked")
	public ConstituenciesStatusVO getConstituenciesWinnerInfo(Long districtId) {
		Set<Long> parliamentConstituencies = new HashSet<Long>();
		List<Long> constituencyIds = new ArrayList<Long>(0);
		List yearsList = new ArrayList(0);
		try {
			log.debug("DistrictPageService.getConstituenciesWinnerInfo()...started started..");
			Long electionYear = 0L;
			Long count = 0L;
			Long stateId = districtDAO.get(districtId).getState().getStateId();
			// Check for Bye Election
			/*
			 * Election election =
			 * electionDAO.getRecentElectionHappendForAnElectionType
			 * (IConstants.ASSEMBLY_ELECTION_TYPE,stateId).get(0);
			 * if(election.getElecSubtype().equalsIgnoreCase("BYE")){ List
			 * resultsCount =
			 * nominationDAO.checkForResultsAvailabilityForAnElection
			 * (election.getElectionId()); Object value =
			 * (Object)resultsCount.get(0); count = (Long)value; }
			 * 
			 * if(count > 0L) electionYear =
			 * Long.parseLong(election.getElectionYear()); else{
			 */
			/*electionYear = Long
					.parseLong(electionDAO
							.findLatestElectionAssemblyElectionYearForState(
									IConstants.ASSEMBLY_ELECTION_TYPE, stateId,
									IConstants.ELECTION_SUBTYPE_MAIN).get(0)
							.toString());*/
			
			
			List latestElectionYear = electionDAO.findLatestElectionYearHappenedInState(stateId,IConstants.ASSEMBLY_ELECTION_TYPE,IConstants.ELECTION_SUBTYPE_MAIN);
			Object latestYear = (Object)latestElectionYear.get(0);
			
			electionYear = Long.parseLong((String)latestYear);
			log.debug("DistrictPageService.getConstituenciesWinnerInfo() delimitationYear:"
							+ electionYear);
			ConstituenciesStatusVO constituenciesStatusVO = getConstituenciesForDistrict(districtId, electionYear, IConstants.ASSEMBLY_ELECTION_TYPE);
			List<SelectOptionVO> constituencies = (constituenciesStatusVO.getExistConstituencies());
			constituencies.addAll(constituenciesStatusVO.getNewConstituencies());

			constituenciesStatusVO.setTotalConstituenciesAfterDelimitation(constituencies.size());
			constituenciesStatusVO.setTotalDeletedConstituencies(constituenciesStatusVO.getDeletedConstituencies().size());
			List<ConstituencyWinnerInfoVO> constituencyWinnerInfoVOList = new ArrayList<ConstituencyWinnerInfoVO>();
			StringBuilder constituencyIDs = new StringBuilder();
			HashMap<Long, Long> constituencyIdsAndYears = new HashMap<Long, Long>();

			for (SelectOptionVO constituency : constituencies) {
				constituencyIDs.append(",").append(constituency.getId());
				constituencyIds.add(constituency.getId());
				constituencyIdsAndYears.put(constituency.getId(), electionYear);
			}

			/*
			 * log.debug("DistrictPageService.getConstituenciesWinnerInfo() constituencies:"
			 * +constituencyIDs);
			 * 
			 * yearsList =
			 * constituencyElectionDAO.getLatestReservationZone(constituencyIds
			 * );
			 * 
			 * for(int i=0;i<yearsList.size();i++){ Object[] params =
			 * (Object[])yearsList.get(i); Long constituencyId = new
			 * Long(params[2].toString()); Long year = new
			 * Long(params[1].toString());
			 * if(constituencyIdsAndYears.containsKey(constituencyId) &&
			 * (constituencyIdsAndYears.get(constituencyId)<year)){
			 * constituencyIdsAndYears.put(constituencyId, year); }else{
			 * constituencyIdsAndYears.put(constituencyId, new
			 * Long(params[1].toString())); } }
			 */
			constituencies.removeAll(constituenciesStatusVO.getNewConstituencies());
			StringBuilder parliamentIDs = new StringBuilder();

			
			for (Map.Entry<Long, Long> result : constituencyIdsAndYears.entrySet()) {
				
				// Modified By Sai
				List recentResultYear = constituencyElectionDAO.getLatestResultsElectionYearInAConstituency(result.getKey());
				
				if(recentResultYear != null && recentResultYear.size() > 0)
				{
					Object value = (Object)recentResultYear.get(0);
					String latestElecYearInConsti = (String)value;
					//
					
					//List candidates =  nominationDAO.findCandidateNamePartyByConstituencyAndElection(result.getKey().toString(),result.getValue().toString());
					List candidates =  nominationDAO.findCandidateNamePartyByConstituencyAndElection(result.getKey().toString(),latestElecYearInConsti);
				
					for (int i = 0; i < candidates.size(); i++) {
						ConstituencyWinnerInfoVO constituencyWinnerInfoVO = new ConstituencyWinnerInfoVO();
						Object[] obj = (Object[]) candidates.get(i);
						constituencyWinnerInfoVO.setConstituencyName(obj[0].toString());
						constituencyWinnerInfoVO.setCandidateName(obj[1].toString());
						constituencyWinnerInfoVO.setCandidateId(obj[4].toString());
						constituencyWinnerInfoVO.setConstituencyId(obj[3].toString());
						constituencyWinnerInfoVO.setElectionYear(latestElecYearInConsti);
						constituencyWinnerInfoVO.setReservationZone(obj[6].toString());
						
						List list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(Long.parseLong(obj[3].toString()));
						
						for (int j = 0; j < list.size(); j++) {
							Object[] params = (Object[]) list.get(j);
							Long id = Long.parseLong(params[0].toString());
							constituencyWinnerInfoVO.setParliamentConstituencyId(id);
							constituencyWinnerInfoVO.setParliamentConstituencyName(params[1].toString());
							parliamentIDs.append(",").append(params[0].toString());
							parliamentConstituencies.add(Long.parseLong(params[0].toString()));
						}
						constituencyWinnerInfoVO.setPartyName(obj[2].toString());
						if (obj[5] != null) {
							constituencyWinnerInfoVO.setPartyFlag(obj[5].toString());
						}
						constituencyWinnerInfoVOList.add(constituencyWinnerInfoVO);
					}
				}
			}
			/*
			 * List parliamentCandidates =
			 * nominationDAO.findCandidateNamePartyByConstituencyAndElection
			 * (parliamentIDs.substring(1),electionYear.toString()); for(int
			 * k=0;k<constituencyWinnerInfoVOList.size();k++){ for(int
			 * l=0;l<parliamentCandidates.size();l++){ Object[]
			 * parliamentCandidatesObj = (Object[]) parliamentCandidates.get(l);
			 * if
			 * (constituencyWinnerInfoVOList.get(k).getParliamentConstituencyId
			 * ().equals(new Long(parliamentCandidatesObj[3].toString()))){
			 * constituencyWinnerInfoVOList
			 * .get(k).setParliamentReservationZone(parliamentCandidatesObj
			 * [6].toString()); } } }
			 */
			constituenciesStatusVO
					.setConstituencyWinnerInfoVO(constituencyWinnerInfoVOList);
			constituenciesStatusVO.setDelimitationYear(electionYear);
			constituenciesStatusVO.setElectionType(electionYear.toString());
			constituenciesStatusVO
					.setElectionType(IConstants.ASSEMBLY_ELECTION_TYPE);
			constituenciesStatusVO.setElectionYear(electionYear.toString());
			constituenciesStatusVO
					.setElectionType(IConstants.ASSEMBLY_ELECTION_TYPE);
			constituenciesStatusVO
					.setParliamentConstituencies(parliamentConstituencies);
			return constituenciesStatusVO;
		} catch (Exception e) {
			log.debug("Exception raised--->");
			log.error(e);
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * This method takes District Id as input and retrives all the
	 * constituencies that are present in that particular district. And it sets
	 * the constituencies names and corresponding Id's in the Data transfer
	 * Object.
	 */

	@SuppressWarnings("unchecked")
	public ConstituenciesStatusVO getConstituenciesForDistrict(Long districtId,
			Long electionYear, String electionType) {
		ConstituenciesStatusVO constituencyVO = new ConstituenciesStatusVO();
		List<SelectOptionVO> deleteList = null;
		List<SelectOptionVO> existList = null;
		List<SelectOptionVO> newList = null;
		try {
			log.info("Entered in to getConstituenciesForDistrict() method...");
			log.info("Making constituencyDAO.findConstituencyByDistrictElectionType(districtId,electionType) DAO call...");
			List result = constituencyDAO.findConstituencyByDistrictElectionType(districtId,electionType);

			deleteList = constituencyVO.getDeletedConstituencies();
			if (deleteList == null) {
				log.warn("Could not initialize the list ");
			}
			existList = constituencyVO.getExistConstituencies();
			if (existList == null) {
				log.warn("Could not initialize the list ");
			}
			newList = constituencyVO.getNewConstituencies();
			if (newList == null) {
				log.warn("Could not initialize the list ");
			}
			log
					.info("Iterating loop to set the data in to the corresponding lists ");
			for (int i = 0; i < result.size(); i++) {
				Object[] parms = (Object[]) result.get(i);
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				if (parms[2] != null && parms[3] == null
						&& parms[2].toString().equals(electionYear.toString())) {
					selectOptionVO.setId(Long.parseLong(parms[0].toString()));
					selectOptionVO.setName(parms[1].toString());
					newList.add(selectOptionVO);
				} else if (parms[3] == null) {
					selectOptionVO.setId(Long.parseLong(parms[0].toString()));
					selectOptionVO.setName(parms[1].toString());
					existList.add(selectOptionVO);
				} else if (parms[3] != null
						&& parms[3].toString().equals(electionYear.toString())) {
					selectOptionVO.setId(Long.parseLong(parms[0].toString()));
					selectOptionVO.setName(parms[1].toString());
					deleteList.add(selectOptionVO);
				}
			}

			return constituencyVO;
		} catch (Exception e) {
			log.debug("Exception raised--->");
			log.error(e);
			return null;
		}
	}

	/*
	 * This method takes District Id as input and retrives all the mandals that
	 * are present in that particular district. And it sets the constituencies
	 * names and corresponding Id's in the Data transfer Object.
	 */
	@SuppressWarnings("unchecked")
	public List<MandalVO> getMandalsForDistrict(Long districtId) {
		List tehsil = tehsilDAO.findTehsilsByDistrict(districtId);
		List<MandalVO> mandal = new ArrayList<MandalVO>();
		if (log.isDebugEnabled())
			log.debug("Entered into getMandalsForDistrict method....");
		try {
			for (int i = 0; i < tehsil.size(); i++) {
				Object[] result = (Object[]) tehsil.get(i);
				MandalVO objVO = new MandalVO();
				objVO.setName(result[1].toString());
				objVO.setId((Long) result[0]);
				mandal.add(objVO);
			}
		} catch (Exception ex) {
			log.debug("Exception Raised -->" + ex);
			return null;
		}
		return mandal;
	}

	public List<ConstituencyElection> getConstituencyElectionsFromNomination(
			Long electionID, Long stateId, Long districtID, Long rank,
			Long partyId, String reportLevel) {
		List<ConstituencyElection> constituencyElectionList = null;

		if ("3".equalsIgnoreCase(reportLevel)) {
			log.debug(" DAO .for Country Level ...");
			if (rank.intValue() == -1)
				constituencyElectionList = nominationDAO
						.findByElectionAndPartyAndNthRank(electionID, new Long(
								4), partyId);
			else
				constituencyElectionList = nominationDAO
						.findByElectionAndPartyAndRank(electionID, rank,
								partyId);
		} else {
			if (districtID == null || districtID == 0L) {
				log.debug(" DAO .for District Level ...");
				if (rank.intValue() == -1)
					constituencyElectionList = nominationDAO
							.findByElectionAndStateAndNthRank(electionID,
									stateId, new Long(4), partyId);
				else
					constituencyElectionList = nominationDAO
							.findByElectionAndStateAndRank(electionID, stateId,
									rank, partyId);
			} else {
				log.debug(" DAO .for State Level ...");
				if (rank.intValue() == -1)
					constituencyElectionList = nominationDAO
							.findByElectionAndStateAndDistrictAndNthRank(
									electionID, stateId, districtID,
									new Long(4), partyId);
				else
					constituencyElectionList = nominationDAO
							.findByElectionAndStateAndDistrictAndRank(
									electionID, stateId, districtID, rank,
									partyId);
			}
		}
		return constituencyElectionList;
	}

	public List<ConstituencyElection> getConstituencyElectionsFromNominationWithAlliances(
			Long electionID, Long stateId, Long districtID, Long rank,
			List<SelectOptionVO> parties) {
		List<ConstituencyElection> constituencyElectionList = null;
		List<Long> partyIds = null;
		if (parties != null && parties.size() > 0) {
			partyIds = new ArrayList<Long>();
			for (SelectOptionVO party : parties)
				partyIds.add(party.getId());
		}
		if (districtID == null || districtID == 0L) {
			System.out.println(" DAO ...1");
			if (rank.intValue() == -1)
				constituencyElectionList = nominationDAO
						.findByElectionAndStateAndNthRank(electionID, stateId,
								new Long(4), partyIds);
			else
				constituencyElectionList = nominationDAO
						.findByElectionAndStateAndRank(electionID, stateId,
								rank, partyIds);
		} else {
			System.out.println("DAO ... 2");
			if (rank.intValue() == -1)
				constituencyElectionList = nominationDAO
						.findByElectionAndStateAndDistrictAndNthRank(
								electionID, stateId, districtID, new Long(4),
								partyIds);
			else
				constituencyElectionList = nominationDAO
						.findByElectionAndStateAndDistrictAndRank(electionID,
								stateId, districtID, rank, partyIds);
		}

		return constituencyElectionList;
	}

	public List<ConstituencyElection> getConstituencyElectionsFromNominationForCountry(
			Long electionID, Long stateId, Long countryId, Long rank,
			Long partyId) {
		List<ConstituencyElection> constituencyElectionList = null;
		log
				.debug("Inside getConstituencyElectionsFromNominationForCountry(staticDataService).....");
		if (rank.intValue() > 0)
			constituencyElectionList = nominationDAO
					.findByElectionIdAndStateIdAndCountryIdAndRank(electionID,
							stateId, countryId, rank, partyId);
		else
			constituencyElectionList = nominationDAO
					.findByElectionIdAndStateIdAndCountryIdAndNthRank(
							electionID, stateId, countryId, rank, partyId);
		return constituencyElectionList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.itgrids.partyanalyst.service.IStaticDataService#
	 * getPartyElectionResultsForAParty(java.lang.Long, java.lang.Long) This
	 * method electionResults for a party,like totalseats won,total votes
	 * percentage,total constituencies participated ..... Input for this method
	 * is election_id and party_id
	 */
	public PartyElectionResult getPartyElectionResultsForAParty(
			Long electionId, Long partyId) {
		log.debug("Inside getPartyElectionResultsForAParty()");
		if (electionId != null && partyId != null) {
			List<PartyElectionResult> partyElectionResultsList = partyElectionResultDAO
					.getByElectionAndParty(electionId, partyId);
			if (partyElectionResultsList != null
					&& partyElectionResultsList.size() > 0) {
				return partyElectionResultsList.get(0);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.itgrids.partyanalyst.service.IStaticDataService#
	 * savePartyElectionResultForAPartyForAElection(java.lang.Long,
	 * java.lang.Long) This method saves electionResults for a party into
	 * party_election_result tableInput for this method is election_id and
	 * party_id
	 */
	public PartyElectionResult savePartyElectionResultForAPartyForAElection(
			Long electionId, Long partyId) {
		log.debug("Inside savePartyElectionResultForAPartyForAElection()");
		PartyElectionResult partyElectionResult = null;
		List<Nomination> nominations = null;
		Election election = null;
		Party party = null;
		Long completeValidVotes = new Long(0);
		Long totalSeatsWon = new Long(0);
		Long totalSecondPositions = new Long(0);
		Long totalThirdPositions = new Long(0);
		Long totalFourthPositions = new Long(0);
		Long totalNthPositions = new Long(0);
		Long totalConstiParticipated = new Long(0);
		Double totalVotesEarned = new Double(0);
		Double totalValidVotes = new Double(0);
		Double totalVotesPercentage = new Double(0);
		Double completeVotesPercent = new Double(0);
		try {
			if (electionId != null && partyId != null) {
				nominations = nominationDAO.findByElectionIdAndPartyId(
						electionId, partyId);
				election = electionDAO.get(electionId);
				party = partyDAO.get(partyId);
				if (nominations != null && nominations.size() > 0
						&& election != null && party != null) {
					completeValidVotes = getCompleteValidVotes(electionId);
					for (Nomination nominationForParty : nominations) {
						if (nominationForParty.getParty().getPartyId().equals(
								partyId)) {
							Long candidRank = nominationForParty
									.getCandidateResult().getRank();
							Double votesEarned = nominationForParty
									.getCandidateResult().getVotesEarned();
							Double validVotes = nominationForParty
									.getConstituencyElection()
									.getConstituencyElectionResult()
									.getValidVotes();
							totalVotesEarned += votesEarned;
							totalValidVotes += validVotes;

							totalConstiParticipated++;
							if (candidRank.equals(new Long(1)))
								totalSeatsWon++;
							else if (candidRank.equals(new Long(2)))
								totalSecondPositions++;
							else if (candidRank.equals(new Long(3)))
								totalThirdPositions++;
							else if (candidRank.equals(new Long(4)))
								totalFourthPositions++;
							else if (candidRank > new Long(4))
								totalNthPositions++;
						}
					}
					totalVotesPercentage = new BigDecimal(
							(totalVotesEarned * 100) / totalValidVotes)
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
					completeVotesPercent = new BigDecimal(
							(totalVotesEarned * 100) / completeValidVotes)
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();

					partyElectionResult = savePartyElectionResult(election,
							party, totalSeatsWon, totalSecondPositions,
							totalThirdPositions, totalFourthPositions,
							totalNthPositions, totalConstiParticipated,
							totalVotesPercentage, completeVotesPercent,
							totalVotesEarned, totalValidVotes,
							completeValidVotes.doubleValue());
				}
			}
		} catch (Exception ex) {
			log.debug("Exception raised ::" + ex);
			ex.printStackTrace();
		}

		return partyElectionResult;
	}

	@SuppressWarnings("unchecked")
	public Long getCompleteValidVotes(Long electionId) throws Exception {
		Long completeValidVotes = new Long(0);
		List list = constituencyElectionDAO
				.findTotalValidVotesForAnElectionForAState(electionId);
		if (list != null) {
			Object params = (Object) list.get(0);
			Double validVotes = (Double) params;
			completeValidVotes = validVotes.longValue();
		}
		return completeValidVotes;
	}

	@SuppressWarnings("unchecked")
	public Long getCompleteValidVotesInState(Long electionId, Long stateId)
			throws Exception {
		Long completeValidVotes = new Long(0);
		List list = constituencyElectionDAO
				.findTotalValidVotesForAnElectionForAnState(electionId, stateId);
		if (list != null) {
			Object params = (Object) list.get(0);
			Double validVotes = (Double) params;
			completeValidVotes = validVotes.longValue();
		}
		return completeValidVotes;
	}

	public PartyElectionResult savePartyElectionResult(final Election election,
			final Party party, final Long totalSeatsWon, final Long secPos,
			final Long thirdPos, final Long fourthPos, final Long nthPos,
			final Long totConstiParticipated,
			final Double totalVotesPercentage,
			final Double completeVotesPercent, final Double totalVotesEarned,
			final Double totalValidVotes, final Double completeConstiValidVotes)
			throws Exception {
		log.debug("Inside savePartyElectionResult()");
		PartyElectionResult partyElectionResultFinal = (PartyElectionResult) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						PartyElectionResult partyElectionResult = null;
						try {
							java.util.Date updatedDate = new java.util.Date();
							String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
							SimpleDateFormat sdf = new SimpleDateFormat(
									DATE_FORMAT);
							String strDateNew = sdf.format(updatedDate);
							updatedDate = sdf.parse(strDateNew);
							if (election != null && party != null) {
								partyElectionResult = new PartyElectionResult();
								partyElectionResult.setElection(election);
								partyElectionResult.setParty(party);
								partyElectionResult
										.setTotalSeatsWon(totalSeatsWon
												.toString());
								partyElectionResult.setSecondPosWon(secPos
										.toString());
								partyElectionResult.setThirdPosWon(thirdPos
										.toString());
								partyElectionResult.setFourthPosWon(fourthPos
										.toString());
								partyElectionResult.setNthPosWon(nthPos
										.toString());
								partyElectionResult
										.setTotalConstiParticipated(totConstiParticipated
												.toString());
								partyElectionResult
										.setVotesPercentage(totalVotesPercentage
												.toString());
								partyElectionResult
										.setCompleteVotesPercent(completeVotesPercent
												.toString());
								partyElectionResult.setLastUpdated(updatedDate);
								partyElectionResult
										.setTotalVotesGained(totalVotesEarned);
								partyElectionResult
										.setTotalValidVotes(totalValidVotes);
								partyElectionResult
										.setCompleteConstiValidVotes(completeConstiValidVotes);
								partyElectionResult = partyElectionResultDAO
										.save(partyElectionResult);
							}
						} catch (Exception ex) {
							ex.printStackTrace();
							log.debug("Exception Raised : " + ex);
							status.setRollbackOnly();
						}
						return partyElectionResult;
					}
				});

		return partyElectionResultFinal;
	}

	public PartyElectionDistrictResult getPartyElectionResultsForAPartyDistrictLevel(
			Long electionId, Long partyId, Long districtId) {
		// System.out.println("Inside getPartyElectionResultsForAPartyDistrictLevel()");
		if (electionId != null && partyId != null && districtId != null) {
			List<PartyElectionDistrictResult> partyElecDistrictResultList = partyElectionDistrictResultDAO
					.getByPartyIdElectionIdAndDistrict(electionId, partyId,
							districtId);
			if (partyElecDistrictResultList != null
					&& partyElecDistrictResultList.size() > 0) {
				return partyElecDistrictResultList.get(0);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Long getCompleteValidVotesForADistrict(Long electionTypeId,
			Long electionId, Long districtId) throws Exception {
		Long completeValidVotes = new Long(0);
		List list = null;

		Long localElectionBodyCount = checkForLocalElectionType(electionTypeId);
		if (localElectionBodyCount > 0) {
			list = constituencyElectionDAO
					.findTotalValidVotesForAnElectionForAStateAndDistrictForLocalElectionBody(
							electionId, districtId);
		} else {
			list = constituencyElectionDAO
					.findTotalValidVotesForAnElectionForAStateAndDistrict(
							electionId, districtId);
		}
		if (list != null) {
			Object params = (Object) list.get(0);
			Double validVotes = (Double) params;
			completeValidVotes = validVotes.longValue();
		}
		return completeValidVotes;
	}

	public PartyElectionDistrictResult savePartyElectionResultForAPartyForAElectionDistrictLevel(
			Long electionId, Long partyId, Long districtId) {

		PartyElectionDistrictResult partyElectionDistrictResult = null;
		List<Nomination> nominations = null;
		Election election = null;
		Party party = null;
		District district = null;
		Long totalSeatsWon = new Long(0);
		Long totalSecondPositions = new Long(0);
		Long totalThirdPositions = new Long(0);
		Long totalFourthPositions = new Long(0);
		Long totalNthPositions = new Long(0);
		Long totalConstiParticipated = new Long(0);
		Double totalVotesEarned = new Double(0);
		Double totalValidVotes = new Double(0);
		Double totalVotesPercentage = new Double(0);
		Long completeValidVotes = new Long(0);
		Double completeVotesPercent = new Double(0);

		try {
			if (electionId != null && partyId != null && districtId != null) {

				// nominations =
				// nominationDAO.findByElectionIdAndPartyIdStateIdAndDistrictId(electionId,
				// partyId, districtId);
				election = electionDAO.get(electionId);
				party = partyDAO.get(partyId);
				district = districtDAO.get(districtId);
				Long electionTypeId = election.getElectionScope()
						.getElectionType().getElectionTypeId();

				nominations = checkAndGetNominationsInAnElectionForAPartyInADistrict(
						electionTypeId, electionId, partyId, districtId);

				if (nominations != null && nominations.size() > 0
						&& election != null && party != null
						&& district != null) {
					completeValidVotes = getCompleteValidVotesForADistrict(
							electionTypeId, electionId, districtId);
					for (Nomination nominationForParty : nominations) {
						if (nominationForParty.getParty().getPartyId().equals(
								partyId)) {
							Long candidRank = nominationForParty
									.getCandidateResult().getRank();
							Double votesEarned = nominationForParty
									.getCandidateResult().getVotesEarned();
							Double validVotes = nominationForParty
									.getConstituencyElection()
									.getConstituencyElectionResult()
									.getValidVotes();
							totalVotesEarned += votesEarned;
							totalValidVotes += validVotes;

							totalConstiParticipated++;
							if (candidRank.equals(new Long(1)))
								totalSeatsWon++;
							else if (candidRank.equals(new Long(2)))
								totalSecondPositions++;
							else if (candidRank.equals(new Long(3)))
								totalThirdPositions++;
							else if (candidRank.equals(new Long(4)))
								totalFourthPositions++;
							else if (candidRank > new Long(4))
								totalNthPositions++;
						}
					}
					totalVotesPercentage = new BigDecimal(
							(totalVotesEarned * 100) / totalValidVotes)
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
					completeVotesPercent = new BigDecimal(
							(totalVotesEarned * 100) / completeValidVotes)
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
					partyElectionDistrictResult = savePartyElectionDistrictResult(
							election, party, district, totalSeatsWon,
							totalSecondPositions, totalThirdPositions,
							totalFourthPositions, totalNthPositions,
							totalConstiParticipated, totalVotesPercentage,
							completeVotesPercent, totalVotesEarned,
							totalValidVotes, completeValidVotes.doubleValue());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug("Exception raised ::" + ex);
		}
		return partyElectionDistrictResult;
	}

	/*
	 * Method To Check Wheather The Election Type As LocalElectionBody Election
	 * Type Or Not And Get Nominations Based On The Value
	 */
	@SuppressWarnings("unchecked")
	public List<Nomination> checkAndGetNominationsInAnElectionForAPartyInADistrict(
			Long electionTypeId, Long electionId, Long partyId, Long districtId) {

		List<Nomination> nominations = null;
		Long localBodyCount = checkForLocalElectionType(electionTypeId);

		if (localBodyCount > 0) {
			nominations = nominationDAO
					.findByElectionIdAndPartyIdStateIdAndDistrictIdForLocalElectionBodys(
							electionId, partyId, districtId);
		} else {
			nominations = nominationDAO
					.findByElectionIdAndPartyIdStateIdAndDistrictId(electionId,
							partyId, districtId);
		}

		return nominations;
	}

	/*
	 * Method To check Local Election Body Type Or Not
	 */
	@SuppressWarnings("unchecked")
	public Long checkForLocalElectionType(Long electionTypeId) {
		Long localBodyCount = 0L;

		List localBodyCheck = localElectionBodyDAO
				.getCountOfLocalBodysForALocalElectionBodyType(electionTypeId);
		if (localBodyCheck != null) {
			Object values = (Object) localBodyCheck.get(0);
			localBodyCount = (Long) values;
		}
		return localBodyCount;
	}

	public PartyElectionDistrictResult savePartyElectionDistrictResult(
			final Election election, final Party party,
			final District district, final Long totalSeatsWon,
			final Long secPos, final Long thirdPos, final Long fourthPos,
			final Long nthPos, final Long totConstiParticipated,
			final Double totalVotesPercentage,
			final Double completeVotesPercent, final Double totalVotesGained,
			final Double totalValidVotes, final Double completeConstiValidVotes)
			throws Exception {

		PartyElectionDistrictResult partyElectionDistrictResultFinal = (PartyElectionDistrictResult) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						PartyElectionDistrictResult partyElectionDistrictResult = null;
						try {
							java.util.Date updatedDate = new java.util.Date();
							String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
							SimpleDateFormat sdf = new SimpleDateFormat(
									DATE_FORMAT);
							String strDateNew = sdf.format(updatedDate);
							updatedDate = sdf.parse(strDateNew);
							if (election != null && party != null) {
								partyElectionDistrictResult = new PartyElectionDistrictResult();
								partyElectionDistrictResult
										.setElection(election);
								partyElectionDistrictResult.setParty(party);
								partyElectionDistrictResult
										.setDistrict(district);
								partyElectionDistrictResult
										.setTotalSeatsWon(totalSeatsWon
												.toString());
								partyElectionDistrictResult
										.setSecondPosWon(secPos.toString());
								partyElectionDistrictResult
										.setThirdPosWon(thirdPos.toString());
								partyElectionDistrictResult
										.setFourthPosWon(fourthPos.toString());
								partyElectionDistrictResult.setNthPosWon(nthPos
										.toString());
								partyElectionDistrictResult
										.setTotalConstiParticipated(totConstiParticipated
												.toString());
								partyElectionDistrictResult
										.setVotesPercentage(totalVotesPercentage
												.toString());
								partyElectionDistrictResult
										.setCompleteVotesPercent(completeVotesPercent
												.toString());
								partyElectionDistrictResult
										.setLastUpdated(updatedDate);
								partyElectionDistrictResult
										.setTotalVotesGained(totalVotesGained);
								partyElectionDistrictResult
										.setTotalValidVotes(totalValidVotes);
								partyElectionDistrictResult
										.setCompleteConstiValidVotes(completeConstiValidVotes);
								partyElectionDistrictResult = partyElectionDistrictResultDAO
										.save(partyElectionDistrictResult);
							}
						} catch (Exception ex) {
							ex.printStackTrace();
							log.debug("Exception Raised : " + ex);
							status.setRollbackOnly();
						}
						return partyElectionDistrictResult;
					}
				});
		return partyElectionDistrictResultFinal;
	}

	public List<DistrictWisePartyResultVO> getDistrictWisePartyElectionResults(
			String electionType, Long electionId, String partyIds,
			Boolean hasAlliance) {
		Map<Long, ElectionInfoVO> constituenciesInDistOrState = new LinkedHashMap<Long, ElectionInfoVO>();
		Map<Long, Long> districtIdAndValidVotesWhenAllianceConsideredList = new LinkedHashMap<Long, Long>();
		Map<Long, Long> constituenciesWonInDistOrState = new LinkedHashMap<Long, Long>();
		List<DistrictWisePartyResultVO> districtWisePartyResultVOList = new ArrayList<DistrictWisePartyResultVO>();
		List validVotesWhenAllianceConsidered = new ArrayList();
		DistrictWisePartyResultVO districtWisePartyResultVO = null;
		ElectionInfoVO electionInfoVO = null;
		Long stateOrDistrictId;
		String districtName;
		List totalConstituenciesList = null;
		List paricipatedConstituenciesList = null;
		List wonConstituenciesList = null;
		Long totalConstituencies;
		Long totalValidVotes;
		Long participatedConstituencies;
		Long seatsWon;
		Long votesEarned;
		Double percenatage;
		Double overallPercent;
		Long validVotes;

		/*
		 * stateId or DIstrictId - 0, state/District name -1, Participated
		 * constituencies - 2, partyId - 3, partyName - 4, validVotes -5,
		 * votesEarned - 6
		 */
		if (IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)) {
			totalConstituenciesList = constituencyElectionDAO
					.findConstituenciesByElectionGroupByDistrict(electionId);
			paricipatedConstituenciesList = nominationDAO
					.findPartiesInfoByElectionAndPartyGroupByDistrict(
							electionId, partyIds);
			wonConstituenciesList = nominationDAO
					.findPartyWonConstituenciesInfoByElectionAndPartyGroupByDistrict(
							electionId, partyIds, 1l);
			if (hasAlliance) {
				validVotesWhenAllianceConsidered = constituencyElectionDAO
						.findPartyvalidVotesInfoByElectionAndPartyGroupByDistrictId(
								electionId, partyIds);
				for (int i = 0; i < validVotesWhenAllianceConsidered.size(); i++) {
					Object[] parms = (Object[]) validVotesWhenAllianceConsidered
							.get(i);
					Double votes = Double.parseDouble(parms[1].toString());
					districtIdAndValidVotesWhenAllianceConsideredList.put(Long
							.parseLong(parms[0].toString()), votes.longValue());
				}
			}
		}

		if (IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType)) {
			totalConstituenciesList = constituencyElectionDAO
					.findConstituenciesByElectionGroupByState(electionId);
			paricipatedConstituenciesList = nominationDAO
					.findPartiesInfoByElectionAndPartyGroupByState(electionId,
							partyIds);
			wonConstituenciesList = nominationDAO
					.findPartyWonConstituenciesInfoByElectionAndPartyGroupByState(
							electionId, partyIds, 1l);
			if (hasAlliance) {
				validVotesWhenAllianceConsidered = constituencyElectionDAO
						.findPartyvalidVotesInfoByElectionAndPartyGroupByStateId(
								electionId, partyIds);
				for (int i = 0; i < validVotesWhenAllianceConsidered.size(); i++) {
					Object[] parms = (Object[]) validVotesWhenAllianceConsidered
							.get(i);
					Double votes = Double.parseDouble(parms[1].toString());
					districtIdAndValidVotesWhenAllianceConsideredList.put(Long
							.parseLong(parms[0].toString()), votes.longValue());
				}
			}
		}

		for (Object[] values : (List<Object[]>) totalConstituenciesList) {
			electionInfoVO = new ElectionInfoVO();
			electionInfoVO.setTotalConstituencies((Long) values[1]);
			electionInfoVO.setTotalValidVotes(((Double) values[2]).longValue());
			electionInfoVO.setConstituencyName(values[3].toString());// 3rd
																		// change
			constituenciesInDistOrState.put((Long) values[0], electionInfoVO);
		}

		for (Object[] values : (List<Object[]>) wonConstituenciesList)
			constituenciesWonInDistOrState.put((Long) values[0],
					(Long) values[2]);

		for (Object[] values : (List<Object[]>) paricipatedConstituenciesList) {
			districtWisePartyResultVO = new DistrictWisePartyResultVO();
			stateOrDistrictId = (Long) values[0];
			districtName = values[1].toString();
			electionInfoVO = constituenciesInDistOrState.get(stateOrDistrictId);
			constituenciesInDistOrState.remove(stateOrDistrictId);// 1st
																	// modification
			totalConstituencies = electionInfoVO.getTotalConstituencies();
			totalValidVotes = electionInfoVO.getTotalValidVotes();
			participatedConstituencies = (Long) values[2];
			seatsWon = constituenciesWonInDistOrState.get(stateOrDistrictId);
			if (seatsWon == null)
				seatsWon = 0l;

			if (hasAlliance) {
				validVotes = districtIdAndValidVotesWhenAllianceConsideredList
						.get(stateOrDistrictId);
			} else {
				validVotes = ((Double) values[5]).longValue();
			}

			votesEarned = ((Double) values[6]).longValue();
			percenatage = new BigDecimal(votesEarned * 100.0 / validVotes)
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			overallPercent = new BigDecimal(votesEarned * 100.0
					/ totalValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
			// System.out.println(districtName+"\t"+percenatage+"\t"+overallPercent+"\t"+votesEarned+"\t"+validVotes+"\t"+totalValidVotes+"\t"+electionId);
			districtWisePartyResultVO.setDistrictId(stateOrDistrictId);
			districtWisePartyResultVO.setDistrictName(districtName);
			districtWisePartyResultVO
					.setTotalConstituencies(participatedConstituencies);
			districtWisePartyResultVO.setConstiCount(totalConstituencies);
			districtWisePartyResultVO
					.setConstiParticipated(participatedConstituencies);
			districtWisePartyResultVO.setSeatsWon(seatsWon);
			districtWisePartyResultVO.setVotesPercent(percenatage);
			districtWisePartyResultVO.setTotalPercentage(overallPercent);
			districtWisePartyResultVOList.add(districtWisePartyResultVO);
		}
		// 2nd Modification....
		for (Entry<Long, ElectionInfoVO> values : constituenciesInDistOrState
				.entrySet()) {
			electionInfoVO = constituenciesInDistOrState.get(values.getKey());
			districtWisePartyResultVO = new DistrictWisePartyResultVO();
			districtWisePartyResultVO.setDistrictId(values.getKey());
			districtWisePartyResultVO.setDistrictName(electionInfoVO
					.getConstituencyName());
			districtWisePartyResultVO.setTotalConstituencies(0l);
			districtWisePartyResultVO.setConstiCount(electionInfoVO
					.getTotalConstituencies());
			districtWisePartyResultVO.setConstiParticipated(0l);
			districtWisePartyResultVO.setSeatsWon(0l);
			districtWisePartyResultVO.setVotesPercent(0d);
			districtWisePartyResultVO.setTotalPercentage(0d);
			districtWisePartyResultVOList.add(districtWisePartyResultVO);
		}
		return districtWisePartyResultVOList;
	}

	// Modified By Siva For Parliament Implementation End

	public Map<Long, List<PartyResultVO>> getDistrictWisePartyElectionResultWithoutAllianc(
			String electionYear, Long electionId, Long partyId) {

		List<ConstituencyElection> constiElections = null;
		Map<Long, List<PartyResultVO>> districtWiseResultsMap = null;

		log
				.debug("Entered Into getDistrictWisePartyElectionResultWithoutAllianc Method .....");
		constiElections = nominationDAO
				.findConstituencyElectionByElectionIdAndPartyId(electionId,
						partyId);
		if (constiElections != null) {
			List<PartyResultVO> partyResultsList = null;
			districtWiseResultsMap = new HashMap<Long, List<PartyResultVO>>();
			for (ConstituencyElection constiElecResults : constiElections) {
				PartyResultVO partyResultVO = null;
				Long districtId = constiElecResults.getConstituency()
						.getDistrict().getDistrictId();

				if (districtWiseResultsMap.isEmpty()
						|| !districtWiseResultsMap.containsKey(districtId))
					partyResultsList = new ArrayList<PartyResultVO>();
				else
					partyResultsList = districtWiseResultsMap.get(districtId);

				partyResultVO = getCandidateResultDetails(constiElecResults,
						partyId);
				partyResultsList.add(partyResultVO);

				districtWiseResultsMap.put(districtId, partyResultsList);
			}

		}

		return districtWiseResultsMap;
	}

	public Map<Long, List<PartyResultVO>> getDistrictWisePartyElectionResultWithAllianc(
			String electionYear, Long electionId, Long partyId,
			List<Party> alliancPartys) {
		List<ConstituencyElection> constiElections = null;
		Map<Long, List<PartyResultVO>> districtWiseResultsMap = null;
		List<Long> partyIds = new ArrayList<Long>();

		log.debug("Entered Into getDistrictWisePartyElectionResultWithAllianc Method .....");
		for (Party party : alliancPartys)
			partyIds.add(party.getPartyId());

		constiElections = nominationDAO
				.findConstituencyElectionByElectionIdAndPartys(electionId,
						partyIds);

		log.debug("ConstiElections With Alliance size ::"
				+ constiElections.size());
		if (constiElections != null) {
			List<PartyResultVO> partyResultsList = null;
			List<Long> constiIds = new ArrayList<Long>();
			districtWiseResultsMap = new HashMap<Long, List<PartyResultVO>>();
			for (ConstituencyElection constiElecResults : constiElections) {

				if (constiIds.contains(constiElecResults.getConstituency()
						.getConstituencyId()))
					continue;
				PartyResultVO partyResultVO = null;
				constiIds.add(constiElecResults.getConstituency()
						.getConstituencyId());
				Long districtId = constiElecResults.getConstituency()
						.getDistrict().getDistrictId();
				if (districtWiseResultsMap.isEmpty()
						|| !districtWiseResultsMap.containsKey(districtId))
					partyResultsList = new ArrayList<PartyResultVO>();
				else
					partyResultsList = districtWiseResultsMap.get(districtId);

				log.debug("PartyResultsList With Alliance(for district"
						+ districtId + ") size ::" + partyResultsList.size());
				partyResultVO = getCandidateResultDetailsWithAllianc(
						constiElecResults, partyId, alliancPartys, partyIds);
				partyResultsList.add(partyResultVO);

				districtWiseResultsMap.put(districtId, partyResultsList);
			}

		}

		return districtWiseResultsMap;
	}

	public PartyResultVO getCandidateResultDetails(
			ConstituencyElection constiElecResults, Long partyId) {
		PartyResultVO partyResultVO = new PartyResultVO();
		CandidateOppositionVO oppositionCandidate = new CandidateOppositionVO();
		Set<Nomination> nominations = constiElecResults.getNominations();
		log.debug("Entered Into getCandidateResultDetails Method .....");
		if (nominations != null) {
			for (Nomination nominatn : nominations) {
				if (nominatn.getParty().getPartyId().equals(partyId)) {
					partyResultVO = getSelectedNominationResults(nominatn,
							partyId);
					Long rankOfCand = nominatn.getCandidateResult().getRank();
					oppositionCandidate = getOppCandidateResultDetails(
							rankOfCand, nominations, partyId);
					partyResultVO.setOppositionCandidates(oppositionCandidate);
					break;
				}
			}
		}

		return partyResultVO;
	}

	public PartyResultVO getSelectedNominationResults(Nomination nominatn,
			Long partyId) {
		PartyResultVO partyResultVO = new PartyResultVO();
		partyResultVO.setCandidateId(nominatn.getCandidate().getCandidateId());
		partyResultVO.setCandidateName(nominatn.getCandidate().getLastname()
				.toLowerCase());
		partyResultVO.setConstituencyId(nominatn.getConstituencyElection()
				.getConstituency().getConstituencyId());
		partyResultVO.setConstituencyName(nominatn.getConstituencyElection()
				.getConstituency().getName().toUpperCase());
		partyResultVO.setElectors(nominatn.getConstituencyElection()
				.getConstituencyElectionResult().getTotalVotes().longValue());
		partyResultVO.setPartyId(partyId);
		partyResultVO.setPartyName(nominatn.getParty().getShortName()
				.toUpperCase());
		partyResultVO.setVotesEarned(nominatn.getCandidateResult()
				.getVotesEarned().longValue());
		partyResultVO.setValidVotes(nominatn.getConstituencyElection()
				.getConstituencyElectionResult().getValidVotes().longValue());
		partyResultVO.setVotesPercent(nominatn.getCandidateResult()
				.getVotesPercengate());
		partyResultVO.setRank(nominatn.getCandidateResult().getRank());

		return partyResultVO;
	}

	public PartyResultVO getCandidateResultDetailsWithAllianc(
			ConstituencyElection constiElecResults, Long partyId,
			List<Party> alliancPartys, List<Long> alliancPartyIds) {
		PartyResultVO partyResultVO = new PartyResultVO();
		CandidateOppositionVO oppositionCandidate = new CandidateOppositionVO();
		Set<Nomination> nominations = constiElecResults.getNominations();
		log.debug("Entered Into getCandidateResultDetailsWithAllianc Method .....");
		if (nominations != null) {
			Boolean flag = false;
			Nomination selectdNominatn = null;
			for (Nomination nominatn : nominations) {
				if (nominatn.getParty().getPartyId().equals(partyId)) {
					partyResultVO = getSelectedNominationResults(nominatn,
							partyId);
					Long rankOfCand = nominatn.getCandidateResult().getRank();
					oppositionCandidate = getOppCandidateResultDetails(
							rankOfCand, nominations, partyId);
					partyResultVO.setOppositionCandidates(oppositionCandidate);
					break;
				} else if (alliancPartyIds.contains(nominatn.getParty()
						.getPartyId())) {
					flag = true;
					if (selectdNominatn != null) {
						if (nominatn.getCandidateResult().getRank() > selectdNominatn
								.getCandidateResult().getRank())
							selectdNominatn = nominatn;
					} else
						selectdNominatn = nominatn;
				}
			}
			if (flag == true && selectdNominatn != null) {
				partyResultVO = getSelectedNominationResults(selectdNominatn,
						selectdNominatn.getParty().getPartyId());
				Long rankOfCand = selectdNominatn.getCandidateResult()
						.getRank();
				oppositionCandidate = getOppCandidateResultDetails(rankOfCand,
						nominations, partyId);
				partyResultVO.setOppositionCandidates(oppositionCandidate);
			}
		}
		return partyResultVO;
	}

	public CandidateOppositionVO getOppCandidateResultDetails(Long rank,
			Set<Nomination> nominatns, Long partyId) {
		log.debug("Entered Into getOppCandidateResultDetails Method .....");
		CandidateOppositionVO oppositionCandidate = new CandidateOppositionVO();
		for (Nomination nomination : nominatns) {
			if (rank.equals(new Long(1))
					&& nomination.getCandidateResult().getRank().equals(
							new Long(2))) {
				oppositionCandidate.setCandidateId(nomination.getCandidate()
						.getCandidateId());
				oppositionCandidate.setCandidateName(nomination.getCandidate()
						.getLastname().toLowerCase());
				oppositionCandidate.setPartyId(nomination.getParty()
						.getPartyId());
				oppositionCandidate.setPartyName(nomination.getParty()
						.getShortName().toUpperCase());
				oppositionCandidate.setVotesEarned(nomination
						.getCandidateResult().getVotesEarned().toString());
				oppositionCandidate.setVotesPercentage(nomination
						.getCandidateResult().getVotesPercengate());
				oppositionCandidate.setRank(nomination.getCandidateResult()
						.getRank());
				break;
			} else if (rank > new Long(1)
					&& nomination.getCandidateResult().getRank().equals(
							new Long(1))) {
				oppositionCandidate.setCandidateId(nomination.getCandidate()
						.getCandidateId());
				oppositionCandidate.setCandidateName(nomination.getCandidate()
						.getLastname().toLowerCase());
				oppositionCandidate.setPartyId(nomination.getParty()
						.getPartyId());
				oppositionCandidate.setPartyName(nomination.getParty()
						.getShortName().toUpperCase());
				oppositionCandidate.setVotesEarned(nomination
						.getCandidateResult().getVotesEarned().toString());
				oppositionCandidate.setVotesPercentage(nomination
						.getCandidateResult().getVotesPercengate());
				oppositionCandidate.setRank(nomination.getCandidateResult()
						.getRank());
				break;
			}
		}

		return oppositionCandidate;
	}

	@SuppressWarnings("unchecked")
	public List<DistrictWisePartyResultVO> getResultsFromMap(
			Map<Long, List<PartyResultVO>> districtWiseResultsMap,
			Long electionId) {

		log.debug("Entered Into getResultsFromMap Method .....");
		List<DistrictWisePartyResultVO> districtWiseResults = null;
		if (!districtWiseResultsMap.isEmpty()) {
			districtWiseResults = new ArrayList<DistrictWisePartyResultVO>();
			Set entries = districtWiseResultsMap.entrySet();
			Iterator iterator = entries.iterator();
			while (iterator.hasNext()) {
				DistrictWisePartyResultVO resultForADist = new DistrictWisePartyResultVO();
				Map.Entry entry = (Map.Entry) iterator.next();
				List<PartyResultVO> partyResultsVO = (List<PartyResultVO>) entry
						.getValue();
				Long distId = (Long) entry.getKey();
				resultForADist = getDistrictCompleteDetails(distId,
						partyResultsVO, electionId);
				districtWiseResults.add(resultForADist);
			}
		}
		return districtWiseResults;
	}

	public DistrictWisePartyResultVO getDistrictCompleteDetails(
			Long districtId, List<PartyResultVO> partyResultsVO, Long electionId) {

		log.debug("Entered Into getDistrictCompleteDetails Method .....");

		DistrictWisePartyResultVO resultForADist = new DistrictWisePartyResultVO();
		District district = districtDAO.get(districtId);
		if (district == null)
			return null;

		int constiCount = 0;
		int seatsWon = 0;
		Long votesEarned = new Long(0);
		Long validVotes = new Long(0);
		List<Constituency> constituencys = constituencyElectionDAO
				.findConstituencyByElectionAndDistrict(electionId, districtId);
		if (constituencys != null && constituencys.size() > 0)
			constiCount = constituencys.size();

		resultForADist.setDistrictId(district.getDistrictId());
		resultForADist.setDistrictName(district.getDistrictName());
		resultForADist.setStateId(district.getState().getStateId());
		resultForADist.setStateName(district.getState().getStateName());
		resultForADist.setTotalConstituencies(new Long(partyResultsVO.size()));
		resultForADist.setConstiCount(new Long(constiCount));
		resultForADist.setConstiParticipated(new Long(partyResultsVO.size()));
		resultForADist.setPartyElectionResultsList(partyResultsVO);

		try {
			for (PartyResultVO results : partyResultsVO) {
				if (results.getRank().equals(new Long(1))) {
					seatsWon++;
				}
				votesEarned += results.getVotesEarned();
				validVotes += results.getValidVotes();
			}
			Double votesPercent = new BigDecimal(
					(votesEarned.doubleValue() / validVotes.doubleValue()) * 100)
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			resultForADist.setSeatsWon(new Long(seatsWon));
			resultForADist.setVotesPercent(votesPercent);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultForADist;
	}

	public List<SelectOptionVO> getHamletsForTownship(Long townshipId) {
		List<SelectOptionVO> hamlets = new ArrayList<SelectOptionVO>();
		List<Hamlet> hamletModels = hamletDAO.findByTownshipId(townshipId);
		SelectOptionVO hamlet = null;
		for (Hamlet hamletModel : hamletModels) {
			hamlet = new SelectOptionVO(hamletModel.getHamletId(), hamletModel
					.getHamletName());
			hamlets.add(hamlet);
		}
		return hamlets;
	}

	@SuppressWarnings("unchecked")
	public List<ConstituencyBoothInfoVO> getBoothPartNosForMandalAndElection(
			Long tehsilId, String electionYear) {
		List<ConstituencyBoothInfoVO> constituencyBoothsList = new ArrayList<ConstituencyBoothInfoVO>();
		List boothsInfo = boothConstituencyElectionDAO
				.findPartNoConstituencyNameForTehsil(tehsilId,
						IConstants.ASSEMBLY_ELECTION_TYPE, electionYear);
		for (int i = 0; i < boothsInfo.size(); i++) {
			Object[] values = (Object[]) boothsInfo.get(i);
			Long boothConstiElecId = (Long) values[0];
			String partNo = (String) values[3];
			String villagesCovered = (String) values[2];
			String constituencyName = (String) values[1];
			constituencyBoothsList.add(new ConstituencyBoothInfoVO(
					boothConstiElecId, partNo, constituencyName,
					villagesCovered));
		}
		return constituencyBoothsList;
	}

	@SuppressWarnings("unchecked")
	public CandidateDetailsVO getCompleteElectionResultsForAConstituency(
			Long constituencyId, Long electionId, Long partyId) {
		CandidateDetailsVO candidateResults = null;
		List<CandidateOppositionVO> oppCandidates = null;
		if (constituencyId != null && electionId != null && partyId != null) {
			candidateResults = new CandidateDetailsVO();
			oppCandidates = new ArrayList<CandidateOppositionVO>();
			List candidateList = null;
			List oppCandidateList = null;

			String electionType = electionDAO.get(electionId)
					.getElectionScope().getElectionType().getElectionType();

			if (IConstants.PARLIAMENT_ELECTION_TYPE
					.equalsIgnoreCase(electionType)) {
				candidateList = nominationDAO
						.findPCElectionResultsForACandidateForAnElectionInAConstituency(
								constituencyId, electionId, partyId);
				oppCandidateList = nominationDAO
						.findPCElectionResultsForAnElectionInAConstituencyWithoutSelectedParty(
								constituencyId, electionId, partyId);
			} else {
				candidateList = nominationDAO
						.findElectionResultsForACandidateForAnElectionInAConstituency(
								constituencyId, electionId, partyId);
				oppCandidateList = nominationDAO
						.findElectionResultsForAnElectionInAConstituencyWithoutSelectedParty(
								constituencyId, electionId, partyId);
			}

			candidateResults = getCandidateResultsVO(electionType,
					candidateList);
			oppCandidates = getOppositionCandidateResults(oppCandidateList);
			candidateResults.setOppositionCandidates(oppCandidates);
		}

		return candidateResults;
	}

	@SuppressWarnings("unchecked")
	public CandidateDetailsVO getCandidateResultsVO(String electionType,
			List candidateList) {
		CandidateDetailsVO candidateResults = null;
		if (candidateList != null) {
			candidateResults = new CandidateDetailsVO();
			String districtName = "";
			Object[] params = (Object[]) candidateList.get(0);

			String candidateName = (String) params[0];
			Long candId = (Long) params[1];
			String partyName = (String) params[2];
			String constiName = (String) params[3];
			String stateName = (String) params[4];
			if (!IConstants.PARLIAMENT_ELECTION_TYPE
					.equalsIgnoreCase(electionType))
				districtName = (String) params[5];
			String elecYear = (String) params[6];
			String elecType = (String) params[7];
			Double votesEarned = (Double) params[8];
			String votesPercent = (String) params[9];
			Long rank = (Long) params[10];

			candidateResults.setCandidateId(candId);
			candidateResults.setCandidateName(candidateName);
			candidateResults.setConstituencyName(constiName);
			candidateResults.setPartyName(partyName);
			candidateResults.setDistrictName(districtName);
			candidateResults.setStateName(stateName);
			candidateResults.setElectionType(elecType);
			candidateResults.setElectionYear(elecYear);
			Long voteEarned = votesEarned.longValue();
			candidateResults.setVotesEarned(voteEarned.toString());
			candidateResults.setVotesPercentage(votesPercent);
			candidateResults.setRank(rank);
		}
		return candidateResults;
	}

	public List<CandidateOppositionVO> getOppositionCandidateResults(
			List oppCandidatesList) {
		List<CandidateOppositionVO> oppCandidates = null;
		if (oppCandidatesList != null) {
			oppCandidates = new ArrayList<CandidateOppositionVO>();
			for (int i = 0; i < oppCandidatesList.size(); i++) {
				Object[] params = (Object[]) oppCandidatesList.get(i);

				String candidateName = (String) params[0];
				Long candId = (Long) params[1];
				String partyName = (String) params[2];
				String constiName = (String) params[3];
				String stateName = (String) params[4];
				String districtName = (String) params[5];
				String elecYear = (String) params[6];
				String elecType = (String) params[7];
				Double votesEarned = (Double) params[8];
				String votesPercent = (String) params[9];
				Long rank = (Long) params[10];

				CandidateOppositionVO oppResult = new CandidateOppositionVO();
				oppResult.setCandidateId(candId);
				oppResult.setCandidateName(candidateName);
				oppResult.setPartyName(partyName);
				Long voteEarned = votesEarned.longValue();
				oppResult.setVotesEarned(voteEarned.toString());
				oppResult.setVotesPercentage(votesPercent);
				oppResult.setRank(rank);

				oppCandidates.add(oppResult);
			}
		}
		return oppCandidates;
	}

	public Set<SelectOptionVO> getAllPartiesParticipatedInMandal(Long tehsilId) {
		Set<SelectOptionVO> partiesAndIdsInMandal = new HashSet<SelectOptionVO>();
		List partiesAndIds = nominationDAO
				.getAllPartiesOfElectionTypeInMandal(tehsilId);
		for (int i = 0; i < partiesAndIds.size(); i++) {
			Object[] values = (Object[]) partiesAndIds.get(i);
			partiesAndIdsInMandal.add(new SelectOptionVO((Long) values[0],
					values[1].toString()));
		}

		List parties = candidateBoothResultDAO
				.getAllACPCPartiesInMandal(tehsilId);
		for (int i = 0; i < parties.size(); i++) {
			Object[] values = (Object[]) parties.get(i);
			partiesAndIdsInMandal.add(new SelectOptionVO((Long) values[0],
					values[1].toString()));
		}

		return partiesAndIdsInMandal;
	}

	public Set<SelectOptionVO> getAllPartiesParticipatedInRevenueVillage(
			Long townshipId) {
		Set<SelectOptionVO> partiesAndIdsInVillage = new HashSet<SelectOptionVO>();
		List parties = candidateBoothResultDAO
				.getAllACPCPartiesInRevenueVillage(townshipId);
		for (int i = 0; i < parties.size(); i++) {
			Object[] values = (Object[]) parties.get(i);
			partiesAndIdsInVillage.add(new SelectOptionVO((Long) values[0],
					values[1].toString()));
		}
		return partiesAndIdsInVillage;
	}

	/*
	 * This method populates all the candidate details for all the election
	 * years based on consitutuencyId.
	 */
	@SuppressWarnings("unchecked")
	public CandidateDetailsVO getElectionResultsForAConstituencyForAllYears(
			Long constituencyId) {
		CandidateDetailsVO candidateResults = null;
		try {
			candidateResults = new CandidateDetailsVO();
			List result = nominationDAO
					.findAllCandidatesForAnElectionByElectionYear(constituencyId);
			candidateResults = populateElectionDataForAllYears(result);
			return candidateResults;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			candidateResults = new CandidateDetailsVO();
			candidateResults.setDataAvailabilityFlag(0L);
			return candidateResults;
		}
	}

	/*
	 * This method populates all the candidate details for all the election
	 * years based on districtId.
	 */
	@SuppressWarnings("unchecked")
	public CandidateDetailsVO getElectionResultsForADistrictForAllYears(
			Long districtId) {
		CandidateDetailsVO candidateResults = null;
		try {
			candidateResults = new CandidateDetailsVO();
			List result = nominationDAO
					.findAllCandidatesForAnElectionByElectionYearByDistrictId(
							districtId, IConstants.ASSEMBLY_ELECTION_TYPE);
			candidateResults = populateElectionDataForAllYears(result);
			return candidateResults;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			candidateResults = new CandidateDetailsVO();
			candidateResults.setDataAvailabilityFlag(0L);
			return candidateResults;
		}
	}

	/*
	 * This method is used by other like
	 * getElectionResultsForADistrictForAllYears and
	 * getElectionResultsForAConstituencyForAllYearsto set the data in to the
	 * CandidateDetailsVO.
	 */
	@SuppressWarnings("unchecked")
	public CandidateDetailsVO populateElectionDataForAllYears(List result) {
		List<CandidateDetailsVO> candidateDetails = new ArrayList<CandidateDetailsVO>(
				0);
		CandidateDetailsVO candidateResults = null;
		try {
			candidateResults = new CandidateDetailsVO();
			for (int i = 0; i < result.size(); i++) {
				CandidateDetailsVO candidateResultsVo = new CandidateDetailsVO();
				Object[] parms = (Object[]) result.get(i);
				candidateResultsVo.setCandidateName(parms[0].toString());
				candidateResultsVo.setConstituencyName(parms[1].toString());
				candidateResultsVo.setVotesEarned(parms[2].toString());
				if (Long.parseLong(parms[3].toString()) == 1l)
					candidateResultsVo.setResult("Win");
				else
					candidateResultsVo.setResult("Loose");
				candidateResultsVo.setPartyName(parms[4].toString());
				candidateResultsVo.setElectionYear(parms[5].toString());
				candidateDetails.add(candidateResultsVo);
			}
			candidateResults.setCandidateDetails(candidateDetails);
			return candidateResults;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			candidateResults = new CandidateDetailsVO();
			candidateResults.setDataAvailabilityFlag(0L);
			return candidateResults;
		}

	}

	/*
	 * This method retrieves all the latest constituences for a particular
	 * election year.
	 */
	@SuppressWarnings("unchecked")
	public CandidateDetailsVO getLatestConstituenciesForAssemblyAndParliamentForAllElectionYears(
			Long electionType, Long stateId) {
		Long electionID = 0l;
		CandidateDetailsVO constituencies = null;
		List<SelectOptionVO> selectOptionVO = new ArrayList<SelectOptionVO>(0);
		SelectOptionVO selectOptionvo = new SelectOptionVO();
		try {
			constituencies = new CandidateDetailsVO();
			selectOptionvo.setId(0l);
			selectOptionvo.setName("Select Constituency");
			List result = null;
			// get ElectionType Object
			ElectionType elecType = electionTypeDAO.get(electionType);
			if (elecType != null) {
				if (elecType.getElectionType().equals(
						IConstants.PARLIAMENT_ELECTION_TYPE))
					result = electionDAO
							.findRecentElectionIdAndYearForParliament(electionType);
				else
					result = electionDAO.findElectionIdAndYear(electionType,
							stateId);
			}

			selectOptionVO.add(0, selectOptionvo);
			for (int i = 0; i < result.size(); i++) {
				Object[] parms = (Object[]) result.get(i);
				electionID = Long.parseLong(parms[0].toString());
			}
			List list = constituencyElectionDAO
					.findTotalAssemblyConstituencies(electionID, stateId);
			for (int i = 0; i < list.size(); i++) {
				Object[] parms = (Object[]) list.get(i);
				SelectOptionVO selectOption = new SelectOptionVO();
				selectOption.setId(Long.parseLong(parms[0].toString()));
				selectOption.setName(parms[1].toString());
				selectOptionVO.add(selectOption);
			}
			constituencies.setLatestConstituencies(selectOptionVO);
			return constituencies;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			constituencies = new CandidateDetailsVO();
			constituencies.setDataAvailabilityFlag(0L);
			return constituencies;
		}
	}

	/*
	 * This method retrieves all the States present in that country.
	 */
	public CandidateDetailsVO getAllStatesInCountry() {
		List<SelectOptionVO> selectOptionVo;
		CandidateDetailsVO states = null;
		try {
			states = new CandidateDetailsVO();
			selectOptionVo = new ArrayList<SelectOptionVO>(0);
			List<State> result = stateDAO.findByCountryId(1l);
			SelectOptionVO selectOption = new SelectOptionVO();
			selectOption.setId(0l);
			selectOption.setName("Select State");
			selectOptionVo.add(0, selectOption);
			for (State list : result) {
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(list.getStateId());
				selectOptionVO.setName(list.getStateName());
				selectOptionVo.add(selectOptionVO);
			}
			states.setGetAllStates(selectOptionVo);
			return states;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			states = new CandidateDetailsVO();
			states.setDataAvailabilityFlag(0L);
			return states;
		}
	}

	/*
	 * This method retrieves all the Constituencies based on election type and
	 * state id.
	 * 
	 * @Author Y.Ravi Kiran.
	 */
	// Version 1.2
	public ConstituencyInfoVO getConstituenciesByElectionTypeAndStateId(
			Long electionTypeId, Long stateID) {
		List<SelectOptionVO> constituenciesList = new ArrayList<SelectOptionVO>();
		List constiList;
		ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
		ResultStatus result = new ResultStatus();

		try {
			ElectionType electionTypeObj = electionTypeDAO.get(electionTypeId);
			if (IConstants.MUNCIPLE_ELECTION_TYPE.equals(electionTypeObj
					.getElectionType())
					|| IConstants.CORPORATION_ELECTION_TYPE
							.equals(electionTypeObj.getElectionType())) {
				constiList = localElectionBodyDAO.findByElectionTypeAndState(electionTypeId, stateID);
			} else {
				constiList = constituencyDAO.getConstituenciesByElectionTypeAndStateId(electionTypeId, stateID);
			}
			if (constiList != null && constiList.size() > 0) {
				for (int i = 0; i < constiList.size(); i++) {
					Object[] obj = (Object[]) constiList.get(i);
					SelectOptionVO constituencyData = new SelectOptionVO();
					constituencyData.setId((Long) obj[0]);
					constituencyData.setName(WordUtils.capitalize(obj[1].toString().toLowerCase()));
         
					constituenciesList.add(constituencyData);
				}
			}

			constituencyInfoVO.setConstituencies(constituenciesList);
			result.setResultPartial(false);
			result.setResultCode(ResultCodeMapper.SUCCESS);
			return constituencyInfoVO;
		} catch (Exception e) {
			e.printStackTrace();
			result.setExceptionEncountered(e);
			result.setResultPartial(true);
			result.setResultCode(ResultCodeMapper.FAILURE);
			return constituencyInfoVO;
		}
	}

	public List<SelectOptionVO> getLatestConstituenciesByStateId(Long stateId) {
		List<SelectOptionVO> data = new ArrayList<SelectOptionVO>();
		try {
			List result = constituencyDAO.getLatestConstituenciesByStateId(
					IConstants.ASSEMBLY_ELECTION_TYPE, stateId);
			for (int i = 0; i < result.size(); i++) {
				Object[] parms = (Object[]) result.get(i);
				SelectOptionVO vo = new SelectOptionVO();
				vo.setId((Long) parms[0]);
				vo.setName(parms[0].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * This method retrieves all the MP's present in the district for the latest
	 * election year.
	 * 
	 * @author Y.Ravi Kiran.
	 * @param districtId
	 * @return CandidateDetailsVO
	 */
	@SuppressWarnings("unchecked")
	public CandidateDetailsVO getAllParliamentWinningCandidatesForADistrict(
			Long districtId, Set<Long> parliamentConstituencies) {
		CandidateDetailsVO candidateVo = new CandidateDetailsVO();
		List<CandidateDetailsVO> candidateDetailsVo = new ArrayList<CandidateDetailsVO>(
				0);
		try {
			log.info("Making nominationDAO.getParliamentCandidateNPartyInfoForADistrict() DAO call");
			Iterator<Long> iterator = parliamentConstituencies.iterator();
			while (iterator.hasNext()) {
				/*List list = nominationDAO.getParliamentCandidateNPartyInfo(Long.parseLong(iterator.next().toString()),
						IConstants.PARLIAMENT_ELECTION_TYPE, 1L, "MAIN");*/
				
				Long constiId = Long.parseLong(iterator.next().toString());
				List recentResultYear = constituencyElectionDAO.getLatestResultsElectionYearInAConstituency(constiId);
				if(recentResultYear != null && recentResultYear.size() > 0)
				{
					Object value = (Object)recentResultYear.get(0);
					String latestElecYearInConsti = (String)value;
				
				
				   
					List list = nominationDAO.getParliamentCandidateNPartyInfoInElection(constiId,
							IConstants.PARLIAMENT_ELECTION_TYPE, 1L,latestElecYearInConsti);
					
					for (int i = 0; i < list.size(); i++) {
						CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
						Object[] parms = (Object[]) list.get(i);
						candidateDetailsVO.setConstituencyId(Long
								.parseLong(parms[0].toString()));
						candidateDetailsVO.setConstituencyName(parms[1].toString()
								.toUpperCase());
						candidateDetailsVO.setCandidateId(Long.parseLong(parms[2]
								.toString()));
						candidateDetailsVO.setCandidateName(parms[5].toString()
								.toUpperCase());
						if (parms[10] != null) {
							candidateDetailsVO.setPartyFlag(parms[10].toString());
						} else {
							candidateDetailsVO.setPartyFlag("no_Image.png");
						}
						candidateDetailsVO.setPartyName(parms[7].toString());
						candidateDetailsVO.setReservationZone(parms[12].toString());
						candidateDetailsVO.setElectionYear(latestElecYearInConsti);
						candidateDetailsVo.add(candidateDetailsVO);
					}
				}
			}
			candidateVo.setCandidateDetails(candidateDetailsVo);
			parliamentConstituencies.clear();
			return candidateVo;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * This method returns all the election years for the given electionType.
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getAllElectionYearsForATeshil(Long electionType) {
		List<SelectOptionVO> SelectOptionVO = new ArrayList<SelectOptionVO>(0);
		Long stateId = 1l;
		try {
			SelectOptionVO selectOptionVO = null;
			List result = electionDAO.findElectionYearsForElectionTypeAndState(
					electionType, stateId);
			for (int i = result.size() - 1; i >= 0; i--) {
				selectOptionVO = new SelectOptionVO();
				Object[] parms = (Object[]) result.get(i);
				selectOptionVO.setId(Long.parseLong(parms[1].toString()));
				selectOptionVO.setName(parms[1].toString());
				SelectOptionVO.add(selectOptionVO);
			}
			return SelectOptionVO;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * This method generates a report to find out the results for all the
	 * parties participated in that zptc/mptc for a district.
	 */

	public List<TeshilPartyInfoVO> getMandalWisePartyReport(
			String electionType, String electionYear, Long districtId) {
		BigDecimal percentage = new BigDecimal(0.0);
		List<TeshilPartyInfoVO> teshilPartyInfoVO = new ArrayList<TeshilPartyInfoVO>(
				0);
		Float totalVotes = null;
		Long winningCandidateRank = 1l;
		Map<String, Long> winningSeats = new HashMap<String, Long>(0);
		Long totalSeats = 0l;
		try {
			if (electionYear.equalsIgnoreCase("null")) {
				Long stateId = districtDAO.get(districtId).getState()
						.getStateId();
				electionYear = electionDAO
						.findLatestElectionAssemblyElectionYearForState(
								IConstants.ASSEMBLY_ELECTION_TYPE, stateId,
								IConstants.ELECTION_SUBTYPE_MAIN).get(0)
						.toString();
			}
			if (log.isDebugEnabled())
				log
						.info("Making nominationDAO.getPartysWinningCandidateInfoForAParticularElectionYear() DAO Call");
			List seatWon = nominationDAO
					.getPartysWinningCandidateInfoForAParticularElectionYear(
							electionType, electionYear, winningCandidateRank,
							districtId);

			for (int i = 0; i < seatWon.size(); i++) {
				Object[] parms = (Object[]) seatWon.get(i);
				winningSeats.put(parms[0].toString(), Long.parseLong(parms[1]
						.toString()));
				totalSeats += Long.parseLong(parms[1].toString());
			}
			if (log.isDebugEnabled())
				log
						.info("Making nominationDAO.getPartysInfoForAParticularElectionYear() DAO Call");
			List totalValidVotes = constituencyElectionDAO
					.getTotalValidVotesParticularElectionYear(electionType,
							electionYear, districtId);
			if (totalValidVotes != null) {
				totalVotes = Float
						.parseFloat(totalValidVotes.get(0).toString());
				List result = nominationDAO
						.getPartysInfoForAParticularElectionYear(electionType,
								electionYear, districtId);
				for (int i = 0; i < result.size(); i++) {
					Object[] parms = (Object[]) result.get(i);
					TeshilPartyInfoVO teshilPartyInfoVo = new TeshilPartyInfoVO();
					teshilPartyInfoVo.setPartyName(parms[0].toString());
					teshilPartyInfoVo.setParticipatedSeats(Long
							.parseLong(parms[1].toString()));
					percentage = new BigDecimal((Float.parseFloat(parms[2]
							.toString()) / totalVotes) * 100).setScale(2,
							BigDecimal.ROUND_HALF_UP);
					teshilPartyInfoVo.setPercentageOfVotesWonByParty(Float
							.parseFloat(percentage.toString()));
					teshilPartyInfoVo.setTotalSeats(totalSeats);
					if (winningSeats.get(parms[0].toString()) != null) {
						teshilPartyInfoVo.setSeatsWonByParty(Long
								.parseLong(winningSeats
										.get(parms[0].toString()).toString()));
					} else {
						teshilPartyInfoVo.setSeatsWonByParty(0L);
					}
					teshilPartyInfoVO.add(teshilPartyInfoVo);
				}
			}
			return teshilPartyInfoVO;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ElectionBasicInfoVO> getAssemblyElectionsInfoForAConstituency(
			String presentYear, Long constituencyId) {

		List<ElectionBasicInfoVO> electionInfo = null;

		if (presentYear != null && constituencyId != null) {
			electionInfo = new ArrayList<ElectionBasicInfoVO>();
			List prevElec = boothConstituencyElectionDAO
					.getPreviousElectionYearsDetails(presentYear,
							constituencyId);
			log.debug("Elections Size :" + prevElec.size());
			if (prevElec != null) {
				for (int i = 0; i < prevElec.size(); i++) {
					Object[] params = (Object[]) prevElec.get(i);
					String elecYear = (String) params[0];
					Long elecId = (Long) params[1];
					Long elecTypeId = (Long) params[2];

					ElectionBasicInfoVO electionDetailsVO = new ElectionBasicInfoVO();
					electionDetailsVO.setElectionId(elecId);
					electionDetailsVO.setElectionTypeId(elecTypeId);
					electionDetailsVO.setElectionYear(elecYear);

					electionInfo.add(electionDetailsVO);
				}
			}
		}
		return electionInfo;
	}

	@SuppressWarnings("unchecked")
	public List<ElectionBasicInfoVO> getParliamentElectionsInfoForAConstituency(
			Long constituencyId) {
		List<ElectionBasicInfoVO> electionInfo = null;
		if (constituencyId != null) {
			electionInfo = new ArrayList<ElectionBasicInfoVO>();
			List list = delimitationConstituencyAssemblyDetailsDAO
					.findParliamentConstituenciesForAAssemblyConstituency(constituencyId);
			for (int i = 0; i < list.size(); i++) {
				Object[] params = (Object[]) list.get(i);
				Long constiId = (Long) params[0];
				Long elecTypeId = (Long) params[1];
				String constName = (String) params[2];
				Long year = (Long) params[3];
				Long elecId = null;

				List election = boothConstituencyElectionDAO
						.getElectionIdForAElectionTypeAndYear(elecTypeId, year
								.toString());
				if (election != null && election.size() > 0) {
					Object electnId = (Object) election.get(0);
					elecId = (Long) electnId;
				}
				ElectionBasicInfoVO electionBasicInfoVO = new ElectionBasicInfoVO();
				electionBasicInfoVO.setElectionId(elecId);
				electionBasicInfoVO.setElectionTypeId(elecTypeId);
				electionBasicInfoVO.setElectionYear(year.toString());

				electionInfo.add(electionBasicInfoVO);
			}
		}
		return electionInfo;
	}

	/*
	 * This method is retrieves all the information like candidate and his votes
	 * and details regarding state,district to which the constituency belongs
	 * for the given election year and election type(i.e.,parliament or
	 * assembly)
	 */
	// More Details Method...
	@SuppressWarnings("unchecked")
	public ConstituencyResultsInElectionVO getAllCandidatesResultsInConstituency(
			final Long constituencyId, final String electionYear,
			final String electionType) {

		// set constituency wise complete results
		ConstituencyElectionCompleteResultsVO constituencyElectionCompleteResults = null;
		PartyElectionCompleteResultsVO partyElectionCompleteResults = new PartyElectionCompleteResultsVO();
		// set opp details
		List<CandidateElectionCompleteResultsVO> candidateElectionCompleteResultsList = new ArrayList<CandidateElectionCompleteResultsVO>();
		List<SelectOptionVO> allYears = new ArrayList<SelectOptionVO>();
		List result = null;
		List nominationResult = null;

		try {
			log
					.info("Making constituencyDAO.getConstituencyInfoByConstituencyIdElectionYearAndElectionType() DAO call");
			ConstituencyResultsInElectionVO constituencyResultsInElection = new ConstituencyResultsInElectionVO();
			// Set Election Basic Information
			ElectionBasicInformationVO electionInfo = new ElectionBasicInformationVO();
			electionInfo.setElectionType(electionType);
			electionInfo.setElectionYear(electionYear);

			if (electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)) {
				result = constituencyDAO
						.getParliamentConstituencyInfoByConstituencyIdElectionYearAndElectionType(constituencyId);
				java.util.ListIterator li = result.listIterator();
				while (li.hasNext()) {
					log.info(" Has Parliament Constituency Details ...");
					// main VO
					constituencyElectionCompleteResults = new ConstituencyElectionCompleteResultsVO();

					Object[] parms = (Object[]) li.next();
					// set to main VO
					constituencyElectionCompleteResults.setConstituencyId(Long
							.parseLong(parms[0].toString()));
					constituencyElectionCompleteResults.setConstituencyName(parms[1].toString());
					constituencyElectionCompleteResults
							.setElectionInfo(electionInfo);
					constituencyElectionCompleteResults.setStateId(Long
							.parseLong(parms[2].toString()));
					constituencyElectionCompleteResults.setStateName(parms[3]
							.toString());
				}
				nominationResult = nominationDAO
						.getCandidatesInfoForTheGivenConstituency(
								constituencyId.toString(), electionYear,
								electionType);
			} else {

				if (electionType.equals(IConstants.CORPORATION_ELECTION_TYPE)
						|| electionType
								.equals(IConstants.MUNCIPLE_ELECTION_TYPE)
						|| electionType
								.equals(IConstants.GREATER_ELECTION_TYPE)) {
					result = constituencyDAO
							.getConstituencyInfoByConstituencyIdElectionYearAndElectionTypeForMuncipalAndCorporationElection(constituencyId);
					nominationResult = nominationDAO
							.getCandidatesInfoForTheGivenMuncipalityOrCorporationConstituency(
									constituencyId.toString(), electionYear,
									electionType);
				} else {
					result = constituencyDAO
							.getConstituencyInfoByConstituencyIdElectionYearAndElectionType(constituencyId);
					nominationResult = nominationDAO
							.getCandidatesInfoForTheGivenConstituency(
									constituencyId.toString(), electionYear,
									electionType);
				}
				java.util.ListIterator li = result.listIterator();
				while (li.hasNext()) {
					log.info(" Has Constituency Details ...");
					// main VO
					constituencyElectionCompleteResults = new ConstituencyElectionCompleteResultsVO();

					Object[] parms = (Object[]) li.next();
					// set to mainVO
					constituencyElectionCompleteResults.setConstituencyId(Long
							.parseLong(parms[0].toString()));
					constituencyElectionCompleteResults
							.setConstituencyName(parms[1].toString());
					constituencyElectionCompleteResults.setDistrictId(Long
							.parseLong(parms[2].toString()));
					constituencyElectionCompleteResults
							.setDistrictName(parms[3].toString());
					constituencyElectionCompleteResults.setStateId(Long
							.parseLong(parms[4].toString()));
					constituencyElectionCompleteResults.setStateName(parms[5]
							.toString());
					constituencyElectionCompleteResults
							.setElectionInfo(electionInfo);

				}
			}
			log
					.info("Making nominationDAO.getCandidatesInfoForTheGivenConstituency() DAO call");
			Double totalPolledVotes = 0d;
			Double totalVoters = 0d;
			Long totalVotesGainedByAllCandidates = 0l;
			java.util.ListIterator resultIterator = nominationResult
					.listIterator();
			while (resultIterator.hasNext()) {
				log.info(" Has Constituency Results ...");
				Object[] parms = (Object[]) resultIterator.next();
				if (parms[16] != null) {
					constituencyElectionCompleteResults
							.setReservationZone(parms[16].toString());
				}
				if (parms[14] != null)
					totalPolledVotes = Double.parseDouble(parms[14].toString());
				if (parms[13] != null) {
					totalVoters = Double.parseDouble(parms[13].toString());
				}

				if (Long.parseLong(parms[4].toString()) == 1l) {

					// set won candidate results
					CandidateElectionCompleteResultsVO wonCandidateResults = new CandidateElectionCompleteResultsVO();
					wonCandidateResults.setCandidateId(Long.parseLong(parms[0]
							.toString()));
					String candidateName = parms[1].toString();
					if (candidateName.contains("\n")) {
						candidateName = candidateName.replace("\n", " ");
						wonCandidateResults.setCandidateLastName(candidateName);
					} else {
						wonCandidateResults.setCandidateLastName(candidateName);
					}
					Double votesEarned = (Double) parms[2];
					Long votesEarn = votesEarned.longValue();
					wonCandidateResults.setCandidateVotesEarned(votesEarn);
					totalVotesGainedByAllCandidates += votesEarn;
					wonCandidateResults.setVotesPercentAsString(parms[3]
							.toString());
					wonCandidateResults.setRank(Long.parseLong(parms[4]
							.toString()));
					// set party details
					PartyElectionDetailsVO partyElectionDetails = new PartyElectionDetailsVO();
					if (!(parms[5] == null)) {
						partyElectionDetails.setPartyId(Long.parseLong(parms[5]
								.toString()));
					}
					if (!(parms[6] == null)) {
						partyElectionDetails.setPartyFlag(parms[6].toString());
					}
					partyElectionDetails.setPartyLongName(parms[7].toString());
					partyElectionDetails.setPartyShortName(parms[8].toString());
					wonCandidateResults
							.setPartyElectionDetails(partyElectionDetails);

					// set won candidate results to VO
					partyElectionCompleteResults
							.setCandidateElectionCompleteResults(wonCandidateResults);

				} else {

					// set opp candidate results
					CandidateElectionCompleteResultsVO oppCandidateResults = new CandidateElectionCompleteResultsVO();
					oppCandidateResults.setCandidateId(Long.parseLong(parms[0]
							.toString()));

					String candidateName = parms[1].toString();
					if (candidateName.contains("\n")) {
						candidateName = candidateName.replace("\n", " ");
						oppCandidateResults.setCandidateLastName(candidateName);
					} else {
						oppCandidateResults.setCandidateLastName(parms[1]
								.toString());
					}
					Double votesEarned = (Double) parms[2];
					Long votesEarn = votesEarned.longValue();
					totalVotesGainedByAllCandidates += votesEarn;
					oppCandidateResults.setCandidateVotesEarned(votesEarn);
					oppCandidateResults.setVotesPercentAsString(parms[3]
							.toString());
					oppCandidateResults.setRank(Long.parseLong(parms[4]
							.toString()));
					PartyElectionDetailsVO partyElectionDetails = new PartyElectionDetailsVO();
					if (!(parms[5] == null)) {
						partyElectionDetails.setPartyId(Long.parseLong(parms[5]
								.toString()));
					}
					if (!(parms[6] == null)) {
						partyElectionDetails.setPartyFlag(parms[6].toString());
					}
					partyElectionDetails.setPartyLongName(parms[7].toString());
					partyElectionDetails.setPartyShortName(parms[8].toString());
					oppCandidateResults
							.setPartyElectionDetails(partyElectionDetails);
					candidateElectionCompleteResultsList
							.add(oppCandidateResults);
				}
			}
			if (totalVoters.equals(0d)) {
				constituencyElectionCompleteResults.setTotalVoters(new Long(0));
				constituencyElectionCompleteResults
						.setTotalVotesPolled(totalVotesGainedByAllCandidates);
				constituencyElectionCompleteResults
						.setTotalVotersPercentInString("N/A");
			} else {
				constituencyElectionCompleteResults.setTotalVoters(totalVoters
						.longValue());
				if (totalPolledVotes.longValue() != 0l) {
					constituencyElectionCompleteResults
							.setTotalVotesPolled(totalPolledVotes.longValue());
					constituencyElectionCompleteResults
							.setTotalVotersPercentInString(new BigDecimal(
									(totalPolledVotes / totalVoters) * 100)
									.setScale(2, BigDecimal.ROUND_HALF_UP)
									.toString());
				} else {
					constituencyElectionCompleteResults
							.setTotalVotesPolled(totalVotesGainedByAllCandidates);
					constituencyElectionCompleteResults
							.setTotalVotersPercentInString(new BigDecimal(
									(totalVotesGainedByAllCandidates / totalVoters) * 100)
									.setScale(2, BigDecimal.ROUND_HALF_UP)
									.toString());
				}
			}

			// set opposition
			partyElectionCompleteResults
					.setCandidateElectionCompleteResultsList(candidateElectionCompleteResultsList);
			constituencyElectionCompleteResults
					.setPartyElectionCompleteResults(partyElectionCompleteResults);

			// for margin votes
			if (constituencyElectionCompleteResults != null
					&& constituencyElectionCompleteResults
							.getPartyElectionCompleteResults() != null) {
				for (CandidateElectionCompleteResultsVO completeRes : constituencyElectionCompleteResults
						.getPartyElectionCompleteResults()
						.getCandidateElectionCompleteResultsList()) {
					if (completeRes.getRank().equals(new Long(2))) {
						Long votesMarginVal = constituencyElectionCompleteResults
								.getPartyElectionCompleteResults()
								.getCandidateElectionCompleteResults()
								.getCandidateVotesEarned()
								- completeRes.getCandidateVotesEarned();
						// Original written Double votesMarginPercent = new
						// Double(constituencyElectionCompleteResults.getPartyElectionCompleteResults().getCandidateElectionCompleteResults().getVotesPercentAsString())
						// - new Double(completeRes.getVotesPercentAsString());

						// System.out.println(" ........................ Votes Margin Gained :"
						// + votesMarginVal);
						// System.out.println(" ........................ Votes Margin % Gained :"
						// + votesMarginPercent);
						// System.out.print(constituencyElectionCompleteResults.getPartyElectionCompleteResults().getCandidateElectionCompleteResults().getCandidateVotesEarned()+" - \t"+completeRes.getCandidateVotesEarned()+" = \t"+votesMarginVal);
						Double votesMarginPercent = (votesMarginVal
								.doubleValue()
								/ constituencyElectionCompleteResults
										.getTotalVotesPolled() * 100);
						// System.out.println("\t"+votesMarginPercent);
						constituencyElectionCompleteResults
								.getPartyElectionCompleteResults()
								.getCandidateElectionCompleteResults()
								.setMarginVotesEarned(votesMarginVal);
						constituencyElectionCompleteResults
								.getPartyElectionCompleteResults()
								.getCandidateElectionCompleteResults()
								.setMarginVotesPercentAsString(
										new BigDecimal(votesMarginPercent)
												.setScale(
														2,
														BigDecimal.ROUND_HALF_UP)
												.toString());
					}
					Long oppMarginVotesVal = completeRes
							.getCandidateVotesEarned()
							- constituencyElectionCompleteResults
									.getPartyElectionCompleteResults()
									.getCandidateElectionCompleteResults()
									.getCandidateVotesEarned();
					Double oppMarginPercent = new Double(completeRes
							.getVotesPercentAsString())
							- new Double(constituencyElectionCompleteResults
									.getPartyElectionCompleteResults()
									.getCandidateElectionCompleteResults()
									.getVotesPercentAsString());
					completeRes.setMarginVotesEarned(oppMarginVotesVal);
					completeRes.setMarginVotesPercentAsString(new BigDecimal(
							oppMarginPercent).setScale(2,
							BigDecimal.ROUND_HALF_UP).toString());
				}

			}
			List allElectionYears = nominationDAO
					.getAllElectionYearsForAConstituency(constituencyId,
							electionType);
			for (int i = 0; i < allElectionYears.size(); i++) {
				SelectOptionVO eleYear = new SelectOptionVO();
				eleYear.setId(Long
						.parseLong(allElectionYears.get(i).toString()));
				eleYear.setName(allElectionYears.get(i).toString());
				allYears.add(eleYear);
			}
			constituencyResultsInElection
					.setConstituencyElectionCompleteResults(constituencyElectionCompleteResults);
			constituencyResultsInElection.setElectionYears(allYears);

			return constituencyResultsInElection;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * This method is retrieves all the information like candidate and his votes
	 * and details regarding state,district to which the constituency belongs
	 * for the given election year and election type(i.e.,parliament or
	 * assembly)
	 */
	// More Details Method...
	@SuppressWarnings("unchecked")
	public ConstituencyElectionResultsVO getAllCandidatesDetailsForConstituency(
			final Long constituencyId, final String electionYear,
			final String electionType) {
		ConstituencyElectionResultsVO constituencyElectionResults = null;
		List<CandidateOppositionVO> candidateOppositionVO = new ArrayList<CandidateOppositionVO>(
				0);
		List<SelectOptionVO> allYears = new ArrayList<SelectOptionVO>();
		List result = null;
		List nominationResult = null;

		try {
			log
					.info("Making constituencyDAO.getConstituencyInfoByConstituencyIdElectionYearAndElectionType() DAO call");
			if (electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)) {
				result = constituencyDAO
						.getParliamentConstituencyInfoByConstituencyIdElectionYearAndElectionType(constituencyId);
				java.util.ListIterator li = result.listIterator();
				while (li.hasNext()) {
					log.info(" Has Parliament Constituency Details ...");
					constituencyElectionResults = new ConstituencyElectionResultsVO();
					Object[] parms = (Object[]) li.next();
					constituencyElectionResults.setConstituencyId(Long
							.parseLong(parms[0].toString()));
					constituencyElectionResults.setConstituencyName(parms[1]
							.toString());
					constituencyElectionResults.setElectionType(electionType);
					constituencyElectionResults.setStateId(Long
							.parseLong(parms[2].toString()));
					constituencyElectionResults.setStateName(parms[3]
							.toString());
					constituencyElectionResults.setElectionYear(electionYear);
				}
				nominationResult = nominationDAO
						.getCandidatesInfoForTheGivenConstituency(
								constituencyId.toString(), electionYear,
								electionType);
			} else {

				if (electionType.equals(IConstants.CORPORATION_ELECTION_TYPE)
						|| electionType
								.equals(IConstants.MUNCIPLE_ELECTION_TYPE)) {
					result = constituencyDAO
							.getConstituencyInfoByConstituencyIdElectionYearAndElectionTypeForMuncipalAndCorporationElection(constituencyId);
					nominationResult = nominationDAO
							.getCandidatesInfoForTheGivenMuncipalityOrCorporationConstituency(
									constituencyId.toString(), electionYear,
									electionType);
				} else {
					result = constituencyDAO
							.getConstituencyInfoByConstituencyIdElectionYearAndElectionType(constituencyId);
					nominationResult = nominationDAO
							.getCandidatesInfoForTheGivenConstituency(
									constituencyId.toString(), electionYear,
									electionType);
				}
				java.util.ListIterator li = result.listIterator();
				while (li.hasNext()) {
					log.info(" Has Constituency Details ...");
					constituencyElectionResults = new ConstituencyElectionResultsVO();
					Object[] parms = (Object[]) li.next();
					constituencyElectionResults.setConstituencyId(Long
							.parseLong(parms[0].toString()));
					constituencyElectionResults.setConstituencyName(parms[1]
							.toString());
					constituencyElectionResults.setElectionType(electionType);
					constituencyElectionResults.setDistrictId(Long
							.parseLong(parms[2].toString()));
					constituencyElectionResults.setDistrictName(parms[3]
							.toString());
					constituencyElectionResults.setStateId(Long
							.parseLong(parms[4].toString()));
					constituencyElectionResults.setStateName(parms[5]
							.toString());
					constituencyElectionResults.setElectionYear(electionYear);
				}
			}
			log
					.info("Making nominationDAO.getCandidatesInfoForTheGivenConstituency() DAO call");
			Double totalPolledVotes = 0d;
			Double totalVoters = 0d;
			java.util.ListIterator resultIterator = nominationResult
					.listIterator();
			while (resultIterator.hasNext()) {
				log.info(" Has Constituency Results ...");
				Object[] parms = (Object[]) resultIterator.next();
				if (parms[14] != null)
					totalPolledVotes = Double.parseDouble(parms[14].toString());
				if (parms[13] != null) {
					totalVoters = Double.parseDouble(parms[13].toString());
				}

				if (Long.parseLong(parms[4].toString()) == 1l) {
					CandidateWonVO candidateWonVO = new CandidateWonVO();
					candidateWonVO.setCandidateId(Long.parseLong(parms[0]
							.toString()));
					String candidateName = parms[1].toString();
					if (candidateName.contains("\n")) {
						candidateName = candidateName.replace("\n", " ");
						candidateWonVO.setCandidateName(candidateName);
					} else
						candidateWonVO.setCandidateName(parms[1].toString());
					Double votesEarned = (Double) parms[2];
					Long votesEarn = votesEarned.longValue();
					candidateWonVO.setVotesEarned(votesEarn.toString());
					candidateWonVO.setVotesPercentage(parms[3].toString());
					candidateWonVO.setRank(Long.parseLong(parms[4].toString()));
					if (!(parms[5] == null)) {
						candidateWonVO.setPartyId(Long.parseLong(parms[5]
								.toString()));
					}
					if (!(parms[6] == null)) {
						candidateWonVO.setPartyFlag(parms[6].toString());
					}
					candidateWonVO.setPartyLongName(parms[7].toString());
					candidateWonVO.setPartyName(parms[8].toString());
					constituencyElectionResults
							.setCandidateResultsVO(candidateWonVO);
				} else {
					CandidateOppositionVO candidateOppositionVo = new CandidateOppositionVO();
					candidateOppositionVo.setCandidateId(Long
							.parseLong(parms[0].toString()));
					String candidateName = parms[1].toString();
					if (candidateName.contains("\n")) {
						candidateName = candidateName.replace("\n", " ");
						candidateOppositionVo.setCandidateName(candidateName);
					} else
						candidateOppositionVo.setCandidateName(parms[1]
								.toString());
					Double votesEarned = (Double) parms[2];
					Long votesEarn = votesEarned.longValue();
					candidateOppositionVo.setVotesEarned(votesEarn.toString());
					candidateOppositionVo.setVotesPercentage(parms[3]
							.toString());
					candidateOppositionVo.setRank(Long.parseLong(parms[4]
							.toString()));
					if (!(parms[5] == null)) {
						candidateOppositionVo.setPartyId(Long
								.parseLong(parms[5].toString()));
					}
					if (!(parms[6] == null)) {
						candidateOppositionVo.setPartyFlag(parms[6].toString());
					}
					candidateOppositionVo.setPartyLongName(parms[7].toString());
					candidateOppositionVo.setPartyName(parms[8].toString());
					candidateOppositionVO.add(candidateOppositionVo);
				}
			}
			constituencyElectionResults.setTotalPolledVotes(new BigDecimal(
					totalPolledVotes).setScale(0, BigDecimal.ROUND_HALF_UP)
					.toString());
			if (totalVoters == 0d) {
				constituencyElectionResults.setTotalVotes("N/A");
			} else {
				constituencyElectionResults.setTotalVotes(new BigDecimal(
						totalVoters).setScale(0, BigDecimal.ROUND_HALF_UP)
						.toString());
			}
			if (totalVoters != 0d) {
				constituencyElectionResults.setVotingPercentage(new BigDecimal(
						(totalPolledVotes / totalVoters) * 100).setScale(2,
						BigDecimal.ROUND_HALF_UP).toString());
			} else {
				constituencyElectionResults.setVotingPercentage("N/A");
			}

			constituencyElectionResults
					.setCandidateOppositionList(candidateOppositionVO);
			if (constituencyElectionResults != null) {
				for (CandidateOppositionVO oppositionCand : constituencyElectionResults
						.getCandidateOppositionList()) {
					if (oppositionCand.getRank().equals(new Long(2))) {
						Double wonVotesMargin = new Double(
								constituencyElectionResults
										.getCandidateResultsVO()
										.getVotesEarned())
								- new Double(oppositionCand.getVotesEarned());
						Double wonVotesPercentMargin = new Double(
								constituencyElectionResults
										.getCandidateResultsVO()
										.getVotesPercentage())
								- new Double(oppositionCand
										.getVotesPercentage());
						Long votesMarg = wonVotesMargin.longValue();
						constituencyElectionResults.getCandidateResultsVO()
								.setVotesMargin(votesMarg.toString());
						constituencyElectionResults
								.getCandidateResultsVO()
								.setVotesPercentMargin(
										new BigDecimal(wonVotesPercentMargin)
												.setScale(
														2,
														BigDecimal.ROUND_HALF_UP)
												.toString());
					}
					Double votesMargin = new Double(oppositionCand
							.getVotesEarned())
							- new Double(constituencyElectionResults
									.getCandidateResultsVO().getVotesEarned());
					Double votesPercentMargin = new Double(oppositionCand
							.getVotesPercentage())
							- new Double(constituencyElectionResults
									.getCandidateResultsVO()
									.getVotesPercentage());
					Long votesMarg = votesMargin.longValue();
					oppositionCand.setVotesMargin(votesMarg.toString());
					oppositionCand.setVotesPercentMargin(new BigDecimal(
							votesPercentMargin.longValue()).setScale(2,
							BigDecimal.ROUND_HALF_UP).toString());
				}
			}
			List allElectionYears = nominationDAO
					.getAllElectionYearsForAConstituency(constituencyId,
							electionType);
			for (int i = 0; i < allElectionYears.size(); i++) {
				SelectOptionVO eleYear = new SelectOptionVO();
				eleYear.setId(Long
						.parseLong(allElectionYears.get(i).toString()));
				eleYear.setName(allElectionYears.get(i).toString());
				allYears.add(eleYear);
			}
			constituencyElectionResults.setAllElectionYears(allYears);
			return constituencyElectionResults;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return null;
		}
	}

	// ZPTC's And MPTC's related code.

	/*
	 * This method retrieves all the ZPTC's winner candidates present in that
	 * particular district
	 */
	public MandalAllElectionDetailsVO getAllZptcOrMptcWinnerForADistrictForLatestYear(
			Long districtId, String electionYear, String electionType) {
		List<MandalAllElectionDetailsVO> allVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(
				0);
		MandalAllElectionDetailsVO mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();
		Long winnerRank = 1l, successorRank = 2l;
		try {
			log.info("Making nominationDAO.findAllZPTCsInaDistrict DAO call");
			List successorCandidate = nominationDAO
					.findAllZPTCsOrMPTCsInaDistrict(districtId, electionType,
							successorRank, electionYear);

			log.info("Making nominationDAO.findAllZPTCsInaDistrict DAO call");
			List winningCandidate = nominationDAO
					.findAllZPTCsOrMPTCsInaDistrict(districtId, electionType,
							winnerRank, electionYear);

			log.info("Calling populateElectionsData() method..");
			allVotersDetails = populateElectionsData(winningCandidate,
					successorCandidate, 0, 0, electionType);

			mandalAllElectionDetailsVo.setAllVotersDetails(allVotersDetails);

			return mandalAllElectionDetailsVo;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * This method retrieves all the MPTC and ZPTC's Party candidates present in
	 * that particular district.
	 */
	public MandalAllElectionDetailsVO getAllZptcsMptcsForADistrictForAPartyForSelectedYear(
			Long districtId, String electionYear, Long partyId, int flag,
			int lostFlag, String electionType) {
		if (IConstants.MPTC_ELECTION_TYPE.equalsIgnoreCase(electionType))
			flag = 0;
		List<MandalAllElectionDetailsVO> winningCandidates = new ArrayList<MandalAllElectionDetailsVO>(
				0);
		List<MandalAllElectionDetailsVO> successorCandidates = new ArrayList<MandalAllElectionDetailsVO>(
				0);
		List<MandalAllElectionDetailsVO> allVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(
				0);
		MandalAllElectionDetailsVO mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();

		Long winnerRank = 1l, successorRank = 2l;
		try {
			log
					.info("Making nominationDAO.findAllZptcPartysInaDistrict DAO call");
			List partySuccessorsResult = nominationDAO
					.findAllZptcPartysInaDistrict(districtId, electionType,
							electionYear, partyId, winnerRank);

			log
					.info("Making nominationDAO.findAllZptcPartysWinnerInaDistrict DAO call");
			List partyWinningCandidate = nominationDAO
					.findAllZptcPartysWinnerInaDistrict(districtId,
							electionType, electionYear, partyId, winnerRank);

			log.info("Making nominationDAO.findAllZPTCsInaDistrict DAO call");
			List winningCandidate = nominationDAO
					.findAllZPTCsOrMPTCsInaDistrict(districtId, electionType,
							winnerRank, electionYear);

			log.info("Making nominationDAO.findAllZPTCsInaDistrict DAO call");
			List successorCandidate = nominationDAO
					.findAllZPTCsOrMPTCsInaDistrict(districtId, electionType,
							successorRank, electionYear);

			log
					.info("Calling populateElectionsDataForAllCandidates() method..");
			successorCandidates = populateElectionsDataForAllCandidates(
					winningCandidate, partySuccessorsResult, 0, electionType);

			if (lostFlag != 1) {
				winningCandidates = populateElectionsData(
						partyWinningCandidate, successorCandidate, flag, 0,
						electionType);
				if (winningCandidates != null) {
					allVotersDetails.addAll(winningCandidates);
				}
			}
			if (successorCandidates != null && flag != 1) {
				allVotersDetails.addAll(allVotersDetails.size(),
						successorCandidates);
			}
			mandalAllElectionDetailsVo.setAllVotersDetails(allVotersDetails);

			return mandalAllElectionDetailsVo;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * This method retrieves all the winning candidates participated in that
	 * particular Zptc for a particular election year.
	 */
	public MandalAllElectionDetailsVO getAllZptcsOrMptcsCandidatesForADistrictForSelectedYear(
			Long districtId, String electionYear, int lostFlag,
			String electionType) {

		List<MandalAllElectionDetailsVO> winningCandidateVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(
				0);
		List<MandalAllElectionDetailsVO> allVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(
				0);

		MandalAllElectionDetailsVO mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();
		Long winnerRank = 1l, successorRank = 2l;
		try {
			log.info("Making nominationDAO.findAllZPTCsInaDistrictDAO call");
			List successorCandidate = nominationDAO
					.findAllZPTCsOrMPTCsInaDistrict(districtId,
							IConstants.ZPTC_ELECTION_TYPE, successorRank,
							electionYear);

			log.info("Making nominationDAO.findAllZPTCsInaDistrict DAO call");
			List winningCandidate = nominationDAO
					.findAllZPTCsOrMPTCsInaDistrict(districtId,
							IConstants.ZPTC_ELECTION_TYPE, winnerRank,
							electionYear);

			log.info("Making nominationDAO.findAllZptcCandidatesInaDistrict DAO call");
			List allCandidates = nominationDAO
					.findAllZptcOrMptcCandidatesInaDistrict(districtId,
							IConstants.ZPTC_ELECTION_TYPE, electionYear);

			log
					.info("Calling populateElectionsDataForAllCandidates() method..");
			allVotersDetails = populateElectionsDataForAllCandidates(
					winningCandidate, allCandidates, 0,
					IConstants.ZPTC_ELECTION_TYPE);

			if (lostFlag != 1) {
				log.info("Calling populateElectionsData() method..");
				winningCandidateVotersDetails = populateElectionsData(
						winningCandidate, successorCandidate, 0, 0,
						IConstants.ZPTC_ELECTION_TYPE);
				if (winningCandidateVotersDetails != null) {
					allVotersDetails.addAll(allVotersDetails.size(),
							winningCandidateVotersDetails);
				}
			}
			mandalAllElectionDetailsVo.setAllVotersDetails(allVotersDetails);

			return mandalAllElectionDetailsVo;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * This method calculates all the voting difference between the winning and
	 * the looser candidates and winner sets a VO containing all the
	 * information.
	 */
	public List<MandalAllElectionDetailsVO> populateElectionsDataForAllCandidates(
			List winningCandidate, List allCandidates, int reservationZone,
			String module) {
		List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO = new ArrayList<MandalAllElectionDetailsVO>(
				0);
		Map<Long, Float> winner = new HashMap<Long, Float>(0);
		Float differenceVotes, votesPercentage;
		Long constituencyId;
		try {
			for (int i = 0; i < winningCandidate.size(); i++) {
				Object[] parms = (Object[]) winningCandidate.get(i);
				winner.put(Long.parseLong(parms[9].toString()), Float
						.parseFloat(parms[6].toString()));
			}
			log.info("Inside populateElectionsDataForAllCandidates() method..");
			for (int i = 0; i < allCandidates.size(); i++) {
				Object[] parms = (Object[]) allCandidates.get(i);
				MandalAllElectionDetailsVO mandalAllElectionDetails = new MandalAllElectionDetailsVO();
				constituencyId = Long.parseLong(parms[9].toString());
				if (parms[0] != null) {
					mandalAllElectionDetails.setPartyFlag(parms[0].toString());
				} else {
					mandalAllElectionDetails.setPartyFlag("no_Image.png");
				}
				mandalAllElectionDetails.setElectionYear(parms[1].toString());
				mandalAllElectionDetails.setTehsilName(parms[3].toString());
				mandalAllElectionDetails.setElectionType(parms[4].toString());
				mandalAllElectionDetails.setTehsilId(Long.parseLong(parms[5]
						.toString()));
				mandalAllElectionDetails.setVotesEarned(Float
						.parseFloat(parms[6].toString()));
				mandalAllElectionDetails.setVotesPolled(Float
						.parseFloat(parms[7].toString()));
				mandalAllElectionDetails.setRank(parms[8].toString());
				mandalAllElectionDetails.setVotesPercentage(parms[10]
						.toString());
				if (reservationZone == 1) {
					if (parms[13] != null) {
						mandalAllElectionDetails.setReservationZone(parms[13]
								.toString());
					} else {
					}
				}
				if (parms[14] != null) {
					mandalAllElectionDetails.setConstituencyName(parms[14]
							.toString());
				} else {
					mandalAllElectionDetails.setConstituencyName(" ");
				}

				if (parms[11] != null) {
					mandalAllElectionDetails.setPartyShortName(parms[11]
							.toString());
				} else {
					mandalAllElectionDetails.setPartyShortName(" ");
				}

				if ((module.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE))
						|| (module
								.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE))) {

					if (parms[14] != null) {
						mandalAllElectionDetails.setConstituencyName(parms[14]
								.toString());
					} else {
						mandalAllElectionDetails.setConstituencyName("");
					}
				}
				mandalAllElectionDetails.setConstituencyId(Long
						.parseLong(parms[9].toString()));
				mandalAllElectionDetails.setPartyId(Long.parseLong(parms[12]
						.toString()));
				if (winner.containsKey(constituencyId)) {
					differenceVotes = winner.get(constituencyId)
							- Float.parseFloat(parms[6].toString());
					if (winner.get(constituencyId) != 0) {
						votesPercentage = differenceVotes
								/ winner.get(constituencyId) * 100;
						mandalAllElectionDetails
								.setMarginVotesPercentage(new BigDecimal(
										votesPercentage.floatValue()).setScale(
										2, BigDecimal.ROUND_HALF_UP));
					} else {
						votesPercentage = 0f;
						mandalAllElectionDetails
								.setMarginVotesPercentage(new BigDecimal(0));
					}
					mandalAllElectionDetails.setVotesDifference(Float
							.parseFloat(differenceVotes.toString()));
				} else {
					differenceVotes = 0f;
					mandalAllElectionDetails
							.setVotesDifference(differenceVotes);
				}
				if (differenceVotes != 0f) {
					mandalAllElectionDetails.setCandidateName(parms[2]
							.toString());
				} else {
					mandalAllElectionDetails.setCandidateName(parms[2]
							.toString());
					mandalAllElectionDetails
							.setMarginVotesPercentage(new BigDecimal(0));
				}
				if (Long.parseLong(parms[8].toString()) != 1) {
					mandalAllElectionDetailsVO.add(mandalAllElectionDetails);
				}
				// System.out.println(mandalAllElectionDetails.getCandidateName()+"\t\t"+mandalAllElectionDetails.getTehsilName()+"\t\t"+mandalAllElectionDetails.getVotesPercentage());
			}
			return mandalAllElectionDetailsVO;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * This method calculates all the voting difference between the winning and
	 * the successor candidates and winner sets a VOcontaining all the
	 * information
	 */
	public List<MandalAllElectionDetailsVO> populateElectionsData(
			List winningCandidate, List successorCandidate, int flag,
			int reservationZone, String module) {
		List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO = new ArrayList<MandalAllElectionDetailsVO>(
				0);
		Long constituencyId;
		Float differenceVotes, votesPercentage;
		Map<Long, Float> winner = new HashMap<Long, Float>(0);
		Map<Long, Float> successor = new HashMap<Long, Float>(0);
		try {
			for (int i = 0; i < successorCandidate.size(); i++) {
				Object[] parms = (Object[]) successorCandidate.get(i);
				successor.put(Long.parseLong(parms[9].toString()), Float
						.parseFloat(parms[6].toString()));
			}
			log.info("Inside populateElectionsData() method..");
			for (int i = 0; i < winningCandidate.size(); i++) {
				MandalAllElectionDetailsVO mandalAllElectionDetails = new MandalAllElectionDetailsVO();
				Object[] parms = (Object[]) winningCandidate.get(i);
				constituencyId = Long.parseLong(parms[9].toString());
				if (parms[0] == null) {
					mandalAllElectionDetails.setPartyFlag("no_Image.png");
				} else {
					mandalAllElectionDetails.setPartyFlag(parms[0].toString());
				}
				mandalAllElectionDetails.setElectionYear(parms[1].toString());
				mandalAllElectionDetails.setTehsilName(parms[3].toString());
				mandalAllElectionDetails.setElectionType(parms[4].toString());
				mandalAllElectionDetails.setTehsilId(Long.parseLong(parms[5]
						.toString()));
				mandalAllElectionDetails.setVotesEarned(Float
						.parseFloat(parms[6].toString()));
				if (parms[7] != null) {
					mandalAllElectionDetails.setVotesPolled(Float
							.parseFloat(parms[7].toString()));
				}
				mandalAllElectionDetails.setRank(parms[8].toString());
				mandalAllElectionDetails.setVotesPercentage(parms[10]
						.toString());
				if (parms[11] != null) {
					mandalAllElectionDetails.setPartyShortName(parms[11]
							.toString());
				} else {
					mandalAllElectionDetails.setPartyShortName(" ");
				}
				if (reservationZone == 1) {
					if (parms[13] != null) {
						mandalAllElectionDetails.setReservationZone(parms[13]
								.toString());
					} else {
					}
				}

				if ((module.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE))
						|| (module
								.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE))) {
					if (parms[14] != null) {
						mandalAllElectionDetails.setConstituencyName(parms[14]
								.toString());
					} else {
						mandalAllElectionDetails.setConstituencyName("");
					}
				}

				mandalAllElectionDetails.setConstituencyId(Long
						.parseLong(parms[9].toString()));
				mandalAllElectionDetails.setPartyId(Long.parseLong(parms[12]
						.toString()));
				if (successor.containsKey(constituencyId)) {
					differenceVotes = (Float.parseFloat(parms[6].toString()) - successor
							.get(constituencyId));
					if (Float.parseFloat(parms[6].toString()) != 0f) {
						votesPercentage = differenceVotes
								/ (Float.parseFloat(parms[6].toString())) * 100;
						mandalAllElectionDetails
								.setMarginVotesPercentage(new BigDecimal(
										votesPercentage.floatValue()).setScale(
										2, BigDecimal.ROUND_HALF_UP));
					} else {
						votesPercentage = 0f;
						mandalAllElectionDetails
								.setMarginVotesPercentage(new BigDecimal(0));
					}
					mandalAllElectionDetails.setVotesDifference(Float
							.parseFloat(differenceVotes.toString()));
				} else {
					differenceVotes = 0f;
					mandalAllElectionDetails
							.setVotesDifference(differenceVotes);
				}
				if (differenceVotes != 0f) {
					mandalAllElectionDetails.setCandidateName(parms[2]
							.toString());
				} else {
					if (parms[2].toString() != null) {
						mandalAllElectionDetails.setCandidateName(parms[2]
								.toString());
						mandalAllElectionDetails
								.setMarginVotesPercentage(new BigDecimal(0));
					}
				}
				if (flag == 1) {
					if (new Long(parms[8].toString()) == 1) {
						mandalAllElectionDetailsVO
								.add(mandalAllElectionDetails);
					} else {
					}
				} else {
					mandalAllElectionDetailsVO.add(mandalAllElectionDetails);
				}
				// System.out.println(mandalAllElectionDetails.getCandidateName()+"\t\t"+mandalAllElectionDetails.getTehsilName()+"\t\t"+mandalAllElectionDetails.getVotesPercentage());
			}
			return mandalAllElectionDetailsVO;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * This method takes districtId,electionType and electionYear and generates
	 * a list of VO that contains partyId and partyName
	 */
	public MandalAllElectionDetailsVO getAllPartiesForAParticularElection(
			Long districtId, String electionType, String electionYear) {
		MandalAllElectionDetailsVO mandalAllElectionDetailsVO = new MandalAllElectionDetailsVO();
		List<SelectOptionVO> SelectOptionVo = new ArrayList<SelectOptionVO>(0);
		try {
			List result = nominationDAO.getAllPartysForAParticularElectionYear(
					districtId, electionType, electionYear);
			SelectOptionVO selectOption = new SelectOptionVO();
			selectOption.setId(0l);
			selectOption.setName("Select Party");
			SelectOptionVo.add(selectOption);
			for (int i = 0; i < result.size(); i++) {
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				Object[] parms = (Object[]) result.get(i);
				selectOptionVO.setId(Long.parseLong(parms[0].toString()));
				selectOptionVO.setName(parms[1].toString());
				SelectOptionVo.add(selectOptionVO);
			}
			mandalAllElectionDetailsVO.setPartyInfo(SelectOptionVo);
			return mandalAllElectionDetailsVO;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method gets all the important parties in the state.
	 */
	public List<SelectOptionVO> getStaticParties() {
		try {
			log.debug("Entered in to getStaticParties() method..");
			List<SelectOptionVO> staticParties = new ArrayList<SelectOptionVO>();
			log.debug("Making partyDAO.findByShortNames() DAO call...");
			List<Party> parties = partyDAO
					.findByShortNames(IConstants.STATIC_PARTIES);
			for (Party party : parties) {
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(party.getPartyId());
				selectOptionVO.setName(party.getShortName());
				staticParties.add(selectOptionVO);
			}
			return staticParties;
		} catch (Exception e) {
			e.printStackTrace();
			if (log.isDebugEnabled()) {
				log
						.debug("Exception raised in getStaticParties() method of Static Data Service ");
			}
			return null;
		}
	}

	/**
	 * This method gets all the important parties in the state.
	 */
	public List<Long> getStaticPartiesAsList(Long stateId) {
		List<Party> parties = null;
		try {
			List<Long> list = new ArrayList<Long>();
			log.debug("Making partyDAO.findByShortNames() DAO call...");
			if (stateId.intValue() == 1) {
				parties = partyDAO.findByShortNames(IConstants.STATIC_PARTIES);
			} else if (stateId.intValue() == 24) {// tamilnadu
				parties = partyDAO
						.findByShortNames(IConstants.STATIC_TAMIL_NADU_PARTIES);
			} else if (stateId.intValue() == 12) {// karnataka
				parties = partyDAO
						.findByShortNames(IConstants.STATIC_KARNATAKA_PARTIES);
			} else {
				parties = partyDAO
						.findByShortNames(IConstants.NATIONAL_STATIC_PARTIES);
			}
			for (Party party : parties) {
				list.add(party.getPartyId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			if (log.isDebugEnabled()) {
				log
						.debug("Exception raised in getStaticParties() method of Static Data Service ");
			}
			return null;
		}
	}

	/**
	 * This method return all parties that have participated in that particular
	 * election year.
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getAllPartiesForAnElectionYear(
			String electionYear, String electionType) {
		ResultStatus resultVO = new ResultStatus();
		resultVO.setResultCode(ResultCodeMapper.SUCCESS);
		List<SelectOptionVO> selectOptionVO = new ArrayList<SelectOptionVO>(0);
		try {
			List list = null;

			if (electionType
					.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)
					|| electionType
							.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)
					|| electionType
							.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE)
					|| electionType
							.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE)) {
				list = nominationDAO.getAllPartiesForAnElectionYear(
						electionYear, electionType);
			} else {
				list = nominationDAO
						.getAllPartiesForAnElectionYearForLocalBody(
								electionYear, electionType);
			}
			Map<Long, String> parties = new HashMap<Long, String>(0);
			ListIterator li = list.listIterator();
			while (li.hasNext()) {
				Object[] parms = (Object[]) li.next();
				parties.put(new Long(parms[0].toString()), parms[1].toString());
			}
			Iterator it = parties.keySet().iterator();
			while (it.hasNext()) {
				SelectOptionVO selectOptionVo = new SelectOptionVO();
				Object temp = it.next();
				selectOptionVo.setId(new Long(temp.toString()));
				selectOptionVo.setName(parties.get(temp));
				selectOptionVO.add(selectOptionVo);
				Collections.sort(selectOptionVO);
			}
		} catch (Exception ex) {
			resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.FAILURE);
			resultVO.setResultPartial(true);
		}
		return selectOptionVO;
	}

	public List<SelectOptionVO> getElectionTypes(List list) {
		List<SelectOptionVO> allElecList = new ArrayList<SelectOptionVO>(0);
		for (int i = 0; i < list.size(); i++) {
			Object[] parms = (Object[]) list.get(i);
			SelectOptionVO seleVO = new SelectOptionVO();
			seleVO.setId(new Long(parms[0].toString()));
			seleVO.setName(parms[1].toString());
			allElecList.add(seleVO);
		}
		return allElecList;
	}

	@SuppressWarnings("unchecked")
	public DistrictWisePartyResultVO getElectionResultsForDistrict(
			Long electionScopeId, Long districtId) {
		List<ElectionResultVO> electionVO = new ArrayList<ElectionResultVO>(0);

		DistrictWisePartyResultVO districtWisePartyResultVO = getDistrictWiseElectionReport(
				electionScopeId, districtId);
		List<PartyResultVO> parList = districtWisePartyResultVO
				.getPartyElectionResultsList();
		Set<String> party = new HashSet<String>(0);
		List list = new ArrayList(0);
		List<SelectOptionVO> allElecList = new ArrayList<SelectOptionVO>(0);
		if (districtWisePartyResultVO.getElectionYearsAndType() != null
				&& districtWisePartyResultVO.getElectionYearsAndType().size() != 0) {
			for (SelectOptionVO selLIst : districtWisePartyResultVO
					.getElectionYearsAndType()) {
				allElecList.add(selLIst);
			}
		}
		for (PartyResultVO result : parList) {
			party.add(result.getPartyName());
		}

		if (electionScopeId == 0) {
			list = nominationDAO
					.getAllElectionIdsAndYearsForADistrict(districtId);
			allElecList.addAll(getElectionTypes(list));
		} else if (electionScopeId != 1) {
			list = partyElectionDistrictResultDAO
					.getAllElectionResultsInDistrict(electionScopeId,
							districtId);
			allElecList = getElectionTypes(list);
		}
		Collections.sort(allElecList, new SelectOptionVOByIdComparator());
		for (int i = 0; i < allElecList.size(); i++) {
			ElectionResultVO electionResultVO = new ElectionResultVO();
			Set<String> party2 = new HashSet<String>(party);
			Map<String, PartyElectionResultVO> seleList = new HashMap<String, PartyElectionResultVO>();
			List<PartyElectionResultVO> selectList = new ArrayList<PartyElectionResultVO>();

			String electYear = allElecList.get(i).getId().toString();
			String electType = allElecList.get(i).getName();

			electionResultVO.setElectionType(electType);
			electionResultVO.setElectionYear(electYear);

			for (PartyResultVO result : parList) {
				List<ElectionResultVO> elecList = result
						.getElectionWiseResults();
				for (ElectionResultVO resultVO : elecList) {
					if (electYear.equalsIgnoreCase(resultVO.getElectionYear())
							&& electType.equalsIgnoreCase(resultVO
									.getElectionType())) {
						PartyElectionResultVO partyElectionResultVO = new PartyElectionResultVO();
						partyElectionResultVO.setVotesPercentage(resultVO
								.getPercentage());
						partyElectionResultVO.setVotesPercent(new BigDecimal(
								resultVO.getPercentage()).setScale(2,
								BigDecimal.ROUND_HALF_UP));
						partyElectionResultVO.setPartyName(result
								.getPartyName());
						// seleList.add(partyElectionResultVO);
						seleList.put(result.getPartyName(),
								partyElectionResultVO);
						party2.remove(result.getPartyName());
					}
				}
			}
			if (party2 != null && party2.size() != 0) {
				Iterator it = party2.iterator();
				while (it.hasNext()) {
					PartyElectionResultVO partyElectionResultVO = new PartyElectionResultVO();
					partyElectionResultVO.setVotesPercentage("0");
					partyElectionResultVO.setVotesPercent(new BigDecimal(0));
					partyElectionResultVO.setPartyName(it.next().toString());
					// seleList.add(partyElectionResultVO);
					seleList.put(partyElectionResultVO.getPartyName(),
							partyElectionResultVO);
				}
			}
			selectList.addAll(seleList.values());
			Collections.sort(selectList, new PartyElectionResultComparator());
			electionResultVO.setResult(selectList);
			electionVO.add(electionResultVO);
		}
		districtWisePartyResultVO.setResult(electionVO);

		return districtWisePartyResultVO;
	}

	/**
	 * This method can be used to get a report of different elections that are
	 * happened in a particular district.
	 * 
	 * @return DistrictWisePartyResultVO
	 * @see getPartyElectionResultsForAPartyDistrictLevel(Long electionId, Long
	 *      partyId, Long districtId)
	 * 
	 *      Reworked by <a href="y.ravi@itgrids.com">y.ravi@itgrids.com</a>
	 * @serialData 13-11-10
	 * @version 1.2
	 */
	public DistrictWisePartyResultVO getDistrictWiseElectionReport(
			Long electionScopeId, Long districtId) {
		DistrictWisePartyResultVO districtWisePartyResultVO = new DistrictWisePartyResultVO();
		try {
			ElectionScope electionScope = null;
			List partiesResults = null;
			List result = nominationDAO
					.getAllPartyDetailsForAllElectionYearsInADistrict(districtId);
			PartyResultVO partyResultVO = new PartyResultVO();
			StringBuilder partyId = new StringBuilder();
			StringBuilder electionId = new StringBuilder();
			Long stateId = 1l;
			for (int i = 0; i < result.size(); i++) {
				Object[] listResult = (Object[]) result.get(i);
				PartyElectionDistrictResult partyElectionDistrictResult = getPartyElectionResultsForAPartyDistrictLevel(
						new Long(listResult[3].toString()), new Long(
								listResult[4].toString()), districtId);
				if (partyElectionDistrictResult == null) {
					savePartyElectionResultForAPartyForAElectionDistrictLevel(
							new Long(listResult[3].toString()), new Long(
									listResult[4].toString()), districtId);
				} else {
				}
				electionId.append(",").append((Long) listResult[3]);
				partyId.append(",").append((Long) listResult[4]);
			}

			if (electionScopeId == 0)
				partiesResults = partyElectionDistrictResultDAO
						.getAllParyDetailsForAllElectionYearsForADistrict(
								electionId.substring(1), partyId.substring(1),
								districtId);
			else {
				electionScope = electionScopeDAO.get(electionScopeId);
				partiesResults = partyElectionDistrictResultDAO
						.getAllElectionResultsInDistrictForElectionType(
								electionScopeId, districtId);
			}

			Map<PartyResultVO, List<ElectionResultVO>> allPartiesInElecsMap = new LinkedHashMap<PartyResultVO, List<ElectionResultVO>>();
			List<PartyResultVO> partyResults = new ArrayList<PartyResultVO>();
			PartyResultVO eachPartyInfo = null;
			List<ElectionResultVO> electionsOfParty = null;
			Set<SelectOptionVO> selectVO = new HashSet<SelectOptionVO>(0);
			ElectionResultVO partyElecReuslt = null;
			Long partySeatsWon = 0l;

			for (Object[] values : (List<Object[]>) partiesResults) {
				eachPartyInfo = new PartyResultVO();
				eachPartyInfo.setPartyId((Long) values[0]);
				eachPartyInfo.setPartyName(values[1].toString());
				electionsOfParty = allPartiesInElecsMap.get(eachPartyInfo);
				if (electionsOfParty == null)
					electionsOfParty = new ArrayList<ElectionResultVO>();
				partyElecReuslt = new ElectionResultVO();
				partyElecReuslt.setElectionType(values[3].toString());
				partyElecReuslt.setElectionYear(values[2].toString());
				partyElecReuslt
						.setVotesEarned(((Double) values[4]).longValue());
				partyElecReuslt.setPercentage(values[5].toString());
				partyElecReuslt.setNoOfSeatsWon(new Long(values[6].toString()));
				electionsOfParty.add(partyElecReuslt);
				allPartiesInElecsMap.put(eachPartyInfo, electionsOfParty);
			}

			// Booth Wise Calculation For Parliament Parties Performance
			if (electionScopeId == 0
					|| IConstants.PARLIAMENT_ELECTION_TYPE
							.equalsIgnoreCase(electionScope.getElectionType()
									.getElectionType())) {
				Map<String, Long> yearWithPolledVotes = new HashMap<String, Long>();
				List parliamentValidVotes = boothResultDAO
						.getAllPolledVotesByElectionsInDistrict(districtId,
								IConstants.PARLIAMENT_ELECTION_TYPE);

				for (Object[] values : (List<Object[]>) parliamentValidVotes)
					yearWithPolledVotes.put(values[0].toString(),
							(Long) values[1]);

				List partiesResultsInParliament = candidateBoothResultDAO
						.findAllPartiesElectionResultsInDistrictForElectionType(
								districtId, IConstants.PARLIAMENT_ELECTION_TYPE);

				for (Object[] values : (List<Object[]>) partiesResultsInParliament) {
					eachPartyInfo = new PartyResultVO();
					eachPartyInfo.setPartyId((Long) values[1]);
					eachPartyInfo.setPartyName(values[2].toString());
					electionsOfParty = allPartiesInElecsMap.get(eachPartyInfo);
					if (electionsOfParty == null)
						electionsOfParty = new ArrayList<ElectionResultVO>();
					partyElecReuslt = new ElectionResultVO();
					partyElecReuslt
							.setElectionType(IConstants.PARLIAMENT_ELECTION_TYPE);
					partyElecReuslt.setElectionYear(values[0].toString());
					SelectOptionVO seleVO = new SelectOptionVO();
					seleVO.setId(new Long(values[0].toString()));
					seleVO.setName(IConstants.PARLIAMENT_ELECTION_TYPE);
					selectVO.add(seleVO);
					partyElecReuslt.setVotesEarned((Long) values[3]);
					partyElecReuslt.setPercentage(new BigDecimal(
							((Long) values[3])
									* 100.0
									/ yearWithPolledVotes.get(values[0]
											.toString())).setScale(2,
							BigDecimal.ROUND_HALF_UP).toString());
					partyElecReuslt.setNoOfSeatsWon(0l);
					electionsOfParty.add(partyElecReuslt);
					allPartiesInElecsMap.put(eachPartyInfo, electionsOfParty);
				}

			}

			for (Map.Entry<PartyResultVO, List<ElectionResultVO>> entry : allPartiesInElecsMap
					.entrySet()) {
				eachPartyInfo = entry.getKey();
				electionsOfParty = entry.getValue();
				partySeatsWon = 0l;

				for (ElectionResultVO electionResultVO : electionsOfParty)
					partySeatsWon += electionResultVO.getNoOfSeatsWon();
				eachPartyInfo.setElectionWiseResults(electionsOfParty);
				eachPartyInfo.setSeatsWonCountToSort(partySeatsWon);
				partyResults.add(eachPartyInfo);
			}

			Collections.sort(partyResults, new PartyResultVOComparator());
			districtWisePartyResultVO.setPartyElectionResultsList(partyResults);
			districtWisePartyResultVO.setElectionYearsAndType(selectVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return districtWisePartyResultVO;
	}

	public DistrictWisePartyResultVO getAllianceGroupsForElections(
			Long districtId) {
		DistrictWisePartyResultVO districtWisePartyResultVO = new DistrictWisePartyResultVO();
		List<ElectionResultVO> alliancePartiesInElection = new ArrayList<ElectionResultVO>();
		ElectionResultVO electionResultVO = null;
		List<AlliancePartyResultsVO> partiesAlliances = null;
		AlliancePartyResultsVO alliancePartyResultsVO = null;
		List<PartyPositionsVO> partiesInAlliance = null;
		PartyPositionsVO partyPositionsVO = null;
		List<AllianceGroup> alliances = null;
		Group group = null;
		StringBuilder electionIds = new StringBuilder();
		Map<ElectionResultVO, List<AlliancePartyResultsVO>> allianceByElections = new HashMap<ElectionResultVO, List<AlliancePartyResultsVO>>();

		List elections = nominationDAO.getAllElectionsInDistrict(districtId);

		for (Object[] values : (List<Object[]>) elections)
			electionIds.append(IConstants.COMMA).append(values[0]);

		List groupsByElections = electionAllianceDAO
				.findAllianceGroupsInElections(electionIds.toString()
						.substring(1));
		for (Object[] values : (List<Object[]>) groupsByElections) {
			electionResultVO = new ElectionResultVO();
			electionResultVO.setElectionType(values[0].toString());
			electionResultVO.setElectionYear(values[1].toString());
			partiesAlliances = allianceByElections.get(electionResultVO);
			if (partiesAlliances == null)
				partiesAlliances = new ArrayList<AlliancePartyResultsVO>();
			alliancePartyResultsVO = new AlliancePartyResultsVO();
			alliancePartyResultsVO.setAllianceGroupName(values[2].toString());
			alliancePartyResultsVO.setGroupId((Long) values[3]);
			partiesAlliances.add(alliancePartyResultsVO);
			allianceByElections.put(electionResultVO, partiesAlliances);
		}

		for (Map.Entry<ElectionResultVO, List<AlliancePartyResultsVO>> entry : allianceByElections
				.entrySet()) {
			electionResultVO = entry.getKey();
			partiesAlliances = entry.getValue();
			for (AlliancePartyResultsVO pariesInGroup : partiesAlliances) {
				group = groupDAO.get(pariesInGroup.getGroupId());
				alliances = group.getAllianceGroups();
				partiesInAlliance = new ArrayList<PartyPositionsVO>();
				for (AllianceGroup partyInAlliance : alliances) {
					partyPositionsVO = new PartyPositionsVO();
					partyPositionsVO.setPartyName(partyInAlliance.getParty()
							.getShortName());
					partiesInAlliance.add(partyPositionsVO);
				}
				pariesInGroup.setPartiesInAlliance(partiesInAlliance);
			}
			electionResultVO.setPartiesAlliances(partiesAlliances);
			alliancePartiesInElection.add(electionResultVO);
		}

		districtWisePartyResultVO
				.setAlliancePartiesInElection(alliancePartiesInElection);
		return districtWisePartyResultVO;
	}

	public DistrictWisePartyResultVO getAllPartiesPositionsInDistrictElection(
			Long electionId, Long districtId) {
		DistrictWisePartyResultVO districtWisePartyResultVO = new DistrictWisePartyResultVO();
		List<PartyPositionsVO> partyPositionsVOs = new ArrayList<PartyPositionsVO>();
		PartyPositionsVO partyPositionsVO = null;
		List partiesInDistrict = partyElectionDistrictResultDAO
				.getPartiesPositionsInDistrictInElection(electionId,
						districtId, IConstants.VOTES_PERCENT_MARGIN);
		for (Object[] values : (List<Object[]>) partiesInDistrict) {
			partyPositionsVO = new PartyPositionsVO();
			partyPositionsVO.setPartyId((Long) values[0]);
			partyPositionsVO.setPartyName(values[1].toString());
			partyPositionsVO.setTotalSeatsWon(new Long(values[2].toString()));
			partyPositionsVO.setSecondPosWon(new Long(values[3].toString()));
			partyPositionsVO.setThirdPosWon(new Long(values[4].toString()));
			partyPositionsVO.setFourthPosWon(new Long(values[5].toString()));
			partyPositionsVOs.add(partyPositionsVO);
		}
		districtWisePartyResultVO
				.setPartiesPositionsInElection(partyPositionsVOs);
		return districtWisePartyResultVO;
	}

	public PartyElectionStateResult getPartyElectionResultsForAPartyStateLevelInParliamentElection(
			Long electionId, Long partyId, Long stateId) {
		log
				.debug("Inside getPartyElectionResultsForAPartyStateLevelInParliamentElection()......");
		if (electionId != null && partyId != null && stateId != null) {
			List<PartyElectionStateResult> partyElectionResultsList = partyElectionStateResultDAO
					.getByPartyIdElectionIdAndStateId(partyId, electionId,
							stateId);
			if (partyElectionResultsList != null
					&& partyElectionResultsList.size() > 0) {
				return partyElectionResultsList.get(0);
			}
		}
		return null;
	}

	public PartyElectionStateResult savePartyElectionResultForAPartyForAParliamentElectionStateLevel(
			Long electionId, Long partyId, Long stateId) {
		log
				.debug("Inside savePartyElectionResultForAPartyForAParliamentElectionStateLevel()");
		log.debug("State Id :" + stateId);
		PartyElectionStateResult partyElectionStateResult = null;
		List<Nomination> nominations = null;
		Election election = null;
		Party party = null;
		State state = null;
		Long totalSeatsWon = new Long(0);
		Long totalSecondPositions = new Long(0);
		Long totalThirdPositions = new Long(0);
		Long totalFourthPositions = new Long(0);
		Long totalNthPositions = new Long(0);
		Long totalConstiParticipated = new Long(0);
		Double totalVotesEarned = new Double(0);
		Double totalValidVotes = new Double(0);
		Double totalVotesPercentage = new Double(0);
		Long completeValidVotes = new Long(0);
		Double completeVotesPercent = new Double(0);

		try {
			if (electionId != null && partyId != null && stateId != null) {
				nominations = nominationDAO.findByElectionIdAndPartyIdStateId(
						electionId, partyId, stateId);
				election = electionDAO.get(electionId);
				party = partyDAO.get(partyId);
				state = stateDAO.get(stateId);

				if (nominations != null && nominations.size() > 0
						&& election != null && party != null && state != null) {
					completeValidVotes = getCompleteValidVotesInState(
							electionId, stateId);
					for (Nomination nominationForParty : nominations) {
						if (nominationForParty.getParty().getPartyId().equals(
								partyId)) {
							Long candidRank = nominationForParty
									.getCandidateResult().getRank();
							Double votesEarned = nominationForParty
									.getCandidateResult().getVotesEarned();
							Double validVotes = nominationForParty
									.getConstituencyElection()
									.getConstituencyElectionResult()
									.getValidVotes();
							totalVotesEarned += votesEarned;
							totalValidVotes += validVotes;

							totalConstiParticipated++;
							if (candidRank.equals(new Long(1)))
								totalSeatsWon++;
							else if (candidRank.equals(new Long(2)))
								totalSecondPositions++;
							else if (candidRank.equals(new Long(3)))
								totalThirdPositions++;
							else if (candidRank.equals(new Long(4)))
								totalFourthPositions++;
							else if (candidRank > new Long(4))
								totalNthPositions++;
						}
					}
					totalVotesPercentage = new BigDecimal(
							(totalVotesEarned * 100) / totalValidVotes)
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
					completeVotesPercent = new BigDecimal(
							(totalVotesEarned * 100) / completeValidVotes)
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
					partyElectionStateResult = savePartyElectionStateResult(
							election, party, state, totalSeatsWon,
							totalSecondPositions, totalThirdPositions,
							totalFourthPositions, totalNthPositions,
							totalConstiParticipated, totalVotesPercentage,
							completeVotesPercent, totalVotesEarned,
							totalValidVotes, completeValidVotes.doubleValue());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return partyElectionStateResult;
	}

	/*
	 * 
	 */
	public PartyElectionStateResult savePartyElectionStateResult(
			final Election election, final Party party, final State state,
			final Long totalSeatsWon, final Long secPos, final Long thirdPos,
			final Long fourthPos, final Long nthPos,
			final Long totConstiParticipated,
			final Double totalVotesPercentage,
			final Double completeVotesPercent, final Double totalVotesGained,
			final Double totalValidVotes, final Double completeConstiValidVotes) {

		PartyElectionStateResult partyElectionStateResultFinal = (PartyElectionStateResult) transactionTemplate
				.execute(new TransactionCallback() {

					public Object doInTransaction(TransactionStatus status) {
						PartyElectionStateResult partyElectionStateResult = null;
						try {
							java.util.Date updatedDate = new java.util.Date();
							String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
							SimpleDateFormat sdf = new SimpleDateFormat(
									DATE_FORMAT);
							String strDateNew = sdf.format(updatedDate);
							updatedDate = sdf.parse(strDateNew);

							partyElectionStateResult = new PartyElectionStateResult();
							partyElectionStateResult.setParty(party);
							partyElectionStateResult.setElection(election);
							partyElectionStateResult.setState(state);
							partyElectionStateResult
									.setTotalSeatsWon(totalSeatsWon.toString());
							partyElectionStateResult.setSecondPosWon(secPos
									.toString());
							partyElectionStateResult.setThirdPosWon(thirdPos
									.toString());
							partyElectionStateResult.setFourthPosWon(fourthPos
									.toString());
							partyElectionStateResult.setNthPosWon(nthPos
									.toString());
							partyElectionStateResult
									.setTotalVotesGained(totalVotesGained);
							partyElectionStateResult
									.setTotalValidVotes(totalValidVotes);
							partyElectionStateResult
									.setCompleteConstiValidVotes(completeConstiValidVotes);
							partyElectionStateResult
									.setCompleteVotesPercent(completeVotesPercent
											.toString());
							partyElectionStateResult
									.setVotesPercentage(totalVotesPercentage
											.toString());
							partyElectionStateResult
									.setTotalConstiParticipated(totConstiParticipated
											.toString());
							partyElectionStateResult
									.setLastUpdated(updatedDate);

							partyElectionStateResult = partyElectionStateResultDAO
									.save(partyElectionStateResult);

						} catch (Exception ex) {
							ex.printStackTrace();
							log.debug("Exception Raised : " + ex);
							status.setRollbackOnly();
						}
						return partyElectionStateResult;
					}

				});
		return partyElectionStateResultFinal;
	}

	@SuppressWarnings("unchecked")
	public ElectionResultPartyVO getElectionResultForAPartyInAnElection(
			Long electionId, Long partyId, Long rank) {

		log.debug(" Inside getElectionResultForAPartyInAnElection Method.... ");

		ElectionResultPartyVO electionResultPartyVO = null;
		List<CandidateElectionResultVO> candidateElectionResultsVO = null;

		ResultStatus resultStatus = new ResultStatus();

		try {
			if (electionId != null && partyId != null && rank != null) {

				electionResultPartyVO = new ElectionResultPartyVO();
				List electionResultsList = null;

				if (!rank.equals(new Long(0)))
					electionResultsList = nominationDAO
							.findElectionResultsByElectionIdAndPartyIdAndRank(
									electionId, partyId, rank);
				else if (rank.equals(new Long(0)))
					electionResultsList = nominationDAO
							.findElectionResultsByElectionIdAndPartyIdAndLostRank(
									electionId, partyId, new Long(1));

				if (electionResultsList != null
						&& electionResultsList.size() > 0) {

					candidateElectionResultsVO = new ArrayList<CandidateElectionResultVO>();
					for (int i = 0; i < electionResultsList.size(); i++) {
						Object[] params = (Object[]) electionResultsList.get(i);
						CandidateElectionResultVO candElecResult = new CandidateElectionResultVO();
						candElecResult.setCandidateId((Long) params[0]);
						candElecResult.setCandidateName((String) params[1]);
						candElecResult.setConstituencyId((Long) params[2]);
						candElecResult.setConstituencyName((String) params[3]);
						candElecResult.setTotalValidVotes(((Double) params[4]).longValue());
						candElecResult.setTotalVotesEarned(((Double) params[5]).longValue());
						candElecResult.setVotesPercentage((String) params[6]);
						candElecResult.setUserComments(new Long(0));

						List oppCandVotesPercent = null;
						if (rank.equals(new Long(0))) {
							candElecResult.setRank((Long) params[7]);
							oppCandVotesPercent = candidateResultDAO
									.getVotesPercentOfACandidateInAnElection(
											electionId, (Long) params[2],
											new Long(1));
						} else {
							oppCandVotesPercent = candidateResultDAO
									.getVotesPercentOfACandidateInAnElection(
											electionId, (Long) params[2],
											new Long(2));
						}
						if (oppCandVotesPercent != null
								&& oppCandVotesPercent.size() > 0) {
							Object votesParams = (Object) oppCandVotesPercent.get(0);
							String oppCandVotesPercnt = (String) votesParams;
					Double votesMargin = new BigDecimal(Double.parseDouble(oppCandVotesPercnt)- Double.parseDouble((String) params[6])).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							candElecResult.setVotesMargin(votesMargin.toString());
						}

		List candComments = commentCategoryCandidateDAO.getCommentsCountForACandidateInAConstituencyInAnELection(electionId, (Long) params[0],(Long) params[2]);
				
		
		if (candComments != null && candComments.size() > 0) {
							Object count = (Object) candComments.get(0);
							candElecResult.setUserComments((Long) count);
						}
						candidateElectionResultsVO.add(candElecResult);
					}

					Party party = partyDAO.get(partyId);

					// set basic data
					electionResultPartyVO.setRank(rank);
					if (rank.equals(new Long(1)))
						electionResultPartyVO.setStatus("WON");
					else
						electionResultPartyVO.setStatus("LOST");
					electionResultPartyVO.setPartyId(partyId);
					electionResultPartyVO.setPartyFlag(party.getPartyFlag());
					electionResultPartyVO.setPartyLongName(party.getLongName());
					electionResultPartyVO.setPartyShortName(party.getShortName());

					electionResultPartyVO
							.setCandidateElectionResultsVO(candidateElectionResultsVO);

					resultStatus.setResultPartial(false);
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					electionResultPartyVO.setResultStatus(resultStatus);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			electionResultPartyVO = new ElectionResultPartyVO();
			electionResultPartyVO.setResultStatus(resultStatus);
		}
		return electionResultPartyVO;
	}

	/**
	 * This method caluculates all the partyTrends in a district for Muncipal
	 * Elections.
	 * 
	 * @param electionType
	 * @param districtId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TeshilPartyInfoVO getAllPartyTrendsForAllMuncipalitiesInADistrict(
			String electionType, Long districtId) {
		String latestMuncipalElectionYear;
		List<TeshilPartyInfoVO> allMuncipalities = new ArrayList<TeshilPartyInfoVO>(
				0);
		TeshilPartyInfoVO teshilPartyInfo = new TeshilPartyInfoVO();
		ResultStatus resultStatus = new ResultStatus();
		try {
			List latestYearResult = electionDAO
					.findLatestElectionYear(electionType);
			latestMuncipalElectionYear = latestYearResult.get(0).toString();
			List result = nominationDAO.getMuncipalityCandidateDetails(
					electionType, districtId);
			allMuncipalities = getLocalElectionPartyDetails(result,
					latestMuncipalElectionYear, electionType);

			teshilPartyInfo.setMuncipalityVO(allMuncipalities);
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			teshilPartyInfo.setResultStatus(resultStatus);
			return teshilPartyInfo;
		} catch (NullPointerException e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			teshilPartyInfo = new TeshilPartyInfoVO();
			teshilPartyInfo.setResultStatus(resultStatus);
			return teshilPartyInfo;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			teshilPartyInfo = new TeshilPartyInfoVO();
			teshilPartyInfo.setResultStatus(resultStatus);
			return teshilPartyInfo;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TeshilPartyInfoVO> getLocalElectionPartyDetails(List result,
			String latestMuncipalElectionYear, String electionType)
			throws GenericException {
		BigDecimal percentage = new BigDecimal(0.0);
		Float totalValidVotes = 0f;
		Long winningCandidateRank = 1l;
		List<TeshilPartyInfoVO> allMuncipalities = new ArrayList<TeshilPartyInfoVO>(
				0);
		if (result == null) {
			throw new GenericException("Failed to retrive data..");
		} else if (result.size() == 0) {
			throw new GenericException("Data Not Available..");
		}
		ListIterator it = result.listIterator();
		while (it.hasNext()) {
			Object[] parmss = (Object[]) it.next();
			String muncipalityId = parmss[1].toString();
			TeshilPartyInfoVO allMuncipalitiesVO = new TeshilPartyInfoVO();
			allMuncipalitiesVO.setMuncipalityName(parmss[0].toString());
			allMuncipalitiesVO.setTotalMuncipalities(result.size());
			allMuncipalitiesVO.setMuncipalityId(Long.parseLong(parmss[1]
					.toString()));
			allMuncipalitiesVO.setTotalWards(Long.parseLong(parmss[2]
					.toString()));
			allMuncipalitiesVO.setTotalVoters((Double) parmss[3]);
			if (parmss.length == 6) {
				allMuncipalitiesVO.setStateId(Long.parseLong(parmss[4]
						.toString()));
				allMuncipalitiesVO.setElectionTypeId(Long.parseLong(parmss[5]
						.toString()));
			}
			allMuncipalitiesVO
					.setLatestMuncipalElectionYear(latestMuncipalElectionYear);
			totalValidVotes = 0f;
			Map<String, Long> winningSeats = new HashMap<String, Long>(0);
			List partyInfo = nominationDAO
					.findSeatsWonByAPartyInMuncipalityForAnElectionYear(
							muncipalityId, latestMuncipalElectionYear,
							electionType, winningCandidateRank);
			if (partyInfo == null) {
				throw new GenericException("Failed to retrive data..");
			} else if (partyInfo.size() == 0) {
				throw new GenericException("Data Not Available..");
			}
			ListIterator partyIds = partyInfo.listIterator();
			while (partyIds.hasNext()) {
				Object[] parms = (Object[]) partyIds.next();
				winningSeats.put(parms[0].toString(), Long.parseLong(parms[1]
						.toString()));
			}
			List allPartyInfo = nominationDAO
					.getPartysInfoForAMuncipalityForAnElectionYear(
							electionType, muncipalityId,
							latestMuncipalElectionYear);
			if (allPartyInfo == null) {
				throw new GenericException("Failed to retrive data..");
			} else if (allPartyInfo.size() == 0) {
				throw new GenericException("Data Not Available..");
			}
			ListIterator partyId = allPartyInfo.listIterator();
			while (partyId.hasNext()) {
				Object[] parms = (Object[]) partyId.next();
				totalValidVotes += Float.parseFloat(parms[2].toString());
			}
			allMuncipalitiesVO.setTotalPolledVotes(totalValidVotes);
			List<TeshilPartyInfoVO> teshilPartyInfoVO = new ArrayList<TeshilPartyInfoVO>(
					0);
			for (int i = 0; i < allPartyInfo.size(); i++) {
				Object[] parms = (Object[]) allPartyInfo.get(i);
				TeshilPartyInfoVO teshilPartyInfoVo = new TeshilPartyInfoVO();
				teshilPartyInfoVo.setPartyName(parms[0].toString());
				teshilPartyInfoVo.setParticipatedSeats(Long.parseLong(parms[1]
						.toString()));
				percentage = new BigDecimal((Float.parseFloat(parms[2]
						.toString()) / totalValidVotes) * 100).setScale(2,
						BigDecimal.ROUND_HALF_UP);
				teshilPartyInfoVo.setPercentageOfVotesWonByParty(Float
						.parseFloat(percentage.toString()));
				if (winningSeats.get(parms[0].toString()) != null) {
					teshilPartyInfoVo.setSeatsWonByParty(Long
							.parseLong(winningSeats.get(parms[0].toString())
									.toString()));
				} else {
					teshilPartyInfoVo.setSeatsWonByParty(0L);
				}
				teshilPartyInfoVO.add(teshilPartyInfoVo);
				Collections.sort(teshilPartyInfoVO,
						new TehsilVotingTrendsComparator());
			}
			allMuncipalitiesVO.setMuncipalityVO(teshilPartyInfoVO);
			allMuncipalities.add(allMuncipalitiesVO);
		}
		return allMuncipalities;
	}

	/**
	 * This method retrives all the muncipality candidate details based on the
	 * user selection inputs.
	 * 
	 */

	public MandalAllElectionDetailsVO getAllMuncipalElectionDetails(
			Long muncipalityId, String candidateDetailsType, Long partyId,
			String electionType) {
		List<MandalAllElectionDetailsVO> allVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(
				0);
		List<MandalAllElectionDetailsVO> winningCandidateVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(
				0);
		MandalAllElectionDetailsVO mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();
		Long winnerRank = 1l, successorRank = 2l;
		List successorCandidate, winningCandidate, allCandidates;
		ResultStatus resultStatus = new ResultStatus();
		int flag = 0;
		try {
			List latestYearResult = electionDAO
					.findLatestElectionYear(electionType);
			String electionYear = latestYearResult.get(0).toString();
			if (candidateDetailsType.equalsIgnoreCase("winners")) {
				successorCandidate = getMuncipalityLevelElectionCandidateDetailsForAConstituency(
						electionType, muncipalityId, electionYear,
						candidateDetailsType, partyId, successorRank);
				winningCandidate = getMuncipalityLevelElectionCandidateDetailsForAConstituency(
						electionType, muncipalityId, electionYear,
						candidateDetailsType, partyId, winnerRank);
				if (successorCandidate == null || winningCandidate == null) {
					throw new GenericException("Failed to retrive data..");
				} else if (successorCandidate.size() == 0
						|| winningCandidate.size() == 0) {
					throw new GenericException("Data Not Available..");
				}
				allVotersDetails = populateElectionsData(winningCandidate,
						successorCandidate, 0, 1,
						IConstants.MUNCIPLE_ELECTION_TYPE);
				mandalAllElectionDetailsVo
						.setAllVotersDetails(allVotersDetails);
			} else if (candidateDetailsType.equalsIgnoreCase("allCandidates")) {
				allCandidates = getMuncipalityLevelElectionCandidateDetailsForAConstituency(
						electionType, muncipalityId, electionYear,
						candidateDetailsType, partyId, 0l);
				successorCandidate = getMuncipalityLevelElectionCandidateDetailsForAConstituency(
						electionType, muncipalityId, electionYear,
						candidateDetailsType, partyId, successorRank);
				winningCandidate = getMuncipalityLevelElectionCandidateDetailsForAConstituency(
						electionType, muncipalityId, electionYear,
						candidateDetailsType, partyId, winnerRank);
				if (allCandidates == null || successorCandidate == null
						|| winningCandidate == null) {
					throw new GenericException("Failed to retrive data..");
				} else if (allCandidates.size() == 0
						|| successorCandidate.size() == 0
						|| winningCandidate.size() == 0) {
					throw new GenericException("Data Not Available..");
				}
				winningCandidateVotersDetails = populateElectionsData(
						winningCandidate, successorCandidate, flag, 1,
						IConstants.MUNCIPLE_ELECTION_TYPE);
				allVotersDetails = populateElectionsDataForAllCandidates(
						winningCandidate, allCandidates, 1,
						IConstants.MUNCIPLE_ELECTION_TYPE);
				if (winningCandidateVotersDetails != null) {
					allVotersDetails.addAll(allVotersDetails.size(),
							winningCandidateVotersDetails);
				}
				mandalAllElectionDetailsVo
						.setAllVotersDetails(allVotersDetails);
			} else if (candidateDetailsType.equalsIgnoreCase("partyWise")) {
				successorCandidate = getMuncipalityLevelElectionCandidateDetailsForAConstituency(
						electionType, muncipalityId, electionYear,
						candidateDetailsType, partyId, successorRank);
				winningCandidate = getMuncipalityLevelElectionCandidateDetailsForAConstituency(
						electionType, muncipalityId, electionYear,
						candidateDetailsType, partyId, winnerRank);
				if (successorCandidate == null || winningCandidate == null) {
					throw new GenericException("Failed to retrive data..");
				} else if (successorCandidate.size() == 0
						|| winningCandidate.size() == 0) {
					throw new GenericException("Data Not Available..");
				}
				allVotersDetails = populateElectionsData(winningCandidate,
						successorCandidate, 0, 1,
						IConstants.MUNCIPLE_ELECTION_TYPE);
				mandalAllElectionDetailsVo
						.setAllVotersDetails(allVotersDetails);
			} else if (candidateDetailsType.equalsIgnoreCase("all")) {
				List result = nominationDAO.getAllPartiesForAMuncipality(
						electionType, muncipalityId, electionYear);
				if (result == null) {
					throw new GenericException("Failed to retrive data..");
				} else if (result.size() == 0) {
					throw new GenericException("Data Not Available..");
				}
				ListIterator li = result.listIterator();
				List<SelectOptionVO> allPartiesInMuncipality = new ArrayList<SelectOptionVO>(
						0);
				while (li.hasNext()) {
					Object[] parms = (Object[]) li.next();
					SelectOptionVO selectOption = new SelectOptionVO();
					selectOption.setId((Long) parms[1]);
					selectOption.setName(parms[0].toString());
					allPartiesInMuncipality.add(selectOption);
				}
				mandalAllElectionDetailsVo
						.setPartyInfo(allPartiesInMuncipality);
			}
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			mandalAllElectionDetailsVo.setResultStatus(resultStatus);
			return mandalAllElectionDetailsVo;
		} catch (NullPointerException e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();
			mandalAllElectionDetailsVo.setResultStatus(resultStatus);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();
			mandalAllElectionDetailsVo.setResultStatus(resultStatus);
			return null;
		}
	}

	/**
	 * This method builds query dynamically to get allCandidates,winners,and
	 * candidates that have participated in that particular constituency.
	 * 
	 * @param tehsilIds
	 * @param candidateDetailsType
	 * @param rank
	 * @param partyId
	 * @param electionType
	 * @param electionYear
	 * @return
	 */
	public List getMuncipalityLevelElectionCandidateDetailsForAConstituency(
			String electionType, Long muncipalityId, String electionYear,
			String candidateDetailsType, Long partyId, Long rank) {
		try {
			Object[] params = { electionType, muncipalityId, electionYear };
			StringBuilder sb = new StringBuilder();
			sb
					.append(" select model.party.partyFlag, model.constituencyElection.election.electionYear,");
			sb
					.append(" upper(model.candidate.lastname),upper(model.constituencyElection.constituency.name),");
			sb
					.append(" model.constituencyElection.constituency.localElectionBody.electionType.electionType,");
			sb
					.append(" model.constituencyElection.constituency.localElectionBody.localElectionBodyId,");
			sb.append(" model.candidateResult.votesEarned,");
			sb
					.append(" model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.rank,model.constituencyElection.constituency.constituencyId,");
			sb
					.append(" model.candidateResult.votesPercengate,model.party.longName,model.party.partyId,");
			sb
					.append(" model.constituencyElection.reservationZone,model.constituencyElection.constituency.name");
			sb
					.append(" from Nomination model where model.constituencyElection.constituency.localElectionBody.electionType.electionType = ?");
			sb
					.append(" and model.constituencyElection.constituency.localElectionBody.localElectionBodyId = ?");
			sb
					.append(" and model.constituencyElection.election.electionYear = ? ");
			if (candidateDetailsType.equalsIgnoreCase("winners")) {
				sb.append(" and model.candidateResult.rank = ").append(rank);
			} else if (candidateDetailsType.equalsIgnoreCase("partyWise")) {
				if (partyId != 0l) {
					sb.append(" and model.party.partyId = ").append(partyId);
				} else {
				}

			} else if (candidateDetailsType.equalsIgnoreCase("allCandidates")) {
				if (rank != 0l) {
					sb.append(" and model.candidateResult.rank = ")
							.append(rank);
				} else {
				}
			}
			if (log.isDebugEnabled()) {
				log
						.debug("Making nominationDAO.getTehsilLevelElectionDetailsForAGivenConstituency().. Call");
			}
			List result = nominationDAO
					.getTehsilLevelElectionDetailsForAGivenConstituency(sb
							.toString(), params);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public CandidateDetailsVO getMuncipalAndCorporationCandidateDetails(
			Long stateId, String electionType, String electionYear, Long rank,
			Long partyId, Long districtId, String resultsCategory) {
		CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
		Long winnerCandidateRank = 1l, successorCandidateRank = 2l;
		List allCandidateResult = null;
		List winnerCandidateResult = null;
		List successorCandidateResult = null;
		List<CandidateDetailsVO> allCandidates = new ArrayList<CandidateDetailsVO>(
				0);
		List<CandidateDetailsVO> winnerCandidate = new ArrayList<CandidateDetailsVO>(
				0);
		List<CandidateDetailsVO> successorCandidate = new ArrayList<CandidateDetailsVO>(
				0);

		if (resultsCategory.equalsIgnoreCase(IConstants.ALL_CANDIDATES)) {
			allCandidateResult = getLocalElectionDetails(stateId, electionType,
					electionYear, rank, partyId, districtId);
			winnerCandidateResult = getLocalElectionDetails(stateId,
					electionType, electionYear, winnerCandidateRank, partyId,
					districtId);
			successorCandidateResult = getLocalElectionDetails(stateId,
					electionType, electionYear, successorCandidateRank,
					partyId, districtId);

			successorCandidate = electionAnalyzeService
					.setWinnerCandidateDetailsIntoVO(winnerCandidateResult,
							successorCandidateResult, 0l, 0, electionType);
			allCandidates = electionAnalyzeService
					.setAllCandidateDetailsIntoVo(winnerCandidateResult,
							allCandidateResult, 1, electionType);
			if (successorCandidate != null) {
				allCandidates.addAll(successorCandidate);
			}
			candidateDetailsVO.setCandidateDetails(allCandidates);
		} else if (resultsCategory
				.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)) {
			winnerCandidateResult = getLocalElectionDetails(stateId,
					electionType, electionYear, winnerCandidateRank, partyId,
					districtId);
			successorCandidateResult = getLocalElectionDetails(stateId,
					electionType, electionYear, successorCandidateRank,
					partyId, districtId);
			winnerCandidate = electionAnalyzeService
					.setWinnerCandidateDetailsIntoVO(winnerCandidateResult,
							successorCandidateResult, 0l, 0, electionType);
			candidateDetailsVO.setCandidateDetails(winnerCandidate);
		}
		return candidateDetailsVO;
	}

	/**
	 * This dynamic Query is used to generate data based on the user selection
	 * criteria in Election report Page(View Candidate Details Button) for
	 * Corporation and Muncipality Election Years.
	 * 
	 * @param stateId
	 * @param electionType
	 * @param electionYear
	 * @param rank
	 * @param partyId
	 * @param districtId
	 * @return
	 */
	public List getLocalElectionDetails(Long stateId, String electionType,
			String electionYear, Long rank, Long partyId, Long districtId) {
		Object[] params = { stateId, electionType, electionYear };
		StringBuilder sb = new StringBuilder();
		sb.append("select model.candidate.candidateId,");
		sb.append(" model.candidate.lastname,");
		sb.append(" model.candidateResult.votesEarned,");
		sb.append(" model.candidateResult.votesPercengate,");
		sb.append(" model.candidateResult.rank,");
		sb.append(" model.party.partyId,");
		sb.append(" model.party.partyFlag,");
		sb.append(" model.party.longName,");
		sb.append(" model.party.shortName,");
		sb.append(" model.constituencyElection.constituency.constituencyId,");
		sb.append(" model.constituencyElection.constituency.name,");
		sb.append(" model.constituencyElection.election.electionYear,");
		sb
				.append(" model.constituencyElection.constituency.localElectionBody.electionType.electionType,");
		sb
				.append(" model.constituencyElection.constituency.localElectionBody.localElectionBodyId");
		sb
				.append(" from Nomination model where model.constituencyElection.constituency.localElectionBody.district.state.stateId = ? and ");
		sb
				.append(" model.constituencyElection.constituency.localElectionBody.electionType.electionType = ?");
		sb.append(" and model.constituencyElection.election.electionYear = ? ");
		if (rank != 0l) {
			sb.append(" and model.candidateResult.rank = ").append(rank);
		}
		if (partyId != 0l) {
			sb.append(" and model.party.partyId = ").append(partyId);
		}
		if (districtId != 0l) {
			sb
					.append(
							" and model.constituencyElection.constituency.localElectionBody.district.districtId = ")
					.append(districtId);
		}
		List result = nominationDAO
				.getTehsilLevelElectionDetailsForAGivenConstituency(sb
						.toString(), params);
		return result;
	}

	public List<SelectOptionVO> getStaticPartiesForCandidateDeatailsReport(
			Long stateId) {
		try {
			log.debug("Entered in to getStaticParties() method..");
			List<SelectOptionVO> staticParties = partyStrengthService
					.getAllPartiesData(stateId);
			SelectOptionVO bspPartyObj;
			SelectOptionVO indPartyObj;

			log.debug("Making partyDAO.findByShortNames() DAO call...");
			/*List<Party> bspParty = partyDAO.findByShortName(IConstants.BSP);*/
			List<Party> indParty = partyDAO.findByShortName(IConstants.IND);

		/*	staticParties.add(new SelectOptionVO(bspParty.get(0).getPartyId(),
					bspParty.get(0).getShortName()));*/
			staticParties.add(new SelectOptionVO(indParty.get(0).getPartyId(),
					indParty.get(0).getShortName()));
			Collections.sort(staticParties);
			return staticParties;
		} catch (Exception e) {
			e.printStackTrace();
			if (log.isDebugEnabled()) {
				log
						.debug("Exception raised in getStaticPartiesForCandidateDeatailsReport() method of Static Data Service ");
			}
			return null;
		}

	}

	public ConstituencyInfoVO getLatestAssemblyConstituenciesForParliament(
			Long parliamentConstituencyId) {
		log
				.debug("Entered in to getLatestAssemblyConstituenciesForParliament in Static Data Service");
		ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
		Constituency parliamentConstituencyObj = new Constituency();
		List list = delimitationConstituencyAssemblyDetailsDAO
				.findAssembliesConstituencies(parliamentConstituencyId);
		parliamentConstituencyObj = constituencyDAO
				.get(parliamentConstituencyId);
		List<SelectOptionVO> assemblyConstList = new ArrayList<SelectOptionVO>();
		for (Object[] values : (List<Object[]>) list)
			assemblyConstList.add(new SelectOptionVO((Long) values[0],
					values[1].toString()));
		constituencyInfoVO.setAssembyConstituencies(assemblyConstList);
		constituencyInfoVO.setConstituencyId(parliamentConstituencyObj
				.getConstituencyId());
		constituencyInfoVO.setConstituencyName(parliamentConstituencyObj
				.getName());
		log.debug("No of Assembly Constituencies:::::"
				+ assemblyConstList.size());
		return constituencyInfoVO;
	}

	public NavigationVO findHirarchiForNavigation(Long locationId,
			String locationType) {
		NavigationVO navigationVO = new NavigationVO();
		SelectOptionVO state = null;
		List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> acs = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> pcs = new ArrayList<SelectOptionVO>();
		if (locationType.equalsIgnoreCase(IConstants.TEHSIL_LEVEL)) {
			List list = delimitationConstituencyMandalDAO
					.getLatestAssemblyConstitueciesOfTehsil(locationId);
			StringBuilder acIds = new StringBuilder();
			if (list.size() != 0) {
				Object[] stateDistrict = (Object[]) list.get(0);
				state = new SelectOptionVO((Long) stateDistrict[0],
						stateDistrict[1].toString());
				districts.add(new SelectOptionVO((Long) stateDistrict[2],
						stateDistrict[3].toString()));
				for (Object[] values : (List<Object[]>) list) {
					acIds.append(IConstants.COMMA).append((Long) values[4]);
					acs.add(new SelectOptionVO((Long) values[4], values[5]
							.toString()));
				}
				List pcsInfo = delimitationConstituencyAssemblyDetailsDAO
						.findParliamentConstituencyForListOfAssemblyConstituency(
								acIds.toString().substring(1),
								IConstants.DELIMITATION_YEAR);

				for (Object[] values : (List<Object[]>) pcsInfo)
					pcs.add(new SelectOptionVO((Long) values[0], values[1]
							.toString()));
			}
		}
		if (locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)) {
			State stateObj = districtDAO.get(locationId).getState();
			state = new SelectOptionVO(stateObj.getStateId(), stateObj
					.getStateName());
		}
		if (locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)) {
			Constituency constituency = constituencyDAO.get(locationId);
			state = new SelectOptionVO(constituency.getState().getStateId(),
					constituency.getState().getStateName());
			if (IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(constituency
					.getElectionScope().getElectionType().getElectionType())) {
				districts.add(new SelectOptionVO(constituency.getDistrict()
						.getDistrictId(), constituency.getDistrict()
						.getDistrictName()));
				List pcsInfo = delimitationConstituencyAssemblyDetailsDAO
						.findLatestParliamentForAssembly(locationId);
				for (Object[] values : (List<Object[]>) pcsInfo)
					pcs.add(new SelectOptionVO((Long) values[0], values[1]
							.toString()));
			} else {
				List districtsInfo = delimitationConstituencyAssemblyDetailsDAO
						.findDistrictsOfParliamentConstituency(locationId);
				for (Object[] values : (List<Object[]>) districtsInfo)
					districts.add(new SelectOptionVO((Long) values[0],
							values[1].toString()));
			}
		}

		navigationVO.setStateInfo(state);
		navigationVO.setDistrictInfo(districts);
		navigationVO.setAcsInfo(acs);
		navigationVO.setPcsInfo(pcs);

		return navigationVO;
	}

	public List<ConstituencyElectionResultsVO> findAssemblyConstituenciesResultsByConstituencyIds(String electionYear, List<Long> constituencyIds, List resultsList) {
		List<ConstituencyElectionResultsVO> assemblyElectionResults = new ArrayList<ConstituencyElectionResultsVO>();
		ConstituencyElectionResultsVO constituencyElectionResultsVO;
		PartyResultsVO PartyResultsVO = new PartyResultsVO();
		Map<ConstituencyElectionResultsVO, List<Object[]>> constuencywisePartyResultsMap = new LinkedHashMap<ConstituencyElectionResultsVO, List<Object[]>>();
		ConstituencyElectionResultsVO constituencyElectionResults = null;
		List<Object[]> partyResults = null;
		PartyResultsVO partyResultsVO = null;
		List<PartyResultsVO> partyResultsInConstituency = null;

		try {
			if (resultsList == null)
				resultsList = nominationDAO.findElectionResultsForAllPartiesInAssemblyConstituencies(electionYear, constituencyIds,IConstants.VOTES_PERCENTAGE_CONCERNED);

			for (int i = 0; i < resultsList.size(); i++) {
				Object[] obj = (Object[]) resultsList.get(i);
				constituencyElectionResults = new ConstituencyElectionResultsVO();
				constituencyElectionResults.setConstituencyId(new Long(obj[5].toString()));
				constituencyElectionResults.setConstituencyName(obj[4].toString());
				constituencyElectionResults.setDistrictId((Long) obj[8]);
				constituencyElectionResults.setDistrictName(obj[9].toString());
				partyResults = constuencywisePartyResultsMap
						.get(constituencyElectionResults);
				if (partyResults == null) {
					partyResults = new ArrayList<Object[]>();
				}
				partyResults.add(obj);
				constuencywisePartyResultsMap.put(constituencyElectionResults,
						partyResults);
			}
			for (Map.Entry<ConstituencyElectionResultsVO, List<Object[]>> entry : constuencywisePartyResultsMap
					.entrySet()) {

				partyResultsInConstituency = new ArrayList<PartyResultsVO>();
				constituencyElectionResults = entry.getKey();
				partyResults = entry.getValue();
				Double votesEarned = new Double(0);
				Double validVotes = new Double(0);
				Long indPartyId = new Long(0);
				String indPartyName = "";
				boolean indipendentExists = false;
				for (Object[] values : partyResults) {
					String partyName = (String) values[2];
					if (partyName.equalsIgnoreCase("IND")) {
						indPartyId = (Long) values[3];
						indPartyName = (String) values[2];
						votesEarned += (Double) values[1];
						validVotes = (Double) values[6];
						log.debug("Inside independent party");
						log.debug("votesEarned after increment:" + votesEarned);
						log.debug("validVotes after increment:" + validVotes);
						indipendentExists = true;
					} else {
						partyResultsVO = new PartyResultsVO();
						partyResultsVO
								.setPartyId(new Long(values[3].toString()));
						partyResultsVO.setPartyName(values[2].toString());
						partyResultsVO.setPercentage(values[0].toString());
						partyResultsVO.setVotesPercent(new BigDecimal(values[0]
								.toString()));
						partyResultsVO.setVotesEarned(((Double) values[1])
								.longValue());
						partyResultsVO.setValidVotes(((Double) values[6])
								.longValue());
						partyResultsInConstituency.add(partyResultsVO);
					}
				}

				if (votesEarned != new Double(0) && validVotes != new Double(0)
						&& indipendentExists) {
					log.debug("Inside ind party setting method");
					log.debug("votesEarned:" + votesEarned);
					log.debug("validVotes:" + validVotes);
					Double votesPercent = 0d;
					if (validVotes > 0)
						votesPercent = new BigDecimal((votesEarned * 100)
								/ validVotes).setScale(2,
								BigDecimal.ROUND_HALF_UP).doubleValue();
					else
						System.out.println("votesEarned:"
								+ votesEarned
								+ "validVotes:"
								+ validVotes
								+ "Constituency:"
								+ constituencyElectionResults
										.getConstituencyName());
					log.debug("caliculated votesPercent:" + votesPercent);
					PartyResultsVO partyResultVO = new PartyResultsVO();
					partyResultVO.setPartyId(indPartyId);
					partyResultVO.setPartyName(indPartyName);
					partyResultVO.setVotesEarned(votesEarned.longValue());
					partyResultVO.setValidVotes(validVotes.longValue());
					partyResultVO.setPercentage(votesPercent.toString());
					log.debug("Party:" + partyResultVO.getPartyName());
					log.debug("Votes Percentage:"
							+ partyResultVO.getPercentage());
					partyResultsInConstituency.add(partyResultVO);
				}

				constituencyElectionResults
						.setPartyResultsVO(partyResultsInConstituency);
				assemblyElectionResults.add(constituencyElectionResults);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception raised ");
		}
		Collections.sort(assemblyElectionResults,
				new ConstituencyNamesComparator());
		return assemblyElectionResults;
	}

	/**
	 * This method retrives all the MandalIds based on election year for a
	 * constituency.
	 * 
	 * @param constituencyId
	 * @param electionYear
	 * @return
	 */
	public StringBuilder getMandalsForAConstituencyBasedOnElectionYear(
			Long constituencyId, Long electionYear) {
		// This below DAO call retrieves all the latest mandal's for a
		// constituency.
		try {
			List list = null;
			StringBuilder tehsilIds = new StringBuilder();
			list = delimitationConstituencyMandalDAO
					.getMandalDetailsForAConstituency(constituencyId,
							electionYear);
			for (int i = 0; i < list.size(); i++) {
				Object[] parms = (Object[]) list.get(i);
				tehsilIds.append(",").append(
						Long.parseLong(parms[0].toString()));
			}
			return tehsilIds;
		} catch (Exception e) {
			log
					.debug("Exception raised while making DelimitationConstituencyMandalDAO call");
			return null;
		}
	}

	/**
	 * This method populates all party's and their votes percentages in a mandal
	 * for all the elections(namely Assembly, Parliament) for the given election
	 * year.
	 * 
	 * @param constituencyId
	 * @param electionYear
	 * @return
	 */
	public List<ElectionResultPartyVO> getAllMandalElectionInformationForAConstituency(
			Long constituencyId, int flag) {

		// flag = 1 specifies that to get Data only for the main parties like
		// TRS,TDP,INC,BJP,PRP i.e., ByElections Parties that are defined in
		// IConstants.BYE_ELECTIONS_STATIC_PARTIES
		// flag = 0 specifies that to get Data for all the parties participated
		// in that constituency

		StringBuilder tehsilIds = new StringBuilder();
		List<ElectionResultPartyVO> electionResults = new ArrayList<ElectionResultPartyVO>(
				0);
		List list = null;
		List<TeshilPartyInfoVO> tehsilVO = new ArrayList<TeshilPartyInfoVO>(0);
		ElectionResultPartyVO electionResult = new ElectionResultPartyVO();
		try {

			tehsilIds = getMandalsForAConstituencyBasedOnElectionYear(
					constituencyId, Long
							.parseLong(IConstants.PRESENT_ELECTION_YEAR));

			tehsilVO = constituencyPageService
					.getTehsilPartyInfoForAConstituency(tehsilIds,
							IConstants.LOCAL_ELECTIONS_PREVIOUS_ELECTION_YEAR,
							IConstants.MPTC_ELECTION_TYPE, constituencyId);
			if (tehsilVO != null) {
				electionResults.add(populateDataForLocalElections(tehsilVO,
						IConstants.LOCAL_ELECTIONS_PREVIOUS_ELECTION_YEAR,
						IConstants.MPTC_ELECTION_TYPE, flag));
			}

			tehsilVO = constituencyPageService
					.getTehsilPartyInfoForAConstituency(tehsilIds,
							IConstants.LOCAL_ELECTIONS_PREVIOUS_ELECTION_YEAR,
							IConstants.ZPTC_ELECTION_TYPE, constituencyId);
			if (tehsilVO != null) {
				electionResults.add(populateDataForLocalElections(tehsilVO,
						IConstants.LOCAL_ELECTIONS_PREVIOUS_ELECTION_YEAR,
						IConstants.ZPTC_ELECTION_TYPE, flag));
			}

			electionResult = getPartyDetailsForTheGivenElectionYearInAConstituency(
					0l, tehsilIds, IConstants.PREVIOUS_ELECTION_YEAR,
					IConstants.ASSEMBLY_ELECTION_TYPE, flag);
			if (electionResult.getResultStatus().getResultCode() != 1) {
				electionResults.add(electionResult);
			}

			electionResult = getPartyDetailsForTheGivenElectionYearInAConstituency(
					0l, tehsilIds, IConstants.PREVIOUS_ELECTION_YEAR,
					IConstants.PARLIAMENT_ELECTION_TYPE, flag);
			if (electionResult.getResultStatus().getResultCode() != 1) {
				electionResults.add(electionResult);
			}

			tehsilVO = constituencyPageService
					.getTehsilPartyInfoForAConstituency(tehsilIds,
							IConstants.LOCAL_ELECTIONS_PRESENT_ELECTION_YEAR,
							IConstants.MPTC_ELECTION_TYPE, constituencyId);
			if (tehsilVO != null) {
				electionResults.add(populateDataForLocalElections(tehsilVO,
						IConstants.LOCAL_ELECTIONS_PRESENT_ELECTION_YEAR,
						IConstants.MPTC_ELECTION_TYPE, flag));
			}

			tehsilVO = constituencyPageService
					.getTehsilPartyInfoForAConstituency(tehsilIds,
							IConstants.LOCAL_ELECTIONS_PRESENT_ELECTION_YEAR,
							IConstants.ZPTC_ELECTION_TYPE, constituencyId);
			if (tehsilVO != null) {
				electionResults.add(populateDataForLocalElections(tehsilVO,
						IConstants.LOCAL_ELECTIONS_PRESENT_ELECTION_YEAR,
						IConstants.ZPTC_ELECTION_TYPE, flag));
			}

			electionResult = getPartyDetailsForTheGivenElectionYearInAConstituency(
					constituencyId, tehsilIds,
					IConstants.PRESENT_ELECTION_YEAR,
					IConstants.ASSEMBLY_ELECTION_TYPE, flag);
			if (electionResult.getResultStatus().getResultCode() != 1) {
				electionResults.add(electionResult);
			}

			if (!constituencyId.equals(new Long(18))) {
				electionResult = getPartyDetailsForTheGivenElectionYearInAConstituency(
						0l, tehsilIds, IConstants.PRESENT_ELECTION_YEAR,
						IConstants.PARLIAMENT_ELECTION_TYPE, flag);
				if (electionResult.getResultStatus().getResultCode() != 1) {
					electionResults.add(electionResult);
				}
			}

			electionResult = getPartyDetailsForTheGivenElectionYearInAConstituency(
					0l, tehsilIds,
					IConstants.BYE_ELECTION_YEAR_2008_PARLIAMENT,
					IConstants.PARLIAMENT_ELECTION_TYPE, flag);
			if (electionResult.getResultStatus().getResultCode() != 1) {
				electionResults.add(electionResult);
			}
			electionResult = getPartyDetailsForTheGivenElectionYearInAConstituency(
					0l, tehsilIds,
					IConstants.BYE_ELECTION_YEAR_2006_PARLIAMENT,
					IConstants.PARLIAMENT_ELECTION_TYPE, flag);
			if (electionResult.getResultStatus().getResultCode() != 1) {
				electionResults.add(electionResult);
			}

			electionResult = getPartyDetailsForTheGivenElectionYearInAConstituency(
					constituencyId, tehsilIds,
					IConstants.LATEST_BYE_ELECTION_YEAR,
					IConstants.ASSEMBLY_ELECTION_TYPE, flag);
			if (electionResult != null
					&& electionResult.getResultStatus().getResultCode() != 1) {
				electionResults.add(electionResult);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception raised ");
		}
		return electionResults;
	}

	/**
	 * This method retrives Votes Percentage Obtained for different Bye-Election
	 * parties in the constituency based on latest mandals for Zptc's and Mptc's
	 * Elections.
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 08-07-10
	 * @param tehsilIds
	 * @param electionType
	 * @param electionYear
	 * @param flag
	 * @return
	 */
	public ElectionResultPartyVO getMptcOrZptcElectionVotesPercentageForDifferentParties(
			StringBuilder tehsilIds, String electionType, String electionYear,
			int flag, Long constituencyId) {
		// flag = 1 specifies that to get Data only for the main parties like
		// TRS,TDP,INC,BJP,PRP i.e., ByElections Parties that are defined in
		// IConstants.BYE_ELECTIONS_STATIC_PARTIES
		// flag = 0 specifies that to get Data for all the parties participated
		// in that constituency
		List<TeshilPartyInfoVO> tehsilVO = new ArrayList<TeshilPartyInfoVO>(0);
		ElectionResultPartyVO electionResult = new ElectionResultPartyVO();
		tehsilVO = constituencyPageService.getTehsilPartyInfoForAConstituency(
				tehsilIds, electionYear, electionType, constituencyId);
		if (tehsilVO != null) {
			electionResult = populateDataForLocalElections(tehsilVO,
					electionYear, electionType, flag);
		}
		return electionResult;
	}

	public ElectionResultPartyVO populateDataForLocalElections(
			List<TeshilPartyInfoVO> result, String electionYear,
			String electionType, int flag) {
		// System.out.println("Inside populateDataForLocalElections....");
		ElectionResultPartyVO candidateElectionResult = new ElectionResultPartyVO();
		List<CandidateElectionResultVO> candidateElectionResultsVO = null;
		try {
			candidateElectionResult.setElectionType(electionType);
			candidateElectionResult.setElectionYear(electionYear);
			candidateElectionResultsVO = new ArrayList<CandidateElectionResultVO>(
					0);
			if (result != null && result.size() != 0) {
				for (TeshilPartyInfoVO resultIterator : result) {
					CandidateElectionResultVO partyInfo = new CandidateElectionResultVO();
					partyInfo.setPartyName(resultIterator.getPartyName());
					partyInfo.setVotesPercentage(resultIterator
							.getPercentageOfVotesWonByParty().toString());

					List resu = null;
					if (electionType
							.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
						resu = electionDAO
								.findElectionIdByParliamentElectionTypeAndYear(
										electionType, electionYear);
					else
						resu = electionDAO.findElectionIdByElectionTypeAndYear(
								electionType, electionYear, 1l);
					List alliance = allianceGroupDAO
							.findAlliancePartiesByElectionAndParty(Long
									.parseLong(resu.get(0).toString()),
									resultIterator.getPartyId());
					partyInfo.setHasAlliance(alliance.size() > 0 ? "true"
							: "false");
					// System.out.println(electionType+"----"+electionYear+"----"+partyInfo.getHasAlliance()+"------"+partyInfo.getPartyName()+"------"+partyInfo.getVotesPercentage());
					if (!resultIterator.getPartyName().equalsIgnoreCase("PRP")) {
						if (flag == 1) {
							if ((IConstants.BYE_ELECTIONS_STATIC_PARTIES
									.contains(resultIterator.getPartyName()))) {
								candidateElectionResultsVO.add(partyInfo);
							}
						} else {
							candidateElectionResultsVO.add(partyInfo);
						}
					}
				}
				Double votesRange = 0d;
				Set smallestValue = new TreeSet();
				String lowerRange = "0";
				for (int i = 0; i < candidateElectionResultsVO.size(); i++) {
					Double nextValue = Double
							.parseDouble(candidateElectionResultsVO.get(i)
									.getVotesPercentage().toString());
					smallestValue.add(nextValue);
					votesRange += nextValue;
				}
				List smallValue = new ArrayList(smallestValue);

				if (smallValue.get(0) != null) {
					lowerRange = smallValue.get(0).toString();
				}
				candidateElectionResult.setVotesShareRange(lowerRange + "-"
						+ smallValue.get(smallValue.size() - 1));
			}
			candidateElectionResult
					.setCandidateElectionResultsVO(candidateElectionResultsVO);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception raised ");
		}
		return candidateElectionResult;
	}

	public ElectionResultPartyVO getPartyDetailsForTheGivenElectionYearInAConstituency(
			Long constituencyId, StringBuilder tehsilIds, String electionYear,
			String electionType, int flag) {
		BigDecimal percentage = new BigDecimal(0.0);
		Long totalVotes = 0l;
		ElectionResultPartyVO candidateElectionResult = new ElectionResultPartyVO();
		List<CandidateElectionResultVO> candidateElectionResultsVO = null;
		ResultStatus resultStatus = new ResultStatus();
		List list2 = null;
		try {
			candidateElectionResult.setElectionType(electionType);
			candidateElectionResult.setElectionYear(electionYear);
			// The below DAO call retrieves all the party's and their votes in
			// the mandal's for the given election year.
			if (constituencyId != null && constituencyId > 0l) {
				list2 = nominationDAO.getResultsForElectionInConstituency(
						constituencyId, electionYear);
				if (list2 == null || list2.size() == 0)
					return null;
			} else {
				list2 = candidateBoothResultDAO
						.getResultsForElectionAndConstituencyByMandal(tehsilIds
								.substring(1), electionYear, electionType);
			}
			ListIterator it = list2.listIterator();
			while (it.hasNext()) {

				Object[] parms = (Object[]) it.next();
				if (constituencyId != null && constituencyId > 0l) {
					Double tot = Double.parseDouble(parms[1].toString());
					totalVotes += tot.longValue();
				} else {
					totalVotes += Long.parseLong(parms[1].toString());
				}
			}
			candidateElectionResultsVO = new ArrayList<CandidateElectionResultVO>(
					0);
			ListIterator resultIterator = list2.listIterator();
			while (resultIterator.hasNext()) {
				Object[] parms = (Object[]) resultIterator.next();

				Long partyId = (Long) parms[2];
				Long votesEarned = new Long(0);
				Long validVotes = new Long(0);

				if (constituencyId != null && constituencyId > 0l) {
					try {
						Double votesEarn = Double.parseDouble(parms[1]
								.toString());
						Double validVote = Double.parseDouble(parms[3]
								.toString());
						votesEarned = votesEarn.longValue();
						validVotes = validVote.longValue();

						/*
						 * if(votesEarned.equals(new Long(0)) ||
						 * validVotes.equals(new Long(0))) return null;
						 */
						Double percent = new Double(parms[4].toString());
						/*
						 * if(percent.equals(new Double(0))) return null;
						 */
						percentage = new BigDecimal(percent).setScale(2,
								BigDecimal.ROUND_HALF_UP);

					} catch (Exception ex) {
						ex.printStackTrace();
						log.debug(" Exception raised :" + ex);

					}
				} else {
					votesEarned = (Long) parms[1];
					validVotes = (Long) parms[3];
					percentage = new BigDecimal(
							(votesEarned.doubleValue() / totalVotes
									.doubleValue()) * 100).setScale(2,
							BigDecimal.ROUND_HALF_UP);
				}

				CandidateElectionResultVO partyInfo = new CandidateElectionResultVO();
				partyInfo.setTotalVotesEarned(votesEarned);
				partyInfo.setTotalValidVotes(totalVotes);
				partyInfo.setPartyId(partyId);
				partyInfo.setPartyName(parms[0].toString());
				partyInfo.setVotesPercentage(percentage.toString());

				List result = null;
				if (electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
					result = electionDAO
							.findElectionIdByParliamentElectionTypeAndYear(
									electionType, electionYear);
				else
					result = electionDAO.findElectionIdByElectionTypeAndYear(
							electionType, electionYear, 1l);
				List alliance = allianceGroupDAO
						.findAlliancePartiesByElectionAndParty(Long
								.parseLong(result.get(0).toString()), Long
								.parseLong(parms[2].toString()));
				partyInfo
						.setHasAlliance(alliance.size() > 0 ? "true" : "false");
				// System.out.println(electionType+"----"+electionYear+"----"+partyInfo.getHasAlliance()+"------"+partyInfo.getPartyName()+"------"+partyInfo.getVotesPercentage());
				if (flag == 1) {
					if ((IConstants.BYE_ELECTIONS_STATIC_PARTIES
							.contains(parms[0].toString()))) {
						candidateElectionResultsVO.add(partyInfo);
					}
				} else {
					candidateElectionResultsVO.add(partyInfo);
				}
			}
			Double votesRange = 0d;
			Set smallestValue = new TreeSet();
			String lowerRange = "0";
			for (int i = 0; i < candidateElectionResultsVO.size(); i++) {
				Double nextValue = Double
						.parseDouble(candidateElectionResultsVO.get(i)
								.getVotesPercentage().toString());
				smallestValue.add(nextValue);
				votesRange += nextValue;
			}
			// System.out.println(smallestValue);
			List smallValue = new ArrayList(smallestValue);

			if (smallValue != null && smallValue.size() > 0) {
				lowerRange = smallValue.get(0).toString();
			}

			candidateElectionResult.setVotesShareRange(lowerRange + "-"
					+ smallValue.get(smallValue.size() - 1));
			candidateElectionResult
					.setCandidateElectionResultsVO(candidateElectionResultsVO);
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			candidateElectionResult.setResultStatus(resultStatus);
		} catch (Exception e) {
			log.debug("Exception raised ");
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			candidateElectionResult = new ElectionResultPartyVO();
			candidateElectionResult.setResultStatus(resultStatus);
		}
		return candidateElectionResult;
	}

	/**
	 * This method retrieves the details of winning candidates in the
	 * constituency list passed
	 */
	public List<CandidateElectionResultVO> getWinningCandidatesInConstituencies(
			String electionYear, List<Long> constituencyIds) {
		List<CandidateElectionResultVO> winningCandidatesInBiElectionConst = new ArrayList<CandidateElectionResultVO>();
		CandidateElectionResultVO candidateElectionResultVO;
		Long rank;
		Double votesPercentage;
		Long constituencyId;
		Double votesMarginPercentage;
		Map<Long, CandidateElectionResultVO> constuencywiseResultsMap = new LinkedHashMap<Long, CandidateElectionResultVO>();
		try {
			candidateElectionResultVO = new CandidateElectionResultVO();
			// this dao call retrieves the details of all winning candidates in
			// the passed constituencies
			List resultsList = nominationDAO
					.findWinningCandidatesDetailsInContituencies(electionYear,
							constituencyIds);
			// this dao call retrieves the votes margin percentage of all
			// opposition candidates in the passed constituencies, this is used
			// to calculate votes margin %
			List oppositionCandidatesList = nominationDAO
					.findOppositionCandidateVotesPercentageInConstituencies(
							electionYear, constituencyIds);

			for (int i = 0; i < resultsList.size(); i++) {
				Object[] obj = (Object[]) resultsList.get(i);

				candidateElectionResultVO = new CandidateElectionResultVO();
				candidateElectionResultVO.setDistrictId(new Long(obj[0]
						.toString()));
				candidateElectionResultVO.setDistrictName(obj[1].toString());
				candidateElectionResultVO.setConstituencyId(new Long(obj[2]
						.toString()));
				candidateElectionResultVO
						.setConstituencyName(obj[3].toString());
				candidateElectionResultVO.setCandidateName(obj[4].toString());
				candidateElectionResultVO.setPartyName(obj[5].toString());
				candidateElectionResultVO
						.setPartyId(new Long(obj[6].toString()));
				candidateElectionResultVO.setVotesPercentage(obj[7].toString());
				candidateElectionResultVO.setMarginVotes(obj[8].toString());
				constuencywiseResultsMap.put(new Long(obj[2].toString()),
						candidateElectionResultVO);

			}

			for (int j = 0; j < oppositionCandidatesList.size(); j++) {
				Object[] oppostionObj = (Object[]) oppositionCandidatesList
						.get(j);
				constituencyId = new Long(oppostionObj[1].toString());

				if (constuencywiseResultsMap.containsKey(constituencyId)) {
					candidateElectionResultVO = constuencywiseResultsMap
							.get(constituencyId);
					if (candidateElectionResultVO.getConstituencyId().equals(
							constituencyId)) {
						candidateElectionResultVO
								.setMarginVotes(new BigDecimal((Double
										.parseDouble(candidateElectionResultVO
												.getMarginVotes()))
										- Double.parseDouble(oppostionObj[2]
												.toString())).setScale(0,
										BigDecimal.ROUND_HALF_UP).toString());
						votesPercentage = new Double(candidateElectionResultVO
								.getVotesPercentage());
						votesMarginPercentage = votesPercentage
								- new Double((String) oppostionObj[0]);
						log.debug("votes Margin %::::::::::::"
								+ votesMarginPercentage);
						candidateElectionResultVO
								.setVotesMargin(new BigDecimal(
										votesMarginPercentage).setScale(2,
										BigDecimal.ROUND_HALF_UP).toString());
						log.debug("votes Margin % from VO::::::::::::"
								+ candidateElectionResultVO.getVotesMargin());
					}
					winningCandidatesInBiElectionConst
							.add(candidateElectionResultVO);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception raised ");
		}
		Collections.sort(winningCandidatesInBiElectionConst,
				new DistrictNamesComparator());
		return winningCandidatesInBiElectionConst;
	}

	/**
	 * This method returns Township-Wise voting trends for a mandal for
	 * different electionIds.
	 * 
	 * @author ravykirany@gmail.com Date:- 29-06-10
	 * @param tehsilId
	 * @param electionIds
	 */
	public List<TownshipBoothDetailsVO> getRevenueVillageVotingTrendsByMandalAndElectionIds(Long tehsilId, String electionIds) 
	{
		Long totalValidVotesInMandal = 0l;
		List mandalResult = new ArrayList<Long>(0);
		List list = new ArrayList(0);
		ResultStatus resultStatus = new ResultStatus();
		Map<Long, Long> electionIdsAndTotalVotes = new HashMap<Long, Long>(0);
		List<TownshipBoothDetailsVO> mandal = new ArrayList<TownshipBoothDetailsVO>(0);
		String mandalName = "";
		String elections = null;
		try {

			// Making DAO call to get Total Valid Votes in mandal.

			elections = getLatestAssemblyElectionId();
			mandalResult = boothConstituencyElectionDAO.getTotalVotesInAMandal(tehsilId,elections);
			for (int i = 0; i < mandalResult.size(); i++) {
				Object[] parms = (Object[]) mandalResult.get(i);
				electionIdsAndTotalVotes.put(Long
						.parseLong(parms[1].toString()), Long
						.parseLong(parms[0].toString()));
				mandalName = parms[2].toString();
			}

			// Making DAO call to get All Total Valid Votes for every township
			// in mandal.
			list = villageBoothElectionDAO.findTownshipWiseVotingTrendsForATehsil(tehsilId, elections);

			StringTokenizer st = new StringTokenizer(elections, ",");

			while (st.hasMoreElements()) {
				Long electionID = Long.parseLong(st.nextElement().toString());
				TownshipBoothDetailsVO votesByElectionId = new TownshipBoothDetailsVO();
				List<TownshipBoothDetailsVO> township = new ArrayList<TownshipBoothDetailsVO>(
						0);
				// Creating a list Iterator to set data in to DTO(Data Transfer
				// Object).
				ListIterator result = list.listIterator();
				Long count = 1l;
				// Iterating the list Iterator for setting the data.
				while (result.hasNext()) {
					Long electionId = electionID;
					Object[] parms = (Object[]) result.next();
					if (electionId == Long.parseLong(parms[3].toString())) {
						TownshipBoothDetailsVO votes = new TownshipBoothDetailsVO();
						votes.setSNO(count++);
						Long eachTownshipVotes = Long.parseLong(parms[2]
								.toString());
						votes
								.setTownshipID(Long.parseLong(parms[0]
										.toString()));
						votes.setTownshipName(parms[1].toString());
						votes
								.setElectionId(Long.parseLong(parms[3]
										.toString()));
						votes.setValidVoters(eachTownshipVotes);
						Double percentage = ((eachTownshipVotes * 100.0) / electionIdsAndTotalVotes
								.get(Long.parseLong(parms[3].toString())));
						votes.setPercentageOfValidVotes(new BigDecimal(
								percentage).setScale(2,
								BigDecimal.ROUND_HALF_UP).toString());
						township.add(votes);
					}
				}
				List election = electionDAO
						.getElectionTypeAndElectionYearByElectionId(electionID);
				String elect = "";
				for (int i = 0; i < election.size(); i++) {
					Object[] parms = (Object[]) election.get(i);
					elect = parms[0].toString() + "-" + parms[1].toString();
				}
				votesByElectionId.setChartTitle("VotesPolling In " + mandalName
						+ " Mandal for " + elect);
				votesByElectionId
						.setChartName("VotesPollingIn_" + mandalName
								+ "forElectionType" + electionID + "_piechart"
								+ ".png");
				votesByElectionId.setTownshipVotingTrends(township);
				votesByElectionId.setMandalId(tehsilId);
				votesByElectionId.setMandalName(mandalName);
				mandal.add(votesByElectionId);
			}
			return mandal;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return mandal;
		}
	}
	
	public List<TownshipBoothDetailsVO> getPanchayatVotingTrendsByMandalAndElectionIds(Long tehsilId, String electionIds) 
	{
		Long totalValidVotesInMandal = 0l;
		List<Object[]> mandalResult = new ArrayList<Object[]>(0);
		List<Object[]> list = new ArrayList<Object[]>(0);
		ResultStatus resultStatus = new ResultStatus();
		Map<Long, Long> electionIdsAndTotalVotes = new HashMap<Long, Long>(0);
		List<TownshipBoothDetailsVO> mandal = new ArrayList<TownshipBoothDetailsVO>(0);
		String mandalName = "";
		String elections = null;
		try {

			elections = getLatestAssemblyElectionId();
			mandalResult = boothConstituencyElectionDAO.getTotalVotesInAMandal(tehsilId,elections);
			
			for(Object[] params : mandalResult)
			{
				electionIdsAndTotalVotes.put((Long)params[1], (Long)params[0]);
				mandalName = params[2].toString();
			}
			
			list = hamletBoothElectionDAO.findPanchayatWiseVotingTrendsForATehsil(tehsilId, elections);

			StringTokenizer st = new StringTokenizer(elections, ",");

			while (st.hasMoreElements())
			{
				Long electionID = Long.parseLong(st.nextElement().toString());
				TownshipBoothDetailsVO townshipBoothDetailsVO = new TownshipBoothDetailsVO();
				List<TownshipBoothDetailsVO> township = new ArrayList<TownshipBoothDetailsVO>(0);
				Long count = 1l;
				ListIterator result = list.listIterator();
				
				while (result.hasNext())
				{
					Long electionId = electionID;
					Object[] parms = (Object[]) result.next();
					
					if (electionId == Long.parseLong(parms[3].toString()))
					{
						TownshipBoothDetailsVO votes = new TownshipBoothDetailsVO();
						votes.setSNO(count++);
						Long eachTownshipVotes = Long.parseLong(parms[2].toString());
						votes.setTownshipID(Long.parseLong(parms[0].toString()));
						votes.setTownshipName(parms[1].toString());
						votes.setElectionId(Long.parseLong(parms[3].toString()));
						votes.setValidVoters(eachTownshipVotes);
						
						Double percentage = ((eachTownshipVotes * 100.0) / electionIdsAndTotalVotes.get(Long.parseLong(parms[3].toString())));
						votes.setPercentageOfValidVotes(new BigDecimal(percentage).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
						township.add(votes);
					}
				}
				List election = electionDAO.getElectionTypeAndElectionYearByElectionId(electionID);
				String elect = "";
				
				for (int i = 0; i < election.size(); i++) 
				{
					Object[] parms = (Object[]) election.get(i);
					elect = parms[0].toString() + "-" + parms[1].toString();
				}
				townshipBoothDetailsVO.setChartTitle("Votes Polling In " + mandalName+ " Mandal for " + elect);
				townshipBoothDetailsVO.setChartName("Votes_PollingIn_" + mandalName+ "for ElectionType" + electionID + "_piechart"+ ".png");
				townshipBoothDetailsVO.setTownshipVotingTrends(township);
				townshipBoothDetailsVO.setMandalId(tehsilId);
				townshipBoothDetailsVO.setMandalName(mandalName);
				mandal.add(townshipBoothDetailsVO);
			}
			return mandal;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			return mandal;
		}
	}

	public MandalVO findListOfElectionsAndPartiesInMandal(Long tehsilId) {
		MandalVO mandalVO = new MandalVO();
		List<SelectOptionVO> parties = Collections
				.synchronizedList(getStaticParties());
		List<SelectOptionVO> partiesInMandal = new ArrayList<SelectOptionVO>();
		for (SelectOptionVO party : parties)
			if (!"'AIMIM'".contains(party.getName()))
				partiesInMandal.add(party);
		Set<SelectOptionVO> elections = new HashSet<SelectOptionVO>();
		List electionsInMandal = villageBoothElectionDAO
				.findPolledVotesInAllElectionsOfMandalByRevenueVillages(tehsilId);
		for (Object[] values : (List<Object[]>) electionsInMandal)
			elections.add(new SelectOptionVO((Long) values[0],
					(values[1] + " " + values[2])));
		// partiesInMandal.add(new SelectOptionVO(0l, "Others"));
		mandalVO.setPartiesInMandal(partiesInMandal);
		mandalVO.setElectionsInMandal(elections);
		return mandalVO;
	}

	/**
	 * This method gets latest assembly elecion-id.
	 * 
	 * @author Ravi Kiran.Y Date:02-07-10
	 * @return
	 */
	public String getLatestAssemblyElectionId() {
		Long stateId = 1l;
		List result = electionDAO.findElectionIdByElectionTypeAndYear(
				IConstants.ASSEMBLY_ELECTION_TYPE,
				IConstants.PRESENT_ELECTION_YEAR, stateId);
		return result.get(0).toString();
	}

	public List<AlliancePartiesInElection> getAlliancGroupAndPartiesInAnElection(
			String electionType, String electionYear) {

		List<AlliancePartiesInElection> partiesInAllianc = null;
		if (electionType != null && electionYear != null) {
			partiesInAllianc = new ArrayList<AlliancePartiesInElection>();

			List groupsList = electionAllianceDAO.findAllGroupsForAnElection(
					electionType, electionYear);
			if (groupsList != null && groupsList.size() > 0) {
				for (int i = 0; i < groupsList.size(); i++) {
					Object[] params = (Object[]) groupsList.get(i);

					AlliancePartiesInElection allianParty = new AlliancePartiesInElection();
					allianParty.setGroupId((Long) params[1]);
					allianParty.setGroupName((String) params[0]);
					List<SelectOptionVO> partiesList = new ArrayList<SelectOptionVO>();
					List alliPartys = allianceGroupDAO
							.findPartiesByGroup((Long) params[1]);
					if (alliPartys != null && alliPartys.size() > 0) {

						for (int j = 0; j < alliPartys.size(); j++) {
							Object[] partyParams = (Object[]) alliPartys.get(j);
							SelectOptionVO party = new SelectOptionVO();

							party.setId((Long) partyParams[0]);
							party.setName((String) partyParams[1]);
							partiesList.add(party);
						}
					}

					if (alliPartys != null && alliPartys.size() > 0) {
						allianParty.setParties(partiesList);
						partiesInAllianc.add(allianParty);
					}
				}

			}
		}

		return partiesInAllianc;
	}

	/**
	 * This method sets all the Bye-Election Constituencies with the
	 * present-year(2010) total voters as well as latest assembly election year
	 * voter details.
	 * 
	 * @author Ravi Kiran.Y Date:03-07-10
	 * @return
	 */
	public ElectionTrendzReportVO getConstituencyOverview(Long constituencyId,String constituencyName) {

		ElectionTrendzReportVO constituencyOverView = new ElectionTrendzReportVO();
		ResultStatus resultStatus = new ResultStatus();
		try {
			Long latestElectionYearTotalPolledVotes = 0l;
			Long latestElectionYearVoters = 0l;
			Map<String, Long> presentYearVoters = new HashMap<String, Long>(0);
			presentYearVoters = populateAllByeElectionConstituencyVoterDetails();
			List list = constituencyElectionDAO.getVotesDataForAConstituency(constituencyId, Long.parseLong(getLatestAssemblyElectionId()));
			for (int i = 0; i < list.size(); i++) {
				Object[] params = (Object[]) list.get(i);
				Double totalPolledVotes = Double.parseDouble(params[1].toString());
				latestElectionYearTotalPolledVotes += totalPolledVotes.longValue();
				Double totalVotes = Double.parseDouble(params[0].toString());
				latestElectionYearVoters = totalVotes.longValue();
			}
			constituencyOverView.setLatestElectionYearsTotalVoters(latestElectionYearVoters);
			constituencyOverView.setLatestElectionYearsTotalPolledVotes(latestElectionYearTotalPolledVotes);
			Double percentage = (latestElectionYearTotalPolledVotes * 100)/(latestElectionYearVoters + 0.0d);
			String votesPercenatage = new BigDecimal(percentage).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			constituencyOverView.setLatestElectionYearsTotalVotesPercentage(votesPercenatage);

			if (presentYearVoters.get(constituencyName.toUpperCase()) != null)
			constituencyOverView.setPresentYearTotalVoters(presentYearVoters.get(constituencyName.toUpperCase()));
			else
				constituencyOverView.setPresentYearTotalVoters(0l);

			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			constituencyOverView.setResultStatus(resultStatus);

		} catch (Exception e) {
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			constituencyOverView = new ElectionTrendzReportVO();
			constituencyOverView.setResultStatus(resultStatus);
		}

		return constituencyOverView;
	}

	/**
	 * This method sets all the Bye-Election Constituencies with the
	 * present-year(2010) total voters.
	 * 
	 * @author Ravi Kiran.Y Date:03-07-10
	 * @return
	 */
	public Map<String, Long> populateAllByeElectionConstituencyVoterDetails() {

		Map<String, Long> allVoters = new HashMap<String, Long>(0);

		allVoters.put("HUZURABAD", 202322l);
		allVoters.put("SIRCILLA", 211442l);
		allVoters.put("KORATLA", 193535l);
		allVoters.put("DHARMAPURI", 191941l);
		allVoters.put("VEMULAWADA", 184633l);
		allVoters.put("CHENNUR", 149227l);
		allVoters.put("SIRPUR", 155180l);
		allVoters.put("MANCHERIAL", 202339l);
		allVoters.put("YELLAREDDY", 190343l);
		allVoters.put("NIZAMABAD URBAN", 267292l);
		allVoters.put("SIDDIPET", 186303l);
		allVoters.put("WARANGAL WEST", 221896l);

		return allVoters;
	}

	public List<PartyResultVO> getPartyVotesShareInConstituency(
			Long constituencyId, int flag,
			List<ElectionResultPartyVO> partyResult) {

		List<PartyResultVO> party = new ArrayList<PartyResultVO>(0);

		String[] partiesList = { "TRS", "INC", "TDP", "BJP", "PRP", "CPI","CPM" };

		String elecYearAndType = "";
		PartyResultVO partyResultVO = null;

		for (int j = 0; j < partiesList.length; j++) {
			PartyResultVO partyVo = new PartyResultVO();
			partyVo.setPartyName(partiesList[j]);
			List<ElectionResultVO> electionWiseResults = new ArrayList<ElectionResultVO>(0);
			for (int i = 0; i < partyResult.size(); i++) {
				ElectionResultVO electionResultVO = new ElectionResultVO();
				electionResultVO.setElectionType(partyResult.get(i).getElectionType());
				electionResultVO.setElectionYear(partyResult.get(i).getElectionYear());

				if (IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(partyResult.get(i).getElectionType())
						|| IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(partyResult.get(i).getElectionType())&& !"2008".equalsIgnoreCase(partyResult.get(i).getElectionYear())
						&& !"2006".equalsIgnoreCase(partyResult.get(i).getElectionYear())) {
					List<CandidateElectionResultVO> candidateResults = getProcessedAlliancePartiesResults(
							partyResult.get(i).getCandidateElectionResultsVO(),
							partyResult.get(i).getElectionType(), partyResult.get(i).getElectionYear());
					if (candidateResults != null && candidateResults.size() > 0) {
						partyResult.get(i).setCandidateElectionResultsVO(candidateResults);
					}
				}

				if ("MPTC".equalsIgnoreCase(partyResult.get(i).getElectionType())
						|| "ZPTC".equalsIgnoreCase(partyResult.get(i).getElectionType()))
					elecYearAndType = partyResult.get(i).getElectionYear()+ " ." + partyResult.get(i).getElectionType();
				else
					elecYearAndType = partyResult.get(i).getElectionYear()+ " " + partyResult.get(i).getElectionType();
				electionResultVO.setElectionYearAndType(elecYearAndType);

				if (partyResult.get(i) != null
						&& partyResult.get(i).getCandidateElectionResultsVO() != null) {
					for (int k = 0; k < partyResult.get(i).getCandidateElectionResultsVO().size(); k++) {
						electionResultVO.setHasAlliance(partyResult.get(i).getCandidateElectionResultsVO().get(k).getHasAlliance());
						electionResultVO.setAlliancRes(partyResult.get(i).getCandidateElectionResultsVO().get(k).getAllianceResult());
						if (partyResult.get(i).getCandidateElectionResultsVO().get(k).getPartyName().equalsIgnoreCase(partiesList[j])) {
							electionResultVO.setPercentage(partyResult.get(i).getCandidateElectionResultsVO().get(k).getVotesPercentage().toString());
							electionResultVO.setPercent(new BigDecimal(partyResult.get(i).getCandidateElectionResultsVO().get(k).getVotesPercentage()).setScale(2, BigDecimal.ROUND_HALF_UP));
							break;
						} else {
							electionResultVO.setPercentage("-1");
							electionResultVO.setPercent(new BigDecimal(-1));
						}
					}
				}
				electionWiseResults.add(electionResultVO);
			}

			partyVo.setElectionWiseResults(electionWiseResults);

			List<SelectOptionVO> electionList = new ArrayList<SelectOptionVO>(0);
			for (int i = 0; i < partyResult.size(); i++) {
				SelectOptionVO select = new SelectOptionVO();
				String electionHeading = "";
				String electionType = "";
				electionType = partyResult.get(i).getElectionType();
				/*
				 * if(partyResult.get(i).getElectionType().equalsIgnoreCase(IConstants
				 * .ASSEMBLY_ELECTION_TYPE)){ electionType ="AC"; }else
				 * if(partyResult
				 * .get(i).getElectionType().equalsIgnoreCase(IConstants
				 * .PARLIAMENT_ELECTION_TYPE)){ electionType ="PC"; }else{
				 * electionType = partyResult.get(i).getElectionType(); }
				 */
				if (electionType != null) {
					if (electionType.equals(IConstants.MPTC_ELECTION_TYPE)
							|| electionType
									.equals(IConstants.ZPTC_ELECTION_TYPE))
						electionHeading = partyResult.get(i).getElectionYear()
								+ " ." + electionType;
					else
						electionHeading = partyResult.get(i).getElectionYear()
								+ " " + electionType;
					select.setId(i + 0l);
					select.setName(electionHeading);
					electionList.add(select);
				}
			}
			partyVo.setElectionList(electionList);

			List<SelectOptionVO> partiesListVo = new ArrayList<SelectOptionVO>(
					0);
			for (int i = 0; i < partiesList.length; i++) {
				SelectOptionVO select = new SelectOptionVO();
				select.setId(i + 0l);
				select.setName(partiesList[i]);
				partiesListVo.add(select);
			}
			partyVo.setPartiesList(partiesListVo);

			party.add(partyVo);
		}

		for (int i = 0; i < party.size(); i++) {
			Set<Double> range = new TreeSet<Double>();
			for (int k = 0; k < party.get(i).getElectionWiseResults().size(); k++) {
				String percentage = party.get(i).getElectionWiseResults()
						.get(k).getPercentage();
				if (percentage != null && !percentage.equalsIgnoreCase("-1")) {
					range.add(Double.parseDouble(percentage));
				}
			}

			List<Double> rangeSelect = new ArrayList<Double>(range);
			if (rangeSelect == null || rangeSelect.size() == 0) {
				party.get(i).setRange(" ");
			} else {
				party.get(i).setRange(
						rangeSelect.get(0).toString()
								+ "  -  "
								+ rangeSelect.get(rangeSelect.size() - 1)
										.toString());
			}

		}

		return party;
	}

	/**
	 * This method retrives all the muncipal/corporation elections that are
	 * happened in the constituency.
	 * 
	 * @author Ravi Kiran.Y
	 * @param constituencyId
	 * @param electionType
	 * @return
	 */
	public TeshilPartyInfoVO getLocalElectionDetailsForAConstituency(
			Long constituencyId, String electionType) {
		ResultStatus resultStatus = new ResultStatus();
		TeshilPartyInfoVO teshilPartyInfo = new TeshilPartyInfoVO();
		try {
			List<TeshilPartyInfoVO> tehsilVo = new ArrayList<TeshilPartyInfoVO>();
			String latestMuncipalElectionYear = "";

			// The below line calls getAllLatestMandalsForAConstituency() method
			// to get all the tehsils for a constituency.
			StringBuilder tehsilIds = getAllLatestMandalsForAConstituency(constituencyId);

			// The below line makes a dao call to get the latest election year
			// for the corresponding election type(muncipality/corporation)
			List latestYearResult = electionDAO.findLatestElectionYear(electionType);
			latestMuncipalElectionYear = latestYearResult.get(0).toString();

			// The below line makes a dao call to get the parties votes for the
			// corresponding election type(muncipality/corporation)
			List result = nominationDAO.getLocalElectionsCandidateDetailsInAConstituency(electionType, tehsilIds.substring(1));
			if (result.size() <= 0 || result == null) {
				return null;
			} else {
				tehsilVo = getLocalElectionPartyDetails(result,	latestMuncipalElectionYear, electionType);
				teshilPartyInfo.setMuncipalityVO(tehsilVo);
				return teshilPartyInfo;
			}
		} catch (NullPointerException e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			teshilPartyInfo = new TeshilPartyInfoVO();
			teshilPartyInfo.setResultStatus(resultStatus);
			return teshilPartyInfo;
		} catch (Exception e) {
			log.error("Exception raised please check the log for details" + e);
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			teshilPartyInfo = new TeshilPartyInfoVO();
			teshilPartyInfo.setResultStatus(resultStatus);
			return teshilPartyInfo;
		}
	}

	/**
	 * This method retrives all the Latest Mandals For A Constituency.
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 07-07-10
	 * @param constituencyId
	 * @return
	 */
	public StringBuilder getAllLatestMandalsForAConstituency(Long constituencyId) {
		StringBuilder tehsilIds = new StringBuilder();
		try {
			List mandals = delimitationConstituencyMandalDAO.getLatestMandalDetailsForAConstituency(constituencyId);
			if (mandals.size() <= 0 || mandals == null) {
				return null;
			} else {
				for (int i = 0; i < mandals.size(); i++) {
					Object[] parms = (Object[]) mandals.get(i);
					tehsilIds.append(",").append(Long.parseLong(parms[0].toString()));
				}
			}
		} catch (Exception e) {
			log.debug("Exception raised While retriving Mandals Information");
		}
		return tehsilIds;
	}

	public List<PartyResultVO> getPartyVotesPercentageInAConstituency(
			Long constituencyId, String all, String[] choices) {
		int flag = 1;
		StringBuilder tehsilIds = new StringBuilder();
		List<PartyResultVO> party = new ArrayList<PartyResultVO>(0);
		List<ElectionResultPartyVO> partyResult = new ArrayList<ElectionResultPartyVO>(0);

		// flag = 1 specifies that to get Data only for the main parties like
		// TRS,TDP,INC,BJP,PRP i.e., ByElections Parties that are defined in
		// IConstants.BYE_ELECTIONS_STATIC_PARTIES
		// flag = 0 specifies that to get Data for all the parties participated
		// in that constituency

		tehsilIds = getMandalsForAConstituencyBasedOnElectionYear(constituencyId, Long.parseLong(IConstants.PRESENT_ELECTION_YEAR));

		if (all.equalsIgnoreCase("all")) {
			partyResult = getAllMandalElectionInformationForAConstituency(constituencyId, flag);
			party = getPartyVotesShareInConstituency(constituencyId, flag,partyResult);
		} else {
			for (int i = 0; i < choices.length; i++) {
				if (choices[i].equalsIgnoreCase("AC")) {
					ElectionResultPartyVO electionResult = new ElectionResultPartyVO();
					partyResult.add(getAssemblyOrParliamentElectionVotesPercentageForDifferentParties(0l, tehsilIds,
									IConstants.ASSEMBLY_ELECTION_TYPE,
									IConstants.PREVIOUS_ELECTION_YEAR, flag));
					partyResult
							.add(getAssemblyOrParliamentElectionVotesPercentageForDifferentParties(
									constituencyId, tehsilIds,
									IConstants.ASSEMBLY_ELECTION_TYPE,
									IConstants.PRESENT_ELECTION_YEAR, flag));

					electionResult = getPartyDetailsForTheGivenElectionYearInAConstituency(
							constituencyId, tehsilIds,
							IConstants.LATEST_BYE_ELECTION_YEAR,
							IConstants.ASSEMBLY_ELECTION_TYPE, flag);
					if (electionResult != null
							&& electionResult.getResultStatus().getResultCode() != 1) {
						partyResult.add(electionResult);
					}
				} else if (choices[i].equalsIgnoreCase("PC")) {
					ElectionResultPartyVO electionResult = new ElectionResultPartyVO();
					partyResult
							.add(getAssemblyOrParliamentElectionVotesPercentageForDifferentParties(
									0l, tehsilIds,
									IConstants.PARLIAMENT_ELECTION_TYPE,
									IConstants.PREVIOUS_ELECTION_YEAR, flag));
					partyResult
							.add(getAssemblyOrParliamentElectionVotesPercentageForDifferentParties(
									0l, tehsilIds,
									IConstants.PARLIAMENT_ELECTION_TYPE,
									IConstants.PRESENT_ELECTION_YEAR, flag));
					electionResult = getPartyDetailsForTheGivenElectionYearInAConstituency(
							0l, tehsilIds,
							IConstants.BYE_ELECTION_YEAR_2008_PARLIAMENT,
							IConstants.PARLIAMENT_ELECTION_TYPE, flag);
					if (electionResult.getResultStatus().getResultCode() != 1) {
						partyResult.add(electionResult);
					}
					electionResult = getPartyDetailsForTheGivenElectionYearInAConstituency(
							0l, tehsilIds,
							IConstants.BYE_ELECTION_YEAR_2006_PARLIAMENT,
							IConstants.PARLIAMENT_ELECTION_TYPE, flag);
					if (electionResult.getResultStatus().getResultCode() != 1) {
						partyResult.add(electionResult);
					}

				} else if (choices[i].equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE)) {
					partyResult.add(getMptcOrZptcElectionVotesPercentageForDifferentParties(
									tehsilIds,
									IConstants.ZPTC_ELECTION_TYPE,
									IConstants.LOCAL_ELECTIONS_PREVIOUS_ELECTION_YEAR,
									constituencyId, flag));
					partyResult.add(getMptcOrZptcElectionVotesPercentageForDifferentParties(
							        tehsilIds,
									IConstants.ZPTC_ELECTION_TYPE,
									IConstants.LOCAL_ELECTIONS_PRESENT_ELECTION_YEAR,
									constituencyId, flag));
				} else if (choices[i]
						.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE)) {
					partyResult
							.add(getMptcOrZptcElectionVotesPercentageForDifferentParties(
									tehsilIds,
									IConstants.MPTC_ELECTION_TYPE,
									IConstants.LOCAL_ELECTIONS_PREVIOUS_ELECTION_YEAR,
									constituencyId, flag));
					partyResult
							.add(getMptcOrZptcElectionVotesPercentageForDifferentParties(
									tehsilIds,
									IConstants.MPTC_ELECTION_TYPE,
									IConstants.LOCAL_ELECTIONS_PRESENT_ELECTION_YEAR,
									constituencyId, flag));
				}
			}
			party.addAll(getPartyVotesShareInConstituency(constituencyId, flag,partyResult));
		}

		List<ElectionResultVO> elections = new ArrayList<ElectionResultVO>();
		List<SelectOptionVO> headings = new ArrayList<SelectOptionVO>();
		for (PartyResultVO partyResultVO : party) {
			elections = partyResultVO.getElectionWiseResults();
			headings = partyResultVO.getElectionList();
			Collections.sort(headings, new SelectOptionVOComparator());// Heading
																		// Data
																		// Sorting

			// headings special character removal
			for (SelectOptionVO head : headings) {
				if (StringUtils.contains(head.getName(), '.'))
					head.setName(StringUtils.remove(head.getName(), '.'));
				else if (StringUtils.contains(head.getName(),
						IConstants.ASSEMBLY_ELECTION_TYPE))
					head.setName(StringUtils.replace(head.getName(),
							IConstants.ASSEMBLY_ELECTION_TYPE, "AC"));
				else if (StringUtils.contains(head.getName(),
						IConstants.PARLIAMENT_ELECTION_TYPE))
					head.setName(StringUtils.replace(head.getName(),
							IConstants.PARLIAMENT_ELECTION_TYPE, "PC"));

			}
			Collections.sort(elections, new ElectionResultTypeComparator());

			partyResultVO.setElectionList(headings);
			partyResultVO.setElectionWiseResults(elections);
		}

		try {
			// to interchange 2009 AC and 2009 PC
			if (party != null && party.size() > 0 && party.get(0) != null) {
				List<SelectOptionVO> headingInfo = party.get(0).getElectionList();
				Boolean rflag = false;
				int j = 0;
				for (int i = 0; i < headingInfo.size(); i++) {
					if (headingInfo.get(i).getName().equalsIgnoreCase("2009 AC")) {
						if (headingInfo.size() > (i + 1)) {
							if (headingInfo.get(i + 1).getName()
									.equalsIgnoreCase("2009 PC")) {
								rflag = true;
								j = i;
								break;
							}
						}
					}
				}
				if (rflag == true) {
					SelectOptionVO jval1 = headingInfo.get(j + 1);
					SelectOptionVO jval2 = headingInfo.get(j);
					headingInfo.set(j, jval1);
					headingInfo.set(j + 1, jval2);
					party.get(0).setElectionList(headingInfo);

					for (PartyResultVO resVO : party) {
						List<ElectionResultVO> electionWiseRes = resVO
								.getElectionWiseResults();

						ElectionResultVO jloc1 = electionWiseRes.get(j + 1);
						ElectionResultVO jloc2 = electionWiseRes.get(j);

						electionWiseRes.set(j, jloc1);
						electionWiseRes.set(j + 1, jloc2);
						resVO.setElectionWiseResults(electionWiseRes);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug(" Exception :" + ex);
		}

		return party;
	}

	/**
	 * This method retrives Votes Percentage Obtained for different Bye-Election
	 * parties in the constituency based on latest mandals for Mptc and Zptc
	 * Elections.
	 * 
	 * @param tehsilIds
	 * @param electionType
	 * @param electionYear
	 * @param constituencyId
	 * @param flag
	 * @return
	 */
	public ElectionResultPartyVO getMptcOrZptcElectionVotesPercentageForDifferentParties(
			StringBuilder tehsilIds, String electionType, String electionYear,
			Long constituencyId, int flag) {
		// flag = 1 specifies that to get Data only for the main parties like
		// TRS,TDP,INC,BJP,PRP i.e., ByElections Parties that are defined in
		// IConstants.BYE_ELECTIONS_STATIC_PARTIES
		// flag = 0 specifies that to get Data for all the parties participated
		// in that constituency
		ElectionResultPartyVO electionResult = new ElectionResultPartyVO();
		List<TeshilPartyInfoVO> tehsilVO = constituencyPageService
				.getTehsilPartyInfoForAConstituency(tehsilIds, electionYear,
						electionType, constituencyId);
		if (tehsilVO != null) {
			electionResult = populateDataForLocalElections(tehsilVO,
					electionYear, electionType, flag);
		}
		return electionResult;
	}

	/**
	 * This method retrives Votes Percentage Obtained for different Bye-Election
	 * parties in the constituency based on latest mandals for Assembly and
	 * Parliament Elections.
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 08-07-10
	 * @param tehsilIds
	 * @param electionType
	 * @param electionYear
	 * @param flag
	 * @return
	 */
	public ElectionResultPartyVO getAssemblyOrParliamentElectionVotesPercentageForDifferentParties(
			Long constituencyId, StringBuilder tehsilIds, String electionType,
			String electionYear, int flag) {
		// flag = 1 specifies that to get Data only for the main parties like
		// TRS,TDP,INC,BJP,PRP i.e., ByElections Parties that are defined in
		// IConstants.BYE_ELECTIONS_STATIC_PARTIES
		// flag = 0 specifies that to get Data for all the parties participated
		// in that constituency
		ElectionResultPartyVO electionResult = new ElectionResultPartyVO();
		electionResult = getPartyDetailsForTheGivenElectionYearInAConstituency(
				constituencyId, tehsilIds, electionYear, electionType, flag);
		return electionResult;
		// electionResult =
		// getAssemblyOrParliamentElectionVotesPercentageForDifferentParties(tehsilIds,IConstants.PARLIAMENT_ELECTION_TYPE,IConstants.PRESENT_ELECTION_YEAR,flag);

	}

	public List<CandidateElectionResultVO> getProcessedAlliancePartiesResults(
			List<CandidateElectionResultVO> elecResultVO, String elecType,
			String elecYear) {

		Long elecTypeId = new Long(0);
		List<CandidateElectionResultVO> candidateElectionResultsVO = null;
		if (elecResultVO != null && elecResultVO.size() > 0) {

			candidateElectionResultsVO = new ArrayList<CandidateElectionResultVO>();
			if (elecType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
				elecTypeId = new Long(2);
			else if (elecType
					.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
				elecTypeId = new Long(1);

			Map<Long, CandidateElectionResultVO> partyResults = getPartyResultsInMap(elecResultVO);
			Map<Long, String> addedParties = new HashMap<Long, String>();
			addedParties.put(new Long(0), "Others");
			if (partyResults != null && !partyResults.isEmpty()) {
				Set<Long> partyIds = partyResults.keySet();
				for (Long party : partyIds) {

					List<SelectOptionVO> alliancParties = getAlliancePartiesAsVO(
							elecYear, elecTypeId, party,0L);

					if (alliancParties != null && alliancParties.size() > 0) {

						List<CandidateElectionResultVO> allianc = new ArrayList<CandidateElectionResultVO>();
						for (SelectOptionVO alliancPar : alliancParties) {

							if (partyResults.containsKey(alliancPar.getId())
									&& !addedParties.containsKey(alliancPar
											.getId())) {

								allianc.add(partyResults
										.get(alliancPar.getId()));

								addedParties.put(alliancPar.getId(),
										"Party Added");
							}

						}

						if (allianc != null && allianc.size() > 0) {
							CandidateElectionResultVO resultVOAllianc = getGroupedResultForAllianc(allianc);
							if (resultVOAllianc != null) {
								candidateElectionResultsVO.add(resultVOAllianc);
							}
						}
					} else {
						if (partyResults.containsKey(party)) {
							candidateElectionResultsVO.add(partyResults
									.get(party));

						}
					}

				}

			}

		}

		return candidateElectionResultsVO;
	}

	public CandidateElectionResultVO getGroupedResultForAllianc(
			List<CandidateElectionResultVO> alliancRes) {
		CandidateElectionResultVO candResult = null;
		if (alliancRes != null && alliancRes.size() > 0) {

			if (alliancRes.size() == 1)
				return alliancRes.get(0);
			else if (alliancRes.size() > 1) {

				Collections.sort(alliancRes,
						new CandidateElecResultVOComparator());

				Double totPercentage = new Double(0);
				for (int i = 0; i < alliancRes.size(); i++) {

					totPercentage += new Double(alliancRes.get(i)
							.getVotesPercentage());
				}

				if (totPercentage > new Double(0)) {
					totPercentage = new BigDecimal(totPercentage).setScale(2,
							BigDecimal.ROUND_HALF_UP).doubleValue();

					alliancRes.get(0).setVotesPercentage(
							totPercentage.toString());
					alliancRes.get(0).setAllianceResult(true);

				}
				candResult = alliancRes.get(0);
			}
		}

		return candResult;
	}

	public Map<Long, CandidateElectionResultVO> getPartyResultsInMap(
			List<CandidateElectionResultVO> candResults) {
		Map<Long, CandidateElectionResultVO> resultsMap = null;
		if (candResults != null && candResults.size() > 0) {

			resultsMap = new HashMap<Long, CandidateElectionResultVO>();
			for (CandidateElectionResultVO result : candResults) {
				resultsMap.put(result.getPartyId(), result);
			}
		}
		return resultsMap;
	}

	public PartyElectionResultsVO getWonAndOppositionCandidateDetailsInAConstituencyWithMargin(
			Long constituencyId, String electionYear) {

		PartyElectionResultsVO partyResult = null;
		if (constituencyId != null && electionYear != null) {

			partyResult = new PartyElectionResultsVO();
			log
					.debug(" Inside getWonAndOppositionCandidateDetailsInAConstituencyWithMargin Method ..");
			List resultsList = nominationDAO.getWonAndOppCandidateInAnElection(
					constituencyId, electionYear);

			if (resultsList != null && resultsList.size() > 0) {
				for (int i = 0; i < resultsList.size(); i++) {
					Object[] params = (Object[]) resultsList.get(i);

					Long rank = (Long) params[6];
					if (rank.equals(new Long(1))) {
						partyResult.setPartyId((Long) params[0]);
						partyResult.setPartyName((String) params[1]);
						partyResult.setCandidateId((Long) params[2]);
						partyResult.setCandidateName((String) params[3]);
						Double votesEarned = (Double) params[4];
						partyResult.setVotesEarned(votesEarned.longValue());
						partyResult.setVotesPercentage((String) params[5]);
						partyResult.setRank(rank);
					} else if (rank.equals(new Long(2))) {
						partyResult.setSecondPartyName((String) params[1]);
						partyResult.setSecondCandidateName((String) params[3]);
						Double votesEarned = (Double) params[4];
						partyResult.setVotesEarnedBySecond(votesEarned
								.longValue());
						partyResult
								.setVotesPercentageBySecond((String) params[5]);
						partyResult.setRankBySecond(rank);
					}
				}

				try {
					// calculating margin votes and margin percent
					Long marginVotes = partyResult.getVotesEarned()
							- partyResult.getVotesEarnedBySecond();
					partyResult.setMarginVotes(marginVotes);
					Double marginPercent = new BigDecimal(new Double(
							partyResult.getVotesPercentage())
							- new Double(partyResult
									.getVotesPercentageBySecond())).setScale(2,
							BigDecimal.ROUND_HALF_UP).doubleValue();
					partyResult.setMarginPercent(marginPercent.toString());
				} catch (Exception ex) {
					ex.printStackTrace();
					log.debug(" Exception Raised :" + ex);
				}
			}
		}

		return partyResult;
	}

	public List<SelectOptionVO> getAllSocialCategories() {
		List<SocialCategory> socialCategoriesList = socialCategoryDAO.getAll();
		List<SelectOptionVO> socialCategories = new ArrayList<SelectOptionVO>();
		for (SocialCategory socialCategory : socialCategoriesList) {

			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(socialCategory.getSocialCategoryId());
			selectOptionVO.setName(socialCategory.getCategory());
			socialCategories.add(selectOptionVO);
		}
		return socialCategories;
	}

	public List<SelectOptionVO> getAllEducationalQualifications() {
		List<EducationalQualifications> qualifications = educationalQualificationsDAO
				.getAll();
		List<SelectOptionVO> eduQualificationsList = new ArrayList<SelectOptionVO>();
		for (EducationalQualifications educationalQualifications : qualifications) {
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(educationalQualifications
					.getEduQualificationId());
			selectOptionVO
					.setName(educationalQualifications.getQualification());
			eduQualificationsList.add(selectOptionVO);
		}
		return eduQualificationsList;
	}

	public List<SelectOptionVO> getAllOccupations() {
		List<Occupation> occupations = occupationDAO.getAll();
		List<SelectOptionVO> occupationsList = new ArrayList<SelectOptionVO>();
		for (Occupation occupation : occupations) {
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(occupation.getOccupationId());
			selectOptionVO.setName(occupation.getOccupation());
			occupationsList.add(selectOptionVO);
		}
		Collections.sort(occupationsList);
		return occupationsList;
	}

	public List<SelectOptionVO> getAllLanguages() {
		List<Language> languages = languageDAO.getAll();
		List<SelectOptionVO> languagesList = new ArrayList<SelectOptionVO>();
		for (Language language : languages) {
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(language.getLanguageId());
			selectOptionVO.setName(language.getLanguage());
			languagesList.add(selectOptionVO);
		}
		return languagesList;
	}

	public Long getElectionScopeForAElection(Long stateId, String electionType,
			Long countryId) {

		Long scopeId = null;
		if (stateId != null && electionType != null && countryId != null) {
			List<ElectionScope> electionScope = null;
			if (electionType
					.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)) {
				electionScope = electionScopeDAO
						.getElectionScopeForAElectionType(electionType,
								countryId);
			} else
				electionScope = electionScopeDAO
						.getElectionScopeForAElectionType(stateId,
								electionType, countryId);

			if (electionScope != null && electionScope.size() > 0)
				scopeId = electionScope.get(0).getElectionScopeId();
		}

		return scopeId;
	}

	public List<SelectOptionVO> getStaticNationalParties() {

		List<SelectOptionVO> selectedParties = new ArrayList<SelectOptionVO>();
		List<Party> parties = partyDAO.findStaticPartiesByRecognization(
				IConstants.NATIONAL_PARTY_TYPE,
				IConstants.NATIONAL_STATIC_PARTIES);
		if (parties != null && parties.size() > 0) {
			for (Party party : parties) {
				SelectOptionVO partyVO = new SelectOptionVO();
				partyVO.setId(party.getPartyId());
				partyVO.setName(party.getShortName());

				selectedParties.add(partyVO);
			}
		}

		return selectedParties;
	}
    
	/** Method to Get State Parties Based on stateId 
	 * @param stateId
	 * @return selectedParties
	 */
	public List<SelectOptionVO> getStaticPartiesListForAState(Long stateId) {

		List<SelectOptionVO> selectedParties = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> staticParties = new ArrayList<SelectOptionVO>();

		// State Parties
		selectedParties.addAll(getStaticStateParties(stateId));
		// National Parties
		selectedParties.addAll(getStaticNationalParties());
        // For state Parties From IConstants
		selectedParties.addAll(getSelectedStateParties(stateId));
		Collections.sort(selectedParties);
		return selectedParties;
	}

	/** Method to Get Selected State Parties Based on stateId from IConstants 
	 * @param stateId
	 * @return stateSelectedParties
	 */
	public List<SelectOptionVO> getSelectedStateParties(Long stateId) {

		List<SelectOptionVO> stateSelectedParties = new ArrayList<SelectOptionVO>();
		String stateParties = "STATIC_STATE_PARTIES_" + stateId;
		IConstants iConstants = new IConstants() { };
		String partyNames = null;
		try {
			partyNames = IConstants.class.getField(stateParties).get(iConstants).toString();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			return stateSelectedParties;
		}
		
		List<Party> parties = partyDAO.findByShortNames(partyNames);
		
		if (parties != null && parties.size() > 0) {
			
			for (Party party : parties) {
				SelectOptionVO partyVO = new SelectOptionVO();
				partyVO.setId(party.getPartyId());
				partyVO.setName(party.getShortName());

				stateSelectedParties.add(partyVO);
			}
		}
		return stateSelectedParties;
	}

	public List<SelectOptionVO> getStaticStateParties(Long stateId) {

		List<SelectOptionVO> selectedParties = new ArrayList<SelectOptionVO>();
		List<Party> parties = partyDAO.findStatePartyByStateId(stateId);
		if (parties != null && parties.size() > 0) {
			for (Party party : parties) {
				SelectOptionVO partyVO = new SelectOptionVO();
				partyVO.setId(party.getPartyId());
				partyVO.setName(party.getShortName());

				selectedParties.add(partyVO);
			}
		}
		return selectedParties;
	}

	public List<SelectOptionVO> getStaticPartiesListFromElectionScope(
			Long scopeId) {

		List<SelectOptionVO> selectedParties = new ArrayList<SelectOptionVO>();
		if (scopeId != null) {
			ElectionScope electionScope = electionScopeDAO.get(scopeId);
			if (electionScope.getElectionType().getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE))
				selectedParties = getAllNationalParties();
			else
				selectedParties = getStaticPartiesListForAState(electionScope.getState().getStateId());
		}

		return selectedParties;
	}

	public SelectOptionVO getStateOfADistrict(Long districtId) {
		District district = districtDAO.get(districtId);
		SelectOptionVO selectoptn = new SelectOptionVO(district.getState().getStateId(), WordUtils.capitalize(district.getState().getStateName().toLowerCase()) );

		return selectoptn;
	}

	public List<SelectOptionVO> getAllNationalParties() {

		List<SelectOptionVO> selectedParties = new ArrayList<SelectOptionVO>();
		List<Party> parties = partyDAO.findStaticParties(IConstants.NATIONAL_PARTY_TYPE);
		if (parties != null && parties.size() > 0) {
			for (Party party : parties) {
				SelectOptionVO partyVO = new SelectOptionVO();
				partyVO.setId(party.getPartyId());
				partyVO.setName(party.getShortName());

				selectedParties.add(partyVO);
			}
		}

		return selectedParties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.itgrids.partyanalyst.service.IStaticDataService#
	 * getLocalBodyElectionTypesInAState(java.lang.Long) Method that returns a
	 * list of Local Body Election Types in a state
	 */
	public List<SelectOptionVO> getLocalBodyElectionTypesInAState(Long stateId) {

		List<SelectOptionVO> localBodyElectionsList = new ArrayList<SelectOptionVO>();

		if (stateId != null) {
			List electionTypes = localElectionBodyDAO.getLocalELectionTypesInAState(stateId);
			if (electionTypes != null && electionTypes.size() > 0) {
				for (int i = 0; i < electionTypes.size(); i++) {
					Object[] values = (Object[]) electionTypes.get(i);
					SelectOptionVO option = new SelectOptionVO();
					option.setId((Long) values[0]);
					option.setName((String) values[1]);

					localBodyElectionsList.add(option);
				}
			}
		}

		return localBodyElectionsList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.itgrids.partyanalyst.service.IStaticDataService#
	 * getLocalBodysInAStateByType(java.lang.Long, java.lang.Long) Method
	 * returns a list of Local Bodys in a state
	 */
	public List<SelectOptionVO> getLocalBodysInAStateByType(Long stateId,
			Long typeId) {
		List<SelectOptionVO> localBodysList = new ArrayList<SelectOptionVO>();

		if (stateId != null && typeId != null) {
			List localBodys = localElectionBodyDAO.findByElectionTypeAndState(typeId, stateId);
			if (localBodys != null && localBodys.size() > 0) {
				for (int i = 0; i < localBodys.size(); i++) {
					Object[] values = (Object[]) localBodys.get(i);
					SelectOptionVO option = new SelectOptionVO();
					option.setId((Long) values[0]);
					option.setName((String) values[1]);

					localBodysList.add(option);
				}
			}
		}

		return localBodysList;
	}

	/**
	 * To Get The List Of All Different Sources Of Information(for Problems &
	 * political changes) From DB Into SelectOptionVO
	 * 
	 * @return
	 */
	public List<SelectOptionVO> getAllInformationSources() {
		List<SelectOptionVO> sources = new ArrayList<SelectOptionVO>();
		try {
			List<InformationSource> informationSources = informationSourceDAO.getAll();
			if (informationSources.size() > 0) {
				for (InformationSource infoSource : informationSources)
					sources.add(new SelectOptionVO(infoSource.getInformationSourceId(), infoSource.getInformationSource()));
				return sources;
			} else {
				throw new GenericException("Data Not Available");
			}

		} catch (Exception e) {
			log.debug(e);
			e.printStackTrace();
			return null;
		}
	}

	public List getListOfElectionIdsForGivenElectionTypeIdAndListOfElectionYears(
			Long electinTypeId, Long electionYear1, Long electionYear2,Long stateId, String electionSubType) {
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		StringBuilder electionYears = new StringBuilder();
		electionYears.append(",").append(electionYear1).append(",").append(electionYear2);
		ElectionType electionType = electionTypeDAO.get(electinTypeId);
		String elecType = electionType.getElectionType();

		List elections = null;
		if (elecType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
			elections = electionDAO.findParliamentElectionIdForGivenElectionYearAndElectionYears(electinTypeId, electionYears.substring(1),electionSubType);
		else
			elections = electionDAO.findElectionIdForGivenElectionYearAndElectionYears(electinTypeId, electionYears.substring(1), stateId,electionSubType);
		return elections;
	}

	public List<SelectOptionVO> getLocationsHirarchyByType(String type, Long id) {

		log.debug(" Inside getLocationsHirarchyByType Method ..");

		List<SelectOptionVO> resultsList = null;

		if (type != null && id != null) {

			resultsList = new ArrayList<SelectOptionVO>();
			if (type.equalsIgnoreCase(IConstants.STATE)) {
				State state = stateDAO.get(id);
				SelectOptionVO option = new SelectOptionVO(state.getStateId(),IConstants.STATE);
				resultsList.add(option);
			} else if (type.equalsIgnoreCase(IConstants.DISTRICT)) {
				District district = districtDAO.get(id);
				SelectOptionVO option = new SelectOptionVO(district.getDistrictId(), IConstants.DISTRICT);
				resultsList.add(option);
				SelectOptionVO option1 = new SelectOptionVO(district.getState().getStateId(), IConstants.STATE);
				resultsList.add(option1);
			} else if (type.equalsIgnoreCase(IConstants.CONSTITUENCY)) {
				Constituency constituency = constituencyDAO.get(id);
				SelectOptionVO option = new SelectOptionVO(constituency.getConstituencyId(), IConstants.CONSTITUENCY.toUpperCase());
				resultsList.add(option);
				SelectOptionVO option1 = new SelectOptionVO(constituency.getState().getStateId(), IConstants.STATE);
				resultsList.add(option1);
				if (!constituency.getElectionScope().getElectionType().getElectionType().equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)) {
					SelectOptionVO option2 = new SelectOptionVO(constituency.getDistrict().getDistrictId(), IConstants.DISTRICT);
					resultsList.add(option2);
				}
			} else if (type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY)) {
				LocalElectionBody localElecBody = localElectionBodyDAO.get(id);
				SelectOptionVO option = new SelectOptionVO(localElecBody.getLocalElectionBodyId(),IConstants.LOCAL_ELECTION_BODY);
				resultsList.add(option);
				SelectOptionVO option1 = new SelectOptionVO(localElecBody.getDistrict().getState().getStateId(),IConstants.STATE);
				resultsList.add(option1);
				SelectOptionVO option2 = new SelectOptionVO(localElecBody.getDistrict().getDistrictId(), IConstants.DISTRICT);
				resultsList.add(option2);

				List values = assemblyLocalElectionBodyDAO.findConstituencyByLocalELectionBody(id,IConstants.DELIMITATION_YEAR.toString());
				if (values != null) {
					Object[] result = (Object[]) values.get(0);
					SelectOptionVO option3 = new SelectOptionVO((Long) result[0], IConstants.CONSTITUENCY);
					resultsList.add(option3);
				}
			}
		}

		return resultsList;
	}

	public List<SelectOptionVO> getLatestAssemblyConstituenciesInDistrict(Long districtId) {
		if (log.isDebugEnabled())
			log.debug("Getting Latest Assembly Constituencies In A District ..");

		List<SelectOptionVO> latestConstituencies = null;

		List constituenciesList = delimitationConstituencyDAO.getLatestConstituenciesForADistrict(districtId);
		if (constituenciesList != null && constituenciesList.size() > 0) {
			latestConstituencies = new ArrayList<SelectOptionVO>();
			Iterator lstItr = constituenciesList.listIterator();
			while (lstItr.hasNext()) {
				Object[] values = (Object[]) lstItr.next();
				SelectOptionVO option = new SelectOptionVO((Long) values[0],(String) values[1]);
				latestConstituencies.add(option);
			}
		}
		return latestConstituencies;
	}

	public String removeSpecialCharectersFromString(String formatString) {
		String str = formatString;

		str = StringUtils.replace(str, "\n", IConstants.HTMLENTER);
		str = StringUtils.replace(str, "'", IConstants.HTMLSINGLEQUOTE);
		str = StringUtils.replace(str, "\"", IConstants.HTMLDOUBLEQUOTES);

		return str;
	}

	/**
	 * Returns Recent Assembly election Id Happened In A State Main Election Is
	 * Considered
	 */
	public Long getRecentAssemblyMainElectionIdInAState(Long stateId) {

		Long electionId = 0L;
		List electionLst = electionDAO.findLatestElectionAssemblyElectionIdForState(IConstants.ASSEMBLY_ELECTION_TYPE, stateId,IConstants.ELECTION_SUBTYPE_MAIN);
		if (electionLst != null && electionLst.size() > 0) {

			Object[] values = (Object[]) electionLst.get(0);
			electionId = (Long) values[1];
		}

		return electionId;
	}

	/**
	 * Method To Get StateId for the User Access Value
	 * 
	 * @author Sai Krishna
	 * @param userId
	 * @return stateId
	 */
	public Long getStateIdForUserByAccessValue(Long userId) {

		Long stateId = 0L;

		try {

			if (log.isInfoEnabled())
				log.info("Getting User Object By User Id ..");

			Registration user = registrationDAO.get(userId);

			String userAccessType = user.getAccessType();
			String userAccessValue = user.getAccessValue();

			stateId = getStateIdByUserAccessTypeAndValue(userAccessType,userAccessValue);

		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("Exception Raised :" + ex);
		}

		return stateId;
	}

	/**
	 * @param userAccessType
	 * @param userAccessValue
	 * @return
	 * @throws Exception
	 */
	public Long getStateIdByUserAccessTypeAndValue(String userAccessType,String userAccessValue) throws Exception {

		Long stateId = 0L;

		if (userAccessType != null && userAccessType != ""&& userAccessValue != null && userAccessValue != "") {

			if (userAccessType.equalsIgnoreCase(IConstants.STATE)) {

				// Access Type is state
				stateId = Long.parseLong(userAccessValue);

			} else if (userAccessType.equalsIgnoreCase(IConstants.DISTRICT)) {

				// Access Type is District
				District district = districtDAO.get(Long.parseLong(userAccessValue));
				stateId = district.getState().getStateId();

			} else if (userAccessType.equalsIgnoreCase(IConstants.MLA)|| userAccessType.equalsIgnoreCase(IConstants.MP)) {

				// Access Type is Constituency (MLA / MP)
				Constituency constituency = constituencyDAO.get(Long.parseLong(userAccessValue));
				stateId = constituency.getState().getStateId();

			}
		}

		return stateId;
	}

	public List<SelectOptionVO> getAllProblemStatus() {
		List<SelectOptionVO> problemStatusList = new ArrayList<SelectOptionVO>();
		try {
			List<ProblemStatus> problemStatus = problemStatusDAO.getAll();
			if (problemStatus.size() > 0) {
				for (ProblemStatus staus : problemStatus) {
					problemStatusList.add(new SelectOptionVO(staus.getProblemStatusId(), staus.getStatus()));
				}
				return problemStatusList;
			} else {
				throw new Exception("Data Not Available");
			}

		} catch (Exception e) {
			log.debug(e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method To Return Problem Status
	 */
	public List<SelectOptionVO> getDefaultProblemStatus(String statusValues) {

		List<SelectOptionVO> problemStatus = new ArrayList<SelectOptionVO>();

		List defaultProblemStatusLst = problemStatusDAO.getDefaultProblemStatus(statusValues);
		if (defaultProblemStatusLst != null	&& defaultProblemStatusLst.size() > 0) {

			Iterator lstItr = defaultProblemStatusLst.listIterator();
			while (lstItr.hasNext()) {

				Object[] values = (Object[]) lstItr.next();

				problemStatus.add(new SelectOptionVO((Long) values[0],(String) values[1]));
			}
		}

		return problemStatus;
	}
	
}



