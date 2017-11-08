package com.itgrids.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.DepartmentDiseasesInfo;

public interface IDepartmentDiseasesInfoDAO extends GenericDao<DepartmentDiseasesInfo, Long> {
	public List<Object[]> getCaseCountDiseasesWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,String type);
	public List<Object[]> getCaseCountLocationWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,Long scopeId, Long locationLevelId, Long locationId,String type,Long constituencyId);
	public List<Object[]> getCaseCountDateWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,String type);
	public List<Object[]> getTotLocationsDiseasesWiseCount(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,Long scopeId);
	public List<String> getMonthAndYear(Date fromDate,Date toDate);
	public List<Object[]> getLocationDtlsRankWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,Long scopeId);
	public List<Object[]> getAllParliamentByStateId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList);
	public List<Object[]> getAllDistrictByStateId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList);
	public List<Object[]> getAllConstituencyByDistrictId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList,String type);
	public List<Object[]> getAllTehsilByConstituencyId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList);
	public List<Object[]> getAllPanchayatByTehsilId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList);
	public List<Object[]> getAllConstituencyByParliamentConstId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList,String type);
	public List<Object[]> getAllLocalElectionBodyByConstituencyId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList);
	public List<Object[]> getCaseCountByLocationIds(List<Long> diseasesIdList,List<Long> deptIdList,Long scopeId,Set<Long> locationIdList,String type,Long constituencyId);
	
	
}
