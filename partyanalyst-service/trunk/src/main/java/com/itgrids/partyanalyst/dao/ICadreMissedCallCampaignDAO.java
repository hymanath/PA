package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreMissedCallCampaign;


public interface ICadreMissedCallCampaignDAO extends GenericDao<CadreMissedCallCampaign, Long>{
	public Long getAllMissedCallsCount(Date startDate, Date endDate);
	public List<String> getAllMobileNos(Date startDate, Date endDate);
}
