package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRepresentee;

public interface IPmRepresenteeDAO extends GenericDao<PmRepresentee, Long> {
	public List<Long> getExistingPetitionRepresenteeDetailsById(String voterCardNo,String adharCardNo);
	public List<Long> getExistingPetitionRepresenteeDetailsByRefId(Long refCandidateId);
	public List<Object[]> getAllDistrictsBySearchType(Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType,List<Long> statIds);
	public List<Object[]> getAlConstituenciesBySearchType(Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds);
	public List<Object[]> getAllMandalsBySearchType(List<Long> constituencyIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType,List<Long> statIds);
	
	
}
