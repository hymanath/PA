package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterFlagDAO;
import com.itgrids.partyanalyst.model.VoterFlag;

public class VoterFlagDAO extends GenericDaoHibernate<VoterFlag, Long> implements IVoterFlagDAO{

	public VoterFlagDAO() {
		super(VoterFlag.class);
		
	}

}
