package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterPartyInfoDAO;
import com.itgrids.partyanalyst.model.VoterPartyInfo;

public class VoterPartyInfoDAO extends GenericDaoHibernate<VoterPartyInfo, Long>implements IVoterPartyInfoDAO{

	public VoterPartyInfoDAO() {
		super(VoterPartyInfo.class);
		
	}
	

}
