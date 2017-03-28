package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreHistoryDAO;
import com.itgrids.partyanalyst.model.TdpCadreHistory;

public class TdpCadreHistoryDAO extends GenericDaoHibernate<TdpCadreHistory, Long> implements ITdpCadreHistoryDAO {

	public TdpCadreHistoryDAO()
	{
		super(TdpCadreHistory.class);
	}

}
