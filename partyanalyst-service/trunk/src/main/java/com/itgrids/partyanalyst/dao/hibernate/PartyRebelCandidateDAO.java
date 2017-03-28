package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.IPartyRebelCandidateDAO;
import com.itgrids.partyanalyst.model.PartyRebelCandidate;

public class PartyRebelCandidateDAO extends GenericDaoHibernate<PartyRebelCandidate, Long> implements 
   IPartyRebelCandidateDAO {

	
	public PartyRebelCandidateDAO() {
		super(PartyRebelCandidate.class);
	}

	@SuppressWarnings("unchecked")
	public List<PartyRebelCandidate> findByPartyIdAndElectionId(final Long partyId, final Long electionId) {
			return ( List<PartyRebelCandidate> ) getHibernateTemplate().execute( new HibernateCallback() {
				public Object doInHibernate( Session session ) throws HibernateException, SQLException {
	            		List<PartyRebelCandidate> rebelParties = session.createCriteria(PartyRebelCandidate.class)
	            							.createAlias("partyRebel", "rebel")
	            							.createAlias("rebel.election", "elec")
	            							.createAlias("rebel.party", "party")
	            							.add(Expression.eq("elec.electionId", electionId))
	            							.add(Expression.eq("party.partyId", partyId))
	            							.list();
	            		 return rebelParties;
	            }
	        });
	}
}
