package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpMemberUnionTabUserRelationDAO;
import com.itgrids.partyanalyst.model.TdpMemberUnionTabUserRelation;

public class TdpMemberUnionTabUserRelationDAO extends GenericDaoHibernate<TdpMemberUnionTabUserRelation, Long> 
implements ITdpMemberUnionTabUserRelationDAO{

	public TdpMemberUnionTabUserRelationDAO() {
		super(TdpMemberUnionTabUserRelation.class);
	}

	public List<Object[]> getAccessMemberTypesBtTabUserId( Long unionTabUserId)
	{
		
		Query query =  getSession().createQuery(" select distinct model.tdpMemberType.tdpMemberTypeId,model.tdpMemberType.memberType  from TdpMemberUnionTabUserRelation model where model.unionTabUserId = :unionTabUserId and " +
				"model.isActive ='Y' and model.isDeleted='false' and model.tdpMemberType.isDeleted='false' order by model.tdpMemberType.orderNo  ");
		
		query.setParameter("unionTabUserId", unionTabUserId);
		return query.list();
	}
}
