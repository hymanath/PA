package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

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
}
