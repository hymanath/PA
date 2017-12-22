package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRefCandidate;

public interface IPmRefCandidateDAO extends GenericDao<PmRefCandidate, Long> {
	public List<Object[]> getAllDistrictsByReferral(List<Long> deptIds);
	public List<Object[]> getAllConstituenciesByReferralAndDistrict(List<Long> districtIds);
	public List<Object[]> getAllMandalsByReferralAndDistrict(List<Long> constituencyIds);
}
