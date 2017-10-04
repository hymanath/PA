package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaActionsDAO;
import com.itgrids.partyanalyst.model.KaizalaActions;

public class KaizalaActionsDAO extends GenericDaoHibernate<KaizalaActions, Long> implements IKaizalaActionsDAO {
	public KaizalaActionsDAO() {
		super(KaizalaActions.class); 
	}
	
	public List<Long> getKaizalaActionId(String actionId){
		Query query = getSession().createQuery(" select model.kaizalaActionsId " +
				" from KaizalaActions model " +
				" where model.actionId=:actionId and model.isDeleted='N' ");
		query.setParameter("actionId", actionId);
		
		return query.list();
		
	}
	
	public String getAccessToken(){
		Query query = getSession().createSQLQuery(" select kit.access_token as token from kaizala_token_info kit").addScalar("token", Hibernate.STRING);
		return (String) query.uniqueResult();
		
	}
	
	public Long checkexistenceOrNot(String actionId){
		Query query = getSession().createQuery(" select model.kaizalaActionsId " +
				" from KaizalaActions model " +
				" where model.actionId =:actionId and model.isDeleted='N' ");
		
		query.setParameter("actionId", actionId);
		return (Long) query.uniqueResult();
		
	}
}