package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IReportEmailDAO;
import com.itgrids.partyanalyst.model.ReportEmail;

public class ReportEmailDAO extends GenericDaoHibernate<ReportEmail, Long> implements IReportEmailDAO {
	public ReportEmailDAO(){
		super(ReportEmail.class);
	}
	public List<Object[]> getEmailList(Long emailReportId){
		Query query = getSession().createQuery("select reportEmail.userEmail.userEmailId, reportEmail.userEmail.email from ReportEmail reportEmail " +
											   " where reportEmail.emailReport.emailReportId = :emailReportId");
		query.setParameter("emailReportId", emailReportId);
		return query.list();
	}
}
