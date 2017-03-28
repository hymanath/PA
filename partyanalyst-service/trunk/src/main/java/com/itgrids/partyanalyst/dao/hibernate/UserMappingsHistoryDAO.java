package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IUserMappingsHistoryDAO;
import com.itgrids.partyanalyst.model.UserMappingsHistory;

public class UserMappingsHistoryDAO extends GenericDaoHibernate<UserMappingsHistory, Long> implements IUserMappingsHistoryDAO {

	public UserMappingsHistoryDAO() {
		super(UserMappingsHistory.class);
		
	}

}
