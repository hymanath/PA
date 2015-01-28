package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCommitteeRoleHistoryDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeRoleHistory;

public class TdpCommitteeRoleHistoryDAO extends GenericDaoHibernate<TdpCommitteeRoleHistory, Long>  implements ITdpCommitteeRoleHistoryDAO {
	public TdpCommitteeRoleHistoryDAO() {
		super(TdpCommitteeRoleHistory.class);
	}
}
