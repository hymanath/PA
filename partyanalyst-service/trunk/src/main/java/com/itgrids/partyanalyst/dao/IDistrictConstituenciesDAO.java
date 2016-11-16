package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DistrictConstituencies;

public interface IDistrictConstituenciesDAO extends GenericDao<DistrictConstituencies,Long>{
	public List<Object[]> getConstituenciesOfDistrict();
	public List<Object[]> getConstituenciesOfDistrictStateWise(Long stateId);
	public List<Long> getConstituenciesOfDistrictById(Long districtId);
}
