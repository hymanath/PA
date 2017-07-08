package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDocument;

public interface ITrainingCampCadreFeedbackDocumentDAO extends GenericDao<TrainingCampCadreFeedbackDocument, Long> {
	public List<Object[]> getFeedbackDocuments(Long tdpCadreId,Long enrollmentYearId);
	public List<Object[]> getDocumentsCountForCadreWise(List<Long> tdpCadreIds,Long enrollmentYearId);
	public List<Object[]> getFeedBackDocumentsCountProgramWise(Date startDate,Date endDate);
}
