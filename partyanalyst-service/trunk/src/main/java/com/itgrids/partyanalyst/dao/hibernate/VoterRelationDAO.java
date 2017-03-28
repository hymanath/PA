package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterRelationDAO;
import com.itgrids.partyanalyst.model.VoterRelation;

public class VoterRelationDAO extends GenericDaoHibernate<VoterRelation, Long> implements IVoterRelationDAO{

	public VoterRelationDAO() {
		super(VoterRelation.class);
	}
	
	public List<Object[]> getAllRelationDetails(){
		Query query = getSession().createQuery("select model.voterRelationId,model.description from VoterRelation model");
		
		return query.list();
	}

	public List<Object[]> getRelationDetails(List<String> description) {
		Query query = getSession().createQuery(
				"select model.voterRelationId,model.description from VoterRelation model"
						+ " where model.description in (:description)");
		query.setParameterList("description", description);
		return query.list();
	}
	
}
