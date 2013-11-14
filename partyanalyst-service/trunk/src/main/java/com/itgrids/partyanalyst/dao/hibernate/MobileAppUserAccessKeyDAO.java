package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileAppUserAccessKeyDAO;

import com.itgrids.partyanalyst.model.MobileAppUserAccessKey;
import org.hibernate.Query;

public class MobileAppUserAccessKeyDAO extends GenericDaoHibernate<MobileAppUserAccessKey,Long> implements IMobileAppUserAccessKeyDAO
{

	public MobileAppUserAccessKeyDAO() {
		super(MobileAppUserAccessKey.class);
		
	}
	
	public List<Object[]> checkUniqueCodeByAccesskey(String uniquecode,String accessKey)
	{
		Query query = getSession().createQuery("select model.mobileAppUser.mobileAppUserId,model.mobileAppUserAccesskeyId from MobileAppUserAccessKey model where model.mobileAppUser.uniqueCode =:uniquecode and model.accessKey =:accessKey");
		query.setParameter("uniquecode", uniquecode);
		query.setParameter("accessKey", accessKey);
		return query.list();
		
	}

}
