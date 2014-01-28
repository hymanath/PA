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

}
