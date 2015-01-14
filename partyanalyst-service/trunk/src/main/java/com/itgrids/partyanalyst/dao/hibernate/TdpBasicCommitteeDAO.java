package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.model.TdpBasicCommittee;

public class TdpBasicCommitteeDAO extends GenericDaoHibernate<TdpBasicCommittee, Long>  implements ITdpBasicCommitteeDAO {
	public TdpBasicCommitteeDAO() {
		super(TdpBasicCommittee.class);
	}
}
