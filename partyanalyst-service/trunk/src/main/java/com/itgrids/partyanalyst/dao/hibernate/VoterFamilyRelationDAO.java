package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterFamilyRelationDAO;
import com.itgrids.partyanalyst.model.VoterFamilyRelation;


public class VoterFamilyRelationDAO extends GenericDaoHibernate<VoterFamilyRelation,Long> implements IVoterFamilyRelationDAO {
	
	public VoterFamilyRelationDAO() {
		super(VoterFamilyRelation.class);
	}
	
	
}
