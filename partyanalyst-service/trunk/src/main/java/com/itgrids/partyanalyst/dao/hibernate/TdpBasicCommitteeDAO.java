package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.model.TdpBasicCommittee;

public class TdpBasicCommitteeDAO extends GenericDaoHibernate<TdpBasicCommittee, Long>  implements ITdpBasicCommitteeDAO {
	public TdpBasicCommitteeDAO() {
		super(TdpBasicCommittee.class);
	}
	
	public List<Object[]> getBasicCommittees(){
		Query query = getSession().createQuery(" select model.tdpBasicCommitteeId," +
				" model.name" +
				" from TdpBasicCommittee model " +
				" order by model.tdpBasicCommitteeId ");
		
		return query.list();
	}
}
