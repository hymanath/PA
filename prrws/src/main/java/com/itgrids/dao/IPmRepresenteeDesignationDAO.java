package com.itgrids.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRepresenteeDesignation;

public interface IPmRepresenteeDesignationDAO extends GenericDao<PmRepresenteeDesignation, Long> {
	public List<PmRepresenteeDesignation> getPmRepresenteeDesignationByRepresenteeId(Long representeeId);
	public int inActiveExistingDesignationsByIds(List<Long> pmRepresenteeDesignationIdsList);
	public List<Object[]> getAllDistrictsByRepresenteeDesignationWise(Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType,List<Long> statIds,Set<Long> petitionIdsList);
	public List<Object[]> getDesignationsByRepresenteeDesigtion(List<Long> deptIds,Date fromDate ,Date toDate,Long desigId,List<Long> statusIds,Set<Long> petitionIdsList);
	public List<Object[]> getAllConstituenciesByRepresenteeDesignationWise(Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds,Set<Long> petitionIdsList);
	public List<Object[]> getAllMandalsByRepresenteeDesignationAndconstincy(List<Long> constituencyIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType,List<Long> statIds,Set<Long> petitionIdsList);
}
