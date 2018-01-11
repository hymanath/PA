package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRefCandidate;

public interface IPmRefCandidateDAO extends GenericDao<PmRefCandidate, Long> {
	public List<Object[]> getAllDistrictsByReferral(Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType,List<Long> statIds);
	public List<Object[]> getAllConstituenciesByReferralAndDistrict(Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds);
	public List<Object[]> getAllMandalsByReferralAndDistrict(List<Long> constituencyIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType,List<Long> statIds);
}
