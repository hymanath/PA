package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActionTypeStatusDAO;
import com.itgrids.partyanalyst.model.ActionTypeStatus;

public class ActionTypeStatusDAO extends GenericDaoHibernate<ActionTypeStatus, Long> implements IActionTypeStatusDAO {

	public ActionTypeStatusDAO(){
		super(ActionTypeStatus.class);
	}
	public List<Object[]> getAlertActionTypeWiseStatus(){
	  StringBuilder queryStr = new StringBuilder();
	    queryStr.append(" select " +
	   		" model.actionType.actionTypeId," +
	   		" model.actionType.typeName," +
	   		" model.actionTypeStatusId," +
	   		" model.status " +
	   		" from ActionTypeStatus model " +
	   		" group by model.actionType.actionTypeId," +
	   		" model.actionTypeStatusId " +
	   		" order by " +
	   		" model.actionType.actionTypeId ");
	    Query query = getSession().createQuery(queryStr.toString());
	     return query.list();
	}
}
