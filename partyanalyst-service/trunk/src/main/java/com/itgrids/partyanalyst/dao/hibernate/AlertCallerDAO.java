package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertCallerDAO;
import com.itgrids.partyanalyst.model.AlertCaller;

public class AlertCallerDAO extends GenericDaoHibernate<AlertCaller, Long> implements IAlertCallerDAO{

	public AlertCallerDAO() {
		super(AlertCaller.class);
	}

	public List<Long> checkIsExist(String mobileNO,String callerName){
		return getSession().createQuery(" select distinct model.alertCallerId from AlertCaller model where model.mobileNo like '"+mobileNO+"' and model.callerName like '"+callerName+"' ").list();
	}
}
