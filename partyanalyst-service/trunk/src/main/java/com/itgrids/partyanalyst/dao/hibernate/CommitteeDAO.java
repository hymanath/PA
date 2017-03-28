package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICommitteeDAO;
import com.itgrids.partyanalyst.model.Committee;

public class CommitteeDAO extends GenericDaoHibernate<Committee, Long> implements ICommitteeDAO{

	public CommitteeDAO() {
		super(Committee.class);
		
	}

	
	public List<Long> getAllCommitteeLevelValuesByCommitteeLevel(Long committeeLevelId)
	{		
		Query query = getSession().createQuery("select distinct model.commiiteeLevelValue from Committee model where model.committeeLevel.commiiteeLevelId = :committeeLevelId");		
		query.setParameter("committeeLevelId", committeeLevelId);
		return query.list();
	}
	

	public List<Object[]> getAllCommitteesForCommitteeLevelValues(Long committeeLevelValueId,Long scopeId)
	{		
		Query query = getSession().createQuery("select distinct model.committeeId,model.name from Committee model where model.commiiteeLevelValue = :committeeLevelValueId and model.committeeLevel.commiiteeLevelId = :scopeId order by model.name");		
		query.setParameter("committeeLevelValueId", committeeLevelValueId);
		query.setParameter("scopeId", scopeId);
		
		return query.list();
	}
	
}
