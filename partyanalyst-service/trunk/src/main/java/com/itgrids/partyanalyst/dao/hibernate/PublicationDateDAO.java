package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.model.PublicationDate;

public class PublicationDateDAO extends
		GenericDaoHibernate<PublicationDate, Long> implements
		IPublicationDateDAO {	
	
	public PublicationDateDAO(){
		super(PublicationDate.class);
		
	}
	@SuppressWarnings("unchecked")
	public List<PublicationDate> getPublicationDates(){
		return getHibernateTemplate().find("from PublicationDate model order by model.year desc " );
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllPublicationDates(){
		return getHibernateTemplate().find("Select model.publicationDateId, model.date from PublicationDate model order by model.year desc ");
	}
	public List<Long> getPreviousPublicationIds(Long publicationDateId)
	{
		Query query = getSession().createQuery("select distinct model.publicationDateId from PublicationDate model " +
				" where model.date < (select model2.date from PublicationDate model2 where model2.publicationDateId = :publicationDateId) " +
				" order by model.date desc ");
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	public String getNamePublicationDateId(Long publicationDateId)
	{
		Query query = getSession().createQuery("select model.name from PublicationDate model where model.publicationDateId = :publicationDateId");
		query.setParameter("publicationDateId", publicationDateId);
		return (String) query.uniqueResult();
	}
	
	public Long getLatestPublicationId(){
		Query query = getSession().createQuery("select model.publicationDateId from PublicationDate model where model.date = (select max(model1.date) from PublicationDate model1)");
		
		return (Long) query.uniqueResult();
	}
	public Long getLatestPublicationIdByConstiId(Long constituencyId){
       Query query = getSession().createQuery("select model.publicationDateId from PublicationDate model where model.date = (select max(model1.booth.publicationDate.date) from BoothPublicationVoter model1 where model1.booth.constituency.constituencyId = :constituencyId )");
       query.setParameter("constituencyId", constituencyId);
		return (Long) query.uniqueResult();
		
	}
}
