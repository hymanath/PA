package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INominatedPostApplicationHistoryDAO;
import com.itgrids.partyanalyst.model.NominatedPostApplicationHistory;

public class NominatedPostApplicationHistoryDAO extends GenericDaoHibernate<NominatedPostApplicationHistory, Long> implements INominatedPostApplicationHistoryDAO{

	public NominatedPostApplicationHistoryDAO() {
		super(NominatedPostApplicationHistory.class);
		// TODO Auto-generated constructor stub
	}

}
