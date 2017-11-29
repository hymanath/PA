package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DistrictConstituencies;

public interface IDistrictConstituenciesDAO extends GenericDao<DistrictConstituencies,Long>{
	public List<Object[]> getConstituenciesOfDistrict(Long districtId);
	public List<Object[]> getConstituenciesOfDistrictStateWise(Long stateId);
	public List<Long> getConstituenciesOfDistrictById(Long districtId);	
	public List<Object[]> getDistrictByConstituenciesIds(Set<Long> constituenciesIds);
	public List<Object[]> getAllConstituenciesInADistrict(Long districtId);
}
