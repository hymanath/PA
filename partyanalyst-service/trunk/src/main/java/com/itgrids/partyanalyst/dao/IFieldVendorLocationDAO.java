package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FieldVendorLocation;

public interface IFieldVendorLocationDAO extends GenericDao<FieldVendorLocation, Long>{
	
	public List<Object[]> getVendors(Long stateId);
	public List<Object[]> getVendorDistricts(Long stateId,Long vendorId);
	public List<Object[]> getVendorConstituencies(Long vendorId,Long districtId);
	public List<Object[]> getConstituencyByVendor(Long fieldVendorId);
}
