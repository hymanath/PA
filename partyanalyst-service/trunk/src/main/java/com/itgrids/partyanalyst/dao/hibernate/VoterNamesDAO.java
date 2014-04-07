package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterNamesDAO;
import com.itgrids.partyanalyst.model.VoterNames;

public class VoterNamesDAO extends GenericDaoHibernate<VoterNames, Long> implements IVoterNamesDAO{

	public VoterNamesDAO() {
		super(VoterNames.class);
		// TODO Auto-generated constructor stub
	}
	
	
}
