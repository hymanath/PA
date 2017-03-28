package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrRespondentCadreDAO;
import com.itgrids.partyanalyst.model.IvrRespondentCadre;

public class IvrRespondentCadreDAO extends GenericDaoHibernate<IvrRespondentCadre, Long> implements IIvrRespondentCadreDAO{

	public IvrRespondentCadreDAO() {
		super(IvrRespondentCadre.class);
	}
	
	public Long getRespondentIdByTdpCadreId(Long tdpCadreId){
		
		Query query = getSession().createQuery(" select distinct model.ivrRespondentId" +
							" from IvrRespondentCadre model" +
							" where model.tdpCadre.tdpCadreId = :tdpCadreId" +
							" and model.isDeleted = 'false'");
		query.setParameter("tdpCadreId", tdpCadreId);
		
		return (Long) query.uniqueResult();
	}
	
	public List<Long> getIvrRespndantDetails(Long tdpCadreId){
		
		Query query = getSession().createQuery(" select model.ivrRespondentId from IvrRespondentCadre model " +
				" where model.tdpCadreId = :tdpCadreId" +
				" and model.isDeleted = 'false' ");
		
		query.setParameter("tdpCadreId", tdpCadreId);
		
		return query.list();
	}
	public Long getIvrRespondentId(Long tdpCadreId){
		Query query = getSession().createQuery(" select distinct model.ivrRespondentId from IvrRespondentCadre model " +
				" where model.tdpCadre.tdpCadreId = :tdpCadreId " +
				" and model.isDeleted = 'false' ");
		
		query.setParameter("tdpCadreId", tdpCadreId);
		
		return (Long) query.uniqueResult();
	}
}
