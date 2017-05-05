package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertCallerDAO;
import com.itgrids.partyanalyst.model.AlertCaller;

public class AlertCallerDAO extends GenericDaoHibernate<AlertCaller, Long> implements IAlertCallerDAO{

	public AlertCallerDAO() {
		super(AlertCaller.class);
	}

	@SuppressWarnings("unchecked")
	public List<Long> checkIsExist(String mobileNO,String callerName){
		return getSession().createQuery(" select distinct model.alertCallerId from AlertCaller model where model.mobileNo like '"+mobileNO+"' and model.callerName like '"+callerName+"' " +
				" and model.socialMediaTypeId is null ").list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> checkIsExistSocialCaller(String mobileNo,String account,Long socialMediaTypeId){
		return getSession().createQuery(" select distinct model.alertCallerId from AlertCaller model where model.mobileNo like '"+mobileNo+"' and model.accountId like '"+account+"' " +
				" and model.socialMediaTypeId ="+socialMediaTypeId+" ").list();
	}
	
}
