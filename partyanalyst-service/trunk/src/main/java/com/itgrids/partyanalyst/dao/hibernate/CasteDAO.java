package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.model.Caste;
import com.itgrids.partyanalyst.utils.IConstants;


public class CasteDAO extends GenericDaoHibernate<Caste, Long> implements ICasteDAO{

	public CasteDAO() {
		super(Caste.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteNamesByCasteId(String electionType, Long stateId, Long districtId,  Long constituencyId, String status)
	{

	String queryString = "select distinct model.candidate.candidateId,model.candidate.lastname from Nomination model where " +
			" model.constituencyElection.election.electionScope.electionType.electionType = :electionType and  model.constituencyElection.election.electionYear='"+IConstants.PRESENT_YEAR+"'" +
			" and model.constituencyElection.election.electionScope.state.stateId = :stateId and " +
			" model.constituencyElection.constituency.district.districtId = :districtId and  model.constituencyElection.constituency.constituencyId = :constituencyId ";
	if(status.equals("all"))
		queryString +=	"";
	if(status.equals("Winner"))
		queryString +=	" and model.candidateResult.rank = 1";
	else if(status.equals("Runner"))
		queryString +=	" and model.candidateResult.rank = 2";
	queryString += "group by model.candidate.candidateId ";
	Query query = getSession().createQuery(queryString);
	query.setParameter("electionType", electionType);
	query.setParameter("stateId", stateId);
	query.setParameter("districtId", districtId);
	query.setParameter("constituencyId", constituencyId);
	
	return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteNames(){
		Query query=getSession().createQuery("select model.casteId,model.casteName from Caste model");
		
		return query.list();
	}
	
	

/*	public Object casteNames(String searchText,Long stateId){
		StringBuffer queryBuffer = new StringBuffer("select model.caste.casteName from casteStatewise where state.stateId=? ");
	}*/
//select model.castestatewise.caste.casteName from candidateCaste model where model.castestatewise.state.stateId=? and model.castestatewise.caste.
	/*
	"select lastname from candidate where candidate_id in (select candidate_id from nomination 
	where nomination_id in (select
	nomination_id from candidate_result where candidate_result_id in
	(select candidate_result_id from candidate_result where
	nomination_id in (Select nomination_id from nomination where
	consti_elec_id = 10337) and rank=2)));
	*/
	
	
	/*	
	query.append("select model.lastname from Candidate model where ");
	query.append("model.candidate_id in (model.nomination.candidate_id from model.nomination");
	query.append("where model.nomination.nomination_id in(select model.nomination.nomination_id ");
	query.append("from model.candidate_result where model.candidate_result.candidate_result_id)");
	query.append("in (select model.nomination.nomination_id from model.nomination where");
	query.append("model.constituency_election.consti_elec_id=10337) and model.candidate_result.rank=2");
	
	
	return getHibernateTemplate().find(query.toString());

			
	*/
	
	
	/*public Object getCasteNamesByCasteId(Long casteId){
		
		Query query = getSession().createQuery("select model.casteName from Caste model where model.casteId = ?");
		query.setParameter(0,casteId);
		
		return query.list();
	}*/
	
	/*@SuppressWarnings("rawtypes")
	public List getCasteNames(Long casteCategoryGroupId){
		
		Query query = getSession().createQuery("select model.casteId,model.casteName from Caste model where model.casteId=?");
		query.setLong(0, casteCategoryGroupId);
		return null;
	}*/
	
	
}
