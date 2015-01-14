package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeLevel;

public class TdpCommitteeLevelDAO extends GenericDaoHibernate<TdpCommitteeLevel, Long>  implements ITdpCommitteeLevelDAO {
	public TdpCommitteeLevelDAO() {
		super(TdpCommitteeLevel.class);
	}
	
}
