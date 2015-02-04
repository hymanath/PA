package com.itgrids.partyanalyst.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreIVREnquiry;

public interface ICadreIVREnquiryDAO  extends GenericDao<CadreIVREnquiry, Long>{
	public List<Object[]> getLocationWiseEnquiryInfo(String locationLvl,Long locationValue,Long userId,String resultType);
	public BigDecimal getTotalReceivedCount(Date startDate, Date endDate,List<Long> locationTypeIds,List<Long> constiIds);
	public BigDecimal getTotalDeliveredCount(Date startDate, Date endDate,List<Long> locationTypeIds,List<Long> constiIds);
	public List<Object[]> getNoOfLocationCountByTypeId(List<Long> locationTypeIds,Date startDate, Date endDate,List<Long> constiIds);	
	public List<Date> getAvailableDates();
	public List<Object[]> getLocationIdsByTypeId(List<Long> locationTypeIds,Date startDate, Date endDate,List<Long> constiIds);
	
	public List<Object[]> getDeliveredAndReceivedCount(List<Long> locationTypeIds,Date startDate, Date endDate,Long locationTypeId);
	
	public List<Object[]> getMandalRecievedCountConstituency(List<Long> constituencyIds);
}
