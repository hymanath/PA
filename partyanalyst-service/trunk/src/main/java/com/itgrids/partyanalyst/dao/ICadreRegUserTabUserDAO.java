package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegUserTabUser;

public interface ICadreRegUserTabUserDAO extends GenericDao<CadreRegUserTabUser, Long>{

	public List<Object[]> getUserAssignedConstituencies(Long cadreRegUserId,Long districtId);
	public List<Object[]> getUserAssignedUsers(Long cadreRegUserId,Long constituencyId);
	public List<Object[]> getAllAssignedConstituencies(Long districtId,String searchType);
	public List<Object[]> getAllUserAssignedUsers(Long constituencyId,String searchType);
	public List<Object[]> getUserAssignedDistricts(Long cadreRegUserId);
	public List<Object[]> getAllAssignedDistricts(String searchType);
	public Long getAssignedUsersCountForRegUser(Long cadreRegUserId);
	
	public List<Object[]> getFieldMonitoringUserWiseDetails();
	public List<Object[]> getIssueTypeWiseCountsForFieldMonrUsers(Date today);
	public List<Object[]> getIssueStatusWiseCountsForFieldMonrUsers(Date today);
	public List<Object[]> getConstituencyWiseFMUsersDetails();
	public List<Object[]> getTotalUsersCountForFMUsers();
	public List<Object[]> getTotalRegisteredUsers();
	public List<Object[]> getTodayStartedUsersOfFMUser(Date today);
	public List<Object[]> getTodayTotalIssues(Date today);
	public List<Object[]> getTodayTotalStartedIssues(Date today);
	public List<Object[]> getStartedUsersIssueTypeWiseCountsForFieldMonrUsers(Date today);
	public List<Object[]> getLastOneHourUsersOfFMUser(Date lastHourTime,Date today);
}
