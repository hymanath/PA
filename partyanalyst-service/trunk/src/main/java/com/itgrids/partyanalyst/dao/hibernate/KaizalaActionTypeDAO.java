package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaActionTypeDAO;
import com.itgrids.partyanalyst.dao.IKaizalaActionsDAO;
import com.itgrids.partyanalyst.model.KaizalaActionType;
import com.itgrids.partyanalyst.model.KaizalaActions;

public class KaizalaActionTypeDAO extends GenericDaoHibernate<KaizalaActionType, Long> implements IKaizalaActionTypeDAO {
	public KaizalaActionTypeDAO() {
		super(KaizalaActionType.class); 
	}
	
	public Long getActionTypeId(String actionType){
		Query query = getSession().createQuery(" select model.kaizalaActionTypeId " +
				" from KaizalaActionType model " +
				" where model.actionType=:actionType ");
		query.setParameter("actionType", actionType);
		return (Long)query.uniqueResult();
	}
	
}