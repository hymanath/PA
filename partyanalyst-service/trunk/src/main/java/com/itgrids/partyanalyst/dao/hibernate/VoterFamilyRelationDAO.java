package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterFamilyRelationDAO;
import com.itgrids.partyanalyst.model.VoterFamilyRelation;


public class VoterFamilyRelationDAO extends GenericDaoHibernate<VoterFamilyRelation,Long> implements IVoterFamilyRelationDAO {
	
	public VoterFamilyRelationDAO() {
		super(VoterFamilyRelation.class);
	}
	
	public List<Object[]> getAllRelations(){
		Query qry=getSession().createQuery("select model.voterFamilyRelationId, model.relation from VoterFamilyRelation model ");
		return qry.list();
	}
}
