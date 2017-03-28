package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCommitteeDesignationDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeDesignation;

public class TdpCommitteeDesignationDAO extends GenericDaoHibernate<TdpCommitteeDesignation, Long>  implements ITdpCommitteeDesignationDAO {
	public TdpCommitteeDesignationDAO() {
		super(TdpCommitteeDesignation.class);
	}
}
