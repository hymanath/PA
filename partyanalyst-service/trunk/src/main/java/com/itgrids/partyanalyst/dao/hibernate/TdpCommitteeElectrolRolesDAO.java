package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolRolesDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeElectrolRoles;

public class TdpCommitteeElectrolRolesDAO extends GenericDaoHibernate<TdpCommitteeElectrolRoles, Long>  implements
		ITdpCommitteeElectrolRolesDAO {
	public TdpCommitteeElectrolRolesDAO() {
		super(TdpCommitteeElectrolRoles.class);
	}
}
