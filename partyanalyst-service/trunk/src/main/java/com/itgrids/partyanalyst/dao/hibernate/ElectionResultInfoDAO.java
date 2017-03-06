package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IElectionResultInfoDAO;
import com.itgrids.partyanalyst.model.ElectionResultInfo;

public class ElectionResultInfoDAO extends  GenericDaoHibernate<ElectionResultInfo, Long> implements IElectionResultInfoDAO{

	public ElectionResultInfoDAO(){
		super(ElectionResultInfo.class);
	}
}
