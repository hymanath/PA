package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMobileAppUserSmsDetailsDAO;
import com.itgrids.partyanalyst.model.MobileAppUser;
import com.itgrids.partyanalyst.model.MobileAppUserSmsDetails;

public class MobileAppUserSmsDetailsDAO extends GenericDaoHibernate<MobileAppUserSmsDetails, Long> implements IMobileAppUserSmsDetailsDAO{

	public MobileAppUserSmsDetailsDAO() {
		super(MobileAppUserSmsDetails.class);
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unchecked")
	public List<MobileAppUserSmsDetails> getMobileAppUserSmsDetails(Long mobileAppUserId)
	{
		Query query = getSession().createQuery("select model from MobileAppUserSmsDetails model where model.mobileAppUser.mobileAppUserId =:mobileAppUserId " +
				" and model.mobileAppUser.isDeleted = 'N' and model.mobileAppUser.isEnabled='Y' ");
		query.setParameter("mobileAppUserId", mobileAppUserId);
		return query.list();
	}
}
