package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateReportDAO;
import com.itgrids.partyanalyst.model.DebateReport;

public class DebateReportDAO extends GenericDaoHibernate<DebateReport, Long> implements IDebateReportDAO{

	public DebateReportDAO() {
		super(DebateReport.class);
		// TODO Auto-generated constructor stub
	}

	public Long checkValidUserForReport(Long reportId,Long userId)
	{
		Query query = getSession().createQuery("select count(model.debateReportId) from DebateReport model " +
				" where model.debateReportId = :reportId and model.userId = :userId");
		query.setParameter("reportId", reportId);
		query.setParameter("userId", userId);
		return (Long)query.uniqueResult();
	}
	
	public int deleteDebateReport(String key)
	{
		Query query = getSession().createQuery("delete from DebateReport model where model.key = :key");
		query.setParameter("key", key);
		int i = query.executeUpdate();
		
		return i;
	}
	
	public String getDebateDatils(Long userId,Long debateId)
	{
		Query query = getSession().createQuery("select distinct model.key from DebateReport model " +
				" where model.user.userId = :userId and model.debate.debateId =:debateId ");
		query.setParameter("userId", userId);
		query.setParameter("debateId", debateId);
		return (String)query.uniqueResult();
	}

}
