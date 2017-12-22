package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRefCandidateDesignation;

public interface IPmRefCandidateDesignationDAO extends GenericDao<PmRefCandidateDesignation, Long> {
	public List<PmRefCandidateDesignation> getPmRepresenteeDesignationByPmRefCandidateId(Long pmRefCandidateId);
	public List<Object[]> getCandidatseDetailsByDesignationAndLocation(Long designationId,Long locationLevelId,Long locationValue);

	public List<Object[]> getAllDistrictsByReferalAndDesignation(List<Long> deptIds);
	public List<Object[]> getAlConstituenciesByReferalAndDesignationBydistrict(List<Long> districtIds);
	public List<Object[]> getAllMandalsByReferalAndDesignationBydistrict(List<Long> constituencyIds);
	public List<Object[]> getDesignationsByReferlDesigtion();
}
