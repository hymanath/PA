package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampCadreAchievement;

public interface ITrainingCampCadreAchievementDAO extends GenericDao<TrainingCampCadreAchievement, Long>{

	public List<Object[]> getAchievmentDetailsforCadre(Long tdpCadreId,Long batchId);
	public Long checkAchievementsForCadreBycadreAndBatch(Long tdpCadreId,Long batchId);
	public int deleteAchievementsforACadre(List<Long> trainingCampCadreAchievementIdsList);
	public List<Long> getTrainingCampCadreAchievementIdsList(Long tdpCadreId,Long batchId);
}
