package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAbusedCommentsDAO;
import com.itgrids.partyanalyst.dao.impl.IAlertSourceUserDAO;
import com.itgrids.partyanalyst.model.AbusedComments;
import com.itgrids.partyanalyst.model.AlertSourceUser;

public class AlertSourceUserDAO extends
		GenericDaoHibernate<AlertSourceUser, Long> implements
		IAlertSourceUserDAO {

	public AlertSourceUserDAO() {
		super(AlertSourceUser.class);
	}
	
	public List<Object[]> getAlertSourceUsersByUser(Long userId)
	{
		return getHibernateTemplate().find("select distinct model.alertSource.alertSourceId,model.alertSource.source from AlertSourceUser model where model.userId = ? and model.isDeleted='N' ",userId);
	}
	public List<Long> getAlertSourceUserIds(Long userId)
	{
		return getHibernateTemplate().find("select distinct model.alertSourceId from AlertSourceUser model where model.userId = ? and model.isDeleted='N' ",userId);
	}
}
