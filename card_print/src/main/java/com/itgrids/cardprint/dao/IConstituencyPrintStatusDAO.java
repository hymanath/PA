package com.itgrids.cardprint.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.cardprint.model.ConstituencyPrintStatus;

public interface IConstituencyPrintStatusDAO extends GenericDao<ConstituencyPrintStatus, Long> {
	
	public List<Long> getConstituencyPrintStatus(Long constituencyId);
	public List<Object[]> getConstituenciesByPrintVendor(Long printVendorId);
}
