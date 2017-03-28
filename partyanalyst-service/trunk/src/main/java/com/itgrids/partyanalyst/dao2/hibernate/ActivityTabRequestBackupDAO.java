package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityTabRequestBackupDAO;
import com.itgrids.partyanalyst.model.ActivityTabRequestBackup;

public class ActivityTabRequestBackupDAO extends GenericDaoHibernate<ActivityTabRequestBackup, Long> implements IActivityTabRequestBackupDAO{

	public ActivityTabRequestBackupDAO() {
		super(ActivityTabRequestBackup.class);
		
	}

}
