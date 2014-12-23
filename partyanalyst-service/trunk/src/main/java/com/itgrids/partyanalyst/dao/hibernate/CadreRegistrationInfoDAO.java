package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegistrationInfoDAO;
import com.itgrids.partyanalyst.model.CadreRegistrationInfo;

public class CadreRegistrationInfoDAO extends GenericDaoHibernate<CadreRegistrationInfo, Long> implements ICadreRegistrationInfoDAO{

	public CadreRegistrationInfoDAO() {
		super(CadreRegistrationInfo.class);
		
	}
	
	public List<Object[]> getCountByReportLevel(Long Id)
	{
		StringBuilder str = new StringBuilder();
		Query query = getSession().createQuery("select model.reportLevelValue,model.count from CadreRegistrationInfo model" +
				" where model.regionScopes.regionScopesId = :Id");
		query.setParameter("Id", Id);
		return query.list();
	}

}
