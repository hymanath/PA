package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmSubWorkDetails;

public interface IPmSubWorkDetailsDAO extends GenericDao<PmSubWorkDetails, Long> {

	public List<Object[]> getAllDistricts();
	public List<Object[]> getAllConstituenciesByDistricId(Long districtId);
	public List<Object[]> getAllMandalsByDistricId(Long constincyIdId);
	public List<Object[]> getDepartmentsByWorks();
}
