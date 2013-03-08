package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.model.VoterModificationAgeInfo;

public class VoterModificationAgeInfoDAO extends GenericDaoHibernate<VoterModificationAgeInfo,Long> implements IVoterModificationAgeInfoDAO
{

	public VoterModificationAgeInfoDAO() {
		super(VoterModificationAgeInfo.class);
		
	}

}
