package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PrTehsil;

public interface IPrTehsilDAO extends GenericDao<PrTehsil, Long> {

	public List<Object[]> getPrPanchayatTehsilName();
	public List<Object[]> getPrTehsilConsituencyName();
	public List<Object[]> getTehsilConstituencyId();
	public List<Object[]> getTehsilFrConstituency(String constituencyId);
}
