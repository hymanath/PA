package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRepresenteeDesignation;

public interface IPmRepresenteeDesignationDAO extends GenericDao<PmRepresenteeDesignation, Long> {
	public List<PmRepresenteeDesignation> getPmRepresenteeDesignationByRepresenteeId(Long representeeId);
	public int inActiveExistingDesignationsByIds(List<Long> pmRepresenteeDesignationIdsList);
	public List<Object[]> getAllDistrictsByRepresenteeDesignationWise(Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType);
	public List<Object[]> getDesignationsByRepresenteeDesigtion(List<Long> deptIds,Date fromDate ,Date toDate);
	public List<Object[]> getAllConstituenciesByRepresenteeDesignationWise(Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type);
	public List<Object[]> getAllMandalsByRepresenteeDesignationAndconstincy(List<Long> constituencyIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType);
}
