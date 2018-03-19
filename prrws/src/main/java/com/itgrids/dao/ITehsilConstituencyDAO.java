package com.itgrids.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.TehsilConstituency;

public interface ITehsilConstituencyDAO extends GenericDao<TehsilConstituency,Long> {
	public List<Object[]> getTehsilIdAndName(Long constId);

	public List<Object[]> getNonFundedLocations(Set<Long> keysList,Long searchLevelId,Long searchScopeId,List<Long> searchScopeValuesList);
	public List<Object[]> getConstituencyOfTehsil(Long tehsilId);
	public List<Object[]> getTehsilIdAndName1(Long tehsilId);
}
