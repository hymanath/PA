package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPfbReportDAO;
import com.itgrids.partyanalyst.model.PfbReport;

public class PfbReportDAO extends GenericDaoHibernate<PfbReport, Long> implements IPfbReportDAO{

	public PfbReportDAO() {
		super(PfbReport.class);
	}

	@Override
	public String getpfbDatils(Long userId, Long pfbId) {
		Query query = getSession().createQuery("select distinct model.key from PfbReport model " +
				" where model.user.userId = :userId and model.parlimentPoliticalFeedback.parlimentPoliticalFeedbackId =:pfbId " +
				" and model.isDeleted = 'N'");
		query.setParameter("userId", userId);
		query.setParameter("pfbId", pfbId);
		return (String)query.uniqueResult();
		
	}
	
	public int deletePfdDetails(String key)
	{
		Query query = getSession().createQuery(" delete from PfbReport model where model.key = :key");
		query.setParameter("key", key);
		int result = query.executeUpdate();
		return result;
	}
	
	public Long getPfdDetails(String key)
	{
		Query query = getSession().createQuery(" select distinct model.pfbReportId from PfbReport model where model.key = :key");
		query.setParameter("key", key);
		return (Long)query.uniqueResult();
	}
	

}
