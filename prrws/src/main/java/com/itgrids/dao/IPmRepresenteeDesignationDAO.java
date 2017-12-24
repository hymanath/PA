package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRepresenteeDesignation;

public interface IPmRepresenteeDesignationDAO extends GenericDao<PmRepresenteeDesignation, Long> {
	public List<PmRepresenteeDesignation> getPmRepresenteeDesignationByRepresenteeId(Long representeeId);
	public int inActiveExistingDesignationsByIds(List<Long> pmRepresenteeDesignationIdsList);
	public List<Object[]> getAllDistrictsByRepresenteeDesignationWise(List<Long> deptIds);
	public List<Object[]> getDesignationsByRepresenteeDesigtion();
	public List<Object[]> getAllConstituenciesByRepresenteeDesignationWise(List<Long> districtIds);
	public List<Object[]> getAllMandalsByRepresenteeDesignationAndconstincy(List<Long> constituencyIds);
}
