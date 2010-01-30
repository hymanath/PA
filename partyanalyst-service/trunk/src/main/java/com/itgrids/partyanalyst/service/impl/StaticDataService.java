package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
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
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
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
	
	public List<SelectOptionVO> findTownshipsByTehsilID(String mandalID){
		List<SelectOptionVO> townshipVOs = new ArrayList<SelectOptionVO>();
		SelectOptionVO townshipVO = null;
		List<Township> townships = townshipDAO.findByTehsilID(new Long(mandalID));
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
		log.debug("DistrictPageService.getConstituenciesWinnerInfo()...started");
		List delimitationYear = delimitationConstituencyDAO.getLatestDelimitationYear();

		Long electionYear = new Long(delimitationYear.get(0).toString()) ;

		log.debug("DistrictPageService.getConstituenciesWinnerInfo() delimitationYear:"+electionYear);
		ConstituenciesStatusVO constituenciesStatusVO = getConstituenciesForDistrict(districtId, electionYear);
		
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
	public ConstituenciesStatusVO getConstituenciesForDistrict(Long districtId,Long electionYear){
		ConstituenciesStatusVO constituencyVO = new ConstituenciesStatusVO();
	
		List result  = constituencyDAO.findConstituencyByDistrictId(districtId);
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
}
