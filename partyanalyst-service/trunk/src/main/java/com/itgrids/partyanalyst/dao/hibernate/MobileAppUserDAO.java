package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileAppUserDAO;
import com.itgrids.partyanalyst.model.MobileAppUser;

public class MobileAppUserDAO extends GenericDaoHibernate<MobileAppUser, Long> implements IMobileAppUserDAO{

	public MobileAppUserDAO() {
		super(MobileAppUser.class);
		
	}
	
	public List<Object> checkUniqueCode(String uniqueCode)
	{
		return getHibernateTemplate().find("select model.mobileAppUserId from MobileAppUser model where model.uniqueCode = ?",uniqueCode);
	}
	
	public List<Object[]> getUserList()
	{
		return getHibernateTemplate().find("select model.mobileAppUserId,model.userName from MobileAppUser model");
	}
}
