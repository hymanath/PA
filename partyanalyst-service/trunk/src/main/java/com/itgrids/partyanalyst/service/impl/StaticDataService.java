package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AllianceGroup;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionAlliance;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IStaticDataService;


public class StaticDataService implements IStaticDataService {

	private IElectionDAO electionDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IPartyDAO partyDAO;
	private IStateDAO stateDAO;
	private IElectionAllianceDAO electionAllianceDAO;
	private IDistrictDAO districtDAO;
	private IAllianceGroupDAO  allianceGroupDAO;
	private IConstituencyDAO constituencyDAO;
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
	
	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
	}

	
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
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
	
	public List<SelectOptionVO> getParties(){
		List<SelectOptionVO> partyList = new ArrayList<SelectOptionVO>();
		partyList.add(new SelectOptionVO(new Long(1), "Indian National Congress"));
		partyList.add(new SelectOptionVO(new Long(2), "Bharatiya Janata Party"));
		partyList.add(new SelectOptionVO(new Long(3), "Bahujan Samaj Party"));
		partyList.add(new SelectOptionVO(new Long(4), "Communist Party of India"));
		partyList.add(new SelectOptionVO(new Long(5), "Communist Party of India (Marxist)"));
		partyList.add(new SelectOptionVO(new Long(6), "Samajwadi Party"));
		partyList.add(new SelectOptionVO(new Long(7), "Samata Party"));
		partyList.add(new SelectOptionVO(new Long(8), "Rashtriya Janata Dal"));
		partyList.add(new SelectOptionVO(new Long(9), "Praja Rajyam Party"));
		partyList.add(new SelectOptionVO(new Long(10), "Telangana Rashtra Samithi"));
		partyList.add(new SelectOptionVO(new Long(11), "Telugu Desam"));
		partyList.add(new SelectOptionVO(new Long(12), "Lok Satta Party"));
		partyList.add(new SelectOptionVO(new Long(13), "Independent"));
		return partyList;
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


	public Set<String> getElectionYears(Long electionType) {
		List<Election> elections = electionDAO.findByPropertyTypeId(electionType);
		Set<String> years = new HashSet<String>();
		for(Election election: elections){
			years.add(election.getElectionYear());
		}
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
	
	
}
