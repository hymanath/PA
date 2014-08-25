package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateDetailsDAO;
import com.itgrids.partyanalyst.model.CandidateDetails;

public class CandidateDetailsDAO extends GenericDaoHibernate<CandidateDetails, Long> implements ICandidateDetailsDAO
{
	public CandidateDetailsDAO()
	{
		super(CandidateDetails.class);
	}
	
	
	
	/*public List<Object[]> getMptcCandidateDetails(Long electionId)
	{
		Query query = getSession().createSQLQuery("SELECT distinct c2.constituency_id,t.tehsil_name,c1.name,c2.name," +
				" ca.lastname,p.short_name,cr.rank,cr.votes_earned,cer.total_votes,CD.mobile_no," +
				" pp.short_name from constituency c1,constituency c2, party p, candidate_result cr, nomination n," +
				" booth b, constituency_election ce,tehsil t,constituency_election_result cer,candidate ca " +
				" Left outer join candidate_details CD on ca.candidate_id = CD.candidate_id " +
				" Left outer join party pp on CD.previous_party = pp.party_id " +
				" where " +
				" c1.constituency_id = b.constituency_id and " +
				" c2.tehsil_id = b.tehsil_id and " +
				" ca.candidate_id=n.candidate_id and " +
				" n.party_id=p.party_id and " +
				" c2.election_scope_id=3 and " +
				" b.tehsil_id=t.tehsil_id and " +
				" c2.constituency_id=ce.constituency_id and " +
				" n.nomination_id = cr.nomination_id and " +
				" ce.consti_elec_id = n.consti_elec_id and " +
				" b.publication_date_id =11 and " +
				" ce.election_id = :electionId and " +
				" cer.consti_elec_id = ce.consti_elec_id order by c2.constituency_id,t.tehsil_name");
		query.setParameter("electionId", electionId);
		return query.list();
	}*/
	
	/*public List<Object[]> getMptcCandidateDetails(Long electionId)
	{
		Query query = getSession().createSQLQuery("SELECT DISTINCT EC.constituency_id,T.tehsil_name,AC.name,EC.name," +
				"CA.lastname,P.short_name,CR.rank,CR.votes_earned,CER.total_votes,CA.candidate_id" +
				" from " +
				" constituency AC,constituency EC, party P, candidate_result CR, nomination N, " +
				" constituency_tehsil CT, constituency_election CE,tehsil T," +
				" constituency_election_result CER,candidate CA  " +
				//" Left outer join candidate_details CD on CA.candidate_id = CD.candidate_id  " +
				//" Left outer join party PP on CD.previous_party = PP.party_id where  " +
				" where AC.constituency_id = CT.constituency_id and  " +
				" T.tehsil_id = CT.tehsil_id and  " +
				" CA.candidate_id = N.candidate_id and  " +
				" N.party_id = P.party_id and  " +
				" EC.election_scope_id = 3 and " +
				" EC.tehsil_id = T.tehsil_id and  " +
				" EC.constituency_id = CE.constituency_id and  " +
				" N.nomination_id = CR.nomination_id and  " +
				" CE.consti_elec_id = N.consti_elec_id and  " +
				" CE.election_id = :electionId and  " +
				" CER.consti_elec_id = CE.consti_elec_id " +
				" order by EC.constituency_id,T.tehsil_name");
		query.setParameter("electionId", electionId);
		query.setFirstResult(0);
		query.setMaxResults(100);
		return query.list();
	}*/
	
	
	
   public List<Object[]> getMobileAndPreviousParty(List<Long> candidateIds)
	{
	
		Query query = getSession().createQuery("select distinct model.candidate.candidateId,model.previousParty.shortName,model.mobileno from CandidateDetails model" +
				" where model.candidate.candidateId in(:candidateIds)");
		query.setParameterList("candidateIds", candidateIds);
		return query.list();
		
	}
   
  public List<Object[]> getMptcCandidateDetails(Long electionId)
	{
		Query query = getSession().createQuery("SELECT DISTINCT EC.constituencyId,T.tehsilName,AC.name,EC.name," +
				"CA.lastname,P.shortName,CR.rank,CR.votesEarned,CER.totalVotes,CA.candidateId" +
				" from" +
				" Constituency AC,Constituency EC,Party P,Nomination N,Tehsil T,CandidateResult CR,ConstituencyTehsil CT,ConstituencyElection CE," +
				"ConstituencyElectionResult CER,Candidate CA" +
				" where AC.constituencyId = CT.constituency.constituencyId and" +
				" T.tehsilId = CT.tehsil.tehsilId and" +
				" CA.candidateId = N.candidate.candidateId and" +
				" N.party.partyId = P.partyId and" +
				" EC.electionScope.electionScopeId = 3 and" +
				" EC.tehsil.tehsilId = T.tehsilId and" +
				" EC.constituencyId = CE.constituency.constituencyId and" +
				" N.nominationId = CR.nomination.nominationId and" +
				" CE.constiElecId = N.constituencyElection.constiElecId and" +
				" CE.election.electionId = :electionId and" +
				" CER.constituencyElection.constiElecId = CE.constiElecId" +
				" order by EC.constituencyId,T.tehsilName");
		query.setParameter("electionId", electionId);
		query.setFirstResult(0);
		query.setMaxResults(100);
		return query.list();
	}
   
   
   public List<Object[]> getZptcCandidateDetails(Long electionId)
	{
	   Query query = getSession().createQuery("SELECT DISTINCT EC.constituencyId,T.tehsilName,AC.name,EC.name," +
				"CA.lastname,P.shortName,CR.rank,CR.votesEarned,CER.totalVotes,CA.candidateId" +
				" from" +
				" Constituency AC,Constituency EC,Party P,Nomination N,Tehsil T,CandidateResult CR,ConstituencyTehsil CT,ConstituencyElection CE," +
				"ConstituencyElectionResult CER,Candidate CA" +
				" where AC.constituencyId = CT.constituency.constituencyId and" +
				" T.tehsilId = CT.tehsil.tehsilId and" +
				" CA.candidateId = N.candidate.candidateId and" +
				" N.party.partyId = P.partyId and" +
				" EC.electionScope.electionScopeId = 4 and" +
				" EC.tehsil.tehsilId = T.tehsilId and" +
				" EC.constituencyId = CE.constituency.constituencyId and" +
				" N.nominationId = CR.nomination.nominationId and" +
				" CE.constiElecId = N.constituencyElection.constiElecId and" +
				" CE.election.electionId = :electionId and" +
				" CER.constituencyElection.constiElecId = CE.constiElecId" +
				" order by EC.constituencyId,T.tehsilName");
		query.setParameter("electionId", electionId);
		query.setFirstResult(0);
		query.setMaxResults(100);
		return query.list();
	}
 /*public List<Object[]> getMptcCandidateDetails(Long electionId)
	{
		Query query = getSession().createQuery("SELECT DISTINCT N.constituencyElection.constituency.constituencyId,CT.tehsil.tehsilName,AC.name,EC.name," +
				" N.candidate.lastname,N.party.shortName,N.candidateResult.rank,N.candidateResult.votesEarned,N.constituencyElection.constituencyElectionResult.totalVotes,N.candidate.candidateId" +
				" from" +
				" Nomination N,ConstituencyTehsil CT,Constituency AC,Constituency EC" +
			   " where " +
				
				" N.constituencyElection.election.electionScope.electionScopeId = 3 and" +
				
				" N.constituencyElection.election.electionId = :electionId" +
				" and AC.constituencyId = CT.constituency.constituencyId and EC.tehsil.tehsilId = CT.tehsil.tehsilId and N.constituencyElection.constituency.constituencyId = AC.constituencyId " +
				" order by N.constituencyElection.constituency.constituencyId,CT.tehsil.tehsilName");
		query.setParameter("electionId", electionId);
		query.setFirstResult(0);
		query.setMaxResults(100);
		return query.list();
	}
   */
   
}
