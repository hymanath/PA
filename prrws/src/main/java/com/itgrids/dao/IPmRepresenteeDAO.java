package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRepresentee;

public interface IPmRepresenteeDAO extends GenericDao<PmRepresentee, Long> {
	public List<Long> getExistingPetitionRepresenteeDetailsById(String voterCardNo,String adharCardNo);
	public List<Long> getExistingPetitionRepresenteeDetailsByRefId(Long refCandidateId);
	public List<Object[]> getAllDistrictsBySearchType(Date fromDate,Date toDate,List<Long> deptIds);
	public List<Object[]> getAlConstituenciesBySearchType(List<Long> districtIds,List<Long> deptIds);
	public List<Object[]> getAllMandalsBySearchType(List<Long> constituencyIds,List<Long> depts);
	
	
}
