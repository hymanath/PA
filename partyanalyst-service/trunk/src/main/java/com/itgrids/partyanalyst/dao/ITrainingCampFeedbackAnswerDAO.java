package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampFeedbackAnswer;

public interface ITrainingCampFeedbackAnswerDAO extends GenericDao<TrainingCampFeedbackAnswer, Long>{
	public List<Object[]> getFeedbackDetailsForCadre(Long cadreId);
	public List<Object[]> getAnswresCountForCadreWise(List<Long> tdpCadreIds);
	public List<Object[]> getFeedbackCategoryCountsCenterWise(Long programId);
	public List<Object[]> getFeedbackDetailsOfEachDistrictAndConstituencyWise(List<Long> districtIds,List<Long> constituencIds,List<Long> categoryIds,Long programId,String type);
	public List<Object[]> getFeedbackDetailsOfCadre(Long locationId,Long programId,String type);
	public List<Object[]> getFeedBackMembersCountProgramWise();
}
