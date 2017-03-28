package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActionTypeDAO;
import com.itgrids.partyanalyst.model.ActionType;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActionTypeDAO extends GenericDaoHibernate<ActionType, Long> implements IActionTypeDAO {

	public ActionTypeDAO(){
		super(ActionType.class);
	}
	public List<Object[]> getActionTypeList(){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.actionTypeId,model.typeName from ActionType model where model.actionTypeId in ("+IConstants.ALERT_ACTION_TYPE_ID+") ");
		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	
}
