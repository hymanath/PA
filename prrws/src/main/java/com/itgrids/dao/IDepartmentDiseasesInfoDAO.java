package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.DepartmentDiseasesInfo;

public interface IDepartmentDiseasesInfoDAO extends GenericDao<DepartmentDiseasesInfo, Long> {
	public List<Object[]> getCaseCountDiseasesWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList);
	public List<Object[]> getCaseCountLocationWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,Long scopeId,Long superLocationId);
	public List<Object[]> getCaseCountDateWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList);
	public List<Object[]> getTotLocationsDiseasesWiseCount(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,Long scopeId);
	public List<String> getMonthAndYear(Date fromDate,Date toDate);
	public List<Object[]> getLocationDtlsRankWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,Long scopeId);
}
