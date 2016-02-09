package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreGoalDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreGoal;

public class TrainingCampCadreGoalDAO extends GenericDaoHibernate<TrainingCampCadreGoal, Long> implements ITrainingCampCadreGoalDAO{

	public TrainingCampCadreGoalDAO() {
		super(TrainingCampCadreGoal.class);
	}
    
	public List<Object[]> getGoalsDetailsforCadre(Long tdpCadreId,Long batchId){
		
		Query query=getSession().createQuery("" +
		" select model.trainingCampCadreGoalId,model.goal,model.achievedOn " +
		" from TrainingCampCadreGoal model " +
		" where model.tdpCadreId=:tdpCadreId and model.trainingCampBatchId =:trainingCampBatchId and model.goal is not null " +
		" and model.trainingCampBatch.attendeeTypeId=1 ");
		query.setParameter("tdpCadreId",tdpCadreId);
		query.setParameter("trainingCampBatchId",batchId);
		return query.list();
	}
   public Long checkGoalsForCadreBycadreAndBatch(Long tdpCadreId,Long batchId){
		
		Query query=getSession().createQuery("" +
		" select count(*) " +
		" from TrainingCampCadreGoal model " +
		" where model.tdpCadre.tdpCadreId=:tdpCadreId and model.trainingCampBatch.trainingCampBatchId =:trainingCampBatchId and model.goal is not null " +
		" and model.trainingCampBatch.attendeeType.attendeeTypeId=1 ");
		query.setParameter("tdpCadreId",tdpCadreId);
		query.setParameter("trainingCampBatchId",batchId);
		return (Long)query.uniqueResult();
	}
	public int deleteGoalsforACadre(Long tdpCadreId,Long batchId){
		
		Query query=getSession().createQuery(" delete from TrainingCampCadreGoal model " +
		" where model.tdpCadreId=:tdpCadreId and model.trainingCampBatchId =:trainingCampBatchId and model.goal is not null " +
		" and model.trainingCampBatch.attendeeTypeId=1 ");
		query.setParameter("tdpCadreId",tdpCadreId);
		query.setParameter("trainingCampBatchId",batchId);
		int count = query.executeUpdate();	
		return count;
	}
	
	public List<Long> trainingCampCadreGoalIds(Long tdpCadreId,Long batchId){
		
		Query query=getSession().createQuery(" select trainingCampCadreGoalId from TrainingCampCadreGoal model " +
		"where model.tdpCadreId=:tdpCadreId and model.trainingCampBatchId =:trainingCampBatchId and model.goal is not null " +
		" and model.trainingCampBatch.attendeeTypeId=1 ");
		query.setParameter("tdpCadreId",tdpCadreId);
		query.setParameter("trainingCampBatchId",batchId);
		return query.list();
	}
	public int deleteGoalsforACadre(List<Long> ids){
		if(ids != null && ids.size()>0){
			Query query=getSession().createQuery(" delete from TrainingCampCadreGoal model " +
			" where model.trainingCampCadreGoalId in(:ids)");
			query.setParameterList("ids",ids);
			int count = query.executeUpdate();	
			return count;
		}
		else
			
			return 0;
	}
	
}
