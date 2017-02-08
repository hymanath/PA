package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentLevelDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentLevel;

public class GovtDepartmentLevelDAO extends GenericDaoHibernate<GovtDepartmentLevel, Long> implements IGovtDepartmentLevelDAO{

	public GovtDepartmentLevelDAO() {
		super(GovtDepartmentLevel.class);
		
	}

	public List<Object[]> getDepartmentLevels(){
		Query query = getSession().createQuery("select model.govtDepartmentLevelId," +
											" model.levelName" +
											" from GovtDepartmentLevel model");
		return query.list();
	}
}
