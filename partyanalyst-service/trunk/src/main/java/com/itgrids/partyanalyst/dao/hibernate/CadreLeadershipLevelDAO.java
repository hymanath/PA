package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreLeadershipLevelDAO;
import com.itgrids.partyanalyst.model.CadreLeadershipLevel;

public class CadreLeadershipLevelDAO extends GenericDaoHibernate<CadreLeadershipLevel, Long> implements ICadreLeadershipLevelDAO{

	public CadreLeadershipLevelDAO() {
		super(CadreLeadershipLevel.class);
	}
    
	public List<Object[]> getAllLeaderShipLevels(){
		
		Query query=getSession().createQuery(" select model.cadreLeadershipLevelId,model.leadershipLevel " +
				" from  CadreLeadershipLevel model " +
				" order by model.orderNo asc ");
	    return query.list();
	}
}
