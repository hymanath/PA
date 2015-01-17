package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeRoleDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeRole;

public class TdpCommitteeRoleDAO extends GenericDaoHibernate<TdpCommitteeRole, Long>  implements ITdpCommitteeRoleDAO {
	public TdpCommitteeRoleDAO() {
		super(TdpCommitteeRole.class);
	}
	
	public List<Object[]> getAllCommitteeRoles(Long committeeId){
		//0committeeRoleid,1role name,2max nos
		Query query = getSession().createQuery("select model.tdpCommitteeRoleId,model.tdpRoles.role,model.maxMembers from TdpCommitteeRole model where " +
				" model.tdpCommittee.tdpCommitteeId =:committeeId order by model.tdpRoles.order");
		query.setParameter("committeeId", committeeId);
		return query.list();
	}
}
