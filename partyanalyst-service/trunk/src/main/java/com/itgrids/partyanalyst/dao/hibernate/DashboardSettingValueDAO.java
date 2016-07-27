package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDashboardSettingValueDAO;
import com.itgrids.partyanalyst.model.DashboardSettingValue;

public class DashboardSettingValueDAO extends GenericDaoHibernate<DashboardSettingValue,Long> implements IDashboardSettingValueDAO {
	
	public DashboardSettingValueDAO() {
		super(DashboardSettingValue.class);
	}
	
	

}
