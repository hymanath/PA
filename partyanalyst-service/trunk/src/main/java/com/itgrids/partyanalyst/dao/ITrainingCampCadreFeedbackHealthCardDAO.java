package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackHealthCard;

public interface ITrainingCampCadreFeedbackHealthCardDAO extends GenericDao<TrainingCampCadreFeedbackHealthCard, Long>{
	public List<Object[]> getHealthCardAttachments(Long tdpCadreId);
}
