package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRefCandidateDesignation;

public interface IPmRefCandidateDesignationDAO extends GenericDao<PmRefCandidateDesignation, Long> {
	public List<PmRefCandidateDesignation> getPmRepresenteeDesignationByPmRefCandidateId(Long pmRefCandidateId);
	public List<Object[]> getCandidatseDetailsByDesignationAndLocation(Long designationId,Long locationLevelId,Long locationValue);

	public List<Object[]> getAllDistrictsByReferalAndDesignation();
	public List<Object[]> getAlConstituenciesByReferalAndDesignationBydistrict(Long districtId);
	public List<Object[]> getAllMandalsByReferalAndDesignationBydistrict(Long constituencyId);
	public List<Object[]> getDesignationsByReferlDesigtion();
}
