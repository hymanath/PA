package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothInchargeHistoryDAO;
import com.itgrids.partyanalyst.model.BoothInchargeHistory;

public class BoothInchargeHistoryDAO extends GenericDaoHibernate<BoothInchargeHistory, Long> implements IBoothInchargeHistoryDAO
{
 
	public BoothInchargeHistoryDAO() {
		super(BoothInchargeHistory.class);
	}

}
