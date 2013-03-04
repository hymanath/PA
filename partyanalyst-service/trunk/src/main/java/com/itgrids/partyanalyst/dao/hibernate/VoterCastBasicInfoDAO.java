package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.model.VoterCastBasicInfo;

public class VoterCastBasicInfoDAO extends GenericDaoHibernate<VoterCastBasicInfo, Long> implements IVoterCastBasicInfoDAO{

	public VoterCastBasicInfoDAO() {
		super(VoterCastBasicInfo.class);
	}

	
}
