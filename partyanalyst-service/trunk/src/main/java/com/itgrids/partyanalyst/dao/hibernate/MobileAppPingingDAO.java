package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileAppPingingDAO;
import com.itgrids.partyanalyst.model.MobileAppPinging;

public class MobileAppPingingDAO extends GenericDaoHibernate<MobileAppPinging, Long> implements IMobileAppPingingDAO{

	public MobileAppPingingDAO() {
		super(MobileAppPinging.class);
		
	}
	public List<Object[]> getPingingTypeIdByType(Long mobileAppUserId)
	{
		return getHibernateTemplate().find("select model.pingingType.type,model.pingTime from MobileAppPinging model where model.mobileAppUser.mobileAppUserId = ?",mobileAppUserId);
	}


}
