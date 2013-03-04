package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.model.VoterCastInfo;

public class VoterCastInfoDAO extends GenericDaoHibernate<VoterCastInfo,Long> implements IVoterCastInfoDAO{

	public VoterCastInfoDAO()
	{
		super(VoterCastInfo.class);
	}
}
