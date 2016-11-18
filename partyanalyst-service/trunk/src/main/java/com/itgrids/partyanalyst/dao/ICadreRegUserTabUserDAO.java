package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.CadreRegUserTabUser;

public interface ICadreRegUserTabUserDAO extends GenericDao<CadreRegUserTabUser, Long>{

	public List<Object[]> getUserAssignedConstituencies(Long cadreRegUserId,Long districtId);
	public List<Object[]> getUserAssignedUsers(Long cadreRegUserId,Long constituencyId);
	public List<Object[]> getAllAssignedConstituencies(Long districtId,String searchType);
	public List<Object[]> getAllUserAssignedUsers(Long constituencyId,String searchType);
	public List<Object[]> getUserAssignedDistricts(Long cadreRegUserId);
	public List<Object[]> getAllAssignedDistricts(String searchType);
	public Long getAssignedUsersCountForRegUser(Long cadreRegUserId);
	
	public List<Object[]> getFieldMonitoringUserWiseDetails(GISVisualizationParameterVO inputVO);
	public List<Object[]> getIssueTypeWiseCountsForFieldMonrUsers(Date today,GISVisualizationParameterVO inputVO);
	public List<Object[]> getIssueStatusWiseCountsForFieldMonrUsers(Date today,GISVisualizationParameterVO inputVO);
	public List<Object[]> getConstituencyWiseFMUsersDetails();
	public List<Object[]> getTotalUsersCountForFMUsers();
	public List<Object[]> getTotalRegisteredUsers(GISVisualizationParameterVO inputVO);
	public List<Object[]> getTodayStartedUsersOfFMUser(Date today,GISVisualizationParameterVO inputVO);
	public List<Object[]> getTodayTotalIssues(Date today,GISVisualizationParameterVO inputVO);
	public List<Object[]> getTodayTotalStartedIssues(Date today,GISVisualizationParameterVO inputVO);
	public List<Object[]> getStartedUsersIssueTypeWiseCountsForFieldMonrUsers(Date today,GISVisualizationParameterVO inputVO);
	public List<Object[]> getLastOneHourUsersOfFMUser(Date lastHourTime,Date today,GISVisualizationParameterVO inputVO);
	public List<Object[]> getFieldMonitoringMapReportDetails(Long constitunecyId, Long fieldUserId);
	public List<Object[]> getLocationWiseIssueStatus(GISVisualizationParameterVO inputVO);
}
