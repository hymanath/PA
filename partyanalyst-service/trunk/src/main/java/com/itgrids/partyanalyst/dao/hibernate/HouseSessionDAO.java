package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHouseSessionDAO;
import com.itgrids.partyanalyst.model.HouseSession;

public class HouseSessionDAO extends GenericDaoHibernate<HouseSession, Long> implements IHouseSessionDAO{

	public HouseSessionDAO() {
		super(HouseSession.class);
	}

	public List<Object[]> getAllSessions(){
		Query query = getSession().createQuery("select model.houseSessionId," +
				" model.sessionName " +
				" from HouseSession model " +
				" where model.adminHouse.adminHouseId = 3" +
				" and model.isActive = 'Y' and model.isDeleted = 'N' ");
		return query.list();
	}
}
