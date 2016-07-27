package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDashboardSettingTypeDAO;
import com.itgrids.partyanalyst.model.DashboardSettingType;

public class DashboardSettingTypeDAO extends GenericDaoHibernate<DashboardSettingType,Long> implements IDashboardSettingTypeDAO {
	
	public DashboardSettingTypeDAO() {
		super(DashboardSettingType.class);
	}
	
	

}
