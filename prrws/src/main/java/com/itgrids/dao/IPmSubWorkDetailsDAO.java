package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmSubWorkDetails;

public interface IPmSubWorkDetailsDAO extends GenericDao<PmSubWorkDetails, Long> {
	public List<Object[]> getPetitionSubWorksDetails(Long petitionId);
	public List<Object[]> getAllDistricts();
	public List<Object[]> getAllConstituenciesByDistricId(List<Long> districtIds);
	public List<Object[]> getAllMandalsByDistricId(List<Long> constincyIdId);
	public List<Object[]> getDepartmentsByWorks();
	
	public List<Long> getPmSubWorkDetailsIds(Long petitionId);
	public int updatePmsubWorkDetails(List<Long> subWorkDetailsIds,Date updateTime,Long userId);
	public List<Object[]> getCompleteOrStatusOverviewDetails(List<Long> deptIds ,Date startDate,Date endDate,String type);
}
