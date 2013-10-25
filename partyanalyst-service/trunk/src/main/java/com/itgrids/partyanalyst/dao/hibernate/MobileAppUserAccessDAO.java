package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMobileAppUserAccessDAO;
import com.itgrids.partyanalyst.model.MobileAppUserAccess;
import com.itgrids.partyanalyst.utils.IConstants;

public class MobileAppUserAccessDAO extends GenericDaoHibernate<MobileAppUserAccess, Long> implements IMobileAppUserAccessDAO{

	public MobileAppUserAccessDAO() {
		super(MobileAppUserAccess.class);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getAuthorisedRecords(String uniqueCode,String macAddressId)
	{
		Object[] params = {uniqueCode,macAddressId};
		return getHibernateTemplate().find("select model.mobileAppUserAccessId from MobileAppUserAccess model where model.mobileAppUser.uniqueCode =? and " +
				"model.isAuthorised ='"+IConstants.TRUE+"' and  model.macAddress=?",params);
	}
	
	
}
