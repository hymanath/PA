package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostStatusDAO;
import com.itgrids.partyanalyst.model.NominatedPostStatus;

public class NominatedPostStatusDAO extends GenericDaoHibernate<NominatedPostStatus, Long> implements INominatedPostStatusDAO{

	public NominatedPostStatusDAO() {
		super(NominatedPostStatus.class);
	}

	public List<Long> getStatusIdsList(){
		Query query = getSession().createQuery("select distinct model.nominatedPostStatusId from NominatedPostStatus model ");
		return query.list();
	}
	
	public List<Object[]> getAllNominatedStatusList(){
		Query query = getSession().createQuery("select model.nominatedPostStatusId,model.status from NominatedPostStatus model ");
		return query.list();
	}
}
