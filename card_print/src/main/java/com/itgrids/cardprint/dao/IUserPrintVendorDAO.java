package com.itgrids.cardprint.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.cardprint.model.UserPrintVendor;

public interface IUserPrintVendorDAO extends GenericDao<UserPrintVendor, Long> {
	
	public List<Long> getPrintVendorIdByUserId(Long userId);
}
