package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRepresenteeDesignation;

public interface IPmRepresenteeDesignationDAO extends GenericDao<PmRepresenteeDesignation, Long> {
	public List<PmRepresenteeDesignation> getPmRepresenteeDesignationByRepresenteeId(Long representeeId);
	public int inActiveExistingDesignationsByIds(List<Long> pmRepresenteeDesignationIdsList);
	public List<Object[]> getAllDistrictsByRepresenteeDesignationWise(Date fromDate,Date toDate,List<Long> deptIds);
	public List<Object[]> getDesignationsByRepresenteeDesigtion();
	public List<Object[]> getAllConstituenciesByRepresenteeDesignationWise(List<Long> districtIds,List<Long> deptIds);
	public List<Object[]> getAllMandalsByRepresenteeDesignationAndconstincy(List<Long> constituencyIds,List<Long> deptIds);
}
