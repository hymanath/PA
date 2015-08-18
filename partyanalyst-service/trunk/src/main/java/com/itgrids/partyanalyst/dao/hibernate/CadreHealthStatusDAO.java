package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreHealthStatusDAO;
import com.itgrids.partyanalyst.model.CadreHealthStatus;

public class CadreHealthStatusDAO extends GenericDaoHibernate<CadreHealthStatus, Long> implements ICadreHealthStatusDAO{

	public CadreHealthStatusDAO() {
		super(CadreHealthStatus.class);
	}
    
    public List<Object[]> getAllCadreHealthStatus(){
		
		Query query=getSession().createQuery(" select model.cadreHealthStatusId,model.status " +
				"from  CadreHealthStatus model ");
	    return query.list();
	}
}
