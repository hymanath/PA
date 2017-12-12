package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelHierarchyDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeLevelHierarchy;

public class TdpCommitteeLevelHierarchyDAO extends GenericDaoHibernate<TdpCommitteeLevelHierarchy, Long> implements ITdpCommitteeLevelHierarchyDAO {

	public TdpCommitteeLevelHierarchyDAO() {
	   super(TdpCommitteeLevelHierarchy.class);	
	}
	
	public List<Long> getUpperLevelTdpCommitteeId(Long tdpCommiteeLevelId){
		Query query = getSession().createQuery(" select distinct model.committeeUpperLevelId from TdpCommitteeLevelHierarchy model where model.tdpCommitteeLevelId=:tdpCommiteeLevelId ");
		 query.setParameter("tdpCommiteeLevelId", tdpCommiteeLevelId);
	return query.list();
	}
}
