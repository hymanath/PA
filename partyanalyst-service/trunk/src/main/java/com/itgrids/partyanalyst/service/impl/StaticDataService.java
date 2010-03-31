package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidateWonVO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyWinnerInfoVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.dto.ElectionBasicInfoVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.model.AllianceGroup;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionAlliance;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ElectionYearsComparator;
import com.itgrids.partyanalyst.utils.IConstants;


public class StaticDataService implements IStaticDataService {

	private IElectionDAO electionDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IPartyDAO partyDAO;
	private IStateDAO stateDAO;
	private IElectionAllianceDAO electionAllianceDAO;
	private IDistrictDAO districtDAO;
	private IAllianceGroupDAO  allianceGroupDAO;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private INominationDAO nominationDAO;
	private ITownshipDAO townshipDAO;
	private ITehsilDAO tehsilDAO;
	private IHamletDAO hamletDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IPartyElectionResultDAO partyElectionResultDAO;
	private IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO;
	private final static Logger log = Logger.getLogger(StaticDataService.class);
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private Set parliamentConstituencies = new HashSet(0);
	private IVillageBoothElectionDAO villageBoothElectionDAO;
	

	/**
	 * @param partyDAO the partyDAO to set
	 */
	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}


	/**
	 * @param stateDAO the stateDAO to set
	 */
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public void setElectionAllianceDAO(IElectionAllianceDAO electionAllianceDAO){
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

	public List<ElectionScope> getElectionScope(Long electionType){
		return electionScopeDAO.findByPropertyElectionTypeId(electionType);
	}
	
	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}


	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
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


	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	public void setConstituencyElectionDAO(IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}
	
	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}

	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}
	

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public void setDelimitationConstituencyDAO(IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
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

	

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}


	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
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


	public List<SelectOptionVO> findTownshipsByTehsilID(Long mandalID){
		List<SelectOptionVO> townshipVOs = new ArrayList<SelectOptionVO>();
		SelectOptionVO townshipVO = null;
		List<Township> townships = townshipDAO.findByTehsilID(mandalID);
		for(Township township:townships){
			townshipVO = new SelectOptionVO(township.getTownshipId(), township.getTownshipName());
			townshipVOs.add(townshipVO);
		}
		return townshipVOs;
	}

	public List<SelectOptionVO> getStates(Long electionType){
		List<ElectionScope> electionScopes = electionScopeDAO.findByPropertyElectionTypeId(electionType);
		List<SelectOptionVO> stateList = new ArrayList<SelectOptionVO>();
		for(ElectionScope scope: electionScopes){
			State state = scope.getState();
			stateList.add(new SelectOptionVO(state.getStateId(), state.getStateName()));
		}
		return stateList;
	}

	public List<String> getElectionYears(){
		return electionDAO.listOfYears();
	}

	public List<SelectOptionVO> getElectionIdsAndYears(Long electionTypeId) {
		List elections = villageBoothElectionDAO.findElectionsForElectionType(electionTypeId);
		List<SelectOptionVO> years = new ArrayList<SelectOptionVO>();
		for(Object[] election: (List<Object[]>)elections){
			years.add(new SelectOptionVO((Long)election[0], election[1].toString()));
		}
		return years;
	}
	
	public List<SelectOptionVO> getElectionIdsAndYearsForConstituency(Long constituencyId) {
		List elections = boothConstituencyElectionDAO.findElectionsHappendInConstituency(constituencyId);
		List<SelectOptionVO> years = new ArrayList<SelectOptionVO>();
		for(Object[] election: (List<Object[]>)elections){
			years.add(new SelectOptionVO((Long)election[0], election[1].toString()));
		}
		return years;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getElectionIdsAndYearsInfo(Long elecType,Long stateId){
		List<SelectOptionVO> electionYears = new ArrayList<SelectOptionVO>();
		List elections = electionDAO.findElectionAndYearForElectionTypeAndState(elecType,stateId);
		if(elections != null){
		 for(int i=0;i<elections.size();i++){
			 Object[] params = (Object[])elections.get(i);
			 Long electionId = (Long)params[0];
			 String elecYear = (String)params[1];
			 
			 SelectOptionVO selectOption = new SelectOptionVO();
			 selectOption.setId(electionId);
			 selectOption.setName(elecYear);
			 
			 electionYears.add(selectOption);
		 }
		}
	
		return electionYears;
	}
	public List<SelectOptionVO> getDistricts(Long stateId) {
		List<District> list =  districtDAO.findByStateId(stateId);
		List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
		for(District district: list) {
			districts.add(new SelectOptionVO(district.getDistrictId(), district.getDistrictName()));
		}
		return districts;
	}


	public List<State> getAllStates() {
		return stateDAO.getAll();
	}

	//Need refactoring the code and unit testing- Ashok	
	public List<SelectOptionVO> getAlliancePartiesAsVO(String electionYear, Long electionType, Long partyId) {
		List<SelectOptionVO> allianceParties = new ArrayList<SelectOptionVO>();
		
		Long groupId = getGroupIdIfPartyHasAlliances(partyId, electionYear, electionType);
			
		if(groupId != null) {
			List<AllianceGroup> allianceGroupList = allianceGroupDAO.findByGroupId(groupId);
			for(AllianceGroup allianceGroup : allianceGroupList){
					allianceParties.add(new SelectOptionVO(allianceGroup.getParty().getPartyId()
							, allianceGroup.getParty().getShortName()));
			}
		return allianceParties;
		}
		
	return null;
	}

	//Need refactoring the code and unit testing- Ashok	
	public Long getGroupIdIfPartyHasAlliances(Long partyId, String electionYear, Long electionType) {
		List<ElectionAlliance> allianceList = electionAllianceDAO.findByElectionYearAndType(electionYear, electionType);
		
		for(ElectionAlliance alliance: allianceList){
			Long groupId = alliance.getGroup().getGroupId();
			List<AllianceGroup> allianceGroupList = allianceGroupDAO.findByGroupId(groupId);
			for(AllianceGroup allianceGroup : allianceGroupList){
				if(allianceGroup.getParty().getPartyId().equals(partyId)){
					return allianceGroup.getGroup().getGroupId();
				}
			}
		}
		return null;
	}

	//Need refactoring the code and unit testing- Ashok
	public List<Party> getAllianceParties(String electionYear, Long electionType, Long partyId) {
		List<Party> allianceParties = null;
		
		Long groupId = getGroupIdIfPartyHasAlliances(partyId, electionYear, electionType);
		if(groupId != null) {
			allianceParties = new ArrayList<Party>();
			List<AllianceGroup> allianceGroupList = allianceGroupDAO.findByGroupId(groupId);
			for(AllianceGroup allianceGroup : allianceGroupList){
				allianceParties.add(allianceGroup.getParty());
			}
		}
		return allianceParties;
	}

	//Need refactoring the code and unit testing- Ashok
	public boolean hasAlliances(String electionYear, Long electionType, Long partyId) {
		if (getGroupIdIfPartyHasAlliances(partyId, electionYear, electionType) != null )
			return true;
		
		return false;
	}


	public List<String> getElectionYears(Long electionType) {
		List<Election> elections = electionDAO.findByPropertyTypeId(electionType);
		List<String> years = new ArrayList<String>();
		for(Election election: elections){
			years.add(election.getElectionYear());
		}
		Collections.sort(years, new ElectionYearsComparator());
		return years;
	}

	public Long getGroupIdIfPartyHasAlliances(
			List<ElectionAlliance> allianceList, Long partyId) {
		return null;
	}

	public List<SelectOptionVO> getConstituencies(Long stateId) {
		List<Constituency> constList = constituencyDAO.findByStateId(stateId);
		List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>();
		
		for(Constituency constituency: constList){
			constituencies.add(new SelectOptionVO(constituency.getConstituencyId(), constituency.getName()));
		}
		return constituencies;
	}
	
	public List<ConstituencyElection> getConstituencyElections(Long electionID,Long stateId,Long  districtID){
		List<ConstituencyElection>  constituencyElectionList = null;
		if(districtID==null || districtID==0L){
			System.out.println(" DAO ...1");
			//constituencyElectionList = constituencyElectionDAO.findByElection(electionID);
			constituencyElectionList = constituencyElectionDAO.findByElectionAndState(electionID, stateId);
		}else{
			System.out.println("DAO ... 2");
			//constituencyElectionList = constituencyElectionDAO.findByElectionAndDistrict(electionID, districtID);
			constituencyElectionList = constituencyElectionDAO.findByElectionAndStateAndDistrict(electionID, stateId, districtID);
		}
		return constituencyElectionList;
	}
	
	public List<SelectOptionVO> getStaticParties(){
		List<SelectOptionVO> staticParties = new ArrayList<SelectOptionVO>();
		List<Party> parties = partyDAO.findByShortNames(IConstants.STATIC_PARTIES);
		for(Party party : parties){
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(party.getPartyId());
			selectOptionVO.setName(party.getShortName());
			staticParties.add(selectOptionVO);
		}
		return staticParties;
	}
	
	public List<SelectOptionVO> getPartiesForConstituency(Long constituencyId, String electionYear){
		List<Party> parties = nominationDAO.findPartiesByConstituencyAndElection(constituencyId, electionYear);
		List<SelectOptionVO> partyVOs = new ArrayList<SelectOptionVO>();
		for(Party party:parties){
			SelectOptionVO partyVO = new SelectOptionVO(party.getPartyId(), party.getShortName());
			partyVOs.add(partyVO);
		}
		return partyVOs;
	}
	
	//Ravi Kiran code started from here on wards
	

	
	/*
	 * This method takes District Id as input and displays all the Assembly Candidates
	 * that are present in that District.
	 */	
	@SuppressWarnings("unchecked")
	public ConstituenciesStatusVO getConstituenciesWinnerInfo(Long districtId){
		try{
		log.debug("DistrictPageService.getConstituenciesWinnerInfo()...started started..");
		List delimitationYear = delimitationConstituencyDAO.getLatestDelimitationYear();
		Long electionYear = new Long(delimitationYear.get(0).toString()) ;
		Long rank = 1l;
		getAllParliamentWinningCandidatesForADistrict(districtId);
		log.debug("DistrictPageService.getConstituenciesWinnerInfo() delimitationYear:"+electionYear);
		ConstituenciesStatusVO constituenciesStatusVO = getConstituenciesForDistrict(districtId, electionYear, IConstants.ASSEMBLY_ELECTION_TYPE);
		List<SelectOptionVO> constituencies = (constituenciesStatusVO.getExistConstituencies());
		constituencies.addAll(constituenciesStatusVO.getNewConstituencies());
		
		constituenciesStatusVO.setTotalConstituenciesAfterDelimitation(constituencies.size());
		constituenciesStatusVO.setTotalDeletedConstituencies(constituenciesStatusVO.getDeletedConstituencies().size());
		List<ConstituencyWinnerInfoVO> constituencyWinnerInfoVOList = new ArrayList<ConstituencyWinnerInfoVO>();
		StringBuilder constituencyIDs = new StringBuilder();
		for(SelectOptionVO constituency : constituencies){
			constituencyIDs.append(",").append(constituency.getId());
		}	
		
		log.debug("DistrictPageService.getConstituenciesWinnerInfo() constituencies:"+constituencyIDs);
		List candidates =  nominationDAO.findCandidateNamePartyByConstituencyAndElection(constituencyIDs.substring(1), electionYear.toString());
		constituencies.removeAll(constituenciesStatusVO.getNewConstituencies());
		
		
		log.debug("DistrictPageService.getConstituenciesWinnerInfo() total candidates:"+candidates.size());
		for(int i = 0; i<candidates.size(); i++){
			ConstituencyWinnerInfoVO constituencyWinnerInfoVO = new ConstituencyWinnerInfoVO();
			Object[] obj = (Object[]) candidates.get(i);
			constituencyWinnerInfoVO.setConstituencyName(obj[0].toString());
			constituencyWinnerInfoVO.setCandidateName(obj[1].toString());
			constituencyWinnerInfoVO.setCandidateId(obj[4].toString());
			constituencyWinnerInfoVO.setConstituencyId(obj[3].toString());
			List list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(Long.parseLong(obj[3].toString()));
			for(int j=0; j<list.size(); j++){
				Object[] params = (Object[])list.get(j);
				constituencyWinnerInfoVO.setParliamentConstituencyId(Long.parseLong(params[0].toString()));
				constituencyWinnerInfoVO.setParliamentConstituencyName(params[1].toString());
				parliamentConstituencies.add(Long.parseLong(params[0].toString()));
			}
			constituencyWinnerInfoVO.setPartyName(obj[2].toString());
			if(obj[5] != null){
				constituencyWinnerInfoVO.setPartyFlag(obj[5].toString());
			}
			constituencyWinnerInfoVOList.add(constituencyWinnerInfoVO);
		}
		List zptcCount = nominationDAO.getZptcCountInADistrict(districtId,IConstants.ZPTC_ELECTION_TYPE,IConstants.ZPTC_ELECTION_TYPE,rank);
		constituenciesStatusVO.setZptcCount(Long.parseLong(zptcCount.get(0).toString()));
		List mptcCount = nominationDAO.getMptcCountInADistrict(districtId,IConstants.MPTC_ELECTION_TYPE,IConstants.MPTC_ELECTION_TYPE,rank);
		constituenciesStatusVO.setMptcCount(Long.parseLong(mptcCount.get(0).toString()));	
		constituenciesStatusVO.setConstituencyWinnerInfoVO(constituencyWinnerInfoVOList);
		constituenciesStatusVO.setDelimitationYear(electionYear);
		constituenciesStatusVO.setElectionType(electionYear.toString());
		constituenciesStatusVO.setElectionType(IConstants.ASSEMBLY_ELECTION_TYPE);
		constituenciesStatusVO.setElectionYear(electionYear.toString());
		constituenciesStatusVO.setElectionType(IConstants.ASSEMBLY_ELECTION_TYPE);
		return constituenciesStatusVO;
		}catch(Exception e){
			log.debug("Exception raised--->");
			log.error(e);			
			return null;
		}
	}
	
	
	
	/*
	 * This method takes District Id as input and retrives all the constituencies that
	 * are present in that particular district. And it sets the constituencies names and 
	 * corresponding Id's in the Data transfer Object.
	 */	
	
	@SuppressWarnings("unchecked")
	public ConstituenciesStatusVO getConstituenciesForDistrict(Long districtId,Long electionYear, String electionType){
		ConstituenciesStatusVO constituencyVO = new ConstituenciesStatusVO();
		List<SelectOptionVO> deleteList = null;
		List<SelectOptionVO> existList = null;
		List<SelectOptionVO> newList = null;
		try{
			log.info("Entered in to getConstituenciesForDistrict() method...");
			log.info("Making constituencyDAO.findConstituencyByDistrictElectionType(districtId,electionType) DAO call...");
			List result  = constituencyDAO.findConstituencyByDistrictElectionType(districtId,electionType);
			
			deleteList= constituencyVO.getDeletedConstituencies();
			if(deleteList == null){
				log.warn("Could not initialize the list ");
			}
			existList= constituencyVO.getExistConstituencies();
			if(existList == null){
				log.warn("Could not initialize the list ");
			}
			newList= constituencyVO.getNewConstituencies();	
			if(newList == null){
				log.warn("Could not initialize the list ");
			}
			log.info("Iterating loop to set the data in to the corresponding lists ");
			for(int i=0;i<result.size();i++){
				Object[] parms = (Object[]) result.get(i);				
				SelectOptionVO selectOptionVO= new SelectOptionVO();				
				if(parms[2]!= null && parms[3] == null && parms[2].toString().equals(electionYear.toString())){
					selectOptionVO.setId(Long.parseLong(parms[0].toString()));
					selectOptionVO.setName(parms[1].toString());		
					newList.add(selectOptionVO);			
				}else if(parms[3] == null){
					selectOptionVO.setId(Long.parseLong(parms[0].toString()));
					selectOptionVO.setName(parms[1].toString());			
					existList.add(selectOptionVO);
					}
				else if(parms[3] != null && parms[3].toString().equals(electionYear.toString())){					
							selectOptionVO.setId(Long.parseLong(parms[0].toString()));
							selectOptionVO.setName(parms[1].toString());
							deleteList.add(selectOptionVO);								
				}		
			}	
			return constituencyVO;
		}catch(Exception e){
			log.debug("Exception raised--->");
			log.error(e);			
			return null;
		}
	}
	
	
	
	/*
	 * This method takes District Id as input and retrives all the mandals that
	 * are present in that particular district. And it sets the constituencies names and 
	 * corresponding Id's in the Data transfer Object.
	 */	
	@SuppressWarnings("unchecked")
	public List<MandalVO> getMandalsForDistrict(Long districtId) {
		List tehsil =  tehsilDAO.findTehsilsByDistrict(districtId);
		List<MandalVO> mandal=new ArrayList<MandalVO>();
		if(log.isDebugEnabled())
			log.debug("Entered into getMandalsForDistrict method....");
		try{
			for(int i=0;i<tehsil.size();i++){
				Object[] result = (Object[])tehsil.get(i);
				MandalVO objVO = new MandalVO();
				if(log.isDebugEnabled())
					log.info("Mandal Name--->"+result[1].toString());
						objVO.setName(result[1].toString());	
			
				if(log.isDebugEnabled())
					log.info("Mandal Id--->"+(Long)result[0]);
					objVO.setId((Long)result[0]);
				mandal.add(objVO);
			}
		}catch(Exception ex){
			log.debug("Exception Raised -->" + ex);
			return null;
		}		
		return mandal;
	}
	
	public List<ConstituencyElection> getConstituencyElectionsFromNomination(Long electionID,Long stateId,Long districtID,Long rank,Long partyId){
		List<ConstituencyElection>  constituencyElectionList = null;
		if(districtID==null || districtID==0L){
			System.out.println(" DAO ...1");
			if(rank.intValue() == -1)
				constituencyElectionList = nominationDAO.findByElectionAndStateAndNthRank(electionID, stateId, new Long(4), partyId);
			else
			    constituencyElectionList = nominationDAO.findByElectionAndStateAndRank(electionID, stateId, rank, partyId);
		}else{
			System.out.println("DAO ... 2");
			if(rank.intValue() == -1)
				constituencyElectionList = nominationDAO.findByElectionAndStateAndDistrictAndNthRank(electionID, stateId, districtID, new Long(4), partyId);
			else
			    constituencyElectionList = nominationDAO.findByElectionAndStateAndDistrictAndRank(electionID, stateId, districtID, rank, partyId);
		}
	return constituencyElectionList;
	}
	
	public List<ConstituencyElection> getConstituencyElectionsFromNominationWithAlliances(Long electionID,Long stateId,Long districtID,Long rank,List<SelectOptionVO> parties){
		List<ConstituencyElection>  constituencyElectionList = null;
		List<Long> partyIds = null;
		if(parties != null && parties.size() > 0){
			partyIds = new ArrayList<Long>();
			for(SelectOptionVO party:parties)
				partyIds.add(party.getId());
		}
		if(districtID==null || districtID==0L){
			System.out.println(" DAO ...1");
			if(rank.intValue() == -1)
				constituencyElectionList = nominationDAO.findByElectionAndStateAndNthRank(electionID, stateId,new Long(4),partyIds);
			else
			    constituencyElectionList = nominationDAO.findByElectionAndStateAndRank(electionID, stateId,rank ,partyIds);
		}else{
			System.out.println("DAO ... 2");
			if(rank.intValue() == -1)
				constituencyElectionList = nominationDAO.findByElectionAndStateAndDistrictAndNthRank(electionID, stateId, districtID, new Long(4), partyIds);
			else
			    constituencyElectionList = nominationDAO.findByElectionAndStateAndDistrictAndRank(electionID, stateId, districtID, rank, partyIds);
		}
		
	return constituencyElectionList;
	}
	
	public List<ConstituencyElection> getConstituencyElectionsFromNominationForCountry(Long electionID,Long stateId,Long countryId,Long rank,Long partyId){
		List<ConstituencyElection>  constituencyElectionList = null;
		log.debug("Inside getConstituencyElectionsFromNominationForCountry(staticDataService).....");
		if(rank.intValue() > 0)
			constituencyElectionList = nominationDAO.findByElectionIdAndStateIdAndCountryIdAndRank(electionID, stateId, countryId, rank, partyId);
		else
			constituencyElectionList = nominationDAO.findByElectionIdAndStateIdAndCountryIdAndNthRank(electionID, stateId, countryId, rank, partyId);
	return constituencyElectionList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IStaticDataService#getPartyElectionResultsForAParty(java.lang.Long, java.lang.Long)
	 * This method electionResults for a party,like totalseats won,total votes percentage,total constituencies participated .....
	 * Input for this method is election_id and party_id
	 */
	public PartyElectionResult getPartyElectionResultsForAParty(Long electionId,Long partyId){
		
		PartyElectionResult partyElectionResult = null;
		if(electionId != null && partyId != null){
		 List<PartyElectionResult> partyElectionResultsList = partyElectionResultDAO.getByElectionAndParty(electionId, partyId);
		 if(partyElectionResultsList != null && partyElectionResultsList.size() > 0){
		 partyElectionResult = new PartyElectionResult();
		 for(PartyElectionResult partyElecResult:partyElectionResultsList){
			 partyElectionResult = partyElecResult;
		 }
		 }
		}
	return partyElectionResult;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IStaticDataService#savePartyElectionResultForAPartyForAElection(java.lang.Long, java.lang.Long)
	 * This method saves electionResults for a party into party_election_result table
	 *Input for this method is election_id and party_id
	 */
	public PartyElectionResult savePartyElectionResultForAPartyForAElection(Long electionId,Long partyId){
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
		try{
		 if(electionId != null && partyId != null){
			nominations = nominationDAO.findByElectionIdAndPartyId(electionId, partyId);
			completeValidVotes = getCompleteValidVotes(electionId);
			election = electionDAO.get(electionId);
			party = partyDAO.get(partyId);
			if(nominations != null && nominations.size() > 0 && election != null && party != null){
				for(Nomination nominationForParty:nominations){
					if(nominationForParty.getParty().getPartyId().equals(partyId)){
						Long candidRank = nominationForParty.getCandidateResult().getRank();
						Double votesEarned = nominationForParty.getCandidateResult().getVotesEarned();
						Double validVotes = nominationForParty.getConstituencyElection().getConstituencyElectionResult().getValidVotes();
						totalVotesEarned+=votesEarned;
						totalValidVotes+=validVotes;
						
						totalConstiParticipated++;
						if(candidRank.equals(new Long(1)))
						totalSeatsWon++;
						else if(candidRank.equals(new Long(2)))
						totalSecondPositions++;
						else if(candidRank.equals(new Long(3)))
						totalThirdPositions++;	
						else if(candidRank.equals(new Long(4)))
						totalFourthPositions++;
						else if(candidRank > new Long(4))
						totalNthPositions++;
					}
				}
				totalVotesPercentage = new BigDecimal((totalVotesEarned*100)/totalValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				completeVotesPercent = new BigDecimal((totalVotesEarned*100)/completeValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				
				partyElectionResult = savePartyElectionResult(election,party,totalSeatsWon,totalSecondPositions,totalThirdPositions,totalFourthPositions,totalNthPositions,totalConstiParticipated,totalVotesPercentage,completeVotesPercent);
			}
		 }		
		}catch(Exception ex){
			log.debug("Exception raised ::" + ex);
		}
		
	return partyElectionResult;
	}
	
	@SuppressWarnings("unchecked")
	public Long getCompleteValidVotes(Long electionId) throws Exception{
		Long completeValidVotes = new Long(0);
		List list = constituencyElectionDAO.findTotalValidVotesForAnElectionForAState(electionId);
		if(list != null){
		Object params = (Object)list.get(0);
		Double validVotes = (Double)params;
		completeValidVotes = validVotes.longValue();
		}
	return completeValidVotes;
	}
	
	public PartyElectionResult savePartyElectionResult(Election election,Party party,Long totalSeatsWon,Long secPos,Long thirdPos,Long fourthPos,Long nthPos,Long totConstiParticipated,Double totalVotesPercentage,Double completeVotesPercent) throws Exception{
		PartyElectionResult partyElectionResult = null;
		java.util.Date updatedDate = new java.util.Date();
		String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String strDateNew = sdf.format(updatedDate) ;
		updatedDate = sdf.parse(strDateNew);
		if(election != null && party != null){
			partyElectionResult = new PartyElectionResult();
			partyElectionResult.setElection(election);
			partyElectionResult.setParty(party);
			partyElectionResult.setTotalSeatsWon(totalSeatsWon.toString());
			partyElectionResult.setSecondPosWon(secPos.toString());
			partyElectionResult.setThirdPosWon(thirdPos.toString());
			partyElectionResult.setFourthPosWon(fourthPos.toString());
			partyElectionResult.setNthPosWon(nthPos.toString());
			partyElectionResult.setTotalConstiParticipated(totConstiParticipated.toString());
			partyElectionResult.setVotesPercentage(totalVotesPercentage.toString());
			partyElectionResult.setCompleteVotesPercent(completeVotesPercent.toString());
			partyElectionResult.setLastUpdated(updatedDate);
			partyElectionResult = partyElectionResultDAO.save(partyElectionResult);
		}
		
	return partyElectionResult;
	}
	
	public PartyElectionDistrictResult getPartyElectionResultsForAPartyDistrictLevel(Long electionId,Long partyId,Long stateId,Long districtId){
		PartyElectionDistrictResult partyElectionDistrictResult = null;
		if(electionId != null && partyId != null && stateId != null && districtId != null){
			List<PartyElectionDistrictResult> partyElecDistrictResultList = partyElectionDistrictResultDAO.getByPartyIdElectionIdAndDistrict(electionId, partyId, stateId, districtId);
			if(partyElecDistrictResultList != null && partyElecDistrictResultList.size() > 0){
				partyElectionDistrictResult = new PartyElectionDistrictResult();
				for(PartyElectionDistrictResult resultList:partyElecDistrictResultList)
					partyElectionDistrictResult = resultList;
			}
		}
	return partyElectionDistrictResult;
	}
	
	@SuppressWarnings("unchecked")
	public Long getCompleteValidVotesForADistrict(Long electionId,Long districtId) throws Exception{
		Long completeValidVotes = new Long(0);
		List list = constituencyElectionDAO.findTotalValidVotesForAnElectionForAStateAndDistrict(electionId,districtId);
		if(list != null){
		Object params = (Object)list.get(0);
		Double validVotes = (Double)params;
		completeValidVotes = validVotes.longValue();
		}
	return completeValidVotes;
	}
	
	public PartyElectionDistrictResult savePartyElectionResultForAPartyForAElectionDistrictLevel(Long electionId,Long partyId,Long stateId,Long districtId){
		PartyElectionDistrictResult partyElectionDistrictResult = null;
		List<Nomination> nominations = null;
		Election election = null;
		Party party = null;
		State state = null;
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
		
		try{
			if(electionId != null && partyId != null && stateId != null && districtId != null){
				//nominations = nominationDAO.findByElectionIdAndPartyId(electionId, partyId);
				nominations = nominationDAO.findByElectionIdAndPartyIdStateIdAndDistrictId(electionId, partyId,stateId,districtId);
				election = electionDAO.get(electionId);
				party = partyDAO.get(partyId);
				state = stateDAO.get(stateId);
				district = districtDAO.get(districtId);
				
				completeValidVotes = getCompleteValidVotesForADistrict(electionId,districtId);
				if(nominations != null && nominations.size() > 0 && election != null && party != null && state != null && district != null){
					for(Nomination nominationForParty:nominations){
						if(nominationForParty.getParty().getPartyId().equals(partyId)){
							Long candidRank = nominationForParty.getCandidateResult().getRank();
							Double votesEarned = nominationForParty.getCandidateResult().getVotesEarned();
							Double validVotes = nominationForParty.getConstituencyElection().getConstituencyElectionResult().getValidVotes();
							totalVotesEarned+=votesEarned;
							totalValidVotes+=validVotes;
							
							totalConstiParticipated++;
							if(candidRank.equals(new Long(1)))
							totalSeatsWon++;
							else if(candidRank.equals(new Long(2)))
							totalSecondPositions++;
							else if(candidRank.equals(new Long(3)))
							totalThirdPositions++;	
							else if(candidRank.equals(new Long(4)))
							totalFourthPositions++;
							else if(candidRank > new Long(4))
							totalNthPositions++;
						}
					}
					totalVotesPercentage = new BigDecimal((totalVotesEarned*100)/totalValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					completeVotesPercent = new BigDecimal((totalVotesEarned*100)/completeValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					partyElectionDistrictResult = savePartyElectionDistrictResult(election,party,state,district,totalSeatsWon,totalSecondPositions,totalThirdPositions,totalFourthPositions,totalNthPositions,totalConstiParticipated,totalVotesPercentage,completeVotesPercent);
				}	
			}
		}catch(Exception ex){
			log.debug("Exception raised ::" + ex);
		}
	return partyElectionDistrictResult;
	}
	
	public PartyElectionDistrictResult savePartyElectionDistrictResult(Election election,Party party,State state,District district,Long totalSeatsWon,Long secPos,Long thirdPos,Long fourthPos,Long nthPos,Long totConstiParticipated,Double totalVotesPercentage,Double completeVotesPercent) throws Exception{
		PartyElectionDistrictResult partyElectionDistrictResult = null;
		java.util.Date updatedDate = new java.util.Date();
		String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String strDateNew = sdf.format(updatedDate) ;
		updatedDate = sdf.parse(strDateNew);
		if(election != null && party != null){
			partyElectionDistrictResult = new PartyElectionDistrictResult();
			partyElectionDistrictResult.setElection(election);
			partyElectionDistrictResult.setParty(party);
			partyElectionDistrictResult.setState(state);
			partyElectionDistrictResult.setDistrict(district);
			partyElectionDistrictResult.setTotalSeatsWon(totalSeatsWon.toString());
			partyElectionDistrictResult.setSecondPosWon(secPos.toString());
			partyElectionDistrictResult.setThirdPosWon(thirdPos.toString());
			partyElectionDistrictResult.setFourthPosWon(fourthPos.toString());
			partyElectionDistrictResult.setNthPosWon(nthPos.toString());
			partyElectionDistrictResult.setTotalConstiParticipated(totConstiParticipated.toString());
			partyElectionDistrictResult.setVotesPercentage(totalVotesPercentage.toString());
			partyElectionDistrictResult.setCompleteVotesPercent(completeVotesPercent.toString());
			partyElectionDistrictResult.setLastUpdated(updatedDate);
			partyElectionDistrictResult = partyElectionDistrictResultDAO.save(partyElectionDistrictResult);
		}
		
	return partyElectionDistrictResult;
	}

	
	public List<DistrictWisePartyResultVO> getDistrictWisePartyElectionResults(String electionYear, Long electionType,Long electionId,Long partyId,Boolean hasAlliances){
		List<DistrictWisePartyResultVO> districtWisePartyResultVOList = null;
		Map<Long,List<PartyResultVO>> districtWiseResultsMap = new HashMap<Long,List<PartyResultVO>>();
	
		log.debug("Entered Into getDistrictWisePartyElectionResults Method .....");
		List<Party> alliancParties = null;
		if(hasAlliances){
			alliancParties = getAllianceParties(electionYear,electionType,partyId);
			log.debug("Has Alliances .....");
			if(alliancParties != null){
				districtWiseResultsMap = getDistrictWisePartyElectionResultWithAllianc(electionYear,electionId,partyId,alliancParties);
				if(districtWiseResultsMap != null)
				districtWisePartyResultVOList = getResultsFromMap(districtWiseResultsMap,electionId);
			}
		}
		if(!hasAlliances || alliancParties == null){
			log.debug("Has No Alliances .....");
			districtWiseResultsMap = getDistrictWisePartyElectionResultWithoutAllianc(electionYear,electionId,partyId);
			if(districtWiseResultsMap != null)
			districtWisePartyResultVOList = getResultsFromMap(districtWiseResultsMap,electionId);
		}
		
	return districtWisePartyResultVOList;
	}
	
	public Map<Long,List<PartyResultVO>> getDistrictWisePartyElectionResultWithoutAllianc(String electionYear,Long electionId,Long partyId){
		
		List<ConstituencyElection> constiElections = null;
		Map<Long,List<PartyResultVO>> districtWiseResultsMap = null;
		
		log.debug("Entered Into getDistrictWisePartyElectionResultWithoutAllianc Method .....");
		constiElections = nominationDAO.findConstituencyElectionByElectionIdAndPartyId(electionId, partyId);
		if(constiElections != null){
			List<PartyResultVO> partyResultsList = null;
			districtWiseResultsMap = new HashMap<Long,List<PartyResultVO>>();
			for(ConstituencyElection constiElecResults:constiElections){
				PartyResultVO partyResultVO = null;
				Long districtId = constiElecResults.getConstituency().getDistrict().getDistrictId();
				
				if(districtWiseResultsMap.isEmpty() || !districtWiseResultsMap.containsKey(districtId))
				partyResultsList = new ArrayList<PartyResultVO>();
				else
				partyResultsList = districtWiseResultsMap.get(districtId);
				
				partyResultVO = getCandidateResultDetails(constiElecResults,partyId);
				partyResultsList.add(partyResultVO);
				
				districtWiseResultsMap.put(districtId, partyResultsList);
			}
			
		}
		
	return districtWiseResultsMap;
	}
	
	public Map<Long,List<PartyResultVO>> getDistrictWisePartyElectionResultWithAllianc(String electionYear,Long electionId,Long partyId,List<Party> alliancPartys){
		List<ConstituencyElection> constiElections = null;
		Map<Long,List<PartyResultVO>> districtWiseResultsMap = null;
		List<Long> partyIds = new ArrayList<Long>();
		
		log.debug("Entered Into getDistrictWisePartyElectionResultWithAllianc Method .....");
		for(Party party:alliancPartys)
			partyIds.add(party.getPartyId());
				
		constiElections = nominationDAO.findConstituencyElectionByElectionIdAndPartys(electionId, partyIds);
		
		log.debug("ConstiElections With Alliance size ::" + constiElections.size());
		if(constiElections != null){
			List<PartyResultVO> partyResultsList = null;
			List<Long> constiIds = new ArrayList<Long>();
			districtWiseResultsMap = new HashMap<Long,List<PartyResultVO>>();
			for(ConstituencyElection constiElecResults:constiElections){
				
				if(constiIds.contains(constiElecResults.getConstituency().getConstituencyId()))
				continue;
				PartyResultVO partyResultVO = null;
				constiIds.add(constiElecResults.getConstituency().getConstituencyId());
				Long districtId = constiElecResults.getConstituency().getDistrict().getDistrictId();
				if(districtWiseResultsMap.isEmpty() || !districtWiseResultsMap.containsKey(districtId))
				partyResultsList = new ArrayList<PartyResultVO>();
				else
				partyResultsList = districtWiseResultsMap.get(districtId);
				
				log.debug("PartyResultsList With Alliance(for district" +districtId +") size ::" + partyResultsList.size());
				partyResultVO = getCandidateResultDetailsWithAllianc(constiElecResults,partyId,alliancPartys,partyIds);
				partyResultsList.add(partyResultVO);
				
				districtWiseResultsMap.put(districtId, partyResultsList);
			}
			
		}
		
	return districtWiseResultsMap;
	}
	
	public PartyResultVO getCandidateResultDetails(ConstituencyElection constiElecResults,Long partyId){
		PartyResultVO partyResultVO = new PartyResultVO();
		CandidateOppositionVO oppositionCandidate = new CandidateOppositionVO();
		Set<Nomination> nominations = constiElecResults.getNominations();
		log.debug("Entered Into getCandidateResultDetails Method .....");
		if(nominations != null){
			for(Nomination nominatn:nominations){
				if(nominatn.getParty().getPartyId().equals(partyId)){
					partyResultVO = getSelectedNominationResults(nominatn,partyId);
					Long rankOfCand = nominatn.getCandidateResult().getRank();
					oppositionCandidate = getOppCandidateResultDetails(rankOfCand,nominations,partyId);
					partyResultVO.setOppositionCandidates(oppositionCandidate);
					break;
				}
			}
		}
		
		return partyResultVO;
	}
	
	public PartyResultVO getSelectedNominationResults(Nomination nominatn,Long partyId){
		PartyResultVO partyResultVO = new PartyResultVO();
		partyResultVO.setCandidateId(nominatn.getCandidate().getCandidateId());
		partyResultVO.setCandidateName(nominatn.getCandidate().getLastname().toLowerCase());
		partyResultVO.setConstituencyId(nominatn.getConstituencyElection().getConstituency().getConstituencyId());
		partyResultVO.setConstituencyName(nominatn.getConstituencyElection().getConstituency().getName().toUpperCase());
		partyResultVO.setElectors(nominatn.getConstituencyElection().getConstituencyElectionResult().getTotalVotes().longValue());
		partyResultVO.setPartyId(partyId);
		partyResultVO.setPartyName(nominatn.getParty().getShortName().toUpperCase());
		partyResultVO.setVotesEarned(nominatn.getCandidateResult().getVotesEarned().longValue());
		partyResultVO.setValidVotes(nominatn.getConstituencyElection().getConstituencyElectionResult().getValidVotes().longValue());
		partyResultVO.setVotesPercent(nominatn.getCandidateResult().getVotesPercengate());
		partyResultVO.setRank(nominatn.getCandidateResult().getRank());
		
		return partyResultVO;
	}
	
	public PartyResultVO getCandidateResultDetailsWithAllianc(ConstituencyElection constiElecResults,Long partyId,List<Party> alliancPartys,List<Long> alliancPartyIds){
		PartyResultVO partyResultVO = new PartyResultVO();
		CandidateOppositionVO oppositionCandidate = new CandidateOppositionVO();
		Set<Nomination> nominations = constiElecResults.getNominations();
		log.debug("Entered Into getCandidateResultDetailsWithAllianc Method .....");
		if(nominations != null){
			Boolean flag = false;
			Nomination selectdNominatn = null;
			for(Nomination nominatn:nominations){
				if(nominatn.getParty().getPartyId().equals(partyId)){
					partyResultVO = getSelectedNominationResults(nominatn,partyId);
					Long rankOfCand = nominatn.getCandidateResult().getRank();
					oppositionCandidate = getOppCandidateResultDetails(rankOfCand,nominations,partyId);
					partyResultVO.setOppositionCandidates(oppositionCandidate);
					break;
				}
				else if(alliancPartyIds.contains(nominatn.getParty().getPartyId())){
					flag = true;
					if(selectdNominatn != null){
					if(nominatn.getCandidateResult().getRank() > selectdNominatn.getCandidateResult().getRank())
					selectdNominatn = nominatn;
					}
					else
					selectdNominatn = nominatn;
				}
			}
			if(flag == true && selectdNominatn != null){
				partyResultVO = getSelectedNominationResults(selectdNominatn,selectdNominatn.getParty().getPartyId());
				Long rankOfCand = selectdNominatn.getCandidateResult().getRank();
				oppositionCandidate = getOppCandidateResultDetails(rankOfCand,nominations,partyId);
				partyResultVO.setOppositionCandidates(oppositionCandidate);
			}
		}
	return partyResultVO;	
	}
	
	public CandidateOppositionVO getOppCandidateResultDetails(Long rank,Set<Nomination> nominatns,Long partyId){
		log.debug("Entered Into getOppCandidateResultDetails Method .....");
		CandidateOppositionVO oppositionCandidate = new CandidateOppositionVO();
		for(Nomination nomination:nominatns){
			if(rank.equals(new Long(1)) && nomination.getCandidateResult().getRank().equals(new Long(2))){
				oppositionCandidate.setCandidateId(nomination.getCandidate().getCandidateId());
				oppositionCandidate.setCandidateName(nomination.getCandidate().getLastname().toLowerCase());
				oppositionCandidate.setPartyId(nomination.getParty().getPartyId());
				oppositionCandidate.setPartyName(nomination.getParty().getShortName().toUpperCase());
				oppositionCandidate.setVotesEarned(nomination.getCandidateResult().getVotesEarned().toString());
				oppositionCandidate.setVotesPercentage(nomination.getCandidateResult().getVotesPercengate());
				oppositionCandidate.setRank(nomination.getCandidateResult().getRank());
				break;
			}
			else if(rank > new Long(1) && nomination.getCandidateResult().getRank().equals(new Long(1))){
				oppositionCandidate.setCandidateId(nomination.getCandidate().getCandidateId());
				oppositionCandidate.setCandidateName(nomination.getCandidate().getLastname().toLowerCase());
				oppositionCandidate.setPartyId(nomination.getParty().getPartyId());
				oppositionCandidate.setPartyName(nomination.getParty().getShortName().toUpperCase());
				oppositionCandidate.setVotesEarned(nomination.getCandidateResult().getVotesEarned().toString());
				oppositionCandidate.setVotesPercentage(nomination.getCandidateResult().getVotesPercengate());
				oppositionCandidate.setRank(nomination.getCandidateResult().getRank());
				break;
			}
		}
		
		return oppositionCandidate;
	}
	
	@SuppressWarnings("unchecked")
	public List<DistrictWisePartyResultVO> getResultsFromMap(Map<Long,List<PartyResultVO>> districtWiseResultsMap,Long electionId){
		
		log.debug("Entered Into getResultsFromMap Method .....");
		List<DistrictWisePartyResultVO> districtWiseResults = null;
		if(!districtWiseResultsMap.isEmpty()){
			districtWiseResults = new ArrayList<DistrictWisePartyResultVO>();
			Set entries = districtWiseResultsMap.entrySet();
			Iterator iterator = entries.iterator();
			while (iterator.hasNext()){
			DistrictWisePartyResultVO resultForADist = new DistrictWisePartyResultVO();
			Map.Entry entry = (Map.Entry)iterator.next();
			List<PartyResultVO> partyResultsVO = (List<PartyResultVO>)entry.getValue();
			Long distId = (Long)entry.getKey();
			resultForADist = getDistrictCompleteDetails(distId,partyResultsVO,electionId);
			districtWiseResults.add(resultForADist);
			}
		}
		return districtWiseResults;
	}
	
	public DistrictWisePartyResultVO getDistrictCompleteDetails(Long districtId,List<PartyResultVO> partyResultsVO,Long electionId){
		
		log.debug("Entered Into getDistrictCompleteDetails Method .....");
		
		DistrictWisePartyResultVO resultForADist = new DistrictWisePartyResultVO();
		District district = districtDAO.get(districtId);
		if(district == null)
			return null;
		
		int constiCount = 0;
		int seatsWon = 0;
		Long votesEarned = new Long(0);
		Long validVotes = new Long(0);
		List<Constituency> constituencys = constituencyElectionDAO.findConstituencyByElectionAndDistrict(electionId, districtId);
		if(constituencys != null && constituencys.size() > 0)
		constiCount = constituencys.size();
		
		resultForADist.setDistrictId(district.getDistrictId());
		resultForADist.setDistrictName(district.getDistrictName());
		resultForADist.setStateId(district.getState().getStateId());
		resultForADist.setStateName(district.getState().getStateName());
		resultForADist.setTotalConstituencies(new Long(partyResultsVO.size()));
		resultForADist.setConstiCount(new Long(constiCount));
		resultForADist.setConstiParticipated(new Long(partyResultsVO.size()));
		resultForADist.setPartyElectionResultsList(partyResultsVO);
		
		try{
		for(PartyResultVO results:partyResultsVO){
			if(results.getRank().equals(new Long(1))){
			seatsWon++;
			}
			votesEarned+=results.getVotesEarned();
			validVotes+=results.getValidVotes();
		}
		Double votesPercent = new BigDecimal((votesEarned.doubleValue()/validVotes.doubleValue())*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		resultForADist.setSeatsWon(new Long(seatsWon));
		resultForADist.setVotesPercent(votesPercent);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resultForADist;
	}

	
	public List<SelectOptionVO> getHamletsForTownship(Long townshipId){
		List<SelectOptionVO> hamlets = new ArrayList<SelectOptionVO>();
		List<Hamlet> hamletModels = hamletDAO.findByTownshipId(townshipId);
		SelectOptionVO hamlet = null;
		for(Hamlet hamletModel:hamletModels){
			hamlet = new SelectOptionVO(hamletModel.getHamletId(), hamletModel.getHamletName());
			hamlets.add(hamlet);
		}
		return hamlets;
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyBoothInfoVO> getBoothPartNosForMandalAndElection(Long tehsilId, String electionYear){
		List<ConstituencyBoothInfoVO> constituencyBoothsList = new ArrayList<ConstituencyBoothInfoVO>();
		List boothsInfo = boothConstituencyElectionDAO.findPartNoConstituencyNameForTehsil(tehsilId, IConstants.ASSEMBLY_ELECTION_TYPE, electionYear);
		for(int i=0; i < boothsInfo.size(); i++){
			Object[] values = (Object[]) boothsInfo.get(i);
			Long boothConstiElecId = (Long)values[0];
			String partNo = (String)values[3];
			String villagesCovered = (String)values[2];
			String constituencyName = (String)values[1];
			constituencyBoothsList.add(new ConstituencyBoothInfoVO(boothConstiElecId, partNo, constituencyName, villagesCovered));
		}
		return constituencyBoothsList;
	}
	
	@SuppressWarnings("unchecked")
	public CandidateDetailsVO getCompleteElectionResultsForAConstituency(Long constituencyId,Long electionId,Long partyId){
		CandidateDetailsVO candidateResults = null;
		List<CandidateOppositionVO> oppCandidates = null;
		if(constituencyId != null && electionId != null && partyId != null){
		candidateResults = new CandidateDetailsVO();
		oppCandidates = new ArrayList<CandidateOppositionVO>();
		
		List candidateList = nominationDAO.findElectionResultsForACandidateForAnElectionInAConstituency(constituencyId,electionId,partyId);
		if(candidateList != null){
		candidateResults = getCandidateResultsVO(candidateList);
		}
		List oppCandidateList = nominationDAO.findElectionResultsForAnElectionInAConstituencyWithoutSelectedParty(constituencyId,electionId,partyId);
		if(oppCandidateList != null){
		oppCandidates = getOppositionCandidateResults(oppCandidateList);
		candidateResults.setOppositionCandidates(oppCandidates);
		}
		}
		
	return candidateResults;
	}
	
	@SuppressWarnings("unchecked")
	public CandidateDetailsVO getCandidateResultsVO(List candidateList){
		CandidateDetailsVO candidateResults = null;
        if(candidateList != null){
        candidateResults = new CandidateDetailsVO();
        
        Object[] params = (Object[])candidateList.get(0);
		
		String candidateName = (String)params[0];
		Long candId = (Long)params[1];
		String partyName = (String)params[2];
		String constiName = (String)params[3];
		String stateName = (String)params[4];
		String districtName = (String)params[5];
		String elecYear = (String)params[6];
		String elecType = (String)params[7];
		Double votesEarned = (Double)params[8];
		String votesPercent = (String)params[9];
		Long rank = (Long)params[10];
		
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
	
	public List<CandidateOppositionVO> getOppositionCandidateResults(List oppCandidatesList){
		List<CandidateOppositionVO> oppCandidates = null;
		if(oppCandidatesList != null){
			oppCandidates = new ArrayList<CandidateOppositionVO>();
			for(int i=0;i<oppCandidatesList.size();i++){		
				Object[] params = (Object[])oppCandidatesList.get(i);
				
				String candidateName = (String)params[0];
				Long candId = (Long)params[1];
				String partyName = (String)params[2];
				String constiName = (String)params[3];
				String stateName = (String)params[4];
				String districtName = (String)params[5];
				String elecYear = (String)params[6];
				String elecType = (String)params[7];
				Double votesEarned = (Double)params[8];
				String votesPercent = (String)params[9];
				Long rank = (Long)params[10];
				
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
	
	public Set<SelectOptionVO> getAllPartiesParticipatedInMandal(Long tehsilId){
		Set<SelectOptionVO> partiesAndIdsInMandal = new HashSet<SelectOptionVO>();
		List partiesAndIds = nominationDAO.getAllPartiesOfElectionTypeInMandal(tehsilId);
		for(int i=0; i<partiesAndIds.size(); i++){
			Object[] values = (Object[])partiesAndIds.get(i);
			partiesAndIdsInMandal.add(new SelectOptionVO((Long)values[0], values[1].toString()));
		}
		
		List parties = candidateBoothResultDAO.getAllACPCPartiesInMandal(tehsilId);
		for(int i=0; i<parties.size(); i++){
			Object[] values = (Object[])parties.get(i);
			partiesAndIdsInMandal.add(new SelectOptionVO((Long)values[0], values[1].toString()));
		}
		
		return partiesAndIdsInMandal;
	}
	
	public Set<SelectOptionVO> getAllPartiesParticipatedInRevenueVillage(Long townshipId){
		Set<SelectOptionVO> partiesAndIdsInVillage = new HashSet<SelectOptionVO>();
		List parties = candidateBoothResultDAO.getAllACPCPartiesInRevenueVillage(townshipId);
		for(int i=0; i<parties.size(); i++){
			Object[] values = (Object[])parties.get(i);
			partiesAndIdsInVillage.add(new SelectOptionVO((Long)values[0], values[1].toString()));
		}
		return partiesAndIdsInVillage;
	}

	/*
	 * This method populates all the candidate details for all the election years based on consitutuencyId.
	 */
	@SuppressWarnings("unchecked")
	public CandidateDetailsVO getElectionResultsForAConstituencyForAllYears(Long constituencyId) {
		CandidateDetailsVO candidateResults =null;		
		try{
			candidateResults = new CandidateDetailsVO();
			List result = nominationDAO.findAllCandidatesForAnElectionByElectionYear(constituencyId);
			candidateResults = populateElectionDataForAllYears(result);
			return candidateResults;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			candidateResults = new CandidateDetailsVO();
			candidateResults.setDataAvailabilityFlag(0L);
			return candidateResults;
		}
	}

	/*
	 * This method populates all the candidate details for all the election years based on districtId.
	 */
	@SuppressWarnings("unchecked")
	public CandidateDetailsVO getElectionResultsForADistrictForAllYears(Long districtId) {
		CandidateDetailsVO candidateResults =null;		
		try{
			candidateResults = new CandidateDetailsVO();
			List result = nominationDAO.findAllCandidatesForAnElectionByElectionYearByDistrictId(districtId,IConstants.ASSEMBLY_ELECTION_TYPE);
			candidateResults = populateElectionDataForAllYears(result);
			return candidateResults;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			candidateResults = new CandidateDetailsVO();
			candidateResults.setDataAvailabilityFlag(0L);
			return candidateResults;
		}	
	}

	/*
	 *This method is used by other like getElectionResultsForADistrictForAllYears and getElectionResultsForAConstituencyForAllYears 
	 *to set the data in to the CandidateDetailsVO.
	 */
	@SuppressWarnings("unchecked")
	public CandidateDetailsVO populateElectionDataForAllYears(List result) {
		List<CandidateDetailsVO> candidateDetails = new ArrayList<CandidateDetailsVO>(0);
		CandidateDetailsVO candidateResults = null;
		try{
			candidateResults = new CandidateDetailsVO();
			for(int i=0;i<result.size();i++){
				CandidateDetailsVO candidateResultsVo = new CandidateDetailsVO();
				Object[] parms = (Object[])result.get(i);
				candidateResultsVo.setCandidateName(parms[0].toString());
				candidateResultsVo.setConstituencyName(parms[1].toString());
				candidateResultsVo.setVotesEarned(parms[2].toString());
				if(Long.parseLong(parms[3].toString())==1l)
					candidateResultsVo.setResult("Win");
				else
					candidateResultsVo.setResult("Loose");
				candidateResultsVo.setPartyName(parms[4].toString());
				candidateResultsVo.setElectionYear(parms[5].toString());
				candidateDetails.add(candidateResultsVo);
			}
				candidateResults.setCandidateDetails(candidateDetails);	
				return candidateResults;				
			}catch(Exception e){
				log.error("Exception raised please check the log for details"+e);
				e.printStackTrace();
				candidateResults = new CandidateDetailsVO();
				candidateResults.setDataAvailabilityFlag(0L);
				return candidateResults;
			}
		
		}	

	/*
	 * This method retrieves all the latest constituences for a particular election year.
	 */
	public CandidateDetailsVO getLatestConstituenciesForAssemblyAndParliamentForAllElectionYears(Long electionType,Long stateId){
		Long electionID=0l;
		CandidateDetailsVO constituencies =null;
		List<SelectOptionVO> selectOptionVO = new ArrayList<SelectOptionVO>(0);
		SelectOptionVO selectOptionvo = new SelectOptionVO();
		try{
			constituencies = new CandidateDetailsVO();
			selectOptionvo.setId(0l);
			selectOptionvo.setName("Select Constituency");
			List result = electionDAO.findElectionIdAndYear(electionType,stateId);
			selectOptionVO.add(0, selectOptionvo);
			for(int i=0;i<result.size();i++){
				Object[] parms = (Object[])result.get(i);
				electionID = Long.parseLong(parms[0].toString());
			}
			List list = constituencyElectionDAO.findTotalAssemblyConstituencies(electionID,stateId);
			for(int i=0;i<list.size();i++){
				Object[] parms = (Object[])list.get(i);
				SelectOptionVO selectOption = new SelectOptionVO();
				selectOption.setId(Long.parseLong(parms[0].toString()));
				selectOption.setName(parms[1].toString());
				selectOptionVO.add(selectOption);
			}
			constituencies.setLatestConstituencies(selectOptionVO);
			return constituencies;		
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			constituencies= new CandidateDetailsVO();
			constituencies.setDataAvailabilityFlag(0L);
			return constituencies;
		}
	}
	
	/*
	 * This method retrieves all the States present in that country.
	 */
	public CandidateDetailsVO getAllStatesInCountry(){
		List<SelectOptionVO> selectOptionVo;
		CandidateDetailsVO states =null;
		try{	
			states= new CandidateDetailsVO();
			selectOptionVo = new ArrayList<SelectOptionVO>(0);
			List<State> result = stateDAO.findByCountryId(1l);
			SelectOptionVO selectOption = new SelectOptionVO();
			selectOption.setId(0l);
			selectOption.setName("Select State");
			selectOptionVo.add(0, selectOption);
			for(State list:result){
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(list.getStateId());
				selectOptionVO.setName(list.getStateName());
				selectOptionVo.add(selectOptionVO);
			}
			states.setGetAllStates(selectOptionVo);
			return states;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			states= new CandidateDetailsVO();
			states.setDataAvailabilityFlag(0L);
			return states;
		}			
	}
	
	/*
	 * This method retrieves all the Constituencies based on election type and state id.
	 */
	public List<SelectOptionVO> getConstituenciesByElectionTypeAndStateId(Long electionTypeId , Long stateID)
	{
		List<SelectOptionVO> constituenciesList = new ArrayList<SelectOptionVO>();
		
		List constiList = constituencyDAO.getConstituenciesByElectionTypeAndStateId(electionTypeId, stateID);
		if(constiList!=null && constiList.size()>0)
		{
			for(int i=0;i<constiList.size();i++)
			{
				Object[] obj = (Object[])constiList.get(i);
				SelectOptionVO constituencyData = new SelectOptionVO();
				constituencyData.setId((Long) obj[0]);
				constituencyData.setName((String) obj[1]);
				
				constituenciesList.add(constituencyData);
			}
		}
		
		return constituenciesList;
		
	}

	/**
	 * @param districtId
	 * @return List<CandidateDetailsVO>
	 * 
	 * This method retrieves all the MP's present in the district for the latest election year.
	 */
	public CandidateDetailsVO getAllParliamentWinningCandidatesForADistrict(Long districtId){
		CandidateDetailsVO candidateVo= new CandidateDetailsVO();
		List<CandidateDetailsVO> candidateDetailsVo = new ArrayList<CandidateDetailsVO>(0);			
	try{
		log.info("Making nominationDAO.getParliamentCandidateNPartyInfoForADistrict() DAO call");
		Iterator iterator = parliamentConstituencies.iterator();
		while(iterator.hasNext()){
			List list = nominationDAO.getParliamentCandidateNPartyInfo(Long.parseLong(iterator.next().toString()),IConstants.PARLIAMENT_ELECTION_TYPE,1L);	
			for(int i=0;i<list.size();i++){
				 CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();							
				 Object[] parms = (Object[])list.get(i);
				 candidateDetailsVO.setConstituencyId(Long.parseLong(parms[0].toString()));
				 candidateDetailsVO.setConstituencyName(parms[1].toString().toUpperCase());
				 candidateDetailsVO.setCandidateId(Long.parseLong(parms[2].toString()));
				 candidateDetailsVO.setCandidateName(parms[5].toString().toUpperCase());
				 if(parms[10] != null){
					 candidateDetailsVO.setPartyFlag(parms[10].toString()); 
				 }		
				 else{
					 candidateDetailsVO.setPartyFlag("no_Image.png");
					}
				 candidateDetailsVO.setPartyName(parms[7].toString()); 
				 candidateDetailsVo.add(candidateDetailsVO);
			}
		}		
		
		candidateVo.setCandidateDetails(candidateDetailsVo);
			 parliamentConstituencies.clear();				
			return candidateVo;
			}catch(Exception e){
				log.error("Exception raised please check the log for details"+e);
				e.printStackTrace();
				return null;
			}
	}
	
	/*
	 * This method returns all the election years for the given electionType.
	 */
	public List<SelectOptionVO> getAllElectionYearsForATeshil(Long electionType){
		List<SelectOptionVO> SelectOptionVO = new ArrayList<SelectOptionVO>(0);
		Long stateId = 1l;
		try{
		/*	SelectOptionVO selectOption = new SelectOptionVO();
			selectOption.setId(0l);
			selectOption.setName("Select");
			SelectOptionVO.add(selectOption);*/
			List result = electionDAO.findElectionAndYearForElectionTypeAndState(electionType,stateId);
			for(int i=result.size()-1;i>=0;i--){
				SelectOptionVO selectOptionVo = new SelectOptionVO();
				Object[] parms = (Object[])result.get(i);
				selectOptionVo.setId(Long.parseLong(parms[1].toString()));
				selectOptionVo.setName(parms[1].toString());
				SelectOptionVO.add(selectOptionVo);
			}
			return SelectOptionVO;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * This method generates a report to find out the results for all the parties participated in that zptc/mptc.
	 */
	
	public List<TeshilPartyInfoVO> getMandalWisePartyReport(String electionType,String electionYear,Long districtId){
		BigDecimal percentage = new BigDecimal(0.0);
		List<TeshilPartyInfoVO> teshilPartyInfoVO = new ArrayList<TeshilPartyInfoVO>(0);
		Long participatedSeats,seatsWon;
		Float totalVotes=null;
		Long winningCandidateRank=1l;
		Map<String,Long> winningSeats =  new HashMap<String,Long>(0);
		try{
			if(log.isDebugEnabled())
				log.info("Making nominationDAO.getPartysWinningCandidateInfoForAParticularElectionYear() DAO Call");
			List seatWon = nominationDAO.getPartysWinningCandidateInfoForAParticularElectionYear(electionType,electionYear,winningCandidateRank,districtId);
			for(int i=0;i<seatWon.size();i++){
				Object[] parms = (Object[])seatWon.get(i);
				winningSeats.put(parms[0].toString(), Long.parseLong(parms[1].toString()));
			}
			if(log.isDebugEnabled())
				log.info("Making nominationDAO.getPartysInfoForAParticularElectionYear() DAO Call");
			List totalValidVotes = constituencyElectionDAO.getTotalValidVotesParticularElectionYear(electionType,electionYear,districtId);
			totalVotes = Float.parseFloat(totalValidVotes.get(0).toString());
			List result = nominationDAO.getPartysInfoForAParticularElectionYear(electionType,electionYear,districtId);
			for(int i=0;i<result.size();i++){
				Object[] parms = (Object[])result.get(i);
				TeshilPartyInfoVO teshilPartyInfoVo = new TeshilPartyInfoVO();
					teshilPartyInfoVo.setPartyName(parms[0].toString());
					teshilPartyInfoVo.setParticipatedSeats(Long.parseLong(parms[1].toString()));
					percentage= new BigDecimal((Float.parseFloat(parms[2].toString())/totalVotes)*100).setScale(2,BigDecimal.ROUND_HALF_UP);
					teshilPartyInfoVo.setPercentageOfVotesWonByParty(Float.parseFloat(percentage.toString()));
				if(winningSeats.get(parms[0].toString()) != null){
					teshilPartyInfoVo.setSeatsWonByParty(Long.parseLong(winningSeats.get(parms[0].toString()).toString()));
				}else{
					teshilPartyInfoVo.setSeatsWonByParty(0L);
				}					
				teshilPartyInfoVO.add(teshilPartyInfoVo);
			}			
			return teshilPartyInfoVO;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<ElectionBasicInfoVO> getAssemblyElectionsInfoForAConstituency(String presentYear,Long constituencyId){
		
		List<ElectionBasicInfoVO> electionInfo  =null;
		
		if(presentYear != null && constituencyId != null){
			electionInfo = new ArrayList<ElectionBasicInfoVO>();
			List prevElec = boothConstituencyElectionDAO.getPreviousElectionYearsDetails(presentYear, constituencyId);
			log.debug("Elections Size :" + prevElec.size());
			if(prevElec != null){
				for(int i=0;i<prevElec.size();i++){
				Object[] params = (Object[])prevElec.get(i);
				String elecYear = (String)params[0];
				Long elecId     = (Long)params[1];
				Long elecTypeId = (Long)params[2];
				
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
	public List<ElectionBasicInfoVO> getParliamentElectionsInfoForAConstituency(Long constituencyId){
		List<ElectionBasicInfoVO> electionInfo  =null;
		if(constituencyId != null){
			electionInfo = new ArrayList<ElectionBasicInfoVO>();
			List list = delimitationConstituencyAssemblyDetailsDAO.findParliamentConstituenciesForAAssemblyConstituency(new Long(232));
			for(int i=0;i<list.size();i++){
				Object[] params = (Object[])list.get(i);
				Long constiId = (Long)params[0];
				Long elecTypeId = (Long)params[1];
				String constName = (String)params[2];
				Long year = (Long)params[3];
				Long elecId = null;
				
				List election = boothConstituencyElectionDAO.getElectionIdForAElectionTypeAndYear(elecTypeId,year.toString());
				if(election != null && election.size() > 0){
					Object electnId = (Object)election.get(0);
					elecId = (Long)electnId;
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
	 * This method is retrieves all the information like candidate and his votes and details regarding state,district to which the constituency
	 * belongs for the given election year and election type(i.e.,parliament or assembly)
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public ConstituencyElectionResultsVO getAllCandidatesDetailsForConstituency(Long constituencyId,String electionYear,String electionType){
		 ConstituencyElectionResultsVO constituencyElectionResults = null;
		 List<CandidateOppositionVO> candidateOppositionVO = new ArrayList<CandidateOppositionVO>(0);
		try{
			log.info("Making constituencyDAO.getConstituencyInfoByConstituencyIdElectionYearAndElectionType() DAO call");
			if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
				List result =  constituencyDAO.getParliamentConstituencyInfoByConstituencyIdElectionYearAndElectionType(constituencyId);
				java.util.ListIterator li = result.listIterator();
				 while(li.hasNext()){
					 log.info(" Has Parliament Constituency Details ...");
					 constituencyElectionResults = new ConstituencyElectionResultsVO();
					 Object[] parms = (Object[])li.next();
					 constituencyElectionResults.setConstituencyId(Long.parseLong(parms[0].toString()));
					 constituencyElectionResults.setConstituencyName(parms[1].toString());
					 constituencyElectionResults.setElectionType(electionType);
					 constituencyElectionResults.setStateId(Long.parseLong(parms[2].toString()));
					 constituencyElectionResults.setStateName(parms[3].toString());
					 constituencyElectionResults.setElectionYear(electionYear);
				 }
			}
			else{
			 List result =  constituencyDAO.getConstituencyInfoByConstituencyIdElectionYearAndElectionType(constituencyId);
			 java.util.ListIterator li = result.listIterator();
			 while(li.hasNext()){
				 log.info(" Has Constituency Details ...");
				 constituencyElectionResults = new ConstituencyElectionResultsVO();
				 Object[] parms = (Object[])li.next();
				 constituencyElectionResults.setConstituencyId(Long.parseLong(parms[0].toString()));
				 constituencyElectionResults.setConstituencyName(parms[1].toString());
				 constituencyElectionResults.setElectionType(electionType);
				 constituencyElectionResults.setDistrictId(Long.parseLong(parms[2].toString()));
				 constituencyElectionResults.setDistrictName(parms[3].toString());
				 constituencyElectionResults.setStateId(Long.parseLong(parms[4].toString()));
				 constituencyElectionResults.setStateName(parms[5].toString());
				 constituencyElectionResults.setElectionYear(electionYear);
			 }
			}
			 log.info("Making nominationDAO.getCandidatesInfoForTheGivenConstituency() DAO call");
			 List nominationResult = nominationDAO.getCandidatesInfoForTheGivenConstituency(constituencyId,electionYear,electionType);
			 java.util.ListIterator resultIterator = nominationResult.listIterator();
			 while(resultIterator.hasNext()){
				 log.info(" Has Constituency Results ...");
				 Object[] parms = (Object[])resultIterator.next();
				 if(Long.parseLong(parms[4].toString())==1l){
					 CandidateWonVO candidateWonVO = new CandidateWonVO(); 
						 candidateWonVO.setCandidateId(Long.parseLong(parms[0].toString()));
						 String candidateName = parms[1].toString();
							if(candidateName.contains("\n")){
								candidateName = candidateName.replace("\n"," ");
								candidateWonVO.setCandidateName(candidateName);
							}else
								candidateWonVO.setCandidateName(parms[1].toString());
						 Double votesEarned = (Double)parms[2];
						 Long votesEarn = votesEarned.longValue();
						 candidateWonVO.setVotesEarned(votesEarn.toString());
						 candidateWonVO.setVotesPercentage(parms[3].toString());
						 candidateWonVO.setRank(Long.parseLong(parms[4].toString())); 
						 if(!(parms[5]==null)){
							 candidateWonVO.setPartyId(Long.parseLong(parms[5].toString())); 
						 }
						 if(!(parms[6]==null)){
							 candidateWonVO.setPartyFlag(parms[6].toString()); 
						 }
						 candidateWonVO.setPartyLongName(parms[7].toString());
						 candidateWonVO.setPartyName(parms[8].toString());
						 constituencyElectionResults.setCandidateResultsVO(candidateWonVO);
				 }
				 else{
					 CandidateOppositionVO  candidateOppositionVo = new CandidateOppositionVO();
					 candidateOppositionVo.setCandidateId(Long.parseLong(parms[0].toString()));
					 String candidateName = parms[1].toString();
						if(candidateName.contains("\n")){
							candidateName = candidateName.replace("\n"," ");
							candidateOppositionVo.setCandidateName(candidateName);
						}else
							candidateOppositionVo.setCandidateName(parms[1].toString());
					 Double votesEarned = (Double)parms[2];
					 Long votesEarn = votesEarned.longValue();
					 candidateOppositionVo.setVotesEarned(votesEarn.toString());
					 candidateOppositionVo.setVotesPercentage(parms[3].toString());
					 candidateOppositionVo.setRank(Long.parseLong(parms[4].toString())); 
					 if(!(parms[5]==null)){
						 candidateOppositionVo.setPartyId(Long.parseLong(parms[5].toString())); 
					 }
					 if(!(parms[6]==null)){
						 candidateOppositionVo.setPartyFlag(parms[6].toString()); 
					 }
					 candidateOppositionVo.setPartyLongName(parms[7].toString());
					 candidateOppositionVo.setPartyName(parms[8].toString());
					 candidateOppositionVO.add(candidateOppositionVo);
				 }
			 }
			 constituencyElectionResults.setCandidateOppositionList(candidateOppositionVO);
			 
			 if(constituencyElectionResults != null){
				 for(CandidateOppositionVO oppositionCand:constituencyElectionResults.getCandidateOppositionList()){
					 if(oppositionCand.getRank().equals(new Long(2))){
					 Double wonVotesMargin = new Double(constituencyElectionResults.getCandidateResultsVO().getVotesEarned()) - new Double(oppositionCand.getVotesEarned());
					 Double wonVotesPercentMargin = new Double(constituencyElectionResults.getCandidateResultsVO().getVotesPercentage()) - new Double(oppositionCand.getVotesPercentage());
					 Long votesMarg = wonVotesMargin.longValue();
					 constituencyElectionResults.getCandidateResultsVO().setVotesMargin(votesMarg.toString());
					 constituencyElectionResults.getCandidateResultsVO().setVotesPercentMargin(new BigDecimal(wonVotesPercentMargin).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					 }
					 
					 Double votesMargin = new Double(oppositionCand.getVotesEarned()) - new Double(constituencyElectionResults.getCandidateResultsVO().getVotesEarned());
					 Double votesPercentMargin = new Double(oppositionCand.getVotesPercentage()) - new Double(constituencyElectionResults.getCandidateResultsVO().getVotesPercentage());
					 Long votesMarg = votesMargin.longValue();
					 oppositionCand.setVotesMargin(votesMarg.toString());
					 oppositionCand.setVotesPercentMargin(new BigDecimal(votesPercentMargin.longValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 }
			 }
		return constituencyElectionResults;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	//ZPTC's And MPTC's related code.
	
	
	/*
	 * This method retrieves all the ZPTC's winner candidates present in that particular district
	 */
	public MandalAllElectionDetailsVO getAllZptcWinnerForADistrictForLatestYear(Long districtId,String electionYear) {
		List<MandalAllElectionDetailsVO> allVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(0);
		MandalAllElectionDetailsVO mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();
		Long winnerRank =1l,successorRank=2l;
		try{
			log.info("Making nominationDAO.findAllZPTCsInaDistrict DAO call");
			List successorCandidate = nominationDAO.findAllZPTCsInaDistrict(districtId,IConstants.ZPTC_ELECTION_TYPE,successorRank,electionYear);
			
			log.info("Making nominationDAO.findAllZPTCsInaDistrict DAO call");
			List winningCandidate = nominationDAO.findAllZPTCsInaDistrict(districtId,IConstants.ZPTC_ELECTION_TYPE,winnerRank,electionYear);
			
			log.info("Calling populateElectionsData() method..");
			allVotersDetails = populateElectionsData(winningCandidate,successorCandidate);			
			
			mandalAllElectionDetailsVo.setAllVotersDetails(allVotersDetails);
			
			return mandalAllElectionDetailsVo;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	/*
	 * This method retrieves all the MPTC's winner candidates present in that particular district
	 */
	public MandalAllElectionDetailsVO getAllMptcWinnerForADistrictForLatestYear(Long districtId,String electionYear) {
		List<MandalAllElectionDetailsVO> allVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(0);
		MandalAllElectionDetailsVO mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();
		Long winnerRank =1l,successorRank=2l;
		try{	
			log.info("Making nominationDAO.findAllMPTCsInaDistrict DAO call");
			List successorCandidate = nominationDAO.findAllMPTCsInaDistrict(districtId,IConstants.MPTC_ELECTION_TYPE,successorRank,electionYear);
			
			log.info("Making nominationDAO.findAllMPTCsInaDistrict DAO call");
			List winningCandidate = nominationDAO.findAllMPTCsInaDistrict(districtId,IConstants.MPTC_ELECTION_TYPE,winnerRank,electionYear);
			
			log.info("Calling populateElectionsData() method..");
			allVotersDetails = populateElectionsData(winningCandidate,successorCandidate);	
			
			log.info("Calling populateElectionsData() method..");
		
			return mandalAllElectionDetailsVo;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/*
	 * This method retrieves all the MPTC's Party candidates present in that particular district.
	 */
	public MandalAllElectionDetailsVO getAllMptcsForADistrictForAPartyForSelectedYear(Long districtId,String electionYear,Long partyId) {

		List<MandalAllElectionDetailsVO> winningCandidates = new ArrayList<MandalAllElectionDetailsVO>(0);
		List<MandalAllElectionDetailsVO> successorCandidates = new ArrayList<MandalAllElectionDetailsVO>(0);
		List<MandalAllElectionDetailsVO> allVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(0);
		MandalAllElectionDetailsVO mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();
		
		Long winnerRank =1l,successorRank=2l;
		try{
			log.info("Making nominationDAO.findAllZptcPartysInaDistrict DAO call");					
			List partySuccessorsResult = nominationDAO.findAllZptcPartysInaDistrict(districtId,IConstants.MPTC_ELECTION_TYPE,electionYear,partyId,winnerRank);
			
			log.info("Making nominationDAO.findAllZptcPartysWinnerInaDistrict DAO call");		
			List partyWinningCandidate = nominationDAO.findAllZptcPartysWinnerInaDistrict(districtId,IConstants.MPTC_ELECTION_TYPE,electionYear,partyId,winnerRank);
			
			log.info("Making nominationDAO.findAllZPTCsInaDistrict DAO call");		
			List winningCandidate = nominationDAO.findAllZPTCsInaDistrict(districtId,IConstants.MPTC_ELECTION_TYPE,winnerRank,electionYear);
			
			log.info("Making nominationDAO.findAllZPTCsInaDistrict DAO call");		
			List successorCandidate = nominationDAO.findAllZPTCsInaDistrict(districtId,IConstants.MPTC_ELECTION_TYPE,successorRank,electionYear);
			
			log.info("Calling populateElectionsDataForAllCandidates() method..");
			successorCandidates = populateElectionsDataForAllCandidates(winningCandidate,partySuccessorsResult);
			
			log.info("Calling populateElectionsData() method..");
			winningCandidates = populateElectionsData(partyWinningCandidate,successorCandidate);
			if(winningCandidates!=null){
				allVotersDetails.addAll(winningCandidates);
			}
			if(successorCandidates!=null){
				allVotersDetails.addAll(allVotersDetails.size(),successorCandidates);
			}
			mandalAllElectionDetailsVo.setAllVotersDetails(allVotersDetails);	
			
			return mandalAllElectionDetailsVo;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	/*
	 * This method retrieves all the ZPTC's Party candidates present in that particular district
	 */
	public MandalAllElectionDetailsVO getAllZptcsForADistrictForAPartyForSelectedYear(Long districtId,String electionYear,Long partyId) {
		
		List<MandalAllElectionDetailsVO> winningCandidates = new ArrayList<MandalAllElectionDetailsVO>(0);
		List<MandalAllElectionDetailsVO> successorCandidates = new ArrayList<MandalAllElectionDetailsVO>(0);
		List<MandalAllElectionDetailsVO> allVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(0);
		MandalAllElectionDetailsVO mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();
		Long winnerRank =1l,successorRank=2l;
		try{
			log.info("Making nominationDAO.findAllZptcPartysInaDistrict DAO call");					
			List partySuccessorsResult = nominationDAO.findAllZptcPartysInaDistrict(districtId,IConstants.ZPTC_ELECTION_TYPE,electionYear,partyId,winnerRank);
			
			log.info("Making nominationDAO.findAllZptcPartysWinnerInaDistrict DAO call");	
			List partyWinningCandidate = nominationDAO.findAllZptcPartysWinnerInaDistrict(districtId,IConstants.ZPTC_ELECTION_TYPE,electionYear,partyId,winnerRank);
			
			log.info("Making nominationDAO.findAllZPTCsInaDistrict DAO call");	
			List winningCandidate = nominationDAO.findAllZPTCsInaDistrict(districtId,IConstants.ZPTC_ELECTION_TYPE,winnerRank,electionYear);
			
			log.info("Making nominationDAO.findAllZPTCsInaDistrict DAO call");	
			List successorCandidate = nominationDAO.findAllZPTCsInaDistrict(districtId,IConstants.ZPTC_ELECTION_TYPE,successorRank,electionYear);
			
			log.info("Calling populateElectionsDataForAllCandidates() method..");
			successorCandidates = populateElectionsDataForAllCandidates(winningCandidate,partySuccessorsResult);	
			log.info("Calling populateElectionsData() method..");
			winningCandidates = populateElectionsData(partyWinningCandidate,successorCandidate);
			if(winningCandidates!=null){
				allVotersDetails.addAll(winningCandidates);
			}
			if(successorCandidates!=null){
				allVotersDetails.addAll(allVotersDetails.size(),successorCandidates);
			}
			mandalAllElectionDetailsVo.setAllVotersDetails(allVotersDetails);		
			
			return mandalAllElectionDetailsVo;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/*
	 * This method retrieves all the candidates participated in that particular Mptc for a particular election year.
	 */
	public MandalAllElectionDetailsVO getAllMptcsCandidatesForADistrictForSelectedYear(Long districtId,String electionYear) {
		
		List<MandalAllElectionDetailsVO> winningCandidateVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(0);
		List<MandalAllElectionDetailsVO> allVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(0);
		MandalAllElectionDetailsVO mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();
		Long winnerRank =1l,successorRank=2l;
		try{	
			log.info("Making nominationDAO.findAllMPTCsInaDistrict DAO call");			
			List successorCandidate = nominationDAO.findAllMPTCsInaDistrict(districtId,IConstants.MPTC_ELECTION_TYPE,successorRank,electionYear);
			
			log.info("Making nominationDAO.findAllMPTCsInaDistrict DAO call");		
			List winningCandidate = nominationDAO.findAllMPTCsInaDistrict(districtId,IConstants.MPTC_ELECTION_TYPE,winnerRank,electionYear);
			
			log.info("Making nominationDAO.findAllMptcCandidatesInaDistrict DAO call");		
			List allCandidates = nominationDAO.findAllMptcCandidatesInaDistrict(districtId,IConstants.MPTC_ELECTION_TYPE,electionYear);
			
			log.info("Calling populateElectionsData() method..");
			winningCandidateVotersDetails = populateElectionsData(winningCandidate,successorCandidate);	
			
			log.info("Calling populateElectionsDataForAllCandidates() method..");
			allVotersDetails = populateElectionsDataForAllCandidates(winningCandidate,allCandidates);
			if(winningCandidateVotersDetails!=null){
				allVotersDetails.addAll(allVotersDetails.size(),winningCandidateVotersDetails);
			}
			mandalAllElectionDetailsVo.setAllVotersDetails(allVotersDetails);
			return mandalAllElectionDetailsVo;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}		
	}
	
	/*
	 * This method retrieves all the winning candidates participated in that particular Zptc for a particular election year.
	 */
	public MandalAllElectionDetailsVO getAllZptcsCandidatesForADistrictForSelectedYear(Long districtId,String electionYear) {
		
		List<MandalAllElectionDetailsVO> winningCandidateVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(0);
		List<MandalAllElectionDetailsVO> allVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(0);
	
		MandalAllElectionDetailsVO mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();
		Long winnerRank =1l,successorRank=2l;
		try{	
			log.info("Making nominationDAO.findAllZPTCsInaDistrictDAO call");			
			List successorCandidate = nominationDAO.findAllZPTCsInaDistrict(districtId,IConstants.ZPTC_ELECTION_TYPE,successorRank,electionYear);
			
			log.info("Making nominationDAO.findAllZPTCsInaDistrict DAO call");
			List winningCandidate = nominationDAO.findAllZPTCsInaDistrict(districtId,IConstants.ZPTC_ELECTION_TYPE,winnerRank,electionYear);
			
			log.info("Making nominationDAO.findAllZptcCandidatesInaDistrict DAO call");
			List allCandidates = nominationDAO.findAllZptcCandidatesInaDistrict(districtId,IConstants.ZPTC_ELECTION_TYPE,electionYear);
			
			log.info("Calling populateElectionsData() method..");
			winningCandidateVotersDetails = populateElectionsData(winningCandidate,successorCandidate);
			
			log.info("Calling populateElectionsDataForAllCandidates() method..");
			allVotersDetails = populateElectionsDataForAllCandidates(winningCandidate,allCandidates);
			if(winningCandidateVotersDetails!=null){
				allVotersDetails.addAll(allVotersDetails.size(),winningCandidateVotersDetails);
			}
			mandalAllElectionDetailsVo.setAllVotersDetails(allVotersDetails);
			
			return mandalAllElectionDetailsVo;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}		
	}
	
	
	
	/*
	 * This method calculates all the voting difference between the winning and the looser candidates and winner sets a VO 
	 * containing all the information.
	 */	
	public List<MandalAllElectionDetailsVO> populateElectionsDataForAllCandidates(List winningCandidate,List allCandidates){
		List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO= new ArrayList<MandalAllElectionDetailsVO>(0);
		Map<Long,Float> winner = new HashMap<Long,Float>(0);
		Float differenceVotes;
		Long constituencyId;
		try{
		for(int i=0;i<winningCandidate.size();i++){
			Object[] parms = (Object[])winningCandidate.get(i);
			winner.put(Long.parseLong(parms[9].toString()), Float.parseFloat(parms[6].toString()));
		}
		log.info("Inside populateElectionsDataForAllCandidates() method..");
		for(int i=0;i<allCandidates.size();i++){
			Object[] parms = (Object[])allCandidates.get(i);
			MandalAllElectionDetailsVO mandalAllElectionDetails = new MandalAllElectionDetailsVO();
			constituencyId=Long.parseLong(parms[9].toString());
			if(parms[0]!=null){
				mandalAllElectionDetails.setPartyFlag(parms[0].toString());
			}else{
				mandalAllElectionDetails.setPartyFlag("no_Image.png");
			}
			mandalAllElectionDetails.setElectionYear(parms[1].toString());
			mandalAllElectionDetails.setCandidateName(parms[2].toString());
			mandalAllElectionDetails.setTehsilName(parms[3].toString());
			mandalAllElectionDetails.setElectionType(parms[4].toString());
			mandalAllElectionDetails.setTehsilId(Long.parseLong(parms[5].toString()));
			mandalAllElectionDetails.setVotesEarned(Float.parseFloat(parms[6].toString()));
			mandalAllElectionDetails.setVotesPolled(Float.parseFloat(parms[7].toString()));
			mandalAllElectionDetails.setRank(parms[8].toString());
			if(winner.containsKey(constituencyId)){
				differenceVotes = winner.get(constituencyId)-Float.parseFloat(parms[6].toString());
				mandalAllElectionDetails.setVotesDifference(Float.parseFloat(differenceVotes.toString()));	
			}
			else{
				differenceVotes = 0f;
				mandalAllElectionDetails.setVotesDifference(differenceVotes);
			}
			if(Long.parseLong(parms[8].toString())!=1){
				mandalAllElectionDetailsVO.add(mandalAllElectionDetails);
			}
		}
		return mandalAllElectionDetailsVO;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}

	
	/*
	 *This method calculates all the voting difference between the winning and the successor candidates and winner sets a VO 
	 *containing all the information
	 */	
	public List<MandalAllElectionDetailsVO> populateElectionsData(List winningCandidate,List successorCandidate){
		List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO = new ArrayList<MandalAllElectionDetailsVO>(0);
		Long constituencyId;
		Float differenceVotes;
		Map<Long,Float> winner = new HashMap<Long,Float>(0);
		Map<Long,Float> successor = new HashMap<Long,Float>(0);
		try{
			for(int i=0;i<successorCandidate.size();i++){
				Object[] parms = (Object[])successorCandidate.get(i);
				successor.put(Long.parseLong(parms[9].toString()), Float.parseFloat(parms[6].toString()));
			}
			log.info("Inside populateElectionsData() method..");
			for(int i=0;i<winningCandidate.size();i++){
				MandalAllElectionDetailsVO mandalAllElectionDetails = new MandalAllElectionDetailsVO();
				Object[] parms = (Object[])winningCandidate.get(i);
				constituencyId = Long.parseLong(parms[9].toString());
				if(parms[0]==null){
					mandalAllElectionDetails.setPartyFlag("no_Image.png");
				}else{
					mandalAllElectionDetails.setPartyFlag(parms[0].toString());
				}				
				mandalAllElectionDetails.setElectionYear(parms[1].toString());
				mandalAllElectionDetails.setCandidateName(parms[2].toString());
				mandalAllElectionDetails.setTehsilName(parms[3].toString());
				mandalAllElectionDetails.setElectionType(parms[4].toString());
				mandalAllElectionDetails.setTehsilId(Long.parseLong(parms[5].toString()));
				mandalAllElectionDetails.setVotesEarned(Float.parseFloat(parms[6].toString()));
				mandalAllElectionDetails.setVotesPolled(Float.parseFloat(parms[7].toString()));
				mandalAllElectionDetails.setRank(parms[8].toString());
				if(successor.containsKey(constituencyId)){
					differenceVotes = (Float.parseFloat(parms[6].toString())-successor.get(constituencyId));
					mandalAllElectionDetails.setVotesDifference(Float.parseFloat(differenceVotes.toString()));
				}else{
					differenceVotes = 0f;
					mandalAllElectionDetails.setVotesDifference(differenceVotes);
				}
				mandalAllElectionDetailsVO.add(mandalAllElectionDetails);
			}		
		return mandalAllElectionDetailsVO;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * This method takes districtId,electionType and electionYear and generates a list of VO that contains partyId and partyName
	 */
	public MandalAllElectionDetailsVO getAllPartiesForAParticularElection(Long districtId,String electionType,String electionYear){
		MandalAllElectionDetailsVO mandalAllElectionDetailsVO = new MandalAllElectionDetailsVO();
		 List<SelectOptionVO>  SelectOptionVo = new ArrayList<SelectOptionVO>(0);
		 try{
			 List result = nominationDAO.getAllPartysForAParticularElectionYear(districtId,electionType,electionYear);
			 SelectOptionVO selectOption = new SelectOptionVO();
			 selectOption.setId(0l);
			 selectOption.setName("Select");
				SelectOptionVo.add(selectOption);
			 for(int i=0;i<result.size();i++){
				 SelectOptionVO selectOptionVO = new SelectOptionVO();
				Object[] parms = (Object[])result.get(i);
				selectOptionVO.setId(Long.parseLong(parms[0].toString()));
				selectOptionVO.setName(parms[1].toString());
				SelectOptionVo.add(selectOptionVO);
			 }
			 mandalAllElectionDetailsVO.setPartyInfo(SelectOptionVo);
			return mandalAllElectionDetailsVO;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}
	
}

