package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.columns.enums.ElectionScopeColumnNames;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;


public class StaticDataService implements IStaticDataService {

	private IElectionDAO electionDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IPartyDAO partyDAO;
	private IStateDAO stateDAO;

	private IDistrictDAO districtDAO;
	private final static Log log = LogFactory.getLog(StaticDataService.class);
	

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


	public Set<String> getElectionYears(Long electionTypeId) {
		List<Election> elections = electionDAO.findByPropertyTypeId(electionTypeId);
		Set<String> years = new HashSet<String>();
		for(Election election: elections){
			years.add(election.getElectionYear());
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

	
}
