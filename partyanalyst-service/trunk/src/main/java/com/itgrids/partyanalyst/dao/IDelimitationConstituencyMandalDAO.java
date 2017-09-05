package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituencyMandal;
import com.itgrids.partyanalyst.model.Tehsil;


public interface IDelimitationConstituencyMandalDAO extends GenericDao<DelimitationConstituencyMandal, Long>{

	public List<DelimitationConstituencyMandal> findDelConstMandalByDelConstID(Long delimitationConstituencyID);
	
	public List<DelimitationConstituencyMandal> findDelConstMandalByDelConstID(
			Long delimitationConstituencyID,Long tehsilId);
	
	public List<Tehsil> getTehsilsByDelimitationConstituencyID(Long delimitationConstituencyID);
	
	public List getStateDistConstituencyMandalByMandalID(Long mandalID);
	
	public List getMandalsOfConstituency(Long constituencyId);
	
	public List getLatestMandalDetailsForAConstituency(Long constituencyId);
	
	public List<Tehsil> getLatestMandalDetailsForAConstituencies(String constituencyIds);
	public List getLatestMandalsInConstituencies(String constituencyIds);
	
	public List getLatestAssemblyConstitueciesOfTehsil(Long tehsilId);
	
	public List getMandalDetailsForAConstituency(Long constituencyId,Long electionYear);
	
	public List<Long> getLatestMandalIdsByConstituenciesIds(List<Long> constituencyIds);
	
	public List<Object[]> getLatestMandalsForAConstituency(Long constituencyId);
	
	public List<Object[]> getPanchayatsByConstituencyId(Long constituencyId);
	
	public List<Constituency> getConstituencyByTehsilId(Long tehsilId);
	
	public List<Long> getConstituencyIdByMandalID(Long mandalId);
	public List<Tehsil> getTehsilsByDelimitationConstituencyIds(List<Long> delimitationConstituencyIds) ;
	
	public List<Long> getMadalDetailsByDelimitationConstituencyIds(List<Long> delimitationConstituencyIds);
	public List<Object[]> getMadalDtlsByDelimitationConstituencyIds(List<Long> delimitationConstituencyIds);

	public List<Object[]> getIspartialForMandalByMandalIdsList(List<Long> mandalIdsList,Long year);
	
	public List<Object[]> getTehsilsForRuralConstituencyes(List<Long> constituencyIds,Long year);

	public List<Object[]> getTehsilByDelimitationConstituencyIds(List<Long> delimitationConstituencyIds) ;
	
	public List<Object[]> getConstituencyByMandals(List<Long> mandalIds);
	
	public List<Object[]> getMandalIdsByConstituencyId(Long constituencyId,Long year);
	
	public List<Object[]> getAssemblyConstituencyAndMandalsInAState(Long stateId);
	
	public List<Object[]> getTehsilsByDelimitationConstituencyIDs(List<Long> delimitationConstituencyIDs) ;
	
	public List<Long> getStateWideDelimitationConstituencyIdsList(Long stateId);
	public List<Object[]> getTehsilsListByConstituencyId(Long constituencyId);
	public List<Tehsil> getTehsilsByDelimitationsConstituencyID(List<Long> delimitationConstituencyIDs);
	public List<Constituency> getConstituencyByTehsilOfYear(Long tehsilId);
}
