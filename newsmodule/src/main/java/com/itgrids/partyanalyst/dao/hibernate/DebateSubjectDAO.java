package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateSubjectDAO;
import com.itgrids.partyanalyst.model.DebateSubject;

public class DebateSubjectDAO extends GenericDaoHibernate<DebateSubject, Long> implements IDebateSubjectDAO{

	public DebateSubjectDAO() {
		super(DebateSubject.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateSubjectDetails(Long debateId)
	{
		return getHibernateTemplate().find("select model.subject , model.debateSubjectId from DebateSubject model  " +
				" where model.debate.debateId = ? ",debateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateDetalsForSelectedDates(Date fromDate,Date toDate)
	{
		Query query = getSession().createQuery("select model.debate.debateId,model.subject,model.debate.startTime " +
				" from DebateSubject model where date(model.debate.startTime) >= :fromDate and date(model.debate.startTime) <= :toDate ");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}

}
