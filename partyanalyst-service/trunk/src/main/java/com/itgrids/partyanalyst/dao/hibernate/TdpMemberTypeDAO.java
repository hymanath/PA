package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpMemberTypeDAO;
import com.itgrids.partyanalyst.model.TdpMemberType;

public class TdpMemberTypeDAO extends GenericDaoHibernate<TdpMemberType, Long> implements ITdpMemberTypeDAO{

	public TdpMemberTypeDAO() {
		super(TdpMemberType.class);
		
	}

	public List<Object[]> getCadreMemberTypeListByYear(Long year){
		Query query = getSession().createQuery(" select model.tdpMemberTypeId,model.memberType from TdpMemberType model" +
				"  order by model.memberType asc ");
		return query.list();
	}
}
