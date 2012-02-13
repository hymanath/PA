package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IElectionMinistersDAO;
import com.itgrids.partyanalyst.model.ElectionMinisters;

public class ElectionMinistersDAO extends GenericDaoHibernate<ElectionMinisters,Long> implements IElectionMinistersDAO{

	public ElectionMinistersDAO()
	{
		super(ElectionMinisters.class);
	}
}
