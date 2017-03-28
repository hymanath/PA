package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.IPartyRebelDAO;
import com.itgrids.partyanalyst.model.PartyRebel;



public class PartyRebelDAO extends GenericDaoHibernate<PartyRebel, Long> 
implements IPartyRebelDAO {

	public PartyRebelDAO() {
		super(PartyRebel.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<PartyRebel> findByPartyIdAndElectionId(final Long partyId, final Long electionId) {
			return ( List<PartyRebel> ) getHibernateTemplate().execute( new HibernateCallback() {
	            public Object doInHibernate( Session session ) throws HibernateException, SQLException {
	            		List<PartyRebel> rebelParties = session.createCriteria(PartyRebel.class)
	            							.createAlias("election", "elec")
	            							.createAlias("party", "party")
	            							.add(Expression.eq("elec.electionId", electionId))
	            							.add(Expression.eq("party.partyId", partyId))
	            							.list();
	            		 return rebelParties;
	            }
	        });
	}
	
	
}
