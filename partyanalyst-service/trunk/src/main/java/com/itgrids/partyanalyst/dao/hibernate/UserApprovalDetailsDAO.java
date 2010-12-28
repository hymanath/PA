package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserApprovalDetailsDAO;
import com.itgrids.partyanalyst.model.UserApprovalDetails;

public class UserApprovalDetailsDAO extends GenericDaoHibernate<UserApprovalDetails, Long> implements IUserApprovalDetailsDAO  {

	public UserApprovalDetailsDAO() {
		super(UserApprovalDetails.class);		
	}

}
