package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterDataInsertDAO;
import com.itgrids.partyanalyst.model.VoterDataInsert;

public class VoterDataInsertDAO extends GenericDaoHibernate<VoterDataInsert,Long> implements IVoterDataInsertDAO{
	
	public VoterDataInsertDAO(){
		
		super(VoterDataInsert.class);
	}

}
