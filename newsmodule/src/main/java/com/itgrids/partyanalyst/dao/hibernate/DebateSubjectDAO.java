package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateSubjectDAO;
import com.itgrids.partyanalyst.model.DebateSubject;

public class DebateSubjectDAO extends GenericDaoHibernate<DebateSubject, Long> implements IDebateSubjectDAO{

	public DebateSubjectDAO() {
		super(DebateSubject.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Object[]> getDebateSubjectDetails(Long debateId)
	{
		return getHibernateTemplate().find("select model.subject from DebateSubject model  " +
				" where model.debate.debateId = ? ",debateId);
	}

}
