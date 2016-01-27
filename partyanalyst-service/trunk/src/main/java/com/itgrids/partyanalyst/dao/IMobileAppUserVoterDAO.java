package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileAppUserVoter;

public interface IMobileAppUserVoterDAO extends GenericDao<MobileAppUserVoter, Long>{
	
	public List<Object[]> locationWiseOverView(Date StartDate,Date endDate,List<Long> locationIds,String locationType);
	public List<Object[]> voterRatings(Date startDate,Date endDate,List<Long> locationIds);
}
