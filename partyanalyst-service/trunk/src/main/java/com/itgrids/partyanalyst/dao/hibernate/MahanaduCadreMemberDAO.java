package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMahanaduCadreMemberDAO;
import com.itgrids.partyanalyst.model.MahanaduCadreMember;

public class MahanaduCadreMemberDAO extends GenericDaoHibernate<MahanaduCadreMember, Long> implements IMahanaduCadreMemberDAO{

	public MahanaduCadreMemberDAO() {
		super(MahanaduCadreMember.class);
	}

	
	public Integer deleteAllCadreDetailsByEvent(Long eventId)
	{
		Query query = getSession().createQuery("delete from MahanaduCadreMember where mahanaduGroupId =:eventId ");
		query.setParameter("eventId", eventId);
		
		int effectedRows = query.executeUpdate();
		return (Integer) query.uniqueResult();
	}
	
	public MahanaduCadreMember getMahanaduCadreMemberByEventId(Long eventId)
	{
		Query query = getSession().createQuery("select model from MahanaduCadreMember model where model.mahanaduGroupId =:eventId ");
		query.setParameter("eventId", eventId);
		
		return (MahanaduCadreMember) query.uniqueResult();
	}
	
	
}
