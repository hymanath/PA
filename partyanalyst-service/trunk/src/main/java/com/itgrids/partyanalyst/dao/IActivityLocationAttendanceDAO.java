package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityLocationAttendance;

public interface IActivityLocationAttendanceDAO extends GenericDao<ActivityLocationAttendance, Long>{
	public List<Object[]> getActivityAttendanceCount(SearchAttributeVO inputVO,String memberType,Long stateId);
	public List<Object[]> getDayWiseActivityLocationsCount(SearchAttributeVO inputVO,String memberType,Long stateId);
	public List<Object[]> getActivityDetailsByTdpCadreId(Long cadreId);
	public List<Object[]> getCadreAttendedActivityDetails(List<Long> activityLocationInfoIds,Long cadreId);
	public List<Long> getActivityInfoIdsByCadreIdFromAttendence(Long locationLevel,Long cadreId);
}
