package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NewDistrictConstituency;

public interface INewDistrictConstituencyDAO  extends GenericDao<NewDistrictConstituency, Long>{
	 public List<String> getConstiDetailsOfDistrict(Long districtId);
	public List<Object[]> getConstituencyListForDistrict(Long districtId);
}
