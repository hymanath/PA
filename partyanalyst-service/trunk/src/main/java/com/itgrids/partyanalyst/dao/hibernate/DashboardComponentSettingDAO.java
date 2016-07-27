package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDashboardComponentSettingDAO;
import com.itgrids.partyanalyst.model.DashboardComponentSetting;

public class DashboardComponentSettingDAO extends GenericDaoHibernate<DashboardComponentSetting,Long> implements IDashboardComponentSettingDAO {
	
	public DashboardComponentSettingDAO() {
		super(DashboardComponentSetting.class);
	}
	
	

}
