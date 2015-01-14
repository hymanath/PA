package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpRolesDAO;
import com.itgrids.partyanalyst.model.TdpRoles;

public class TdpRolesDAO extends GenericDaoHibernate<TdpRoles, Long>  implements ITdpRolesDAO {
	public TdpRolesDAO() {
		super(TdpRoles.class);
	}
}
