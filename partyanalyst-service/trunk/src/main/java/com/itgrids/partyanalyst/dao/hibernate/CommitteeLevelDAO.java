package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommitteeLevelDAO;
import com.itgrids.partyanalyst.model.CommitteeLevel;

public class CommitteeLevelDAO extends GenericDaoHibernate<CommitteeLevel, Long> implements ICommitteeLevelDAO{

	public CommitteeLevelDAO() {
		super(CommitteeLevel.class);
		
	}

	
	public List<Object[]> getCommitteeLevels()
	{
		return getHibernateTemplate().find("select model.commiiteeLevelId,model.commiiteeLevel from CommitteeLevel model");
	}
}
