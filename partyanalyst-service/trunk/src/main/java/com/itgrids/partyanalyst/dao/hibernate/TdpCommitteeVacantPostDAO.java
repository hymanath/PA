package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCommitteeVacantPostDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeVacantPost;

public class TdpCommitteeVacantPostDAO extends GenericDaoHibernate<TdpCommitteeVacantPost, Long> implements ITdpCommitteeVacantPostDAO {
	public TdpCommitteeVacantPostDAO() {
		super(TdpCommitteeVacantPost.class);
		
	}
}
