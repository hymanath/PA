package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.jdbc.object.SqlQuery;

import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.model.PartyTrends;
import com.itgrids.partyanalyst.model.VoterFamilyCount;

public class PartyTrendsDAO extends GenericDaoHibernate<PartyTrends, Long> implements IPartyTrendsDAO{

	public PartyTrendsDAO() {
		super(PartyTrends.class);
	}
	
	public List<?> loadConst() {
		
		Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name from   PartyTrends model  group by  model.constituency.constituencyId order by model.constituency.name ");
		return query.list();
		//Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name from   PartyTrends model  group by  model.constituency.constituencyId order by model.constituency.name ");
	}
	
	public List<?> loadEntitiesForXl(List<Long> constIds) {
		Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name,model.name,model.pervTrenzWt,model.prpWt,model.youngVotersWt,model.totalWt,model.id,model.type  from   PartyTrends model  where model.constituency.constituencyId in(:constIds)  order by model.constituency.name asc,model.totalWt desc ");
	
		query.setParameterList("constIds", constIds);
		return query.list();
		
	}
	public List<Object[]> findAssemblyConstituenciesForSimaAndra(Long electionScopeId,Long staetId,List<String> areas,List<Long> districIds) {
		
		Query query = getSession().createQuery("select model.constituencyId , model.name ,model.areaType from Constituency model   " +
				"  where model.state.stateId= :stateId  and model.electionScope.electionScopeId=:electionScopeId  and  model.areaType in (:areas) and model.deformDate is null   "   +
				"order by model.name");
		
		
		//query.setParameterList("parliamentConstituencyIds", parliamentConstituencyIds);
		query.setParameter("electionScopeId", electionScopeId);
		query.setParameter("stateId", staetId);
		query.setParameterList("areas", areas);
		


		return query.list();
	}
	
	public List<?> loadConst(List<Long> constIds) {
		
		Query query= getSession().createQuery("select model.constituencyId from   Constituency model  where  model.constituencyId  in(:constIds)  and model.constituencyId not in(select distinct model1.constituency.constituencyId from PartyTrends model1 ) ");
		query.setParameterList("constIds", constIds);
		return query.list();
		//Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name from   PartyTrends model  group by  model.constituency.constituencyId order by model.constituency.name ");
	}
	
   public List<Long> getConstituencyIds() {
	   List<Long> constiIds = new ArrayList<Long>();
	   constiIds.add(108l);
	   constiIds.add(109l);
	   constiIds.add(111l);
	   constiIds.add(112l);
	   constiIds.add(114l);
	   constiIds.add(122l);
	   constiIds.add(125l);
	   constiIds.add(133l);
	   constiIds.add(135l);
	   constiIds.add(136l);
	   constiIds.add(141l);
	   constiIds.add(157l);
	   constiIds.add(171l);
	   constiIds.add(176l);
	   constiIds.add(192l);
	   constiIds.add(211l);
	   constiIds.add(222l);
	   constiIds.add(224l);
	   constiIds.add(225l);
	   constiIds.add(226l);
	   constiIds.add(227l);
	   constiIds.add(236l);
	   constiIds.add(244l);
	   constiIds.add(245l);
	   constiIds.add(248l);
	   constiIds.add(250l);
	   constiIds.add(251l);
	   constiIds.add(263l);
	   constiIds.add(264l);
	   constiIds.add(265l);
	   constiIds.add(271l);
	   constiIds.add(281l);
	   constiIds.add(283l);
	   constiIds.add(288l);
	   constiIds.add(289l);
	   constiIds.add(300l);
	   constiIds.add(301l);
	   constiIds.add(302l);
	   constiIds.add(303l);
	   constiIds.add(305l);
	   constiIds.add(327l);
	   constiIds.add(329l);
	   constiIds.add(330l);
	   constiIds.add(332l);
	   constiIds.add(333l);
	   constiIds.add(334l);
	   constiIds.add(352l);
	   constiIds.add(353l);
	   constiIds.add(359l);
	   constiIds.add(361l);

		Query query= getSession().createQuery("select distinct model.constituencyId from   PartyTrends model  where  model.constituencyId  not in(:constiIds)   ");
		query.setParameterList("constiIds", constiIds);
		return query.list();
		//Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name from   PartyTrends model  group by  model.constituency.constituencyId order by model.constituency.name ");
	}

    public List<PartyTrends> getAllTrends(Long constiId){
    	Query query= getSession().createQuery("select  model from   PartyTrends model  where  model.constituencyId  = :constiId  order by  model.totalWt desc");
		query.setParameter("constiId", constiId);
		return query.list();
    }
  public List<Object[]> getPreviousTrendsData(Long constId)
	{
		Query   q=      getSession().createSQLQuery("select election_year,total_votes,total_votes_polled,p.short_name,p.party_id,cr1.votes_earned,(cr1.votes_earned-cr2.votes_earned),round((( cr1.votes_earned-cr2.votes_earned)/total_votes_polled * 100 ),1) AS marginpercentage,round((cr1.votes_earned / total_votes_polled * 100),2) AS tdppercentage,p1.short_name,p1.party_id,cr2.votes_earned,round(( cr2.votes_earned/total_votes_polled * 100 ),2) AS incpercentage,'others',sum(cr3.votes_earned),round(( sum(cr3.votes_earned)/total_votes_polled * 100 ),2),rejected_votes from  constituency_election ce join election e on  ce.election_id=e.election_id join constituency_election_result cr on cr.consti_elec_id= ce.consti_elec_id join nomination n on ce.consti_elec_id=n.consti_elec_id join nomination n1 on ce.consti_elec_id=n1.consti_elec_id join nomination n2 on ce.consti_elec_id=n2.consti_elec_id join party p on p.party_id=n.party_id  join candidate_result cr1 on n.nomination_id=cr1.nomination_id join party p1 on p1.party_id=n1.party_id join candidate_result cr2 on n1.nomination_id=cr2.nomination_id    join party p2 on p2.party_id=n2.party_id join candidate_result cr3 on n2.nomination_id=cr3.nomination_id  join constituency c on ce.constituency_id=c.constituency_id where e.election_scope_id=2 and ce.constituency_id=? and p.party_id =872 and p1.party_id=362  and p2.party_id not in(662,362,872)  group by election_year,sub_type  order by election_year desc ");
	  
		  q.setBigDecimal(0, new BigDecimal(constId));
		  return q.list();
	}
    public List<Object[]> getPreviousForPrp(Long constId)
   	{
   		  Query q=      getSession().createSQLQuery("select election_year,p.short_name,p.party_id,cr1.votes_earned,round(( cr1.votes_earned/total_votes_polled * 100 ),2) AS tdppercentage from  constituency_election ce join election e on  ce.election_id=e.election_id join constituency_election_result cr on cr.consti_elec_id= ce.consti_elec_id join nomination n on ce.consti_elec_id=n.consti_elec_id  join party p on p.party_id=n.party_id  join candidate_result cr1 on n.nomination_id=cr1.nomination_id  join constituency c on ce.constituency_id=c.constituency_id where e.election_scope_id=2 and ce.constituency_id=? and p.party_id =662   group by election_year,sub_type  order by election_year desc ");
   		 q.setBigDecimal(0, new BigDecimal(constId));
   		  return q.list();
   	}
    public List<Object[]> getParliamentCountForPrpAndYsr(Long constId)
   	{
    	Query q=      getSession().createSQLQuery("SELECT  distinct  election_year ,sum( br.valid_votes) as tv  ,p.party_id,sum( cb.votes_earned),round(( sum(cb.votes_earned)/sum(br.valid_votes) * 100 ),2) AS tdppercentage from  constituency_election as ce join booth_constituency_election bc on ce.consti_elec_id=bc.consti_elec_id join nomination n on ce.consti_elec_id=n.consti_elec_id join booth b on b.booth_id=bc.booth_id join booth_result br  on  br.booth_constituency_election_id=bc.booth_constituency_election_id join election e on  ce.election_id=e.election_id join party p on p.party_id=n.party_id join candidate_booth_result cb on cb.booth_constituency_election_id=bc.booth_constituency_election_id and  cb.nomination_id=n.nomination_id where b.constituency_id=232 and e.election_scope_id=1 and p.party_id  in(662, 1117 ) group by election_year order by election_year desc ");
    	q.setBigDecimal(0, new BigDecimal(constId));
 		  return q.list();
   	}
    public List<Object[]> getParliamentCountForInc(Long constId)

    {
    	Query q=   getSession().createSQLQuery("SELECT  distinct  election_year ,sum( br.valid_votes) as tv  ,p.short_name,p.party_id,sum( cb.votes_earned),round(( sum(cb.votes_earned)/sum(br.valid_votes) * 100 ),2) AS tdppercentage,p1.short_name ,p1.party_id, sum(cb1.votes_earned),round(( sum(cb1.votes_earned)/sum(br.valid_votes) * 100 ),2) AS incpercentage from  constituency_election as ce join booth_constituency_election bc on ce.consti_elec_id=bc.consti_elec_id join nomination n on ce.consti_elec_id=n.consti_elec_id join booth b on b.booth_id=bc.booth_id join booth_result br  on  br.booth_constituency_election_id=bc.booth_constituency_election_id join election e on  ce.election_id=e.election_id join party p on p.party_id=n.party_id join candidate_booth_result cb on cb.booth_constituency_election_id=bc.booth_constituency_election_id and  cb.nomination_id=n.nomination_id join nomination n1 on ce.consti_elec_id=n1.consti_elec_id join party p1 on p1.party_id=n1.party_id join candidate_booth_result cb1 on cb1.booth_constituency_election_id=bc.booth_constituency_election_id and  cb1.nomination_id=n1.nomination_id where b.constituency_id=? and e.election_scope_id=1 and p1.party_id =362  and p.party_id=872   group by election_year order by election_year desc");     
    	q.setBigDecimal(0, new BigDecimal(constId));
		  return q.list();
    }
    
    //previous trends dataa queries
    
    public List<?> getPreviousTrendsData(List<Long> partyIds,Long constId)
    {
    	Query q= getSession().createQuery("select ce.election.electionYear,cer.totalVotes,cer.totalVotesPolled,n.party.partyId,sum(cr.votesEarned),cr.marginVotes,cr.marginVotesPercentage,(cr.votesEarned/cer.totalVotesPolled) * 100 ,cr.rank,ce.constituency.district.districtId,p.shortName,ce.election.electionId  from ConstituencyElection ce join ce.nominations  n join ce.constituencyElectionResult cer join n.candidateResult cr join n.party p  where ce.constituency.constituencyId=? and ce.election.electionScope.electionScopeId=2 group by  ce.election.electionYear,n.party.partyId ");
    	q.setParameter(0, constId);
    	return q.list();
    }
    
    
    public List<?> getPreviousTrendsDataForParleament(List<Long> partyIds,Long constId)
    {
    	Query q= getSession().createQuery("select ce.election.electionYear,sum(be.boothResult.validVotes),n.party.partyId,sum(cr.votesEarned),(sum(cr.votesEarned)/sum(be.boothResult.validVotes)) * 100,  " +
    			" b.constituency.district.districtId,n.candidateResult.rank,n.party.shortName,ce.election.electionId,ce.constituency.constituencyId,dc.delimitationConstituency.constituency.constituencyId,e.electionId  from ConstituencyElection ce " +
    			"  ,BoothConstituencyElection be " +
    			" ,Booth b   " +
    			", Nomination   n  " +
    			" ,CandidateBoothResult    cr     " +
    			",DelimitationConstituencyAssemblyDetails dc   "+
    			",Election  e "+
    			"where  " +
    			"e.electionScope.electionScopeId=2 and e.electionYear=ce.election.electionYear and "+
    			"dc.constituency.constituencyId= b.constituency.constituencyId and "+
    			"dc.delimitationConstituency.year<=ce.election.electionYear and "+
    			"dc.delimitationConstituency.constituency.constituencyId=ce.constituency.constituencyId and "+
    			"be.constituencyElection.constiElecId=ce.constiElecId and " +
    			"b.boothId= be.booth.boothId and " +
    			"n.constituencyElection.constiElecId=ce.constiElecId and " +
    			"cr.nomination.nominationId = n.nominationId and  cr.boothConstituencyElection.boothConstituencyElectionId= be.boothConstituencyElectionId and  " +
    			" ce.election.electionScope.electionScopeId=1 and b.constituency.constituencyId=? group by  ce.election.electionYear,n.party.partyId ");
    	q.setParameter(0, constId);
    	return q.list();
    }
    
    //getAllVotes
   
    
    /*@SuppressWarnings("unchecked")
	public List<Object[]> getPreviousTrendsDataForParleament(List<Long> partyIds,Long constituencyId)
    {
    	Query query = getSession().createQuery("select model.boothConstituencyElection.constituencyElection.election.electionYear,sum(model.boothConstituencyElection.boothResult.validVotes),model.nomination.party.partyId," +
    			" sum(model.votesEarned),sum(model.votesEarned)*100/sum(model.boothConstituencyElection.boothResult.validVotes),model.boothConstituencyElection.constituencyElection.constituency.district.districtId,model.nomination.candidateResult.rank from CandidateBoothResult model where " +
    			" model.boothConstituencyElection.constituencyElection.election.electionScope.electionScopeId = 1 and model.boothConstituencyElection.constituencyElection.constituency.constituencyId = :constituencyId " +
    			" group by model.boothConstituencyElection.constituencyElection.election.electionYear,model.nomination.party.partyId");
    	query.setParameter("constituencyId",constituencyId);
    	return query.list();
    }
    */
    public List<Object[]> getTotalVotersForConst(Long constId)
    {
    	Query q= getSession().createQuery("select ce.election.electionYear,cer.totalVotes  from ConstituencyElection ce join ce.nominations  n join ce.constituencyElectionResult cer join n.candidateResult cr where ce.constituency.constituencyId=? and ce.election.electionScope.electionScopeId=2 group by  ce.election.electionYear");
    	q.setParameter(0, constId);
    	return q.list();
    }
    public List<Object[]> getTotalVotersForConstFormBooth(Long constId,Long year)
    {
    	Query q= getSession().createQuery("select sum(b.totalVoters),b.year  from Booth b where   b.constituency.constituencyId=? and b.year=? group by b.year");
    	q.setParameter(0, constId);
    	q.setParameter(1, year);
    	return q.list();
    }
    
    // get count for aliance
    public List<?> getPreviousTrendsDataWithAlliance(List<Long> partyIds,Long constId,Long year)
    {
    	Query q= getSession().createQuery("select ce.election.electionYear,cer.totalVotes,cer.totalVotesPolled,n.party.partyId,sum(cr.votesEarned),cr.marginVotes,cr.marginVotesPercentage,(cr.votesEarned/cer.totalVotesPolled) * 100 ,cr.rank,ce.constituency.district.districtId,p.shortName,ce.election.electionId  from ConstituencyElection ce join ce.nominations  n join ce.constituencyElectionResult cer join n.candidateResult cr join n.party p  where ce.constituency.constituencyId=? and ce.election.electionScope.electionScopeId=2 and ce.election.electionYear=? and n.party.partyId in(:ids) group by  ce.election.electionYear,n.party.partyId order by cr.rank  ");
    	q.setParameter(0, constId);
    	q.setParameter(1, year.toString());
    	q.setParameterList("ids", partyIds);
    	return q.list();
    }
    
    // get alliance group from aliance
    
  //  "ElectionAlliance"
    public List<Long> getWithAlliance(Long partyIds,Long electionId)
    {
    	Query q= getSession().createQuery("select ag1.party.partyId   from ElectionAlliance ea ,AllianceGroup ag,AllianceGroup ag1   where ea.group.groupId = ag1.group.groupId and ea.group.groupId = ag.group.groupId and ea.election.electionId=? and ea.state.stateId=1 and ag.party.partyId=? ");
    	q.setParameter(0, electionId);
    	q.setParameter(1, partyIds);
    //	q.setParameter("ids", partyIds);
    	return q.list();
    }
    
    public List<Object[]> getPanchayatIds(Long districtId){
    	Query query = getSession().createQuery("select model.id,model.constituency.constituencyId from PartyTrends model where model.constituency.district.districtId =:districtId and model.type = 'Panchayat' and model.priority < 26");
    	query.setParameter("districtId", districtId);
    	return query.list();
    }
    
    
 public List<?> callStoredProcedure()
    
    {
    	Query query = getSession().createSQLQuery("CALL VOTER_FAMILY_Details_Booth7(:constituencyId,:minCount,:maxCount,:uuid,:publicationDateId)")
    			.addEntity(VoterFamilyCount.class)
    			.setParameter("constituencyId", 232).setParameter("minCount", 3).setParameter("maxCount", 100).setParameter("uuid", UUID.randomUUID().toString()).setParameter("publicationDateId", 8);
    	
    	return query.list();
    }
}
