package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityLocationPublicAttendanceDAO;
import com.itgrids.partyanalyst.model.ActivityLocationPublicAttendance;

public class ActivityLocationPublicAttendanceDAO extends GenericDaoHibernate<ActivityLocationPublicAttendance, Long> implements IActivityLocationPublicAttendanceDAO{

	public ActivityLocationPublicAttendanceDAO() {
		super(ActivityLocationPublicAttendance.class);
	}
	
	public ActivityLocationPublicAttendance checkWhetherExistingOrNew(String uniqueKey){
		Query query = getSession().createQuery(" select model from ActivityLocationPublicAttendance model " +
				" where model.uniqueKey=:uniqueKey ");
		query.setParameter("uniqueKey", uniqueKey);
		return (ActivityLocationPublicAttendance)query.uniqueResult();
	}

}
