package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.model.VoterAgeInfo;

public class VoterAgeInfoDAO extends GenericDaoHibernate<VoterAgeInfo, Long> implements IVoterAgeInfoDAO{

	public VoterAgeInfoDAO(){
		super(VoterAgeInfo.class);
	}
}
