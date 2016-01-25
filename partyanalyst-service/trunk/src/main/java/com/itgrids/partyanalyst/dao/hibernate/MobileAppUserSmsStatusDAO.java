package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileAppUserSmsStatusDAO;
import com.itgrids.partyanalyst.model.MobileAppUserSmsStatus;

public class MobileAppUserSmsStatusDAO extends GenericDaoHibernate<MobileAppUserSmsStatus, Long> implements IMobileAppUserSmsStatusDAO{

	public MobileAppUserSmsStatusDAO() {
		super(MobileAppUserSmsStatus.class);
		// TODO Auto-generated constructor stub
	}

}
