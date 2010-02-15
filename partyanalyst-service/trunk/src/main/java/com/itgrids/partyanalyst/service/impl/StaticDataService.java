package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyWinnerInfoVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
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
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IPartyElectionResultDAO partyElectionResultDAO;
	private IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO;
	private final static Logger log = Logger.getLogger(StaticDataService.class);
	

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


	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
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
		List<Election> elections = electionDAO.findByPropertyTypeId(electionTypeId);
		List<SelectOptionVO> years = new ArrayList<SelectOptionVO>();
		for(Election election: elections){
			years.add(new SelectOptionVO(election.getElectionId(), election.getElectionYear()));
		}
		return years;
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
		List<Party> allianceParties = new ArrayList<Party>();
		
		Long groupId = getGroupIdIfPartyHasAlliances(partyId, electionYear, electionType);
		if(groupId != null) {
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
		// TODO Auto-generated method stub
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
	
	//Ravi kiran code started from here on wards
	

	
	/*
	 * This method takes District Id as input and displays all the Assembly Candidates
	 * that are present in that District.
	 */	
	@SuppressWarnings("unchecked")
	public ConstituenciesStatusVO getConstituenciesWinnerInfo(Long districtId){
		log.debug("DistrictPageService.getConstituenciesWinnerInfo()...started started..");
		List delimitationYear = delimitationConstituencyDAO.getLatestDelimitationYear();

		Long electionYear = new Long(delimitationYear.get(0).toString()) ;

		log.debug("DistrictPageService.getConstituenciesWinnerInfo() delimitationYear:"+electionYear);
		ConstituenciesStatusVO constituenciesStatusVO = getConstituenciesForDistrict(districtId, electionYear, IConstants.ASSEMBLY_ELECTION_TYPE);
		
		List<SelectOptionVO> constituencies = (constituenciesStatusVO.getExistConstituencies());
		constituencies.addAll(constituenciesStatusVO.getNewConstituencies());
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
			constituencyWinnerInfoVO.setPartyName(obj[2].toString());
			constituencyWinnerInfoVOList.add(constituencyWinnerInfoVO);
		}
		constituenciesStatusVO.setConstituencyWinnerInfoVO(constituencyWinnerInfoVOList);
		constituenciesStatusVO.setDelimitationYear(electionYear);
	
		return constituenciesStatusVO;
	}
	
	
	
	/*
	 * This method takes District Id as input and retrives all the constituencies that
	 * are present in that particular district. And it sets the constituencies names and 
	 * corresponding Id's in the Data transfer Object.
	 */	
	
	@SuppressWarnings("unchecked")
	public ConstituenciesStatusVO getConstituenciesForDistrict(Long districtId,Long electionYear, String electionType){
		ConstituenciesStatusVO constituencyVO = new ConstituenciesStatusVO();
	
		List result  = constituencyDAO.findConstituencyByDistrictElectionType(districtId,electionType);
		List<SelectOptionVO> deleteList= constituencyVO.getDeletedConstituencies();
		List<SelectOptionVO> existList= constituencyVO.getExistConstituencies();
		List<SelectOptionVO> newList= constituencyVO.getNewConstituencies();

	
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
		Long totalSeatsWon = new Long(0);
		Long totalSecondPositions = new Long(0);
		Long totalThirdPositions = new Long(0);
		Long totalFourthPositions = new Long(0);
		Long totalNthPositions = new Long(0);
		Long totalConstiParticipated = new Long(0);
		Double totalVotesEarned = new Double(0);
		Double totalValidVotes = new Double(0);
		Double totalVotesPercentage = new Double(0);
		try{
		 if(electionId != null && partyId != null){
			nominations = nominationDAO.findByElectionIdAndPartyId(electionId, partyId);
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
				partyElectionResult = savePartyElectionResult(election,party,totalSeatsWon,totalSecondPositions,totalThirdPositions,totalFourthPositions,totalNthPositions,totalConstiParticipated,totalVotesPercentage);
			}
		 }		
		}catch(Exception ex){
			log.debug("Exception raised ::" + ex);
		}
		
	return partyElectionResult;
	}
	
	public PartyElectionResult savePartyElectionResult(Election election,Party party,Long totalSeatsWon,Long secPos,Long thirdPos,Long fourthPos,Long nthPos,Long totConstiParticipated,Double totalVotesPercentage) throws Exception{
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
		try{
			if(electionId != null && partyId != null && stateId != null && districtId != null){
				//nominations = nominationDAO.findByElectionIdAndPartyId(electionId, partyId);
				nominations = nominationDAO.findByElectionIdAndPartyIdStateIdAndDistrictId(electionId, partyId,stateId,districtId);
				election = electionDAO.get(electionId);
				party = partyDAO.get(partyId);
				state = stateDAO.get(stateId);
				district = districtDAO.get(districtId);
				
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
					partyElectionDistrictResult = savePartyElectionDistrictResult(election,party,state,district,totalSeatsWon,totalSecondPositions,totalThirdPositions,totalFourthPositions,totalNthPositions,totalConstiParticipated,totalVotesPercentage);
				}	
			}
		}catch(Exception ex){
			log.debug("Exception raised ::" + ex);
		}
	return partyElectionDistrictResult;
	}
	
	public PartyElectionDistrictResult savePartyElectionDistrictResult(Election election,Party party,State state,District district,Long totalSeatsWon,Long secPos,Long thirdPos,Long fourthPos,Long nthPos,Long totConstiParticipated,Double totalVotesPercentage) throws Exception{
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
			partyElectionDistrictResult.setLastUpdated(updatedDate);
			partyElectionDistrictResult = partyElectionDistrictResultDAO.save(partyElectionDistrictResult);
		}
		
	return partyElectionDistrictResult;
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
}
