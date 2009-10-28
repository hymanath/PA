package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ConstituencyTehsil;

public interface IConstituencyTehsilDAO extends GenericDao<ConstituencyTehsil, Long>{
	public List<ConstituencyTehsil> getTehsilsByConstituency(Long constituencyID);
}
