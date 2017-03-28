package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDocument;

public interface ITrainingCampCadreFeedbackDocumentDAO extends GenericDao<TrainingCampCadreFeedbackDocument, Long> {
	public List<Object[]> getFeedbackDocuments(Long tdpCadreId);
	public List<Object[]> getDocumentsCountForCadreWise(List<Long> tdpCadreIds);
	public List<Object[]> getFeedBackDocumentsCountProgramWise(Date startDate,Date endDate);
}
