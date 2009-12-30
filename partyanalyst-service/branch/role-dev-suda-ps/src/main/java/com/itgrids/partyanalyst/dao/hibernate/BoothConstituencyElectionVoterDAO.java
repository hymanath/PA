package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.model.BoothConstituencyElectionVoter;

public class BoothConstituencyElectionVoterDAO extends GenericDaoHibernate<BoothConstituencyElectionVoter, Long> implements IBoothConstituencyElectionVoterDAO{
	public BoothConstituencyElectionVoterDAO(){
		super(BoothConstituencyElectionVoter.class);
	}

	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElectionVoter> findByBoothConstituencyElection(Long boothConstituencyElectionId) {
		return getHibernateTemplate().find("from BoothConstituencyElectionVoter model where " +
				"model.boothConstituencyElection.boothConstituencyElectionId = ?", boothConstituencyElectionId);
	}

	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElectionVoter> findByBoothConstituencyElectionAndVoter(Long boothConstituencyElectionId, Long voterId) {
		Object[] params = {boothConstituencyElectionId, voterId};
		return getHibernateTemplate().find("from BoothConstituencyElectionVoter model where " +
				"model.boothConstituencyElection.boothConstituencyElectionId = ? and " +
				"model.voter.voterId = ?", params);
	}

	public List findCastFromVoterByConstituencyAndElection(Long constituencyID, String electionYear){
		StringBuilder query = new StringBuilder();
		query.append("Select model.voter.cast,");
		query.append("count(model.voter.cast) ");
		query.append("from BoothConstituencyElectionVoter model where ");
		query.append("model.boothConstituencyElection.constituencyElection.constituency.constituencyId = "+constituencyID);
		query.append(" and model.boothConstituencyElection.constituencyElection.election.electionYear = "+electionYear);
		query.append(" group by model.voter.cast");
		return getHibernateTemplate().find(query.toString());
	}
	
	
	public List findVotersByConstituencyAndElectionForGender(Long constituencyID, String electionYear){
		StringBuilder query = new StringBuilder();
				query.append("Select model.voter.gender,count(model.voter.gender)");
				query.append("from BoothConstituencyElectionVoter model where ");
				query.append("model.boothConstituencyElection.constituencyElection.constituency.constituencyId = "+constituencyID);
				query.append(" and model.boothConstituencyElection.constituencyElection.election.electionYear = "+electionYear);
				query.append(" and gender like 'm%'");
				return getHibernateTemplate().find(query.toString());
	}
}
