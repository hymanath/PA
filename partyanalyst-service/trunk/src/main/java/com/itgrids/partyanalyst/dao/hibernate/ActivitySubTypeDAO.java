package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivitySubTypeDAO;
import com.itgrids.partyanalyst.model.ActivitySubType;

public class ActivitySubTypeDAO extends GenericDaoHibernate<ActivitySubType, Long> implements IActivitySubTypeDAO{

	public ActivitySubTypeDAO() {
		super(ActivitySubType.class);
		
	}

	public List<Object[]> activityTypeNames(){
		Query query=getSession().createQuery("select AST.activitySubTypeId, AST.subType from ActivitySubType AST " +
				"where AST.isDeleted= 'N' order by AST.subType asc");
		return query.list();
	}
 }
