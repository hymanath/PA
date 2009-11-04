package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ConstituencyTehsil;
import com.itgrids.partyanalyst.model.Tehsil;

public interface IConstituencyTehsilDAO extends GenericDao<ConstituencyTehsil, Long>{
	public List<ConstituencyTehsil> findByConstituency(Long constituencyID);
	public List<Tehsil> getTehsilsByConstituency(Long constituencyID);
}
