package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileAppUserSmsStatus;

public interface IMobileAppUserSmsStatusDAO extends GenericDao<MobileAppUserSmsStatus, Long>{
	public List<MobileAppUserSmsStatus> getUsersLatestData(List<Long> userIds, Date fromDate, Date toDatee,List<String> userType);
	public List<Long> getNoOfSmsCountOfUser(Long userId,List<Date> dates);
}
