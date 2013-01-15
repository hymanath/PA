package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.model.VoterFamilyInfo;

public class VoterFamilyInfoDAO extends GenericDaoHibernate<VoterFamilyInfo, Long> implements IVoterFamilyInfoDAO{

	public VoterFamilyInfoDAO()
	{
		super(VoterFamilyInfo.class);
	}
	
}
