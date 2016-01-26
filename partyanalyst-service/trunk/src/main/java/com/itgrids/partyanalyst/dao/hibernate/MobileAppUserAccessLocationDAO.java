package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMobileAppUserAccessLocationDAO;
import com.itgrids.partyanalyst.model.MobileAppUserAccessLocation;
import com.itgrids.partyanalyst.model.MobileAppUserSmsDetails;

public class MobileAppUserAccessLocationDAO extends GenericDaoHibernate<MobileAppUserAccessLocation, Long> implements IMobileAppUserAccessLocationDAO{

	public MobileAppUserAccessLocationDAO() {
		super(MobileAppUserAccessLocation.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<MobileAppUserAccessLocation> getMobileAppUserAccessLocations(Long mobileAppUserId)
	{
		Query query = getSession().createQuery("select model from MobileAppUserAccessLocation model where model.mobileAppUser.mobileAppUserId =:mobileAppUserId");
		query.setParameter("mobileAppUserId", mobileAppUserId);
		return query.list();
	}

}
