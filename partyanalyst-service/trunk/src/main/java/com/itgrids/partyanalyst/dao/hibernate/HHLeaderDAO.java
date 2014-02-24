package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHHLeaderDAO;
import com.itgrids.partyanalyst.model.HHLeader;


public class HHLeaderDAO extends GenericDaoHibernate<HHLeader,Long> implements IHHLeaderDAO {
	
	public HHLeaderDAO() {
		super(HHLeader.class);
	}
	
}
