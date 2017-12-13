package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRefCandidate;

public interface IPmRefCandidateDAO extends GenericDao<PmRefCandidate, Long> {
	public List<Object[]> getAllDistrictsByReferral();
	public List<Object[]> getAllConstituenciesByReferralAndDistrict(Long districtId);
	public List<Object[]> getAllMandalsByReferralAndDistrict(Long constituencyId);
}
