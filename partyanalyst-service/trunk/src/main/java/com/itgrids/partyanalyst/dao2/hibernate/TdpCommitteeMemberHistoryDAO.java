package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberHistoryDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeMemberHistory;


public class TdpCommitteeMemberHistoryDAO extends GenericDaoHibernate<TdpCommitteeMemberHistory, Long>implements ITdpCommitteeMemberHistoryDAO{

	public TdpCommitteeMemberHistoryDAO() {
		super(TdpCommitteeMemberHistory.class);
	}

}
