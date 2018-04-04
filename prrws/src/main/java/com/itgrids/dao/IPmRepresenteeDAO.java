package com.itgrids.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.dto.InputVO;
import com.itgrids.model.PmRepresentee;

public interface IPmRepresenteeDAO extends GenericDao<PmRepresentee, Long> {
	public List<Long> getExistingPetitionRepresenteeDetailsById(String voterCardNo,String adharCardNo);
	public List<Long> getExistingPetitionRepresenteeDetailsByRefId(Long refCandidateId);
	public List<Object[]> getAllDistrictsBySearchType(InputVO inputVO, Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType,List<Long> statIds,Set<Long> petitionIdsList);
	public List<Object[]> getAlConstituenciesBySearchType(InputVO inputVO,Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds,Set<Long> petitionIdsList);
	public List<Object[]> getAllMandalsBySearchType(InputVO inputVO,List<Long> constituencyIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType,List<Long> statIds,Set<Long> petitionIdsList);
	
	
}
