package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpRolesDAO;
import com.itgrids.partyanalyst.model.TdpRoles;

public class TdpRolesDAO extends GenericDaoHibernate<TdpRoles, Long>  implements ITdpRolesDAO {
	public TdpRolesDAO() {
		super(TdpRoles.class);
	}
	
	public List<Object[]> getRoles(){
		Query query = getSession().createQuery(" select model.tdpRolesId," +
				" model.role" +
				" from TdpRoles model " +
				" order by model.order ");
		
		return query.list();
	}
}
