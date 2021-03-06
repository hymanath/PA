package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmOfficerUser;

public interface IPmOfficerUserDAO extends GenericDao<PmOfficerUser, Long> {

	public  List<Object[]> getPmOffceUserDetails(Long userId);
	public List<Object[]> getPmDeptIdByUserId(Long userId);
	public List<Long> getPmDeptStatusIdByUserIdsLst(Long userId,String isDashboard);
	public List<Long> getPmDeptDesignationIdByUserId(Long userId);
	public List<Object[]> getPmOffceDetailsByUserIdsList(List<Long> userIdsList);
}
