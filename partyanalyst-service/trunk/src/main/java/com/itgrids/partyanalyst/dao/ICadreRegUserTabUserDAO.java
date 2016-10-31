package com.itgrids.partyanalyst.dao;

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
}
