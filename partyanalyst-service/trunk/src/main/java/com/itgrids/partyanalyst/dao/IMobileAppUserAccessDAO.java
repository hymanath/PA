package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileAppUserAccess;

public interface IMobileAppUserAccessDAO extends GenericDao<MobileAppUserAccess, Long>{
	
	public List<Object> getAuthorisedRecords(String uniqueCode,String macAddressId);
	public List<Object[]> getMobileAppUserdetails();
	
	public List<Long> getMobileAppUserAccessIds(List<Long> mobileAppUserIds);
	
	public List<Object> getMobileAppUserAccesId(Long mobileAppUserId);
	
	public List<Object[]> getMobileAppUserdetailsByMobileAppUserId(Long mobileAppUserId);
	
	public List<Object[]> getSuperAdminDetailsByMobileAppUserId(Long mobileAppUserId);
	
	public List getMobileAppUserdetailsByUserId(Long mobileAppUserId);
	
	public List<Object[]> getMobileAppUserAuthorisedTime();
	
}
