package com.itgrids.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRefCandidateDesignation;

public interface IPmRefCandidateDesignationDAO extends GenericDao<PmRefCandidateDesignation, Long> {
	public List<PmRefCandidateDesignation> getPmRepresenteeDesignationByPmRefCandidateId(Long pmRefCandidateId);
	public List<Object[]> getCandidatseDetailsByDesignationAndLocation(Long designationId,Long locationLevelId,Long locationValue,List<Long> referralCanIds,List<Long> desiIds);

	public List<Object[]> getAllDistrictsByReferalAndDesignation(Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType,List<Long> statIds,Set<Long> petitionIdsList);
	public List<Object[]> getAlConstituenciesByReferalAndDesignationBydistrict(Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds,Set<Long> petitionIdsList);
	public List<Object[]> getAllMandalsByReferalAndDesignationBydistrict(List<Long> constituencyIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType,List<Long> statIds,Set<Long> petitionIdsList);
	public List<Object[]> getDesignationsByReferlDesigtion(Date fromDate,Date toDate,List<Long> deptIds,Long desigId,List<Long> statusIds,Set<Long> petitionIdsList);
}
