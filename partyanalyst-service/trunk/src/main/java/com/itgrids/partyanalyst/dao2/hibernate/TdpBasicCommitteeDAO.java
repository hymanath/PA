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
				" order by model.order ");
		
		return query.list();
	}
	public List<Object[]> getBasicCommitteesByTypeId(Long committeeTypeId){
		StringBuilder str = new StringBuilder();
		str.append(" select model.tdpBasicCommitteeId," +
				" model.name" +
				" from TdpBasicCommittee model " );
		if(committeeTypeId == 1)
		str.append(" where model.tdpCommitteeTypeId =1");
		if(committeeTypeId == 2)
			str.append(" where model.tdpCommitteeTypeId !=1");	
		str.append(" order by model.order ");
		Query query = getSession().createQuery(str.toString());
		
		return query.list();
	}
}
