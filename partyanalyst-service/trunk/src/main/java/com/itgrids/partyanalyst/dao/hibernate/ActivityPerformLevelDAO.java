package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityPerformLevelDAO;
import com.itgrids.partyanalyst.model.ActivityPerformLevel;


public class ActivityPerformLevelDAO extends GenericDaoHibernate<ActivityPerformLevel, Long>  implements IActivityPerformLevelDAO {
	public ActivityPerformLevelDAO() {
		super(ActivityPerformLevel.class);
	}
	
	
}
