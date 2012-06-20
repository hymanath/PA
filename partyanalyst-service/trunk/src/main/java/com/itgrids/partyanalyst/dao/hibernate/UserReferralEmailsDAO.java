package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserReferralEmailsDAO;
import com.itgrids.partyanalyst.model.UserReferralEmails;

public class UserReferralEmailsDAO extends GenericDaoHibernate<UserReferralEmails, Long> implements IUserReferralEmailsDAO{

	public UserReferralEmailsDAO()
	{
		super(UserReferralEmails.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getUserReferencedEmails(Long userId)
	{
		return getHibernateTemplate().find("select model.email from UserReferralEmails model where model.user.userId = ?",userId);
	}
}
