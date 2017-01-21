package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActionTypeStatusDAO;
import com.itgrids.partyanalyst.model.ActionTypeStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActionTypeStatusDAO extends GenericDaoHibernate<ActionTypeStatus, Long> implements IActionTypeStatusDAO {

	public ActionTypeStatusDAO(){
		super(ActionTypeStatus.class);
	}
	public List<Object[]> getActionTypeList(){
		StringBuilder sb = new StringBuilder();
		sb.append("select " +
				  " model.actionType.actionTypeId, " +//0
				  " model.actionType.typeName, " +//1
				  " model.actionTypeStatusId, " +//2
				  " model.status " +//3
				  " from ActionTypeStatus model " +
				  " where model.actionType.actionTypeId in ("+IConstants.ALERT_ACTION_TYPE_ID+") " +
				  " group by " +
				  " model.actionType.actionTypeId, model.actionTypeStatusId " +
				  " order by " +
				  " model.actionType.actionTypeId, model.actionTypeStatusId");
		Query query = getSession().createQuery(sb.toString());
		return query.list();
	}
	public List<Object[]> getAlertActionTypeWiseStatus(Long actionTypeId){
	  StringBuilder queryStr = new StringBuilder();
	    queryStr.append(" select " +
	   		" model.actionType.actionTypeId," +
	   		" model.actionType.typeName," +
	   		" model.actionTypeStatusId," +
	   		" model.status " +
	   		" from ActionTypeStatus model " );
	    if(actionTypeId > 0l ){
	    	queryStr.append(" where model.actionType.actionTypeId=:actionTypeId ");
	    }
        queryStr.append(" group by model.actionType.actionTypeId," +
	   		            " model.actionTypeStatusId " +
	   		            " order by " +
	   		            " model.actionType.actionTypeId ");
	    Query query = getSession().createQuery(queryStr.toString());
	    if(actionTypeId > 0l){
	     query.setParameter("actionTypeId", actionTypeId);
	    }
	     return query.list();
	}
}
