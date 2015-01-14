package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCommitteeRoleDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeRole;

public class TdpCommitteeRoleDAO extends GenericDaoHibernate<TdpCommitteeRole, Long>  implements ITdpCommitteeRoleDAO {
	public TdpCommitteeRoleDAO() {
		super(TdpCommitteeRole.class);
	}
}
