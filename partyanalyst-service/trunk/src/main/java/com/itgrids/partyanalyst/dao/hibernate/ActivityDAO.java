package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.model.Activity;

public class ActivityDAO extends GenericDaoHibernate<Activity, Long> implements IActivityDAO{

	public ActivityDAO() {
		super(Activity.class);
		
	}
	
	public List<Object[]> getAllActivities(){
		
		Query query = getSession().createQuery(" SELECT model.activityId,model.activityName " +
				" FROM Activity model" +
				" WHERE model.isActive = 'Y' ");
		
		return query.list();
	} 

}
