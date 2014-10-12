package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterRelationDAO;
import com.itgrids.partyanalyst.model.VoterRelation;

public class VoterRelationDAO extends GenericDaoHibernate<VoterRelation, Long> implements IVoterRelationDAO{

	public VoterRelationDAO() {
		super(VoterRelation.class);
	}

}
