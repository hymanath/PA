package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertImpactDAO;
import com.itgrids.partyanalyst.model.AlertComment;
import com.itgrids.partyanalyst.model.AlertImpact;

public class AlertImpactDAO extends GenericDaoHibernate<AlertImpact, Long>
		implements IAlertImpactDAO {
	public AlertImpactDAO() {
		super(AlertImpact.class);
	}
	
	public List<Long> getIdOfName(String impact){
   		Query query = getSession().createQuery(" select " +
   				" model.alertImpactId " +
   				" from AlertImpact model where model.impact=:impact");
   		query.setParameter("impact", impact);
   		return query.list();
     }

}
