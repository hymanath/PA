package com.itgrids.partyanalyst.dao;


import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ConstituencyPrintStatus;

public interface IConstituencyPrintStatusDAO extends GenericDao<ConstituencyPrintStatus, Long> {
	public List<Object[]> getConstListByVendorId(Long vendorId,Long districtId);
	public List<Object[]> getDstrListByVendorId(Long vendorId);
	public List<Object[]> getStatusWisePrintingConstituencyDetails(Long stateId,Long vendorId,Date fromDate,Date toDate);
	public List<Object[]> getStatusWiseDistrictWisePrintingConstituencyDetails(Long stateId,Long vendorId,Date fromDate,Date toDate);
	public List<Object[]> getStatusWiseVendorWiseConstituencyDetails(Long stateId,Date fromDate,Date toDate);
	
	public List<Long> getConstituencyPrintStatusIds(Long printVendorId , Long constituencyId);
	public String getPresentStatusForAConstituencyByVendor(Long vendorId , Long constituencyId);
}