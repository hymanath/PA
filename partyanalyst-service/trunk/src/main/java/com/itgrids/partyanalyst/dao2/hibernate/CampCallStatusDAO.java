package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICampCallStatusDAO;
import com.itgrids.partyanalyst.model.CampCallStatus;

public class CampCallStatusDAO extends GenericDaoHibernate<CampCallStatus, Long> implements ICampCallStatusDAO{

	public CampCallStatusDAO() {
		super(CampCallStatus.class);
		// TODO Auto-generated constructor stub
	}

	
	public List<Object[]> getCallStatusList()
	{
		return getHibernateTemplate().find("select model.campCallStatusId,model.status from CampCallStatus model order by model.campCallStatusId ");
	}
}
