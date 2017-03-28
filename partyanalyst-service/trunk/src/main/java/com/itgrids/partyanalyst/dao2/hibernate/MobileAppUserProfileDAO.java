package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileAppUserProfileDAO;
import com.itgrids.partyanalyst.model.MobileAppUserProfile;

public class MobileAppUserProfileDAO extends GenericDaoHibernate<MobileAppUserProfile, Long> implements IMobileAppUserProfileDAO{

	public MobileAppUserProfileDAO() {
		super(MobileAppUserProfile.class);
		
	}
	public List<Object[]> getMobileNoByUniquecode(String uniqueCode)
	{
		return getHibernateTemplate().find("select model.mobileAppUser.mobileNo,model.firstName,model.lastName,model.mobileAppUser.email,model.mobileAppUser.mobileAppUserId from MobileAppUserProfile model where model.mobileAppUser.uniqueCode=?",uniqueCode);
		
	}
	public List<Object> getMobileAppUserProfileId(Long mobileAppUserId)
	{
		return getHibernateTemplate().find("select model.mobileAppUserProfileId from MobileAppUserProfile model where model.mobileAppUser.mobileAppUserId = ?",mobileAppUserId);
	}

}
