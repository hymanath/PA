package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IParlimentPoliticalFeedbackDAO;
import com.itgrids.partyanalyst.model.ParlimentPoliticalFeedback;

public class ParlimentPoliticalFeedbackDAO extends GenericDaoHibernate<ParlimentPoliticalFeedback, Long> implements IParlimentPoliticalFeedbackDAO{

	public ParlimentPoliticalFeedbackDAO() {
		super(ParlimentPoliticalFeedback.class);
	}

	
	@SuppressWarnings("unchecked")
	public List<Object[]> getParlimentPoliticalFeedBacks(Date date,Long pcId)
	{
		Query query = getSession().createQuery("select model.parlimentPoliticalFeedbackId , model.parlimentConstituency.constituencyId ,model.parlimentConstituency.name " +
				" , model.summary ,model.createdDate from ParlimentPoliticalFeedback model where " +
				" model.parlimentConstituency.constituencyId = :pcId and " +
				" date(model.createdDate) = :date and model.isDeleted = 'N'");
		query.setParameter("date", date);
		query.setParameter("pcId", pcId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getParlimentPoliticalFeedBacksById(Long pcId)
	{
		Query query = getSession().createQuery("select distinct model.parlimentPoliticalFeedbackId , model.parlimentConstituency.constituencyId ,model.parlimentConstituency.name " +
				"  , model.summary ,model.createdDate from ParlimentPoliticalFeedback model where " +
				"  model.parlimentPoliticalFeedbackId = :pcId and " +
				"  model.isDeleted = 'N'");
		query.setParameter("pcId", pcId);
		return query.list();
	}
	
	public int deleteRecord(Long id)
	{
		Query query = getSession().createQuery(" delete from ParlimentPoliticalFeedback model where " +
				" model.parlimentPoliticalFeedbackId = :id");
		query.setParameter("id", id);
		int resutlt = query.executeUpdate();
		return resutlt;
	}
	
	public int deletePoliticalFeedBack(Long id)
	{
		Query query = getSession().createQuery(" update  ParlimentPoliticalFeedback model set model.isDeleted = 'Y' " +
				" where model.parlimentPoliticalFeedbackId = :id");
		query.setParameter("id", id);
		int resutlt = query.executeUpdate();
		return resutlt;
	}
}
