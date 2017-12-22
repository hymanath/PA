package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmOfficerUser;

public interface IPmOfficerUserDAO extends GenericDao<PmOfficerUser, Long> {

	public Object[] getPmOffceUserDetails(Long userId);
	public List<Long> getPmDeptIdByUserId(Long userId);
	public List<Long> getPmDeptStatusIdByUserIdsLst(Long userId);
}
