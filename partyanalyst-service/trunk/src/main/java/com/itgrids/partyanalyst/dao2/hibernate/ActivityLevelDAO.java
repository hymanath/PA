package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityLevelDAO;
import com.itgrids.partyanalyst.model.ActivityLevel;

public class ActivityLevelDAO extends GenericDaoHibernate<ActivityLevel, Long> implements IActivityLevelDAO{

	public ActivityLevelDAO() {
		super(ActivityLevel.class);
		
	}
	public List<Object[]> actvityLvlOrder(){
	    Query query=getSession().createQuery("select model.activityLevelId, model.level from ActivityLevel model " +
	        " order by model.activityOrderId");
	    return query.list();
	  
	  }

}
