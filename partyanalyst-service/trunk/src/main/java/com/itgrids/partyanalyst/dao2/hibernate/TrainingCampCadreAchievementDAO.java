package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreAchievementDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreAchievement;

public class TrainingCampCadreAchievementDAO extends GenericDaoHibernate<TrainingCampCadreAchievement, Long> implements ITrainingCampCadreAchievementDAO{

	public TrainingCampCadreAchievementDAO() {
		super(TrainingCampCadreAchievement.class);
	}
	
	public List<Object[]> getAchievmentDetailsforCadre(Long tdpCadreId,Long batchId){
		Query query=getSession().createQuery("" +
		" select model.trainingCampCadreAchievementId,model.achievement " +
		" from TrainingCampCadreAchievement model " +
		" where model.tdpCadreId=:tdpCadreId and model.trainingCampBatchId =:trainingCampBatchId and model.achievement is not null and " +
		" model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled ='false' ");
		query.setParameter("tdpCadreId",tdpCadreId);
		query.setParameter("trainingCampBatchId",batchId);
		return query.list();
	}
	public Long checkAchievementsForCadreBycadreAndBatch(Long tdpCadreId,Long batchId){
		
		Query query=getSession().createQuery("" +
		" select count(*) " +
		" from TrainingCampCadreAchievement model " +
		" where model.tdpCadreId=:tdpCadreId and model.trainingCampBatchId =:trainingCampBatchId and model.achievement is not null " +
		" and model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled ='false' ");
		query.setParameter("tdpCadreId",tdpCadreId);
		query.setParameter("trainingCampBatchId",batchId);
		return (Long)query.uniqueResult();
	}
	
	public List<Long> getTrainingCampCadreAchievementIdsList(Long tdpCadreId,Long batchId)	
	{
		Query query=getSession().createQuery(" select distinct model.trainingCampCadreAchievementId from TrainingCampCadreAchievement model " +
				" where model.tdpCadreId=:tdpCadreId and model.trainingCampBatchId =:trainingCampBatchId and model.achievement is not null " +
				" and model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled ='false' ");
				query.setParameter("tdpCadreId",tdpCadreId);
				query.setParameter("trainingCampBatchId",batchId);
			
		return query.list();
	}
	
	
	public int deleteAchievementsforACadre(List<Long> trainingCampCadreAchievementIdsList)
	{
		try{
			Query query=getSession().createQuery(" delete from TrainingCampCadreAchievement model " +
					" where model.trainingCampCadreAchievementId in (:trainingCampCadreAchievementIdsList) ");
			query.setParameterList("trainingCampCadreAchievementIdsList",trainingCampCadreAchievementIdsList);
			int count = query.executeUpdate();	
			return count;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
