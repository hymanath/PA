package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRepresenteeDesignation;

public interface IPmRepresenteeDesignationDAO extends GenericDao<PmRepresenteeDesignation, Long> {
	public List<PmRepresenteeDesignation> getPmRepresenteeDesignationByRepresenteeId(Long representeeId);
	public List<Object[]> getAllDistrictsByRepresenteeDesignationWise();
	public List<Object[]> getDesignationsByRepresenteeDesigtion();
	public List<Object[]> getAllConstituenciesByRepresenteeDesignationWise(Long districtId);
	public List<Object[]> getAllMandalsByRepresenteeDesignationAndconstincy(Long constituencyId);
}
