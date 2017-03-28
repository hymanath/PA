package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPredictedVoterCasteDAO;
import com.itgrids.partyanalyst.model.PredictedVoterCaste;

public class PredictedVoterCasteDAO extends GenericDaoHibernate<PredictedVoterCaste, Long> implements IPredictedVoterCasteDAO{

	public PredictedVoterCasteDAO() {
		super(PredictedVoterCaste.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPredictedCasteList()
	{
		Query query = getSession().createQuery("Select distinct model.casteState.casteStateId,model.casteState.caste.casteName from PredictedVoterCaste model" +
				" order by model.casteState.caste.casteName");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getVoterIdsFromCastePredictionForACaste(Long casteStateId,Integer firstRecord,Integer maxRecords)
	{
		Query query = getSession().createQuery("Select model.voter.voterId from PredictedVoterCaste model where model.casteState.casteStateId = :casteStateId ");
		query.setParameter("casteStateId",casteStateId);
		query.setFirstResult(firstRecord);
		query.setMaxResults(maxRecords);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getVoterIdsFromCastePrediction(Long userId,Long casteStateId,Integer firstRecord,Integer maxRecords)
	{
		Query query = getSession().createSQLQuery("SELECT PVC.voter_id from predicted_voter_caste PVC where PVC.caste_state_id = :casteStateId and PVC.voter_id not in (" +
				" SELECT UVD.voter_id from user_voter_details UVD where UVD.user_id = :userId and UVD.caste_state_id is not null and UVD.caste_insert_type_id != 1) ");
		query.setParameter("userId",userId);
		query.setParameter("casteStateId",casteStateId);
		query.setFirstResult(firstRecord);
		query.setMaxResults(maxRecords);
		return query.list();
	}
}
