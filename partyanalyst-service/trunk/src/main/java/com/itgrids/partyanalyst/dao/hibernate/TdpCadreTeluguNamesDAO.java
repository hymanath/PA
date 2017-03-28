package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreTeluguNamesDAO;
import com.itgrids.partyanalyst.model.TdpCadreTeluguNames;

public class TdpCadreTeluguNamesDAO extends GenericDaoHibernate<TdpCadreTeluguNames, Long> implements ITdpCadreTeluguNamesDAO{

	public TdpCadreTeluguNamesDAO() {
		super(TdpCadreTeluguNames.class);
	}
	
	public List<String> getTeluguVoterNameByTdpCadreId(Long tdpCadreId){
		Query query = getSession().createQuery(" select model.teluguName " +
				" from TdpCadreTeluguNames model " +
				" where model.tdpCadre.tdpCadreId =:tdpCadreId ");
		
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	

	public List<Object[]> getTeluguVoterNameByTdpCadreIds(List<Long> tdpCadreIds){
		Query query = getSession().createQuery(" select model.tdpCadreId,model.teluguName " +
				" from TdpCadreTeluguNames model " +
				" where model.tdpCadre.tdpCadreId in (:tdpCadreIds) ");
		
		query.setParameterList("tdpCadreIds", tdpCadreIds);
		return query.list();
	}
	
	public List getModelByTdpCadreId(Long tdpCadreId){
		Query query = getSession().createQuery(" select model.tdpCadreTeluguNamesId " +
				" from TdpCadreTeluguNames model " +
				" where model.tdpCadre.tdpCadreId =:tdpCadreId ");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	
}
