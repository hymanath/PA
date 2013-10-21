package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileAppUserProfileDAO;
import com.itgrids.partyanalyst.model.MobileAppUserProfile;

public class MobileAppUserProfileDAO extends GenericDaoHibernate<MobileAppUserProfile, Long> implements IMobileAppUserProfileDAO{

	public MobileAppUserProfileDAO() {
		super(MobileAppUserProfile.class);
		
	}

}
