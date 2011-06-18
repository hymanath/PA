package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IUserTrackingDAO;
import com.itgrids.partyanalyst.model.UserTracking;

public class UserTrackingDAO extends GenericDaoHibernate<UserTracking, Long> implements IUserTrackingDAO {

	public UserTrackingDAO() {
		super(UserTracking.class);
	}

}
