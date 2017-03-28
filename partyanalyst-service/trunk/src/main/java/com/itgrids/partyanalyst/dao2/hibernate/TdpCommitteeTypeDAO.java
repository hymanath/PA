package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCommitteeTypeDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeType;

public class TdpCommitteeTypeDAO extends GenericDaoHibernate<TdpCommitteeType, Long>  implements ITdpCommitteeTypeDAO {

	public TdpCommitteeTypeDAO() {
		super(TdpCommitteeType.class);
	}
}
